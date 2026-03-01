package supwisdom;

import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: CacheCall.java */
/* JADX INFO: loaded from: classes2.dex */
public class hv0<T> implements iv0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public lv0<T> f7878a;
    public Request<T, ? extends Request> b;

    /* JADX INFO: compiled from: CacheCall.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7879a;

        static {
            int[] iArr = new int[CacheMode.values().length];
            f7879a = iArr;
            try {
                iArr[CacheMode.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7879a[CacheMode.NO_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7879a[CacheMode.IF_NONE_CACHE_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7879a[CacheMode.FIRST_CACHE_THEN_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7879a[CacheMode.REQUEST_FAILED_READ_CACHE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public hv0(Request<T, ? extends Request> request) {
        this.f7878a = null;
        this.b = request;
        this.f7878a = a();
    }

    @Override // supwisdom.iv0
    public void a(rv0<T> rv0Var) {
        dw0.a(rv0Var, "callback == null");
        this.f7878a.a(this.f7878a.a(), rv0Var);
    }

    public iv0<T> clone() {
        return new hv0(this.b);
    }

    public final lv0<T> a() {
        int i = a.f7879a[this.b.getCacheMode().ordinal()];
        if (i == 1) {
            this.f7878a = new mv0(this.b);
        } else if (i == 2) {
            this.f7878a = new ov0(this.b);
        } else if (i == 3) {
            this.f7878a = new pv0(this.b);
        } else if (i == 4) {
            this.f7878a = new nv0(this.b);
        } else if (i == 5) {
            this.f7878a = new qv0(this.b);
        }
        if (this.b.getCachePolicy() != null) {
            this.f7878a = this.b.getCachePolicy();
        }
        dw0.a(this.f7878a, "policy == null");
        return this.f7878a;
    }
}
