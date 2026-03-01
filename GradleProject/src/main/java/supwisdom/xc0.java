package supwisdom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class xc0 implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final xc0 f9739e = new xc0();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f9740a = new AtomicBoolean();
    public final AtomicBoolean b = new AtomicBoolean();

    @GuardedBy("sInstance")
    public final ArrayList<a> c = new ArrayList<>();

    @GuardedBy("sInstance")
    public boolean d = false;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
    public interface a {
        void a(boolean z);
    }

    public static void a(@RecentlyNonNull Application application) {
        synchronized (f9739e) {
            if (!f9739e.d) {
                application.registerActivityLifecycleCallbacks(f9739e);
                application.registerComponentCallbacks(f9739e);
                f9739e.d = true;
            }
        }
    }

    @RecentlyNonNull
    public static xc0 b() {
        return f9739e;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(@RecentlyNonNull Activity activity, Bundle bundle) {
        boolean zCompareAndSet = this.f9740a.compareAndSet(true, false);
        this.b.set(true);
        if (zCompareAndSet) {
            b(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(@RecentlyNonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(@RecentlyNonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(@RecentlyNonNull Activity activity) {
        boolean zCompareAndSet = this.f9740a.compareAndSet(true, false);
        this.b.set(true);
        if (zCompareAndSet) {
            b(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(@RecentlyNonNull Activity activity, @RecentlyNonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(@RecentlyNonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(@RecentlyNonNull Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(@RecentlyNonNull Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        if (i == 20 && this.f9740a.compareAndSet(false, true)) {
            this.b.set(true);
            b(true);
        }
    }

    public final void b(boolean z) {
        synchronized (f9739e) {
            ArrayList<a> arrayList = this.c;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                a aVar = arrayList.get(i);
                i++;
                aVar.a(z);
            }
        }
    }

    @TargetApi(16)
    public final boolean a(boolean z) {
        if (!this.b.get()) {
            if (!th0.a()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.b.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.f9740a.set(true);
            }
        }
        return a();
    }

    public final boolean a() {
        return this.f9740a.get();
    }

    public final void a(@RecentlyNonNull a aVar) {
        synchronized (f9739e) {
            this.c.add(aVar);
        }
    }
}
