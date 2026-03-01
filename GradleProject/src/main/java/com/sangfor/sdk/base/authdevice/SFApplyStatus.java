package com.sangfor.sdk.base.authdevice;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFApplyStatus {
    Approvaling(1),
    BindSuccess(2),
    ApprovalReject(3);

    public int mValue;

    SFApplyStatus(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    public static SFApplyStatus valueOf(int i) {
        if (i == 1) {
            return Approvaling;
        }
        if (i == 2) {
            return BindSuccess;
        }
        if (i == 3) {
            return ApprovalReject;
        }
        throw new IllegalArgumentException("SFApplyStatus valueOf failed, invalid value = " + i);
    }
}
