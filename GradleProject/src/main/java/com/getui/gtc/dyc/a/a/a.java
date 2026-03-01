package com.getui.gtc.dyc.a.a;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Logger f2193a;

    /* JADX INFO: renamed from: com.getui.gtc.dyc.a.a.a$a, reason: collision with other inner class name */
    public static class C0042a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f2194a = new a();
    }

    public a() {
        Logger logger = new Logger(GtcProvider.context());
        this.f2193a = logger;
        logger.setGlobalTag("gtc.dyc");
        this.f2193a.setFileEnableProperty("dyc.fileLog");
        this.f2193a.setLogcatEnable(false);
        this.f2193a.setLogFileNameSuffix("gtc");
        this.f2193a.setStackOffset(1);
    }

    public static Logger a() {
        return C0042a.f2194a.f2193a;
    }

    public static void a(String str) {
        C0042a.f2194a.f2193a.e(str);
    }

    public static void a(Throwable th) {
        C0042a.f2194a.f2193a.w(th);
    }

    public static void c(Throwable th) {
        C0042a.f2194a.f2193a.e(th);
    }
}
