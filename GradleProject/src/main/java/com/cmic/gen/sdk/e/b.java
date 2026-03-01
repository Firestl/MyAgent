package com.cmic.gen.sdk.e;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesCbcKS;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static byte[] f1727a;

    public static String a(Context context, String str) {
        a();
        byte[] bArrB = b(context);
        if (bArrB != null) {
            return a.a(bArrB, str, f1727a);
        }
        a();
        return null;
    }

    public static void a() {
        k.a("AES_KEY");
    }

    public static boolean a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                keyGenerator.init(new KeyGenParameterSpec.Builder("CMCC_SDK_V1", 3).setDigests("SHA-256", "SHA-512").setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").setRandomizedEncryptionRequired(false).setKeySize(256).build());
                Thread.sleep(1000L);
                keyGenerator.generateKey();
                return true;
            }
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 30);
            if (Build.VERSION.SDK_INT < 18) {
                return false;
            }
            KeyPairGeneratorSpec keyPairGeneratorSpecBuild = new KeyPairGeneratorSpec.Builder(context).setAlias("CMCC_SDK_V1").setSubject(new X500Principal("CN=CMCC_SDK_V1")).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            keyPairGenerator.initialize(keyPairGeneratorSpecBuild);
            Thread.sleep(1000L);
            keyPairGenerator.generateKeyPair();
            return true;
        } catch (Exception e2) {
            c.a("KeystoreUtil", e2.getMessage());
            return false;
        }
    }

    public static boolean a(Context context, boolean z) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.getKey("CMCC_SDK_V1", null) != null) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (z) {
            return a(context);
        }
        return false;
    }

    public static String b() {
        return k.b("AES_KEY", "");
    }

    public static String b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bArrB = b(context);
        if (bArrB != null) {
            return a.b(bArrB, str, f1727a);
        }
        a();
        return null;
    }

    public static synchronized byte[] b(Context context) {
        Cipher cipher;
        String str;
        String str2;
        byte[] bArrDoFinal;
        Cipher cipher2;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (!a(context, false)) {
                return null;
            }
            String strB = b();
            if (TextUtils.isEmpty(strB)) {
                bArrDoFinal = q.a();
                f1727a = q.a();
                Key key = keyStore.getKey("CMCC_SDK_V1", null);
                if (key instanceof SecretKey) {
                    c.b("KeystoreUtil", "随机生成aes秘钥");
                    cipher2 = Cipher.getInstance(AesCbcKS.c);
                    cipher2.init(1, key, new IvParameterSpec(f1727a));
                } else {
                    if (!(key instanceof PrivateKey)) {
                        return null;
                    }
                    PublicKey publicKey = keyStore.getCertificate("CMCC_SDK_V1").getPublicKey();
                    Cipher cipher3 = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
                    c.b("KeystoreUtil", "生成rsa密");
                    cipher3.init(1, publicKey);
                    cipher2 = cipher3;
                }
                String strEncodeToString = Base64.encodeToString(cipher2.doFinal(bArrDoFinal), 0);
                String strEncodeToString2 = Base64.encodeToString(f1727a, 0);
                HashMap map = new HashMap();
                map.put("AES_IV", strEncodeToString2);
                map.put("AES_KEY", strEncodeToString);
                k.a(map);
            } else {
                f1727a = Base64.decode(c(), 0);
                byte[] bArrDecode = Base64.decode(strB, 0);
                Key key2 = keyStore.getKey("CMCC_SDK_V1", null);
                if (key2 == null) {
                    return null;
                }
                if (key2 instanceof SecretKey) {
                    cipher = Cipher.getInstance(AesCbcKS.c);
                    cipher.init(2, key2, new IvParameterSpec(f1727a));
                    str = "KeystoreUtil";
                    str2 = "使用aes";
                } else {
                    if (!(key2 instanceof PrivateKey)) {
                        return null;
                    }
                    cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
                    cipher.init(2, key2);
                    str = "KeystoreUtil";
                    str2 = "使用rsa";
                }
                c.b(str, str2);
                bArrDoFinal = cipher.doFinal(bArrDecode);
                StringBuilder sb = new StringBuilder();
                sb.append("是否解密出秘钥：");
                sb.append(TextUtils.isEmpty(Base64.encodeToString(bArrDoFinal, 0)) ? false : true);
                c.b("KeystoreUtil", sb.toString());
            }
            return bArrDoFinal;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c() {
        return k.b("AES_IV", "");
    }
}
