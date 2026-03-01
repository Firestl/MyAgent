package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFOnlineState {
    ONLINE_STATE_NONE(0),
    ONLINE_STATE_ONLINE(1),
    ONLINE_STATE_OFFLINE(2);

    public final int mState;

    SFOnlineState(int i) {
        this.mState = i;
    }

    public static SFOnlineState valueOfInt(int i) {
        return i != 1 ? i != 2 ? ONLINE_STATE_NONE : ONLINE_STATE_OFFLINE : ONLINE_STATE_ONLINE;
    }

    public int toInit() {
        return this.mState;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mState;
        return i != 1 ? i != 2 ? "ONLINE_STATE_NONE" : "ONLINE_STATE_OFFLINE" : "ONLINE_STATE_ONLINE";
    }
}
