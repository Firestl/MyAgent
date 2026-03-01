package com.ta.utdid2.device;

/* JADX INFO: loaded from: classes2.dex */
public class Device {
    public String imei = "";
    public String imsi = "";
    public String deviceId = "";
    public String utdid = "";
    public long mCreateTimestamp = 0;
    public long mCheckSum = 0;

    public long getCheckSum() {
        return this.mCheckSum;
    }

    public long getCreateTimestamp() {
        return this.mCreateTimestamp;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getImei() {
        return this.imei;
    }

    public String getImsi() {
        return this.imsi;
    }

    public String getUtdid() {
        return this.utdid;
    }

    public void setCheckSum(long j) {
        this.mCheckSum = j;
    }

    public void setCreateTimestamp(long j) {
        this.mCreateTimestamp = j;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public void setUtdid(String str) {
        this.utdid = str;
    }
}
