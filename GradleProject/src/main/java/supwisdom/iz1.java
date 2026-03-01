package supwisdom;

import supwisdom.pw1;
import supwisdom.rw1;
import supwisdom.vw1;

/* JADX INFO: compiled from: RxJavaHooks.java */
/* JADX INFO: loaded from: classes3.dex */
public final class iz1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile bx1<Throwable> f8002a;
    public static volatile fx1<rw1.a, rw1.a> b;
    public static volatile fx1<vw1.a, vw1.a> c;
    public static volatile fx1<pw1.d, pw1.d> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile gx1<rw1, rw1.a, rw1.a> f8003e;
    public static volatile fx1<uw1, uw1> f;
    public static volatile fx1<ax1, ax1> g;
    public static volatile fx1<yw1, yw1> h;
    public static volatile fx1<Throwable, Throwable> i;
    public static volatile fx1<rw1.b, rw1.b> j;

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class a implements fx1<Throwable, Throwable> {
        public Throwable a(Throwable th) {
            lz1.f().e().a(th);
            return th;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ Throwable call(Throwable th) {
            Throwable th2 = th;
            a(th2);
            return th2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class b implements fx1<rw1.b, rw1.b> {
        public rw1.b a(rw1.b bVar) {
            lz1.f().e().a(bVar);
            return bVar;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ rw1.b call(rw1.b bVar) {
            rw1.b bVar2 = bVar;
            a(bVar2);
            return bVar2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class c implements fx1<Throwable, Throwable> {
        public Throwable a(Throwable th) {
            lz1.f().a().a(th);
            return th;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ Throwable call(Throwable th) {
            Throwable th2 = th;
            a(th2);
            return th2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class d implements fx1<pw1.e, pw1.e> {
        public pw1.e a(pw1.e eVar) {
            lz1.f().a().a(eVar);
            return eVar;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ pw1.e call(pw1.e eVar) {
            pw1.e eVar2 = eVar;
            a(eVar2);
            return eVar2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class e implements fx1<rw1.a, rw1.a> {
        public rw1.a a(rw1.a aVar) {
            lz1.f().c().a(aVar);
            return aVar;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ rw1.a call(rw1.a aVar) {
            rw1.a aVar2 = aVar;
            a(aVar2);
            return aVar2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class f implements fx1<vw1.a, vw1.a> {
        public vw1.a a(vw1.a aVar) {
            lz1.f().e().a(aVar);
            return aVar;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ vw1.a call(vw1.a aVar) {
            vw1.a aVar2 = aVar;
            a(aVar2);
            return aVar2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class g implements fx1<pw1.d, pw1.d> {
        public pw1.d a(pw1.d dVar) {
            lz1.f().a().a(dVar);
            return dVar;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ pw1.d call(pw1.d dVar) {
            pw1.d dVar2 = dVar;
            a(dVar2);
            return dVar2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class h implements bx1<Throwable> {
        @Override // supwisdom.bx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void call(Throwable th) {
            lz1.f().b().a(th);
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class i implements gx1<rw1, rw1.a, rw1.a> {
        @Override // supwisdom.gx1
        public /* bridge */ /* synthetic */ rw1.a a(rw1 rw1Var, rw1.a aVar) {
            rw1.a aVar2 = aVar;
            a2(rw1Var, aVar2);
            return aVar2;
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public rw1.a a2(rw1 rw1Var, rw1.a aVar) {
            lz1.f().c().a(rw1Var, aVar);
            return aVar;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class j implements fx1<yw1, yw1> {
        public yw1 a(yw1 yw1Var) {
            lz1.f().c().a(yw1Var);
            return yw1Var;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ yw1 call(yw1 yw1Var) {
            yw1 yw1Var2 = yw1Var;
            a(yw1Var2);
            return yw1Var2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class k implements gx1<vw1, vw1.a, vw1.a> {
        @Override // supwisdom.gx1
        public vw1.a a(vw1 vw1Var, vw1.a aVar) {
            nz1 nz1VarE = lz1.f().e();
            if (nz1VarE == oz1.a()) {
                return aVar;
            }
            sx1 sx1Var = new sx1(aVar);
            nz1VarE.a(vw1Var, sx1Var);
            return new qx1(sx1Var);
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class l implements fx1<yw1, yw1> {
        public yw1 a(yw1 yw1Var) {
            lz1.f().e().a(yw1Var);
            return yw1Var;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ yw1 call(yw1 yw1Var) {
            yw1 yw1Var2 = yw1Var;
            a(yw1Var2);
            return yw1Var2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class m implements gx1<pw1, pw1.d, pw1.d> {
        @Override // supwisdom.gx1
        public /* bridge */ /* synthetic */ pw1.d a(pw1 pw1Var, pw1.d dVar) {
            pw1.d dVar2 = dVar;
            a2(pw1Var, dVar2);
            return dVar2;
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public pw1.d a2(pw1 pw1Var, pw1.d dVar) {
            lz1.f().a().a(pw1Var, dVar);
            return dVar;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class n implements fx1<ax1, ax1> {
        public ax1 a(ax1 ax1Var) {
            lz1.f().d().a(ax1Var);
            return ax1Var;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ ax1 call(ax1 ax1Var) {
            ax1 ax1Var2 = ax1Var;
            a(ax1Var2);
            return ax1Var2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class o implements fx1<Throwable, Throwable> {
        public Throwable a(Throwable th) {
            lz1.f().c().a(th);
            return th;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ Throwable call(Throwable th) {
            Throwable th2 = th;
            a(th2);
            return th2;
        }
    }

    /* JADX INFO: compiled from: RxJavaHooks.java */
    public static class p implements fx1<rw1.b, rw1.b> {
        public rw1.b a(rw1.b bVar) {
            lz1.f().c().a(bVar);
            return bVar;
        }

        @Override // supwisdom.fx1
        public /* bridge */ /* synthetic */ rw1.b call(rw1.b bVar) {
            rw1.b bVar2 = bVar;
            a(bVar2);
            return bVar2;
        }
    }

    static {
        a();
    }

    public static void a() {
        f8002a = new h();
        f8003e = new i();
        h = new j();
        new k();
        new l();
        new m();
        g = new n();
        i = new o();
        j = new p();
        new a();
        new b();
        new c();
        new d();
        b();
    }

    public static void b() {
        b = new e();
        c = new f();
        d = new g();
    }

    public static void c(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }

    public static Throwable b(Throwable th) {
        fx1<Throwable, Throwable> fx1Var = i;
        return fx1Var != null ? fx1Var.call(th) : th;
    }

    public static void a(Throwable th) {
        bx1<Throwable> bx1Var = f8002a;
        if (bx1Var != null) {
            try {
                bx1Var.call(th);
                return;
            } catch (Throwable th2) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + th2.getMessage());
                th2.printStackTrace();
                c(th2);
            }
        }
        c(th);
    }

    public static <T> rw1.a<T> a(rw1.a<T> aVar) {
        fx1<rw1.a, rw1.a> fx1Var = b;
        return fx1Var != null ? fx1Var.call(aVar) : aVar;
    }

    public static <T> vw1.a<T> a(vw1.a<T> aVar) {
        fx1<vw1.a, vw1.a> fx1Var = c;
        return fx1Var != null ? fx1Var.call(aVar) : aVar;
    }

    public static pw1.d a(pw1.d dVar) {
        fx1<pw1.d, pw1.d> fx1Var = d;
        return fx1Var != null ? fx1Var.call(dVar) : dVar;
    }

    public static uw1 a(uw1 uw1Var) {
        fx1<uw1, uw1> fx1Var = f;
        return fx1Var != null ? fx1Var.call(uw1Var) : uw1Var;
    }

    public static ax1 a(ax1 ax1Var) {
        fx1<ax1, ax1> fx1Var = g;
        return fx1Var != null ? fx1Var.call(ax1Var) : ax1Var;
    }

    public static <T> rw1.a<T> a(rw1<T> rw1Var, rw1.a<T> aVar) {
        gx1<rw1, rw1.a, rw1.a> gx1Var = f8003e;
        return gx1Var != null ? gx1Var.a(rw1Var, aVar) : aVar;
    }

    public static yw1 a(yw1 yw1Var) {
        fx1<yw1, yw1> fx1Var = h;
        return fx1Var != null ? fx1Var.call(yw1Var) : yw1Var;
    }

    public static <T, R> rw1.b<R, T> a(rw1.b<R, T> bVar) {
        fx1<rw1.b, rw1.b> fx1Var = j;
        return fx1Var != null ? fx1Var.call(bVar) : bVar;
    }
}
