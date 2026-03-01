package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.List;

/* JADX INFO: compiled from: RadarDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class lm extends hm {
    public lm(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.hm
    public void b(Canvas canvas) {
        float fP;
        uk ukVar;
        kk kkVar;
        float f;
        Path path;
        lm lmVar = this;
        kk kkVar2 = (kk) lmVar.f9893a.getChartData();
        List<uk> listG = kkVar2.g();
        List<Double> listJ = kkVar2.j();
        kkVar2.c();
        kkVar2.e();
        if (kkVar2.g() == null || kkVar2.g().size() <= 0) {
            fP = 1.0f;
        } else {
            ((sk) kkVar2.g().get(0)).n();
            fP = ((sk) kkVar2.g().get(0)).p();
        }
        RectF rectFA = lmVar.a(lmVar.m, fP);
        float f2 = lmVar.r * fP;
        float f3 = lmVar.s * fP;
        float fMin = rectFA.left + Math.min(f2, f3);
        float fMin2 = rectFA.top + Math.min(f2, f3);
        int size = ((tk) kkVar2.f().get(0)).d().size();
        for (uk ukVar2 : listG) {
            Path path2 = new Path();
            int i = 0;
            while (i < ukVar2.i().size()) {
                yk<Double> ykVar = ukVar2.i().get(i);
                if (ykVar.b() == null) {
                    kkVar = kkVar2;
                    f = fMin2;
                    ukVar = ukVar2;
                    path = path2;
                } else {
                    ukVar = ukVar2;
                    Path path3 = path2;
                    kkVar = kkVar2;
                    double d = (((double) (((i * 360) / size) + 180)) * 3.141592653589793d) / 180.0d;
                    float fMin3 = (float) (((double) fMin) + ((((double) Math.min(f2, f3)) / listJ.get(i).doubleValue()) * (ykVar.b().doubleValue() - kkVar2.e()) * Math.sin(d)));
                    f = fMin2;
                    float fMin4 = (float) (((double) fMin2) + ((((double) Math.min(f2, f3)) / listJ.get(i).doubleValue()) * (ykVar.b().doubleValue() - kkVar.e()) * Math.cos(d)));
                    if (i == 0) {
                        path = path3;
                        path.moveTo(fMin3, fMin4);
                    } else {
                        path = path3;
                        path.lineTo(fMin3, fMin4);
                    }
                }
                i++;
                path2 = path;
                fMin2 = f;
                ukVar2 = ukVar;
                kkVar2 = kkVar;
            }
            kk kkVar3 = kkVar2;
            Path path4 = path2;
            path4.close();
            lmVar = this;
            lmVar.d.setColor(ukVar2.d());
            lmVar.d.setStrokeWidth(r16.o());
            canvas.drawPath(path4, lmVar.d);
            kkVar2 = kkVar3;
        }
        if (lmVar.f9893a.getChartConfig().h) {
            float fA = (lmVar.f9893a.getChartAnimator().a() - 1.0f) * 360.0f;
            lmVar.d.setColor(-12236506);
            lmVar.d.setStyle(Paint.Style.FILL);
            lmVar.d.setXfermode(lmVar.t);
            canvas.drawArc(lmVar.m, 270.0f, fA, true, lmVar.d);
            lmVar.d.reset();
        }
        lmVar.d.setAntiAlias(true);
        lmVar.d.setStyle(Paint.Style.STROKE);
    }

    @Override // supwisdom.hm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = false;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
    }
}
