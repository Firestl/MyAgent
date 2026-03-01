package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: FullNodeDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class dm extends BlockDecorator {
    public dm(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        b(canvas);
        canvas.restore();
        return this;
    }

    public void b(Canvas canvas) {
        for (pk pkVar : this.f9893a.getChartData().g()) {
            if (pkVar.m) {
                for (int i = 0; i < pkVar.i().size(); i++) {
                    yk<Double> ykVar = pkVar.i().get(i);
                    if (ykVar.b() != null) {
                        Float[] fArrA = wn.a(this.f9893a, pkVar.b());
                        float[] fArr = {i, ykVar.b().floatValue()};
                        this.f9893a.getTransformUtil().a(fArr, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArrA[0].floatValue(), fArrA[1].floatValue());
                        if (j()) {
                            this.f9893a.getTransformUtil().b(fArr);
                        }
                        a(canvas, pkVar, fArr);
                    }
                }
            }
        }
    }

    public final void a(Canvas canvas, pk pkVar, float[] fArr) {
        this.d.setColor(pkVar.d());
        canvas.drawCircle(fArr[0], fArr[1], pkVar.o, this.d);
        this.d.setColor(pkVar.p);
        this.d.setShadowLayer(0.0f, 3.0f, 0.0f, Color.parseColor("#4C333333"));
        canvas.drawCircle(fArr[0], fArr[1], pkVar.n, this.d);
    }
}
