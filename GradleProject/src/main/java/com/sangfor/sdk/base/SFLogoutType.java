package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFLogoutType {
    LOGOUT_TYPE_USER_ACTIVE(0),
    LOGOUT_TYPE_TCIKET_AUTH_ERROR(1),
    LOGOUT_TYPE_SERVER_SHUTDOWN(2),
    LOGOUT_TYPE_AUTHOR_ERROR(3),
    LOGOUT_TYPE_OTHERS(100);

    public int mValue;

    SFLogoutType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 100) {
            return "LOGOUT_TYPE_OTHERS";
        }
        if (i == 0) {
            return "LOGOUT_TYPE_USER_ACTIVE";
        }
        if (i == 1) {
            return "LOGOUT_TYPE_TCIKET_AUTH_ERROR";
        }
        if (i == 2) {
            return "LOGOUT_TYPE_SERVER_SHUTDOWN";
        }
        if (i == 3) {
            return "LOGOUT_TYPE_AUTHOR_ERROR";
        }
        return "SFLogoutType UNKNOWN:" + this.mValue;
    }

    public static SFLogoutType valueOf(int i) {
        if (i == 100) {
            return LOGOUT_TYPE_OTHERS;
        }
        if (i == 0) {
            return LOGOUT_TYPE_USER_ACTIVE;
        }
        if (i == 1) {
            return LOGOUT_TYPE_TCIKET_AUTH_ERROR;
        }
        if (i == 2) {
            return LOGOUT_TYPE_SERVER_SHUTDOWN;
        }
        if (i == 3) {
            return LOGOUT_TYPE_AUTHOR_ERROR;
        }
        throw new IllegalArgumentException("SFLogoutType valueOf failed, invalid value = " + i);
    }
}
