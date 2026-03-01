package com.alibaba.dt.AChartsLib.chartStrategys;

import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;
import supwisdom.bl;
import supwisdom.fm;
import supwisdom.sm;
import supwisdom.tm;
import supwisdom.wm;
import supwisdom.yl;
import supwisdom.zl;
import supwisdom.zm;

/* JADX INFO: loaded from: classes.dex */
public class BarChartStrategy extends bl {

    public enum BarChartDirection {
        VERTICAL,
        HORIZONTAL
    }

    public BarChartStrategy(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.h = false;
        this.f9894e = true;
        a((yl) new AxisDecorator(this.f9893a));
        a((yl) new fm(this.f9893a));
        a((yl) new zl(this.f9893a));
        a((yl) new sm(this.f9893a));
        a((yl) new wm(this.f9893a));
        a((yl) new tm(this.f9893a));
        a((yl) new zm(this.f9893a));
    }
}
