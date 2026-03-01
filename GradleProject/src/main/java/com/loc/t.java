package com.loc;

import android.text.TextUtils;

/* JADX INFO: compiled from: SDKInfo.java */
/* JADX INFO: loaded from: classes2.dex */
@af(a = "a")
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ag(a = "a1", b = 6)
    public String f3833a;

    @ag(a = "a2", b = 6)
    public String b;

    @ag(a = "a6", b = 2)
    public int c;

    @ag(a = "a3", b = 6)
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @ag(a = "a4", b = 6)
    public String f3834e;

    @ag(a = "a5", b = 6)
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String[] l;

    /* JADX INFO: compiled from: SDKInfo.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3835a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3836e = true;
        public String f = "standard";
        public String[] g = null;

        public a(String str, String str2, String str3) {
            this.f3835a = str2;
            this.b = str2;
            this.d = str3;
            this.c = str;
        }

        public final a a(String str) {
            this.b = str;
            return this;
        }

        public final a a(String[] strArr) {
            if (strArr != null) {
                this.g = (String[]) strArr.clone();
            }
            return this;
        }

        public final t a() throws j {
            if (this.g != null) {
                return new t(this, (byte) 0);
            }
            throw new j("sdk packages is null");
        }
    }

    public t() {
        this.c = 1;
        this.l = null;
    }

    public t(a aVar) {
        this.c = 1;
        this.l = null;
        this.g = aVar.f3835a;
        this.h = aVar.b;
        this.j = aVar.c;
        this.i = aVar.d;
        this.c = aVar.f3836e ? 1 : 0;
        this.k = aVar.f;
        this.l = aVar.g;
        this.b = u.b(this.h);
        this.f3833a = u.b(this.j);
        this.d = u.b(this.i);
        this.f3834e = u.b(a(this.l));
        this.f = u.b(this.k);
    }

    public /* synthetic */ t(a aVar, byte b) {
        this(aVar);
    }

    public static String a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(";");
            }
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String[] a(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f3833a)) {
            this.j = u.c(this.f3833a);
        }
        return this.j;
    }

    public final void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public final String b() {
        return this.g;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = u.c(this.b);
        }
        return this.h;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = u.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public final boolean e() {
        return this.c == 1;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (t.class != obj.getClass()) {
            return false;
        }
        try {
            if (this.j.equals(((t) obj).j) && this.g.equals(((t) obj).g)) {
                if (this.h.equals(((t) obj).h)) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public final String[] f() {
        String[] strArr = this.l;
        if ((strArr == null || strArr.length == 0) && !TextUtils.isEmpty(this.f3834e)) {
            this.l = a(u.c(this.f3834e));
        }
        return (String[]) this.l.clone();
    }
}
