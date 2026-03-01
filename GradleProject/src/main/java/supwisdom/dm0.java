package supwisdom;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.material.internal.VisibilityAwareImageButton;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class dm0 {
    public static final TimeInterpolator B = cl0.c;
    public static final int[] C = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] D = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    public static final int[] E = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] F = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] G = {R.attr.state_enabled};
    public static final int[] H = new int[0];
    public ViewTreeObserver.OnPreDrawListener A;
    public Animator b;
    public jl0 c;
    public jl0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public jl0 f7357e;
    public jl0 f;
    public final nm0 g;
    public wm0 h;
    public float i;
    public Drawable j;
    public Drawable k;
    public fm0 l;
    public Drawable m;
    public float n;
    public float o;
    public float p;
    public int q;
    public ArrayList<Animator.AnimatorListener> s;
    public ArrayList<Animator.AnimatorListener> t;
    public final VisibilityAwareImageButton u;
    public final xm0 v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7356a = 0;
    public float r = 1.0f;
    public final Rect w = new Rect();
    public final RectF x = new RectF();
    public final RectF y = new RectF();
    public final Matrix z = new Matrix();

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7358a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ g c;

        public a(boolean z, g gVar) {
            this.b = z;
            this.c = gVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f7358a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            dm0 dm0Var = dm0.this;
            dm0Var.f7356a = 0;
            dm0Var.b = null;
            if (this.f7358a) {
                return;
            }
            dm0Var.u.a(this.b ? 8 : 4, this.b);
            g gVar = this.c;
            if (gVar != null) {
                gVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            dm0.this.u.a(0, this.b);
            dm0 dm0Var = dm0.this;
            dm0Var.f7356a = 1;
            dm0Var.b = animator;
            this.f7358a = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f7359a;
        public final /* synthetic */ g b;

        public b(boolean z, g gVar) {
            this.f7359a = z;
            this.b = gVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            dm0 dm0Var = dm0.this;
            dm0Var.f7356a = 0;
            dm0Var.b = null;
            g gVar = this.b;
            if (gVar != null) {
                gVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            dm0.this.u.a(0, this.f7359a);
            dm0 dm0Var = dm0.this;
            dm0Var.f7356a = 2;
            dm0Var.b = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class c implements ViewTreeObserver.OnPreDrawListener {
        public c() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            dm0.this.s();
            return true;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class d extends i {
        public d(dm0 dm0Var) {
            super(dm0Var, null);
        }

        @Override // supwisdom.dm0.i
        public float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class e extends i {
        public e() {
            super(dm0.this, null);
        }

        @Override // supwisdom.dm0.i
        public float a() {
            dm0 dm0Var = dm0.this;
            return dm0Var.n + dm0Var.o;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class f extends i {
        public f() {
            super(dm0.this, null);
        }

        @Override // supwisdom.dm0.i
        public float a() {
            dm0 dm0Var = dm0.this;
            return dm0Var.n + dm0Var.p;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public interface g {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class h extends i {
        public h() {
            super(dm0.this, null);
        }

        @Override // supwisdom.dm0.i
        public float a() {
            return dm0.this.n;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public abstract class i extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7364a;
        public float b;
        public float c;

        public i() {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            dm0.this.h.b(this.c);
            this.f7364a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f7364a) {
                this.b = dm0.this.h.c();
                this.c = a();
                this.f7364a = true;
            }
            wm0 wm0Var = dm0.this.h;
            float f = this.b;
            wm0Var.b(f + ((this.c - f) * valueAnimator.getAnimatedFraction()));
        }

        public /* synthetic */ i(dm0 dm0Var, a aVar) {
            this();
        }
    }

    public dm0(VisibilityAwareImageButton visibilityAwareImageButton, xm0 xm0Var) {
        this.u = visibilityAwareImageButton;
        this.v = xm0Var;
        nm0 nm0Var = new nm0();
        this.g = nm0Var;
        nm0Var.a(C, a((i) new f()));
        this.g.a(D, a((i) new e()));
        this.g.a(E, a((i) new e()));
        this.g.a(F, a((i) new e()));
        this.g.a(G, a((i) new h()));
        this.g.a(H, a((i) new d(this)));
        this.i = this.u.getRotation();
    }

    public void a(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        Drawable[] drawableArr;
        Drawable drawableI = y8.i(a());
        this.j = drawableI;
        y8.a(drawableI, colorStateList);
        if (mode != null) {
            y8.a(this.j, mode);
        }
        Drawable drawableI2 = y8.i(a());
        this.k = drawableI2;
        y8.a(drawableI2, vm0.a(colorStateList2));
        if (i2 > 0) {
            fm0 fm0VarA = a(i2, colorStateList);
            this.l = fm0VarA;
            drawableArr = new Drawable[]{fm0VarA, this.j, this.k};
        } else {
            this.l = null;
            drawableArr = new Drawable[]{this.j, this.k};
        }
        this.m = new LayerDrawable(drawableArr);
        Context context = this.u.getContext();
        Drawable drawable = this.m;
        float fB = this.v.b();
        float f2 = this.n;
        wm0 wm0Var = new wm0(context, drawable, fB, f2, f2 + this.p);
        this.h = wm0Var;
        wm0Var.a(false);
        this.v.a(this.h);
    }

    public void b(ColorStateList colorStateList) {
        Drawable drawable = this.k;
        if (drawable != null) {
            y8.a(drawable, vm0.a(colorStateList));
        }
    }

    public void b(Rect rect) {
    }

    public final void c(float f2) {
        this.r = f2;
        Matrix matrix = this.z;
        a(f2, matrix);
        this.u.setImageMatrix(matrix);
    }

    public final void d(float f2) {
        if (this.p != f2) {
            this.p = f2;
            a(this.n, this.o, f2);
        }
    }

    public final jl0 e() {
        if (this.f7357e == null) {
            this.f7357e = jl0.a(this.u.getContext(), com.google.android.material.R.animator.design_fab_show_motion_spec);
        }
        return this.f7357e;
    }

    public float f() {
        return this.n;
    }

    public final jl0 g() {
        return this.d;
    }

    public float h() {
        return this.o;
    }

    public float i() {
        return this.p;
    }

    public final jl0 j() {
        return this.c;
    }

    public boolean k() {
        return this.u.getVisibility() == 0 ? this.f7356a == 1 : this.f7356a != 2;
    }

    public boolean l() {
        return this.u.getVisibility() != 0 ? this.f7356a == 2 : this.f7356a != 1;
    }

    public void m() {
        this.g.b();
    }

    public fm0 n() {
        return new fm0();
    }

    public GradientDrawable o() {
        return new GradientDrawable();
    }

    public void p() {
        if (t()) {
            b();
            this.u.getViewTreeObserver().addOnPreDrawListener(this.A);
        }
    }

    public void q() {
    }

    public void r() {
        if (this.A != null) {
            this.u.getViewTreeObserver().removeOnPreDrawListener(this.A);
            this.A = null;
        }
    }

    public void s() {
        float rotation = this.u.getRotation();
        if (this.i != rotation) {
            this.i = rotation;
            v();
        }
    }

    public boolean t() {
        return true;
    }

    public final boolean u() {
        return lb.M(this.u) && !this.u.isInEditMode();
    }

    public final void v() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.i % 90.0f != 0.0f) {
                if (this.u.getLayerType() != 1) {
                    this.u.setLayerType(1, null);
                }
            } else if (this.u.getLayerType() != 0) {
                this.u.setLayerType(0, null);
            }
        }
        wm0 wm0Var = this.h;
        if (wm0Var != null) {
            wm0Var.a(-this.i);
        }
        fm0 fm0Var = this.l;
        if (fm0Var != null) {
            fm0Var.b(-this.i);
        }
    }

    public final void w() {
        c(this.r);
    }

    public final void x() {
        Rect rect = this.w;
        a(rect);
        b(rect);
        this.v.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final void b(float f2) {
        if (this.o != f2) {
            this.o = f2;
            a(this.n, f2, this.p);
        }
    }

    public void d(Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.s;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    public void c(Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.t;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    public final jl0 d() {
        if (this.f == null) {
            this.f = jl0.a(this.u.getContext(), com.google.android.material.R.animator.design_fab_hide_motion_spec);
        }
        return this.f;
    }

    public final void b(jl0 jl0Var) {
        this.c = jl0Var;
    }

    public final Drawable c() {
        return this.m;
    }

    public void b(Animator.AnimatorListener animatorListener) {
        if (this.s == null) {
            this.s = new ArrayList<>();
        }
        this.s.add(animatorListener);
    }

    public void b(g gVar, boolean z) {
        if (l()) {
            return;
        }
        Animator animator = this.b;
        if (animator != null) {
            animator.cancel();
        }
        if (u()) {
            if (this.u.getVisibility() != 0) {
                this.u.setAlpha(0.0f);
                this.u.setScaleY(0.0f);
                this.u.setScaleX(0.0f);
                c(0.0f);
            }
            jl0 jl0VarE = this.c;
            if (jl0VarE == null) {
                jl0VarE = e();
            }
            AnimatorSet animatorSetA = a(jl0VarE, 1.0f, 1.0f, 1.0f);
            animatorSetA.addListener(new b(z, gVar));
            ArrayList<Animator.AnimatorListener> arrayList = this.s;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    animatorSetA.addListener(it.next());
                }
            }
            animatorSetA.start();
            return;
        }
        this.u.a(0, z);
        this.u.setAlpha(1.0f);
        this.u.setScaleY(1.0f);
        this.u.setScaleX(1.0f);
        c(1.0f);
        if (gVar != null) {
            gVar.a();
        }
    }

    public void a(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable != null) {
            y8.a(drawable, colorStateList);
        }
        fm0 fm0Var = this.l;
        if (fm0Var != null) {
            fm0Var.a(colorStateList);
        }
    }

    public void a(PorterDuff.Mode mode) {
        Drawable drawable = this.j;
        if (drawable != null) {
            y8.a(drawable, mode);
        }
    }

    public final void a(float f2) {
        if (this.n != f2) {
            this.n = f2;
            a(f2, this.o, this.p);
        }
    }

    public final void a(int i2) {
        if (this.q != i2) {
            this.q = i2;
            w();
        }
    }

    public final void a(float f2, Matrix matrix) {
        matrix.reset();
        if (this.u.getDrawable() == null || this.q == 0) {
            return;
        }
        RectF rectF = this.x;
        RectF rectF2 = this.y;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i2 = this.q;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.q;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    public final void b() {
        if (this.A == null) {
            this.A = new c();
        }
    }

    public final void a(jl0 jl0Var) {
        this.d = jl0Var;
    }

    public void a(float f2, float f3, float f4) {
        wm0 wm0Var = this.h;
        if (wm0Var != null) {
            wm0Var.a(f2, this.p + f2);
            x();
        }
    }

    public void a(int[] iArr) {
        this.g.a(iArr);
    }

    public void a(Animator.AnimatorListener animatorListener) {
        if (this.t == null) {
            this.t = new ArrayList<>();
        }
        this.t.add(animatorListener);
    }

    public void a(g gVar, boolean z) {
        if (k()) {
            return;
        }
        Animator animator = this.b;
        if (animator != null) {
            animator.cancel();
        }
        if (u()) {
            jl0 jl0VarD = this.d;
            if (jl0VarD == null) {
                jl0VarD = d();
            }
            AnimatorSet animatorSetA = a(jl0VarD, 0.0f, 0.0f, 0.0f);
            animatorSetA.addListener(new a(z, gVar));
            ArrayList<Animator.AnimatorListener> arrayList = this.t;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    animatorSetA.addListener(it.next());
                }
            }
            animatorSetA.start();
            return;
        }
        this.u.a(z ? 8 : 4, z);
        if (gVar != null) {
            gVar.b();
        }
    }

    public final AnimatorSet a(jl0 jl0Var, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.u, (Property<VisibilityAwareImageButton, Float>) View.ALPHA, f2);
        jl0Var.a("opacity").a((Animator) objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.u, (Property<VisibilityAwareImageButton, Float>) View.SCALE_X, f3);
        jl0Var.a("scale").a((Animator) objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.u, (Property<VisibilityAwareImageButton, Float>) View.SCALE_Y, f3);
        jl0Var.a("scale").a((Animator) objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        a(f4, this.z);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.u, new hl0(), new il0(), new Matrix(this.z));
        jl0Var.a("iconScale").a((Animator) objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        dl0.a(animatorSet, arrayList);
        return animatorSet;
    }

    public void a(Rect rect) {
        this.h.getPadding(rect);
    }

    public fm0 a(int i2, ColorStateList colorStateList) {
        Context context = this.u.getContext();
        fm0 fm0VarN = n();
        fm0VarN.a(y7.a(context, com.google.android.material.R.color.design_fab_stroke_top_outer_color), y7.a(context, com.google.android.material.R.color.design_fab_stroke_top_inner_color), y7.a(context, com.google.android.material.R.color.design_fab_stroke_end_inner_color), y7.a(context, com.google.android.material.R.color.design_fab_stroke_end_outer_color));
        fm0VarN.a(i2);
        fm0VarN.a(colorStateList);
        return fm0VarN;
    }

    public GradientDrawable a() {
        GradientDrawable gradientDrawableO = o();
        gradientDrawableO.setShape(1);
        gradientDrawableO.setColor(-1);
        return gradientDrawableO;
    }

    public final ValueAnimator a(i iVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(B);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(iVar);
        valueAnimator.addUpdateListener(iVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }
}
