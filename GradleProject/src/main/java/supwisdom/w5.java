package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class w5 {
    public static boolean r = false;
    public static boolean s = true;
    public static boolean t = true;
    public static boolean u = true;
    public static boolean v = false;
    public static int w = 1000;
    public static x5 x;
    public static long y;
    public static long z;
    public a d;
    public u5[] g;
    public final v5 n;
    public a q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9586a = false;
    public int b = 0;
    public HashMap<String, SolverVariable> c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9587e = 32;
    public int f = 32;
    public boolean h = false;
    public boolean i = false;
    public boolean[] j = new boolean[32];
    public int k = 1;
    public int l = 0;
    public int m = 32;
    public SolverVariable[] o = new SolverVariable[w];
    public int p = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    public interface a {
        SolverVariable a(w5 w5Var, boolean[] zArr);

        void a(SolverVariable solverVariable);

        void a(a aVar);

        void clear();

        SolverVariable getKey();

        boolean isEmpty();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    public class b extends u5 {
        public b(w5 w5Var, v5 v5Var) {
            this.f9370e = new b6(this, v5Var);
        }
    }

    public w5() {
        this.g = null;
        this.g = new u5[32];
        h();
        v5 v5Var = new v5();
        this.n = v5Var;
        this.d = new a6(v5Var);
        if (v) {
            this.q = new b(this, this.n);
        } else {
            this.q = new u5(this.n);
        }
    }

    public static x5 j() {
        return x;
    }

    public SolverVariable a(Object obj) {
        SolverVariable solverVariableF = null;
        if (obj == null) {
            return null;
        }
        if (this.k + 1 >= this.f) {
            f();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariableF = constraintAnchor.f();
            if (solverVariableF == null) {
                constraintAnchor.a(this.n);
                solverVariableF = constraintAnchor.f();
            }
            int i = solverVariableF.c;
            if (i == -1 || i > this.b || this.n.d[i] == null) {
                if (solverVariableF.c != -1) {
                    solverVariableF.a();
                }
                int i2 = this.b + 1;
                this.b = i2;
                this.k++;
                solverVariableF.c = i2;
                solverVariableF.j = SolverVariable.Type.UNRESTRICTED;
                this.n.d[i2] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public SolverVariable b() {
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.n++;
        }
        if (this.k + 1 >= this.f) {
            f();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, (String) null);
        int i = this.b + 1;
        this.b = i;
        this.k++;
        solverVariableA.c = i;
        this.n.d[i] = solverVariableA;
        return solverVariableA;
    }

    public u5 c() {
        u5 u5VarAcquire;
        if (v) {
            u5VarAcquire = this.n.f9480a.acquire();
            if (u5VarAcquire == null) {
                u5VarAcquire = new b(this, this.n);
                z++;
            } else {
                u5VarAcquire.c();
            }
        } else {
            u5VarAcquire = this.n.b.acquire();
            if (u5VarAcquire == null) {
                u5VarAcquire = new u5(this.n);
                y++;
            } else {
                u5VarAcquire.c();
            }
        }
        SolverVariable.b();
        return u5VarAcquire;
    }

    public SolverVariable d() {
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.m++;
        }
        if (this.k + 1 >= this.f) {
            f();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, (String) null);
        int i = this.b + 1;
        this.b = i;
        this.k++;
        solverVariableA.c = i;
        this.n.d[i] = solverVariableA;
        return solverVariableA;
    }

    public v5 e() {
        return this.n;
    }

    public final void f() {
        int i = this.f9587e * 2;
        this.f9587e = i;
        this.g = (u5[]) Arrays.copyOf(this.g, i);
        v5 v5Var = this.n;
        v5Var.d = (SolverVariable[]) Arrays.copyOf(v5Var.d, this.f9587e);
        int i2 = this.f9587e;
        this.j = new boolean[i2];
        this.f = i2;
        this.m = i2;
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.d++;
            x5Var.o = Math.max(x5Var.o, i2);
            x5 x5Var2 = x;
            x5Var2.x = x5Var2.o;
        }
    }

    public void g() throws Exception {
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.f9712e++;
        }
        if (this.d.isEmpty()) {
            a();
            return;
        }
        if (!this.h && !this.i) {
            b(this.d);
            return;
        }
        x5 x5Var2 = x;
        if (x5Var2 != null) {
            x5Var2.q++;
        }
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= this.l) {
                z2 = true;
                break;
            } else if (!this.g[i].f) {
                break;
            } else {
                i++;
            }
        }
        if (!z2) {
            b(this.d);
            return;
        }
        x5 x5Var3 = x;
        if (x5Var3 != null) {
            x5Var3.p++;
        }
        a();
    }

    public final void h() {
        int i = 0;
        if (v) {
            while (i < this.l) {
                u5 u5Var = this.g[i];
                if (u5Var != null) {
                    this.n.f9480a.release(u5Var);
                }
                this.g[i] = null;
                i++;
            }
            return;
        }
        while (i < this.l) {
            u5 u5Var2 = this.g[i];
            if (u5Var2 != null) {
                this.n.b.release(u5Var2);
            }
            this.g[i] = null;
            i++;
        }
    }

    public void i() {
        v5 v5Var;
        int i = 0;
        while (true) {
            v5Var = this.n;
            SolverVariable[] solverVariableArr = v5Var.d;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.a();
            }
            i++;
        }
        v5Var.c.a(this.o, this.p);
        this.p = 0;
        Arrays.fill(this.n.d, (Object) null);
        HashMap<String, SolverVariable> map = this.c;
        if (map != null) {
            map.clear();
        }
        this.b = 0;
        this.d.clear();
        this.k = 1;
        for (int i2 = 0; i2 < this.l; i2++) {
            u5[] u5VarArr = this.g;
            if (u5VarArr[i2] != null) {
                u5VarArr[i2].c = false;
            }
        }
        h();
        this.l = 0;
        if (v) {
            this.q = new b(this, this.n);
        } else {
            this.q = new u5(this.n);
        }
    }

    public int b(Object obj) {
        SolverVariable solverVariableF = ((ConstraintAnchor) obj).f();
        if (solverVariableF != null) {
            return (int) (solverVariableF.f + 0.5f);
        }
        return 0;
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        u5 u5VarC = c();
        SolverVariable solverVariableD = d();
        solverVariableD.f1223e = 0;
        u5VarC.b(solverVariable, solverVariable2, solverVariableD, i);
        if (i2 != 8) {
            a(u5VarC, (int) (u5VarC.f9370e.a(solverVariableD) * (-1.0f)), i2);
        }
        a(u5VarC);
    }

    public void b(a aVar) throws Exception {
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.t++;
            x5Var.u = Math.max(x5Var.u, this.k);
            x5 x5Var2 = x;
            x5Var2.v = Math.max(x5Var2.v, this.l);
        }
        a(aVar);
        a(aVar, false);
        a();
    }

    public void a(u5 u5Var, int i, int i2) {
        u5Var.a(a(i2, (String) null), i);
    }

    public SolverVariable a(int i, String str) {
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.l++;
        }
        if (this.k + 1 >= this.f) {
            f();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i2 = this.b + 1;
        this.b = i2;
        this.k++;
        solverVariableA.c = i2;
        solverVariableA.f1223e = i;
        this.n.d[i2] = solverVariableA;
        this.d.a(solverVariableA);
        return solverVariableA;
    }

    public final void b(u5 u5Var) {
        int i;
        if (t && u5Var.f) {
            u5Var.f9369a.a(this, u5Var.b);
        } else {
            u5[] u5VarArr = this.g;
            int i2 = this.l;
            u5VarArr[i2] = u5Var;
            SolverVariable solverVariable = u5Var.f9369a;
            solverVariable.d = i2;
            this.l = i2 + 1;
            solverVariable.a(this, u5Var);
        }
        if (t && this.f9586a) {
            int i3 = 0;
            while (i3 < this.l) {
                if (this.g[i3] == null) {
                    System.out.println("WTF");
                }
                u5[] u5VarArr2 = this.g;
                if (u5VarArr2[i3] != null && u5VarArr2[i3].f) {
                    u5 u5Var2 = u5VarArr2[i3];
                    u5Var2.f9369a.a(this, u5Var2.b);
                    if (v) {
                        this.n.f9480a.release(u5Var2);
                    } else {
                        this.n.b.release(u5Var2);
                    }
                    this.g[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.l;
                        if (i4 >= i) {
                            break;
                        }
                        u5[] u5VarArr3 = this.g;
                        int i6 = i4 - 1;
                        u5VarArr3[i6] = u5VarArr3[i4];
                        if (u5VarArr3[i6].f9369a.d == i4) {
                            u5VarArr3[i6].f9369a.d = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.g[i5] = null;
                    }
                    this.l--;
                    i3--;
                }
                i3++;
            }
            this.f9586a = false;
        }
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableAcquire = this.n.c.acquire();
        if (solverVariableAcquire == null) {
            solverVariableAcquire = new SolverVariable(type, str);
            solverVariableAcquire.a(type, str);
        } else {
            solverVariableAcquire.a();
            solverVariableAcquire.a(type, str);
        }
        int i = this.p;
        int i2 = w;
        if (i >= i2) {
            int i3 = i2 * 2;
            w = i3;
            this.o = (SolverVariable[]) Arrays.copyOf(this.o, i3);
        }
        SolverVariable[] solverVariableArr = this.o;
        int i4 = this.p;
        this.p = i4 + 1;
        solverVariableArr[i4] = solverVariableAcquire;
        return solverVariableAcquire;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(supwisdom.u5 r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            supwisdom.x5 r0 = supwisdom.w5.x
            r1 = 1
            if (r0 == 0) goto L17
            long r3 = r0.f
            long r3 = r3 + r1
            r0.f = r3
            boolean r3 = r8.f
            if (r3 == 0) goto L17
            long r3 = r0.g
            long r3 = r3 + r1
            r0.g = r3
        L17:
            int r0 = r7.l
            r3 = 1
            int r0 = r0 + r3
            int r4 = r7.m
            if (r0 >= r4) goto L26
            int r0 = r7.k
            int r0 = r0 + r3
            int r4 = r7.f
            if (r0 < r4) goto L29
        L26:
            r7.f()
        L29:
            r0 = 0
            boolean r4 = r8.f
            if (r4 != 0) goto La1
            r8.c(r7)
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L38
            return
        L38:
            r8.a()
            boolean r4 = r8.a(r7)
            if (r4 == 0) goto L98
            androidx.constraintlayout.solver.SolverVariable r4 = r7.b()
            r8.f9369a = r4
            int r5 = r7.l
            r7.b(r8)
            int r6 = r7.l
            int r5 = r5 + r3
            if (r6 != r5) goto L98
            supwisdom.w5$a r0 = r7.q
            r0.a(r8)
            supwisdom.w5$a r0 = r7.q
            r7.a(r0, r3)
            int r0 = r4.d
            r5 = -1
            if (r0 != r5) goto L99
            androidx.constraintlayout.solver.SolverVariable r0 = r8.f9369a
            if (r0 != r4) goto L76
            androidx.constraintlayout.solver.SolverVariable r0 = r8.c(r4)
            if (r0 == 0) goto L76
            supwisdom.x5 r4 = supwisdom.w5.x
            if (r4 == 0) goto L73
            long r5 = r4.j
            long r5 = r5 + r1
            r4.j = r5
        L73:
            r8.d(r0)
        L76:
            boolean r0 = r8.f
            if (r0 != 0) goto L7f
            androidx.constraintlayout.solver.SolverVariable r0 = r8.f9369a
            r0.a(r7, r8)
        L7f:
            boolean r0 = supwisdom.w5.v
            if (r0 == 0) goto L8b
            supwisdom.v5 r0 = r7.n
            supwisdom.y5<supwisdom.u5> r0 = r0.f9480a
            r0.release(r8)
            goto L92
        L8b:
            supwisdom.v5 r0 = r7.n
            supwisdom.y5<supwisdom.u5> r0 = r0.b
            r0.release(r8)
        L92:
            int r0 = r7.l
            int r0 = r0 - r3
            r7.l = r0
            goto L99
        L98:
            r3 = 0
        L99:
            boolean r0 = r8.b()
            if (r0 != 0) goto La0
            return
        La0:
            r0 = r3
        La1:
            if (r0 != 0) goto La6
            r7.b(r8)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.w5.a(supwisdom.u5):void");
    }

    public void b(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        u5 u5VarC = c();
        SolverVariable solverVariableD = d();
        solverVariableD.f1223e = 0;
        u5VarC.a(solverVariable, solverVariable2, solverVariableD, i);
        if (i2 != 8) {
            a(u5VarC, (int) (u5VarC.f9370e.a(solverVariableD) * (-1.0f)), i2);
        }
        a(u5VarC);
    }

    public void b(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z2) {
        u5 u5VarC = c();
        SolverVariable solverVariableD = d();
        solverVariableD.f1223e = 0;
        u5VarC.b(solverVariable, solverVariable2, solverVariableD, i);
        a(u5VarC);
    }

    public final int a(a aVar, boolean z2) {
        x5 x5Var = x;
        if (x5Var != null) {
            x5Var.h++;
        }
        for (int i = 0; i < this.k; i++) {
            this.j[i] = false;
        }
        boolean z3 = false;
        int i2 = 0;
        while (!z3) {
            x5 x5Var2 = x;
            if (x5Var2 != null) {
                x5Var2.i++;
            }
            i2++;
            if (i2 >= this.k * 2) {
                return i2;
            }
            if (aVar.getKey() != null) {
                this.j[aVar.getKey().c] = true;
            }
            SolverVariable solverVariableA = aVar.a(this, this.j);
            if (solverVariableA != null) {
                boolean[] zArr = this.j;
                int i3 = solverVariableA.c;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (solverVariableA != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.l; i5++) {
                    u5 u5Var = this.g[i5];
                    if (u5Var.f9369a.j != SolverVariable.Type.UNRESTRICTED && !u5Var.f && u5Var.b(solverVariableA)) {
                        float fA = u5Var.f9370e.a(solverVariableA);
                        if (fA < 0.0f) {
                            float f2 = (-u5Var.b) / fA;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    u5 u5Var2 = this.g[i4];
                    u5Var2.f9369a.d = -1;
                    x5 x5Var3 = x;
                    if (x5Var3 != null) {
                        x5Var3.j++;
                    }
                    u5Var2.d(solverVariableA);
                    SolverVariable solverVariable = u5Var2.f9369a;
                    solverVariable.d = i4;
                    solverVariable.a(this, u5Var2);
                }
            } else {
                z3 = true;
            }
        }
        return i2;
    }

    public final int a(a aVar) throws Exception {
        boolean z2;
        int i = 0;
        while (true) {
            if (i >= this.l) {
                z2 = false;
                break;
            }
            u5[] u5VarArr = this.g;
            if (u5VarArr[i].f9369a.j != SolverVariable.Type.UNRESTRICTED && u5VarArr[i].b < 0.0f) {
                z2 = true;
                break;
            }
            i++;
        }
        if (!z2) {
            return 0;
        }
        boolean z3 = false;
        int i2 = 0;
        while (!z3) {
            x5 x5Var = x;
            if (x5Var != null) {
                x5Var.k++;
            }
            i2++;
            float f = Float.MAX_VALUE;
            int i3 = -1;
            int i4 = -1;
            int i5 = 0;
            for (int i6 = 0; i6 < this.l; i6++) {
                u5 u5Var = this.g[i6];
                if (u5Var.f9369a.j != SolverVariable.Type.UNRESTRICTED && !u5Var.f && u5Var.b < 0.0f) {
                    int i7 = 9;
                    if (u) {
                        int currentSize = u5Var.f9370e.getCurrentSize();
                        int i8 = 0;
                        while (i8 < currentSize) {
                            SolverVariable solverVariableA = u5Var.f9370e.a(i8);
                            float fA = u5Var.f9370e.a(solverVariableA);
                            if (fA > 0.0f) {
                                int i9 = 0;
                                while (i9 < i7) {
                                    float f2 = solverVariableA.h[i9] / fA;
                                    if ((f2 < f && i9 == i5) || i9 > i5) {
                                        i4 = solverVariableA.c;
                                        i5 = i9;
                                        i3 = i6;
                                        f = f2;
                                    }
                                    i9++;
                                    i7 = 9;
                                }
                            }
                            i8++;
                            i7 = 9;
                        }
                    } else {
                        for (int i10 = 1; i10 < this.k; i10++) {
                            SolverVariable solverVariable = this.n.d[i10];
                            float fA2 = u5Var.f9370e.a(solverVariable);
                            if (fA2 > 0.0f) {
                                for (int i11 = 0; i11 < 9; i11++) {
                                    float f3 = solverVariable.h[i11] / fA2;
                                    if ((f3 < f && i11 == i5) || i11 > i5) {
                                        i4 = i10;
                                        i5 = i11;
                                        i3 = i6;
                                        f = f3;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (i3 != -1) {
                u5 u5Var2 = this.g[i3];
                u5Var2.f9369a.d = -1;
                x5 x5Var2 = x;
                if (x5Var2 != null) {
                    x5Var2.j++;
                }
                u5Var2.d(this.n.d[i4]);
                SolverVariable solverVariable2 = u5Var2.f9369a;
                solverVariable2.d = i3;
                solverVariable2.a(this, u5Var2);
            } else {
                z3 = true;
            }
            if (i2 > this.k / 2) {
                z3 = true;
            }
        }
        return i2;
    }

    public final void a() {
        for (int i = 0; i < this.l; i++) {
            u5 u5Var = this.g[i];
            u5Var.f9369a.f = u5Var.b;
        }
    }

    public void a(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z2) {
        u5 u5VarC = c();
        SolverVariable solverVariableD = d();
        solverVariableD.f1223e = 0;
        u5VarC.a(solverVariable, solverVariable2, solverVariableD, i);
        a(u5VarC);
    }

    public void a(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        u5 u5VarC = c();
        u5VarC.a(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i3 != 8) {
            u5VarC.a(this, i3);
        }
        a(u5VarC);
    }

    public void a(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        u5 u5VarC = c();
        u5VarC.a(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            u5VarC.a(this, i);
        }
        a(u5VarC);
    }

    public u5 a(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (s && i2 == 8 && solverVariable2.g && solverVariable.d == -1) {
            solverVariable.a(this, solverVariable2.f + i);
            return null;
        }
        u5 u5VarC = c();
        u5VarC.a(solverVariable, solverVariable2, i);
        if (i2 != 8) {
            u5VarC.a(this, i2);
        }
        a(u5VarC);
        return u5VarC;
    }

    public void a(SolverVariable solverVariable, int i) {
        if (s && solverVariable.d == -1) {
            float f = i;
            solverVariable.a(this, f);
            for (int i2 = 0; i2 < this.b + 1; i2++) {
                SolverVariable solverVariable2 = this.n.d[i2];
                if (solverVariable2 != null && solverVariable2.n && solverVariable2.o == solverVariable.c) {
                    solverVariable2.a(this, solverVariable2.p + f);
                }
            }
            return;
        }
        int i3 = solverVariable.d;
        if (i3 != -1) {
            u5 u5Var = this.g[i3];
            if (u5Var.f) {
                u5Var.b = i;
                return;
            }
            if (u5Var.f9370e.getCurrentSize() == 0) {
                u5Var.f = true;
                u5Var.b = i;
                return;
            } else {
                u5 u5VarC = c();
                u5VarC.c(solverVariable, i);
                a(u5VarC);
                return;
            }
        }
        u5 u5VarC2 = c();
        u5VarC2.b(solverVariable, i);
        a(u5VarC2);
    }

    public static u5 a(w5 w5Var, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        u5 u5VarC = w5Var.c();
        u5VarC.a(solverVariable, solverVariable2, f);
        return u5VarC;
    }

    public void a(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        SolverVariable solverVariableA = a(constraintWidget.a(ConstraintAnchor.Type.LEFT));
        SolverVariable solverVariableA2 = a(constraintWidget.a(ConstraintAnchor.Type.TOP));
        SolverVariable solverVariableA3 = a(constraintWidget.a(ConstraintAnchor.Type.RIGHT));
        SolverVariable solverVariableA4 = a(constraintWidget.a(ConstraintAnchor.Type.BOTTOM));
        SolverVariable solverVariableA5 = a(constraintWidget2.a(ConstraintAnchor.Type.LEFT));
        SolverVariable solverVariableA6 = a(constraintWidget2.a(ConstraintAnchor.Type.TOP));
        SolverVariable solverVariableA7 = a(constraintWidget2.a(ConstraintAnchor.Type.RIGHT));
        SolverVariable solverVariableA8 = a(constraintWidget2.a(ConstraintAnchor.Type.BOTTOM));
        u5 u5VarC = c();
        double d = f;
        double d2 = i;
        u5VarC.b(solverVariableA2, solverVariableA4, solverVariableA6, solverVariableA8, (float) (Math.sin(d) * d2));
        a(u5VarC);
        u5 u5VarC2 = c();
        u5VarC2.b(solverVariableA, solverVariableA3, solverVariableA5, solverVariableA7, (float) (Math.cos(d) * d2));
        a(u5VarC2);
    }
}
