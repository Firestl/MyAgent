package supwisdom;

import android.content.Context;
import android.content.pm.Signature;
import com.taobao.weex.utils.FunctionParser;
import io.dcloud.common.util.Md5Utils;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ib1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Signature[] f7936a;

    public static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            sb.append(cArr[(bArr[i] >> 4) & 15]);
            sb.append(cArr[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public static String b(Context context) {
        try {
            Signature[] signatureArr = f7936a;
            if (signatureArr == null) {
                signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            }
            return a(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getPublicKey().getEncoded());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(Context context) {
        try {
            String strB = b(context);
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(strB.getBytes());
            return a(messageDigest.digest()).substring(0, 16);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
