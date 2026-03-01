package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFEventType {
    SFEventTypeACL(0),
    SFEventTypeLaunchApp(1),
    SFEventTypeLaunchAppPre(2);

    public int mValue;

    SFEventType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    public static SFEventType valueOf(int i) {
        if (i == 0) {
            return SFEventTypeACL;
        }
        throw new IllegalArgumentException("SFEventType valueOf failed, invalid value = " + i);
    }
}
