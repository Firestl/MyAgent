package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.loc.l;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ei {
    public static volatile boolean h = false;
    public static boolean i = true;
    public static int j = 1000;
    public static int k = 200;
    public static boolean l = false;
    public static int m = 20;
    public static int n = 0;
    public static volatile int o = 0;
    public static boolean p = true;
    public static boolean q = true;
    public static int r = -1;
    public static long s;
    public static ArrayList<String> t = new ArrayList<>();
    public static ArrayList<String> u = new ArrayList<>();
    public static volatile boolean v = false;
    public static boolean w = true;
    public static long x = 300000;
    public static boolean y = false;
    public static double z = 0.618d;
    public static boolean A = true;
    public static int B = 80;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f3780a = 3600000;
    public static boolean C = false;
    public static boolean D = true;
    public static boolean E = false;
    public static volatile long b = 0;
    public static boolean c = true;
    public static boolean F = true;
    public static long G = -1;
    public static boolean H = true;
    public static int I = 1;
    public static boolean J = true;
    public static int K = 5;
    public static boolean L = false;
    public static String M = "CMjAzLjEwNy4xLjEvMTU0MDgxL2Q";
    public static long N = 0;
    public static boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3781e = false;
    public static int f = 20480;
    public static int g = 10800000;

    public static void a(final Context context) {
        if (h) {
            return;
        }
        h = true;
        l.a(context, ej.c(), ej.d(), new l.a() { // from class: com.loc.ei.1
            @Override // com.loc.l.a
            public final void a(l.b bVar) {
                ei.a(context, bVar);
            }
        });
    }

    public static boolean a() {
        return i;
    }

    public static boolean a(long j2) {
        if (!w) {
            return false;
        }
        long jA = ep.a() - j2;
        long j3 = x;
        return j3 < 0 || jA < j3;
    }

    public static boolean a(Context context, l.b bVar) {
        SharedPreferences.Editor editorA;
        String str;
        String str2;
        int i2;
        JSONArray jSONArrayOptJSONArray;
        try {
            editorA = eo.a(context, "pref");
            try {
                l.b.a aVar = bVar.g;
                if (aVar != null) {
                    boolean z2 = aVar.f3817a;
                    i = z2;
                    eo.a(editorA, "exception", z2);
                    JSONObject jSONObject = aVar.c;
                    if (jSONObject != null) {
                        j = jSONObject.optInt("fn", j);
                        int iOptInt = jSONObject.optInt("mpn", k);
                        k = iOptInt;
                        if (iOptInt > 500) {
                            k = 500;
                        }
                        if (k < 30) {
                            k = 30;
                        }
                        l = l.a(jSONObject.optString("igu"), l);
                        m = jSONObject.optInt("ms", m);
                        o = jSONObject.optInt("rot", 0);
                        n = jSONObject.optInt("pms", 0);
                    }
                    str = "c";
                    try {
                        ba.a(j, l, m, n);
                        bc.a(l, n);
                        eo.a(editorA, "fn", j);
                        eo.a(editorA, "mpn", k);
                        eo.a(editorA, "igu", l);
                        eo.a(editorA, "ms", m);
                        eo.a(editorA, "rot", o);
                        eo.a(editorA, "pms", n);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            ej.a(th, "AuthUtil", "loadConfigDataUploadException");
                        } catch (Throwable unused) {
                            if (editorA == null) {
                                return false;
                            }
                            try {
                                eo.a(editorA);
                                return false;
                            } catch (Throwable unused2) {
                                return false;
                            }
                        }
                    }
                } else {
                    str = "c";
                }
            } catch (Throwable th2) {
                th = th2;
                str = "c";
            }
            c(context);
            JSONObject jSONObject2 = bVar.f;
            if (jSONObject2 == null) {
                if (editorA != null) {
                    try {
                        eo.a(editorA);
                    } catch (Throwable unused3) {
                    }
                }
                return true;
            }
            try {
                JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("13S");
                if (jSONObjectOptJSONObject != null) {
                    try {
                        long jOptInt = jSONObjectOptJSONObject.optInt("at", 123) * 60 * 1000;
                        f3780a = jOptInt;
                        eo.a(editorA, "13S_at", jOptInt);
                    } catch (Throwable th3) {
                        ej.a(th3, "AuthUtil", "requestSdkAuthInterval");
                    }
                    if (jSONObjectOptJSONObject != null) {
                        try {
                            boolean zA = l.a(jSONObjectOptJSONObject.optString("re"), c);
                            c = zA;
                            eo.a(editorA, "fr", zA);
                        } catch (Throwable th4) {
                            ej.a(th4, "AuthUtil", "checkReLocationAble");
                        }
                    }
                    try {
                        boolean zA2 = l.a(jSONObjectOptJSONObject.optString("nla"), D);
                        D = zA2;
                        eo.a(editorA, "13S_nla", zA2);
                    } catch (Throwable unused4) {
                    }
                    try {
                        boolean zA3 = l.a(jSONObjectOptJSONObject.optString("asw"), F);
                        F = zA3;
                        eo.a(editorA, "asw", zA3);
                    } catch (Throwable unused5) {
                    }
                    try {
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("mlpl");
                        if (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.length() <= 0 || context == null) {
                            E = false;
                            eo.a(editorA, "13S_mlpl");
                        } else {
                            eo.a(editorA, "13S_mlpl", u.b(jSONArrayOptJSONArray2.toString()));
                            E = a(context, jSONArrayOptJSONArray2);
                        }
                    } catch (Throwable unused6) {
                    }
                }
            } catch (Throwable th5) {
                ej.a(th5, "AuthUtil", "loadConfigAbleStatus");
            }
            try {
                JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("11G");
                if (jSONObjectOptJSONObject2 != null) {
                    boolean zA4 = l.a(jSONObjectOptJSONObject2.optString("able"), w);
                    w = zA4;
                    if (zA4) {
                        str2 = str;
                        try {
                            x = jSONObjectOptJSONObject2.optInt(str2, 300) * 1000;
                        } catch (Throwable th6) {
                            th = th6;
                            ej.a(th, "AuthUtil", "loadConfigDataCacheAble");
                        }
                    } else {
                        str2 = str;
                    }
                    y = l.a(jSONObjectOptJSONObject2.optString("fa"), y);
                    z = Math.min(1.0d, Math.max(0.2d, jSONObjectOptJSONObject2.optDouble("ms")));
                    eo.a(editorA, "ca", w);
                    eo.a(editorA, "ct", x);
                    eo.a(editorA, "11G_fa", y);
                    eo.a(editorA, "11G_ms", String.valueOf(z));
                } else {
                    str2 = str;
                }
            } catch (Throwable th7) {
                th = th7;
                str2 = str;
            }
            try {
                JSONObject jSONObjectOptJSONObject3 = jSONObject2.optJSONObject("13J");
                if (jSONObjectOptJSONObject3 != null) {
                    boolean zA5 = l.a(jSONObjectOptJSONObject3.optString("able"), A);
                    A = zA5;
                    if (zA5) {
                        B = jSONObjectOptJSONObject3.optInt(str2, B);
                    }
                    eo.a(editorA, "13J_able", A);
                    eo.a(editorA, "13J_c", B);
                }
            } catch (Throwable th8) {
                ej.a(th8, "AuthUtil", "loadConfigDataGpsGeoAble");
            }
            try {
                JSONObject jSONObjectOptJSONObject4 = jSONObject2.optJSONObject("15O");
                if (jSONObjectOptJSONObject4 != null) {
                    if (l.a(jSONObjectOptJSONObject4.optString("able"), false) && ((jSONArrayOptJSONArray = jSONObjectOptJSONObject4.optJSONArray("fl")) == null || jSONArrayOptJSONArray.length() <= 0 || jSONArrayOptJSONArray.toString().contains(Build.MANUFACTURER))) {
                        G = jSONObjectOptJSONObject4.optInt("iv", 30) * 1000;
                    } else {
                        G = -1L;
                    }
                    eo.a(editorA, "awsi", G);
                }
            } catch (Throwable unused7) {
            }
            try {
                JSONObject jSONObjectOptJSONObject5 = jSONObject2.optJSONObject("15U");
                if (jSONObjectOptJSONObject5 != null) {
                    boolean zA6 = l.a(jSONObjectOptJSONObject5.optString("able"), H);
                    int iOptInt2 = jSONObjectOptJSONObject5.optInt("yn", I);
                    N = jSONObjectOptJSONObject5.optLong("sysTime", N);
                    eo.a(editorA, "15ua", zA6);
                    eo.a(editorA, "15un", iOptInt2);
                    eo.a(editorA, "15ust", N);
                }
            } catch (Throwable unused8) {
            }
            if (jSONObject2 != null) {
                try {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("17Y");
                    if (jSONObject3 != null) {
                        boolean zA7 = l.a(jSONObject3.optString("able"), d);
                        d = zA7;
                        eo.a(editorA, "17ya", zA7);
                        boolean zA8 = l.a(jSONObject3.optString("mup"), f3781e);
                        f3781e = zA8;
                        eo.a(editorA, "17ym", zA8);
                        int iOptInt3 = jSONObject3.optInt("max", 20);
                        if (iOptInt3 > 0) {
                            eo.a(editorA, "17yx", iOptInt3);
                            f = iOptInt3 * 1024;
                        }
                        int iOptInt4 = jSONObject3.optInt("inv", 3);
                        if (iOptInt4 > 0) {
                            eo.a(editorA, "17yi", iOptInt4);
                            g = iOptInt4 * 60 * 60 * 1000;
                        }
                    }
                } catch (Throwable unused9) {
                }
            }
            if (jSONObject2 != null) {
                try {
                    JSONObject jSONObjectOptJSONObject6 = jSONObject2.optJSONObject("17J");
                    if (jSONObjectOptJSONObject6 != null) {
                        boolean zA9 = l.a(jSONObjectOptJSONObject6.optString("able"), false);
                        J = zA9;
                        eo.a(editorA, "ok9", zA9);
                        if (zA9) {
                            String strOptString = jSONObjectOptJSONObject6.optString("auth");
                            String strOptString2 = jSONObjectOptJSONObject6.optString("ht");
                            M = strOptString2;
                            eo.a(editorA, "ok11", strOptString2);
                            l.a(strOptString, false);
                            L = l.a(jSONObjectOptJSONObject6.optString("nr"), false);
                            String strOptString3 = jSONObjectOptJSONObject6.optString("tm");
                            if (!TextUtils.isEmpty(strOptString3) && (i2 = Integer.parseInt(strOptString3)) > 0 && i2 < 20) {
                                K = i2;
                                eo.a(editorA, "ok10", i2);
                            }
                        }
                    }
                } catch (Throwable unused10) {
                }
            }
            if (editorA != null) {
                try {
                    eo.a(editorA);
                } catch (Throwable unused11) {
                }
            }
            return true;
        } catch (Throwable unused12) {
            editorA = null;
        }
    }

    public static boolean a(Context context, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0 && context != null) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        if (ep.b(context, jSONArray.getString(i2))) {
                            return true;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static int b() {
        return k;
    }

    public static void b(Context context) {
        if (v) {
            return;
        }
        v = true;
        try {
            i = eo.a(context, "pref", "exception", i);
            c(context);
        } catch (Throwable th) {
            ej.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            j = eo.a(context, "pref", "fn", j);
            k = eo.a(context, "pref", "mpn", k);
            l = eo.a(context, "pref", "igu", l);
            m = eo.a(context, "pref", "ms", m);
            o = eo.a(context, "pref", "rot", 0);
            int iA = eo.a(context, "pref", "pms", 0);
            n = iA;
            ba.a(j, l, m, iA);
            bc.a(l, n);
        } catch (Throwable unused) {
        }
        try {
            w = eo.a(context, "pref", "ca", w);
            x = eo.a(context, "pref", "ct", x);
            y = eo.a(context, "pref", "11G_fa", y);
            double dDoubleValue = Double.valueOf(eo.a(context, "pref", "11G_ms", String.valueOf(z))).doubleValue();
            z = dDoubleValue;
            z = Math.max(0.2d, dDoubleValue);
        } catch (Throwable unused2) {
        }
        try {
            c = eo.a(context, "pref", "fr", c);
        } catch (Throwable unused3) {
        }
        try {
            F = eo.a(context, "pref", "asw", F);
        } catch (Throwable unused4) {
        }
        try {
            G = eo.a(context, "pref", "awsi", G);
        } catch (Throwable unused5) {
        }
        try {
            H = eo.a(context, "pref", "15ua", H);
            I = eo.a(context, "pref", "15un", I);
            N = eo.a(context, "pref", "15ust", N);
        } catch (Throwable unused6) {
        }
        try {
            J = eo.a(context, "pref", "ok9", J);
            K = eo.a(context, "pref", "ok10", K);
            M = eo.a(context, "pref", "ok11", M);
        } catch (Throwable unused7) {
        }
        try {
            d = eo.a(context, "pref", "17ya", false);
            f3781e = eo.a(context, "pref", "17ym", false);
            g = eo.a(context, "pref", "17yi", 2) * 60 * 60 * 1000;
            f = eo.a(context, "pref", "17yx", 100) * 1024;
        } catch (Throwable unused8) {
        }
        try {
            b = ep.b();
            f3780a = eo.a(context, "pref", "13S_at", f3780a);
            D = eo.a(context, "pref", "13S_nla", D);
            A = eo.a(context, "pref", "13J_able", A);
            B = eo.a(context, "pref", "13J_c", B);
        } catch (Throwable unused9) {
        }
        l.b(context);
        try {
            String strA = eo.a(context, "pref", "13S_mlpl", (String) null);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            E = a(context, new JSONArray(u.c(strA)));
        } catch (Throwable unused10) {
        }
    }

    public static int c() {
        if (o < 0) {
            o = 0;
        }
        return o;
    }

    public static void c(Context context) {
        try {
            t tVarC = ej.c();
            tVarC.a(i);
            ab.a(context, tVarC);
        } catch (Throwable unused) {
        }
    }

    public static long d() {
        return x;
    }

    public static boolean e() {
        return w;
    }

    public static boolean f() {
        return y;
    }

    public static double g() {
        return z;
    }

    public static boolean h() {
        return A;
    }

    public static int i() {
        return B;
    }

    public static boolean j() {
        return D;
    }

    public static boolean k() {
        return E;
    }

    public static boolean l() {
        return c;
    }

    public static boolean m() {
        return F;
    }

    public static long n() {
        return G;
    }

    public static boolean o() {
        return L;
    }

    public static boolean p() {
        return J;
    }

    public static int q() {
        return K;
    }

    public static String r() {
        return u.c(M);
    }

    public static boolean s() {
        return H && I > 0;
    }

    public static int t() {
        return I;
    }

    public static long u() {
        return N;
    }
}
