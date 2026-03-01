package supwisdom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class hf0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7834a;

    public final void a(Context context, Bitmap bitmap, boolean z) {
        kf0.a(bitmap);
        a(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    public abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);

    public final void a(Context context, ii0 ii0Var, boolean z) {
        int i = this.f7834a;
        a(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }
}
