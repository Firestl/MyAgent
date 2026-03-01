package supwisdom;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.List;

/* JADX INFO: compiled from: ScatterPlotDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class mm extends am {
    public mk n;
    public boolean o;

    public mm(Chart chart) {
        super(chart);
        this.o = true;
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        canvas.clipRect(this.f9893a.getViewportHandler().c(), this.f9893a.getViewportHandler().e(), this.f9893a.getContentWidth() - this.f9893a.getViewportHandler().d(), this.f9893a.getContentHeight() - this.f9893a.getViewportHandler().b());
        c(canvas);
        b(canvas);
        canvas.restore();
        return this;
    }

    public void c(Canvas canvas) {
        if (this.o) {
            this.n = (mk) this.f9893a.getChartData();
        }
        if (this.n == null) {
            return;
        }
        d(canvas);
    }

    public final void d(Canvas canvas) {
        List list;
        List list2;
        RectF rectF;
        List listF = this.n.f();
        List listG = this.n.g();
        if (listF == null && listG == null) {
            return;
        }
        float[] fArr = new float[2];
        bo viewportHandler = this.f9893a.getViewportHandler();
        RectF rectF2 = new RectF(viewportHandler.c(), viewportHandler.e(), viewportHandler.d(), viewportHandler.b());
        rectF2.right = this.f9893a.getContentWidth() - viewportHandler.d();
        rectF2.bottom = this.f9893a.getContentHeight() - viewportHandler.b();
        rectF2.left = viewportHandler.c();
        rectF2.top = viewportHandler.e();
        int i = 0;
        while (i < listF.size()) {
            wk wkVar = (wk) listF.get(i);
            xk xkVar = (xk) listG.get(i);
            if (xkVar.a() != wkVar.a()) {
                list = listF;
                list2 = listG;
                rectF = rectF2;
            } else {
                Path path = new Path();
                List<Double> listF2 = wkVar.f();
                List<Double> listO = xkVar.o();
                float[] fArrA = this.f9893a.getTransformUtil().a(listF2, xkVar.i());
                Float[] fArrA2 = wn.a(this.f9893a, xkVar.b());
                RectF rectF3 = rectF2;
                list = listF;
                list2 = listG;
                this.f9893a.getTransformUtil().a(fArrA, (float) this.n.d(), (float) this.n.a(), fArrA2[0].floatValue(), fArrA2[1].floatValue());
                int i2 = 0;
                for (int i3 = 0; i3 < wkVar.d().size(); i3++) {
                    if (i3 < xkVar.i().size()) {
                        Double dValueOf = listO.size() == 0 ? Double.valueOf(18.0d) : listO.get(i3);
                        fArr[0] = fArrA[i2];
                        int i4 = i2 + 1;
                        fArr[1] = fArrA[i4];
                        i2 = i4 + 1;
                        if (xkVar.i().get(i3).b() != null) {
                            path.addCircle(fArr[0], fArr[1], dValueOf.floatValue(), Path.Direction.CCW);
                        }
                    }
                }
                xkVar.a(path);
                if (j()) {
                    this.f9893a.getTransformUtil().a(path);
                }
                this.d.setColor(xkVar.d());
                if (xkVar.c() == null || !xkVar.c().f8401a.equals("lineargradient")) {
                    rectF = rectF3;
                    this.d.setShader(null);
                    this.d.setColor(xkVar.d());
                } else {
                    rectF = rectF3;
                    this.d.setShader(new LinearGradient(rectF.left + (xkVar.c().b[0] * rectF.width()), rectF.top + (xkVar.c().b[1] * rectF.height()), rectF.left + (xkVar.c().c[0] * rectF.width()), rectF.top + (xkVar.c().c[1] * rectF.height()), xkVar.c().d, xkVar.c().f8402e, Shader.TileMode.CLAMP));
                }
                this.d.setStrokeWidth(xkVar.n());
                canvas.drawPath(path, this.d);
                this.d.reset();
                this.d.setAntiAlias(true);
                this.d.setStyle(Paint.Style.FILL_AND_STROKE);
                this.d.setAlpha(255);
                xkVar.a(false);
            }
            i++;
            rectF2 = rectF;
            listF = list;
            listG = list2;
        }
    }

    @Override // supwisdom.am, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.d.setStrokeWidth(2.0f);
    }

    public void a(boolean z) {
        this.o = z;
    }

    public void a(mk mkVar) {
        this.n = mkVar;
    }
}
