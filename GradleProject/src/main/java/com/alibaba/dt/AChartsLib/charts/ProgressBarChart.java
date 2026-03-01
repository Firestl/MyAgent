package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class ProgressBarChart extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1535a;
    public int b;
    public Paint c;
    public Paint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f1536e;
    public float f;
    public b g;
    public String h;
    public final RectF i;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1537a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1538e;
        public int f;
        public boolean g;
        public boolean h;
        public int i;
        public int j;

        public static final class a {
            public boolean g;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1539a = 0;
            public int b = 0;
            public int c = 0;
            public int d = 0;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public int f1540e = 0;
            public int f = 0;
            public boolean h = false;
            public int i = 0;
            public int j = 0;

            public b a() {
                return new b(this);
            }
        }

        public b(a aVar) {
            this.f1537a = aVar.f1539a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.f1538e = aVar.f1540e;
            this.f = aVar.f;
            this.g = aVar.g;
            this.h = aVar.h;
            this.i = aVar.i;
            this.j = aVar.j;
        }
    }

    public ProgressBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new RectF();
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (context == null) {
            return;
        }
        this.g = new b.a().a();
        Paint paint = new Paint();
        this.c = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.d = paint2;
        paint2.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
        this.f1536e = new Paint(1);
    }

    public final void b(Canvas canvas, float f) {
        if (this.g.h) {
            float f2 = this.b - ((this.g.i + f) * 2.0f);
            if (f2 < 0.0f) {
                f2 = f * 2.0f;
            }
            this.i.set(this.g.i, (f2 * (1.0f - this.f)) + this.g.i, this.f1535a - this.g.i, this.b - this.g.i);
            canvas.drawRoundRect(this.i, f, f, this.c);
            return;
        }
        float f3 = this.f1535a - ((this.g.i + f) * 2.0f);
        if (f3 < 0.0f) {
            f3 = f * 2.0f;
        }
        this.i.set(this.g.i, this.g.i, (f3 * this.f) + (2.0f * f) + this.g.i, this.b - this.g.i);
        canvas.drawRoundRect(this.i, f, f, this.c);
    }

    public b getConfig() {
        return this.g;
    }

    public float getPercent() {
        return this.f;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getContext() == null || this.g == null) {
            return;
        }
        canvas.save();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        float f = measuredHeight;
        float f2 = f / 2.0f;
        if (this.g.h) {
            f2 = measuredWidth / 2.0f;
        }
        float f3 = f2;
        this.f1535a = measuredWidth;
        this.b = measuredHeight;
        RectF rectF = this.i;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        float f4 = measuredWidth;
        rectF.right = f4;
        rectF.bottom = f;
        this.c.setColor(this.g.f1537a);
        a(canvas, f3);
        if (this.g.f1538e != 0 || this.g.f != 0) {
            this.c.setShader(new LinearGradient(0.0f, 0.0f, f4, f, new int[]{this.g.f1538e, this.g.f}, (float[]) null, Shader.TileMode.CLAMP));
        }
        b(canvas, f3);
        a(canvas);
    }

    public void setConfig(b bVar) {
        this.g = bVar;
        invalidate();
    }

    public void setPercent(float f) {
        float fMax = Math.max(0.0f, Math.min(1.0f, f));
        if (this.f != fMax) {
            this.f = fMax;
            invalidate();
        }
    }

    public final void a(Canvas canvas) {
        if (this.g.g) {
            int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            RectF rectF = this.i;
            rectF.left = 0.0f;
            rectF.top = 0.0f;
            rectF.right = measuredWidth;
            rectF.bottom = measuredHeight;
            this.f1536e.setTextSize(this.g.c);
            this.f1536e.setColor(this.g.d);
            String str = String.format("%.2f", Float.valueOf(this.f * 100.0f)) + "%";
            this.h = str;
            canvas.drawText(this.h, (this.f1535a / 2) - (this.f1536e.measureText(str) / 2.0f), (int) ((this.b / 2.0f) - ((this.f1536e.descent() + this.f1536e.ascent()) / 2.0f)), this.f1536e);
        }
    }

    public final void a(Canvas canvas, float f) {
        if (this.g.i > 0) {
            this.d.setStyle(Paint.Style.FILL);
            this.d.setColor(this.g.j);
            canvas.drawRoundRect(this.i, f, f, this.d);
        }
        if (this.g.b != 0) {
            this.d.setStyle(Paint.Style.FILL);
            this.d.setColor(this.g.b);
            this.i.set(this.g.i, this.g.i, this.f1535a - this.g.i, this.b - this.g.i);
            canvas.drawRoundRect(this.i, f, f, this.d);
        }
    }
}
