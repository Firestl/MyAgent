package supwisdom;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: NoneCacheRequestPolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public class pv0<T> extends kv0<T> {

    /* JADX INFO: compiled from: NoneCacheRequestPolicy.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8838a;

        public a(aw0 aw0Var) {
            this.f8838a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            pv0.this.f.b(this.f8838a);
            pv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: NoneCacheRequestPolicy.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8839a;

        public b(aw0 aw0Var) {
            this.f8839a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            pv0.this.f.a(this.f8839a);
            pv0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: NoneCacheRequestPolicy.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CacheEntity f8840a;

        public c(CacheEntity cacheEntity) {
            this.f8840a = cacheEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            pv0 pv0Var = pv0.this;
            pv0Var.f.a(pv0Var.f8209a);
            try {
                pv0.this.b();
                CacheEntity cacheEntity = this.f8840a;
                if (cacheEntity == null) {
                    pv0.this.c();
                    return;
                }
                pv0.this.f.c(aw0.a(true, cacheEntity.getData(), pv0.this.f8210e, (dt1) null));
                pv0.this.f.a();
            } catch (Throwable th) {
                pv0.this.f.a(aw0.a(false, pv0.this.f8210e, (dt1) null, th));
            }
        }
    }

    public pv0(Request<T, ? extends Request> request) {
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
