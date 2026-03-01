package com.supwisdom.superapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.supwisdom.superapp.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceLineView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4415a;
    public int b;
    public float c;
    public Paint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f4416e;
    public int f;
    public int g;
    public float h;
    public float i;
    public boolean j;
    public float k;
    public float l;
    public int m;
    public float n;
    public long o;
    public float p;
    public float q;
    public float r;
    public List<Rect> s;
    public long t;
    public int u;
    public List<Path> v;

    public VoiceLineView(Context context) {
        super(context);
        this.f4415a = -16777216;
        this.b = -16777216;
        this.c = 4.0f;
        this.g = 4;
        this.h = 100.0f;
        this.i = 0.0f;
        this.j = false;
        this.k = 1.0f;
        this.l = 10.0f;
        this.m = 1;
        this.n = 1.0f;
        this.o = 50L;
        this.p = 25.0f;
        this.q = 5.0f;
        this.r = 4.0f;
        this.t = 0L;
        this.u = 90;
        this.v = null;
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.voiceView);
        this.f = typedArrayObtainStyledAttributes.getInt(9, 0);
        this.b = typedArrayObtainStyledAttributes.getColor(10, -16777216);
        this.h = typedArrayObtainStyledAttributes.getFloat(2, 100.0f);
        this.g = typedArrayObtainStyledAttributes.getInt(8, 4);
        if (this.f == 1) {
            this.p = typedArrayObtainStyledAttributes.getDimension(7, 25.0f);
            this.q = typedArrayObtainStyledAttributes.getDimension(6, 5.0f);
            this.r = typedArrayObtainStyledAttributes.getDimension(5, 4.0f);
        } else {
            this.f4415a = typedArrayObtainStyledAttributes.getColor(3, -16777216);
            this.c = typedArrayObtainStyledAttributes.getDimension(4, 4.0f);
            this.u = typedArrayObtainStyledAttributes.getInt(1, 90);
            this.m = typedArrayObtainStyledAttributes.getInt(0, 1);
            this.v = new ArrayList(20);
            for (int i = 0; i < 20; i++) {
                this.v.add(new Path());
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(Canvas canvas) {
        a();
        if (this.f4416e == null) {
            Paint paint = new Paint();
            this.f4416e = paint;
            paint.setColor(this.b);
            this.f4416e.setAntiAlias(true);
            this.f4416e.setStyle(Paint.Style.STROKE);
            this.f4416e.setStrokeWidth(2.0f);
        }
        canvas.save();
        int height = getHeight() / 2;
        for (int i = 0; i < this.v.size(); i++) {
            this.v.get(i).reset();
            this.v.get(i).moveTo(getWidth(), getHeight() / 2);
        }
        float width = getWidth() - 1;
        while (width >= 0.0f) {
            this.k = (((this.l * 4.0f) * width) / getWidth()) - (((((this.l * 4.0f) * width) * width) / getWidth()) / getWidth());
            for (int i2 = 1; i2 <= this.v.size(); i2++) {
                float fSin = this.k * ((float) Math.sin((((((double) width) - Math.pow(1.22d, i2)) * 3.141592653589793d) / 180.0d) - ((double) this.i)));
                this.v.get(i2 - 1).lineTo(width, ((((i2 * 2) * fSin) / this.v.size()) - ((fSin * 15.0f) / this.v.size())) + height);
            }
            width -= this.m;
        }
        for (int i3 = 0; i3 < this.v.size(); i3++) {
            if (i3 == this.v.size() - 1) {
                this.f4416e.setAlpha(255);
            } else {
                this.f4416e.setAlpha((i3 * 130) / this.v.size());
            }
            if (this.f4416e.getAlpha() > 0) {
                canvas.drawPath(this.v.get(i3), this.f4416e);
            }
        }
        canvas.restore();
    }

    public final void c(Canvas canvas) {
        if (this.f4416e == null) {
            Paint paint = new Paint();
            this.f4416e = paint;
            paint.setColor(this.b);
            this.f4416e.setAntiAlias(true);
            this.f4416e.setStyle(Paint.Style.STROKE);
            this.f4416e.setStrokeWidth(2.0f);
        }
        if (this.s == null) {
            this.s = new LinkedList();
        }
        long j = (int) (this.q + this.p);
        if (this.o % j < 6) {
            int i = (int) ((((-this.p) - 10.0f) - this.o) + (r8 % j));
            float height = (getHeight() / 2) - (this.r / 2.0f);
            float f = this.l;
            int i2 = (int) (height - (f == 10.0f ? 0.0f : f / 2.0f));
            long j2 = this.o;
            int i3 = (int) (((-10) - j2) + (j2 % j));
            float height2 = (getHeight() / 2) + (this.r / 2.0f);
            float f2 = this.l;
            Rect rect = new Rect(i, i2, i3, (int) (height2 + (f2 == 10.0f ? 0.0f : f2 / 2.0f)));
            if (this.s.size() > (getWidth() / (this.q + this.p)) + 2.0f) {
                this.s.remove(0);
            }
            this.s.add(rect);
        }
        canvas.translate(this.o, 0.0f);
        for (int size = this.s.size() - 1; size >= 0; size--) {
            canvas.drawRect(this.s.get(size), this.f4416e);
        }
        b();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f == 1) {
            c(canvas);
        } else {
            a(canvas);
            b(canvas);
        }
        c();
    }

    public void setVolume(int i) {
        if (i > (this.h * this.g) / 25.0f) {
            this.j = true;
            this.n = ((getHeight() * i) / 2) / this.h;
        }
    }

    public final void a(Canvas canvas) {
        if (this.d == null) {
            Paint paint = new Paint();
            this.d = paint;
            paint.setColor(this.f4415a);
            this.d.setAntiAlias(true);
        }
        canvas.save();
        canvas.drawRect(0.0f, (getHeight() / 2) - (this.c / 2.0f), getWidth(), (getHeight() / 2) + (this.c / 2.0f), this.d);
        canvas.restore();
    }

    public VoiceLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4415a = -16777216;
        this.b = -16777216;
        this.c = 4.0f;
        this.g = 4;
        this.h = 100.0f;
        this.i = 0.0f;
        this.j = false;
        this.k = 1.0f;
        this.l = 10.0f;
        this.m = 1;
        this.n = 1.0f;
        this.o = 50L;
        this.p = 25.0f;
        this.q = 5.0f;
        this.r = 4.0f;
        this.t = 0L;
        this.u = 90;
        this.v = null;
        a(context, attributeSet);
    }

    public void c() {
        if (this.f == 1) {
            postInvalidateDelayed(30L);
        } else {
            invalidate();
        }
    }

    public final void a() {
        if (this.t != 0 && System.currentTimeMillis() - this.t <= this.u) {
            return;
        }
        this.t = System.currentTimeMillis();
        this.i = (float) (((double) this.i) + 1.5d);
        float f = this.l;
        if (f < this.n && this.j) {
            this.l = f + (getHeight() / 30);
            return;
        }
        this.j = false;
        float f2 = this.l;
        if (f2 <= 10.0f) {
            this.l = 10.0f;
        } else if (f2 < getHeight() / 30) {
            this.l -= getHeight() / 60;
        } else {
            this.l -= getHeight() / 30;
        }
    }

    public final void b() {
        this.o += 6;
        float f = this.l;
        if (f < this.n && this.j) {
            this.l = f + (getHeight() / 30);
            return;
        }
        this.j = false;
        float f2 = this.l;
        if (f2 <= 10.0f) {
            this.l = 10.0f;
        } else if (f2 < getHeight() / 30) {
            this.l -= getHeight() / 60;
        } else {
            this.l -= getHeight() / 30;
        }
    }

    public VoiceLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4415a = -16777216;
        this.b = -16777216;
        this.c = 4.0f;
        this.g = 4;
        this.h = 100.0f;
        this.i = 0.0f;
        this.j = false;
        this.k = 1.0f;
        this.l = 10.0f;
        this.m = 1;
        this.n = 1.0f;
        this.o = 50L;
        this.p = 25.0f;
        this.q = 5.0f;
        this.r = 4.0f;
        this.t = 0L;
        this.u = 90;
        this.v = null;
        a(context, attributeSet);
    }
}
