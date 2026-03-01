package io.dcloud.js.geolocation.system;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes3.dex */
public class b implements LocationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6763a;
    public LocationManager b;
    public a c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6764e;
    public boolean d = false;
    public long f = System.currentTimeMillis();

    public b(Context context, a aVar) {
        this.f6764e = false;
        this.c = aVar;
        this.f6763a = context;
        this.b = (LocationManager) context.getSystemService("location");
        this.f6764e = false;
    }

    private void a(boolean z) {
        this.d = z;
        if (z) {
            this.f = System.currentTimeMillis();
        }
    }

    public void b() {
        if (this.f6764e) {
            this.b.removeUpdates(this);
        }
        this.f6764e = false;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        Logger.d("GpsListener: The location has been updated!");
        a(true);
        this.c.a(location, a.o);
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        this.f6764e = false;
        if (this.d) {
            return;
        }
        this.c.a(a.l, "GPS provider disabled.", a.o);
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        Logger.d("GpsListener: The provider " + str + " is enabled");
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        Logger.d("GpsListener: The status of the provider " + str + " has changed");
        if (i == 0) {
            Logger.d("GpsListener: " + str + " is OUT OF SERVICE");
            this.c.a(a.l, "GPS out of service.", a.o);
            return;
        }
        if (i == 1) {
            Logger.d("GpsListener: " + str + " is TEMPORARILY_UNAVAILABLE");
            return;
        }
        Logger.d("GpsListener: " + str + " is Available");
    }

    public boolean a() {
        if (!(System.currentTimeMillis() - this.f < 10000)) {
            this.d = false;
        }
        return this.d;
    }

    public void a(int i) {
        if (this.f6764e) {
            return;
        }
        this.f6764e = true;
        this.b.requestLocationUpdates("gps", i, 0.0f, this);
    }
}
