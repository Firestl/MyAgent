package supwisdom;

import android.text.TextUtils;
import com.google.zxing.common.StringUtils;
import java.nio.charset.Charset;
import org.mozilla.universalchardet.UniversalDetector;

/* JADX INFO: loaded from: classes.dex */
public final class nw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f8584a;
    public static final boolean b;

    static {
        String strName = Charset.defaultCharset().name();
        f8584a = strName;
        b = StringUtils.SHIFT_JIS.equalsIgnoreCase(strName) || StringUtils.EUC_JP.equalsIgnoreCase(strName);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x008d A[PHI: r10
  0x008d: PHI (r10v6 int) = (r10v1 int), (r10v5 int), (r10v1 int) binds: [B:37:0x006f, B:46:0x0088, B:32:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(byte[] r19, java.util.Map<com.dcloud.zxing2.DecodeHintType, ?> r20) {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.nw.a(byte[], java.util.Map):java.lang.String");
    }

    public static String a(byte[] bArr) {
        UniversalDetector universalDetector = new UniversalDetector();
        universalDetector.a(bArr);
        universalDetector.a();
        String strB = universalDetector.b();
        universalDetector.c();
        return !TextUtils.isEmpty(strB) ? strB : StringUtils.GB2312;
    }
}
