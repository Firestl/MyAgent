package com.heytap.mcssdk.f;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2579a = "shared_msg_sdk";
    public static final String b = "hasDefaultChannelCreated";

    public static void a(Context context, boolean z) {
        context.getSharedPreferences(f2579a, 0).edit().putBoolean(b, z).commit();
    }

    public static boolean a(Context context) {
        return context.getSharedPreferences(f2579a, 0).getBoolean(b, false);
    }
}
