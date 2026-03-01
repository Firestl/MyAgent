package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.Log;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.List;

/* JADX INFO: compiled from: HighlightBarDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class rm extends vm {
    public Paint r;

    public rm(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.vm
    public void a(Canvas canvas, float[] fArr) {
        float[] fArr2;
        if (fArr == null || this.f9893a.getChartConfig().g.f <= 0 || this.f9893a.getChartConfig().g.f8298a) {
            canvas.save();
            canvas.restore();
            return;
        }
        canvas.save();
        Path path = new Path();
        Path path2 = new Path();
        List<pk> listG = this.f9893a.getChartData().g();
        float f = tn.a(this.f9893a.getContext()).density;
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            for (int i = 0; i < fArr.length; i += 2) {
                path.moveTo(fArr[1], (float) this.f9893a.getChartData().e());
                path.lineTo(fArr[1], (float) this.f9893a.getChartData().c());
            }
            this.f9893a.getTransformUtil().b(path);
            this.f9893a.getTransformUtil().a(path);
        } else {
            float fJ = this.f9893a.getChartData() instanceof lk ? (float) ((lk) this.f9893a.getChartData()).j() : 0.0f;
            Float fValueOf = null;
            Float[] fArrA = null;
            for (pk pkVar : listG) {
                if (fArr[0] < pkVar.i().size() && fArr[0] >= 0.0f) {
                    yk<Double> ykVar = pkVar.i().get((int) fArr[0]);
                    if (ykVar.b() != null) {
                        float fFloatValue = ykVar.b().floatValue();
                        if (fValueOf == null || fFloatValue > fValueOf.floatValue()) {
                            fValueOf = Float.valueOf(fFloatValue);
                            fArrA = wn.a(this.f9893a, pkVar.b());
                        }
                    }
                }
            }
            if (fValueOf == null) {
                return;
            }
            float[] fArr3 = {fArr[0], fValueOf.floatValue()};
            float[] fArr4 = {fArr[0], (float) this.f9893a.getChartData().e()};
            if (fArr.length > 0) {
                float f2 = fArr[0] - 0.5f;
                if (fArr[0] == 0.0f) {
                    f2 = fArr[0] - fJ;
                }
                path.addRect(new RectF(f2, (float) this.f9893a.getChartData().e(), ((double) fArr[0]) == this.f9893a.getChartData().a() ? (float) this.f9893a.getChartData().a() : fArr[0] + 0.5f, (float) this.f9893a.getChartData().c()), Path.Direction.CW);
                path.setFillType(Path.FillType.WINDING);
            }
            if (fArrA != null) {
                fArr2 = fArr3;
                this.f9893a.getTransformUtil().a(fArr3, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArrA[0].floatValue(), fArrA[1].floatValue());
            } else {
                fArr2 = fArr3;
            }
            this.f9893a.getTransformUtil().a(fArr4, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), (float) this.f9893a.getChartData().e(), (float) this.f9893a.getChartData().c());
            path2.moveTo(fArr2[0], fArr2[1]);
            path2.lineTo(fArr2[0], fArr4[1]);
            this.r.setShader(new LinearGradient(0.0f, fArr2[1], 0.0f, fArr4[1], this.f9893a.getChartConfig().g.c, nn.a(this.f9893a.getChartConfig().g.c, 0.0f), Shader.TileMode.CLAMP));
            this.f9893a.getTransformUtil().b(path);
            Log.i("Test", "test");
        }
        if (this.f9893a.getChartConfig().g.b) {
            this.r.setPathEffect(new DashPathEffect(new float[]{10.0f, 4.0f, 10.0f, 4.0f}, 0.0f));
        }
        if (this.f9893a.getChartConfig().g.f > 0) {
            this.d.setShadowLayer(this.f9893a.getChartConfig().g.f * f, 0.0f, 0.0f, nn.a(this.f9893a.getChartConfig().g.f9187e, this.f9893a.getChartConfig().g.g.floatValue()));
        }
        this.d.setStrokeWidth(this.f9893a.getChartConfig().g.d);
        canvas.clipPath(path, Region.Op.XOR);
        canvas.drawPath(path, this.d);
        canvas.restore();
        canvas.drawPath(path2, this.r);
        canvas.restore();
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setAntiAlias(true);
        this.d.setColor(Color.parseColor("#ffffff"));
        this.d.setStyle(Paint.Style.FILL);
        this.d.setStrokeWidth(2.0f);
        Paint paint = new Paint();
        this.r = paint;
        paint.setAntiAlias(true);
        this.r.setColor(Color.parseColor(AMapUtil.HtmlBlack));
        this.r.setStyle(Paint.Style.STROKE);
        this.r.setStrokeWidth(4.0f);
    }
}
