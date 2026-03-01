package supwisdom;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: StackedDataDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class nm extends zl {
    public nm(Chart chart) {
        super(chart);
    }

    public final void a(ek ekVar, Canvas canvas, BarChartStrategy.BarChartDirection barChartDirection, Float f, float f2) {
        int i;
        Iterator it;
        List list;
        List listF = ekVar.f();
        List listG = ekVar.g();
        float fB = f == null ? b(this.q) / 3.0f : f.floatValue();
        float[] fArr = new float[2];
        bo viewportHandler = this.f9893a.getViewportHandler();
        Iterator it2 = listF.iterator();
        while (it2.hasNext()) {
            ok okVar = (ok) it2.next();
            char c = 0;
            int i2 = 0;
            while (i2 < okVar.d().size()) {
                float fA = ((yk) okVar.d().get(i2)).a() + f2;
                this.d.reset();
                char c2 = 1;
                this.d.setAntiAlias(true);
                this.d.setStyle(Paint.Style.FILL_AND_STROKE);
                int i3 = 0;
                float f3 = 0.0f;
                while (i3 < listG.size()) {
                    Float[] fArrA = wn.a(this.f9893a, ((pk) listG.get(i3)).b());
                    Path path = new Path();
                    pk pkVar = (pk) listG.get(i3);
                    if (i2 >= pkVar.i().size()) {
                        it = it2;
                        list = listG;
                        i = i2;
                    } else {
                        yk<Double> ykVar = pkVar.i().get(i2);
                        fArr[c] = fA;
                        fArr[c2] = f3 + (ykVar.b() == null ? 0.0f : ykVar.b().floatValue());
                        f3 = fArr[c2];
                        a(path, fArr, fB, ykVar.b() == null ? 0.0f : ykVar.b().floatValue());
                        i = i2;
                        it = it2;
                        list = listG;
                        this.f9893a.getTransformUtil().a(path, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArrA[0].floatValue(), fArrA[1].floatValue());
                        if (barChartDirection == BarChartStrategy.BarChartDirection.HORIZONTAL) {
                            this.f9893a.getTransformUtil().a(path);
                        }
                        if (pkVar.c() == null || !pkVar.c().f8401a.equals("lineargradient")) {
                            this.d.setShader(null);
                            this.d.setColor(pkVar.d());
                        } else {
                            this.d.setShader(new LinearGradient(viewportHandler.c() * pkVar.c().b[0], viewportHandler.e() * pkVar.c().b[1], viewportHandler.d() * pkVar.c().c[0], viewportHandler.b() * pkVar.c().c[1], pkVar.c().d, pkVar.c().f8402e, Shader.TileMode.CLAMP));
                        }
                        canvas.drawPath(path, this.d);
                    }
                    i3++;
                    listG = list;
                    i2 = i;
                    it2 = it;
                    c = 0;
                    c2 = 1;
                }
                i2++;
                it2 = it2;
                c = 0;
            }
        }
    }

    public final void b(Canvas canvas, BarChartStrategy.BarChartDirection barChartDirection) {
        float f;
        List<dk> listK = ((nk) this.q).k();
        if (listK.size() == 0) {
            return;
        }
        float fB = b(this.q);
        int size = listK.size();
        float fA = a((ek) this.q);
        float f2 = (float) (((double) fB) / (((double) fA) + 1.0d));
        float size2 = f2 / (listK.size() + ((listK.size() - 1) * fA));
        float size3 = (f2 - (listK.size() * size2)) / (listK.size() + 1);
        this.o = size3;
        if (size % 2 == 0) {
            float f3 = size / 2;
            f = (f3 * size2) + ((f3 - 1.0f) * size3) + (size3 / 2.0f);
        } else {
            float fFloor = (float) Math.floor(size / 2);
            f = (fFloor * size2) + (fFloor * this.o) + (size2 / 2.0f);
        }
        float f4 = -f;
        Iterator<dk> it = listK.iterator();
        while (it.hasNext()) {
            b(it.next(), canvas, barChartDirection, Float.valueOf(size2), f4);
            f4 = f4 + size2 + this.o;
        }
    }

    @Override // supwisdom.zl, supwisdom.am, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.p = BarChartStrategy.BarChartDirection.HORIZONTAL;
    }

    @Override // supwisdom.zl
    public float b(ek ekVar) {
        dk dkVar = null;
        for (dk dkVar2 : ((nk) ekVar).k()) {
            if (dkVar == null || ekVar.f().size() > dkVar.f().size()) {
                dkVar = dkVar2;
            }
        }
        return super.b(dkVar);
    }

    public final void b(ek ekVar, Canvas canvas, BarChartStrategy.BarChartDirection barChartDirection, Float f, float f2) {
        List list;
        float f3;
        int i;
        int i2;
        int i3;
        List<ok> listF = ekVar.f();
        List listG = ekVar.g();
        float fB = f == null ? b(this.q) / 3.0f : f.floatValue();
        float[] fArr = new float[2];
        bo viewportHandler = this.f9893a.getViewportHandler();
        RectF rectF = new RectF(viewportHandler.c(), viewportHandler.e(), viewportHandler.d(), viewportHandler.b());
        rectF.right = this.f9893a.getContentWidth() - viewportHandler.d();
        rectF.bottom = this.f9893a.getContentHeight() - viewportHandler.b();
        rectF.left = viewportHandler.c();
        rectF.top = viewportHandler.e();
        for (ok okVar : listF) {
            int i4 = 0;
            while (i4 < okVar.d().size()) {
                float fA = ((yk) okVar.d().get(i4)).a() + f2;
                this.d.reset();
                this.d.setAntiAlias(true);
                this.d.setStyle(Paint.Style.FILL_AND_STROKE);
                int size = listG.size();
                float f4 = 0.0f;
                int i5 = 0;
                while (i5 < size) {
                    Float[] fArrA = wn.a(this.f9893a, ((pk) listG.get(i5)).b());
                    Path path = new Path();
                    pk pkVar = (pk) listG.get(i5);
                    if (i4 >= pkVar.i().size()) {
                        i = i5;
                        i2 = size;
                        i3 = i4;
                        list = listG;
                        f3 = fB;
                    } else {
                        float f5 = pkVar instanceof vk ? ((vk) pkVar).F : 0.0f;
                        yk<Double> ykVar = pkVar.i().get(i4);
                        fArr[0] = fA;
                        fArr[1] = f4 + (ykVar.b() == null ? 0.0f : ykVar.b().floatValue());
                        float f6 = fArr[1];
                        float f7 = f5;
                        float[] fArr2 = {fArr[0], fArr[1]};
                        int i6 = i4;
                        float[] fArr3 = new float[2];
                        fArr3[0] = fArr[0] + fB;
                        fArr3[1] = fArr[1] - (ykVar.b() == null ? 0.0f : ykVar.b().floatValue());
                        list = listG;
                        f3 = fB;
                        this.f9893a.getTransformUtil().a(fArr2, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArrA[0].floatValue(), fArrA[1].floatValue());
                        this.f9893a.getTransformUtil().a(fArr3, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArrA[0].floatValue(), fArrA[1].floatValue());
                        i = i5;
                        i2 = size;
                        i3 = i6;
                        a(path, fArr2[0], fArr2[1], fArr3[0], fArr3[1], i5 == size + (-1) ? f7 : 0.0f);
                        if (barChartDirection == BarChartStrategy.BarChartDirection.HORIZONTAL) {
                            this.f9893a.getTransformUtil().a(path);
                        }
                        if (pkVar.c() != null && pkVar.c().f8401a.equals("lineargradient")) {
                            this.d.setShader(new LinearGradient(rectF.left + (pkVar.c().b[0] * rectF.width()), rectF.top + (pkVar.c().b[1] * rectF.height()), rectF.left + (pkVar.c().c[0] * rectF.width()), rectF.top + (pkVar.c().c[1] * rectF.height()), pkVar.c().d, pkVar.c().f8402e, Shader.TileMode.CLAMP));
                        } else {
                            this.d.setShader(null);
                            this.d.setColor(pkVar.d());
                        }
                        canvas.drawPath(path, this.d);
                        f4 = f6;
                    }
                    i5 = i + 1;
                    listG = list;
                    size = i2;
                    i4 = i3;
                    fB = f3;
                }
                i4++;
            }
        }
    }

    @Override // supwisdom.zl
    public void a(Canvas canvas, BarChartStrategy.BarChartDirection barChartDirection) {
        if (((nk) this.q).k().size() == 0) {
            a(this.q, canvas, barChartDirection, null, 0.0f);
        } else {
            b(canvas, barChartDirection);
        }
    }

    public void a(Path path, float[] fArr, float f, float f2) {
        path.addRect(fArr[0], fArr[1], fArr[0] + f, fArr[1] - f2, Path.Direction.CW);
    }

    public void a(Path path, float f, float f2, float f3, float f4, float f5) {
        RectF rectF = new RectF((int) Math.floor(f), (int) Math.floor(f2), (int) Math.floor(f3), (int) Math.floor(f4));
        if (f5 > 0.0f) {
            path.addRoundRect(rectF, new float[]{f5, f5, f5, f5, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CW);
        } else {
            path.addRect(rectF, Path.Direction.CW);
        }
    }
}
