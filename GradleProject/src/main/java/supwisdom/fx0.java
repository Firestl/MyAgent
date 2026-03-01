package supwisdom;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.dcloudimageloader.core.assist.LoadedFrom;

/* JADX INFO: loaded from: classes2.dex */
public class fx0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final bx0 f7665a;
    public final Bitmap b;
    public final dx0 c;
    public final Handler d;

    public fx0(bx0 bx0Var, Bitmap bitmap, dx0 dx0Var, Handler handler) {
        this.f7665a = bx0Var;
        this.b = bitmap;
        this.c = dx0Var;
        this.d = handler;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f7665a.f7120a.u) {
            by0.a("PostProcess image before displaying [%s]", this.c.b);
        }
        xw0 xw0Var = new xw0(this.c.f7393e.g().a(this.b), this.c, this.f7665a, LoadedFrom.MEMORY_CACHE);
        xw0Var.a(this.f7665a.f7120a.u);
        if (this.c.f7393e.m()) {
            xw0Var.run();
        } else {
            this.d.post(xw0Var);
        }
    }
}
