package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import supwisdom.a7;
import supwisdom.c7;
import supwisdom.f6;
import supwisdom.h6;
import supwisdom.k6;
import supwisdom.l6;
import supwisdom.p6;
import supwisdom.s6;
import supwisdom.v5;
import supwisdom.w5;
import supwisdom.y6;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintWidget {
    public static float J0 = 0.5f;
    public boolean A0;
    public boolean B0;
    public float[] C0;
    public boolean D;
    public ConstraintWidget[] D0;
    public ConstraintWidget[] E0;
    public ConstraintWidget F0;
    public ConstraintWidget G0;
    public int H0;
    public int I0;
    public ConstraintAnchor O;
    public ConstraintAnchor[] P;
    public ArrayList<ConstraintAnchor> Q;
    public boolean[] R;
    public DimensionBehaviour[] S;
    public ConstraintWidget T;
    public int U;
    public int V;
    public float W;
    public int X;
    public int Y;
    public int Z;
    public int a0;
    public p6 b;
    public int b0;
    public p6 c;
    public int c0;
    public int d0;
    public int e0;
    public int f0;
    public int g0;
    public float h0;
    public float i0;
    public Object j0;
    public int k0;
    public int l0;
    public String m0;
    public String n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public boolean s0;
    public boolean t0;
    public boolean u0;
    public boolean v0;
    public boolean w;
    public boolean w0;
    public boolean x;
    public boolean x0;
    public int y0;
    public int z0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1227a = false;
    public y6 d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a7 f1228e = null;
    public boolean[] f = {true, true};
    public boolean g = true;
    public boolean h = false;
    public boolean i = true;
    public boolean j = false;
    public boolean k = false;
    public int l = -1;
    public int m = -1;
    public int n = 0;
    public int o = 0;
    public int[] p = new int[2];
    public int q = 0;
    public int r = 0;
    public float s = 1.0f;
    public int t = 0;
    public int u = 0;
    public float v = 1.0f;
    public int y = -1;
    public float z = 1.0f;
    public int[] A = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    public float B = 0.0f;
    public boolean C = false;
    public boolean E = false;
    public int F = 0;
    public int G = 0;
    public ConstraintAnchor H = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
    public ConstraintAnchor I = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
    public ConstraintAnchor J = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
    public ConstraintAnchor K = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
    public ConstraintAnchor L = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
    public ConstraintAnchor M = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
    public ConstraintAnchor N = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1229a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            b = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            f1229a = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1229a[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1229a[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1229a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1229a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1229a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1229a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1229a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1229a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.O = constraintAnchor;
        this.P = new ConstraintAnchor[]{this.H, this.J, this.I, this.K, this.L, constraintAnchor};
        this.Q = new ArrayList<>();
        this.R = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.S = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.T = null;
        this.U = 0;
        this.V = 0;
        this.W = 0.0f;
        this.X = -1;
        this.Y = 0;
        this.Z = 0;
        this.a0 = 0;
        this.b0 = 0;
        this.c0 = 0;
        this.d0 = 0;
        this.e0 = 0;
        float f = J0;
        this.h0 = f;
        this.i0 = f;
        this.k0 = 0;
        this.l0 = 0;
        this.m0 = null;
        this.n0 = null;
        this.y0 = 0;
        this.z0 = 0;
        this.C0 = new float[]{-1.0f, -1.0f};
        this.D0 = new ConstraintWidget[]{null, null};
        this.E0 = new ConstraintWidget[]{null, null};
        this.F0 = null;
        this.G0 = null;
        this.H0 = -1;
        this.I0 = -1;
        b();
    }

    public DimensionBehaviour A() {
        return this.S[1];
    }

    public int B() {
        int i = this.H != null ? 0 + this.I.g : 0;
        return this.J != null ? i + this.K.g : i;
    }

    public int C() {
        return this.l0;
    }

    public int D() {
        if (this.l0 == 8) {
            return 0;
        }
        return this.U;
    }

    public int E() {
        ConstraintWidget constraintWidget = this.T;
        return (constraintWidget == null || !(constraintWidget instanceof f6)) ? this.Y : ((f6) constraintWidget).R0 + this.Y;
    }

    public int F() {
        ConstraintWidget constraintWidget = this.T;
        return (constraintWidget == null || !(constraintWidget instanceof f6)) ? this.Z : ((f6) constraintWidget).S0 + this.Z;
    }

    public boolean G() {
        return this.C;
    }

    public boolean H() {
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            if (this.Q.get(i).j()) {
                return true;
            }
        }
        return false;
    }

    public boolean I() {
        ConstraintAnchor constraintAnchor = this.H;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f;
        if (constraintAnchor2 != null && constraintAnchor2.f == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.J;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f;
        return constraintAnchor4 != null && constraintAnchor4.f == constraintAnchor3;
    }

    public boolean J() {
        return this.D;
    }

    public boolean K() {
        ConstraintAnchor constraintAnchor = this.I;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f;
        if (constraintAnchor2 != null && constraintAnchor2.f == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.K;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f;
        return constraintAnchor4 != null && constraintAnchor4.f == constraintAnchor3;
    }

    public boolean L() {
        return this.E;
    }

    public boolean M() {
        return this.g && this.l0 != 8;
    }

    public boolean N() {
        return this.j || (this.H.k() && this.J.k());
    }

    public boolean O() {
        return this.k || (this.I.k() && this.K.k());
    }

    public boolean P() {
        DimensionBehaviour[] dimensionBehaviourArr = this.S;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour2 && dimensionBehaviourArr[1] == dimensionBehaviour2;
    }

    public void Q() {
        this.H.m();
        this.I.m();
        this.J.m();
        this.K.m();
        this.L.m();
        this.M.m();
        this.N.m();
        this.O.m();
        this.T = null;
        this.B = 0.0f;
        this.U = 0;
        this.V = 0;
        this.W = 0.0f;
        this.X = -1;
        this.Y = 0;
        this.Z = 0;
        this.c0 = 0;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = 0;
        this.g0 = 0;
        float f = J0;
        this.h0 = f;
        this.i0 = f;
        DimensionBehaviour[] dimensionBehaviourArr = this.S;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.j0 = null;
        this.k0 = 0;
        this.l0 = 0;
        this.n0 = null;
        this.w0 = false;
        this.x0 = false;
        this.y0 = 0;
        this.z0 = 0;
        this.A0 = false;
        this.B0 = false;
        float[] fArr = this.C0;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.l = -1;
        this.m = -1;
        int[] iArr = this.A;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.n = 0;
        this.o = 0;
        this.s = 1.0f;
        this.v = 1.0f;
        this.r = Integer.MAX_VALUE;
        this.u = Integer.MAX_VALUE;
        this.q = 0;
        this.t = 0;
        this.y = -1;
        this.z = 1.0f;
        boolean[] zArr = this.f;
        zArr[0] = true;
        zArr[1] = true;
        this.E = false;
        boolean[] zArr2 = this.R;
        zArr2[0] = false;
        zArr2[1] = false;
        this.g = true;
    }

    public void R() {
        ConstraintWidget constraintWidgetW = w();
        if (constraintWidgetW != null && (constraintWidgetW instanceof f6) && ((f6) w()).Z()) {
            return;
        }
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).m();
        }
    }

    public void S() {
        this.j = false;
        this.k = false;
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).n();
        }
    }

    public void a(int i, int i2) {
        this.H.a(i);
        this.J.a(i2);
        this.Y = i;
        this.U = i2 - i;
        this.j = true;
    }

    public void b(int i, int i2) {
        this.I.a(i);
        this.K.a(i2);
        this.Z = i;
        this.V = i2 - i;
        if (this.C) {
            this.L.a(i + this.e0);
        }
        this.k = true;
    }

    public void c(boolean z) {
        this.E = z;
    }

    public void d(boolean z) {
        this.g = z;
    }

    public void e() {
        if (this.d == null) {
            this.d = new y6(this);
        }
        if (this.f1228e == null) {
            this.f1228e = new a7(this);
        }
    }

    public c7 f(int i) {
        if (i == 0) {
            return this.d;
        }
        if (i == 1) {
            return this.f1228e;
        }
        return null;
    }

    public boolean g(int i) {
        if (i == 0) {
            return (this.H.f != null ? 1 : 0) + (this.J.f != null ? 1 : 0) < 2;
        }
        return ((this.I.f != null ? 1 : 0) + (this.K.f != null ? 1 : 0)) + (this.L.f != null ? 1 : 0) < 2;
    }

    public Object h() {
        return this.j0;
    }

    public String i() {
        return this.m0;
    }

    public void j(int i) {
        if (this.C) {
            int i2 = i - this.e0;
            int i3 = this.V + i2;
            this.Z = i2;
            this.I.a(i2);
            this.K.a(i3);
            this.L.a(i);
            this.k = true;
        }
    }

    public void k(int i) {
        this.H.a(i);
        this.Y = i;
    }

    public void l(int i) {
        this.I.a(i);
        this.Z = i;
    }

    public float m() {
        return this.h0;
    }

    public void n(int i) {
        this.y0 = i;
    }

    public void o(int i) {
        this.A[1] = i;
    }

    public void p(int i) {
        this.A[0] = i;
    }

    public int q() {
        return this.F;
    }

    public int r() {
        return this.G;
    }

    public int s() {
        return this.A[1];
    }

    public int t() {
        return this.A[0];
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.n0 != null) {
            str = "type: " + this.n0 + Operators.SPACE_STR;
        } else {
            str = "";
        }
        sb.append(str);
        if (this.m0 != null) {
            str2 = "id: " + this.m0 + Operators.SPACE_STR;
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.Y);
        sb.append(", ");
        sb.append(this.Z);
        sb.append(") - (");
        sb.append(this.U);
        sb.append(" x ");
        sb.append(this.V);
        sb.append(")");
        return sb.toString();
    }

    public int u() {
        return this.g0;
    }

    public int v() {
        return this.f0;
    }

    public ConstraintWidget w() {
        return this.T;
    }

    public int x() {
        return E() + this.U;
    }

    public float y() {
        return this.i0;
    }

    public int z() {
        return this.z0;
    }

    public int c(int i) {
        if (i == 0) {
            return D();
        }
        if (i == 1) {
            return l();
        }
        return 0;
    }

    public void d(int i, int i2) {
        this.F = i;
        this.G = i2;
        d(false);
    }

    public final boolean h(int i) {
        int i2 = i * 2;
        ConstraintAnchor[] constraintAnchorArr = this.P;
        if (constraintAnchorArr[i2].f != null && constraintAnchorArr[i2].f.f != constraintAnchorArr[i2]) {
            int i3 = i2 + 1;
            if (constraintAnchorArr[i3].f != null && constraintAnchorArr[i3].f.f == constraintAnchorArr[i3]) {
                return true;
            }
        }
        return false;
    }

    public void i(int i) {
        this.e0 = i;
        this.C = i > 0;
    }

    public void m(int i) {
        this.V = i;
        int i2 = this.g0;
        if (i < i2) {
            this.V = i2;
        }
    }

    public int n() {
        return this.y0;
    }

    public DimensionBehaviour o() {
        return this.S[0];
    }

    public int p() {
        ConstraintAnchor constraintAnchor = this.H;
        int i = constraintAnchor != null ? 0 + constraintAnchor.g : 0;
        ConstraintAnchor constraintAnchor2 = this.J;
        return constraintAnchor2 != null ? i + constraintAnchor2.g : i;
    }

    public void q(int i) {
        if (i < 0) {
            this.g0 = 0;
        } else {
            this.g0 = i;
        }
    }

    public void r(int i) {
        if (i < 0) {
            this.f0 = 0;
        } else {
            this.f0 = i;
        }
    }

    public void s(int i) {
        this.z0 = i;
    }

    public void t(int i) {
        this.l0 = i;
    }

    public void u(int i) {
        this.U = i;
        int i2 = this.f0;
        if (i < i2) {
            this.U = i2;
        }
    }

    public void v(int i) {
        this.Y = i;
    }

    public void w(int i) {
        this.Z = i;
    }

    public int f() {
        return this.e0;
    }

    public int g() {
        return F() + this.V;
    }

    public int k() {
        return this.X;
    }

    public int l() {
        if (this.l0 == 8) {
            return 0;
        }
        return this.V;
    }

    public void c(float f) {
        this.i0 = f;
    }

    public void f(int i, int i2) {
        this.Z = i;
        int i3 = i2 - i;
        this.V = i3;
        int i4 = this.g0;
        if (i3 < i4) {
            this.V = i4;
        }
    }

    public void c(int i, int i2) {
        this.Y = i;
        int i3 = i2 - i;
        this.U = i3;
        int i4 = this.f0;
        if (i3 < i4) {
            this.U = i4;
        }
    }

    public void d(float f) {
        this.C0[1] = f;
    }

    public void e(int i, int i2) {
        this.Y = i;
        this.Z = i2;
    }

    public void a(boolean z) {
        this.C = z;
    }

    public boolean d() {
        return this.l0 != 8;
    }

    public void a(int i, boolean z) {
        this.R[i] = z;
    }

    public ConstraintWidget d(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i != 0) {
            if (i == 1 && (constraintAnchor2 = (constraintAnchor = this.K).f) != null && constraintAnchor2.f == constraintAnchor) {
                return constraintAnchor2.d;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.J;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f;
        if (constraintAnchor4 == null || constraintAnchor4.f != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.d;
    }

    public ConstraintWidget e(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i != 0) {
            if (i == 1 && (constraintAnchor2 = (constraintAnchor = this.I).f) != null && constraintAnchor2.f == constraintAnchor) {
                return constraintAnchor2.d;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.H;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f;
        if (constraintAnchor4 == null || constraintAnchor4.f != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.d;
    }

    public void a(v5 v5Var) {
        this.H.a(v5Var);
        this.I.a(v5Var);
        this.J.a(v5Var);
        this.K.a(v5Var);
        this.L.a(v5Var);
        this.O.a(v5Var);
        this.M.a(v5Var);
        this.N.a(v5Var);
    }

    public void b(boolean z) {
        this.D = z;
    }

    public final void b() {
        this.Q.add(this.H);
        this.Q.add(this.I);
        this.Q.add(this.J);
        this.Q.add(this.K);
        this.Q.add(this.M);
        this.Q.add(this.N);
        this.Q.add(this.O);
        this.Q.add(this.L);
    }

    public boolean c() {
        return (this instanceof l6) || (this instanceof h6);
    }

    public float j() {
        return this.W;
    }

    public void a(ConstraintWidget constraintWidget, float f, int i) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        a(type, constraintWidget, type, i, 0);
        this.B = f;
    }

    public void b(ConstraintWidget constraintWidget) {
        this.T = constraintWidget;
    }

    public void a(String str) {
        this.m0 = str;
    }

    public void b(int i, int i2, int i3, float f) {
        this.o = i;
        this.t = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.u = i3;
        this.v = f;
        if (f <= 0.0f || f >= 1.0f || this.o != 0) {
            return;
        }
        this.o = 2;
    }

    public void a(w5 w5Var) {
        w5Var.a(this.H);
        w5Var.a(this.I);
        w5Var.a(this.J);
        w5Var.a(this.K);
        if (this.e0 > 0) {
            w5Var.a(this.L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0084 A[PHI: r0
  0x0084: PHI (r0v2 int) = (r0v1 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int) binds: [B:45:0x0084, B:35:0x007d, B:23:0x004f, B:25:0x0055, B:27:0x0061, B:29:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0084 -> B:39:0x0085). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L8e
            int r1 = r9.length()
            if (r1 != 0) goto Lb
            goto L8e
        Lb:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L37
            int r6 = r2 + (-1)
            if (r3 >= r6) goto L37
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L2c
            r1 = 0
            goto L35
        L2c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L35
            r1 = 1
        L35:
            int r4 = r3 + 1
        L37:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L75
            int r2 = r2 - r5
            if (r3 >= r2) goto L75
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L84
            int r3 = r9.length()
            if (r3 <= 0) goto L84
            float r2 = java.lang.Float.parseFloat(r2)     // Catch: java.lang.NumberFormatException -> L84
            float r9 = java.lang.Float.parseFloat(r9)     // Catch: java.lang.NumberFormatException -> L84
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L84
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L84
            if (r1 != r5) goto L6f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch: java.lang.NumberFormatException -> L84
            goto L85
        L6f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch: java.lang.NumberFormatException -> L84
            goto L85
        L75:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L84
            float r9 = java.lang.Float.parseFloat(r9)     // Catch: java.lang.NumberFormatException -> L84
            goto L85
        L84:
            r9 = 0
        L85:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L8d
            r8.W = r9
            r8.X = r1
        L8d:
            return
        L8e:
            r8.W = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.b(java.lang.String):void");
    }

    public float a(int i) {
        if (i == 0) {
            return this.h0;
        }
        if (i == 1) {
            return this.i0;
        }
        return -1.0f;
    }

    public void a(int i, int i2, int i3, float f) {
        this.n = i;
        this.q = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.r = i3;
        this.s = f;
        if (f <= 0.0f || f >= 1.0f || this.n != 0) {
            return;
        }
        this.n = 2;
    }

    public void a(float f) {
        this.h0 = f;
    }

    public void a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.Y = i;
        this.Z = i2;
        if (this.l0 == 8) {
            this.U = 0;
            this.V = 0;
            return;
        }
        if (this.S[0] == DimensionBehaviour.FIXED && i7 < (i6 = this.U)) {
            i7 = i6;
        }
        if (this.S[1] == DimensionBehaviour.FIXED && i8 < (i5 = this.V)) {
            i8 = i5;
        }
        this.U = i7;
        this.V = i8;
        int i9 = this.g0;
        if (i8 < i9) {
            this.V = i9;
        }
        int i10 = this.U;
        int i11 = this.f0;
        if (i10 < i11) {
            this.U = i11;
        }
    }

    public void b(float f) {
        this.C0[0] = f;
    }

    public DimensionBehaviour b(int i) {
        if (i == 0) {
            return o();
        }
        if (i == 1) {
            return A();
        }
        return null;
    }

    public void a(Object obj) {
        this.j0 = obj;
    }

    public void b(DimensionBehaviour dimensionBehaviour) {
        this.S[1] = dimensionBehaviour;
    }

    public void a(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        a(type).a(constraintWidget.a(type2), i, i2, true);
    }

    public void b(w5 w5Var, boolean z) {
        a7 a7Var;
        y6 y6Var;
        int iB = w5Var.b(this.H);
        int iB2 = w5Var.b(this.I);
        int iB3 = w5Var.b(this.J);
        int iB4 = w5Var.b(this.K);
        if (z && (y6Var = this.d) != null) {
            s6 s6Var = y6Var.h;
            if (s6Var.j) {
                s6 s6Var2 = y6Var.i;
                if (s6Var2.j) {
                    iB = s6Var.g;
                    iB3 = s6Var2.g;
                }
            }
        }
        if (z && (a7Var = this.f1228e) != null) {
            s6 s6Var3 = a7Var.h;
            if (s6Var3.j) {
                s6 s6Var4 = a7Var.i;
                if (s6Var4.j) {
                    iB2 = s6Var3.g;
                    iB4 = s6Var4.g;
                }
            }
        }
        int i = iB4 - iB2;
        if (iB3 - iB < 0 || i < 0 || iB == Integer.MIN_VALUE || iB == Integer.MAX_VALUE || iB2 == Integer.MIN_VALUE || iB2 == Integer.MAX_VALUE || iB3 == Integer.MIN_VALUE || iB3 == Integer.MAX_VALUE || iB4 == Integer.MIN_VALUE || iB4 == Integer.MAX_VALUE) {
            iB4 = 0;
            iB = 0;
            iB2 = 0;
            iB3 = 0;
        }
        a(iB, iB2, iB3, iB4);
    }

    public void a(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        if (constraintAnchor.e() == this) {
            a(constraintAnchor.h(), constraintAnchor2.e(), constraintAnchor2.h(), i);
        }
    }

    public void a(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        boolean z;
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.CENTER;
        if (type == type3) {
            if (type2 == type3) {
                ConstraintAnchor constraintAnchorA = a(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor constraintAnchorA2 = a(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor constraintAnchorA3 = a(ConstraintAnchor.Type.TOP);
                ConstraintAnchor constraintAnchorA4 = a(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((constraintAnchorA == null || !constraintAnchorA.l()) && (constraintAnchorA2 == null || !constraintAnchorA2.l())) {
                    ConstraintAnchor.Type type4 = ConstraintAnchor.Type.LEFT;
                    a(type4, constraintWidget, type4, 0);
                    ConstraintAnchor.Type type5 = ConstraintAnchor.Type.RIGHT;
                    a(type5, constraintWidget, type5, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((constraintAnchorA3 == null || !constraintAnchorA3.l()) && (constraintAnchorA4 == null || !constraintAnchorA4.l())) {
                    ConstraintAnchor.Type type6 = ConstraintAnchor.Type.TOP;
                    a(type6, constraintWidget, type6, 0);
                    ConstraintAnchor.Type type7 = ConstraintAnchor.Type.BOTTOM;
                    a(type7, constraintWidget, type7, 0);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    a(ConstraintAnchor.Type.CENTER).a(constraintWidget.a(ConstraintAnchor.Type.CENTER), 0);
                    return;
                } else if (z) {
                    a(ConstraintAnchor.Type.CENTER_X).a(constraintWidget.a(ConstraintAnchor.Type.CENTER_X), 0);
                    return;
                } else {
                    if (z2) {
                        a(ConstraintAnchor.Type.CENTER_Y).a(constraintWidget.a(ConstraintAnchor.Type.CENTER_Y), 0);
                        return;
                    }
                    return;
                }
            }
            if (type2 != ConstraintAnchor.Type.LEFT && type2 != ConstraintAnchor.Type.RIGHT) {
                if (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM) {
                    a(ConstraintAnchor.Type.TOP, constraintWidget, type2, 0);
                    a(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                    a(ConstraintAnchor.Type.CENTER).a(constraintWidget.a(type2), 0);
                    return;
                }
                return;
            }
            a(ConstraintAnchor.Type.LEFT, constraintWidget, type2, 0);
            a(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
            a(ConstraintAnchor.Type.CENTER).a(constraintWidget.a(type2), 0);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_X && (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor constraintAnchorA5 = a(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor constraintAnchorA6 = constraintWidget.a(type2);
            ConstraintAnchor constraintAnchorA7 = a(ConstraintAnchor.Type.RIGHT);
            constraintAnchorA5.a(constraintAnchorA6, 0);
            constraintAnchorA7.a(constraintAnchorA6, 0);
            a(ConstraintAnchor.Type.CENTER_X).a(constraintAnchorA6, 0);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_Y && (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor constraintAnchorA8 = constraintWidget.a(type2);
            a(ConstraintAnchor.Type.TOP).a(constraintAnchorA8, 0);
            a(ConstraintAnchor.Type.BOTTOM).a(constraintAnchorA8, 0);
            a(ConstraintAnchor.Type.CENTER_Y).a(constraintAnchorA8, 0);
            return;
        }
        ConstraintAnchor.Type type8 = ConstraintAnchor.Type.CENTER_X;
        if (type == type8 && type2 == type8) {
            a(ConstraintAnchor.Type.LEFT).a(constraintWidget.a(ConstraintAnchor.Type.LEFT), 0);
            a(ConstraintAnchor.Type.RIGHT).a(constraintWidget.a(ConstraintAnchor.Type.RIGHT), 0);
            a(ConstraintAnchor.Type.CENTER_X).a(constraintWidget.a(type2), 0);
            return;
        }
        ConstraintAnchor.Type type9 = ConstraintAnchor.Type.CENTER_Y;
        if (type == type9 && type2 == type9) {
            a(ConstraintAnchor.Type.TOP).a(constraintWidget.a(ConstraintAnchor.Type.TOP), 0);
            a(ConstraintAnchor.Type.BOTTOM).a(constraintWidget.a(ConstraintAnchor.Type.BOTTOM), 0);
            a(ConstraintAnchor.Type.CENTER_Y).a(constraintWidget.a(type2), 0);
            return;
        }
        ConstraintAnchor constraintAnchorA9 = a(type);
        ConstraintAnchor constraintAnchorA10 = constraintWidget.a(type2);
        if (constraintAnchorA9.a(constraintAnchorA10)) {
            if (type == ConstraintAnchor.Type.BASELINE) {
                ConstraintAnchor constraintAnchorA11 = a(ConstraintAnchor.Type.TOP);
                ConstraintAnchor constraintAnchorA12 = a(ConstraintAnchor.Type.BOTTOM);
                if (constraintAnchorA11 != null) {
                    constraintAnchorA11.m();
                }
                if (constraintAnchorA12 != null) {
                    constraintAnchorA12.m();
                }
                i = 0;
            } else if (type != ConstraintAnchor.Type.TOP && type != ConstraintAnchor.Type.BOTTOM) {
                if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor constraintAnchorA13 = a(ConstraintAnchor.Type.CENTER);
                    if (constraintAnchorA13.g() != constraintAnchorA10) {
                        constraintAnchorA13.m();
                    }
                    ConstraintAnchor constraintAnchorD = a(type).d();
                    ConstraintAnchor constraintAnchorA14 = a(ConstraintAnchor.Type.CENTER_X);
                    if (constraintAnchorA14.l()) {
                        constraintAnchorD.m();
                        constraintAnchorA14.m();
                    }
                }
            } else {
                ConstraintAnchor constraintAnchorA15 = a(ConstraintAnchor.Type.BASELINE);
                if (constraintAnchorA15 != null) {
                    constraintAnchorA15.m();
                }
                ConstraintAnchor constraintAnchorA16 = a(ConstraintAnchor.Type.CENTER);
                if (constraintAnchorA16.g() != constraintAnchorA10) {
                    constraintAnchorA16.m();
                }
                ConstraintAnchor constraintAnchorD2 = a(type).d();
                ConstraintAnchor constraintAnchorA17 = a(ConstraintAnchor.Type.CENTER_Y);
                if (constraintAnchorA17.l()) {
                    constraintAnchorD2.m();
                    constraintAnchorA17.m();
                }
            }
            constraintAnchorA9.a(constraintAnchorA10, i);
        }
    }

    public ConstraintAnchor a(ConstraintAnchor.Type type) {
        switch (a.f1229a[type.ordinal()]) {
            case 1:
                return this.H;
            case 2:
                return this.I;
            case 3:
                return this.J;
            case 4:
                return this.K;
            case 5:
                return this.L;
            case 6:
                return this.O;
            case 7:
                return this.M;
            case 8:
                return this.N;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public void a(DimensionBehaviour dimensionBehaviour) {
        this.S[0] = dimensionBehaviour;
    }

    /* JADX WARN: Removed duplicated region for block: B:198:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x05ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(supwisdom.w5 r51, boolean r52) {
        /*
            Method dump skipped, instruction units count: 1524
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.a(supwisdom.w5, boolean):void");
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.y == -1) {
            if (z3 && !z4) {
                this.y = 0;
            } else if (!z3 && z4) {
                this.y = 1;
                if (this.X == -1) {
                    this.z = 1.0f / this.z;
                }
            }
        }
        if (this.y == 0 && (!this.I.l() || !this.K.l())) {
            this.y = 1;
        } else if (this.y == 1 && (!this.H.l() || !this.J.l())) {
            this.y = 0;
        }
        if (this.y == -1 && (!this.I.l() || !this.K.l() || !this.H.l() || !this.J.l())) {
            if (this.I.l() && this.K.l()) {
                this.y = 0;
            } else if (this.H.l() && this.J.l()) {
                this.z = 1.0f / this.z;
                this.y = 1;
            }
        }
        if (this.y == -1) {
            if (this.q > 0 && this.t == 0) {
                this.y = 0;
            } else {
                if (this.q != 0 || this.t <= 0) {
                    return;
                }
                this.z = 1.0f / this.z;
                this.y = 1;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0535 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:360:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(supwisdom.w5 r36, boolean r37, boolean r38, boolean r39, boolean r40, androidx.constraintlayout.solver.SolverVariable r41, androidx.constraintlayout.solver.SolverVariable r42, androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour r43, boolean r44, androidx.constraintlayout.solver.widgets.ConstraintAnchor r45, androidx.constraintlayout.solver.widgets.ConstraintAnchor r46, int r47, int r48, int r49, int r50, float r51, boolean r52, boolean r53, boolean r54, boolean r55, boolean r56, int r57, int r58, int r59, int r60, float r61, boolean r62) {
        /*
            Method dump skipped, instruction units count: 1393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.a(supwisdom.w5, boolean, boolean, boolean, boolean, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.solver.widgets.ConstraintAnchor, androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    public void a(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        this.l = constraintWidget.l;
        this.m = constraintWidget.m;
        this.n = constraintWidget.n;
        this.o = constraintWidget.o;
        int[] iArr = this.p;
        int[] iArr2 = constraintWidget.p;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.q = constraintWidget.q;
        this.r = constraintWidget.r;
        this.t = constraintWidget.t;
        this.u = constraintWidget.u;
        this.v = constraintWidget.v;
        this.w = constraintWidget.w;
        this.x = constraintWidget.x;
        this.y = constraintWidget.y;
        this.z = constraintWidget.z;
        int[] iArr3 = constraintWidget.A;
        this.A = Arrays.copyOf(iArr3, iArr3.length);
        this.B = constraintWidget.B;
        this.C = constraintWidget.C;
        this.D = constraintWidget.D;
        this.H.m();
        this.I.m();
        this.J.m();
        this.K.m();
        this.L.m();
        this.M.m();
        this.N.m();
        this.O.m();
        this.S = (DimensionBehaviour[]) Arrays.copyOf(this.S, 2);
        this.T = this.T == null ? null : map.get(constraintWidget.T);
        this.U = constraintWidget.U;
        this.V = constraintWidget.V;
        this.W = constraintWidget.W;
        this.X = constraintWidget.X;
        this.Y = constraintWidget.Y;
        this.Z = constraintWidget.Z;
        this.a0 = constraintWidget.a0;
        this.b0 = constraintWidget.b0;
        this.c0 = constraintWidget.c0;
        this.d0 = constraintWidget.d0;
        this.e0 = constraintWidget.e0;
        this.f0 = constraintWidget.f0;
        this.g0 = constraintWidget.g0;
        this.h0 = constraintWidget.h0;
        this.i0 = constraintWidget.i0;
        this.j0 = constraintWidget.j0;
        this.k0 = constraintWidget.k0;
        this.l0 = constraintWidget.l0;
        this.m0 = constraintWidget.m0;
        this.n0 = constraintWidget.n0;
        this.o0 = constraintWidget.o0;
        this.p0 = constraintWidget.p0;
        this.q0 = constraintWidget.q0;
        this.r0 = constraintWidget.r0;
        this.s0 = constraintWidget.s0;
        this.t0 = constraintWidget.t0;
        this.u0 = constraintWidget.u0;
        this.v0 = constraintWidget.v0;
        this.w0 = constraintWidget.w0;
        this.x0 = constraintWidget.x0;
        this.y0 = constraintWidget.y0;
        this.z0 = constraintWidget.z0;
        this.A0 = constraintWidget.A0;
        this.B0 = constraintWidget.B0;
        float[] fArr = this.C0;
        float[] fArr2 = constraintWidget.C0;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.D0;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.D0;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.E0;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.E0;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget2 = constraintWidget.F0;
        this.F0 = constraintWidget2 == null ? null : map.get(constraintWidget2);
        ConstraintWidget constraintWidget3 = constraintWidget.G0;
        this.G0 = constraintWidget3 != null ? map.get(constraintWidget3) : null;
    }

    public void a(boolean z, boolean z2) {
        int i;
        int i2;
        boolean zE = z & this.d.e();
        boolean zE2 = z2 & this.f1228e.e();
        y6 y6Var = this.d;
        int i3 = y6Var.h.g;
        a7 a7Var = this.f1228e;
        int i4 = a7Var.h.g;
        int i5 = y6Var.i.g;
        int i6 = a7Var.i.g;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i5 = 0;
            i3 = 0;
            i6 = 0;
            i4 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (zE) {
            this.Y = i3;
        }
        if (zE2) {
            this.Z = i4;
        }
        if (this.l0 == 8) {
            this.U = 0;
            this.V = 0;
            return;
        }
        if (zE) {
            if (this.S[0] == DimensionBehaviour.FIXED && i8 < (i2 = this.U)) {
                i8 = i2;
            }
            this.U = i8;
            int i10 = this.f0;
            if (i8 < i10) {
                this.U = i10;
            }
        }
        if (zE2) {
            if (this.S[1] == DimensionBehaviour.FIXED && i9 < (i = this.V)) {
                i9 = i;
            }
            this.V = i9;
            int i11 = this.g0;
            if (i9 < i11) {
                this.V = i11;
            }
        }
    }

    public void a(f6 f6Var, w5 w5Var, HashSet<ConstraintWidget> hashSet, int i, boolean z) {
        if (z) {
            if (!hashSet.contains(this)) {
                return;
            }
            k6.a(f6Var, w5Var, this);
            hashSet.remove(this);
            a(w5Var, f6Var.x(64));
        }
        if (i == 0) {
            HashSet<ConstraintAnchor> hashSetA = this.H.a();
            if (hashSetA != null) {
                Iterator<ConstraintAnchor> it = hashSetA.iterator();
                while (it.hasNext()) {
                    it.next().d.a(f6Var, w5Var, hashSet, i, true);
                }
            }
            HashSet<ConstraintAnchor> hashSetA2 = this.J.a();
            if (hashSetA2 != null) {
                Iterator<ConstraintAnchor> it2 = hashSetA2.iterator();
                while (it2.hasNext()) {
                    it2.next().d.a(f6Var, w5Var, hashSet, i, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> hashSetA3 = this.I.a();
        if (hashSetA3 != null) {
            Iterator<ConstraintAnchor> it3 = hashSetA3.iterator();
            while (it3.hasNext()) {
                it3.next().d.a(f6Var, w5Var, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> hashSetA4 = this.K.a();
        if (hashSetA4 != null) {
            Iterator<ConstraintAnchor> it4 = hashSetA4.iterator();
            while (it4.hasNext()) {
                it4.next().d.a(f6Var, w5Var, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> hashSetA5 = this.L.a();
        if (hashSetA5 != null) {
            Iterator<ConstraintAnchor> it5 = hashSetA5.iterator();
            while (it5.hasNext()) {
                it5.next().d.a(f6Var, w5Var, hashSet, i, true);
            }
        }
    }
}
