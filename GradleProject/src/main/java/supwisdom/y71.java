package supwisdom;

import android.R;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class y71 extends StateListDrawable {
    public y71(int i, int i2, int i3, int i4, int i5) {
        a(i, -1381654, i2, i3, i4, i5);
    }

    public final void a(int i, int i2, int i3, int i4, int i5, int i6) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(s71.a(i3, i4, i5, i6));
        shapeDrawable.getPaint().setColor(i2);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(s71.a(i3, i4, i5, i6));
        shapeDrawable2.getPaint().setColor(i);
        addState(new int[]{R.attr.state_pressed}, shapeDrawable);
        addState(new int[]{-16842919}, shapeDrawable2);
    }
}
