package master.flame.danmaku.danmaku.util;

/* JADX INFO: loaded from: classes3.dex */
public class SystemClock {
    public static final void sleep(long j) {
        android.os.SystemClock.sleep(j);
    }

    public static final long uptimeMillis() {
        return android.os.SystemClock.elapsedRealtime();
    }
}
