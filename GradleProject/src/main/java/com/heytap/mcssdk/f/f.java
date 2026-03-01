package com.heytap.mcssdk.f;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ExecutorService f2581a = Executors.newSingleThreadExecutor();
    public static Handler b = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        f2581a.execute(runnable);
    }

    public static void b(Runnable runnable) {
        b.post(runnable);
    }
}
