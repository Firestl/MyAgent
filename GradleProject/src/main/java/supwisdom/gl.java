package supwisdom;

import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;

/* JADX INFO: compiled from: LineChartStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class gl extends bl {
    public gl(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.h = false;
        this.f9894e = true;
        a((yl) new AxisDecorator(this.f9893a));
        a((yl) new fm(this.f9893a));
        a((yl) new gm(this.f9893a));
        a((yl) new sm(this.f9893a));
        a((yl) new um(this.f9893a));
        a((yl) new wm(this.f9893a));
        a((yl) new tm(this.f9893a));
        a((yl) new zm(this.f9893a));
    }
}
