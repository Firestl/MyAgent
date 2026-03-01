package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFSDKType {
    SDK_TYPE_UNKNOWN(0),
    SDK_TYPE_VPN(1),
    SDK_TYPE_SDP(2);

    public int mValue;

    SFSDKType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 0) {
            return "SDK_TYPE_UNKNOWN";
        }
        if (i == 1) {
            return "SDK_TYPE_VPN";
        }
        if (i == 2) {
            return "SDK_TYPE_SDP";
        }
        return "SFSDKType UNKNOWN:" + this.mValue;
    }

    public static SFSDKType valueOf(int i) {
        if (i == 0) {
            return SDK_TYPE_UNKNOWN;
        }
        if (i == 1) {
            return SDK_TYPE_VPN;
        }
        if (i == 2) {
            return SDK_TYPE_SDP;
        }
        throw new IllegalArgumentException("SFSDKType valueOf failed, invalid value = " + i);
    }
}
