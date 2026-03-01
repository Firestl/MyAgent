package com.getui.gtc.dim.e;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Logger f2185a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2186a = new b(0);
    }

    public b() {
        Logger logger = new Logger(GtcProvider.context());
        this.f2185a = logger;
        logger.setGlobalTag("gtc.dim");
        this.f2185a.setFileEnableProperty("dim.fileLog");
        this.f2185a.setLogcatEnable(false);
        this.f2185a.setLogFileNameSuffix("gtc");
        this.f2185a.setStackOffset(1);
    }

    public /* synthetic */ b(byte b) {
        this();
    }

    public static void a(String str) {
        a.f2186a.f2185a.d(str);
    }

    public static void a(String str, Throwable th) {
        a.f2186a.f2185a.e(str, th);
    }

    public static void a(Throwable th) {
        a.f2186a.f2185a.w(th);
    }

    public static void b(String str) {
        a.f2186a.f2185a.w(str);
    }

    public static void b(Throwable th) {
        a.f2186a.f2185a.e(th);
    }
}
