package supwisdom;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class x3 extends z3 {
    public static volatile x3 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z3 f9704a;
    public z3 b;

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            x3.b().b(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            x3.b().a(runnable);
        }
    }

    static {
        new a();
        new b();
    }

    public x3() {
        y3 y3Var = new y3();
        this.b = y3Var;
        this.f9704a = y3Var;
    }

    public static x3 b() {
        if (c != null) {
            return c;
        }
        synchronized (x3.class) {
            if (c == null) {
                c = new x3();
            }
        }
        return c;
    }

    @Override // supwisdom.z3
    public void a(Runnable runnable) {
        this.f9704a.a(runnable);
    }

    @Override // supwisdom.z3
    public boolean a() {
        return this.f9704a.a();
    }

    @Override // supwisdom.z3
    public void b(Runnable runnable) {
        this.f9704a.b(runnable);
    }
}
