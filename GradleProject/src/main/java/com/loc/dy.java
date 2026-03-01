package com.loc;

import com.taobao.weex.utils.FunctionParser;
import com.taobao.weex.wson.Wson;
import io.dcloud.common.util.Md5Utils;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: compiled from: Encrypt.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f3761a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};
    public static final byte[] b = {61, 61, 81, 65, 65, 69, 119, 65, 67, 48, 74, UTF8.S_P4A, Wson.STRING_TYPE, Wson.BOOLEAN_TYPE_TRUE, 54, 75, 104, 76, 122, 97, 88, 99, 53, 71, 49, 122, 68, Wson.NUMBER_FLOAT_TYPE, 79, 104, KeyFactorySpi.Ed448_type, KeyFactorySpi.Ed448_type, 65, 97, 76, 54, 65, 66, 87, 53, Wson.NUMBER_BIG_INTEGER_TYPE, 85, 84, KeyFactorySpi.Ed448_type, 71, 68, 69, 76, UTF8.S_P4A, 82, 106, 51, 66, 75, 75, 69, 98, 55, 84, Wson.NUMBER_LONG_TYPE, Wson.STRING_TYPE, 122, 51, 106, 76, 55, 88, 122, Wson.NUMBER_FLOAT_TYPE, 121, 73, 75, 52, 50, 43, Wson.NUMBER_BIG_DECIMAL_TYPE, Wson.NUMBER_FLOAT_TYPE, 121, 56, Wson.NUMBER_INT_TYPE, Wson.STRING_TYPE, Wson.NUMBER_INT_TYPE, 89, 120, 117, KeyFactorySpi.Ed25519_type, 53, 48, 76, 81, Wson.NUMBER_FLOAT_TYPE, 86, Wson.NUMBER_LONG_TYPE, KeyFactorySpi.x25519_type, 73, 65, 66, 74, 65, 83, 119, 65, 119, 83, 68, 65, 81, 66, 66, 69, 81, 65, 78, 99, 118, 104, 73, 90, KeyFactorySpi.x448_type, 75, 74, 89, 81, 68, 119, 119, Wson.NUMBER_FLOAT_TYPE, 77};
    public static final byte[] c = {0, 1, 1, 2, 3, 5, 8, 13, 8, 7, 6, 5, 4, 3, 2, 1};
    public static final IvParameterSpec d = new IvParameterSpec(c);

    public static String a(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return a(Md5Utils.ALGORITHM, a("SHA1", str) + str);
                }
            } catch (Throwable th) {
                ej.a(th, "Encrypt", "generatorKey");
            }
        }
        return null;
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            byte[] bArrA = r.a(str2.getBytes("UTF-8"), str);
            int length = bArrA.length;
            StringBuilder sb = new StringBuilder(length * 2);
            for (int i = 0; i < length; i++) {
                sb.append(f3761a[(bArrA[i] >> 4) & 15]);
                sb.append(f3761a[bArrA[i] & 15]);
            }
            return sb.toString();
        } catch (Throwable th) {
            ej.a(th, "Encrypt", "encode");
            return null;
        }
    }

    public static byte[] a(byte[] bArr) throws Exception {
        PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(dl.a(new StringBuilder(new String(b)).reverse().toString().getBytes())));
        Cipher cipher = Cipher.getInstance(u.c("WUlNBL0VDQi9PQUVQV0lUSFNIQS0xQU5ETUdGMVBBRERJTkc"));
        cipher.init(1, publicKeyGeneratePublic);
        return cipher.doFinal(bArr);
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpecB = b(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(u.c());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpecB, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            ej.a(th, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    public static SecretKeySpec b(String str) {
        byte[] bytes;
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        while (true) {
            stringBuffer.append(str);
            if (stringBuffer.length() >= 16) {
                break;
            }
            str = "0";
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bytes = stringBuffer.toString().getBytes("UTF-8");
        } catch (Throwable th) {
            ej.a(th, "Encrypt", "createKey");
            bytes = null;
        }
        return new SecretKeySpec(bytes, "AES");
    }

    public static byte[] b(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArr.length - 16];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(u.c()));
            return cipher.doFinal(bArr3);
        } catch (Throwable th) {
            ej.a(th, "Encrypt", "decryptRsponse length = " + (bArr != null ? bArr.length : 0));
            return null;
        }
    }

    public static byte[] b(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpecB = b(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(u.c());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpecB, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            ej.a(th, "Encrypt", "aesDecrypt");
            return null;
        }
    }
}
