package supwisdom;

import java.util.Collection;
import supwisdom.w41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class d31 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t11 f7291a;
    public final h61 b;

    public d31(t11 t11Var, h61 h61Var) {
        if (t11Var == null) {
            throw new NullPointerException("file == null");
        }
        if (h61Var == null) {
            throw new NullPointerException("out == null");
        }
        this.f7291a = t11Var;
        this.b = h61Var;
    }

    public static void a(t11 t11Var, w31 w31Var) {
        z21 z21VarP = t11Var.p();
        x21 x21VarO = t11Var.o();
        z21VarP.b(w31Var.getType());
        for (y31 y31Var : w31Var.h()) {
            x21VarO.b(y31Var.a());
            a(t11Var, y31Var.b());
        }
    }

    public static String b(u41 u41Var) {
        if (c(u41Var) == 30) {
            return com.igexin.push.core.b.m;
        }
        return u41Var.c() + ' ' + u41Var.toHuman();
    }

    public static int c(u41 u41Var) {
        if (u41Var instanceof z41) {
            return 0;
        }
        if (u41Var instanceof u51) {
            return 2;
        }
        if (u41Var instanceof c51) {
            return 3;
        }
        if (u41Var instanceof h51) {
            return 4;
        }
        if (u41Var instanceof o51) {
            return 6;
        }
        if (u41Var instanceof g51) {
            return 16;
        }
        if (u41Var instanceof d51) {
            return 17;
        }
        if (u41Var instanceof t51) {
            return 21;
        }
        if (u41Var instanceof q51) {
            return 22;
        }
        if (u41Var instanceof v51) {
            return 23;
        }
        if (u41Var instanceof w51) {
            return 24;
        }
        if (u41Var instanceof f51) {
            return 25;
        }
        if (u41Var instanceof r51) {
            return 26;
        }
        if (u41Var instanceof e51) {
            return 27;
        }
        if (u41Var instanceof w41) {
            return 28;
        }
        if (u41Var instanceof v41) {
            return 29;
        }
        if (u41Var instanceof k51) {
            return 30;
        }
        if (u41Var instanceof y41) {
            return 31;
        }
        throw new RuntimeException("Shouldn't happen");
    }

    public void a(w31 w31Var, boolean z) {
        boolean z2 = z && this.b.e();
        x21 x21VarO = this.f7291a.o();
        z21 z21VarP = this.f7291a.p();
        w51 type = w31Var.getType();
        int iA = z21VarP.a(type);
        if (z2) {
            this.b.a("  type_idx: " + m61.g(iA) + " // " + type.toHuman());
        }
        this.b.c(z21VarP.a(w31Var.getType()));
        Collection<y31> collectionH = w31Var.h();
        int size = collectionH.size();
        if (z2) {
            this.b.a("  size: " + m61.g(size));
        }
        this.b.c(size);
        int i = 0;
        for (y31 y31Var : collectionH) {
            v51 v51VarA = y31Var.a();
            int iA2 = x21VarO.a(v51VarA);
            u41 u41VarB = y31Var.b();
            if (z2) {
                this.b.a(0, "  elements[" + i + "]:");
                i++;
                this.b.a("    name_idx: " + m61.g(iA2) + " // " + v51VarA.toHuman());
            }
            this.b.c(iA2);
            if (z2) {
                this.b.a("    value: " + b(u41VarB));
            }
            a(u41VarB);
        }
        if (z2) {
            this.b.d();
        }
    }

    public void a(w41 w41Var, boolean z) {
        boolean z2 = z && this.b.e();
        w41.a aVarD = w41Var.d();
        int size = aVarD.size();
        if (z2) {
            this.b.a("  size: " + m61.g(size));
        }
        this.b.c(size);
        for (int i = 0; i < size; i++) {
            u41 u41VarD = aVarD.d(i);
            if (z2) {
                this.b.a("  [" + Integer.toHexString(i) + "] " + b(u41VarD));
            }
            a(u41VarD);
        }
        if (z2) {
            this.b.d();
        }
    }

    public void a(u41 u41Var) {
        int iC = c(u41Var);
        if (iC != 0 && iC != 6 && iC != 2) {
            if (iC == 3) {
                oy0.c(this.b, iC, ((n51) u41Var).f());
                return;
            }
            if (iC != 4) {
                if (iC == 16) {
                    oy0.a(this.b, iC, ((g51) u41Var).f() << 32);
                    return;
                }
                if (iC != 17) {
                    switch (iC) {
                        case 21:
                            oy0.c(this.b, iC, this.f7291a.l().a(((t51) u41Var).d()));
                            return;
                        case 22:
                            oy0.c(this.b, iC, this.f7291a.j().a((q51) u41Var));
                            return;
                        case 23:
                            oy0.c(this.b, iC, this.f7291a.o().a((v51) u41Var));
                            return;
                        case 24:
                            oy0.c(this.b, iC, this.f7291a.p().a((w51) u41Var));
                            return;
                        case 25:
                            oy0.c(this.b, iC, this.f7291a.e().a((f51) u41Var));
                            return;
                        case 26:
                            oy0.c(this.b, iC, this.f7291a.k().a((x41) u41Var));
                            return;
                        case 27:
                            oy0.c(this.b, iC, this.f7291a.e().a(((e51) u41Var).f()));
                            return;
                        case 28:
                            this.b.writeByte(iC);
                            a((w41) u41Var, false);
                            return;
                        case 29:
                            this.b.writeByte(iC);
                            a(((v41) u41Var).d(), false);
                            return;
                        case 30:
                            this.b.writeByte(iC);
                            return;
                        case 31:
                            this.b.writeByte((((y41) u41Var).e() << 5) | iC);
                            return;
                        default:
                            throw new RuntimeException("Shouldn't happen");
                    }
                }
                oy0.a(this.b, iC, ((d51) u41Var).f());
                return;
            }
        }
        oy0.b(this.b, iC, ((n51) u41Var).f());
    }

    public static void a(t11 t11Var, u41 u41Var) {
        if (u41Var instanceof v41) {
            a(t11Var, ((v41) u41Var).d());
            return;
        }
        if (u41Var instanceof w41) {
            w41.a aVarD = ((w41) u41Var).d();
            int size = aVarD.size();
            for (int i = 0; i < size; i++) {
                a(t11Var, aVarD.d(i));
            }
            return;
        }
        t11Var.b(u41Var);
    }
}
