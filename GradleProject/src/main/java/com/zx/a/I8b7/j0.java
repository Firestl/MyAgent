package com.zx.a.I8b7;

import com.zx.module.base.Callback;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class j0 {

    public class a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f6233a;
        public final /* synthetic */ CountDownLatch b;

        public a(String[] strArr, CountDownLatch countDownLatch) {
            this.f6233a = strArr;
            this.b = countDownLatch;
        }

        @Override // com.zx.module.base.Callback
        public void callback(String str) {
            try {
                this.f6233a[0] = str;
                this.b.countDown();
            } catch (Throwable unused) {
            }
        }
    }

    public static final String a() {
        String[] strArr = {""};
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread thread = new Thread(new k0(new a(strArr, countDownLatch)));
            thread.setUncaughtExceptionHandler(new l0());
            thread.start();
            countDownLatch.await(1L, TimeUnit.SECONDS);
        } catch (Throwable unused) {
        }
        return strArr[0];
    }
}
