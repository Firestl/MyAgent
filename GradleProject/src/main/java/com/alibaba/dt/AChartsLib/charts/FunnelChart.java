package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.util.AttributeSet;
import supwisdom.cl;
import supwisdom.fl;
import supwisdom.pl;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class FunnelChart extends Chart {
    public fl A;
    public pl z;

    public FunnelChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.alibaba.dt.AChartsLib.charts.Chart
    public void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        this.d = new cl(this);
        fl flVar = new fl(this);
        this.A = flVar;
        this.d.a((yl) flVar);
    }

    public pl getFunnelConfig() {
        if (this.z == null) {
            this.z = new pl();
        }
        return this.z;
    }

    public void setFunnelConfig(pl plVar) {
        this.z = plVar;
    }

    public FunnelChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
