package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: PolarNodeDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class xm extends vm {
    public int r;
    public vn s;
    public float[] t;
    public Float[] u;
    public Float v;
    public Float w;
    public Float x;

    public xm(Chart chart) {
        super(chart);
        this.r = 0;
        this.t = new float[2];
        this.u = new Float[2];
        this.v = null;
        this.w = null;
        this.x = null;
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.f9893a.getChartData() == null) {
            return this;
        }
        a(canvas, this.r);
        return this;
    }

    @Override // supwisdom.vm
    public boolean c(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return true;
        }
        if (this.w == null || this.x == null) {
            return false;
        }
        return Math.sqrt(Math.pow((double) (motionEvent.getX() - this.w.floatValue()), 2.0d) + Math.pow((double) (motionEvent.getY() - this.x.floatValue()), 2.0d)) > ((double) n());
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        this.s = new sn(this.f9893a);
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public float n() {
        this.w = Float.valueOf(this.f9893a.getContentWidth() / 2.0f);
        this.x = Float.valueOf(this.f9893a.getContentHeight() / 2.0f);
        if (this.v == null) {
            this.v = Float.valueOf(Math.min(this.w.floatValue(), this.x.floatValue()));
        }
        return this.v.floatValue();
    }

    public void a(Canvas canvas, int i) {
        Float[] fArr = this.u;
        if (fArr[0] == null || fArr[1] == null) {
            return;
        }
        canvas.save();
        if (this.f9893a.getChartConfig().f.f8298a) {
            return;
        }
        this.s.a(new float[]{this.u[0].floatValue(), this.u[1].floatValue()});
        this.s.a().measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.s.a().layout(0, 0, this.s.a().getMeasuredWidth(), this.s.a().getMeasuredHeight());
        if (ao.c(this.f9893a, this.s.a(), this.t)) {
            float[] fArr2 = this.t;
            canvas.translate(fArr2[0], fArr2[1]);
        } else {
            float fA = ao.a(this.f9893a, this.s.a(), this.t);
            if (fA < 0.0f) {
                fA = 0.0f;
            }
            float fB = ao.b(this.f9893a, this.s.a(), this.t);
            float f = fB >= 0.0f ? fB : 0.0f;
            float[] fArr3 = this.t;
            canvas.translate(fArr3[0] - fA, fArr3[1] - f);
        }
        this.s.a().draw(canvas);
        this.s.c();
        canvas.restore();
    }

    @Override // supwisdom.vm, supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        this.f9893a.setSelectMode(false);
        if (c(motionEvent)) {
            return;
        }
        if (this.f9893a.getChartConfig().d) {
            this.f9893a.requestDisallowInterceptTouchEvent(true);
        } else {
            this.f9893a.requestDisallowInterceptTouchEvent(false);
        }
        float[] fArrA = a(motionEvent);
        this.u[0] = Float.valueOf(fArrA[0]);
        this.u[1] = Float.valueOf(fArrA[1]);
        this.t[0] = motionEvent.getX();
        this.t[1] = motionEvent.getY();
        this.f9893a.postInvalidate();
    }

    @Override // supwisdom.vm
    public float[] a(MotionEvent motionEvent) {
        n();
        float x = motionEvent.getX() - this.w.floatValue();
        float fFloatValue = this.x.floatValue() - motionEvent.getY();
        double dSqrt = Math.sqrt((x * x) + (fFloatValue * fFloatValue));
        double d = ((double) x) / dSqrt;
        double d2 = ((double) fFloatValue) / dSqrt;
        double degrees = Math.toDegrees(Math.asin(d));
        if (d <= 0.0d) {
            degrees = d2 < 0.0d ? (-degrees) + 180.0d : degrees + 360.0d;
        } else if (d2 <= 0.0d) {
            degrees = 180.0d - degrees;
        }
        int i = 0;
        int i2 = 0;
        float fC = 0.0f;
        for (pk pkVar : this.f9893a.getChartData().g()) {
            if (i > 0) {
                break;
            }
            int i3 = 0;
            while (true) {
                if (i3 < pkVar.i().size()) {
                    fC += ((al) pkVar.i().get(i3)).c();
                    if (fC > degrees) {
                        i2 = i3;
                        break;
                    }
                    i2++;
                    i3++;
                }
            }
            i++;
        }
        this.f9893a.setSelectedIndex(Integer.valueOf(i2));
        return new float[]{i2, 0.0f};
    }
}
