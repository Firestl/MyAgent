package supwisdom;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class sq {
    public static String a(String str) {
        String strA;
        try {
            strA = wq.a(str);
        } catch (Throwable unused) {
            strA = "";
        }
        if (!lq.a(strA)) {
            return strA;
        }
        return tq.a(".SystemConfig" + File.separator + str);
    }
}
