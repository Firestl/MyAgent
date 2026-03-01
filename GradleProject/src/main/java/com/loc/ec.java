package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: AMapDnsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3771a = 1;
    public static int b = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ec f3772e;
    public Context j;
    public String k;
    public long c = 0;
    public boolean d = false;
    public ArrayList<String> f = new ArrayList<>();
    public dq g = new dq();
    public dq h = new dq();
    public long i = com.igexin.push.config.c.l;
    public boolean l = false;

    public ec(Context context) {
        this.j = context;
    }

    public static synchronized ec a(Context context) {
        if (f3772e == null) {
            f3772e = new ec(context);
        }
        return f3772e;
    }

    public static /* synthetic */ boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0 || strArr.length != strArr2.length) {
            return false;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (!strArr[i].equals(strArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ String[] a(JSONArray jSONArray, int i) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new String[0];
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String string = jSONArray.getString(i2);
            if (!TextUtils.isEmpty(string)) {
                if (i == b) {
                    string = Operators.ARRAY_START_STR + string + Operators.ARRAY_END_STR;
                }
                strArr[i2] = string;
            }
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public dq b(int i) {
        return i == b ? this.h : this.g;
    }

    public static /* synthetic */ void b(ec ecVar, int i) {
        if (ecVar.b(i).a() == null || ecVar.b(i).a().length <= 0) {
            return;
        }
        String str = ecVar.b(i).a()[0];
        if (str.equals(ecVar.k) || ecVar.f.contains(str)) {
            return;
        }
        ecVar.k = str;
        SharedPreferences.Editor editorA = eo.a(ecVar.j, "cbG9jaXA");
        eo.a(editorA, c(i), str);
        eo.a(editorA);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0017 A[Catch: all -> 0x0090, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:10:0x000f, B:12:0x0017, B:17:0x0027, B:22:0x0033, B:24:0x004d, B:25:0x0082), top: B:31:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004d A[Catch: all -> 0x0090, LOOP:0: B:23:0x004b->B:24:0x004d, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:10:0x000f, B:12:0x0017, B:17:0x0027, B:22:0x0033, B:24:0x004d, B:25:0x0082), top: B:31:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void b(boolean r7, final int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 != 0) goto Lf
            boolean r7 = com.loc.ei.o()     // Catch: java.lang.Throwable -> L90
            if (r7 != 0) goto Lf
            boolean r7 = r6.l     // Catch: java.lang.Throwable -> L90
            if (r7 == 0) goto Lf
            monitor-exit(r6)
            return
        Lf:
            long r0 = r6.c     // Catch: java.lang.Throwable -> L90
            r2 = 0
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L33
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L90
            long r2 = r6.c     // Catch: java.lang.Throwable -> L90
            long r2 = r0 - r2
            long r4 = r6.i     // Catch: java.lang.Throwable -> L90
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 >= 0) goto L27
            monitor-exit(r6)
            return
        L27:
            long r2 = r6.c     // Catch: java.lang.Throwable -> L90
            long r0 = r0 - r2
            r2 = 60000(0xea60, double:2.9644E-319)
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 >= 0) goto L33
            monitor-exit(r6)
            return
        L33:
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L90
            r6.c = r0     // Catch: java.lang.Throwable -> L90
            r7 = 1
            r6.l = r7     // Catch: java.lang.Throwable -> L90
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L90
            java.lang.StackTraceElement[] r7 = r7.getStackTrace()     // Catch: java.lang.Throwable -> L90
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L90
            r0.<init>()     // Catch: java.lang.Throwable -> L90
            int r1 = r7.length     // Catch: java.lang.Throwable -> L90
            r2 = 0
        L4b:
            if (r2 >= r1) goto L82
            r3 = r7[r2]     // Catch: java.lang.Throwable -> L90
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L90
            r4.<init>()     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = r3.getClassName()     // Catch: java.lang.Throwable -> L90
            r4.append(r5)     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = "("
            r4.append(r5)     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = r3.getMethodName()     // Catch: java.lang.Throwable -> L90
            r4.append(r5)     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = ":"
            r4.append(r5)     // Catch: java.lang.Throwable -> L90
            int r3 = r3.getLineNumber()     // Catch: java.lang.Throwable -> L90
            r4.append(r3)     // Catch: java.lang.Throwable -> L90
            java.lang.String r3 = "),"
            r4.append(r3)     // Catch: java.lang.Throwable -> L90
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L90
            r0.append(r3)     // Catch: java.lang.Throwable -> L90
            int r2 = r2 + 1
            goto L4b
        L82:
            java.util.concurrent.ExecutorService r7 = com.loc.ab.d()     // Catch: java.lang.Throwable -> L90
            com.loc.ec$1 r0 = new com.loc.ec$1     // Catch: java.lang.Throwable -> L90
            r0.<init>()     // Catch: java.lang.Throwable -> L90
            r7.submit(r0)     // Catch: java.lang.Throwable -> L90
            monitor-exit(r6)
            return
        L90:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ec.b(boolean, int):void");
    }

    public static String c(int i) {
        return i == b ? "last_ip_6" : "last_ip_4";
    }

    private void d(int i) {
        if (b(i).d()) {
            SharedPreferences.Editor editorA = eo.a(this.j, "cbG9jaXA");
            eo.a(editorA, c(i));
            eo.a(editorA);
            b(i).a(false);
        }
    }

    private String e(int i) {
        String str;
        int i2 = 0;
        b(false, i);
        String[] strArrA = b(i).a();
        if (strArrA == null || strArrA.length <= 0) {
            String strA = eo.a(this.j, "cbG9jaXA", c(i), (String) null);
            if (!TextUtils.isEmpty(strA) && !this.f.contains(strA)) {
                b(i).a(strA);
                b(i).b(strA);
                b(i).a(true);
            }
            return b(i).b();
        }
        int length = strArrA.length;
        while (true) {
            if (i2 >= length) {
                str = null;
                break;
            }
            str = strArrA[i2];
            if (!this.f.contains(str)) {
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        b(i).a(str);
        return str;
    }

    public final String a(ef efVar, int i) {
        try {
            if (ei.p() && efVar != null) {
                String strC = efVar.c();
                String host = new URL(strC).getHost();
                if (!"http://abroad.apilocate.amap.com/mobile/binary".equals(strC) && !"abroad.apilocate.amap.com".equals(host)) {
                    String str = "apilocate.amap.com".equalsIgnoreCase(host) ? "httpdns.apilocate.amap.com" : host;
                    String strE = e(i);
                    if (!TextUtils.isEmpty(strE)) {
                        if (i == b) {
                            efVar.h = strC.replace(host, strE);
                        } else {
                            efVar.g = strC.replace(host, strE);
                        }
                        efVar.b().put("host", str);
                        efVar.c(str);
                        return strE;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public final void a(int i) {
        if (b(i).e()) {
            d(i);
            return;
        }
        this.f.add(b(i).b());
        d(i);
        b(true, i);
    }

    public final void a(boolean z, int i) {
        b(i).b(z);
        if (z) {
            String strC = b(i).c();
            String strB = b(i).b();
            if (TextUtils.isEmpty(strB) || strB.equals(strC)) {
                return;
            }
            SharedPreferences.Editor editorA = eo.a(this.j, "cbG9jaXA");
            eo.a(editorA, c(i), strB);
            eo.a(editorA);
        }
    }
}
