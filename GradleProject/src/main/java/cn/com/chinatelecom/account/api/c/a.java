package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.CtSetting;
import cn.com.chinatelecom.account.api.ResultListener;
import cn.com.chinatelecom.account.api.d.g;
import cn.com.chinatelecom.account.api.e.f;
import cn.com.chinatelecom.account.api.e.g;
import cn.com.chinatelecom.account.api.e.h;
import cn.com.chinatelecom.account.api.e.j;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1482a = "a";
    public boolean b = false;
    public Context c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1483e;
    public c f;

    public a(Context context, String str, String str2) {
        this.c = context;
        this.d = str;
        this.f1483e = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(Context context, String str, String str2, String str3, CtSetting ctSetting, Network network, String str4, String str5, int i) {
        String strB;
        String strB2;
        boolean z;
        try {
            long jA = cn.com.chinatelecom.account.api.e.a.a(context);
            if (i == cn.com.chinatelecom.account.api.a.d) {
                strB = h.a();
                strB2 = h.a(context, str, str2, str3, jA, "");
            } else {
                strB = h.b();
                strB2 = h.b(context, str, str2, str3, jA, "");
            }
            if (g.a() != null) {
                strB = strB.replace(cn.com.chinatelecom.account.api.a.d.a(cn.com.chinatelecom.account.api.e.b.f), g.a());
            }
            JSONObject jSONObject = new JSONObject(strB2);
            String strOptString = jSONObject.optString("p");
            String strOptString2 = jSONObject.optString("k");
            g.a aVar = new g.a();
            aVar.a(str5);
            aVar.a(false, cn.com.chinatelecom.account.api.d.c.a(), cn.com.chinatelecom.account.api.a.d.a(cn.com.chinatelecom.account.api.e.b.f));
            aVar.b(str4);
            aVar.a(network);
            aVar.a(CtSetting.getConnTimeout(ctSetting));
            aVar.b(CtSetting.getReadTimeout(ctSetting));
            cn.com.chinatelecom.account.api.d.g gVarA = aVar.a();
            cn.com.chinatelecom.account.api.d.b bVar = new cn.com.chinatelecom.account.api.d.b(context);
            cn.com.chinatelecom.account.api.d.h hVarA = bVar.a(strB, strOptString, 1, gVarA);
            if (hVarA.d) {
                synchronized (this) {
                    z = this.b;
                }
                if (!z) {
                    cn.com.chinatelecom.account.api.d.h hVarA2 = bVar.a(strB, strOptString, 1, aVar.a(true).a(false, "", "").a());
                    f.a(str4).b(1);
                    hVarA = hVarA2;
                }
            }
            JSONObject jSONObjectA = cn.com.chinatelecom.account.api.e.a.a(context, hVarA, strOptString2, network, true, str4);
            f.b(str4, jSONObjectA, strOptString);
            return jSONObjectA;
        } catch (Throwable th) {
            JSONObject jSONObjectG = j.g();
            f.a(str4).g("gpm ：" + th.getMessage()).a(80102).e(cn.com.chinatelecom.account.api.a.d.a(j.k));
            CtAuth.warn(f1482a, "GPM Throwable", th);
            return jSONObjectG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        c cVar = this.f;
        if (cVar != null) {
            cVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2, long j, String str3, ResultListener resultListener) {
        f.a(str2).a(i).e(str).b(j).g(str3);
        f.c(str2);
        String strA = j.a(i, str, str2);
        if (resultListener != null) {
            resultListener.onResult(strA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final CtSetting ctSetting, final Network network, final ResultListener resultListener, long j, final String str2, final String str3, final int i) {
        new d().a(new e(j) { // from class: cn.com.chinatelecom.account.api.c.a.3
            @Override // cn.com.chinatelecom.account.api.c.e
            public void a() {
                a aVar = a.this;
                JSONObject jSONObjectA = aVar.a(aVar.c, a.this.d, a.this.f1483e, str, ctSetting, network, str2, str3, i);
                synchronized (this) {
                    if (!c()) {
                        a(true);
                        d();
                        CtAuth.postResultOnMainThread(str2, jSONObjectA, resultListener);
                    }
                }
                if (network != null) {
                    a.this.a();
                }
            }

            @Override // cn.com.chinatelecom.account.api.c.e
            public void b() {
                super.b();
                synchronized (a.this) {
                    a.this.b = true;
                }
                synchronized (this) {
                    if (!c()) {
                        a(true);
                        a.this.a(80000, cn.com.chinatelecom.account.api.a.d.a(j.f1522a), str2, 0L, "", resultListener);
                    }
                }
                if (network != null) {
                    a.this.a();
                }
            }
        });
    }

    public void a(String str, CtSetting ctSetting, int i, ResultListener resultListener) {
        int totalTimeout = CtSetting.getTotalTimeout(ctSetting);
        String strA = cn.com.chinatelecom.account.api.e.d.a();
        String strA2 = cn.com.chinatelecom.account.api.e.d.a(this.c);
        String strA3 = cn.com.chinatelecom.account.api.e.a.a(i);
        f.a(strA).a(strA2).c(strA3).b(cn.com.chinatelecom.account.api.e.g.e(this.c)).f(cn.com.chinatelecom.account.api.e.g.i(this.c));
        a(str, ctSetting, null, resultListener, totalTimeout, strA, strA3, i);
    }

    public void b(final String str, final CtSetting ctSetting, final int i, final ResultListener resultListener) {
        final int totalTimeout = CtSetting.getTotalTimeout(ctSetting);
        final String strA = cn.com.chinatelecom.account.api.e.d.a();
        String strA2 = cn.com.chinatelecom.account.api.e.d.a(this.c);
        final String strA3 = cn.com.chinatelecom.account.api.e.a.a(i);
        f.a(strA).a(strA2).c(strA3).b("BOTH").f(cn.com.chinatelecom.account.api.e.g.i(this.c));
        if (Build.VERSION.SDK_INT >= 21) {
            c cVar = new c(this.c);
            this.f = cVar;
            cVar.a(new b() { // from class: cn.com.chinatelecom.account.api.c.a.1
                @Override // cn.com.chinatelecom.account.api.c.b
                public void a() {
                    a.this.a();
                    a.this.a(80800, cn.com.chinatelecom.account.api.a.d.a(j.o), strA, 2500L, "", resultListener);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(long j) {
                    a.this.a();
                    a.this.a(80801, cn.com.chinatelecom.account.api.a.d.a(j.p), strA, j, "", resultListener);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(Network network, long j) {
                    long j2 = ((long) totalTimeout) - j;
                    if (j2 > 100) {
                        a.this.a(str, ctSetting, network, resultListener, j2, strA, strA3, i);
                    } else {
                        a.this.a();
                        CtAuth.postResultOnMainThread(strA, j.c(), resultListener);
                    }
                    f.a(strA).b(j);
                }
            });
            return;
        }
        this.f = new c(this.c);
        String strA4 = h.a();
        if (cn.com.chinatelecom.account.api.e.g.a() != null) {
            strA4 = strA4.replace(cn.com.chinatelecom.account.api.a.d.a(cn.com.chinatelecom.account.api.e.b.f), cn.com.chinatelecom.account.api.e.g.a());
        }
        this.f.a(new b() { // from class: cn.com.chinatelecom.account.api.c.a.2
            @Override // cn.com.chinatelecom.account.api.c.b
            public void a() {
                a.this.a(80800, cn.com.chinatelecom.account.api.a.d.a(j.o), strA, 2500L, "Switching network timeout (4.x)", resultListener);
            }

            @Override // cn.com.chinatelecom.account.api.c.b
            public void a(long j) {
                a.this.a(80801, cn.com.chinatelecom.account.api.a.d.a(j.p), strA, j, "Switching network failed (4.x)", resultListener);
            }

            @Override // cn.com.chinatelecom.account.api.c.b
            public void a(Network network, long j) {
                long j2 = ((long) totalTimeout) - j;
                if (j2 > 100) {
                    a.this.a(str, ctSetting, null, resultListener, j2, strA, strA3, i);
                } else {
                    CtAuth.postResultOnMainThread(strA, j.c(), resultListener);
                }
                f.a(strA).b(j);
            }
        }, strA4);
    }
}
