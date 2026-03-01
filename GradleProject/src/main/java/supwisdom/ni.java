package supwisdom;

import bolts.ExecutorException;
import bolts.UnobservedTaskException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: Task.java */
/* JADX INFO: loaded from: classes.dex */
public class ni<TResult> {
    public static volatile f l;
    public boolean b;
    public boolean c;
    public TResult d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Exception f8524e;
    public boolean f;
    public pi g;
    public static final ExecutorService i = ki.a();
    public static final Executor j = ki.b();
    public static final Executor k = ji.b();
    public static ni<?> m = new ni<>((Object) null);
    public static ni<Boolean> n = new ni<>(true);
    public static ni<Boolean> o = new ni<>(false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8523a = new Object();
    public List<mi<TResult, Void>> h = new ArrayList();

    /* JADX INFO: compiled from: Task.java */
    public class a implements mi<TResult, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ oi f8525a;
        public final /* synthetic */ mi b;
        public final /* synthetic */ Executor c;
        public final /* synthetic */ li d;

        public a(ni niVar, oi oiVar, mi miVar, Executor executor, li liVar) {
            this.f8525a = oiVar;
            this.b = miVar;
            this.c = executor;
            this.d = liVar;
        }

        @Override // supwisdom.mi
        public Void then(ni<TResult> niVar) {
            ni.d(this.f8525a, this.b, niVar, this.c, this.d);
            return null;
        }
    }

    /* JADX INFO: compiled from: Task.java */
    public class b implements mi<TResult, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ oi f8526a;
        public final /* synthetic */ mi b;
        public final /* synthetic */ Executor c;
        public final /* synthetic */ li d;

        public b(ni niVar, oi oiVar, mi miVar, Executor executor, li liVar) {
            this.f8526a = oiVar;
            this.b = miVar;
            this.c = executor;
            this.d = liVar;
        }

        @Override // supwisdom.mi
        public Void then(ni<TResult> niVar) {
            ni.c(this.f8526a, this.b, niVar, this.c, this.d);
            return null;
        }
    }

    /* JADX INFO: compiled from: Task.java */
    public static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ li f8527a;
        public final /* synthetic */ oi b;
        public final /* synthetic */ mi c;
        public final /* synthetic */ ni d;

        public c(li liVar, oi oiVar, mi miVar, ni niVar) {
            this.f8527a = liVar;
            this.b = oiVar;
            this.c = miVar;
            this.d = niVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            li liVar = this.f8527a;
            if (liVar != null) {
                liVar.a();
                throw null;
            }
            try {
                this.b.a(this.c.then(this.d));
            } catch (CancellationException unused) {
                this.b.b();
            } catch (Exception e2) {
                this.b.a(e2);
            }
        }
    }

    /* JADX INFO: compiled from: Task.java */
    public static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ li f8528a;
        public final /* synthetic */ oi b;
        public final /* synthetic */ mi c;
        public final /* synthetic */ ni d;

        /* JADX INFO: Add missing generic type declarations: [TContinuationResult] */
        /* JADX INFO: compiled from: Task.java */
        public class a<TContinuationResult> implements mi<TContinuationResult, Void> {
            public a() {
            }

            @Override // supwisdom.mi
            public Void then(ni<TContinuationResult> niVar) {
                li liVar = d.this.f8528a;
                if (liVar != null) {
                    liVar.a();
                    throw null;
                }
                if (niVar.c()) {
                    d.this.b.b();
                } else if (niVar.e()) {
                    d.this.b.a(niVar.a());
                } else {
                    d.this.b.a(niVar.b());
                }
                return null;
            }
        }

        public d(li liVar, oi oiVar, mi miVar, ni niVar) {
            this.f8528a = liVar;
            this.b = oiVar;
            this.c = miVar;
            this.d = niVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            li liVar = this.f8528a;
            if (liVar != null) {
                liVar.a();
                throw null;
            }
            try {
                ni niVar = (ni) this.c.then(this.d);
                if (niVar == null) {
                    this.b.a((Object) null);
                } else {
                    niVar.a((mi) new a());
                }
            } catch (CancellationException unused) {
                this.b.b();
            } catch (Exception e2) {
                this.b.a(e2);
            }
        }
    }

    /* JADX INFO: compiled from: Task.java */
    public static class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ li f8530a;
        public final /* synthetic */ oi b;
        public final /* synthetic */ Callable c;

        public e(li liVar, oi oiVar, Callable callable) {
            this.f8530a = liVar;
            this.b = oiVar;
            this.c = callable;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            li liVar = this.f8530a;
            if (liVar != null) {
                liVar.a();
                throw null;
            }
            try {
                this.b.a(this.c.call());
            } catch (CancellationException unused) {
                this.b.b();
            } catch (Exception e2) {
                this.b.a(e2);
            }
        }
    }

    /* JADX INFO: compiled from: Task.java */
    public interface f {
        void a(ni<?> niVar, UnobservedTaskException unobservedTaskException);
    }

    static {
        new ni(true);
    }

    public ni() {
    }

    public static f h() {
        return l;
    }

    public boolean c() {
        boolean z;
        synchronized (this.f8523a) {
            z = this.c;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.f8523a) {
            z = this.b;
        }
        return z;
    }

    public boolean e() {
        boolean z;
        synchronized (this.f8523a) {
            z = a() != null;
        }
        return z;
    }

    public final void f() {
        synchronized (this.f8523a) {
            Iterator<mi<TResult, Void>> it = this.h.iterator();
            while (it.hasNext()) {
                try {
                    it.next().then(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.h = null;
        }
    }

    public boolean g() {
        synchronized (this.f8523a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.c = true;
            this.f8523a.notifyAll();
            f();
            return true;
        }
    }

    public Exception a() {
        Exception exc;
        synchronized (this.f8523a) {
            if (this.f8524e != null) {
                this.f = true;
                if (this.g != null) {
                    this.g.a();
                    this.g = null;
                }
            }
            exc = this.f8524e;
        }
        return exc;
    }

    public TResult b() {
        TResult tresult;
        synchronized (this.f8523a) {
            tresult = this.d;
        }
        return tresult;
    }

    public ni(TResult tresult) {
        a(tresult);
    }

    public static <TContinuationResult, TResult> void c(oi<TContinuationResult> oiVar, mi<TResult, ni<TContinuationResult>> miVar, ni<TResult> niVar, Executor executor, li liVar) {
        try {
            executor.execute(new d(liVar, oiVar, miVar, niVar));
        } catch (Exception e2) {
            oiVar.a(new ExecutorException(e2));
        }
    }

    public static <TContinuationResult, TResult> void d(oi<TContinuationResult> oiVar, mi<TResult, TContinuationResult> miVar, ni<TResult> niVar, Executor executor, li liVar) {
        try {
            executor.execute(new c(liVar, oiVar, miVar, niVar));
        } catch (Exception e2) {
            oiVar.a(new ExecutorException(e2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <TResult> ni<TResult> b(TResult tresult) {
        if (tresult == 0) {
            return (ni<TResult>) m;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? (ni<TResult>) n : (ni<TResult>) o;
        }
        oi oiVar = new oi();
        oiVar.a(tresult);
        return oiVar.a();
    }

    public ni(boolean z) {
        if (z) {
            g();
        } else {
            a((Object) null);
        }
    }

    public static <TResult> ni<TResult> a(Callable<TResult> callable, Executor executor) {
        return a(callable, executor, (li) null);
    }

    public static <TResult> ni<TResult> a(Callable<TResult> callable, Executor executor, li liVar) {
        oi oiVar = new oi();
        try {
            executor.execute(new e(liVar, oiVar, callable));
        } catch (Exception e2) {
            oiVar.a((Exception) new ExecutorException(e2));
        }
        return oiVar.a();
    }

    public static <TResult> ni<TResult> b(Exception exc) {
        oi oiVar = new oi();
        oiVar.a(exc);
        return oiVar.a();
    }

    public <TContinuationResult> ni<TContinuationResult> b(mi<TResult, ni<TContinuationResult>> miVar, Executor executor, li liVar) {
        boolean zD;
        oi oiVar = new oi();
        synchronized (this.f8523a) {
            zD = d();
            if (!zD) {
                this.h.add(new b(this, oiVar, miVar, executor, liVar));
            }
        }
        if (zD) {
            c(oiVar, miVar, this, executor, liVar);
        }
        return oiVar.a();
    }

    public <TContinuationResult> ni<TContinuationResult> a(mi<TResult, TContinuationResult> miVar, Executor executor, li liVar) {
        boolean zD;
        oi oiVar = new oi();
        synchronized (this.f8523a) {
            zD = d();
            if (!zD) {
                this.h.add(new a(this, oiVar, miVar, executor, liVar));
            }
        }
        if (zD) {
            d(oiVar, miVar, this, executor, liVar);
        }
        return oiVar.a();
    }

    public <TContinuationResult> ni<TContinuationResult> b(mi<TResult, ni<TContinuationResult>> miVar) {
        return b(miVar, j, null);
    }

    public <TContinuationResult> ni<TContinuationResult> a(mi<TResult, TContinuationResult> miVar) {
        return a(miVar, j, (li) null);
    }

    public boolean a(TResult tresult) {
        synchronized (this.f8523a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.d = tresult;
            this.f8523a.notifyAll();
            f();
            return true;
        }
    }

    public boolean a(Exception exc) {
        synchronized (this.f8523a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.f8524e = exc;
            this.f = false;
            this.f8523a.notifyAll();
            f();
            if (!this.f && h() != null) {
                this.g = new pi(this);
            }
            return true;
        }
    }
}
