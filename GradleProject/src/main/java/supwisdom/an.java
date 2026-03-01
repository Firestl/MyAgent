package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.List;

/* JADX INFO: compiled from: CrossDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class an extends vm {
    public float[] r;
    public Double s;
    public Double t;
    public Double u;
    public int v;

    public an(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.f9893a.getChartData() == null) {
            return this;
        }
        if (!this.f9893a.b() || this.f9893a.getSelectedIndex() == null || this.f9893a.getSelecetedSetIndex() == null) {
            b(canvas, this.r);
            return this;
        }
        b(canvas, a(this.f9893a.getSelectedIndex(), this.f9893a.getSelecetedSetIndex()));
        return this;
    }

    public final void b(Canvas canvas, float[] fArr) {
        if (fArr == null) {
            return;
        }
        c(canvas, fArr);
    }

    public final float c(float f, float f2, float f3, float f4) {
        return (float) Math.sqrt(Math.pow(f3 - f, 2.0d) + Math.pow(f4 - f2, 2.0d));
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setAntiAlias(true);
        this.d.setColor(Color.parseColor("#696969"));
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(2.0f);
    }

    public void c(Canvas canvas, float[] fArr) {
        if ((fArr[0] == 0.0f && fArr[1] == 0.0f) || this.f9893a.getChartConfig().g.f8298a) {
            return;
        }
        canvas.save();
        mk mkVar = (mk) this.f9893a.getChartData();
        this.d.setStrokeWidth(this.f9893a.getChartConfig().g.d);
        if (this.f9893a.getChartConfig().g.b) {
            this.d.setPathEffect(new DashPathEffect(new float[]{10.0f, 4.0f, 10.0f, 4.0f}, 0.0f));
        }
        float[] fArr2 = {(float) mkVar.d(), (float) mkVar.e(), (float) mkVar.a(), (float) mkVar.c()};
        this.f9893a.getTransformUtil().d(fArr2);
        Path path = new Path();
        path.moveTo(fArr2[0], fArr[1]);
        path.lineTo(fArr2[2], fArr[1]);
        Path path2 = new Path();
        path2.moveTo(fArr[0], fArr2[1]);
        path2.lineTo(fArr[0], fArr2[3]);
        canvas.drawPath(path, this.d);
        canvas.drawPath(path2, this.d);
        canvas.restore();
    }

    public float[] a(Integer num, Integer num2) {
        mk mkVar = (mk) this.f9893a.getChartData();
        if (num.intValue() <= -1 || num2.intValue() <= -1 || num.intValue() >= ((wk) mkVar.f().get(0)).f().size() || num2.intValue() >= mkVar.g().size() || num.intValue() >= ((xk) mkVar.g().get(num2.intValue())).i().size()) {
            return null;
        }
        float[] fArr = {((wk) mkVar.f().get(num2.intValue())).f().get(num.intValue()).floatValue(), ((xk) mkVar.g().get(num2.intValue())).i().get(num.intValue()).b().floatValue()};
        this.f9893a.getTransformUtil().d(fArr);
        return fArr;
    }

    @Override // supwisdom.vm, supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        super.a(view, motionEvent);
        if ((this.f9893a.getChartData() instanceof mk) && !b(motionEvent)) {
            if (this.p == null || !((BaseAxisChart) this.f9893a).getmChartGraphicBuffer().a()) {
                m();
            }
            this.r = a(motionEvent);
            this.f9893a.postInvalidate();
            if (this.f9893a.getSelectedListener() != null) {
                ((dn) this.f9893a.getSelectedListener()).a(Integer.valueOf(this.n), this.s, this.t, this.u);
            }
        }
    }

    @Override // supwisdom.vm
    public float[] a(MotionEvent motionEvent) {
        List list;
        List list2;
        int i;
        List list3;
        List list4;
        List<Double> list5;
        List<yk<Double>> list6;
        List<Double> list7;
        Float[] fArr;
        int i2;
        int i3;
        int i4;
        super.a(motionEvent);
        ek chartData = this.f9893a.getChartData();
        List listF = chartData.f();
        List listG = chartData.g();
        float[] fArr2 = new float[2];
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float[] fArr3 = new float[2];
        char c = 0;
        Float f = null;
        int i5 = 0;
        int i6 = 0;
        while (i6 < listF.size()) {
            wk wkVar = (wk) listF.get(i6);
            xk xkVar = (xk) listG.get(i6);
            if (xkVar.a() != wkVar.a()) {
                list = listG;
                list2 = listF;
                i = i6;
            } else {
                List<Double> listF2 = wkVar.f();
                List<yk<Double>> listI = xkVar.i();
                List<Double> listO = xkVar.o();
                Float[] fArrA = wn.a(this.f9893a, xkVar.b());
                Float f2 = f;
                int i7 = 0;
                while (i7 < listF2.size()) {
                    if (i7 >= listI.size() || listF2.get(i7) == null || listI.get(i7).b() == null) {
                        list3 = listG;
                        list4 = listF;
                        list5 = listO;
                        list6 = listI;
                        list7 = listF2;
                        fArr = fArrA;
                        i2 = i6;
                        i3 = i7;
                        i4 = i5;
                    } else {
                        fArr2[c] = listF2.get(i7).floatValue();
                        fArr2[1] = listI.get(i7).b().floatValue();
                        zn transformUtil = this.f9893a.getTransformUtil();
                        list3 = listG;
                        list4 = listF;
                        float fD = (float) this.f9893a.getChartData().d();
                        list5 = listO;
                        float fA = (float) this.f9893a.getChartData().a();
                        float fFloatValue = fArrA[0].floatValue();
                        float fFloatValue2 = fArrA[1].floatValue();
                        fArr = fArrA;
                        i3 = i7;
                        list6 = listI;
                        list7 = listF2;
                        i2 = i6;
                        i4 = i5;
                        transformUtil.a(fArr2, fD, fA, fFloatValue, fFloatValue2);
                        if (Math.abs(fArr2[0] - x) <= 100.0f && Math.abs(fArr2[1] - y) <= 100.0f) {
                            Float fValueOf = Float.valueOf(c(fArr2[0], fArr2[1], x, y));
                            if (f2 == null || f2.floatValue() > fValueOf.floatValue()) {
                                fArr3[0] = fArr2[0];
                                fArr3[1] = fArr2[1];
                                this.s = list7.get(i3);
                                this.t = list6.get(i3).b();
                                if (list5.size() == 0) {
                                    this.u = null;
                                } else {
                                    this.u = list5.get(i3);
                                }
                                this.n = i3;
                                this.v = i4;
                                f2 = fValueOf;
                                listI = list6;
                                listF2 = list7;
                                i7 = i3 + 1;
                                listO = list5;
                                i5 = i4;
                                listF = list4;
                                listG = list3;
                                i6 = i2;
                                fArrA = fArr;
                                c = 0;
                            }
                        }
                    }
                    listI = list6;
                    listF2 = list7;
                    i7 = i3 + 1;
                    listO = list5;
                    i5 = i4;
                    listF = list4;
                    listG = list3;
                    i6 = i2;
                    fArrA = fArr;
                    c = 0;
                }
                list = listG;
                list2 = listF;
                i = i6;
                i5++;
                f = f2;
            }
            i6 = i + 1;
            listF = list2;
            listG = list;
            c = 0;
        }
        return fArr3;
    }
}
