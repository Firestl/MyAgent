package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Iterator;
import supwisdom.o6;

/* JADX INFO: compiled from: Direct.java */
/* JADX INFO: loaded from: classes.dex */
public class u6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static o6.a f9373a = new o6.a();

    public static void a(f6 f6Var, o6.b bVar) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviourO = f6Var.o();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourA = f6Var.A();
        f6Var.S();
        ArrayList<ConstraintWidget> arrayListT = f6Var.T();
        int size = arrayListT.size();
        for (int i = 0; i < size; i++) {
            arrayListT.get(i).S();
        }
        boolean zD0 = f6Var.d0();
        if (dimensionBehaviourO == ConstraintWidget.DimensionBehaviour.FIXED) {
            f6Var.a(0, f6Var.D());
        } else {
            f6Var.k(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = arrayListT.get(i2);
            if (constraintWidget instanceof h6) {
                h6 h6Var = (h6) constraintWidget;
                if (h6Var.U() == 1) {
                    if (h6Var.V() != -1) {
                        h6Var.x(h6Var.V());
                    } else if (h6Var.W() != -1 && f6Var.N()) {
                        h6Var.x(f6Var.D() - h6Var.W());
                    } else if (f6Var.N()) {
                        h6Var.x((int) ((h6Var.X() * f6Var.D()) + 0.5f));
                    }
                    z = true;
                }
            } else if ((constraintWidget instanceof c6) && ((c6) constraintWidget).X() == 0) {
                z2 = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = arrayListT.get(i3);
                if (constraintWidget2 instanceof h6) {
                    h6 h6Var2 = (h6) constraintWidget2;
                    if (h6Var2.U() == 1) {
                        a(h6Var2, bVar, zD0);
                    }
                }
            }
        }
        a(f6Var, bVar, zD0);
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget3 = arrayListT.get(i4);
                if (constraintWidget3 instanceof c6) {
                    c6 c6Var = (c6) constraintWidget3;
                    if (c6Var.X() == 0) {
                        a(c6Var, bVar, 0, zD0);
                    }
                }
            }
        }
        if (dimensionBehaviourA == ConstraintWidget.DimensionBehaviour.FIXED) {
            f6Var.b(0, f6Var.l());
        } else {
            f6Var.l(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = arrayListT.get(i5);
            if (constraintWidget4 instanceof h6) {
                h6 h6Var3 = (h6) constraintWidget4;
                if (h6Var3.U() == 0) {
                    if (h6Var3.V() != -1) {
                        h6Var3.x(h6Var3.V());
                    } else if (h6Var3.W() != -1 && f6Var.O()) {
                        h6Var3.x(f6Var.l() - h6Var3.W());
                    } else if (f6Var.O()) {
                        h6Var3.x((int) ((h6Var3.X() * f6Var.l()) + 0.5f));
                    }
                    z3 = true;
                }
            } else if ((constraintWidget4 instanceof c6) && ((c6) constraintWidget4).X() == 1) {
                z4 = true;
            }
        }
        if (z3) {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget5 = arrayListT.get(i6);
                if (constraintWidget5 instanceof h6) {
                    h6 h6Var4 = (h6) constraintWidget5;
                    if (h6Var4.U() == 0) {
                        a(h6Var4, bVar);
                    }
                }
            }
        }
        a((ConstraintWidget) f6Var, bVar);
        if (z4) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget6 = arrayListT.get(i7);
                if (constraintWidget6 instanceof c6) {
                    c6 c6Var2 = (c6) constraintWidget6;
                    if (c6Var2.X() == 1) {
                        a(c6Var2, bVar, 1, zD0);
                    }
                }
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget7 = arrayListT.get(i8);
            if (constraintWidget7.M() && a(constraintWidget7)) {
                f6.a(constraintWidget7, bVar, f9373a, o6.a.k);
                a(constraintWidget7, bVar, zD0);
                a(constraintWidget7, bVar);
            }
        }
    }

    public static void a(c6 c6Var, o6.b bVar, int i, boolean z) {
        if (c6Var.T()) {
            if (i == 0) {
                a(c6Var, bVar, z);
            } else {
                a(c6Var, bVar);
            }
        }
    }

    public static void a(ConstraintWidget constraintWidget, o6.b bVar, boolean z) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (!(constraintWidget instanceof f6) && constraintWidget.M() && a(constraintWidget)) {
            f6.a(constraintWidget, bVar, new o6.a(), o6.a.k);
        }
        ConstraintAnchor constraintAnchorA = constraintWidget.a(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorA2 = constraintWidget.a(ConstraintAnchor.Type.RIGHT);
        int iB = constraintAnchorA.b();
        int iB2 = constraintAnchorA2.b();
        if (constraintAnchorA.a() != null && constraintAnchorA.k()) {
            Iterator<ConstraintAnchor> it = constraintAnchorA.a().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.d;
                boolean zA = a(constraintWidget2);
                if (constraintWidget2.M() && zA) {
                    f6.a(constraintWidget2, bVar, new o6.a(), o6.a.k);
                }
                if (constraintWidget2.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !zA) {
                    if (constraintWidget2.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget2.r >= 0 && constraintWidget2.q >= 0 && (constraintWidget2.C() == 8 || (constraintWidget2.n == 0 && constraintWidget2.j() == 0.0f))) {
                        if (!constraintWidget2.I() && !constraintWidget2.L()) {
                            if (((next == constraintWidget2.H && (constraintAnchor5 = constraintWidget2.J.f) != null && constraintAnchor5.k()) || (next == constraintWidget2.J && (constraintAnchor4 = constraintWidget2.H.f) != null && constraintAnchor4.k())) && !constraintWidget2.I()) {
                                a(constraintWidget, bVar, constraintWidget2, z);
                            }
                        }
                    }
                } else if (!constraintWidget2.M()) {
                    ConstraintAnchor constraintAnchor6 = constraintWidget2.H;
                    if (next == constraintAnchor6 && constraintWidget2.J.f == null) {
                        int iC = constraintAnchor6.c() + iB;
                        constraintWidget2.a(iC, constraintWidget2.D() + iC);
                        a(constraintWidget2, bVar, z);
                    } else {
                        ConstraintAnchor constraintAnchor7 = constraintWidget2.J;
                        if (next == constraintAnchor7 && constraintWidget2.H.f == null) {
                            int iC2 = iB - constraintAnchor7.c();
                            constraintWidget2.a(iC2 - constraintWidget2.D(), iC2);
                            a(constraintWidget2, bVar, z);
                        } else if (next == constraintWidget2.H && (constraintAnchor3 = constraintWidget2.J.f) != null && constraintAnchor3.k() && !constraintWidget2.I()) {
                            a(bVar, constraintWidget2, z);
                        }
                    }
                }
            }
        }
        if ((constraintWidget instanceof h6) || constraintAnchorA2.a() == null || !constraintAnchorA2.k()) {
            return;
        }
        Iterator<ConstraintAnchor> it2 = constraintAnchorA2.a().iterator();
        while (it2.hasNext()) {
            ConstraintAnchor next2 = it2.next();
            ConstraintWidget constraintWidget3 = next2.d;
            boolean zA2 = a(constraintWidget3);
            if (constraintWidget3.M() && zA2) {
                f6.a(constraintWidget3, bVar, new o6.a(), o6.a.k);
            }
            boolean z2 = (next2 == constraintWidget3.H && (constraintAnchor2 = constraintWidget3.J.f) != null && constraintAnchor2.k()) || (next2 == constraintWidget3.J && (constraintAnchor = constraintWidget3.H.f) != null && constraintAnchor.k());
            if (constraintWidget3.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !zA2) {
                if (constraintWidget3.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget3.r >= 0 && constraintWidget3.q >= 0 && (constraintWidget3.C() == 8 || (constraintWidget3.n == 0 && constraintWidget3.j() == 0.0f))) {
                    if (!constraintWidget3.I() && !constraintWidget3.L() && z2 && !constraintWidget3.I()) {
                        a(constraintWidget, bVar, constraintWidget3, z);
                    }
                }
            } else if (!constraintWidget3.M()) {
                ConstraintAnchor constraintAnchor8 = constraintWidget3.H;
                if (next2 == constraintAnchor8 && constraintWidget3.J.f == null) {
                    int iC3 = constraintAnchor8.c() + iB2;
                    constraintWidget3.a(iC3, constraintWidget3.D() + iC3);
                    a(constraintWidget3, bVar, z);
                } else {
                    ConstraintAnchor constraintAnchor9 = constraintWidget3.J;
                    if (next2 == constraintAnchor9 && constraintWidget3.H.f == null) {
                        int iC4 = iB2 - constraintAnchor9.c();
                        constraintWidget3.a(iC4 - constraintWidget3.D(), iC4);
                        a(constraintWidget3, bVar, z);
                    } else if (z2 && !constraintWidget3.I()) {
                        a(bVar, constraintWidget3, z);
                    }
                }
            }
        }
    }

    public static void a(ConstraintWidget constraintWidget, o6.b bVar) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (!(constraintWidget instanceof f6) && constraintWidget.M() && a(constraintWidget)) {
            f6.a(constraintWidget, bVar, new o6.a(), o6.a.k);
        }
        ConstraintAnchor constraintAnchorA = constraintWidget.a(ConstraintAnchor.Type.TOP);
        ConstraintAnchor constraintAnchorA2 = constraintWidget.a(ConstraintAnchor.Type.BOTTOM);
        int iB = constraintAnchorA.b();
        int iB2 = constraintAnchorA2.b();
        if (constraintAnchorA.a() != null && constraintAnchorA.k()) {
            Iterator<ConstraintAnchor> it = constraintAnchorA.a().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.d;
                boolean zA = a(constraintWidget2);
                if (constraintWidget2.M() && zA) {
                    f6.a(constraintWidget2, bVar, new o6.a(), o6.a.k);
                }
                if (constraintWidget2.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !zA) {
                    if (constraintWidget2.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget2.u >= 0 && constraintWidget2.t >= 0 && (constraintWidget2.C() == 8 || (constraintWidget2.o == 0 && constraintWidget2.j() == 0.0f))) {
                        if (!constraintWidget2.K() && !constraintWidget2.L()) {
                            if (((next == constraintWidget2.I && (constraintAnchor5 = constraintWidget2.K.f) != null && constraintAnchor5.k()) || (next == constraintWidget2.K && (constraintAnchor4 = constraintWidget2.I.f) != null && constraintAnchor4.k())) && !constraintWidget2.K()) {
                                a(constraintWidget, bVar, constraintWidget2);
                            }
                        }
                    }
                } else if (!constraintWidget2.M()) {
                    ConstraintAnchor constraintAnchor6 = constraintWidget2.I;
                    if (next == constraintAnchor6 && constraintWidget2.K.f == null) {
                        int iC = constraintAnchor6.c() + iB;
                        constraintWidget2.b(iC, constraintWidget2.l() + iC);
                        a(constraintWidget2, bVar);
                    } else {
                        ConstraintAnchor constraintAnchor7 = constraintWidget2.K;
                        if (next == constraintAnchor7 && constraintAnchor7.f == null) {
                            int iC2 = iB - constraintAnchor7.c();
                            constraintWidget2.b(iC2 - constraintWidget2.l(), iC2);
                            a(constraintWidget2, bVar);
                        } else if (next == constraintWidget2.I && (constraintAnchor3 = constraintWidget2.K.f) != null && constraintAnchor3.k()) {
                            a(bVar, constraintWidget2);
                        }
                    }
                }
            }
        }
        if (constraintWidget instanceof h6) {
            return;
        }
        if (constraintAnchorA2.a() != null && constraintAnchorA2.k()) {
            Iterator<ConstraintAnchor> it2 = constraintAnchorA2.a().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.d;
                boolean zA2 = a(constraintWidget3);
                if (constraintWidget3.M() && zA2) {
                    f6.a(constraintWidget3, bVar, new o6.a(), o6.a.k);
                }
                boolean z = (next2 == constraintWidget3.I && (constraintAnchor2 = constraintWidget3.K.f) != null && constraintAnchor2.k()) || (next2 == constraintWidget3.K && (constraintAnchor = constraintWidget3.I.f) != null && constraintAnchor.k());
                if (constraintWidget3.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !zA2) {
                    if (constraintWidget3.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget3.u >= 0 && constraintWidget3.t >= 0 && (constraintWidget3.C() == 8 || (constraintWidget3.o == 0 && constraintWidget3.j() == 0.0f))) {
                        if (!constraintWidget3.K() && !constraintWidget3.L() && z && !constraintWidget3.K()) {
                            a(constraintWidget, bVar, constraintWidget3);
                        }
                    }
                } else if (!constraintWidget3.M()) {
                    ConstraintAnchor constraintAnchor8 = constraintWidget3.I;
                    if (next2 == constraintAnchor8 && constraintWidget3.K.f == null) {
                        int iC3 = constraintAnchor8.c() + iB2;
                        constraintWidget3.b(iC3, constraintWidget3.l() + iC3);
                        a(constraintWidget3, bVar);
                    } else {
                        ConstraintAnchor constraintAnchor9 = constraintWidget3.K;
                        if (next2 == constraintAnchor9 && constraintWidget3.I.f == null) {
                            int iC4 = iB2 - constraintAnchor9.c();
                            constraintWidget3.b(iC4 - constraintWidget3.l(), iC4);
                            a(constraintWidget3, bVar);
                        } else if (z && !constraintWidget3.K()) {
                            a(bVar, constraintWidget3);
                        }
                    }
                }
            }
        }
        ConstraintAnchor constraintAnchorA3 = constraintWidget.a(ConstraintAnchor.Type.BASELINE);
        if (constraintAnchorA3.a() == null || !constraintAnchorA3.k()) {
            return;
        }
        int iB3 = constraintAnchorA3.b();
        for (ConstraintAnchor constraintAnchor10 : constraintAnchorA3.a()) {
            ConstraintWidget constraintWidget4 = constraintAnchor10.d;
            boolean zA3 = a(constraintWidget4);
            if (constraintWidget4.M() && zA3) {
                f6.a(constraintWidget4, bVar, new o6.a(), o6.a.k);
            }
            if (constraintWidget4.A() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || zA3) {
                if (!constraintWidget4.M() && constraintAnchor10 == constraintWidget4.L) {
                    constraintWidget4.j(iB3);
                    a(constraintWidget4, bVar);
                }
            }
        }
    }

    public static void a(o6.b bVar, ConstraintWidget constraintWidget, boolean z) {
        float fM = constraintWidget.m();
        int iB = constraintWidget.H.f.b();
        int iB2 = constraintWidget.J.f.b();
        int iC = constraintWidget.H.c() + iB;
        int iC2 = iB2 - constraintWidget.J.c();
        if (iB == iB2) {
            fM = 0.5f;
        } else {
            iB = iC;
            iB2 = iC2;
        }
        int iD = constraintWidget.D();
        int i = (iB2 - iB) - iD;
        if (iB > iB2) {
            i = (iB - iB2) - iD;
        }
        int i2 = ((int) ((fM * i) + 0.5f)) + iB;
        int i3 = i2 + iD;
        if (iB > iB2) {
            i3 = i2 - iD;
        }
        constraintWidget.a(i2, i3);
        a(constraintWidget, bVar, z);
    }

    public static void a(o6.b bVar, ConstraintWidget constraintWidget) {
        float fY = constraintWidget.y();
        int iB = constraintWidget.I.f.b();
        int iB2 = constraintWidget.K.f.b();
        int iC = constraintWidget.I.c() + iB;
        int iC2 = iB2 - constraintWidget.K.c();
        if (iB == iB2) {
            fY = 0.5f;
        } else {
            iB = iC;
            iB2 = iC2;
        }
        int iL = constraintWidget.l();
        int i = (iB2 - iB) - iL;
        if (iB > iB2) {
            i = (iB - iB2) - iL;
        }
        int i2 = (int) ((fY * i) + 0.5f);
        int i3 = iB + i2;
        int i4 = i3 + iL;
        if (iB > iB2) {
            i3 = iB - i2;
            i4 = i3 - iL;
        }
        constraintWidget.b(i3, i4);
        a(constraintWidget, bVar);
    }

    public static void a(ConstraintWidget constraintWidget, o6.b bVar, ConstraintWidget constraintWidget2, boolean z) {
        int iD;
        float fM = constraintWidget2.m();
        int iB = constraintWidget2.H.f.b() + constraintWidget2.H.c();
        int iB2 = constraintWidget2.J.f.b() - constraintWidget2.J.c();
        if (iB2 >= iB) {
            int iD2 = constraintWidget2.D();
            if (constraintWidget2.C() != 8) {
                int i = constraintWidget2.n;
                if (i == 2) {
                    if (constraintWidget instanceof f6) {
                        iD = constraintWidget.D();
                    } else {
                        iD = constraintWidget.w().D();
                    }
                    iD2 = (int) (constraintWidget2.m() * 0.5f * iD);
                } else if (i == 0) {
                    iD2 = iB2 - iB;
                }
                iD2 = Math.max(constraintWidget2.q, iD2);
                int i2 = constraintWidget2.r;
                if (i2 > 0) {
                    iD2 = Math.min(i2, iD2);
                }
            }
            int i3 = iB + ((int) ((fM * ((iB2 - iB) - iD2)) + 0.5f));
            constraintWidget2.a(i3, iD2 + i3);
            a(constraintWidget2, bVar, z);
        }
    }

    public static void a(ConstraintWidget constraintWidget, o6.b bVar, ConstraintWidget constraintWidget2) {
        int iL;
        float fY = constraintWidget2.y();
        int iB = constraintWidget2.I.f.b() + constraintWidget2.I.c();
        int iB2 = constraintWidget2.K.f.b() - constraintWidget2.K.c();
        if (iB2 >= iB) {
            int iL2 = constraintWidget2.l();
            if (constraintWidget2.C() != 8) {
                int i = constraintWidget2.o;
                if (i == 2) {
                    if (constraintWidget instanceof f6) {
                        iL = constraintWidget.l();
                    } else {
                        iL = constraintWidget.w().l();
                    }
                    iL2 = (int) (fY * 0.5f * iL);
                } else if (i == 0) {
                    iL2 = iB2 - iB;
                }
                iL2 = Math.max(constraintWidget2.t, iL2);
                int i2 = constraintWidget2.u;
                if (i2 > 0) {
                    iL2 = Math.min(i2, iL2);
                }
            }
            int i3 = iB + ((int) ((fY * ((iB2 - iB) - iL2)) + 0.5f));
            constraintWidget2.b(i3, iL2 + i3);
            a(constraintWidget2, bVar);
        }
    }

    public static boolean a(ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviourO = constraintWidget.o();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourA = constraintWidget.A();
        f6 f6Var = constraintWidget.w() != null ? (f6) constraintWidget.w() : null;
        if (f6Var != null) {
            f6Var.o();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (f6Var != null) {
            f6Var.A();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        boolean z = dimensionBehaviourO == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourO == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviourO == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.n == 0 && constraintWidget.W == 0.0f && constraintWidget.g(0)) || constraintWidget.N();
        boolean z2 = dimensionBehaviourA == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourA == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviourA == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.o == 0 && constraintWidget.W == 0.0f && constraintWidget.g(1)) || constraintWidget.O();
        if (constraintWidget.W <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }
}
