package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: compiled from: LocationRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ef extends ar {
    public Map<String, String> f;
    public String g;
    public String h;
    public byte[] i;
    public byte[] j;
    public boolean k;
    public String l;
    public Map<String, String> m;
    public boolean n;
    public String o;

    public ef(Context context, t tVar) {
        super(context, tVar);
        this.f = null;
        this.o = "";
        this.g = "";
        this.h = "";
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = null;
        this.n = false;
    }

    public final void a(String str) {
        this.g = str;
    }

    @Override // com.loc.ar
    public final byte[] a_() {
        return this.i;
    }

    @Override // com.loc.av
    public final Map<String, String> b() {
        return this.f;
    }

    public final void b(String str) {
        this.h = str;
    }

    @Override // com.loc.ar, com.loc.av
    public final Map<String, String> b_() {
        return this.m;
    }

    @Override // com.loc.av
    public final String c() {
        return this.g;
    }

    public final void c(String str) {
        if (TextUtils.isEmpty(str)) {
            this.o = "";
        } else {
            this.o = str;
        }
    }

    @Override // com.loc.q, com.loc.av
    public final String d() {
        return this.h;
    }

    @Override // com.loc.ar
    public final byte[] f() {
        return this.j;
    }

    @Override // com.loc.av
    public final String h() {
        return this.o;
    }

    @Override // com.loc.ar
    public final boolean j() {
        return this.k;
    }

    @Override // com.loc.ar
    public final String k() {
        return this.l;
    }

    @Override // com.loc.ar
    public final boolean l() {
        return this.n;
    }

    @Override // com.loc.av
    public final String p() {
        return "loc";
    }
}
