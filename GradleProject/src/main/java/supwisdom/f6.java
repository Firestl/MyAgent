package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import supwisdom.o6;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class f6 extends m6 {
    public x5 P0;
    public int R0;
    public int S0;
    public o6 L0 = new o6(this);
    public r6 M0 = new r6(this);
    public o6.b N0 = null;
    public boolean O0 = false;
    public w5 Q0 = new w5();
    public int T0 = 0;
    public int U0 = 0;
    public e6[] V0 = new e6[4];
    public e6[] W0 = new e6[4];
    public int X0 = 257;
    public boolean Y0 = false;
    public boolean Z0 = false;
    public WeakReference<ConstraintAnchor> a1 = null;
    public WeakReference<ConstraintAnchor> b1 = null;
    public WeakReference<ConstraintAnchor> c1 = null;
    public WeakReference<ConstraintAnchor> d1 = null;
    public o6.a e1 = new o6.a();

    @Override // supwisdom.m6, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void Q() {
        this.Q0.i();
        this.R0 = 0;
        this.S0 = 0;
        super.Q();
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x0316 A[PHI: r0 r13
  0x0316: PHI (r0v20 boolean) = (r0v19 boolean), (r0v22 boolean), (r0v22 boolean), (r0v22 boolean) binds: [B:143:0x02d8, B:151:0x02fe, B:152:0x0300, B:154:0x0306] A[DONT_GENERATE, DONT_INLINE]
  0x0316: PHI (r13v9 boolean) = (r13v8 boolean), (r13v11 boolean), (r13v11 boolean), (r13v11 boolean) binds: [B:143:0x02d8, B:151:0x02fe, B:152:0x0300, B:154:0x0306] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [boolean] */
    @Override // supwisdom.m6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void U() {
        /*
            Method dump skipped, instruction units count: 819
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.f6.U():void");
    }

    public o6.b W() {
        return this.N0;
    }

    public int X() {
        return this.X0;
    }

    public w5 Y() {
        return this.Q0;
    }

    public boolean Z() {
        return false;
    }

    public boolean a(boolean z, int i) {
        return this.M0.a(z, i);
    }

    public void a0() {
        this.M0.b();
    }

    public void b(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.b1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.b() > this.b1.get().b()) {
            this.b1 = new WeakReference<>(constraintAnchor);
        }
    }

    public void b0() {
        this.M0.c();
    }

    public void c(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.c1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.b() > this.c1.get().b()) {
            this.c1 = new WeakReference<>(constraintAnchor);
        }
    }

    public boolean c0() {
        return this.Z0;
    }

    public void d(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.a1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.b() > this.a1.get().b()) {
            this.a1 = new WeakReference<>(constraintAnchor);
        }
    }

    public boolean d0() {
        return this.O0;
    }

    public boolean e(boolean z) {
        return this.M0.a(z);
    }

    public boolean e0() {
        return this.Y0;
    }

    public boolean f(boolean z) {
        return this.M0.b(z);
    }

    public final void f0() {
        this.T0 = 0;
        this.U0 = 0;
    }

    public void g(boolean z) {
        this.O0 = z;
    }

    public void g0() {
        this.L0.b(this);
    }

    public boolean x(int i) {
        return (this.X0 & i) == i;
    }

    public void y(int i) {
        this.X0 = i;
        w5.r = x(512);
    }

    public long a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.R0 = i8;
        this.S0 = i9;
        return this.L0.a(this, i, i8, i9, i2, i3, i4, i5, i6, i7);
    }

    public final void e(ConstraintWidget constraintWidget) {
        int i = this.U0 + 1;
        e6[] e6VarArr = this.V0;
        if (i >= e6VarArr.length) {
            this.V0 = (e6[]) Arrays.copyOf(e6VarArr, e6VarArr.length * 2);
        }
        this.V0[this.U0] = new e6(constraintWidget, 1, d0());
        this.U0++;
    }

    public final void b(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.Q0.b(this.Q0.a(constraintAnchor), solverVariable, 0, 5);
    }

    public final void d(ConstraintWidget constraintWidget) {
        int i = this.T0 + 1;
        e6[] e6VarArr = this.W0;
        if (i >= e6VarArr.length) {
            this.W0 = (e6[]) Arrays.copyOf(e6VarArr, e6VarArr.length * 2);
        }
        this.W0[this.T0] = new e6(constraintWidget, 0, d0());
        this.T0++;
    }

    public void a(o6.b bVar) {
        this.N0 = bVar;
        this.M0.a(bVar);
    }

    public boolean b(w5 w5Var) {
        boolean zX = x(64);
        a(w5Var, zX);
        int size = this.K0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.K0.get(i);
            constraintWidget.a(0, false);
            constraintWidget.a(1, false);
            if (constraintWidget instanceof c6) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = this.K0.get(i2);
                if (constraintWidget2 instanceof c6) {
                    ((c6) constraintWidget2).Y();
                }
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget3 = this.K0.get(i3);
            if (constraintWidget3.c()) {
                constraintWidget3.a(w5Var, zX);
            }
        }
        if (w5.r) {
            HashSet<ConstraintWidget> hashSet = new HashSet<>();
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget4 = this.K0.get(i4);
                if (!constraintWidget4.c()) {
                    hashSet.add(constraintWidget4);
                }
            }
            a(this, w5Var, hashSet, o() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? 0 : 1, false);
            for (ConstraintWidget constraintWidget5 : hashSet) {
                k6.a(this, w5Var, constraintWidget5);
                constraintWidget5.a(w5Var, zX);
            }
        } else {
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget6 = this.K0.get(i5);
                if (constraintWidget6 instanceof f6) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget6.S;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.a(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.b(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget6.a(w5Var, zX);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.a(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.b(dimensionBehaviour2);
                    }
                } else {
                    k6.a(this, w5Var, constraintWidget6);
                    if (!constraintWidget6.c()) {
                        constraintWidget6.a(w5Var, zX);
                    }
                }
            }
        }
        if (this.T0 > 0) {
            d6.a(this, w5Var, null, 0);
        }
        if (this.U0 > 0) {
            d6.a(this, w5Var, null, 1);
        }
        return true;
    }

    public void a(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.d1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.b() > this.d1.get().b()) {
            this.d1 = new WeakReference<>(constraintAnchor);
        }
    }

    public final void a(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.Q0.b(solverVariable, this.Q0.a(constraintAnchor), 0, 5);
    }

    public void a(w5 w5Var, boolean[] zArr) {
        zArr[2] = false;
        boolean zX = x(64);
        b(w5Var, zX);
        int size = this.K0.size();
        for (int i = 0; i < size; i++) {
            this.K0.get(i).b(w5Var, zX);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(boolean z, boolean z2) {
        super.a(z, z2);
        int size = this.K0.size();
        for (int i = 0; i < size; i++) {
            this.K0.get(i).a(z, z2);
        }
    }

    public static boolean a(ConstraintWidget constraintWidget, o6.b bVar, o6.a aVar, int i) {
        int i2;
        int i3;
        if (bVar == null) {
            return false;
        }
        aVar.f8631a = constraintWidget.o();
        aVar.b = constraintWidget.A();
        aVar.c = constraintWidget.D();
        aVar.d = constraintWidget.l();
        aVar.i = false;
        aVar.j = i;
        boolean z = aVar.f8631a == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = aVar.b == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z3 = z && constraintWidget.W > 0.0f;
        boolean z4 = z2 && constraintWidget.W > 0.0f;
        if (z && constraintWidget.g(0) && constraintWidget.n == 0 && !z3) {
            aVar.f8631a = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z2 && constraintWidget.o == 0) {
                aVar.f8631a = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z2 && constraintWidget.g(1) && constraintWidget.o == 0 && !z4) {
            aVar.b = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.n == 0) {
                aVar.b = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z2 = false;
        }
        if (constraintWidget.N()) {
            aVar.f8631a = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.O()) {
            aVar.b = ConstraintWidget.DimensionBehaviour.FIXED;
            z2 = false;
        }
        if (z3) {
            if (constraintWidget.p[0] == 4) {
                aVar.f8631a = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z2) {
                if (aVar.b == ConstraintWidget.DimensionBehaviour.FIXED) {
                    i3 = aVar.d;
                } else {
                    aVar.f8631a = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    bVar.a(constraintWidget, aVar);
                    i3 = aVar.f;
                }
                aVar.f8631a = ConstraintWidget.DimensionBehaviour.FIXED;
                int i4 = constraintWidget.X;
                if (i4 != 0 && i4 != -1) {
                    aVar.c = (int) (constraintWidget.j() / i3);
                } else {
                    aVar.c = (int) (constraintWidget.j() * i3);
                }
            }
        }
        if (z4) {
            if (constraintWidget.p[1] == 4) {
                aVar.b = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                if (aVar.f8631a == ConstraintWidget.DimensionBehaviour.FIXED) {
                    i2 = aVar.c;
                } else {
                    aVar.b = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    bVar.a(constraintWidget, aVar);
                    i2 = aVar.f8632e;
                }
                aVar.b = ConstraintWidget.DimensionBehaviour.FIXED;
                int i5 = constraintWidget.X;
                if (i5 != 0 && i5 != -1) {
                    aVar.d = (int) (i2 * constraintWidget.j());
                } else {
                    aVar.d = (int) (i2 / constraintWidget.j());
                }
            }
        }
        bVar.a(constraintWidget, aVar);
        constraintWidget.u(aVar.f8632e);
        constraintWidget.m(aVar.f);
        constraintWidget.a(aVar.h);
        constraintWidget.i(aVar.g);
        aVar.j = o6.a.k;
        return aVar.i;
    }

    public void a(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            d(constraintWidget);
        } else if (i == 1) {
            e(constraintWidget);
        }
    }
}
