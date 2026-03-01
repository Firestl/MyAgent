package com.dcloud.android.v4.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import org.json.JSONObject;
import supwisdom.fu;
import supwisdom.gu;
import supwisdom.hu;
import supwisdom.iu;
import supwisdom.ku;
import supwisdom.uu;
import supwisdom.vu;
import supwisdom.wu;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements hu, fu, vu {
    public static final int[] b0 = {R.attr.enabled};
    public Animation A;
    public Animation B;
    public Animation C;
    public Animation D;
    public float E;
    public boolean F;
    public int G;
    public int H;
    public boolean I;
    public boolean J;
    public int K;
    public float L;
    public float M;
    public Animation.AnimationListener N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public JSONObject R;
    public View S;
    public View T;
    public boolean U;
    public boolean V;
    public final Animation W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f1780a;
    public final Animation a0;
    public vu.a b;
    public boolean c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1781e;
    public float f;
    public final iu g;
    public final gu h;
    public final int[] i;
    public int j;
    public int k;
    public boolean l;
    public float m;
    public float n;
    public float o;
    public boolean p;
    public boolean q;
    public boolean r;
    public final DecelerateInterpolator s;
    public uu t;
    public int u;
    public int v;
    public float w;
    public int x;
    public wu y;
    public Animation z;

    public class a extends Animation {
        public a() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.w + ((-SwipeRefreshLayout.this.w) * f));
            SwipeRefreshLayout.this.c(f);
        }
    }

    public class b implements Animation.AnimationListener {
        public b() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (SwipeRefreshLayout.this.c) {
                SwipeRefreshLayout.this.y.setAlpha(255);
                SwipeRefreshLayout.this.y.start();
                SwipeRefreshLayout.this.J = true;
                if (SwipeRefreshLayout.this.F && SwipeRefreshLayout.this.b != null) {
                    SwipeRefreshLayout.this.b.onRefresh(3);
                }
            } else {
                SwipeRefreshLayout.this.y.stop();
                SwipeRefreshLayout.this.J = false;
                SwipeRefreshLayout.this.t.setVisibility(8);
                SwipeRefreshLayout.this.setColorViewAlpha(255);
                if (SwipeRefreshLayout.this.q) {
                    SwipeRefreshLayout.this.setAnimationProgress(0.0f);
                } else {
                    SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                    swipeRefreshLayout.a(swipeRefreshLayout.x - swipeRefreshLayout.k, true);
                }
            }
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            swipeRefreshLayout2.k = swipeRefreshLayout2.t.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            SwipeRefreshLayout.this.l();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class c extends Animation {
        public c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f);
        }
    }

    public class d extends Animation {
        public d() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
        }
    }

    public class e extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1786a;
        public final /* synthetic */ int b;

        public e(int i, int i2) {
            this.f1786a = i;
            this.b = i2;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.y.setAlpha((int) (this.f1786a + ((this.b - r0) * f)));
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1787a = 0;

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1787a >= SwipeRefreshLayout.this.f1781e) {
                SwipeRefreshLayout.this.a(true, true);
                SwipeRefreshLayout.this.P = false;
            } else {
                SwipeRefreshLayout.this.b(this.f1787a);
                SwipeRefreshLayout.this.postDelayed(this, 1L);
                this.f1787a += 15;
            }
        }
    }

    public class g implements Animation.AnimationListener {
        public g() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (SwipeRefreshLayout.this.t != null) {
                SwipeRefreshLayout.this.t.setVisibility(8);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class h extends Animation {
        public h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            float fAbs = !SwipeRefreshLayout.this.I ? SwipeRefreshLayout.this.E - Math.abs(SwipeRefreshLayout.this.x) : SwipeRefreshLayout.this.E;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.a((swipeRefreshLayout.v + ((int) ((((int) fAbs) - r1) * f))) - swipeRefreshLayout.t.getTop(), false);
            SwipeRefreshLayout.this.y.a(1.0f - f);
        }
    }

    public class i extends Animation {
        public i() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            float fAbs = !SwipeRefreshLayout.this.I ? SwipeRefreshLayout.this.E - Math.abs(SwipeRefreshLayout.this.x) : SwipeRefreshLayout.this.E;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.a((swipeRefreshLayout.v + ((int) ((((int) fAbs) - r1) * f))) - swipeRefreshLayout.t.getTop(), false);
            SwipeRefreshLayout.this.y.a(1.0f - f);
        }
    }

    public class j extends Animation {
        public j() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.c(f);
        }
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet, boolean z) {
        super(context);
        this.c = false;
        this.f1781e = -1.0f;
        this.i = new int[2];
        this.l = false;
        this.p = false;
        this.u = -1;
        this.J = false;
        this.N = new b();
        this.O = false;
        this.P = false;
        this.Q = false;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = true;
        this.V = false;
        this.W = new h();
        new i();
        this.a0 = new j();
        this.d = ViewConfiguration.get(context).getScaledTouchSlop();
        this.O = z;
        this.j = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.s = new DecelerateInterpolator(2.0f);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b0);
            setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
            typedArrayObtainStyledAttributes.recycle();
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i2 = (int) (displayMetrics.density * 40.0f);
        this.G = i2;
        this.H = i2;
        h();
        ku.a((ViewGroup) this, true);
        float f2 = displayMetrics.density * 64.0f;
        this.E = f2;
        this.f1781e = f2;
        this.L = f2;
        this.M = f2;
        this.g = new iu(this);
        this.h = new gu(this);
        setNestedScrollingEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimationProgress(float f2) {
        if (j()) {
            setColorViewAlpha((int) (f2 * 255.0f));
        } else {
            ku.b(this.t, f2);
            ku.c(this.t, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setColorViewAlpha(int i2) {
        this.t.getBackground().setAlpha(i2);
        this.y.setAlpha(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.O) {
            super.dispatchDraw(canvas);
        } else {
            l();
        }
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.h.a(f2, f3, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.h.a(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.h.a(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.h.a(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.u;
        return i4 < 0 ? i3 : i3 == i2 + (-1) ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.g.a();
    }

    public int getProgressCircleDiameter() {
        uu uuVar = this.t;
        if (uuVar != null) {
            return uuVar.getMeasuredHeight();
        }
        return 0;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.h.a();
    }

    @Override // android.view.View, supwisdom.fu
    public boolean isNestedScrollingEnabled() {
        return this.h.b();
    }

    public final void m() {
        this.C = a(this.y.getAlpha(), 255);
    }

    public final void n() {
        this.B = a(this.y.getAlpha(), 76);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f1780a == null) {
            i();
        }
        int measuredWidth2 = this.t.getMeasuredWidth();
        int measuredHeight = this.t.getMeasuredHeight();
        int i6 = measuredWidth / 2;
        int i7 = measuredWidth2 / 2;
        int i8 = this.k;
        this.t.layout(i6 - i7, i8, i6 + i7, measuredHeight + i8);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1780a == null) {
            i();
        }
        this.t.measure(View.MeasureSpec.makeMeasureSpec(this.G, 1073741824), View.MeasureSpec.makeMeasureSpec(this.H, 1073741824));
        if (!this.I && !this.l) {
            this.l = true;
            int i4 = -this.t.getMeasuredHeight();
            this.x = i4;
            this.k = i4;
            this.K = i4;
        }
        this.u = -1;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            if (getChildAt(i5) == this.t) {
                this.u = i5;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (i3 > 0) {
            float f2 = this.f;
            if (f2 > 0.0f) {
                float f3 = i3;
                if (f3 > f2) {
                    iArr[1] = i3 - ((int) f2);
                    this.f = 0.0f;
                } else {
                    this.f = f2 - f3;
                    iArr[1] = i3;
                }
                b(this.f);
            }
        }
        int[] iArr2 = this.i;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        if (i5 < 0) {
            float fAbs = this.f + Math.abs(i5);
            this.f = fAbs;
            b(fAbs);
        }
        dispatchNestedScroll(i2, i3, i4, i2, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.g.a(view, view2, i2);
        this.f = 0.0f;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        int i3;
        if (!isEnabled() || (i3 = i2 & 2) == 0) {
            return false;
        }
        startNestedScroll(i3);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onStopNestedScroll(View view) {
        this.g.a(view);
        float f2 = this.f;
        if (f2 > 0.0f) {
            a(f2);
            this.f = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.O) {
            return a(motionEvent);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.f1780a instanceof AbsListView)) {
            View view = this.f1780a;
            if (view == null || ku.b(view)) {
                super.requestDisallowInterceptTouchEvent(z);
            }
        }
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        i();
        this.y.a(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = resources.getColor(iArr[i2]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i2) {
        this.f1781e = i2;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.h.a(z);
    }

    public void setOnRefreshListener(vu.a aVar) {
        this.b = aVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i2) {
        setProgressBackgroundColorSchemeResource(i2);
    }

    public void setProgressBackgroundColorSchemeColor(int i2) {
        this.t.setBackgroundColor(i2);
        this.y.a(i2);
    }

    public void setProgressBackgroundColorSchemeResource(int i2) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i2));
    }

    @Override // supwisdom.vu
    public void setRefreshEnable(boolean z) {
        this.U = z;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.c == z) {
            a(z, true);
            return;
        }
        this.c = z;
        a(((int) (!this.I ? this.E + this.x : this.E)) - this.k, true);
        this.F = false;
        b(this.N);
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i2 == 0) {
                int i3 = (int) (displayMetrics.density * 56.0f);
                this.G = i3;
                this.H = i3;
            } else {
                int i4 = (int) (displayMetrics.density * 40.0f);
                this.G = i4;
                this.H = i4;
            }
            this.t.setImageDrawable(null);
            this.y.b(i2);
            this.t.setImageDrawable(this.y);
        }
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return this.h.a(i2);
    }

    @Override // android.view.View, supwisdom.fu
    public void stopNestedScroll() {
        this.h.c();
    }

    @Override // supwisdom.vu
    public boolean d() {
        return this.U;
    }

    @Override // supwisdom.vu
    public boolean e() {
        return this.p || b();
    }

    public boolean f() {
        View view = this.f1780a;
        if (view == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            return ku.a(view, -1);
        }
        if (!(view instanceof AbsListView)) {
            return ku.a(view, -1) || this.f1780a.getScrollY() > 0;
        }
        AbsListView absListView = (AbsListView) view;
        if (absListView.getChildCount() > 0) {
            return absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop();
        }
        return false;
    }

    public final void g() {
        this.c = false;
        this.y.a(0.0f, 0.0f);
        b(this.k, !this.q ? new g() : null);
        this.y.a(false);
    }

    public final void h() {
        this.t = new uu(getContext(), -328966, 20.0f, false);
        wu wuVar = new wu(getContext(), this);
        this.y = wuVar;
        wuVar.a(-328966);
        this.t.setImageDrawable(this.y);
        this.t.setVisibility(8);
        addView(this.t);
    }

    public final void i() {
        if (this.f1780a == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (!childAt.equals(this.t)) {
                    this.f1780a = childAt;
                    return;
                }
            }
        }
    }

    public final boolean j() {
        return Build.VERSION.SDK_INT < 11;
    }

    public final boolean k() {
        return this.t.getVisibility() == 0 && this.t.getTop() > this.x - this.t.getMeasuredHeight() && (this.T.getScrollY() <= 0 || this.J);
    }

    public final void l() {
        if (!k() || this.T == null) {
            return;
        }
        Log.d("parentInvalidate", "parentInvalidate");
        int width = ((this.T.getWidth() - this.G) / 2) + this.T.getScrollX();
        int scrollY = this.x + this.H + this.T.getScrollY();
        this.S.invalidate(width, scrollY, this.G + width, this.t.getTop() + scrollY + this.H);
    }

    @Override // supwisdom.vu
    public void c() {
        if (this.P || this.t.getVisibility() == 0) {
            return;
        }
        post(new f());
        this.P = true;
    }

    public final void b(int i2, Animation.AnimationListener animationListener) {
        if (this.q) {
            c(i2, animationListener);
            return;
        }
        this.v = i2;
        this.a0.reset();
        this.a0.setDuration(200L);
        this.a0.setInterpolator(this.s);
        if (animationListener != null) {
            this.t.a(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.a0);
    }

    public final void a(int i2, Animation.AnimationListener animationListener) {
        this.v = i2;
        this.W.reset();
        this.W.setDuration(200L);
        this.W.setInterpolator(this.s);
        if (animationListener != null) {
            this.t.a(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.W);
    }

    public final void c(float f2) {
        a((this.v + ((int) ((this.x - r0) * f2))) - this.t.getTop(), false);
    }

    public final void c(int i2, Animation.AnimationListener animationListener) {
        this.v = i2;
        if (j()) {
            this.w = this.y.getAlpha();
        } else {
            this.w = ku.a(this.t);
        }
        a aVar = new a();
        this.D = aVar;
        aVar.setDuration(150L);
        if (animationListener != null) {
            this.t.a(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.D);
    }

    public final boolean b(MotionEvent motionEvent) {
        if (b()) {
            return false;
        }
        return motionEvent.getAction() == 0 || this.V;
    }

    @Override // supwisdom.vu
    public void a() {
        setRefreshing(false);
    }

    public final void a(float f2) {
        if (f2 > this.f1781e) {
            a(true, true);
        } else {
            g();
        }
    }

    @Override // supwisdom.vu
    public boolean b() {
        return this.c;
    }

    public final void b(float f2) {
        this.y.a(true);
        float fMin = Math.min(1.0f, Math.abs(f2 / this.f1781e));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f2) - this.f1781e;
        float f3 = this.I ? this.E - this.x : this.E;
        double dMax = Math.max(0.0f, Math.min(fAbs, f3 * 2.0f) / f3) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i2 = this.x + ((int) ((f3 * fMin) + (f3 * fPow * 2.0f)));
        if (this.t.getVisibility() != 0) {
            this.t.setVisibility(0);
        }
        if (!this.q) {
            ku.b((View) this.t, 1.0f);
            ku.c(this.t, 1.0f);
        }
        float f4 = this.f1781e;
        if (f2 < f4) {
            if (this.q) {
                setAnimationProgress(f2 / f4);
            }
            if (this.y.getAlpha() > 76 && !a(this.B)) {
                n();
            }
            this.y.a(0.0f, Math.min(0.8f, fMax * 0.8f));
            this.y.a(Math.min(1.0f, fMax));
        } else if (this.y.getAlpha() < 255 && !a(this.C)) {
            m();
            this.y.a(0.0f, 0.8f);
            this.y.a(1.0f);
        }
        this.y.b((((fMax * 0.4f) - 0.25f) + (fPow * 2.0f)) * 0.5f);
        a(i2 - this.k, true);
    }

    public final boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    @Override // supwisdom.vu
    public void a(ViewGroup viewGroup, View view, vu.a aVar) {
        this.T = view;
        this.S = view;
        setOnRefreshListener(aVar);
        viewGroup.addView(this, -1, -1);
    }

    @Override // supwisdom.vu
    public void a(int i2, int i3, float f2) {
        a(this.R, i2, i3, f2);
    }

    @Override // supwisdom.vu
    public void a(Canvas canvas) {
        if (k()) {
            canvas.save();
            int measuredWidth = this.t.getMeasuredWidth();
            int measuredHeight = this.t.getMeasuredHeight();
            int width = ((this.T.getWidth() - measuredWidth) / 2) + this.T.getScrollX();
            int iMax = Math.max((this.T.getScrollY() - measuredHeight) + this.t.getTop(), this.x);
            canvas.clipRect(width, iMax, measuredWidth + width, iMax + measuredHeight);
            canvas.translate(this.T.getScrollX(), this.T.getScrollY() - measuredHeight);
            super.dispatchDraw(canvas);
            canvas.restore();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0098  */
    @Override // supwisdom.vu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dcloud.android.v4.widget.SwipeRefreshLayout.a(android.view.MotionEvent):boolean");
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, true);
    }

    public final void b(Animation.AnimationListener animationListener) {
        this.t.setVisibility(0);
        if (Build.VERSION.SDK_INT >= 11) {
            this.y.setAlpha(255);
        }
        c cVar = new c();
        this.z = cVar;
        cVar.setDuration(this.j);
        if (animationListener != null) {
            this.t.a(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.z);
    }

    @Override // supwisdom.vu
    public void a(JSONObject jSONObject, int i2, int i3, float f2) {
        if (f2 == 0.0f || f2 == 1.0f) {
            try {
                f2 = this.T.getContext().getResources().getDisplayMetrics().density;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        this.x = this.K;
        this.E = this.L;
        this.f1781e = this.M;
        this.R = jSONObject;
        String strOptString = jSONObject.optString("offset");
        int iConvertToScreenInt = this.x;
        if (!TextUtils.isEmpty(strOptString)) {
            iConvertToScreenInt = PdrUtil.convertToScreenInt(strOptString, i3, iConvertToScreenInt, f2);
        }
        String strOptString2 = jSONObject.optString("height");
        int iConvertToScreenInt2 = (int) this.f1781e;
        if (!TextUtils.isEmpty(strOptString2)) {
            iConvertToScreenInt2 = PdrUtil.convertToScreenInt(strOptString2, i3, iConvertToScreenInt2, f2);
        }
        String strOptString3 = jSONObject.optString(AbsoluteConst.PULL_REFRESH_RANGE);
        int iConvertToScreenInt3 = (int) this.E;
        if (!TextUtils.isEmpty(strOptString3)) {
            iConvertToScreenInt3 = PdrUtil.convertToScreenInt(strOptString3, i3, iConvertToScreenInt3, f2);
        }
        int i4 = iConvertToScreenInt3 + iConvertToScreenInt;
        String strOptString4 = jSONObject.optString("color");
        int color = Color.parseColor("#2BD009");
        if (!TextUtils.isEmpty(strOptString4) && strOptString4.startsWith("#")) {
            try {
                color = Color.parseColor(strOptString4);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        setColorSchemeColors(color);
        if (this.x != iConvertToScreenInt) {
            this.Q = false;
        }
        if (this.Q) {
            return;
        }
        this.Q = true;
        a(false, iConvertToScreenInt, i4, iConvertToScreenInt2);
    }

    public void a(boolean z, int i2, int i3, int i4) {
        this.q = z;
        this.t.setVisibility(8);
        this.k = i2;
        this.x = i2;
        this.E = i3;
        this.f1781e = i4;
        this.I = true;
        this.t.invalidate();
    }

    public final void a(int i2, boolean z) {
        this.t.bringToFront();
        this.t.offsetTopAndBottom(i2);
        this.k = this.t.getTop();
        if (!z || Build.VERSION.SDK_INT >= 11) {
            return;
        }
        invalidate();
    }

    public final Animation a(int i2, int i3) {
        if (this.q && j()) {
            return null;
        }
        e eVar = new e(i2, i3);
        eVar.setDuration(300L);
        this.t.a((Animation.AnimationListener) null);
        this.t.clearAnimation();
        this.t.startAnimation(eVar);
        return eVar;
    }

    public final void a(Animation.AnimationListener animationListener) {
        d dVar = new d();
        this.A = dVar;
        dVar.setDuration(150L);
        this.t.a(animationListener);
        this.t.clearAnimation();
        this.t.startAnimation(this.A);
    }

    public final void a(boolean z, boolean z2) {
        if (this.c != z) {
            this.F = z2;
            i();
            this.c = z;
            if (z) {
                a(this.k, this.N);
            } else {
                a(this.N);
            }
        }
    }
}
