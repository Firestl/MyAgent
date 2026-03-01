package com.cmic.gen.sdk.c.c;

import android.net.Network;
import com.cmic.gen.sdk.c.b.g;
import com.cmic.gen.sdk.e.e;
import com.loopj.android.http.RequestParams;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1717a;
    public final String b;
    public final Map<String, String> c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1718e;
    public final String f;
    public Network g;
    public long h;
    public final String i;
    public int j;
    public final g k;

    public c(String str, g gVar, String str2, String str3) {
        this(str, null, gVar, str2, str3);
    }

    public c(String str, Map<String, String> map, g gVar, String str2, String str3) {
        this.f1718e = false;
        this.b = str;
        this.k = gVar;
        this.c = map == null ? new HashMap<>() : map;
        this.f1717a = gVar == null ? "" : gVar.b().toString();
        this.d = str2;
        this.f = str3;
        this.i = gVar != null ? gVar.a() : "";
        l();
    }

    private void l() {
        this.c.put(com.heytap.mcssdk.a.a.o, com.cmic.gen.sdk.auth.c.SDK_VERSION);
        this.c.put("Content-Type", RequestParams.APPLICATION_JSON);
        this.c.put("CMCC-EncryptType", "STD");
        this.c.put("traceId", this.f);
        this.c.put("appid", this.i);
        this.c.put(HttpHeaders.HEAD_KEY_CONNECTION, "close");
    }

    public String a() {
        return this.b;
    }

    public void a(long j) {
        this.h = j;
    }

    public void a(Network network) {
        this.g = network;
    }

    public void a(String str, String str2) {
        this.c.put(str, str2);
    }

    public void a(boolean z) {
        this.f1718e = z;
    }

    public boolean b() {
        return this.f1718e;
    }

    public Map<String, String> c() {
        return this.c;
    }

    public String d() {
        return this.f1717a;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.f;
    }

    public boolean g() {
        return !e.a(this.f) || this.b.contains("logReport") || this.b.contains("uniConfig");
    }

    public Network h() {
        return this.g;
    }

    public long i() {
        return this.h;
    }

    public boolean j() {
        int i = this.j;
        this.j = i + 1;
        return i < 2;
    }

    public g k() {
        return this.k;
    }
}
