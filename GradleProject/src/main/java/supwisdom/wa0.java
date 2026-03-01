package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: HlsMediaPlaylist.java */
/* JADX INFO: loaded from: classes.dex */
public final class wa0 extends xa0 {
    public final int b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f9604e;
    public final int f;
    public final int g;
    public final int h;
    public final long i;
    public final boolean j;
    public final boolean k;
    public final a l;
    public final List<a> m;
    public final List<String> n;
    public final long o;

    /* JADX INFO: compiled from: HlsMediaPlaylist.java */
    public static final class a implements Comparable<Long> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9605a;
        public final long b;
        public final int c;
        public final long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f9606e;
        public final String f;
        public final String g;
        public final long h;
        public final long i;

        public a(String str, long j, long j2) {
            this(str, 0L, -1, -9223372036854775807L, false, null, null, j, j2);
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(Long l) {
            if (this.d > l.longValue()) {
                return 1;
            }
            return this.d < l.longValue() ? -1 : 0;
        }

        public a(String str, long j, int i, long j2, boolean z, String str2, String str3, long j3, long j4) {
            this.f9605a = str;
            this.b = j;
            this.c = i;
            this.d = j2;
            this.f9606e = z;
            this.f = str2;
            this.g = str3;
            this.h = j3;
            this.i = j4;
        }
    }

    public wa0(int i, String str, long j, long j2, boolean z, int i2, int i3, int i4, long j3, boolean z2, boolean z3, a aVar, List<a> list, List<String> list2) {
        super(str);
        this.b = i;
        this.d = j2;
        this.f9604e = z;
        this.f = i2;
        this.g = i3;
        this.h = i4;
        this.i = j3;
        this.j = z2;
        this.k = z3;
        this.l = aVar;
        this.m = Collections.unmodifiableList(list);
        if (list.isEmpty()) {
            this.o = 0L;
        } else {
            a aVar2 = list.get(list.size() - 1);
            this.o = aVar2.d + aVar2.b;
        }
        this.c = j == -9223372036854775807L ? -9223372036854775807L : j >= 0 ? j : this.o + j;
        this.n = Collections.unmodifiableList(list2);
    }

    public boolean a(wa0 wa0Var) {
        int i;
        int i2;
        if (wa0Var == null || (i = this.g) > (i2 = wa0Var.g)) {
            return true;
        }
        if (i < i2) {
            return false;
        }
        int size = this.m.size();
        int size2 = wa0Var.m.size();
        if (size <= size2) {
            return size == size2 && this.j && !wa0Var.j;
        }
        return true;
    }

    public wa0 b() {
        return this.j ? this : new wa0(this.b, this.f9726a, this.c, this.d, this.f9604e, this.f, this.g, this.h, this.i, true, this.k, this.l, this.m, this.n);
    }

    public long a() {
        return this.d + this.o;
    }

    public wa0 a(long j, int i) {
        return new wa0(this.b, this.f9726a, this.c, j, true, i, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n);
    }
}
