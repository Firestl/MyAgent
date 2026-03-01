package com.g.gysdk.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.g.gysdk.a.ak;
import com.g.gysdk.a.d;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class c implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Activity, b> f2083a = new HashMap();
    public final Map<Activity, Boolean> b = new ConcurrentHashMap();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2084a = new c();
    }

    public static c a() {
        return a.f2084a;
    }

    public void a(Activity activity, b bVar) {
        b bVar2;
        if (activity == null || bVar == null) {
            ak.e(new IllegalStateException("hasNewActivity, but activity or helper null"));
            return;
        }
        synchronized (this.f2083a) {
            bVar2 = this.f2083a.get(activity);
            if (bVar2 != bVar) {
                this.f2083a.put(activity, bVar);
            }
        }
        if (bVar2 != null) {
            bVar2.a();
        }
        ak.a("after call hasNewActivity, GyEloginUIHelper count = " + this.f2083a.size());
    }

    public void a(Activity activity, boolean z) {
        b bVarRemove;
        if (activity == null) {
            ak.e(new IllegalStateException("hasDestoryActivity, but activity null"));
            return;
        }
        synchronized (this.f2083a) {
            bVarRemove = this.f2083a.remove(activity);
        }
        if (z && bVarRemove != null) {
            bVarRemove.a();
        }
        ak.a("after call hasDestoryActivity, GyEloginUIHelper count = " + this.f2083a.size());
    }

    public boolean a(Activity activity) {
        Boolean bool;
        return (activity == null || (bool = this.b.get(activity)) == null || !bool.booleanValue()) ? false : true;
    }

    public void b() {
        try {
            ((Application) d.b).registerActivityLifecycleCallbacks(this);
        } catch (Throwable th) {
            ak.e("GyLifeManager init failed", th);
        }
    }

    public void c() {
        try {
            synchronized (this.f2083a) {
                for (b bVar : (b[]) this.f2083a.values().toArray(new b[0])) {
                    bVar.a();
                }
                this.f2083a.clear();
            }
            ak.a("after call releaseAll, GyEloginUIHelper count = 0");
        } catch (Throwable th) {
            ak.e("releaseAll exception", th);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ak.a("onActivityCreated:" + activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        ak.a("onActivityDestroyed:" + activity);
        this.b.remove(activity);
        a(activity, true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        ak.a("onActivityPaused:" + activity);
        this.b.put(activity, false);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        ak.a("onActivityResumed:" + activity);
        this.b.put(activity, true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        ak.a("onActivitySaveInstanceState:" + activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        ak.a("onActivityStarted:" + activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        ak.a("onActivityStopped:" + activity);
    }
}
