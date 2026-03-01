package supwisdom;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ChartStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class dl extends BlockDecorator {
    public List<yl> m;
    public dl n;
    public String o;

    /* JADX INFO: compiled from: ChartStrategy.java */
    public class a implements Comparator<yl> {
        public a(dl dlVar) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(yl ylVar, yl ylVar2) {
            return ylVar.a() - ylVar2.a();
        }
    }

    public dl(Chart chart) {
        super(chart);
        this.n = null;
        this.o = "ChartStrategy";
    }

    public final void b(BlockDecorator blockDecorator) {
        bo viewportHandler = this.f9893a.getViewportHandler();
        if (blockDecorator.i()) {
            viewportHandler.b(viewportHandler.c() + blockDecorator.e());
            viewportHandler.c(viewportHandler.d() + blockDecorator.f());
            viewportHandler.d(viewportHandler.e() + blockDecorator.g());
            viewportHandler.a(viewportHandler.b() + blockDecorator.d());
        }
    }

    @Override // supwisdom.yl
    public void c() {
        super.c();
        List<yl> list = this.m;
        if (list == null) {
            return;
        }
        Iterator<yl> it = list.iterator();
        while (it.hasNext()) {
            it.next().c();
        }
    }

    public List<yl> l() {
        return this.m;
    }

    public dl m() {
        return this.n;
    }

    public void n() {
        this.n = this;
        for (yl ylVar : this.m) {
            if (ylVar instanceof dl) {
                this.n = (dl) ylVar;
            }
        }
    }

    public final void o() {
        Collections.sort(this.m, new a(this));
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public dl a(Canvas canvas) {
        n();
        return this;
    }

    public dl a(yl ylVar) {
        if (ylVar == null) {
            Log.e(this.o, "the ChartDecorator added is NULL");
            return this;
        }
        if (this.m == null) {
            this.m = new LinkedList();
        }
        this.m.add(ylVar);
        o();
        return this;
    }

    @Override // supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        if (this.f9893a.getChartData() == null || this.f9893a.getChartStrategy().m() == null) {
            return;
        }
        this.f9893a.getViewportHandler().g();
        for (yl ylVar : this.m) {
            if (ylVar instanceof BlockDecorator) {
                a((BlockDecorator) ylVar);
            }
            if (ylVar.b()) {
                ylVar.a(view, motionEvent);
            }
        }
        this.f9893a.getViewportHandler().f();
    }

    public final void a(BlockDecorator blockDecorator) {
        if (blockDecorator instanceof dl) {
            for (yl ylVar : ((dl) blockDecorator).l()) {
                if (ylVar instanceof BlockDecorator) {
                    a((BlockDecorator) ylVar);
                }
            }
            return;
        }
        b(blockDecorator);
    }
}
