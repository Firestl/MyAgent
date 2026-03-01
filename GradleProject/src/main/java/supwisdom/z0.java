package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: classes.dex */
public class z0 {
    public static z0 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9944a;
    public final LocationManager b;
    public final a c = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f9945a;
        public long b;
        public long c;
        public long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f9946e;
        public long f;
    }

    public z0(Context context, LocationManager locationManager) {
        this.f9944a = context;
        this.b = locationManager;
    }

    public static z0 a(Context context) {
        if (d == null) {
            Context applicationContext = context.getApplicationContext();
            d = new z0(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return d;
    }

    public boolean b() {
        a aVar = this.c;
        if (c()) {
            return aVar.f9945a;
        }
        Location locationA = a();
        if (locationA != null) {
            a(locationA);
            return aVar.f9945a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    public final boolean c() {
        return this.c.f > System.currentTimeMillis();
    }

    @SuppressLint({"MissingPermission"})
    public final Location a() {
        Location locationA = a8.a(this.f9944a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        Location locationA2 = a8.a(this.f9944a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? a("gps") : null;
        return (locationA2 == null || locationA == null) ? locationA2 != null ? locationA2 : locationA : locationA2.getTime() > locationA.getTime() ? locationA2 : locationA;
    }

    public final Location a(String str) {
        try {
            if (this.b.isProviderEnabled(str)) {
                return this.b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    public final void a(Location location) {
        long j;
        a aVar = this.c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        y0 y0VarA = y0.a();
        y0VarA.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = y0VarA.f9831a;
        y0VarA.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = y0VarA.c == 1;
        long j3 = y0VarA.b;
        long j4 = y0VarA.f9831a;
        boolean z2 = z;
        y0VarA.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = y0VarA.b;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f9945a = z2;
        aVar.b = j2;
        aVar.c = j3;
        aVar.d = j4;
        aVar.f9946e = j5;
        aVar.f = j;
    }
}
