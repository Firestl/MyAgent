package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.util.AttributeSet;
import supwisdom.bm;
import supwisdom.cl;
import supwisdom.hl;
import supwisdom.nl;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class NightingaleRoseChart extends PolarChartBase {
    public hl D;

    public NightingaleRoseChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.PolarChartBase, com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        this.d = new cl(this);
        this.D = new hl(this);
        this.d.a((yl) new bm(this));
        this.d.a((yl) this.D);
        this.c = new nl();
    }

    @Override // com.alibaba.dt.AChartsLib.charts.PolarChartBase, com.alibaba.dt.AChartsLib.charts.Chart
    public void setSelectedIndex(Integer num) {
        this.i = num;
    }

    public NightingaleRoseChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
