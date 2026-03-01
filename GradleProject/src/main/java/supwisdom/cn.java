package supwisdom;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.interfaces.ChartTouchListener;

/* JADX INFO: compiled from: BaseAcxisChartTouchListener.java */
/* JADX INFO: loaded from: classes.dex */
public class cn extends ChartTouchListener<Chart<?>> {
    public Matrix d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Matrix f7232e;
    public float f;
    public float g;
    public mn h;
    public mn i;
    public mn j;
    public mn k;
    public mn l;
    public mn m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public float u;
    public float v;
    public float w;
    public long x;

    public cn(BaseAxisChart baseAxisChart) {
        super(baseAxisChart);
        this.d = new Matrix();
        this.f7232e = new Matrix();
        this.f = 1.0f;
        this.h = new mn(0.0f, 0.0f);
        this.i = new mn(0.0f, 0.0f);
        this.j = new mn(0.0f, 0.0f);
        this.k = new mn(1.0f, 1.0f);
        this.l = new mn(0.0f, 0.0f);
        this.m = new mn(1.0f, 1.0f);
        this.n = 1.0f;
        this.o = 100.0f;
        this.p = 1.0f;
        this.q = 100.0f;
        this.r = 1.0f;
        this.s = 1.0f;
        this.t = 0.0f;
        this.u = 0.0f;
        this.v = 0.0f;
        this.w = 0.0f;
        this.g = tn.a(3.5f);
    }

    public static float d(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    public static float e(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    public static float f(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public final void a(MotionEvent motionEvent, float f, float f2) {
        ChartTouchListener.ChartGesture chartGesture = ChartTouchListener.ChartGesture.DRAG;
        T t = this.c;
        if (t instanceof BaseAxisChart) {
            if (!t.getChartConfig().i) {
                mn mnVar = this.l;
                mnVar.f8409a = 0.0f;
                mnVar.b = f2;
            } else if (this.c.getChartConfig().j) {
                mn mnVar2 = this.l;
                mnVar2.f8409a = f;
                mnVar2.b = f2;
            } else {
                mn mnVar3 = this.l;
                mnVar3.f8409a = f;
                mnVar3.b = 0.0f;
            }
        }
    }

    public final void b(MotionEvent motionEvent) {
        boolean z = this.c instanceof BaseAxisChart;
        mn mnVar = this.h;
        mnVar.f8409a = 0.0f;
        mnVar.b = 0.0f;
    }

    public final void c(MotionEvent motionEvent) {
        if (this.c instanceof BaseAxisChart) {
            mn mnVar = this.k;
            mnVar.f8409a = this.r;
            mnVar.b = this.s;
            mn mnVar2 = this.i;
            mnVar2.f8409a = this.t;
            mnVar2.b = this.u;
        }
        this.f7232e.set(this.d);
        this.h.f8409a = motionEvent.getX();
        this.h.b = motionEvent.getY();
        this.x = System.currentTimeMillis();
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.c.getItemClickedListener() != null) {
            this.c.getItemClickedListener().c(this.c.getSelectedIndex(), null, null, null);
        }
        return super.onDoubleTap(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        if (this.c.getItemClickedListener() != null) {
            this.c.getItemClickedListener().b(this.c.getSelectedIndex(), null, null, null);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.b.onTouchEvent(motionEvent);
        boolean z = false;
        if (this.c.getChartConfig().d) {
            this.c.requestDisallowInterceptTouchEvent(true);
        } else {
            this.c.requestDisallowInterceptTouchEvent(false);
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            c(motionEvent);
        } else if (action == 1) {
            b(motionEvent);
            float x = motionEvent.getX() - this.h.f8409a;
            float y = motionEvent.getY() - this.h.b;
            if (System.currentTimeMillis() - this.x < 300 || (Math.abs(x) < 10.0f && Math.abs(y) < 10.0f)) {
                this.f1545a = 7;
            } else {
                this.f1545a = 0;
            }
        } else if (action == 2) {
            float x2 = motionEvent.getX() - this.h.f8409a;
            float y2 = motionEvent.getY() - this.h.b;
            if (this.f1545a != 4) {
                mn mnVar = this.k;
                if (mnVar.f8409a > 1.0f || mnVar.b > 1.0f) {
                    this.f1545a = 1;
                }
            }
            int i = this.f1545a;
            if (i == 4) {
                if (this.c.getChartConfig().i || this.c.getChartConfig().j) {
                    a(motionEvent);
                    a();
                }
            } else if (i != 1) {
                this.f1545a = 11;
            } else if (this.c.getChartConfig().i || this.c.getChartConfig().j) {
                a(motionEvent, x2, y2);
                b();
            }
        } else if (action == 5 && motionEvent.getPointerCount() >= 2) {
            c(motionEvent);
            d(motionEvent);
            e(motionEvent);
            float f = f(motionEvent);
            this.f = f;
            if (f > 10.0f) {
                this.f1545a = 4;
            }
            a(this.j, motionEvent);
        }
        int i2 = this.f1545a;
        if ((i2 == 11 || i2 == 7) && this.c.getChartStrategy() != null) {
            this.c.getChartStrategy().a(null, motionEvent);
        }
        if (this.c.getItemClickedListener() != null && this.f1545a == 7) {
            this.c.getItemClickedListener().a(this.c.getSelectedIndex(), null, null, null);
            z = true;
        }
        if (!z && this.c.getSelectedListener() != null && this.f1545a == 11) {
            this.c.getSelectedListener().a(this.c.getSelectedIndex(), null, null, null);
        }
        return true;
    }

    public void b() {
        bo viewportHandler = this.c.getViewportHandler();
        float[] fArr = new float[9];
        this.f7232e.getValues(fArr);
        float contentWidth = (this.c.getContentWidth() - viewportHandler.d()) - viewportHandler.c();
        float contentHeight = (this.c.getContentHeight() - viewportHandler.b()) - viewportHandler.e();
        float f = fArr[2];
        mn mnVar = this.l;
        float f2 = f + mnVar.f8409a;
        float f3 = fArr[5] + mnVar.b;
        this.t = Math.min(Math.max(f2, ((-contentWidth) * (this.r - 1.0f)) - this.v), this.v);
        this.u = Math.min(Math.max(f3, ((-contentHeight) * (this.s - 1.0f)) - this.w), this.w);
        float[] fArr2 = new float[9];
        this.d.getValues(fArr2);
        fArr2[2] = this.t;
        fArr2[5] = this.u;
        this.d.setValues(fArr2);
        this.c.setScaleAndTransMatrix(this.d);
        ((BaseAxisChart) this.c).getmChartGraphicBuffer().a(true);
        this.c.postInvalidate();
    }

    public final void a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2) {
            float f = f(motionEvent);
            if (f > this.g) {
                float f2 = f / this.f;
                mn mnVar = this.k;
                float f3 = mnVar.f8409a * f2;
                float f4 = mnVar.b * f2;
                mn mnVar2 = this.m;
                mnVar2.f8409a = f3;
                mnVar2.b = f4;
                ((BaseAxisChart) this.c).getmChartGraphicBuffer().a(true);
            }
        }
    }

    public static void a(mn mnVar, MotionEvent motionEvent) {
        float x = motionEvent.getX(0) + motionEvent.getX(1);
        float y = motionEvent.getY(0) + motionEvent.getY(1);
        mnVar.f8409a = x / 2.0f;
        mnVar.b = y / 2.0f;
    }

    public final void a() {
        this.d.reset();
        mn mnVar = this.m;
        float f = mnVar.f8409a;
        float f2 = mnVar.b;
        this.r = Math.min(Math.max(this.p, f), this.q);
        this.s = Math.min(Math.max(this.n, f2), this.o);
        if (!this.c.getChartConfig().i) {
            this.r = 1.0f;
            this.d.postScale(1.0f, this.s, 0.0f, this.j.b);
        } else if (!this.c.getChartConfig().j) {
            this.s = 1.0f;
            this.d.postScale(this.r, 1.0f, this.j.f8409a, 0.0f);
        } else {
            Matrix matrix = this.d;
            float f3 = this.r;
            float f4 = this.s;
            mn mnVar2 = this.j;
            matrix.postScale(f3, f4, mnVar2.f8409a, mnVar2.b);
        }
        this.c.setScaleAndTransMatrix(this.d);
        this.c.setCurrentScaleY(this.s);
        this.c.setCurrentScaleX(this.r);
        ((BaseAxisChart) this.c).getmChartGraphicBuffer().a(true);
        this.c.postInvalidate();
    }
}
