package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import okio.ByteString;

/* JADX INFO: compiled from: Credentials.java */
/* JADX INFO: loaded from: classes2.dex */
public final class le1 {
    public static String a(String str, String str2) {
        try {
            return "Basic " + ByteString.of((str + Constants.COLON_SEPARATOR + str2).getBytes("ISO-8859-1")).base64();
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
