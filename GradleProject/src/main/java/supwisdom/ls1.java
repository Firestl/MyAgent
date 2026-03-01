package supwisdom;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import supwisdom.vt1;

/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ls1 {
    public static final Executor g = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), kt1.a("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8312a;
    public final long b;
    public final Runnable c;
    public final Deque<st1> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final tt1 f8313e;
    public boolean f;

    /* JADX INFO: compiled from: ConnectionPool.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                long jA = ls1.this.a(System.nanoTime());
                if (jA == -1) {
                    return;
                }
                if (jA > 0) {
                    long j = jA / 1000000;
                    long j2 = jA - (1000000 * j);
                    synchronized (ls1.this) {
                        try {
                            ls1.this.wait(j, (int) j2);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            }
        }
    }

    public ls1() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    @Nullable
    public st1 a(cs1 cs1Var, vt1 vt1Var, ft1 ft1Var) {
        for (st1 st1Var : this.d) {
            if (st1Var.a(cs1Var, ft1Var)) {
                vt1Var.a(st1Var, true);
                return st1Var;
            }
        }
        return null;
    }

    public void b(st1 st1Var) {
        if (!this.f) {
            this.f = true;
            g.execute(this.c);
        }
        this.d.add(st1Var);
    }

    public ls1(int i, long j, TimeUnit timeUnit) {
        this.c = new a();
        this.d = new ArrayDeque();
        this.f8313e = new tt1();
        this.f8312a = i;
        this.b = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }

    @Nullable
    public Socket a(cs1 cs1Var, vt1 vt1Var) {
        for (st1 st1Var : this.d) {
            if (st1Var.a(cs1Var, null) && st1Var.d() && st1Var != vt1Var.c()) {
                return vt1Var.b(st1Var);
            }
        }
        return null;
    }

    public boolean a(st1 st1Var) {
        if (!st1Var.k && this.f8312a != 0) {
            notifyAll();
            return false;
        }
        this.d.remove(st1Var);
        return true;
    }

    public long a(long j) {
        synchronized (this) {
            st1 st1Var = null;
            long j2 = Long.MIN_VALUE;
            int i = 0;
            int i2 = 0;
            for (st1 st1Var2 : this.d) {
                if (a(st1Var2, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - st1Var2.o;
                    if (j3 > j2) {
                        st1Var = st1Var2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.b && i <= this.f8312a) {
                if (i > 0) {
                    return this.b - j2;
                }
                if (i2 > 0) {
                    return this.b;
                }
                this.f = false;
                return -1L;
            }
            this.d.remove(st1Var);
            kt1.a(st1Var.socket());
            return 0L;
        }
    }

    public final int a(st1 st1Var, long j) {
        List<Reference<vt1>> list = st1Var.n;
        int i = 0;
        while (i < list.size()) {
            Reference<vt1> reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                yu1.c().a("A connection to " + st1Var.e().a().k() + " was leaked. Did you forget to close a response body?", ((vt1.a) reference).f9550a);
                list.remove(i);
                st1Var.k = true;
                if (list.isEmpty()) {
                    st1Var.o = j - this.b;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
