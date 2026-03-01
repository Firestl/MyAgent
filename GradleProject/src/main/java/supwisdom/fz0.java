package supwisdom;

import com.bumptech.glide.load.engine.GlideException;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class fz0 extends l61 implements Comparable<fz0> {
    public static final fz0 c = new fz0(0);

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final w51 f7671a;
        public final int b;

        public a(w51 w51Var, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("handler < 0");
            }
            if (w51Var == null) {
                throw new NullPointerException("exceptionType == null");
            }
            this.b = i;
            this.f7671a = w51Var;
        }

        public w51 a() {
            return this.f7671a;
        }

        public int b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && compareTo((a) obj) == 0;
        }

        public int hashCode() {
            return (this.b * 31) + this.f7671a.hashCode();
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            int i = this.b;
            int i2 = aVar.b;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            return this.f7671a.compareTo((u41) aVar.f7671a);
        }
    }

    public fz0(int i) {
        super(i);
    }

    public void a(int i, w51 w51Var, int i2) {
        a(i, new a(w51Var, i2));
    }

    public a d(int i) {
        return (a) a(i);
    }

    public boolean h() {
        int size = size();
        if (size == 0) {
            return false;
        }
        return d(size - 1).a().equals(w51.d);
    }

    @Override // supwisdom.l61, supwisdom.t61
    public String toHuman() {
        return a("", "");
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(fz0 fz0Var) {
        if (this == fz0Var) {
            return 0;
        }
        int size = size();
        int size2 = fz0Var.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = d(i).compareTo(fz0Var.d(i));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (size < size2) {
            return -1;
        }
        return size > size2 ? 1 : 0;
    }

    public String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(100);
        int size = size();
        sb.append(str);
        sb.append(str2);
        sb.append("catch ");
        for (int i = 0; i < size; i++) {
            a aVarD = d(i);
            if (i != 0) {
                sb.append(",\n");
                sb.append(str);
                sb.append(GlideException.IndentedAppendable.INDENT);
            }
            if (i == size - 1 && h()) {
                sb.append("<any>");
            } else {
                sb.append(aVarD.a().toHuman());
            }
            sb.append(" -> ");
            sb.append(m61.e(aVarD.b()));
        }
        return sb.toString();
    }
}
