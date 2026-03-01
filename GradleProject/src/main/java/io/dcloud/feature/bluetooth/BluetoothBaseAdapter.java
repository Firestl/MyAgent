package io.dcloud.feature.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.huawei.hms.framework.common.ExceptionCode;
import com.igexin.push.core.b;
import com.igexin.sdk.PushConsts;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothBaseAdapter implements MessageHandler.IMessages {
    public static String CALLBACK_ADAPTER_STATUS_CHANGED = "callback_adapter_status_changed";
    public static String CALLBACK_BLECHARACTERISTIC_VALUE_CHANGE = "callback_blecharacteristicvaluechange";
    public static String CALLBACK_CONNECTION_STATUS_CHANGED = "callback_connection_status_change";
    public static String CALLBACK_DEVICE_FOUND = "callback_device_found";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public Map<String, BluetoothGatt> bleConnected;
    public Map<String, HashMap<String, IWebview>> callbacks;
    public HashMap<String, BluetoothGatt> createGatt;
    public BTBluetoothGattCallback gattCallback;
    public String getDeviceRSSICallback;
    public IWebview getDeviceRSSIWebview;
    public boolean allowDuplicatesDevice = false;
    public boolean isInit = false;
    public boolean isSearchBTDevice = false;
    public final String _JS_FUNCTION = DOMException.JSON_ERROR_INFO;
    public String STATUS_ACTION = "io.dcloud.bluetooth.sendsearch";
    public BroadcastReceiver bluetoothStatuReceiver = new BroadcastReceiver() { // from class: io.dcloud.feature.bluetooth.BluetoothBaseAdapter.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                if (action.equalsIgnoreCase(BluetoothBaseAdapter.this.STATUS_ACTION)) {
                    BluetoothBaseAdapter.this.execJsCallback(BluetoothBaseAdapter.CALLBACK_ADAPTER_STATUS_CHANGED, StringUtil.format("{discovering:%b,available:%b}", Boolean.valueOf(BluetoothBaseAdapter.this.isSearchBTDevice), true), true);
                    return;
                }
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
            BluetoothBaseAdapter bluetoothBaseAdapter = BluetoothBaseAdapter.this;
            String str = BluetoothBaseAdapter.CALLBACK_ADAPTER_STATUS_CHANGED;
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(BluetoothBaseAdapter.this.isSearchBTDevice);
            objArr[1] = Boolean.valueOf(intExtra == 12);
            bluetoothBaseAdapter.execJsCallback(str, StringUtil.format("{discovering:%b,available:%b}", objArr), true);
        }
    };

    public class BTBluetoothGattCallback extends BluetoothGattCallback {
        public BTBluetoothGattCallback() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BluetoothBaseAdapter.this.execJsCallback(BluetoothBaseAdapter.CALLBACK_BLECHARACTERISTIC_VALUE_CHANGE, StringUtil.format("{deviceId:'%s',serviceId:'%s',characteristicId:'%s',value:'%s'}", bluetoothGatt.getDevice().getAddress().toUpperCase(), bluetoothGattCharacteristic.getService().getUuid().toString().toUpperCase(), bluetoothGattCharacteristic.getUuid().toString().toUpperCase(), BluetoothBaseAdapter.bytesToHexString(bluetoothGattCharacteristic.getValue())), true);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BluetoothBaseAdapter.this.execJsCallback(BluetoothBaseAdapter.CALLBACK_BLECHARACTERISTIC_VALUE_CHANGE, StringUtil.format("{deviceId:'%s',serviceId:'%s',characteristicId:'%s',value:'%s'}", bluetoothGatt.getDevice().getAddress().toUpperCase(), bluetoothGattCharacteristic.getService().getUuid().toString().toUpperCase(), bluetoothGattCharacteristic.getUuid().toString().toUpperCase(), BluetoothBaseAdapter.bytesToHexString(bluetoothGattCharacteristic.getValue())), true);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            String address = bluetoothGatt.getDevice().getAddress();
            if (i == 0 && i2 == 2) {
                bluetoothGatt.discoverServices();
                BluetoothBaseAdapter.this.bleConnected.put(address, bluetoothGatt);
                BluetoothBaseAdapter.this.execJsCallback(address, StringUtil.format(DOMException.JSON_ERROR_INFO, 0, b.B), false);
                BluetoothBaseAdapter.this.callbacks.remove(address);
                BluetoothBaseAdapter.this.execJsCallback(BluetoothBaseAdapter.CALLBACK_CONNECTION_STATUS_CHANGED, StringUtil.format("{deviceId:'%s',connected:%b}", address, true), true);
                return;
            }
            if (BluetoothBaseAdapter.this.bleConnected != null && BluetoothBaseAdapter.this.bleConnected.containsKey(address)) {
                ((BluetoothGatt) BluetoothBaseAdapter.this.bleConnected.get(address)).disconnect();
                ((BluetoothGatt) BluetoothBaseAdapter.this.bleConnected.get(address)).close();
                BluetoothBaseAdapter.this.bleConnected.remove(address);
            }
            if (i2 == 0) {
                BluetoothBaseAdapter.this.execJsCallback(address, StringUtil.format(DOMException.JSON_ERROR_INFO, 10012, "operate time out"), false);
                BluetoothBaseAdapter.this.callbacks.remove(address);
            }
            BluetoothBaseAdapter.this.execJsCallback(BluetoothBaseAdapter.CALLBACK_CONNECTION_STATUS_CHANGED, StringUtil.format("{deviceId:'%s',connected:%b}", address, false), true);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (BluetoothBaseAdapter.this.getDeviceRSSIWebview == null || BluetoothBaseAdapter.this.getDeviceRSSICallback == null) {
                return;
            }
            Deprecated_JSUtil.execCallback(BluetoothBaseAdapter.this.getDeviceRSSIWebview, BluetoothBaseAdapter.this.getDeviceRSSICallback, StringUtil.format("{RSSI:%d,code:%d}", Integer.valueOf(i), 0), JSUtil.OK, true, false);
            BluetoothBaseAdapter.this.getDeviceRSSIWebview = null;
            BluetoothBaseAdapter.this.getDeviceRSSICallback = null;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    private void execCallback(IWebview iWebview, String str, int i, String str2, int i2) {
        Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), str2), i2, true, false);
    }

    private BluetoothGatt getBluetoothGatt(String str) {
        Map<String, BluetoothGatt> map = this.bleConnected;
        if (map == null || !map.containsKey(str) || this.bleConnected.get(str) == null) {
            return null;
        }
        return this.bleConnected.get(str);
    }

    private HashMap<String, IWebview> getStringIWebviewHashMap(String str) {
        if (this.callbacks == null) {
            this.callbacks = new HashMap();
        }
        return new HashMap<>();
    }

    public static byte[] hexToByte(String str) {
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public boolean checkNull(IWebview iWebview, String str, String... strArr) {
        for (String str2 : strArr) {
            if (PdrUtil.isEmpty(str2)) {
                execCallback(iWebview, str, PushConsts.GET_DEVICETOKEN, "invalid data,please check parameters", JSUtil.ERROR);
                return true;
            }
        }
        return false;
    }

    public void closeBLEConnection(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optJSONObject(1).optString("deviceId");
        String strOptString2 = jSONArray.optString(0);
        if (checkNull(iWebview, strOptString2, strOptString)) {
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString2, 10000, "not init", JSUtil.ERROR);
            return;
        }
        Map<String, BluetoothGatt> map = this.bleConnected;
        if (map == null) {
            execCallback(iWebview, strOptString2, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        if (!map.containsKey(strOptString) || this.bleConnected.get(strOptString) == null) {
            HashMap<String, BluetoothGatt> map2 = this.createGatt;
            if (map2 != null && map2.containsKey(strOptString) && this.createGatt.get(strOptString) != null) {
                this.createGatt.get(strOptString).disconnect();
            }
        } else {
            this.bleConnected.get(strOptString).disconnect();
        }
        execCallback(iWebview, strOptString2, 0, b.B, JSUtil.OK);
    }

    public void closeBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!iWebview.getContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            execCallback(iWebview, strOptString, 10009, "system not support", JSUtil.ERROR);
            return;
        }
        if (defaultAdapter == null) {
            execCallback(iWebview, strOptString, 10010, "unsupport", JSUtil.ERROR);
            return;
        }
        if (!defaultAdapter.isEnabled()) {
            execCallback(iWebview, strOptString, 10001, "not available", JSUtil.ERROR);
            return;
        }
        this.isInit = false;
        Map<String, BluetoothGatt> map = this.bleConnected;
        if (map != null && map.size() > 0) {
            Iterator<String> it = this.bleConnected.keySet().iterator();
            while (it.hasNext()) {
                BluetoothGatt bluetoothGatt = this.bleConnected.get(it.next());
                bluetoothGatt.disconnect();
                bluetoothGatt.close();
            }
            this.bleConnected.clear();
        }
        HashMap<String, BluetoothGatt> map2 = this.createGatt;
        if (map2 != null) {
            map2.clear();
        }
        execCallback(iWebview, strOptString, 0, b.B, JSUtil.OK);
    }

    public void createBLEConnection(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONObjectOptJSONObject.optString("deviceId");
        String strOptString3 = jSONObjectOptJSONObject.optString("timeout");
        if (checkNull(iWebview, strOptString, strOptString2)) {
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.bleConnected == null) {
            this.bleConnected = new HashMap();
        }
        if (this.createGatt == null) {
            this.createGatt = new HashMap<>();
        }
        try {
            BluetoothDevice remoteDevice = defaultAdapter.getRemoteDevice(strOptString2);
            if (this.bleConnected.containsKey(strOptString2) && this.bleConnected.get(strOptString2) != null) {
                execCallback(iWebview, strOptString, -1, "already connect", JSUtil.ERROR);
                return;
            }
            HashMap<String, IWebview> stringIWebviewHashMap = getStringIWebviewHashMap("");
            stringIWebviewHashMap.put(strOptString, iWebview);
            this.callbacks.put(strOptString2, stringIWebviewHashMap);
            BluetoothGatt bluetoothGattConnectGatt = Build.VERSION.SDK_INT >= 23 ? remoteDevice.connectGatt(iWebview.getContext(), false, this.gattCallback, 2) : remoteDevice.connectGatt(iWebview.getContext(), false, this.gattCallback);
            this.createGatt.put(strOptString2, bluetoothGattConnectGatt);
            if (!jSONObjectOptJSONObject.has("timeout") || PdrUtil.isEmpty(strOptString3)) {
                return;
            }
            try {
                MessageHandler.sendMessage(this, Long.parseLong(strOptString3), bluetoothGattConnectGatt);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            execCallback(iWebview, strOptString, 10002, "no device", JSUtil.ERROR);
        }
    }

    public void dispose(String str) {
        Map<String, HashMap<String, IWebview>> map = this.callbacks;
        if (map != null) {
            HashMap<String, IWebview> map2 = map.get(CALLBACK_ADAPTER_STATUS_CHANGED);
            if (map2 != null) {
                for (String str2 : map2.keySet()) {
                    if (map2.get(str2) != null) {
                        map2.get(str2).getContext().unregisterReceiver(this.bluetoothStatuReceiver);
                    }
                }
            }
            this.callbacks.clear();
        }
        this.isInit = false;
        Map<String, BluetoothGatt> map3 = this.bleConnected;
        if (map3 == null || map3.size() <= 0) {
            return;
        }
        Iterator<String> it = this.bleConnected.keySet().iterator();
        while (it.hasNext()) {
            BluetoothGatt bluetoothGatt = this.bleConnected.get(it.next());
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        }
        this.bleConnected.clear();
    }

    public void execJsCallback(String str, String str2, boolean z) {
        HashMap<String, IWebview> map;
        Map<String, HashMap<String, IWebview>> map2 = this.callbacks;
        if (map2 == null || (map = map2.get(str)) == null) {
            return;
        }
        for (String str3 : map.keySet()) {
            Deprecated_JSUtil.execCallback(map.get(str3), str3, str2, JSUtil.OK, true, z);
        }
    }

    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
    public void execute(Object obj) {
        HashMap<String, IWebview> map;
        try {
            BluetoothGatt bluetoothGatt = (BluetoothGatt) obj;
            String address = bluetoothGatt.getDevice().getAddress();
            if ((!this.bleConnected.containsKey(address) || this.bleConnected.get(address) == null) && (map = this.callbacks.get(address)) != null && map.size() > 0) {
                Iterator<Map.Entry<String, IWebview>> it = map.entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry<String, IWebview> next = it.next();
                    IWebview value = next.getValue();
                    String key = next.getKey();
                    Iterator<BluetoothDevice> it2 = ((BluetoothManager) value.getContext().getSystemService("bluetooth")).getConnectedDevices(7).iterator();
                    while (it2.hasNext()) {
                        if (it2.next().getAddress().equals(address)) {
                            return;
                        }
                    }
                    bluetoothGatt.disconnect();
                    execCallback(value, key, 10012, "operate time out", JSUtil.ERROR);
                    this.callbacks.remove(address);
                }
            }
        } catch (Exception unused) {
        }
    }

    public void getBLEDeviceCharacteristics(IWebview iWebview, JSONArray jSONArray) {
        JSONObject jSONObject;
        String strOptString = jSONArray.optString(0);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONObjectOptJSONObject.optString("deviceId");
        String strOptString3 = jSONObjectOptJSONObject.optString("serviceId");
        if (checkNull(iWebview, strOptString, strOptString2, strOptString3)) {
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
            return;
        }
        Map<String, BluetoothGatt> map = this.bleConnected;
        if (map == null || !map.containsKey(strOptString2) || this.bleConnected.get(strOptString2) == null) {
            execCallback(iWebview, strOptString, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        BluetoothGattService service = this.bleConnected.get(strOptString2).getService(UUID.fromString(strOptString3));
        if (service == null) {
            execCallback(iWebview, strOptString, 10004, "no service", JSUtil.ERROR);
            return;
        }
        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < characteristics.size(); i++) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = characteristics.get(i);
            JSONObject jSONObject2 = null;
            try {
                jSONObject = new JSONObject();
            } catch (JSONException unused) {
            }
            try {
                jSONObject.put("uuid", bluetoothGattCharacteristic.getUuid().toString().toUpperCase());
                JSONObject jSONObject3 = new JSONObject();
                int properties = bluetoothGattCharacteristic.getProperties();
                jSONObject3.put(ExceptionCode.READ, (properties & 2) > 0);
                jSONObject3.put(ExceptionCode.WRITE, (properties & 8) > 0 || (properties & 4) > 0);
                jSONObject3.put("notify", (properties & 16) > 0);
                jSONObject3.put("indicate", (properties & 32) > 0);
                jSONObject.put("properties", jSONObject3);
            } catch (JSONException unused2) {
                jSONObject2 = jSONObject;
                jSONObject = jSONObject2;
            }
            jSONArray2.put(jSONObject);
        }
        Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format("{'characteristics':%s}", jSONArray2.toString()), JSUtil.OK, true, false);
    }

    public void getBLEDeviceRSSI(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optJSONObject(1).optString("deviceId");
        String strOptString2 = jSONArray.optString(0);
        if (!this.isInit) {
            execCallback(iWebview, strOptString2, 10000, "not init", JSUtil.ERROR);
            return;
        }
        if (checkNull(iWebview, strOptString2, strOptString)) {
            return;
        }
        Map<String, BluetoothGatt> map = this.bleConnected;
        if (map == null || !map.containsKey(strOptString) || this.bleConnected.get(strOptString) == null) {
            execCallback(iWebview, strOptString2, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        BluetoothGatt bluetoothGatt = this.bleConnected.get(strOptString);
        if (bluetoothGatt == null) {
            execCallback(iWebview, strOptString2, 10004, "no service", JSUtil.ERROR);
            return;
        }
        this.getDeviceRSSIWebview = iWebview;
        this.getDeviceRSSICallback = strOptString2;
        bluetoothGatt.readRemoteRssi();
    }

    public void getBLEDeviceServices(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optJSONObject(1).optString("deviceId");
        String strOptString2 = jSONArray.optString(0);
        if (checkNull(iWebview, strOptString2, strOptString)) {
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString2, 10000, "not init", JSUtil.ERROR);
            return;
        }
        BluetoothGatt bluetoothGatt = getBluetoothGatt(strOptString);
        if (bluetoothGatt == null) {
            execCallback(iWebview, strOptString2, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        JSONArray jSONArray2 = new JSONArray();
        for (BluetoothGattService bluetoothGattService : services) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("uuid", bluetoothGattService.getUuid().toString().toUpperCase());
                jSONObject.put("isPrimary", bluetoothGattService.getType() == 0);
            } catch (JSONException unused) {
            }
            jSONArray2.put(jSONObject);
        }
        Deprecated_JSUtil.execCallback(iWebview, strOptString2, StringUtil.format("{'services':%s}", jSONArray2.toString()), JSUtil.OK, true, false);
    }

    public void getBluetoothAdapterState(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        if (this.isInit) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format("{discovering:%b,available:true}", Boolean.valueOf(this.isSearchBTDevice)), JSUtil.OK, true, false);
        } else {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
        }
    }

    public void getBluetoothDevices(IWebview iWebview, JSONArray jSONArray) {
    }

    public void getConnectedBluetoothDevices(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        jSONArray.optJSONObject(1).optJSONArray(IApp.ConfigProperty.CONFIG_SERVICES);
        if (!this.isInit) {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
            return;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) iWebview.getContext().getSystemService("bluetooth");
        if (bluetoothManager != null) {
            List<BluetoothDevice> connectedDevices = bluetoothManager.getConnectedDevices(7);
            JSONArray jSONArray2 = new JSONArray();
            Map<String, BluetoothGatt> map = this.bleConnected;
            if (map != null && map.size() > 0) {
                try {
                    for (BluetoothDevice bluetoothDevice : connectedDevices) {
                        JSONObject jSONObject = new JSONObject();
                        if (this.bleConnected.containsKey(bluetoothDevice.getAddress())) {
                            jSONObject.put("name", bluetoothDevice.getName());
                            jSONObject.put("deviceId", bluetoothDevice.getAddress());
                            jSONArray2.put(jSONObject);
                        }
                    }
                } catch (Exception unused) {
                }
            }
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format("{devices:%s}", jSONArray2.toString()), JSUtil.OK, true, false);
        }
    }

    public void notifyBLECharacteristicValueChange(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONObjectOptJSONObject.optString("serviceId");
        String strOptString3 = jSONObjectOptJSONObject.optString("deviceId");
        String strOptString4 = jSONObjectOptJSONObject.optString("characteristicId");
        boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("state", true);
        if (checkNull(iWebview, strOptString, strOptString2, strOptString3, strOptString4)) {
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
            return;
        }
        BluetoothGatt bluetoothGatt = getBluetoothGatt(strOptString3);
        if (bluetoothGatt == null) {
            execCallback(iWebview, strOptString, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(strOptString2));
        if (service == null) {
            execCallback(iWebview, strOptString, 10004, "no service", JSUtil.ERROR);
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(strOptString4));
        if (characteristic == null) {
            execCallback(iWebview, strOptString, 10005, "no characteristic", JSUtil.ERROR);
            return;
        }
        boolean characteristicNotification = bluetoothGatt.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG));
        if (descriptor == null) {
            execCallback(iWebview, strOptString, 10008, "no descriptor", JSUtil.ERROR);
            return;
        }
        if (!zOptBoolean) {
            descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            bluetoothGatt.writeDescriptor(descriptor);
        } else if ((characteristic.getProperties() & 32) == 32) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            bluetoothGatt.writeDescriptor(descriptor);
        } else {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            bluetoothGatt.writeDescriptor(descriptor);
        }
        if (characteristicNotification) {
            execCallback(iWebview, strOptString, 0, b.B, JSUtil.OK);
        } else {
            execCallback(iWebview, strOptString, 10011, "notify fail", JSUtil.ERROR);
        }
    }

    public void onBLECharacteristicValueChange(IWebview iWebview, JSONArray jSONArray) {
        HashMap<String, IWebview> stringIWebviewHashMap = getStringIWebviewHashMap(CALLBACK_BLECHARACTERISTIC_VALUE_CHANGE);
        stringIWebviewHashMap.put(jSONArray.optString(0), iWebview);
        this.callbacks.put(CALLBACK_BLECHARACTERISTIC_VALUE_CHANGE, stringIWebviewHashMap);
    }

    public void onBLEConnectionStateChange(IWebview iWebview, JSONArray jSONArray) {
        HashMap<String, IWebview> stringIWebviewHashMap = getStringIWebviewHashMap(CALLBACK_CONNECTION_STATUS_CHANGED);
        stringIWebviewHashMap.put(jSONArray.optString(0), iWebview);
        this.callbacks.put(CALLBACK_CONNECTION_STATUS_CHANGED, stringIWebviewHashMap);
    }

    public void onBluetoothAdapterStateChange(IWebview iWebview, JSONArray jSONArray) {
        HashMap<String, IWebview> stringIWebviewHashMap = getStringIWebviewHashMap(CALLBACK_ADAPTER_STATUS_CHANGED);
        stringIWebviewHashMap.put(jSONArray.optString(0), iWebview);
        this.callbacks.put(CALLBACK_ADAPTER_STATUS_CHANGED, stringIWebviewHashMap);
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        IntentFilter intentFilter2 = new IntentFilter("android.bluetooth.device.action.ACL_CONNECTED");
        IntentFilter intentFilter3 = new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED");
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, new IntentFilter(this.STATUS_ACTION));
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, intentFilter);
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, intentFilter2);
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, intentFilter3);
    }

    public void onBluetoothDeviceFound(IWebview iWebview, JSONArray jSONArray) {
        HashMap<String, IWebview> stringIWebviewHashMap = getStringIWebviewHashMap(CALLBACK_DEVICE_FOUND);
        stringIWebviewHashMap.put(jSONArray.optString(0), iWebview);
        this.callbacks.put(CALLBACK_DEVICE_FOUND, stringIWebviewHashMap);
    }

    public void openBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!iWebview.getContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            execCallback(iWebview, strOptString, 10009, "system not support", JSUtil.ERROR);
            return;
        }
        if (defaultAdapter == null) {
            execCallback(iWebview, strOptString, 10010, "unsupport", JSUtil.ERROR);
            return;
        }
        if (!defaultAdapter.isEnabled()) {
            execCallback(iWebview, strOptString, 10001, "not available", JSUtil.ERROR);
            return;
        }
        defaultAdapter.enable();
        this.isInit = true;
        this.gattCallback = new BTBluetoothGattCallback();
        execCallback(iWebview, strOptString, 0, b.B, JSUtil.OK);
    }

    public void readBLECharacteristicValue(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONObjectOptJSONObject.optString("serviceId");
        String strOptString3 = jSONObjectOptJSONObject.optString("deviceId");
        String strOptString4 = jSONObjectOptJSONObject.optString("characteristicId");
        if (checkNull(iWebview, strOptString, strOptString2, strOptString3, strOptString4)) {
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
            return;
        }
        BluetoothGatt bluetoothGatt = getBluetoothGatt(strOptString3);
        if (bluetoothGatt == null) {
            execCallback(iWebview, strOptString, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(strOptString2));
        if (service != null) {
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(strOptString4));
            if (characteristic == null) {
                execCallback(iWebview, strOptString, 10005, "no characteristic", JSUtil.ERROR);
            } else if (bluetoothGatt.readCharacteristic(characteristic)) {
                execCallback(iWebview, strOptString, 0, b.B, JSUtil.OK);
            } else {
                execCallback(iWebview, strOptString, 10007, "property not support", JSUtil.ERROR);
            }
        }
    }

    public void setBLEMTU(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optJSONObject(1).optString("deviceId");
        String strOptString2 = jSONArray.optString(0);
        String strOptString3 = jSONArray.optJSONObject(1).optString("mtu");
        if (Build.VERSION.SDK_INT < 21) {
            execCallback(iWebview, strOptString2, 10004, "not support", JSUtil.ERROR);
            return;
        }
        if (!this.isInit) {
            execCallback(iWebview, strOptString2, 10000, "not init", JSUtil.ERROR);
            return;
        }
        if (checkNull(iWebview, strOptString2, strOptString, strOptString3)) {
            return;
        }
        try {
            int i = Integer.parseInt(strOptString3);
            if (i <= 22 || i > 512) {
                execCallback(iWebview, strOptString2, PushConsts.GET_DEVICETOKEN, "invalid data", JSUtil.ERROR);
            } else if (this.bleConnected == null || !this.bleConnected.containsKey(strOptString)) {
                execCallback(iWebview, strOptString2, 10004, "no connection", JSUtil.ERROR);
            } else {
                BluetoothGatt bluetoothGatt = this.bleConnected.get(strOptString);
                if (bluetoothGatt != null) {
                    bluetoothGatt.requestMtu(i);
                    execCallback(iWebview, strOptString2, 0, b.B, JSUtil.OK);
                } else {
                    execCallback(iWebview, strOptString2, 10004, "no service", JSUtil.ERROR);
                }
            }
        } catch (Exception unused) {
            execCallback(iWebview, strOptString2, PushConsts.GET_DEVICETOKEN, "parameter.mtu should be Number", JSUtil.ERROR);
        }
    }

    public void startBluetoothDevicesDiscovery(IWebview iWebview, JSONArray jSONArray) {
    }

    public void stopBluetoothDevicesDiscovery(IWebview iWebview, JSONArray jSONArray) {
    }

    public void writeBLECharacteristicValue(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONObjectOptJSONObject.optString("serviceId");
        String strOptString3 = jSONObjectOptJSONObject.optString("deviceId");
        String strOptString4 = jSONObjectOptJSONObject.optString("characteristicId");
        String strOptString5 = jSONObjectOptJSONObject.optString("value");
        if (checkNull(iWebview, strOptString, strOptString2, strOptString3, strOptString4, strOptString5)) {
            return;
        }
        byte[] bArrHexToByte = hexToByte(strOptString5);
        if (!this.isInit) {
            execCallback(iWebview, strOptString, 10000, "not init", JSUtil.ERROR);
            return;
        }
        BluetoothGatt bluetoothGatt = getBluetoothGatt(strOptString3);
        if (bluetoothGatt == null) {
            execCallback(iWebview, strOptString, 10004, "no connection", JSUtil.ERROR);
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(strOptString2));
        if (service != null) {
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(strOptString4));
            if (characteristic == null) {
                execCallback(iWebview, strOptString, 10005, "no characteristic", JSUtil.ERROR);
                return;
            }
            characteristic.setValue(bArrHexToByte);
            if (bluetoothGatt.writeCharacteristic(characteristic)) {
                execCallback(iWebview, strOptString, 0, b.B, JSUtil.OK);
            } else {
                execCallback(iWebview, strOptString, 10007, "property not support", JSUtil.ERROR);
            }
        }
    }
}
