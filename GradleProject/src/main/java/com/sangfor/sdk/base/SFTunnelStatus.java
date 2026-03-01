package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFTunnelStatus {
    INIT(0),
    ONLINE(1),
    OFFLINE(2);

    public int mValue;

    SFTunnelStatus(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 0) {
            return "INIT";
        }
        if (i == 1) {
            return "ONLINE";
        }
        if (i == 2) {
            return "OFFLINE";
        }
        return "SFTunnelStatus UNKNOWN:" + this.mValue;
    }

    public static SFTunnelStatus valueOf(int i) {
        if (i == 0) {
            return INIT;
        }
        if (i == 1) {
            return ONLINE;
        }
        if (i == 2) {
            return OFFLINE;
        }
        throw new IllegalArgumentException("SFTunnelStatus valueOf failed, invalid value = " + i);
    }
}
