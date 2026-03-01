package com.getui.gtc.dim.b;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final List<String> b = Arrays.asList("dim-2-1-21-5", "dim-2-1-21-3", "dim-2-1-21-2", "dim-2-1-21-1");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, h> f2158a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2159a = new c(0);
    }

    public c() {
        this.f2158a = new ConcurrentHashMap();
    }

    public /* synthetic */ c(byte b2) {
        this();
    }

    public static c a() {
        return a.f2159a;
    }

    public final void a(String str, Object obj, long j) {
        if (f.i(str)) {
            com.getui.gtc.dim.e.b.a(str + " skip dim ram cache = " + obj);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (j <= 0) {
            j = System.currentTimeMillis();
        }
        h hVar = new h(obj, j);
        com.getui.gtc.dim.e.b.a(str + " update dim ram cache = " + obj);
        this.f2158a.put(str, hVar);
    }
}
