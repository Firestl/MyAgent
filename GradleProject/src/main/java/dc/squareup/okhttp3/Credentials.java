package dc.squareup.okhttp3;

import com.xiaomi.mipush.sdk.Constants;
import dc.squareup.okhttp3.internal.Util;
import dc.squareup.okio.ByteString;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public final class Credentials {
    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return "Basic " + ByteString.encodeString(str + Constants.COLON_SEPARATOR + str2, charset).base64();
    }
}
