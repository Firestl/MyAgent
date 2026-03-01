package supwisdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.charts.FunnelChart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FunnelDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class em extends BlockDecorator {
    public pl m;

    public em(Chart chart) {
        super(chart);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        this.m = ((FunnelChart) this.f9893a).getFunnelConfig();
        canvas.save();
        b(canvas);
        canvas.restore();
        return super.a(canvas);
    }

    public void b(Canvas canvas) {
        Iterator<qk> it;
        int i;
        int i2;
        String str;
        List<qk> listJ = ((gk) this.f9893a.getChartData()).j();
        RectF rectFL = l();
        float f = 2.0f;
        float fWidth = rectFL.left + (rectFL.width() / 2.0f);
        float f2 = rectFL.top;
        float f3 = rectFL.left;
        int[] iArr = this.m.x;
        if (iArr == null || iArr.length == 0) {
            iArr = this.f9893a.getChartPalette().f8808a;
        }
        int[] iArr2 = iArr;
        String str2 = "value";
        float fWidth2 = 0.0f;
        if (this.m.w) {
            float f4 = 0.0f;
            float fWidth3 = 0.0f;
            float f5 = 0.0f;
            float f6 = 0.0f;
            for (qk qkVar : listJ) {
                float fHeight = rectFL.height() / qkVar.a().size();
                int i3 = 0;
                while (i3 < qkVar.a().size()) {
                    Double d = (Double) ((zk) qkVar.a().get(i3)).a().get(str2);
                    if (d == null) {
                        i2 = i3;
                        str = str2;
                    } else {
                        if (i3 == 0) {
                            fWidth2 = fWidth - ((rectFL.width() / f) * f6);
                            f4 = rectFL.top;
                            fWidth3 = ((rectFL.width() / f) * f6) + fWidth;
                            f5 = rectFL.top;
                        }
                        float fDoubleValue = (float) (d.doubleValue() / qkVar.b());
                        float fWidth4 = fWidth - ((rectFL.width() / f) * fDoubleValue);
                        float f7 = ((i3 + 1) * fHeight) + f2;
                        zk zkVar = (zk) qkVar.a().get(i3);
                        String str3 = str2;
                        Path path = new Path();
                        path.moveTo(fWidth3, f5);
                        path.lineTo(fWidth2, f4);
                        path.lineTo(fWidth4, f7);
                        path.lineTo((rectFL.width() * fDoubleValue) + fWidth4, f7);
                        path.close();
                        this.d.setColor(iArr2[i3 % iArr2.length]);
                        this.d.setStyle(Paint.Style.FILL);
                        canvas.drawPath(path, this.d);
                        this.d.setColor(Color.parseColor(this.m.u));
                        this.d.setStyle(Paint.Style.STROKE);
                        this.d.setStrokeWidth(tn.a(this.f9893a.getContext(), this.m.r));
                        canvas.drawPath(path, this.d);
                        float f8 = f7 - (fHeight / 2.0f);
                        i2 = i3;
                        str = str3;
                        a(canvas, fWidth, f8, zkVar, qkVar, rectFL, -16777216);
                        fWidth3 = fWidth4 + (rectFL.width() * fDoubleValue);
                        f6 = fDoubleValue;
                        fWidth2 = fWidth4;
                        f4 = f7;
                        f5 = f4;
                    }
                    i3 = i2 + 1;
                    str2 = str;
                    f = 2.0f;
                }
            }
            return;
        }
        Iterator<qk> it2 = listJ.iterator();
        float f9 = f2;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (it2.hasNext()) {
            qk next = it2.next();
            float fHeight2 = rectFL.height() / next.a().size();
            float f13 = f10;
            float fWidth5 = f11;
            float fWidth6 = fWidth2;
            float f14 = f9;
            float f15 = f12;
            int i4 = 0;
            while (i4 < next.a().size()) {
                Double d2 = (Double) ((zk) next.a().get(i4)).a().get("value");
                if (d2 == null) {
                    i = i4;
                    it = it2;
                } else if (i4 == 0) {
                    Path path2 = new Path();
                    float fDoubleValue2 = (float) (d2.doubleValue() / next.b());
                    fWidth6 = fWidth - ((rectFL.width() / 2.0f) * fDoubleValue2);
                    path2.moveTo(fWidth6, f14);
                    path2.lineTo((rectFL.width() * fDoubleValue2) + fWidth6, f14);
                    fWidth5 = fWidth6 + (rectFL.width() * fDoubleValue2);
                    f15 = f14;
                    f13 = f15;
                    i = i4;
                    it = it2;
                } else {
                    zk zkVar2 = (zk) next.a().get(i4 - 1);
                    Path path3 = new Path();
                    float fDoubleValue3 = (float) (d2.doubleValue() / next.b());
                    float fWidth7 = fWidth - ((rectFL.width() / 2.0f) * fDoubleValue3);
                    it = it2;
                    float f16 = (i4 * fHeight2) + f2;
                    path3.moveTo(fWidth5, f15);
                    path3.lineTo(fWidth6, f13);
                    path3.lineTo(fWidth7, f16);
                    path3.lineTo((rectFL.width() * fDoubleValue3) + fWidth7, f16);
                    path3.close();
                    this.d.setColor(iArr2[(i4 % iArr2.length) - 1]);
                    this.d.setStyle(Paint.Style.FILL);
                    canvas.drawPath(path3, this.d);
                    this.d.setColor(Color.parseColor(this.m.u));
                    this.d.setStyle(Paint.Style.STROKE);
                    this.d.setStrokeWidth(tn.a(this.f9893a.getContext(), this.m.r));
                    canvas.drawPath(path3, this.d);
                    i = i4;
                    a(canvas, fWidth, f16 - (fHeight2 / 2.0f), zkVar2, next, rectFL, -16777216);
                    fWidth5 = fWidth7 + (rectFL.width() * fDoubleValue3);
                    f14 = f16;
                    f15 = f14;
                    f13 = f15;
                    fWidth6 = fWidth7;
                }
                i4 = i + 1;
                it2 = it;
            }
            Iterator<qk> it3 = it2;
            Path path4 = new Path();
            zk zkVar3 = (zk) next.a().get(next.a().size() - 1);
            float fB = (float) (0.0d / next.b());
            float fWidth8 = fWidth - ((rectFL.width() / 2.0f) * fB);
            float size = (next.a().size() * fHeight2) + f2;
            path4.moveTo(fWidth5, f15);
            path4.lineTo(fWidth6, f13);
            path4.lineTo(fWidth8, size);
            path4.lineTo(fWidth8 + (rectFL.width() * fB), size);
            path4.close();
            this.d.setColor(iArr2[(next.a().size() % iArr2.length) - 1]);
            this.d.setStyle(Paint.Style.FILL);
            canvas.drawPath(path4, this.d);
            this.d.setColor(Color.parseColor(this.m.u));
            this.d.setStyle(Paint.Style.STROKE);
            this.d.setStrokeWidth(tn.a(this.f9893a.getContext(), this.m.r));
            canvas.drawPath(path4, this.d);
            a(canvas, fWidth, size - (fHeight2 / 2.0f), zkVar3, next, rectFL, -16777216);
            fWidth2 = fWidth6;
            f11 = fWidth5;
            f12 = f15;
            it2 = it3;
            f9 = size;
            f10 = f13;
        }
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.m = ((FunnelChart) this.f9893a).getFunnelConfig();
        this.f9894e = true;
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setColor(-65536);
        this.d.setStrokeWidth(tn.a(this.f9893a.getContext(), this.m.v));
    }

    public RectF l() {
        bo viewportHandler = this.f9893a.getViewportHandler();
        RectF rectF = new RectF();
        rectF.left = viewportHandler.c();
        rectF.top = viewportHandler.e();
        rectF.right = this.f9893a.getMeasuredWidth() - viewportHandler.d();
        rectF.bottom = this.f9893a.getMeasuredHeight() - viewportHandler.b();
        float fWidth = rectF.width();
        rectF.height();
        float f = rectF.left;
        pl plVar = this.m;
        float f2 = plVar.n;
        rectF.left = f + (fWidth * f2);
        rectF.right = (rectF.right - ((1.0f - plVar.o) * fWidth)) + (fWidth * f2);
        return rectF;
    }

    public final void a(Canvas canvas, float f, float f2, zk zkVar, qk qkVar, RectF rectF, int i) {
        String strA;
        this.d.setTextSize(tn.a(this.f9893a.getContext(), this.m.t));
        this.d.setStyle(Paint.Style.FILL);
        Paint.FontMetrics fontMetrics = this.d.getFontMetrics();
        float f3 = fontMetrics.bottom - fontMetrics.top;
        String str = zkVar.a().get("subtitle") != null ? (String) zkVar.a().get("subtitle") : "";
        this.d.setColor(i);
        String str2 = this.m.s;
        byte b = -1;
        int iHashCode = str2.hashCode();
        if (iHashCode != -1364013995) {
            if (iHashCode != 3317767) {
                if (iHashCode == 108511772 && str2.equals("right")) {
                    b = 1;
                }
            } else if (str2.equals("left")) {
                b = 0;
            }
        } else if (str2.equals("center")) {
            b = 2;
        }
        if (b == 0) {
            this.d.setTextAlign(Paint.Align.RIGHT);
            f -= rectF.width() / 2.0f;
        } else if (b != 1) {
            this.d.setTextAlign(Paint.Align.CENTER);
            this.d.setColor(Color.parseColor("#ffffff"));
        } else {
            this.d.setTextAlign(Paint.Align.LEFT);
            f += rectF.width() / 2.0f;
        }
        Double dValueOf = Double.valueOf(String.valueOf(zkVar.a().get("value")));
        if (this.m.p.equals("percentage")) {
            strA = xn.a((Number) dValueOf, this.m.q, false, true);
        } else {
            strA = xn.a((Number) dValueOf, this.m.q, true, false);
        }
        canvas.drawText(zkVar.a().get("name") + Operators.SPACE_STR + strA, f, f2, this.d);
        this.d.setTextSize(tn.a(this.f9893a.getContext(), this.m.t * 0.8f));
        canvas.drawText(str, f, f2 + f3, this.d);
    }
}
