package supwisdom;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import supwisdom.zs1;
import supwisdom.zv0;

/* JADX INFO: compiled from: OkGo.java */
/* JADX INFO: loaded from: classes2.dex */
public class fv0 {
    public static long i = 300;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Application f7649a;
    public Handler b;
    public zs1 c;
    public HttpParams d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HttpHeaders f7650e;
    public int f;
    public CacheMode g;
    public long h;

    /* JADX INFO: compiled from: OkGo.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static fv0 f7651a = new fv0();
    }

    public static fv0 i() {
        return b.f7651a;
    }

    public CacheMode a() {
        return this.g;
    }

    public long b() {
        return this.h;
    }

    public HttpHeaders c() {
        return this.f7650e;
    }

    public HttpParams d() {
        return this.d;
    }

    public Context e() {
        dw0.a(this.f7649a, "please call OkGo.getInstance().init() first in application!");
        return this.f7649a;
    }

    public Handler f() {
        return this.b;
    }

    public zs1 g() {
        dw0.a(this.c, "please call OkGo.getInstance().setOkHttpClient() first in application!");
        return this.c;
    }

    public int h() {
        return this.f;
    }

    public fv0() {
        this.b = new Handler(Looper.getMainLooper());
        this.f = 3;
        this.h = -1L;
        this.g = CacheMode.NO_CACHE;
        zs1.b bVar = new zs1.b();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("OkGo");
        httpLoggingInterceptor.a(HttpLoggingInterceptor.Level.BODY);
        httpLoggingInterceptor.a(Level.INFO);
        bVar.a(httpLoggingInterceptor);
        bVar.c(60000L, TimeUnit.MILLISECONDS);
        bVar.d(60000L, TimeUnit.MILLISECONDS);
        bVar.a(60000L, TimeUnit.MILLISECONDS);
        zv0.c cVarA = zv0.a();
        bVar.a(cVarA.f10037a, cVarA.b);
        bVar.a(zv0.b);
        this.c = bVar.a();
    }
}
