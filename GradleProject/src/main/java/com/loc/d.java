package com.loc;

import android.app.Application;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.amap.api.location.APSService;
import com.amap.api.location.UmidtokenInfo;
import com.tencent.liteav.TXLiteAVCode;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: AmapLocationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static boolean D = true;
    public static boolean F = false;
    public static volatile boolean f = false;
    public Context A;
    public c b;
    public g c;
    public i h;
    public Intent k;
    public b m;
    public en q;
    public a x;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AMapLocationClientOption f3713a = new AMapLocationClientOption();
    public boolean B = false;
    public volatile boolean C = false;
    public ArrayList<AMapLocationListener> d = new ArrayList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3714e = false;
    public boolean g = true;
    public Messenger i = null;
    public Messenger j = null;
    public int l = 0;
    public boolean E = true;
    public boolean n = false;
    public AMapLocationClientOption.AMapLocationMode o = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    public Object p = new Object();
    public boolean r = false;
    public e s = null;
    public h G = null;
    public String t = null;
    public ServiceConnection H = new ServiceConnection() { // from class: com.loc.d.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                d.this.i = new Messenger(iBinder);
                d.this.B = true;
                d.this.r = true;
            } catch (Throwable th) {
                ej.a(th, "ALManager", "onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            d dVar = d.this;
            dVar.i = null;
            dVar.B = false;
        }
    };
    public AMapLocationQualityReport u = null;
    public boolean v = false;
    public boolean w = false;
    public volatile boolean I = false;
    public String y = null;
    public boolean z = false;

    /* JADX INFO: renamed from: com.loc.d$2, reason: invalid class name */
    /* JADX INFO: compiled from: AmapLocationManager.java */
    public static /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3716a;

        static {
            int[] iArr = new int[AMapLocationClientOption.AMapLocationMode.values().length];
            f3716a = iArr;
            try {
                iArr[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3716a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3716a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: AmapLocationManager.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v9 */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            ?? r0 = 0;
            try {
                super.handleMessage(message);
                int i = message.what;
                if (i == 11) {
                    d.this.a(message.getData());
                    return;
                }
                if (i == 12) {
                    d.a(d.this, message);
                    return;
                }
                if (i == 1008) {
                    d.h(d.this);
                    return;
                }
                if (i == 1009) {
                    d.i(d.this);
                    return;
                }
                if (i == 1011) {
                    d.this.a(14, (Bundle) null);
                    d.this.h();
                }
                try {
                    switch (i) {
                        case 1002:
                            d.a(d.this, (AMapLocationListener) message.obj);
                            break;
                        case 1003:
                            d.this.j();
                            d.this.a(13, (Bundle) null);
                            break;
                        case 1004:
                            d.this.k();
                            d.this.a(14, (Bundle) null);
                            break;
                        case 1005:
                            d.b(d.this, (AMapLocationListener) message.obj);
                            break;
                        default:
                            switch (i) {
                                case 1014:
                                    d.b(d.this, message);
                                    break;
                                case 1015:
                                    d.this.c.a(d.this.f3713a);
                                    d.this.a(TXLiteAVCode.EVT_CAMERA_CLOSE, (Object) null, 300000L);
                                    break;
                                case 1016:
                                    if (!d.this.c.b()) {
                                        d.e(d.this);
                                    } else {
                                        d.this.a(1016, (Object) null, 1000L);
                                    }
                                    break;
                                case com.heytap.mcssdk.f.e.d /* 1017 */:
                                    d.this.c.a();
                                    d.this.a(TXLiteAVCode.EVT_CAMERA_CLOSE);
                                    break;
                                case 1018:
                                    d.this.f3713a = (AMapLocationClientOption) message.obj;
                                    if (d.this.f3713a != null) {
                                        d.g(d.this);
                                    }
                                    break;
                                default:
                                    switch (i) {
                                        case TXLiteAVCode.EVT_CAMERA_REMOVED /* 1023 */:
                                            d.c(d.this, message);
                                            break;
                                        case 1024:
                                            d.d(d.this, message);
                                            break;
                                        case TXLiteAVCode.EVT_CAMERA_CLOSE /* 1025 */:
                                            g gVar = d.this.c;
                                            boolean z = false;
                                            if (gVar.c != null && !gVar.c.isOnceLocation() && ep.b() - gVar.d > 300000) {
                                                z = true;
                                            }
                                            if (z) {
                                                d.this.c.a();
                                                d.this.c.a(d.this.f3713a);
                                            }
                                            d.this.a(TXLiteAVCode.EVT_CAMERA_CLOSE, (Object) null, 300000L);
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } catch (Throwable th) {
                    r0 = message;
                    th = th;
                    if (r0 == 0) {
                        r0 = "handleMessage";
                    }
                    ej.a(th, "AMapLocationManage$MHandlerr", r0);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: compiled from: AmapLocationManager.java */
    public static class b extends HandlerThread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f3718a;

        public b(String str, d dVar) {
            super(str);
            this.f3718a = null;
            this.f3718a = dVar;
        }

        @Override // android.os.HandlerThread
        public final void onLooperPrepared() {
            try {
                this.f3718a.h.a();
                this.f3718a.m();
                dw.j();
                if (this.f3718a != null && this.f3718a.A != null) {
                    ei.b(this.f3718a.A);
                    ei.a(this.f3718a.A);
                }
                super.onLooperPrepared();
            } catch (Throwable unused) {
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: compiled from: AmapLocationManager.java */
    public class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String str = null;
            try {
                super.handleMessage(message);
                if (d.this.n) {
                    return;
                }
                int i = message.what;
                if (i == 1) {
                    Message messageObtainMessage = d.this.x.obtainMessage();
                    messageObtainMessage.what = 11;
                    messageObtainMessage.setData(message.getData());
                    d.this.x.sendMessage(messageObtainMessage);
                    return;
                }
                if (i != 2) {
                    switch (i) {
                        case 5:
                            Bundle data = message.getData();
                            data.putBundle("optBundle", ej.a(d.this.f3713a));
                            d.this.a(10, data);
                            break;
                        case 6:
                            str = "handleMessage RESULT_GPS_GEO_SUCCESS";
                            Bundle data2 = message.getData();
                            if (d.this.c != null) {
                                g gVar = d.this.c;
                                if (data2 != null) {
                                    try {
                                        data2.setClassLoader(AMapLocation.class.getClassLoader());
                                        gVar.g = data2.getInt("I_MAX_GEO_DIS");
                                        gVar.h = data2.getInt("I_MIN_GEO_DIS");
                                        AMapLocation aMapLocation = (AMapLocation) data2.getParcelable("loc");
                                        if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                            synchronized (gVar.o) {
                                                g.y = aMapLocation;
                                            }
                                        }
                                    } catch (Throwable th) {
                                        ej.a(th, "GpsLocation", "setLastGeoLocation");
                                        return;
                                    }
                                }
                            }
                            break;
                        case 7:
                            Bundle data3 = message.getData();
                            d.this.E = data3.getBoolean("ngpsAble");
                            break;
                        case 8:
                            en.a((String) null, 2141);
                            break;
                        case 9:
                            boolean unused = d.F = message.getData().getBoolean("installMockApp");
                            break;
                        case 10:
                            d.a(d.this, (AMapLocation) message.obj);
                            break;
                    }
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = message.obj;
                d.this.x.sendMessage(messageObtain);
            } catch (Throwable th2) {
                if (str == null) {
                    str = "handleMessage";
                }
                ej.a(th2, "AmapLocationManager$MainHandler", str);
            }
        }
    }

    public d(Context context, Intent intent, Looper looper) {
        this.c = null;
        this.k = null;
        this.m = null;
        this.q = null;
        this.x = null;
        this.A = context;
        this.k = intent;
        try {
            this.b = looper == null ? Looper.myLooper() == null ? new c(this.A.getMainLooper()) : new c() : new c(looper);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "init 1");
        }
        try {
            try {
                this.h = new i(this.A);
            } catch (Throwable th2) {
                ej.a(th2, "ALManager", "init 2");
            }
            b bVar = new b("amapLocManagerThread", this);
            this.m = bVar;
            bVar.setPriority(5);
            this.m.start();
            this.x = a(this.m.getLooper());
        } catch (Throwable th3) {
            ej.a(th3, "ALManager", "init 5");
        }
        try {
            this.c = new g(this.A, this.b);
        } catch (Throwable th4) {
            ej.a(th4, "ALManager", "init 3");
        }
        if (this.q == null) {
            this.q = new en();
        }
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.p) {
            aVar = new a(looper);
            this.x = aVar;
        }
        return aVar;
    }

    private ds a(dn dnVar) {
        if (!this.f3713a.isLocationCacheEnable()) {
            return null;
        }
        try {
            return dnVar.f();
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doFirstCacheLoc");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (this.p) {
            if (this.x != null) {
                this.x.removeMessages(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                boolean z = (th instanceof IllegalStateException) && th.getMessage().contains("sending message to a Handler on a dead thread");
                if ((th instanceof RemoteException) || z) {
                    this.i = null;
                    this.B = false;
                }
                ej.a(th, "ALManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = ej.b(this.A);
        }
        bundle.putString("c", this.t);
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.setData(bundle);
        messageObtain.replyTo = this.j;
        if (this.i != null) {
            this.i.send(messageObtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj, long j) {
        synchronized (this.p) {
            if (this.x != null) {
                Message messageObtain = Message.obtain();
                messageObtain.what = i;
                if (obj instanceof Bundle) {
                    messageObtain.setData((Bundle) obj);
                } else {
                    messageObtain.obj = obj;
                }
                this.x.sendMessageDelayed(messageObtain, j);
            }
        }
    }

    private void a(Intent intent, boolean z) {
        if (this.A != null) {
            if (Build.VERSION.SDK_INT < 26 || !z) {
                this.A.startService(intent);
            } else if (!o()) {
                Log.e("amapapi", "-------------调用后台定位服务，缺少权限：android.permission.FOREGROUND_SERVICE--------------");
                return;
            } else {
                try {
                    this.A.getClass().getMethod("startForegroundService", Intent.class).invoke(this.A, intent);
                } catch (Throwable unused) {
                    this.A.startService(intent);
                }
            }
            this.z = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        dm dmVar;
        AMapLocation aMapLocation;
        AMapLocation aMapLocationA = null;
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                this.y = bundle.getString("nb");
                dmVar = (dm) bundle.getParcelable("statics");
                if (aMapLocation != null) {
                    try {
                        if (aMapLocation.getErrorCode() == 0 && this.c != null) {
                            this.c.w = 0;
                            if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                g.y = aMapLocation;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        ej.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                dmVar = null;
                ej.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
            }
        } else {
            dmVar = null;
            aMapLocation = null;
        }
        aMapLocationA = this.c != null ? this.c.a(aMapLocation, this.y) : aMapLocation;
        a(aMapLocationA, dmVar);
    }

    private synchronized void a(AMapLocation aMapLocation, dm dmVar) {
        if (aMapLocation == null) {
            try {
                aMapLocation = new AMapLocation("");
                aMapLocation.setErrorCode(8);
                aMapLocation.setLocationDetail("amapLocation is null#0801");
            } catch (Throwable th) {
                ej.a(th, "ALManager", "handlerLocation part3");
                return;
            }
        }
        if (!"gps".equalsIgnoreCase(aMapLocation.getProvider())) {
            aMapLocation.setProvider("lbs");
        }
        if (this.u == null) {
            this.u = new AMapLocationQualityReport();
        }
        this.u.setLocationMode(this.f3713a.getLocationMode());
        if (this.c != null) {
            this.u.setGPSSatellites(this.c.d());
            this.u.setGpsStatus(this.c.c());
        }
        this.u.setWifiAble(ep.h(this.A));
        this.u.setNetworkType(ep.i(this.A));
        if (aMapLocation.getLocationType() == 1 || "gps".equalsIgnoreCase(aMapLocation.getProvider())) {
            this.u.setNetUseTime(0L);
        }
        if (dmVar != null) {
            this.u.setNetUseTime(dmVar.a());
        }
        this.u.setInstallHighDangerMockApp(F);
        aMapLocation.setLocationQualityReport(this.u);
        try {
            if (this.C) {
                String str = this.y;
                Bundle bundle = new Bundle();
                bundle.putParcelable("loc", aMapLocation);
                bundle.putString("lastLocNb", str);
                a(1014, bundle, 0L);
                if (dmVar != null) {
                    dmVar.d(ep.b());
                }
                en.a(this.A, aMapLocation, dmVar);
                en.a(this.A, aMapLocation);
                AMapLocation aMapLocationM1clone = aMapLocation.m1clone();
                Message messageObtainMessage = this.b.obtainMessage();
                messageObtainMessage.what = 10;
                messageObtainMessage.obj = aMapLocationM1clone;
                this.b.sendMessage(messageObtainMessage);
            }
        } catch (Throwable th2) {
            ej.a(th2, "ALManager", "handlerLocation part2");
        }
        if (this.n) {
            return;
        }
        if (this.f3713a.isOnceLocation()) {
            k();
            a(14, (Bundle) null);
        }
    }

    public static /* synthetic */ void a(d dVar, Message message) {
        try {
            AMapLocation aMapLocation = (AMapLocation) message.obj;
            if (dVar.g && dVar.i != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", ej.a(dVar.f3713a));
                dVar.a(0, bundle);
                if (dVar.C) {
                    dVar.a(13, (Bundle) null);
                }
                dVar.g = false;
            }
            dVar.a(aMapLocation, (dm) null);
            dVar.a(TXLiteAVCode.EVT_CAMERA_CLOSE);
            dVar.a(TXLiteAVCode.EVT_CAMERA_CLOSE, (Object) null, 300000L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "resultGpsLocationSuccess");
        }
    }

    public static /* synthetic */ void a(d dVar, AMapLocation aMapLocation) {
        try {
            if (aMapLocation.getErrorCode() != 0) {
                aMapLocation.setLocationType(0);
            }
            if (aMapLocation.getErrorCode() == 0) {
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                if ((latitude == 0.0d && longitude == 0.0d) || latitude < -90.0d || latitude > 90.0d || longitude < -180.0d || longitude > 180.0d) {
                    en.a("errorLatLng", aMapLocation.toStr());
                    aMapLocation.setLocationType(0);
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("LatLng is error#0802");
                }
            }
            if ("gps".equalsIgnoreCase(aMapLocation.getProvider()) || !dVar.c.b()) {
                aMapLocation.setAltitude(ep.c(aMapLocation.getAltitude()));
                aMapLocation.setBearing(ep.a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(ep.a(aMapLocation.getSpeed()));
                Iterator<AMapLocationListener> it = dVar.d.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(aMapLocation);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
    }

    public static /* synthetic */ void a(d dVar, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (dVar.d == null) {
            dVar.d = new ArrayList<>();
        }
        if (dVar.d.contains(aMapLocationListener)) {
            return;
        }
        dVar.d.add(aMapLocationListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ds b(dn dnVar) {
        boolean z;
        String strK;
        dm dmVar = new dm();
        AMapLocation aMapLocationA = null;
        try {
            dmVar.c(ep.b());
            try {
                String apikey = AMapLocationClientOption.getAPIKEY();
                if (!TextUtils.isEmpty(apikey)) {
                    l.a(this.A, apikey);
                }
            } catch (Throwable th) {
                ej.a(th, "ALManager", "apsLocation setAuthKey");
            }
            try {
                String umidtoken = UmidtokenInfo.getUmidtoken();
                if (!TextUtils.isEmpty(umidtoken)) {
                    n.a(umidtoken);
                }
            } catch (Throwable th2) {
                ej.a(th2, "ALManager", "apsLocation setUmidToken");
            }
            try {
                dnVar.a(this.A);
                dnVar.a(this.f3713a);
                dnVar.b(dmVar);
            } catch (Throwable th3) {
                ej.a(th3, "ALManager", "initApsBase");
            }
            boolean zL = ei.l();
            ds dsVarA = a(dnVar);
            if (dsVarA == null) {
                try {
                    try {
                        dsVarA = dnVar.a(!zL, dmVar);
                        if (dsVarA != null) {
                            try {
                                if (dsVarA.getErrorCode() == 0) {
                                    dnVar.b(dsVarA);
                                }
                            } catch (Throwable th4) {
                                ej.a(th4, "ALManager", "apsLocation:doFirstAddCache");
                            }
                        }
                    } catch (Throwable th5) {
                        ej.a(th5, "ALManager", "apsLocation:doFirstNetLocate");
                    }
                    z = true;
                } catch (Throwable th6) {
                    th = th6;
                    aMapLocationA = dsVarA;
                    try {
                        ej.a(th, "ALManager", "apsLocation");
                        return aMapLocationA;
                    } finally {
                        try {
                            dnVar.d();
                        } catch (Throwable unused) {
                        }
                    }
                }
            } else {
                z = false;
            }
            if (dsVarA != null) {
                strK = dsVarA.k();
                aMapLocationA = dsVarA.m1clone();
            } else {
                strK = null;
            }
            try {
                if (this.f3713a.isLocationCacheEnable() && this.h != null) {
                    aMapLocationA = this.h.a(aMapLocationA, strK, this.f3713a.getLastLocationLifeCycle());
                }
            } catch (Throwable th7) {
                ej.a(th7, "ALManager", "fixLastLocation");
            }
            try {
                Bundle bundle = new Bundle();
                if (aMapLocationA != null) {
                    bundle.putParcelable("loc", aMapLocationA);
                    bundle.putString("nb", dsVarA.k());
                    bundle.putParcelable("statics", dmVar);
                }
                a(bundle);
            } catch (Throwable th8) {
                ej.a(th8, "ALManager", "apsLocation:callback");
            }
            if (z && zL && !f) {
                f = true;
                try {
                    dnVar.c();
                    dnVar.a(new AMapLocationClientOption().setNeedAddress(false));
                    dnVar.a(true, new dm());
                } catch (Throwable th9) {
                    ej.a(th9, "ALManager", "apsLocation:doFirstNetLocate 2");
                }
            }
            try {
                dnVar.d();
                return dsVarA;
            } catch (Throwable unused2) {
                return dsVarA;
            }
        } catch (Throwable th10) {
            th = th10;
        }
    }

    public static /* synthetic */ void b(d dVar, Message message) {
        try {
            Bundle data = message.getData();
            AMapLocation aMapLocation = (AMapLocation) data.getParcelable("loc");
            String string = data.getString("lastLocNb");
            if (aMapLocation != null) {
                AMapLocation aMapLocationA = null;
                try {
                    if (i.b != null) {
                        aMapLocationA = i.b.a();
                    } else if (dVar.h != null) {
                        aMapLocationA = dVar.h.b();
                    }
                    en.a(aMapLocationA, aMapLocation);
                } catch (Throwable unused) {
                }
            }
            if (dVar.h.a(aMapLocation, string)) {
                dVar.h.d();
            }
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doSaveLastLocation");
        }
    }

    public static /* synthetic */ void b(d dVar, AMapLocationListener aMapLocationListener) {
        if (!dVar.d.isEmpty() && dVar.d.contains(aMapLocationListener)) {
            dVar.d.remove(aMapLocationListener);
        }
        if (dVar.d.isEmpty()) {
            dVar.k();
        }
    }

    public static /* synthetic */ void c(d dVar, Message message) {
        if (message == null) {
            return;
        }
        try {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            int i = data.getInt("i", 0);
            Notification notification = (Notification) data.getParcelable("h");
            Intent intentN = dVar.n();
            intentN.putExtra("i", i);
            intentN.putExtra("h", notification);
            intentN.putExtra(z.f, 1);
            dVar.a(intentN, true);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doEnableBackgroundLocation");
        }
    }

    public static /* synthetic */ void d(d dVar, Message message) {
        if (message == null) {
            return;
        }
        try {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            boolean z = data.getBoolean(z.j, true);
            Intent intentN = dVar.n();
            intentN.putExtra(z.j, z);
            intentN.putExtra(z.f, 2);
            dVar.a(intentN, false);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doDisableBackgroundLocation");
        }
    }

    public static /* synthetic */ void e(d dVar) {
        try {
            if (D || !(dVar.r || dVar.I)) {
                D = false;
                dVar.I = true;
                ds dsVarB = dVar.b(new dn());
                if (dVar.i()) {
                    Bundle bundle = new Bundle();
                    String str = "0";
                    if (dsVarB != null && (dsVarB.getLocationType() == 2 || dsVarB.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putBundle("optBundle", ej.a(dVar.f3713a));
                    bundle.putString("isCacheLoc", str);
                    dVar.a(0, bundle);
                    if (dVar.C) {
                        dVar.a(13, (Bundle) null);
                    }
                }
            } else {
                try {
                    if (dVar.r && !dVar.B && !dVar.w) {
                        dVar.w = true;
                        dVar.m();
                    }
                } catch (Throwable th) {
                    dVar.w = true;
                    ej.a(th, "ALManager", "doLBSLocation reStartService");
                }
                if (dVar.i()) {
                    dVar.w = false;
                    Bundle bundle2 = new Bundle();
                    bundle2.putBundle("optBundle", ej.a(dVar.f3713a));
                    bundle2.putString(cn.com.chinatelecom.account.api.a.d.f1473a, UmidtokenInfo.getUmidtoken());
                    if (!dVar.c.b()) {
                        dVar.a(1, bundle2);
                    }
                }
            }
        } catch (Throwable th2) {
            try {
                ej.a(th2, "ALManager", "doLBSLocation");
                try {
                    if (dVar.f3713a.isOnceLocation()) {
                        return;
                    }
                    dVar.l();
                } catch (Throwable unused) {
                }
            } finally {
                try {
                    if (!dVar.f3713a.isOnceLocation()) {
                        dVar.l();
                    }
                } catch (Throwable unused2) {
                }
            }
        }
    }

    public static /* synthetic */ void g(d dVar) {
        en enVar;
        Context context;
        int i;
        Handler handler;
        g gVar = dVar.c;
        AMapLocationClientOption aMapLocationClientOption = dVar.f3713a;
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        gVar.c = aMapLocationClientOption;
        if (aMapLocationClientOption.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors && (handler = gVar.f3796a) != null) {
            handler.removeMessages(8);
        }
        if (gVar.r != gVar.c.getGeoLanguage()) {
            synchronized (gVar.o) {
                g.y = null;
            }
        }
        gVar.r = gVar.c.getGeoLanguage();
        if (dVar.C && !dVar.f3713a.getLocationMode().equals(dVar.o)) {
            dVar.k();
            dVar.j();
        }
        dVar.o = dVar.f3713a.getLocationMode();
        if (dVar.q != null) {
            if (dVar.f3713a.isOnceLocation()) {
                enVar = dVar.q;
                context = dVar.A;
                i = 0;
            } else {
                enVar = dVar.q;
                context = dVar.A;
                i = 1;
            }
            enVar.a(context, i);
            dVar.q.a(dVar.A, dVar.f3713a);
        }
    }

    public static /* synthetic */ void h(d dVar) {
        try {
            if (dVar.i != null) {
                dVar.l = 0;
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", ej.a(dVar.f3713a));
                dVar.a(2, bundle);
                return;
            }
            int i = dVar.l + 1;
            dVar.l = i;
            if (i < 10) {
                dVar.a(1008, (Object) null, 50L);
            }
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startAssistantLocationImpl");
        }
    }

    public static /* synthetic */ void i(d dVar) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle("optBundle", ej.a(dVar.f3713a));
            dVar.a(3, bundle);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopAssistantLocationImpl");
        }
    }

    private boolean i() {
        boolean z = false;
        int i = 0;
        while (this.i == null) {
            try {
                Thread.sleep(100L);
                i++;
                if (i >= 50) {
                    break;
                }
            } catch (Throwable th) {
                ej.a(th, "ALManager", "checkAPSManager");
            }
        }
        if (this.i == null) {
            Message messageObtain = Message.obtain();
            Bundle bundle = new Bundle();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setErrorCode(10);
            aMapLocation.setLocationDetail(!ep.l(this.A.getApplicationContext()) ? "请检查配置文件是否配置服务，并且manifest中service标签是否配置在application标签内#1003" : "启动ApsServcie失败#1001");
            bundle.putParcelable("loc", aMapLocation);
            messageObtain.setData(bundle);
            messageObtain.what = 1;
            this.b.sendMessage(messageObtain);
        } else {
            z = true;
        }
        if (!z) {
            en.a((String) null, !ep.l(this.A.getApplicationContext()) ? 2103 : 2101);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        if (this.f3713a == null) {
            this.f3713a = new AMapLocationClientOption();
        }
        if (this.C) {
            return;
        }
        this.C = true;
        int i = AnonymousClass2.f3716a[this.f3713a.getLocationMode().ordinal()];
        long gpsFirstTimeout = 0;
        if (i == 1) {
            a(com.heytap.mcssdk.f.e.d, (Object) null, 0L);
            a(1016, (Object) null, 0L);
        } else {
            if (i == 2) {
                a(1016);
                a(1015, (Object) null, 0L);
                return;
            }
            if (i == 3) {
                a(1015, (Object) null, 0L);
                if (this.f3713a.isGpsFirst() && this.f3713a.isOnceLocation()) {
                    gpsFirstTimeout = this.f3713a.getGpsFirstTimeout();
                }
                a(1016, (Object) null, gpsFirstTimeout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            a(TXLiteAVCode.EVT_CAMERA_CLOSE);
            if (this.c != null) {
                this.c.a();
            }
            a(1016);
            this.C = false;
            this.l = 0;
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopLocation");
        }
    }

    private void l() {
        if (this.f3713a.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
            a(1016, (Object) null, this.f3713a.getInterval() >= 1000 ? this.f3713a.getInterval() : 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            if (this.j == null) {
                this.j = new Messenger(this.b);
            }
            try {
                this.A.bindService(n(), this.H, 1);
            } catch (Throwable th) {
                ej.a(th, "ALManager", "startServiceImpl");
            }
        } catch (Throwable unused) {
        }
    }

    private Intent n() {
        String apikey;
        if (this.k == null) {
            this.k = new Intent(this.A, (Class<?>) APSService.class);
        }
        try {
            apikey = !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : k.f(this.A);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startServiceImpl p2");
            apikey = "";
        }
        this.k.putExtra("a", apikey);
        this.k.putExtra("b", k.c(this.A));
        this.k.putExtra(cn.com.chinatelecom.account.api.a.d.f1473a, UmidtokenInfo.getUmidtoken());
        return this.k;
    }

    private boolean o() {
        if (ep.k(this.A)) {
            int iB = -1;
            try {
                iB = em.b(((Application) this.A.getApplicationContext()).getBaseContext(), "checkSelfPermission", "android.permission.FOREGROUND_SERVICE");
            } catch (Throwable unused) {
            }
            if (iB != 0) {
                return false;
            }
        }
        return true;
    }

    public final void a(int i, Notification notification) {
        if (i == 0 || notification == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("i", i);
            bundle.putParcelable("h", notification);
            a(TXLiteAVCode.EVT_CAMERA_REMOVED, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "disableBackgroundLocation");
        }
    }

    public final void a(WebView webView) {
        if (this.G == null) {
            this.G = new h(this.A, webView);
        }
        this.G.a();
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            a(1018, aMapLocationClientOption.m2clone(), 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "setLocationOption");
        }
    }

    public final void a(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, aMapLocationListener, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "setLocationListener");
        }
    }

    public final void a(boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean(z.j, z);
            a(1024, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "disableBackgroundLocation");
        }
    }

    public final boolean a() {
        return this.B;
    }

    public final void b() {
        try {
            a(1003, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startLocation");
        }
    }

    public final void b(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, aMapLocationListener, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "unRegisterLocationListener");
        }
    }

    public final void c() {
        try {
            a(1004, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopLocation");
        }
    }

    public final void d() {
        try {
            if (this.G != null) {
                this.G.b();
                this.G = null;
            }
            a(1011, (Object) null, 0L);
            this.n = true;
        } catch (Throwable th) {
            ej.a(th, "ALManager", "onDestroy");
        }
    }

    public final AMapLocation e() {
        AMapLocation aMapLocationB = null;
        try {
            if (this.h != null && (aMapLocationB = this.h.b()) != null) {
                aMapLocationB.setTrustedLevel(3);
            }
        } catch (Throwable th) {
            ej.a(th, "ALManager", "getLastKnownLocation");
        }
        return aMapLocationB;
    }

    public final void f() {
        try {
            a(1008, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startAssistantLocation");
        }
    }

    public final void g() {
        try {
            if (this.G != null) {
                this.G.b();
                this.G = null;
            }
            a(1009, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopAssistantLocation");
        }
    }

    public final void h() {
        a(12, (Bundle) null);
        this.g = true;
        this.B = false;
        this.r = false;
        k();
        en enVar = this.q;
        if (enVar != null) {
            enVar.b(this.A);
        }
        en.a(this.A);
        e eVar = this.s;
        if (eVar != null) {
            eVar.d.sendEmptyMessage(11);
        } else {
            ServiceConnection serviceConnection = this.H;
            if (serviceConnection != null) {
                this.A.unbindService(serviceConnection);
            }
        }
        try {
            if (this.z) {
                this.A.stopService(n());
            }
        } catch (Throwable unused) {
        }
        this.z = false;
        ArrayList<AMapLocationListener> arrayList = this.d;
        if (arrayList != null) {
            arrayList.clear();
            this.d = null;
        }
        this.H = null;
        synchronized (this.p) {
            if (this.x != null) {
                this.x.removeCallbacksAndMessages(null);
            }
            this.x = null;
        }
        b bVar = this.m;
        if (bVar != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    em.a(bVar, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable unused2) {
                    this.m.quit();
                }
            } else {
                bVar.quit();
            }
        }
        this.m = null;
        c cVar = this.b;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        i iVar = this.h;
        if (iVar != null) {
            iVar.c();
            this.h = null;
        }
    }
}
