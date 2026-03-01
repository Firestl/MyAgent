package supwisdom;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class fb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7582a;
    public int b;

    public fb(ViewGroup viewGroup) {
    }

    public void a(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    public void a(View view, View view2, int i, int i2) {
        if (i2 == 1) {
            this.b = i;
        } else {
            this.f7582a = i;
        }
    }

    public int a() {
        return this.f7582a | this.b;
    }

    public void a(View view) {
        a(view, 0);
    }

    public void a(View view, int i) {
        if (i == 1) {
            this.b = 0;
        } else {
            this.f7582a = 0;
        }
    }
}
