package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/* JADX INFO: compiled from: DpToPixelUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class tn {
    public static DisplayMetrics a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static float a(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int a(float f) {
        return (int) Math.ceil(f * Resources.getSystem().getDisplayMetrics().density);
    }
}
