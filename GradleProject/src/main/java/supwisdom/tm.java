package supwisdom;

import android.graphics.Canvas;
import android.view.View;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: MarkerViewDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class tm extends vm {
    public vn r;

    public tm(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.vm
    public void a(Canvas canvas, float[] fArr) {
        if (this.r == null || fArr == null || this.f9893a.getChartConfig().f.f8298a) {
            canvas.save();
            canvas.restore();
            return;
        }
        canvas.save();
        float[] fArr2 = ((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL ? new float[]{fArr[1], (float) this.f9893a.getChartData().e()} : new float[]{fArr[0], (float) this.f9893a.getChartData().c()};
        this.r.a(fArr);
        if (this.r.a().getVisibility() != 0) {
            canvas.restore();
            return;
        }
        this.r.a().measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.r.a().layout(0, 0, this.r.a().getMeasuredWidth(), this.r.a().getMeasuredHeight());
        this.f9893a.getTransformUtil().d(fArr2);
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            this.f9893a.getTransformUtil().b(fArr2);
            if (ao.c(this.f9893a, this.r.a(), fArr2, true)) {
                canvas.translate(fArr2[0], fArr2[1] - (this.r.a().getMeasuredHeight() / 2));
            } else {
                canvas.translate(fArr2[0], (fArr2[1] - (this.r.a().getMeasuredHeight() / 2)) - (ao.a(this.f9893a, this.r.a(), fArr2, true) * 1.2f));
            }
        } else if (ao.c(this.f9893a, this.r.a(), fArr2, false)) {
            canvas.translate(fArr2[0] - (this.r.a().getMeasuredWidth() / 2), fArr2[1]);
        } else {
            canvas.translate((fArr2[0] - (this.r.a().getMeasuredWidth() / 2)) - (ao.a(this.f9893a, this.r.a(), fArr2, false) * 1.2f), fArr2[1]);
        }
        this.r.a().draw(canvas);
        this.r.c();
        canvas.restore();
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.r = new rn(this.f9893a);
    }
}
