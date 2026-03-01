package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;

/* JADX INFO: compiled from: NodeCircleDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class um extends vm {
    public um(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.vm
    public void a(Canvas canvas, float[] fArr) {
        if (fArr == null || this.f9893a.getChartConfig().g.f8298a) {
            canvas.save();
            canvas.restore();
            return;
        }
        canvas.save();
        for (pk pkVar : this.f9893a.getChartData().g()) {
            if (fArr[0] < pkVar.i().size() && fArr[0] >= 0.0f && pkVar.k) {
                yk<Double> ykVar = pkVar.i().get((int) fArr[0]);
                if (ykVar.b() != null) {
                    Float[] fArrA = wn.a(this.f9893a, pkVar.b());
                    float[] fArr2 = {fArr[0], ykVar.b().floatValue()};
                    this.f9893a.getTransformUtil().a(fArr2, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArrA[0].floatValue(), fArrA[1].floatValue());
                    if (j()) {
                        this.f9893a.getTransformUtil().b(fArr2);
                    }
                    this.d.setColor(Color.parseColor("#7f7f7f"));
                    canvas.drawCircle(fArr2[0], fArr2[1], this.f9893a.getChartConfig().g.i + 1, this.d);
                    if (this.f9893a.getChartConfig().g.h.floatValue() > 0.0f) {
                        this.d.setShadowLayer(this.f9893a.getChartConfig().g.i * this.f9893a.getChartConfig().g.h.floatValue(), 0.0f, 0.0f, nn.a(0, this.f9893a.getChartConfig().g.g.floatValue()));
                    }
                    this.d.reset();
                    this.d.setStyle(Paint.Style.FILL_AND_STROKE);
                    this.d.setColor(Color.parseColor("#ffffff"));
                    canvas.drawCircle(fArr2[0], fArr2[1], this.f9893a.getChartConfig().g.i, this.d);
                    this.d.setColor(pkVar.w);
                    canvas.drawCircle(fArr2[0], fArr2[1], this.f9893a.getChartConfig().g.i - (this.f9893a.getChartConfig().g.i / 3), this.d);
                }
            }
        }
        canvas.restore();
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setColor(DefaultImageHeaderParser.VP8_HEADER_MASK);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.d.setStrokeWidth(1.0f);
    }
}
