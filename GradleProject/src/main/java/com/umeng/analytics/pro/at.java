package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SpWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5177a = "cl_count";
    public static final String b = "interval_";
    public static final String c = "config_ts";
    public static final String d = "iucc_s1";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f5178e = "iucc_s2";
    public static final String f = "sdk_type_ver";
    public static final String g = "should_fetch";
    public static final String h = "ccg_sp_config_file";

    public static SharedPreferences a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(h, 0);
        } catch (Throwable unused) {
            return null;
        }
    }
}
