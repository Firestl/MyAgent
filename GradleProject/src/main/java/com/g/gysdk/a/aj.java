package com.g.gysdk.a;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;

/* JADX INFO: loaded from: classes.dex */
public class aj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1958a = "GySDK-Debug";
    public static boolean b = false;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public StringBuilder f1959a;

        public a(String str) {
            StringBuilder sb = new StringBuilder();
            this.f1959a = sb;
            sb.append(str);
        }

        public a a(String str, int i) {
            this.f1959a.append(Operators.SPACE_STR);
            this.f1959a.append(str);
            this.f1959a.append(Constants.COLON_SEPARATOR);
            this.f1959a.append(i);
            return this;
        }

        public a a(String str, Object obj) {
            this.f1959a.append(Operators.SPACE_STR);
            this.f1959a.append(str);
            this.f1959a.append(Constants.COLON_SEPARATOR);
            this.f1959a.append(obj != null ? obj.toString() : com.igexin.push.core.b.m);
            return this;
        }

        public a a(String str, boolean z) {
            this.f1959a.append(Operators.SPACE_STR);
            this.f1959a.append(str);
            this.f1959a.append(Constants.COLON_SEPARATOR);
            this.f1959a.append(z);
            return this;
        }

        public String toString() {
            return this.f1959a.toString();
        }
    }

    public static void a(String str) {
        if (b) {
            Log.d(f1958a, e(str));
        }
        ak.b(str);
    }

    public static void a(String str, Throwable th) {
        Log.e(f1958a, e(str), th);
        ak.f(str, th);
    }

    public static void a(Throwable th) {
        if (b) {
            Log.d(f1958a, "", th);
        }
        ak.b(th);
    }

    public static void b(String str) {
        if (b) {
            Log.w(f1958a, e(str));
        }
        ak.d(str);
    }

    public static void b(Throwable th) {
        Log.e(f1958a, "", th);
        ak.f(th);
    }

    public static void c(String str) {
        Log.e(f1958a, e(str));
        ak.f(str);
    }

    public static a d(String str) {
        return new a(str);
    }

    public static String e(String str) {
        return str == null ? com.igexin.push.core.b.m : str;
    }
}
