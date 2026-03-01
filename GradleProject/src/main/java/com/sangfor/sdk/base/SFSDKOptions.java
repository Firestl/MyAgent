package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFSDKOptions {
    OPTIONS_KEY_AUTH_TIMEOUT("kOptionsAuthTimeOut"),
    OPTIONS_KEY_AUTH_LANGUAGE("kOptionsAuthLanguage"),
    OPTIONS_KEY_LOG_REPORT("kOptionsLogReport"),
    OPTIONS_KEY_RAND_SDK("kOptionsRandSdk"),
    OPTIONS_KEY_TRUST_TERMINAL_SDK("kOptionsTrustTerminalSdk");

    public String mValue;

    SFSDKOptions(String str) {
        this.mValue = str;
    }

    public String stringValue() {
        return this.mValue;
    }
}
