package com.g.gysdk.a;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;

/* JADX INFO: loaded from: classes.dex */
public class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Logger f1960a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ak f1961a = new ak();
    }

    public ak() {
        Logger logger = new Logger(GtcProvider.context());
        this.f1960a = logger;
        logger.setGlobalTag("GY");
        this.f1960a.setFileEnableProperty("gy.fileLog");
        this.f1960a.setLogcatEnable(false);
        this.f1960a.setLogFileNameSuffix("gy");
        this.f1960a.setStackOffset(2);
    }

    public static Logger a() {
        return a.f1961a.f1960a;
    }

    public static void a(String str) {
        b(str);
    }

    public static void a(String str, Throwable th) {
        b(str, th);
    }

    public static void a(Throwable th) {
        b(th);
    }

    public static void b(String str) {
        a.f1961a.f1960a.d(str);
    }

    public static void b(String str, Throwable th) {
        a.f1961a.f1960a.d(str, th);
    }

    public static void b(Throwable th) {
        a.f1961a.f1960a.d(th);
    }

    public static void c(String str) {
        d(str);
    }

    public static void c(String str, Throwable th) {
        d(str, th);
    }

    public static void c(Throwable th) {
        d(th);
    }

    public static void d(String str) {
        a.f1961a.f1960a.w(str);
    }

    public static void d(String str, Throwable th) {
        a.f1961a.f1960a.w(str, th);
    }

    public static void d(Throwable th) {
        a.f1961a.f1960a.w(th);
    }

    public static void e(String str) {
        f(str);
    }

    public static void e(String str, Throwable th) {
        f(str, th);
    }

    public static void e(Throwable th) {
        f(th);
    }

    public static void f(String str) {
        a.f1961a.f1960a.e(str);
    }

    public static void f(String str, Throwable th) {
        a.f1961a.f1960a.e(str, th);
    }

    public static void f(Throwable th) {
        a.f1961a.f1960a.e(th);
    }
}
