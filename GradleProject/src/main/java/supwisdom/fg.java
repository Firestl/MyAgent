package supwisdom;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: GhostViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class fg {
    public static eg a(View view, ViewGroup viewGroup, Matrix matrix) {
        return Build.VERSION.SDK_INT >= 21 ? dg.a(view, viewGroup, matrix) : cg.a(view, viewGroup);
    }

    public static void a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            dg.a(view);
        } else {
            cg.b(view);
        }
    }
}
