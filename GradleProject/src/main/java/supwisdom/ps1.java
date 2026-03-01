package supwisdom;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import supwisdom.at1;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ps1 {

    @Nullable
    public Runnable c;

    @Nullable
    public ExecutorService d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8825a = 64;
    public int b = 5;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<at1.b> f8826e = new ArrayDeque();
    public final Deque<at1.b> f = new ArrayDeque();
    public final Deque<at1> g = new ArrayDeque();

    public synchronized ExecutorService a() {
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), kt1.a("OkHttp Dispatcher", false));
        }
        return this.d;
    }

    public final boolean b() {
        int i;
        boolean z;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<at1.b> it = this.f8826e.iterator();
            while (it.hasNext()) {
                at1.b next = it.next();
                if (this.f.size() >= this.f8825a) {
                    break;
                }
                if (c(next) < this.b) {
                    it.remove();
                    arrayList.add(next);
                    this.f.add(next);
                }
            }
            z = c() > 0;
        }
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            ((at1.b) arrayList.get(i)).a(a());
        }
        return z;
    }

    public final int c(at1.b bVar) {
        int i = 0;
        for (at1.b bVar2 : this.f) {
            if (!bVar2.b().f && bVar2.c().equals(bVar.c())) {
                i++;
            }
        }
        return i;
    }

    public synchronized int c() {
        return this.f.size() + this.g.size();
    }

    public void a(at1.b bVar) {
        synchronized (this) {
            this.f8826e.add(bVar);
        }
        b();
    }

    public synchronized void a(at1 at1Var) {
        this.g.add(at1Var);
    }

    public final <T> void a(Deque<T> deque, T t) {
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t)) {
                runnable = this.c;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (b() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public void b(at1.b bVar) {
        a(this.f, bVar);
    }

    public void b(at1 at1Var) {
        a(this.g, at1Var);
    }
}
