package supwisdom;

import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DeferredRequestCreator.java */
/* JADX INFO: loaded from: classes2.dex */
public class yg1 implements ViewTreeObserver.OnPreDrawListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kh1 f9883a;
    public final WeakReference<ImageView> b;
    public vg1 c;

    public yg1(kh1 kh1Var, ImageView imageView, vg1 vg1Var) {
        this.f9883a = kh1Var;
        this.b = new WeakReference<>(imageView);
        this.c = vg1Var;
        imageView.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void a() {
        this.c = null;
        ImageView imageView = this.b.get();
        if (imageView == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this);
        }
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        ImageView imageView = this.b.get();
        if (imageView == null) {
            return true;
        }
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (width > 0 && height > 0) {
            viewTreeObserver.removeOnPreDrawListener(this);
            kh1 kh1Var = this.f9883a;
            kh1Var.b();
            kh1Var.a(width, height);
            kh1Var.a(imageView, this.c);
        }
        return true;
    }
}
