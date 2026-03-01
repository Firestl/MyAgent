package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.alibaba.dt.AChartsLib.chartStrategys.PolarChartStrategy;
import supwisdom.bm;
import supwisdom.ck;
import supwisdom.cl;
import supwisdom.nl;
import supwisdom.qm;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class PolarChartBase extends Chart {
    public PolarChartStrategy A;
    public qm B;
    public bm C;
    public ck z;

    public PolarChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        this.d = new cl(this);
        this.A = new PolarChartStrategy(this);
        this.B = new qm(this);
        this.C = new bm(this);
        this.d.a((yl) this.B);
        this.d.a((yl) this.C);
        this.d.a((yl) this.A);
        this.c = new nl();
        this.z = new ck();
    }

    public PolarChartStrategy getPolarChartStrategy() {
        return this.A;
    }

    public qm getTitleDecorator() {
        return this.B;
    }

    public ck getmChartGraphicBuffer() {
        return this.z;
    }

    @Override // com.alibaba.dt.AChartsLib.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.z.a()) {
            this.z.a(false);
        }
    }

    public void setPolarChartStrategy(PolarChartStrategy polarChartStrategy) {
        this.A = polarChartStrategy;
    }

    @Override // com.alibaba.dt.AChartsLib.charts.Chart
    public void setSelectedIndex(Integer num) {
        this.i = num;
    }

    public void setTitleDecorator(qm qmVar) {
        this.B = qmVar;
    }

    public PolarChartBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
