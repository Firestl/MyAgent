package com.sangfor.sdk.sandbox.business.file.jni;

import android.content.Context;
import android.os.Handler;
import io.dcloud.common.util.Md5Utils;
import java.io.File;
import java.io.FileDescriptor;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import supwisdom.fb1;
import supwisdom.ib1;
import supwisdom.w61;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class CryptoFilesManager {
    public static CryptoFilesManager b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<String> f3941a;

    /* JADX INFO: compiled from: Proguard */
    public enum Sangfor_c {
        NONE(0),
        V1(1),
        V2(2),
        V3(3),
        VMAX(4);

        public int Sangfor_g;

        Sangfor_c(int i) {
            this.Sangfor_g = i;
        }
    }

    public CryptoFilesManager() {
        new Handler();
        Sangfor_c sangfor_c = Sangfor_c.NONE;
        this.f3941a = new HashSet();
        new File("/sdcard/.sangfor", ".MIGRATING");
        this.f3941a.add("com.lenovo.browser");
        this.f3941a.add("com.quark.browser");
        this.f3941a.add("com.cn21.ecloud");
        this.f3941a.add("com.opera.mini.native");
        this.f3941a.add("cn.mozilla.firefox");
        this.f3941a.add("org.mozilla.firefox");
        this.f3941a.add("com.lite.tjlanxin");
        this.f3941a.add("com.alibaba.android.rimet.zqcz");
        c(w61.b());
    }

    private native void nativeAddCryptoRule(String str);

    public static native boolean nativeAddCryptoRuleDynamic(String str);

    private native void nativeAddCryptoWhiteRule(String str);

    public static native boolean nativeAddCryptoWhiteRuleDynamic(String str);

    private native void nativeAddRedirectRule(String str, String str2);

    public static native boolean nativeAddRedirectRuleDynamic(String str, String str2);

    private native void nativeAddRedirectWhiteRule(String str);

    public static native boolean nativeAddRedirectWhiteRuleDynamic(String str);

    private native void nativeAddRemoteDescriptor(FileDescriptor fileDescriptor);

    public static native boolean nativeBackUpData(String str, String str2, String str3);

    public static native void nativeCloseFileDescriptor(FileDescriptor fileDescriptor);

    public static native boolean nativeConversionData(String str, String str2, String str3);

    public static native boolean nativeDecryptoFileToPath(int i, String str);

    public static native boolean nativeDecryptoFileToPathWithProgress(int i, String str, Object obj);

    private native void nativeInitCryptoFiles(String str, byte[] bArr, byte[] bArr2, boolean z, int i, boolean z2);

    private native void nativeInitCryptoKeys(byte[] bArr, byte[] bArr2);

    private native void nativeInitProperties(int i, String str);

    public static native boolean nativeIsRemoteFileEncrypted(FileDescriptor fileDescriptor, boolean z);

    private native void nativeMakeDirectory(String str);

    public static native boolean nativeMakeDirectoryDirect(String str);

    public static native boolean nativeMigrateData(String str, String str2, String str3);

    public static native int nativeOpenFileDirect(String str);

    private native void nativeRedirecFilepath(String str, boolean z);

    private native void nativeRedirectFilepathTo(String str, String str2);

    public static native boolean nativeRemoveCryptoRuleDynamic(String str);

    public static native boolean nativeRemoveCryptoWhiteRuleDynamic(String str);

    private native void nativeRemovePath(String str);

    public static native boolean nativeRemoveRedirectRuleDynamic(String str, String str2);

    public static native boolean nativeRemoveRedirectWhiteRuleDynamic(String str);

    private native void nativeUpdateIsofsFlags(int i);

    public final byte[] a(Context context) {
        byte[] bArr = {65, 66, 67, 68, 49, 50, 51, 52};
        try {
            String strB = fb1.b(context);
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(strB.getBytes(Charset.defaultCharset()));
            return messageDigest.digest();
        } catch (Exception unused) {
            return bArr;
        }
    }

    public final byte[] b(Context context) {
        try {
            String str = fb1.a(context) + ib1.a(context);
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return messageDigest.digest();
        } catch (Exception unused) {
            return a(context);
        }
    }

    public void c(Context context) {
        nativeInitCryptoKeys(b(context), a(context));
    }

    public static CryptoFilesManager a() {
        CryptoFilesManager cryptoFilesManager = b;
        if (cryptoFilesManager != null) {
            return cryptoFilesManager;
        }
        synchronized (CryptoFilesManager.class) {
            if (b == null) {
                b = new CryptoFilesManager();
            }
        }
        return b;
    }
}
