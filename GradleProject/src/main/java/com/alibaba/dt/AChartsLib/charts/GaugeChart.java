package com.alibaba.dt.AChartsLib.charts;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import supwisdom.hn;
import supwisdom.ql;
import supwisdom.tn;

/* JADX INFO: loaded from: classes.dex */
public class GaugeChart extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f1533a;
    public Paint b;
    public Paint c;
    public Paint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f1534e;
    public Paint f;
    public Paint g;
    public ql h;
    public float i;
    public float j;
    public RectF k;
    public Path l;
    public float m;
    public float n;
    public String o;
    public float p;
    public float q;
    public Rect r;
    public hn s;
    public hn t;

    public GaugeChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void a() {
        this.h = new ql();
        this.l = new Path();
        Paint paint = new Paint();
        this.f1533a = paint;
        paint.setAntiAlias(true);
        this.f1533a.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.b = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.c = paint3;
        paint3.setAntiAlias(true);
        Paint paint4 = new Paint();
        this.g = paint4;
        paint4.setAntiAlias(true);
        this.g.setColor(-16777216);
        this.g.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.d = paint5;
        paint5.setColor(-16777216);
        this.d.setAntiAlias(true);
        Paint paint6 = new Paint();
        this.f1534e = paint6;
        paint6.setColor(-16777216);
        this.f1534e.setAntiAlias(true);
        Paint paint7 = new Paint();
        this.f = paint7;
        paint7.setAntiAlias(true);
        this.s = new hn(null, 2);
        this.t = new hn(null, 2);
        ql qlVar = this.h;
        this.q = qlVar.h - qlVar.g;
        float fFloatValue = qlVar.z.floatValue();
        this.p = fFloatValue;
        this.o = this.s.a(String.valueOf(fFloatValue));
    }

    public final void b(Canvas canvas) {
        this.f1533a.setStrokeWidth(tn.a(getContext(), this.h.b));
        ql qlVar = this.h;
        double[] dArr = qlVar.n;
        int length = dArr.length;
        int length2 = qlVar.m.length;
        if (length == 0) {
            this.f1533a.setColor(ql.C);
            canvas.drawArc(this.k, this.h.g, this.q, false, this.f1533a);
            return;
        }
        double d = qlVar.j - qlVar.i;
        float f = qlVar.g;
        int i = 0;
        float f2 = (float) (((double) f) * (dArr[0] / d));
        while (i < length) {
            double d2 = this.q;
            double[] dArr2 = this.h.n;
            f2 = (float) (d2 * ((dArr2[i] - (i > 0 ? dArr2[i - 1] : 0.0d)) / d));
            ql qlVar2 = this.h;
            f = (float) (((double) qlVar2.g) + ((((double) this.q) * (i > 0 ? qlVar2.n[i - 1] : 0.0d)) / d));
            this.f1533a.setColor(i < length2 ? this.h.m[i] : ql.C);
            canvas.drawArc(this.k, f, f2, false, this.f1533a);
            i++;
        }
        float f3 = f + f2;
        ql qlVar3 = this.h;
        if (f3 != qlVar3.h) {
            this.f1533a.setColor(i < length2 ? qlVar3.m[i] : ql.C);
            canvas.drawArc(this.k, f3, this.h.h - f3, false, this.f1533a);
        }
    }

    public final void c(Canvas canvas) {
        ql qlVar = this.h;
        double d = (qlVar.j - qlVar.i) / ((double) qlVar.k);
        float fA = (this.i - tn.a(getContext(), this.h.b)) - tn.a(getContext(), this.h.t);
        this.f.setTextSize(tn.a(getContext(), this.h.s));
        int i = 0;
        int i2 = 0;
        while (true) {
            ql qlVar2 = this.h;
            if (i2 > qlVar2.k) {
                return;
            }
            double d2 = (((double) i2) * d) + qlVar2.i;
            String strA = this.t.a(String.valueOf(d2));
            this.f.getTextBounds(strA, i, strA.length(), this.r);
            ql qlVar3 = this.h;
            double radians = Math.toRadians(qlVar3.g + ((this.q / qlVar3.k) * i2));
            double d3 = fA;
            float fCos = ((float) (((double) this.m) + (Math.cos(radians) * d3))) - (this.r.width() / 2);
            float fSin = (float) (((double) this.n) + (d3 * Math.sin(radians)) + ((double) (this.r.height() / 2)));
            this.f.setColor(a(Double.valueOf(d2)));
            canvas.drawText(strA, fCos, fSin, this.f);
            i2++;
            i = 0;
        }
    }

    public final void d(Canvas canvas) {
        double d = this.p;
        ql qlVar = this.h;
        double d2 = qlVar.i;
        double radians = Math.toRadians((((d - d2) / (qlVar.j - d2)) * ((double) this.q)) + ((double) qlVar.g));
        this.l.reset();
        this.l.moveTo((float) (((double) this.m) - (Math.cos(radians) * 30.0d)), (float) (((double) this.n) - (Math.sin(radians) * 30.0d)));
        this.l.lineTo((float) (((double) this.m) - (Math.sin(radians) * 20.0d)), (float) (((double) this.n) + (Math.cos(radians) * 20.0d)));
        this.l.lineTo((float) (((double) this.m) + (((double) this.j) * Math.cos(radians))), (float) (((double) this.n) + (((double) this.j) * Math.sin(radians))));
        this.l.lineTo((float) (((double) this.m) + (Math.sin(radians) * 20.0d)), (float) (((double) this.n) - (Math.cos(radians) * 20.0d)));
        this.l.close();
        this.g.setColor(a(Float.valueOf(this.p)));
        canvas.drawPath(this.l, this.g);
    }

    public final void e(Canvas canvas) {
        a(canvas, this.h.g, this.q, tn.a(getContext(), this.h.d), this.h.k, true);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        b(canvas);
        e(canvas);
        a(canvas);
        c(canvas);
        d(canvas);
        this.d.setTextSize(tn.a(getContext(), this.h.v));
        ql qlVar = this.h;
        String str = qlVar.u;
        canvas.drawText(str, (this.m + (this.i * qlVar.w)) - (this.d.measureText(str) / 2.0f), this.n + (this.i * this.h.x), this.d);
        this.f1534e.setTextSize(tn.a(getContext(), this.h.y));
        String str2 = this.o;
        canvas.drawText(str2, this.m - (this.f1534e.measureText(str2) / 2.0f), (float) (((double) this.n) + (((double) this.i) / 1.7d)), this.f1534e);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(900, size) : 900;
        }
        if (mode2 != 1073741824) {
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(900, size2) : 900;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.m = (getWidth() / 2) * ((this.h.A / 100.0f) + 1.0f);
        this.n = (getHeight() / 2) * ((this.h.B / 100.0f) + 1.0f);
        this.i = (float) (((double) Math.min(getWidth() / 2, getHeight() / 2)) * this.h.f8932a);
        float f = this.m;
        float f2 = this.i;
        float f3 = this.n;
        this.k = new RectF(f - f2, f3 - f2, f + f2, f3 + f2);
        this.j = (float) (((double) this.i) * 0.7d);
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setData(Number number) {
        a(number, true);
    }

    public void setGaugeConfig(ql qlVar) {
        this.h = qlVar;
        this.m = (getWidth() / 2) * ((this.h.A / 100.0f) + 1.0f);
        this.n = (getHeight() / 2) * ((this.h.B / 100.0f) + 1.0f);
        this.i = (float) (((double) Math.min(getWidth() / 2, getHeight() / 2)) * this.h.f8932a);
        float f = this.m;
        float f2 = this.i;
        float f3 = this.n;
        this.k = new RectF(f - f2, f3 - f2, f + f2, f3 + f2);
        this.j = (float) (((double) this.i) * 0.7d);
        ql qlVar2 = this.h;
        this.q = qlVar2.h - qlVar2.g;
    }

    public void setLabelFormatter(hn hnVar) {
        this.t = hnVar;
    }

    public void setValue(float f) {
        this.p = f;
        invalidate();
    }

    public void setValueFormatter(hn hnVar) {
        this.s = hnVar;
    }

    public GaugeChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = new Rect();
        a();
    }

    public final void a(Canvas canvas) {
        float f = this.q / this.h.k;
        int i = 0;
        while (true) {
            ql qlVar = this.h;
            if (i >= qlVar.k) {
                return;
            }
            a(canvas, qlVar.g + (i * f), f, tn.a(getContext(), this.h.c), this.h.l, false);
            i++;
        }
    }

    public final void a(Canvas canvas, float f, float f2, float f3, int i, boolean z) {
        int i2 = i;
        float f4 = f2 / i2;
        float fA = this.i + tn.a(getContext(), this.h.b / 2.0f);
        int i3 = 0;
        while (i3 <= i2) {
            double d = f + (i3 * f4);
            double radians = Math.toRadians(d);
            double d2 = fA;
            float fCos = (float) (((double) this.m) + (Math.cos(radians) * d2));
            float fSin = (float) (((double) this.n) + (d2 * Math.sin(radians)));
            double d3 = fA - f3;
            float fCos2 = (float) (((double) this.m) + (Math.cos(radians) * d3));
            float f5 = f4;
            float fSin2 = (float) (((double) this.n) + (d3 * Math.sin(radians)));
            if (z) {
                this.b.setStrokeWidth(tn.a(getContext(), (int) this.h.f));
                Paint paint = this.b;
                ql qlVar = this.h;
                paint.setColor(qlVar.q ? a(d) : qlVar.o);
                canvas.drawLine(fCos, fSin, fCos2, fSin2, this.b);
            } else {
                this.c.setStrokeWidth(tn.a(getContext(), (int) this.h.f8933e));
                Paint paint2 = this.c;
                ql qlVar2 = this.h;
                paint2.setColor(qlVar2.r ? a(d) : qlVar2.p);
                canvas.drawLine(fCos, fSin, fCos2, fSin2, this.c);
            }
            i3++;
            f4 = f5;
            i2 = i;
        }
    }

    public int a(Number number) {
        int length = this.h.n.length;
        int i = 0;
        while (i < length) {
            double dDoubleValue = number.doubleValue();
            ql qlVar = this.h;
            if (dDoubleValue <= qlVar.n[i]) {
                return qlVar.m[i];
            }
            i++;
        }
        int[] iArr = this.h.m;
        return i < iArr.length ? iArr[i] : ql.C;
    }

    public int a(double d) {
        int length = this.h.n.length;
        int i = 0;
        while (i < length) {
            ql qlVar = this.h;
            if (((d - ((double) qlVar.g)) / ((double) this.q)) * (qlVar.j - qlVar.i) <= qlVar.n[i]) {
                return qlVar.m[i];
            }
            i++;
        }
        int[] iArr = this.h.m;
        return i < iArr.length ? iArr[i] : ql.C;
    }

    public void a(Number number, boolean z) {
        this.f1534e.setColor(a(number));
        this.o = this.s.a(String.valueOf(number));
        double dDoubleValue = number.doubleValue();
        double d = this.h.i;
        if (dDoubleValue < d) {
            number = Float.valueOf((float) d);
        }
        double dDoubleValue2 = number.doubleValue();
        double d2 = this.h.j;
        if (dDoubleValue2 > d2) {
            number = Float.valueOf((float) d2);
        }
        if (z) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "value", this.p, number.floatValue());
            objectAnimatorOfFloat.setDuration(400L);
            objectAnimatorOfFloat.start();
            return;
        }
        setValue(number.floatValue());
    }
}
