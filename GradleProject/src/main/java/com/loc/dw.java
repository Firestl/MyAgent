package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CgiManager.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public final class dw {
    public static int r = 0;
    public static boolean v = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3754a;
    public TelephonyManager d;
    public CellLocation f;
    public String i;
    public Context l;
    public du p;
    public Object q;

    @SuppressLint({"NewApi"})
    public TelephonyManager.CellInfoCallback t;
    public Cdo w;
    public ArrayList<dv> b = new ArrayList<>();
    public ArrayList<cw> c = new ArrayList<>();
    public String m = null;
    public ArrayList<dv> n = new ArrayList<>();
    public int o = -113;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3755e = 0;
    public long s = 0;
    public boolean g = false;
    public PhoneStateListener h = null;
    public boolean u = false;
    public boolean j = false;
    public StringBuilder k = null;
    public boolean x = false;
    public Object y = new Object();

    /* JADX INFO: compiled from: CgiManager.java */
    @SuppressLint({"NewApi"})
    public class a extends TelephonyManager.CellInfoCallback {
        public a() {
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public final void onCellInfo(List<CellInfo> list) {
            dw.d(dw.this);
            CellLocation cellLocationA = dw.this.a(list);
            if (cellLocationA != null) {
                dw dwVar = dw.this;
                dwVar.f = cellLocationA;
                dwVar.g = true;
                dwVar.a(false);
                dw.this.s = ep.b();
            }
        }
    }

    /* JADX INFO: compiled from: CgiManager.java */
    public class b extends PhoneStateListener {
        public b() {
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellInfoChanged(List<CellInfo> list) {
            try {
                if (dw.this.w != null) {
                    dw.this.w.c();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellLocationChanged(CellLocation cellLocation) {
            try {
                if (dw.this.a(cellLocation)) {
                    dw.this.f = cellLocation;
                    dw.this.g = true;
                    dw.this.a(false);
                    dw.this.s = ep.b();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onServiceStateChanged(ServiceState serviceState) {
            try {
                int state = serviceState.getState();
                if (state == 0) {
                    dw.this.a(false, false);
                } else {
                    if (state != 1) {
                        return;
                    }
                    dw.this.i();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthChanged(int i) {
            int iA = -113;
            try {
                int i2 = dw.this.f3754a;
                if (i2 == 1 || i2 == 2) {
                    iA = ep.a(i);
                }
                dw.this.b(iA);
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (signalStrength == null) {
                return;
            }
            int iA = -113;
            try {
                int i = dw.this.f3754a;
                if (i == 1) {
                    iA = ep.a(signalStrength.getGsmSignalStrength());
                } else if (i == 2) {
                    iA = signalStrength.getCdmaDbm();
                }
                dw.this.b(iA);
                if (dw.this.w != null) {
                    dw.this.w.c();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public dw(Context context) {
        Object objA;
        this.f3754a = 0;
        this.d = null;
        this.p = null;
        this.i = null;
        this.l = context;
        if (this.d == null) {
            this.d = (TelephonyManager) ep.a(context, "phone");
        }
        TelephonyManager telephonyManager = this.d;
        if (telephonyManager != null) {
            try {
                this.f3754a = c(telephonyManager.getCellLocation());
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            } catch (Throwable th) {
                this.i = null;
                ej.a(th, "CgiManager", "CgiManager");
                this.f3754a = 0;
            }
            try {
                int i = r;
                if (i != 1) {
                    objA = ep.a(i != 2 ? this.l : this.l, "phone2");
                } else {
                    objA = ep.a(this.l, "phone_msim");
                }
                this.q = objA;
            } catch (Throwable unused) {
            }
            ab.d().submit(new Runnable() { // from class: com.loc.dw.1
                @Override // java.lang.Runnable
                public final void run() {
                    synchronized (dw.this.y) {
                        if (!dw.this.x) {
                            dw.c(dw.this);
                        }
                    }
                }
            });
        }
        this.p = new du();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        CellLocation cellLocation;
        if (obj == null) {
            return null;
        }
        try {
            Object objA = em.a(obj, str, objArr);
            cellLocation = objA != null ? (CellLocation) objA : null;
        } catch (Throwable unused) {
        }
        if (b(cellLocation)) {
            return cellLocation;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.telephony.CellLocation] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9, types: [android.telephony.cdma.CdmaCellLocation] */
    @SuppressLint({"NewApi"})
    public synchronized CellLocation a(List<CellInfo> list) {
        ?? cdmaCellLocation;
        GsmCellLocation gsmCellLocation;
        GsmCellLocation gsmCellLocation2 = null;
         = 0;
        gsmCellLocation2 = null;
        gsmCellLocation2 = null;
        ?? r0 = 0;
        if (list != null) {
            if (!list.isEmpty()) {
                dv dvVarA = null;
                for (int i = 0; i < list.size(); i++) {
                    CellInfo cellInfo = list.get(i);
                    if (cellInfo != null) {
                        try {
                            dvVarA = a(cellInfo);
                            if (dvVarA != null) {
                                break;
                            }
                        } catch (Throwable unused) {
                            continue;
                        }
                    }
                }
                if (dvVarA != null) {
                    try {
                        if (dvVarA.k == 2) {
                            cdmaCellLocation = new CdmaCellLocation();
                            try {
                                cdmaCellLocation.setCellLocationData(dvVarA.i, dvVarA.f3753e, dvVarA.f, dvVarA.g, dvVarA.h);
                                cdmaCellLocation = cdmaCellLocation;
                            } catch (Throwable unused2) {
                            }
                        } else {
                            gsmCellLocation = new GsmCellLocation();
                            try {
                                gsmCellLocation.setLacAndCid(dvVarA.c, dvVarA.d);
                            } catch (Throwable unused3) {
                                gsmCellLocation2 = gsmCellLocation;
                                cdmaCellLocation = 0;
                                GsmCellLocation gsmCellLocation3 = gsmCellLocation2;
                                r0 = cdmaCellLocation;
                                gsmCellLocation = gsmCellLocation3;
                            }
                        }
                    } catch (Throwable unused4) {
                        cdmaCellLocation = gsmCellLocation2;
                    }
                    GsmCellLocation gsmCellLocation32 = gsmCellLocation2;
                    r0 = cdmaCellLocation;
                    gsmCellLocation = gsmCellLocation32;
                } else {
                    gsmCellLocation = null;
                }
                return r0 == 0 ? gsmCellLocation : r0;
            }
        }
        return null;
    }

    public static dv a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        dv dvVar = new dv(i, z);
        dvVar.f3752a = i2;
        dvVar.b = i3;
        dvVar.c = i4;
        dvVar.d = i5;
        dvVar.j = i6;
        return dvVar;
    }

    private dv a(CellInfo cellInfo) {
        CellInfoLte cellInfoLte;
        dv dvVarA;
        int pci;
        boolean zIsRegistered = cellInfo.isRegistered();
        if (cellInfo instanceof CellInfoCdma) {
            dvVarA = a((CellInfoCdma) cellInfo, zIsRegistered);
        } else if (cellInfo instanceof CellInfoGsm) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            if (cellInfoGsm == null || cellInfoGsm.getCellIdentity() == null) {
                return null;
            }
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            if (!c(cellIdentity.getLac()) || !d(cellIdentity.getCid())) {
                return null;
            }
            dvVarA = a(1, zIsRegistered, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
        } else {
            if (cellInfo instanceof CellInfoWcdma) {
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                if (cellInfoWcdma == null || cellInfoWcdma.getCellIdentity() == null) {
                    return null;
                }
                CellIdentityWcdma cellIdentity2 = cellInfoWcdma.getCellIdentity();
                if (!c(cellIdentity2.getLac()) || !d(cellIdentity2.getCid())) {
                    return null;
                }
                dvVarA = a(4, zIsRegistered, cellIdentity2.getMcc(), cellIdentity2.getMnc(), cellIdentity2.getLac(), cellIdentity2.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
                pci = cellIdentity2.getPsc();
            } else {
                if (!(cellInfo instanceof CellInfoLte) || (cellInfoLte = (CellInfoLte) cellInfo) == null || cellInfoLte.getCellIdentity() == null) {
                    return null;
                }
                CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                if (!c(cellIdentity3.getTac()) || !d(cellIdentity3.getCi())) {
                    return null;
                }
                dvVarA = a(3, zIsRegistered, cellIdentity3.getMcc(), cellIdentity3.getMnc(), cellIdentity3.getTac(), cellIdentity3.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
                pci = cellIdentity3.getPci();
            }
            dvVarA.o = pci;
        }
        return dvVarA;
    }

    private dv a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        int i2;
        int i3;
        if (cellInfoCdma != null && cellInfoCdma.getCellIdentity() != null) {
            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
            if (cellIdentity.getSystemId() > 0 && cellIdentity.getNetworkId() >= 0 && cellIdentity.getBasestationId() >= 0) {
                CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                String[] strArrA = ep.a(this.d);
                try {
                    i = Integer.parseInt(strArrA[0]);
                    try {
                        i3 = Integer.parseInt(strArrA[1]);
                        i2 = i;
                    } catch (Throwable unused) {
                        i2 = i;
                        i3 = 0;
                    }
                } catch (Throwable unused2) {
                    i = 0;
                }
                dv dvVarA = a(2, z, i2, i3, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                dvVarA.g = cellIdentity2.getSystemId();
                dvVarA.h = cellIdentity2.getNetworkId();
                dvVarA.i = cellIdentity2.getBasestationId();
                dvVarA.f3753e = cellIdentity2.getLatitude();
                dvVarA.f = cellIdentity2.getLongitude();
                return dvVarA;
            }
        }
        return null;
    }

    public static dv a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            dv dvVar = new dv(1, false);
            dvVar.f3752a = Integer.parseInt(strArr[0]);
            dvVar.b = Integer.parseInt(strArr[1]);
            dvVar.c = em.b(neighboringCellInfo, "getLac", new Object[0]);
            dvVar.d = neighboringCellInfo.getCid();
            dvVar.j = ep.a(neighboringCellInfo.getRssi());
            return dvVar;
        } catch (Throwable th) {
            ej.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private synchronized void a(CellLocation cellLocation, String[] strArr, boolean z) {
        dv dvVarA;
        if (cellLocation != null) {
            if (this.d != null) {
                this.b.clear();
                if (b(cellLocation)) {
                    this.f3754a = 1;
                    ArrayList<dv> arrayList = this.b;
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    dv dvVar = new dv(1, true);
                    dvVar.f3752a = ep.g(strArr[0]);
                    dvVar.b = ep.g(strArr[1]);
                    dvVar.c = gsmCellLocation.getLac();
                    dvVar.d = gsmCellLocation.getCid();
                    dvVar.j = this.o;
                    arrayList.add(dvVar);
                    if (z) {
                        return;
                    }
                    if (Build.VERSION.SDK_INT <= 28) {
                        List<NeighboringCellInfo> list = (List) em.a(this.d, "getNeighboringCellInfo", new Object[0]);
                        if (list != null && !list.isEmpty()) {
                            for (NeighboringCellInfo neighboringCellInfo : list) {
                                if (neighboringCellInfo != null && a(neighboringCellInfo.getLac(), neighboringCellInfo.getCid()) && (dvVarA = a(neighboringCellInfo, strArr)) != null && !this.b.contains(dvVarA)) {
                                    this.b.add(dvVarA);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z) {
        CellLocation cellLocation;
        boolean z2;
        GsmCellLocation gsmCellLocation;
        String[] strArrA = ep.a(this.d);
        int iC = c(this.f);
        boolean z3 = true;
        if (iC == 1) {
            a(this.f, strArrA, z);
            return;
        }
        if (iC == 2 && (cellLocation = this.f) != null) {
            this.b.clear();
            try {
                if (this.q != null) {
                    try {
                        Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        gsmCellLocation = (GsmCellLocation) declaredField.get(cellLocation);
                    } catch (Throwable unused) {
                    }
                    if (gsmCellLocation == null || !b(gsmCellLocation)) {
                        z2 = false;
                    } else {
                        a(gsmCellLocation, strArrA, z);
                        z2 = true;
                    }
                    if (z2) {
                        return;
                    }
                }
                if (b(cellLocation)) {
                    this.f3754a = 2;
                    dv dvVar = new dv(2, true);
                    dvVar.f3752a = Integer.parseInt(strArrA[0]);
                    dvVar.b = Integer.parseInt(strArrA[1]);
                    dvVar.g = em.b(cellLocation, "getSystemId", new Object[0]);
                    dvVar.h = em.b(cellLocation, "getNetworkId", new Object[0]);
                    dvVar.i = em.b(cellLocation, "getBaseStationId", new Object[0]);
                    dvVar.j = this.o;
                    dvVar.f3753e = em.b(cellLocation, "getBaseStationLatitude", new Object[0]);
                    int iB = em.b(cellLocation, "getBaseStationLongitude", new Object[0]);
                    dvVar.f = iB;
                    if (dvVar.f3753e != iB || dvVar.f3753e <= 0) {
                        z3 = false;
                    }
                    if (dvVar.f3753e < 0 || dvVar.f < 0 || dvVar.f3753e == Integer.MAX_VALUE || dvVar.f == Integer.MAX_VALUE || z3) {
                        dvVar.f3753e = 0;
                        dvVar.f = 0;
                    }
                    if (!this.b.contains(dvVar)) {
                        this.b.add(dvVar);
                    }
                }
            } catch (Throwable th) {
                ej.a(th, "CgiManager", "hdlCdmaLocChange");
            }
        }
    }

    public static boolean a(int i) {
        return i > 0 && i <= 15;
    }

    public static boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > 65535 || i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(int i) {
        if (i == -113) {
            this.o = -113;
            return;
        }
        this.o = i;
        int i2 = this.f3754a;
        if ((i2 == 1 || i2 == 2) && this.b != null && !this.b.isEmpty()) {
            try {
                this.b.get(0).j = this.o;
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00a6 A[Catch: all -> 0x012c, TRY_LEAVE, TryCatch #5 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:9:0x0011, B:11:0x001b, B:13:0x001f, B:14:0x0026, B:18:0x0036, B:25:0x0046, B:27:0x0050, B:28:0x0054, B:30:0x005a, B:31:0x0063, B:33:0x0071, B:34:0x007d, B:36:0x0082, B:41:0x008b, B:45:0x0093, B:44:0x0090, B:49:0x009c, B:51:0x00a6, B:82:0x0112, B:84:0x0116, B:86:0x0124), top: B:105:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d8 A[Catch: all -> 0x0112, TryCatch #1 {all -> 0x0112, blocks: (B:52:0x00a9, B:54:0x00b1, B:56:0x00b5, B:57:0x00b9, B:58:0x00bf, B:66:0x00d0, B:69:0x00d8, B:71:0x00dd, B:79:0x0103, B:81:0x0109, B:64:0x00c8), top: B:97:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00dd A[Catch: all -> 0x0112, TRY_LEAVE, TryCatch #1 {all -> 0x0112, blocks: (B:52:0x00a9, B:54:0x00b1, B:56:0x00b5, B:57:0x00b9, B:58:0x00bf, B:66:0x00d0, B:69:0x00d8, B:71:0x00dd, B:79:0x0103, B:81:0x0109, B:64:0x00c8), top: B:97:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0116 A[Catch: all -> 0x012c, TryCatch #5 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:9:0x0011, B:11:0x001b, B:13:0x001f, B:14:0x0026, B:18:0x0036, B:25:0x0046, B:27:0x0050, B:28:0x0054, B:30:0x005a, B:31:0x0063, B:33:0x0071, B:34:0x007d, B:36:0x0082, B:41:0x008b, B:45:0x0093, B:44:0x0090, B:49:0x009c, B:51:0x00a6, B:82:0x0112, B:84:0x0116, B:86:0x0124), top: B:105:0x0001, inners: #4 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void b(boolean r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dw.b(boolean, boolean):void");
    }

    private boolean b(CellLocation cellLocation) {
        boolean zA = a(cellLocation);
        if (!zA) {
            this.f3754a = 0;
        }
        return zA;
    }

    private int c(CellLocation cellLocation) {
        if (this.j || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            ej.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    public static /* synthetic */ void c(dw dwVar) {
        int iB;
        dwVar.h = dwVar.new b();
        try {
            iB = em.b("android.telephony.PhoneStateListener", "LISTEN_SIGNAL_STRENGTHS");
        } catch (Throwable unused) {
            iB = 0;
        }
        if (iB == 0) {
            try {
                dwVar.d.listen(dwVar.h, 16);
            } catch (Throwable unused2) {
            }
        } else {
            try {
                dwVar.d.listen(dwVar.h, iB | 16);
            } catch (Throwable unused3) {
            }
        }
    }

    public static boolean c(int i) {
        return (i == -1 || i == 0 || i > 65535) ? false : true;
    }

    public static boolean d(int i) {
        return (i == -1 || i == 0 || i == 65535 || i >= 268435455) ? false : true;
    }

    public static /* synthetic */ boolean d(dw dwVar) {
        dwVar.u = true;
        return true;
    }

    public static int j() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            r = 1;
        } catch (Throwable unused) {
        }
        if (r == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                r = 2;
            } catch (Throwable unused2) {
            }
        }
        return r;
    }

    private CellLocation o() {
        TelephonyManager telephonyManager = this.d;
        if (telephonyManager != null) {
            try {
                CellLocation cellLocation = telephonyManager.getCellLocation();
                this.i = null;
                if (b(cellLocation)) {
                    this.f = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            } catch (Throwable th) {
                this.i = null;
                ej.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    private synchronized void p() {
        int i = this.f3754a & 3;
        if (i != 1) {
            if (i == 2 && this.b.isEmpty()) {
                this.f3754a = 0;
            }
        } else if (this.b.isEmpty()) {
            this.f3754a = 0;
        }
    }

    @SuppressLint({"NewApi"})
    private CellLocation q() {
        TelephonyManager telephonyManager = this.d;
        CellLocation cellLocationA = null;
        if (telephonyManager == null) {
            return null;
        }
        if (ep.c() >= 18) {
            try {
                cellLocationA = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            }
        }
        if (cellLocationA != null) {
            return cellLocationA;
        }
        CellLocation cellLocationO = o();
        if (b(cellLocationO)) {
            return cellLocationO;
        }
        CellLocation cellLocationA2 = a(telephonyManager, "getCellLocationExt", 1);
        return cellLocationA2 != null ? cellLocationA2 : a(telephonyManager, "getCellLocationGemini", 1);
    }

    private CellLocation r() {
        if (!v) {
            v = true;
        }
        Object obj = this.q;
        CellLocation cellLocationA = null;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> clsS = s();
            if (clsS.isInstance(obj)) {
                Object objCast = clsS.cast(obj);
                CellLocation cellLocationA2 = a(objCast, "getCellLocation", new Object[0]);
                if (cellLocationA2 != null) {
                    return cellLocationA2;
                }
                CellLocation cellLocationA3 = a(objCast, "getCellLocation", 1);
                if (cellLocationA3 != null) {
                    return cellLocationA3;
                }
                CellLocation cellLocationA4 = a(objCast, "getCellLocationGemini", 1);
                if (cellLocationA4 != null) {
                    return cellLocationA4;
                }
                cellLocationA = a(objCast, "getAllCellInfo", 1);
                if (cellLocationA != null) {
                    return cellLocationA;
                }
            }
        } catch (Throwable th) {
            ej.a(th, "CgiManager", "getSim2Cgi");
        }
        return cellLocationA;
    }

    public static Class<?> s() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        int i = r;
        try {
            return systemClassLoader.loadClass(i != 0 ? i != 1 ? i != 2 ? null : "android.telephony.TelephonyManager2" : "android.telephony.MSimTelephonyManager" : "android.telephony.TelephonyManager");
        } catch (Throwable th) {
            ej.a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    public final List<cw> a() {
        Object obj;
        Object obj2;
        ArrayList arrayList = new ArrayList();
        List<CellInfo> allCellInfo = this.d.getAllCellInfo();
        if (Build.VERSION.SDK_INT >= 17 && allCellInfo != null) {
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                    cx cxVar = new cx(cellInfo.isRegistered(), true);
                    cxVar.m = cellIdentity.getLatitude();
                    cxVar.n = cellIdentity.getLongitude();
                    cxVar.j = cellIdentity.getSystemId();
                    cxVar.k = cellIdentity.getNetworkId();
                    cxVar.l = cellIdentity.getBasestationId();
                    cxVar.d = cellInfoCdma.getCellSignalStrength().getAsuLevel();
                    cxVar.c = cellInfoCdma.getCellSignalStrength().getCdmaDbm();
                    obj = cxVar;
                } else {
                    if (cellInfo instanceof CellInfoGsm) {
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                        CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                        cy cyVar = new cy(cellInfo.isRegistered(), true);
                        cyVar.f3711a = String.valueOf(cellIdentity2.getMcc());
                        cyVar.b = String.valueOf(cellIdentity2.getMnc());
                        cyVar.j = cellIdentity2.getLac();
                        cyVar.k = cellIdentity2.getCid();
                        cyVar.c = cellInfoGsm.getCellSignalStrength().getDbm();
                        cyVar.d = cellInfoGsm.getCellSignalStrength().getAsuLevel();
                        obj2 = cyVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            cyVar.m = cellIdentity2.getArfcn();
                            cyVar.n = cellIdentity2.getBsic();
                            obj2 = cyVar;
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                        cz czVar = new cz(cellInfo.isRegistered());
                        czVar.f3711a = String.valueOf(cellIdentity3.getMcc());
                        czVar.b = String.valueOf(cellIdentity3.getMnc());
                        czVar.l = cellIdentity3.getPci();
                        czVar.d = cellInfoLte.getCellSignalStrength().getAsuLevel();
                        czVar.k = cellIdentity3.getCi();
                        czVar.j = cellIdentity3.getTac();
                        czVar.n = cellInfoLte.getCellSignalStrength().getTimingAdvance();
                        czVar.c = cellInfoLte.getCellSignalStrength().getDbm();
                        obj = czVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            czVar.m = cellIdentity3.getEarfcn();
                            obj = czVar;
                        }
                    } else if (Build.VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma)) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                        da daVar = new da(cellInfo.isRegistered(), true);
                        daVar.f3711a = String.valueOf(cellIdentity4.getMcc());
                        daVar.b = String.valueOf(cellIdentity4.getMnc());
                        daVar.j = cellIdentity4.getLac();
                        daVar.k = cellIdentity4.getCid();
                        daVar.l = cellIdentity4.getPsc();
                        daVar.d = cellInfoWcdma.getCellSignalStrength().getAsuLevel();
                        daVar.c = cellInfoWcdma.getCellSignalStrength().getDbm();
                        obj2 = daVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            daVar.m = cellIdentity4.getUarfcn();
                            obj2 = daVar;
                        }
                    }
                    arrayList.add(obj2);
                }
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void a(Cdo cdo) {
        this.w = cdo;
    }

    public final synchronized void a(boolean z, boolean z2) {
        try {
            try {
                boolean zA = ep.a(this.l);
                this.j = zA;
                if ((!zA && ep.b() - this.f3755e >= 10000) || this.b.isEmpty()) {
                    b(z, z2);
                    this.f3755e = ep.b();
                }
                if (this.j) {
                    i();
                } else {
                    p();
                }
            } catch (Throwable th) {
                ej.a(th, "CgiManager", "refresh");
            }
        } catch (SecurityException e2) {
            this.i = e2.getMessage();
        }
    }

    public final boolean a(CellLocation cellLocation) {
        String str;
        boolean z = false;
        if (cellLocation == null) {
            return false;
        }
        int iC = c(cellLocation);
        if (iC == 1) {
            try {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                return a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
            } catch (Throwable th) {
                th = th;
                str = "cgiUseful Cgi.I_GSM_T";
            }
        } else {
            if (iC != 2) {
                return true;
            }
            try {
                if (em.b(cellLocation, "getSystemId", new Object[0]) > 0 && em.b(cellLocation, "getNetworkId", new Object[0]) >= 0) {
                    if (em.b(cellLocation, "getBaseStationId", new Object[0]) >= 0) {
                        z = true;
                    }
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                str = "cgiUseful Cgi.I_CDMA_T";
            }
        }
        ej.a(th, "CgiManager", str);
        return true;
    }

    public final synchronized ArrayList<dv> b() {
        return this.b;
    }

    public final ArrayList<dv> c() {
        return this.n;
    }

    public final synchronized dv d() {
        if (this.j) {
            return null;
        }
        ArrayList<dv> arrayList = this.b;
        if (arrayList.size() <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    public final int e() {
        return this.f3754a;
    }

    public final int f() {
        return this.f3754a & 3;
    }

    public final TelephonyManager g() {
        return this.d;
    }

    public final void h() {
        PhoneStateListener phoneStateListener;
        this.p.a();
        this.s = 0L;
        synchronized (this.y) {
            this.x = true;
        }
        TelephonyManager telephonyManager = this.d;
        if (telephonyManager != null && (phoneStateListener = this.h) != null) {
            try {
                telephonyManager.listen(phoneStateListener, 0);
            } catch (Throwable th) {
                ej.a(th, "CgiManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
            }
        }
        this.h = null;
        this.o = -113;
        this.d = null;
        this.q = null;
    }

    public final synchronized void i() {
        this.i = null;
        this.f = null;
        this.f3754a = 0;
        this.b.clear();
        this.n.clear();
    }

    public final String k() {
        return this.i;
    }

    public final String l() {
        return this.m;
    }

    public final synchronized String m() {
        if (this.j) {
            i();
        }
        if (this.k == null) {
            this.k = new StringBuilder();
        } else {
            this.k.delete(0, this.k.length());
        }
        if ((this.f3754a & 3) == 1) {
            for (int i = 1; i < this.b.size(); i++) {
                StringBuilder sb = this.k;
                sb.append("#");
                sb.append(this.b.get(i).b);
                StringBuilder sb2 = this.k;
                sb2.append("|");
                sb2.append(this.b.get(i).c);
                StringBuilder sb3 = this.k;
                sb3.append("|");
                sb3.append(this.b.get(i).d);
            }
        }
        if (this.k.length() > 0) {
            this.k.deleteCharAt(0);
        }
        return this.k.toString();
    }

    public final boolean n() {
        try {
            if (this.d != null) {
                if (!TextUtils.isEmpty(this.d.getSimOperator())) {
                    return true;
                }
                if (!TextUtils.isEmpty(this.d.getSimCountryIso())) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        try {
            int iA = ep.a(ep.c(this.l));
            return iA == 0 || iA == 4 || iA == 2 || iA == 5 || iA == 3;
        } catch (Throwable unused2) {
            return false;
        }
    }
}
