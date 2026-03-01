package supwisdom;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.nostra13.dcloudimageloader.core.assist.LoadedFrom;
import com.nostra13.dcloudimageloader.core.assist.ViewScaleType;
import supwisdom.yw0;

/* JADX INFO: loaded from: classes2.dex */
public class zw0 {
    public static final String d = "ImageLoader";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile zw0 f10040e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ax0 f10041a;
    public bx0 b;
    public final hx0 c = new kx0();

    public static zw0 f() {
        if (f10040e == null) {
            synchronized (zw0.class) {
                if (f10040e == null) {
                    f10040e = new zw0();
                }
            }
        }
        return f10040e;
    }

    public final void a() {
        if (this.f10041a == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public void b() {
        a();
        this.f10041a.p.clear();
    }

    public jw0 c() {
        a();
        return this.f10041a.q;
    }

    public sw0 d() {
        a();
        return this.f10041a.p;
    }

    public boolean e() {
        return this.f10041a != null;
    }

    public synchronized void a(ax0 ax0Var) {
        if (ax0Var != null) {
            if (this.f10041a == null) {
                if (ax0Var.u) {
                    by0.a("Initialize ImageLoader with configuration", new Object[0]);
                }
                this.b = new bx0(ax0Var);
                this.f10041a = ax0Var;
            } else {
                by0.d("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
            }
        } else {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        }
    }

    public void a(String str, hx0 hx0Var) {
        a(str, (ix0) null, (yw0) null, hx0Var);
    }

    public Bitmap a(String str) {
        return a(str, (ix0) null, (yw0) null);
    }

    public Bitmap a(String str, yw0 yw0Var) {
        return a(str, (ix0) null, yw0Var);
    }

    public void a(String str, yw0 yw0Var, hx0 hx0Var) {
        a(str, (ix0) null, yw0Var, hx0Var);
    }

    public void a(String str, wx0 wx0Var, yw0 yw0Var, hx0 hx0Var) {
        a();
        if (wx0Var != null) {
            if (hx0Var == null) {
                hx0Var = this.c;
            }
            hx0 hx0Var2 = hx0Var;
            if (yw0Var == null) {
                yw0Var = this.f10041a.t;
            }
            if (TextUtils.isEmpty(str)) {
                this.b.a(wx0Var);
                hx0Var2.onLoadingStarted(str, wx0Var.a());
                if (yw0Var.q()) {
                    wx0Var.a(yw0Var.a(this.f10041a.f6992a));
                } else {
                    wx0Var.a(null);
                }
                hx0Var2.onLoadingComplete(str, wx0Var.a(), null);
                return;
            }
            ix0 ix0VarA = zx0.a(wx0Var, this.f10041a.a());
            String strA = jx0.a(str, ix0VarA);
            this.b.a(wx0Var, strA);
            hx0Var2.onLoadingStarted(str, wx0Var.a());
            Bitmap bitmapA = this.f10041a.p.a(strA);
            if (bitmapA != null && !bitmapA.isRecycled()) {
                if (this.f10041a.u) {
                    by0.a("Load image from memory cache [%s]", strA);
                }
                if (yw0Var.o()) {
                    fx0 fx0Var = new fx0(this.b, bitmapA, new dx0(str, wx0Var, ix0VarA, strA, yw0Var, hx0Var2, this.b.a(str)), yw0Var.e());
                    if (yw0Var.m()) {
                        fx0Var.run();
                        return;
                    } else {
                        this.b.a(fx0Var);
                        return;
                    }
                }
                yw0Var.c().a(bitmapA, wx0Var, LoadedFrom.MEMORY_CACHE);
                hx0Var2.onLoadingComplete(str, wx0Var.a(), bitmapA);
                return;
            }
            if (yw0Var.s()) {
                wx0Var.a(yw0Var.c(this.f10041a.f6992a));
            } else if (yw0Var.l()) {
                wx0Var.a(null);
            }
            ex0 ex0Var = new ex0(this.b, new dx0(str, wx0Var, ix0VarA, strA, yw0Var, hx0Var2, this.b.a(str)), yw0Var.e());
            if (yw0Var.m()) {
                ex0Var.run();
                return;
            } else {
                this.b.a(ex0Var);
                return;
            }
        }
        throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
    }

    public void a(String str, ix0 ix0Var, yw0 yw0Var, hx0 hx0Var) {
        a();
        if (ix0Var == null) {
            ix0Var = this.f10041a.a();
        }
        if (yw0Var == null) {
            yw0Var = this.f10041a.t;
        }
        a(str, new xx0(ix0Var, ViewScaleType.CROP), yw0Var, hx0Var);
    }

    public Bitmap a(String str, ix0 ix0Var, yw0 yw0Var) {
        if (yw0Var == null) {
            yw0Var = this.f10041a.t;
        }
        yw0.b bVar = new yw0.b();
        bVar.a(yw0Var);
        bVar.c(true);
        yw0 yw0VarA = bVar.a();
        lx0 lx0Var = new lx0();
        a(str, ix0Var, yw0VarA, lx0Var);
        return lx0Var.a();
    }
}
