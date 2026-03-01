package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import io.dcloud.common.util.TitleNViewUtil;
import java.util.Iterator;

/* JADX INFO: compiled from: PolarNodeSelectDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class ym extends im {
    public ym(Chart chart) {
        super(chart);
    }

    @Override // supwisdom.im
    public void b(Canvas canvas) {
        this.n = this.o.q();
        this.f9893a.getChartData().f();
        int length = 0;
        int iIntValue = this.f9893a.getSelectedIndex() == null ? 0 : this.f9893a.getSelectedIndex().intValue();
        RectF rectF = this.m;
        float f = rectF.right;
        float f2 = rectF.left;
        float f3 = rectF.bottom;
        float f4 = rectF.top;
        Iterator it = this.f9893a.getChartData().g().iterator();
        if (it.hasNext()) {
            sk skVar = (sk) it.next();
            int[] iArrL = skVar.l();
            skVar.n();
            float fP = skVar.p();
            RectF rectFA = a(this.m, fP);
            float f5 = rectFA.right;
            float f6 = rectFA.left;
            Iterator<yk<Double>> it2 = skVar.i().iterator();
            int i = 0;
            while (it2.hasNext()) {
                al alVar = (al) it2.next();
                if (iArrL != null) {
                    if (length >= iArrL.length) {
                        length %= iArrL.length;
                    }
                    this.d.setColor(iArrL[length]);
                } else {
                    this.d.setColor(this.f9893a.getChartPalette().f8808a[length % 7]);
                }
                if (this.f9893a.getSelectedIndex() != null && i == iIntValue) {
                    this.d.setStyle(Paint.Style.FILL);
                    this.d.setColor(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR));
                    this.d.setAlpha(120);
                    canvas.drawArc(a(this.m, fP), this.n, alVar.c(), true, this.d);
                }
                length++;
                i++;
                this.n = Math.abs(this.n + alVar.c());
            }
        }
    }

    @Override // supwisdom.im, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
    }
}
