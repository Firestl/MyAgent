package com.sangfor.sdk.base;

import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFSyncDataResult {
    SyncDataFailed(-1),
    SyncDataDuplicate(0),
    SyncDataComplete(1),
    SyncDataSession(2);

    public static final String TAG = "SFOnlineType";
    public final int mValue;

    SFSyncDataResult(int i) {
        this.mValue = i;
    }

    public static SFSyncDataResult ValueOf(int i) {
        if (i == -1) {
            return SyncDataFailed;
        }
        if (i == 0) {
            return SyncDataDuplicate;
        }
        if (i == 1) {
            return SyncDataComplete;
        }
        if (i == 2) {
            return SyncDataSession;
        }
        SFLogN.b("SFOnlineType", "ValueOf failed.", "unknown value:" + i + " will ret SyncDataFailed");
        return SyncDataFailed;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == -1) {
            return "SyncDataFailed";
        }
        if (i == 0) {
            return "SyncDataDuplicate";
        }
        if (i == 1) {
            return "SyncDataComplete";
        }
        if (i == 2) {
            return "SyncDataSession";
        }
        return "SFSyncDataResult UNKNOWN:" + this.mValue;
    }
}
