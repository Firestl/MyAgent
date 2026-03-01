package supwisdom;

import android.graphics.Canvas;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: BlockChartStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class cl extends dl {
    public BarChartStrategy.BarChartDirection p;

    public cl(Chart chart) {
        super(chart);
        this.p = BarChartStrategy.BarChartDirection.VERTICAL;
    }

    public BarChartStrategy.BarChartDirection p() {
        return this.p;
    }

    public void a(BarChartStrategy.BarChartDirection barChartDirection) {
        this.p = barChartDirection;
    }

    @Override // supwisdom.dl, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public dl a(Canvas canvas) {
        if (this.f9893a.getChartData() == null) {
            return this;
        }
        super.a(canvas);
        bo viewportHandler = this.f9893a.getViewportHandler();
        viewportHandler.g();
        for (yl ylVar : this.m) {
            if (ylVar instanceof BlockDecorator) {
                b((BlockDecorator) ylVar);
            }
            ylVar.a(canvas);
        }
        viewportHandler.f();
        return this;
    }
}
