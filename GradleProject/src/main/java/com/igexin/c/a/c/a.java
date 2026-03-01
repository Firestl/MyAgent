package com.igexin.c.a.c;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;
import com.igexin.c.a.d.g;
import com.igexin.push.config.e;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static volatile a b;
    public static final List<String> c = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Logger f3183a;

    public a() {
        try {
            this.f3183a = new Logger(GtcProvider.context());
            this.f3183a.setGlobalTag("gtsdk");
            this.f3183a.setLogcatEnable(false);
            this.f3183a.setLogFileNameSuffix("GTSDK");
            this.f3183a.setStackOffset(1);
            this.f3183a.setFileEnableProperty("sdk.debug");
            c.add(g.h);
            c.add("ScheduleQueue");
        } catch (Throwable unused) {
        }
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public static void a(String str, String str2) {
        try {
            if (a().f3183a == null || str == null || c.contains(str)) {
                return;
            }
            a().f3183a.e(str + "|" + str2);
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, Object... objArr) {
        try {
            if (a().f3183a != null) {
                if (objArr.length > 0) {
                    str = String.format(str, objArr);
                }
                a().f3183a.filelog(1, null, str, null);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Throwable th) {
        try {
            if (a().f3183a != null) {
                a().f3183a.e(th);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(boolean z) {
        try {
            e.a(Boolean.valueOf(z));
            if (a().f3183a != null) {
                a().f3183a.setLogcatEnable(false);
                a().f3183a.setFileEnableProperty("sdk.debug");
            }
        } catch (Throwable unused) {
        }
    }

    public static Logger b() {
        return a().f3183a;
    }

    public static void b(String str, String str2) {
        try {
            if (a().f3183a == null || str == null || c.contains(str)) {
                return;
            }
            a().f3183a.d(str + "|" + str2);
        } catch (Throwable unused) {
        }
    }

    public static void c(String str, String str2) {
        try {
            if (a().f3183a == null || str == null || c.contains(str)) {
                return;
            }
            a().f3183a.logcat(2, null, str2, null);
        } catch (Throwable unused) {
        }
    }

    public static void d(String str, String str2) {
        try {
            if (a().f3183a == null || str == null || c.contains(str)) {
                return;
            }
            a().f3183a.logcat(3, null, str2, null);
        } catch (Throwable unused) {
        }
    }

    public static void e(String str, String str2) {
        try {
            if (a().f3183a == null || str == null || c.contains(str)) {
                return;
            }
            a().f3183a.logcat(4, null, str2, null);
        } catch (Throwable unused) {
        }
    }
}
