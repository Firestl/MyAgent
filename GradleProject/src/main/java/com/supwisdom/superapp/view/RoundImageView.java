package com.supwisdom.superapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class RoundImageView extends AppCompatImageView {
    public Paint c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4402e;
    public int f;
    public int g;
    public int h;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public final void c() {
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            this.c.setColor(colorDrawable.getColor());
            setBackground(colorDrawable);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        int i = this.g;
        canvas.drawCircle(i + r1, this.h + r1, this.f, this.c);
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.d = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.f4402e = measuredHeight;
        int i3 = this.d;
        if (i3 > measuredHeight) {
            this.f = measuredHeight / 2;
            this.h = 0;
            this.g = getPaddingLeft();
        } else if (i3 >= measuredHeight) {
            this.g = 0;
            this.h = 0;
        } else {
            this.f = i3 / 2;
            this.g = 0;
            this.h = getPaddingTop();
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint();
        this.c = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.c.setColor(context.getResources().getColor(R.color.color_66070708));
        c();
    }
}
