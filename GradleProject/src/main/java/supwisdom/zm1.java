package supwisdom;

/* JADX INFO: compiled from: PositionUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class zm1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static double f10013a = 3.141592653589793d;
    public static double b = 6378245.0d;
    public static double c = 0.006693421622965943d;
    public static String d = "WGS84";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f10014e = "GCJ02";
    public static String f = "BD09";

    public static vm1 a(String str, String str2, double d2, double d3) {
        if (str.equals(f10014e) && str2.equals(d)) {
            return d(d2, d3);
        }
        if (str.equals(f10014e) && str2.equals(f)) {
            return c(d2, d3);
        }
        if (str.equals(d) && str2.equals(f10014e)) {
            return e(d2, d3);
        }
        if (str.equals(d) && str2.equals(f)) {
            vm1 vm1VarE = e(d2, d3);
            return c(vm1VarE.a(), vm1VarE.b());
        }
        if (str.equals(f) && str2.equals(d)) {
            return b(d2, d3);
        }
        if (str.equals(f) && str2.equals(f10014e)) {
            return a(d2, d3);
        }
        return null;
    }

    public static vm1 b(double d2, double d3) {
        vm1 vm1VarA = a(d2, d3);
        return d(vm1VarA.a(), vm1VarA.b());
    }

    public static vm1 c(double d2, double d3) {
        double dSqrt = Math.sqrt((d3 * d3) + (d2 * d2)) + (Math.sin(f10013a * d2) * 2.0E-5d);
        double dAtan2 = Math.atan2(d2, d3) + (Math.cos(d3 * f10013a) * 3.0E-6d);
        return new vm1((dSqrt * Math.sin(dAtan2)) + 0.006d, (Math.cos(dAtan2) * dSqrt) + 0.0065d);
    }

    public static vm1 d(double d2, double d3) {
        vm1 vm1VarG = g(d2, d3);
        return new vm1((d2 * 2.0d) - vm1VarG.a(), (d3 * 2.0d) - vm1VarG.b());
    }

    public static vm1 e(double d2, double d3) {
        if (f(d2, d3)) {
            return null;
        }
        double d4 = d3 - 105.0d;
        double d5 = d2 - 35.0d;
        double dH = h(d4, d5);
        double dI = i(d4, d5);
        double d6 = (d2 / 180.0d) * f10013a;
        double dSin = Math.sin(d6);
        double d7 = 1.0d - ((c * dSin) * dSin);
        double dSqrt = Math.sqrt(d7);
        double d8 = b;
        return new vm1(d2 + ((dH * 180.0d) / ((((1.0d - c) * d8) / (d7 * dSqrt)) * f10013a)), d3 + ((dI * 180.0d) / (((d8 / dSqrt) * Math.cos(d6)) * f10013a)));
    }

    public static boolean f(double d2, double d3) {
        return d3 < 72.004d || d3 > 137.8347d || d2 < 0.8293d || d2 > 55.8271d;
    }

    public static vm1 g(double d2, double d3) {
        if (f(d2, d3)) {
            return new vm1(d2, d3);
        }
        double d4 = d3 - 105.0d;
        double d5 = d2 - 35.0d;
        double dH = h(d4, d5);
        double dI = i(d4, d5);
        double d6 = (d2 / 180.0d) * f10013a;
        double dSin = Math.sin(d6);
        double d7 = 1.0d - ((c * dSin) * dSin);
        double dSqrt = Math.sqrt(d7);
        double d8 = b;
        return new vm1(d2 + ((dH * 180.0d) / ((((1.0d - c) * d8) / (d7 * dSqrt)) * f10013a)), d3 + ((dI * 180.0d) / (((d8 / dSqrt) * Math.cos(d6)) * f10013a)));
    }

    public static double h(double d2, double d3) {
        double d4 = d2 * 2.0d;
        return (-100.0d) + d4 + (d3 * 3.0d) + (d3 * 0.2d * d3) + (0.1d * d2 * d3) + (Math.sqrt(Math.abs(d2)) * 0.2d) + ((((Math.sin((d2 * 6.0d) * f10013a) * 20.0d) + (Math.sin(d4 * f10013a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f10013a * d3) * 20.0d) + (Math.sin((d3 / 3.0d) * f10013a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d3 / 12.0d) * f10013a) * 160.0d) + (Math.sin((d3 * f10013a) / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    public static double i(double d2, double d3) {
        double d4 = d2 * 0.1d;
        return d2 + 300.0d + (d3 * 2.0d) + (d4 * d2) + (d4 * d3) + (Math.sqrt(Math.abs(d2)) * 0.1d) + ((((Math.sin((6.0d * d2) * f10013a) * 20.0d) + (Math.sin((d2 * 2.0d) * f10013a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f10013a * d2) * 20.0d) + (Math.sin((d2 / 3.0d) * f10013a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * f10013a) * 150.0d) + (Math.sin((d2 / 30.0d) * f10013a) * 300.0d)) * 2.0d) / 3.0d);
    }

    public static vm1 a(double d2, double d3) {
        double d4 = d3 - 0.0065d;
        double d5 = d2 - 0.006d;
        double dSqrt = Math.sqrt((d4 * d4) + (d5 * d5)) - (Math.sin(f10013a * d5) * 2.0E-5d);
        double dAtan2 = Math.atan2(d5, d4) - (Math.cos(d4 * f10013a) * 3.0E-6d);
        return new vm1(dSqrt * Math.sin(dAtan2), Math.cos(dAtan2) * dSqrt);
    }
}
