package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFUpdatePolicyErrorCode {
    UPDATE_POLICY_SUCCESS(0),
    UPDATE_POLICY_FORMAT_ERROR(1),
    UPDATE_POLICY_NOT_AUTH(2),
    UPDATE_POLICY_INNER_ERROR(3);

    public int mValue;

    SFUpdatePolicyErrorCode(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 0) {
            return "UPDATE_POLICY_SUCCESS";
        }
        if (i == 1) {
            return "UPDATE_POLICY_FORMAT_ERROR";
        }
        if (i == 2) {
            return "UPDATE_POLICY_NOT_AUTH";
        }
        if (i == 3) {
            return "UPDATE_POLICY_INNER_ERROR";
        }
        return "SFUpdatePolicyErrorCode UNKNOWN:" + this.mValue;
    }

    public static SFUpdatePolicyErrorCode valueOf(int i) {
        if (i == 0) {
            return UPDATE_POLICY_SUCCESS;
        }
        if (i == 1) {
            return UPDATE_POLICY_FORMAT_ERROR;
        }
        if (i == 2) {
            return UPDATE_POLICY_NOT_AUTH;
        }
        if (i == 3) {
            return UPDATE_POLICY_INNER_ERROR;
        }
        throw new IllegalArgumentException("SFUpdatePolicyErrorCode valueOf failed, invalid value = " + i);
    }
}
