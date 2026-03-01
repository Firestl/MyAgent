package com.alibaba.dt.AChartsLib.charts.AxisChart;

import android.content.Context;
import android.util.AttributeSet;
import supwisdom.bm;
import supwisdom.cl;
import supwisdom.kl;
import supwisdom.qm;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class StackedBarChart extends BaseAxisChart {
    public StackedBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart, com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        cl clVar = new cl(this);
        this.d = clVar;
        clVar.a((yl) new qm(this));
        this.d.a((yl) new bm(this));
        this.d.a((yl) new kl(this));
    }

    public StackedBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
