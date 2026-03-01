package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFNotificationType {
    SFNotificaitonTypeTicketAuthFailed(0),
    SFNotificaitonTypeTicketAuthSuccess(1),
    SFNotificaitonTypeDisconnectFromProxy(2),
    SFNotificaitonTypeConnectedToProxy(3);

    public int mValue;

    SFNotificationType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 0) {
            return "SFNotificaitonTypeTicketAuthFailed";
        }
        if (i == 1) {
            return "SFNotificaitonTypeTicketAuthSuccess";
        }
        if (i == 2) {
            return "SFNotificaitonTypeDisconnectFromProxy";
        }
        if (i == 3) {
            return "SFNotificaitonTypeConnectedToProxy";
        }
        return "SFNotificationType UNKNOWN:" + this.mValue;
    }

    public static SFNotificationType valueOf(int i) {
        if (i == 0) {
            return SFNotificaitonTypeTicketAuthFailed;
        }
        if (i == 1) {
            return SFNotificaitonTypeTicketAuthSuccess;
        }
        if (i == 2) {
            return SFNotificaitonTypeDisconnectFromProxy;
        }
        if (i == 3) {
            return SFNotificaitonTypeConnectedToProxy;
        }
        throw new IllegalArgumentException("SFNotificationType valueOf failed, invalid value = " + i);
    }
}
