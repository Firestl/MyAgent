package supwisdom;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class uq {
    public static synchronized void a(Context context, String str, String str2, String str3) {
        if (!lq.a(str)) {
            if (!lq.a(str2) && context != null) {
                try {
                    String strA = kq.a(kq.a(), str3);
                    HashMap map = new HashMap();
                    map.put(str2, strA);
                    vq.a(context, str, map);
                } catch (Throwable unused) {
                }
            }
        }
    }
}
