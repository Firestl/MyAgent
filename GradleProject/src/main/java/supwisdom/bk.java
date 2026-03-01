package supwisdom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.charts.PolarChartBase;

/* JADX INFO: compiled from: ChartAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class bk implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ValueAnimator f7074a;
    public Chart b;
    public float c = 0.0f;

    public bk(Chart chart) {
        this.b = chart;
    }

    public float a() {
        if (this.f7074a == null) {
            return 1.0f;
        }
        return this.c;
    }

    public boolean b() {
        ValueAnimator valueAnimator = this.f7074a;
        if (valueAnimator == null) {
            return false;
        }
        return valueAnimator.isRunning();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.b.setIsTouchable(true);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.b.setIsTouchable(true);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.c = valueAnimator.getAnimatedFraction();
        Chart chart = this.b;
        if (chart instanceof BaseAxisChart) {
            ((BaseAxisChart) chart).getmChartGraphicBuffer().a(true);
            this.b.setIsTouchable(false);
        }
        Chart chart2 = this.b;
        if (chart2 instanceof PolarChartBase) {
            ((PolarChartBase) chart2).getmChartGraphicBuffer().a(true);
            this.b.setIsTouchable(false);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.b.invalidate();
        }
    }
}
