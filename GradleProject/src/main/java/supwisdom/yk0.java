package supwisdom;

import com.google.android.gms.tasks.DuplicateTaskCompletionException;
import com.google.android.gms.tasks.RuntimeExecutionException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.2.0 */
/* JADX INFO: loaded from: classes.dex */
public final class yk0<TResult> extends qk0<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9890a = new Object();
    public final vk0<TResult> b = new vk0<>();

    @GuardedBy("mLock")
    public boolean c;
    public volatile boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @GuardedBy("mLock")
    public TResult f9891e;

    @GuardedBy("mLock")
    public Exception f;

    @Override // supwisdom.qk0
    public final Exception a() {
        Exception exc;
        synchronized (this.f9890a) {
            exc = this.f;
        }
        return exc;
    }

    @Override // supwisdom.qk0
    public final TResult b() {
        TResult tresult;
        synchronized (this.f9890a) {
            f();
            h();
            if (this.f != null) {
                throw new RuntimeExecutionException(this.f);
            }
            tresult = this.f9891e;
        }
        return tresult;
    }

    @Override // supwisdom.qk0
    public final boolean c() {
        return this.d;
    }

    @Override // supwisdom.qk0
    public final boolean d() {
        boolean z;
        synchronized (this.f9890a) {
            z = this.c;
        }
        return z;
    }

    @Override // supwisdom.qk0
    public final boolean e() {
        boolean z;
        synchronized (this.f9890a) {
            z = this.c && !this.d && this.f == null;
        }
        return z;
    }

    @GuardedBy("mLock")
    public final void f() {
        pf0.b(this.c, "Task is not yet complete");
    }

    @GuardedBy("mLock")
    public final void g() {
        if (this.c) {
            throw DuplicateTaskCompletionException.of(this);
        }
    }

    @GuardedBy("mLock")
    public final void h() {
        if (this.d) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    public final void i() {
        synchronized (this.f9890a) {
            if (this.c) {
                this.b.a(this);
            }
        }
    }

    @Override // supwisdom.qk0
    public final qk0<TResult> a(pk0<TResult> pk0Var) {
        a(sk0.f9184a, pk0Var);
        return this;
    }

    @Override // supwisdom.qk0
    public final qk0<TResult> a(Executor executor, pk0<TResult> pk0Var) {
        vk0<TResult> vk0Var = this.b;
        zk0.a(executor);
        vk0Var.a(new tk0(executor, pk0Var));
        i();
        return this;
    }

    public final boolean b(TResult tresult) {
        synchronized (this.f9890a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.f9891e = tresult;
            this.b.a(this);
            return true;
        }
    }

    public final void a(TResult tresult) {
        synchronized (this.f9890a) {
            g();
            this.c = true;
            this.f9891e = tresult;
        }
        this.b.a(this);
    }

    public final void a(Exception exc) {
        pf0.a(exc, "Exception must not be null");
        synchronized (this.f9890a) {
            g();
            this.c = true;
            this.f = exc;
        }
        this.b.a(this);
    }

    public final boolean b(Exception exc) {
        pf0.a(exc, "Exception must not be null");
        synchronized (this.f9890a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.f = exc;
            this.b.a(this);
            return true;
        }
    }
}
