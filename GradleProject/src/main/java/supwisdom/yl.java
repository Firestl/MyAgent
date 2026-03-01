package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.enums.DefaultDecoratorPriority;

/* JADX INFO: compiled from: ChartDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class yl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Chart f9893a;
    public Float b;
    public Float c;
    public Paint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9894e;
    public int f;

    static {
        DefaultDecoratorPriority.ORIGIN_DECORATOR.getTag();
    }

    public yl(Chart chart) {
        Float fValueOf = Float.valueOf(0.0f);
        this.b = fValueOf;
        this.c = fValueOf;
        this.d = new Paint();
        this.f9894e = false;
        this.f = DefaultDecoratorPriority.ORIGIN_DECORATOR.getPriority();
        this.f9893a = chart;
    }

    public int a() {
        return this.f;
    }

    public abstract yl a(Canvas canvas);

    public void a(View view, MotionEvent motionEvent) {
    }

    public boolean b() {
        return this.f9894e;
    }

    public void c() {
    }
}
