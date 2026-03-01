package supwisdom;

import com.alibaba.dt.AChartsLib.chartStrategys.PolarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: RadarChartStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class il extends PolarChartStrategy {
    public il(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.chartStrategys.PolarChartStrategy, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.m.clear();
        a((yl) new km(this.f9893a));
        a((yl) new lm(this.f9893a));
        a((yl) new xm(this.f9893a));
    }
}
