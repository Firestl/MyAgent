package supwisdom;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class jh0 {
    public static final Object b = new Object();

    @Nullable
    public static volatile jh0 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<ServiceConnection, ServiceConnection> f8061a = new ConcurrentHashMap<>();

    @RecentlyNonNull
    public static jh0 a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new jh0();
                }
            }
        }
        jh0 jh0Var = c;
        pf0.a(jh0Var);
        return jh0Var;
    }

    public final boolean a(@RecentlyNonNull Context context, @RecentlyNonNull String str, @RecentlyNonNull Intent intent, @RecentlyNonNull ServiceConnection serviceConnection, int i) {
        return a(context, str, intent, serviceConnection, i, true);
    }

    @SuppressLint({"UntrackedBindService"})
    public final boolean a(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i, boolean z) {
        ComponentName component = intent.getComponent();
        if (component == null ? false : nh0.a(context, component.getPackageName())) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        if (a(serviceConnection)) {
            ServiceConnection serviceConnectionPutIfAbsent = this.f8061a.putIfAbsent(serviceConnection, serviceConnection);
            if (serviceConnectionPutIfAbsent != null && serviceConnection != serviceConnectionPutIfAbsent) {
                Log.w("ConnectionTracker", String.format("Duplicate binding with the same ServiceConnection: %s, %s, %s.", serviceConnection, str, intent.getAction()));
            }
            try {
                boolean zBindService = context.bindService(intent, serviceConnection, i);
                return !zBindService ? zBindService : zBindService;
            } finally {
                this.f8061a.remove(serviceConnection, serviceConnection);
            }
        }
        return context.bindService(intent, serviceConnection, i);
    }

    @SuppressLint({"UntrackedBindService"})
    public void a(@RecentlyNonNull Context context, @RecentlyNonNull ServiceConnection serviceConnection) {
        if (a(serviceConnection) && this.f8061a.containsKey(serviceConnection)) {
            try {
                try {
                    context.unbindService(this.f8061a.get(serviceConnection));
                } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused) {
                }
            } finally {
                this.f8061a.remove(serviceConnection);
            }
        } else {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused2) {
            }
        }
    }

    public static boolean a(ServiceConnection serviceConnection) {
        return !(serviceConnection instanceof ug0);
    }
}
