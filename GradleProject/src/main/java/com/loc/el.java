package com.loc;

import android.content.Context;
import com.amap.api.location.DPoint;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: OffsetUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class el {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static double f3785a = 3.141592653589793d;
    public static final List<DPoint> b = new ArrayList(Arrays.asList(new DPoint(23.379947d, 119.757001d), new DPoint(24.983296d, 120.474496d), new DPoint(25.518722d, 121.359866d), new DPoint(25.41329d, 122.443582d), new DPoint(24.862708d, 122.288354d), new DPoint(24.461292d, 122.188319d), new DPoint(21.584761d, 120.968923d), new DPoint(21.830837d, 120.654445d)));

    public static double a(double d) {
        return Math.sin(d * 3000.0d * (f3785a / 180.0d)) * 2.0E-5d;
    }

    public static double a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static DPoint a(Context context, DPoint dPoint) {
        if (context == null) {
            return null;
        }
        return b(dPoint);
    }

    public static DPoint a(DPoint dPoint) {
        if (dPoint != null) {
            try {
                if (ej.a(dPoint.getLatitude(), dPoint.getLongitude())) {
                    return c(dPoint);
                }
                if (!ej.a(new DPoint(dPoint.getLatitude(), dPoint.getLongitude()), b)) {
                    return dPoint;
                }
                DPoint dPointC = c(dPoint);
                double latitude = dPointC.getLatitude();
                double longitude = dPointC.getLongitude();
                double d = longitude - 105.0d;
                double d2 = latitude - 35.0d;
                double d3 = d * 2.0d;
                double d4 = d * 0.1d;
                double d5 = d4 * d2;
                double d6 = 6.0d * d;
                double dSqrt = (-100.0d) + d3 + (d2 * 3.0d) + (d2 * 0.2d * d2) + d5 + (Math.sqrt(Math.abs(d)) * 0.2d) + ((((Math.sin(f3785a * d6) * 20.0d) + (Math.sin(f3785a * d3) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f3785a * d2) * 20.0d) + (Math.sin((d2 / 3.0d) * f3785a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * f3785a) * 160.0d) + (Math.sin((f3785a * d2) / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
                double dSqrt2 = d + 300.0d + (d2 * 2.0d) + (d4 * d) + d5 + (Math.sqrt(Math.abs(d)) * 0.1d) + ((((Math.sin(d6 * f3785a) * 20.0d) + (Math.sin(d3 * f3785a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f3785a * d) * 20.0d) + (Math.sin((d / 3.0d) * f3785a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d / 12.0d) * f3785a) * 150.0d) + (Math.sin((d / 30.0d) * f3785a) * 300.0d)) * 2.0d) / 3.0d);
                double d7 = (latitude / 180.0d) * f3785a;
                double dSin = Math.sin(d7);
                double d8 = 1.0d - ((0.006693421622965943d * dSin) * dSin);
                double dSqrt3 = Math.sqrt(d8);
                DPoint dPoint2 = new DPoint(((dSqrt * 180.0d) / ((6335552.717000426d / (d8 * dSqrt3)) * f3785a)) + latitude, longitude + ((dSqrt2 * 180.0d) / (((6378245.0d / dSqrt3) * Math.cos(d7)) * f3785a)));
                return new DPoint((latitude * 2.0d) - dPoint2.getLatitude(), (longitude * 2.0d) - dPoint2.getLongitude());
            } catch (Throwable th) {
                ej.a(th, "OffsetUtil", "b2G");
            }
        }
        return dPoint;
    }

    public static double b(double d) {
        return Math.cos(d * 3000.0d * (f3785a / 180.0d)) * 3.0E-6d;
    }

    public static double b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static DPoint b(Context context, DPoint dPoint) {
        try {
            if (!ej.a(dPoint.getLatitude(), dPoint.getLongitude())) {
                return dPoint;
            }
            double longitude = ((long) (dPoint.getLongitude() * 100000.0d)) % 36000000;
            double latitude = ((long) (dPoint.getLatitude() * 100000.0d)) % 36000000;
            double d = (int) ((-a(longitude, latitude)) + longitude);
            double d2 = (int) ((-b(longitude, latitude)) + latitude);
            int i = 1;
            double d3 = (int) ((-a(d, d2)) + longitude + ((double) (longitude > 0.0d ? 1 : -1)));
            double d4 = (-b(d3, d2)) + latitude;
            if (latitude <= 0.0d) {
                i = -1;
            }
            return a(context, new DPoint(((double) ((int) (d4 + ((double) i)))) / 100000.0d, d3 / 100000.0d));
        } catch (Throwable th) {
            ej.a(th, "OffsetUtil", "marbar2G");
            return dPoint;
        }
    }

    public static DPoint b(DPoint dPoint) {
        try {
            if (!ej.a(dPoint.getLatitude(), dPoint.getLongitude())) {
                return dPoint;
            }
            double[] dArrA = eq.a(dPoint.getLongitude(), dPoint.getLatitude());
            return new DPoint(dArrA[1], dArrA[0]);
        } catch (Throwable th) {
            ej.a(th, "OffsetUtil", "cover part2");
            return dPoint;
        }
    }

    public static double c(double d) {
        return new BigDecimal(d).setScale(8, 4).doubleValue();
    }

    public static DPoint c(DPoint dPoint) {
        double longitude = 0.006401062d;
        double latitude = 0.0060424805d;
        DPoint dPoint2 = null;
        for (int i = 0; i < 2; i++) {
            double longitude2 = dPoint.getLongitude();
            double latitude2 = dPoint.getLatitude();
            dPoint2 = new DPoint();
            double d = longitude2 - longitude;
            double d2 = latitude2 - latitude;
            DPoint dPoint3 = new DPoint();
            double d3 = (d * d) + (d2 * d2);
            double dCos = (Math.cos(b(d) + Math.atan2(d2, d)) * (a(d2) + Math.sqrt(d3))) + 0.0065d;
            double dSin = (Math.sin(b(d) + Math.atan2(d2, d)) * (a(d2) + Math.sqrt(d3))) + 0.006d;
            dPoint3.setLongitude(c(dCos));
            dPoint3.setLatitude(c(dSin));
            dPoint2.setLongitude(c((longitude2 + d) - dPoint3.getLongitude()));
            dPoint2.setLatitude(c((latitude2 + d2) - dPoint3.getLatitude()));
            longitude = dPoint.getLongitude() - dPoint2.getLongitude();
            latitude = dPoint.getLatitude() - dPoint2.getLatitude();
        }
        return dPoint2;
    }
}
