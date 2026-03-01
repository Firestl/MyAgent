package supwisdom;

import android.graphics.Bitmap;
import android.text.Layout;

/* JADX INFO: compiled from: Cue.java */
/* JADX INFO: loaded from: classes.dex */
public class y50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f9847a;
    public final Bitmap b;
    public final float c;
    public final float d;

    public y50(Bitmap bitmap, float f, int i, float f2, int i2, float f3, float f4) {
        this(null, null, bitmap, f2, 0, i2, f, i, f3, f4, false, -16777216);
    }

    public y50(CharSequence charSequence) {
        this(charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public y50(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        this(charSequence, alignment, f, i, i2, f2, i3, f3, false, -16777216);
    }

    public y50(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4) {
        this(charSequence, alignment, null, f, i, i2, f2, i3, f3, Float.MIN_VALUE, z, i4);
    }

    public y50(CharSequence charSequence, Layout.Alignment alignment, Bitmap bitmap, float f, int i, int i2, float f2, int i3, float f3, float f4, boolean z, int i4) {
        this.f9847a = charSequence;
        this.b = bitmap;
        this.c = f;
        this.d = f2;
    }
}
