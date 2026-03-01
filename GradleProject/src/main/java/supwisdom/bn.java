package supwisdom;

import android.graphics.Canvas;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: CrossMarkerViewDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class bn extends an {
    public qn w;

    public bn(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.an
    public void c(Canvas canvas, float[] fArr) {
        if (this.w == null || fArr == null || this.f9893a.getChartConfig().f.f8298a) {
            canvas.save();
            canvas.restore();
            return;
        }
        if (fArr[0] == 0.0f && fArr[1] == 0.0f) {
            return;
        }
        Float[] fArr2 = {Float.valueOf(((wk) this.f9893a.getChartData().f().get(this.v)).f().get(this.n).floatValue()), Float.valueOf(((xk) this.f9893a.getChartData().g().get(this.v)).i().get(this.n).b().floatValue())};
        this.w.a(new float[]{fArr2[0].floatValue(), fArr2[1].floatValue()}, this.v);
        canvas.save();
        this.w.a().measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.w.a().layout(0, 0, this.w.a().getMeasuredWidth(), this.w.a().getMeasuredHeight());
        if (ao.d(this.f9893a, this.w.a(), fArr, false)) {
            canvas.translate(fArr[0] - (this.w.a().getMeasuredWidth() / 2), fArr[1]);
        } else {
            canvas.translate((fArr[0] - (this.w.a().getMeasuredWidth() / 2)) - ao.a(this.f9893a, this.w.a(), fArr, false), fArr[1] - ao.b(this.f9893a, this.w.a(), fArr, false));
        }
        this.w.a().draw(canvas);
        this.w.c();
        this.w.a().draw(canvas);
        canvas.restore();
    }

    @Override // supwisdom.an, supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.w = new qn(this.f9893a);
    }
}
