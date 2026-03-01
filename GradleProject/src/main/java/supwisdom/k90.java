package supwisdom;

import android.os.Handler;
import java.io.IOException;

/* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface k90 {

    /* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f8144a;
        public final k90 b;
        public final long c;

        /* JADX INFO: renamed from: supwisdom.k90$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
        public class RunnableC0216a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ u70 f8145a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ com.google.android.exoplayer2.j d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f8146e;
            public final /* synthetic */ Object f;
            public final /* synthetic */ long g;
            public final /* synthetic */ long h;
            public final /* synthetic */ long i;

            public RunnableC0216a(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3) {
                this.f8145a = u70Var;
                this.b = i;
                this.c = i2;
                this.d = jVar;
                this.f8146e = i3;
                this.f = obj;
                this.g = j;
                this.h = j2;
                this.i = j3;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onLoadStarted(this.f8145a, this.b, this.c, this.d, this.f8146e, this.f, a.this.b(this.g), a.this.b(this.h), this.i);
            }
        }

        /* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ u70 f8147a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ com.google.android.exoplayer2.j d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f8148e;
            public final /* synthetic */ Object f;
            public final /* synthetic */ long g;
            public final /* synthetic */ long h;
            public final /* synthetic */ long i;
            public final /* synthetic */ long j;
            public final /* synthetic */ long k;

            public b(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
                this.f8147a = u70Var;
                this.b = i;
                this.c = i2;
                this.d = jVar;
                this.f8148e = i3;
                this.f = obj;
                this.g = j;
                this.h = j2;
                this.i = j3;
                this.j = j4;
                this.k = j5;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onLoadCompleted(this.f8147a, this.b, this.c, this.d, this.f8148e, this.f, a.this.b(this.g), a.this.b(this.h), this.i, this.j, this.k);
            }
        }

        /* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ u70 f8149a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ com.google.android.exoplayer2.j d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f8150e;
            public final /* synthetic */ Object f;
            public final /* synthetic */ long g;
            public final /* synthetic */ long h;
            public final /* synthetic */ long i;
            public final /* synthetic */ long j;
            public final /* synthetic */ long k;

            public c(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
                this.f8149a = u70Var;
                this.b = i;
                this.c = i2;
                this.d = jVar;
                this.f8150e = i3;
                this.f = obj;
                this.g = j;
                this.h = j2;
                this.i = j3;
                this.j = j4;
                this.k = j5;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onLoadCanceled(this.f8149a, this.b, this.c, this.d, this.f8150e, this.f, a.this.b(this.g), a.this.b(this.h), this.i, this.j, this.k);
            }
        }

        /* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
        public class d implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ u70 f8151a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ com.google.android.exoplayer2.j d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f8152e;
            public final /* synthetic */ Object f;
            public final /* synthetic */ long g;
            public final /* synthetic */ long h;
            public final /* synthetic */ long i;
            public final /* synthetic */ long j;
            public final /* synthetic */ long k;
            public final /* synthetic */ IOException l;
            public final /* synthetic */ boolean m;

            public d(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
                this.f8151a = u70Var;
                this.b = i;
                this.c = i2;
                this.d = jVar;
                this.f8152e = i3;
                this.f = obj;
                this.g = j;
                this.h = j2;
                this.i = j3;
                this.j = j4;
                this.k = j5;
                this.l = iOException;
                this.m = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onLoadError(this.f8151a, this.b, this.c, this.d, this.f8152e, this.f, a.this.b(this.g), a.this.b(this.h), this.i, this.j, this.k, this.l, this.m);
            }
        }

        /* JADX INFO: compiled from: AdaptiveMediaSourceEventListener.java */
        public class e implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8153a;
            public final /* synthetic */ com.google.android.exoplayer2.j b;
            public final /* synthetic */ int c;
            public final /* synthetic */ Object d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ long f8154e;

            public e(int i, com.google.android.exoplayer2.j jVar, int i2, Object obj, long j) {
                this.f8153a = i;
                this.b = jVar;
                this.c = i2;
                this.d = obj;
                this.f8154e = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onDownstreamFormatChanged(this.f8153a, this.b, this.c, this.d, a.this.b(this.f8154e));
            }
        }

        public a(Handler handler, k90 k90Var) {
            this(handler, k90Var, 0L);
        }

        public void b(u70 u70Var, int i, long j, long j2, long j3) {
            b(u70Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
        }

        public a(Handler handler, k90 k90Var, long j) {
            Handler handler2;
            if (k90Var != null) {
                e80.a(handler);
                handler2 = handler;
            } else {
                handler2 = null;
            }
            this.f8144a = handler2;
            this.b = k90Var;
            this.c = j;
        }

        public void b(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            if (this.b != null) {
                this.f8144a.post(new c(u70Var, i, i2, jVar, i3, obj, j, j2, j3, j4, j5));
            }
        }

        public a a(long j) {
            return new a(this.f8144a, this.b, j);
        }

        public void a(u70 u70Var, int i, long j) {
            a(u70Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j);
        }

        public final long b(long j) {
            long jA = b20.a(j);
            if (jA == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            return this.c + jA;
        }

        public void a(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3) {
            if (this.b != null) {
                this.f8144a.post(new RunnableC0216a(u70Var, i, i2, jVar, i3, obj, j, j2, j3));
            }
        }

        public void a(u70 u70Var, int i, long j, long j2, long j3) {
            a(u70Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
        }

        public void a(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            if (this.b != null) {
                this.f8144a.post(new b(u70Var, i, i2, jVar, i3, obj, j, j2, j3, j4, j5));
            }
        }

        public void a(u70 u70Var, int i, long j, long j2, long j3, IOException iOException, boolean z) {
            a(u70Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3, iOException, z);
        }

        public void a(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
            if (this.b != null) {
                this.f8144a.post(new d(u70Var, i, i2, jVar, i3, obj, j, j2, j3, j4, j5, iOException, z));
            }
        }

        public void a(int i, com.google.android.exoplayer2.j jVar, int i2, Object obj, long j) {
            if (this.b != null) {
                this.f8144a.post(new e(i, jVar, i2, obj, j));
            }
        }
    }

    void onDownstreamFormatChanged(int i, com.google.android.exoplayer2.j jVar, int i2, Object obj, long j);

    void onLoadCanceled(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5);

    void onLoadCompleted(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5);

    void onLoadError(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z);

    void onLoadStarted(u70 u70Var, int i, int i2, com.google.android.exoplayer2.j jVar, int i3, Object obj, long j, long j2, long j3);
}
