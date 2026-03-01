package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.Iterator;

/* JADX INFO: compiled from: SelectValueDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class zm extends vm {
    public int r;

    public zm(Chart chart) {
        super(chart);
        this.r = -1;
        this.d.setStyle(Paint.Style.FILL);
        this.d.setTextAlign(Paint.Align.CENTER);
        this.d.setAntiAlias(true);
    }

    @Override // supwisdom.vm, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        b(canvas);
        canvas.restore();
        return this;
    }

    public void b(Canvas canvas) {
        canvas.save();
        if (this.f9893a.b() && this.f9893a.getSelectedIndex() != null) {
            this.r = this.f9893a.getSelectedIndex().intValue();
        }
        if (this.f9893a.getChartData() instanceof fk) {
            Iterator<ek> it = ((fk) this.f9893a.getChartData()).k().iterator();
            while (it.hasNext()) {
                a(canvas, it.next());
            }
        } else {
            a(canvas, this.f9893a.getChartData());
        }
        canvas.restore();
    }

    @Override // supwisdom.vm
    public void a(Canvas canvas, float[] fArr) {
        b(canvas);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.graphics.Canvas r17, supwisdom.ek r18) {
        /*
            r16 = this;
            r0 = r16
            java.util.List r1 = r18.g()
            java.util.Iterator r1 = r1.iterator()
        La:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto Lc5
            java.lang.Object r2 = r1.next()
            supwisdom.pk r2 = (supwisdom.pk) r2
            com.alibaba.dt.AChartsLib.charts.Chart r3 = r0.f9893a
            com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator$YAxisLocation r4 = r2.b()
            java.lang.Float[] r3 = supwisdom.wn.a(r3, r4)
            r4 = 0
            r5 = 0
        L22:
            java.util.List r6 = r2.i()
            int r6 = r6.size()
            if (r5 >= r6) goto Lc1
            java.util.List r6 = r2.i()
            java.lang.Object r6 = r6.get(r5)
            supwisdom.yk r6 = (supwisdom.yk) r6
            java.lang.Object r7 = r6.b()
            if (r7 != 0) goto L40
        L3c:
            r10 = r17
            goto Lbd
        L40:
            r7 = 2
            float[] r14 = new float[r7]
            float r8 = (float) r5
            r14[r4] = r8
            java.lang.Object r8 = r6.b()
            java.lang.Double r8 = (java.lang.Double) r8
            float r8 = r8.floatValue()
            r15 = 1
            r14[r15] = r8
            com.alibaba.dt.AChartsLib.charts.Chart r8 = r0.f9893a
            supwisdom.zn r8 = r8.getTransformUtil()
            com.alibaba.dt.AChartsLib.charts.Chart r9 = r0.f9893a
            supwisdom.ek r9 = r9.getChartData()
            double r9 = r9.d()
            float r10 = (float) r9
            com.alibaba.dt.AChartsLib.charts.Chart r9 = r0.f9893a
            supwisdom.ek r9 = r9.getChartData()
            double r11 = r9.a()
            float r11 = (float) r11
            r9 = r3[r4]
            float r12 = r9.floatValue()
            r9 = r3[r15]
            float r13 = r9.floatValue()
            r9 = r14
            r8.a(r9, r10, r11, r12, r13)
            boolean r8 = r16.j()
            if (r8 == 0) goto L8e
            com.alibaba.dt.AChartsLib.charts.Chart r8 = r0.f9893a
            supwisdom.zn r8 = r8.getTransformUtil()
            r8.b(r14)
        L8e:
            android.graphics.Paint r8 = r0.d
            int r9 = r2.s
            r8.setColor(r9)
            android.graphics.Paint r8 = r0.d
            int r9 = r2.r
            float r9 = (float) r9
            r8.setTextSize(r9)
            boolean r8 = r2.q
            if (r8 == 0) goto L3c
            int r8 = r0.r
            if (r8 != r5) goto L3c
            java.lang.Object r6 = r6.b()
            java.lang.Number r6 = (java.lang.Number) r6
            java.lang.String r6 = supwisdom.xn.a(r6, r7, r15)
            r7 = r14[r4]
            r8 = r14[r15]
            float r9 = r2.t
            float r8 = r8 - r9
            android.graphics.Paint r9 = r0.d
            r10 = r17
            r10.drawText(r6, r7, r8, r9)
        Lbd:
            int r5 = r5 + 1
            goto L22
        Lc1:
            r10 = r17
            goto La
        Lc5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.zm.a(android.graphics.Canvas, supwisdom.ek):void");
    }
}
