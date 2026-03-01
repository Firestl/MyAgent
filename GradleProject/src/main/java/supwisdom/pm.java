package supwisdom;

import android.graphics.Canvas;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: SubViewDecorateor.java */
/* JADX INFO: loaded from: classes.dex */
public class pm extends BlockDecorator {
    public pm(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.f9893a.getChartConfig().l) {
            return super.a(canvas);
        }
        l();
        return super.a(canvas);
    }

    public void l() {
        if (this.f9893a.getChartData() == null) {
            return;
        }
        bo viewportHandler = this.f9893a.getViewportHandler();
        float contentWidth = (this.f9893a.getContentWidth() - viewportHandler.d()) - viewportHandler.c();
        this.f9893a.getContentHeight();
        viewportHandler.b();
        viewportHandler.e();
        float[] fArr = new float[9];
        this.f9893a.getScaleAndTransMatrix().getValues(fArr);
        float f = fArr[0];
        float f2 = fArr[4];
        float f3 = fArr[2];
        float f4 = fArr[5];
        float f5 = -f3;
        float f6 = (f5 + contentWidth) / fArr[0];
        if (f5 != 0.0f) {
            f5 /= fArr[0];
        }
        if (f6 > contentWidth) {
            f6 = contentWidth;
        }
        float fA = (float) ((this.f9893a.getChartData().a() - this.f9893a.getChartData().d()) / ((double) contentWidth));
        float fAbs = f5 * fA;
        float f7 = f6 * fA;
        if (fAbs > f7) {
            fAbs = f7 - Math.abs(f7 - fAbs);
        }
        if (this.f9893a.getGestureSubViewListener() != null) {
            this.f9893a.getGestureSubViewListener().a(fAbs, f7);
        }
    }
}
