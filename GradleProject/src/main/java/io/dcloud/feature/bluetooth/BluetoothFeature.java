package io.dcloud.feature.bluetooth;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.adapter.util.PermissionUtil;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothFeature extends StandardFeature {
    public BluetoothBaseAdapter bluetoothF;

    public void closeBLEConnection(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.closeBLEConnection(iWebview, jSONArray);
    }

    public void closeBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.closeBluetoothAdapter(iWebview, jSONArray);
    }

    public void createBLEConnection(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.createBLEConnection(iWebview, jSONArray);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.bluetoothF.dispose(str);
    }

    public void getBLEDeviceCharacteristics(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.getBLEDeviceCharacteristics(iWebview, jSONArray);
    }

    public void getBLEDeviceRSSI(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.getBLEDeviceRSSI(iWebview, jSONArray);
    }

    public void getBLEDeviceServices(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.getBLEDeviceServices(iWebview, jSONArray);
    }

    public void getBluetoothAdapterState(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.getBluetoothAdapterState(iWebview, jSONArray);
    }

    public void getBluetoothDevices(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.getBluetoothDevices(iWebview, jSONArray);
    }

    public void getConnectedBluetoothDevices(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.getConnectedBluetoothDevices(iWebview, jSONArray);
    }

    @Override // io.dcloud.common.DHInterface.StandardFeature, io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        super.init(absMgr, str);
        this.bluetoothF = new BluetoothUnder21();
    }

    public void notifyBLECharacteristicValueChange(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.notifyBLECharacteristicValueChange(iWebview, jSONArray);
    }

    public void onBLECharacteristicValueChange(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.onBLECharacteristicValueChange(iWebview, jSONArray);
    }

    public void onBLEConnectionStateChange(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.onBLEConnectionStateChange(iWebview, jSONArray);
    }

    public void onBluetoothAdapterStateChange(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.onBluetoothAdapterStateChange(iWebview, jSONArray);
    }

    public void onBluetoothDeviceFound(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.onBluetoothDeviceFound(iWebview, jSONArray);
    }

    public void openBluetoothAdapter(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.openBluetoothAdapter(iWebview, jSONArray);
    }

    public void readBLECharacteristicValue(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.readBLECharacteristicValue(iWebview, jSONArray);
    }

    public void setBLEMTU(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.setBLEMTU(iWebview, jSONArray);
    }

    public void startBluetoothDevicesDiscovery(final IWebview iWebview, final JSONArray jSONArray) {
        PermissionUtil.useSystemPermissions(iWebview.getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, new PermissionUtil.Request() { // from class: io.dcloud.feature.bluetooth.BluetoothFeature.1
            public int times = 0;

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str) {
                int i = this.times + 1;
                this.times = i;
                if (i == 2) {
                    BluetoothFeature.this.bluetoothF.startBluetoothDevicesDiscovery(iWebview, jSONArray);
                }
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str) {
                int i = this.times + 1;
                this.times = i;
                if (i == 2) {
                    BluetoothFeature.this.bluetoothF.startBluetoothDevicesDiscovery(iWebview, jSONArray);
                }
            }
        });
    }

    public void stopBluetoothDevicesDiscovery(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.stopBluetoothDevicesDiscovery(iWebview, jSONArray);
    }

    public void writeBLECharacteristicValue(IWebview iWebview, JSONArray jSONArray) {
        this.bluetoothF.writeBLECharacteristicValue(iWebview, jSONArray);
    }
}
