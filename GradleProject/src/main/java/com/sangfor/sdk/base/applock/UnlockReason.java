package com.sangfor.sdk.base.applock;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum UnlockReason {
    None(0),
    Launch(1),
    EnvAnormaly(2),
    Policy(3),
    ApplyUnlock(4);

    public int value;

    UnlockReason(int i) {
        this.value = 0;
        this.value = i;
    }

    public int value() {
        return this.value;
    }
}
