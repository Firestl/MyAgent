package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFAuthMode {
    Unknown(0),
    LoginAuth(1),
    ForgetPwdAuth(2),
    CasAuth(3),
    PrimarySmsAuth(4),
    TicketAuth(5),
    BindAuthDevice(6),
    CommonHttpsAuth(7);

    public int mValue;

    SFAuthMode(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        switch (this.mValue) {
            case 0:
                return "Unknown";
            case 1:
                return "LoginAuth";
            case 2:
                return "ForgetPwdAuth";
            case 3:
                return "CasAuth";
            case 4:
                return "PrimarySmsAuth";
            case 5:
                return "TicketAuth";
            case 6:
                return "BindAuthDevice";
            case 7:
                return "CommonHttpsAuth";
            default:
                return "SFAuthMode UNKNOWN:" + this.mValue;
        }
    }

    public static SFAuthMode valueOf(int i) {
        switch (i) {
            case 0:
                return Unknown;
            case 1:
                return LoginAuth;
            case 2:
                return ForgetPwdAuth;
            case 3:
                return CasAuth;
            case 4:
                return PrimarySmsAuth;
            case 5:
                return TicketAuth;
            case 6:
                return BindAuthDevice;
            case 7:
                return CommonHttpsAuth;
            default:
                throw new IllegalArgumentException("SFAuthMode valueOf failed, invalid value = " + i);
        }
    }
}
