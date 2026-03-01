package supwisdom;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.Toolbar;
import supwisdom.c2;
import supwisdom.w1;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class q3 implements x2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f8867a;
    public int b;
    public View c;
    public View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f8868e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public ActionMenuPresenter n;
    public int o;
    public int p;
    public Drawable q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q1 f8869a;

        public a() {
            this.f8869a = new q1(q3.this.f8867a.getContext(), 0, R.id.home, 0, 0, q3.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            q3 q3Var = q3.this;
            Window.Callback callback = q3Var.l;
            if (callback == null || !q3Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f8869a);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends rb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8870a = false;
        public final /* synthetic */ int b;

        public b(int i) {
            this.b = i;
        }

        @Override // supwisdom.rb, supwisdom.qb
        public void a(View view) {
            this.f8870a = true;
        }

        @Override // supwisdom.qb
        public void b(View view) {
            if (this.f8870a) {
                return;
            }
            q3.this.f8867a.setVisibility(this.b);
        }

        @Override // supwisdom.rb, supwisdom.qb
        public void c(View view) {
            q3.this.f8867a.setVisibility(0);
        }
    }

    public q3(Toolbar toolbar, boolean z) {
        this(toolbar, z, androidx.appcompat.R.string.abc_action_bar_up_description, androidx.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    public void a(Drawable drawable) {
        this.f = drawable;
        r();
    }

    @Override // supwisdom.x2
    public void a(boolean z) {
    }

    public void b(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.b & 8) != 0) {
            this.f8867a.setSubtitle(charSequence);
        }
    }

    public void c(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f8867a.getNavigationContentDescription())) {
            d(this.p);
        }
    }

    @Override // supwisdom.x2
    public void collapseActionView() {
        this.f8867a.c();
    }

    public final void d(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.b & 8) != 0) {
            this.f8867a.setTitle(charSequence);
        }
    }

    @Override // supwisdom.x2
    public boolean e() {
        return this.f8867a.l();
    }

    @Override // supwisdom.x2
    public boolean f() {
        return this.f8867a.r();
    }

    @Override // supwisdom.x2
    public void g() {
        this.f8867a.d();
    }

    @Override // supwisdom.x2
    public Context getContext() {
        return this.f8867a.getContext();
    }

    @Override // supwisdom.x2
    public CharSequence getTitle() {
        return this.f8867a.getTitle();
    }

    @Override // supwisdom.x2
    public boolean h() {
        return this.f8867a.k();
    }

    @Override // supwisdom.x2
    public Menu i() {
        return this.f8867a.getMenu();
    }

    @Override // supwisdom.x2
    public int j() {
        return this.o;
    }

    @Override // supwisdom.x2
    public ViewGroup k() {
        return this.f8867a;
    }

    @Override // supwisdom.x2
    public int l() {
        return this.b;
    }

    @Override // supwisdom.x2
    public void m() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // supwisdom.x2
    public void n() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public final int o() {
        if (this.f8867a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f8867a.getNavigationIcon();
        return 15;
    }

    public final void p() {
        if ((this.b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f8867a.setNavigationContentDescription(this.p);
            } else {
                this.f8867a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void q() {
        if ((this.b & 4) == 0) {
            this.f8867a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f8867a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void r() {
        Drawable drawable;
        int i = this.b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.f8868e;
        }
        this.f8867a.setLogo(drawable);
    }

    @Override // supwisdom.x2
    public void setIcon(int i) {
        setIcon(i != 0 ? b1.c(getContext(), i) : null);
    }

    @Override // supwisdom.x2
    public void setVisibility(int i) {
        this.f8867a.setVisibility(i);
    }

    @Override // supwisdom.x2
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // supwisdom.x2
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        d(charSequence);
    }

    public q3(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f8867a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        p3 p3VarA = p3.a(toolbar.getContext(), null, androidx.appcompat.R.styleable.ActionBar, androidx.appcompat.R.attr.actionBarStyle, 0);
        this.q = p3VarA.b(androidx.appcompat.R.styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceE = p3VarA.e(androidx.appcompat.R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceE)) {
                c(charSequenceE);
            }
            CharSequence charSequenceE2 = p3VarA.e(androidx.appcompat.R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceE2)) {
                b(charSequenceE2);
            }
            Drawable drawableB = p3VarA.b(androidx.appcompat.R.styleable.ActionBar_logo);
            if (drawableB != null) {
                a(drawableB);
            }
            Drawable drawableB2 = p3VarA.b(androidx.appcompat.R.styleable.ActionBar_icon);
            if (drawableB2 != null) {
                setIcon(drawableB2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                b(drawable);
            }
            a(p3VarA.d(androidx.appcompat.R.styleable.ActionBar_displayOptions, 0));
            int iG = p3VarA.g(androidx.appcompat.R.styleable.ActionBar_customNavigationLayout, 0);
            if (iG != 0) {
                a(LayoutInflater.from(this.f8867a.getContext()).inflate(iG, (ViewGroup) this.f8867a, false));
                a(this.b | 16);
            }
            int iF = p3VarA.f(androidx.appcompat.R.styleable.ActionBar_height, 0);
            if (iF > 0) {
                ViewGroup.LayoutParams layoutParams = this.f8867a.getLayoutParams();
                layoutParams.height = iF;
                this.f8867a.setLayoutParams(layoutParams);
            }
            int iB = p3VarA.b(androidx.appcompat.R.styleable.ActionBar_contentInsetStart, -1);
            int iB2 = p3VarA.b(androidx.appcompat.R.styleable.ActionBar_contentInsetEnd, -1);
            if (iB >= 0 || iB2 >= 0) {
                this.f8867a.a(Math.max(iB, 0), Math.max(iB2, 0));
            }
            int iG2 = p3VarA.g(androidx.appcompat.R.styleable.ActionBar_titleTextStyle, 0);
            if (iG2 != 0) {
                Toolbar toolbar2 = this.f8867a;
                toolbar2.b(toolbar2.getContext(), iG2);
            }
            int iG3 = p3VarA.g(androidx.appcompat.R.styleable.ActionBar_subtitleTextStyle, 0);
            if (iG3 != 0) {
                Toolbar toolbar3 = this.f8867a;
                toolbar3.a(toolbar3.getContext(), iG3);
            }
            int iG4 = p3VarA.g(androidx.appcompat.R.styleable.ActionBar_popupTheme, 0);
            if (iG4 != 0) {
                this.f8867a.setPopupTheme(iG4);
            }
        } else {
            this.b = o();
        }
        p3VarA.b();
        c(i);
        this.k = this.f8867a.getNavigationContentDescription();
        this.f8867a.setNavigationOnClickListener(new a());
    }

    @Override // supwisdom.x2
    public void setIcon(Drawable drawable) {
        this.f8868e = drawable;
        r();
    }

    @Override // supwisdom.x2
    public boolean a() {
        return this.f8867a.n();
    }

    @Override // supwisdom.x2
    public void a(Menu menu, c2.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f8867a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.a(androidx.appcompat.R.id.action_menu_presenter);
        }
        this.n.a(aVar);
        this.f8867a.a((w1) menu, this.n);
    }

    @Override // supwisdom.x2
    public void b(int i) {
        a(i != 0 ? b1.c(getContext(), i) : null);
    }

    @Override // supwisdom.x2
    public boolean d() {
        return this.f8867a.m();
    }

    @Override // supwisdom.x2
    public void b() {
        this.m = true;
    }

    public void c(CharSequence charSequence) {
        this.h = true;
        d(charSequence);
    }

    public void d(int i) {
        a(i == 0 ? null : getContext().getString(i));
    }

    @Override // supwisdom.x2
    public void b(boolean z) {
        this.f8867a.setCollapsible(z);
    }

    public void b(Drawable drawable) {
        this.g = drawable;
        q();
    }

    @Override // supwisdom.x2
    public boolean c() {
        return this.f8867a.b();
    }

    @Override // supwisdom.x2
    public void a(int i) {
        View view;
        int i2 = this.b ^ i;
        this.b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    p();
                }
                q();
            }
            if ((i2 & 3) != 0) {
                r();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f8867a.setTitle(this.i);
                    this.f8867a.setSubtitle(this.j);
                } else {
                    this.f8867a.setTitle((CharSequence) null);
                    this.f8867a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f8867a.addView(view);
            } else {
                this.f8867a.removeView(view);
            }
        }
    }

    @Override // supwisdom.x2
    public void a(i3 i3Var) {
        View view = this.c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f8867a;
            if (parent == toolbar) {
                toolbar.removeView(this.c);
            }
        }
        this.c = i3Var;
        if (i3Var == null || this.o != 2) {
            return;
        }
        this.f8867a.addView(i3Var, 0);
        Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = -2;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = -2;
        layoutParams.f1081a = 8388691;
        i3Var.setAllowCollapse(true);
    }

    public void a(View view) {
        View view2 = this.d;
        if (view2 != null && (this.b & 16) != 0) {
            this.f8867a.removeView(view2);
        }
        this.d = view;
        if (view == null || (this.b & 16) == 0) {
            return;
        }
        this.f8867a.addView(view);
    }

    @Override // supwisdom.x2
    public pb a(int i, long j) {
        pb pbVarA = lb.a(this.f8867a);
        pbVarA.a(i == 0 ? 1.0f : 0.0f);
        pbVarA.a(j);
        pbVarA.a(new b(i));
        return pbVarA;
    }

    public void a(CharSequence charSequence) {
        this.k = charSequence;
        p();
    }

    @Override // supwisdom.x2
    public void a(c2.a aVar, w1.a aVar2) {
        this.f8867a.a(aVar, aVar2);
    }
}
