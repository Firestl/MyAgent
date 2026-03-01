package com.sangfor.sdk.base;

import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFLaunchReason {
    UNKNOWN(-1),
    Launch_HOSTAPP_AUTH_AUTHORIZATION(0),
    Launch_HOSTAPP_APPLOCK_AUTHORIZATION(1),
    Launch_SUBAPP_AUTH_BACK(2),
    Launch_SUBAPP_APPLOCK_BACK(3),
    Launch_SUBAPP_ACTIVE(4),
    Launch_SUBAPP_AUTH_REFUSE_BACK(5),
    Launch_SUBAPP_SSO_RECORD(6),
    Launch_HOSTAPP_SSO_RECORD_BACK(7),
    Launch_SUBAPP_PUSH_DATA(8),
    Launch_SUBAPP_SHARE_LOG(9),
    LAUNCH_SUBAPP_UPLOAD_LOG(11);

    public static final String TAG = "SFLaunchReason";
    public final int mValue;

    SFLaunchReason(int i) {
        this.mValue = i;
    }

    public static SFLaunchReason ValueOf(int i) {
        switch (i) {
            case -1:
                return UNKNOWN;
            case 0:
                return Launch_HOSTAPP_AUTH_AUTHORIZATION;
            case 1:
                return Launch_HOSTAPP_APPLOCK_AUTHORIZATION;
            case 2:
                return Launch_SUBAPP_AUTH_BACK;
            case 3:
                return Launch_SUBAPP_APPLOCK_BACK;
            case 4:
                return Launch_SUBAPP_ACTIVE;
            case 5:
                return Launch_SUBAPP_AUTH_REFUSE_BACK;
            case 6:
                return Launch_SUBAPP_SSO_RECORD;
            case 7:
                return Launch_HOSTAPP_SSO_RECORD_BACK;
            case 8:
                return Launch_SUBAPP_PUSH_DATA;
            case 9:
                return Launch_SUBAPP_SHARE_LOG;
            case 10:
            default:
                SFLogN.b(TAG, "ValueOf failed.", "unknown value:" + i + " will ret UNKNOWN");
                return UNKNOWN;
            case 11:
                return LAUNCH_SUBAPP_UPLOAD_LOG;
        }
    }

    public int intValue() {
        return this.mValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        switch (this.mValue) {
            case 0:
                return "Launch_HOSTAPP_AUTH_AUTHORIZATION";
            case 1:
                return "Launch_HOSTAPP_APPLOCK_AUTHORIZATION";
            case 2:
                return "Launch_SUBAPP_AUTH_BACK";
            case 3:
                return "Launch_SUBAPP_APPLOCK_BACK";
            case 4:
                return "Launch_SUBAPP_ACTIVE";
            case 5:
                return "Launch_SUBAPP_AUTH_REFUSE_BACK";
            case 6:
                return "Launch_SUBAPP_SSO_RECORD";
            case 7:
                return "Launch_HOSTAPP_SSO_RECORD_BACK";
            case 8:
                return "Launch_SUBAPP_PUSH_DATA";
            case 9:
                return "Launch_SUBAPP_SHARE_LOG";
            case 10:
            default:
                return "SFLaunchReason UNKNOWN:" + this.mValue;
            case 11:
                return "LAUNCH_SUBAPP_UPLOAD_LOG";
        }
    }
}
