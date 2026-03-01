package supwisdom;

import android.view.View;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class ml0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f8403a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8404e;

    public ml0(View view) {
        this.f8403a = view;
    }

    public boolean a(int i) {
        if (this.f8404e == i) {
            return false;
        }
        this.f8404e = i;
        d();
        return true;
    }

    public boolean b(int i) {
        if (this.d == i) {
            return false;
        }
        this.d = i;
        d();
        return true;
    }

    public void c() {
        this.b = this.f8403a.getTop();
        this.c = this.f8403a.getLeft();
        d();
    }

    public final void d() {
        View view = this.f8403a;
        lb.f(view, this.d - (view.getTop() - this.b));
        View view2 = this.f8403a;
        lb.e(view2, this.f8404e - (view2.getLeft() - this.c));
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }
}
