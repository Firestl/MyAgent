package supwisdom;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

/* JADX INFO: compiled from: SignatureCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class as {
    public static String a(Context context, String str) {
        Signature[] signatureArrB = b(context, str);
        if (signatureArrB == null || signatureArrB.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Signature signature : signatureArrB) {
            stringBuffer.append(pr.a(signature.toByteArray()));
        }
        return stringBuffer.toString();
    }

    public static Signature[] b(Context context, String str) {
        if (str != null && str.length() != 0 && context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                if (packageInfo == null) {
                    return null;
                }
                return packageInfo.signatures;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }
}
