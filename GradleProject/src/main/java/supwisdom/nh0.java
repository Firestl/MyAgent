package supwisdom;

import android.content.Context;
import androidx.annotation.RecentlyNonNull;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class nh0 {
    public static boolean a(@RecentlyNonNull Context context, @RecentlyNonNull String str) {
        "com.google.android.gms".equals(str);
        return (wh0.b(context).a(str, 0).flags & PKIFailureInfo.badSenderNonce) != 0;
    }
}
