package supwisdom;

/* JADX INFO: compiled from: SeekMap.java */
/* JADX INFO: loaded from: classes.dex */
public interface e50 {

    /* JADX INFO: compiled from: SeekMap.java */
    public static final class a implements e50 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f7425a;

        public a(long j) {
            this.f7425a = j;
        }

        @Override // supwisdom.e50
        public boolean a() {
            return false;
        }

        @Override // supwisdom.e50
        public long b() {
            return this.f7425a;
        }

        @Override // supwisdom.e50
        public long b(long j) {
            return 0L;
        }
    }

    boolean a();

    long b();

    long b(long j);
}
