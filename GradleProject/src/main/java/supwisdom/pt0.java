package supwisdom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Incorrect class signature: super class is equals to this class */
/* JADX INFO: loaded from: classes.dex */
public final class pt0<TResult> {
    public boolean b;
    public TResult c;
    public Exception d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8828a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<eu0<TResult>> f8829e = new ArrayList();

    public final void a() {
        synchronized (this.f8828a) {
            Iterator<eu0<TResult>> it = this.f8829e.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.f8829e = null;
        }
    }

    public final Exception b() {
        Exception exc;
        synchronized (this.f8828a) {
            exc = this.d;
        }
        return exc;
    }

    public final TResult c() {
        TResult tresult;
        synchronized (this.f8828a) {
            if (this.d != null) {
                throw new RuntimeException(this.d);
            }
            tresult = this.c;
        }
        return tresult;
    }

    public final boolean d() {
        synchronized (this.f8828a) {
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f8828a
            monitor-enter(r0)
            boolean r1 = r2.b     // Catch: java.lang.Throwable -> L13
            if (r1 == 0) goto L10
            r2.d()     // Catch: java.lang.Throwable -> L13
            java.lang.Exception r1 = r2.d     // Catch: java.lang.Throwable -> L13
            if (r1 != 0) goto L10
            r1 = 1
            goto L11
        L10:
            r1 = 0
        L11:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L13
            return r1
        L13:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L13
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.pt0.e():boolean");
    }

    public final pt0<TResult> a(eu0<TResult> eu0Var) {
        synchronized (this.f8828a) {
            if (!this.b) {
                this.f8829e.add(eu0Var);
            } else {
                eu0Var.a(this);
            }
        }
        return this;
    }
}
