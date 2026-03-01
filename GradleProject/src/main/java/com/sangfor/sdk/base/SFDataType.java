package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFDataType {
    DATA_SESSION(1),
    DATA_RCLIST(2),
    DATA_EMMPOLICY(4),
    DATA_APPLIST(8),
    DATA_SPACONFIG(16);

    public int mValue;

    SFDataType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 1) {
            return "DATA_SESSION";
        }
        if (i == 2) {
            return "DATA_RCLIST";
        }
        if (i == 4) {
            return "DATA_EMMPOLICY";
        }
        if (i == 8) {
            return "DATA_APPLIST";
        }
        if (i == 16) {
            return "DATA_SPACONFIG";
        }
        return "SFDataType UNKNOWN:" + this.mValue;
    }

    public static SFDataType valueOf(int i) {
        if (i == 1) {
            return DATA_SESSION;
        }
        if (i == 2) {
            return DATA_RCLIST;
        }
        if (i == 4) {
            return DATA_EMMPOLICY;
        }
        if (i == 8) {
            return DATA_APPLIST;
        }
        if (i == 16) {
            return DATA_SPACONFIG;
        }
        throw new IllegalArgumentException("SFDataType valueOf failed, invalid value = " + i);
    }
}
