package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import supwisdom.b7;
import supwisdom.h6;
import supwisdom.v5;
import supwisdom.v6;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {
    public int b;
    public boolean c;
    public final ConstraintWidget d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Type f1225e;
    public ConstraintAnchor f;
    public SolverVariable i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashSet<ConstraintAnchor> f1224a = null;
    public int g = 0;
    public int h = -1;

    public enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1226a;

        static {
            int[] iArr = new int[Type.values().length];
            f1226a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1226a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1226a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1226a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1226a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1226a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1226a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1226a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1226a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.d = constraintWidget;
        this.f1225e = type;
    }

    public void a(int i, ArrayList<b7> arrayList, b7 b7Var) {
        HashSet<ConstraintAnchor> hashSet = this.f1224a;
        if (hashSet != null) {
            Iterator<ConstraintAnchor> it = hashSet.iterator();
            while (it.hasNext()) {
                v6.a(it.next().d, i, arrayList, b7Var);
            }
        }
    }

    public int b() {
        if (this.c) {
            return this.b;
        }
        return 0;
    }

    public int c() {
        ConstraintAnchor constraintAnchor;
        if (this.d.C() == 8) {
            return 0;
        }
        return (this.h <= -1 || (constraintAnchor = this.f) == null || constraintAnchor.d.C() != 8) ? this.g : this.h;
    }

    public final ConstraintAnchor d() {
        switch (a.f1226a[this.f1225e.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.d.J;
            case 3:
                return this.d.H;
            case 4:
                return this.d.K;
            case 5:
                return this.d.I;
            default:
                throw new AssertionError(this.f1225e.name());
        }
    }

    public ConstraintWidget e() {
        return this.d;
    }

    public SolverVariable f() {
        return this.i;
    }

    public ConstraintAnchor g() {
        return this.f;
    }

    public Type h() {
        return this.f1225e;
    }

    public boolean i() {
        HashSet<ConstraintAnchor> hashSet = this.f1224a;
        if (hashSet == null) {
            return false;
        }
        Iterator<ConstraintAnchor> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().d().l()) {
                return true;
            }
        }
        return false;
    }

    public boolean j() {
        HashSet<ConstraintAnchor> hashSet = this.f1224a;
        return hashSet != null && hashSet.size() > 0;
    }

    public boolean k() {
        return this.c;
    }

    public boolean l() {
        return this.f != null;
    }

    public void m() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f1224a) != null) {
            hashSet.remove(this);
            if (this.f.f1224a.size() == 0) {
                this.f.f1224a = null;
            }
        }
        this.f1224a = null;
        this.f = null;
        this.g = 0;
        this.h = -1;
        this.c = false;
        this.b = 0;
    }

    public void n() {
        this.c = false;
        this.b = 0;
    }

    public String toString() {
        return this.d.i() + Constants.COLON_SEPARATOR + this.f1225e.toString();
    }

    public void b(int i) {
        if (l()) {
            this.h = i;
        }
    }

    public HashSet<ConstraintAnchor> a() {
        return this.f1224a;
    }

    public void a(int i) {
        this.b = i;
        this.c = true;
    }

    public void a(v5 v5Var) {
        SolverVariable solverVariable = this.i;
        if (solverVariable == null) {
            this.i = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.a();
        }
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i, int i2, boolean z) {
        if (constraintAnchor == null) {
            m();
            return true;
        }
        if (!z && !a(constraintAnchor)) {
            return false;
        }
        this.f = constraintAnchor;
        if (constraintAnchor.f1224a == null) {
            constraintAnchor.f1224a = new HashSet<>();
        }
        HashSet<ConstraintAnchor> hashSet = this.f.f1224a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        if (i > 0) {
            this.g = i;
        } else {
            this.g = 0;
        }
        this.h = i2;
        return true;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i) {
        return a(constraintAnchor, i, -1, false);
    }

    public boolean a(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeH = constraintAnchor.h();
        Type type = this.f1225e;
        if (typeH == type) {
            return type != Type.BASELINE || (constraintAnchor.e().G() && e().G());
        }
        switch (a.f1226a[type.ordinal()]) {
            case 1:
                return (typeH == Type.BASELINE || typeH == Type.CENTER_X || typeH == Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = typeH == Type.LEFT || typeH == Type.RIGHT;
                if (constraintAnchor.e() instanceof h6) {
                    return z || typeH == Type.CENTER_X;
                }
                return z;
            case 4:
            case 5:
                boolean z2 = typeH == Type.TOP || typeH == Type.BOTTOM;
                if (constraintAnchor.e() instanceof h6) {
                    return z2 || typeH == Type.CENTER_Y;
                }
                return z2;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f1225e.name());
        }
    }
}
