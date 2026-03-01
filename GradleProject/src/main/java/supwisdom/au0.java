package supwisdom;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.hihonor.push.framework.aidl.IPushInvoke;
import com.hihonor.push.sdk.internal.HonorPushErrorEnum;
import supwisdom.pu0;
import supwisdom.rt0;

/* JADX INFO: loaded from: classes.dex */
public class au0 implements ServiceConnection {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f6982e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ut0 f6983a;
    public a b;
    public Handler c = null;
    public boolean d = false;

    public interface a {
    }

    public au0(ut0 ut0Var) {
        this.f6983a = ut0Var;
    }

    public final void a(int i) {
        a aVar = this.b;
        if (aVar != null) {
            vt0 vt0Var = (vt0) aVar;
            vt0Var.f9547a.f9809a.set(i == HonorPushErrorEnum.ERROR_SERVICE_TIME_OUT.statusCode ? 2 : 1);
            vt0Var.f9547a.a(i);
            vt0Var.f9547a.b = null;
        }
    }

    public void b() {
        try {
            Log.i("AIDLSrvConnection", "trying to unbind service from " + this);
            gu0.b.a().unbindService(this);
        } catch (Exception e2) {
            String str = "on unBind service exception:" + e2.getMessage();
        }
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        Log.i("AIDLSrvConnection", "enter onNullBinding, than unBind.");
        if (this.d) {
            this.d = false;
            return;
        }
        b();
        a();
        a aVar = this.b;
        if (aVar != null) {
            vt0 vt0Var = (vt0) aVar;
            vt0Var.f9547a.f9809a.set(1);
            vt0Var.f9547a.a(8002005);
            vt0Var.f9547a.b = null;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i("AIDLSrvConnection", "enter onServiceConnected.");
        a();
        a aVar = this.b;
        if (aVar != null) {
            vt0 vt0Var = (vt0) aVar;
            vt0Var.f9547a.b = IPushInvoke.Stub.asInterface(iBinder);
            if (vt0Var.f9547a.b == null) {
                vt0Var.f9547a.d.b();
                vt0Var.f9547a.f9809a.set(1);
                vt0Var.f9547a.a(8002001);
                return;
            }
            vt0Var.f9547a.f9809a.set(3);
            rt0.a aVar2 = vt0Var.f9547a.c;
            if (aVar2 != null) {
                pu0.a aVar3 = (pu0.a) aVar2;
                if (Looper.myLooper() == pu0.this.f8830a.getLooper()) {
                    aVar3.b();
                } else {
                    pu0.this.f8830a.post(new nu0(aVar3));
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("AIDLSrvConnection", "enter onServiceDisconnected.");
        a aVar = this.b;
        if (aVar != null) {
            vt0 vt0Var = (vt0) aVar;
            vt0Var.f9547a.f9809a.set(1);
            vt0Var.f9547a.a(8002002);
            vt0Var.f9547a.b = null;
        }
    }

    public final void a() {
        synchronized (f6982e) {
            Handler handler = this.c;
            if (handler != null) {
                handler.removeMessages(1001);
                this.c = null;
            }
        }
    }
}
