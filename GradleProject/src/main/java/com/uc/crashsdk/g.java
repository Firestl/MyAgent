package com.uc.crashsdk;

import android.os.Build;
import android.os.Bundle;
import com.igexin.push.core.a.c.h;
import com.uc.crashsdk.export.CustomInfo;
import com.uc.crashsdk.export.VersionInfo;
import java.io.File;
import java.lang.reflect.Field;
import java.util.zip.ZipFile;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    public static final /* synthetic */ boolean c = !g.class.desiredAssertionStatus();
    public static CustomInfo d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static VersionInfo f5134e = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static RuntimeException f5133a = null;
    public static RuntimeException b = null;
    public static final Object f = new Object();
    public static String g = null;
    public static String h = null;
    public static String i = null;
    public static String j = null;
    public static final Object k = new Object();

    public static int A() {
        return d.mLogMaxBytesLimit;
    }

    public static int B() {
        return d.mLogMaxUploadBytesLimit;
    }

    public static long C() {
        return d.mMaxUploadBytesPerDay;
    }

    public static int D() {
        return d.mMaxUploadBuiltinLogCountPerDay;
    }

    public static int E() {
        return d.mMaxUploadCustomLogCountPerDay;
    }

    public static int F() {
        return d.mMaxCustomLogCountPerTypePerDay;
    }

    public static int G() {
        return d.mInfoUpdateInterval;
    }

    public static int H() {
        return d.mInfoSaveFrequency;
    }

    public static int I() {
        return d.mReservedJavaFileHandleCount;
    }

    public static int J() {
        return d.mFdDumpMinLimit;
    }

    public static int K() {
        return d.mThreadsDumpMinLimit;
    }

    public static boolean L() {
        return d.mAutoDetectLifeCycle;
    }

    public static boolean M() {
        return d.mMonitorBattery;
    }

    public static int N() {
        return d.mAnrTraceStrategy;
    }

    public static boolean O() {
        CustomInfo customInfo = d;
        return customInfo == null || customInfo.mDebug;
    }

    public static boolean P() {
        CustomInfo customInfo = d;
        return customInfo == null || customInfo.mPrintStackInfos;
    }

    public static boolean Q() {
        return d.mEnableStatReport;
    }

    public static boolean R() {
        return d.mIsInternational;
    }

    public static boolean S() {
        return d.mAddPvForNewDay;
    }

    public static String T() {
        return com.uc.crashsdk.a.g.a(f5134e.mVersion) ? a.a() : a(f5134e.mVersion);
    }

    public static String U() {
        return com.uc.crashsdk.a.g.a(f5134e.mSubVersion) ? "release" : f5134e.mSubVersion;
    }

    public static String V() {
        return com.uc.crashsdk.a.g.a(f5134e.mBuildId) ? ad() : a(f5134e.mBuildId);
    }

    public static String W() {
        if (h == null) {
            h = com.uc.crashsdk.a.g.b() + File.separatorChar + d.mTagFilesFolderName + File.separatorChar;
        }
        return h;
    }

    public static String X() {
        if (i == null) {
            i = com.uc.crashsdk.a.g.b() + File.separatorChar + d.mCrashLogsFolderName + File.separatorChar;
        }
        return i;
    }

    public static String Y() {
        if (j == null) {
            if (com.uc.crashsdk.a.g.a(d.mLogsBackupPathName)) {
                j = (com.uc.crashsdk.a.g.b() + File.separatorChar + "msdb" + File.separatorChar) + File.separatorChar + d.mCrashLogsFolderName + File.separatorChar;
            } else {
                String strTrim = d.mLogsBackupPathName.trim();
                if (!strTrim.endsWith(File.separator)) {
                    strTrim = strTrim + File.separator;
                }
                j = strTrim;
            }
        }
        return j;
    }

    public static boolean Z() {
        return d.mEnableCrpStat;
    }

    public static void a(CustomInfo customInfo, VersionInfo versionInfo) {
        CustomInfo customInfo2 = new CustomInfo(customInfo);
        d = customInfo2;
        c(customInfo2);
        if (!d.mZipLog) {
            f5133a = new RuntimeException("initialize set mZipLog to false, info.mZipLog: " + customInfo.mZipLog);
        }
        if (d.mEncryptLog) {
            b = new RuntimeException("initialize set mEncryptLog to true, info.mEncryptLog: " + customInfo.mEncryptLog);
        }
        f5134e = new VersionInfo(versionInfo);
        if (b.L()) {
            return;
        }
        try {
            a();
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
        }
    }

    public static boolean aa() {
        return d.mEnableStatToWPKDirect;
    }

    public static String ab() {
        return d.mUserId;
    }

    public static String ac() {
        return d.mChannel;
    }

    public static String ad() {
        ZipFile zipFile;
        Throwable th;
        String str = g;
        if (str != null) {
            return str;
        }
        try {
            try {
                zipFile = new ZipFile(com.uc.crashsdk.a.g.c());
            } catch (Throwable th2) {
                zipFile = null;
                th = th2;
            }
        } catch (Throwable unused) {
        }
        try {
            g = Long.toHexString(zipFile.getEntry("classes.dex").getCrc());
            com.uc.crashsdk.a.a.a("crashsdk", "version unique build id: " + g);
            zipFile.close();
        } catch (Throwable th3) {
            th = th3;
            try {
                g = "";
                com.uc.crashsdk.a.g.a(th);
                if (zipFile != null) {
                    zipFile.close();
                }
                return g;
            } catch (Throwable th4) {
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (Throwable unused2) {
                    }
                }
                throw th4;
            }
        }
        return g;
    }

    public static void ae() {
        if (b.d) {
            JNIBridge.nativeSet(24, 1L, a.b, null);
        }
    }

    public static void b() {
        JNIBridge.set(103, com.uc.crashsdk.a.g.b());
        JNIBridge.set(104, d.mTagFilesFolderName);
        JNIBridge.set(105, d.mCrashLogsFolderName);
        JNIBridge.set(106, Y());
        JNIBridge.set(107, e.h());
        JNIBridge.set(108, b.a());
        JNIBridge.set(109, T());
        JNIBridge.set(110, U());
        JNIBridge.set(111, V());
        JNIBridge.set(112, "211215141717");
        JNIBridge.set(116, Build.MODEL);
        JNIBridge.set(117, Build.VERSION.RELEASE);
        JNIBridge.set(118, e.q());
        JNIBridge.set(5, d.mCallNativeDefaultHandler);
        JNIBridge.set(6, d.mDumpUserSolibBuildId);
        JNIBridge.set(7, d.mReservedNativeMemoryBytes);
        JNIBridge.set(100, d.mNativeCrashLogFileName);
        JNIBridge.set(101, d.mUnexpCrashLogFileName);
        JNIBridge.set(35, d.mEnableMemoryGroup);
        JNIBridge.set(36, d.mEnableLibcMallocDetail);
        JNIBridge.set(h.b, d.mLibcMallocDetailConfig);
        JNIBridge.set(102, d.mAppId);
        JNIBridge.set(38, d.mCrashRateUploadUrl);
        JNIBridge.set(39, d.mCrashSDKAuthUrl);
    }

    public static void c(CustomInfo customInfo) {
        if (customInfo.mZippedLogExtension == null) {
            customInfo.mZippedLogExtension = "";
        }
        if (customInfo.mZippedLogExtension.equals(".tmp")) {
            throw new IllegalArgumentException("mZippedLogExtension can not be '.tmp'!");
        }
        if (customInfo.mOmitJavaCrash) {
            customInfo.mCallJavaDefaultHandler = false;
        }
        if (customInfo.mOmitNativeCrash) {
            customInfo.mCallNativeDefaultHandler = false;
        }
        long jB = e.b();
        if (jB >= 1) {
            customInfo.mMaxBuiltinLogFilesCount = 200;
            customInfo.mMaxCustomLogFilesCount = 100;
            customInfo.mMaxUploadBytesPerDay = 268435456L;
            customInfo.mMaxUploadBuiltinLogCountPerDay = 2000;
            customInfo.mMaxUploadCustomLogCountPerDay = 2000;
            customInfo.mMaxCustomLogCountPerTypePerDay = 100;
            customInfo.mMaxAnrLogCountPerProcess = 100;
            customInfo.mAnrTraceStrategy = 2;
            if (jB >= 2) {
                customInfo.mSyncUploadSetupCrashLogs = true;
                customInfo.mSyncUploadLogs = true;
                if (jB >= 3) {
                    customInfo.mBackupLogs = true;
                    customInfo.mPrintStackInfos = true;
                    customInfo.mDebug = true;
                }
            }
        }
    }

    public static void d() {
        JNIBridge.set(23, d.mIsInternational);
        if (b.H()) {
            JNIBridge.set(34, true);
        }
        if (e.i()) {
            JNIBridge.set(1, true);
        }
        JNIBridge.set(10, d.mFdDumpMinLimit);
        JNIBridge.nativeCmd(3, d.mReservedNativeFileHandleCount, null, null);
        JNIBridge.nativeSetForeground(b.B());
        JNIBridge.set(2, b.F());
        a.e();
        a.g();
        a.i();
        a.k();
        JNIBridge.set(113, a.f5104a);
        JNIBridge.cmd(1);
        JNIBridge.set(22, d.mThreadsDumpMinLimit);
        JNIBridge.set(122, a.a());
        JNIBridge.set(33, a.c());
        ae();
        b.K();
        b.D();
        com.uc.crashsdk.a.g.k();
    }

    public static String e() {
        return d.mAppId;
    }

    public static boolean f() {
        if (com.uc.crashsdk.a.g.b(d.mJavaCrashLogFileName) || com.uc.crashsdk.a.g.b(d.mNativeCrashLogFileName)) {
            return true;
        }
        return com.uc.crashsdk.a.g.b(d.mUnexpCrashLogFileName);
    }

    public static String g() {
        return d.mJavaCrashLogFileName;
    }

    public static int h() {
        return d.mCrashRestartInterval;
    }

    public static boolean i() {
        return d.mCallJavaDefaultHandler;
    }

    public static boolean j() {
        return d.mDumpHprofDataForJavaOOM;
    }

    public static boolean k() {
        return d.mRenameFileToDefaultName;
    }

    public static int l() {
        return d.mMaxBuiltinLogFilesCount;
    }

    public static int m() {
        return d.mMaxCustomLogFilesCount;
    }

    public static int n() {
        return d.mMaxJavaLogcatLineCount;
    }

    public static int o() {
        return d.mUnexpDelayMillSeconds;
    }

    public static int p() {
        return d.mUnexpSubTypes;
    }

    public static boolean q() {
        return d.mBackupLogs;
    }

    public static boolean r() {
        return d.mSyncUploadSetupCrashLogs;
    }

    public static boolean s() {
        return d.mSyncUploadLogs;
    }

    public static boolean t() {
        return d.mOmitJavaCrash;
    }

    public static boolean u() {
        return d.mAutoDeleteOldVersionStats;
    }

    public static boolean v() {
        return d.mZipLog;
    }

    public static String w() {
        return d.mZippedLogExtension;
    }

    public static boolean x() {
        return d.mEncryptLog;
    }

    public static String y() {
        return d.mCrashLogUploadUrl;
    }

    public static String z() {
        return d.mCrashRateUploadUrl;
    }

    public static void a(CustomInfo customInfo) {
        if (!c && customInfo.mTagFilesFolderName == null) {
            throw new AssertionError();
        }
        if (!c && customInfo.mCrashLogsFolderName == null) {
            throw new AssertionError();
        }
        if (customInfo.mTagFilesFolderName.equals(customInfo.mCrashLogsFolderName)) {
            throw new IllegalArgumentException("mTagFilesFolderName and mCrashLogsFolderName can not be set to the same!");
        }
    }

    public static void a(VersionInfo versionInfo) {
        synchronized (f) {
            f5134e = new VersionInfo(versionInfo);
            e.c();
            if (b.d) {
                JNIBridge.set(109, T());
                JNIBridge.set(110, U());
                JNIBridge.set(111, V());
                JNIBridge.set(112, "211215141717");
                JNIBridge.cmd(2);
            }
        }
    }

    public static void a() {
        b.y();
        b.x();
        if (d.mBackupLogs) {
            File file = new File(Y());
            if (file.exists()) {
                return;
            }
            file.mkdirs();
        }
    }

    public static void c() {
        JNIBridge.set(11, P());
        JNIBridge.set(12, d.mBackupLogs);
        JNIBridge.set(13, d.mCrashRestartInterval);
        JNIBridge.set(14, d.mMaxBuiltinLogFilesCount);
        JNIBridge.set(15, d.mMaxNativeLogcatLineCount);
        JNIBridge.set(16, d.mMaxUnexpLogcatLineCount);
        JNIBridge.set(31, d.mMaxAnrLogcatLineCount);
        JNIBridge.set(18, O());
        JNIBridge.set(20, Build.VERSION.SDK_INT);
        JNIBridge.set(21, d.mOmitNativeCrash);
        JNIBridge.set(32, d.mMaxAnrLogCountPerProcess);
        JNIBridge.set(8, d.mDisableSignals);
        JNIBridge.set(9, d.mDisableBackgroundSignals);
        JNIBridge.nativeSet(3, d.mZipLog ? 1L : 0L, d.mZippedLogExtension, null);
        JNIBridge.set(4, d.mLogMaxBytesLimit);
        JNIBridge.set(119, Build.FINGERPRINT);
    }

    public static String a(String str) {
        return (str == null || !str.contains("_")) ? str : str.replaceAll("_", "-");
    }

    public static int b(CustomInfo customInfo) {
        int i2;
        int i3;
        boolean z;
        boolean z2;
        synchronized (k) {
            i2 = 0;
            if (customInfo != null) {
                c(customInfo);
                if (d == null) {
                    d = new CustomInfo();
                }
                CustomInfo customInfo2 = d;
                boolean z3 = true;
                if (a(customInfo.mAppId, customInfo2.mAppId)) {
                    i3 = 0;
                    z = false;
                } else {
                    String str = customInfo.mAppId;
                    customInfo2.mAppId = str;
                    if (b.d) {
                        JNIBridge.set(102, str);
                    }
                    i3 = 1;
                    z = true;
                }
                if (!a(customInfo.mJavaCrashLogFileName, customInfo2.mJavaCrashLogFileName)) {
                    customInfo2.mJavaCrashLogFileName = customInfo.mJavaCrashLogFileName;
                    i3++;
                }
                if (!a(customInfo.mNativeCrashLogFileName, customInfo2.mNativeCrashLogFileName)) {
                    String str2 = customInfo.mNativeCrashLogFileName;
                    customInfo2.mNativeCrashLogFileName = str2;
                    if (b.d) {
                        JNIBridge.set(100, str2);
                    }
                    i3++;
                    z = true;
                }
                if (!a(customInfo.mUnexpCrashLogFileName, customInfo2.mUnexpCrashLogFileName)) {
                    String str3 = customInfo.mUnexpCrashLogFileName;
                    customInfo2.mUnexpCrashLogFileName = str3;
                    if (b.d) {
                        JNIBridge.set(101, str3);
                    }
                    i3++;
                    z = true;
                }
                if (z) {
                    e.c();
                    if (b.d) {
                        JNIBridge.cmd(2);
                    }
                }
                if (customInfo2.mPrintStackInfos != customInfo.mPrintStackInfos) {
                    boolean z4 = customInfo.mPrintStackInfos;
                    customInfo2.mPrintStackInfos = z4;
                    if (b.d) {
                        JNIBridge.set(11, z4);
                    }
                    i3++;
                }
                if (customInfo2.mDebug != customInfo.mDebug) {
                    boolean z5 = customInfo.mDebug;
                    customInfo2.mDebug = z5;
                    if (b.d) {
                        JNIBridge.set(18, z5);
                    }
                    i3++;
                }
                if (customInfo2.mBackupLogs != customInfo.mBackupLogs) {
                    boolean z6 = customInfo.mBackupLogs;
                    customInfo2.mBackupLogs = z6;
                    if (b.d) {
                        JNIBridge.set(12, z6);
                    }
                    i3++;
                }
                if (customInfo2.mOmitNativeCrash != customInfo.mOmitNativeCrash) {
                    boolean z7 = customInfo.mOmitNativeCrash;
                    customInfo2.mOmitNativeCrash = z7;
                    if (b.d) {
                        JNIBridge.set(21, z7);
                    }
                    i3++;
                }
                if (customInfo2.mCrashRestartInterval != customInfo.mCrashRestartInterval) {
                    int i4 = customInfo.mCrashRestartInterval;
                    customInfo2.mCrashRestartInterval = i4;
                    if (b.d) {
                        JNIBridge.set(13, i4);
                    }
                    if (customInfo2.mCrashRestartInterval >= 0) {
                        b.M();
                    }
                    i3++;
                }
                if (customInfo2.mMaxBuiltinLogFilesCount != customInfo.mMaxBuiltinLogFilesCount) {
                    int i5 = customInfo.mMaxBuiltinLogFilesCount;
                    customInfo2.mMaxBuiltinLogFilesCount = i5;
                    if (b.d) {
                        JNIBridge.set(14, i5);
                    }
                    i3++;
                }
                if (customInfo2.mMaxNativeLogcatLineCount != customInfo.mMaxNativeLogcatLineCount) {
                    int i6 = customInfo.mMaxNativeLogcatLineCount;
                    customInfo2.mMaxNativeLogcatLineCount = i6;
                    if (b.d) {
                        JNIBridge.set(15, i6);
                    }
                    i3++;
                }
                if (customInfo2.mMaxJavaLogcatLineCount != customInfo.mMaxJavaLogcatLineCount) {
                    customInfo2.mMaxJavaLogcatLineCount = customInfo.mMaxJavaLogcatLineCount;
                    i3++;
                }
                if (customInfo2.mMaxUnexpLogcatLineCount != customInfo.mMaxUnexpLogcatLineCount) {
                    int i7 = customInfo.mMaxUnexpLogcatLineCount;
                    customInfo2.mMaxUnexpLogcatLineCount = i7;
                    if (b.d) {
                        JNIBridge.set(16, i7);
                    }
                    i3++;
                }
                if (customInfo2.mMaxAnrLogcatLineCount != customInfo.mMaxAnrLogcatLineCount) {
                    int i8 = customInfo.mMaxAnrLogcatLineCount;
                    customInfo2.mMaxAnrLogcatLineCount = i8;
                    if (b.d) {
                        JNIBridge.set(31, i8);
                    }
                    i3++;
                }
                if (customInfo2.mZipLog != customInfo.mZipLog) {
                    boolean z8 = customInfo.mZipLog;
                    customInfo2.mZipLog = z8;
                    if (!z8) {
                        com.uc.crashsdk.a.a.d("DEBUG", "updateCustomInfoImpl set mZipLog to false");
                        f5133a = new RuntimeException("updateCustomInfoImpl set mZipLog to false");
                    }
                    i3++;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (a(customInfo.mZippedLogExtension, customInfo2.mZippedLogExtension)) {
                    z3 = z2;
                } else {
                    customInfo2.mZippedLogExtension = customInfo.mZippedLogExtension;
                    i3++;
                }
                if (z3 && b.d) {
                    JNIBridge.nativeSet(3, customInfo2.mZipLog ? 1L : 0L, customInfo2.mZippedLogExtension, null);
                }
                if (customInfo2.mLogMaxBytesLimit != customInfo.mLogMaxBytesLimit) {
                    int i9 = customInfo.mLogMaxBytesLimit;
                    customInfo2.mLogMaxBytesLimit = i9;
                    if (b.d) {
                        JNIBridge.set(4, i9);
                    }
                    i3++;
                }
                if (customInfo2.mEncryptLog != customInfo.mEncryptLog) {
                    boolean z9 = customInfo.mEncryptLog;
                    customInfo2.mEncryptLog = z9;
                    if (z9) {
                        com.uc.crashsdk.a.a.d("DEBUG", "updateCustomInfoImpl set mEncryptLog to true");
                        b = new RuntimeException("updateCustomInfoImpl set mEncryptLog to true");
                    }
                    i3++;
                }
                if (customInfo2.mSyncUploadSetupCrashLogs != customInfo.mSyncUploadSetupCrashLogs) {
                    customInfo2.mSyncUploadSetupCrashLogs = customInfo.mSyncUploadSetupCrashLogs;
                    i3++;
                }
                if (customInfo2.mSyncUploadLogs != customInfo.mSyncUploadLogs) {
                    customInfo2.mSyncUploadLogs = customInfo.mSyncUploadLogs;
                    i3++;
                }
                if (customInfo2.mMaxCustomLogFilesCount != customInfo.mMaxCustomLogFilesCount) {
                    customInfo2.mMaxCustomLogFilesCount = customInfo.mMaxCustomLogFilesCount;
                    i3++;
                }
                if (customInfo2.mOmitJavaCrash != customInfo.mOmitJavaCrash) {
                    customInfo2.mOmitJavaCrash = customInfo.mOmitJavaCrash;
                    i3++;
                }
                if (customInfo2.mLogMaxUploadBytesLimit != customInfo.mLogMaxUploadBytesLimit) {
                    customInfo2.mLogMaxUploadBytesLimit = customInfo.mLogMaxUploadBytesLimit;
                    i3++;
                }
                if (customInfo2.mMaxUploadBytesPerDay != customInfo.mMaxUploadBytesPerDay) {
                    customInfo2.mMaxUploadBytesPerDay = customInfo.mMaxUploadBytesPerDay;
                    i3++;
                }
                if (customInfo2.mMaxUploadBuiltinLogCountPerDay != customInfo.mMaxUploadBuiltinLogCountPerDay) {
                    customInfo2.mMaxUploadBuiltinLogCountPerDay = customInfo.mMaxUploadBuiltinLogCountPerDay;
                    i3++;
                }
                if (customInfo2.mMaxUploadCustomLogCountPerDay != customInfo.mMaxUploadCustomLogCountPerDay) {
                    customInfo2.mMaxUploadCustomLogCountPerDay = customInfo.mMaxUploadCustomLogCountPerDay;
                    i3++;
                }
                if (customInfo2.mMaxCustomLogCountPerTypePerDay != customInfo.mMaxCustomLogCountPerTypePerDay) {
                    customInfo2.mMaxCustomLogCountPerTypePerDay = customInfo.mMaxCustomLogCountPerTypePerDay;
                    i3++;
                }
                if (customInfo2.mMaxAnrLogCountPerProcess != customInfo.mMaxAnrLogCountPerProcess) {
                    customInfo2.mMaxAnrLogCountPerProcess = customInfo.mMaxAnrLogCountPerProcess;
                    if (b.d) {
                        JNIBridge.set(32, d.mMaxAnrLogCountPerProcess);
                    }
                    i3++;
                }
                if (customInfo2.mCallJavaDefaultHandler != customInfo.mCallJavaDefaultHandler) {
                    customInfo2.mCallJavaDefaultHandler = customInfo.mCallJavaDefaultHandler;
                    i3++;
                }
                if (customInfo2.mCallNativeDefaultHandler != customInfo.mCallNativeDefaultHandler) {
                    customInfo2.mCallNativeDefaultHandler = customInfo.mCallNativeDefaultHandler;
                    i3++;
                    if (b.d) {
                        JNIBridge.set(5, d.mCallNativeDefaultHandler);
                    }
                }
                if (customInfo2.mDumpUserSolibBuildId != customInfo.mDumpUserSolibBuildId) {
                    customInfo2.mDumpUserSolibBuildId = customInfo.mDumpUserSolibBuildId;
                    i3++;
                    if (b.d) {
                        JNIBridge.set(6, d.mDumpUserSolibBuildId);
                    }
                }
                if (customInfo2.mDumpHprofDataForJavaOOM != customInfo.mDumpHprofDataForJavaOOM) {
                    customInfo2.mDumpHprofDataForJavaOOM = customInfo.mDumpHprofDataForJavaOOM;
                    i3++;
                }
                if (customInfo2.mRenameFileToDefaultName != customInfo.mRenameFileToDefaultName) {
                    customInfo2.mRenameFileToDefaultName = customInfo.mRenameFileToDefaultName;
                    i3++;
                }
                if (customInfo2.mAutoDeleteOldVersionStats != customInfo.mAutoDeleteOldVersionStats) {
                    customInfo2.mAutoDeleteOldVersionStats = customInfo.mAutoDeleteOldVersionStats;
                    i3++;
                }
                if (customInfo2.mFdDumpMinLimit != customInfo.mFdDumpMinLimit) {
                    int i10 = customInfo.mFdDumpMinLimit;
                    customInfo2.mFdDumpMinLimit = i10;
                    if (b.d) {
                        JNIBridge.set(10, i10);
                    }
                    i3++;
                }
                if (customInfo2.mThreadsDumpMinLimit != customInfo.mThreadsDumpMinLimit) {
                    int i11 = customInfo.mThreadsDumpMinLimit;
                    customInfo2.mThreadsDumpMinLimit = i11;
                    if (b.d) {
                        JNIBridge.set(22, i11);
                    }
                    i3++;
                }
                if (customInfo2.mInfoUpdateInterval != customInfo.mInfoUpdateInterval) {
                    if (customInfo2.mInfoUpdateInterval <= 0 && customInfo.mInfoUpdateInterval > 0) {
                        a.a(false);
                    }
                    customInfo2.mInfoUpdateInterval = customInfo.mInfoUpdateInterval;
                    i3++;
                }
                if (customInfo2.mInfoSaveFrequency != customInfo.mInfoSaveFrequency) {
                    customInfo2.mInfoSaveFrequency = customInfo.mInfoSaveFrequency;
                    i3++;
                }
                if (customInfo2.mDisableBackgroundSignals != customInfo.mDisableBackgroundSignals) {
                    long j2 = customInfo.mDisableBackgroundSignals;
                    customInfo2.mDisableBackgroundSignals = j2;
                    if (b.d) {
                        JNIBridge.set(9, j2);
                    }
                    i3++;
                }
                if (customInfo2.mEnableStatReport != customInfo.mEnableStatReport) {
                    boolean z10 = customInfo.mEnableStatReport;
                    customInfo2.mEnableStatReport = z10;
                    if (z10) {
                        e.B();
                    }
                    i3++;
                }
                if (customInfo2.mEnableCrpStat != customInfo.mEnableCrpStat) {
                    customInfo2.mEnableCrpStat = customInfo.mEnableCrpStat;
                    i3++;
                }
                if (customInfo2.mEnableStatToWPKDirect != customInfo.mEnableStatToWPKDirect) {
                    customInfo2.mEnableStatToWPKDirect = customInfo.mEnableStatToWPKDirect;
                    i3++;
                }
                if (customInfo2.mIsInternational != customInfo.mIsInternational) {
                    boolean z11 = customInfo.mIsInternational;
                    customInfo2.mIsInternational = z11;
                    if (b.d) {
                        JNIBridge.set(23, z11);
                    }
                    e.l();
                    com.uc.crashsdk.a.d.c();
                    com.uc.crashsdk.a.h.k();
                    i3++;
                }
                if (customInfo2.mAutoDetectLifeCycle != customInfo.mAutoDetectLifeCycle) {
                    boolean z12 = customInfo.mAutoDetectLifeCycle;
                    customInfo2.mAutoDetectLifeCycle = z12;
                    if (z12) {
                        b.C();
                    }
                    i3++;
                }
                if (customInfo2.mMonitorBattery != customInfo.mMonitorBattery) {
                    customInfo2.mMonitorBattery = customInfo.mMonitorBattery;
                    e.c(b.B());
                    i3++;
                }
                if (customInfo2.mUnexpSubTypes != customInfo.mUnexpSubTypes) {
                    customInfo2.mUnexpSubTypes = customInfo.mUnexpSubTypes;
                    i3++;
                }
                if (customInfo2.mEnableMemoryGroup != customInfo.mEnableMemoryGroup) {
                    boolean z13 = customInfo.mEnableMemoryGroup;
                    customInfo2.mEnableMemoryGroup = z13;
                    if (b.d) {
                        JNIBridge.set(35, z13);
                    }
                    i3++;
                }
                if (customInfo2.mEnableLibcMallocDetail != customInfo.mEnableLibcMallocDetail) {
                    boolean z14 = customInfo.mEnableLibcMallocDetail;
                    customInfo2.mEnableLibcMallocDetail = z14;
                    if (b.d) {
                        JNIBridge.set(36, z14);
                    }
                    i3++;
                }
                if (customInfo2.mLibcMallocDetailConfig != customInfo.mLibcMallocDetailConfig) {
                    String str4 = customInfo.mLibcMallocDetailConfig;
                    customInfo2.mLibcMallocDetailConfig = str4;
                    if (b.d) {
                        JNIBridge.set(h.b, str4);
                    }
                    i3++;
                }
                if (!a(customInfo.mUserId, customInfo2.mUserId)) {
                    customInfo2.mUserId = customInfo.mUserId;
                    i3++;
                }
                if (!a(customInfo.mChannel, customInfo2.mChannel)) {
                    customInfo2.mChannel = customInfo.mChannel;
                    i3++;
                }
                if (!a(customInfo2.mCrashLogUploadUrl, customInfo.mCrashLogUploadUrl)) {
                    customInfo2.mCrashLogUploadUrl = customInfo.mCrashLogUploadUrl;
                    i3++;
                }
                if (!a(customInfo2.mCrashRateUploadUrl, customInfo.mCrashRateUploadUrl)) {
                    String str5 = customInfo.mCrashRateUploadUrl;
                    customInfo2.mCrashRateUploadUrl = str5;
                    i3++;
                    if (b.d) {
                        JNIBridge.set(38, str5);
                    }
                }
                if (!a(customInfo2.mCrashSDKAuthUrl, customInfo.mCrashSDKAuthUrl)) {
                    String str6 = customInfo.mCrashSDKAuthUrl;
                    customInfo2.mCrashSDKAuthUrl = str6;
                    i3++;
                    if (b.d) {
                        JNIBridge.set(39, str6);
                    }
                }
                i2 = i3;
            }
        }
        return i2;
    }

    public static CustomInfo a(CustomInfo customInfo, Bundle bundle) {
        if (customInfo == null) {
            CustomInfo customInfo2 = d;
            if (customInfo2 == null) {
                customInfo = new CustomInfo();
            } else {
                customInfo = new CustomInfo(customInfo2);
            }
        }
        Field[] fields = customInfo.getClass().getFields();
        for (String str : bundle.keySet()) {
            for (Field field : fields) {
                if (field.getName().equals(str)) {
                    Object obj = bundle.get(str);
                    try {
                        field.set(customInfo, obj);
                    } catch (Exception e2) {
                        com.uc.crashsdk.a.g.a(e2);
                        StringBuilder sb = new StringBuilder("Field ");
                        sb.append(str);
                        sb.append(" must be a ");
                        sb.append(field.getType().getName());
                        sb.append(", but give a ");
                        sb.append(obj != null ? obj.getClass().getName() : "(null)");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
        }
        return customInfo;
    }

    public static VersionInfo a(Bundle bundle) {
        VersionInfo versionInfo;
        VersionInfo versionInfo2 = f5134e;
        if (versionInfo2 == null) {
            versionInfo = new VersionInfo();
        } else {
            versionInfo = new VersionInfo(versionInfo2);
        }
        String string = bundle.getString("mVersion");
        if (!com.uc.crashsdk.a.g.a(string)) {
            versionInfo.mVersion = string;
        }
        String string2 = bundle.getString("mSubVersion");
        if (!com.uc.crashsdk.a.g.a(string2)) {
            versionInfo.mSubVersion = string2;
        }
        String string3 = bundle.getString("mBuildId");
        if (!com.uc.crashsdk.a.g.a(string3)) {
            versionInfo.mBuildId = string3;
        }
        String string4 = bundle.getString("crver");
        if (!com.uc.crashsdk.a.g.a(string4)) {
            a.b = string4;
            ae();
        }
        return versionInfo;
    }

    public static boolean a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
