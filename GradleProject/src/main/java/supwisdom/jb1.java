package supwisdom;

import com.google.zxing.client.android.LocaleManager;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class jb1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f8040a = -1;
    public static boolean b = false;
    public static a c = new a();

    /* JADX INFO: compiled from: Proguard */
    public static class a {
        public String A;
        public String A0;
        public String A1;
        public String A2;
        public String B;
        public String B0;
        public String B1;
        public String B2;
        public String C;
        public String C0;
        public Map<String, String> C1;
        public String C2;
        public String D;
        public String D0;
        public String D1;
        public String D2;
        public String E;
        public String E0;
        public String E1;
        public String E2;
        public String F;
        public String F0;
        public String F1;
        public String F2;
        public String G;
        public String G0;
        public String G1;
        public String G2;
        public String H;
        public String H0;
        public String H1;
        public String H2;
        public String I;
        public String I0;
        public String I1;
        public String I2;
        public String J;
        public String J0;
        public String J1;
        public String J2;
        public String K;
        public String K0;
        public String K1;
        public String K2;
        public String L;
        public String L0;
        public String L1;
        public String L2;
        public String M;
        public String M0;
        public String M1;
        public String M2;
        public String N;
        public String N0;
        public String N1;
        public String N2;
        public String O;
        public String O0;
        public String O1;
        public String P;
        public String P0;
        public String P1;
        public String Q;
        public String Q0;
        public String Q1;
        public String R;
        public String R0;
        public String R1;
        public String S;
        public String S0;
        public String S1;
        public String T;
        public String T0;
        public String T1;
        public String U;
        public String U0;
        public String U1;
        public String V;
        public String V0;
        public String V1;
        public String W;
        public String W0;
        public String W1;
        public String X;
        public String X0;
        public String X1;
        public String Y;
        public String Y0;
        public String Y1;
        public String Z;
        public String Z0;
        public String Z1;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f8041a;
        public String a0;
        public String a1;
        public String a2;
        public String b;
        public String b0;
        public String b1;
        public String b2;
        public String c;
        public String c0;
        public String c1;
        public String c2;
        public String d;
        public String d0;
        public String d1;
        public String d2;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8042e;
        public String e0;
        public String e1;
        public String e2;
        public String f;
        public String f0;
        public String f1;
        public String f2;
        public String g;
        public String g0;
        public String g1;
        public String g2;
        public String h;
        public String h0;
        public String h1;
        public String h2;
        public String i;
        public String i0;
        public String i1;
        public String i2;
        public String j;
        public String j0;
        public String j1;
        public String j2;
        public String k;
        public String k0;
        public String k1;
        public String k2;
        public String l;
        public String l0;
        public String l1;
        public String l2;
        public String m;
        public String m0;
        public String m1;
        public String m2;
        public String n;
        public String n0;
        public String n1;
        public String n2;
        public String o;
        public String o0;
        public String o1;
        public String o2;
        public String p;
        public String p0;
        public String p1;
        public String p2;
        public String q;
        public String q0;
        public String q1;
        public String q2;
        public String r;
        public String r0;
        public String r1;
        public String r2;
        public String s;
        public String s0;
        public String s1;
        public String s2;
        public String t;
        public String t0;
        public String t1;
        public String t2;
        public String u;
        public String u0;
        public String u1;
        public String[] u2;
        public String v;
        public String v0;
        public String v1;
        public String[] v2;
        public String w;
        public String w0;
        public String w1;
        public String w2;
        public String x;
        public String x0;
        public String x1;
        public String x2;
        public String y;
        public String y0;
        public String y1;
        public String y2;
        public String z;
        public String z0;
        public String z1;
        public String z2;
    }

    static {
        c();
    }

    public static void a() {
        if (b) {
            return;
        }
        b = true;
        String language = Locale.getDefault().getLanguage();
        if (language.equalsIgnoreCase("zh") && f8040a != 1) {
            c();
        } else {
            if (!language.equalsIgnoreCase(LocaleManager.DEFAULT_LANGUAGE) || f8040a == 0) {
                return;
            }
            b();
        }
    }

    public static void b() {
        f8040a = 0;
        gb1.a();
    }

    public static void c() {
        f8040a = 1;
        eb1.a();
    }
}
