package io.dcloud.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class AESUtil {
    public static final String Default_transformation = "AES/CBC/PKCS5Padding";
    public static final String TAG = "AESUtils";

    public static Cipher createCipher(int i, byte[] bArr, String str, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance(str);
        if (bArr2 == null) {
            bArr2 = new byte[cipher.getBlockSize()];
        }
        cipher.init(i, secretKeySpec, new IvParameterSpec(bArr2));
        return cipher;
    }

    public static String decrypt(String str, String str2, byte[] bArr) {
        try {
            return new String(decrypt(str.getBytes(), str2.getBytes(), bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(String str, String str2, String str3) {
        return encrypt(str, str2, str3.getBytes());
    }

    public static byte[] encrypt(String str, String str2, byte[] bArr) {
        try {
            return encrypt(str.getBytes(), bArr, "AES/CBC/PKCS5Padding", str2 != null ? str2.getBytes() : null);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        return createCipher(2, bArr, "AES/CBC/PKCS5Padding", bArr2).doFinal(bArr3);
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) throws Exception {
        return createCipher(1, bArr, str, bArr3).doFinal(bArr2);
    }
}
