package supwisdom;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class gw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7778a = {0, 0, 0, 0, 0, 0, 0, 0};

    public static String a(String str, String str2) {
        return new String(a(f0.a(str), f7778a, f0.a(str2)), "UTF-8");
    }

    public static String a(String str, String str2, String str3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        if ("1".equals(str2)) {
            str = URLDecoder.decode(str, "UTF-8");
        }
        int blockSize = Cipher.getInstance("DESede/CBC/PKCS5Padding").getBlockSize();
        byte[] bArr = new byte[blockSize];
        for (int i = 0; i < blockSize; i++) {
            bArr[i] = 0;
        }
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(f0.a(str3), b0.a("DESede/CBC/PKCS5Padding", "/")), new IvParameterSpec(bArr));
        return new String(cipher.doFinal(f0.a(str)), StandardCharsets.UTF_8);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bArr));
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(2, secretKeyGenerateSecret, new IvParameterSpec(bArr2));
        return cipher.doFinal(bArr3);
    }

    public static String b(String str, String str2) throws UnsupportedEncodingException {
        return f0.a(b(f0.a(str), f7778a, str2.getBytes("utf-8")));
    }

    public static String b(String str, String str2, String str3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        int blockSize = Cipher.getInstance("DESede/CBC/PKCS5Padding").getBlockSize();
        byte[] bArr = new byte[blockSize];
        for (int i = 0; i < blockSize; i++) {
            bArr[i] = 0;
        }
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(f0.a(str3), b0.a("DESede/CBC/PKCS5Padding", "/")), new IvParameterSpec(bArr));
        String strA = f0.a(cipher.doFinal(str.getBytes("utf-8")));
        return "1".equals(str2) ? URLEncoder.encode(strA, "utf-8") : strA;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bArr));
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(1, secretKeyGenerateSecret, new IvParameterSpec(bArr2));
        return cipher.doFinal(bArr3);
    }
}
