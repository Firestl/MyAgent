package com.cmic.gen.sdk.c.d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1720a;
    public Map<String, List<String>> b;
    public String c;

    public b(int i, Map<String, List<String>> map, String str) {
        this.f1720a = i;
        this.b = map;
        this.c = str;
    }

    public int a() {
        return this.f1720a;
    }

    public Map<String, List<String>> b() {
        Map<String, List<String>> map = this.b;
        return map == null ? new HashMap() : map;
    }

    public String c() {
        String str = this.c;
        return str == null ? "" : str;
    }

    public boolean d() {
        int i = this.f1720a;
        return i == 302 || i == 301;
    }
}
