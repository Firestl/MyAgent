package io.dcloud.feature.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.Build;
import android.os.ParcelUuid;
import com.igexin.push.core.b;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothOver21 extends BluetoothBaseAdapter {
    public BTScanCallback mScanCallback;
    public BluetoothLeScanner scanner;

    @TargetApi(21)
    public class BTScanCallback extends ScanCallback {
        public String __JS__FUNCTION;
        public String callbackId;
        public IWebview pwebview;
        public Map<String, DCBluetoothDevice> scanList;

        public BTScanCallback(BluetoothOver21 bluetoothOver21) {
            this(null, null);
        }

        public Map<String, DCBluetoothDevice> getScanList() {
            return this.scanList;
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            DCBluetoothDevice dCBluetoothDevice = new DCBluetoothDevice(scanResult);
            BluetoothOver21 bluetoothOver21 = BluetoothOver21.this;
            if (bluetoothOver21.allowDuplicatesDevice) {
                bluetoothOver21.execJsCallback(BluetoothBaseAdapter.CALLBACK_DEVICE_FOUND, StringUtil.format(this.__JS__FUNCTION, dCBluetoothDevice.toString()), true);
                return;
            }
            String address = scanResult.getDevice().getAddress();
            if (this.scanList.containsKey(address)) {
                return;
            }
            this.scanList.put(address, dCBluetoothDevice);
            BluetoothOver21.this.execJsCallback(BluetoothBaseAdapter.CALLBACK_DEVICE_FOUND, StringUtil.format(this.__JS__FUNCTION, dCBluetoothDevice.toString()), true);
        }

        public BTScanCallback(IWebview iWebview, String str) {
            this.__JS__FUNCTION = "{devices:[%s]}";
            this.pwebview = iWebview;
            this.callbackId = str;
            this.scanList = new HashMap();
        }
    }

    @Override // io.dcloud.feature.bluetooth.BluetoothBaseAdapter
    public void closeBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        BluetoothLeScanner bluetoothLeScanner;
        BTScanCallback bTScanCallback;
        super.closeBluetoothAdapter(iWebview, jSONArray);
        if (!this.isSearchBTDevice || Build.VERSION.SDK_INT < 21 || (bluetoothLeScanner = this.scanner) == null || (bTScanCallback = this.mScanCallback) == null) {
            return;
        }
        bluetoothLeScanner.stopScan(bTScanCallback);
        this.isSearchBTDevice = false;
    }

    @Override // io.dcloud.feature.bluetooth.BluetoothBaseAdapter
    public void dispose(String str) {
        super.dispose(str);
        if (!this.isSearchBTDevice || Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.scanner.stopScan(this.mScanCallback);
        this.isSearchBTDevice = false;
    }

    @Override // io.dcloud.feature.bluetooth.BluetoothBaseAdapter
    public void getBluetoothDevices(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        if (!this.isInit) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(DOMException.JSON_ERROR_INFO, 10000, "not init"), JSUtil.ERROR, true, false);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BTScanCallback bTScanCallback = this.mScanCallback;
        if (bTScanCallback != null) {
            Map<String, DCBluetoothDevice> scanList = bTScanCallback.getScanList();
            Iterator<String> it = scanList.keySet().iterator();
            while (it.hasNext()) {
                sb.append(scanList.get(it.next()).toString() + ",");
            }
        }
        if (sb.lastIndexOf(",") > 5) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format("{devices:[%s]}", sb.toString()), JSUtil.OK, true, false);
    }

    @Override // io.dcloud.feature.bluetooth.BluetoothBaseAdapter
    public void startBluetoothDevicesDiscovery(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray(IApp.ConfigProperty.CONFIG_SERVICES);
        this.allowDuplicatesDevice = jSONObjectOptJSONObject.optBoolean("allowDuplicatesKey", false);
        String strOptString2 = jSONObjectOptJSONObject.optString("interval");
        if (!this.isInit) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(DOMException.JSON_ERROR_INFO, 10000, "not init"), JSUtil.ERROR, true, false);
            return;
        }
        this.mScanCallback = new BTScanCallback(this);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.scanner = defaultAdapter.getBluetoothLeScanner();
        ArrayList arrayList = null;
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                ScanFilter.Builder builder2 = new ScanFilter.Builder();
                builder2.setServiceUuid(new ParcelUuid(UUID.fromString(jSONArrayOptJSONArray.optString(i))));
                arrayList.add(builder2.build());
            }
        }
        if (!PdrUtil.isEmpty(strOptString2)) {
            try {
                builder.setReportDelay(Long.parseLong(strOptString2));
            } catch (Exception unused) {
            }
        }
        this.scanner.startScan(arrayList, builder.build(), this.mScanCallback);
        Intent intent = new Intent();
        intent.setAction(this.STATUS_ACTION);
        intent.putExtra("android.bluetooth.adapter.extra.STATE", 12);
        iWebview.getContext().sendBroadcast(intent);
        this.isSearchBTDevice = true;
        Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(DOMException.JSON_ERROR_INFO, 0, b.B), JSUtil.OK, true, false);
    }

    @Override // io.dcloud.feature.bluetooth.BluetoothBaseAdapter
    public void stopBluetoothDevicesDiscovery(IWebview iWebview, JSONArray jSONArray) {
        BluetoothLeScanner bluetoothLeScanner;
        BTScanCallback bTScanCallback;
        String strOptString = jSONArray.optString(0);
        if (!this.isInit) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(DOMException.JSON_ERROR_INFO, 10000, "not init"), JSUtil.ERROR, true, false);
            return;
        }
        if (Build.VERSION.SDK_INT < 21 || (bluetoothLeScanner = this.scanner) == null || (bTScanCallback = this.mScanCallback) == null) {
            return;
        }
        bluetoothLeScanner.stopScan(bTScanCallback);
        this.isSearchBTDevice = false;
        Intent intent = new Intent();
        intent.setAction(this.STATUS_ACTION);
        intent.putExtra("android.bluetooth.adapter.extra.STATE", 12);
        iWebview.getContext().sendBroadcast(intent);
        Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(DOMException.JSON_ERROR_INFO, 0, b.B), JSUtil.OK, true, false);
    }
}
