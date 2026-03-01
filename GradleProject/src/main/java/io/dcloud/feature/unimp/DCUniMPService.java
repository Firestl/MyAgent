package io.dcloud.feature.unimp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.sdk.IDCUniMPCallBack;
import io.dcloud.feature.sdk.IDCUniMPServer;
import io.src.dcloud.adapter.DCloudBaseService;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPService extends DCloudBaseService {
    public static String sHostServiceName;
    public static String sProcessName;
    public IDCUniMPCallBack b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f6674a = null;
    public ServiceConnection c = new a();
    public Binder d = new IDCUniMPServer.Stub() { // from class: io.dcloud.feature.unimp.DCUniMPService.2
        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void callBack(String str, Bundle bundle) throws RemoteException {
            str.hashCode();
            if (!str.equals("bindHostService")) {
                DCUniMPService.this.b().a(str, bundle);
            } else {
                DCUniMPService dCUniMPService = DCUniMPService.this;
                dCUniMPService.a(dCUniMPService.getBaseContext(), DCUniMPService.sHostServiceName, DCUniMPService.this.c);
            }
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public boolean closeCurrentApp() throws RemoteException {
            return DCUniMPService.this.b().a(DCUniMPService.this.b().b(), 1);
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String execute(java.lang.String r7, android.os.Bundle r8) throws android.os.RemoteException {
            /*
                r6 = this;
                int r0 = r7.hashCode()     // Catch: java.lang.Exception -> La7
                r1 = 5
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                switch(r0) {
                    case -953952837: goto L3f;
                    case -524413869: goto L35;
                    case -353716881: goto L2b;
                    case 912590207: goto L21;
                    case 1069972512: goto L17;
                    case 2006282603: goto Ld;
                    default: goto Lc;
                }     // Catch: java.lang.Exception -> La7
            Lc:
                goto L49
            Ld:
                java.lang.String r0 = "uniMPEventToJS"
                boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> La7
                if (r7 == 0) goto L49
                r7 = 1
                goto L4a
            L17:
                java.lang.String r0 = "startActivityForUniMPTask"
                boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> La7
                if (r7 == 0) goto L49
                r7 = 5
                goto L4a
            L21:
                java.lang.String r0 = "hideApp"
                boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> La7
                if (r7 == 0) goto L49
                r7 = 3
                goto L4a
            L2b:
                java.lang.String r0 = "sendUniMPEvent"
                boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> La7
                if (r7 == 0) goto L49
                r7 = 2
                goto L4a
            L35:
                java.lang.String r0 = "setCapsubeClick"
                boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> La7
                if (r7 == 0) goto L49
                r7 = 4
                goto L4a
            L3f:
                java.lang.String r0 = "getAppVersionInfo"
                boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> La7
                if (r7 == 0) goto L49
                r7 = 0
                goto L4a
            L49:
                r7 = -1
            L4a:
                if (r7 == 0) goto L90
                if (r7 == r5) goto L85
                if (r7 == r4) goto L7a
                if (r7 == r3) goto L70
                if (r7 == r2) goto L66
                if (r7 == r1) goto L57
                goto La7
            L57:
                io.dcloud.feature.unimp.DCUniMPService r7 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.d r7 = io.dcloud.feature.unimp.DCUniMPService.b(r7)     // Catch: java.lang.Exception -> La7
                boolean r7 = r7.d(r8)     // Catch: java.lang.Exception -> La7
                java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Exception -> La7
                return r7
            L66:
                io.dcloud.feature.unimp.DCUniMPService r7 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.d r7 = io.dcloud.feature.unimp.DCUniMPService.b(r7)     // Catch: java.lang.Exception -> La7
                r7.c(r8)     // Catch: java.lang.Exception -> La7
                goto La7
            L70:
                io.dcloud.feature.unimp.DCUniMPService r7 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.d r7 = io.dcloud.feature.unimp.DCUniMPService.b(r7)     // Catch: java.lang.Exception -> La7
                r7.a(r8)     // Catch: java.lang.Exception -> La7
                goto La7
            L7a:
                io.dcloud.feature.unimp.DCUniMPService r7 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.d r7 = io.dcloud.feature.unimp.DCUniMPService.b(r7)     // Catch: java.lang.Exception -> La7
                java.lang.String r7 = r7.b(r8)     // Catch: java.lang.Exception -> La7
                return r7
            L85:
                io.dcloud.feature.unimp.DCUniMPService r7 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.d r7 = io.dcloud.feature.unimp.DCUniMPService.b(r7)     // Catch: java.lang.Exception -> La7
                java.lang.String r7 = r7.e(r8)     // Catch: java.lang.Exception -> La7
                return r7
            L90:
                io.dcloud.feature.unimp.DCUniMPService r7 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.d r7 = io.dcloud.feature.unimp.DCUniMPService.b(r7)     // Catch: java.lang.Exception -> La7
                io.dcloud.feature.unimp.DCUniMPService r0 = io.dcloud.feature.unimp.DCUniMPService.this     // Catch: java.lang.Exception -> La7
                android.content.Context r0 = r0.getBaseContext()     // Catch: java.lang.Exception -> La7
                java.lang.String r1 = "appid"
                java.lang.String r8 = r8.getString(r1)     // Catch: java.lang.Exception -> La7
                java.lang.String r7 = r7.a(r0, r8)     // Catch: java.lang.Exception -> La7
                return r7
            La7:
                r7 = 0
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.unimp.DCUniMPService.AnonymousClass2.execute(java.lang.String, android.os.Bundle):java.lang.String");
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public String getCurrentPageUrl() throws RemoteException {
            return DCUniMPService.this.b().a(DCUniMPService.this.b().b());
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public String getRunningAppid() throws RemoteException {
            return DCUniMPService.this.b().b();
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void initConfig(String str, String str2, String str3) throws RemoteException {
            JSONObject object = JSON.parseObject(str);
            DCUniMPService.sProcessName = str2;
            DCUniMPService.sHostServiceName = str3;
            if (object != null) {
                if (object.containsKey(AbsoluteConst.EVENTS_WEBVIEW_SHOW)) {
                    SDK.isCapsule = object.getBoolean(AbsoluteConst.EVENTS_WEBVIEW_SHOW).booleanValue();
                } else {
                    SDK.isCapsule = true;
                }
                if (object.containsKey("enableBackground")) {
                    SDK.isEnableBackground = object.getBoolean("enableBackground").booleanValue();
                }
                SDK.sDefaultMenuButton = str;
            }
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void preUniMP(String str) throws RemoteException {
            DCUniMPService.this.b().a(DCUniMPService.this.getApplication(), str);
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void registerCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException {
            DCUniMPService.this.b().a(iDCUniMPCallBack);
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void setRunningAppid(String str) throws RemoteException {
            DCUniMPService.this.b().b(str);
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public boolean stopApp(String str) throws RemoteException {
            return DCUniMPService.this.b().a(str, 2);
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void unregisterCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException {
            DCUniMPService.this.b().b(iDCUniMPCallBack);
        }
    };

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DCUniMPService.this.b = IDCUniMPCallBack.Stub.asInterface(iBinder);
            try {
                DCUniMPService.this.b.callBack("unimp_connection", null);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DCUniMPService.this.b = null;
        }
    }

    public static void startHostService(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, str);
        context.startService(intent);
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public IBinder onBindImpl(Intent intent) {
        return this.d;
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public void onCreateImpl() {
        DCLoudApplicationImpl.self().init(getApplication(), true);
        Log.e("shutao", "onCreateImpl--------------------");
        super.onCreateImpl();
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public void onDestroyImpl() {
        b().a().kill();
        super.onDestroyImpl();
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public int onStartCommandImpl(Intent intent, int i, int i2) {
        return super.onStartCommandImpl(intent, i, i2);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean stopService(Intent intent) {
        return super.stopService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d b() {
        if (this.f6674a == null) {
            this.f6674a = new d();
        }
        return this.f6674a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, ServiceConnection serviceConnection) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, str);
        context.bindService(intent, serviceConnection, 64);
    }
}
