package supwisdom;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: NoCachePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public class ov0<T> extends kv0<T> {

    /* JADX INFO: compiled from: NoCachePolicy.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8721a;

        public a(aw0 aw0Var) {
            this.f8721a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            ov0.this.f.b(this.f8721a);
            ov0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: NoCachePolicy.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ aw0 f8722a;

        public b(aw0 aw0Var) {
            this.f8722a = aw0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            ov0.this.f.a(this.f8722a);
            ov0.this.f.a();
        }
    }

    /* JADX INFO: compiled from: NoCachePolicy.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ov0 ov0Var = ov0.this;
            ov0Var.f.a(ov0Var.f8209a);
            try {
                ov0.this.b();
                ov0.this.c();
            } catch (Throwable th) {
                ov0.this.f.a(aw0.a(false, ov0.this.f8210e, (dt1) null, th));
            }
        }
    }

    public ov0(Request<T, ? extends Request> request) {
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
        a(new c());
    }
}
