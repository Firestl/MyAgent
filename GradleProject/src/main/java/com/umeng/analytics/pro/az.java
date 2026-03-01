package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.umeng.analytics.pro.a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CoolpadDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class az implements ax {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5186a = "Coolpad";
    public static final String b = "com.coolpad.deviceidsupport";
    public static final String c = "com.coolpad.deviceidsupport.DeviceIdService";
    public static a d;
    public CountDownLatch f;
    public Context g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5187e = "";
    public final ServiceConnection h = new ServiceConnection() { // from class: com.umeng.analytics.pro.az.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                a unused = az.d = a.b.a(iBinder);
                az.this.f5187e = az.d.b(az.this.g.getPackageName());
                Log.d(az.f5186a, "onServiceConnected: oaid = " + az.this.f5187e);
            } catch (RemoteException | NullPointerException e2) {
                Log.e(az.f5186a, "onServiceConnected failed e=" + e2.getMessage());
            }
            az.this.f.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(az.f5186a, "onServiceDisconnected");
            a unused = az.d = null;
        }
    };

    private void b(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(b, c));
            if (context.bindService(intent, this.h, 1)) {
                return;
            }
            Log.e(f5186a, "bindService return false");
        } catch (Throwable th) {
            Log.e(f5186a, "bindService failed. e=" + th.getMessage());
            this.f.countDown();
        }
    }

    private void c(Context context) {
        try {
            Log.d(f5186a, "call unbindService.");
            context.unbindService(this.h);
        } catch (Throwable th) {
            Log.e(f5186a, "unbindService failed. e=" + th.getMessage());
        }
    }

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        this.g = context.getApplicationContext();
        this.f = new CountDownLatch(1);
        try {
            b(context);
            if (!this.f.await(500L, TimeUnit.MILLISECONDS)) {
                Log.e(f5186a, "getOAID time-out");
            }
            return this.f5187e;
        } catch (InterruptedException e2) {
            Log.e(f5186a, "getOAID interrupted. e=" + e2.getMessage());
            return null;
        } finally {
            c(context);
        }
    }
}
