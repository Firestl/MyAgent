package com.g.gysdk.a;

import android.text.TextUtils;
import com.g.gysdk.a.k;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, String> f2007a;
    public final k b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f2009a = new j();
    }

    public j() {
        this.f2007a = new HashMap();
        this.b = new k.a().b("gysdk").a(ae.a("gy.cs") + "/api.php?format=json&t=1").e("GY-3.1.0.0").c(d.f2000e).d(d.h).a();
        Map<String, String> mapA = i.a(d.b, "gysdk");
        if (mapA != null) {
            this.f2007a.putAll(mapA);
        }
    }

    public static void a() {
        try {
            j jVar = a.f2009a;
            jVar.b.a(h());
            jVar.b.a(new l() { // from class: com.g.gysdk.a.j.1
                @Override // com.g.gysdk.a.l
                public void a(Exception exc) {
                    ak.c("dyc update net failed: ", exc);
                    if (exc instanceof IOException) {
                        String strB = j.this.b.b();
                        String strSubstring = strB.substring(0, strB.indexOf("/api.php?format=json&t=1"));
                        if (ae.a("gy.cs", strSubstring)) {
                            String strA = ae.a("gy.cs");
                            ak.c("dyc update failed witch " + strSubstring + ", retry with " + strA);
                            k kVar = j.this.b;
                            StringBuilder sb = new StringBuilder();
                            sb.append(strA);
                            sb.append("/api.php?format=json&t=1");
                            kVar.a(sb.toString());
                            j.a();
                        }
                    }
                }

                @Override // com.g.gysdk.a.l
                public void a(String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("dyc update error, cause: ");
                    if (str == null) {
                        str = com.igexin.push.core.b.m;
                    }
                    sb.append(str);
                    ak.c(sb.toString());
                }

                @Override // com.g.gysdk.a.l
                public void a(Map<String, String> map, Map<String, String> map2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("dyc update from:");
                    String string = com.igexin.push.core.b.m;
                    sb.append(map == null ? com.igexin.push.core.b.m : map.toString());
                    sb.append(" to:");
                    if (map2 != null) {
                        string = map2.toString();
                    }
                    sb.append(string);
                    ak.a(sb.toString());
                    String strB = j.this.b.b();
                    ae.b("gy.cs", strB.substring(0, strB.indexOf("/api.php?format=json&t=1")));
                    if (map2 != null) {
                        j.this.f2007a.clear();
                        j.this.f2007a.putAll(map2);
                    }
                    j.this.b.a(j.h());
                }
            });
            Map<String, String> mapA = i.a(d.b, jVar.b);
            if (mapA != null) {
                jVar.f2007a.clear();
                jVar.f2007a.putAll(mapA);
            }
        } catch (Throwable th) {
            ak.e("dyc update error", th);
        }
    }

    public static long b() {
        try {
            String str = a.f2009a.f2007a.get("sdk.gy.offline.data.max");
            if (TextUtils.isEmpty(str)) {
                return 2000L;
            }
            return Long.parseLong(str);
        } catch (Throwable th) {
            ak.e("sdk.gy.offline.data.max error", th);
            return 2000L;
        }
    }

    public static long c() {
        try {
            String str = a.f2009a.f2007a.get("sdk.gy.register.interval");
            if (TextUtils.isEmpty(str)) {
                return 86400L;
            }
            return Long.parseLong(str);
        } catch (Throwable th) {
            ak.e("sdk.gy.register.interval error", th);
            return 86400L;
        }
    }

    public static boolean d() {
        try {
            String str = a.f2009a.f2007a.get("sdk.gy.prelogin.enable");
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            return Boolean.parseBoolean(str);
        } catch (Throwable th) {
            ak.e("sdk.gy.prelogin.enable error", th);
            return true;
        }
    }

    public static long e() {
        try {
            String str = a.f2009a.f2007a.get("sdk.gy.prelogin.interval");
            if (TextUtils.isEmpty(str)) {
                return 86400L;
            }
            return Long.parseLong(str);
        } catch (Throwable th) {
            ak.e("sdk.gy.prelogin.interval error", th);
            return 86400L;
        }
    }

    public static int f() {
        try {
            String str = a.f2009a.f2007a.get("sdk.gy.prelogin.parallel.count");
            if (!TextUtils.isEmpty(str)) {
                int i = Integer.parseInt(str);
                if (i < 1) {
                    return 1;
                }
                if (i > 5) {
                    return 5;
                }
                return i;
            }
        } catch (Throwable th) {
            ak.e("sdk.gy.prelogin.parallel.count error", th);
        }
        return 1;
    }

    public static long h() {
        try {
            String str = a.f2009a.f2007a.get("sdk.gy.config.interval");
            if (TextUtils.isEmpty(str)) {
                return 43200000L;
            }
            return Long.parseLong(str);
        } catch (Throwable th) {
            ak.e("sdk.gy.config.interval error", th);
            return 43200000L;
        }
    }
}
