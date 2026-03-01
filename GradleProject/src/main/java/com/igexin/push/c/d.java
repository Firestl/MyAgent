package com.igexin.push.c;

import com.lzy.okgo.cookie.SerializableCookie;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static final String f = "DT_DetectResult";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3259a;
    public int b;
    public String g;
    public int h;
    public int i;
    public long c = 2147483647L;
    public long d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3260e = true;
    public final int j = 1;

    public d() {
    }

    public d(String str, int i) {
        this.g = str;
        this.b = i;
    }

    private void a(int i) {
        this.b = i;
    }

    private void a(long j) {
        this.c = j;
    }

    private void b(long j) {
        this.d = j;
    }

    private void b(String str) {
        this.f3259a = str;
    }

    private void b(boolean z) {
        this.f3260e = z;
    }

    private String g() {
        return this.f3259a;
    }

    private int h() {
        return this.b;
    }

    private void i() {
        this.f3259a = null;
        this.h = 0;
        this.f3260e = true;
    }

    private boolean j() {
        return this.f3259a != null && System.currentTimeMillis() - this.d <= b.d && this.h <= 0;
    }

    public final synchronized String a() {
        return this.g;
    }

    public final synchronized String a(boolean z) {
        if (j()) {
            if (z) {
                this.h++;
            }
            this.f3260e = false;
            return this.f3259a;
        }
        this.f3259a = null;
        this.h = 0;
        this.f3260e = true;
        com.igexin.c.a.c.a.a("DT_DetectResult|disc, ip is invalid, use domain = " + this.g, new Object[0]);
        if (z) {
            this.i++;
        }
        return this.g;
    }

    public final synchronized void a(String str) {
        this.g = str;
    }

    public final synchronized void a(String str, long j, long j2) {
        this.f3259a = str;
        this.c = j;
        this.d = j2;
        this.h = 0;
        this.i = 0;
        this.f3260e = false;
    }

    public final synchronized void b() {
        this.f3259a = null;
        this.c = 2147483647L;
        this.d = -1L;
        this.f3260e = true;
        this.h = 0;
    }

    public final synchronized long c() {
        return this.c;
    }

    public final synchronized boolean d() {
        if (j()) {
            return true;
        }
        if (this.i <= 0) {
            return true;
        }
        this.i = 0;
        return false;
    }

    public final synchronized void e() {
        this.h = 0;
        this.i = 0;
    }

    public final JSONObject f() {
        if (this.g != null && this.f3259a != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(SerializableCookie.DOMAIN, this.g);
                jSONObject.put("ip", this.f3259a);
                if (this.c != 2147483647L) {
                    jSONObject.put("consumeTime", this.c);
                }
                jSONObject.put("port", this.b);
                if (this.d != -1) {
                    jSONObject.put("detectSuccessTime", this.d);
                }
                jSONObject.put("isDomain", this.f3260e);
                jSONObject.put("connectTryCnt", 1);
                return jSONObject;
            } catch (JSONException e2) {
                com.igexin.c.a.c.a.a(f, e2.toString());
            }
        }
        return null;
    }
}
