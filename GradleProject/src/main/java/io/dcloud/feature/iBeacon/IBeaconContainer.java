package io.dcloud.feature.iBeacon;

import android.bluetooth.BluetoothDevice;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class IBeaconContainer {
    public static JSONArray WxBeaconUUID;
    public Map<String, IBeaconRecord> iBeaconRecordMap = new HashMap();
    public long mExpireProcessTimeStampMillis = 0;
    public long mExpireTimeMillis = 8000;
    public int mRssiMinFilter = -75;

    private void processExpireBeacons() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.mExpireProcessTimeStampMillis >= 1000) {
            HashMap map = new HashMap();
            for (Map.Entry<String, IBeaconRecord> entry : this.iBeaconRecordMap.entrySet()) {
                if (timeInMillis - entry.getValue().timeStampMillis <= this.mExpireTimeMillis) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
            this.iBeaconRecordMap.clear();
            this.iBeaconRecordMap.putAll(map);
            this.mExpireProcessTimeStampMillis = timeInMillis;
        }
    }

    public static void setWxBeaconUUID(JSONArray jSONArray) {
        WxBeaconUUID = jSONArray;
    }

    public synchronized Map<String, IBeaconRecord> getBeacons() {
        processExpireBeacons();
        return new HashMap(this.iBeaconRecordMap);
    }

    public synchronized void update(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        IBeaconRecord iBeaconRecordFromScanData;
        if (i >= this.mRssiMinFilter && i < -1 && (iBeaconRecordFromScanData = IBeaconRecord.fromScanData(bluetoothDevice, bArr, i)) != null) {
            if (WxBeaconUUID == null || WxBeaconUUID.length() <= 0) {
                this.iBeaconRecordMap.put(iBeaconRecordFromScanData.address, iBeaconRecordFromScanData);
            } else {
                for (int i2 = 0; i2 < WxBeaconUUID.length(); i2++) {
                    if (WxBeaconUUID.optString(i2).equalsIgnoreCase(iBeaconRecordFromScanData.getUuid())) {
                        this.iBeaconRecordMap.put(iBeaconRecordFromScanData.address, iBeaconRecordFromScanData);
                    }
                }
            }
        }
        processExpireBeacons();
    }
}
