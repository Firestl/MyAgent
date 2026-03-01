package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import io.dcloud.common.util.TitleNViewUtil;
import java.util.List;

/* JADX INFO: compiled from: RadarAxisDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class km extends lm {
    public boolean u;
    public RectF v;

    public km(Chart chart) {
        super(chart);
        this.u = false;
        this.v = new RectF();
    }

    public void a(Canvas canvas, float f, float f2, float f3, float f4, String str) {
        this.d.setTextSize(35.0f);
        this.d.measureText(str);
        Paint.FontMetrics fontMetrics = this.d.getFontMetrics();
        float f5 = (fontMetrics.top - fontMetrics.bottom) / 3.0f;
        float f6 = f - f3;
        float f7 = f2 - f4;
        if (Math.abs(f6) < 30.0f) {
            this.d.setTextAlign(Paint.Align.CENTER);
        } else if (f6 > 0.0f) {
            this.d.setTextAlign(Paint.Align.LEFT);
        } else {
            this.d.setTextAlign(Paint.Align.RIGHT);
        }
        float f8 = f7 > 0.0f ? f2 - (f5 * 3.0f) : f2 + (f5 * 2.0f);
        this.d.setStyle(Paint.Style.FILL);
        canvas.drawText(str, f, f8, this.d);
    }

    @Override // supwisdom.lm, supwisdom.hm
    public void b(Canvas canvas) {
        float fN;
        float fP;
        int i;
        kk kkVar;
        float f;
        float f2;
        float f3;
        km kmVar = this;
        bo viewportHandler = kmVar.f9893a.getViewportHandler();
        kmVar.v.left = viewportHandler.c();
        kmVar.v.top = viewportHandler.e();
        kmVar.v.right = kmVar.f9893a.getContentWidth() - viewportHandler.d();
        kmVar.v.bottom = kmVar.f9893a.getContentHeight() - viewportHandler.b();
        kk kkVar2 = (kk) kmVar.f9893a.getChartData();
        kkVar2.c();
        kkVar2.e();
        if (kkVar2.g() == null || kkVar2.g().size() <= 0) {
            fN = 0.0f;
            fP = 1.0f;
        } else {
            fN = ((sk) kkVar2.g().get(0)).n();
            fP = ((sk) kkVar2.g().get(0)).p();
        }
        RectF rectFA = kmVar.a(kmVar.m, fP);
        float f4 = kmVar.r * fP;
        float f5 = kmVar.s * fP;
        float fMin = rectFA.left + Math.min(f4, f5);
        float fMin2 = rectFA.top + Math.min(f4, f5);
        int size = ((tk) kkVar2.f().get(0)).d().size();
        float f6 = 2.0f;
        int i2 = 5;
        if (kmVar.u) {
            int i3 = 0;
            float f7 = 1.0f;
            while (i3 < i2) {
                kmVar.d.setStrokeWidth(f6);
                if (i3 % 2 != 0) {
                    kmVar.d.setStyle(Paint.Style.FILL_AND_STROKE);
                    kmVar.d.setColor(Color.parseColor("#e3e3e3"));
                } else {
                    kmVar.d.setStyle(Paint.Style.FILL_AND_STROKE);
                    kmVar.d.setColor(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR));
                }
                canvas.drawArc(kmVar.a(kmVar.m, ((fP - fN) * f7) + fN), 0.0f, 360.0f, false, kmVar.d);
                f7 -= 0.2f;
                i3++;
                size = size;
                fMin2 = fMin2;
                i2 = 5;
                f6 = 2.0f;
            }
            i = size;
            f3 = fMin2;
            kkVar = kkVar2;
            f = f4;
            f2 = f5;
        } else {
            i = size;
            float f8 = fMin2;
            int i4 = 5;
            float f9 = 2.0f;
            int i5 = 0;
            float f10 = 1.0f;
            while (i5 < i4) {
                Path path = new Path();
                kmVar.d.setStrokeWidth(f9);
                if (i5 % 2 == 0) {
                    kmVar.d.setStyle(Paint.Style.FILL_AND_STROKE);
                    kmVar.d.setColor(Color.parseColor("#e3e3e3"));
                } else {
                    kmVar.d.setStyle(Paint.Style.FILL_AND_STROKE);
                    kmVar.d.setColor(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR));
                }
                int i6 = i;
                int i7 = 0;
                while (i7 < i6) {
                    int i8 = i6;
                    double d = (((double) (((i7 * 360) / i6) + 180)) * 3.141592653589793d) / 180.0d;
                    double dMin = ((double) fMin) + (((double) (Math.min(f4, f5) * f10)) * Math.sin(d));
                    kk kkVar3 = kkVar2;
                    float f11 = f8;
                    float f12 = f4;
                    float f13 = f5;
                    double dMin2 = ((double) f11) + (((double) (f10 * Math.min(f4, f5))) * Math.cos(d));
                    if (i7 == 0) {
                        path.moveTo((float) dMin, (float) dMin2);
                    } else {
                        path.lineTo((float) dMin, (float) dMin2);
                    }
                    i7++;
                    f8 = f11;
                    kkVar2 = kkVar3;
                    f5 = f13;
                    f4 = f12;
                    i6 = i8;
                }
                i = i6;
                path.close();
                kmVar = this;
                canvas.drawPath(path, kmVar.d);
                f10 -= 0.2f;
                i5++;
                i4 = 5;
                f9 = 2.0f;
            }
            kkVar = kkVar2;
            f = f4;
            f2 = f5;
            f3 = f8;
        }
        Path path2 = new Path();
        kmVar.d.setColor(Color.parseColor("#bbbbbb"));
        kmVar.d.setStrokeWidth(5.0f);
        List listC = ((tk) kkVar.f().get(0)).c();
        int i9 = i;
        int i10 = 0;
        while (i10 < i9) {
            Path path3 = new Path();
            path3.moveTo(fMin, f3);
            double d2 = fMin;
            float f14 = f2;
            float f15 = f;
            double d3 = (((double) (((i10 * 360) / i9) + 180)) * 3.141592653589793d) / 180.0d;
            double d4 = f3;
            float f16 = f3;
            List list = listC;
            path3.lineTo((float) ((((double) Math.min(f15, f14)) * Math.sin(d3)) + d2), (float) ((((double) Math.min(f15, f14)) * Math.cos(d3)) + d4));
            path2.addPath(path3);
            a(canvas, (float) (d2 + (((double) Math.min(f15, f14)) * Math.sin(d3))), (float) (d4 + (((double) Math.min(f15, f14)) * Math.cos(d3))), fMin, f16, (String) list.get(i10));
            i10++;
            listC = list;
            f2 = f14;
            i9 = i9;
            f3 = f16;
            kmVar = this;
        }
        km kmVar2 = kmVar;
        kmVar2.d.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path2, kmVar2.d);
        if (kmVar2.f9893a.getChartConfig().h) {
            float fA = (kmVar2.f9893a.getChartAnimator().a() - 1.0f) * 360.0f;
            kmVar2.d.setColor(-12236506);
            kmVar2.d.setStyle(Paint.Style.FILL);
            kmVar2.d.setXfermode(kmVar2.t);
            canvas.drawArc(kmVar2.v, 270.0f, fA, true, kmVar2.d);
            kmVar2.d.reset();
        }
    }

    @Override // supwisdom.lm, supwisdom.hm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = false;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setColor(Color.parseColor("#e3e3e3"));
        this.d.setStrokeWidth(2.0f);
    }
}
