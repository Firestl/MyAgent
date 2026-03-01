package supwisdom;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import supwisdom.c2;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7005a;
    public final w1 b;
    public final boolean c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7006e;
    public View f;
    public int g;
    public boolean h;
    public c2.a i;
    public a2 j;
    public PopupWindow.OnDismissListener k;
    public final PopupWindow.OnDismissListener l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            b2.this.e();
        }
    }

    public b2(Context context, w1 w1Var, View view, boolean z, int i) {
        this(context, w1Var, view, z, i, 0);
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void b() {
        if (d()) {
            this.j.dismiss();
        }
    }

    public a2 c() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean d() {
        a2 a2Var = this.j;
        return a2Var != null && a2Var.isShowing();
    }

    public void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f() {
        if (!g()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean g() {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public b2(Context context, w1 w1Var, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.f7005a = context;
        this.b = w1Var;
        this.f = view;
        this.c = z;
        this.d = i;
        this.f7006e = i2;
    }

    public void a(View view) {
        this.f = view;
    }

    public void a(boolean z) {
        this.h = z;
        a2 a2Var = this.j;
        if (a2Var != null) {
            a2Var.b(z);
        }
    }

    public void a(int i) {
        this.g = i;
    }

    public boolean a(int i, int i2) {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(i, i2, true, true);
        return true;
    }

    public final a2 a() {
        a2 g2Var;
        Display defaultDisplay = ((WindowManager) this.f7005a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        if (Math.min(point.x, point.y) >= this.f7005a.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
            g2Var = new t1(this.f7005a, this.f, this.d, this.f7006e, this.c);
        } else {
            g2Var = new g2(this.f7005a, this.b, this.f, this.d, this.f7006e, this.c);
        }
        g2Var.a(this.b);
        g2Var.a(this.l);
        g2Var.a(this.f);
        g2Var.a(this.i);
        g2Var.b(this.h);
        g2Var.a(this.g);
        return g2Var;
    }

    public final void a(int i, int i2, boolean z, boolean z2) {
        a2 a2VarC = c();
        a2VarC.c(z2);
        if (z) {
            if ((sa.a(this.g, lb.n(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            a2VarC.b(i);
            a2VarC.c(i2);
            int i3 = (int) ((this.f7005a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            a2VarC.a(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        a2VarC.show();
    }

    public void a(c2.a aVar) {
        this.i = aVar;
        a2 a2Var = this.j;
        if (a2Var != null) {
            a2Var.a(aVar);
        }
    }
}
