package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFAuthResultType {
    AUTH_FETCH_SOURCE_FAIL(-1),
    AUTH_RESULT_NONE(0),
    AUTH_SELECT_LINE_OK(1),
    AUTH_SELECT_LINE_FAIL(2),
    AUTH_RESULT_OK(4),
    AUTH_RESULT_FAIL(8),
    AUTH_RESULT_NEXT_AUTH(16),
    AUTH_RESULT_CANCEL(32);

    public int mValue;

    SFAuthResultType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == -1) {
            return "AUTH_FETCH_SOURCE_FAIL";
        }
        if (i == 4) {
            return "AUTH_RESULT_OK";
        }
        if (i == 8) {
            return "AUTH_RESULT_FAIL";
        }
        if (i == 16) {
            return "AUTH_RESULT_NEXT_AUTH";
        }
        if (i == 32) {
            return "AUTH_RESULT_CANCEL";
        }
        if (i == 1) {
            return "AUTH_RESULT_NONE";
        }
        if (i == 2) {
            return "AUTH_SELECT_LINE_OK";
        }
        return "SFAuthResultType UNKNOWN:" + this.mValue;
    }

    public static SFAuthResultType valueOf(int i) {
        if (i == -1) {
            return AUTH_FETCH_SOURCE_FAIL;
        }
        if (i == 4) {
            return AUTH_RESULT_OK;
        }
        if (i == 8) {
            return AUTH_RESULT_FAIL;
        }
        if (i == 16) {
            return AUTH_RESULT_NEXT_AUTH;
        }
        if (i == 32) {
            return AUTH_RESULT_CANCEL;
        }
        if (i == 1) {
            return AUTH_RESULT_NONE;
        }
        if (i == 2) {
            return AUTH_SELECT_LINE_OK;
        }
        throw new IllegalArgumentException("SFAuthResultType valueOf failed, invalid value = " + i);
    }
}
