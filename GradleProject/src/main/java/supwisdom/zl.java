package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.List;

/* JADX INFO: compiled from: BarDataDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class zl extends am {
    public float n;
    public float o;
    public BarChartStrategy.BarChartDirection p;
    public dk q;
    public boolean r;

    public zl(Chart chart) {
        super(chart);
        this.r = true;
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

    public float b(ek ekVar) {
        List listF = ekVar.f();
        List listG = ekVar.g();
        if (listF == null && listG == null) {
            return 1.0f;
        }
        ok okVar = (ok) listF.get(0);
        if (okVar.d().size() > 1) {
            return ((yk) okVar.d().get(1)).a() - ((yk) okVar.d().get(0)).a();
        }
        return 1.0f;
    }

    public void c(Canvas canvas) {
        if (this.r) {
            this.q = (dk) this.f9893a.getChartData();
        }
        if (this.q == null) {
            return;
        }
        BarChartStrategy.BarChartDirection barChartDirectionP = ((cl) this.f9893a.getChartStrategy()).p();
        this.p = barChartDirectionP;
        a(canvas, barChartDirectionP);
    }

    @Override // supwisdom.am, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void a(boolean z) {
        this.r = z;
    }

    public void a(dk dkVar) {
        this.q = dkVar;
    }

    public float a(ek ekVar) {
        ((nk) ekVar).k();
        float f = -1.0f;
        for (pk pkVar : ekVar.g()) {
            if (pkVar instanceof vk) {
                float f2 = ((vk) pkVar).E;
                if (f2 > f) {
                    f = f2;
                }
            }
        }
        return f;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.graphics.Canvas r33, com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy.BarChartDirection r34) {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.zl.a(android.graphics.Canvas, com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy$BarChartDirection):void");
    }

    public void a(Path path, float[] fArr, float f) {
        path.addRect(fArr[0], this.f9893a.getChartAnimator().a() * fArr[1], fArr[0] + f, 0.0f, Path.Direction.CW);
    }
}
