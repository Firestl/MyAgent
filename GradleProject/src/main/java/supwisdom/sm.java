package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: HighlightDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class sm extends vm {
    public sm(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.vm
    public void a(Canvas canvas, float[] fArr) {
        if (fArr == null || this.f9893a.getChartConfig().g.f8298a || this.f9893a.getChartConfig().g.f > 0) {
            canvas.save();
            canvas.restore();
            return;
        }
        canvas.save();
        Path path = new Path();
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            for (int i = 0; i < fArr.length; i += 2) {
                path.moveTo(fArr[1], (float) this.f9893a.getChartData().e());
                path.lineTo(fArr[1], (float) this.f9893a.getChartData().c());
            }
            this.f9893a.getTransformUtil().b(path);
            this.f9893a.getTransformUtil().a(path);
        } else {
            for (int i2 = 0; i2 < fArr.length; i2 += 2) {
                path.moveTo(fArr[0], (float) this.f9893a.getChartData().e());
                path.lineTo(fArr[0], (float) this.f9893a.getChartData().c());
            }
            this.f9893a.getTransformUtil().b(path);
        }
        if (this.f9893a.getChartConfig().g.b) {
            this.d.setPathEffect(new DashPathEffect(new float[]{10.0f, 4.0f, 10.0f, 4.0f}, 0.0f));
        }
        this.d.setColor(this.f9893a.getChartConfig().g.c);
        this.d.setStrokeWidth(this.f9893a.getChartConfig().g.d);
        canvas.drawPath(path, this.d);
        canvas.restore();
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setAntiAlias(true);
        this.d.setColor(Color.parseColor("#696969"));
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(2.0f);
    }
}
