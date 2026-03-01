package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import supwisdom.o6;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class r6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f6 f9015a;
    public f6 d;
    public o6.b f;
    public o6.a g;
    public ArrayList<z6> h;
    public boolean b = true;
    public boolean c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<c7> f9016e = new ArrayList<>();

    public r6(f6 f6Var) {
        new ArrayList();
        this.f = null;
        this.g = new o6.a();
        this.h = new ArrayList<>();
        this.f9015a = f6Var;
        this.d = f6Var;
    }

    public void a(o6.b bVar) {
        this.f = bVar;
    }

    public boolean b(boolean z) {
        if (this.b) {
            for (ConstraintWidget constraintWidget : this.f9015a.K0) {
                constraintWidget.e();
                constraintWidget.f1227a = false;
                y6 y6Var = constraintWidget.d;
                y6Var.f7149e.j = false;
                y6Var.g = false;
                y6Var.g();
                a7 a7Var = constraintWidget.f1228e;
                a7Var.f7149e.j = false;
                a7Var.g = false;
                a7Var.g();
            }
            this.f9015a.e();
            f6 f6Var = this.f9015a;
            f6Var.f1227a = false;
            y6 y6Var2 = f6Var.d;
            y6Var2.f7149e.j = false;
            y6Var2.g = false;
            y6Var2.g();
            a7 a7Var2 = this.f9015a.f1228e;
            a7Var2.f7149e.j = false;
            a7Var2.g = false;
            a7Var2.g();
            a();
        }
        if (a(this.d)) {
            return false;
        }
        this.f9015a.v(0);
        this.f9015a.w(0);
        this.f9015a.d.h.a(0);
        this.f9015a.f1228e.h.a(0);
        return true;
    }

    public void c() {
        this.c = true;
    }

    public void d() {
        t6 t6Var;
        for (ConstraintWidget constraintWidget : this.f9015a.K0) {
            if (!constraintWidget.f1227a) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.S;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i = constraintWidget.n;
                int i2 = constraintWidget.o;
                boolean z2 = dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                t6 t6Var2 = constraintWidget.d.f7149e;
                boolean z3 = t6Var2.j;
                t6 t6Var3 = constraintWidget.f1228e.f7149e;
                boolean z4 = t6Var3.j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
                    a(constraintWidget, dimensionBehaviour3, t6Var2.g, dimensionBehaviour3, t6Var3.g);
                    constraintWidget.f1227a = true;
                } else if (z3 && z) {
                    a(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, constraintWidget.d.f7149e.g, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, constraintWidget.f1228e.f7149e.g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f1228e.f7149e.m = constraintWidget.l();
                    } else {
                        constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                        constraintWidget.f1227a = true;
                    }
                } else if (z4 && z2) {
                    a(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, constraintWidget.d.f7149e.g, ConstraintWidget.DimensionBehaviour.FIXED, constraintWidget.f1228e.f7149e.g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.d.f7149e.m = constraintWidget.D();
                    } else {
                        constraintWidget.d.f7149e.a(constraintWidget.D());
                        constraintWidget.f1227a = true;
                    }
                }
                if (constraintWidget.f1227a && (t6Var = constraintWidget.f1228e.l) != null) {
                    t6Var.a(constraintWidget.f());
                }
            }
        }
    }

    public final int a(f6 f6Var, int i) {
        int size = this.h.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jMax = Math.max(jMax, this.h.get(i2).a(f6Var, i));
        }
        return (int) jMax;
    }

    public boolean a(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.b || this.c) {
            for (ConstraintWidget constraintWidget : this.f9015a.K0) {
                constraintWidget.e();
                constraintWidget.f1227a = false;
                constraintWidget.d.g();
                constraintWidget.f1228e.g();
            }
            this.f9015a.e();
            f6 f6Var = this.f9015a;
            f6Var.f1227a = false;
            f6Var.d.g();
            this.f9015a.f1228e.g();
            this.c = false;
        }
        if (a(this.d)) {
            return false;
        }
        this.f9015a.v(0);
        this.f9015a.w(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourB = this.f9015a.b(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourB2 = this.f9015a.b(1);
        if (this.b) {
            a();
        }
        int iE = this.f9015a.E();
        int iF = this.f9015a.F();
        this.f9015a.d.h.a(iE);
        this.f9015a.f1228e.h.a(iF);
        d();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourB == dimensionBehaviour || dimensionBehaviourB2 == dimensionBehaviour) {
            if (z4) {
                Iterator<c7> it = this.f9016e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().f()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && dimensionBehaviourB == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f9015a.a(ConstraintWidget.DimensionBehaviour.FIXED);
                f6 f6Var2 = this.f9015a;
                f6Var2.u(a(f6Var2, 0));
                f6 f6Var3 = this.f9015a;
                f6Var3.d.f7149e.a(f6Var3.D());
            }
            if (z4 && dimensionBehaviourB2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f9015a.b(ConstraintWidget.DimensionBehaviour.FIXED);
                f6 f6Var4 = this.f9015a;
                f6Var4.m(a(f6Var4, 1));
                f6 f6Var5 = this.f9015a;
                f6Var5.f1228e.f7149e.a(f6Var5.l());
            }
        }
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = this.f9015a.S;
        if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iD = this.f9015a.D() + iE;
            this.f9015a.d.i.a(iD);
            this.f9015a.d.f7149e.a(iD - iE);
            d();
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = this.f9015a.S;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iL = this.f9015a.l() + iF;
                this.f9015a.f1228e.i.a(iL);
                this.f9015a.f1228e.f7149e.a(iL - iF);
            }
            d();
            z2 = true;
        } else {
            z2 = false;
        }
        for (c7 c7Var : this.f9016e) {
            if (c7Var.b != this.f9015a || c7Var.g) {
                c7Var.b();
            }
        }
        for (c7 c7Var2 : this.f9016e) {
            if (z2 || c7Var2.b != this.f9015a) {
                if (!c7Var2.h.j || ((!c7Var2.i.j && !(c7Var2 instanceof w6)) || (!c7Var2.f7149e.j && !(c7Var2 instanceof p6) && !(c7Var2 instanceof w6)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f9015a.a(dimensionBehaviourB);
        this.f9015a.b(dimensionBehaviourB2);
        return z3;
    }

    public void b() {
        this.b = true;
    }

    public boolean a(boolean z, int i) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourB = this.f9015a.b(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourB2 = this.f9015a.b(1);
        int iE = this.f9015a.E();
        int iF = this.f9015a.F();
        if (z4 && (dimensionBehaviourB == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourB2 == dimensionBehaviour)) {
            Iterator<c7> it = this.f9016e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                c7 next = it.next();
                if (next.f == i && !next.f()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && dimensionBehaviourB == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f9015a.a(ConstraintWidget.DimensionBehaviour.FIXED);
                    f6 f6Var = this.f9015a;
                    f6Var.u(a(f6Var, 0));
                    f6 f6Var2 = this.f9015a;
                    f6Var2.d.f7149e.a(f6Var2.D());
                }
            } else if (z4 && dimensionBehaviourB2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f9015a.b(ConstraintWidget.DimensionBehaviour.FIXED);
                f6 f6Var3 = this.f9015a;
                f6Var3.m(a(f6Var3, 1));
                f6 f6Var4 = this.f9015a;
                f6Var4.f1228e.f7149e.a(f6Var4.l());
            }
        }
        if (i == 0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = this.f9015a.S;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iD = this.f9015a.D() + iE;
                this.f9015a.d.i.a(iD);
                this.f9015a.d.f7149e.a(iD - iE);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = this.f9015a.S;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iL = this.f9015a.l() + iF;
                this.f9015a.f1228e.i.a(iL);
                this.f9015a.f1228e.f7149e.a(iL - iF);
                z2 = true;
            }
            z2 = false;
        }
        d();
        for (c7 c7Var : this.f9016e) {
            if (c7Var.f == i && (c7Var.b != this.f9015a || c7Var.g)) {
                c7Var.b();
            }
        }
        for (c7 c7Var2 : this.f9016e) {
            if (c7Var2.f == i && (z2 || c7Var2.b != this.f9015a)) {
                if (!c7Var2.h.j || !c7Var2.i.j || (!(c7Var2 instanceof p6) && !c7Var2.f7149e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f9015a.a(dimensionBehaviourB);
        this.f9015a.b(dimensionBehaviourB2);
        return z3;
    }

    public final void a(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        o6.a aVar = this.g;
        aVar.f8631a = dimensionBehaviour;
        aVar.b = dimensionBehaviour2;
        aVar.c = i;
        aVar.d = i2;
        this.f.a(constraintWidget, aVar);
        constraintWidget.u(this.g.f8632e);
        constraintWidget.m(this.g.f);
        constraintWidget.a(this.g.h);
        constraintWidget.i(this.g.g);
    }

    public final boolean a(f6 f6Var) {
        int iD;
        int iL;
        int i;
        for (ConstraintWidget constraintWidget : f6Var.K0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.S;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
            if (constraintWidget.C() == 8) {
                constraintWidget.f1227a = true;
            } else {
                if (constraintWidget.s < 1.0f && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.n = 2;
                }
                if (constraintWidget.v < 1.0f && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.o = 2;
                }
                if (constraintWidget.j() > 0.0f) {
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.n = 3;
                    } else if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.o = 3;
                    } else {
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (dimensionBehaviour == dimensionBehaviour3 && dimensionBehaviour2 == dimensionBehaviour3) {
                            if (constraintWidget.n == 0) {
                                constraintWidget.n = 3;
                            }
                            if (constraintWidget.o == 0) {
                                constraintWidget.o = 3;
                            }
                        }
                    }
                }
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.n == 1 && (constraintWidget.H.f == null || constraintWidget.J.f == null)) {
                    dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = dimensionBehaviour;
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.o == 1 && (constraintWidget.I.f == null || constraintWidget.K.f == null)) {
                    dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviour2;
                y6 y6Var = constraintWidget.d;
                y6Var.d = dimensionBehaviour4;
                y6Var.f7148a = constraintWidget.n;
                a7 a7Var = constraintWidget.f1228e;
                a7Var.d = dimensionBehaviour5;
                a7Var.f7148a = constraintWidget.o;
                if ((dimensionBehaviour4 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour4 != ConstraintWidget.DimensionBehaviour.FIXED && dimensionBehaviour4 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour5 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour5 != ConstraintWidget.DimensionBehaviour.FIXED && dimensionBehaviour5 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        int i2 = constraintWidget.n;
                        if (i2 == 3) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            if (dimensionBehaviour5 == dimensionBehaviour6) {
                                a(constraintWidget, dimensionBehaviour6, 0, dimensionBehaviour6, 0);
                            }
                            int iL2 = constraintWidget.l();
                            int i3 = (int) ((iL2 * constraintWidget.W) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.FIXED;
                            a(constraintWidget, dimensionBehaviour7, i3, dimensionBehaviour7, iL2);
                            constraintWidget.d.f7149e.a(constraintWidget.D());
                            constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                            constraintWidget.f1227a = true;
                        } else if (i2 == 1) {
                            a(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour5, 0);
                            constraintWidget.d.f7149e.m = constraintWidget.D();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = f6Var.S;
                            if (dimensionBehaviourArr2[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                                a(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((constraintWidget.s * f6Var.D()) + 0.5f), dimensionBehaviour5, constraintWidget.l());
                                constraintWidget.d.f7149e.a(constraintWidget.D());
                                constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                                constraintWidget.f1227a = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.P;
                            if (constraintAnchorArr[0].f == null || constraintAnchorArr[1].f == null) {
                                a(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour5, 0);
                                constraintWidget.d.f7149e.a(constraintWidget.D());
                                constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                                constraintWidget.f1227a = true;
                            }
                        }
                    }
                    if (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        int i4 = constraintWidget.o;
                        if (i4 == 3) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            if (dimensionBehaviour4 == dimensionBehaviour8) {
                                a(constraintWidget, dimensionBehaviour8, 0, dimensionBehaviour8, 0);
                            }
                            int iD2 = constraintWidget.D();
                            float f = constraintWidget.W;
                            if (constraintWidget.k() == -1) {
                                f = 1.0f / f;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = ConstraintWidget.DimensionBehaviour.FIXED;
                            a(constraintWidget, dimensionBehaviour9, iD2, dimensionBehaviour9, (int) ((iD2 * f) + 0.5f));
                            constraintWidget.d.f7149e.a(constraintWidget.D());
                            constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                            constraintWidget.f1227a = true;
                        } else if (i4 == 1) {
                            a(constraintWidget, dimensionBehaviour4, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            constraintWidget.f1228e.f7149e.m = constraintWidget.l();
                        } else if (i4 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = f6Var.S;
                            if (dimensionBehaviourArr3[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr3[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                                a(constraintWidget, dimensionBehaviour4, constraintWidget.D(), ConstraintWidget.DimensionBehaviour.FIXED, (int) ((constraintWidget.v * f6Var.l()) + 0.5f));
                                constraintWidget.d.f7149e.a(constraintWidget.D());
                                constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                                constraintWidget.f1227a = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.P;
                            if (constraintAnchorArr2[2].f == null || constraintAnchorArr2[3].f == null) {
                                a(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour5, 0);
                                constraintWidget.d.f7149e.a(constraintWidget.D());
                                constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                                constraintWidget.f1227a = true;
                            }
                        }
                    }
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour4 == dimensionBehaviour10 && dimensionBehaviour5 == dimensionBehaviour10) {
                        int i5 = constraintWidget.n;
                        if (i5 == 1 || (i = constraintWidget.o) == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            a(constraintWidget, dimensionBehaviour11, 0, dimensionBehaviour11, 0);
                            constraintWidget.d.f7149e.m = constraintWidget.D();
                            constraintWidget.f1228e.f7149e.m = constraintWidget.l();
                        } else if (i == 2 && i5 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = f6Var.S;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour12 == dimensionBehaviour13 || dimensionBehaviourArr4[0] == dimensionBehaviour13) {
                                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr5 = f6Var.S;
                                ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = dimensionBehaviourArr5[1];
                                ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                                if (dimensionBehaviour14 == dimensionBehaviour15 || dimensionBehaviourArr5[1] == dimensionBehaviour15) {
                                    float f2 = constraintWidget.s;
                                    int iL3 = (int) ((constraintWidget.v * f6Var.l()) + 0.5f);
                                    ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = ConstraintWidget.DimensionBehaviour.FIXED;
                                    a(constraintWidget, dimensionBehaviour16, (int) ((f2 * f6Var.D()) + 0.5f), dimensionBehaviour16, iL3);
                                    constraintWidget.d.f7149e.a(constraintWidget.D());
                                    constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                                    constraintWidget.f1227a = true;
                                }
                            }
                        }
                    }
                } else {
                    int iD3 = constraintWidget.D();
                    if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        iD = (f6Var.D() - constraintWidget.H.g) - constraintWidget.J.g;
                        dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iD = iD3;
                    }
                    int iL4 = constraintWidget.l();
                    if (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        iL = (f6Var.l() - constraintWidget.I.g) - constraintWidget.K.g;
                        dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iL = iL4;
                    }
                    a(constraintWidget, dimensionBehaviour4, iD, dimensionBehaviour5, iL);
                    constraintWidget.d.f7149e.a(constraintWidget.D());
                    constraintWidget.f1228e.f7149e.a(constraintWidget.l());
                    constraintWidget.f1227a = true;
                }
            }
        }
        return false;
    }

    public void a() {
        a(this.f9016e);
        this.h.clear();
        z6.d = 0;
        a(this.f9015a.d, 0, this.h);
        a(this.f9015a.f1228e, 1, this.h);
        this.b = false;
    }

    public void a(ArrayList<c7> arrayList) {
        arrayList.clear();
        this.d.d.c();
        this.d.f1228e.c();
        arrayList.add(this.d.d);
        arrayList.add(this.d.f1228e);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.d.K0) {
            if (constraintWidget instanceof h6) {
                arrayList.add(new w6(constraintWidget));
            } else {
                if (constraintWidget.I()) {
                    if (constraintWidget.b == null) {
                        constraintWidget.b = new p6(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.b);
                } else {
                    arrayList.add(constraintWidget.d);
                }
                if (constraintWidget.K()) {
                    if (constraintWidget.c == null) {
                        constraintWidget.c = new p6(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.c);
                } else {
                    arrayList.add(constraintWidget.f1228e);
                }
                if (constraintWidget instanceof j6) {
                    arrayList.add(new x6(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<c7> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().c();
        }
        for (c7 c7Var : arrayList) {
            if (c7Var.b != this.d) {
                c7Var.a();
            }
        }
    }

    public final void a(s6 s6Var, int i, int i2, s6 s6Var2, ArrayList<z6> arrayList, z6 z6Var) {
        c7 c7Var = s6Var.d;
        if (c7Var.c == null) {
            f6 f6Var = this.f9015a;
            if (c7Var == f6Var.d || c7Var == f6Var.f1228e) {
                return;
            }
            if (z6Var == null) {
                z6Var = new z6(c7Var, i2);
                arrayList.add(z6Var);
            }
            c7Var.c = z6Var;
            z6Var.a(c7Var);
            for (q6 q6Var : c7Var.h.k) {
                if (q6Var instanceof s6) {
                    a((s6) q6Var, i, 0, s6Var2, arrayList, z6Var);
                }
            }
            for (q6 q6Var2 : c7Var.i.k) {
                if (q6Var2 instanceof s6) {
                    a((s6) q6Var2, i, 1, s6Var2, arrayList, z6Var);
                }
            }
            if (i == 1 && (c7Var instanceof a7)) {
                for (q6 q6Var3 : ((a7) c7Var).k.k) {
                    if (q6Var3 instanceof s6) {
                        a((s6) q6Var3, i, 2, s6Var2, arrayList, z6Var);
                    }
                }
            }
            for (s6 s6Var3 : c7Var.h.l) {
                if (s6Var3 == s6Var2) {
                    z6Var.f9970a = true;
                }
                a(s6Var3, i, 0, s6Var2, arrayList, z6Var);
            }
            for (s6 s6Var4 : c7Var.i.l) {
                if (s6Var4 == s6Var2) {
                    z6Var.f9970a = true;
                }
                a(s6Var4, i, 1, s6Var2, arrayList, z6Var);
            }
            if (i == 1 && (c7Var instanceof a7)) {
                Iterator<s6> it = ((a7) c7Var).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i, 2, s6Var2, arrayList, z6Var);
                }
            }
        }
    }

    public final void a(c7 c7Var, int i, ArrayList<z6> arrayList) {
        for (q6 q6Var : c7Var.h.k) {
            if (q6Var instanceof s6) {
                a((s6) q6Var, i, 0, c7Var.i, arrayList, null);
            } else if (q6Var instanceof c7) {
                a(((c7) q6Var).h, i, 0, c7Var.i, arrayList, null);
            }
        }
        for (q6 q6Var2 : c7Var.i.k) {
            if (q6Var2 instanceof s6) {
                a((s6) q6Var2, i, 1, c7Var.h, arrayList, null);
            } else if (q6Var2 instanceof c7) {
                a(((c7) q6Var2).i, i, 1, c7Var.h, arrayList, null);
            }
        }
        if (i == 1) {
            for (q6 q6Var3 : ((a7) c7Var).k.k) {
                if (q6Var3 instanceof s6) {
                    a((s6) q6Var3, i, 2, null, arrayList, null);
                }
            }
        }
    }
}
