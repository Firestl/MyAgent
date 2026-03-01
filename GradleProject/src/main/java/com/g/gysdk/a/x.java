package com.g.gysdk.a;

import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;

/* JADX INFO: loaded from: classes.dex */
public class x extends t {
    public x(as asVar, String str, long j, String str2) {
        super(asVar, str, j);
        this.c = str2;
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ long a() {
        return super.a();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ void a(long j) {
        super.a(j);
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ void a(GyErrorCode gyErrorCode) {
        super.a(gyErrorCode);
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ void a(String str) {
        super.a(str);
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ boolean b() {
        return super.b();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ String c() {
        return super.c();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ long d() {
        return super.d();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ as e() {
        return super.e();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ String f() {
        return super.f();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ String g() {
        return super.g();
    }

    public com.g.gysdk.b i() {
        return new com.g.gysdk.b(b() ? GyCode.SUCCESS : GyCode.VERIFY_ERROR, this.f2044e, h().toString());
    }
}
