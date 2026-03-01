package supwisdom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* JADX INFO: compiled from: ImageViewAction.java */
/* JADX INFO: loaded from: classes2.dex */
public class bh1 extends rg1<ImageView> {
    public vg1 m;

    public bh1(Picasso picasso, ImageView imageView, jh1 jh1Var, int i, int i2, int i3, Drawable drawable, String str, Object obj, vg1 vg1Var, boolean z) {
        super(picasso, imageView, jh1Var, i, i2, i3, drawable, str, obj, z);
        this.m = vg1Var;
    }

    @Override // supwisdom.rg1
    public void a(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        if (bitmap == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", this));
        }
        ImageView imageView = (ImageView) this.c.get();
        if (imageView == null) {
            return;
        }
        Picasso picasso = this.f9060a;
        hh1.a(imageView, picasso.f3963e, bitmap, loadedFrom, this.d, picasso.m);
        vg1 vg1Var = this.m;
        if (vg1Var != null) {
            vg1Var.onSuccess();
        }
    }

    @Override // supwisdom.rg1
    public void b() {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView == null) {
            return;
        }
        int i = this.g;
        if (i != 0) {
            imageView.setImageResource(i);
        } else {
            Drawable drawable = this.h;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }
        vg1 vg1Var = this.m;
        if (vg1Var != null) {
            vg1Var.onError();
        }
    }

    @Override // supwisdom.rg1
    public void a() {
        super.a();
        if (this.m != null) {
            this.m = null;
        }
    }
}
