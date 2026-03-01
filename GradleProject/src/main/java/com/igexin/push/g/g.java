package com.igexin.push.g;

import android.text.TextUtils;
import android.util.Base64;
import com.getui.gtc.base.crypt.CryptTools;
import io.dcloud.common.util.Md5Utils;
import java.security.MessageDigest;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3590a = "MHwwDQYJKoZIhvcNAQEBBQADawAwaAJhAJp1rROuvBF7sBSnvLaesj2iFhMcY8aXyLvpnNLKs2wjL3JmEnyr++SlVa35liUlzi83tnAFkn3A9GB7pHBNzawyUkBh8WUhq5bnFIkk2RaDa6+5MpG84DEv52p7RR+aWwIDAQAB";
    public static String b = "69d747c4b9f641baf4004be4297e9f3b";
    public static String c = "";
    public static String d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3591e = "com.igexin.push.g.g";
    public static boolean f = false;
    public static int g = 0;
    public static byte[] h = null;
    public static byte[] i = null;
    public static final int j = 200;

    static {
        try {
            h = h();
            byte[] bArrG = g();
            i = bArrG;
            f = (h == null || bArrG == null || b.getBytes() == null) ? false : true;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "|load key error = " + th.toString(), new Object[0]);
            f = false;
            d = th.getMessage();
        }
        if (f) {
            com.igexin.c.a.c.a.a(f3591e, "load Encrypt key success ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "|load  Encrypt key success ~~~~~~~", new Object[0]);
            return;
        }
        com.igexin.c.a.c.a.a(f3591e, "load key error ++++++++");
        com.igexin.c.a.c.a.a(f3591e + "|load key error ++++++++", new Object[0]);
        if (TextUtils.isEmpty(d)) {
            d = "value = null, normal error";
        }
    }

    public static String a(String str, byte[] bArr) throws Exception {
        byte[] bytes = str.getBytes();
        byte[] bArr2 = new byte[bytes.length + bArr.length];
        int iA = com.igexin.c.a.b.g.a(bytes, bArr2, 0, bytes.length);
        if (bArr.length > 0) {
            com.igexin.c.a.b.g.a(bArr, bArr2, iA, bArr.length);
        }
        return Base64.encodeToString(d(bArr2), 2);
    }

    public static boolean a() {
        return f;
    }

    public static byte[] a(com.igexin.push.d.c.a aVar, int i2, int i3) {
        byte[] bArr = new byte[aVar.f3518a + 11];
        com.igexin.c.a.b.g.a(i2, bArr, 0);
        com.igexin.c.a.b.g.a(i3, bArr, 4);
        com.igexin.c.a.b.g.b((short) aVar.f3518a, bArr, 8);
        bArr[10] = aVar.b;
        com.igexin.c.a.b.g.a(aVar.f3519e, bArr, 11, aVar.f3518a);
        return d(bArr);
    }

    public static byte[] a(byte[] bArr) {
        return com.igexin.c.a.a.a.b(bArr, com.igexin.push.core.e.M);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            return CryptTools.encrypt("AES/CFB/NoPadding", new SecretKeySpec(i, "AES"), new IvParameterSpec(bArr2), bArr);
        } catch (Throwable unused) {
            com.igexin.c.a.c.a.a(f3591e, " httpId encrypt  http data  fail ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "| httpId encrypt  http data  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }

    public static byte[] b() {
        if (!f) {
            return new byte[0];
        }
        try {
            byte[] bArr = new byte[h.length];
            System.arraycopy(h, 0, bArr, 0, h.length);
            return CryptTools.encrypt("RSA/NONE/OAEPWithSHA1AndMGF1Padding", CryptTools.parsePublicKey("RSA", f3590a), bArr);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "| getSocketAESKey  fail ~~~~~~~" + th.getMessage(), new Object[0]);
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr) {
        return c(bArr);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            return CryptTools.decrypt("AES/CFB/NoPadding", new SecretKeySpec(i, "AES"), new IvParameterSpec(bArr2), bArr);
        } catch (Throwable unused) {
            com.igexin.c.a.c.a.a(f3591e, " httpId decrypt  http data  fail ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "| httpId decrypt  http data  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }

    public static byte[] c() {
        return b.getBytes();
    }

    public static byte[] c(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        try {
            return CryptTools.encrypt("AES/CFB/NoPadding", new SecretKeySpec(h, "AES"), new IvParameterSpec(bArr2), bArr);
        } catch (Throwable unused) {
            com.igexin.c.a.c.a.a(f3591e, " sockeId encrypt  http data  fail ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "| sockeId encrypt  http data  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }

    public static boolean d() {
        try {
            h = h();
            byte[] bArrG = g();
            i = bArrG;
            f = (h == null || bArrG == null || b.getBytes() == null) ? false : true;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "|load key error = " + th.toString(), new Object[0]);
            f = false;
        }
        if (f) {
            com.igexin.c.a.c.a.a(f3591e, "load key success ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "|load key success ~~~~~~~", new Object[0]);
        } else {
            com.igexin.c.a.c.a.a(f3591e, "load key error ++++++++");
            com.igexin.c.a.c.a.a(f3591e + "|load key error ++++++++", new Object[0]);
        }
        return f;
    }

    public static byte[] d(byte[] bArr) {
        try {
            return MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(bArr);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        try {
            return CryptTools.decrypt("AES/CFB/NoPadding", new SecretKeySpec(h, "AES"), new IvParameterSpec(bArr2), bArr);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "| sockeId encrypt  http data  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }

    public static int e() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        try {
            byte[] bytes = c.getBytes();
            byte[] bArr3 = new byte[bytes.length];
            for (int i2 = 0; i2 < bytes.length; i2++) {
                bArr3[i2] = (byte) (~(bytes[(bytes.length - i2) - 1] & 255));
            }
            return CryptTools.decrypt("AES/CFB/NoPadding", new SecretKeySpec(c(bArr3), "AES"), new IvParameterSpec(c(bArr2)), bArr);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "| altAesDecSocket  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }

    public static String f() {
        byte[] bArrJ = j();
        byte[] bytes = p.b().getBytes();
        byte[] bArr = new byte[bytes.length + bArrJ.length];
        com.igexin.c.a.b.g.a(bArrJ, bArr, com.igexin.c.a.b.g.a(bytes, bArr, 0, bytes.length), bArrJ.length);
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] f(byte[] bArr, byte[] bArr2) {
        try {
            byte[] bytes = c.getBytes();
            byte[] bArr3 = new byte[bytes.length];
            for (int i2 = 0; i2 < bytes.length; i2++) {
                bArr3[i2] = (byte) (~(bytes[(bytes.length - i2) - 1] & 255));
            }
            return CryptTools.encrypt("AES/CFB/NoPadding", new SecretKeySpec(c(bArr3), "AES"), new IvParameterSpec(c(bArr2)), bArr);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "| altAesEncSocket  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }

    public static byte[] g() {
        try {
            return CryptTools.generateKey("AES", 128).getEncoded();
        } catch (Throwable unused) {
            com.igexin.c.a.c.a.a(f3591e, "generate  http key fail ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "|generate  http key fail ~~~~~~~", new Object[0]);
            return null;
        }
    }

    public static byte[] h() {
        try {
            return CryptTools.generateKey("AES", 128).getEncoded();
        } catch (Throwable unused) {
            com.igexin.c.a.c.a.a(f3591e, "generate  socket key fail ~~~~~~~");
            com.igexin.c.a.c.a.a(f3591e + "|generate  socket key fail ~~~~~~~", new Object[0]);
            return null;
        }
    }

    public static byte[] i() {
        return new byte[0];
    }

    public static byte[] j() {
        try {
            byte[] bArr = new byte[i.length];
            System.arraycopy(i, 0, bArr, 0, i.length);
            return CryptTools.encrypt("RSA/NONE/OAEPWithSHA1AndMGF1Padding", CryptTools.parsePublicKey("RSA", f3590a), bArr);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.a(f3591e + "| getHttpAESKey  fail ~~~~~~~", new Object[0]);
            return new byte[0];
        }
    }
}
