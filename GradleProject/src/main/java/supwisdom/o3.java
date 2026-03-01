package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class o3 extends g3 {
    public final WeakReference<Context> b;

    public o3(Context context, Resources resources) {
        super(resources);
        this.b = new WeakReference<>(context);
    }

    @Override // supwisdom.g3, android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Drawable drawable = super.getDrawable(i);
        Context context = this.b.get();
        if (drawable != null && context != null) {
            f3.a().a(context, i, drawable);
        }
        return drawable;
    }
}
