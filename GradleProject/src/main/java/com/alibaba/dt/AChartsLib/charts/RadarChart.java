package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.util.AttributeSet;
import supwisdom.cl;
import supwisdom.il;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class RadarChart extends PolarChartBase {
    public il D;

    public RadarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.PolarChartBase, com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        this.d = new cl(this);
        il ilVar = new il(this);
        this.D = ilVar;
        this.d.a((yl) ilVar);
    }

    public RadarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
