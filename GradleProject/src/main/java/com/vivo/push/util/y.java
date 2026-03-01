package com.vivo.push.util;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: compiled from: SystemCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<String, Integer> f5646a = new HashMap<>();
    public static final HashMap<String, Long> b = new HashMap<>();
    public static final HashMap<String, String> c = new HashMap<>();
    public static y d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f5647e;
    public d f;
    public boolean g;

    public y(Context context) {
        this.g = false;
        this.f5647e = context;
        this.g = a(context);
        o.d("SystemCache", "init status is " + this.g + ";  curCache is " + this.f);
    }

    public static synchronized y b(Context context) {
        if (d == null) {
            d = new y(context.getApplicationContext());
        }
        return d;
    }

    public final void a() {
        x xVar = new x();
        if (xVar.a(this.f5647e)) {
            xVar.a();
            o.d("SystemCache", "sp cache is cleared");
        }
    }

    @Override // com.vivo.push.util.d
    public final void b(String str, String str2) {
        d dVar;
        c.put(str, str2);
        if (!this.g || (dVar = this.f) == null) {
            return;
        }
        dVar.b(str, str2);
    }

    @Override // com.vivo.push.util.d
    public final boolean a(Context context) {
        v vVar = new v();
        this.f = vVar;
        boolean zA = vVar.a(context);
        if (!zA) {
            u uVar = new u();
            this.f = uVar;
            zA = uVar.a(context);
        }
        if (!zA) {
            x xVar = new x();
            this.f = xVar;
            zA = xVar.a(context);
        }
        if (!zA) {
            this.f = null;
        }
        return zA;
    }

    @Override // com.vivo.push.util.d
    public final String a(String str, String str2) {
        d dVar;
        String str3 = c.get(str);
        return (str3 != null || (dVar = this.f) == null) ? str3 : dVar.a(str, str2);
    }
}
