package androidx.constraintlayout.solver;

import java.util.Arrays;
import supwisdom.u5;
import supwisdom.w5;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable {
    public static int q = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1222a;
    public String b;
    public float f;
    public Type j;
    public int c = -1;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1223e = 0;
    public boolean g = false;
    public float[] h = new float[9];
    public float[] i = new float[9];
    public u5[] k = new u5[16];
    public int l = 0;
    public int m = 0;
    public boolean n = false;
    public int o = -1;
    public float p = 0.0f;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.j = type;
    }

    public static void b() {
        q++;
    }

    public final void a(u5 u5Var) {
        int i = 0;
        while (true) {
            int i2 = this.l;
            if (i >= i2) {
                u5[] u5VarArr = this.k;
                if (i2 >= u5VarArr.length) {
                    this.k = (u5[]) Arrays.copyOf(u5VarArr, u5VarArr.length * 2);
                }
                u5[] u5VarArr2 = this.k;
                int i3 = this.l;
                u5VarArr2[i3] = u5Var;
                this.l = i3 + 1;
                return;
            }
            if (this.k[i] == u5Var) {
                return;
            } else {
                i++;
            }
        }
    }

    public String toString() {
        if (this.b != null) {
            return "" + this.b;
        }
        return "" + this.c;
    }

    public final void b(u5 u5Var) {
        int i = this.l;
        int i2 = 0;
        while (i2 < i) {
            if (this.k[i2] == u5Var) {
                while (i2 < i - 1) {
                    u5[] u5VarArr = this.k;
                    int i3 = i2 + 1;
                    u5VarArr[i2] = u5VarArr[i3];
                    i2 = i3;
                }
                this.l--;
                return;
            }
            i2++;
        }
    }

    public final void a(w5 w5Var, u5 u5Var) {
        int i = this.l;
        for (int i2 = 0; i2 < i; i2++) {
            this.k[i2].a(w5Var, u5Var, false);
        }
        this.l = 0;
    }

    public void a(w5 w5Var, float f) {
        this.f = f;
        this.g = true;
        this.n = false;
        this.o = -1;
        this.p = 0.0f;
        int i = this.l;
        this.d = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.k[i2].a(w5Var, this, false);
        }
        this.l = 0;
    }

    public void a() {
        this.b = null;
        this.j = Type.UNKNOWN;
        this.f1223e = 0;
        this.c = -1;
        this.d = -1;
        this.f = 0.0f;
        this.g = false;
        this.n = false;
        this.o = -1;
        this.p = 0.0f;
        int i = this.l;
        for (int i2 = 0; i2 < i; i2++) {
            this.k[i2] = null;
        }
        this.l = 0;
        this.m = 0;
        this.f1222a = false;
        Arrays.fill(this.i, 0.0f);
    }

    public void a(Type type, String str) {
        this.j = type;
    }
}
