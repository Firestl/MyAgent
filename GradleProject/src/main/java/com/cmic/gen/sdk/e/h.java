package com.cmic.gen.sdk.e;

import android.content.Context;
import android.text.TextUtils;
import com.cmic.gen.sdk.e.k;
import com.cmic.gen.sdk.e.n;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1730a = null;
    public static String b = null;
    public static long c = 0;
    public static int d = -1;

    public static int a(String str) {
        String strB;
        if (TextUtils.isEmpty(b)) {
            strB = k.b("pre_sim_key", "");
            b = strB;
        } else {
            strB = b;
        }
        if (TextUtils.isEmpty(strB)) {
            return 0;
        }
        return strB.equals(str) ? 1 : 2;
    }

    public static long a() {
        long jA;
        long j;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(f1730a)) {
            String strB = k.b("phonescripcache", "");
            jA = k.a("phonescripstarttime", 0L);
            if (TextUtils.isEmpty(strB)) {
                j = 0;
            }
            return Math.max(j / 1000, 0L);
        }
        c.b("PhoneScripUtils", b + Operators.SPACE_STR + c);
        jA = c;
        j = (jA - jCurrentTimeMillis) - 10000;
        return Math.max(j / 1000, 0L);
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f1730a)) {
            return f1730a;
        }
        String strB = k.b("phonescripcache", "");
        if (TextUtils.isEmpty(strB)) {
            c.a("PhoneScripUtils", com.igexin.push.core.b.m);
            return null;
        }
        c = k.a("phonescripstarttime", 0L);
        b = k.b("pre_sim_key", "");
        d = k.a("phonescripversion", -1);
        String strB2 = b.b(context, strB);
        f1730a = strB2;
        return strB2;
    }

    public static void a(final Context context, final String str, long j, final String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || j <= 0) {
            return;
        }
        c.b("PhoneScripUtils", "save phone scrip simKey = " + str2);
        f1730a = str;
        long j2 = j * 1000;
        c = System.currentTimeMillis() + j2;
        c.b("sLifeTime", c + "");
        b = str2;
        d = 1;
        if (!"operator".equals(str3)) {
            n.a(new n.a() { // from class: com.cmic.gen.sdk.e.h.1
                @Override // com.cmic.gen.sdk.e.n.a
                public void a() {
                    c.b("PhoneScripUtils", "start save scrip to sp in sub thread");
                    h.b(context, str, h.c, str2);
                }
            });
        } else if (j2 > 3600000) {
            c = System.currentTimeMillis() + 3600000;
        } else {
            c = System.currentTimeMillis() + j2;
        }
    }

    public static void a(boolean z, boolean z2) {
        k.a aVarA = k.a();
        aVarA.a("phonescripstarttime");
        aVarA.a("phonescripcache");
        aVarA.a("pre_sim_key");
        aVarA.a("phonescripversion");
        if (z2) {
            aVarA.a();
        } else {
            aVarA.b();
        }
        if (z) {
            f1730a = null;
            b = null;
            c = 0L;
            d = -1;
        }
    }

    public static boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        c.b("PhoneScripUtils", j + "");
        c.b("PhoneScripUtils", jCurrentTimeMillis + "");
        return j - jCurrentTimeMillis > 10000;
    }

    public static boolean a(com.cmic.gen.sdk.a aVar) {
        int iA = a(aVar.b("scripKey"));
        aVar.a("imsiState", iA + "");
        c.b("PhoneScripUtils", "simState = " + iA);
        if (iA == 0) {
            return false;
        }
        if (d == -1) {
            d = k.a("phonescripversion", -1);
        }
        if (d != 1) {
            a(true, false);
            b.a();
            c.b("PhoneScripUtils", "phoneScriptVersion change");
            return false;
        }
        if (iA != 2) {
            return c();
        }
        a(true, false);
        return false;
    }

    public static void b(Context context, String str, long j, String str2) {
        String strA = b.a(context, str);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        k.a aVarA = k.a();
        aVarA.a("phonescripcache", strA);
        aVarA.a("phonescripstarttime", j);
        aVarA.a("phonescripversion", 1);
        aVarA.a("pre_sim_key", str2);
        aVarA.b();
    }

    public static boolean c() {
        if (TextUtils.isEmpty(f1730a)) {
            return !TextUtils.isEmpty(k.b("phonescripcache", "")) && a(k.a("phonescripstarttime", 0L));
        }
        c.b("PhoneScripUtils", b + Operators.SPACE_STR + c);
        return a(c);
    }
}
