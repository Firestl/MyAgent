package supwisdom;

import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;

/* JADX INFO: compiled from: ScatterChartStragy.java */
/* JADX INFO: loaded from: classes.dex */
public class jl extends bl {
    public jl(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        a((yl) new AxisDecorator(this.f9893a));
        a((yl) new fm(this.f9893a));
        a((yl) new mm(this.f9893a));
        a((yl) new an(this.f9893a));
        a((yl) new bn(this.f9893a));
    }
}
