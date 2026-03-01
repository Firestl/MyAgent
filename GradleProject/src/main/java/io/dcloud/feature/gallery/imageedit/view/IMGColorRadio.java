package io.dcloud.feature.gallery.imageedit.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RadioButton;
import io.dcloud.base.R;

/* JADX INFO: loaded from: classes2.dex */
public class IMGColorRadio extends RadioButton implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6548a;
    public int b;
    public float c;
    public ValueAnimator d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f6549e;

    public IMGColorRadio(Context context) {
        this(context, null, 0);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IMGColorRadio);
        this.f6548a = typedArrayObtainStyledAttributes.getColor(R.styleable.IMGColorRadio_image_color, -1);
        this.b = typedArrayObtainStyledAttributes.getColor(R.styleable.IMGColorRadio_image_stroke_color, -1);
        typedArrayObtainStyledAttributes.recycle();
        setButtonDrawable((Drawable) null);
        this.f6549e.setColor(this.f6548a);
        this.f6549e.setStrokeWidth(5.0f);
    }

    private float b(float f) {
        return f * ((this.c * 0.29999995f) + 0.6f);
    }

    private ValueAnimator getAnimator() {
        if (this.d == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.d = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(this);
            this.d.setDuration(200L);
            this.d.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        return this.d;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        float fMin = Math.min(width, height);
        canvas.save();
        this.f6549e.setColor(this.f6548a);
        this.f6549e.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width, height, a(fMin), this.f6549e);
        this.f6549e.setColor(this.b);
        this.f6549e.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width, height, b(fMin), this.f6549e);
        canvas.restore();
    }

    public int getColor() {
        return this.f6548a;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.c = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        boolean z2 = z != isChecked();
        super.setChecked(z);
        if (z2) {
            ValueAnimator animator = getAnimator();
            if (z) {
                animator.start();
            } else {
                animator.reverse();
            }
        }
    }

    public void setColor(int i) {
        this.f6548a = i;
        this.f6549e.setColor(i);
    }

    public IMGColorRadio(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6548a = -1;
        this.b = -1;
        this.c = 0.0f;
        this.f6549e = new Paint(1);
        a(context, attributeSet, 0);
    }

    public IMGColorRadio(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6548a = -1;
        this.b = -1;
        this.c = 0.0f;
        this.f6549e = new Paint(1);
        a(context, attributeSet, i);
    }

    private float a(float f) {
        return f * ((this.c * 0.120000005f) + 0.6f);
    }
}
