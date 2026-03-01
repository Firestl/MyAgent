package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import supwisdom.ck;

/* JADX INFO: compiled from: GridDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class fm extends BlockDecorator {
    public int m;
    public ck.b n;

    public fm(Chart chart) {
        super(chart);
        this.m = 5;
        this.n = new ck.b();
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.f9893a.getChartConfig().c.f8298a || this.f9893a.getChartData() == null) {
            return this;
        }
        canvas.save();
        canvas.clipRect(this.f9893a.getViewportHandler().c(), this.f9893a.getViewportHandler().e(), this.f9893a.getContentWidth() - this.f9893a.getViewportHandler().d(), this.f9893a.getContentHeight() - this.f9893a.getViewportHandler().b());
        this.d.setStrokeWidth(this.f9893a.getChartConfig().c.f9073e);
        this.d.setColor(this.f9893a.getChartConfig().c.d);
        if (this.f9893a.getChartConfig().c.b) {
            b(canvas);
        }
        if (this.f9893a.getChartConfig().c.c) {
            c(canvas);
        }
        canvas.restore();
        return this;
    }

    public void b(Canvas canvas) {
        if (!((BaseAxisChart) this.f9893a).getmChartGraphicBuffer().a() && !this.n.a().isEmpty()) {
            Iterator<Path> it = this.n.a().iterator();
            while (it.hasNext()) {
                canvas.drawPath(it.next(), this.d);
            }
            return;
        }
        ek chartData = this.f9893a.getChartData();
        float[] fArrA = wn.a(chartData, (int) (this.f9893a.getCurrentScaleY() * this.m));
        ArrayList arrayList = new ArrayList();
        Path path = new Path();
        for (int i = 0; i < fArrA.length; i++) {
            path.moveTo((float) chartData.d(), fArrA[i]);
            path.lineTo((float) chartData.a(), fArrA[i]);
        }
        this.f9893a.getTransformUtil().b(path);
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            this.f9893a.getTransformUtil().a(path);
        }
        canvas.drawPath(path, this.d);
        arrayList.add(path);
        this.n.a(arrayList);
    }

    public void c(Canvas canvas) {
        if (!((BaseAxisChart) this.f9893a).getmChartGraphicBuffer().a() && !this.n.b().isEmpty()) {
            Iterator<Path> it = this.n.b().iterator();
            while (it.hasNext()) {
                canvas.drawPath(it.next(), this.d);
            }
            return;
        }
        ek chartData = this.f9893a.getChartData();
        ArrayList arrayList = new ArrayList();
        List listF = chartData.f();
        Path path = new Path();
        if (chartData instanceof mk) {
            float[] fArrA = wn.a((float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), ((wk) listF.get(0)).d().size() - 1);
            for (int i = 0; i < fArrA.length; i++) {
                path.moveTo(fArrA[i], (float) chartData.e());
                path.lineTo(fArrA[i], (float) chartData.c());
            }
        } else {
            for (int i2 = 0; i2 < ((ok) listF.get(0)).d().size(); i2++) {
                yk ykVar = (yk) ((ok) listF.get(0)).d().get(i2);
                path.moveTo(ykVar.a(), (float) chartData.e());
                path.lineTo(ykVar.a(), (float) chartData.c());
            }
        }
        this.f9893a.getTransformUtil().b(path);
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            this.f9893a.getTransformUtil().a(path);
        }
        canvas.drawPath(path, this.d);
        arrayList.add(path);
        this.n.b(arrayList);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        this.d.setColor(Color.parseColor("#e3e3e3"));
        this.d.setAlpha(120);
        this.d.setStrokeWidth(1.0f);
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
    }
}
