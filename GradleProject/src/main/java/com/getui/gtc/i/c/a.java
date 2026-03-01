package com.getui.gtc.i.c;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Logger f2243a;

    /* JADX INFO: renamed from: com.getui.gtc.i.c.a$a, reason: collision with other inner class name */
    public static class C0045a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2244a = new a(0);
    }

    public a() {
        Logger logger = new Logger(GtcProvider.context());
        this.f2243a = logger;
        logger.setGlobalTag("gtc");
        this.f2243a.setFileEnableProperty("gtc.fileLog");
        this.f2243a.setLogcatEnable(false);
        this.f2243a.setLogFileNameSuffix("gtc");
        this.f2243a.setStackOffset(1);
    }

    public /* synthetic */ a(byte b) {
        this();
    }

    public static void a(String str) {
        C0045a.f2244a.f2243a.d(str);
    }

    public static void a(String str, Throwable th) {
        C0045a.f2244a.f2243a.e(str, th);
    }

    public static void a(Throwable th) {
        C0045a.f2244a.f2243a.w(th);
    }

    public static void b(String str) {
        C0045a.f2244a.f2243a.w(str);
    }

    public static void b(Throwable th) {
        C0045a.f2244a.f2243a.e(th);
    }

    public static void c(String str) {
        C0045a.f2244a.f2243a.e(str);
    }

    public static void c(Throwable th) {
        C0045a.f2244a.f2243a.filelog(2, null, null, th);
    }

    public static void d(String str) {
        C0045a.f2244a.f2243a.filelog(2, null, str, null);
    }
}
