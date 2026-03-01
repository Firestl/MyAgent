package com.amap.api.location;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.speech.utils.cuid.util.DeviceId;
import com.loc.ej;
import com.loc.el;
import com.loc.en;
import com.loc.ep;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CoordinateConverter {
    public static int b = 0;
    public static int c = 1;
    public static int d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f1647e = 4;
    public static int f = 8;
    public static int g = 16;
    public static int h = 32;
    public static int i = 64;
    public Context j;
    public CoordType k = null;
    public DPoint l = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public DPoint f1648a = null;

    /* JADX INFO: renamed from: com.amap.api.location.CoordinateConverter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1649a;

        static {
            int[] iArr = new int[CoordType.values().length];
            f1649a = iArr;
            try {
                iArr[CoordType.BAIDU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1649a[CoordType.MAPBAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1649a[CoordType.MAPABC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1649a[CoordType.SOSOMAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1649a[CoordType.ALIYUN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1649a[CoordType.GOOGLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1649a[CoordType.GPS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.j = context;
    }

    public static float calculateLineDistance(DPoint dPoint, DPoint dPoint2) {
        try {
            return ep.a(dPoint, dPoint2);
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    public static boolean isAMapDataAvailable(double d2, double d3) {
        return ej.a(d2, d3);
    }

    public synchronized DPoint convert() throws Exception {
        int i2;
        int i3;
        DPoint dPointA;
        if (this.k == null) {
            throw new IllegalArgumentException("转换坐标类型不能为空");
        }
        if (this.l == null) {
            throw new IllegalArgumentException("转换坐标源不能为空");
        }
        if (this.l.getLongitude() > 180.0d || this.l.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        }
        if (this.l.getLatitude() > 90.0d || this.l.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        }
        boolean z = false;
        String str = null;
        switch (AnonymousClass1.f1649a[this.k.ordinal()]) {
            case 1:
                this.f1648a = el.a(this.l);
                if ((b & c) == 0) {
                    str = DeviceId.OLD_EXT_DIR;
                    i2 = b;
                    i3 = c;
                    b = i2 | i3;
                    z = true;
                }
                break;
            case 2:
                this.f1648a = el.b(this.j, this.l);
                if ((b & d) == 0) {
                    str = "mapbar";
                    i2 = b;
                    i3 = d;
                    b = i2 | i3;
                    z = true;
                }
                break;
            case 3:
                if ((b & f1647e) == 0) {
                    str = "mapabc";
                    b |= f1647e;
                    z = true;
                }
                dPointA = this.l;
                this.f1648a = dPointA;
                break;
            case 4:
                if ((b & f) == 0) {
                    str = "sosomap";
                    b |= f;
                    z = true;
                }
                dPointA = this.l;
                this.f1648a = dPointA;
                break;
            case 5:
                if ((b & g) == 0) {
                    str = "aliyun";
                    b |= g;
                    z = true;
                }
                dPointA = this.l;
                this.f1648a = dPointA;
                break;
            case 6:
                if ((b & h) == 0) {
                    str = "google";
                    b |= h;
                    z = true;
                }
                dPointA = this.l;
                this.f1648a = dPointA;
                break;
            case 7:
                if ((b & i) == 0) {
                    str = "gps";
                    b |= i;
                    z = true;
                }
                dPointA = el.a(this.j, this.l);
                this.f1648a = dPointA;
                break;
        }
        if (z) {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("amap_loc_coordinate", str);
            }
            en.a(this.j, "O021", jSONObject);
        }
        return this.f1648a;
    }

    public synchronized CoordinateConverter coord(DPoint dPoint) throws Exception {
        try {
            if (dPoint == null) {
                throw new IllegalArgumentException("传入经纬度对象为空");
            }
            if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                throw new IllegalArgumentException("请传入合理经度");
            }
            if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
                throw new IllegalArgumentException("请传入合理纬度");
            }
            this.l = dPoint;
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public synchronized CoordinateConverter from(CoordType coordType) {
        this.k = coordType;
        return this;
    }
}
