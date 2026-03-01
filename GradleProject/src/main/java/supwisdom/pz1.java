package supwisdom;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pz1 {
    public static final AtomicReference<pz1> d = new AtomicReference<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uw1 f8854a;
    public final uw1 b;
    public final uw1 c;

    public pz1() {
        mz1 mz1VarD = lz1.f().d();
        uw1 uw1VarA = mz1VarD.a();
        if (uw1VarA != null) {
            this.f8854a = uw1VarA;
        } else {
            this.f8854a = mz1.d();
        }
        uw1 uw1VarB = mz1VarD.b();
        if (uw1VarB != null) {
            this.b = uw1VarB;
        } else {
            this.b = mz1.e();
        }
        uw1 uw1VarC = mz1VarD.c();
        if (uw1VarC != null) {
            this.c = uw1VarC;
        } else {
            this.c = mz1.f();
        }
    }

    public static pz1 b() {
        while (true) {
            pz1 pz1Var = d.get();
            if (pz1Var != null) {
                return pz1Var;
            }
            pz1 pz1Var2 = new pz1();
            if (d.compareAndSet(null, pz1Var2)) {
                return pz1Var2;
            }
            pz1Var2.a();
        }
    }

    public static uw1 c() {
        return iz1.a(b().b);
    }

    public synchronized void a() {
        if (this.f8854a instanceof yx1) {
            ((yx1) this.f8854a).shutdown();
        }
        if (this.b instanceof yx1) {
            ((yx1) this.b).shutdown();
        }
        if (this.c instanceof yx1) {
            ((yx1) this.c).shutdown();
        }
    }
}
