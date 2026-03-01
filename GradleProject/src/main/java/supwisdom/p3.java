package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import supwisdom.k8;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class p3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8762a;
    public final TypedArray b;
    public TypedValue c;

    public p3(Context context, TypedArray typedArray) {
        this.f8762a = context;
        this.b = typedArray;
    }

    public static p3 a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new p3(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public Drawable b(int i) {
        int resourceId;
        return (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) ? this.b.getDrawable(i) : b1.c(this.f8762a, resourceId);
    }

    public Drawable c(int i) {
        int resourceId;
        if (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return o2.b().a(this.f8762a, resourceId, true);
    }

    public String d(int i) {
        return this.b.getString(i);
    }

    public CharSequence e(int i) {
        return this.b.getText(i);
    }

    public int f(int i, int i2) {
        return this.b.getLayoutDimension(i, i2);
    }

    public int g(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public static p3 a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new p3(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public int d(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public int e(int i, int i2) {
        return this.b.getInteger(i, i2);
    }

    public CharSequence[] f(int i) {
        return this.b.getTextArray(i);
    }

    public boolean g(int i) {
        return this.b.hasValue(i);
    }

    public static p3 a(Context context, int i, int[] iArr) {
        return new p3(context, context.obtainStyledAttributes(i, iArr));
    }

    public int c(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public TypedArray a() {
        return this.b;
    }

    public float b(int i, float f) {
        return this.b.getFloat(i, f);
    }

    public Typeface a(int i, int i2, k8.c cVar) {
        int resourceId = this.b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return k8.a(this.f8762a, resourceId, this.c, i2, cVar);
    }

    public int b(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public void b() {
        this.b.recycle();
    }

    public boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public int a(int i, int i2) {
        return this.b.getColor(i, i2);
    }

    public ColorStateList a(int i) {
        int resourceId;
        ColorStateList colorStateListB;
        return (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0 || (colorStateListB = b1.b(this.f8762a, resourceId)) == null) ? this.b.getColorStateList(i) : colorStateListB;
    }

    public float a(int i, float f) {
        return this.b.getDimension(i, f);
    }
}
