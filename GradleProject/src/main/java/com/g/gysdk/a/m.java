package com.g.gysdk.a;

import android.content.Context;
import com.cmic.gen.sdk.auth.GenTokenListener;

/* JADX INFO: loaded from: classes.dex */
public class m {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final m f2014a = new m();
    }

    public m() {
        com.cmic.gen.sdk.auth.c.getInstance(d.c());
    }

    public static m a(Context context) {
        return a.f2014a;
    }

    public static void a(boolean z) {
        com.cmic.gen.sdk.auth.c.setDebugMode(z);
    }

    public void a(long j) {
        com.cmic.gen.sdk.auth.c.getInstance(d.b).setOverTime(j);
    }

    public void a(String str, String str2, GenTokenListener genTokenListener) {
        com.cmic.gen.sdk.auth.c.getInstance(d.b).getPhoneInfo(str, str2, genTokenListener);
    }

    public void b(String str, String str2, GenTokenListener genTokenListener) {
        com.cmic.gen.sdk.auth.c.getInstance(d.b).loginAuth(str, str2, genTokenListener);
    }

    public void c(String str, String str2, GenTokenListener genTokenListener) {
        com.cmic.gen.sdk.auth.c.getInstance(d.b).mobileAuth(str, str2, genTokenListener);
    }
}
