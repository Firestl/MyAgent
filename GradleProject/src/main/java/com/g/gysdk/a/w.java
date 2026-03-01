package com.g.gysdk.a;

import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class w extends t {
    public String h;

    public w(as asVar, String str, long j) {
        super(asVar, str, j);
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

    public void b(String str) {
        this.h = str;
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
        JSONObject jSONObjectH = h();
        try {
            if (b()) {
                jSONObjectH.put("accessCode", this.f);
                jSONObjectH.put("phone", this.h);
                jSONObjectH.put("expiredTime", this.g);
            }
        } catch (Throwable th) {
            ak.e(th);
        }
        return new com.g.gysdk.b(b() ? GyCode.SUCCESS : GyCode.PREVERIFY_ERROR, this.f2044e, jSONObjectH.toString()).a("getAccessCode", this.c).a("costTime", Long.valueOf(a())).b(g());
    }
}
