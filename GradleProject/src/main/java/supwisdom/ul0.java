package supwisdom;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.google.android.material.R;
import com.google.android.material.card.MaterialCardView;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class ul0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaterialCardView f9422a;
    public int b;
    public int c;

    public ul0(MaterialCardView materialCardView) {
        this.f9422a = materialCardView;
    }

    public void a(TypedArray typedArray) {
        this.b = typedArray.getColor(R.styleable.MaterialCardView_strokeColor, -1);
        this.c = typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
        e();
        a();
    }

    public void b(int i) {
        this.c = i;
        e();
        a();
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public void e() {
        this.f9422a.setForeground(b());
    }

    public final Drawable b() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(this.f9422a.getRadius());
        int i = this.b;
        if (i != -1) {
            gradientDrawable.setStroke(this.c, i);
        }
        return gradientDrawable;
    }

    public void a(int i) {
        this.b = i;
        e();
    }

    public final void a() {
        this.f9422a.a(this.f9422a.getContentPaddingLeft() + this.c, this.f9422a.getContentPaddingTop() + this.c, this.f9422a.getContentPaddingRight() + this.c, this.f9422a.getContentPaddingBottom() + this.c);
    }
}
