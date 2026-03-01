package supwisdom;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import supwisdom.i1;
import supwisdom.w1;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class a1 extends ActionBar implements ActionBarOverlayLayout.d {
    public static final Interpolator C = new AccelerateInterpolator();
    public static final Interpolator D = new DecelerateInterpolator();
    public final qb A;
    public final sb B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6839a;
    public Context b;
    public Activity c;
    public ActionBarOverlayLayout d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionBarContainer f6840e;
    public x2 f;
    public ActionBarContextView g;
    public View h;
    public i3 i;
    public boolean j;
    public d k;
    public i1 l;
    public i1.a m;
    public boolean n;
    public ArrayList<ActionBar.a> o;
    public boolean p;
    public int q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public o1 w;
    public boolean x;
    public boolean y;
    public final qb z;

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class a extends rb {
        public a() {
        }

        @Override // supwisdom.qb
        public void b(View view) {
            View view2;
            a1 a1Var = a1.this;
            if (a1Var.r && (view2 = a1Var.h) != null) {
                view2.setTranslationY(0.0f);
                a1.this.f6840e.setTranslationY(0.0f);
            }
            a1.this.f6840e.setVisibility(8);
            a1.this.f6840e.setTransitioning(false);
            a1 a1Var2 = a1.this;
            a1Var2.w = null;
            a1Var2.n();
            ActionBarOverlayLayout actionBarOverlayLayout = a1.this.d;
            if (actionBarOverlayLayout != null) {
                lb.R(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends rb {
        public b() {
        }

        @Override // supwisdom.qb
        public void b(View view) {
            a1 a1Var = a1.this;
            a1Var.w = null;
            a1Var.f6840e.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements sb {
        public c() {
        }

        @Override // supwisdom.sb
        public void a(View view) {
            ((View) a1.this.f6840e.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends i1 implements w1.a {
        public final Context c;
        public final w1 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public i1.a f6844e;
        public WeakReference<View> f;

        public d(Context context, i1.a aVar) {
            this.c = context;
            this.f6844e = aVar;
            w1 w1Var = new w1(context);
            w1Var.c(1);
            this.d = w1Var;
            w1Var.a(this);
        }

        @Override // supwisdom.i1
        public void a() {
            a1 a1Var = a1.this;
            if (a1Var.k != this) {
                return;
            }
            if (a1.a(a1Var.s, a1Var.t, false)) {
                this.f6844e.a(this);
            } else {
                a1 a1Var2 = a1.this;
                a1Var2.l = this;
                a1Var2.m = this.f6844e;
            }
            this.f6844e = null;
            a1.this.e(false);
            a1.this.g.a();
            a1.this.f.k().sendAccessibilityEvent(32);
            a1 a1Var3 = a1.this;
            a1Var3.d.setHideOnContentScrollEnabled(a1Var3.y);
            a1.this.k = null;
        }

        @Override // supwisdom.i1
        public void b(CharSequence charSequence) {
            a1.this.g.setTitle(charSequence);
        }

        @Override // supwisdom.i1
        public Menu c() {
            return this.d;
        }

        @Override // supwisdom.i1
        public MenuInflater d() {
            return new n1(this.c);
        }

        @Override // supwisdom.i1
        public CharSequence e() {
            return a1.this.g.getSubtitle();
        }

        @Override // supwisdom.i1
        public CharSequence g() {
            return a1.this.g.getTitle();
        }

        @Override // supwisdom.i1
        public void i() {
            if (a1.this.k != this) {
                return;
            }
            this.d.s();
            try {
                this.f6844e.b(this, this.d);
            } finally {
                this.d.r();
            }
        }

        @Override // supwisdom.i1
        public boolean j() {
            return a1.this.g.c();
        }

        public boolean k() {
            this.d.s();
            try {
                return this.f6844e.a(this, this.d);
            } finally {
                this.d.r();
            }
        }

        @Override // supwisdom.i1
        public void b(int i) {
            b(a1.this.f6839a.getResources().getString(i));
        }

        @Override // supwisdom.i1
        public View b() {
            WeakReference<View> weakReference = this.f;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // supwisdom.i1
        public void a(View view) {
            a1.this.g.setCustomView(view);
            this.f = new WeakReference<>(view);
        }

        @Override // supwisdom.i1
        public void a(CharSequence charSequence) {
            a1.this.g.setSubtitle(charSequence);
        }

        @Override // supwisdom.i1
        public void a(int i) {
            a((CharSequence) a1.this.f6839a.getResources().getString(i));
        }

        @Override // supwisdom.i1
        public void a(boolean z) {
            super.a(z);
            a1.this.g.setTitleOptional(z);
        }

        @Override // supwisdom.w1.a
        public boolean a(w1 w1Var, MenuItem menuItem) {
            i1.a aVar = this.f6844e;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        @Override // supwisdom.w1.a
        public void a(w1 w1Var) {
            if (this.f6844e == null) {
                return;
            }
            i();
            a1.this.g.e();
        }
    }

    public a1(Activity activity, boolean z) {
        new ArrayList();
        this.o = new ArrayList<>();
        this.q = 0;
        this.r = true;
        this.v = true;
        this.z = new a();
        this.A = new b();
        this.B = new c();
        this.c = activity;
        View decorView = activity.getWindow().getDecorView();
        b(decorView);
        if (z) {
            return;
        }
        this.h = decorView.findViewById(R.id.content);
    }

    public static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final x2 a(View view) {
        if (view instanceof x2) {
            return (x2) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : com.igexin.push.core.b.m);
        throw new IllegalStateException(sb.toString());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
    }

    public final void b(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(androidx.appcompat.R.id.decor_content_parent);
        this.d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f = a(view.findViewById(androidx.appcompat.R.id.action_bar));
        this.g = (ActionBarContextView) view.findViewById(androidx.appcompat.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(androidx.appcompat.R.id.action_bar_container);
        this.f6840e = actionBarContainer;
        x2 x2Var = this.f;
        if (x2Var == null || this.g == null || actionBarContainer == null) {
            throw new IllegalStateException(a1.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f6839a = x2Var.getContext();
        boolean z = (this.f.l() & 4) != 0;
        if (z) {
            this.j = true;
        }
        h1 h1VarA = h1.a(this.f6839a);
        k(h1VarA.a() || z);
        i(h1VarA.f());
        TypedArray typedArrayObtainStyledAttributes = this.f6839a.obtainStyledAttributes(null, androidx.appcompat.R.styleable.ActionBar, androidx.appcompat.R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.ActionBar_hideOnContentScroll, false)) {
            j(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
        if (this.t) {
            return;
        }
        this.t = true;
        l(true);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void d(boolean z) {
        o1 o1Var;
        this.x = z;
        if (z || (o1Var = this.w) == null) {
            return;
        }
        o1Var.a();
    }

    public void e(boolean z) {
        pb pbVarA;
        pb pbVarA2;
        if (z) {
            r();
        } else {
            p();
        }
        if (!q()) {
            if (z) {
                this.f.setVisibility(4);
                this.g.setVisibility(0);
                return;
            } else {
                this.f.setVisibility(0);
                this.g.setVisibility(8);
                return;
            }
        }
        if (z) {
            pbVarA2 = this.f.a(4, 100L);
            pbVarA = this.g.a(0, 200L);
        } else {
            pbVarA = this.f.a(0, 200L);
            pbVarA2 = this.g.a(8, 100L);
        }
        o1 o1Var = new o1();
        o1Var.a(pbVarA2, pbVarA);
        o1Var.c();
    }

    public void f(boolean z) {
        View view;
        o1 o1Var = this.w;
        if (o1Var != null) {
            o1Var.a();
        }
        if (this.q != 0 || (!this.x && !z)) {
            this.z.b(null);
            return;
        }
        this.f6840e.setAlpha(1.0f);
        this.f6840e.setTransitioning(true);
        o1 o1Var2 = new o1();
        float f = -this.f6840e.getHeight();
        if (z) {
            this.f6840e.getLocationInWindow(new int[]{0, 0});
            f -= r5[1];
        }
        pb pbVarA = lb.a(this.f6840e);
        pbVarA.b(f);
        pbVarA.a(this.B);
        o1Var2.a(pbVarA);
        if (this.r && (view = this.h) != null) {
            pb pbVarA2 = lb.a(view);
            pbVarA2.b(f);
            o1Var2.a(pbVarA2);
        }
        o1Var2.a(C);
        o1Var2.a(250L);
        o1Var2.a(this.z);
        this.w = o1Var2;
        o1Var2.c();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int g() {
        return this.f.l();
    }

    public void h(boolean z) {
        a(z ? 4 : 0, 4);
    }

    public final void i(boolean z) {
        this.p = z;
        if (z) {
            this.f6840e.setTabContainer(null);
            this.f.a(this.i);
        } else {
            this.f.a((i3) null);
            this.f6840e.setTabContainer(this.i);
        }
        boolean z2 = o() == 2;
        i3 i3Var = this.i;
        if (i3Var != null) {
            if (z2) {
                i3Var.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.d;
                if (actionBarOverlayLayout != null) {
                    lb.R(actionBarOverlayLayout);
                }
            } else {
                i3Var.setVisibility(8);
            }
        }
        this.f.b(!this.p && z2);
        this.d.setHasNonEmbeddedTabs(!this.p && z2);
    }

    public void j(boolean z) {
        if (z && !this.d.j()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.y = z;
        this.d.setHideOnContentScrollEnabled(z);
    }

    public void k(boolean z) {
        this.f.a(z);
    }

    public final void l(boolean z) {
        if (a(this.s, this.t, this.u)) {
            if (this.v) {
                return;
            }
            this.v = true;
            g(z);
            return;
        }
        if (this.v) {
            this.v = false;
            f(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void m() {
        if (this.s) {
            this.s = false;
            l(false);
        }
    }

    public void n() {
        i1.a aVar = this.m;
        if (aVar != null) {
            aVar.a(this.l);
            this.l = null;
            this.m = null;
        }
    }

    public int o() {
        return this.f.j();
    }

    public final void p() {
        if (this.u) {
            this.u = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            l(false);
        }
    }

    public final boolean q() {
        return lb.M(this.f6840e);
    }

    public final void r() {
        if (this.u) {
            return;
        }
        this.u = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        l(false);
    }

    public void g(boolean z) {
        View view;
        View view2;
        o1 o1Var = this.w;
        if (o1Var != null) {
            o1Var.a();
        }
        this.f6840e.setVisibility(0);
        if (this.q == 0 && (this.x || z)) {
            this.f6840e.setTranslationY(0.0f);
            float f = -this.f6840e.getHeight();
            if (z) {
                this.f6840e.getLocationInWindow(new int[]{0, 0});
                f -= r5[1];
            }
            this.f6840e.setTranslationY(f);
            o1 o1Var2 = new o1();
            pb pbVarA = lb.a(this.f6840e);
            pbVarA.b(0.0f);
            pbVarA.a(this.B);
            o1Var2.a(pbVarA);
            if (this.r && (view2 = this.h) != null) {
                view2.setTranslationY(f);
                pb pbVarA2 = lb.a(this.h);
                pbVarA2.b(0.0f);
                o1Var2.a(pbVarA2);
            }
            o1Var2.a(D);
            o1Var2.a(250L);
            o1Var2.a(this.A);
            this.w = o1Var2;
            o1Var2.c();
        } else {
            this.f6840e.setAlpha(1.0f);
            this.f6840e.setTranslationY(0.0f);
            if (this.r && (view = this.h) != null) {
                view.setTranslationY(0.0f);
            }
            this.A.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.d;
        if (actionBarOverlayLayout != null) {
            lb.R(actionBarOverlayLayout);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context h() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.f6839a.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.b = new ContextThemeWrapper(this.f6839a, i);
            } else {
                this.b = this.f6839a;
            }
        }
        return this.b;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void c(boolean z) {
        if (this.j) {
            return;
        }
        h(z);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
        o1 o1Var = this.w;
        if (o1Var != null) {
            o1Var.a();
            this.w = null;
        }
    }

    public void a(float f) {
        lb.a(this.f6840e, f);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(Configuration configuration) {
        i(h1.a(this.f6839a).f());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(int i) {
        this.q = i;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(CharSequence charSequence) {
        this.f.setWindowTitle(charSequence);
    }

    public void a(int i, int i2) {
        int iL = this.f.l();
        if ((i2 & 4) != 0) {
            this.j = true;
        }
        this.f.a((i & i2) | ((~i2) & iL));
    }

    @Override // androidx.appcompat.app.ActionBar
    public i1 a(i1.a aVar) {
        d dVar = this.k;
        if (dVar != null) {
            dVar.a();
        }
        this.d.setHideOnContentScrollEnabled(false);
        this.g.d();
        d dVar2 = new d(this.g.getContext(), aVar);
        if (!dVar2.k()) {
            return null;
        }
        this.k = dVar2;
        dVar2.i();
        this.g.a(dVar2);
        e(true);
        this.g.sendAccessibilityEvent(32);
        return dVar2;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void i() {
        if (this.s) {
            return;
        }
        this.s = true;
        l(false);
    }

    public a1(Dialog dialog) {
        new ArrayList();
        this.o = new ArrayList<>();
        this.q = 0;
        this.r = true;
        this.v = true;
        this.z = new a();
        this.A = new b();
        this.B = new c();
        b(dialog.getWindow().getDecorView());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void b(boolean z) {
        if (z == this.n) {
            return;
        }
        this.n = z;
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            this.o.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean f() {
        x2 x2Var = this.f;
        if (x2Var == null || !x2Var.h()) {
            return false;
        }
        this.f.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z) {
        this.r = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.t) {
            this.t = false;
            l(true);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a(int i, KeyEvent keyEvent) {
        Menu menuC;
        d dVar = this.k;
        if (dVar == null || (menuC = dVar.c()) == null) {
            return false;
        }
        menuC.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuC.performShortcut(i, keyEvent, 0);
    }
}
