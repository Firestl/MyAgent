package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFSDKMode {
    MODE_VPN(1),
    MODE_SANDBOX(2),
    MODE_VPN_SANDBOX(3),
    MODE_SUPPORT_MUTABLE(7);

    public int mValue;

    SFSDKMode(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 1) {
            return "MODE_VPN";
        }
        if (i == 2) {
            return "MODE_SANDBOX";
        }
        if (i == 3) {
            return "MODE_VPN_SANDBOX";
        }
        if (i == 7) {
            return "MODE_SUPPORT_MUTABLE";
        }
        return "SFSDKMode UNKNOWN: " + this.mValue;
    }

    public static SFSDKMode valueOf(int i) {
        if (i == 1) {
            return MODE_VPN;
        }
        if (i == 2) {
            return MODE_SANDBOX;
        }
        if (i == 3) {
            return MODE_VPN_SANDBOX;
        }
        if (i == 7) {
            return MODE_SUPPORT_MUTABLE;
        }
        throw new IllegalArgumentException("SFSDKMode valueOf failed, invalid value = " + i);
    }
}
