package supwisdom;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import supwisdom.ee1;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class me1 {
    public ExecutorService c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8377a = 64;
    public int b = 5;
    public final Deque<ee1.c> d = new ArrayDeque();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<ee1.c> f8378e = new ArrayDeque();
    public final Deque<ee1> f = new ArrayDeque();

    public synchronized ExecutorService a() {
        if (this.c == null) {
            this.c = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), gf1.a("OkHttp Dispatcher", false));
        }
        return this.c;
    }

    public synchronized void b(ee1.c cVar) {
        if (!this.f8378e.remove(cVar)) {
            throw new AssertionError("AsyncCall wasn't running!");
        }
        b();
    }

    public final int c(ee1.c cVar) {
        Iterator<ee1.c> it = this.f8378e.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().b().equals(cVar.b())) {
                i++;
            }
        }
        return i;
    }

    public synchronized void a(ee1.c cVar) {
        if (this.f8378e.size() < this.f8377a && c(cVar) < this.b) {
            this.f8378e.add(cVar);
            a().execute(cVar);
        } else {
            this.d.add(cVar);
        }
    }

    public final void b() {
        if (this.f8378e.size() < this.f8377a && !this.d.isEmpty()) {
            Iterator<ee1.c> it = this.d.iterator();
            while (it.hasNext()) {
                ee1.c next = it.next();
                if (c(next) < this.b) {
                    it.remove();
                    this.f8378e.add(next);
                    a().execute(next);
                }
                if (this.f8378e.size() >= this.f8377a) {
                    return;
                }
            }
        }
    }

    public synchronized void a(ee1 ee1Var) {
        this.f.add(ee1Var);
    }

    public synchronized void b(ee1 ee1Var) {
        if (!this.f.remove(ee1Var)) {
            throw new AssertionError("Call wasn't in-flight!");
        }
    }
}
