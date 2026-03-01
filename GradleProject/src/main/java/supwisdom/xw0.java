package supwisdom;

import android.graphics.Bitmap;
import com.nostra13.dcloudimageloader.core.assist.LoadedFrom;

/* JADX INFO: loaded from: classes2.dex */
public final class xw0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bitmap f9819a;
    public final String b;
    public final wx0 c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final rx0 f9820e;
    public final hx0 f;
    public final bx0 g;
    public final LoadedFrom h;
    public boolean i;

    public xw0(Bitmap bitmap, dx0 dx0Var, bx0 bx0Var, LoadedFrom loadedFrom) {
        this.f9819a = bitmap;
        this.b = dx0Var.f7392a;
        this.c = dx0Var.c;
        this.d = dx0Var.b;
        this.f9820e = dx0Var.f7393e.c();
        this.f = dx0Var.f;
        this.g = bx0Var;
        this.h = loadedFrom;
    }

    public final boolean a() {
        return !this.d.equals(this.g.b(this.c));
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.b()) {
            if (this.i) {
                by0.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.d);
            }
            this.f.onLoadingCancelled(this.b, this.c.a());
        } else {
            if (a()) {
                if (this.i) {
                    by0.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.d);
                }
                this.f.onLoadingCancelled(this.b, this.c.a());
                return;
            }
            if (this.i) {
                by0.a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.h, this.d);
            }
            rx0 rx0Var = this.f9820e;
            Bitmap bitmap = this.f9819a;
            rx0Var.a(bitmap, this.c, this.h);
            this.f.onLoadingComplete(this.b, this.c.a(), bitmap);
            this.g.a(this.c);
        }
    }

    public void a(boolean z) {
        this.i = z;
    }
}
