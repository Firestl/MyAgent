package com.g.gysdk.a;

import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.n;
import com.unicom.xiaowo.account.shield.ResultListener;
import com.unicom.xiaowo.account.shield.UniAccountHelper;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class q extends n {
    public q() {
        super(as.CU, 6);
    }

    @Override // com.g.gysdk.a.n
    public void a(int i, n.a aVar) {
        u uVar;
        v vVarE = e();
        if (vVarE == null || !vVarE.k()) {
            uVar = new u(this.f2015a, "登录-未预登录", 0L, vVarE);
        } else {
            uVar = new u(this.f2015a, "", 0L, vVarE);
            uVar.a(GyErrorCode.SUCCESS);
            a((v) null);
        }
        aVar.a(uVar);
    }

    @Override // com.g.gysdk.a.n
    public void a(int i, final n.b bVar) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        UniAccountHelper.getInstance().login(i, new ResultListener() { // from class: com.g.gysdk.a.q.1
            @Override // com.unicom.xiaowo.account.shield.ResultListener
            public void onResult(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append("cu.preLogin: ");
                sb.append(str != null ? str : "");
                ak.a(sb.toString());
                v vVar = new v(q.this.f2015a, str, jCurrentTimeMillis);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String strOptString = jSONObject.optString("resultCode");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("resultData");
                    jSONObject.optString("resultMsg");
                    if (!"0".equals(strOptString) || jSONObjectOptJSONObject == null) {
                        GyErrorCode gyErrorCodeA = ar.a(d.b);
                        if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                            gyErrorCodeA = "10000".equals(strOptString) ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                        }
                        vVar.a(gyErrorCodeA);
                    } else {
                        vVar.a(jSONObjectOptJSONObject.getString("accessCode"));
                        long j = jSONObjectOptJSONObject.getLong("expires");
                        if (j > 1800) {
                            j = 1800;
                        }
                        vVar.a(System.currentTimeMillis() + (j * 1000));
                        vVar.b(jSONObjectOptJSONObject.getString("mobile"));
                        vVar.a(GyErrorCode.SUCCESS);
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
        UniAccountHelper.getInstance().mobileAuth(i, new ResultListener() { // from class: com.g.gysdk.a.q.2
            @Override // com.unicom.xiaowo.account.shield.ResultListener
            public void onResult(String str2) {
                StringBuilder sb = new StringBuilder();
                sb.append("cu.preVerify: ");
                sb.append(str2 != null ? str2 : "");
                ak.a(sb.toString());
                w wVar = new w(q.this.f2015a, str2, jCurrentTimeMillis);
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    String strOptString = jSONObject.optString("resultCode", "");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("resultData");
                    if (!"0".equals(strOptString) || jSONObjectOptJSONObject == null) {
                        GyErrorCode gyErrorCodeA = ar.a(d.b);
                        if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                            gyErrorCodeA = "10000".equals(strOptString) ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                        }
                        wVar.a(gyErrorCodeA);
                    } else {
                        wVar.a(jSONObjectOptJSONObject.getString("accessCode"));
                        long j = jSONObjectOptJSONObject.getLong("expires");
                        if (j > 1800) {
                            j = 1800;
                        }
                        wVar.a(System.currentTimeMillis() + (j * 1000));
                        wVar.b(str);
                        wVar.a(GyErrorCode.SUCCESS);
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
        UniAccountHelper.getInstance().setLogEnable(z);
    }

    @Override // com.g.gysdk.a.n
    public void b() {
        UniAccountHelper.getInstance().setLogEnable(this.d);
        UniAccountHelper.getInstance().init(d.c(), this.b, this.c);
    }
}
