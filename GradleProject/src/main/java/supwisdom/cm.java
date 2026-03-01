package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: CombinedDataDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class cm extends am {
    public Paint n;
    public gm o;
    public zl p;
    public nm q;
    public mm r;

    public cm(Chart chart) {
        super(chart);
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

    public final void c(Canvas canvas) {
        for (ek ekVar : ((fk) this.f9893a.getChartData()).k()) {
            if (ekVar instanceof mk) {
                this.r.a((mk) ekVar);
                this.r.a(canvas);
            } else if (ekVar instanceof hk) {
                this.o.a((hk) ekVar);
                this.o.a(canvas);
            } else if (ekVar instanceof nk) {
                this.q.a((dk) ekVar);
                this.q.a(canvas);
            } else if (ekVar instanceof dk) {
                this.p.a((dk) ekVar);
                this.p.a(canvas);
            }
        }
    }

    @Override // supwisdom.am, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint = new Paint();
        this.n = paint;
        paint.setAntiAlias(true);
        this.n.setStyle(Paint.Style.STROKE);
        this.o = new gm(this.f9893a);
        this.p = new zl(this.f9893a);
        this.q = new nm(this.f9893a);
        this.r = new mm(this.f9893a);
        this.o.a(false);
        this.p.a(false);
        this.q.a(false);
        this.r.a(false);
    }
}
