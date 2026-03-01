package supwisdom;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class cm0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7230a;
    public boolean b = false;
    public int c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public cm0(bm0 bm0Var) {
        this.f7230a = (View) bm0Var;
    }

    public void a(Bundle bundle) {
        this.b = bundle.getBoolean("expanded", false);
        this.c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.b) {
            a();
        }
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.b;
    }

    public Bundle d() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.b);
        bundle.putInt("expandedComponentIdHint", this.c);
        return bundle;
    }

    public void a(int i) {
        this.c = i;
    }

    public final void a() {
        ViewParent parent = this.f7230a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).a(this.f7230a);
        }
    }
}
