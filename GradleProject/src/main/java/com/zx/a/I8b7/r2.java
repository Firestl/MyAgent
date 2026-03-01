package com.zx.a.I8b7;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public class r2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Handler f6274a = new Handler(Looper.getMainLooper());

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final r2 f6275a = new r2();
    }

    public boolean a() {
        return (t2.r == 1 && t2.s == 1 && t2.q == 1) || (t2.r == 0 && t2.q == 1);
    }

    public boolean b() {
        return t2.r == 1 && t2.s == -1 && t2.q == 1;
    }
}
