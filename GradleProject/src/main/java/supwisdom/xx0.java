package supwisdom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.nostra13.dcloudimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes2.dex */
public class xx0 implements wx0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ix0 f9822a;
    public final ViewScaleType b;

    public xx0(ix0 ix0Var, ViewScaleType viewScaleType) {
        this.f9822a = ix0Var;
        this.b = viewScaleType;
    }

    @Override // supwisdom.wx0
    public View a() {
        return null;
    }

    @Override // supwisdom.wx0
    public boolean a(Drawable drawable) {
        return true;
    }

    @Override // supwisdom.wx0
    public boolean b() {
        return false;
    }

    @Override // supwisdom.wx0
    public ViewScaleType c() {
        return this.b;
    }

    @Override // supwisdom.wx0
    public int getHeight() {
        return this.f9822a.a();
    }

    @Override // supwisdom.wx0
    public int getId() {
        return super.hashCode();
    }

    @Override // supwisdom.wx0
    public int getWidth() {
        return this.f9822a.b();
    }

    @Override // supwisdom.wx0
    public boolean setImageBitmap(Bitmap bitmap) {
        return true;
    }
}
