package com.g.gysdk.a;

import android.text.TextUtils;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class v extends t {
    public String h;
    public String i;
    public boolean j;

    public v(as asVar, String str, long j) {
        super(asVar, str, j);
    }

    public static v a(int i) {
        if (i <= 0) {
            return null;
        }
        try {
            String strA = g.a().a(i);
            if (!TextUtils.isEmpty(strA)) {
                JSONObject jSONObject = new JSONObject(strA);
                jSONObject.getString("processId");
                int i2 = jSONObject.getInt("operatorValue");
                String string = jSONObject.getString("accessCode");
                long j = jSONObject.getLong("expireTime");
                String strOptString = jSONObject.optString("auth", null);
                String string2 = jSONObject.getString("number");
                v vVar = new v(as.a(i2), "", -1L);
                vVar.a(string);
                vVar.c(strOptString);
                vVar.a(j);
                vVar.b(string2);
                vVar.a(GyErrorCode.SUCCESS);
                vVar.j = true;
                return vVar;
            }
        } catch (Throwable th) {
            ak.e("ELoginResultPreLogin.getFromDb error", th);
        }
        return null;
    }

    public static void a(int i, v vVar) {
        if (i <= 0) {
            return;
        }
        if (vVar == null || !vVar.k()) {
            g.a().a(i, "");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("processId", vVar.c);
            jSONObject.put("operatorValue", vVar.f2043a.g);
            jSONObject.put("accessCode", vVar.f);
            jSONObject.put("expireTime", vVar.g);
            jSONObject.put("auth", vVar.h);
            jSONObject.put("number", vVar.i);
            g.a().a(i, jSONObject.toString());
        } catch (Throwable th) {
            ak.e("ELoginResultPreLogin.saveToDb error", th);
        }
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
        this.i = str;
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ boolean b() {
        return super.b();
    }

    @Override // com.g.gysdk.a.t
    public /* bridge */ /* synthetic */ String c() {
        return super.c();
    }

    public void c(String str) {
        this.h = str;
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
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public boolean k() {
        return b() && (this.f2043a == as.CM || !TextUtils.isEmpty(this.f)) && this.g > System.currentTimeMillis() - 15000;
    }

    public com.g.gysdk.b l() {
        JSONObject jSONObjectH = h();
        try {
            if (b()) {
                jSONObjectH.put("accessCode", this.f);
                jSONObjectH.put("number", this.i);
                jSONObjectH.put("expiredTime", this.g);
            }
        } catch (Throwable th) {
            ak.e(th);
        }
        return new com.g.gysdk.b(b() ? GyCode.SUCCESS : GyCode.PRELOGIN_ERROR, this.f2044e, jSONObjectH.toString()).a("preLogin", this.c).a("costTime", Long.valueOf(a())).b(g());
    }
}
