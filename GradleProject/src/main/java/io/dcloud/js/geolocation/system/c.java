package io.dcloud.js.geolocation.system;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes3.dex */
public class c implements LocationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6765a;
    public LocationManager b;
    public a c;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6766e;

    public c(Context context, a aVar) {
        this.f6766e = false;
        this.c = aVar;
        this.f6765a = context;
        this.b = (LocationManager) context.getSystemService("location");
        this.f6766e = false;
    }

    public void a(int i) {
        if (this.f6766e) {
            return;
        }
        this.f6766e = true;
        this.b.requestLocationUpdates("network", i, 0.0f, this);
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        Logger.d("NetworkListener: The location has been updated!");
        this.d = true;
        this.c.a(location, a.p);
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        b bVar;
        this.f6766e = false;
        if (!this.d && ((bVar = this.c.c) == null || !bVar.a())) {
            this.c.a(a.l, "The provider " + str + " is disabled", a.p);
        }
        Logger.d("NetworkListener: The provider " + str + " is disabled");
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        Logger.d("NetworkListener: The provider " + str + " is enabled");
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        Logger.d("NetworkListener: The status of the provider " + str + " has changed");
        if (i == 0) {
            Logger.d("NetworkListener: " + str + " is OUT OF SERVICE");
            return;
        }
        if (i == 1) {
            Logger.d("NetworkListener: " + str + " is TEMPORARILY_UNAVAILABLE");
            return;
        }
        Logger.d("NetworkListener: " + str + " is Available");
    }

    public void a() {
        if (this.f6766e) {
            this.b.removeUpdates(this);
        }
        this.f6766e = false;
    }
}
