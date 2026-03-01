package com.supwisdom.superapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.supwisdom.superapp.R;

/* JADX INFO: loaded from: classes2.dex */
public class CircleImageView extends AppCompatImageView {
    public static final ImageView.ScaleType u = ImageView.ScaleType.CENTER_CROP;
    public static final Bitmap.Config v = Bitmap.Config.ARGB_8888;
    public final RectF c;
    public final RectF d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Matrix f4396e;
    public final Paint f;
    public final Paint g;
    public int h;
    public int i;
    public int j;
    public Bitmap k;
    public Canvas l;
    public float m;
    public float n;
    public ColorFilter o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (CircleImageView.this.t) {
                ViewOutlineProvider.BACKGROUND.getOutline(view, outline);
                return;
            }
            Rect rect = new Rect();
            CircleImageView.this.d.roundOut(rect);
            outline.setRoundRect(rect, rect.width() / 2.0f);
        }
    }

    public CircleImageView(Context context) {
        this(context, null);
    }

    public final RectF c() {
        int iMin = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        float paddingLeft = getPaddingLeft() + ((r0 - iMin) / 2.0f);
        float paddingTop = getPaddingTop() + ((r1 - iMin) / 2.0f);
        float f = iMin;
        return new RectF(paddingLeft, paddingTop, paddingLeft + f, f + paddingTop);
    }

    public final void d() {
        this.p = true;
        super.setScaleType(u);
        this.f.setAntiAlias(true);
        this.f.setDither(true);
        this.f.setFilterBitmap(true);
        this.f.setAlpha(this.j);
        this.f.setColorFilter(this.o);
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setAntiAlias(true);
        this.g.setColor(this.h);
        this.g.setStrokeWidth(this.i);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new b());
        }
    }

    public final void e() {
        Bitmap bitmapA = a(getDrawable());
        this.k = bitmapA;
        if (bitmapA == null || !bitmapA.isMutable()) {
            this.l = null;
        } else {
            this.l = new Canvas(this.k);
        }
        if (this.p) {
            if (this.k != null) {
                g();
            } else {
                this.f.setShader(null);
            }
        }
    }

    public final void f() {
        int i;
        this.d.set(c());
        this.n = Math.min((this.d.height() - this.i) / 2.0f, (this.d.width() - this.i) / 2.0f);
        this.c.set(this.d);
        if (!this.s && (i = this.i) > 0) {
            this.c.inset(i - 1.0f, i - 1.0f);
        }
        this.m = Math.min(this.c.height() / 2.0f, this.c.width() / 2.0f);
        g();
    }

    public final void g() {
        float fWidth;
        float fHeight;
        if (this.k == null) {
            return;
        }
        this.f4396e.set(null);
        int height = this.k.getHeight();
        float width = this.k.getWidth();
        float f = height;
        float fWidth2 = 0.0f;
        if (this.c.height() * width > this.c.width() * f) {
            fWidth = this.c.height() / f;
            fWidth2 = (this.c.width() - (width * fWidth)) * 0.5f;
            fHeight = 0.0f;
        } else {
            fWidth = this.c.width() / width;
            fHeight = (this.c.height() - (f * fWidth)) * 0.5f;
        }
        this.f4396e.setScale(fWidth, fWidth);
        Matrix matrix = this.f4396e;
        RectF rectF = this.c;
        matrix.postTranslate(((int) (fWidth2 + 0.5f)) + rectF.left, ((int) (fHeight + 0.5f)) + rectF.top);
        this.q = true;
    }

    public int getBorderColor() {
        return this.h;
    }

    public int getBorderWidth() {
        return this.i;
    }

    @Override // android.widget.ImageView
    public ColorFilter getColorFilter() {
        return this.o;
    }

    @Override // android.widget.ImageView
    public int getImageAlpha() {
        return this.j;
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        this.r = true;
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    @SuppressLint({"CanvasSize"})
    public void onDraw(Canvas canvas) {
        if (this.t) {
            super.onDraw(canvas);
            return;
        }
        if (this.k != null) {
            if (this.r && this.l != null) {
                this.r = false;
                Drawable drawable = getDrawable();
                drawable.setBounds(0, 0, this.l.getWidth(), this.l.getHeight());
                drawable.draw(this.l);
            }
            if (this.q) {
                this.q = false;
                Bitmap bitmap = this.k;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
                bitmapShader.setLocalMatrix(this.f4396e);
                this.f.setShader(bitmapShader);
            }
            canvas.drawCircle(this.c.centerX(), this.c.centerY(), this.m, this.f);
        }
        if (this.i > 0) {
            canvas.drawCircle(this.d.centerX(), this.d.centerY(), this.n, this.g);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        f();
        invalidate();
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.t ? super.onTouchEvent(motionEvent) : a(motionEvent.getX(), motionEvent.getY()) && super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void setBorderColor(int i) {
        if (i == this.h) {
            return;
        }
        this.h = i;
        this.g.setColor(i);
        invalidate();
    }

    public void setBorderOverlay(boolean z) {
        if (z == this.s) {
            return;
        }
        this.s = z;
        f();
        invalidate();
    }

    public void setBorderWidth(int i) {
        if (i == this.i) {
            return;
        }
        this.i = i;
        this.g.setStrokeWidth(i);
        f();
        invalidate();
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter == this.o) {
            return;
        }
        this.o = colorFilter;
        if (this.p) {
            this.f.setColorFilter(colorFilter);
            invalidate();
        }
    }

    public void setDisableCircularTransformation(boolean z) {
        if (z == this.t) {
            return;
        }
        this.t = z;
        if (z) {
            this.k = null;
            this.l = null;
            this.f.setShader(null);
        } else {
            e();
        }
        invalidate();
    }

    @Override // android.widget.ImageView
    public void setImageAlpha(int i) {
        int i2 = i & 255;
        if (i2 == this.j) {
            return;
        }
        this.j = i2;
        if (this.p) {
            this.f.setAlpha(i2);
            invalidate();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        e();
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        e();
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        e();
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        e();
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        f();
        invalidate();
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        f();
        invalidate();
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != u) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmapCreateBitmap = drawable instanceof ColorDrawable ? Bitmap.createBitmap(2, 2, v) : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), v);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new RectF();
        this.d = new RectF();
        this.f4396e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = -16777216;
        this.i = 0;
        this.j = 255;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleImageView, i, 0);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.h = typedArrayObtainStyledAttributes.getColor(0, -16777216);
        this.s = typedArrayObtainStyledAttributes.getBoolean(1, false);
        typedArrayObtainStyledAttributes.recycle();
        d();
    }

    public final boolean a(float f, float f2) {
        return this.d.isEmpty() || Math.pow((double) (f - this.d.centerX()), 2.0d) + Math.pow((double) (f2 - this.d.centerY()), 2.0d) <= Math.pow((double) this.n, 2.0d);
    }
}
