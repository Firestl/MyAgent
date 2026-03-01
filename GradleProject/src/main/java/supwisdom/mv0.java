package supwisdom;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.exception.CacheException;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: DefaultCachePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public class mv0<T> extends kv0<T> {

    /* JADX INFO: compiled from: DefaultCachePolicy.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8439a;

        public a(aw0 aw0Var) {
            this.f8439a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            mv0.this.f.b(this.f8439a);
            mv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: DefaultCachePolicy.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8440a;

        public b(aw0 aw0Var) {
            this.f8440a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            mv0.this.f.a(this.f8440a);
            mv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: DefaultCachePolicy.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8441a;

        public c(aw0 aw0Var) {
            this.f8441a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            mv0.this.f.a(this.f8441a);
            mv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: DefaultCachePolicy.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8442a;

        public d(aw0 aw0Var) {
            this.f8442a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            mv0.this.f.c(this.f8442a);
            mv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: DefaultCachePolicy.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            mv0 mv0Var = mv0.this;
            mv0Var.f.a(mv0Var.f8209a);
            try {
                mv0.this.b();
                mv0.this.c();
            } catch (Throwable th) {
                mv0.this.f.a(aw0.a(false, mv0.this.f8210e, (dt1) null, th));
            }
        }
    }

    public mv0(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // supwisdom.lv0
    public void a(aw0<T> aw0Var) {
        a(new b(aw0Var));
    }

    @Override // supwisdom.lv0
    public void b(aw0<T> aw0Var) {
        a(new a(aw0Var));
    }

    @Override // supwisdom.kv0
    public boolean a(gs1 gs1Var, dt1 dt1Var) {
        if (dt1Var.c() != 304) {
            return false;
        }
        CacheEntity<T> cacheEntity = this.g;
        if (cacheEntity == null) {
            a(new c(aw0.a(true, gs1Var, dt1Var, (Throwable) CacheException.NON_AND_304(this.f8209a.getCacheKey()))));
        } else {
            a(new d(aw0.a(true, (Object) cacheEntity.getData(), gs1Var, dt1Var)));
        }
        return true;
    }

    @Override // supwisdom.lv0
    public void a(CacheEntity<T> cacheEntity, rv0<T> rv0Var) {
        this.f = rv0Var;
        a(new e());
    }
}
