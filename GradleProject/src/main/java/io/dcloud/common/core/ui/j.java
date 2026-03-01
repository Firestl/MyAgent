package io.dcloud.common.core.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import java.util.Arrays;
import supwisdom.du;
import supwisdom.kb;
import supwisdom.xu;
import supwisdom.zu;

/* JADX INFO: loaded from: classes2.dex */
public class j {
    public static final Interpolator A = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6412a;
    public int b;
    public int c;
    public float[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f6413e;
    public float[] f;
    public float[] g;
    public int[] h;
    public int[] i;
    public int[] j;
    public int k;
    public VelocityTracker l;
    public float m;
    public float n;
    public int o;
    public int p;
    public xu q;
    public final c r;
    public View s;
    public boolean t;
    public final ViewGroup u;
    public int v;
    public io.dcloud.common.core.ui.b w;
    public io.dcloud.common.core.ui.a x;
    public final Runnable y;
    public int z;

    public static class a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.d(0);
        }
    }

    public static abstract class c {
        public int a(int i) {
            return i;
        }

        public abstract int a(View view);

        public abstract int a(View view, int i, int i2);

        public void a(int i, int i2) {
        }

        public abstract void a(View view, float f, float f2);

        public void a(View view, int i) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public abstract boolean a(io.dcloud.common.core.ui.b bVar);

        public abstract int b(View view);

        public void b(int i, int i2) {
        }

        public boolean b(int i) {
            return false;
        }

        public abstract boolean b(View view, int i);

        public void c(int i) {
        }
    }

    public j(ViewGroup viewGroup, c cVar, io.dcloud.common.core.ui.a aVar) {
        this(viewGroup.getContext(), viewGroup, cVar, aVar);
    }

    public void a(View view, int i) {
        if (view.getParent() == this.u) {
            this.s = view;
            this.c = i;
            this.r.a(view, i);
            d(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the SwipeBackHelper's tracked parent view (" + this.u + ")");
    }

    public void b(float f) {
        this.n = f;
    }

    public int c() {
        return this.f6412a;
    }

    public void d(int i) {
        if (this.f6412a != i) {
            this.f6412a = i;
            this.r.c(i);
            if (i == 0) {
                this.s = null;
            }
        }
    }

    public void e(int i) {
        this.o = i;
    }

    public void f(int i) {
        this.p = i;
    }

    public j(Context context, ViewGroup viewGroup, c cVar, io.dcloud.common.core.ui.a aVar) {
        this.c = -1;
        this.v = 170;
        this.x = null;
        this.y = new b();
        this.z = 0;
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.x = aVar;
        this.u = viewGroup;
        this.r = cVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.b = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.q = xu.a(context, A);
    }

    @TargetApi(11)
    private boolean b(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.a();
            d(0);
            return false;
        }
        this.q.a(left, top, i5, i6, a(this.s, i5, i6, i3, i4));
        d(2);
        return true;
    }

    public boolean c(int i) {
        return ((1 << i) & this.k) != 0;
    }

    public boolean e(int i, int i2) {
        if (this.t) {
            return b(i, i2, (int) kb.a(this.l, this.c), (int) kb.b(this.l, this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.j.c(android.view.MotionEvent):boolean");
    }

    public boolean d(int i, int i2) {
        return c(i2) && (i & this.h[i2]) != 0;
    }

    private void d() {
        this.l.computeCurrentVelocity(1000, this.m);
        a(a(kb.a(this.l, this.c), this.n, this.m), a(kb.b(this.l, this.c), this.n, this.m));
    }

    public void a() {
        this.c = -1;
        b();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    private int b(int i, int i2, int i3) {
        int iAbs;
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f = width / 2;
        float fA = f + (a(Math.min(1.0f, Math.abs(i) / width)) * f);
        int iAbs2 = Math.abs(i2);
        if (iAbs2 > 0) {
            iAbs = Math.round(Math.abs(fA / iAbs2) * 1000.0f) * 4;
        } else {
            iAbs = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(iAbs, 600);
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int iA = a(i3, (int) this.n, (int) this.m);
        int iA2 = a(i4, (int) this.n, (int) this.m);
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(i2);
        int iAbs3 = Math.abs(iA);
        int iAbs4 = Math.abs(iA2);
        int i5 = iAbs3 + iAbs4;
        int i6 = iAbs + iAbs2;
        if (iA != 0) {
            f = iAbs3;
            f2 = i5;
        } else {
            f = iAbs;
            f2 = i6;
        }
        float f5 = f / f2;
        if (iA2 != 0) {
            f3 = iAbs4;
            f4 = i5;
        } else {
            f3 = iAbs2;
            f4 = i6;
        }
        return (int) ((b(i, iA, this.r.a(view)) * f5) + (b(i2, iA2, this.r.b(view)) * (f3 / f4)));
    }

    private void b() {
        float[] fArr = this.d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f6413e, 0.0f);
        Arrays.fill(this.f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    private int a(int i, int i2, int i3) {
        int iAbs = Math.abs(i);
        if (iAbs < i2) {
            return 0;
        }
        return iAbs > i3 ? i > 0 ? i3 : -i3 : i;
    }

    private float a(float f, float f2, float f3) {
        float fAbs = Math.abs(f);
        if (fAbs < f2) {
            return 0.0f;
        }
        return fAbs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }

    private float a(float f) {
        return (float) Math.sin((float) (((double) (f - 0.5f)) * 0.4712389167638204d));
    }

    public boolean a(boolean z) {
        if (this.f6412a == 2) {
            boolean zB = this.q.b();
            int iC = this.q.c();
            int iD = this.q.d();
            int left = iC - this.s.getLeft();
            int top = iD - this.s.getTop();
            if (left != 0) {
                this.s.offsetLeftAndRight(left);
            }
            if (left != 0 || top != 0) {
                this.r.a(this.s, iC, iD, left, top);
            }
            if (zB && iC == this.q.e() && iD == this.q.f()) {
                this.q.a();
                zB = this.q.g();
            }
            if (!zB) {
                if (z) {
                    this.u.post(this.y);
                } else {
                    d(0);
                }
            }
        }
        return this.f6412a == 2;
    }

    private void b(int i) {
        float[] fArr = this.d;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f6413e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.d = fArr2;
            this.f6413e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    private void a(float f, float f2) {
        this.t = true;
        this.r.a(this.s, f, f2);
        this.t = false;
        if (this.f6412a == 1) {
            d(0);
        }
    }

    private void a(int i) {
        float[] fArr = this.d;
        if (fArr == null) {
            return;
        }
        fArr[i] = 0.0f;
        this.f6413e[i] = 0.0f;
        this.f[i] = 0.0f;
        this.g[i] = 0.0f;
        this.h[i] = 0;
        this.i[i] = 0;
        this.j[i] = 0;
        this.k = (~(1 << i)) & this.k;
    }

    private void b(float f, float f2, int i) {
        b(i);
        float[] fArr = this.d;
        this.f[i] = f;
        fArr[i] = f;
        float[] fArr2 = this.f6413e;
        this.g[i] = f2;
        fArr2[i] = f2;
        this.h[i] = b((int) f, (int) f2);
        this.k |= 1 << i;
        this.w = this.x.i();
    }

    @TargetApi(11)
    public void a(MotionEvent motionEvent) {
        int i;
        int iB = du.b(motionEvent);
        int iA = du.a(motionEvent);
        if (iB == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i2 = 0;
        if (iB == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int iB2 = du.b(motionEvent, 0);
            View viewA = a((int) x, (int) y);
            b(x, y, iB2);
            b(viewA, iB2);
            int i3 = this.h[iB2] & this.p;
            if (i3 != 0) {
                this.r.b(i3, iB2);
                return;
            }
            return;
        }
        if (iB == 1) {
            if (this.f6412a == 1) {
                d();
            }
            a();
            return;
        }
        if (iB == 2) {
            io.dcloud.common.core.ui.b bVar = this.w;
            if (bVar == null || bVar.obtainMainView() == null) {
                return;
            }
            if (this.f6412a == 1) {
                int iA2 = du.a(motionEvent, this.c);
                float fC = du.c(motionEvent, iA2);
                float fD = du.d(motionEvent, iA2);
                float[] fArr = this.f;
                int i4 = this.c;
                int i5 = (int) (fC - fArr[i4]);
                int i6 = (int) (fD - this.g[i4]);
                a(this.s.getLeft() + i5, this.s.getTop() + i6, i5, i6);
                b(motionEvent);
                return;
            }
            int iC = du.c(motionEvent);
            while (i2 < iC) {
                int iB3 = du.b(motionEvent, i2);
                float fC2 = du.c(motionEvent, i2);
                float fD2 = du.d(motionEvent, i2);
                float f = fC2 - this.d[iB3];
                float f2 = fD2 - this.f6413e[iB3];
                a(f, f2, iB3);
                if (this.f6412a != 1) {
                    View viewA2 = a((int) fC2, (int) fD2);
                    if (a(viewA2, f, f2) && b(viewA2, iB3)) {
                        break;
                    } else {
                        i2++;
                    }
                } else {
                    break;
                }
            }
            b(motionEvent);
            return;
        }
        if (iB == 3) {
            if (this.f6412a == 1) {
                a(0.0f, 0.0f);
            }
            a();
            return;
        }
        if (iB == 5) {
            int iB4 = du.b(motionEvent, iA);
            float fC3 = du.c(motionEvent, iA);
            float fD3 = du.d(motionEvent, iA);
            b(fC3, fD3, iB4);
            if (this.f6412a == 0) {
                b(a((int) fC3, (int) fD3), iB4);
                int i7 = this.h[iB4] & this.p;
                if (i7 != 0) {
                    this.r.b(i7, iB4);
                    return;
                }
                return;
            }
            if (c((int) fC3, (int) fD3)) {
                b(this.s, iB4);
                return;
            }
            return;
        }
        if (iB != 6) {
            return;
        }
        int iB5 = du.b(motionEvent, iA);
        if (this.f6412a == 1 && iB5 == this.c) {
            int iC2 = du.c(motionEvent);
            while (true) {
                if (i2 >= iC2) {
                    i = -1;
                    break;
                }
                int iB6 = du.b(motionEvent, i2);
                if (iB6 != this.c) {
                    View viewA3 = a((int) du.c(motionEvent, i2), (int) du.d(motionEvent, i2));
                    View view = this.s;
                    if (viewA3 == view && b(view, iB6)) {
                        i = this.c;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                d();
            }
        }
        a(iB5);
    }

    private void b(MotionEvent motionEvent) {
        int iC = du.c(motionEvent);
        for (int i = 0; i < iC; i++) {
            int iB = du.b(motionEvent, i);
            float fC = du.c(motionEvent, i);
            float fD = du.d(motionEvent, i);
            this.f[iB] = fC;
            this.g[iB] = fD;
        }
    }

    public boolean c(int i, int i2) {
        return a(this.s, i, i2);
    }

    public boolean b(View view, int i) {
        if (view == this.s && this.c == i) {
            return true;
        }
        if (view == null || !this.r.b(view, i)) {
            return false;
        }
        this.c = i;
        a(view, i);
        return true;
    }

    private int b(int i, int i2) {
        int i3 = i < this.u.getLeft() + this.o ? 1 : 0;
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        return i2 > this.u.getBottom() - this.o ? i3 | 8 : i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [io.dcloud.common.core.ui.j$c] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void a(float f, float f2, int i) {
        boolean zA = a(f, f2, i, 1);
        ?? r0 = zA;
        if (a(f2, f, i, 4)) {
            r0 = (zA ? 1 : 0) | 4;
        }
        ?? r02 = r0;
        if (a(f, f2, i, 2)) {
            r02 = (r0 == true ? 1 : 0) | 2;
        }
        ?? r03 = r02;
        if (a(f2, f, i, 8)) {
            r03 = (r02 == true ? 1 : 0) | 8;
        }
        if (r03 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | r03;
            this.r.a(r03, i);
        }
    }

    private boolean a(float f, float f2, int i, int i2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        float f3 = this.b;
        if (fAbs <= f3 && fAbs2 <= f3) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.r.b(i2)) {
            return (this.i[i] & i2) == 0 && fAbs > ((float) this.b);
        }
        int[] iArr = this.j;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    private boolean a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.r.a(view) > 0;
        boolean z2 = this.r.b(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.b) : z2 && Math.abs(f2) > ((float) this.b);
        }
        float f3 = (f * f) + (f2 * f2);
        int i = this.b;
        return f3 > ((float) (i * i));
    }

    @TargetApi(11)
    private void a(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i3 != 0) {
            i = this.r.a(this.s, i, i3);
            this.s.offsetLeftAndRight(i - left);
        }
        int i5 = i;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.r.a(this.s, i5, i2, i5 - left, i2 - top);
    }

    public boolean a(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }

    public View a(int i, int i2) {
        io.dcloud.common.core.ui.b bVar = this.w;
        if (bVar != null && bVar.obtainMainView() != null) {
            return this.w.obtainMainView();
        }
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.a(childCount));
            if ((childAt instanceof zu) && i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }
}
