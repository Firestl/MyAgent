package supwisdom;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public class bx0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ax0 f7120a;
    public Executor b;
    public Executor c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<Integer, String> f7121e = Collections.synchronizedMap(new HashMap());
    public final Map<String, ReentrantLock> f = new WeakHashMap();
    public final AtomicBoolean g = new AtomicBoolean(false);
    public final AtomicBoolean h = new AtomicBoolean(false);
    public final AtomicBoolean i = new AtomicBoolean(false);
    public ExecutorService d = Executors.newCachedThreadPool();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ex0 f7122a;

        public a(ex0 ex0Var) {
            this.f7122a = ex0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zExists = bx0.this.f7120a.q.a(this.f7122a.i()).exists();
            bx0.this.c();
            if (zExists) {
                bx0.this.c.execute(this.f7122a);
            } else {
                bx0.this.b.execute(this.f7122a);
            }
        }
    }

    public bx0(ax0 ax0Var) {
        this.f7120a = ax0Var;
        this.b = ax0Var.i;
        this.c = ax0Var.j;
    }

    public boolean d() {
        return this.h.get();
    }

    public boolean e() {
        return this.i.get();
    }

    public void a(wx0 wx0Var) {
        this.f7121e.remove(Integer.valueOf(wx0Var.getId()));
    }

    public String b(wx0 wx0Var) {
        return this.f7121e.get(Integer.valueOf(wx0Var.getId()));
    }

    public final void c() {
        if (!this.f7120a.k && ((ExecutorService) this.b).isShutdown()) {
            this.b = a();
        }
        if (this.f7120a.l || !((ExecutorService) this.c).isShutdown()) {
            return;
        }
        this.c = a();
    }

    public final Executor a() {
        ax0 ax0Var = this.f7120a;
        return ww0.a(ax0Var.m, ax0Var.n, ax0Var.o);
    }

    public AtomicBoolean b() {
        return this.g;
    }

    public ReentrantLock a(String str) {
        ReentrantLock reentrantLock = this.f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        ReentrantLock reentrantLock2 = new ReentrantLock();
        this.f.put(str, reentrantLock2);
        return reentrantLock2;
    }

    public void a(wx0 wx0Var, String str) {
        this.f7121e.put(Integer.valueOf(wx0Var.getId()), str);
    }

    public void a(ex0 ex0Var) {
        this.d.execute(new a(ex0Var));
    }

    public void a(fx0 fx0Var) {
        c();
        this.c.execute(fx0Var);
    }
}
