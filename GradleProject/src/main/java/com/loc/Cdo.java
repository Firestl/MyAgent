package com.loc;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.loc.by;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.crypto.KeyGenerator;

/* JADX INFO: renamed from: com.loc.do, reason: invalid class name */
/* JADX INFO: compiled from: CollectionManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class Cdo implements dk {
    public static long k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3739a;
    public cu d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ax f3740e;
    public Handler g;
    public LocationManager h;
    public a i;
    public ArrayList<ca> f = new ArrayList<>();
    public dx b = null;
    public dw c = null;
    public volatile boolean j = false;

    /* JADX INFO: renamed from: com.loc.do$a */
    /* JADX INFO: compiled from: CollectionManager.java */
    public static class a implements LocationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Cdo f3742a;

        public a(Cdo cdo) {
            this.f3742a = cdo;
        }

        public final void a() {
            this.f3742a = null;
        }

        public final void a(Cdo cdo) {
            this.f3742a = cdo;
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            try {
                if (this.f3742a != null) {
                    this.f3742a.a(location);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* JADX INFO: renamed from: com.loc.do$b */
    /* JADX INFO: compiled from: CollectionManager.java */
    public class b implements Runnable {
        public int b;
        public Location c;

        public b(int i) {
            this.b = 0;
            this.b = i;
        }

        public b(Cdo cdo, Location location) {
            this(1);
            this.c = location;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.b;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        Cdo.this.f();
                        return;
                    }
                    return;
                }
                ao aoVarA = null;
                try {
                    long unused = Cdo.k = System.currentTimeMillis();
                    if (Cdo.this.f3740e.f.c()) {
                        aoVarA = ao.a(new File(Cdo.this.f3740e.f3664a), Cdo.this.f3740e.b);
                        ArrayList arrayList = new ArrayList();
                        byte[] bArrE = Cdo.e();
                        if (bArrE == null) {
                            try {
                                aoVarA.close();
                                return;
                            } catch (Throwable unused2) {
                                return;
                            }
                        }
                        List listB = Cdo.b(aoVarA, Cdo.this.f3740e, arrayList, bArrE);
                        if (listB != null && listB.size() != 0) {
                            Cdo.this.f3740e.f.a(true);
                            if (cu.a(u.b(cu.a(dy.a(bArrE), o.b(bArrE, cu.a(), u.c()), listB)))) {
                                Cdo.a(aoVarA, arrayList);
                            }
                        }
                        try {
                            aoVarA.close();
                            return;
                        } catch (Throwable unused3) {
                            return;
                        }
                    }
                    if (aoVarA != null) {
                        try {
                            aoVarA.close();
                            return;
                        } catch (Throwable unused4) {
                            return;
                        }
                    }
                    return;
                } catch (Throwable th) {
                    try {
                        ab.b(th, "leg", "uts");
                        if (aoVarA != null) {
                            try {
                                aoVarA.close();
                                return;
                            } catch (Throwable unused5) {
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th2) {
                        if (aoVarA != null) {
                            try {
                                aoVarA.close();
                            } catch (Throwable unused6) {
                            }
                        }
                        throw th2;
                    }
                }
            }
            try {
                if (this.c != null && Cdo.this.j) {
                    Bundle extras = this.c.getExtras();
                    int i2 = extras != null ? extras.getInt("satellites") : 0;
                    if (ep.a(this.c, i2)) {
                        return;
                    }
                    if (Cdo.this.b != null && !Cdo.this.b.r) {
                        Cdo.this.b.f();
                    }
                    ArrayList<dd> arrayListA = Cdo.this.b.a();
                    List<cw> listA = Cdo.this.c.a();
                    by.a aVar = new by.a();
                    dc dcVar = new dc();
                    dcVar.i = this.c.getAccuracy();
                    dcVar.f = this.c.getAltitude();
                    dcVar.d = this.c.getLatitude();
                    dcVar.h = this.c.getBearing();
                    dcVar.f3721e = this.c.getLongitude();
                    dcVar.j = this.c.isFromMockProvider();
                    dcVar.f3720a = this.c.getProvider();
                    dcVar.g = this.c.getSpeed();
                    dcVar.l = (byte) i2;
                    dcVar.b = System.currentTimeMillis();
                    dcVar.c = this.c.getTime();
                    dcVar.k = this.c.getTime();
                    aVar.f3699a = dcVar;
                    aVar.b = arrayListA;
                    WifiInfo wifiInfoC = Cdo.this.b.c();
                    if (wifiInfoC != null) {
                        aVar.c = dd.a(wifiInfoC.getBSSID());
                    }
                    aVar.d = dx.w;
                    aVar.f = this.c.getTime();
                    aVar.g = (byte) n.p(Cdo.this.f3739a);
                    aVar.h = n.u(Cdo.this.f3739a);
                    aVar.f3700e = Cdo.this.b.q;
                    aVar.j = ep.a(Cdo.this.f3739a);
                    aVar.i = listA;
                    ca caVarA = cu.a(aVar);
                    if (caVarA == null) {
                        return;
                    }
                    synchronized (Cdo.this.f) {
                        Cdo.this.f.add(caVarA);
                        if (Cdo.this.f.size() >= 5) {
                            try {
                                ab.d().submit(Cdo.this.new b(3));
                            } catch (Throwable unused7) {
                            }
                        }
                    }
                    Cdo.this.d();
                }
            } catch (Throwable th3) {
                ej.a(th3, "cl", "coll");
            }
        }
    }

    public Cdo(Context context) {
        this.f3739a = null;
        this.f3739a = context;
        ax axVar = new ax();
        this.f3740e = axVar;
        bd.a(this.f3739a, axVar, z.k, 100, 1024000, "0");
        ax axVar2 = this.f3740e;
        int i = ei.g;
        boolean z = ei.f3781e;
        int i2 = ei.f;
        axVar2.f = new bp(context, i, "kKey", new bn(context, z, i2, i2 * 10, "carrierLocKey"));
        this.f3740e.f3665e = new am();
    }

    public static /* synthetic */ void a(ao aoVar, List list) {
        if (aoVar != null) {
            try {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    aoVar.c((String) it.next());
                }
                aoVar.close();
            } catch (Throwable th) {
                ab.b(th, "aps", "dlo");
            }
        }
    }

    public static byte[] a(int i) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            if (keyGenerator == null) {
                return null;
            }
            keyGenerator.init(i);
            return keyGenerator.generateKey().getEncoded();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00ff, code lost:
    
        if (r7 == null) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0101, code lost:
    
        r7.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x00f8 A[EXC_TOP_SPLITTER, PHI: r5 r9 r16
  0x00f8: PHI (r5v3 int) = (r5v4 int), (r5v5 int) binds: [B:82:0x0124, B:64:0x00f6] A[DONT_GENERATE, DONT_INLINE]
  0x00f8: PHI (r9v1 com.loc.ao$b) = (r9v2 com.loc.ao$b), (r9v3 com.loc.ao$b) binds: [B:82:0x0124, B:64:0x00f6] A[DONT_GENERATE, DONT_INLINE]
  0x00f8: PHI (r16v3 java.lang.String[]) = (r16v4 java.lang.String[]), (r16v7 java.lang.String[]) binds: [B:82:0x0124, B:64:0x00f6] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0131 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.loc.ca> b(com.loc.ao r17, com.loc.ax r18, java.util.List<java.lang.String> r19, byte[] r20) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.Cdo.b(com.loc.ao, com.loc.ax, java.util.List, byte[]):java.util.List");
    }

    public static byte[] b(int i) {
        return new byte[]{(byte) ((i & 65280) >> 8), (byte) (i & 255)};
    }

    public static /* synthetic */ byte[] e() {
        return a(128);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.f != null && this.f.size() != 0) {
                ArrayList<ca> arrayList = new ArrayList();
                synchronized (this.f) {
                    arrayList.addAll(this.f);
                    this.f.clear();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArrA = a(256);
                if (bArrA == null) {
                    return;
                }
                byteArrayOutputStream.write(b(bArrA.length));
                byteArrayOutputStream.write(bArrA);
                for (ca caVar : arrayList) {
                    byte[] bArrB = caVar.b();
                    if (bArrB.length >= 10 && bArrB.length <= 65535) {
                        byte[] bArrB2 = o.b(bArrA, bArrB, u.c());
                        byteArrayOutputStream.write(b(bArrB2.length));
                        byteArrayOutputStream.write(bArrB2);
                        int iA = caVar.a();
                        byteArrayOutputStream.write(new byte[]{(byte) ((iA >> 24) & 255), (byte) ((iA >> 16) & 255), (byte) ((iA >> 8) & 255), (byte) (iA & 255)});
                    }
                }
                ay.a(Long.toString(System.currentTimeMillis()), byteArrayOutputStream.toByteArray(), this.f3740e);
            }
        } catch (Throwable th) {
            ej.a(th, "clm", "wtD");
        }
    }

    @Override // com.loc.dk
    public final dj a(di diVar) {
        try {
            ed edVar = new ed();
            edVar.a(diVar.b);
            edVar.a(diVar.f3727a);
            edVar.a(diVar.d);
            aq.a();
            aw awVarC = aq.c(edVar);
            dj djVar = new dj();
            djVar.c = awVarC.f3662a;
            djVar.b = awVarC.b;
            djVar.f3729a = 200;
            return djVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final void a() {
        try {
            if (this.i != null && this.h != null) {
                this.h.removeUpdates(this.i);
            }
            if (this.i != null) {
                this.i.a();
            }
            if (this.j) {
                f();
                this.b.a((Cdo) null);
                this.c.a((Cdo) null);
                this.c = null;
                this.b = null;
                this.g = null;
                this.j = false;
            }
        } catch (Throwable th) {
            ej.a(th, "clm", "stc");
        }
    }

    public final void a(Location location) {
        try {
            if (this.g != null) {
                this.g.post(new b(this, location));
            }
        } catch (Throwable th) {
            ab.b(th, "cl", "olcc");
        }
    }

    public final void a(dw dwVar, dx dxVar, Handler handler) {
        if (this.j || dwVar == null || dxVar == null || handler == null) {
            return;
        }
        this.j = true;
        this.c = dwVar;
        this.b = dxVar;
        dxVar.a(this);
        this.c.a(this);
        this.g = handler;
        try {
            if (this.h == null && handler != null) {
                this.h = (LocationManager) this.f3739a.getSystemService("location");
            }
            if (this.i == null) {
                this.i = new a(this);
            }
            this.i.a(this);
            if (this.i != null && this.h != null) {
                this.h.requestLocationUpdates("passive", 1000L, -1.0f, this.i);
            }
            if (this.d == null) {
                cu cuVar = new cu("5.2.0", k.f(this.f3739a), "S128DF1572465B890OE3F7A13167KLEI", k.c(this.f3739a), this);
                this.d = cuVar;
                cuVar.a(n.x(this.f3739a)).b(n.h(this.f3739a)).c(n.a(this.f3739a)).d(n.g(this.f3739a)).e(n.A(this.f3739a)).f(n.i(this.f3739a)).g(Build.MODEL).h(Build.MANUFACTURER).i(Build.BRAND).a(Build.VERSION.SDK_INT).j(Build.VERSION.RELEASE).a(dd.a(n.m(this.f3739a))).k(n.m(this.f3739a));
            }
        } catch (Throwable th) {
            ej.a(th, "col", "init");
        }
    }

    public final void b() {
        try {
            if (this.g != null) {
                this.g.post(new Runnable() { // from class: com.loc.do.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (Cdo.this.d == null || Cdo.this.b == null) {
                                return;
                            }
                            cu.b(Cdo.this.b.a());
                        } catch (Throwable th) {
                            ej.a(th, "cl", "upwr");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            ej.a(th, "cl", "upw");
        }
    }

    public final void c() {
        try {
            if (this.d == null || this.c == null) {
                return;
            }
            cu.a(this.c.a());
        } catch (Throwable th) {
            ej.a(th, "cl", "upc");
        }
    }

    public final void d() {
        try {
            if (System.currentTimeMillis() - k < 60000) {
                return;
            }
            ab.d().submit(new b(2));
        } catch (Throwable unused) {
        }
    }
}
