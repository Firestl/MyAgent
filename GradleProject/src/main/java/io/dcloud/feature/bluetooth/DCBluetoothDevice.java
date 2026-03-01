package io.dcloud.feature.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.os.ParcelUuid;
import android.util.SparseArray;
import io.dcloud.common.util.PdrUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DCBluetoothDevice {
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final int DATA_TYPE_FLAGS = 1;
    public static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    public static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    public static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    public static final int DATA_TYPE_SERVICE_DATA_128_BIT = 33;
    public static final int DATA_TYPE_SERVICE_DATA_16_BIT = 22;
    public static final int DATA_TYPE_SERVICE_DATA_32_BIT = 32;
    public static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    public static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    public static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    public static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    public static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    public static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    public static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;
    public int RSSI;
    public String advertisData;
    public JSONArray advertisServiceUUIDs;
    public String deviceId;
    public String localName;
    public String name;
    public JSONObject serviceData;

    @TargetApi(21)
    public DCBluetoothDevice(ScanResult scanResult) {
        setDeviceId(scanResult.getDevice().getAddress());
        setName(PdrUtil.isEmpty(scanResult.getDevice().getName()) ? "" : scanResult.getDevice().getName());
        setRSSI(scanResult.getRssi());
        SparseArray<byte[]> manufacturerSpecificData = scanResult.getScanRecord().getManufacturerSpecificData();
        if (manufacturerSpecificData != null && manufacturerSpecificData.size() > 0) {
            setAdvertisData(bytesToHexString(manufacturerSpecificData.valueAt(0)));
        }
        setLocalName(PdrUtil.isEmpty(scanResult.getScanRecord().getDeviceName()) ? "" : scanResult.getScanRecord().getDeviceName());
        try {
            setServiceData(map2Str(scanResult.getScanRecord().getServiceData()));
            setAdvertisServiceUUIDs(list2Str(scanResult.getScanRecord().getServiceUuids()));
        } catch (JSONException unused) {
            setAdvertisServiceUUIDs(new JSONArray());
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private byte[] extractBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    private JSONArray list2Str(List<ParcelUuid> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            Iterator<ParcelUuid> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toString().toUpperCase());
            }
        }
        return jSONArray;
    }

    private JSONObject map2Str(Map<ParcelUuid, byte[]> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (ParcelUuid parcelUuid : map.keySet()) {
                jSONObject.put(parcelUuid.toString().toUpperCase(), bytesToHexString(map.get(parcelUuid)));
            }
        }
        if (jSONObject.length() <= 0) {
            return null;
        }
        return jSONObject;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:4|(4:7|(11:59|9|31|(1:33)|35|(1:38)|39|56|40|43|(5:45|(4:48|(2:50|70)(1:71)|51|46)|69|52|53)(1:72))(3:10|(4:24|(1:26)(1:(1:28))|29|60)(1:(1:(0)(1:SW:15))(2:23|68))|30)|5|54)|58|31|(0)|35|(0)|39|56|40|43|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c7, code lost:
    
        setAdvertisServiceUUIDs(new org.json.JSONArray());
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseFromBytes(byte[] r15) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.bluetooth.DCBluetoothDevice.parseFromBytes(byte[]):void");
    }

    private int parseServiceUuid(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(parseUuidFrom(extractBytes(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    public String getAdvertisData() {
        return this.advertisData;
    }

    public JSONArray getAdvertisServiceUUIDs() {
        return this.advertisServiceUUIDs;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getLocalName() {
        return this.localName;
    }

    public String getName() {
        return this.name;
    }

    public int getRSSI() {
        return this.RSSI;
    }

    public JSONObject getServiceData() {
        return this.serviceData;
    }

    public ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 16) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 2) {
            j = ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8));
        } else {
            j = ((long) ((bArr[3] & 255) << 24)) + ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8)) + ((long) ((bArr[2] & 255) << 16));
        }
        return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + (j << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
    }

    public void setAdvertisData(String str) {
        this.advertisData = str;
    }

    public void setAdvertisServiceUUIDs(JSONArray jSONArray) {
        this.advertisServiceUUIDs = jSONArray;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setLocalName(String str) {
        this.localName = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRSSI(int i) {
        this.RSSI = i;
    }

    public void setServiceData(JSONObject jSONObject) {
        this.serviceData = jSONObject;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", this.deviceId);
            jSONObject.put("name", this.name);
            jSONObject.put("RSSI", this.RSSI);
            jSONObject.put("localName", this.localName);
            jSONObject.put("advertisServiceUUIDs", this.advertisServiceUUIDs);
            if (this.advertisData != null) {
                jSONObject.put("advertisData", this.advertisData);
            }
            if (this.serviceData != null) {
                jSONObject.put("serviceData", this.serviceData);
            }
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public DCBluetoothDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        setDeviceId(bluetoothDevice.getAddress());
        setName(PdrUtil.isEmpty(bluetoothDevice.getName()) ? "" : bluetoothDevice.getName());
        parseFromBytes(bArr);
    }

    public DCBluetoothDevice(String str, String str2, int i, String str3, JSONObject jSONObject, JSONArray jSONArray) {
        this.deviceId = str;
        this.name = str2;
        this.RSSI = i;
        this.advertisData = str3;
        this.serviceData = jSONObject;
        this.advertisServiceUUIDs = jSONArray;
    }
}
