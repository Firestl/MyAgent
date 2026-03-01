package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Comparator;
import supwisdom.u5;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class a6 extends u5 {
    public int g;
    public SolverVariable[] h;
    public SolverVariable[] i;
    public int j;
    public b k;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class a implements Comparator<SolverVariable> {
        public a(a6 a6Var) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.c - solverVariable2.c;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class b implements Comparable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SolverVariable f6857a;

        public b(a6 a6Var) {
        }

        public void a(SolverVariable solverVariable) {
            this.f6857a = solverVariable;
        }

        public final boolean b(SolverVariable solverVariable) {
            int i = 8;
            while (true) {
                if (i < 0) {
                    break;
                }
                float f = solverVariable.i[i];
                float f2 = this.f6857a.i[i];
                if (f2 == f) {
                    i--;
                } else if (f2 < f) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f6857a.c - ((SolverVariable) obj).c;
        }

        public String toString() {
            String str = "[ ";
            if (this.f6857a != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.f6857a.i[i] + Operators.SPACE_STR;
                }
            }
            return str + "] " + this.f6857a;
        }

        public boolean a(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (!this.f6857a.f1222a) {
                for (int i = 0; i < 9; i++) {
                    float f2 = solverVariable.i[i];
                    if (f2 != 0.0f) {
                        float f3 = f2 * f;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        this.f6857a.i[i] = f3;
                    } else {
                        this.f6857a.i[i] = 0.0f;
                    }
                }
                return true;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f6857a.i;
                fArr[i2] = fArr[i2] + (solverVariable.i[i2] * f);
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f6857a.i[i2] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                a6.this.f(this.f6857a);
            }
            return false;
        }

        public void b() {
            Arrays.fill(this.f6857a.i, 0.0f);
        }

        public final boolean a() {
            for (int i = 8; i >= 0; i--) {
                float f = this.f6857a.i[i];
                if (f > 0.0f) {
                    return false;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }
    }

    public a6(v5 v5Var) {
        super(v5Var);
        this.g = 128;
        this.h = new SolverVariable[128];
        this.i = new SolverVariable[128];
        this.j = 0;
        this.k = new b(this);
    }

    @Override // supwisdom.u5, supwisdom.w5.a
    public void clear() {
        this.j = 0;
        this.b = 0.0f;
    }

    public final void e(SolverVariable solverVariable) {
        int i;
        int i2 = this.j + 1;
        SolverVariable[] solverVariableArr = this.h;
        if (i2 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.h = solverVariableArr2;
            this.i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.h;
        int i3 = this.j;
        solverVariableArr3[i3] = solverVariable;
        int i4 = i3 + 1;
        this.j = i4;
        if (i4 > 1 && solverVariableArr3[i4 - 1].c > solverVariable.c) {
            int i5 = 0;
            while (true) {
                i = this.j;
                if (i5 >= i) {
                    break;
                }
                this.i[i5] = this.h[i5];
                i5++;
            }
            Arrays.sort(this.i, 0, i, new a(this));
            for (int i6 = 0; i6 < this.j; i6++) {
                this.h[i6] = this.i[i6];
            }
        }
        solverVariable.f1222a = true;
        solverVariable.a(this);
    }

    public final void f(SolverVariable solverVariable) {
        int i = 0;
        while (i < this.j) {
            if (this.h[i] == solverVariable) {
                while (true) {
                    int i2 = this.j;
                    if (i >= i2 - 1) {
                        this.j = i2 - 1;
                        solverVariable.f1222a = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.h;
                        int i3 = i + 1;
                        solverVariableArr[i] = solverVariableArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // supwisdom.u5, supwisdom.w5.a
    public boolean isEmpty() {
        return this.j == 0;
    }

    @Override // supwisdom.u5
    public String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.j; i++) {
            this.k.a(this.h[i]);
            str = str + this.k + Operators.SPACE_STR;
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // supwisdom.u5, supwisdom.w5.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.solver.SolverVariable a(supwisdom.w5 r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = -1
        L3:
            int r2 = r4.j
            if (r0 >= r2) goto L32
            androidx.constraintlayout.solver.SolverVariable[] r2 = r4.h
            r2 = r2[r0]
            int r3 = r2.c
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            supwisdom.a6$b r3 = r4.k
            r3.a(r2)
            if (r1 != r5) goto L22
            supwisdom.a6$b r2 = r4.k
            boolean r2 = r2.a()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            supwisdom.a6$b r2 = r4.k
            androidx.constraintlayout.solver.SolverVariable[] r3 = r4.h
            r3 = r3[r1]
            boolean r2 = r2.b(r3)
            if (r2 == 0) goto L2f
        L2e:
            r1 = r0
        L2f:
            int r0 = r0 + 1
            goto L3
        L32:
            if (r1 != r5) goto L36
            r5 = 0
            return r5
        L36:
            androidx.constraintlayout.solver.SolverVariable[] r5 = r4.h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.a6.a(supwisdom.w5, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    @Override // supwisdom.u5, supwisdom.w5.a
    public void a(SolverVariable solverVariable) {
        this.k.a(solverVariable);
        this.k.b();
        solverVariable.i[solverVariable.f1223e] = 1.0f;
        e(solverVariable);
    }

    @Override // supwisdom.u5
    public void a(w5 w5Var, u5 u5Var, boolean z) {
        SolverVariable solverVariable = u5Var.f9369a;
        if (solverVariable == null) {
            return;
        }
        u5.a aVar = u5Var.f9370e;
        int currentSize = aVar.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable solverVariableA = aVar.a(i);
            float fB = aVar.b(i);
            this.k.a(solverVariableA);
            if (this.k.a(solverVariable, fB)) {
                e(solverVariableA);
            }
            this.b += u5Var.b * fB;
        }
        f(solverVariable);
    }
}
