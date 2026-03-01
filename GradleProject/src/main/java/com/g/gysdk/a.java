package com.g.gysdk;

import com.g.gysdk.a.aj;
import com.g.gysdk.a.ak;
import com.g.gysdk.a.al;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final GyCallBack f1945a;
    public boolean b;
    public final AtomicBoolean c = new AtomicBoolean(false);
    public final AtomicBoolean d = new AtomicBoolean(false);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1946e = System.currentTimeMillis();

    public a(GyCallBack gyCallBack, boolean z) {
        this.f1945a = gyCallBack;
        this.b = z;
    }

    public static void a(a aVar, b bVar) {
        a(true, aVar, bVar);
    }

    public static void a(boolean z, a aVar, b bVar) {
        if (aVar == null) {
            ak.e(new IllegalStateException("Error callback null!!!"));
        } else {
            aVar.a(z, bVar);
        }
    }

    private void a(boolean z, final b bVar) {
        if (bVar == null) {
            ak.e(new IllegalStateException("callback response null"));
            return;
        }
        if (this.c.get()) {
            ak.c(new IllegalStateException("callback stopped before"));
            return;
        }
        if (!z) {
            this.d.set(true);
        } else if (!this.c.compareAndSet(false, true)) {
            ak.c(new IllegalStateException("callback stopped before2"));
            return;
        } else if (!this.d.compareAndSet(false, true)) {
            ak.a("callback called before");
            return;
        }
        bVar.a(this.f1946e);
        if (this.f1945a != null) {
            al.a(al.b.UI, new Runnable() { // from class: com.g.gysdk.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (bVar.a()) {
                            a.this.f1945a.onSuccess(bVar.c());
                        } else {
                            a.this.f1945a.onFailed(bVar.c());
                        }
                    } catch (Throwable th) {
                        aj.a("gyCallBack exception:", th);
                    }
                }
            });
        }
        if (this.b) {
            com.g.gysdk.a.b.a(bVar.d(), bVar.g(), bVar.e(), bVar.f());
            return;
        }
        if (bVar.c() == null) {
            ak.e(new IllegalStateException("response.getResponse() == null"));
            return;
        }
        ak.a("no biLog, code:" + bVar.c().getCode() + "response:" + bVar.c().toString());
    }

    public void a() {
        this.f1946e = System.currentTimeMillis();
    }

    public void a(boolean z) {
        this.b = z;
    }
}
