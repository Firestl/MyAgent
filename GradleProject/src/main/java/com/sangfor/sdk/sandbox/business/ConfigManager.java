package com.sangfor.sdk.sandbox.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import supwisdom.pb1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class ConfigManager {
    public static ConfigManager b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, pb1> f3936a = new HashMap(13);

    public static ConfigManager a() {
        synchronized (ConfigManager.class) {
            if (b == null) {
                b = new ConfigManager();
            }
        }
        return b;
    }

    public static native String getEmmPolicy(String str);

    public static native ArrayList<String> getEmmWhiteAppList();

    public static native void updatePolicyNative(String str);

    public static pb1 a(String str) {
        return a().f3936a.get(str);
    }
}
