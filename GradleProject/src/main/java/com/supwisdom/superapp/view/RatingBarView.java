package com.supwisdom.superapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.supwisdom.superapp.R;

/* JADX INFO: loaded from: classes2.dex */
public class RatingBarView extends View {
    public static final String m = RatingBarView.class.getCanonicalName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Bitmap f4399a;
    public Bitmap b;
    public Bitmap c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4400e;
    public b f;
    public boolean g;
    public a h;
    public Paint i;
    public int j;
    public float k;
    public float l;

    public interface a {
        void a(float f, int i);
    }

    public enum b {
        FULL,
        HALF
    }

    public RatingBarView(Context context) {
        this(context, null);
    }

    public final void a() {
        this.i = new Paint();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < this.d; i++) {
            float paddingLeft = getPaddingLeft();
            if (i > 0) {
                paddingLeft = getPaddingLeft() + ((this.f4399a.getWidth() + this.j) * i);
            }
            float paddingTop = getPaddingTop();
            float f = i;
            float f2 = this.f4400e;
            if (f >= f2) {
                canvas.drawBitmap(this.f4399a, paddingLeft, paddingTop, this.i);
            } else if (f < f2 - 1.0f) {
                canvas.drawBitmap(this.c, paddingLeft, paddingTop, this.i);
            } else if (this.f == b.FULL) {
                canvas.drawBitmap(this.c, paddingLeft, paddingTop, this.i);
            } else {
                canvas.drawBitmap(this.b, paddingLeft, paddingTop, this.i);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingTop = getPaddingTop() + getPaddingBottom() + this.f4399a.getHeight();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int width = this.f4399a.getWidth();
        int i3 = this.d;
        setMeasuredDimension(paddingLeft + (width * i3) + (this.j * (i3 - 1)), paddingTop);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2) {
            float x = motionEvent.getX();
            int width = getWidth() / this.d;
            float f = width;
            int i = (int) ((x / f) + 1.0f);
            if (i < 0) {
                i = 0;
            }
            int i2 = this.d;
            if (i > i2) {
                i = i2;
            }
            b bVar = x - ((float) (width * (i + (-1)))) > f * 0.5f ? b.FULL : b.HALF;
            if (this.g) {
                bVar = b.FULL;
            }
            float f2 = i;
            if (this.f4400e == f2 && bVar == this.f) {
                return true;
            }
            this.f4400e = f2;
            this.f = bVar;
            invalidate();
            if (this.h == null) {
                return true;
            }
            float f3 = this.f4400e;
            int i3 = (int) (f3 - 1.0f);
            if (this.f != b.FULL) {
                f3 -= 0.5f;
            }
            this.h.a(f3, i3 >= 0 ? i3 : 0);
            return true;
        }
        if (motionEvent.getAction() != 0) {
            return true;
        }
        float x2 = motionEvent.getX();
        int width2 = getWidth() / this.d;
        float f4 = width2;
        int i4 = (int) ((x2 / f4) + 1.0f);
        if (i4 < 0) {
            i4 = 0;
        }
        int i5 = this.d;
        if (i4 > i5) {
            i4 = i5;
        }
        b bVar2 = x2 - ((float) (width2 * (i4 + (-1)))) > f4 * 0.5f ? b.FULL : b.HALF;
        if (this.g) {
            bVar2 = b.FULL;
        }
        float f5 = i4;
        if (this.f4400e == f5 && bVar2 == this.f) {
            return true;
        }
        this.f4400e = f5;
        this.f = bVar2;
        invalidate();
        if (this.h == null) {
            return true;
        }
        float f6 = this.f4400e;
        int i6 = (int) (f6 - 1.0f);
        if (this.f != b.FULL) {
            f6 -= 0.5f;
        }
        this.h.a(f6, i6 >= 0 ? i6 : 0);
        return true;
    }

    public void setOnStarChangeListener(a aVar) {
        this.h = aVar;
    }

    public void setSelectedNumber(int i) {
        if (i < 0 || i > this.d) {
            return;
        }
        this.f4400e = i;
        invalidate();
    }

    public void setStartTotalNumber(int i) {
        if (i > 0) {
            this.d = i;
            invalidate();
        }
    }

    public RatingBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatingBar);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(5, 0);
        if (resourceId == 0) {
            throw new IllegalArgumentException("请设置属性 starNormal");
        }
        this.f4399a = a(context, resourceId);
        Log.e(m, "AirRatingBar: " + resourceId);
        Log.e(m, "AirRatingBar: " + this.f4399a);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        if (resourceId2 != 0) {
            this.b = a(context, resourceId2);
        }
        Log.e(m, "AirRatingBar: " + resourceId2);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(6, 0);
        if (resourceId3 == 0) {
            throw new IllegalArgumentException("请设置属性 starSelected");
        }
        this.c = a(context, resourceId3);
        Log.e(m, "AirRatingBar: " + resourceId3);
        Log.e(m, "AirRatingBar: " + this.c);
        if (resourceId2 == 0) {
            this.b = this.c;
        }
        this.d = typedArrayObtainStyledAttributes.getInt(7, this.d);
        this.f4400e = typedArrayObtainStyledAttributes.getFloat(0, this.f4400e);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getDimension(8, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getDimension(3, 0.0f);
        this.g = typedArrayObtainStyledAttributes.getBoolean(4, true);
        typedArrayObtainStyledAttributes.recycle();
        int iMax = (int) Math.max(this.k, this.l);
        if (iMax > 0) {
            this.f4399a = a(this.f4399a, iMax);
            this.c = a(this.c, iMax);
            this.b = a(this.b, iMax);
        }
        if (this.g) {
            return;
        }
        if (this.f4400e <= ((int) r6) + 0.5f) {
            this.f = b.HALF;
        }
    }

    public RatingBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 5;
        this.f = b.FULL;
        a();
        a(context, attributeSet);
    }

    public Bitmap a(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, i, i, true);
    }

    public final Bitmap a(Context context, int i) {
        if (Build.VERSION.SDK_INT > 21) {
            Drawable drawable = context.getDrawable(i);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        }
        return BitmapFactory.decodeResource(context.getResources(), i);
    }
}
