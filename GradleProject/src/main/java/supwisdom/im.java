package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import com.alibaba.dt.AChartsLib.chartStrategys.PolarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.charts.PolarChartBase;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import java.util.Iterator;

/* JADX INFO: compiled from: PieDataDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class im extends BlockDecorator {
    public RectF m;
    public float n;
    public PolarChartStrategy o;
    public final Xfermode p;
    public RectF q;
    public Float r;
    public Float s;
    public float t;
    public float u;

    public im(Chart chart) {
        super(chart);
        this.m = new RectF();
        this.p = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.q = new RectF();
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        bo viewportHandler = this.f9893a.getViewportHandler();
        this.q.left = viewportHandler.c();
        this.q.top = viewportHandler.e();
        this.q.right = this.f9893a.getContentWidth() - viewportHandler.d();
        this.q.bottom = this.f9893a.getContentHeight() - viewportHandler.b();
        this.o = ((PolarChartBase) this.f9893a).getPolarChartStrategy();
        m();
        l();
        float contentWidth = (this.f9893a.getContentWidth() - viewportHandler.c()) - viewportHandler.d();
        float contentHeight = (this.f9893a.getContentHeight() - viewportHandler.e()) - viewportHandler.b();
        float f = contentWidth / 2.0f;
        this.t = f;
        float f2 = contentHeight / 2.0f;
        this.u = f2;
        Math.min(f, f2);
        this.m.left = viewportHandler.c();
        this.m.top = viewportHandler.e();
        this.m.right = this.f9893a.getContentWidth() - viewportHandler.d();
        this.m.bottom = this.f9893a.getContentHeight() - viewportHandler.b();
        float f3 = this.t;
        float f4 = this.u;
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
        if (rectF3.left >= rectF3.right || rectF3.top >= rectF3.bottom) {
            canvas.restore();
            return this;
        }
        b(canvas);
        canvas.restore();
        return this;
    }

    public void b(Canvas canvas) {
        this.n = this.o.q();
        this.f9893a.getChartData().f();
        if (this.f9893a.getSelectedIndex() != null) {
            this.f9893a.getSelectedIndex().intValue();
        }
        RectF rectF = this.m;
        float f = rectF.right;
        float f2 = rectF.left;
        float f3 = rectF.bottom;
        float f4 = rectF.top;
        Iterator it = this.f9893a.getChartData().g().iterator();
        if (it.hasNext()) {
            sk skVar = (sk) it.next();
            int[] iArrL = skVar.l();
            int length = 0;
            float fN = skVar.n();
            float fP = skVar.p();
            RectF rectFA = a(this.m, fP);
            float f5 = rectFA.right;
            float f6 = rectFA.left;
            Iterator<yk<Double>> it2 = skVar.i().iterator();
            while (it2.hasNext()) {
                al alVar = (al) it2.next();
                if (iArrL != null) {
                    if (length >= iArrL.length) {
                        length %= iArrL.length;
                    }
                    this.d.setColor(iArrL[length]);
                } else {
                    this.d.setColor(this.f9893a.getChartPalette().f8808a[length % 7]);
                }
                this.d.setStyle(Paint.Style.FILL);
                canvas.drawArc(a(this.m, fP), this.n, alVar.c(), true, this.d);
                this.d.setStrokeWidth(skVar.o());
                this.d.setAlpha(255);
                this.d.setColor(skVar.d());
                this.d.setStyle(Paint.Style.STROKE);
                canvas.drawArc(a(this.m, fP), this.n, alVar.c(), true, this.d);
                length++;
                this.n = Math.abs(this.n + alVar.c());
            }
            if (fN > 0.0f) {
                this.d.setColor(-1);
                this.d.setStyle(Paint.Style.FILL);
                canvas.drawArc(a(this.m, fN), 270.0f, 360.0f, true, this.d);
            }
        }
        if (this.f9893a.getChartConfig().h) {
            float fA = (this.f9893a.getChartAnimator().a() - 1.0f) * 360.0f;
            this.d.setColor(-12236506);
            this.d.setStyle(Paint.Style.FILL);
            this.d.setXfermode(this.p);
            canvas.drawArc(this.m, 270.0f, fA, true, this.d);
            this.d.reset();
        }
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = false;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
    }

    public float l() {
        if (this.r == null) {
            this.r = Float.valueOf(Math.min(this.f9893a.getContentWidth() / 2.0f, this.f9893a.getContentHeight() / 2.0f) * 0.95f);
        }
        return this.r.floatValue();
    }

    public float m() {
        if (this.s == null) {
            this.s = Float.valueOf(Math.min(this.f9893a.getContentWidth() / 2.0f, this.f9893a.getContentHeight() / 2.0f));
        }
        return this.s.floatValue();
    }

    public RectF a(RectF rectF, float f) {
        RectF rectF2 = new RectF(rectF);
        float fMin = (1.0f - f) * Math.min(this.t, this.u);
        rectF2.left += fMin;
        rectF2.right -= fMin;
        rectF2.top += fMin;
        rectF2.bottom -= fMin;
        return rectF2;
    }
}
