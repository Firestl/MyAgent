package supwisdom;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class rq {
    public static String a(Context context, String str, String str2) {
        synchronized (rq.class) {
            String strB = null;
            if (context != null) {
                if (!lq.a(str) && !lq.a(str2)) {
                    try {
                        String strA = vq.a(context, str, str2, "");
                        if (lq.a(strA)) {
                            return null;
                        }
                        strB = kq.b(kq.a(), strA);
                    } catch (Throwable unused) {
                    }
                    return strB;
                }
            }
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        synchronized (rq.class) {
            if (lq.a(str) || lq.a(str2) || context == null) {
                return;
            }
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
