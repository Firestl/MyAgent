package supwisdom;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okio.AsyncTimeout;
import okio.Timeout;

/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes3.dex */
public final class at1 implements gs1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zs1 f6977a;
    public final fu1 b;
    public final AsyncTimeout c;

    @Nullable
    public rs1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final bt1 f6978e;
    public final boolean f;
    public boolean g;

    /* JADX INFO: compiled from: RealCall.java */
    public class a extends AsyncTimeout {
        public a() {
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            at1.this.cancel();
        }
    }

    public at1(zs1 zs1Var, bt1 bt1Var, boolean z) {
        this.f6977a = zs1Var;
        this.f6978e = bt1Var;
        this.f = z;
        this.b = new fu1(zs1Var, z);
        a aVar = new a();
        this.c = aVar;
        aVar.timeout(zs1Var.b(), TimeUnit.MILLISECONDS);
    }

    public dt1 b() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f6977a.n());
        arrayList.add(this.b);
        arrayList.add(new wt1(this.f6977a.g()));
        arrayList.add(new mt1(this.f6977a.o()));
        arrayList.add(new qt1(this.f6977a));
        if (!this.f) {
            arrayList.addAll(this.f6977a.p());
        }
        arrayList.add(new xt1(this.f));
        return new cu1(arrayList, null, null, null, 0, this.f6978e, this, this.d, this.f6977a.d(), this.f6977a.w(), this.f6977a.A()).a(this.f6978e);
    }

    public String c() {
        return this.f6978e.g().m();
    }

    @Override // supwisdom.gs1
    public void cancel() {
        this.b.a();
    }

    public vt1 d() {
        return this.b.c();
    }

    public String e() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.f ? "web socket" : "call");
        sb.append(" to ");
        sb.append(c());
        return sb.toString();
    }

    @Override // supwisdom.gs1
    public dt1 execute() throws IOException {
        synchronized (this) {
            if (this.g) {
                throw new IllegalStateException("Already Executed");
            }
            this.g = true;
        }
        a();
        this.c.enter();
        this.d.callStart(this);
        try {
            try {
                this.f6977a.h().a(this);
                dt1 dt1VarB = b();
                if (dt1VarB != null) {
                    return dt1VarB;
                }
                throw new IOException("Canceled");
            } catch (IOException e2) {
                IOException iOExceptionA = a(e2);
                this.d.callFailed(this, iOExceptionA);
                throw iOExceptionA;
            }
        } finally {
            this.f6977a.h().b(this);
        }
        this.f6977a.h().b(this);
    }

    @Override // supwisdom.gs1
    public boolean isCanceled() {
        return this.b.b();
    }

    @Override // supwisdom.gs1
    public bt1 request() {
        return this.f6978e;
    }

    @Override // supwisdom.gs1
    public Timeout timeout() {
        return this.c;
    }

    public static at1 a(zs1 zs1Var, bt1 bt1Var, boolean z) {
        at1 at1Var = new at1(zs1Var, bt1Var, z);
        at1Var.d = zs1Var.j().create(at1Var);
        return at1Var;
    }

    public at1 clone() {
        return a(this.f6977a, this.f6978e, this.f);
    }

    @Nullable
    public IOException a(@Nullable IOException iOException) {
        if (!this.c.exit()) {
            return iOException;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* JADX INFO: compiled from: RealCall.java */
    public final class b extends jt1 {
        public final hs1 b;

        public b(hs1 hs1Var) {
            super("OkHttp %s", at1.this.c());
            this.b = hs1Var;
        }

        public void a(ExecutorService executorService) {
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e2) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e2);
                    at1.this.d.callFailed(at1.this, interruptedIOException);
                    this.b.onFailure(at1.this, interruptedIOException);
                    at1.this.f6977a.h().b(this);
                }
            } catch (Throwable th) {
                at1.this.f6977a.h().b(this);
                throw th;
            }
        }

        public at1 b() {
            return at1.this;
        }

        public String c() {
            return at1.this.f6978e.g().g();
        }

        @Override // supwisdom.jt1
        public void a() {
            IOException e2;
            at1.this.c.enter();
            boolean z = true;
            try {
                try {
                    dt1 dt1VarB = at1.this.b();
                    try {
                        if (at1.this.b.b()) {
                            this.b.onFailure(at1.this, new IOException("Canceled"));
                        } else {
                            this.b.onResponse(at1.this, dt1VarB);
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        IOException iOExceptionA = at1.this.a(e2);
                        if (!z) {
                            at1.this.d.callFailed(at1.this, iOExceptionA);
                            this.b.onFailure(at1.this, iOExceptionA);
                        } else {
                            yu1.c().a(4, "Callback failure for " + at1.this.e(), iOExceptionA);
                        }
                    }
                } finally {
                    at1.this.f6977a.h().b(this);
                }
            } catch (IOException e4) {
                e2 = e4;
                z = false;
            }
        }
    }

    public final void a() {
        this.b.a(yu1.c().a("response.body().close()"));
    }

    @Override // supwisdom.gs1
    public void a(hs1 hs1Var) {
        synchronized (this) {
            if (!this.g) {
                this.g = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        a();
        this.d.callStart(this);
        this.f6977a.h().a(new b(hs1Var));
    }
}
