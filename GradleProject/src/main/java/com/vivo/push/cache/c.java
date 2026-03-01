package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.g;
import com.vivo.push.util.o;
import com.vivo.push.util.w;
import com.vivo.push.util.y;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ICacheSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f5602a = new Object();
    public List<T> b = new ArrayList();
    public Context c;
    public byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f5603e;

    public c(Context context) {
        this.c = ContextDelegate.getContext(context);
        w wVarB = w.b();
        wVarB.a(this.c);
        this.d = wVarB.c();
        this.f5603e = wVarB.d();
        c();
    }

    private String b() {
        return y.b(this.c).a(a(), null);
    }

    private void d(String str) {
        y.b(this.c).b(a(), str);
    }

    public abstract String a();

    public abstract List<T> a(String str);

    public abstract String b(String str) throws Exception;

    public final void c() {
        synchronized (f5602a) {
            g.a(a());
            this.b.clear();
            c(b());
        }
    }

    public final byte[] e() {
        byte[] bArr = this.d;
        return (bArr == null || bArr.length <= 0) ? w.b().c() : bArr;
    }

    public final byte[] f() {
        byte[] bArr = this.f5603e;
        return (bArr == null || bArr.length <= 0) ? w.b().d() : bArr;
    }

    public final void d() {
        synchronized (f5602a) {
            this.b.clear();
            d("");
            o.d("CacheSettings", "clear " + a() + " strApps");
        }
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            o.d("CacheSettings", "ClientManager init " + a() + " strApps empty.");
            return;
        }
        if (str.length() > 10000) {
            o.d("CacheSettings", "sync " + a() + " strApps lenght too large");
            d();
            return;
        }
        try {
            o.d("CacheSettings", "ClientManager init " + a() + " strApps : " + str);
            List<T> listA = a(b(str));
            if (listA != null) {
                this.b.addAll(listA);
            }
        } catch (Exception e2) {
            d();
            o.d("CacheSettings", o.a(e2));
        }
    }
}
