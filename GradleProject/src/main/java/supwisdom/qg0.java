package supwisdom;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;
import supwisdom.nf0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class qg0 extends nf0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f8917e;
    public final Handler f;

    @GuardedBy("connectionStatus")
    public final HashMap<nf0.a, sg0> d = new HashMap<>();
    public final jh0 g = jh0.a();
    public final long h = 5000;
    public final long i = 300000;

    public qg0(Context context) {
        this.f8917e = context.getApplicationContext();
        this.f = new li0(context.getMainLooper(), new rg0(this));
    }

    @Override // supwisdom.nf0
    public final boolean a(nf0.a aVar, ServiceConnection serviceConnection, String str) {
        boolean zA;
        pf0.a(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.d) {
            sg0 sg0Var = this.d.get(aVar);
            if (sg0Var == null) {
                sg0Var = new sg0(this, aVar);
                sg0Var.a(serviceConnection, serviceConnection, str);
                sg0Var.a(str);
                this.d.put(aVar, sg0Var);
            } else {
                this.f.removeMessages(0, aVar);
                if (sg0Var.a(serviceConnection)) {
                    String strValueOf = String.valueOf(aVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(strValueOf);
                    throw new IllegalStateException(sb.toString());
                }
                sg0Var.a(serviceConnection, serviceConnection, str);
                int iB = sg0Var.b();
                if (iB == 1) {
                    serviceConnection.onServiceConnected(sg0Var.e(), sg0Var.d());
                } else if (iB == 2) {
                    sg0Var.a(str);
                }
            }
            zA = sg0Var.a();
        }
        return zA;
    }

    @Override // supwisdom.nf0
    public final void b(nf0.a aVar, ServiceConnection serviceConnection, String str) {
        pf0.a(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.d) {
            sg0 sg0Var = this.d.get(aVar);
            if (sg0Var == null) {
                String strValueOf = String.valueOf(aVar);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(strValueOf);
                throw new IllegalStateException(sb.toString());
            }
            if (!sg0Var.a(serviceConnection)) {
                String strValueOf2 = String.valueOf(aVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(strValueOf2);
                throw new IllegalStateException(sb2.toString());
            }
            sg0Var.a(serviceConnection, str);
            if (sg0Var.c()) {
                this.f.sendMessageDelayed(this.f.obtainMessage(0, aVar), this.h);
            }
        }
    }
}
