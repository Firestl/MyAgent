package supwisdom;

import java.util.List;

/* JADX INFO: compiled from: SegmentBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ia0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fa0 f7930a;
    public final long b;
    public final long c;

    /* JADX INFO: compiled from: SegmentBase.java */
    public static class b extends a {
        public final List<fa0> g;

        public b(fa0 fa0Var, long j, long j2, int i, long j3, List<d> list, List<fa0> list2) {
            super(fa0Var, j, j2, i, j3, list);
            this.g = list2;
        }

        @Override // supwisdom.ia0.a
        public fa0 a(ga0 ga0Var, int i) {
            return this.g.get(i - this.d);
        }

        @Override // supwisdom.ia0.a
        public boolean c() {
            return true;
        }

        @Override // supwisdom.ia0.a
        public int a(long j) {
            return this.g.size();
        }
    }

    /* JADX INFO: compiled from: SegmentBase.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f7932a;
        public final long b;

        public d(long j, long j2) {
            this.f7932a = j;
            this.b = j2;
        }
    }

    public ia0(fa0 fa0Var, long j, long j2) {
        this.f7930a = fa0Var;
        this.b = j;
        this.c = j2;
    }

    public fa0 a(ga0 ga0Var) {
        return this.f7930a;
    }

    public long a() {
        return x80.a(this.c, 1000000L, this.b);
    }

    /* JADX INFO: compiled from: SegmentBase.java */
    public static class e extends ia0 {
        public final long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f7933e;

        public e(fa0 fa0Var, long j, long j2, long j3, long j4) {
            super(fa0Var, j, j2);
            this.d = j3;
            this.f7933e = j4;
        }

        public fa0 b() {
            long j = this.f7933e;
            if (j <= 0) {
                return null;
            }
            return new fa0(null, this.d, j);
        }

        public e() {
            this(null, 1L, 0L, 0L, 0L);
        }
    }

    /* JADX INFO: compiled from: SegmentBase.java */
    public static class c extends a {
        public final ka0 g;
        public final ka0 h;

        public c(fa0 fa0Var, long j, long j2, int i, long j3, List<d> list, ka0 ka0Var, ka0 ka0Var2) {
            super(fa0Var, j, j2, i, j3, list);
            this.g = ka0Var;
            this.h = ka0Var2;
        }

        @Override // supwisdom.ia0
        public fa0 a(ga0 ga0Var) {
            ka0 ka0Var = this.g;
            if (ka0Var == null) {
                return super.a(ga0Var);
            }
            com.google.android.exoplayer2.j jVar = ga0Var.f7707a;
            return new fa0(ka0Var.a(jVar.f2303a, 0, jVar.b, 0L), 0L, -1L);
        }

        @Override // supwisdom.ia0.a
        public fa0 a(ga0 ga0Var, int i) {
            long j;
            List<d> list = this.f;
            if (list != null) {
                j = list.get(i - this.d).f7932a;
            } else {
                j = ((long) (i - this.d)) * this.f7931e;
            }
            long j2 = j;
            ka0 ka0Var = this.h;
            com.google.android.exoplayer2.j jVar = ga0Var.f7707a;
            return new fa0(ka0Var.a(jVar.f2303a, i, jVar.b, j2), 0L, -1L);
        }

        @Override // supwisdom.ia0.a
        public int a(long j) {
            List<d> list = this.f;
            if (list != null) {
                return list.size();
            }
            if (j != -9223372036854775807L) {
                return (int) x80.a(j, (this.f7931e * 1000000) / this.b);
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: SegmentBase.java */
    public static abstract class a extends ia0 {
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f7931e;
        public final List<d> f;

        public a(fa0 fa0Var, long j, long j2, int i, long j3, List<d> list) {
            super(fa0Var, j, j2);
            this.d = i;
            this.f7931e = j3;
            this.f = list;
        }

        public abstract int a(long j);

        public int a(long j, long j2) {
            int iB = b();
            int iA = a(j2);
            if (iA == 0) {
                return iB;
            }
            if (this.f == null) {
                int i = this.d + ((int) (j / ((this.f7931e * 1000000) / this.b)));
                return i < iB ? iB : iA == -1 ? i : Math.min(i, (iB + iA) - 1);
            }
            int i2 = (iA + iB) - 1;
            int i3 = iB;
            while (i3 <= i2) {
                int i4 = ((i2 - i3) / 2) + i3;
                long jA = a(i4);
                if (jA < j) {
                    i3 = i4 + 1;
                } else {
                    if (jA <= j) {
                        return i4;
                    }
                    i2 = i4 - 1;
                }
            }
            return i3 == iB ? i3 : i2;
        }

        public abstract fa0 a(ga0 ga0Var, int i);

        public int b() {
            return this.d;
        }

        public boolean c() {
            return this.f != null;
        }

        public final long a(int i, long j) {
            List<d> list = this.f;
            if (list != null) {
                return (list.get(i - this.d).b * 1000000) / this.b;
            }
            int iA = a(j);
            return (iA == -1 || i != (b() + iA) + (-1)) ? (this.f7931e * 1000000) / this.b : j - a(i);
        }

        public final long a(int i) {
            long j;
            List<d> list = this.f;
            if (list != null) {
                j = list.get(i - this.d).f7932a - this.c;
            } else {
                j = ((long) (i - this.d)) * this.f7931e;
            }
            return x80.a(j, 1000000L, this.b);
        }
    }
}
