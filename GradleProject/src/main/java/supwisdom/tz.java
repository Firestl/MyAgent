package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public abstract class tz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final tz[] f9340a;

    public static final class b extends tz {
        public b() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }
    }

    public static final class c extends tz {
        public c() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            return (i & 1) == 0;
        }
    }

    public static final class d extends tz {
        public d() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            return i2 % 3 == 0;
        }
    }

    public static final class e extends tz {
        public e() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            return (i + i2) % 3 == 0;
        }
    }

    public static final class f extends tz {
        public f() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            return (((i / 2) + (i2 / 3)) & 1) == 0;
        }
    }

    public static final class g extends tz {
        public g() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            int i3 = i * i2;
            return (i3 & 1) + (i3 % 3) == 0;
        }
    }

    public static final class h extends tz {
        public h() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            int i3 = i * i2;
            return (((i3 & 1) + (i3 % 3)) & 1) == 0;
        }
    }

    public static final class i extends tz {
        public i() {
            super();
        }

        @Override // supwisdom.tz
        public boolean a(int i, int i2) {
            return ((((i + i2) & 1) + ((i * i2) % 3)) & 1) == 0;
        }
    }

    static {
        f9340a = new tz[]{new b(), new c(), new d(), new e(), new f(), new g(), new h(), new i()};
    }

    public static tz a(int i2) {
        if (i2 < 0 || i2 > 7) {
            throw new IllegalArgumentException();
        }
        return f9340a[i2];
    }

    public abstract boolean a(int i2, int i3);

    public tz() {
    }

    public final void a(fw fwVar, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (a(i3, i4)) {
                    fwVar.a(i4, i3);
                }
            }
        }
    }
}
