package supwisdom;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NodeDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class vm extends BlockDecorator {
    public float[] m;
    public int n;
    public float[][] o;
    public float[] p;
    public int q;

    public vm(Chart chart) {
        super(chart);
        this.n = 0;
        this.q = 1;
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.f9893a.getChartData() == null) {
            return this;
        }
        int i = 0;
        if (this.f9893a.b() && this.f9893a.getSelectedIndex() != null) {
            int iIntValue = this.f9893a.getSelectedIndex().intValue();
            this.n = iIntValue;
            a(canvas, new float[]{iIntValue, 0.0f});
            return this;
        }
        int i2 = this.q;
        if (i2 == 1) {
            a(canvas, this.m);
        } else if (i2 == 2) {
            float[][] fArr = this.o;
            int length = fArr.length;
            while (i < length) {
                a(canvas, fArr[i]);
                i++;
            }
        } else if (i2 == 3) {
            float[][] fArr2 = this.o;
            int length2 = fArr2.length;
            while (i < length2) {
                a(canvas, fArr2[i]);
                i++;
            }
            a(canvas, this.m);
        }
        return this;
    }

    public void a(Canvas canvas, float[] fArr) {
    }

    public boolean b(MotionEvent motionEvent) {
        if (this.f9893a.getChartData() == null) {
            return true;
        }
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        this.f9893a.getTransformUtil().a(fArr);
        return ((double) fArr[0]) < this.f9893a.getChartData().d() || ((double) fArr[0]) > this.f9893a.getChartData().a() || ((double) fArr[1]) < this.f9893a.getChartData().e() || ((double) fArr[1]) > this.f9893a.getChartData().c();
    }

    public boolean c(MotionEvent motionEvent) {
        if (this.f9893a.getChartData() == null) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return x < this.f9893a.getViewportHandler().c() || x > ((float) this.f9893a.getMeasuredWidth()) - this.f9893a.getViewportHandler().d() || y < this.f9893a.getViewportHandler().e() || y > ((float) this.f9893a.getMeasuredHeight()) - this.f9893a.getViewportHandler().b();
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
    }

    public float[][] l() {
        List listG = this.f9893a.getChartData().g();
        List listF = this.f9893a.getChartData().f();
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, listG.size(), listF.size() * 2);
        int i = 0;
        for (int i2 = 0; i2 < fArr.length; i2++) {
            int i3 = 0;
            while (i3 < fArr[i2].length) {
                yk<Double> ykVar = ((pk) listG.get(i2)).i().get(i);
                fArr[i2][i3] = ((yk) listF.get(i)).a();
                int i4 = i3 + 1;
                if (ykVar.b() == null) {
                    fArr[i2][i4] = ((Float) ykVar.b()).floatValue();
                }
                i++;
                i3 = i4 + 1;
            }
        }
        this.o = fArr;
        return fArr;
    }

    public void m() {
        int i = 0;
        List listD = ((ok) this.f9893a.getChartData().f().get(0)).d();
        this.p = new float[listD.size() * 2];
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            this.p[i] = ((yk) it.next()).a();
            int i2 = i + 1;
            this.p[i2] = (float) this.f9893a.getChartData().e();
            i = i2 + 1;
        }
        this.f9893a.getTransformUtil().c(this.p);
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            this.f9893a.getTransformUtil().b(this.p);
        }
    }

    @Override // supwisdom.yl
    public void c() {
        this.n = 0;
        this.o = null;
    }

    @Override // supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        this.f9893a.setSelectMode(false);
        nl chartConfig = this.f9893a.getChartConfig();
        if (this.m == null && chartConfig.d) {
            this.m = new float[2];
        }
        if (!c(motionEvent) && chartConfig.d) {
            this.f9893a.requestDisallowInterceptTouchEvent(true);
        } else {
            this.f9893a.requestDisallowInterceptTouchEvent(false);
        }
        if (this.p == null || !((BaseAxisChart) this.f9893a).getmChartGraphicBuffer().a()) {
            m();
        }
        int i = this.q;
        if (i == 1) {
            a(motionEvent);
        } else if (i == 2) {
            l();
        } else if (i == 3) {
            l();
            a(motionEvent);
        }
        this.f9893a.postInvalidate();
    }

    public float[] a(MotionEvent motionEvent) {
        if (b(motionEvent)) {
            return this.m;
        }
        int i = 0;
        List listD = ((ok) this.f9893a.getChartData().f().get(0)).d();
        float fAbs = 0.0f;
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            if (listD.size() > 1) {
                float[] fArr2 = this.p;
                fAbs = Math.abs(fArr2[3] - fArr2[1]);
            }
            int i2 = 1;
            while (true) {
                if (i2 < this.p.length) {
                    if (i2 != 1) {
                        i++;
                    }
                    if (fArr[1] <= this.p[i2] + (fAbs / 2.0f)) {
                        fArr[1] = i;
                        this.n = (int) fArr[1];
                        break;
                    }
                    i2 += 2;
                } else {
                    break;
                }
            }
        } else {
            if (listD.size() > 1) {
                float[] fArr3 = this.p;
                fAbs = Math.abs(fArr3[2] - fArr3[0]);
            }
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 < this.p.length) {
                    if (i3 != 0 && i3 % 2 == 0) {
                        i4++;
                    }
                    if (fArr[0] <= this.p[i3] + (fAbs / 3.0f)) {
                        fArr[0] = i4;
                        this.n = (int) fArr[0];
                        break;
                    }
                    i3 += 2;
                } else {
                    break;
                }
            }
        }
        this.m = fArr;
        this.f9893a.setSelectedIndex(Integer.valueOf(this.n));
        return fArr;
    }
}
