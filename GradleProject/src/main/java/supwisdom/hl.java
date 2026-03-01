package supwisdom;

import com.alibaba.dt.AChartsLib.chartStrategys.PolarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: NightingaleRoseChartStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class hl extends PolarChartStrategy {
    public hl(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.chartStrategys.PolarChartStrategy, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.m.clear();
        a((yl) new jm(this.f9893a));
        a((yl) new hm(this.f9893a));
        a((yl) new ym(this.f9893a));
        a((yl) new xm(this.f9893a));
    }
}
