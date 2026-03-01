package supwisdom;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class z6 {
    public static int d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9970a;
    public c7 b;
    public ArrayList<c7> c = new ArrayList<>();

    public z6(c7 c7Var, int i) {
        this.b = null;
        d++;
        this.b = c7Var;
    }

    public void a(c7 c7Var) {
        this.c.add(c7Var);
    }

    public final long b(s6 s6Var, long j) {
        c7 c7Var = s6Var.d;
        if (c7Var instanceof x6) {
            return j;
        }
        int size = s6Var.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            q6 q6Var = s6Var.k.get(i);
            if (q6Var instanceof s6) {
                s6 s6Var2 = (s6) q6Var;
                if (s6Var2.d != c7Var) {
                    jMax = Math.max(jMax, b(s6Var2, ((long) s6Var2.f) + j));
                }
            }
        }
        if (s6Var != c7Var.h) {
            return jMax;
        }
        long jD = j + c7Var.d();
        return Math.max(Math.max(jMax, b(c7Var.i, jD)), jD - ((long) c7Var.i.f));
    }

    public final long a(s6 s6Var, long j) {
        c7 c7Var = s6Var.d;
        if (c7Var instanceof x6) {
            return j;
        }
        int size = s6Var.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            q6 q6Var = s6Var.k.get(i);
            if (q6Var instanceof s6) {
                s6 s6Var2 = (s6) q6Var;
                if (s6Var2.d != c7Var) {
                    jMin = Math.min(jMin, a(s6Var2, ((long) s6Var2.f) + j));
                }
            }
        }
        if (s6Var != c7Var.i) {
            return jMin;
        }
        long jD = j - c7Var.d();
        return Math.min(Math.min(jMin, a(c7Var.h, jD)), jD - ((long) c7Var.h.f));
    }

    public long a(f6 f6Var, int i) {
        long jD;
        int i2;
        c7 c7Var = this.b;
        if (c7Var instanceof p6) {
            if (((p6) c7Var).f != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(c7Var instanceof y6)) {
                return 0L;
            }
        } else if (!(c7Var instanceof a7)) {
            return 0L;
        }
        s6 s6Var = (i == 0 ? f6Var.d : f6Var.f1228e).h;
        s6 s6Var2 = (i == 0 ? f6Var.d : f6Var.f1228e).i;
        boolean zContains = this.b.h.l.contains(s6Var);
        boolean zContains2 = this.b.i.l.contains(s6Var2);
        long jD2 = this.b.d();
        if (zContains && zContains2) {
            long jB = b(this.b.h, 0L);
            long jA = a(this.b.i, 0L);
            long j = jB - jD2;
            int i3 = this.b.i.f;
            if (j >= (-i3)) {
                j += (long) i3;
            }
            int i4 = this.b.h.f;
            long j2 = ((-jA) - jD2) - ((long) i4);
            if (j2 >= i4) {
                j2 -= (long) i4;
            }
            float fA = this.b.b.a(i);
            float f = fA > 0.0f ? (long) ((j2 / fA) + (j / (1.0f - fA))) : 0L;
            long j3 = ((long) ((f * fA) + 0.5f)) + jD2 + ((long) ((f * (1.0f - fA)) + 0.5f));
            c7 c7Var2 = this.b;
            jD = ((long) c7Var2.h.f) + j3;
            i2 = c7Var2.i.f;
        } else {
            if (zContains) {
                return Math.max(b(this.b.h, r12.f), ((long) this.b.h.f) + jD2);
            }
            if (zContains2) {
                return Math.max(-a(this.b.i, r12.f), ((long) (-this.b.i.f)) + jD2);
            }
            c7 c7Var3 = this.b;
            jD = ((long) c7Var3.h.f) + c7Var3.d();
            i2 = this.b.i.f;
        }
        return jD - ((long) i2);
    }
}
