package supwisdom;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: supwisdom.if, reason: invalid class name */
/* JADX INFO: compiled from: ItemTouchUIUtilImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class Cif implements hf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final hf f7952a = new Cif();

    public static float a(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != view) {
                float fJ = lb.j(childAt);
                if (fJ > f) {
                    f = fJ;
                }
            }
        }
        return f;
    }

    @Override // supwisdom.hf
    public void a(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
    }

    @Override // supwisdom.hf
    public void b(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21 && z && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
            Float fValueOf = Float.valueOf(lb.j(view));
            lb.a(view, a(recyclerView, view) + 1.0f);
            view.setTag(R.id.item_touch_helper_previous_elevation, fValueOf);
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }

    @Override // supwisdom.hf
    public void b(View view) {
    }

    @Override // supwisdom.hf
    public void a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
            if (tag != null && (tag instanceof Float)) {
                lb.a(view, ((Float) tag).floatValue());
            }
            view.setTag(R.id.item_touch_helper_previous_elevation, null);
        }
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }
}
