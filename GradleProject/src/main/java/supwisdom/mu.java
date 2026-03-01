package supwisdom;

import android.graphics.Paint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class mu {
    public static float a(View view) {
        return view.getScaleX();
    }

    public static void b(View view, float f) {
        view.setScaleY(f);
    }

    public static void a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    public static void a(View view, float f) {
        view.setScaleX(f);
    }
}
