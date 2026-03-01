package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import org.repackage.com.zui.deviceidservice.IDeviceidInterface;

/* JADX INFO: compiled from: OpenDeviceId.java */
/* JADX INFO: loaded from: classes3.dex */
public class ow1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f8725e = "OpenDeviceId library";
    public static boolean f = false;
    public IDeviceidInterface b;
    public ServiceConnection c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8726a = null;
    public b d = null;

    /* JADX INFO: compiled from: OpenDeviceId.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ow1.this.b = IDeviceidInterface.Stub.a(iBinder);
            if (ow1.this.d != null) {
                ow1.this.d.a("Deviceid Service Connected", ow1.this);
            }
            ow1.this.a("Service onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ow1.this.b = null;
            ow1.this.a("Service onServiceDisconnected");
        }
    }

    /* JADX INFO: compiled from: OpenDeviceId.java */
    public interface b<T> {
        void a(T t, ow1 ow1Var);
    }

    public boolean b() {
        try {
            if (this.b == null) {
                return false;
            }
            a("Device support opendeviceid");
            return this.b.c();
        } catch (RemoteException unused) {
            b("isSupport error, RemoteException!");
            return false;
        }
    }

    public int a(Context context, b<String> bVar) {
        if (context != null) {
            this.f8726a = context;
            this.d = bVar;
            this.c = new a();
            Intent intent = new Intent();
            intent.setClassName("org.repackage.com.zui.deviceidservice", "org.repackage.com.zui.deviceidservice.DeviceidService");
            if (this.f8726a.bindService(intent, this.c, 1)) {
                a("bindService Successful!");
                return 1;
            }
            a("bindService Failed!");
            return -1;
        }
        throw new NullPointerException("Context can not be null.");
    }

    public final void b(String str) {
        if (f) {
            Log.e(f8725e, str);
        }
    }

    public String a() {
        if (this.f8726a != null) {
            try {
                if (this.b != null) {
                    return this.b.a();
                }
                return null;
            } catch (RemoteException e2) {
                b("getOAID error, RemoteException!");
                e2.printStackTrace();
                return null;
            }
        }
        b("Context is null.");
        throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
    }

    public final void a(String str) {
        if (f) {
            Log.i(f8725e, str);
        }
    }
}
