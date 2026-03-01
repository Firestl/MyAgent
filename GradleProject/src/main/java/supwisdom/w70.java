package supwisdom;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import supwisdom.r70;

/* JADX INFO: compiled from: DefaultBandwidthMeter.java */
/* JADX INFO: loaded from: classes.dex */
public final class w70 implements r70, d80<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f9593a;
    public final r70.a b;
    public final s80 c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f9594e;
    public long f;
    public long g;
    public long h;
    public long i;

    /* JADX INFO: compiled from: DefaultBandwidthMeter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f9595a;
        public final /* synthetic */ long b;
        public final /* synthetic */ long c;

        public a(int i, long j, long j2) {
            this.f9595a = i;
            this.b = j;
            this.c = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            w70.this.b.a(this.f9595a, this.b, this.c);
        }
    }

    public w70() {
        this(null, null);
    }

    public w70(Handler handler, r70.a aVar) {
        this(handler, aVar, 2000);
    }

    @Override // supwisdom.r70
    public synchronized long a() {
        return this.i;
    }

    public w70(Handler handler, r70.a aVar, int i) {
        this.f9593a = handler;
        this.b = aVar;
        this.c = new s80(i);
        this.i = -1L;
    }

    @Override // supwisdom.d80
    public synchronized void a(Object obj, u70 u70Var) {
        if (this.d == 0) {
            this.f9594e = SystemClock.elapsedRealtime();
        }
        this.d++;
    }

    @Override // supwisdom.d80
    public synchronized void a(Object obj, int i) {
        this.f += (long) i;
    }

    @Override // supwisdom.d80
    public synchronized void a(Object obj) {
        e80.b(this.d > 0);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i = (int) (jElapsedRealtime - this.f9594e);
        this.g += i;
        this.h += this.f;
        if (i > 0) {
            this.c.a((int) Math.sqrt(this.f), (this.f * 8000) / r7);
            if (this.g >= 2000 || this.h >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                float fA = this.c.a(0.5f);
                this.i = Float.isNaN(fA) ? -1L : (long) fA;
            }
        }
        a(i, this.f, this.i);
        int i2 = this.d - 1;
        this.d = i2;
        if (i2 > 0) {
            this.f9594e = jElapsedRealtime;
        }
        this.f = 0L;
    }

    public final void a(int i, long j, long j2) {
        Handler handler = this.f9593a;
        if (handler == null || this.b == null) {
            return;
        }
        handler.post(new a(i, j, j2));
    }
}
