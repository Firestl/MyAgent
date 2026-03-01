package supwisdom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.WindowManager;

/* JADX INFO: compiled from: VideoFrameReleaseTimeHelper.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class c90 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f7164a;
    public final boolean b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f7165e;
    public long f;
    public long g;
    public boolean h;
    public long i;
    public long j;
    public long k;

    /* JADX INFO: compiled from: VideoFrameReleaseTimeHelper.java */
    public static final class a implements Handler.Callback, Choreographer.FrameCallback {
        public static final a f = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public volatile long f7166a;
        public final Handler b;
        public final HandlerThread c;
        public Choreographer d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7167e;

        public a() {
            HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
            this.c = handlerThread;
            handlerThread.start();
            Handler handler = new Handler(this.c.getLooper(), this);
            this.b = handler;
            handler.sendEmptyMessage(0);
        }

        public static a f() {
            return f;
        }

        public void a() {
            this.b.sendEmptyMessage(1);
        }

        public void b() {
            this.b.sendEmptyMessage(2);
        }

        public final void c() {
            this.d = Choreographer.getInstance();
        }

        public final void d() {
            int i = this.f7167e + 1;
            this.f7167e = i;
            if (i == 1) {
                this.d.postFrameCallback(this);
            }
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            this.f7166a = j;
            this.d.postFrameCallbackDelayed(this, 500L);
        }

        public final void e() {
            int i = this.f7167e - 1;
            this.f7167e = i;
            if (i == 0) {
                this.d.removeFrameCallback(this);
                this.f7166a = 0L;
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                c();
                return true;
            }
            if (i == 1) {
                d();
                return true;
            }
            if (i != 2) {
                return false;
            }
            e();
            return true;
        }
    }

    public c90(Context context) {
        this(a(context), true);
    }

    public void a() {
        this.h = false;
        if (this.b) {
            this.f7164a.a();
        }
    }

    public void b() {
        if (this.b) {
            this.f7164a.b();
        }
    }

    public void c() {
    }

    public c90(double d, boolean z) {
        this.b = z;
        if (!z) {
            this.f7164a = null;
            this.c = -1L;
            this.d = -1L;
        } else {
            this.f7164a = a.f();
            long j = (long) (1.0E9d / d);
            this.c = j;
            this.d = (j * 80) / 100;
        }
    }

    public final boolean b(long j, long j2) {
        return Math.abs((j2 - this.i) - (j - this.j)) > 20000000;
    }

    public long a(long j, long j2) {
        long j3;
        long j4;
        long j5 = 1000 * j;
        if (this.h) {
            if (j != this.f7165e) {
                this.k++;
                this.f = this.g;
            }
            long j6 = this.k;
            if (j6 >= 6) {
                j4 = this.f + ((j5 - this.j) / j6);
                if (b(j4, j2)) {
                    this.h = false;
                } else {
                    j3 = (this.i + j4) - this.j;
                }
            } else if (b(j5, j2)) {
                this.h = false;
            }
            j3 = j2;
            j4 = j5;
        } else {
            j3 = j2;
            j4 = j5;
        }
        if (!this.h) {
            this.j = j5;
            this.i = j2;
            this.k = 0L;
            this.h = true;
            c();
        }
        this.f7165e = j;
        this.g = j4;
        a aVar = this.f7164a;
        return (aVar == null || aVar.f7166a == 0) ? j3 : a(j3, this.f7164a.f7166a, this.c) - this.d;
    }

    public static long a(long j, long j2, long j3) {
        long j4;
        long j5 = j2 + (((j - j2) / j3) * j3);
        if (j <= j5) {
            j4 = j5 - j3;
        } else {
            j5 = j3 + j5;
            j4 = j5;
        }
        return j5 - j < j - j4 ? j5 : j4;
    }

    public static float a(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }
}
