package supwisdom;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: TitleDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class qm extends BlockDecorator {
    public String m;
    public float n;
    public float o;

    public qm(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        l();
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        this.f9894e = true;
        this.o = 10.0f;
        float f = tn.a(this.f9893a.getContext()).density;
        this.n = f;
        this.b = Float.valueOf(f * 30.0f);
        this.c = Float.valueOf(this.n * 30.0f);
        this.m = "";
        this.d.setTextSize(this.n * 20.0f);
        this.d.setColor(-16777216);
        this.d.setAntiAlias(true);
    }

    public void l() {
        this.h = true;
        a(0.0f, this.o * this.n, 0.0f, 0.0f);
        this.f9893a.getChartStrategy().m().a((BlockDecorator) this);
        this.h = false;
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.m.isEmpty()) {
            a(0.0f, 0.0f, 0.0f, 0.0f);
            return this;
        }
        l();
        canvas.drawText(this.m, this.b.floatValue(), this.c.floatValue(), this.d);
        return this;
    }
}
