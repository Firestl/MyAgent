package supwisdom;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes2.dex */
public final class je1 {
    public static final je1 f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8052a;
    public final long b;
    public final LinkedList<ie1> c = new LinkedList<>();
    public Executor d = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), gf1.a("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Runnable f8053e = new a();

    /* JADX INFO: compiled from: ConnectionPool.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            je1.this.b();
        }
    }

    static {
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        long j = property2 != null ? Long.parseLong(property2) : 300000L;
        if (property != null && !Boolean.parseBoolean(property)) {
            f = new je1(0, j);
        } else if (property3 != null) {
            f = new je1(Integer.parseInt(property3), j);
        } else {
            f = new je1(5, j);
        }
    }

    public je1(int i, long j) {
        this.f8052a = i;
        this.b = j * 1000 * 1000;
    }

    public static je1 c() {
        return f;
    }

    public void b(ie1 ie1Var) {
        if (!ie1Var.l() && ie1Var.a()) {
            if (!ie1Var.h()) {
                gf1.a(ie1Var.f());
                return;
            }
            try {
                ef1.c().b(ie1Var.f());
                synchronized (this) {
                    a(ie1Var);
                    ie1Var.g();
                    ie1Var.n();
                }
            } catch (SocketException e2) {
                ef1.c().a("Unable to untagSocket(): " + e2);
                gf1.a(ie1Var.f());
            }
        }
    }

    public synchronized ie1 a(ae1 ae1Var) {
        ie1 ie1Var;
        ie1Var = null;
        ListIterator<ie1> listIterator = this.c.listIterator(this.c.size());
        while (listIterator.hasPrevious()) {
            ie1 ie1VarPrevious = listIterator.previous();
            if (ie1VarPrevious.e().a().equals(ae1Var) && ie1VarPrevious.h() && System.nanoTime() - ie1VarPrevious.c() < this.b) {
                listIterator.remove();
                if (!ie1VarPrevious.l()) {
                    try {
                        ef1.c().a(ie1VarPrevious.f());
                    } catch (SocketException e2) {
                        gf1.a(ie1VarPrevious.f());
                        ef1.c().a("Unable to tagSocket(): " + e2);
                    }
                }
                ie1Var = ie1VarPrevious;
                break;
            }
        }
        if (ie1Var != null && ie1Var.l()) {
            this.c.addFirst(ie1Var);
        }
        return ie1Var;
    }

    public void c(ie1 ie1Var) {
        if (!ie1Var.l()) {
            throw new IllegalArgumentException();
        }
        if (ie1Var.h()) {
            synchronized (this) {
                a(ie1Var);
            }
        }
    }

    public final void b() {
        while (a()) {
        }
    }

    public final void a(ie1 ie1Var) {
        boolean zIsEmpty = this.c.isEmpty();
        this.c.addFirst(ie1Var);
        if (zIsEmpty) {
            this.d.execute(this.f8053e);
        } else {
            notifyAll();
        }
    }

    public boolean a() {
        synchronized (this) {
            if (this.c.isEmpty()) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            long jNanoTime = System.nanoTime();
            long jMin = this.b;
            ListIterator<ie1> listIterator = this.c.listIterator(this.c.size());
            int i = 0;
            while (listIterator.hasPrevious()) {
                ie1 ie1VarPrevious = listIterator.previous();
                long jC = (ie1VarPrevious.c() + this.b) - jNanoTime;
                if (jC > 0 && ie1VarPrevious.h()) {
                    if (ie1VarPrevious.j()) {
                        i++;
                        jMin = Math.min(jMin, jC);
                    }
                } else {
                    listIterator.remove();
                    arrayList.add(ie1VarPrevious);
                }
            }
            ListIterator<ie1> listIterator2 = this.c.listIterator(this.c.size());
            while (listIterator2.hasPrevious() && i > this.f8052a) {
                ie1 ie1VarPrevious2 = listIterator2.previous();
                if (ie1VarPrevious2.j()) {
                    arrayList.add(ie1VarPrevious2);
                    listIterator2.remove();
                    i--;
                }
            }
            if (arrayList.isEmpty()) {
                try {
                    long j = jMin / 1000000;
                    Long.signum(j);
                    wait(j, (int) (jMin - (1000000 * j)));
                    return true;
                } catch (InterruptedException unused) {
                }
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                gf1.a(((ie1) arrayList.get(i2)).f());
            }
            return true;
        }
    }
}
