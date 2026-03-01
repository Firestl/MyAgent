package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VectorEnabledTintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class u3 extends Resources {
    public static boolean b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference<Context> f9361a;

    public u3(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f9361a = new WeakReference<>(context);
    }

    public static boolean b() {
        return a() && Build.VERSION.SDK_INT <= 20;
    }

    public final Drawable a(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Context context = this.f9361a.get();
        return context != null ? f3.a().a(context, this, i) : super.getDrawable(i);
    }

    public static boolean a() {
        return b;
    }
}
