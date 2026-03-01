package com.cmic.gen.sdk.e;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
