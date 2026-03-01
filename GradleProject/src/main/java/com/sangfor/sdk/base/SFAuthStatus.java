package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFAuthStatus {
    AuthStatusNone(0),
    AuthStatusLogining(1),
    AuthStatusPrimaryAuthOK(2),
    AuthStatusAuthOk(3),
    AuthStatusLogouting(4),
    AuthStatusLogouted(5);

    public int mValue;

    SFAuthStatus(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 0) {
            return "AuthStatusNone";
        }
        if (i == 1) {
            return "AuthStatusLogining";
        }
        if (i == 2) {
            return "AuthStatusPrimaryAuthOK";
        }
        if (i == 3) {
            return "AuthStatusAuthOk";
        }
        if (i == 4) {
            return "AuthStatusLogouting";
        }
        if (i == 5) {
            return "AuthStatusLogouted";
        }
        return "SFAuthStatus UNKNOWN:" + this.mValue;
    }

    public static SFAuthStatus valueOf(int i) {
        if (i == 0) {
            return AuthStatusNone;
        }
        if (i == 1) {
            return AuthStatusLogining;
        }
        if (i == 2) {
            return AuthStatusPrimaryAuthOK;
        }
        if (i == 3) {
            return AuthStatusAuthOk;
        }
        if (i == 4) {
            return AuthStatusLogouting;
        }
        if (i == 5) {
            return AuthStatusLogouted;
        }
        throw new IllegalArgumentException("SFAuthStatus valueOf failed, invalid value = " + i);
    }
}
