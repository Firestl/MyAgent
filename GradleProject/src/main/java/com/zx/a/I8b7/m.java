package com.zx.a.I8b7;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f6240a;

    public static int a(Context context, float f) {
        if (f6240a == 0.0f) {
            f6240a = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((f * f6240a) + 0.5f);
    }
}
