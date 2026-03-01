package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import com.nostra13.dcloudimageloader.core.assist.QueueProcessingType;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public final class ax0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f6992a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6993e;
    public final Bitmap.CompressFormat f;
    public final int g;
    public final yx0 h;
    public final Executor i;
    public final Executor j;
    public final boolean k;
    public final boolean l;
    public final int m;
    public final int n;
    public final QueueProcessingType o;
    public final sw0 p;
    public final jw0 q;
    public final ImageDownloader r;
    public final px0 s;
    public final yw0 t;
    public final boolean u;
    public final jw0 v;
    public final ImageDownloader w;
    public final ImageDownloader x;

    public static class b {
        public static final QueueProcessingType A = QueueProcessingType.FIFO;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f6994a;
        public px0 x;
        public int b = 0;
        public int c = 0;
        public int d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6995e = 0;
        public Bitmap.CompressFormat f = null;
        public int g = 0;
        public yx0 h = null;
        public Executor i = null;
        public Executor j = null;
        public boolean k = false;
        public boolean l = false;
        public int m = 3;
        public int n = 4;
        public boolean o = false;
        public QueueProcessingType p = A;
        public int q = 0;
        public int r = 0;
        public int s = 0;
        public sw0 t = null;
        public jw0 u = null;
        public ow0 v = null;
        public ImageDownloader w = null;
        public yw0 y = null;
        public boolean z = false;

        public b(Context context) {
            this.f6994a = context.getApplicationContext();
        }

        public ax0 a() {
            c();
            return new ax0(this);
        }

        public b b() {
            this.o = true;
            return this;
        }

        public final void c() {
            if (this.i == null) {
                this.i = ww0.a(this.m, this.n, this.p);
            } else {
                this.k = true;
            }
            if (this.j == null) {
                this.j = ww0.a(this.m, this.n, this.p);
            } else {
                this.l = true;
            }
            if (this.u == null) {
                if (this.v == null) {
                    this.v = ww0.b();
                }
                this.u = ww0.a(this.f6994a, this.v, this.r, this.s);
            }
            if (this.t == null) {
                this.t = ww0.a(this.q);
            }
            if (this.o) {
                this.t = new tw0(this.t, jx0.a());
            }
            if (this.w == null) {
                this.w = ww0.a(this.f6994a);
            }
            if (this.x == null) {
                this.x = ww0.a(this.z);
            }
            if (this.y == null) {
                this.y = yw0.t();
            }
        }

        public b d(int i) {
            if (this.i != null || this.j != null) {
                by0.d("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            if (i < 1) {
                this.n = 1;
            } else if (i > 10) {
                this.n = 10;
            } else {
                this.n = i;
            }
            return this;
        }

        public b b(int i) {
            if (i > 0) {
                if (this.t != null) {
                    by0.d("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
                }
                this.q = i;
                return this;
            }
            throw new IllegalArgumentException("memoryCacheSize must be a positive number");
        }

        public b a(yw0 yw0Var) {
            this.y = yw0Var;
            return this;
        }

        public b a(jw0 jw0Var) {
            if (this.r > 0 || this.s > 0) {
                by0.d("discCache(), discCacheSize() and discCacheFileCount calls overlap each other", new Object[0]);
            }
            if (this.v != null) {
                by0.d("discCache() and discCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }
            this.u = jw0Var;
            return this;
        }

        public b a(int i) {
            if (i > 0) {
                if (this.u != null || this.r > 0) {
                    by0.d("discCache(), discCacheSize() and discCacheFileCount calls overlap each other", new Object[0]);
                }
                this.r = 0;
                this.s = i;
                return this;
            }
            throw new IllegalArgumentException("maxFileCount must be a positive number");
        }

        public b a(px0 px0Var) {
            this.x = px0Var;
            return this;
        }

        public b a(ImageDownloader imageDownloader) {
            this.w = imageDownloader;
            return this;
        }

        public b a(sw0 sw0Var) {
            if (this.q != 0) {
                by0.d("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            this.t = sw0Var;
            return this;
        }

        public b a(int i, int i2) {
            this.b = i;
            this.c = i2;
            return this;
        }

        public b a(QueueProcessingType queueProcessingType) {
            if (this.i != null || this.j != null) {
                by0.d("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.p = queueProcessingType;
            return this;
        }

        public b c(int i) {
            if (this.i != null || this.j != null) {
                by0.d("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.m = i;
            return this;
        }
    }

    public ix0 a() {
        DisplayMetrics displayMetrics = this.f6992a.getDisplayMetrics();
        int i = this.b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new ix0(i, i2);
    }

    public ax0(b bVar) {
        this.f6992a = bVar.f6994a.getResources();
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.f6993e = bVar.f6995e;
        this.f = bVar.f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.m = bVar.m;
        this.n = bVar.n;
        this.o = bVar.p;
        this.q = bVar.u;
        this.p = bVar.t;
        this.t = bVar.y;
        this.u = bVar.z;
        ImageDownloader imageDownloader = bVar.w;
        this.r = imageDownloader;
        this.s = bVar.x;
        this.k = bVar.k;
        this.l = bVar.l;
        this.w = new ux0(imageDownloader);
        this.x = new vx0(imageDownloader);
        this.v = ww0.a(cy0.a(bVar.f6994a, false));
    }
}
