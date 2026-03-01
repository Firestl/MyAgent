package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.bm;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: compiled from: HeaderHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f5449a;
    public String b;
    public String c;

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.internal.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeaderHelper.java */
    public static class C0128a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5450a = new a();
    }

    public static a a(Context context) {
        if (f5449a == null && context != null) {
            f5449a = context.getApplicationContext();
        }
        return C0128a.f5450a;
    }

    private void f(String str) {
        try {
            this.b = str.replaceAll("&=", Operators.SPACE_STR).replaceAll(Operators.AND, Operators.SPACE_STR).replaceAll(Operators.EQUAL2, "/") + "/Android" + Operators.SPACE_STR + HelperUtils.getUmengMD5(UMUtils.getAppkey(f5449a));
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f5449a, th);
        }
    }

    private void g(String str) {
        try {
            String str2 = str.split(Operators.AND)[0];
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String[] strArrSplit = str2.split("&=");
            StringBuilder sb = new StringBuilder();
            sb.append(bm.aU);
            for (String str3 : strArrSplit) {
                if (!TextUtils.isEmpty(str3)) {
                    String strSubstring = str3.substring(0, 2);
                    if (strSubstring.endsWith(ContainerUtils.KEY_VALUE_DELIMITER)) {
                        strSubstring = strSubstring.replace(ContainerUtils.KEY_VALUE_DELIMITER, "");
                    }
                    sb.append(strSubstring);
                }
            }
            this.c = sb.toString();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f5449a, th);
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("t");
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(bm.aH);
    }

    public boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("h");
    }

    public void e(String str) {
        String strSubstring = str.substring(0, str.indexOf(95));
        g(strSubstring);
        f(strSubstring);
    }

    public a() {
        this.b = null;
        this.c = null;
    }

    public String b() {
        return this.b;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("a");
    }

    public String a() {
        return this.c;
    }
}
