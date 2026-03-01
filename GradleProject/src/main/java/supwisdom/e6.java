package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class e6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConstraintWidget f7426a;
    public ConstraintWidget b;
    public ConstraintWidget c;
    public ConstraintWidget d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConstraintWidget f7427e;
    public ConstraintWidget f;
    public ConstraintWidget g;
    public ArrayList<ConstraintWidget> h;
    public int i;
    public int j;
    public float k = 0.0f;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;

    public e6(ConstraintWidget constraintWidget, int i, boolean z) {
        this.p = false;
        this.f7426a = constraintWidget;
        this.o = i;
        this.p = z;
    }

    public static boolean a(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget.C() != 8 && constraintWidget.S[i] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.p;
            if (iArr[i] == 0 || iArr[i] == 3) {
                return true;
            }
        }
        return false;
    }

    public final void b() {
        int i = this.o * 2;
        ConstraintWidget constraintWidget = this.f7426a;
        boolean z = false;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z2 = false;
        while (!z2) {
            this.i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.E0;
            int i2 = this.o;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i2] = null;
            constraintWidget.D0[i2] = null;
            if (constraintWidget.C() != 8) {
                this.l++;
                if (constraintWidget.b(this.o) != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.m += constraintWidget.c(this.o);
                }
                int iC = this.m + constraintWidget.P[i].c();
                this.m = iC;
                int i3 = i + 1;
                this.m = iC + constraintWidget.P[i3].c();
                int iC2 = this.n + constraintWidget.P[i].c();
                this.n = iC2;
                this.n = iC2 + constraintWidget.P[i3].c();
                if (this.b == null) {
                    this.b = constraintWidget;
                }
                this.d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.S;
                int i4 = this.o;
                if (dimensionBehaviourArr[i4] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    int[] iArr = constraintWidget.p;
                    if (iArr[i4] == 0 || iArr[i4] == 3 || iArr[i4] == 2) {
                        this.j++;
                        float[] fArr = constraintWidget.C0;
                        int i5 = this.o;
                        float f = fArr[i5];
                        if (f > 0.0f) {
                            this.k += fArr[i5];
                        }
                        if (a(constraintWidget, this.o)) {
                            if (f < 0.0f) {
                                this.q = true;
                            } else {
                                this.r = true;
                            }
                            if (this.h == null) {
                                this.h = new ArrayList<>();
                            }
                            this.h.add(constraintWidget);
                        }
                        if (this.f == null) {
                            this.f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.D0[this.o] = constraintWidget;
                        }
                        this.g = constraintWidget;
                    }
                    if (this.o == 0) {
                        if (constraintWidget.n == 0 && constraintWidget.q == 0) {
                            int i6 = constraintWidget.r;
                        }
                    } else if (constraintWidget.o == 0 && constraintWidget.t == 0) {
                        int i7 = constraintWidget.u;
                    }
                    int i8 = (constraintWidget.W > 0.0f ? 1 : (constraintWidget.W == 0.0f ? 0 : -1));
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.E0[this.o] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.P[i + 1].f;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.d;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.P;
                if (constraintAnchorArr[i].f != null && constraintAnchorArr[i].f.d == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z2 = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.b;
        if (constraintWidget6 != null) {
            this.m -= constraintWidget6.P[i].c();
        }
        ConstraintWidget constraintWidget7 = this.d;
        if (constraintWidget7 != null) {
            this.m -= constraintWidget7.P[i + 1].c();
        }
        this.c = constraintWidget;
        if (this.o == 0 && this.p) {
            this.f7427e = constraintWidget;
        } else {
            this.f7427e = this.f7426a;
        }
        if (this.r && this.q) {
            z = true;
        }
        this.s = z;
    }

    public void a() {
        if (!this.t) {
            b();
        }
        this.t = true;
    }
}
