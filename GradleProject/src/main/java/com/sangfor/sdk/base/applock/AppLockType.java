package com.sangfor.sdk.base.applock;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum AppLockType {
    None(0),
    Gesture(1),
    TouchID(2),
    FaceID(4);

    public int value;

    AppLockType(int i) {
        this.value = 0;
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AppLockType valueOf(int i) {
        if (i == 0) {
            return None;
        }
        if (i == 1) {
            return Gesture;
        }
        if (i == 2) {
            return TouchID;
        }
        if (i == 4) {
            return FaceID;
        }
        throw new IllegalArgumentException("AppLockType valueOf failed, invalid value = " + i);
    }
}
