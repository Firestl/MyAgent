package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;
import supwisdom.w5;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class u5 implements w5.a {
    public boolean c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f9370e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SolverVariable f9369a = null;
    public float b = 0.0f;
    public ArrayList<SolverVariable> d = new ArrayList<>();
    public boolean f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(SolverVariable solverVariable);

        float a(SolverVariable solverVariable, boolean z);

        float a(u5 u5Var, boolean z);

        SolverVariable a(int i);

        void a(float f);

        void a(SolverVariable solverVariable, float f);

        void a(SolverVariable solverVariable, float f, boolean z);

        float b(int i);

        boolean b(SolverVariable solverVariable);

        void clear();

        int getCurrentSize();

        void invert();
    }

    public u5() {
    }

    public u5 a(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
        } else {
            this.f9370e.a(solverVariable, -1.0f);
            this.f9370e.a(solverVariable2, 1.0f);
        }
        return this;
    }

    public boolean b() {
        SolverVariable solverVariable = this.f9369a;
        return solverVariable != null && (solverVariable.j == SolverVariable.Type.UNRESTRICTED || this.b >= 0.0f);
    }

    public void c() {
        this.f9369a = null;
        this.f9370e.clear();
        this.b = 0.0f;
        this.f = false;
    }

    @Override // supwisdom.w5.a
    public void clear() {
        this.f9370e.clear();
        this.f9369a = null;
        this.b = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String d() {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.u5.d():java.lang.String");
    }

    @Override // supwisdom.w5.a
    public SolverVariable getKey() {
        return this.f9369a;
    }

    @Override // supwisdom.w5.a
    public boolean isEmpty() {
        return this.f9369a == null && this.b == 0.0f && this.f9370e.getCurrentSize() == 0;
    }

    public String toString() {
        return d();
    }

    public boolean b(SolverVariable solverVariable) {
        return this.f9370e.b(solverVariable);
    }

    public u5 b(SolverVariable solverVariable, int i) {
        this.f9369a = solverVariable;
        float f = i;
        solverVariable.f = f;
        this.b = f;
        this.f = true;
        return this;
    }

    public u5 c(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.b = i * (-1);
            this.f9370e.a(solverVariable, 1.0f);
        } else {
            this.b = i;
            this.f9370e.a(solverVariable, -1.0f);
        }
        return this;
    }

    public u5(v5 v5Var) {
        this.f9370e = new t5(this, v5Var);
    }

    public u5 a(SolverVariable solverVariable, int i) {
        this.f9370e.a(solverVariable, i);
        return this;
    }

    public u5 a(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (!z) {
            this.f9370e.a(solverVariable, -1.0f);
            this.f9370e.a(solverVariable2, 1.0f);
            this.f9370e.a(solverVariable3, 1.0f);
        } else {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
            this.f9370e.a(solverVariable3, -1.0f);
        }
        return this;
    }

    public u5 b(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (!z) {
            this.f9370e.a(solverVariable, -1.0f);
            this.f9370e.a(solverVariable2, 1.0f);
            this.f9370e.a(solverVariable3, -1.0f);
        } else {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
            this.f9370e.a(solverVariable3, 1.0f);
        }
        return this;
    }

    public SolverVariable c(SolverVariable solverVariable) {
        return a((boolean[]) null, solverVariable);
    }

    public void c(w5 w5Var) {
        if (w5Var.g.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int currentSize = this.f9370e.getCurrentSize();
            for (int i = 0; i < currentSize; i++) {
                SolverVariable solverVariableA = this.f9370e.a(i);
                if (solverVariableA.d != -1 || solverVariableA.g || solverVariableA.n) {
                    this.d.add(solverVariableA);
                }
            }
            int size = this.d.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    SolverVariable solverVariable = this.d.get(i2);
                    if (solverVariable.g) {
                        a(w5Var, solverVariable, true);
                    } else if (solverVariable.n) {
                        b(w5Var, solverVariable, true);
                    } else {
                        a(w5Var, w5Var.g[solverVariable.d], true);
                    }
                }
                this.d.clear();
            } else {
                z = true;
            }
        }
        if (w5.t && this.f9369a != null && this.f9370e.getCurrentSize() == 0) {
            this.f = true;
            w5Var.f9586a = true;
        }
    }

    public u5 a(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
            this.f9370e.a(solverVariable4, 1.0f);
            this.f9370e.a(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.f9370e.a(solverVariable3, 1.0f);
            this.f9370e.a(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
            this.f9370e.a(solverVariable4, f4);
            this.f9370e.a(solverVariable3, -f4);
        }
        return this;
    }

    public u5 b(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.f9370e.a(solverVariable3, 0.5f);
        this.f9370e.a(solverVariable4, 0.5f);
        this.f9370e.a(solverVariable, -0.5f);
        this.f9370e.a(solverVariable2, -0.5f);
        this.b = -f;
        return this;
    }

    public void d(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f9369a;
        if (solverVariable2 != null) {
            this.f9370e.a(solverVariable2, -1.0f);
            this.f9369a.d = -1;
            this.f9369a = null;
        }
        float fA = this.f9370e.a(solverVariable, true) * (-1.0f);
        this.f9369a = solverVariable;
        if (fA == 1.0f) {
            return;
        }
        this.b /= fA;
        this.f9370e.a(fA);
    }

    public SolverVariable b(w5 w5Var) {
        int currentSize = this.f9370e.getCurrentSize();
        SolverVariable solverVariable = null;
        SolverVariable solverVariable2 = null;
        boolean z = false;
        boolean z2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < currentSize; i++) {
            float fB = this.f9370e.b(i);
            SolverVariable solverVariableA = this.f9370e.a(i);
            if (solverVariableA.j == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f > fB) {
                    boolean zA = a(solverVariableA, w5Var);
                    z = zA;
                    f = fB;
                    solverVariable = solverVariableA;
                } else if (!z && a(solverVariableA, w5Var)) {
                    f = fB;
                    solverVariable = solverVariableA;
                    z = true;
                }
            } else if (solverVariable == null && fB < 0.0f) {
                if (solverVariable2 == null || f2 > fB) {
                    boolean zA2 = a(solverVariableA, w5Var);
                    z2 = zA2;
                    f2 = fB;
                    solverVariable2 = solverVariableA;
                } else if (!z2 && a(solverVariableA, w5Var)) {
                    f2 = fB;
                    solverVariable2 = solverVariableA;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    public u5 a(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable4, 1.0f);
            this.f9370e.a(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.f9370e.a(solverVariable, 1.0f);
            this.f9370e.a(solverVariable2, -1.0f);
            this.f9370e.a(solverVariable3, -1.0f);
            this.f9370e.a(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            this.f9370e.a(solverVariable, -1.0f);
            this.f9370e.a(solverVariable2, 1.0f);
            this.b = i;
        } else if (f >= 1.0f) {
            this.f9370e.a(solverVariable4, -1.0f);
            this.f9370e.a(solverVariable3, 1.0f);
            this.b = -i2;
        } else {
            float f2 = 1.0f - f;
            this.f9370e.a(solverVariable, f2 * 1.0f);
            this.f9370e.a(solverVariable2, f2 * (-1.0f));
            this.f9370e.a(solverVariable3, (-1.0f) * f);
            this.f9370e.a(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.b = ((-i) * f2) + (i2 * f);
            }
        }
        return this;
    }

    public void b(w5 w5Var, SolverVariable solverVariable, boolean z) {
        if (solverVariable.n) {
            float fA = this.f9370e.a(solverVariable);
            this.b += solverVariable.p * fA;
            this.f9370e.a(solverVariable, z);
            if (z) {
                solverVariable.b(this);
            }
            this.f9370e.a(w5Var.n.d[solverVariable.o], fA, z);
            if (w5.t && solverVariable != null && this.f9370e.getCurrentSize() == 0) {
                this.f = true;
                w5Var.f9586a = true;
            }
        }
    }

    public u5 a(w5 w5Var, int i) {
        this.f9370e.a(w5Var.a(i, "ep"), 1.0f);
        this.f9370e.a(w5Var.a(i, com.umeng.analytics.c.d), -1.0f);
        return this;
    }

    public u5 a(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.f9370e.a(solverVariable, -1.0f);
        this.f9370e.a(solverVariable2, f);
        return this;
    }

    public u5 a(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.f9370e.a(solverVariable, -1.0f);
        this.f9370e.a(solverVariable2, 1.0f);
        this.f9370e.a(solverVariable3, f);
        this.f9370e.a(solverVariable4, -f);
        return this;
    }

    public void a() {
        float f = this.b;
        if (f < 0.0f) {
            this.b = f * (-1.0f);
            this.f9370e.invert();
        }
    }

    public boolean a(w5 w5Var) {
        boolean z;
        SolverVariable solverVariableB = b(w5Var);
        if (solverVariableB == null) {
            z = true;
        } else {
            d(solverVariableB);
            z = false;
        }
        if (this.f9370e.getCurrentSize() == 0) {
            this.f = true;
        }
        return z;
    }

    public final boolean a(SolverVariable solverVariable, w5 w5Var) {
        return solverVariable.m <= 1;
    }

    public void a(w5 w5Var, u5 u5Var, boolean z) {
        this.b += u5Var.b * this.f9370e.a(u5Var, z);
        if (z) {
            u5Var.f9369a.b(this);
        }
        if (w5.t && this.f9369a != null && this.f9370e.getCurrentSize() == 0) {
            this.f = true;
            w5Var.f9586a = true;
        }
    }

    public void a(w5 w5Var, SolverVariable solverVariable, boolean z) {
        if (solverVariable.g) {
            this.b += solverVariable.f * this.f9370e.a(solverVariable);
            this.f9370e.a(solverVariable, z);
            if (z) {
                solverVariable.b(this);
            }
            if (w5.t && solverVariable != null && this.f9370e.getCurrentSize() == 0) {
                this.f = true;
                w5Var.f9586a = true;
            }
        }
    }

    public final SolverVariable a(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int currentSize = this.f9370e.getCurrentSize();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < currentSize; i++) {
            float fB = this.f9370e.b(i);
            if (fB < 0.0f) {
                SolverVariable solverVariableA = this.f9370e.a(i);
                if ((zArr == null || !zArr[solverVariableA.c]) && solverVariableA != solverVariable && (((type = solverVariableA.j) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fB < f)) {
                    f = fB;
                    solverVariable2 = solverVariableA;
                }
            }
        }
        return solverVariable2;
    }

    @Override // supwisdom.w5.a
    public SolverVariable a(w5 w5Var, boolean[] zArr) {
        return a(zArr, (SolverVariable) null);
    }

    @Override // supwisdom.w5.a
    public void a(w5.a aVar) {
        if (aVar instanceof u5) {
            u5 u5Var = (u5) aVar;
            this.f9369a = null;
            this.f9370e.clear();
            for (int i = 0; i < u5Var.f9370e.getCurrentSize(); i++) {
                this.f9370e.a(u5Var.f9370e.a(i), u5Var.f9370e.b(i), true);
            }
        }
    }

    @Override // supwisdom.w5.a
    public void a(SolverVariable solverVariable) {
        int i = solverVariable.f1223e;
        float f = 1.0f;
        if (i != 1) {
            if (i == 2) {
                f = 1000.0f;
            } else if (i == 3) {
                f = 1000000.0f;
            } else if (i == 4) {
                f = 1.0E9f;
            } else if (i == 5) {
                f = 1.0E12f;
            }
        }
        this.f9370e.a(solverVariable, f);
    }
}
