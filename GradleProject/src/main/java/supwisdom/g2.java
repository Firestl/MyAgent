package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import supwisdom.c2;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class g2 extends a2 implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, c2, View.OnKeyListener {
    public static final int v = R.layout.abc_popup_menu_item_layout;
    public final Context b;
    public final w1 c;
    public final v1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f7678e;
    public final int f;
    public final int g;
    public final int h;
    public final d3 i;
    public PopupWindow.OnDismissListener l;
    public View m;
    public View n;
    public c2.a o;
    public ViewTreeObserver p;
    public boolean q;
    public boolean r;
    public int s;
    public boolean u;
    public final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    public final View.OnAttachStateChangeListener k = new b();
    public int t = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!g2.this.isShowing() || g2.this.i.k()) {
                return;
            }
            View view = g2.this.n;
            if (view == null || !view.isShown()) {
                g2.this.dismiss();
            } else {
                g2.this.i.show();
            }
        }
    }

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = g2.this.p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    g2.this.p = view.getViewTreeObserver();
                }
                g2 g2Var = g2.this;
                g2Var.p.removeGlobalOnLayoutListener(g2Var.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public g2(Context context, w1 w1Var, View view, int i, int i2, boolean z) {
        this.b = context;
        this.c = w1Var;
        this.f7678e = z;
        this.d = new v1(w1Var, LayoutInflater.from(context), this.f7678e, v);
        this.g = i;
        this.h = i2;
        Resources resources = context.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.m = view;
        this.i = new d3(this.b, null, this.g, this.h);
        w1Var.a(this, context);
    }

    @Override // supwisdom.a2
    public void a(int i) {
        this.t = i;
    }

    @Override // supwisdom.c2
    public void a(Parcelable parcelable) {
    }

    @Override // supwisdom.a2
    public void a(w1 w1Var) {
    }

    @Override // supwisdom.c2
    public boolean a() {
        return false;
    }

    @Override // supwisdom.c2
    public Parcelable b() {
        return null;
    }

    @Override // supwisdom.a2
    public void b(boolean z) {
        this.d.a(z);
    }

    @Override // supwisdom.a2
    public void c(int i) {
        this.i.b(i);
    }

    @Override // supwisdom.f2
    public ListView d() {
        return this.i.d();
    }

    @Override // supwisdom.f2
    public void dismiss() {
        if (isShowing()) {
            this.i.dismiss();
        }
    }

    public final boolean f() {
        View view;
        if (isShowing()) {
            return true;
        }
        if (this.q || (view = this.m) == null) {
            return false;
        }
        this.n = view;
        this.i.a((PopupWindow.OnDismissListener) this);
        this.i.a((AdapterView.OnItemClickListener) this);
        this.i.a(true);
        View view2 = this.n;
        boolean z = this.p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.p = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.j);
        }
        view2.addOnAttachStateChangeListener(this.k);
        this.i.a(view2);
        this.i.f(this.t);
        if (!this.r) {
            this.s = a2.a(this.d, null, this.b, this.f);
            this.r = true;
        }
        this.i.e(this.s);
        this.i.g(2);
        this.i.a(e());
        this.i.show();
        ListView listViewD = this.i.d();
        listViewD.setOnKeyListener(this);
        if (this.u && this.c.h() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.b).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewD, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            if (textView != null) {
                textView.setText(this.c.h());
            }
            frameLayout.setEnabled(false);
            listViewD.addHeaderView(frameLayout, null, false);
        }
        this.i.a((ListAdapter) this.d);
        this.i.show();
        return true;
    }

    @Override // supwisdom.f2
    public boolean isShowing() {
        return !this.q && this.i.isShowing();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.c.close();
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // supwisdom.f2
    public void show() {
        if (!f()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // supwisdom.c2
    public void a(boolean z) {
        this.r = false;
        v1 v1Var = this.d;
        if (v1Var != null) {
            v1Var.notifyDataSetChanged();
        }
    }

    @Override // supwisdom.a2
    public void b(int i) {
        this.i.a(i);
    }

    @Override // supwisdom.a2
    public void c(boolean z) {
        this.u = z;
    }

    @Override // supwisdom.c2
    public void a(c2.a aVar) {
        this.o = aVar;
    }

    @Override // supwisdom.c2
    public boolean a(h2 h2Var) {
        if (h2Var.hasVisibleItems()) {
            b2 b2Var = new b2(this.b, h2Var, this.n, this.f7678e, this.g, this.h);
            b2Var.a(this.o);
            b2Var.a(a2.b(h2Var));
            b2Var.a(this.l);
            this.l = null;
            this.c.a(false);
            int iA = this.i.a();
            int iE = this.i.e();
            if ((Gravity.getAbsoluteGravity(this.t, lb.n(this.m)) & 7) == 5) {
                iA += this.m.getWidth();
            }
            if (b2Var.a(iA, iE)) {
                c2.a aVar = this.o;
                if (aVar == null) {
                    return true;
                }
                aVar.a(h2Var);
                return true;
            }
        }
        return false;
    }

    @Override // supwisdom.c2
    public void a(w1 w1Var, boolean z) {
        if (w1Var != this.c) {
            return;
        }
        dismiss();
        c2.a aVar = this.o;
        if (aVar != null) {
            aVar.a(w1Var, z);
        }
    }

    @Override // supwisdom.a2
    public void a(View view) {
        this.m = view;
    }

    @Override // supwisdom.a2
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }
}
