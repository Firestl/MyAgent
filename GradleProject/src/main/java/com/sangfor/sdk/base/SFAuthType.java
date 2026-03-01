package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFAuthType {
    AUTH_TYPE_CERTIFICATE(0),
    AUTH_TYPE_PASSWORD(1),
    AUTH_TYPE_SMS(2),
    AUTH_TYPE_HARDID(4),
    AUTH_TYPE_RADIUS(6),
    AUTH_TYPE_TOKEN(7),
    AUTH_TYPE_AUTHOR(10),
    AUTH_TYPE_DINGDING_CODE(11),
    AUTH_TYPE_SESSION(16),
    AUTH_TYPE_NONE(17),
    AUTH_TYPE_RENEW_PASSWORD(18),
    AUTH_TYPE_RAND(22),
    AUTH_TYPE_SANGFORID(23),
    AUTH_TYPE_QYWECHAT(24),
    AUTH_TYPE_TOKEN_TOTP(25),
    AUTH_TYPE_TOKEN_RADIUS(26),
    AUTH_TYPE_TOKEN_HTTPS(27),
    AUTH_TYPE_AUTH_CHECK(28),
    AUTH_TYPE_PURE_BIND_AUTH_DEVICE(29),
    AUTH_TYPE_APPLY_BIND_AUTH_DEVICE(30),
    AUTH_TYPE_UNBIND_AUTH_DEVICE(31),
    AUTH_TYPE_PURE_TRUST_DEVICE(32),
    AUTH_TYPE_APPLY_TRUST_DEVICE(33),
    AUTH_TYPE_UNBIND_TRUST_DEVICE(34),
    AUTH_TYPE_PRE_ENHANCED(35),
    AUTH_TYPE_FORGET_PWD_PRE(36),
    AUTH_TYPE_FORGET_PWD(37),
    AUTH_TYPE_RESET_PWD(38),
    AUTH_TYPE_CAS(39),
    AUTH_TYPE_CAS_PRE(40),
    AUTH_TYPE_PRIMARY_SMS_PRE(41),
    AUTH_TYPE_PRIMARY_SMS(42),
    AUTH_TYPE_THIRD_CODE(43),
    AUTH_TYPE_EXDINGTALK(1024),
    AUTH_TYPE_TICKET(2048),
    AUTH_TYPE_UNKNOWN(-1);

    public int mValue;

    SFAuthType(int i) {
        this.mValue = i;
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mValue;
        if (i == 6) {
            return "AUTH_TYPE_RADIUS";
        }
        if (i == 7) {
            return "AUTH_TYPE_TOKEN";
        }
        if (i == 10) {
            return "AUTH_TYPE_AUTHOR";
        }
        if (i == 11) {
            return "AUTH_TYPE_DINGDING_CODE";
        }
        if (i == 1024) {
            return "AUTH_TYPE_EXDINGTALK";
        }
        if (i == 2048) {
            return "AUTH_TYPE_TICKET";
        }
        if (i == -1) {
            return "AUTH_TYPE_UNKNOWN";
        }
        if (i == 0) {
            return "AUTH_TYPE_CERTIFICATE";
        }
        if (i == 1) {
            return "AUTH_TYPE_PASSWORD";
        }
        if (i == 2 || i == 3) {
            return "AUTH_TYPE_SMS";
        }
        if (i == 4) {
            return "AUTH_TYPE_HARDID";
        }
        switch (i) {
            case 16:
                return "AUTH_TYPE_SESSION";
            case 17:
                return "AUTH_TYPE_NONE";
            case 18:
                return "AUTH_TYPE_RENEW_PASSWORD";
            default:
                switch (i) {
                    case 22:
                        return "AUTH_TYPE_RAND";
                    case 23:
                        return "AUTH_TYPE_SANGFORID";
                    case 24:
                        return "AUTH_TYPE_QYWECHAT";
                    case 25:
                        return "AUTH_TYPE_TOKEN_TOTP";
                    case 26:
                        return "AUTH_TYPE_TOKEN_RADIUS";
                    case 27:
                        return "AUTH_TYPE_TOKEN_HTTPS";
                    case 28:
                        return "AUTH_TYPE_AUTH_CHECK";
                    case 29:
                        return "AUTH_TYPE_PURE_BIND_AUTH_DEVICE";
                    case 30:
                        return "AUTH_TYPE_APPLY_BIND_AUTH_DEVICE";
                    case 31:
                        return "AUTH_TYPE_UNBIND_AUTH_DEVICE";
                    case 32:
                        return "AUTH_TYPE_PURE_TRUST_DEVICE";
                    case 33:
                        return "AUTH_TYPE_APPLY_TRUST_DEVICE";
                    case 34:
                        return "AUTH_TYPE_UNBIND_TRUST_DEVICE";
                    case 35:
                        return "AUTH_TYPE_PRE_ENHANCED";
                    case 36:
                        return "AUTH_TYPE_FORGET_PWD_PRE";
                    case 37:
                        return "AUTH_TYPE_FORGET_PWD";
                    case 38:
                        return "AUTH_TYPE_RESET_PWD";
                    case 39:
                        return "AUTH_TYPE_CAS";
                    case 40:
                        return "AUTH_TYPE_CAS_PRE";
                    case 41:
                        return "AUTH_TYPE_PRIMARY_SMS_PRE";
                    case 42:
                        return "AUTH_TYPE_PRIMARY_SMS";
                    case 43:
                        return "AUTH_TYPE_THIRD_CODE";
                    default:
                        return "SFAuthType UNKNOWN:" + this.mValue;
                }
        }
    }

    public static SFAuthType valueOf(int i) {
        if (i == 6) {
            return AUTH_TYPE_RADIUS;
        }
        if (i == 7) {
            return AUTH_TYPE_TOKEN;
        }
        if (i == 10) {
            return AUTH_TYPE_AUTHOR;
        }
        if (i == 11) {
            return AUTH_TYPE_DINGDING_CODE;
        }
        if (i == 1024) {
            return AUTH_TYPE_EXDINGTALK;
        }
        if (i == 2048) {
            return AUTH_TYPE_TICKET;
        }
        if (i == -1) {
            return AUTH_TYPE_UNKNOWN;
        }
        if (i == 0) {
            return AUTH_TYPE_CERTIFICATE;
        }
        if (i == 1) {
            return AUTH_TYPE_PASSWORD;
        }
        if (i == 2 || i == 3) {
            return AUTH_TYPE_SMS;
        }
        if (i == 4) {
            return AUTH_TYPE_HARDID;
        }
        switch (i) {
            case 16:
                return AUTH_TYPE_SESSION;
            case 17:
                return AUTH_TYPE_NONE;
            case 18:
                return AUTH_TYPE_RENEW_PASSWORD;
            default:
                switch (i) {
                    case 22:
                        return AUTH_TYPE_RAND;
                    case 23:
                        return AUTH_TYPE_SANGFORID;
                    case 24:
                        return AUTH_TYPE_QYWECHAT;
                    case 25:
                        return AUTH_TYPE_TOKEN_TOTP;
                    case 26:
                        return AUTH_TYPE_TOKEN_RADIUS;
                    case 27:
                        return AUTH_TYPE_TOKEN_HTTPS;
                    case 28:
                        return AUTH_TYPE_AUTH_CHECK;
                    case 29:
                        return AUTH_TYPE_PURE_BIND_AUTH_DEVICE;
                    case 30:
                        return AUTH_TYPE_APPLY_BIND_AUTH_DEVICE;
                    case 31:
                        return AUTH_TYPE_UNBIND_AUTH_DEVICE;
                    case 32:
                        return AUTH_TYPE_PURE_TRUST_DEVICE;
                    case 33:
                        return AUTH_TYPE_APPLY_TRUST_DEVICE;
                    case 34:
                        return AUTH_TYPE_UNBIND_TRUST_DEVICE;
                    case 35:
                        return AUTH_TYPE_PRE_ENHANCED;
                    case 36:
                        return AUTH_TYPE_FORGET_PWD_PRE;
                    case 37:
                        return AUTH_TYPE_FORGET_PWD;
                    case 38:
                        return AUTH_TYPE_RESET_PWD;
                    case 39:
                        return AUTH_TYPE_CAS;
                    case 40:
                        return AUTH_TYPE_CAS_PRE;
                    case 41:
                        return AUTH_TYPE_PRIMARY_SMS_PRE;
                    case 42:
                        return AUTH_TYPE_PRIMARY_SMS;
                    case 43:
                        return AUTH_TYPE_THIRD_CODE;
                    default:
                        throw new IllegalArgumentException("AuthType valueOf failed, invalid value = " + i);
                }
        }
    }
}
