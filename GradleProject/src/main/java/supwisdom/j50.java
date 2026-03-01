package supwisdom;

/* JADX INFO: compiled from: ExoPlayer.java */
/* JADX INFO: loaded from: classes.dex */
public interface j50 {

    /* JADX INFO: compiled from: ExoPlayer.java */
    public interface a {
        void onLoadingChanged(boolean z);

        void onPlaybackParametersChanged(g90 g90Var);

        void onPlayerError(com.google.android.exoplayer2.e eVar);

        void onPlayerStateChanged(boolean z, int i);

        void onPositionDiscontinuity();

        void onTimelineChanged(yb0 yb0Var, Object obj);

        void onTracksChanged(qb0 qb0Var, l70 l70Var);
    }

    /* JADX INFO: compiled from: ExoPlayer.java */
    public interface b {
        void a(int i, Object obj) throws com.google.android.exoplayer2.e;
    }

    /* JADX INFO: compiled from: ExoPlayer.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f8023a;
        public final int b;
        public final Object c;

        public c(b bVar, int i, Object obj) {
            this.f8023a = bVar;
            this.b = i;
            this.c = obj;
        }
    }

    int a();

    void a(long j);

    void a(g90 g90Var);

    void a(a aVar);

    void a(ua0 ua0Var);

    void a(ua0 ua0Var, boolean z, boolean z2);

    void a(boolean z);

    void a(c... cVarArr);

    void b(a aVar);

    void b(c... cVarArr);

    boolean b();

    g90 c();

    void d();

    long e();

    long f();

    int g();
}
