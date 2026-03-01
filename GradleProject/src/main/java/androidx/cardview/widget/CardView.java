package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import supwisdom.c4;
import supwisdom.d4;
import supwisdom.e4;
import supwisdom.f4;
import supwisdom.g4;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {
    public static final int[] h = {R.attr.colorBackground};
    public static final g4 i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1207a;
    public boolean b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f1208e;
    public final Rect f;
    public final f4 g;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            i = new d4();
        } else if (i2 >= 17) {
            i = new c4();
        } else {
            i = new e4();
        }
        i.a();
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.cardview.R.attr.cardViewStyle);
    }

    public ColorStateList getCardBackgroundColor() {
        return i.e(this.g);
    }

    public float getCardElevation() {
        return i.a(this.g);
    }

    public int getContentPaddingBottom() {
        return this.f1208e.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f1208e.left;
    }

    public int getContentPaddingRight() {
        return this.f1208e.right;
    }

    public int getContentPaddingTop() {
        return this.f1208e.top;
    }

    public float getMaxCardElevation() {
        return i.d(this.g);
    }

    public boolean getPreventCornerOverlap() {
        return this.b;
    }

    public float getRadius() {
        return i.b(this.g);
    }

    public boolean getUseCompatPadding() {
        return this.f1207a;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (i instanceof d4) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(i.h(this.g)), View.MeasureSpec.getSize(i2)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(i.g(this.g)), View.MeasureSpec.getSize(i3)), mode2);
        }
        super.onMeasure(i2, i3);
    }

    public void setCardBackgroundColor(int i2) {
        i.a(this.g, ColorStateList.valueOf(i2));
    }

    public void setCardElevation(float f) {
        i.b(this.g, f);
    }

    public void setMaxCardElevation(float f) {
        i.c(this.g, f);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i2) {
        this.d = i2;
        super.setMinimumHeight(i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i2) {
        this.c = i2;
        super.setMinimumWidth(i2);
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.b) {
            this.b = z;
            i.i(this.g);
        }
    }

    public void setRadius(float f) {
        i.a(this.g, f);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f1207a != z) {
            this.f1207a = z;
            i.c(this.g);
        }
    }

    public class a implements f4 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Drawable f1209a;

        public a() {
        }

        @Override // supwisdom.f4
        public void a(Drawable drawable) {
            this.f1209a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // supwisdom.f4
        public boolean b() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // supwisdom.f4
        public Drawable c() {
            return this.f1209a;
        }

        @Override // supwisdom.f4
        public View d() {
            return CardView.this;
        }

        @Override // supwisdom.f4
        public boolean a() {
            return CardView.this.getPreventCornerOverlap();
        }

        @Override // supwisdom.f4
        public void a(int i, int i2, int i3, int i4) {
            CardView.this.f.set(i, i2, i3, i4);
            CardView cardView = CardView.this;
            Rect rect = cardView.f1208e;
            CardView.super.setPadding(i + rect.left, i2 + rect.top, i3 + rect.right, i4 + rect.bottom);
        }

        @Override // supwisdom.f4
        public void a(int i, int i2) {
            CardView cardView = CardView.this;
            if (i > cardView.c) {
                CardView.super.setMinimumWidth(i);
            }
            CardView cardView2 = CardView.this;
            if (i2 > cardView2.d) {
                CardView.super.setMinimumHeight(i2);
            }
        }
    }

    public CardView(Context context, AttributeSet attributeSet, int i2) {
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i2);
        this.f1208e = new Rect();
        this.f = new Rect();
        this.g = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.cardview.R.styleable.CardView, i2, androidx.cardview.R.style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(androidx.cardview.R.styleable.CardView_cardBackgroundColor)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(androidx.cardview.R.styleable.CardView_cardBackgroundColor);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(h);
            int color = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            colorStateListValueOf = ColorStateList.valueOf(fArr[2] > 0.5f ? getResources().getColor(androidx.cardview.R.color.cardview_light_background) : getResources().getColor(androidx.cardview.R.color.cardview_dark_background));
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(androidx.cardview.R.styleable.CardView_cardCornerRadius, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(androidx.cardview.R.styleable.CardView_cardElevation, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(androidx.cardview.R.styleable.CardView_cardMaxElevation, 0.0f);
        this.f1207a = typedArrayObtainStyledAttributes.getBoolean(androidx.cardview.R.styleable.CardView_cardUseCompatPadding, false);
        this.b = typedArrayObtainStyledAttributes.getBoolean(androidx.cardview.R.styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_contentPadding, 0);
        this.f1208e.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        this.f1208e.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_contentPaddingTop, dimensionPixelSize);
        this.f1208e.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_contentPaddingRight, dimensionPixelSize);
        this.f1208e.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        float f = dimension2 > dimension3 ? dimension2 : dimension3;
        this.c = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_android_minWidth, 0);
        this.d = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.cardview.R.styleable.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        i.a(this.g, context, colorStateList, dimension, dimension2, f);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        i.a(this.g, colorStateList);
    }

    public void a(int i2, int i3, int i4, int i5) {
        this.f1208e.set(i2, i3, i4, i5);
        i.f(this.g);
    }
}
