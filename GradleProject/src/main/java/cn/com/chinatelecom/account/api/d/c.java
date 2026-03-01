package cn.com.chinatelecom.account.api.d;

import android.content.Context;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.e.j;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1502a = "c";
    public static String b = null;
    public static long c = 0;
    public static long d = 1800000;

    public static synchronized String a() {
        if (System.currentTimeMillis() >= c || !cn.com.chinatelecom.account.api.e.d.a(b)) {
            return null;
        }
        return b;
    }

    public static void a(final Context context) {
        if (b == null && cn.com.chinatelecom.account.api.e.g.a() == null) {
            new cn.com.chinatelecom.account.api.c.d().a(new cn.com.chinatelecom.account.api.c.e() { // from class: cn.com.chinatelecom.account.api.d.c.1
                @Override // cn.com.chinatelecom.account.api.c.e
                public void a() {
                    cn.com.chinatelecom.account.api.e.e eVarA;
                    String strA;
                    try {
                        String strA2 = cn.com.chinatelecom.account.api.e.d.a();
                        cn.com.chinatelecom.account.api.e.f.a(strA2).a(cn.com.chinatelecom.account.api.e.d.a(context)).c("dns").b(cn.com.chinatelecom.account.api.e.g.f(context));
                        String strA3 = cn.com.chinatelecom.account.api.a.d.a(cn.com.chinatelecom.account.api.e.b.f);
                        String strB = c.b(strA3, strA2, 0);
                        if (TextUtils.isEmpty(strB)) {
                            strB = c.b(strA3, strA2, 1);
                        }
                        synchronized (c.class) {
                            if (TextUtils.isEmpty(strB)) {
                                eVarA = cn.com.chinatelecom.account.api.e.f.a(strA2).a(80011);
                                strA = cn.com.chinatelecom.account.api.a.d.a(j.q);
                            } else {
                                String unused = c.b = strB;
                                long unused2 = c.c = System.currentTimeMillis() + c.d;
                                eVarA = cn.com.chinatelecom.account.api.e.f.a(strA2).a(0);
                                strA = "success";
                            }
                            eVarA.e(strA);
                        }
                        cn.com.chinatelecom.account.api.e.f.b(strA2);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public static String b(String str, String str2, int i) {
        StringBuilder sb;
        String str3;
        try {
            cn.com.chinatelecom.account.api.e.f.a(str2).b(i);
            return InetAddress.getByName(str).getHostAddress();
        } catch (Exception e2) {
            if (i == 0) {
                sb = new StringBuilder();
                str3 = "first exception: ";
            } else {
                sb = new StringBuilder();
                str3 = "retry exception: ";
            }
            sb.append(str3);
            sb.append(e2.getMessage());
            cn.com.chinatelecom.account.api.e.f.a(str2).g(sb.toString());
            return null;
        }
    }
}
