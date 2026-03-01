package supwisdom;

import android.graphics.Canvas;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: NodeValueDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class wm extends BlockDecorator {
    public final List<a> m;
    public int n;

    /* JADX INFO: compiled from: NodeValueDecorator.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9644a;
        public int b;
        public int c;
        public int d;

        public a(wm wmVar, int i, int i2, int i3, int i4) {
            this.f9644a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    public wm(Chart chart) {
        super(chart);
        this.m = new LinkedList();
        this.n = 30;
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        b(canvas);
        canvas.restore();
        return this;
    }

    public void b(Canvas canvas) {
        canvas.save();
        if (this.f9893a.b() && this.f9893a.getSelectedIndex() != null) {
            this.f9893a.getSelectedIndex().intValue();
        }
        synchronized (this.m) {
            this.m.clear();
            if (this.f9893a.getChartData() instanceof fk) {
                for (ek ekVar : ((fk) this.f9893a.getChartData()).k()) {
                    if (ekVar instanceof nk) {
                        b(canvas, ekVar);
                    } else {
                        a(canvas, ekVar);
                    }
                }
            } else {
                a(canvas, this.f9893a.getChartData());
            }
        }
        canvas.restore();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.graphics.Canvas r28, supwisdom.ek r29) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.wm.a(android.graphics.Canvas, supwisdom.ek):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x018a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.graphics.Canvas r27, supwisdom.ek r28) {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.wm.b(android.graphics.Canvas, supwisdom.ek):void");
    }

    public final boolean a(a aVar) {
        Iterator<a> it = this.m.iterator();
        while (it.hasNext()) {
            if (a(it.next(), aVar)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(a aVar, a aVar2) {
        int i = aVar.f9644a;
        int i2 = aVar.c + i;
        int i3 = aVar2.f9644a;
        if (i2 <= i3 || i3 + aVar2.c <= i) {
            return false;
        }
        int i4 = aVar.b;
        int i5 = aVar.d + i4;
        int i6 = aVar2.b;
        return i5 > i6 && i6 + aVar2.d > i4;
    }
}
