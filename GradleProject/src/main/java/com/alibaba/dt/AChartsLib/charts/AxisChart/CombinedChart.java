package com.alibaba.dt.AChartsLib.charts.AxisChart;

import android.content.Context;
import android.util.AttributeSet;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import supwisdom.bm;
import supwisdom.cl;
import supwisdom.el;
import supwisdom.ol;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class CombinedChart extends BaseAxisChart {
    public CombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart, com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        cl clVar = new cl(this);
        this.d = clVar;
        clVar.a((yl) new bm(this));
        this.d.a((yl) new el(this));
        this.c = new ol();
    }

    public void setBarOriention(BarChartStrategy.BarChartDirection barChartDirection) {
        ((cl) this.d).a(barChartDirection);
    }

    public CombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
