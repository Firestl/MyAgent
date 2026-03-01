package supwisdom;

import android.content.Context;
import supwisdom.bw1;

/* JADX INFO: compiled from: OpenIDSDK.java */
/* JADX INFO: loaded from: classes3.dex */
public class cw1 {
    public static void a(Context context) {
        aw1.b = bw1.b.f7118a.a(context.getApplicationContext());
        aw1.f6990a = true;
    }

    public static String b(Context context) {
        if (aw1.f6990a) {
            return bw1.b.f7118a.a(context.getApplicationContext(), "OUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }

    public static boolean a() {
        if (aw1.f6990a) {
            return aw1.b;
        }
        throw new RuntimeException("SDK Need Init First!");
    }
}
