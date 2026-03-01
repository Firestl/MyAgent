package supwisdom;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: Easing.java */
/* JADX INFO: loaded from: classes.dex */
public class t4 {
    public static t4 b = new t4();
    public static String[] c = {"standard", "accelerate", "decelerate", "linear"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9245a = "identity";

    /* JADX INFO: compiled from: Easing.java */
    public static class a extends t4 {
        public static double h = 0.01d;
        public static double i = 1.0E-4d;
        public double d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f9246e;
        public double f;
        public double g;

        public a(String str) {
            this.f9245a = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.d = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i2 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i2);
            this.f9246e = Double.parseDouble(str.substring(i2, iIndexOf3).trim());
            int i3 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i3);
            this.f = Double.parseDouble(str.substring(i3, iIndexOf4).trim());
            int i4 = iIndexOf4 + 1;
            this.g = Double.parseDouble(str.substring(i4, str.indexOf(41, i4)).trim());
        }

        @Override // supwisdom.t4
        public double a(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double d2 = 0.5d;
            double d3 = 0.5d;
            while (d2 > h) {
                d2 *= 0.5d;
                d3 = c(d3) < d ? d3 + d2 : d3 - d2;
            }
            double d4 = d3 - d2;
            double dC = c(d4);
            double d5 = d3 + d2;
            double dC2 = c(d5);
            double d6 = d(d4);
            return (((d(d5) - d6) * (d - dC)) / (dC2 - dC)) + d6;
        }

        @Override // supwisdom.t4
        public double b(double d) {
            double d2 = 0.5d;
            double d3 = 0.5d;
            while (d2 > i) {
                d2 *= 0.5d;
                d3 = c(d3) < d ? d3 + d2 : d3 - d2;
            }
            double d4 = d3 - d2;
            double d5 = d3 + d2;
            return (d(d5) - d(d4)) / (c(d5) - c(d4));
        }

        public final double c(double d) {
            double d2 = 1.0d - d;
            double d3 = 3.0d * d2;
            return (this.d * d2 * d3 * d) + (this.f * d3 * d * d) + (d * d * d);
        }

        public final double d(double d) {
            double d2 = 1.0d - d;
            double d3 = 3.0d * d2;
            return (this.f9246e * d2 * d3 * d) + (this.g * d3 * d * d) + (d * d * d);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static t4 a(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new a(str);
        }
        byte b2 = -1;
        switch (str.hashCode()) {
            case -1354466595:
                if (str.equals("accelerate")) {
                    b2 = 1;
                }
                break;
            case -1263948740:
                if (str.equals("decelerate")) {
                    b2 = 2;
                }
                break;
            case -1102672091:
                if (str.equals("linear")) {
                    b2 = 3;
                }
                break;
            case 1312628413:
                if (str.equals("standard")) {
                    b2 = 0;
                }
                break;
        }
        if (b2 == 0) {
            return new a("cubic(0.4, 0.0, 0.2, 1)");
        }
        if (b2 == 1) {
            return new a("cubic(0.4, 0.05, 0.8, 0.7)");
        }
        if (b2 == 2) {
            return new a("cubic(0.0, 0.0, 0.2, 0.95)");
        }
        if (b2 == 3) {
            return new a("cubic(1, 1, 0, 0)");
        }
        Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(c));
        return b;
    }

    public double a(double d) {
        return d;
    }

    public double b(double d) {
        return 1.0d;
    }

    public String toString() {
        return this.f9245a;
    }
}
