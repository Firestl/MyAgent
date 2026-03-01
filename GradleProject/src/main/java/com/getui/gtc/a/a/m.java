package com.getui.gtc.a.a;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2106a = "RSA";
    public static String b = "RSA/NONE/OAEPWithSHA1AndMGF1Padding";

    public static PublicKey a(byte[] bArr) {
        try {
            return KeyFactory.getInstance(f2106a).generatePublic(new X509EncodedKeySpec(bArr));
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return null;
        }
    }

    public static byte[] a(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(b);
            cipher.init(1, publicKey);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
