package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.el.parse.Operators;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: ApsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static boolean g = false;
    public List<Messenger> B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f3766e;
    public boolean t = false;
    public boolean u = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3765a = null;
    public b b = null;
    public long v = 0;
    public long w = 0;
    public ds x = null;
    public AMapLocation c = null;
    public long y = 0;
    public int z = 0;
    public a d = null;
    public i A = null;
    public dn f = null;
    public HashMap<Messenger, Long> h = new HashMap<>();
    public en i = null;
    public long j = 0;
    public long k = 0;
    public String l = null;
    public boolean C = true;
    public String D = "";
    public AMapLocationClientOption m = null;
    public AMapLocationClientOption n = new AMapLocationClientOption();
    public ServerSocket o = null;
    public boolean p = false;
    public Socket q = null;
    public boolean r = false;
    public c s = null;
    public final int E = 5000;
    public String F = "jsonp1";

    /* JADX INFO: compiled from: ApsManager.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data;
            Messenger messenger;
            try {
                data = message.getData();
                try {
                    messenger = message.replyTo;
                    if (data != null) {
                        try {
                            if (!data.isEmpty()) {
                                String string = data.getString("c");
                                e eVar = e.this;
                                if (TextUtils.isEmpty(eVar.l)) {
                                    eVar.l = ej.b(eVar.f3766e);
                                }
                                if (!(!TextUtils.isEmpty(string) && string.equals(eVar.l))) {
                                    if (message.what == 1) {
                                        en.a((String) null, 2102);
                                        ds dsVarA = e.a("invalid handlder scode!!!#1002");
                                        dm dmVar = new dm();
                                        dmVar.f("#1002");
                                        dmVar.e("conitue");
                                        e.this.a(messenger, dsVarA, dsVarA.k(), dmVar);
                                        return;
                                    }
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            try {
                                ej.a(th, "ApsServiceCore", "ActionHandler handlerMessage");
                            } catch (Throwable th2) {
                                ej.a(th2, "actionHandler", "handleMessage");
                                return;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    messenger = null;
                }
            } catch (Throwable th4) {
                th = th4;
                data = null;
                messenger = null;
            }
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        if (data != null && !data.isEmpty()) {
                            e.this.a((Bundle) null);
                            e eVar2 = e.this;
                            try {
                                if (!eVar2.r) {
                                    c cVar = eVar2.new c();
                                    eVar2.s = cVar;
                                    cVar.start();
                                    eVar2.r = true;
                                }
                            } catch (Throwable th5) {
                                ej.a(th5, "ApsServiceCore", "startSocket");
                            }
                        }
                        return;
                    }
                    if (i == 3) {
                        if (data != null && !data.isEmpty()) {
                            e.this.a((Bundle) null);
                            e.this.b();
                        }
                        return;
                    }
                    switch (i) {
                        case 9:
                            e.this.a(data);
                            e.a(e.this);
                            break;
                        case 10:
                            e.this.a(data);
                            e.this.a(messenger, data);
                            break;
                        case 11:
                            e.this.c();
                            break;
                        case 12:
                            e.a(e.this, messenger);
                            break;
                        case 13:
                            Messenger messenger2 = message.replyTo;
                            if (messenger2 != null && e.this.B != null && !e.this.B.contains(messenger2)) {
                                e.this.B.add(messenger2);
                                if (e.this.B.size() == 1) {
                                    e.this.f();
                                }
                            }
                            break;
                        case 14:
                            Messenger messenger3 = message.replyTo;
                            if (messenger3 != null && e.this.B != null && e.this.B.contains(messenger3)) {
                                e.this.B.remove(messenger3);
                            }
                            if (e.this.B != null && e.this.B.size() == 0) {
                                e.this.f.h();
                            }
                            break;
                    }
                } else {
                    e.this.a(data);
                    e.b(e.this, messenger, data);
                }
            } else {
                e.this.a(data);
                e.a(e.this, messenger, data);
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: compiled from: ApsManager.java */
    public class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        public final void onLooperPrepared() {
            try {
                try {
                    e.this.A = new i(e.this.f3766e);
                } catch (Throwable th) {
                    ej.a(th, "APSManager$ActionThread", "init 2");
                }
                try {
                    ei.b(e.this.f3766e);
                    ei.a(e.this.f3766e);
                } catch (Throwable th2) {
                    ej.a(th2, "APSManager$ActionThread", "init 3");
                }
                e.this.f = new dn();
                super.onLooperPrepared();
            } catch (Throwable th3) {
                ej.a(th3, "APSManager$ActionThread", "onLooperPrepared");
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
                ej.a(th, "APSManager$ActionThread", "run");
            }
        }
    }

    /* JADX INFO: compiled from: ApsManager.java */
    public class c extends Thread {
        public c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                if (!e.this.p) {
                    e.this.p = true;
                    e.this.o = new ServerSocket(43689);
                }
                while (e.this.p && e.this.o != null) {
                    e.this.q = e.this.o.accept();
                    e.a(e.this, e.this.q);
                }
            } catch (Throwable th) {
                ej.a(th, "ApsServiceCore", "run");
            }
            super.run();
        }
    }

    public e(Context context) {
        this.f3766e = null;
        this.f3766e = context;
    }

    public static ds a(int i, String str) {
        try {
            ds dsVar = new ds("");
            dsVar.setErrorCode(i);
            dsVar.setLocationDetail(str);
            return dsVar;
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    public static /* synthetic */ ds a(String str) {
        return a(10, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        try {
            if (this.t) {
                return;
            }
            ej.a(this.f3766e);
            if (bundle != null) {
                this.n = ej.a(bundle.getBundle("optBundle"));
            }
            this.f.a(this.f3766e);
            this.f.a();
            a(this.n);
            this.f.b();
            this.t = true;
            this.C = true;
            this.D = "";
            if (this.B == null || this.B.size() <= 0) {
                return;
            }
            f();
        } catch (Throwable th) {
            this.C = false;
            this.D = th.getMessage();
            ej.a(th, "ApsServiceCore", "init");
        }
    }

    public static void a(Messenger messenger, int i, Bundle bundle) {
        if (messenger != null) {
            try {
                Message messageObtain = Message.obtain();
                messageObtain.setData(bundle);
                messageObtain.what = i;
                messenger.send(messageObtain);
            } catch (Throwable th) {
                ej.a(th, "ApsServiceCore", "sendMessage");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Messenger messenger, AMapLocation aMapLocation, String str, dm dmVar) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString("nb", str);
        bundle.putParcelable("statics", dmVar);
        this.h.put(messenger, Long.valueOf(ep.b()));
        a(messenger, 1, bundle);
    }

    private void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (this.f != null) {
                this.f.a(aMapLocationClientOption);
            }
            if (aMapLocationClientOption != null) {
                g = aMapLocationClientOption.isKillProcess();
                if (this.m != null && (aMapLocationClientOption.isOffset() != this.m.isOffset() || aMapLocationClientOption.isNeedAddress() != this.m.isNeedAddress() || aMapLocationClientOption.isLocationCacheEnable() != this.m.isLocationCacheEnable() || this.m.getGeoLanguage() != aMapLocationClientOption.getGeoLanguage())) {
                    this.w = 0L;
                    this.c = null;
                }
                this.m = aMapLocationClientOption;
            }
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "setExtra");
        }
    }

    public static /* synthetic */ void a(e eVar) {
        try {
            ei.c(eVar.f3766e);
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "doCallOtherSer");
        }
    }

    public static /* synthetic */ void a(e eVar, Messenger messenger) {
        eVar.h.remove(messenger);
    }

    public static /* synthetic */ void a(e eVar, Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty() || eVar.u) {
                    return;
                }
                eVar.u = true;
                try {
                    eVar.f.e();
                    if (ei.k()) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("installMockApp", true);
                        a(messenger, 9, bundle2);
                    }
                } catch (Throwable th) {
                    ej.a(th, "ApsServiceCore", "initAuth");
                }
            } catch (Throwable th2) {
                ej.a(th2, "ApsServiceCore", "doInitAuth");
            }
        }
    }

    public static /* synthetic */ void a(e eVar, Socket socket) {
        BufferedReader bufferedReader;
        if (socket == null) {
            return;
        }
        try {
            int i = ej.g;
            String str = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
            }
            try {
                eVar.a(bufferedReader);
                String strE = eVar.e();
                ej.g = i;
                try {
                    eVar.b(strE);
                } catch (Throwable th2) {
                    try {
                        ej.a(th2, "apm", "inSocetLn part2");
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th3) {
                            ej.a(th3, "apm", "inSocetLn part3");
                        }
                    } finally {
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th4) {
                            ej.a(th4, "apm", "inSocetLn part3");
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                try {
                    str = eVar.F + Operators.AND + eVar.F + "({'package':'" + eVar.f3765a + "','error_code':1,'error':'params error'})";
                    ej.a(th, "apm", "inSocetLn");
                    ej.g = i;
                    try {
                        eVar.b(str);
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th6) {
                            ej.a(th6, "apm", "inSocetLn part3");
                        }
                    } catch (Throwable th7) {
                        try {
                            ej.a(th7, "apm", "inSocetLn part2");
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th8) {
                                ej.a(th8, "apm", "inSocetLn part3");
                            }
                        } finally {
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th9) {
                                ej.a(th9, "apm", "inSocetLn part3");
                            }
                        }
                    }
                } catch (Throwable th10) {
                    ej.g = i;
                    try {
                        eVar.b(str);
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th11) {
                            th = th11;
                            ej.a(th, "apm", "inSocetLn part3");
                            throw th10;
                        }
                    } catch (Throwable th12) {
                        try {
                            ej.a(th12, "apm", "inSocetLn part2");
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th13) {
                                th = th13;
                                ej.a(th, "apm", "inSocetLn part3");
                                throw th10;
                            }
                        } finally {
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th14) {
                                ej.a(th14, "apm", "inSocetLn part3");
                            }
                        }
                    }
                    throw th10;
                }
            }
        } catch (Throwable th15) {
            ej.a(th15, "apm", "inSocetLn part4");
        }
    }

    private void a(BufferedReader bufferedReader) throws Exception {
        String[] strArrSplit;
        String[] strArrSplit2;
        String[] strArrSplit3;
        String line = bufferedReader.readLine();
        int iG = 30000;
        if (line != null && line.length() > 0 && (strArrSplit = line.split(Operators.SPACE_STR)) != null && strArrSplit.length > 1 && (strArrSplit2 = strArrSplit[1].split("\\?")) != null && strArrSplit2.length > 1 && (strArrSplit3 = strArrSplit2[1].split("&")) != null && strArrSplit3.length > 0) {
            for (String str : strArrSplit3) {
                String[] strArrSplit4 = str.split(ContainerUtils.KEY_VALUE_DELIMITER);
                if (strArrSplit4 != null && strArrSplit4.length > 1) {
                    if (RemoteMessageConst.TO.equals(strArrSplit4[0])) {
                        iG = ep.g(strArrSplit4[1]);
                    }
                    if (WXBridgeManager.METHOD_CALLBACK.equals(strArrSplit4[0])) {
                        this.F = strArrSplit4[1];
                    }
                }
            }
        }
        ej.g = iG;
    }

    private AMapLocationClientOption b(Bundle bundle) {
        AMapLocationClientOption aMapLocationClientOptionA = ej.a(bundle.getBundle("optBundle"));
        a(aMapLocationClientOptionA);
        try {
            String string = bundle.getString(cn.com.chinatelecom.account.api.a.d.f1473a);
            if (!TextUtils.isEmpty(string)) {
                n.a(string);
            }
        } catch (Throwable th) {
            try {
                ej.a(th, "APSManager", "doLocation setUmidToken");
            } catch (Throwable th2) {
                ej.a(th2, "APSManager", "parseBundle");
            }
        }
        return aMapLocationClientOptionA;
    }

    public static /* synthetic */ void b(e eVar, Messenger messenger, Bundle bundle) {
        String str;
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                dm dmVar = new dm();
                dmVar.e("conitue");
                AMapLocationClientOption aMapLocationClientOptionB = eVar.b(bundle);
                if (eVar.h.containsKey(messenger) && !aMapLocationClientOptionB.isOnceLocation()) {
                    if (ep.b() - eVar.h.get(messenger).longValue() < 800) {
                        return;
                    }
                }
                AMapLocation aMapLocationA = null;
                if (!eVar.C) {
                    eVar.x = a(9, "init error : " + eVar.D + "#0901");
                    dmVar.f("#0901");
                    eVar.a(messenger, eVar.x, eVar.x.k(), dmVar);
                    en.a((String) null, 2091);
                    return;
                }
                long jB = ep.b();
                if (ep.a(eVar.x) && jB - eVar.w < 600) {
                    eVar.a(messenger, eVar.x, eVar.x.k(), dmVar);
                    return;
                }
                dmVar.c(ep.b());
                try {
                    ds dsVarA = eVar.f.a(dmVar);
                    eVar.x = dsVarA;
                    if (dsVarA.getLocationType() != 6) {
                        eVar.x.getLocationType();
                    }
                    eVar.x = eVar.f.a(eVar.x);
                } catch (Throwable th) {
                    en.a((String) null, 2081);
                    dmVar.f("#0801");
                    eVar.x = a(8, "loc error : " + th.getMessage() + "#0801");
                    ej.a(th, "ApsServiceCore", "run part2");
                }
                if (ep.a(eVar.x)) {
                    eVar.w = ep.b();
                }
                if (eVar.x == null) {
                    eVar.x = a(8, "loc is null#0801");
                    dmVar.f("#0801");
                }
                if (eVar.x != null) {
                    String strK = eVar.x.k();
                    aMapLocationA = eVar.x.m1clone();
                    str = strK;
                } else {
                    str = null;
                }
                try {
                    if (aMapLocationClientOptionB.isLocationCacheEnable() && eVar.A != null) {
                        aMapLocationA = eVar.A.a(aMapLocationA, str, aMapLocationClientOptionB.getLastLocationLifeCycle());
                    }
                } catch (Throwable th2) {
                    ej.a(th2, "ApsServiceCore", "fixLastLocation");
                }
                eVar.a(messenger, aMapLocationA, str, dmVar);
            } catch (Throwable th3) {
                ej.a(th3, "ApsServiceCore", "doLocation");
            }
        }
    }

    private void b(String str) throws IOException {
        PrintStream printStream = new PrintStream(this.q.getOutputStream(), true, "UTF-8");
        printStream.println("HTTP/1.0 200 OK");
        printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
        printStream.println();
        printStream.println(str);
        printStream.close();
    }

    public static void d() {
        g = false;
    }

    private String e() {
        StringBuilder sb;
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (ep.e(this.f3766e)) {
            sb = new StringBuilder();
            sb.append(this.F);
            sb.append(Operators.AND);
            sb.append(this.F);
            sb.append("({'package':'");
            sb.append(this.f3765a);
            str = "','error_code':36,'error':'app is background'})";
        } else {
            ds dsVar = this.x;
            if (dsVar == null || jCurrentTimeMillis - dsVar.getTime() > 5000) {
                try {
                    this.x = this.f.a(new dm());
                } catch (Throwable th) {
                    ej.a(th, "ApsServiceCore", "getSocketLocResult");
                }
            }
            ds dsVar2 = this.x;
            if (dsVar2 == null) {
                sb = new StringBuilder();
                sb.append(this.F);
                sb.append(Operators.AND);
                sb.append(this.F);
                sb.append("({'package':'");
                sb.append(this.f3765a);
                str = "','error_code':8,'error':'unknown error'})";
            } else if (dsVar2.getErrorCode() != 0) {
                sb = new StringBuilder();
                sb.append(this.F);
                sb.append(Operators.AND);
                sb.append(this.F);
                sb.append("({'package':'");
                sb.append(this.f3765a);
                sb.append("','error_code':");
                sb.append(this.x.getErrorCode());
                sb.append(",'error':'");
                sb.append(this.x.getErrorInfo());
                str = "'})";
            } else {
                sb = new StringBuilder();
                sb.append(this.F);
                sb.append(Operators.AND);
                sb.append(this.F);
                sb.append("({'package':'");
                sb.append(this.f3765a);
                sb.append("','error_code':0,'error':'','location':{'y':");
                sb.append(this.x.getLatitude());
                sb.append(",'precision':");
                sb.append(this.x.getAccuracy());
                sb.append(",'x':");
                sb.append(this.x.getLongitude());
                str = "},'version_code':'5.2.0','version':'5.2.0'})";
            }
        }
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.f == null || this.f == null) {
                return;
            }
            this.f.a(this.d);
            this.f.g();
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "startColl");
        }
    }

    public final void a() {
        try {
            this.i = new en();
            b bVar = new b("amapLocCoreThread");
            this.b = bVar;
            bVar.setPriority(5);
            this.b.start();
            this.d = new a(this.b.getLooper());
            this.B = new ArrayList();
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final void a(Messenger messenger, Bundle bundle) {
        float fA;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && ei.h()) {
                    double d = bundle.getDouble(com.umeng.analytics.pro.f.C);
                    double d2 = bundle.getDouble("lon");
                    b(bundle);
                    if (this.c != null) {
                        fA = ep.a(new double[]{d, d2, this.c.getLatitude(), this.c.getLongitude()});
                        if (fA < ei.i() * 3) {
                            Bundle bundle2 = new Bundle();
                            bundle2.setClassLoader(AMapLocation.class.getClassLoader());
                            bundle2.putInt("I_MAX_GEO_DIS", ei.i() * 3);
                            bundle2.putInt("I_MIN_GEO_DIS", ei.i());
                            bundle2.putParcelable("loc", this.c);
                            a(messenger, 6, bundle2);
                        }
                    } else {
                        fA = -1.0f;
                    }
                    if (fA == -1.0f || fA > ei.i()) {
                        a(bundle);
                        this.c = this.f.a(d, d2);
                    }
                }
            } catch (Throwable th) {
                ej.a(th, "ApsServiceCore", "doLocationGeo");
            }
        }
    }

    public final void b() {
        try {
            if (this.q != null) {
                this.q.close();
            }
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "doStopScocket 1");
        }
        try {
            if (this.o != null) {
                this.o.close();
            }
        } catch (Throwable th2) {
            ej.a(th2, "ApsServiceCore", "doStopScocket 2");
        }
        try {
            if (this.s != null) {
                this.s.interrupt();
            }
        } catch (Throwable unused) {
        }
        this.s = null;
        this.o = null;
        this.p = false;
        this.r = false;
    }

    public final void c() {
        b bVar;
        try {
            if (this.h != null) {
                this.h.clear();
                this.h = null;
            }
            try {
                if (this.B != null) {
                    this.B.clear();
                }
            } catch (Throwable th) {
                ej.a(th, "apm", "des1");
            }
            if (this.d != null) {
                this.d.removeCallbacksAndMessages(null);
            }
            if (this.b != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    try {
                        em.a(this.b, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                    } catch (Throwable unused) {
                        bVar = this.b;
                        bVar.quit();
                    }
                } else {
                    bVar = this.b;
                }
                bVar.quit();
            }
            this.b = null;
            this.d = null;
            if (this.A != null) {
                this.A.c();
                this.A = null;
            }
            b();
            this.t = false;
            this.u = false;
            this.f.d();
            if (this.i != null && this.j != 0 && this.k != 0) {
                long jB = ep.b() - this.j;
                en.a(this.f3766e, this.i.c(this.f3766e), this.i.d(this.f3766e), this.k, jB);
                this.i.e(this.f3766e);
            }
            en.a(this.f3766e);
            ab.b();
            if (g) {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable th2) {
            ej.a(th2, "apm", "tdest");
        }
    }
}
