package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: SubMarkDecorateor.java */
/* JADX INFO: loaded from: classes.dex */
public class om extends BlockDecorator {
    public om(Chart chart) {
        super(chart);
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setStrokeWidth(2.0f);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (!this.f9893a.getChartConfig().l) {
            return super.a(canvas);
        }
        b(canvas);
        return this;
    }

    public void b(Canvas canvas) {
        canvas.save();
        this.f9893a.getChartData().f();
        Path path = new Path();
        path.addRect(this.f9893a.getStartIndex(), (float) this.f9893a.getChartData().e(), this.f9893a.getEndIndex(), (float) this.f9893a.getChartData().c(), Path.Direction.CCW);
        this.f9893a.getTransformUtil().b(path);
        this.d.setColor(571548228);
        canvas.drawPath(path, this.d);
        canvas.restore();
    }
}
