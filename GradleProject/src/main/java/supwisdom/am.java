package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: BaseAxisDataDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class am extends BlockDecorator {
    public RectF m;

    public am(Chart chart) {
        super(chart);
    }

    public void b(Canvas canvas) {
        RectF rectF = new RectF();
        this.m = rectF;
        rectF.left = this.f9893a.getViewportHandler().c();
        this.m.top = this.f9893a.getViewportHandler().e();
        this.m.right = this.f9893a.getContentWidth() - this.f9893a.getViewportHandler().d();
        this.m.bottom = this.f9893a.getContentHeight() - this.f9893a.getViewportHandler().b();
        if (this.f9893a.getChartAnimator().b()) {
            this.d.setColor(-1);
            RectF rectF2 = this.m;
            float f = rectF2.left;
            rectF2.left = f + ((rectF2.right - f) * this.f9893a.getChartAnimator().a());
            canvas.drawRect(this.m, this.d);
        }
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
    }
}
