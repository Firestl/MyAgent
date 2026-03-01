package supwisdom;

import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import supwisdom.mq1;

/* JADX INFO: compiled from: ForkJoinWorkerThread.java */
/* JADX INFO: loaded from: classes3.dex */
public class nq1 extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final mq1 f8555a;
    public final mq1.f b;

    /* JADX INFO: compiled from: ForkJoinWorkerThread.java */
    public static final class a extends nq1 {
        public static final ThreadGroup c = (ThreadGroup) AccessController.doPrivileged(new C0221a());
        public static final AccessControlContext d = new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, null)});

        /* JADX INFO: renamed from: supwisdom.nq1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ForkJoinWorkerThread.java */
        public static class C0221a implements PrivilegedAction<ThreadGroup> {
            @Override // java.security.PrivilegedAction
            public ThreadGroup run() {
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                while (true) {
                    ThreadGroup parent = threadGroup.getParent();
                    if (parent == null) {
                        return new ThreadGroup(threadGroup, "InnocuousForkJoinWorkerThreadGroup");
                    }
                    threadGroup = parent;
                }
            }
        }

        public a(mq1 mq1Var) {
            super(mq1Var, ClassLoader.getSystemClassLoader(), c, d);
        }

        @Override // supwisdom.nq1
        public void a() {
            pq1.a(this);
        }

        @Override // java.lang.Thread
        public void setContextClassLoader(ClassLoader classLoader) {
            throw new SecurityException("setContextClassLoader");
        }

        @Override // java.lang.Thread
        public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        }
    }

    public nq1(mq1 mq1Var, ClassLoader classLoader) {
        super("aForkJoinWorkerThread");
        pq1.a(this, classLoader);
        this.f8555a = mq1Var;
        this.b = mq1Var.a(this);
    }

    public void a() {
    }

    public void a(Throwable th) {
    }

    public void b() {
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        if (this.b.h == null) {
            Throwable th = null;
            try {
                b();
                this.f8555a.c(this.b);
                try {
                    a(null);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    a(th);
                } catch (Throwable unused) {
                }
            }
            this.f8555a.a(this, th);
        }
    }

    public nq1(mq1 mq1Var, ClassLoader classLoader, ThreadGroup threadGroup, AccessControlContext accessControlContext) {
        super(threadGroup, "aForkJoinWorkerThread");
        super.setContextClassLoader(classLoader);
        pq1.a(this, accessControlContext);
        pq1.a(this);
        this.f8555a = mq1Var;
        this.b = mq1Var.a(this);
    }
}
