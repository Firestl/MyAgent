package com.g.gysdk.a;

import android.text.TextUtils;
import com.cmic.gen.sdk.auth.GenTokenListener;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.n;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class o extends n {
    public o() {
        super(as.CM, -1);
    }

    @Override // com.g.gysdk.a.n
    public void a(int i, final n.a aVar) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final v vVarE = e();
        if (vVarE == null || !vVarE.k()) {
            aVar.a(new u(this.f2015a, "登录-未预登录", 0L, vVarE));
        } else {
            m.a(d.b).a(i);
            m.a(d.b).b(this.b, this.c, new GenTokenListener() { // from class: com.g.gysdk.a.o.2
                @Override // com.cmic.gen.sdk.auth.GenTokenListener
                public void onGetTokenComplete(int i2, JSONObject jSONObject) {
                    String string = jSONObject != null ? jSONObject.toString() : null;
                    StringBuilder sb = new StringBuilder();
                    sb.append("cm.login: ");
                    sb.append(string != null ? string : "");
                    ak.a(sb.toString());
                    u uVar = new u(o.this.f2015a, string, jCurrentTimeMillis, vVarE);
                    try {
                        int i3 = jSONObject.getInt("resultCode");
                        String strOptString = jSONObject.optString("token");
                        if (i3 != 103000 || TextUtils.isEmpty(strOptString)) {
                            GyErrorCode gyErrorCodeA = ar.a(d.b);
                            if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                                gyErrorCodeA = i3 == 200023 ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                            }
                            uVar.a(gyErrorCodeA);
                        } else {
                            uVar.a(strOptString);
                            uVar.a(System.currentTimeMillis() + com.igexin.push.config.c.l);
                            uVar.a(GyErrorCode.SUCCESS);
                            o.this.a((v) null);
                        }
                    } catch (Throwable th) {
                        ak.c(th);
                        uVar.a(GyErrorCode.OPERATOR_RETURN_ERROR);
                    }
                    aVar.a(uVar);
                }
            });
        }
    }

    @Override // com.g.gysdk.a.n
    public void a(int i, final n.b bVar) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        m.a(d.b).a(i);
        m.a(d.b).a(this.b, this.c, new GenTokenListener() { // from class: com.g.gysdk.a.o.1
            @Override // com.cmic.gen.sdk.auth.GenTokenListener
            public void onGetTokenComplete(int i2, JSONObject jSONObject) {
                String string = jSONObject != null ? jSONObject.toString() : null;
                StringBuilder sb = new StringBuilder();
                sb.append("cm.preLogin: ");
                sb.append(string != null ? string : com.igexin.push.core.b.m);
                ak.a(sb.toString());
                v vVar = new v(o.this.f2015a, string, jCurrentTimeMillis);
                try {
                    int i3 = jSONObject.getInt("resultCode");
                    if (i3 == 103000) {
                        vVar.a(System.currentTimeMillis() + 3600000);
                        vVar.b(com.cmic.gen.sdk.e.k.b("securityphone", ""));
                        vVar.a(GyErrorCode.SUCCESS);
                    } else {
                        GyErrorCode gyErrorCodeA = ar.a(d.b);
                        if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                            gyErrorCodeA = i3 == 200023 ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                        }
                        vVar.a(gyErrorCodeA);
                    }
                } catch (Throwable th) {
                    ak.c(th);
                    vVar.a(GyErrorCode.OPERATOR_RETURN_ERROR);
                }
                bVar.a(vVar);
            }
        });
    }

    @Override // com.g.gysdk.a.n
    public void a(final String str, int i, final n.c cVar) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        m.a(d.b).a(i);
        m.a(d.b).c(this.b, this.c, new GenTokenListener() { // from class: com.g.gysdk.a.o.3
            @Override // com.cmic.gen.sdk.auth.GenTokenListener
            public void onGetTokenComplete(int i2, JSONObject jSONObject) {
                String string = jSONObject != null ? jSONObject.toString() : null;
                StringBuilder sb = new StringBuilder();
                sb.append("cm.preVerify: ");
                sb.append(string != null ? string : "");
                ak.a(sb.toString());
                w wVar = new w(o.this.f2015a, string, jCurrentTimeMillis);
                try {
                    int i3 = jSONObject.getInt("resultCode");
                    if (i3 == 103000) {
                        wVar.a(jSONObject.getString("token"));
                        wVar.a(System.currentTimeMillis() + com.igexin.push.config.c.l);
                        wVar.b(str);
                        wVar.a(GyErrorCode.SUCCESS);
                    } else {
                        GyErrorCode gyErrorCodeA = ar.a(d.b);
                        if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                            gyErrorCodeA = i3 == 200023 ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                        }
                        wVar.a(gyErrorCodeA);
                    }
                } catch (Throwable th) {
                    ak.c(th);
                    wVar.a(GyErrorCode.OPERATOR_RETURN_ERROR);
                }
                cVar.a(wVar);
            }
        });
    }

    @Override // com.g.gysdk.a.n
    public void a(boolean z) {
        super.a(z);
        m.a(z);
    }

    @Override // com.g.gysdk.a.n
    public void b() {
        m.a(this.d);
        m.a(d.b);
    }
}
