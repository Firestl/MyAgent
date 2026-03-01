package supwisdom;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.squareup.picasso.Picasso;

/* JADX INFO: compiled from: Stats.java */
/* JADX INFO: loaded from: classes2.dex */
public class nh1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HandlerThread f8519a;
    public final ug1 b;
    public final Handler c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f8520e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public long k;
    public int l;
    public int m;
    public int n;

    /* JADX INFO: compiled from: Stats.java */
    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final nh1 f8521a;

        /* JADX INFO: renamed from: supwisdom.nh1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Stats.java */
        public class RunnableC0220a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Message f8522a;

            public RunnableC0220a(a aVar, Message message) {
                this.f8522a = message;
            }

            @Override // java.lang.Runnable
            public void run() {
                throw new AssertionError("Unhandled stats message." + this.f8522a.what);
            }
        }

        public a(Looper looper, nh1 nh1Var) {
            super(looper);
            this.f8521a = nh1Var;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                this.f8521a.d();
                return;
            }
            if (i == 1) {
                this.f8521a.e();
                return;
            }
            if (i == 2) {
                this.f8521a.b(message.arg1);
                return;
            }
            if (i == 3) {
                this.f8521a.c(message.arg1);
            } else if (i != 4) {
                Picasso.p.post(new RunnableC0220a(this, message));
            } else {
                this.f8521a.a((Long) message.obj);
            }
        }
    }

    public nh1(ug1 ug1Var) {
        this.b = ug1Var;
        HandlerThread handlerThread = new HandlerThread("Picasso-Stats", 10);
        this.f8519a = handlerThread;
        handlerThread.start();
        rh1.a(this.f8519a.getLooper());
        this.c = new a(this.f8519a.getLooper(), this);
    }

    public void a(Bitmap bitmap) {
        a(bitmap, 2);
    }

    public void b(Bitmap bitmap) {
        a(bitmap, 3);
    }

    public void c() {
        this.c.sendEmptyMessage(1);
    }

    public void d() {
        this.d++;
    }

    public void e() {
        this.f8520e++;
    }

    public void a(long j) {
        Handler handler = this.c;
        handler.sendMessage(handler.obtainMessage(4, Long.valueOf(j)));
    }

    public void b() {
        this.c.sendEmptyMessage(0);
    }

    public void c(long j) {
        this.n++;
        long j2 = this.h + j;
        this.h = j2;
        this.k = a(this.m, j2);
    }

    public void a(Long l) {
        this.l++;
        long jLongValue = this.f + l.longValue();
        this.f = jLongValue;
        this.i = a(this.l, jLongValue);
    }

    public void b(long j) {
        int i = this.m + 1;
        this.m = i;
        long j2 = this.g + j;
        this.g = j2;
        this.j = a(i, j2);
    }

    public oh1 a() {
        return new oh1(this.b.a(), this.b.size(), this.d, this.f8520e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, System.currentTimeMillis());
    }

    public final void a(Bitmap bitmap, int i) {
        int iA = rh1.a(bitmap);
        Handler handler = this.c;
        handler.sendMessage(handler.obtainMessage(i, iA, 0));
    }

    public static long a(int i, long j) {
        return j / ((long) i);
    }
}
