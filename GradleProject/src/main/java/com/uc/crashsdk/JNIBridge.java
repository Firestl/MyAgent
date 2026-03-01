package com.uc.crashsdk;

import android.os.Looper;
import android.os.Process;
import com.uc.crashsdk.export.LogType;
import java.io.File;
import java.util.Locale;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class JNIBridge {
    public static int addCachedInfo(String str, String str2) {
        return a.b(str, str2);
    }

    public static int addDumpFile(String str, String str2, boolean z, boolean z2, int i, boolean z3) {
        return a.a(str, str2, z, z2, i, z3);
    }

    public static void addHeaderInfo(String str, String str2) {
        a.a(str, str2);
    }

    public static long cmd(int i) {
        return nativeCmd(i, 0L, null, null);
    }

    public static int createCachedInfo(String str, int i, int i2) {
        return a.a(str, i, i2);
    }

    public static boolean generateCustomLog(String str, String str2, long j, String str3, String str4, String str5, String str6) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        return e.a(stringBuffer, str2, j, a.c(str3), a.c(str4), a.c(str5), str6);
    }

    public static String getCallbackInfo(String str, boolean z) {
        return a.a(str, z);
    }

    public static String getDatasForClientJavaLog(int i, String str) {
        boolean zEquals = "$all$".equals(str);
        if (i == 1) {
            return zEquals ? a.h() : a.a(str);
        }
        if (i == 2) {
            return zEquals ? a.j() : a.a(str, true);
        }
        if (i == 3) {
            return zEquals ? a.l() : a.b(str);
        }
        if (i == 4) {
            return e.d(str) ? "1" : "0";
        }
        return null;
    }

    public static String getJavaStackTrace(Thread thread, int i) {
        if (i != 0 && i == Process.myPid()) {
            thread = Looper.getMainLooper().getThread();
        }
        if (thread != null) {
            return e.a(thread.getStackTrace(), "getJavaStackTrace").toString();
        }
        return null;
    }

    public static native boolean nativeAddCachedInfo(String str, String str2);

    public static native int nativeAddCallbackInfo(String str, int i, long j, int i2);

    public static native int nativeAddDumpFile(String str, String str2, boolean z, boolean z2, int i, boolean z3);

    public static native void nativeAddHeaderInfo(String str, String str2);

    public static native boolean nativeChangeState(String str, String str2, boolean z);

    public static native void nativeClientCloseConnection(long j);

    public static native long nativeClientCreateConnection(String str, String str2, String str3, int i);

    public static native int nativeClientWriteData(long j, String str);

    public static native void nativeCloseFile(int i);

    public static native long nativeCmd(int i, long j, String str, Object[] objArr);

    public static native void nativeCrash(int i, int i2);

    public static native int nativeCreateCachedInfo(String str, int i, int i2);

    public static native String nativeDumpThreads(String str, long j);

    public static native int nativeGenerateUnexpLog(long j, int i);

    public static native String nativeGet(int i, long j, String str);

    public static native String nativeGetCallbackInfo(String str, long j, int i, boolean z);

    public static native boolean nativeIsCrashing();

    public static native boolean nativeLockFile(int i, boolean z);

    public static native int nativeLog(int i, String str, String str2);

    public static native int nativeOpenFile(String str);

    public static native long nativeSet(int i, long j, String str, Object[] objArr);

    public static native void nativeSetForeground(boolean z);

    public static void onCrashLogGenerated(String str, String str2, String str3, boolean z) {
        boolean zEquals = e.h().equals(str2);
        boolean zEquals2 = LogType.NATIVE_TYPE.equals(str3);
        if (!e.F()) {
            if (zEquals && zEquals2) {
                try {
                    f.c(true);
                } catch (Throwable th) {
                    com.uc.crashsdk.a.g.a(th);
                }
            }
            str = e.a(str);
        }
        d.a(str, str2, str3);
        if (e.F()) {
            return;
        }
        if (z || (!zEquals && g.s())) {
            e.a(false, false);
        } else if (zEquals) {
            e.b(zEquals2);
        }
    }

    public static void onCrashRestarting() {
        d.a(false);
        b.N();
    }

    public static void onKillProcess(String str, int i, int i2) {
        String str2 = "onKillProcess. SIG: " + i2;
        if (e.a()) {
            com.uc.crashsdk.a.a.b("crashsdk", str2);
        } else {
            com.uc.crashsdk.a.a.a("crashsdk", str2);
        }
        StringBuilder sbF = e.f("onKillProcess");
        sbF.insert(0, String.format(Locale.US, "State in disk: '%s'\n", b.p()));
        sbF.insert(0, String.format(Locale.US, "SIG: %d, fg: %s, exiting: %s, main process: %s, time: %s\n", Integer.valueOf(i2), Boolean.valueOf(b.B()), Boolean.valueOf(b.u()), Boolean.valueOf(b.F()), e.n()));
        sbF.insert(0, String.format(Locale.US, "Kill PID: %d (%s) by pid: %d (%s) tid: %d (%s)\n", Integer.valueOf(i), e.a(i), Integer.valueOf(Process.myPid()), e.a(Process.myPid()), Integer.valueOf(Process.myTid()), Thread.currentThread().getName()));
        String string = sbF.toString();
        if (e.a()) {
            com.uc.crashsdk.a.a.b("crashsdk", string);
        } else {
            com.uc.crashsdk.a.a.a("crashsdk", string);
        }
        if (b.L()) {
            return;
        }
        com.uc.crashsdk.a.g.a(new File(str), string.getBytes());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String onNativeEvent(int i, long j, Object[] objArr) {
        switch (i) {
            case 1:
                return String.valueOf(Runtime.getRuntime().maxMemory());
            case 2:
                return e.d();
            case 3:
                if (objArr != null && objArr.length == 2 && (objArr[0] instanceof String) && (objArr[1] instanceof String)) {
                    return e.a((String) objArr[0], (String) objArr[1]);
                }
                return null;
            case 4:
                return e.g();
            case 5:
                a.a(true);
                return null;
            case 6:
                return com.uc.crashsdk.a.g.d();
            case 7:
                com.uc.crashsdk.a.f.a(2, new com.uc.crashsdk.a.e(102), 8000L);
                e.r();
                return null;
            default:
                return null;
        }
    }

    public static int onPreClientCustomLog(String str, String str2, boolean z) {
        g.a();
        e.a(false);
        if (e.a(str, str2, z)) {
            return 0;
        }
        e.b(str, str2, z);
        return 1;
    }

    public static int registerCurrentThread(String str, int i) {
        return a.a(i, str);
    }

    public static int registerInfoCallback(String str, int i, long j, int i2) {
        return a.a(str, i, null, j, i2);
    }

    public static long set(int i, boolean z) {
        return nativeSet(i, z ? 1L : 0L, null, null);
    }

    public static long cmd(int i, String str) {
        return nativeCmd(i, 0L, str, null);
    }

    public static long set(int i, long j) {
        return nativeSet(i, j, null, null);
    }

    public static long set(int i, String str) {
        return nativeSet(i, 0L, str, null);
    }
}
