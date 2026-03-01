package supwisdom;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: compiled from: GrayManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class wm1 {
    public static wm1 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f9646a;
    public ColorMatrix b;

    public static wm1 b() {
        if (c == null) {
            synchronized (wm1.class) {
                if (c == null) {
                    c = new wm1();
                }
            }
        }
        return c;
    }

    public void a() {
        this.b = new ColorMatrix();
        this.f9646a = new Paint();
        this.b.setSaturation(0.0f);
        this.f9646a.setColorFilter(new ColorMatrixColorFilter(this.b));
    }

    public void a(View view) {
        if (this.b == null || this.f9646a == null) {
            a();
        }
        view.setLayerType(2, this.f9646a);
    }
}
