package com.alibaba.dt.AChartsLib.charts.AxisChart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.interfaces.ChartTouchListener;
import supwisdom.ck;
import supwisdom.cn;

/* JADX INFO: loaded from: classes.dex */
public class BaseAxisChart extends Chart {
    public ck z;

    public BaseAxisChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        this.z = new ck();
        this.s = new cn(this);
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

    @Override // com.alibaba.dt.AChartsLib.charts.Chart, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.c.f8539e) {
            return super.onTouchEvent(motionEvent);
        }
        ChartTouchListener chartTouchListener = this.s;
        if (chartTouchListener == null || this.o == 0) {
            return false;
        }
        return chartTouchListener.onTouch(this, motionEvent);
    }

    public BaseAxisChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
