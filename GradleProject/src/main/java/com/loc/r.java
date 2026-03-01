package com.loc;

import io.dcloud.common.util.Md5Utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: MD5.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r {
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        return u.e(c(str));
    }

    public static String a(byte[] bArr) {
        return u.e(a(bArr, u.c("ETUQ1")));
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            y.a(th, Md5Utils.ALGORITHM, "gmb");
            return null;
        }
    }

    public static String b(String str) {
        return u.f(d(str));
    }

    public static byte[] c(String str) {
        try {
            return e(str);
        } catch (Throwable th) {
            y.a(th, Md5Utils.ALGORITHM, "gmb");
            return new byte[0];
        }
    }

    public static byte[] d(String str) {
        try {
            return e(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] e(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(u.c("ETUQ1"));
        messageDigest.update(u.a(str));
        return messageDigest.digest();
    }
}
