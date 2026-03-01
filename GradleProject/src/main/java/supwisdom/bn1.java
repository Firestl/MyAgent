package supwisdom;

import android.util.Base64;
import com.baidu.speech.utils.cuid.util.DeviceId;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: compiled from: RSAUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class bn1 {
    static {
        "#PART#".getBytes();
    }

    public static KeyPair a(int i) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(i);
            return keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2) {
        try {
            if (str.startsWith("-----BEGIN PUBLIC KEY-----")) {
                str = str.replaceAll("-----BEGIN PUBLIC KEY-----", "");
            }
            if (str.endsWith("-----END PUBLIC KEY-----")) {
                str = str.replaceAll("-----END PUBLIC KEY-----", "");
            }
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.replaceAll("\n", ""), 0)));
            Cipher cipher = Cipher.getInstance(DeviceId.RSA_ALGORITHM);
            cipher.init(1, rSAPublicKey);
            return Base64.encodeToString(cipher.doFinal(str2.getBytes("UTF-8")), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(String str, RSAPrivateKey rSAPrivateKey) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(rSAPrivateKey);
            signature.update(str.getBytes("UTF-8"));
            return Base64.encodeToString(signature.sign(), 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
