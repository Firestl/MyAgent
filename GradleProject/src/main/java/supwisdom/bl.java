package supwisdom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;

/* JADX INFO: compiled from: AxisStrategyBase.java */
/* JADX INFO: loaded from: classes.dex */
public class bl extends cl {
    public Bitmap q;
    public Canvas r;

    public bl(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.dl, supwisdom.yl
    public void c() {
        super.c();
        Bitmap bitmap = this.q;
        if (bitmap != null) {
            bitmap.recycle();
            this.q = null;
            ((BaseAxisChart) this.f9893a).getmChartGraphicBuffer().a(true);
        }
    }

    @Override // supwisdom.dl, supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        for (yl ylVar : this.m) {
            if (ylVar.b()) {
                ylVar.a(view, motionEvent);
            }
        }
    }

    @Override // supwisdom.cl, supwisdom.dl, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public dl a(Canvas canvas) {
        Bitmap bitmap;
        if (this.f9893a.getChartData() == null) {
            return this;
        }
        this.f9893a.getViewportHandler().g();
        bo viewportHandler = this.f9893a.getViewportHandler();
        int contentWidth = (int) ((this.f9893a.getContentWidth() - viewportHandler.c()) - viewportHandler.d());
        int contentHeight = (int) (this.f9893a.getContentHeight() - viewportHandler.b());
        Bitmap bitmap2 = this.q;
        if ((bitmap2 == null || bitmap2.getWidth() != contentWidth || this.q.getHeight() != contentHeight) && contentHeight != 0 && contentWidth != 0) {
            this.q = Bitmap.createBitmap(contentWidth, contentHeight, Bitmap.Config.ARGB_4444);
            this.r = new Canvas(this.q);
        }
        if (!((BaseAxisChart) this.f9893a).getmChartGraphicBuffer().a() && (bitmap = this.q) != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.d);
            this.n = this;
            for (yl ylVar : this.m) {
                if (ylVar instanceof dl) {
                    this.n = (dl) ylVar;
                }
                if (ylVar instanceof BlockDecorator) {
                    b((BlockDecorator) ylVar);
                }
                if (ylVar instanceof vm) {
                    ylVar.a(canvas);
                }
            }
            this.f9893a.getViewportHandler().f();
            return this;
        }
        Bitmap bitmap3 = this.q;
        if (bitmap3 != null) {
            bitmap3.eraseColor(0);
        }
        for (yl ylVar2 : this.m) {
            if (ylVar2 instanceof BlockDecorator) {
                b((BlockDecorator) ylVar2);
            }
            if (this.q != null) {
                if (ylVar2 instanceof vm) {
                    ylVar2.a(canvas);
                } else {
                    ylVar2.a(this.r);
                    canvas.drawBitmap(this.q, 0.0f, 0.0f, this.d);
                }
            } else {
                ylVar2.a(canvas);
            }
        }
        this.f9893a.getViewportHandler().f();
        return this;
    }
}
