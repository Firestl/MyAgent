package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import supwisdom.a5;
import supwisdom.c6;
import supwisdom.d5;
import supwisdom.d7;
import supwisdom.db;
import supwisdom.e7;
import supwisdom.f6;
import supwisdom.g6;
import supwisdom.g7;
import supwisdom.h6;
import supwisdom.i6;
import supwisdom.j6;
import supwisdom.l6;
import supwisdom.m5;
import supwisdom.n5;
import supwisdom.p5;
import supwisdom.s5;
import supwisdom.x4;
import supwisdom.z4;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements db {
    public static boolean E0;
    public int A;
    public boolean A0;
    public boolean B;
    public RectF B0;
    public HashMap<View, m5> C;
    public View C0;
    public long D;
    public ArrayList<Integer> D0;
    public float E;
    public float F;
    public float G;
    public long H;
    public float I;
    public boolean J;
    public boolean K;
    public i L;
    public float M;
    public float N;
    public int O;
    public d P;
    public boolean Q;
    public x4 R;
    public c S;
    public a5 T;
    public int U;
    public int V;
    public boolean W;
    public float a0;
    public float b0;
    public long c0;
    public float d0;
    public boolean e0;
    public ArrayList<MotionHelper> f0;
    public ArrayList<MotionHelper> g0;
    public ArrayList<i> h0;
    public int i0;
    public long j0;
    public float k0;
    public int l0;
    public float m0;
    public boolean n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public int s0;
    public p5 t;
    public int t0;
    public Interpolator u;
    public float u0;
    public float v;
    public d5 v0;
    public int w;
    public boolean w0;
    public int x;
    public h x0;
    public int y;
    public j y0;
    public int z;
    public e z0;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1210a;

        public a(View view) {
            this.f1210a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1210a.setNestedScrollingEnabled(true);
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1211a;

        static {
            int[] iArr = new int[j.values().length];
            f1211a = iArr;
            try {
                iArr[j.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1211a[j.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1211a[j.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1211a[j.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public interface f {
        float a();

        void a(int i);

        void a(MotionEvent motionEvent);

        float b();

        void recycle();
    }

    public interface i {
        void a(MotionLayout motionLayout, int i);

        void a(MotionLayout motionLayout, int i, int i2);

        void a(MotionLayout motionLayout, int i, int i2, float f);

        void a(MotionLayout motionLayout, int i, boolean z, float f);
    }

    public enum j {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = 0.0f;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap<>();
        this.D = 0L;
        this.E = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.I = 0.0f;
        this.K = false;
        this.O = 0;
        this.Q = false;
        this.R = new x4();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = 0;
        this.j0 = -1L;
        this.k0 = 0.0f;
        this.l0 = 0;
        this.m0 = 0.0f;
        this.n0 = false;
        this.v0 = new d5();
        this.w0 = false;
        this.y0 = j.UNDEFINED;
        this.z0 = new e();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = new ArrayList<>();
        a(attributeSet);
    }

    public static boolean a(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    @Override // supwisdom.cb
    public void a(View view, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // supwisdom.cb
    public void b(View view, View view2, int i2, int i3) {
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        a(false);
        super.dispatchDraw(canvas);
        if (this.t == null) {
            return;
        }
        if ((this.O & 1) == 1 && !isInEditMode()) {
            this.i0++;
            long nanoTime = getNanoTime();
            long j2 = this.j0;
            if (j2 != -1) {
                if (nanoTime - j2 > 200000000) {
                    this.k0 = ((int) ((this.i0 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.i0 = 0;
                    this.j0 = nanoTime;
                }
            } else {
                this.j0 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.k0 + " fps " + z4.a(this, this.w) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(z4.a(this, this.y));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.x;
            sb.append(i2 == -1 ? "undefined" : z4.a(this, i2));
            String string = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.O > 1) {
            if (this.P == null) {
                this.P = new d();
            }
            this.P.a(canvas, this.C, this.t.d(), this.O);
        }
    }

    public int[] getConstraintSetIds() {
        p5 p5Var = this.t;
        if (p5Var == null) {
            return null;
        }
        return p5Var.b();
    }

    public int getCurrentState() {
        return this.x;
    }

    public ArrayList<p5.b> getDefinedTransitions() {
        p5 p5Var = this.t;
        if (p5Var == null) {
            return null;
        }
        return p5Var.c();
    }

    public a5 getDesignTool() {
        if (this.T == null) {
            this.T = new a5(this);
        }
        return this.T;
    }

    public int getEndState() {
        return this.y;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.G;
    }

    public int getStartState() {
        return this.w;
    }

    public float getTargetPosition() {
        return this.I;
    }

    public Bundle getTransitionState() {
        if (this.x0 == null) {
            this.x0 = new h();
        }
        this.x0.c();
        return this.x0.b();
    }

    public long getTransitionTimeMs() {
        if (this.t != null) {
            this.E = r0.d() / 1000.0f;
        }
        return (long) (this.E * 1000.0f);
    }

    public float getVelocity() {
        return this.v;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    public void l() {
        p5 p5Var = this.t;
        if (p5Var == null) {
            return;
        }
        if (p5Var.b(this, this.x)) {
            requestLayout();
            return;
        }
        int i2 = this.x;
        if (i2 != -1) {
            this.t.a(this, i2);
        }
        if (this.t.n()) {
            this.t.m();
        }
    }

    public final void m() {
        ArrayList<i> arrayList;
        if (this.L == null && ((arrayList = this.h0) == null || arrayList.isEmpty())) {
            return;
        }
        for (Integer num : this.D0) {
            i iVar = this.L;
            if (iVar != null) {
                iVar.a(this, num.intValue());
            }
            ArrayList<i> arrayList2 = this.h0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, num.intValue());
                }
            }
        }
        this.D0.clear();
    }

    public void n() {
        this.z0.b();
        invalidate();
    }

    public final void o() {
        int childCount = getChildCount();
        this.z0.a();
        boolean z = true;
        this.K = true;
        int width = getWidth();
        int height = getHeight();
        int iA = this.t.a();
        int i2 = 0;
        if (iA != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                m5 m5Var = this.C.get(getChildAt(i3));
                if (m5Var != null) {
                    m5Var.b(iA);
                }
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            m5 m5Var2 = this.C.get(getChildAt(i4));
            if (m5Var2 != null) {
                this.t.a(m5Var2);
                m5Var2.a(width, height, this.E, getNanoTime());
            }
        }
        float fJ = this.t.j();
        if (fJ != 0.0f) {
            boolean z2 = ((double) fJ) < 0.0d;
            float fAbs = Math.abs(fJ);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            int i5 = 0;
            float fMin2 = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            while (true) {
                if (i5 >= childCount) {
                    z = false;
                    break;
                }
                m5 m5Var3 = this.C.get(getChildAt(i5));
                if (!Float.isNaN(m5Var3.j)) {
                    break;
                }
                float fB = m5Var3.b();
                float fC = m5Var3.c();
                float f2 = z2 ? fC - fB : fC + fB;
                fMin2 = Math.min(fMin2, f2);
                fMax2 = Math.max(fMax2, f2);
                i5++;
            }
            if (!z) {
                while (i2 < childCount) {
                    m5 m5Var4 = this.C.get(getChildAt(i2));
                    float fB2 = m5Var4.b();
                    float fC2 = m5Var4.c();
                    float f3 = z2 ? fC2 - fB2 : fC2 + fB2;
                    m5Var4.l = 1.0f / (1.0f - fAbs);
                    m5Var4.k = fAbs - (((f3 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i2++;
                }
                return;
            }
            for (int i6 = 0; i6 < childCount; i6++) {
                m5 m5Var5 = this.C.get(getChildAt(i6));
                if (!Float.isNaN(m5Var5.j)) {
                    fMin = Math.min(fMin, m5Var5.j);
                    fMax = Math.max(fMax, m5Var5.j);
                }
            }
            while (i2 < childCount) {
                m5 m5Var6 = this.C.get(getChildAt(i2));
                if (!Float.isNaN(m5Var6.j)) {
                    m5Var6.l = 1.0f / (1.0f - fAbs);
                    if (z2) {
                        m5Var6.k = fAbs - (((fMax - m5Var6.j) / (fMax - fMin)) * fAbs);
                    } else {
                        m5Var6.k = fAbs - (((m5Var6.j - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i2++;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        p5.b bVar;
        int i2;
        super.onAttachedToWindow();
        p5 p5Var = this.t;
        if (p5Var != null && (i2 = this.x) != -1) {
            e7 e7VarA = p5Var.a(i2);
            this.t.a(this);
            if (e7VarA != null) {
                e7VarA.b(this);
            }
            this.w = this.x;
        }
        l();
        h hVar = this.x0;
        if (hVar != null) {
            hVar.a();
            return;
        }
        p5 p5Var2 = this.t;
        if (p5Var2 == null || (bVar = p5Var2.c) == null || bVar.a() != 4) {
            return;
        }
        p();
        setState(j.SETUP);
        setState(j.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        p5.b bVar;
        s5 s5VarF;
        int iE;
        RectF rectFB;
        p5 p5Var = this.t;
        if (p5Var != null && this.B && (bVar = p5Var.c) != null && bVar.g() && (s5VarF = bVar.f()) != null && ((motionEvent.getAction() != 0 || (rectFB = s5VarF.b(this, new RectF())) == null || rectFB.contains(motionEvent.getX(), motionEvent.getY())) && (iE = s5VarF.e()) != -1)) {
            View view = this.C0;
            if (view == null || view.getId() != iE) {
                this.C0 = findViewById(iE);
            }
            if (this.C0 != null) {
                this.B0.set(r0.getLeft(), this.C0.getTop(), this.C0.getRight(), this.C0.getBottom());
                if (this.B0.contains(motionEvent.getX(), motionEvent.getY()) && !a(0.0f, 0.0f, this.C0, motionEvent)) {
                    return onTouchEvent(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.w0 = true;
        try {
            if (this.t == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.U != i6 || this.V != i7) {
                n();
                a(true);
            }
            this.U = i6;
            this.V = i7;
        } finally {
            this.w0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.t == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.z == i2 && this.A == i3) ? false : true;
        if (this.A0) {
            this.A0 = false;
            l();
            m();
            z2 = true;
        }
        if (this.h) {
            z2 = true;
        }
        this.z = i2;
        this.A = i3;
        int iK = this.t.k();
        int iE = this.t.e();
        if ((z2 || this.z0.a(iK, iE)) && this.w != -1) {
            super.onMeasure(i2, i3);
            this.z0.a(this.c, this.t.a(iK), this.t.a(iE));
            this.z0.b();
            this.z0.c(iK, iE);
        } else {
            z = true;
        }
        if (this.n0 || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iD = this.c.D() + getPaddingLeft() + getPaddingRight();
            int iL = this.c.l() + paddingTop;
            int i4 = this.s0;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                iD = (int) (this.o0 + (this.u0 * (this.q0 - r7)));
                requestLayout();
            }
            int i5 = this.t0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iL = (int) (this.p0 + (this.u0 * (this.r0 - r7)));
                requestLayout();
            }
            setMeasuredDimension(iD, iL);
        }
        g();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.eb
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.eb
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        p5 p5Var = this.t;
        if (p5Var != null) {
            p5Var.a(a());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        p5 p5Var = this.t;
        if (p5Var == null || !this.B || !p5Var.n()) {
            return super.onTouchEvent(motionEvent);
        }
        p5.b bVar = this.t.c;
        if (bVar != null && !bVar.g()) {
            return super.onTouchEvent(motionEvent);
        }
        this.t.a(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.h0 == null) {
                this.h0 = new ArrayList<>();
            }
            this.h0.add(motionHelper);
            if (motionHelper.d()) {
                if (this.f0 == null) {
                    this.f0 = new ArrayList<>();
                }
                this.f0.add(motionHelper);
            }
            if (motionHelper.c()) {
                if (this.g0 == null) {
                    this.g0 = new ArrayList<>();
                }
                this.g0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.f0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.g0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public void p() {
        a(1.0f);
    }

    public void q() {
        a(0.0f);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        p5 p5Var;
        p5.b bVar;
        if (this.n0 || this.x != -1 || (p5Var = this.t) == null || (bVar = p5Var.c) == null || bVar.d() != 0) {
            super.requestLayout();
        }
    }

    public void setDebugMode(int i2) {
        this.O = i2;
        invalidate();
    }

    public void setInteractionEnabled(boolean z) {
        this.B = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.t != null) {
            setState(j.MOVING);
            Interpolator interpolatorF = this.t.f();
            if (interpolatorF != null) {
                setProgress(interpolatorF.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.g0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g0.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.f0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f0.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.x0 == null) {
                this.x0 = new h();
            }
            this.x0.a(f2);
            return;
        }
        if (f2 <= 0.0f) {
            this.x = this.w;
            if (this.G == 0.0f) {
                setState(j.FINISHED);
            }
        } else if (f2 >= 1.0f) {
            this.x = this.y;
            if (this.G == 1.0f) {
                setState(j.FINISHED);
            }
        } else {
            this.x = -1;
            setState(j.MOVING);
        }
        if (this.t == null) {
            return;
        }
        this.J = true;
        this.I = f2;
        this.F = f2;
        this.H = -1L;
        this.D = -1L;
        this.u = null;
        this.K = true;
        invalidate();
    }

    public void setScene(p5 p5Var) {
        this.t = p5Var;
        p5Var.a(a());
        n();
    }

    public void setState(j jVar) {
        if (jVar == j.FINISHED && this.x == -1) {
            return;
        }
        j jVar2 = this.y0;
        this.y0 = jVar;
        j jVar3 = j.MOVING;
        if (jVar2 == jVar3 && jVar == jVar3) {
            h();
        }
        int i2 = b.f1211a[jVar2.ordinal()];
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3 && jVar == j.FINISHED) {
                i();
                return;
            }
            return;
        }
        if (jVar == j.MOVING) {
            h();
        }
        if (jVar == j.FINISHED) {
            i();
        }
    }

    public void setTransition(int i2) {
        if (this.t != null) {
            p5.b bVarD = d(i2);
            this.w = bVarD.e();
            this.y = bVarD.c();
            if (!isAttachedToWindow()) {
                if (this.x0 == null) {
                    this.x0 = new h();
                }
                this.x0.b(this.w);
                this.x0.a(this.y);
                return;
            }
            float f2 = Float.NaN;
            int i3 = this.x;
            if (i3 == this.w) {
                f2 = 0.0f;
            } else if (i3 == this.y) {
                f2 = 1.0f;
            }
            this.t.a(bVarD);
            this.z0.a(this.c, this.t.a(this.w), this.t.a(this.y));
            n();
            this.G = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v("MotionLayout", z4.a() + " transitionToStart ");
            q();
        }
    }

    public void setTransitionDuration(int i2) {
        p5 p5Var = this.t;
        if (p5Var == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            p5Var.g(i2);
        }
    }

    public void setTransitionListener(i iVar) {
        this.L = iVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.x0 == null) {
            this.x0 = new h();
        }
        this.x0.a(bundle);
        if (isAttachedToWindow()) {
            this.x0.a();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return z4.a(context, this.w) + "->" + z4.a(context, this.y) + " (pos:" + this.G + " Dpos/Dt:" + this.v;
    }

    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public f6 f1215a = new f6();
        public f6 b = new f6();
        public e7 c = null;
        public e7 d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1216e;
        public int f;

        public e() {
        }

        public void a(f6 f6Var, f6 f6Var2) {
            ArrayList<ConstraintWidget> arrayListT = f6Var.T();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(f6Var, f6Var2);
            f6Var2.T().clear();
            f6Var2.a(f6Var, map);
            for (ConstraintWidget constraintWidget : arrayListT) {
                ConstraintWidget c6Var = constraintWidget instanceof c6 ? new c6() : constraintWidget instanceof h6 ? new h6() : constraintWidget instanceof g6 ? new g6() : constraintWidget instanceof i6 ? new j6() : new ConstraintWidget();
                f6Var2.a(c6Var);
                map.put(constraintWidget, c6Var);
            }
            for (ConstraintWidget constraintWidget2 : arrayListT) {
                map.get(constraintWidget2).a(constraintWidget2, map);
            }
        }

        public void b() {
            b(MotionLayout.this.z, MotionLayout.this.A);
            MotionLayout.this.o();
        }

        public void c(int i, int i2) {
            this.f1216e = i;
            this.f = i2;
        }

        public void b(int i, int i2) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.s0 = mode;
            motionLayout.t0 = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.x == motionLayout2.getStartState()) {
                MotionLayout.this.a(this.b, optimizationLevel, i, i2);
                if (this.c != null) {
                    MotionLayout.this.a(this.f1215a, optimizationLevel, i, i2);
                }
            } else {
                if (this.c != null) {
                    MotionLayout.this.a(this.f1215a, optimizationLevel, i, i2);
                }
                MotionLayout.this.a(this.b, optimizationLevel, i, i2);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.s0 = mode;
                motionLayout3.t0 = mode2;
                if (motionLayout3.x == motionLayout3.getStartState()) {
                    MotionLayout.this.a(this.b, optimizationLevel, i, i2);
                    if (this.c != null) {
                        MotionLayout.this.a(this.f1215a, optimizationLevel, i, i2);
                    }
                } else {
                    if (this.c != null) {
                        MotionLayout.this.a(this.f1215a, optimizationLevel, i, i2);
                    }
                    MotionLayout.this.a(this.b, optimizationLevel, i, i2);
                }
                MotionLayout.this.o0 = this.f1215a.D();
                MotionLayout.this.p0 = this.f1215a.l();
                MotionLayout.this.q0 = this.b.D();
                MotionLayout.this.r0 = this.b.l();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.n0 = (motionLayout4.o0 == motionLayout4.q0 && motionLayout4.p0 == motionLayout4.r0) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i3 = motionLayout5.o0;
            int i4 = motionLayout5.p0;
            int i5 = motionLayout5.s0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                MotionLayout motionLayout6 = MotionLayout.this;
                i3 = (int) (motionLayout6.o0 + (motionLayout6.u0 * (motionLayout6.q0 - r1)));
            }
            int i6 = i3;
            int i7 = MotionLayout.this.t0;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                MotionLayout motionLayout7 = MotionLayout.this;
                i4 = (int) (motionLayout7.p0 + (motionLayout7.u0 * (motionLayout7.r0 - r1)));
            }
            MotionLayout.this.a(i, i2, i6, i4, this.f1215a.e0() || this.b.e0(), this.f1215a.c0() || this.b.c0());
        }

        public void a(f6 f6Var, e7 e7Var, e7 e7Var2) {
            this.c = e7Var;
            this.d = e7Var2;
            this.f1215a = new f6();
            this.b = new f6();
            this.f1215a.a(MotionLayout.this.c.W());
            this.b.a(MotionLayout.this.c.W());
            this.f1215a.V();
            this.b.V();
            a(MotionLayout.this.c, this.f1215a);
            a(MotionLayout.this.c, this.b);
            if (MotionLayout.this.G > 0.5d) {
                if (e7Var != null) {
                    a(this.f1215a, e7Var);
                }
                a(this.b, e7Var2);
            } else {
                a(this.b, e7Var2);
                if (e7Var != null) {
                    a(this.f1215a, e7Var);
                }
            }
            this.f1215a.g(MotionLayout.this.a());
            this.f1215a.g0();
            this.b.g(MotionLayout.this.a());
            this.b.g0();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    this.f1215a.a(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    this.b.a(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                }
                if (layoutParams.height == -2) {
                    this.f1215a.b(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    this.b.b(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(f6 f6Var, e7 e7Var) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, f6Var);
            sparseArray.put(MotionLayout.this.getId(), f6Var);
            for (ConstraintWidget constraintWidget : f6Var.T()) {
                sparseArray.put(((View) constraintWidget.h()).getId(), constraintWidget);
            }
            for (ConstraintWidget constraintWidget2 : f6Var.T()) {
                View view = (View) constraintWidget2.h();
                e7Var.a(view.getId(), layoutParams);
                constraintWidget2.u(e7Var.g(view.getId()));
                constraintWidget2.m(e7Var.c(view.getId()));
                if (view instanceof ConstraintHelper) {
                    e7Var.a((ConstraintHelper) view, constraintWidget2, layoutParams, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).b();
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    layoutParams.resolveLayoutDirection(0);
                }
                MotionLayout.this.a(false, view, constraintWidget2, (ConstraintLayout.LayoutParams) layoutParams, sparseArray);
                if (e7Var.f(view.getId()) == 1) {
                    constraintWidget2.t(view.getVisibility());
                } else {
                    constraintWidget2.t(e7Var.e(view.getId()));
                }
            }
            for (ConstraintWidget constraintWidget3 : f6Var.T()) {
                if (constraintWidget3 instanceof l6) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) constraintWidget3.h();
                    i6 i6Var = (i6) constraintWidget3;
                    constraintHelper.a(f6Var, i6Var, sparseArray);
                    ((l6) i6Var).T();
                }
            }
        }

        public ConstraintWidget a(f6 f6Var, View view) {
            if (f6Var.h() == view) {
                return f6Var;
            }
            ArrayList<ConstraintWidget> arrayListT = f6Var.T();
            int size = arrayListT.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = arrayListT.get(i);
                if (constraintWidget.h() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.C.clear();
            for (int i = 0; i < childCount; i++) {
                View childAt = MotionLayout.this.getChildAt(i);
                MotionLayout.this.C.put(childAt, new m5(childAt));
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt2 = MotionLayout.this.getChildAt(i2);
                m5 m5Var = MotionLayout.this.C.get(childAt2);
                if (m5Var != null) {
                    if (this.c != null) {
                        ConstraintWidget constraintWidgetA = a(this.f1215a, childAt2);
                        if (constraintWidgetA != null) {
                            m5Var.b(constraintWidgetA, this.c);
                        } else if (MotionLayout.this.O != 0) {
                            Log.e("MotionLayout", z4.a() + "no widget for  " + z4.a(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.d != null) {
                        ConstraintWidget constraintWidgetA2 = a(this.b, childAt2);
                        if (constraintWidgetA2 != null) {
                            m5Var.a(constraintWidgetA2, this.d);
                        } else if (MotionLayout.this.O != 0) {
                            Log.e("MotionLayout", z4.a() + "no widget for  " + z4.a(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        public boolean a(int i, int i2) {
            return (i == this.f1216e && i2 == this.f) ? false : true;
        }
    }

    public static class g implements f {
        public static g b = new g();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public VelocityTracker f1217a;

        public static g c() {
            b.f1217a = VelocityTracker.obtain();
            return b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f1217a;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float b() {
            VelocityTracker velocityTracker = this.f1217a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void recycle() {
            VelocityTracker velocityTracker = this.f1217a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f1217a = null;
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(int i) {
            VelocityTracker velocityTracker = this.f1217a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float a() {
            VelocityTracker velocityTracker = this.f1217a;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }
    }

    public void i() {
        int iIntValue;
        ArrayList<i> arrayList;
        if ((this.L != null || ((arrayList = this.h0) != null && !arrayList.isEmpty())) && this.l0 == -1) {
            this.l0 = this.x;
            if (this.D0.isEmpty()) {
                iIntValue = -1;
            } else {
                iIntValue = this.D0.get(r0.size() - 1).intValue();
            }
            int i2 = this.x;
            if (iIntValue != i2 && i2 != -1) {
                this.D0.add(Integer.valueOf(i2));
            }
        }
        m();
    }

    public boolean j() {
        return this.B;
    }

    public f k() {
        return g.c();
    }

    public class c extends n5 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1212a = 0.0f;
        public float b = 0.0f;
        public float c;

        public c() {
        }

        public void a(float f, float f2, float f3) {
            this.f1212a = f;
            this.b = f2;
            this.c = f3;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2;
            float f3;
            float f4 = this.f1212a;
            if (f4 > 0.0f) {
                float f5 = this.c;
                if (f4 / f5 < f) {
                    f = f4 / f5;
                }
                MotionLayout motionLayout = MotionLayout.this;
                float f6 = this.f1212a;
                float f7 = this.c;
                motionLayout.v = f6 - (f7 * f);
                f2 = (f6 * f) - (((f7 * f) * f) / 2.0f);
                f3 = this.b;
            } else {
                float f8 = this.c;
                if ((-f4) / f8 < f) {
                    f = (-f4) / f8;
                }
                MotionLayout motionLayout2 = MotionLayout.this;
                float f9 = this.f1212a;
                float f10 = this.c;
                motionLayout2.v = (f10 * f) + f9;
                f2 = (f9 * f) + (((f10 * f) * f) / 2.0f);
                f3 = this.b;
            }
            return f2 + f3;
        }

        @Override // supwisdom.n5
        public float a() {
            return MotionLayout.this.v;
        }
    }

    public void b(int i2, int i3, int i4) {
        g7 g7Var;
        int iA;
        p5 p5Var = this.t;
        if (p5Var != null && (g7Var = p5Var.b) != null && (iA = g7Var.a(this.x, i2, i3, i4)) != -1) {
            i2 = iA;
        }
        int i5 = this.x;
        if (i5 == i2) {
            return;
        }
        if (this.w == i2) {
            a(0.0f);
            return;
        }
        if (this.y == i2) {
            a(1.0f);
            return;
        }
        this.y = i2;
        if (i5 != -1) {
            a(i5, i2);
            a(1.0f);
            this.G = 0.0f;
            p();
            return;
        }
        this.Q = false;
        this.I = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.H = getNanoTime();
        this.D = getNanoTime();
        this.J = false;
        this.u = null;
        this.E = this.t.d() / 1000.0f;
        this.w = -1;
        this.t.a(-1, this.y);
        this.t.k();
        int childCount = getChildCount();
        this.C.clear();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            this.C.put(childAt, new m5(childAt));
        }
        this.K = true;
        this.z0.a(this.c, null, this.t.a(i2));
        n();
        this.z0.a();
        f();
        int width = getWidth();
        int height = getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            m5 m5Var = this.C.get(getChildAt(i7));
            this.t.a(m5Var);
            m5Var.a(width, height, this.E, getNanoTime());
        }
        float fJ = this.t.j();
        if (fJ != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                m5 m5Var2 = this.C.get(getChildAt(i8));
                float fC = m5Var2.c() + m5Var2.b();
                fMin = Math.min(fMin, fC);
                fMax = Math.max(fMax, fC);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                m5 m5Var3 = this.C.get(getChildAt(i9));
                float fB = m5Var3.b();
                float fC2 = m5Var3.c();
                m5Var3.l = 1.0f / (1.0f - fJ);
                m5Var3.k = fJ - ((((fB + fC2) - fMin) * fJ) / (fMax - fMin));
            }
        }
        this.F = 0.0f;
        this.G = 0.0f;
        this.K = true;
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void c(int i2) {
        this.k = null;
    }

    public p5.b d(int i2) {
        return this.t.c(i2);
    }

    public void e(int i2) {
        if (!isAttachedToWindow()) {
            if (this.x0 == null) {
                this.x0 = new h();
            }
            this.x0.a(i2);
            return;
        }
        b(i2, -1, -1);
    }

    public final void f() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            m5 m5Var = this.C.get(childAt);
            if (m5Var != null) {
                m5Var.a(childAt);
            }
        }
    }

    public final void g() {
        boolean z;
        float fSignum = Math.signum(this.I - this.G);
        long nanoTime = getNanoTime();
        float interpolation = this.G + (!(this.u instanceof x4) ? (((nanoTime - this.H) * fSignum) * 1.0E-9f) / this.E : 0.0f);
        if (this.J) {
            interpolation = this.I;
        }
        if ((fSignum <= 0.0f || interpolation < this.I) && (fSignum > 0.0f || interpolation > this.I)) {
            z = false;
        } else {
            interpolation = this.I;
            z = true;
        }
        Interpolator interpolator = this.u;
        if (interpolator != null && !z) {
            if (this.Q) {
                interpolation = interpolator.getInterpolation((nanoTime - this.D) * 1.0E-9f);
            } else {
                interpolation = interpolator.getInterpolation(interpolation);
            }
        }
        if ((fSignum > 0.0f && interpolation >= this.I) || (fSignum <= 0.0f && interpolation <= this.I)) {
            interpolation = this.I;
        }
        this.u0 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            m5 m5Var = this.C.get(childAt);
            if (m5Var != null) {
                m5Var.a(childAt, interpolation, nanoTime2, this.v0);
            }
        }
        if (this.n0) {
            requestLayout();
        }
    }

    public final void h() {
        ArrayList<i> arrayList;
        if ((this.L == null && ((arrayList = this.h0) == null || arrayList.isEmpty())) || this.m0 == this.F) {
            return;
        }
        if (this.l0 != -1) {
            i iVar = this.L;
            if (iVar != null) {
                iVar.a(this, this.w, this.y);
            }
            ArrayList<i> arrayList2 = this.h0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.w, this.y);
                }
            }
        }
        this.l0 = -1;
        float f2 = this.F;
        this.m0 = f2;
        i iVar2 = this.L;
        if (iVar2 != null) {
            iVar2.a(this, this.w, this.y, f2);
        }
        ArrayList<i> arrayList3 = this.h0;
        if (arrayList3 != null) {
            Iterator<i> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().a(this, this.w, this.y, this.F);
            }
        }
    }

    public class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1218a = Float.NaN;
        public float b = Float.NaN;
        public int c = -1;
        public int d = -1;

        public h() {
        }

        public void a() {
            if (this.c != -1 || this.d != -1) {
                int i = this.c;
                if (i == -1) {
                    MotionLayout.this.e(this.d);
                } else {
                    int i2 = this.d;
                    if (i2 == -1) {
                        MotionLayout.this.a(i, -1, -1);
                    } else {
                        MotionLayout.this.a(i, i2);
                    }
                }
                MotionLayout.this.setState(j.SETUP);
            }
            if (Float.isNaN(this.b)) {
                if (Float.isNaN(this.f1218a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f1218a);
            } else {
                MotionLayout.this.a(this.f1218a, this.b);
                this.f1218a = Float.NaN;
                this.b = Float.NaN;
                this.c = -1;
                this.d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f1218a);
            bundle.putFloat("motion.velocity", this.b);
            bundle.putInt("motion.StartState", this.c);
            bundle.putInt("motion.EndState", this.d);
            return bundle;
        }

        public void c() {
            this.d = MotionLayout.this.y;
            this.c = MotionLayout.this.w;
            this.b = MotionLayout.this.getVelocity();
            this.f1218a = MotionLayout.this.getProgress();
        }

        public void b(float f) {
            this.b = f;
        }

        public void b(int i) {
            this.c = i;
        }

        public void a(Bundle bundle) {
            this.f1218a = bundle.getFloat("motion.progress");
            this.b = bundle.getFloat("motion.velocity");
            this.c = bundle.getInt("motion.StartState");
            this.d = bundle.getInt("motion.EndState");
        }

        public void a(float f) {
            this.f1218a = f;
        }

        public void a(int i) {
            this.d = i;
        }
    }

    public void a(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.x0 == null) {
                this.x0 = new h();
            }
            this.x0.b(i2);
            this.x0.a(i3);
            return;
        }
        p5 p5Var = this.t;
        if (p5Var != null) {
            this.w = i2;
            this.y = i3;
            p5Var.a(i2, i3);
            this.z0.a(this.c, this.t.a(i2), this.t.a(i3));
            n();
            this.G = 0.0f;
            q();
        }
    }

    public final void e() {
        p5 p5Var = this.t;
        if (p5Var == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iK = p5Var.k();
        p5 p5Var2 = this.t;
        a(iK, p5Var2.a(p5Var2.k()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (p5.b bVar : this.t.c()) {
            if (bVar == this.t.c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            a(bVar);
            int iE = bVar.e();
            int iC = bVar.c();
            String strA = z4.a(getContext(), iE);
            String strA2 = z4.a(getContext(), iC);
            if (sparseIntArray.get(iE) == iC) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strA + "->" + strA2);
            }
            if (sparseIntArray2.get(iC) == iE) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strA + "->" + strA2);
            }
            sparseIntArray.put(iE, iC);
            sparseIntArray2.put(iC, iE);
            if (this.t.a(iE) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strA);
            }
            if (this.t.a(iC) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strA);
            }
        }
    }

    public void a(int i2, int i3, int i4) {
        setState(j.SETUP);
        this.x = i2;
        this.w = -1;
        this.y = -1;
        d7 d7Var = this.k;
        if (d7Var != null) {
            d7Var.a(i2, i3, i4);
            return;
        }
        p5 p5Var = this.t;
        if (p5Var != null) {
            p5Var.a(i2).b(this);
        }
    }

    public void setTransition(p5.b bVar) {
        this.t.a(bVar);
        setState(j.SETUP);
        if (this.x == this.t.e()) {
            this.G = 1.0f;
            this.F = 1.0f;
            this.I = 1.0f;
        } else {
            this.G = 0.0f;
            this.F = 0.0f;
            this.I = 0.0f;
        }
        this.H = bVar.a(1) ? -1L : getNanoTime();
        int iK = this.t.k();
        int iE = this.t.e();
        if (iK == this.w && iE == this.y) {
            return;
        }
        this.w = iK;
        this.y = iE;
        this.t.a(iK, iE);
        this.z0.a(this.c, this.t.a(this.w), this.t.a(this.y));
        this.z0.c(this.w, this.y);
        this.z0.b();
        n();
    }

    public void a(float f2, float f3) {
        if (!isAttachedToWindow()) {
            if (this.x0 == null) {
                this.x0 = new h();
            }
            this.x0.a(f2);
            this.x0.b(f3);
            return;
        }
        setProgress(f2);
        setState(j.MOVING);
        this.v = f3;
        a(1.0f);
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f1213a;
        public int[] b;
        public float[] c;
        public Path d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f1214e;
        public Paint f;
        public Paint g;
        public Paint h;
        public Paint i;
        public float[] j;
        public DashPathEffect k;
        public int l;
        public Rect m = new Rect();
        public boolean n = false;
        public int o;

        public d() {
            this.o = 1;
            Paint paint = new Paint();
            this.f1214e = paint;
            paint.setAntiAlias(true);
            this.f1214e.setColor(-21965);
            this.f1214e.setStrokeWidth(2.0f);
            this.f1214e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f = paint2;
            paint2.setAntiAlias(true);
            this.f.setColor(-2067046);
            this.f.setStrokeWidth(2.0f);
            this.f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.g = paint3;
            paint3.setAntiAlias(true);
            this.g.setColor(-13391360);
            this.g.setStrokeWidth(2.0f);
            this.g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.h = paint4;
            paint4.setAntiAlias(true);
            this.h.setColor(-13391360);
            this.h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.k = dashPathEffect;
            this.g.setPathEffect(dashPathEffect);
            this.c = new float[100];
            this.b = new int[50];
            if (this.n) {
                this.f1214e.setStrokeWidth(8.0f);
                this.i.setStrokeWidth(8.0f);
                this.f.setStrokeWidth(8.0f);
                this.o = 4;
            }
        }

        public void a(Canvas canvas, HashMap<View, m5> map, int i, int i2) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i2 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.y) + Constants.COLON_SEPARATOR + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f1214e);
            }
            for (m5 m5Var : map.values()) {
                int iA = m5Var.a();
                if (i2 > 0 && iA == 0) {
                    iA = 1;
                }
                if (iA != 0) {
                    this.l = m5Var.a(this.c, this.b);
                    if (iA >= 1) {
                        int i3 = i / 16;
                        float[] fArr = this.f1213a;
                        if (fArr == null || fArr.length != i3 * 2) {
                            this.f1213a = new float[i3 * 2];
                            this.d = new Path();
                        }
                        int i4 = this.o;
                        canvas.translate(i4, i4);
                        this.f1214e.setColor(1996488704);
                        this.i.setColor(1996488704);
                        this.f.setColor(1996488704);
                        this.g.setColor(1996488704);
                        m5Var.a(this.f1213a, i3);
                        a(canvas, iA, this.l, m5Var);
                        this.f1214e.setColor(-21965);
                        this.f.setColor(-2067046);
                        this.i.setColor(-2067046);
                        this.g.setColor(-13391360);
                        int i5 = this.o;
                        canvas.translate(-i5, -i5);
                        a(canvas, iA, this.l, m5Var);
                        if (iA == 5) {
                            a(canvas, m5Var);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public final void b(Canvas canvas, int i, int i2, m5 m5Var) {
            int width;
            int height;
            float f;
            float f2;
            int i3;
            View view = m5Var.f8359a;
            if (view != null) {
                width = view.getWidth();
                height = m5Var.f8359a.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i4 = 1; i4 < i2 - 1; i4++) {
                if (i != 4 || this.b[i4 - 1] != 0) {
                    float[] fArr = this.c;
                    int i5 = i4 * 2;
                    float f3 = fArr[i5];
                    float f4 = fArr[i5 + 1];
                    this.d.reset();
                    this.d.moveTo(f3, f4 + 10.0f);
                    this.d.lineTo(f3 + 10.0f, f4);
                    this.d.lineTo(f3, f4 - 10.0f);
                    this.d.lineTo(f3 - 10.0f, f4);
                    this.d.close();
                    int i6 = i4 - 1;
                    m5Var.a(i6);
                    if (i == 4) {
                        int[] iArr = this.b;
                        if (iArr[i6] == 1) {
                            b(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i6] == 2) {
                            a(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else {
                            if (iArr[i6] == 3) {
                                i3 = 3;
                                f = f4;
                                f2 = f3;
                                a(canvas, f3 - 0.0f, f4 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.d, this.i);
                        }
                        f = f4;
                        f2 = f3;
                        i3 = 3;
                        canvas.drawPath(this.d, this.i);
                    } else {
                        f = f4;
                        f2 = f3;
                        i3 = 3;
                    }
                    if (i == 2) {
                        b(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == i3) {
                        a(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 6) {
                        a(canvas, f2 - 0.0f, f - 0.0f, width, height);
                    }
                    canvas.drawPath(this.d, this.i);
                }
            }
            float[] fArr2 = this.f1213a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f);
                float[] fArr3 = this.f1213a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f);
            }
        }

        public final void c(Canvas canvas) {
            float[] fArr = this.f1213a;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.g);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.g);
        }

        public final void d(Canvas canvas) {
            float[] fArr = this.f1213a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.g);
        }

        public final void b(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.l; i++) {
                if (this.b[i] == 1) {
                    z = true;
                }
                if (this.b[i] == 2) {
                    z2 = true;
                }
            }
            if (z) {
                d(canvas);
            }
            if (z2) {
                c(canvas);
            }
        }

        public void a(Canvas canvas, int i, int i2, m5 m5Var) {
            if (i == 4) {
                b(canvas);
            }
            if (i == 2) {
                d(canvas);
            }
            if (i == 3) {
                c(canvas);
            }
            a(canvas);
            b(canvas, i, i2, m5Var);
        }

        public final void b(Canvas canvas, float f, float f2) {
            float[] fArr = this.f1213a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f3 - f5, f4 - f6);
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f - f3) * f7) + ((f2 - f4) * f8)) / (fHypot * fHypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float fHypot2 = (float) Math.hypot(f10 - f, f11 - f2);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            a(str, this.h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.m.width() / 2), -20.0f, this.h);
            canvas.drawLine(f, f2, f10, f11, this.g);
        }

        public final void a(Canvas canvas) {
            canvas.drawLines(this.f1213a, this.f1214e);
        }

        public void a(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.m);
        }

        public final void a(Canvas canvas, float f, float f2) {
            float[] fArr = this.f1213a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float fMin = Math.min(f3, f5);
            float fMax = Math.max(f4, f6);
            float fMin2 = f - Math.min(f3, f5);
            float fMax2 = Math.max(f4, f6) - f2;
            String str = "" + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f5 - f3))) + 0.5d)) / 100.0f);
            a(str, this.h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.m.width() / 2)) + fMin, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(f3, f5), f2, this.g);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f6 - f4))) + 0.5d)) / 100.0f);
            a(str2, this.h);
            canvas.drawText(str2, f + 5.0f, fMax - ((fMax2 / 2.0f) - (this.m.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), this.g);
        }

        public final void a(Canvas canvas, float f, float f2, int i, int i2) {
            String str = "" + (((int) (((double) (((f - (i / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i))) + 0.5d)) / 100.0f);
            a(str, this.h);
            canvas.drawText(str, ((f / 2.0f) - (this.m.width() / 2)) + 0.0f, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.g);
            String str2 = "" + (((int) (((double) (((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i2))) + 0.5d)) / 100.0f);
            a(str2, this.h);
            canvas.drawText(str2, f + 5.0f, 0.0f - ((f2 / 2.0f) - (this.m.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.g);
        }

        public final void a(Canvas canvas, m5 m5Var) {
            this.d.reset();
            for (int i = 0; i <= 50; i++) {
                m5Var.a(i / 50, this.j, 0);
                Path path = this.d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.d.close();
            }
            this.f1214e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.d, this.f1214e);
            canvas.translate(-2.0f, -2.0f);
            this.f1214e.setColor(-65536);
            canvas.drawPath(this.d, this.f1214e);
        }
    }

    public void a(int i2, float f2, float f3) {
        if (this.t == null || this.G == f2) {
            return;
        }
        this.Q = true;
        this.D = getNanoTime();
        this.E = this.t.d() / 1000.0f;
        this.I = f2;
        this.K = true;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            if (i2 == 1) {
                f2 = 0.0f;
            } else if (i2 == 2) {
                f2 = 1.0f;
            }
            this.R.a(this.G, f2, f3, this.E, this.t.g(), this.t.h());
            int i3 = this.x;
            this.I = f2;
            this.x = i3;
            this.u = this.R;
        } else if (i2 == 4) {
            this.S.a(f3, this.G, this.t.g());
            this.u = this.S;
        } else if (i2 == 5) {
            if (a(f3, this.G, this.t.g())) {
                this.S.a(f3, this.G, this.t.g());
                this.u = this.S;
            } else {
                this.R.a(this.G, f2, f3, this.E, this.t.g(), this.t.h());
                this.v = 0.0f;
                int i4 = this.x;
                this.I = f2;
                this.x = i4;
                this.u = this.R;
            }
        }
        this.J = false;
        this.D = getNanoTime();
        invalidate();
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.v = 0.0f;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap<>();
        this.D = 0L;
        this.E = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.I = 0.0f;
        this.K = false;
        this.O = 0;
        this.Q = false;
        this.R = new x4();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = 0;
        this.j0 = -1L;
        this.k0 = 0.0f;
        this.l0 = 0;
        this.m0 = 0.0f;
        this.n0 = false;
        this.v0 = new d5();
        this.w0 = false;
        this.y0 = j.UNDEFINED;
        this.z0 = new e();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = new ArrayList<>();
        a(attributeSet);
    }

    public void a(float f2) {
        if (this.t == null) {
            return;
        }
        float f3 = this.G;
        float f4 = this.F;
        if (f3 != f4 && this.J) {
            this.G = f4;
        }
        float f5 = this.G;
        if (f5 == f2) {
            return;
        }
        this.Q = false;
        this.I = f2;
        this.E = this.t.d() / 1000.0f;
        setProgress(this.I);
        this.u = this.t.f();
        this.J = false;
        this.D = getNanoTime();
        this.K = true;
        this.F = f5;
        this.G = f5;
        invalidate();
    }

    public void a(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float fA = this.v;
        float f5 = this.G;
        if (this.u != null) {
            float fSignum = Math.signum(this.I - f5);
            float interpolation = this.u.getInterpolation(this.G + 1.0E-5f);
            float interpolation2 = this.u.getInterpolation(this.G);
            fA = (fSignum * ((interpolation - interpolation2) / 1.0E-5f)) / this.E;
            f4 = interpolation2;
        } else {
            f4 = f5;
        }
        Interpolator interpolator = this.u;
        if (interpolator instanceof n5) {
            fA = ((n5) interpolator).a();
        }
        m5 m5Var = this.C.get(view);
        if ((i2 & 1) == 0) {
            m5Var.a(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            m5Var.a(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * fA;
            fArr[1] = fArr[1] * fA;
        }
    }

    @Override // supwisdom.cb
    public boolean a(View view, View view2, int i2, int i3) {
        p5.b bVar;
        p5 p5Var = this.t;
        return (p5Var == null || (bVar = p5Var.c) == null || bVar.f() == null || (this.t.c.f().a() & 2) != 0) ? false : true;
    }

    @Override // supwisdom.cb
    public void a(View view, int i2) {
        p5 p5Var = this.t;
        if (p5Var == null) {
            return;
        }
        float f2 = this.a0;
        float f3 = this.d0;
        p5Var.c(f2 / f3, this.b0 / f3);
    }

    @Override // supwisdom.db
    public void a(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.W || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.W = false;
    }

    @Override // supwisdom.cb
    public void a(View view, int i2, int i3, int[] iArr, int i4) {
        p5.b bVar;
        s5 s5VarF;
        int iE;
        p5 p5Var = this.t;
        if (p5Var == null || (bVar = p5Var.c) == null || !bVar.g()) {
            return;
        }
        p5.b bVar2 = this.t.c;
        if (bVar2 == null || !bVar2.g() || (s5VarF = bVar2.f()) == null || (iE = s5VarF.e()) == -1 || view.getId() == iE) {
            p5 p5Var2 = this.t;
            if (p5Var2 != null && p5Var2.i()) {
                float f2 = this.F;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (bVar2.f() != null && (this.t.c.f().a() & 1) != 0) {
                float fA = this.t.a(i2, i3);
                if ((this.G <= 0.0f && fA < 0.0f) || (this.G >= 1.0f && fA > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(view));
                        return;
                    }
                    return;
                }
            }
            float f3 = this.F;
            long nanoTime = getNanoTime();
            float f4 = i2;
            this.a0 = f4;
            float f5 = i3;
            this.b0 = f5;
            this.d0 = (float) ((nanoTime - this.c0) * 1.0E-9d);
            this.c0 = nanoTime;
            this.t.b(f4, f5);
            if (f3 != this.F) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            a(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.W = true;
        }
    }

    public void a(boolean z) {
        float f2;
        boolean z2;
        int i2;
        float interpolation;
        boolean z3;
        if (this.H == -1) {
            this.H = getNanoTime();
        }
        float f3 = this.G;
        if (f3 > 0.0f && f3 < 1.0f) {
            this.x = -1;
        }
        boolean z4 = false;
        if (this.e0 || (this.K && (z || this.I != this.G))) {
            float fSignum = Math.signum(this.I - this.G);
            long nanoTime = getNanoTime();
            if (this.u instanceof n5) {
                f2 = 0.0f;
            } else {
                f2 = (((nanoTime - this.H) * fSignum) * 1.0E-9f) / this.E;
                this.v = f2;
            }
            float f4 = this.G + f2;
            if (this.J) {
                f4 = this.I;
            }
            if ((fSignum <= 0.0f || f4 < this.I) && (fSignum > 0.0f || f4 > this.I)) {
                z2 = false;
            } else {
                f4 = this.I;
                this.K = false;
                z2 = true;
            }
            this.G = f4;
            this.F = f4;
            this.H = nanoTime;
            Interpolator interpolator = this.u;
            if (interpolator != null && !z2) {
                if (this.Q) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.D) * 1.0E-9f);
                    this.G = interpolation;
                    this.H = nanoTime;
                    Interpolator interpolator2 = this.u;
                    if (interpolator2 instanceof n5) {
                        float fA = ((n5) interpolator2).a();
                        this.v = fA;
                        if (Math.abs(fA) * this.E <= 1.0E-5f) {
                            this.K = false;
                        }
                        if (fA > 0.0f && interpolation >= 1.0f) {
                            this.G = 1.0f;
                            this.K = false;
                            interpolation = 1.0f;
                        }
                        if (fA < 0.0f && interpolation <= 0.0f) {
                            this.G = 0.0f;
                            this.K = false;
                            f4 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f4);
                    Interpolator interpolator3 = this.u;
                    if (interpolator3 instanceof n5) {
                        this.v = ((n5) interpolator3).a();
                    } else {
                        this.v = ((interpolator3.getInterpolation(f4 + f2) - interpolation) * fSignum) / f2;
                    }
                }
                f4 = interpolation;
            }
            if (Math.abs(this.v) > 1.0E-5f) {
                setState(j.MOVING);
            }
            if ((fSignum > 0.0f && f4 >= this.I) || (fSignum <= 0.0f && f4 <= this.I)) {
                f4 = this.I;
                this.K = false;
            }
            if (f4 >= 1.0f || f4 <= 0.0f) {
                this.K = false;
                setState(j.FINISHED);
            }
            int childCount = getChildCount();
            this.e0 = false;
            long nanoTime2 = getNanoTime();
            this.u0 = f4;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                m5 m5Var = this.C.get(childAt);
                if (m5Var != null) {
                    this.e0 = m5Var.a(childAt, f4, nanoTime2, this.v0) | this.e0;
                }
            }
            boolean z5 = (fSignum > 0.0f && f4 >= this.I) || (fSignum <= 0.0f && f4 <= this.I);
            if (!this.e0 && !this.K && z5) {
                setState(j.FINISHED);
            }
            if (this.n0) {
                requestLayout();
            }
            this.e0 = (!z5) | this.e0;
            if (f4 <= 0.0f && (i2 = this.w) != -1 && this.x != i2) {
                this.x = i2;
                this.t.a(i2).a(this);
                setState(j.FINISHED);
                z4 = true;
            }
            if (f4 >= 1.0d) {
                int i4 = this.x;
                int i5 = this.y;
                if (i4 != i5) {
                    this.x = i5;
                    this.t.a(i5).a(this);
                    setState(j.FINISHED);
                    z4 = true;
                }
            }
            if (this.e0 || this.K) {
                invalidate();
            } else if ((fSignum > 0.0f && f4 == 1.0f) || (fSignum < 0.0f && f4 == 0.0f)) {
                setState(j.FINISHED);
            }
            if ((!this.e0 && this.K && fSignum > 0.0f && f4 == 1.0f) || (fSignum < 0.0f && f4 == 0.0f)) {
                l();
            }
        }
        float f5 = this.G;
        if (f5 >= 1.0f) {
            z3 = this.x == this.y ? z4 : true;
            this.x = this.y;
        } else {
            if (f5 <= 0.0f) {
                z3 = this.x == this.w ? z4 : true;
                this.x = this.w;
            }
            this.A0 |= z4;
            if (z4 && !this.w0) {
                requestLayout();
            }
            this.F = this.G;
        }
        z4 = z3;
        this.A0 |= z4;
        if (z4) {
            requestLayout();
        }
        this.F = this.G;
    }

    public final void a(AttributeSet attributeSet) {
        p5 p5Var;
        E0 = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionLayout_layoutDescription) {
                    this.t = new p5(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R.styleable.MotionLayout_currentState) {
                    this.x = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R.styleable.MotionLayout_motionProgress) {
                    this.I = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.K = true;
                } else if (index == R.styleable.MotionLayout_applyMotionScene) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == R.styleable.MotionLayout_showPaths) {
                    if (this.O == 0) {
                        this.O = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R.styleable.MotionLayout_motionDebug) {
                    this.O = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.t == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.t = null;
            }
        }
        if (this.O != 0) {
            e();
        }
        if (this.x != -1 || (p5Var = this.t) == null) {
            return;
        }
        this.x = p5Var.k();
        this.w = this.t.k();
        this.y = this.t.e();
    }

    public final void a(int i2, e7 e7Var) {
        String strA = z4.a(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strA + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (e7Var.b(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strA + " NO CONSTRAINTS for " + z4.a(childAt));
            }
        }
        int[] iArrA = e7Var.a();
        for (int i4 = 0; i4 < iArrA.length; i4++) {
            int i5 = iArrA[i4];
            String strA2 = z4.a(getContext(), i5);
            if (findViewById(iArrA[i4]) == null) {
                Log.w("MotionLayout", "CHECK: " + strA + " NO View matches id " + strA2);
            }
            if (e7Var.c(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strA + "(" + strA2 + ") no LAYOUT_HEIGHT");
            }
            if (e7Var.g(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strA + "(" + strA2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    public final void a(p5.b bVar) {
        Log.v("MotionLayout", "CHECK: transition = " + bVar.a(getContext()));
        Log.v("MotionLayout", "CHECK: transition.setDuration = " + bVar.b());
        if (bVar.e() == bVar.c()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    public final boolean a(float f2, float f3, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (a(view.getLeft() + f2, view.getTop() + f3, viewGroup.getChildAt(i2), motionEvent)) {
                    return true;
                }
            }
        }
        this.B0.set(view.getLeft() + f2, view.getTop() + f3, f2 + view.getRight(), f3 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (this.B0.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void a(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, m5> map = this.C;
        View viewB = b(i2);
        m5 m5Var = map.get(viewB);
        if (m5Var != null) {
            m5Var.a(f2, f3, f4, fArr);
            float y = viewB.getY();
            int i3 = ((f2 - this.M) > 0.0f ? 1 : ((f2 - this.M) == 0.0f ? 0 : -1));
            this.M = f2;
            this.N = y;
            return;
        }
        if (viewB == null) {
            resourceName = "" + i2;
        } else {
            resourceName = viewB.getContext().getResources().getResourceName(i2);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    public void a(int i2, boolean z, float f2) {
        i iVar = this.L;
        if (iVar != null) {
            iVar.a(this, i2, z, f2);
        }
        ArrayList<i> arrayList = this.h0;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a(this, i2, z, f2);
            }
        }
    }
}
