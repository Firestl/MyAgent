package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.List;

/* JADX INFO: compiled from: PolarAxisDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class jm extends hm {
    public DashPathEffect u;
    public float[] v;

    public jm(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.hm
    public void b(Canvas canvas) {
        float fP;
        if (this.f9893a.getChartConfig().c.f8298a) {
            return;
        }
        ik ikVar = (ik) this.f9893a.getChartData();
        float fN = 0.0f;
        List<Double> listJ = ikVar.j();
        float f = 1.0f;
        int i = 0;
        if (ikVar.g() == null || ikVar.g().size() <= 0) {
            fP = 1.0f;
        } else {
            fN = ((sk) ikVar.g().get(0)).n();
            fP = ((sk) ikVar.g().get(0)).p();
        }
        RectF rectFA = a(this.m, fP);
        float f2 = this.r * fP;
        float f3 = this.s * fP;
        for (int i2 = 0; i2 < 5; i2++) {
            this.d.setStrokeWidth(2.0f);
            if (i2 % 2 > 0) {
                this.d.setPathEffect(this.u);
            } else {
                this.d.setPathEffect(null);
            }
            canvas.drawArc(a(this.m, ((fP - fN) * f) + fN), 0.0f, 360.0f, true, this.d);
            f -= 0.2f;
        }
        float fMin = rectFA.left + Math.min(f2, f3);
        float fMin2 = rectFA.top + Math.min(f2, f3);
        int size = listJ.size();
        this.d.setPathEffect(this.u);
        Path path = new Path();
        while (i < size) {
            Path path2 = new Path();
            path2.moveTo(fMin, fMin2);
            double d = (((double) (((((-360) / size) / 2) - ((i * 360) / size)) + 180)) * 3.141592653589793d) / 180.0d;
            path2.lineTo((float) (((double) fMin) + (((double) Math.min(f2, f3)) * Math.sin(d))), (float) (((double) fMin2) + (((double) Math.min(f2, f3)) * Math.cos(d))));
            path.addPath(path2);
            i++;
            fMin = fMin;
            fMin2 = fMin2;
        }
        canvas.drawPath(path, this.d);
    }

    @Override // supwisdom.hm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = false;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setColor(Color.parseColor("#e3e3e3"));
        this.d.setStrokeWidth(2.0f);
        this.v = new float[]{10.0f, 8.0f};
        this.u = new DashPathEffect(this.v, 5.0f);
    }
}
