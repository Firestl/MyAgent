package supwisdom;

import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;

/* JADX INFO: compiled from: StackedBarChartStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class kl extends bl {
    public kl(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.h = false;
        this.f9894e = true;
        a((yl) new AxisDecorator(this.f9893a));
        a((yl) new fm(this.f9893a));
        a((yl) new nm(this.f9893a));
        a((yl) new sm(this.f9893a));
        a((yl) new tm(this.f9893a));
    }
}
