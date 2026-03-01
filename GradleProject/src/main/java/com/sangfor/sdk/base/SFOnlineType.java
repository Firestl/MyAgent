package com.sangfor.sdk.base;

import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFOnlineType {
    UNKNOWN(0),
    SESSION(1),
    INNER(2);

    public static final String TAG = "SFOnlineType";
    public final int mValue;

    SFOnlineType(int i) {
        this.mValue = i;
    }

    public static SFOnlineType ValueOf(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return SESSION;
        }
        if (i == 2) {
            return INNER;
        }
        SFLogN.b("SFOnlineType", "ValueOf failed.", "unknown value:" + i + " will ret UNKNOWN");
        return UNKNOWN;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 0) {
            return GrsBaseInfo.CountryCodeSource.UNKNOWN;
        }
        if (i == 1) {
            return "SESSION";
        }
        if (i == 2) {
            return "INNER";
        }
        return "SFOnlineType UNKNOWN:" + this.mValue;
    }
}
