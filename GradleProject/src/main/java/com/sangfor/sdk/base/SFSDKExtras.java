package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum SFSDKExtras {
    EXTRA_KEY_FILE_ISOLATION("kExtraFileIsolation"),
    EXTRA_KEY_HOSTAPP_PACKAGE_NAME("kExtraHostAppPackageName"),
    EXTRA_KEY_AWORK_CURRENT_VERSION("kExtraAworkCurrentVersion"),
    EXTRA_KEY_INJECT_APPID("kExtraAppId"),
    EXTRA_KEY_ENABLE_APPSTORE("kExtraEnableAppStoreKey"),
    EXTRA_KEY_ENCAP_TYPE("KExtraEncapType");

    public String mValue;

    SFSDKExtras(String str) {
        this.mValue = str;
    }

    public String stringValue() {
        return this.mValue;
    }
}
