package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: Sniffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class g30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7684a = {x80.g("isom"), x80.g("iso2"), x80.g("iso3"), x80.g("iso4"), x80.g("iso5"), x80.g("iso6"), x80.g("avc1"), x80.g("hvc1"), x80.g("hev1"), x80.g("mp41"), x80.g("mp42"), x80.g("3g2a"), x80.g("3g2b"), x80.g("3gr6"), x80.g("3gs6"), x80.g("3ge6"), x80.g("3gg6"), x80.g("M4V "), x80.g("M4A "), x80.g("f4v "), x80.g("kddi"), x80.g("M4VP"), x80.g("qt  "), x80.g("MSNV")};

    public static boolean a(v40 v40Var) throws InterruptedException, IOException {
        return a(v40Var, true);
    }

    public static boolean b(v40 v40Var) throws InterruptedException, IOException {
        return a(v40Var, false);
    }

    public static boolean a(v40 v40Var, boolean z) throws InterruptedException, IOException {
        boolean z2;
        long jD = v40Var.d();
        if (jD == -1 || jD > 4096) {
            jD = 4096;
        }
        int i = (int) jD;
        o80 o80Var = new o80(64);
        int i2 = 0;
        boolean z3 = false;
        while (i2 < i) {
            o80Var.a(8);
            v40Var.c(o80Var.f8644a, 0, 8);
            long jL = o80Var.l();
            int iN = o80Var.n();
            int i3 = 16;
            if (jL == 1) {
                v40Var.c(o80Var.f8644a, 8, 8);
                o80Var.b(16);
                jL = o80Var.v();
            } else {
                i3 = 8;
            }
            long j = i3;
            if (jL < j) {
                return false;
            }
            i2 += i3;
            if (iN != y20.C) {
                if (iN == y20.L || iN == y20.N) {
                    z2 = true;
                    break;
                }
                if ((((long) i2) + jL) - j >= i) {
                    break;
                }
                int i4 = (int) (jL - j);
                i2 += i4;
                if (iN == y20.b) {
                    if (i4 < 8) {
                        return false;
                    }
                    o80Var.a(i4);
                    v40Var.c(o80Var.f8644a, 0, i4);
                    int i5 = i4 / 4;
                    int i6 = 0;
                    while (true) {
                        if (i6 >= i5) {
                            break;
                        }
                        if (i6 == 1) {
                            o80Var.d(4);
                        } else if (a(o80Var.n())) {
                            z3 = true;
                            break;
                        }
                        i6++;
                    }
                    if (!z3) {
                        return false;
                    }
                } else if (i4 != 0) {
                    v40Var.c(i4);
                }
            }
        }
        z2 = false;
        return z3 && z == z2;
    }

    public static boolean a(int i) {
        if ((i >>> 8) == x80.g("3gp")) {
            return true;
        }
        for (int i2 : f7684a) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
