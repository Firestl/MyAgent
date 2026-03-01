package com.g.gysdk.a;

import android.util.Base64;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class u extends t {
    public String h;

    public u(as asVar, String str, long j, v vVar) {
        super(asVar, str, j);
        if (vVar != null) {
            this.c = vVar.c;
            this.h = vVar.h;
            this.f = vVar.f;
            this.g = vVar.g;
        }
    }

    private String a(String str, String str2, String str3, String str4, String str5) {
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        if (str4 == null) {
            str4 = "";
        }
        if (str5 == null) {
            str5 = "";
        }
        return Base64.encodeToString((str + "|" + str2 + "|" + str3 + "|" + DefaultDiskStorage.DEFAULT_DISK_STORAGE_VERSION_PREFIX + "|" + str4 + "|" + str5).getBytes(), 0).replaceAll("[\\s*\t\n\r]", "");
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

    public String i() {
        return this.h;
    }

    public com.g.gysdk.b j() {
        JSONObject jSONObjectH = h();
        try {
            if (b()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("token", a(this.f, this.h, String.valueOf(this.f2043a.g), String.valueOf(this.f2043a.g), this.c));
                jSONObject.put("expiredTime", this.g);
                jSONObjectH.put("data", jSONObject);
            }
        } catch (Throwable th) {
            ak.e(th);
        }
        return new com.g.gysdk.b(b() ? GyCode.SUCCESS : GyCode.LOGIN_ERROR, this.f2044e, jSONObjectH.toString()).a("eLogin", this.c).a("costTime", Long.valueOf(a())).b(g());
    }
}
