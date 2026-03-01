package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import supwisdom.u5;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class t5 implements u5.a {
    public static float l = 0.001f;
    public final u5 b;
    public final v5 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9252a = 0;
    public int d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SolverVariable f9253e = null;
    public int[] f = new int[8];
    public int[] g = new int[8];
    public float[] h = new float[8];
    public int i = -1;
    public int j = -1;
    public boolean k = false;

    public t5(u5 u5Var, v5 v5Var) {
        this.b = u5Var;
        this.c = v5Var;
    }

    @Override // supwisdom.u5.a
    public final void a(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            a(solverVariable, true);
            return;
        }
        int i = this.i;
        if (i == -1) {
            this.i = 0;
            this.h[0] = f;
            this.f[0] = solverVariable.c;
            this.g[0] = -1;
            solverVariable.m++;
            solverVariable.a(this.b);
            this.f9252a++;
            if (this.k) {
                return;
            }
            int i2 = this.j + 1;
            this.j = i2;
            int[] iArr = this.f;
            if (i2 >= iArr.length) {
                this.k = true;
                this.j = iArr.length - 1;
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.f9252a; i4++) {
            int[] iArr2 = this.f;
            int i5 = iArr2[i];
            int i6 = solverVariable.c;
            if (i5 == i6) {
                this.h[i] = f;
                return;
            }
            if (iArr2[i] < i6) {
                i3 = i;
            }
            i = this.g[i];
        }
        int length = this.j;
        int i7 = length + 1;
        if (this.k) {
            int[] iArr3 = this.f;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i7;
        }
        int[] iArr4 = this.f;
        if (length >= iArr4.length && this.f9252a < iArr4.length) {
            int i8 = 0;
            while (true) {
                int[] iArr5 = this.f;
                if (i8 >= iArr5.length) {
                    break;
                }
                if (iArr5[i8] == -1) {
                    length = i8;
                    break;
                }
                i8++;
            }
        }
        int[] iArr6 = this.f;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i9 = this.d * 2;
            this.d = i9;
            this.k = false;
            this.j = length - 1;
            this.h = Arrays.copyOf(this.h, i9);
            this.f = Arrays.copyOf(this.f, this.d);
            this.g = Arrays.copyOf(this.g, this.d);
        }
        this.f[length] = solverVariable.c;
        this.h[length] = f;
        if (i3 != -1) {
            int[] iArr7 = this.g;
            iArr7[length] = iArr7[i3];
            iArr7[i3] = length;
        } else {
            this.g[length] = this.i;
            this.i = length;
        }
        solverVariable.m++;
        solverVariable.a(this.b);
        this.f9252a++;
        if (!this.k) {
            this.j++;
        }
        if (this.f9252a >= this.f.length) {
            this.k = true;
        }
        int i10 = this.j;
        int[] iArr8 = this.f;
        if (i10 >= iArr8.length) {
            this.k = true;
            this.j = iArr8.length - 1;
        }
    }

    @Override // supwisdom.u5.a
    public boolean b(SolverVariable solverVariable) {
        int i = this.i;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f9252a; i2++) {
            if (this.f[i] == solverVariable.c) {
                return true;
            }
            i = this.g[i];
        }
        return false;
    }

    @Override // supwisdom.u5.a
    public final void clear() {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f9252a; i2++) {
            SolverVariable solverVariable = this.c.d[this.f[i]];
            if (solverVariable != null) {
                solverVariable.b(this.b);
            }
            i = this.g[i];
        }
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.f9252a = 0;
    }

    @Override // supwisdom.u5.a
    public int getCurrentSize() {
        return this.f9252a;
    }

    @Override // supwisdom.u5.a
    public void invert() {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f9252a; i2++) {
            float[] fArr = this.h;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.g[i];
        }
    }

    public String toString() {
        int i = this.i;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.f9252a; i2++) {
            str = ((str + " -> ") + this.h[i] + " : ") + this.c.d[this.f[i]];
            i = this.g[i];
        }
        return str;
    }

    @Override // supwisdom.u5.a
    public float b(int i) {
        int i2 = this.i;
        for (int i3 = 0; i2 != -1 && i3 < this.f9252a; i3++) {
            if (i3 == i) {
                return this.h[i2];
            }
            i2 = this.g[i2];
        }
        return 0.0f;
    }

    @Override // supwisdom.u5.a
    public void a(SolverVariable solverVariable, float f, boolean z) {
        float f2 = l;
        if (f <= (-f2) || f >= f2) {
            int i = this.i;
            if (i == -1) {
                this.i = 0;
                this.h[0] = f;
                this.f[0] = solverVariable.c;
                this.g[0] = -1;
                solverVariable.m++;
                solverVariable.a(this.b);
                this.f9252a++;
                if (this.k) {
                    return;
                }
                int i2 = this.j + 1;
                this.j = i2;
                int[] iArr = this.f;
                if (i2 >= iArr.length) {
                    this.k = true;
                    this.j = iArr.length - 1;
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.f9252a; i4++) {
                int[] iArr2 = this.f;
                int i5 = iArr2[i];
                int i6 = solverVariable.c;
                if (i5 == i6) {
                    float f3 = this.h[i] + f;
                    float f4 = l;
                    if (f3 > (-f4) && f3 < f4) {
                        f3 = 0.0f;
                    }
                    this.h[i] = f3;
                    if (f3 == 0.0f) {
                        if (i == this.i) {
                            this.i = this.g[i];
                        } else {
                            int[] iArr3 = this.g;
                            iArr3[i3] = iArr3[i];
                        }
                        if (z) {
                            solverVariable.b(this.b);
                        }
                        if (this.k) {
                            this.j = i;
                        }
                        solverVariable.m--;
                        this.f9252a--;
                        return;
                    }
                    return;
                }
                if (iArr2[i] < i6) {
                    i3 = i;
                }
                i = this.g[i];
            }
            int length = this.j;
            int i7 = length + 1;
            if (this.k) {
                int[] iArr4 = this.f;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i7;
            }
            int[] iArr5 = this.f;
            if (length >= iArr5.length && this.f9252a < iArr5.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr6 = this.f;
                    if (i8 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i8] == -1) {
                        length = i8;
                        break;
                    }
                    i8++;
                }
            }
            int[] iArr7 = this.f;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i9 = this.d * 2;
                this.d = i9;
                this.k = false;
                this.j = length - 1;
                this.h = Arrays.copyOf(this.h, i9);
                this.f = Arrays.copyOf(this.f, this.d);
                this.g = Arrays.copyOf(this.g, this.d);
            }
            this.f[length] = solverVariable.c;
            this.h[length] = f;
            if (i3 != -1) {
                int[] iArr8 = this.g;
                iArr8[length] = iArr8[i3];
                iArr8[i3] = length;
            } else {
                this.g[length] = this.i;
                this.i = length;
            }
            solverVariable.m++;
            solverVariable.a(this.b);
            this.f9252a++;
            if (!this.k) {
                this.j++;
            }
            int i10 = this.j;
            int[] iArr9 = this.f;
            if (i10 >= iArr9.length) {
                this.k = true;
                this.j = iArr9.length - 1;
            }
        }
    }

    @Override // supwisdom.u5.a
    public float a(u5 u5Var, boolean z) {
        float fA = a(u5Var.f9369a);
        a(u5Var.f9369a, z);
        u5.a aVar = u5Var.f9370e;
        int currentSize = aVar.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable solverVariableA = aVar.a(i);
            a(solverVariableA, aVar.a(solverVariableA) * fA, z);
        }
        return fA;
    }

    @Override // supwisdom.u5.a
    public final float a(SolverVariable solverVariable, boolean z) {
        if (this.f9253e == solverVariable) {
            this.f9253e = null;
        }
        int i = this.i;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f9252a) {
            if (this.f[i] == solverVariable.c) {
                if (i == this.i) {
                    this.i = this.g[i];
                } else {
                    int[] iArr = this.g;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.b(this.b);
                }
                solverVariable.m--;
                this.f9252a--;
                this.f[i] = -1;
                if (this.k) {
                    this.j = i;
                }
                return this.h[i];
            }
            i2++;
            i3 = i;
            i = this.g[i];
        }
        return 0.0f;
    }

    @Override // supwisdom.u5.a
    public void a(float f) {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f9252a; i2++) {
            float[] fArr = this.h;
            fArr[i] = fArr[i] / f;
            i = this.g[i];
        }
    }

    @Override // supwisdom.u5.a
    public SolverVariable a(int i) {
        int i2 = this.i;
        for (int i3 = 0; i2 != -1 && i3 < this.f9252a; i3++) {
            if (i3 == i) {
                return this.c.d[this.f[i2]];
            }
            i2 = this.g[i2];
        }
        return null;
    }

    @Override // supwisdom.u5.a
    public final float a(SolverVariable solverVariable) {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f9252a; i2++) {
            if (this.f[i] == solverVariable.c) {
                return this.h[i];
            }
            i = this.g[i];
        }
        return 0.0f;
    }
}
