package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import supwisdom.in;

/* JADX INFO: loaded from: classes.dex */
public class SourceGoalChart extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1541a;
    public String b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1542e;
    public float f;
    public Paint g;
    public List<Number> h;
    public int i;
    public Path j;

    public SourceGoalChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void a() {
        Paint paint = new Paint();
        this.g = paint;
        paint.setColor(Color.parseColor(this.b));
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setAntiAlias(true);
        this.j = new Path();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        a(canvas);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.d = this.f1541a ? 0.0f : getWidth();
        this.f1542e = this.f1541a ? getWidth() : 0.0f;
        this.f = getHeight() / 2;
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setData(List<Number> list) {
        if (list == null || list.size() == 0 || list.get(0) == null) {
            return;
        }
        this.h = list;
        this.i = list.size();
        invalidate();
    }

    public void setOption(in inVar) {
        this.f1541a = "left".equals(inVar.f7966a);
        String str = inVar.b;
        if (str != null && !"".equals(str)) {
            this.b = inVar.b;
        }
        Float f = inVar.c;
        if (f == null || f.floatValue() == 0.0f) {
            return;
        }
        this.c = inVar.c.floatValue();
    }

    public SourceGoalChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1541a = false;
        this.b = "#fef3e9";
        this.c = 20.0f;
        this.h = new ArrayList();
        a();
    }

    public final void a(Canvas canvas) {
        if (this.i == 0) {
            return;
        }
        this.d = this.f1541a ? 0.0f : getWidth();
        this.f1542e = this.f1541a ? getWidth() : 0.0f;
        double height = getHeight() / this.i;
        for (int i = 0; i < this.i; i++) {
            float f = (float) ((((double) i) + 0.5d) * height);
            this.j.moveTo(this.d, this.f);
            float f2 = this.d;
            float f3 = this.f1542e;
            float f4 = this.f;
            this.j.quadTo((f2 + f3) / 2.0f, (float) ((((double) (f4 + f)) * 0.5d) + (((double) (f - f4)) * 0.5d)), f3, f);
            this.g.setStrokeWidth((float) (((double) this.c) * this.h.get(i).doubleValue()));
            canvas.drawPath(this.j, this.g);
            this.j.reset();
        }
    }
}
