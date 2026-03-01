package cn.com.chinatelecom.account.api.c;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class e implements Runnable {
    public static Handler c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1499a = false;
    public long b;
    public a d;

    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f1500a;

        public a(e eVar) {
            this.f1500a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = this.f1500a;
            if (eVar != null) {
                eVar.b();
            }
        }
    }

    public e() {
    }

    public e(long j) {
        this.b = j;
    }

    private void e() {
        a aVar = new a(this);
        this.d = aVar;
        c.postDelayed(aVar, this.b);
    }

    public abstract void a();

    public void a(boolean z) {
        this.f1499a = z;
    }

    public void b() {
    }

    public boolean c() {
        return this.f1499a;
    }

    public void d() {
        try {
            if (this.d != null) {
                c.removeCallbacks(this.d);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b > 0) {
            e();
        }
        a();
    }
}
