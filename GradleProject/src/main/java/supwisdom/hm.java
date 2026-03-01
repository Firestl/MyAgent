package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import io.dcloud.common.util.TitleNViewUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: NightingaleRoseDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class hm extends BlockDecorator {
    public RectF m;
    public float n;
    public float o;
    public Float p;
    public Float q;
    public float r;
    public float s;
    public final Xfermode t;

    public hm(Chart chart) {
        super(chart);
        this.m = new RectF();
        this.o = 270.0f;
        this.t = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    public RectF a(RectF rectF, double d) {
        RectF rectF2 = new RectF(rectF);
        double dMin = (1.0d - d) * ((double) Math.min(this.r, this.s));
        rectF2.left = (float) (((double) rectF2.left) + dMin);
        rectF2.right = (float) (((double) rectF2.right) - dMin);
        rectF2.top = (float) (((double) rectF2.top) + dMin);
        rectF2.bottom = (float) (((double) rectF2.bottom) - dMin);
        return rectF2;
    }

    public void b(Canvas canvas) {
        ik ikVar = (ik) this.f9893a.getChartData();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(ikVar.j());
        int iIntValue = this.f9893a.getSelectedIndex() == null ? 0 : this.f9893a.getSelectedIndex().intValue();
        Iterator it = this.f9893a.getChartData().g().iterator();
        rk rkVar = null;
        int i = 0;
        while (it.hasNext()) {
            rk rkVar2 = (rk) it.next();
            float fN = rkVar2.n();
            float fP = rkVar2.p();
            int[] iArrL = rkVar2.l();
            this.o = 270.0f;
            a(this.m, fP);
            Iterator<yk<Double>> it2 = rkVar2.i().iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                al alVar = (al) it2.next();
                arrayList.set(i2, Double.valueOf(((Double) arrayList.get(i2)).doubleValue() - (i == 0 ? Double.valueOf(0.0d) : (Double) ((al) rkVar.i().get(i2)).b()).doubleValue()));
                if (iArrL != null) {
                    this.d.setColor(iArrL[i2 % iArrL.length]);
                } else {
                    this.d.setColor(this.f9893a.getChartPalette().f8808a[i2 % 7]);
                }
                this.d.setStyle(Paint.Style.FILL);
                Iterator it3 = it;
                rk rkVar3 = rkVar;
                double d = fP - fN;
                float f = fP;
                int[] iArr = iArrL;
                double d2 = fN;
                canvas.drawArc(a(this.m, ((((Double) arrayList.get(i2)).doubleValue() * d) / ikVar.c()) + d2), this.o, alVar.c(), true, this.d);
                if (this.f9893a.getSelectedIndex() != null && iIntValue == i2 && i == this.f9893a.getChartData().g().size() - 1 && !this.f9893a.getChartConfig().f.f8298a) {
                    this.d.setColor(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR));
                    this.d.setAlpha(120);
                    canvas.drawArc(this.m, this.o, alVar.c(), true, this.d);
                }
                this.d.setStrokeWidth(rkVar2.o());
                this.d.setAlpha(255);
                this.d.setColor(rkVar2.d());
                this.d.setStyle(Paint.Style.STROKE);
                canvas.drawArc(a(this.m, ((((Double) arrayList.get(i2)).doubleValue() * d) / ikVar.c()) + d2), this.o, alVar.c(), true, this.d);
                i2++;
                this.o = Math.abs(this.o + alVar.c());
                fP = f;
                it = it3;
                rkVar = rkVar3;
                iArrL = iArr;
            }
            Iterator it4 = it;
            if (fN > 0.0f) {
                this.d.setColor(-1);
                this.d.setStyle(Paint.Style.FILL);
                canvas.drawArc(a(this.m, fN), 270.0f, 360.0f, true, this.d);
            }
            i++;
            rkVar = rkVar2;
            it = it4;
        }
        if (this.f9893a.getChartConfig().h) {
            float fA = (this.f9893a.getChartAnimator().a() - 1.0f) * 360.0f;
            this.d.setColor(-12236506);
            this.d.setStyle(Paint.Style.FILL);
            this.d.setXfermode(this.t);
            canvas.drawArc(this.m, 270.0f, fA, true, this.d);
            this.d.reset();
        }
    }

    @Override // supwisdom.yl
    public void c() {
        super.c();
        this.o = 270.0f;
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = false;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
    }

    public float l() {
        if (this.p == null) {
            this.p = Float.valueOf(Math.min(this.f9893a.getContentWidth() / 2.0f, this.f9893a.getContentHeight() / 2.0f) * 0.95f);
        }
        return this.p.floatValue();
    }

    public float m() {
        if (this.q == null) {
            this.q = Float.valueOf(Math.min(this.f9893a.getContentWidth() / 2.0f, this.f9893a.getContentHeight() / 2.0f));
        }
        return this.q.floatValue();
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        float fM = m() - l();
        this.n = fM;
        this.d.setStrokeWidth(fM / 2.0f);
        bo viewportHandler = this.f9893a.getViewportHandler();
        float contentWidth = (this.f9893a.getContentWidth() - viewportHandler.c()) - viewportHandler.d();
        float contentHeight = (this.f9893a.getContentHeight() - viewportHandler.e()) - viewportHandler.b();
        float f = contentWidth / 2.0f;
        this.r = f;
        float f2 = contentHeight / 2.0f;
        this.s = f2;
        this.n = Math.min(f, f2);
        this.m.left = viewportHandler.c();
        this.m.top = viewportHandler.e();
        this.m.right = this.f9893a.getContentWidth() - viewportHandler.d();
        this.m.bottom = this.f9893a.getContentHeight() - viewportHandler.b();
        float f3 = this.r;
        float f4 = this.s;
        if (f3 > f4) {
            RectF rectF = this.m;
            rectF.left += f3 - f4;
            rectF.right -= f3 - f4;
        } else if (f3 < f4) {
            RectF rectF2 = this.m;
            rectF2.top += f4 - f3;
            rectF2.bottom -= f4 - f3;
        }
        RectF rectF3 = this.m;
        if (rectF3.left < rectF3.right && rectF3.top < rectF3.bottom) {
            b(canvas);
            canvas.restore();
            return this;
        }
        canvas.restore();
        return this;
    }
}
