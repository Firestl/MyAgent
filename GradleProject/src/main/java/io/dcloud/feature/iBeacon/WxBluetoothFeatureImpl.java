package io.dcloud.feature.iBeacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.speech.utils.AsrError;
import com.umeng.analytics.pro.bm;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.StringUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class WxBluetoothFeatureImpl extends StandardFeature {
    public static final int MSG_BEACON_SCAN_RESTART_NOTIFY = 2;
    public static final int MSG_BEACON_SCAN_UPDATE_NOTIFY = 1;
    public static final String TAG = "WxBluetoothFeatureImpl";
    public IBeaconContainer iBeaconContainer = new IBeaconContainer();
    public boolean mBeaconScanFlag = false;
    public long beaconScanUpdateMillis = 1000;
    public long beaconScanRestartMillis = 15000;
    public Handler handler = new Handler() { // from class: io.dcloud.feature.iBeacon.WxBluetoothFeatureImpl.1
        @Override // android.os.Handler
        @TargetApi(18)
        public void handleMessage(Message message) {
            BluetoothAdapter defaultAdapter;
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                WxBluetoothFeatureImpl.this.processBeaconRecords();
                WxBluetoothFeatureImpl.this.startBeaconScanNotifyCheck();
            } else {
                if (i != 2) {
                    return;
                }
                if (WxBluetoothFeatureImpl.this.mBeaconScanFlag && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null && defaultAdapter.isEnabled()) {
                    defaultAdapter.stopLeScan(WxBluetoothFeatureImpl.this.leScanCallback);
                    defaultAdapter.stopLeScan(WxBluetoothFeatureImpl.this.leScanCallback);
                }
                WxBluetoothFeatureImpl.this.startBeaconScanRestartNotify();
            }
        }
    };
    public SensorManager sm = null;
    public Sensor orientationSensor = null;
    public Sensor accelerometerSensor = null;
    public Sensor magneticSensor = null;
    public float[] accelerometerValues = null;
    public float[] magneticValues = null;
    public float[] rotateValues = new float[9];
    public float[] resultValues = new float[3];
    public double rotateDegree = 0.0d;
    public IWebview bindWebview = null;
    public String bindCallbackID = null;
    public Map<String, IWebview> updateListener = null;
    public BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: io.dcloud.feature.iBeacon.WxBluetoothFeatureImpl.3
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            WxBluetoothFeatureImpl.this.iBeaconContainer.update(bluetoothDevice, i, bArr);
            Log.d(WxBluetoothFeatureImpl.TAG, "onLeScan: " + WxBluetoothFeatureImpl.this.iBeaconContainer.getBeacons().size());
        }
    };
    public DecimalFormat df = new DecimalFormat("0.000000");
    public DecimalFormat df_heading = new DecimalFormat("0.0000");
    public SensorEventListener sensorEventListener = new SensorEventListener() { // from class: io.dcloud.feature.iBeacon.WxBluetoothFeatureImpl.5
        public long lastRotateUpdateMillis = 0;

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                WxBluetoothFeatureImpl.this.accelerometerValues = sensorEvent.values;
            } else {
                if (type != 2) {
                    return;
                }
                WxBluetoothFeatureImpl.this.magneticValues = sensorEvent.values;
            }
            if (WxBluetoothFeatureImpl.this.accelerometerValues == null || WxBluetoothFeatureImpl.this.magneticValues == null) {
                return;
            }
            SensorManager.getRotationMatrix(WxBluetoothFeatureImpl.this.rotateValues, null, WxBluetoothFeatureImpl.this.accelerometerValues, WxBluetoothFeatureImpl.this.magneticValues);
            SensorManager.getOrientation(WxBluetoothFeatureImpl.this.rotateValues, WxBluetoothFeatureImpl.this.resultValues);
            WxBluetoothFeatureImpl.this.rotateDegree = (Math.toDegrees(WxBluetoothFeatureImpl.this.resultValues[0]) + 360.0d) % 360.0d;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (timeInMillis - this.lastRotateUpdateMillis >= 250) {
                WxBluetoothFeatureImpl.this.processBeaconRecords();
                this.lastRotateUpdateMillis = timeInMillis;
            }
        }
    };
    public BroadcastReceiver bluetoothStatuReceiver = new BroadcastReceiver() { // from class: io.dcloud.feature.iBeacon.WxBluetoothFeatureImpl.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "android.bluetooth.adapter.action.STATE_CHANGED") {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                if (intExtra == 12) {
                    Deprecated_JSUtil.execCallback(WxBluetoothFeatureImpl.this.bindWebview, WxBluetoothFeatureImpl.this.bindCallbackID, StringUtil.format("{discovering:%b,available:true}", Boolean.valueOf(WxBluetoothFeatureImpl.this.mBeaconScanFlag)), JSUtil.OK, true, true);
                } else if (intExtra == 10) {
                    WxBluetoothFeatureImpl.this.stopDiscovery();
                    Deprecated_JSUtil.execCallback(WxBluetoothFeatureImpl.this.bindWebview, WxBluetoothFeatureImpl.this.bindCallbackID, StringUtil.format("{discovering:%b,available:false}", Boolean.valueOf(WxBluetoothFeatureImpl.this.mBeaconScanFlag)), JSUtil.OK, true, true);
                }
            }
        }
    };

    private JSONArray getBeaconsJSON() {
        JSONArray jSONArray = new JSONArray();
        Map<String, IBeaconRecord> beacons = this.iBeaconContainer.getBeacons();
        if (!beacons.isEmpty()) {
            ArrayList<IBeaconRecord> arrayList = new ArrayList(beacons.values());
            Collections.sort(arrayList, new Comparator<IBeaconRecord>() { // from class: io.dcloud.feature.iBeacon.WxBluetoothFeatureImpl.4
                @Override // java.util.Comparator
                public int compare(IBeaconRecord iBeaconRecord, IBeaconRecord iBeaconRecord2) {
                    return iBeaconRecord.getRssi() - iBeaconRecord2.getRssi();
                }
            });
            try {
                for (IBeaconRecord iBeaconRecord : arrayList) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("uuid", iBeaconRecord.getUuid());
                    jSONObject.put("major", String.valueOf(iBeaconRecord.getMajor()));
                    jSONObject.put("minor", String.valueOf(iBeaconRecord.getMinor()));
                    jSONObject.put("rssi", String.valueOf(iBeaconRecord.getRssi()));
                    jSONObject.put("accuracy", this.df.format(iBeaconRecord.getAccuracy()));
                    jSONObject.put("heading", this.df_heading.format(this.rotateDegree));
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONArray;
    }

    public static final boolean isGpsEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processBeaconRecords() {
        if (this.mBeaconScanFlag) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", 0);
                jSONObject.put("beacons", getBeaconsJSON());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            for (String str : this.updateListener.keySet()) {
                JSUtil.execCallback(this.updateListener.get(str), str, jSONObject, JSUtil.OK, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBeaconScanNotifyCheck() {
        this.handler.removeMessages(1);
        this.handler.sendEmptyMessageDelayed(1, this.beaconScanUpdateMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBeaconScanRestartNotify() {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageDelayed(2, this.beaconScanRestartMillis);
    }

    private void stopBeaconScanNotifyCheck() {
        this.handler.removeMessages(1);
    }

    private void stopBeaconScanRestartNotify() {
        this.handler.removeMessages(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(18)
    public void stopDiscovery() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            defaultAdapter.stopLeScan(this.leScanCallback);
        }
        this.mBeaconScanFlag = false;
    }

    public void closeBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        int i = 10001;
        if (defaultAdapter != null && defaultAdapter.disable()) {
            i = 0;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSUtil.execCallback(iWebview, strOptString, jSONObject, i == 0 ? JSUtil.OK : JSUtil.ERROR, false);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    @TargetApi(18)
    public void dispose(String str) {
        BluetoothAdapter defaultAdapter;
        super.dispose(str);
        stopBeaconScanNotifyCheck();
        this.sm.unregisterListener(this.sensorEventListener, this.accelerometerSensor);
        this.sm.unregisterListener(this.sensorEventListener, this.magneticSensor);
        if (this.mBeaconScanFlag && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            defaultAdapter.stopLeScan(this.leScanCallback);
            this.mBeaconScanFlag = false;
        }
        IWebview iWebview = this.bindWebview;
        if (iWebview != null) {
            iWebview.getContext().unregisterReceiver(this.bluetoothStatuReceiver);
        }
    }

    public void getBeacons(IWebview iWebview, JSONArray jSONArray) {
        String str;
        int i;
        String strOptString = jSONArray.optString(0);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        JSONArray beaconsJSON = null;
        if (defaultAdapter == null) {
            i = 11000;
            str = "Bluetooth is not supported on this hardware platform";
        } else if (defaultAdapter.isEnabled()) {
            beaconsJSON = getBeaconsJSON();
            str = "success";
            i = 0;
        } else {
            i = AsrError.ERROR_WAKEUP_ENGINE_EXCEPTION;
            str = "Bluetooth is off";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            if (beaconsJSON != null) {
                jSONObject.put("beacons", beaconsJSON);
            } else {
                jSONObject.put("message", str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSUtil.execCallback(iWebview, strOptString, jSONObject, i == 0 ? JSUtil.OK : JSUtil.ERROR, false);
    }

    public void getBluetoothAdapterState(IWebview iWebview, JSONArray jSONArray) {
    }

    @Override // io.dcloud.common.DHInterface.StandardFeature, io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        super.init(absMgr, str);
        startBeaconScanNotifyCheck();
        SensorManager sensorManager = (SensorManager) this.mApplicationContext.getSystemService(bm.ac);
        this.sm = sensorManager;
        this.orientationSensor = sensorManager.getDefaultSensor(3);
        this.accelerometerSensor = this.sm.getDefaultSensor(1);
        this.magneticSensor = this.sm.getDefaultSensor(2);
        this.sm.registerListener(this.sensorEventListener, this.accelerometerSensor, 1);
        this.sm.registerListener(this.sensorEventListener, this.magneticSensor, 1);
        this.updateListener = new HashMap();
    }

    public void onBeaconServiceChange(IWebview iWebview, JSONArray jSONArray) {
        this.bindCallbackID = jSONArray.optString(0);
        this.bindWebview = iWebview;
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        IntentFilter intentFilter2 = new IntentFilter("android.bluetooth.device.action.ACL_CONNECTED");
        IntentFilter intentFilter3 = new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED");
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, intentFilter);
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, intentFilter2);
        iWebview.getContext().registerReceiver(this.bluetoothStatuReceiver, intentFilter3);
    }

    public void onBeaconUpdate(IWebview iWebview, JSONArray jSONArray) {
        this.updateListener.put(jSONArray.optString(0), iWebview);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onStart(Context context, Bundle bundle, String[] strArr) {
        super.onStart(context, bundle, strArr);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onStop() {
        super.onStop();
    }

    public void openBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        int i = 10001;
        if (defaultAdapter != null && defaultAdapter.enable()) {
            i = 0;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSUtil.execCallback(iWebview, strOptString, jSONObject, i == 0 ? JSUtil.OK : JSUtil.ERROR, false);
    }

    @TargetApi(18)
    public void startBeaconDiscovery(IWebview iWebview, JSONArray jSONArray) {
        String str;
        int i;
        String strOptString = jSONArray.optString(0);
        JSONArray jSONArrayOptJSONArray = jSONArray.optJSONArray(1);
        PermissionUtil.useSystemPermission(iWebview.getActivity(), "android.permission.ACCESS_COARSE_LOCATION", new PermissionUtil.Request() { // from class: io.dcloud.feature.iBeacon.WxBluetoothFeatureImpl.2
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str2) {
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str2) {
            }
        });
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        defaultAdapter.enable();
        if (defaultAdapter == null) {
            i = 11000;
            this.mBeaconScanFlag = false;
            str = "Bluetooth is not supported on this hardware platform";
        } else if (!defaultAdapter.isEnabled()) {
            i = AsrError.ERROR_WAKEUP_ENGINE_EXCEPTION;
            this.mBeaconScanFlag = false;
            str = "Bluetooth is off";
        } else {
            if (this.mBeaconScanFlag) {
                Deprecated_JSUtil.execCallback(iWebview, strOptString, "{code:11003,message:'already start'}", JSUtil.ERROR, true, false);
                return;
            }
            if (isGpsEnable(iWebview.getContext())) {
                defaultAdapter.stopLeScan(this.leScanCallback);
                defaultAdapter.startLeScan(this.leScanCallback);
                this.mBeaconScanFlag = true;
                str = "";
                i = 0;
            } else {
                i = AsrError.ERROR_WAKEUP_NO_LICENSE;
                this.mBeaconScanFlag = false;
                str = "location service unavailable";
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            jSONObject.put("message", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        IBeaconContainer.setWxBeaconUUID(jSONArrayOptJSONArray);
        JSUtil.execCallback(iWebview, strOptString, jSONObject, i == 0 ? JSUtil.OK : JSUtil.ERROR, false);
    }

    public void stopBeaconDiscovery(IWebview iWebview, JSONArray jSONArray) {
        String str;
        int i;
        String strOptString = jSONArray.optString(0);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            i = 11000;
            str = "Bluetooth is not supported on this hardware platform";
        } else if (!defaultAdapter.isEnabled()) {
            i = AsrError.ERROR_WAKEUP_ENGINE_EXCEPTION;
            str = "Bluetooth is off";
        } else if (isGpsEnable(iWebview.getContext())) {
            defaultAdapter.stopLeScan(this.leScanCallback);
            str = "success";
            i = 0;
        } else {
            i = AsrError.ERROR_WAKEUP_NO_LICENSE;
            str = "location service unavailable";
        }
        this.mBeaconScanFlag = false;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            jSONObject.put("message", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSUtil.execCallback(iWebview, strOptString, jSONObject, i == 0 ? JSUtil.OK : JSUtil.ERROR, false);
    }
}
