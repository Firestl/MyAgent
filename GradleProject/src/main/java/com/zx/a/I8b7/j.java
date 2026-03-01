package com.zx.a.I8b7;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.hash.HMACSHA256;
import com.zx.module.annotation.Java2C;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SecureRandom f6232a = new SecureRandom();

    @Java2C.Method2C
    public static native String a(String str, boolean z) throws NoSuchAlgorithmException;

    public static byte[] a(String str, SecretKey secretKey, IvParameterSpec ivParameterSpec, byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(str);
        cipher.init(2, secretKey, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static byte[] b(String str, SecretKey secretKey, IvParameterSpec ivParameterSpec, byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(str);
        cipher.init(1, secretKey, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static String a(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(bArr);
        return a(messageDigest.digest());
    }

    public static SecretKey b(byte[] bArr, String str) {
        try {
            Mac mac = Mac.getInstance(HMACSHA256.b);
            mac.init(new SecretKeySpec(str.getBytes(StandardCharsets.UTF_8), HMACSHA256.b));
            return new SecretKeySpec(mac.doFinal(bArr), "AES");
        } catch (Exception e2) {
            y1.a(e2);
            return null;
        }
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return a(str2, str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e2) {
            y1.a(e2);
            return "";
        }
    }

    public static byte[] a(String str, SecretKey secretKey, String str2) throws Exception {
        byte[] bArr = new byte[12];
        f6232a.nextBytes(bArr);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(1, secretKey, new GCMParameterSpec(128, bArr));
        cipher.updateAAD(str2.getBytes(StandardCharsets.UTF_8));
        byte[] bArrDoFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrDoFinal.length + 12);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.put(bArrDoFinal);
        return byteBufferAllocate.array();
    }

    public static String a(byte[] bArr, SecretKey secretKey, String str) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(2, secretKey, new GCMParameterSpec(128, bArr, 0, 12));
        cipher.updateAAD(str.getBytes(StandardCharsets.UTF_8));
        return new String(cipher.doFinal(bArr, 12, bArr.length - 12), StandardCharsets.UTF_8);
    }

    public static SecretKey a(byte[] bArr, String str) {
        try {
            Mac mac = Mac.getInstance(HMACSHA256.b);
            mac.init(new SecretKeySpec(str.getBytes(StandardCharsets.UTF_8), HMACSHA256.b));
            byte[] bArr2 = new byte[16];
            System.arraycopy(mac.doFinal(bArr), 0, bArr2, 0, 16);
            return new SecretKeySpec(bArr2, "AES");
        } catch (Exception e2) {
            y1.a(e2);
            return null;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i];
            if (i2 < 0) {
                i2 += 256;
            }
            if (i2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i2));
        }
        return sb.toString();
    }
}
