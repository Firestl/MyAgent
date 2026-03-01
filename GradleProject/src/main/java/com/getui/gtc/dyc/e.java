package com.getui.gtc.dyc;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f2201a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f2202a = new e();
    }

    public e() {
        this.f2201a = c.a();
    }

    public static e a() {
        return a.f2202a;
    }

    public h a(String str) {
        return this.f2201a.a(str);
    }

    public boolean a(String str, h hVar) {
        if (hVar == null) {
            return false;
        }
        return this.f2201a.a(str, hVar);
    }

    public HashMap<String, h> c() {
        return this.f2201a.c();
    }
}
