package supwisdom;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: FirstCacheRequestPolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public class nv0<T> extends kv0<T> {

    /* JADX INFO: compiled from: FirstCacheRequestPolicy.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8580a;

        public a(aw0 aw0Var) {
            this.f8580a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            nv0.this.f.b(this.f8580a);
            nv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: FirstCacheRequestPolicy.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8581a;

        public b(aw0 aw0Var) {
            this.f8581a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            nv0.this.f.a(this.f8581a);
            nv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: FirstCacheRequestPolicy.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CacheEntity f8582a;

        public c(CacheEntity cacheEntity) {
            this.f8582a = cacheEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            nv0 nv0Var = nv0.this;
            nv0Var.f.a(nv0Var.f8209a);
            try {
                nv0.this.b();
                CacheEntity cacheEntity = this.f8582a;
                if (cacheEntity != null) {
                    nv0.this.f.c(aw0.a(true, cacheEntity.getData(), nv0.this.f8210e, (dt1) null));
                }
                nv0.this.c();
            } catch (Throwable th) {
                nv0.this.f.a(aw0.a(false, nv0.this.f8210e, (dt1) null, th));
            }
        }
    }

    public nv0(Request<T, ? extends Request> request) {
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

    @Override // supwisdom.lv0
    public void a(CacheEntity<T> cacheEntity, rv0<T> rv0Var) {
        this.f = rv0Var;
        a(new c(cacheEntity));
    }
}
