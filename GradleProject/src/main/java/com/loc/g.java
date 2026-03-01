package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.DPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: GpsLocation.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    public static AMapLocation j = null;
    public static long k = 0;
    public static Object l = new Object();
    public static long q = 0;
    public static boolean t = false;
    public static boolean u = false;
    public static volatile AMapLocation y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f3796a;
    public LocationManager b;
    public AMapLocationClientOption c;
    public dr f;
    public Context z;
    public long A = 0;
    public long d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3797e = false;
    public int B = 0;
    public int g = 240;
    public int h = 80;
    public AMapLocation i = null;
    public long m = 0;
    public float n = 0.0f;
    public Object o = new Object();
    public Object p = new Object();
    public AMapLocationClientOption.GeoLanguage r = AMapLocationClientOption.GeoLanguage.DEFAULT;
    public boolean s = true;
    public long v = 0;
    public int w = 0;
    public LocationListener x = null;
    public int C = 0;
    public GpsStatus D = null;
    public GpsStatus.Listener E = new GpsStatus.Listener() { // from class: com.loc.g.1
        @Override // android.location.GpsStatus.Listener
        public final void onGpsStatusChanged(int i) {
            Iterable<GpsSatellite> satellites;
            try {
                if (g.this.b == null) {
                    return;
                }
                g.this.D = g.this.b.getGpsStatus(g.this.D);
                int i2 = 0;
                if (i == 2) {
                    g.this.C = 0;
                    return;
                }
                if (i != 4) {
                    return;
                }
                try {
                    if (g.this.D != null && (satellites = g.this.D.getSatellites()) != null) {
                        Iterator<GpsSatellite> it = satellites.iterator();
                        int maxSatellites = g.this.D.getMaxSatellites();
                        while (it.hasNext() && i2 < maxSatellites) {
                            if (it.next().usedInFix()) {
                                i2++;
                            }
                        }
                    }
                } catch (Throwable th) {
                    ej.a(th, "GpsLocation", "GPS_EVENT_SATELLITE_STATUS");
                }
                g.this.C = i2;
            } catch (Throwable th2) {
                ej.a(th2, "GpsLocation", "onGpsStatusChanged");
            }
        }
    };
    public String F = null;
    public boolean G = false;
    public int H = 0;
    public boolean I = false;

    /* JADX INFO: compiled from: GpsLocation.java */
    public static class a implements LocationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g f3799a;

        public a(g gVar) {
            this.f3799a = gVar;
        }

        public final void a() {
            this.f3799a = null;
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            try {
                if (this.f3799a != null) {
                    g.a(this.f3799a, location);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                if (this.f3799a != null) {
                    g.a(this.f3799a, str);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
            try {
                if (this.f3799a != null) {
                    g.a(this.f3799a, i);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public g(Context context, Handler handler) {
        this.f = null;
        this.z = context;
        this.f3796a = handler;
        try {
            this.b = (LocationManager) context.getSystemService("location");
        } catch (Throwable th) {
            ej.a(th, "GpsLocation", "<init>");
        }
        this.f = new dr();
    }

    private void a(int i, int i2, String str, long j2) {
        try {
            if (this.f3796a == null || this.c.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                return;
            }
            Message messageObtain = Message.obtain();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setProvider("gps");
            aMapLocation.setErrorCode(i2);
            aMapLocation.setLocationDetail(str);
            aMapLocation.setLocationType(1);
            messageObtain.obj = aMapLocation;
            messageObtain.what = i;
            this.f3796a.sendMessageDelayed(messageObtain, j2);
        } catch (Throwable unused) {
        }
    }

    private void a(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() != 15 || AMapLocationClientOption.AMapLocationMode.Device_Sensors.equals(this.c.getLocationMode())) {
            if (this.c.getLocationMode().equals(AMapLocationClientOption.AMapLocationMode.Device_Sensors) && this.c.getDeviceModeDistanceFilter() > 0.0f) {
                b(aMapLocation);
            } else if (ep.b() - this.v >= this.c.getInterval() - 200) {
                this.v = ep.b();
                b(aMapLocation);
            }
        }
    }

    public static /* synthetic */ void a(g gVar, int i) {
        if (i == 0) {
            try {
                gVar.d = 0L;
                gVar.C = 0;
            } catch (Throwable unused) {
            }
        }
    }

    public static /* synthetic */ void a(g gVar, Location location) {
        String str;
        Handler handler = gVar.f3796a;
        if (handler != null) {
            handler.removeMessages(8);
        }
        if (location == null) {
            return;
        }
        try {
            AMapLocation aMapLocation = new AMapLocation(location);
            if (ep.a(aMapLocation)) {
                aMapLocation.setProvider("gps");
                aMapLocation.setLocationType(1);
                if (!gVar.f3797e && ep.a(aMapLocation)) {
                    en.a(gVar.z, ep.b() - gVar.A, ej.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    gVar.f3797e = true;
                }
                if (ep.a(location, gVar.C)) {
                    aMapLocation.setMock(true);
                    aMapLocation.setTrustedLevel(4);
                    if (!gVar.c.isMockEnable()) {
                        if (gVar.w <= 3) {
                            gVar.w++;
                            return;
                        }
                        en.a((String) null, 2152);
                        aMapLocation.setErrorCode(15);
                        aMapLocation.setLocationDetail("GpsLocation has been mocked!#1501");
                        aMapLocation.setLatitude(0.0d);
                        aMapLocation.setLongitude(0.0d);
                        aMapLocation.setAltitude(0.0d);
                        aMapLocation.setSpeed(0.0f);
                        aMapLocation.setAccuracy(0.0f);
                        aMapLocation.setBearing(0.0f);
                        aMapLocation.setExtras(null);
                        gVar.a(aMapLocation);
                        return;
                    }
                } else {
                    gVar.w = 0;
                }
                aMapLocation.setSatellites(gVar.C);
                try {
                    if (ej.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) && gVar.c.isOffset()) {
                        DPoint dPointA = el.a(gVar.z, new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                        aMapLocation.setLatitude(dPointA.getLatitude());
                        aMapLocation.setLongitude(dPointA.getLongitude());
                        aMapLocation.setOffset(gVar.c.isOffset());
                        str = AMapLocation.COORD_TYPE_GCJ02;
                    } else {
                        aMapLocation.setOffset(false);
                        str = AMapLocation.COORD_TYPE_WGS84;
                    }
                    aMapLocation.setCoordType(str);
                } catch (Throwable unused) {
                    aMapLocation.setOffset(false);
                    aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
                }
                try {
                    if (gVar.C >= 4) {
                        aMapLocation.setGpsAccuracyStatus(1);
                    } else if (gVar.C == 0) {
                        aMapLocation.setGpsAccuracyStatus(-1);
                    } else {
                        aMapLocation.setGpsAccuracyStatus(0);
                    }
                } catch (Throwable unused2) {
                }
                if (ep.a(aMapLocation) && ei.s()) {
                    long time = aMapLocation.getTime();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long jA = ek.a(time, jCurrentTimeMillis, ei.t());
                    if (jA != time) {
                        aMapLocation.setTime(jA);
                        en.a(time, jCurrentTimeMillis);
                    }
                }
                if (ep.a(aMapLocation) && gVar.B >= 3) {
                    if (aMapLocation.getAccuracy() < 0.0f || aMapLocation.getAccuracy() == Float.MAX_VALUE) {
                        aMapLocation.setAccuracy(0.0f);
                    }
                    if (aMapLocation.getSpeed() < 0.0f || aMapLocation.getSpeed() == Float.MAX_VALUE) {
                        aMapLocation.setSpeed(0.0f);
                    }
                    aMapLocation = gVar.f.a(aMapLocation);
                }
                if (ep.a(aMapLocation)) {
                    gVar.d = ep.b();
                    synchronized (l) {
                        k = ep.b();
                        j = aMapLocation.m1clone();
                    }
                    gVar.B++;
                }
                if (ep.a(aMapLocation) && gVar.f3796a != null && gVar.c.isNeedAddress()) {
                    long jB = ep.b();
                    if (gVar.c.getInterval() <= 8000 || jB - gVar.v > gVar.c.getInterval() - 8000) {
                        Bundle bundle = new Bundle();
                        bundle.putDouble(com.umeng.analytics.pro.f.C, aMapLocation.getLatitude());
                        bundle.putDouble("lon", aMapLocation.getLongitude());
                        Message messageObtain = Message.obtain();
                        messageObtain.setData(bundle);
                        messageObtain.what = 5;
                        synchronized (gVar.o) {
                            if (y == null || ep.a(aMapLocation, y) > gVar.h) {
                                Handler handler2 = gVar.f3796a;
                                handler2.sendMessage(messageObtain);
                            }
                        }
                    }
                }
                synchronized (gVar.o) {
                    AMapLocation aMapLocation2 = y;
                    if (aMapLocation2 != null && gVar.c.isNeedAddress() && ep.a(aMapLocation, aMapLocation2) < gVar.g) {
                        ej.a(aMapLocation, aMapLocation2);
                    }
                }
                try {
                    if (ep.a(aMapLocation)) {
                        if (gVar.i != null) {
                            gVar.m = location.getTime() - gVar.i.getTime();
                            gVar.n = ep.a(gVar.i, aMapLocation);
                        }
                        synchronized (gVar.p) {
                            gVar.i = aMapLocation.m1clone();
                        }
                        gVar.F = null;
                        gVar.G = false;
                        gVar.H = 0;
                    }
                } catch (Throwable th) {
                    ej.a(th, "GpsLocation", "onLocationChangedLast");
                }
                gVar.a(aMapLocation);
            }
        } catch (Throwable th2) {
            ej.a(th2, "GpsLocation", "onLocationChanged");
        }
    }

    public static /* synthetic */ void a(g gVar, String str) {
        try {
            if ("gps".equalsIgnoreCase(str)) {
                gVar.d = 0L;
                gVar.C = 0;
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean a(LocationManager locationManager) {
        try {
            if (t) {
                return u;
            }
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders == null || allProviders.size() <= 0) {
                u = false;
            } else {
                u = allProviders.contains("gps");
            }
            t = true;
            return u;
        } catch (Throwable unused) {
            return u;
        }
    }

    private boolean a(String str) {
        try {
            ArrayList<String> arrayListD = ep.d(str);
            ArrayList<String> arrayListD2 = ep.d(this.F);
            if (arrayListD.size() < 8 || arrayListD2.size() < 8) {
                return false;
            }
            return ep.a(this.F, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    private void b(AMapLocation aMapLocation) {
        if (this.f3796a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.obj = aMapLocation;
            messageObtain.what = 2;
            this.f3796a.sendMessage(messageObtain);
        }
    }

    public static boolean e() {
        try {
            return ((Boolean) em.a(u.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), u.c("UaXNOYXZpU3RhcnRlZA=="), (Object[]) null, (Class<?>[]) null)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    private AMapLocation f() {
        float f;
        float f2;
        try {
            if (ep.a(this.i) && ei.j() && e()) {
                JSONObject jSONObject = new JSONObject((String) em.a(u.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), u.c("UZ2V0TmF2aUxvY2F0aW9u"), (Object[]) null, (Class<?>[]) null));
                long jOptLong = jSONObject.optLong("time");
                if (!this.I) {
                    this.I = true;
                    en.a("useNaviLoc", "use NaviLoc");
                }
                if (ep.a() - jOptLong <= 5500) {
                    double dOptDouble = jSONObject.optDouble(com.umeng.analytics.pro.f.C, 0.0d);
                    double dOptDouble2 = jSONObject.optDouble(com.umeng.analytics.pro.f.D, 0.0d);
                    float f3 = 0.0f;
                    try {
                        f = Float.parseFloat(jSONObject.optString("accuracy", "0"));
                    } catch (NumberFormatException unused) {
                        f = 0.0f;
                    }
                    double dOptDouble3 = jSONObject.optDouble("altitude", 0.0d);
                    try {
                        f2 = Float.parseFloat(jSONObject.optString("bearing", "0"));
                    } catch (NumberFormatException unused2) {
                        f2 = 0.0f;
                    }
                    try {
                        f3 = (Float.parseFloat(jSONObject.optString("speed", "0")) * 10.0f) / 36.0f;
                    } catch (NumberFormatException unused3) {
                    }
                    AMapLocation aMapLocation = new AMapLocation("lbs");
                    aMapLocation.setLocationType(9);
                    aMapLocation.setLatitude(dOptDouble);
                    aMapLocation.setLongitude(dOptDouble2);
                    aMapLocation.setAccuracy(f);
                    aMapLocation.setAltitude(dOptDouble3);
                    aMapLocation.setBearing(f2);
                    aMapLocation.setSpeed(f3);
                    aMapLocation.setTime(jOptLong);
                    aMapLocation.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
                    if (ep.a(aMapLocation, this.i) <= 300.0f) {
                        synchronized (this.p) {
                            this.i.setLongitude(dOptDouble2);
                            this.i.setLatitude(dOptDouble);
                            this.i.setAccuracy(f);
                            this.i.setBearing(f2);
                            this.i.setSpeed(f3);
                            this.i.setTime(jOptLong);
                            this.i.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
                        }
                        return aMapLocation;
                    }
                }
            }
        } catch (Throwable unused4) {
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amap.api.location.AMapLocation a(com.amap.api.location.AMapLocation r17, java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.g.a(com.amap.api.location.AMapLocation, java.lang.String):com.amap.api.location.AMapLocation");
    }

    public final void a() {
        LocationManager locationManager = this.b;
        if (locationManager == null) {
            return;
        }
        try {
            if (this.x != null) {
                locationManager.removeUpdates(this.x);
                ((a) this.x).a();
                this.x = null;
            }
        } catch (Throwable unused) {
        }
        try {
            if (this.E != null) {
                this.b.removeGpsStatusListener(this.E);
            }
        } catch (Throwable unused2) {
        }
        try {
            if (this.f3796a != null) {
                this.f3796a.removeMessages(8);
            }
        } catch (Throwable unused3) {
        }
        this.C = 0;
        this.A = 0L;
        this.v = 0L;
        this.d = 0L;
        this.B = 0;
        this.w = 0;
        this.f.a();
        this.i = null;
        this.m = 0L;
        this.n = 0.0f;
        this.F = null;
        this.I = false;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        LocationManager locationManager;
        String str;
        long interval;
        float deviceModeDistanceFilter;
        LocationListener locationListener;
        this.c = aMapLocationClientOption;
        if (aMapLocationClientOption == null) {
            this.c = new AMapLocationClientOption();
        }
        try {
            q = eo.a(this.z, "pref", "lagt", q);
        } catch (Throwable unused) {
        }
        if (this.b == null) {
            return;
        }
        try {
            if (ep.b() - k <= 5000 && ep.a(j) && (this.c.isMockEnable() || !j.isMock())) {
                this.d = ep.b();
                a(j);
            }
            this.s = true;
            Looper looperMyLooper = Looper.myLooper();
            if (looperMyLooper == null) {
                looperMyLooper = this.z.getMainLooper();
            }
            Looper looper = looperMyLooper;
            this.A = ep.b();
            if (!a(this.b)) {
                a(8, 14, "no gps provider#1402", 0L);
                return;
            }
            try {
                if (ep.a() - q >= 259200000) {
                    this.b.sendExtraCommand("gps", "force_xtra_injection", null);
                    q = ep.a();
                    SharedPreferences.Editor editorA = eo.a(this.z, "pref");
                    eo.a(editorA, "lagt", q);
                    eo.a(editorA);
                }
            } catch (Throwable unused2) {
            }
            if (this.x == null) {
                this.x = new a(this);
            }
            if (!this.c.getLocationMode().equals(AMapLocationClientOption.AMapLocationMode.Device_Sensors) || this.c.getDeviceModeDistanceFilter() <= 0.0f) {
                locationManager = this.b;
                str = "gps";
                interval = 900;
                deviceModeDistanceFilter = 0.0f;
                locationListener = this.x;
            } else {
                locationManager = this.b;
                str = "gps";
                interval = this.c.getInterval();
                deviceModeDistanceFilter = this.c.getDeviceModeDistanceFilter();
                locationListener = this.x;
            }
            locationManager.requestLocationUpdates(str, interval, deviceModeDistanceFilter, locationListener, looper);
            this.b.addGpsStatusListener(this.E);
            a(8, 14, "no enough satellites#1401", this.c.getHttpTimeOut());
        } catch (SecurityException e2) {
            this.s = false;
            en.a((String) null, 2121);
            a(2, 12, e2.getMessage() + "#1201", 0L);
        } catch (Throwable th) {
            ej.a(th, "GpsLocation", "requestLocationUpdates part2");
        }
    }

    public final boolean b() {
        return ep.b() - this.d <= 2800;
    }

    @SuppressLint({"NewApi"})
    public final int c() {
        LocationManager locationManager = this.b;
        if (locationManager == null || !a(locationManager)) {
            return 1;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            int i = Settings.Secure.getInt(this.z.getContentResolver(), "location_mode", 0);
            if (i == 0) {
                return 2;
            }
            if (i == 2) {
                return 3;
            }
        } else if (!this.b.isProviderEnabled("gps")) {
            return 2;
        }
        return !this.s ? 4 : 0;
    }

    public final int d() {
        return this.C;
    }
}
