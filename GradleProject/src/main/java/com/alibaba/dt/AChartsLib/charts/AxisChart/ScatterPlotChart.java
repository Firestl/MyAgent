package com.alibaba.dt.AChartsLib.charts.AxisChart;

import android.content.Context;
import android.util.AttributeSet;
import supwisdom.bm;
import supwisdom.cl;
import supwisdom.jl;
import supwisdom.ol;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class ScatterPlotChart extends BaseAxisChart {
    public ScatterPlotChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart, com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        cl clVar = new cl(this);
        this.d = clVar;
        clVar.a((yl) new bm(this));
        this.d.a((yl) new jl(this));
        this.c = new ol();
    }

    public ScatterPlotChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
