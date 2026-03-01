package supwisdom;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: RequestFailedCachePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public class qv0<T> extends kv0<T> {

    /* JADX INFO: compiled from: RequestFailedCachePolicy.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8958a;

        public a(aw0 aw0Var) {
            this.f8958a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            qv0.this.f.b(this.f8958a);
            qv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: RequestFailedCachePolicy.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8959a;

        public b(aw0 aw0Var) {
            this.f8959a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            qv0.this.f.c(this.f8959a);
            qv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: RequestFailedCachePolicy.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8960a;

        public c(aw0 aw0Var) {
            this.f8960a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            qv0.this.f.a(this.f8960a);
            qv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: RequestFailedCachePolicy.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            qv0 qv0Var = qv0.this;
            qv0Var.f.a(qv0Var.f8209a);
            try {
                qv0.this.b();
                qv0.this.c();
            } catch (Throwable th) {
                qv0.this.f.a(aw0.a(false, qv0.this.f8210e, (dt1) null, th));
            }
        }
    }

    public qv0(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // supwisdom.lv0
    public void a(aw0<T> aw0Var) {
        CacheEntity<T> cacheEntity = this.g;
        if (cacheEntity != null) {
            a(new b(aw0.a(true, (Object) cacheEntity.getData(), aw0Var.b(), aw0Var.c())));
        } else {
            a(new c(aw0Var));
        }
    }

    @Override // supwisdom.lv0
    public void b(aw0<T> aw0Var) {
        a(new a(aw0Var));
    }

    @Override // supwisdom.lv0
    public void a(CacheEntity<T> cacheEntity, rv0<T> rv0Var) {
        this.f = rv0Var;
        a(new d());
    }
}
