package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import supwisdom.u5;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class b6 implements u5.a {
    public static float m = 0.001f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7026a = 16;
    public int b = 16;
    public int[] c = new int[16];
    public int[] d = new int[16];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f7027e = new int[16];
    public float[] f = new float[16];
    public int[] g = new int[16];
    public int[] h = new int[16];
    public int i = 0;
    public int j = -1;
    public final u5 k;
    public final v5 l;

    public b6(u5 u5Var, v5 v5Var) {
        this.k = u5Var;
        this.l = v5Var;
        clear();
    }

    @Override // supwisdom.u5.a
    public SolverVariable a(int i) {
        int i2 = this.i;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.j;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return this.l.d[this.f7027e[i3]];
            }
            i3 = this.h[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // supwisdom.u5.a
    public float b(int i) {
        int i2 = this.i;
        int i3 = this.j;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i) {
                return this.f[i3];
            }
            i3 = this.h[i3];
            if (i3 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    public int c(SolverVariable solverVariable) {
        if (this.i != 0 && solverVariable != null) {
            int i = solverVariable.c;
            int i2 = this.c[i % this.b];
            if (i2 == -1) {
                return -1;
            }
            if (this.f7027e[i2] == i) {
                return i2;
            }
            while (true) {
                int[] iArr = this.d;
                if (iArr[i2] == -1 || this.f7027e[iArr[i2]] == i) {
                    break;
                }
                i2 = iArr[i2];
            }
            int[] iArr2 = this.d;
            if (iArr2[i2] != -1 && this.f7027e[iArr2[i2]] == i) {
                return iArr2[i2];
            }
        }
        return -1;
    }

    @Override // supwisdom.u5.a
    public void clear() {
        int i = this.i;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableA = a(i2);
            if (solverVariableA != null) {
                solverVariableA.b(this.k);
            }
        }
        for (int i3 = 0; i3 < this.f7026a; i3++) {
            this.f7027e[i3] = -1;
            this.d[i3] = -1;
        }
        for (int i4 = 0; i4 < this.b; i4++) {
            this.c[i4] = -1;
        }
        this.i = 0;
        this.j = -1;
    }

    public final void d(SolverVariable solverVariable) {
        int i = solverVariable.c;
        int i2 = i % this.b;
        int[] iArr = this.c;
        int i3 = iArr[i2];
        if (i3 == -1) {
            return;
        }
        if (this.f7027e[i3] == i) {
            int[] iArr2 = this.d;
            iArr[i2] = iArr2[i3];
            iArr2[i3] = -1;
            return;
        }
        while (true) {
            int[] iArr3 = this.d;
            if (iArr3[i3] == -1 || this.f7027e[iArr3[i3]] == i) {
                break;
            } else {
                i3 = iArr3[i3];
            }
        }
        int[] iArr4 = this.d;
        int i4 = iArr4[i3];
        if (i4 == -1 || this.f7027e[i4] != i) {
            return;
        }
        iArr4[i3] = iArr4[i4];
        iArr4[i4] = -1;
    }

    @Override // supwisdom.u5.a
    public int getCurrentSize() {
        return this.i;
    }

    @Override // supwisdom.u5.a
    public void invert() {
        int i = this.i;
        int i2 = this.j;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.f;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.h[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i = this.i;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableA = a(i2);
            if (solverVariableA != null) {
                String str2 = str + solverVariableA + " = " + b(i2) + Operators.SPACE_STR;
                int iC = c(solverVariableA);
                String str3 = str2 + "[p: ";
                String str4 = (this.g[iC] != -1 ? str3 + this.l.d[this.f7027e[this.g[iC]]] : str3 + "none") + ", n: ";
                str = (this.h[iC] != -1 ? str4 + this.l.d[this.f7027e[this.h[iC]]] : str4 + "none") + Operators.ARRAY_END_STR;
            }
        }
        return str + " }";
    }

    @Override // supwisdom.u5.a
    public float a(SolverVariable solverVariable) {
        int iC = c(solverVariable);
        if (iC != -1) {
            return this.f[iC];
        }
        return 0.0f;
    }

    @Override // supwisdom.u5.a
    public boolean b(SolverVariable solverVariable) {
        return c(solverVariable) != -1;
    }

    public final void b() {
        int i = this.f7026a * 2;
        this.f7027e = Arrays.copyOf(this.f7027e, i);
        this.f = Arrays.copyOf(this.f, i);
        this.g = Arrays.copyOf(this.g, i);
        this.h = Arrays.copyOf(this.h, i);
        this.d = Arrays.copyOf(this.d, i);
        for (int i2 = this.f7026a; i2 < i; i2++) {
            this.f7027e[i2] = -1;
            this.d[i2] = -1;
        }
        this.f7026a = i;
    }

    public final void a(SolverVariable solverVariable, int i) {
        int[] iArr;
        int i2 = solverVariable.c % this.b;
        int[] iArr2 = this.c;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            iArr2[i2] = i;
        } else {
            while (true) {
                iArr = this.d;
                if (iArr[i3] == -1) {
                    break;
                } else {
                    i3 = iArr[i3];
                }
            }
            iArr[i3] = i;
        }
        this.d[i] = -1;
    }

    public final void a(int i, SolverVariable solverVariable, float f) {
        this.f7027e[i] = solverVariable.c;
        this.f[i] = f;
        this.g[i] = -1;
        this.h[i] = -1;
        solverVariable.a(this.k);
        solverVariable.m++;
        this.i++;
    }

    public final void b(int i, SolverVariable solverVariable, float f) {
        int iA = a();
        a(iA, solverVariable, f);
        if (i != -1) {
            this.g[iA] = i;
            int[] iArr = this.h;
            iArr[iA] = iArr[i];
            iArr[i] = iA;
        } else {
            this.g[iA] = -1;
            if (this.i > 0) {
                this.h[iA] = this.j;
                this.j = iA;
            } else {
                this.h[iA] = -1;
            }
        }
        int[] iArr2 = this.h;
        if (iArr2[iA] != -1) {
            this.g[iArr2[iA]] = iA;
        }
        a(solverVariable, iA);
    }

    public final int a() {
        for (int i = 0; i < this.f7026a; i++) {
            if (this.f7027e[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    @Override // supwisdom.u5.a
    public void a(SolverVariable solverVariable, float f) {
        float f2 = m;
        if (f > (-f2) && f < f2) {
            a(solverVariable, true);
            return;
        }
        if (this.i == 0) {
            a(0, solverVariable, f);
            a(solverVariable, 0);
            this.j = 0;
            return;
        }
        int iC = c(solverVariable);
        if (iC != -1) {
            this.f[iC] = f;
            return;
        }
        if (this.i + 1 >= this.f7026a) {
            b();
        }
        int i = this.i;
        int i2 = this.j;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int[] iArr = this.f7027e;
            int i5 = iArr[i2];
            int i6 = solverVariable.c;
            if (i5 == i6) {
                this.f[i2] = f;
                return;
            }
            if (iArr[i2] < i6) {
                i3 = i2;
            }
            i2 = this.h[i2];
            if (i2 == -1) {
                break;
            }
        }
        b(i3, solverVariable, f);
    }

    @Override // supwisdom.u5.a
    public float a(SolverVariable solverVariable, boolean z) {
        int iC = c(solverVariable);
        if (iC == -1) {
            return 0.0f;
        }
        d(solverVariable);
        float f = this.f[iC];
        if (this.j == iC) {
            this.j = this.h[iC];
        }
        this.f7027e[iC] = -1;
        int[] iArr = this.g;
        if (iArr[iC] != -1) {
            int[] iArr2 = this.h;
            iArr2[iArr[iC]] = iArr2[iC];
        }
        int[] iArr3 = this.h;
        if (iArr3[iC] != -1) {
            int[] iArr4 = this.g;
            iArr4[iArr3[iC]] = iArr4[iC];
        }
        this.i--;
        solverVariable.m--;
        if (z) {
            solverVariable.b(this.k);
        }
        return f;
    }

    @Override // supwisdom.u5.a
    public void a(SolverVariable solverVariable, float f, boolean z) {
        float f2 = m;
        if (f <= (-f2) || f >= f2) {
            int iC = c(solverVariable);
            if (iC == -1) {
                a(solverVariable, f);
                return;
            }
            float[] fArr = this.f;
            fArr[iC] = fArr[iC] + f;
            float f3 = fArr[iC];
            float f4 = m;
            if (f3 <= (-f4) || fArr[iC] >= f4) {
                return;
            }
            fArr[iC] = 0.0f;
            a(solverVariable, z);
        }
    }

    @Override // supwisdom.u5.a
    public float a(u5 u5Var, boolean z) {
        float fA = a(u5Var.f9369a);
        a(u5Var.f9369a, z);
        b6 b6Var = (b6) u5Var.f9370e;
        int currentSize = b6Var.getCurrentSize();
        int i = b6Var.j;
        int i2 = 0;
        int i3 = 0;
        while (i2 < currentSize) {
            int[] iArr = b6Var.f7027e;
            if (iArr[i3] != -1) {
                a(this.l.d[iArr[i3]], b6Var.f[i3] * fA, z);
                i2++;
            }
            i3++;
        }
        return fA;
    }

    @Override // supwisdom.u5.a
    public void a(float f) {
        int i = this.i;
        int i2 = this.j;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.f;
            fArr[i2] = fArr[i2] / f;
            i2 = this.h[i2];
            if (i2 == -1) {
                return;
            }
        }
    }
}
