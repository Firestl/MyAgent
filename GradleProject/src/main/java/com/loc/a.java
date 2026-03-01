package com.loc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: GeoFenceManager.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public final class a {
    public static boolean A = false;
    public Context b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public en f3618a = null;
    public PendingIntent c = null;
    public String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public GeoFenceListener f3619e = null;
    public Object z = new Object();
    public volatile int f = 1;
    public ArrayList<GeoFence> g = new ArrayList<>();
    public c h = null;
    public Object i = new Object();
    public Object j = new Object();
    public HandlerC0079a k = null;
    public b l = null;
    public volatile boolean m = false;
    public volatile boolean n = false;
    public volatile boolean o = false;
    public com.loc.b p = null;
    public com.loc.c q = null;
    public AMapLocationClient r = null;
    public volatile AMapLocation s = null;
    public long t = 0;
    public AMapLocationClientOption u = null;
    public int v = 0;
    public AMapLocationListener w = new AMapLocationListener() { // from class: com.loc.a.1
        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            boolean z;
            int errorCode;
            try {
                if (!a.this.y && a.this.o) {
                    a.this.s = aMapLocation;
                    if (aMapLocation != null) {
                        errorCode = aMapLocation.getErrorCode();
                        if (aMapLocation.getErrorCode() == 0) {
                            a.this.t = ep.b();
                            a.this.a(5, (Bundle) null, 0L);
                            z = true;
                        } else {
                            a.a("定位失败", aMapLocation.getErrorCode(), aMapLocation.getErrorInfo(), "locationDetail:" + aMapLocation.getLocationDetail());
                            z = false;
                        }
                    } else {
                        z = false;
                        errorCode = 8;
                    }
                    if (z) {
                        a.this.v = 0;
                        a.this.a(6, (Bundle) null, 0L);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    if (!a.this.m) {
                        a.this.b(7);
                        bundle.putLong("interval", 2000L);
                        a.this.a(8, bundle, 2000L);
                    }
                    a.this.v++;
                    if (a.this.v >= 3) {
                        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, errorCode);
                        a.this.a(1002, bundle);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    };
    public final int x = 3;
    public volatile boolean y = false;

    /* JADX INFO: renamed from: com.loc.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GeoFenceManager.java */
    public class HandlerC0079a extends Handler {
        public HandlerC0079a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String string;
            GeoFence geoFenceA;
            try {
                String str = "";
                int iB = 1;
                switch (message.what) {
                    case 0:
                        a aVar = a.this;
                        Bundle data = message.getData();
                        try {
                            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                            if (data == null || data.isEmpty()) {
                                string = str;
                            } else {
                                DPoint dPoint = (DPoint) data.getParcelable("centerPoint");
                                string = data.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (dPoint == null) {
                                    str = string;
                                    string = str;
                                } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                                    a.a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                                } else {
                                    GeoFence geoFenceA2 = aVar.a(data, false);
                                    iB = aVar.b(geoFenceA2);
                                    if (iB == 0) {
                                        arrayList.add(geoFenceA2);
                                    }
                                }
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("errorCode", iB);
                            bundle.putParcelableArrayList("resultList", arrayList);
                            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                            aVar.a(1000, bundle);
                            return;
                        } catch (Throwable th) {
                            ej.a(th, "GeoFenceManager", "doAddGeoFenceRound");
                            return;
                        }
                    case 1:
                        a aVar2 = a.this;
                        Bundle data2 = message.getData();
                        try {
                            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                            if (data2 != null && !data2.isEmpty()) {
                                ArrayList parcelableArrayList = data2.getParcelableArrayList("pointList");
                                String string2 = data2.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (parcelableArrayList != null && parcelableArrayList.size() > 2 && (iB = aVar2.b((geoFenceA = aVar2.a(data2, true)))) == 0) {
                                    arrayList2.add(geoFenceA);
                                }
                                str = string2;
                            }
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                            bundle2.putInt("errorCode", iB);
                            bundle2.putParcelableArrayList("resultList", arrayList2);
                            aVar2.a(1000, bundle2);
                            return;
                        } catch (Throwable th2) {
                            ej.a(th2, "GeoFenceManager", "doAddGeoFencePolygon");
                            return;
                        }
                    case 2:
                        a.this.c(message.getData());
                        return;
                    case 3:
                        a.this.b(message.getData());
                        return;
                    case 4:
                        a.this.d(message.getData());
                        return;
                    case 5:
                        a.this.f();
                        return;
                    case 6:
                        a.this.a(a.this.s);
                        return;
                    case 7:
                        a aVar3 = a.this;
                        try {
                            if (aVar3.r != null) {
                                aVar3.e();
                                aVar3.u.setOnceLocation(true);
                                aVar3.r.setLocationOption(aVar3.u);
                                aVar3.r.startLocation();
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            ej.a(th3, "GeoFenceManager", "doStartOnceLocation");
                            return;
                        }
                    case 8:
                        a aVar4 = a.this;
                        Bundle data3 = message.getData();
                        try {
                            if (aVar4.r != null) {
                                long j = 2000;
                                if (data3 != null && !data3.isEmpty()) {
                                    j = data3.getLong("interval", 2000L);
                                }
                                aVar4.u.setOnceLocation(false);
                                aVar4.u.setInterval(j);
                                aVar4.r.setLocationOption(aVar4.u);
                                if (aVar4.m) {
                                    return;
                                }
                                aVar4.r.stopLocation();
                                aVar4.r.startLocation();
                                aVar4.m = true;
                                return;
                            }
                            return;
                        } catch (Throwable th4) {
                            ej.a(th4, "GeoFenceManager", "doStartContinueLocation");
                            return;
                        }
                    case 9:
                        a.this.a(message.getData());
                        return;
                    case 10:
                        a.this.c();
                        return;
                    case 11:
                        a.this.f(message.getData());
                        return;
                    case 12:
                        a.this.e(message.getData());
                        return;
                    case 13:
                        a.this.h();
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: compiled from: GeoFenceManager.java */
    public static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: compiled from: GeoFenceManager.java */
    public class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                Bundle data = message.getData();
                switch (message.what) {
                    case 1000:
                        a aVar = a.this;
                        if (data != null) {
                            try {
                                if (data.isEmpty()) {
                                    return;
                                }
                                int i = data.getInt("errorCode");
                                ArrayList parcelableArrayList = data.getParcelableArrayList("resultList");
                                if (parcelableArrayList == null) {
                                    parcelableArrayList = new ArrayList();
                                }
                                String string = data.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (string == null) {
                                    string = "";
                                }
                                if (aVar.f3619e != null) {
                                    aVar.f3619e.onGeoFenceCreateFinished((ArrayList) parcelableArrayList.clone(), i, string);
                                }
                                if (i == 0) {
                                    aVar.d();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                ej.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
                                return;
                            }
                        }
                        return;
                    case 1001:
                        try {
                            a.this.c((GeoFence) data.getParcelable("geoFence"));
                            return;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            return;
                        }
                    case 1002:
                        try {
                            a.this.c(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                            return;
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    public a(Context context) {
        this.b = null;
        try {
            this.b = context.getApplicationContext();
            k();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManger", "<init>");
        }
    }

    public static float a(DPoint dPoint, List<DPoint> list) {
        float fMin = Float.MAX_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                fMin = Math.min(fMin, ep.a(dPoint, it.next()));
            }
        }
        return fMin;
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            Iterator<GeoFence> it = list.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            return 0;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    public static Bundle a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        if (str == null) {
            str = "";
        }
        bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    public static void a(String str, int i, String str2, String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("===========================================\n");
        stringBuffer.append("              " + str + "                ");
        stringBuffer.append("\n");
        stringBuffer.append("-------------------------------------------\n");
        stringBuffer.append("errorCode:" + i);
        stringBuffer.append("\n");
        stringBuffer.append("错误信息:" + str2);
        stringBuffer.append("\n");
        if (strArr.length > 0) {
            for (String str3 : strArr) {
                stringBuffer.append(str3);
                stringBuffer.append("\n");
            }
        }
        stringBuffer.append("===========================================\n");
        Log.i("fenceErrLog", stringBuffer.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(com.amap.api.fence.GeoFence r4, int r5) {
        /*
            r0 = r5 & 1
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L10
            int r0 = r4.getStatus()     // Catch: java.lang.Throwable -> Le
            if (r0 != r1) goto L10
            r2 = 1
            goto L10
        Le:
            r4 = move-exception
            goto L28
        L10:
            r0 = r5 & 2
            r3 = 2
            if (r0 != r3) goto L1c
            int r0 = r4.getStatus()     // Catch: java.lang.Throwable -> Le
            if (r0 != r3) goto L1c
            r2 = 1
        L1c:
            r0 = 4
            r5 = r5 & r0
            if (r5 != r0) goto L30
            int r4 = r4.getStatus()     // Catch: java.lang.Throwable -> Le
            r5 = 3
            if (r4 != r5) goto L30
            goto L31
        L28:
            java.lang.String r5 = "Utils"
            java.lang.String r0 = "remindStatus"
            com.loc.ej.a(r4, r5, r0)
            goto L32
        L30:
            r1 = r2
        L31:
            r2 = r1
        L32:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.a(com.amap.api.fence.GeoFence, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002a A[Catch: all -> 0x0092, TRY_LEAVE, TryCatch #0 {all -> 0x0092, blocks: (B:3:0x0002, B:6:0x000a, B:8:0x0010, B:10:0x001a, B:16:0x002a, B:29:0x0061), top: B:41:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061 A[Catch: all -> 0x0092, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0092, blocks: (B:3:0x0002, B:6:0x000a, B:8:0x0010, B:10:0x001a, B:16:0x002a, B:29:0x0061), top: B:41:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(com.amap.api.location.AMapLocation r10, com.amap.api.fence.GeoFence r11) {
        /*
            r0 = 0
            r1 = 1
            boolean r2 = com.loc.ep.a(r10)     // Catch: java.lang.Throwable -> L92
            if (r2 == 0) goto L9a
            if (r11 == 0) goto L9a
            java.util.List r2 = r11.getPointList()     // Catch: java.lang.Throwable -> L92
            if (r2 == 0) goto L9a
            java.util.List r2 = r11.getPointList()     // Catch: java.lang.Throwable -> L92
            boolean r2 = r2.isEmpty()     // Catch: java.lang.Throwable -> L92
            if (r2 != 0) goto L9a
            int r2 = r11.getType()     // Catch: java.lang.Throwable -> L92
            r3 = 2
            r4 = 3
            if (r2 == 0) goto L61
            if (r2 == r1) goto L2a
            if (r2 == r3) goto L61
            if (r2 == r4) goto L2a
            goto L9a
        L2a:
            java.util.List r11 = r11.getPointList()     // Catch: java.lang.Throwable -> L92
            java.util.Iterator r11 = r11.iterator()     // Catch: java.lang.Throwable -> L92
            r2 = 0
        L33:
            boolean r3 = r11.hasNext()     // Catch: java.lang.Throwable -> L5e
            if (r3 == 0) goto L5c
            java.lang.Object r3 = r11.next()     // Catch: java.lang.Throwable -> L5e
            java.util.List r3 = (java.util.List) r3     // Catch: java.lang.Throwable -> L5e
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L5e
            if (r5 >= r4) goto L47
            r3 = 0
            goto L58
        L47:
            com.amap.api.location.DPoint r5 = new com.amap.api.location.DPoint     // Catch: java.lang.Throwable -> L5e
            double r6 = r10.getLatitude()     // Catch: java.lang.Throwable -> L5e
            double r8 = r10.getLongitude()     // Catch: java.lang.Throwable -> L5e
            r5.<init>(r6, r8)     // Catch: java.lang.Throwable -> L5e
            boolean r3 = com.loc.ej.a(r5, r3)     // Catch: java.lang.Throwable -> L5e
        L58:
            if (r3 == 0) goto L33
            r2 = 1
            goto L33
        L5c:
            r0 = r2
            goto L9a
        L5e:
            r10 = move-exception
            r0 = r2
            goto L93
        L61:
            com.amap.api.location.DPoint r2 = r11.getCenter()     // Catch: java.lang.Throwable -> L92
            float r11 = r11.getRadius()     // Catch: java.lang.Throwable -> L92
            r5 = 4
            double[] r5 = new double[r5]     // Catch: java.lang.Throwable -> L92
            double r6 = r2.getLatitude()     // Catch: java.lang.Throwable -> L92
            r5[r0] = r6     // Catch: java.lang.Throwable -> L92
            double r6 = r2.getLongitude()     // Catch: java.lang.Throwable -> L92
            r5[r1] = r6     // Catch: java.lang.Throwable -> L92
            double r6 = r10.getLatitude()     // Catch: java.lang.Throwable -> L92
            r5[r3] = r6     // Catch: java.lang.Throwable -> L92
            double r2 = r10.getLongitude()     // Catch: java.lang.Throwable -> L92
            r5[r4] = r2     // Catch: java.lang.Throwable -> L92
            float r10 = com.loc.ep.a(r5)     // Catch: java.lang.Throwable -> L92
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 > 0) goto L8e
            r10 = 1
            goto L8f
        L8e:
            r10 = 0
        L8f:
            if (r10 == 0) goto L9a
            goto L9b
        L92:
            r10 = move-exception
        L93:
            java.lang.String r11 = "Utils"
            java.lang.String r1 = "isInGeoFence"
            com.loc.ej.a(r10, r11, r1)
        L9a:
            r1 = r0
        L9b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.a(com.amap.api.location.AMapLocation, com.amap.api.fence.GeoFence):boolean");
    }

    public static float b(DPoint dPoint, List<DPoint> list) {
        float fMax = Float.MIN_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                fMax = Math.max(fMax, ep.a(dPoint, it.next()));
            }
        }
        return fMax;
    }

    public static DPoint b(List<DPoint> list) {
        DPoint dPoint = new DPoint();
        if (list == null) {
            return dPoint;
        }
        try {
            double latitude = 0.0d;
            double longitude = 0.0d;
            for (DPoint dPoint2 : list) {
                latitude += dPoint2.getLatitude();
                longitude += dPoint2.getLongitude();
            }
            return new DPoint(ep.b(latitude / ((double) list.size())), ep.b(longitude / ((double) list.size())));
        } catch (Throwable th) {
            ej.a(th, "GeoFenceUtil", "getPolygonCenter");
            return dPoint;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0203  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(int r27, android.os.Bundle r28) {
        /*
            Method dump skipped, instruction units count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.b(int, android.os.Bundle):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b(com.amap.api.location.AMapLocation r7, com.amap.api.fence.GeoFence r8) {
        /*
            r0 = 1
            r1 = 0
            boolean r7 = a(r7, r8)     // Catch: java.lang.Throwable -> L50
            r2 = -1
            if (r7 == 0) goto L3e
            long r4 = r8.getEnterTime()     // Catch: java.lang.Throwable -> L50
            int r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r7 != 0) goto L23
            int r7 = r8.getStatus()     // Catch: java.lang.Throwable -> L50
            if (r7 == r0) goto L4e
            long r2 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L50
            r8.setEnterTime(r2)     // Catch: java.lang.Throwable -> L50
            r8.setStatus(r0)     // Catch: java.lang.Throwable -> L50
            goto L59
        L23:
            int r7 = r8.getStatus()     // Catch: java.lang.Throwable -> L50
            r2 = 3
            if (r7 == r2) goto L4e
            long r3 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L50
            long r5 = r8.getEnterTime()     // Catch: java.lang.Throwable -> L50
            long r3 = r3 - r5
            r5 = 600000(0x927c0, double:2.964394E-318)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L4e
            r8.setStatus(r2)     // Catch: java.lang.Throwable -> L50
            goto L59
        L3e:
            int r7 = r8.getStatus()     // Catch: java.lang.Throwable -> L50
            r4 = 2
            if (r7 == r4) goto L4e
            r8.setStatus(r4)     // Catch: java.lang.Throwable -> L4c
            r8.setEnterTime(r2)     // Catch: java.lang.Throwable -> L4c
            goto L59
        L4c:
            r7 = move-exception
            goto L52
        L4e:
            r0 = 0
            goto L59
        L50:
            r7 = move-exception
            r0 = 0
        L52:
            java.lang.String r8 = "Utils"
            java.lang.String r1 = "isFenceStatusChanged"
            com.loc.ej.a(r7, r8, r1)
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.b(com.amap.api.location.AMapLocation, com.amap.api.fence.GeoFence):boolean");
    }

    private void k() {
        if (!this.o) {
            this.o = true;
        }
        if (this.n) {
            return;
        }
        try {
            this.h = Looper.myLooper() == null ? new c(this.b.getMainLooper()) : new c();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManger", "init 1");
        }
        try {
            b bVar = new b("fenceActionThread");
            this.l = bVar;
            bVar.setPriority(5);
            this.l.start();
            this.k = new HandlerC0079a(this.l.getLooper());
        } catch (Throwable th2) {
            ej.a(th2, "GeoFenceManger", "init 2");
        }
        try {
            this.p = new com.loc.b(this.b);
            this.q = new com.loc.c();
            this.u = new AMapLocationClientOption();
            this.r = new AMapLocationClient(this.b);
            this.u.setLocationCacheEnable(true);
            this.u.setNeedAddress(false);
            this.r.setLocationListener(this.w);
            if (this.f3618a == null) {
                this.f3618a = new en();
            }
        } catch (Throwable th3) {
            ej.a(th3, "GeoFenceManger", "initBase");
        }
        this.n = true;
        try {
            if (this.d != null && this.c == null) {
                a(this.d);
            }
        } catch (Throwable th4) {
            ej.a(th4, "GeoFenceManger", "init 4");
        }
        if (A) {
            return;
        }
        A = true;
        en.a(this.b, "O020", (JSONObject) null);
    }

    public final PendingIntent a(String str) {
        synchronized (this.z) {
            try {
                Intent intent = new Intent(str);
                intent.setPackage(k.c(this.b));
                this.c = PendingIntent.getBroadcast(this.b, 0, intent, 0);
                this.d = str;
            } finally {
            }
            if (this.g != null && !this.g.isEmpty()) {
                for (GeoFence geoFence : this.g) {
                    geoFence.setPendingIntent(this.c);
                    geoFence.setPendingIntentAction(this.d);
                }
            }
        }
        return this.c;
    }

    public final GeoFence a(Bundle bundle, boolean z) {
        GeoFence geoFence = new GeoFence();
        ArrayList arrayList = new ArrayList();
        DPoint dPoint = new DPoint();
        if (z) {
            geoFence.setType(1);
            arrayList = bundle.getParcelableArrayList("pointList");
            if (arrayList != null) {
                dPoint = b(arrayList);
            }
            geoFence.setMaxDis2Center(b(dPoint, arrayList));
            geoFence.setMinDis2Center(a(dPoint, arrayList));
        } else {
            geoFence.setType(0);
            dPoint = (DPoint) bundle.getParcelable("centerPoint");
            if (dPoint != null) {
                arrayList.add(dPoint);
            }
            float f = bundle.getFloat("fenceRadius", 1000.0f);
            float f2 = f > 0.0f ? f : 1000.0f;
            geoFence.setRadius(f2);
            geoFence.setMinDis2Center(f2);
            geoFence.setMaxDis2Center(f2);
        }
        geoFence.setActivatesAction(this.f);
        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(arrayList);
        geoFence.setPointList(arrayList2);
        geoFence.setCenter(dPoint);
        geoFence.setPendingIntentAction(this.d);
        geoFence.setExpiration(-1L);
        geoFence.setPendingIntent(this.c);
        StringBuilder sb = new StringBuilder();
        sb.append(com.loc.c.a());
        geoFence.setFenceId(sb.toString());
        en enVar = this.f3618a;
        if (enVar != null) {
            enVar.a(this.b, 2);
        }
        return geoFence;
    }

    public final void a() {
        try {
            this.o = false;
            a(10, (Bundle) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "removeGeoFence");
        }
    }

    public final void a(int i) {
        try {
            k();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            a(9, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    public final void a(int i, Bundle bundle) {
        try {
            synchronized (this.j) {
                if (this.h != null) {
                    Message messageObtainMessage = this.h.obtainMessage();
                    messageObtainMessage.what = i;
                    messageObtainMessage.setData(bundle);
                    this.h.sendMessage(messageObtainMessage);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    public final void a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    Message messageObtainMessage = this.k.obtainMessage();
                    messageObtainMessage.what = i;
                    messageObtainMessage.setData(bundle);
                    this.k.sendMessageDelayed(messageObtainMessage, j);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    public final void a(Bundle bundle) {
        int i = 1;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                ej.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        if (this.f != i) {
            if (this.g != null && !this.g.isEmpty()) {
                for (GeoFence geoFence : this.g) {
                    geoFence.setStatus(0);
                    geoFence.setEnterTime(-1L);
                }
            }
            d();
        }
        this.f = i;
    }

    public final void a(GeoFenceListener geoFenceListener) {
        try {
            this.f3619e = geoFenceListener;
        } catch (Throwable unused) {
        }
    }

    public final void a(AMapLocation aMapLocation) {
        try {
            if (this.y || this.g == null || this.g.isEmpty() || aMapLocation == null || aMapLocation.getErrorCode() != 0) {
                return;
            }
            for (GeoFence geoFence : this.g) {
                if (geoFence.isAble() && b(aMapLocation, geoFence) && a(geoFence, this.f)) {
                    geoFence.setCurrentLocation(aMapLocation);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("geoFence", geoFence);
                    a(1001, bundle);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    public final void a(DPoint dPoint, float f, String str) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("fenceRadius", f);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(0, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    public final void a(String str, String str2) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    public final void a(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        try {
            k();
            if (f <= 0.0f || f > 50000.0f) {
                f = 3000.0f;
            }
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f);
            bundle.putInt("searchSize", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    public final void a(String str, String str2, String str3, int i, String str4) {
        try {
            k();
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putString("city", str3);
            bundle.putInt("searchSize", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    public final void a(String str, boolean z) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putString("fid", str);
            bundle.putBoolean("ab", z);
            a(12, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "setGeoFenceAble");
        }
    }

    public final void a(List<DPoint> list, String str) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("pointList", new ArrayList<>(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    public final boolean a(GeoFence geoFence) {
        try {
            if (this.g != null && !this.g.isEmpty()) {
                if (!this.g.contains(geoFence)) {
                    return false;
                }
                if (this.g.size() == 1) {
                    this.o = false;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("fc", geoFence);
                a(11, bundle, 0L);
                return true;
            }
            this.o = false;
            a(10, (Bundle) null, 0L);
            return true;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
            return false;
        }
    }

    public final int b(GeoFence geoFence) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            if (this.g.contains(geoFence)) {
                return 17;
            }
            this.g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    public final List<GeoFence> b() {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            return (ArrayList) this.g.clone();
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    public final void b(int i) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    this.k.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    public final void b(Bundle bundle) {
        b(2, bundle);
    }

    public final void c() {
        try {
            if (!this.n) {
                return;
            }
            if (this.g != null) {
                this.g.clear();
                this.g = null;
            }
            if (this.o) {
                return;
            }
            try {
                synchronized (this.i) {
                    if (this.k != null) {
                        this.k.removeCallbacksAndMessages(null);
                    }
                    this.k = null;
                }
            } catch (Throwable th) {
                ej.a(th, "GeoFenceManager", "destroyActionHandler");
            }
            if (this.r != null) {
                this.r.stopLocation();
                this.r.onDestroy();
            }
            this.r = null;
            if (this.l != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.l.quitSafely();
                } else {
                    this.l.quit();
                }
            }
            this.l = null;
            this.p = null;
            synchronized (this.z) {
                if (this.c != null) {
                    this.c.cancel();
                }
                this.c = null;
            }
            try {
                synchronized (this.j) {
                    if (this.h != null) {
                        this.h.removeCallbacksAndMessages(null);
                    }
                    this.h = null;
                }
            } catch (Throwable th2) {
                ej.a(th2, "GeoFenceManager", "destroyResultHandler");
            }
            if (this.f3618a != null) {
                this.f3618a.b(this.b);
            }
        } catch (Throwable unused) {
        }
        this.m = false;
        this.n = false;
    }

    public final void c(int i) {
        try {
            if (this.b != null) {
                synchronized (this.z) {
                    if (this.c == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a((GeoFence) null, (String) null, (String) null, 4, i));
                    this.c.send(this.b, 0, intent);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    public final void c(Bundle bundle) {
        b(1, bundle);
    }

    public final void c(GeoFence geoFence) {
        PendingIntent pendingIntent;
        Context context;
        try {
            synchronized (this.z) {
                if (this.b != null) {
                    if (this.c == null && geoFence.getPendingIntent() == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a(geoFence, geoFence.getFenceId(), geoFence.getCustomId(), geoFence.getStatus(), 0));
                    if (this.d != null) {
                        intent.setAction(this.d);
                    }
                    intent.setPackage(k.c(this.b));
                    if (geoFence.getPendingIntent() != null) {
                        pendingIntent = geoFence.getPendingIntent();
                        context = this.b;
                    } else {
                        pendingIntent = this.c;
                        context = this.b;
                    }
                    pendingIntent.send(context, 0, intent);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "resultTriggerGeoFence");
        }
    }

    public final void d() {
        if (this.y || this.k == null) {
            return;
        }
        boolean z = false;
        if (this.s != null && ep.a(this.s) && ep.b() - this.t < 10000) {
            z = true;
        }
        if (z) {
            a(6, (Bundle) null, 0L);
            a(5, (Bundle) null, 0L);
        } else {
            b(7);
            a(7, (Bundle) null, 0L);
        }
    }

    public final void d(Bundle bundle) {
        b(3, bundle);
    }

    public final void e() {
        try {
            if (this.m) {
                b(8);
            }
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable unused) {
        }
    }

    public final void e(Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                String string = bundle.getString("fid");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                boolean z = true;
                boolean z2 = bundle.getBoolean("ab", true);
                if (this.g != null && !this.g.isEmpty()) {
                    Iterator<GeoFence> it = this.g.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        GeoFence next = it.next();
                        if (next.getFenceId().equals(string)) {
                            next.setAble(z2);
                            break;
                        }
                    }
                }
                if (z2) {
                    d();
                    return;
                }
                if (this.g != null && !this.g.isEmpty()) {
                    Iterator<GeoFence> it2 = this.g.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (it2.next().isAble()) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    h();
                }
            } catch (Throwable th) {
                ej.a(th, "GeoFenceManager", "doSetGeoFenceAble");
            }
        }
    }

    public final void f() {
        float fMin;
        float f;
        try {
            if (!this.y && ep.a(this.s)) {
                AMapLocation aMapLocation = this.s;
                ArrayList<GeoFence> arrayList = this.g;
                if (aMapLocation == null || aMapLocation.getErrorCode() != 0 || arrayList == null || arrayList.isEmpty()) {
                    fMin = Float.MAX_VALUE;
                } else {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    fMin = Float.MAX_VALUE;
                    for (GeoFence geoFence : arrayList) {
                        if (geoFence.isAble()) {
                            float fA = ep.a(dPoint, geoFence.getCenter());
                            if (fA > geoFence.getMinDis2Center() && fA < geoFence.getMaxDis2Center()) {
                                f = 0.0f;
                                break;
                            }
                            if (fA > geoFence.getMaxDis2Center()) {
                                fMin = Math.min(fMin, fA - geoFence.getMaxDis2Center());
                            }
                            if (fA < geoFence.getMinDis2Center()) {
                                fMin = Math.min(fMin, geoFence.getMinDis2Center() - fA);
                            }
                        }
                    }
                }
                f = fMin;
                if (f == Float.MAX_VALUE) {
                    return;
                }
                if (f < 1000.0f) {
                    b(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong("interval", 2000L);
                    a(8, bundle, 500L);
                    return;
                }
                if (f < 5000.0f) {
                    e();
                    b(7);
                    a(7, (Bundle) null, 10000L);
                } else {
                    e();
                    b(7);
                    a(7, (Bundle) null, (long) (((f - 4000.0f) / 100.0f) * 1000.0f));
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    public final void f(Bundle bundle) {
        try {
            if (this.g != null) {
                GeoFence geoFence = (GeoFence) bundle.getParcelable("fc");
                if (this.g.contains(geoFence)) {
                    this.g.remove(geoFence);
                }
                if (this.g.size() <= 0) {
                    c();
                } else {
                    d();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public final void g() {
        try {
            k();
            this.y = true;
            a(13, (Bundle) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "pauseGeoFence");
        }
    }

    public final void h() {
        try {
            b(7);
            b(8);
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "doPauseGeoFence");
        }
    }

    public final void i() {
        try {
            k();
            if (this.y) {
                this.y = false;
                d();
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "resumeGeoFence");
        }
    }

    public final boolean j() {
        return this.y;
    }
}
