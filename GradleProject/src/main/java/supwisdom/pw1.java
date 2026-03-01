package supwisdom;

/* JADX INFO: compiled from: Completable.java */
/* JADX INFO: loaded from: classes3.dex */
public class pw1 {

    /* JADX INFO: compiled from: Completable.java */
    public static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ rw1 f8844a;

        /* JADX INFO: renamed from: supwisdom.pw1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Completable.java */
        public class C0225a extends xw1<Object> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ qw1 f8845a;

            public C0225a(a aVar, qw1 qw1Var) {
                this.f8845a = qw1Var;
            }

            @Override // supwisdom.sw1
            public void onCompleted() {
                this.f8845a.onCompleted();
            }

            @Override // supwisdom.sw1
            public void onError(Throwable th) {
                this.f8845a.onError(th);
            }

            @Override // supwisdom.sw1
            public void onNext(Object obj) {
            }
        }

        public a(rw1 rw1Var) {
            this.f8844a = rw1Var;
        }

        @Override // supwisdom.bx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void call(qw1 qw1Var) {
            C0225a c0225a = new C0225a(this, qw1Var);
            qw1Var.a(c0225a);
            this.f8844a.b(c0225a);
        }
    }

    /* JADX INFO: compiled from: Completable.java */
    public static class b implements d {
        @Override // supwisdom.bx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void call(qw1 qw1Var) {
            qw1Var.a(tz1.a());
            qw1Var.onCompleted();
        }
    }

    /* JADX INFO: compiled from: Completable.java */
    public static class c implements d {
        @Override // supwisdom.bx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void call(qw1 qw1Var) {
            qw1Var.a(tz1.a());
        }
    }

    /* JADX INFO: compiled from: Completable.java */
    public interface d extends bx1<qw1> {
    }

    /* JADX INFO: compiled from: Completable.java */
    public interface e extends fx1<qw1, qw1> {
    }

    static {
        new pw1(new b(), false);
        new pw1(new c(), false);
    }

    public pw1(d dVar) {
        iz1.a(dVar);
    }

    public static pw1 a(d dVar) {
        a(dVar);
        try {
            return new pw1(dVar);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            iz1.a(th);
            throw a(th);
        }
    }

    public pw1(d dVar, boolean z) {
        if (z) {
            iz1.a(dVar);
        }
    }

    public static pw1 a(rw1<?> rw1Var) {
        a(rw1Var);
        return a((d) new a(rw1Var));
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static NullPointerException a(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }
}
