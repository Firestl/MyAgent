package com.zx.a.I8b7;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.zx.a.I8b7.c3;
import com.zx.a.I8b7.o2;
import com.zx.a.I8b7.r2;
import com.zx.module.annotation.Java2C;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class e2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f6213a = new AtomicBoolean(false);

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6214a;

        public a(Context context) {
            this.f6214a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                t2.b(this.f6214a);
                try {
                    e2.a().a();
                } catch (Throwable th) {
                    Log.i("core info Except", "can ignore," + th.getMessage());
                }
            } catch (Throwable th2) {
                n2.a(th2, m2.a("ZXCore init failed: "));
                e2.f6213a.set(false);
            }
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e2 f6215a = new e2();
    }

    public static o2 a() {
        Handler handler = r2.f6274a;
        r2 r2Var = r2.a.f6275a;
        if (r2Var.b()) {
            throw new RuntimeException("请先调用 ZXManager.checkPermission() 检查用户是否已授权");
        }
        if (!r2Var.a()) {
            throw new RuntimeException("用户未授权");
        }
        AtomicBoolean atomicBoolean = o2.f6246e;
        return o2.e.f6252a;
    }

    public static final e2 b() {
        if (f6213a.get()) {
            return b.f6215a;
        }
        throw new IllegalStateException("ZXManager not init, should init firstly");
    }

    @Java2C.Method2C
    public native String a(String str, String str2) throws Throwable;

    public static void a(Context context) {
        try {
            if (f6213a.getAndSet(true)) {
                return;
            }
            c3.e.f6204a.f6203a.execute(new a(context));
        } catch (Throwable th) {
            f6213a.set(false);
            l.b("ZXManager.init failed:" + th);
        }
    }
}
