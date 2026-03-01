package supwisdom;

import java.util.UUID;

/* JADX INFO: compiled from: C.java */
/* JADX INFO: loaded from: classes.dex */
public final class b20 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f7008a;
    public static final UUID b;
    public static final UUID c;

    static {
        f7008a = x80.f9718a < 23 ? 1020 : 6396;
        b = new UUID(0L, 0L);
        new UUID(1186680826959645954L, -5988876978535335093L);
        new UUID(-1301668207276963122L, -6645017420763422227L);
        c = new UUID(-7348484286925749626L, -6083546864340672619L);
    }

    public static long a(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return j / 1000;
    }

    public static long b(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return 1000 * j;
    }
}
