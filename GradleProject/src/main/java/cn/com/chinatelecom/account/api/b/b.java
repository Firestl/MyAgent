package cn.com.chinatelecom.account.api.b;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.CtSetting;
import cn.com.chinatelecom.account.api.Helper;
import cn.com.chinatelecom.account.api.a.d;
import cn.com.chinatelecom.account.api.c.c;
import cn.com.chinatelecom.account.api.c.e;
import cn.com.chinatelecom.account.api.d.g;
import cn.com.chinatelecom.account.api.e.f;
import cn.com.chinatelecom.account.api.e.h;
import cn.com.chinatelecom.account.api.e.j;
import com.taobao.weex.wson.Wson;
import java.util.HashMap;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f1474a = {122, 99, 122, Wson.BOOLEAN_TYPE_FALSE};
    public static final byte[] b = {44, 104, 120, 99, 109, 75, 122, 122, 55};
    public static final byte[] c = {44, 121, 126, 55};
    public static final byte[] d = {44, 104, 126, 55};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f1475e = {44, 107, 127, 126, 98, 94, Wson.STRING_TYPE, 122, KeyFactorySpi.x448_type, 55};
    public static final String f = "b";
    public c g;

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(Context context, String str, String str2, Network network, String str3, String str4) {
        try {
            g.a aVar = new g.a();
            aVar.a(str4);
            aVar.a(false, cn.com.chinatelecom.account.api.d.c.a(), d.a(cn.com.chinatelecom.account.api.e.b.f));
            aVar.b(str3);
            aVar.a(network);
            aVar.a(CtSetting.getConnTimeout(null));
            aVar.b(CtSetting.getReadTimeout(null));
            HashMap map = new HashMap();
            map.put(d.a(f1474a), Helper.dnprecohdjs());
            aVar.a(map);
            JSONObject jSONObjectA = cn.com.chinatelecom.account.api.e.a.a(context, new cn.com.chinatelecom.account.api.d.b(context).a(str2, "", 0, aVar.a()), str, network, true, str3);
            f.b(str3, jSONObjectA, str2);
            return jSONObjectA;
        } catch (Throwable th) {
            JSONObject jSONObjectG = j.g();
            f.a(str3).g("gpm ：" + th.getMessage()).a(80102).e(d.a(j.k));
            CtAuth.warn(f, "GPM Throwable", th);
            return jSONObjectG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        c cVar = this.g;
        if (cVar != null) {
            cVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2, long j, String str3, a aVar) {
        f.a(str2).a(i).e(str).b(j).g(str3);
        f.c(str2);
        String strA = j.a(i, str, str2);
        if (aVar != null) {
            aVar.callbackPreCode(strA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str, final String str2, final Network network, long j, final String str3, final String str4, final a aVar) {
        new cn.com.chinatelecom.account.api.c.d().a(new e(j) { // from class: cn.com.chinatelecom.account.api.b.b.3
            @Override // cn.com.chinatelecom.account.api.c.e
            public void a() {
                JSONObject jSONObjectA = b.this.a(context, str, str2, network, str3, str4);
                synchronized (this) {
                    if (!c()) {
                        a(true);
                        d();
                        if (aVar != null) {
                            try {
                                jSONObjectA.put("reqId", str3);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            aVar.callbackPreCode(jSONObjectA.toString());
                        }
                        f.c(str3);
                    }
                }
                if (network != null) {
                    b.this.a();
                }
            }

            @Override // cn.com.chinatelecom.account.api.c.e
            public void b() {
                super.b();
                synchronized (this) {
                    if (!c()) {
                        a(true);
                        b.this.a(80000, d.a(j.f1522a), str3, 0L, "", aVar);
                    }
                }
                if (network != null) {
                    b.this.a();
                }
            }
        });
    }

    public void a(String str, a aVar) {
        String strOptString;
        String str2;
        JSONObject jSONObject;
        Context context = CtAuth.getInstance().getContext();
        String strOptString2 = null;
        String strOptString3 = "";
        if (TextUtils.isEmpty(str)) {
            str2 = null;
        } else {
            try {
                jSONObject = new JSONObject(str);
                strOptString3 = jSONObject.optString("st");
                strOptString = jSONObject.optString("bt");
            } catch (JSONException e2) {
                e = e2;
                strOptString = null;
            }
            try {
                strOptString2 = jSONObject.optString("authType");
            } catch (JSONException e3) {
                e = e3;
                e.printStackTrace();
            }
            String str3 = strOptString2;
            strOptString2 = strOptString;
            str2 = str3;
        }
        long jA = cn.com.chinatelecom.account.api.e.a.a(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(d.a(b));
        stringBuffer.append(CtAuth.mAppId);
        stringBuffer.append(d.a(c));
        stringBuffer.append(strOptString3);
        stringBuffer.append(d.a(d));
        if (TextUtils.isEmpty(strOptString2)) {
            strOptString2 = "tyx";
        }
        stringBuffer.append(strOptString2);
        stringBuffer.append(d.a(f1475e));
        if (TextUtils.isEmpty(str2)) {
            str2 = "2";
        }
        stringBuffer.append(str2);
        String strA = h.a(context, jA, stringBuffer.toString());
        if (aVar != null) {
            aVar.callbackPreCodeParams(strA);
        }
    }

    public void a(String str, String str2, a aVar) {
        Context context = CtAuth.getInstance().getContext();
        int totalTimeout = CtSetting.getTotalTimeout(null);
        String strA = cn.com.chinatelecom.account.api.e.d.a();
        f.a(strA).a(cn.com.chinatelecom.account.api.e.d.a(context)).c("preCodeByJs").b(cn.com.chinatelecom.account.api.e.g.e(context)).f(cn.com.chinatelecom.account.api.e.g.i(context));
        a(context, str, str2, null, totalTimeout, strA, "preCodeByJs", aVar);
    }

    public void b(final String str, final String str2, final a aVar) {
        final Context context = CtAuth.getInstance().getContext();
        final int totalTimeout = CtSetting.getTotalTimeout(null);
        final String strA = cn.com.chinatelecom.account.api.e.d.a();
        f.a(strA).a(cn.com.chinatelecom.account.api.e.d.a(context)).c("preCodeByJs").b("BOTH").f(cn.com.chinatelecom.account.api.e.g.i(context));
        if (Build.VERSION.SDK_INT >= 21) {
            c cVar = new c(context);
            this.g = cVar;
            cVar.a(new cn.com.chinatelecom.account.api.c.b() { // from class: cn.com.chinatelecom.account.api.b.b.1
                @Override // cn.com.chinatelecom.account.api.c.b
                public void a() {
                    b.this.a();
                    b.this.a(80800, d.a(j.o), strA, 2500L, "", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(long j) {
                    b.this.a();
                    b.this.a(80801, d.a(j.p), strA, j, "", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(Network network, long j) {
                    f.a(strA).b(j);
                    long j2 = ((long) totalTimeout) - j;
                    if (j2 > 100) {
                        b.this.a(context, str, str2, network, j2, strA, "preCodeByJs", aVar);
                    } else {
                        b.this.a();
                        b.this.a(80000, d.a(j.f1522a), strA, j, "", aVar);
                    }
                }
            });
        } else {
            c cVar2 = new c(context);
            this.g = cVar2;
            cVar2.a(new cn.com.chinatelecom.account.api.c.b() { // from class: cn.com.chinatelecom.account.api.b.b.2
                @Override // cn.com.chinatelecom.account.api.c.b
                public void a() {
                    b.this.a(80800, d.a(j.o), strA, 2500L, "Switching network timeout (4.x)", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(long j) {
                    b.this.a(80801, d.a(j.p), strA, j, "Switching network failed (4.x)", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(Network network, long j) {
                    f.a(strA).b(j);
                    long j2 = ((long) totalTimeout) - j;
                    if (j2 > 100) {
                        b.this.a(context, str, str2, network, j2, strA, "preCodeByJs", aVar);
                    } else {
                        b.this.a(80000, d.a(j.f1522a), strA, j, "", aVar);
                    }
                }
            }, str2);
        }
    }
}
