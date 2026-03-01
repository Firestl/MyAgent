package com.cmic.gen.sdk;

import com.cmic.gen.sdk.a.a;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<String, Object> f1657a;

    public a(int i) {
        this.f1657a = new ConcurrentHashMap<>(i);
    }

    public com.cmic.gen.sdk.d.b a() {
        com.cmic.gen.sdk.d.b bVar = (com.cmic.gen.sdk.d.b) this.f1657a.get("logBean");
        return bVar != null ? bVar : new com.cmic.gen.sdk.d.b();
    }

    public void a(com.cmic.gen.sdk.a.a aVar) {
        if (aVar != null) {
            this.f1657a.put("current_config", aVar);
        }
    }

    public void a(com.cmic.gen.sdk.d.b bVar) {
        if (bVar != null) {
            this.f1657a.put("logBean", bVar);
        }
    }

    public void a(String str, int i) {
        if (str != null) {
            this.f1657a.put(str, Integer.valueOf(i));
        }
    }

    public void a(String str, long j) {
        if (str != null) {
            this.f1657a.put(str, Long.valueOf(j));
        }
    }

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.f1657a.put(str, str2);
    }

    public void a(String str, boolean z) {
        if (str != null) {
            this.f1657a.put(str, Boolean.valueOf(z));
        }
    }

    public void a(String str, byte[] bArr) {
        if (str == null || bArr == null) {
            return;
        }
        this.f1657a.put(str, bArr);
    }

    public byte[] a(String str) {
        if (str != null) {
            return (byte[]) this.f1657a.get(str);
        }
        return null;
    }

    public int b(String str, int i) {
        return (str == null || !this.f1657a.containsKey(str)) ? i : ((Integer) this.f1657a.get(str)).intValue();
    }

    public long b(String str, long j) {
        return (str == null || !this.f1657a.containsKey(str)) ? j : ((Long) this.f1657a.get(str)).longValue();
    }

    public com.cmic.gen.sdk.a.a b() {
        com.cmic.gen.sdk.a.a aVar = (com.cmic.gen.sdk.a.a) this.f1657a.get("current_config");
        if (aVar != null) {
            return aVar;
        }
        com.cmic.gen.sdk.e.c.a("UmcConfigBean为空", "请核查");
        return new a.C0014a().a();
    }

    public String b(String str) {
        return b(str, "");
    }

    public String b(String str, String str2) {
        return (str == null || !this.f1657a.containsKey(str)) ? str2 : (String) this.f1657a.get(str);
    }

    public boolean b(String str, boolean z) {
        return (str == null || !this.f1657a.containsKey(str)) ? z : ((Boolean) this.f1657a.get(str)).booleanValue();
    }

    public int c(String str) {
        return b(str, 0);
    }
}
