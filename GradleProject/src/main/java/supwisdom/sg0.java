package supwisdom;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import supwisdom.nf0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class sg0 implements ServiceConnection, ug0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<ServiceConnection, ServiceConnection> f9169a = new HashMap();
    public int b = 2;
    public boolean c;
    public IBinder d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final nf0.a f9170e;
    public ComponentName f;
    public final /* synthetic */ qg0 g;

    public sg0(qg0 qg0Var, nf0.a aVar) {
        this.g = qg0Var;
        this.f9170e = aVar;
    }

    public final void a(String str) {
        this.b = 3;
        boolean zA = this.g.g.a(this.g.f8917e, str, this.f9170e.a(this.g.f8917e), this, this.f9170e.c());
        this.c = zA;
        if (zA) {
            this.g.f.sendMessageDelayed(this.g.f.obtainMessage(1, this.f9170e), this.g.i);
        } else {
            this.b = 2;
            try {
                this.g.g.a(this.g.f8917e, this);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public final void b(String str) {
        this.g.f.removeMessages(1, this.f9170e);
        this.g.g.a(this.g.f8917e, this);
        this.c = false;
        this.b = 2;
    }

    public final boolean c() {
        return this.f9169a.isEmpty();
    }

    public final IBinder d() {
        return this.d;
    }

    public final ComponentName e() {
        return this.f;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.g.d) {
            this.g.f.removeMessages(1, this.f9170e);
            this.d = iBinder;
            this.f = componentName;
            Iterator<ServiceConnection> it = this.f9169a.values().iterator();
            while (it.hasNext()) {
                it.next().onServiceConnected(componentName, iBinder);
            }
            this.b = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.g.d) {
            this.g.f.removeMessages(1, this.f9170e);
            this.d = null;
            this.f = componentName;
            Iterator<ServiceConnection> it = this.f9169a.values().iterator();
            while (it.hasNext()) {
                it.next().onServiceDisconnected(componentName);
            }
            this.b = 2;
        }
    }

    public final int b() {
        return this.b;
    }

    public final void a(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.f9169a.put(serviceConnection, serviceConnection2);
    }

    public final void a(ServiceConnection serviceConnection, String str) {
        this.f9169a.remove(serviceConnection);
    }

    public final boolean a() {
        return this.c;
    }

    public final boolean a(ServiceConnection serviceConnection) {
        return this.f9169a.containsKey(serviceConnection);
    }
}
