package io.dcloud.feature.iBeacon;

import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: loaded from: classes3.dex */
public class IBeaconRecord {
    public static final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};
    public String address;
    public int major;
    public int minor;
    public int rssi;
    public long timeStampMillis;
    public int txPower;
    public String uuid;

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
    
        if (r1 != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
    
        r1 = new io.dcloud.feature.iBeacon.IBeaconRecord();
        r1.address = r9.getAddress();
        r1.major = ((r10[r2 + 20] & 255) * 256) + (r10[r2 + 21] & 255);
        r1.minor = ((r10[r2 + 22] & 255) * 256) + (r10[r2 + 23] & 255);
        r1.txPower = r10[r2 + 24];
        r1.rssi = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009f, code lost:
    
        r11 = new byte[16];
        java.lang.System.arraycopy(r10, r2 + 4, r11, 0, 16);
        r10 = bytesToHex(r11);
        r1.uuid = r10.substring(0, 8) + "-" + r10.substring(8, 12) + "-" + r10.substring(12, 16) + "-" + r10.substring(16, 20) + "-" + r10.substring(20, 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ed, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ee, code lost:
    
        r9.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.dcloud.feature.iBeacon.IBeaconRecord fromScanData(android.bluetooth.BluetoothDevice r9, byte[] r10, int r11) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.iBeacon.IBeaconRecord.fromScanData(android.bluetooth.BluetoothDevice, byte[], int):io.dcloud.feature.iBeacon.IBeaconRecord");
    }

    public double getAccuracy() {
        int i = this.rssi;
        if (i >= -1 || this.txPower >= 0) {
            return -1.0d;
        }
        double d = Double.parseDouble(String.valueOf(i)) / ((double) this.txPower);
        double dPow = ((Math.pow(Math.abs(this.rssi), 3.0d) % 10.0d) / 150.0d) + 0.96d;
        if (d <= 1.0d) {
            return Math.pow(d, 9.98d) * dPow;
        }
        double dMax = Math.max(0.0d, ((Math.pow(d, 7.5d) * 0.89978d) + 0.103d) * dPow);
        if (Double.NaN == dMax) {
            return -1.0d;
        }
        return dMax;
    }

    public String getAddress() {
        return this.address;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getRssi() {
        return this.rssi;
    }

    public long getTimeStampMillis() {
        return this.timeStampMillis;
    }

    public int getTxPower() {
        return this.txPower;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setMajor(int i) {
        this.major = i;
    }

    public void setMinor(int i) {
        this.minor = i;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setTimeStampMillis(long j) {
        this.timeStampMillis = j;
    }

    public void setTxPower(int i) {
        this.txPower = i;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }
}
