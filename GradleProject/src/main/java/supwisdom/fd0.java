package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Collections;
import java.util.Set;
import supwisdom.mc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class fd0 implements ServiceConnection, mc0.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7591a;
    public final String b;
    public final ComponentName c;
    public final Context d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ad0 f7592e;
    public final Handler f;
    public final gd0 g;
    public IBinder h;
    public boolean i;
    public String j;

    @Override // supwisdom.mc0.f
    public final void a(@RecentlyNonNull BaseGmsClient.c cVar) {
        h();
        c("Connect started.");
        if (isConnected()) {
            try {
                a("connect() called when already connected");
            } catch (Exception unused) {
            }
        }
        try {
            Intent intent = new Intent();
            if (this.c != null) {
                intent.setComponent(this.c);
            } else {
                intent.setPackage(this.f7591a).setAction(this.b);
            }
            boolean zBindService = this.d.bindService(intent, this, nf0.a());
            this.i = zBindService;
            if (!zBindService) {
                this.h = null;
                this.g.onConnectionFailed(new ConnectionResult(16));
            }
            c("Finished connect.");
        } catch (SecurityException e2) {
            this.i = false;
            this.h = null;
            throw e2;
        }
    }

    @Override // supwisdom.mc0.f
    public final void a(@RecentlyNonNull BaseGmsClient.e eVar) {
    }

    @Override // supwisdom.mc0.f
    public final void a(IAccountAccessor iAccountAccessor, Set<Scope> set) {
    }

    @Override // supwisdom.mc0.f
    @RecentlyNonNull
    public final String b() {
        String str = this.f7591a;
        if (str != null) {
            return str;
        }
        pf0.a(this.c);
        return this.c.getPackageName();
    }

    public final void b(String str) {
    }

    public final void c(String str) {
        String strValueOf = String.valueOf(this.h);
        boolean z = this.i;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30 + String.valueOf(strValueOf).length());
        sb.append(str);
        sb.append(" binder: ");
        sb.append(strValueOf);
        sb.append(", isConnecting: ");
        sb.append(z);
    }

    @Override // supwisdom.mc0.f
    public final boolean c() {
        return false;
    }

    @Override // supwisdom.mc0.f
    public final int d() {
        return 0;
    }

    @Override // supwisdom.mc0.f
    public final void disconnect() {
        h();
        c("Disconnect called.");
        try {
            this.d.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
        this.i = false;
        this.h = null;
    }

    @Override // supwisdom.mc0.f
    @RecentlyNonNull
    public final Feature[] e() {
        return new Feature[0];
    }

    @Override // supwisdom.mc0.f
    @RecentlyNullable
    public final String f() {
        return this.j;
    }

    @Override // supwisdom.mc0.f
    public final boolean g() {
        return false;
    }

    public final void h() {
        if (Thread.currentThread() != this.f.getLooper().getThread()) {
            throw new IllegalStateException("This method should only run on the NonGmsServiceBrokerClient's handler thread.");
        }
    }

    public final /* synthetic */ void i() {
        this.i = false;
        this.h = null;
        c("Disconnected.");
        this.f7592e.onConnectionSuspended(1);
    }

    @Override // supwisdom.mc0.f
    public final boolean isConnected() {
        h();
        return this.h != null;
    }

    @Override // supwisdom.mc0.f
    public final boolean isConnecting() {
        h();
        return this.i;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(@RecentlyNonNull ComponentName componentName, @RecentlyNonNull final IBinder iBinder) {
        this.f.post(new Runnable(this, iBinder) { // from class: supwisdom.ee0

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final fd0 f7478a;
            public final IBinder b;

            {
                this.f7478a = this;
                this.b = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f7478a.a(this.b);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(@RecentlyNonNull ComponentName componentName) {
        this.f.post(new Runnable(this) { // from class: supwisdom.fe0

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final fd0 f7595a;

            {
                this.f7595a = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f7595a.i();
            }
        });
    }

    @Override // supwisdom.mc0.f
    public final void a(@RecentlyNonNull String str) {
        h();
        this.j = str;
        disconnect();
    }

    @Override // supwisdom.mc0.f
    public final Set<Scope> a() {
        return Collections.emptySet();
    }

    public final /* synthetic */ void a(IBinder iBinder) {
        this.i = false;
        this.h = iBinder;
        c("Connected.");
        this.f7592e.onConnected(new Bundle());
    }
}
