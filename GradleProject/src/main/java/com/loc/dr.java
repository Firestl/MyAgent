package com.loc;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;

/* JADX INFO: compiled from: LocFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ds f3746a = null;
    public long b = 0;
    public long c = 0;
    public boolean h = true;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3747e = 0;
    public AMapLocation f = null;
    public long g = 0;

    private ds b(ds dsVar) {
        int i;
        if (ep.a(dsVar)) {
            if (!this.h || !ei.a(dsVar.getTime())) {
                i = this.d;
            } else if (dsVar.getLocationType() == 5 || dsVar.getLocationType() == 6) {
                i = 4;
            }
            dsVar.setLocationType(i);
        }
        return dsVar;
    }

    public final AMapLocation a(AMapLocation aMapLocation) {
        if (!ep.a(aMapLocation)) {
            return aMapLocation;
        }
        long jB = ep.b() - this.g;
        this.g = ep.b();
        if (jB > 5000) {
            return aMapLocation;
        }
        AMapLocation aMapLocation2 = this.f;
        if (aMapLocation2 == null) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        if (1 != aMapLocation2.getLocationType() && !"gps".equalsIgnoreCase(this.f.getProvider())) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        if (this.f.getAltitude() == aMapLocation.getAltitude() && this.f.getLongitude() == aMapLocation.getLongitude()) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        long jAbs = Math.abs(aMapLocation.getTime() - this.f.getTime());
        if (com.igexin.push.config.c.k < jAbs) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        if (ep.a(aMapLocation, this.f) > (((this.f.getSpeed() + aMapLocation.getSpeed()) * jAbs) / 2000.0f) + ((this.f.getAccuracy() + aMapLocation.getAccuracy()) * 2.0f) + 3000.0f) {
            return this.f;
        }
        this.f = aMapLocation;
        return aMapLocation;
    }

    public final ds a(ds dsVar) {
        if (ep.b() - this.f3747e > com.igexin.push.config.c.k) {
            this.f3746a = dsVar;
            this.f3747e = ep.b();
            return this.f3746a;
        }
        this.f3747e = ep.b();
        if (!ep.a(this.f3746a) || !ep.a(dsVar)) {
            this.b = ep.b();
            this.f3746a = dsVar;
            return dsVar;
        }
        if (dsVar.getTime() == this.f3746a.getTime() && dsVar.getAccuracy() < 300.0f) {
            return dsVar;
        }
        if (dsVar.getProvider().equals("gps")) {
            this.b = ep.b();
            this.f3746a = dsVar;
            return dsVar;
        }
        if (dsVar.c() != this.f3746a.c()) {
            this.b = ep.b();
            this.f3746a = dsVar;
            return dsVar;
        }
        if (!dsVar.getBuildingId().equals(this.f3746a.getBuildingId()) && !TextUtils.isEmpty(dsVar.getBuildingId())) {
            this.b = ep.b();
            this.f3746a = dsVar;
            return dsVar;
        }
        this.d = dsVar.getLocationType();
        float fA = ep.a(dsVar, this.f3746a);
        float accuracy = this.f3746a.getAccuracy();
        float accuracy2 = dsVar.getAccuracy();
        float f = accuracy2 - accuracy;
        long jB = ep.b();
        long j = jB - this.b;
        boolean z = accuracy <= 100.0f && accuracy2 > 299.0f;
        boolean z2 = accuracy > 299.0f && accuracy2 > 299.0f;
        if (z || z2) {
            long j2 = this.c;
            if (j2 == 0) {
                this.c = jB;
            } else if (jB - j2 > com.igexin.push.config.c.k) {
                this.b = jB;
                this.f3746a = dsVar;
                this.c = 0L;
                return dsVar;
            }
            ds dsVarB = b(this.f3746a);
            this.f3746a = dsVarB;
            return dsVarB;
        }
        if (accuracy2 < 100.0f && accuracy > 299.0f) {
            this.b = jB;
            this.f3746a = dsVar;
            this.c = 0L;
            return dsVar;
        }
        if (accuracy2 <= 299.0f) {
            this.c = 0L;
        }
        if (fA >= 10.0f || fA <= 0.1d || accuracy2 <= 5.0f) {
            if (f < 300.0f) {
                this.b = ep.b();
                this.f3746a = dsVar;
                return dsVar;
            }
            if (j >= com.igexin.push.config.c.k) {
                this.b = ep.b();
                this.f3746a = dsVar;
                return dsVar;
            }
            ds dsVarB2 = b(this.f3746a);
            this.f3746a = dsVarB2;
            return dsVarB2;
        }
        if (f >= -300.0f) {
            ds dsVarB3 = b(this.f3746a);
            this.f3746a = dsVarB3;
            return dsVarB3;
        }
        if (accuracy / accuracy2 >= 2.0f) {
            this.b = jB;
            this.f3746a = dsVar;
            return dsVar;
        }
        ds dsVarB4 = b(this.f3746a);
        this.f3746a = dsVarB4;
        return dsVarB4;
    }

    public final void a() {
        this.f3746a = null;
        this.b = 0L;
        this.c = 0L;
        this.f = null;
        this.g = 0L;
    }

    public final void a(boolean z) {
        this.h = z;
    }
}
