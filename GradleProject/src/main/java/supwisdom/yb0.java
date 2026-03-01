package supwisdom;

/* JADX INFO: compiled from: Timeline.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class yb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final yb0 f9863a = new a();

    /* JADX INFO: compiled from: Timeline.java */
    public static class a extends yb0 {
        @Override // supwisdom.yb0
        public int a(Object obj) {
            return -1;
        }

        @Override // supwisdom.yb0
        public c a(int i, c cVar, boolean z, long j) {
            throw new IndexOutOfBoundsException();
        }

        @Override // supwisdom.yb0
        public int b() {
            return 0;
        }

        @Override // supwisdom.yb0
        public int c() {
            return 0;
        }

        @Override // supwisdom.yb0
        public b a(int i, b bVar, boolean z) {
            throw new IndexOutOfBoundsException();
        }
    }

    public abstract int a(Object obj);

    public abstract b a(int i, b bVar, boolean z);

    public abstract c a(int i, c cVar, boolean z, long j);

    public final boolean a() {
        return b() == 0;
    }

    public abstract int b();

    public abstract int c();

    public final c a(int i, c cVar) {
        return a(i, cVar, false);
    }

    public c a(int i, c cVar, boolean z) {
        return a(i, cVar, z, 0L);
    }

    public final b a(int i, b bVar) {
        return a(i, bVar, false);
    }

    /* JADX INFO: compiled from: Timeline.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f9864a;
        public Object b;
        public int c;
        public long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f9865e;

        public b a(Object obj, Object obj2, int i, long j, long j2, boolean z) {
            this.f9864a = obj;
            this.b = obj2;
            this.c = i;
            this.d = j;
            this.f9865e = j2;
            return this;
        }

        public long b() {
            return this.d;
        }

        public long c() {
            return b20.a(this.f9865e);
        }

        public long a() {
            return b20.a(this.d);
        }
    }

    /* JADX INFO: compiled from: Timeline.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f9866a;
        public boolean b;
        public boolean c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9867e;
        public long f;
        public long g;
        public long h;

        public c a(Object obj, long j, long j2, boolean z, boolean z2, long j3, long j4, int i, int i2, long j5) {
            this.f9866a = obj;
            this.b = z;
            this.c = z2;
            this.f = j3;
            this.g = j4;
            this.d = i;
            this.f9867e = i2;
            this.h = j5;
            return this;
        }

        public long b() {
            return b20.a(this.g);
        }

        public long c() {
            return this.h;
        }

        public long a() {
            return this.f;
        }
    }
}
