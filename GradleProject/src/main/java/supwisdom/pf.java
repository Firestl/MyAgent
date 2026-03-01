package supwisdom;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class pf {
    public static int a(RecyclerView.y yVar, mf mfVar, View view, View view2, RecyclerView.o oVar, boolean z, boolean z2) {
        if (oVar.getChildCount() == 0 || yVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (yVar.a() - Math.max(oVar.getPosition(view), oVar.getPosition(view2))) - 1) : Math.max(0, Math.min(oVar.getPosition(view), oVar.getPosition(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(mfVar.a(view2) - mfVar.d(view)) / (Math.abs(oVar.getPosition(view) - oVar.getPosition(view2)) + 1))) + (mfVar.f() - mfVar.d(view)));
        }
        return iMax;
    }

    public static int b(RecyclerView.y yVar, mf mfVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.getChildCount() == 0 || yVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return yVar.a();
        }
        return (int) (((mfVar.a(view2) - mfVar.d(view)) / (Math.abs(oVar.getPosition(view) - oVar.getPosition(view2)) + 1)) * yVar.a());
    }

    public static int a(RecyclerView.y yVar, mf mfVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.getChildCount() == 0 || yVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(oVar.getPosition(view) - oVar.getPosition(view2)) + 1;
        }
        return Math.min(mfVar.g(), mfVar.a(view2) - mfVar.d(view));
    }
}
