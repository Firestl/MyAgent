package supwisdom;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class da {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ca f7314a = new e(null, false);
    public static final ca b = new e(null, true);
    public static final ca c = new e(b.f7316a, false);
    public static final ca d = new e(b.f7316a, true);

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class a implements c {
        public static final a b = new a(true);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f7315a;

        public a(boolean z) {
            this.f7315a = z;
        }

        @Override // supwisdom.da.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            boolean z = false;
            while (i < i3) {
                int iA = da.a(Character.getDirectionality(charSequence.charAt(i)));
                if (iA != 0) {
                    if (iA != 1) {
                        continue;
                        i++;
                    } else if (!this.f7315a) {
                        return 1;
                    }
                } else if (this.f7315a) {
                    return 0;
                }
                z = true;
                i++;
            }
            if (z) {
                return this.f7315a ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7316a = new b();

        @Override // supwisdom.da.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int iB = 2;
            while (i < i3 && iB == 2) {
                iB = da.b(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return iB;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static abstract class d implements ca {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f7317a;

        public d(c cVar) {
            this.f7317a = cVar;
        }

        public abstract boolean a();

        @Override // supwisdom.ca
        public boolean a(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            return this.f7317a == null ? a() : b(charSequence, i, i2);
        }

        public final boolean b(CharSequence charSequence, int i, int i2) {
            int iA = this.f7317a.a(charSequence, i, i2);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return a();
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class e extends d {
        public final boolean b;

        public e(c cVar, boolean z) {
            super(cVar);
            this.b = z;
        }

        @Override // supwisdom.da.d
        public boolean a() {
            return this.b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class f extends d {
        public static final f b = new f();

        public f() {
            super(null);
        }

        @Override // supwisdom.da.d
        public boolean a() {
            return ea.b(Locale.getDefault()) == 1;
        }
    }

    static {
        new e(a.b, false);
        f fVar = f.b;
    }

    public static int a(int i) {
        if (i != 0) {
            return (i == 1 || i == 2) ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i) {
        if (i != 0) {
            if (i == 1 || i == 2) {
                return 0;
            }
            switch (i) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
