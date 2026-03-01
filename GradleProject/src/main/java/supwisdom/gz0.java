package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class gz0 extends l61 implements Comparable<gz0> {
    public static final gz0 c = new gz0(0);

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7784a;
        public final int b;
        public final fz0 c;

        public a(int i, int i2, fz0 fz0Var) {
            if (i < 0) {
                throw new IllegalArgumentException("start < 0");
            }
            if (i2 <= i) {
                throw new IllegalArgumentException("end <= start");
            }
            if (fz0Var.d()) {
                throw new IllegalArgumentException("handlers.isMutable()");
            }
            this.f7784a = i;
            this.b = i2;
            this.c = fz0Var;
        }

        public int a() {
            return this.b;
        }

        public fz0 b() {
            return this.c;
        }

        public int c() {
            return this.f7784a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && compareTo((a) obj) == 0;
        }

        public int hashCode() {
            return (((this.f7784a * 31) + this.b) * 31) + this.c.hashCode();
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            int i = this.f7784a;
            int i2 = aVar.f7784a;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int i3 = this.b;
            int i4 = aVar.b;
            if (i3 < i4) {
                return -1;
            }
            if (i3 > i4) {
                return 1;
            }
            return this.c.compareTo(aVar.c);
        }
    }

    public gz0(int i) {
        super(i);
    }

    public void a(int i, a aVar) {
        a(i, (Object) aVar);
    }

    public a d(int i) {
        return (a) a(i);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(gz0 gz0Var) {
        if (this == gz0Var) {
            return 0;
        }
        int size = size();
        int size2 = gz0Var.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = d(i).compareTo(gz0Var.d(i));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (size < size2) {
            return -1;
        }
        return size > size2 ? 1 : 0;
    }
}
