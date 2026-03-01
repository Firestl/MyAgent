package com.heytap.mcssdk.c;

import com.heytap.msp.push.mode.BaseMode;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends BaseMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2568a = "&";
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2569e;
    public int f;
    public String g;
    public int h = -2;
    public String i;

    public static <T> String a(List<T> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("&");
        }
        return sb.toString();
    }

    public String a() {
        return this.b;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void b(int i) {
        this.h = i;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.f2569e;
    }

    public void d(String str) {
        this.f2569e = str;
    }

    public int e() {
        return this.f;
    }

    public void e(String str) {
        this.g = str;
    }

    public String f() {
        return this.g;
    }

    public void f(String str) {
        this.i = str;
    }

    public int g() {
        return this.h;
    }

    @Override // com.heytap.msp.push.mode.BaseMode
    public int getType() {
        return 4105;
    }

    public String h() {
        return this.i;
    }

    public String toString() {
        return "CallBackResult{, mRegisterID='" + this.d + Operators.SINGLE_QUOTE + ", mSdkVersion='" + this.f2569e + Operators.SINGLE_QUOTE + ", mCommand=" + this.f + Operators.SINGLE_QUOTE + ", mContent='" + this.g + Operators.SINGLE_QUOTE + ", mAppPackage=" + this.i + Operators.SINGLE_QUOTE + ", mResponseCode=" + this.h + Operators.BLOCK_END;
    }
}
