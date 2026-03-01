package com.sangfor.sdk.base.authdevice;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFBindStatus {
    SelfServiceBind(0),
    NeedRemoveTerminal(1),
    ApplyInApproval(2),
    NeedApply(3);

    public int mValue;

    SFBindStatus(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    public static SFBindStatus valueOf(int i) {
        if (i == 0) {
            return SelfServiceBind;
        }
        if (i == 1) {
            return NeedRemoveTerminal;
        }
        if (i == 2) {
            return ApplyInApproval;
        }
        if (i == 3) {
            return NeedApply;
        }
        throw new IllegalArgumentException("SFBindStatus valueOf failed, invalid value = " + i);
    }
}
