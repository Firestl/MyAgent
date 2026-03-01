package supwisdom;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class pu {
    public static boolean a(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static void b(View view) {
        view.stopNestedScroll();
    }

    public static void a(View view, float f) {
        view.setElevation(f);
    }
}
