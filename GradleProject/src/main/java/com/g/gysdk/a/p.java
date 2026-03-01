package com.g.gysdk.a;

import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.CtSetting;
import cn.com.chinatelecom.account.api.ResultListener;
import cn.com.chinatelecom.account.api.TraceLogger;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.n;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class p extends n implements TraceLogger {
    public p() {
        super(as.CT, 5);
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
        CtSetting ctSetting = new CtSetting();
        ctSetting.setTotalTimeout(i);
        CtAuth.getInstance().requestPreLogin(ctSetting, new ResultListener() { // from class: com.g.gysdk.a.p.1
            @Override // cn.com.chinatelecom.account.api.ResultListener
            public void onResult(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append("ct.preLogin: ");
                sb.append(str != null ? str : "");
                ak.a(sb.toString());
                v vVar = new v(p.this.f2015a, str, jCurrentTimeMillis);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i2 = jSONObject.getInt("result");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                    if (i2 != 0 || jSONObjectOptJSONObject == null) {
                        GyErrorCode gyErrorCodeA = ar.a(d.b);
                        if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                            gyErrorCodeA = i2 == 80000 ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                        }
                        vVar.a(gyErrorCodeA);
                    } else {
                        vVar.a(jSONObjectOptJSONObject.getString("accessCode"));
                        long j = jSONObjectOptJSONObject.getLong("expiredTime");
                        if (j > 3600) {
                            j = 3600;
                        }
                        vVar.a(System.currentTimeMillis() + (j * 1000));
                        vVar.b(jSONObjectOptJSONObject.getString("number"));
                        vVar.c(jSONObjectOptJSONObject.getString("gwAuth"));
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
        CtSetting ctSetting = new CtSetting();
        ctSetting.setTotalTimeout(i);
        CtAuth.getInstance().requestPreLogin(ctSetting, new ResultListener() { // from class: com.g.gysdk.a.p.2
            @Override // cn.com.chinatelecom.account.api.ResultListener
            public void onResult(String str2) {
                StringBuilder sb = new StringBuilder();
                sb.append("ct.preVerify: ");
                sb.append(str2 != null ? str2 : "");
                ak.a(sb.toString());
                w wVar = new w(p.this.f2015a, str2, jCurrentTimeMillis);
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    int i2 = jSONObject.getInt("result");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                    if (i2 != 0 || jSONObjectOptJSONObject == null) {
                        GyErrorCode gyErrorCodeA = ar.a(d.b);
                        if (gyErrorCodeA == GyErrorCode.SUCCESS) {
                            gyErrorCodeA = i2 == 80000 ? GyErrorCode.OPERATE_TIMEOUT : GyErrorCode.OPERATOR_RETURN_ERROR;
                        }
                        wVar.a(gyErrorCodeA);
                    } else {
                        wVar.a(jSONObjectOptJSONObject.getString("accessCode"));
                        long j = jSONObjectOptJSONObject.getLong("expiredTime");
                        if (j > 3600) {
                            j = 3600;
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
        CtAuth.mTraceLogger = z ? this : null;
    }

    @Override // com.g.gysdk.a.n
    public void b() {
        CtAuth.getInstance().init(d.c(), this.b, this.c, this.d ? this : null);
    }

    @Override // cn.com.chinatelecom.account.api.TraceLogger
    public void debug(String str, String str2) {
        if (this.d) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            aj.a("CtAuth_D " + str + Constants.COLON_SEPARATOR + str2);
        }
    }

    @Override // cn.com.chinatelecom.account.api.TraceLogger
    public void info(String str, String str2) {
        if (this.d) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            aj.a("CtAuth_I " + str + Constants.COLON_SEPARATOR + str2);
        }
    }

    @Override // cn.com.chinatelecom.account.api.TraceLogger
    public void warn(String str, String str2, Throwable th) {
        if (this.d) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            aj.b("CtAuth_W " + str + Constants.COLON_SEPARATOR + str2);
        }
    }
}
