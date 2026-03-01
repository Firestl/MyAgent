package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.umeng.analytics.pro.b;
import com.umeng.analytics.pro.c;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HonorDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class bb implements ax {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5191a = "";

    /* JADX INFO: compiled from: HonorDeviceIdSupplier.java */
    public static final class a implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5192a;
        public final LinkedBlockingQueue<IBinder> b;

        public IBinder a() throws InterruptedException {
            if (this.f5192a) {
                throw new IllegalStateException();
            }
            this.f5192a = true;
            return this.b.poll(5L, TimeUnit.SECONDS);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        public a() {
            this.f5192a = false;
            this.b = new LinkedBlockingQueue<>();
        }
    }

    /* JADX INFO: compiled from: HonorDeviceIdSupplier.java */
    public static final class b extends b.AbstractBinderC0120b {
        @Override // com.umeng.analytics.pro.b
        public void a(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        @Override // com.umeng.analytics.pro.b
        public void a(int i, Bundle bundle) throws RemoteException {
            if (i != 0 || bundle == null) {
                return;
            }
            String unused = bb.f5191a = bundle.getString(c.b);
        }

        public b() {
        }
    }

    /* JADX INFO: compiled from: HonorDeviceIdSupplier.java */
    public interface c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int f5193a = 0;
        public static final String b = "oa_id_flag";
    }

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        a aVar = new a();
        Intent intent = new Intent();
        intent.setAction("com.hihonor.id.HnOaIdService");
        intent.setPackage("com.hihonor.id");
        if (context.bindService(intent, aVar, 1)) {
            try {
                c.b.a(aVar.a()).a(new b());
                return f5191a;
            } catch (Exception unused) {
            } finally {
                context.unbindService(aVar);
            }
        }
        return null;
    }
}
