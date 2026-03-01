package com.loc;

import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: compiled from: HttpRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ed extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, String> f3774a = null;
    public Map<String, String> b = null;
    public String f = "";
    public byte[] g = null;
    public String h = null;

    public final void a(String str) {
        this.f = str;
    }

    public final void a(Map<String, String> map) {
        this.f3774a = map;
    }

    public final void a(byte[] bArr) {
        this.g = bArr;
    }

    @Override // com.loc.av
    public final Map<String, String> b() {
        return this.f3774a;
    }

    public final void b(String str) {
        this.h = str;
    }

    public final void b(Map<String, String> map) {
        this.b = map;
    }

    @Override // com.loc.av
    public final Map<String, String> b_() {
        return this.b;
    }

    @Override // com.loc.av
    public final String c() {
        return this.f;
    }

    @Override // com.loc.q, com.loc.av
    public final String d() {
        return !TextUtils.isEmpty(this.h) ? this.h : super.d();
    }

    @Override // com.loc.av
    public final byte[] e() {
        return this.g;
    }
}
