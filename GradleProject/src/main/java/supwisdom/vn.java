package supwisdom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.ViewGroup;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: MarkerView.java */
/* JADX INFO: loaded from: classes.dex */
public class vn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f9527a;
    public Context b;
    public Chart c;

    public vn(Chart chart) {
        this.c = chart;
        this.b = chart.getContext();
        b();
    }

    public ViewGroup a() {
        return this.f9527a;
    }

    public void a(float[] fArr) {
        throw null;
    }

    public void b() {
        throw null;
    }

    public void c() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(this.c.getChartConfig().f.d));
        if (this.c.getChartConfig().f.f > 0.0f) {
            gradientDrawable.setCornerRadius(this.c.getChartConfig().f.f);
        }
        gradientDrawable.setStroke((int) this.c.getChartConfig().f.h, this.c.getChartConfig().f.g);
        if (Build.VERSION.SDK_INT < 16) {
            this.f9527a.setBackgroundDrawable(gradientDrawable);
        } else {
            this.f9527a.setBackground(gradientDrawable);
        }
    }
}
