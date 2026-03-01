package supwisdom;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;

/* JADX INFO: loaded from: classes2.dex */
public final class yw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9928a;
    public final int b;
    public final int c;
    public final Drawable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Drawable f9929e;
    public final Drawable f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final ImageScaleType j;
    public final BitmapFactory.Options k;
    public final int l;
    public final boolean m;
    public final Object n;
    public final yx0 o;
    public final yx0 p;
    public final rx0 q;
    public final Handler r;
    public final boolean s;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9930a = 0;
        public int b = 0;
        public int c = 0;
        public Drawable d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Drawable f9931e = null;
        public Drawable f = null;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;
        public ImageScaleType j = ImageScaleType.IN_SAMPLE_POWER_OF_2;
        public BitmapFactory.Options k = new BitmapFactory.Options();
        public int l = 0;
        public boolean m = false;
        public Object n = null;
        public yx0 o = null;
        public yx0 p = null;
        public rx0 q = ww0.a();
        public Handler r = null;
        public boolean s = false;

        public b() {
            BitmapFactory.Options options = this.k;
            options.inPurgeable = true;
            options.inInputShareable = true;
        }

        public b a(Bitmap.Config config) {
            if (config == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.k.inPreferredConfig = config;
            return this;
        }

        public b b(boolean z) {
            this.i = z;
            return this;
        }

        public b c(boolean z) {
            this.s = z;
            return this;
        }

        public yw0 a() {
            return new yw0(this);
        }

        public b a(yw0 yw0Var) {
            this.f9930a = yw0Var.f9928a;
            this.b = yw0Var.b;
            this.c = yw0Var.c;
            this.d = yw0Var.d;
            this.f9931e = yw0Var.f9929e;
            this.f = yw0Var.f;
            this.g = yw0Var.g;
            this.h = yw0Var.h;
            this.i = yw0Var.i;
            this.j = yw0Var.j;
            this.k = yw0Var.k;
            this.l = yw0Var.l;
            this.m = yw0Var.m;
            this.n = yw0Var.n;
            this.o = yw0Var.o;
            this.p = yw0Var.p;
            this.q = yw0Var.q;
            this.r = yw0Var.r;
            this.s = yw0Var.s;
            return this;
        }

        public b a(ImageScaleType imageScaleType) {
            this.j = imageScaleType;
            return this;
        }

        public b a(int i) {
            this.f9930a = i;
            return this;
        }

        public b a(boolean z) {
            this.h = z;
            return this;
        }

        public b a(Drawable drawable) {
            this.d = drawable;
            return this;
        }
    }

    public static yw0 t() {
        return new b().a();
    }

    public yw0(b bVar) {
        this.f9928a = bVar.f9930a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.f9929e = bVar.f9931e;
        this.f = bVar.f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.l = bVar.l;
        this.m = bVar.m;
        this.n = bVar.n;
        this.o = bVar.o;
        this.p = bVar.p;
        this.q = bVar.q;
        this.r = bVar.r;
        this.s = bVar.s;
    }

    public BitmapFactory.Options a() {
        return this.k;
    }

    public int b() {
        return this.l;
    }

    public rx0 c() {
        return this.q;
    }

    public Object d() {
        return this.n;
    }

    public Handler e() {
        if (this.s) {
            return null;
        }
        Handler handler = this.r;
        if (handler != null) {
            return handler;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        throw new IllegalStateException("ImageLoader.displayImage(...) must be invoked from the main thread or from Looper thread");
    }

    public ImageScaleType f() {
        return this.j;
    }

    public yx0 g() {
        return this.p;
    }

    public yx0 h() {
        return this.o;
    }

    public boolean i() {
        return this.h;
    }

    public boolean j() {
        return this.i;
    }

    public boolean k() {
        return this.m;
    }

    public boolean l() {
        return this.g;
    }

    public boolean m() {
        return this.s;
    }

    public boolean n() {
        return this.l > 0;
    }

    public boolean o() {
        return this.p != null;
    }

    public boolean p() {
        return this.o != null;
    }

    public boolean q() {
        return (this.f9929e == null && this.b == 0) ? false : true;
    }

    public boolean r() {
        return (this.f == null && this.c == 0) ? false : true;
    }

    public boolean s() {
        return (this.d == null && this.f9928a == 0) ? false : true;
    }

    public Drawable a(Resources resources) {
        int i = this.b;
        return i != 0 ? resources.getDrawable(i) : this.f9929e;
    }

    public Drawable b(Resources resources) {
        int i = this.c;
        return i != 0 ? resources.getDrawable(i) : this.f;
    }

    public Drawable c(Resources resources) {
        int i = this.f9928a;
        return i != 0 ? resources.getDrawable(i) : this.d;
    }
}
