package supwisdom;

import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: MathUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class wn {
    public static float[] a(ek ekVar, int i) {
        int i2 = i + 1;
        float[] fArr = new float[i2];
        float fC = (float) ekVar.c();
        float fE = (float) ekVar.e();
        float fAbs = Math.abs(fC - fE) / i;
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i3] = (i3 * fAbs) + fE;
        }
        return fArr;
    }

    public static float[] a(float f, float f2, int i) {
        int i2 = i + 1;
        float[] fArr = new float[i2];
        float fAbs = Math.abs(f2 - f) / i;
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i3] = (i3 * fAbs) + f;
        }
        return fArr;
    }

    public static Float[] a(Chart chart, AxisDecorator.YAxisLocation yAxisLocation) {
        double dDoubleValue;
        Double[] dArr = new Double[2];
        boolean z = chart.getChartConfig().b.get(yAxisLocation.getId()).k;
        for (pk pkVar : chart.getChartData().g()) {
            if (pkVar.b() == yAxisLocation) {
                if (dArr[0] == null || dArr[0].doubleValue() > pkVar.f()) {
                    dArr[0] = Double.valueOf(pkVar.f());
                }
                if (dArr[1] == null) {
                    dArr[1] = Double.valueOf(pkVar.e());
                } else if (dArr[1].doubleValue() < pkVar.e()) {
                    dArr[1] = Double.valueOf(pkVar.e());
                }
            }
        }
        if (chart.getChartData() instanceof fk) {
            for (ek ekVar : ((fk) chart.getChartData()).k()) {
                if (ekVar instanceof nk) {
                    for (dk dkVar : ((nk) ekVar).k()) {
                        if (dkVar.g().size() > 0 && ((pk) dkVar.g().get(0)).b() == yAxisLocation && dkVar.b() > dArr[1].doubleValue()) {
                            dArr[1] = Double.valueOf(dkVar.b());
                        }
                    }
                }
            }
        }
        Float[] fArrA = new Float[2];
        float fDoubleValue = (float) (dArr[1].doubleValue() - dArr[0].doubleValue());
        if (fDoubleValue == 0.0f) {
            if (dArr[1].doubleValue() > 0.0d) {
                dDoubleValue = (dArr[1].doubleValue() * 1.2d) - (dArr[0].doubleValue() * 0.8d);
            } else {
                dDoubleValue = (dArr[1].doubleValue() * 0.8d) - (dArr[0].doubleValue() * 1.2d);
            }
            fDoubleValue = (float) dDoubleValue;
        }
        if (dArr[1].doubleValue() > 0.0d) {
            float f = fDoubleValue * 0.2f;
            fArrA[1] = Float.valueOf(dArr[1].floatValue() + f < dArr[1].floatValue() * 1.2f ? dArr[1].floatValue() + f : dArr[1].floatValue() * 1.2f);
        } else {
            float f2 = fDoubleValue * 0.2f;
            fArrA[1] = Float.valueOf(dArr[1].floatValue() + f2 < dArr[1].floatValue() * 0.8f ? dArr[1].floatValue() + f2 : dArr[1].floatValue() * 0.8f);
        }
        if (dArr[0].doubleValue() > 0.0d) {
            float f3 = fDoubleValue * 0.2f;
            fArrA[0] = Float.valueOf(dArr[0].floatValue() - f3 < dArr[0].floatValue() * 0.8f ? dArr[0].floatValue() * 0.8f : dArr[0].floatValue() - f3);
        } else {
            float f4 = fDoubleValue * 0.2f;
            fArrA[0] = Float.valueOf(dArr[0].floatValue() - f4 < dArr[0].floatValue() * 1.2f ? dArr[0].floatValue() * 1.2f : dArr[0].floatValue() - f4);
        }
        if (z) {
            fArrA = a(fArrA);
        }
        if (fArrA[0].floatValue() == 0.0f && fArrA[1].floatValue() == 0.0f) {
            fArrA[0] = Float.valueOf(-0.5f);
            fArrA[1] = Float.valueOf(0.5f);
        }
        return fArrA;
    }

    public static Float[] a(Float[] fArr) {
        Float[] fArr2 = new Float[2];
        float fFloatValue = fArr[1].floatValue();
        Float fValueOf = Float.valueOf(0.0f);
        if (fFloatValue > 0.0f) {
            fArr2[0] = fValueOf;
            fArr2[1] = fArr[1];
        } else {
            fArr2[0] = fArr[0];
            fArr2[1] = fValueOf;
        }
        return fArr2;
    }

    public static boolean a(String str) {
        if (str == null || str.equals(com.igexin.push.core.b.m) || str.equals("")) {
            return true;
        }
        return Pattern.compile("^\\d+$|-\\d+$").matcher(str).matches() || Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$").matcher(str).matches();
    }
}
