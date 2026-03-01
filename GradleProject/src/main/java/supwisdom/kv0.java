package supwisdom;

import android.graphics.Bitmap;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import java.io.IOException;
import java.net.SocketTimeoutException;

/* JADX INFO: compiled from: BaseCachePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class kv0<T> implements lv0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Request<T, ? extends Request> f8209a;
    public volatile boolean b;
    public volatile int c = 0;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public gs1 f8210e;
    public rv0<T> f;
    public CacheEntity<T> g;

    /* JADX INFO: compiled from: BaseCachePolicy.java */
    public class a implements hs1 {
        public a() {
        }

        @Override // supwisdom.hs1
        public void onFailure(gs1 gs1Var, IOException iOException) {
            if (!(iOException instanceof SocketTimeoutException) || kv0.this.c >= kv0.this.f8209a.getRetryCount()) {
                if (gs1Var.isCanceled()) {
                    return;
                }
                kv0.this.a(aw0.a(false, gs1Var, (dt1) null, (Throwable) iOException));
                return;
            }
            kv0.this.c++;
            kv0 kv0Var = kv0.this;
            kv0Var.f8210e = kv0Var.f8209a.getRawCall();
            if (kv0.this.b) {
                kv0.this.f8210e.cancel();
            } else {
                kv0.this.f8210e.a(this);
            }
        }

        @Override // supwisdom.hs1
        public void onResponse(gs1 gs1Var, dt1 dt1Var) throws IOException {
            int iC = dt1Var.c();
            if (iC == 404 || iC >= 500) {
                kv0.this.a(aw0.a(false, gs1Var, dt1Var, (Throwable) HttpException.NET_ERROR()));
            } else {
                if (kv0.this.a(gs1Var, dt1Var)) {
                    return;
                }
                try {
                    T tA = kv0.this.f8209a.getConverter().a(dt1Var);
                    kv0.this.a(dt1Var.e(), tA);
                    kv0.this.b(aw0.a(false, (Object) tA, gs1Var, dt1Var));
                } catch (Throwable th) {
                    kv0.this.a(aw0.a(false, gs1Var, dt1Var, th));
                }
            }
        }
    }

    public kv0(Request<T, ? extends Request> request) {
        this.f8209a = request;
    }

    public boolean a(gs1 gs1Var, dt1 dt1Var) {
        return false;
    }

    public synchronized gs1 b() throws Throwable {
        if (this.d) {
            throw HttpException.COMMON("Already executed!");
        }
        this.d = true;
        this.f8210e = this.f8209a.getRawCall();
        if (this.b) {
            this.f8210e.cancel();
        }
        return this.f8210e;
    }

    public void c() {
        this.f8210e.a(new a());
    }

    @Override // supwisdom.lv0
    public CacheEntity<T> a() {
        if (this.f8209a.getCacheKey() == null) {
            Request<T, ? extends Request> request = this.f8209a;
            request.cacheKey(dw0.a(request.getBaseUrl(), this.f8209a.getParams().urlParamsMap));
        }
        if (this.f8209a.getCacheMode() == null) {
            this.f8209a.cacheMode(CacheMode.NO_CACHE);
        }
        CacheMode cacheMode = this.f8209a.getCacheMode();
        if (cacheMode != CacheMode.NO_CACHE) {
            CacheEntity<T> cacheEntity = (CacheEntity<T>) uv0.c().a(this.f8209a.getCacheKey());
            this.g = cacheEntity;
            cw0.a(this.f8209a, cacheEntity, cacheMode);
            CacheEntity<T> cacheEntity2 = this.g;
            if (cacheEntity2 != null && cacheEntity2.checkExpire(cacheMode, this.f8209a.getCacheTime(), System.currentTimeMillis())) {
                this.g.setExpire(true);
            }
        }
        CacheEntity<T> cacheEntity3 = this.g;
        if (cacheEntity3 == null || cacheEntity3.isExpire() || this.g.getData() == null || this.g.getResponseHeaders() == null) {
            this.g = null;
        }
        return this.g;
    }

    public final void a(us1 us1Var, T t) {
        if (this.f8209a.getCacheMode() == CacheMode.NO_CACHE || (t instanceof Bitmap)) {
            return;
        }
        CacheEntity<T> cacheEntityA = cw0.a(us1Var, t, this.f8209a.getCacheMode(), this.f8209a.getCacheKey());
        if (cacheEntityA == null) {
            uv0.c().b(this.f8209a.getCacheKey());
        } else {
            uv0.c().a(this.f8209a.getCacheKey(), cacheEntityA);
        }
    }

    public void a(Runnable runnable) {
        fv0.i().f().post(runnable);
    }
}
