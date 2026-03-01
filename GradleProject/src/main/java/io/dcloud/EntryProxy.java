package io.dcloud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.core.ui.DCKeyboardManager;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.feature.internal.sdk.SDK;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class EntryProxy {
    public static EntryProxy d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<Activity> f6315a = new ArrayList<>(1);
    public boolean b = false;
    public io.dcloud.f.b.d c = null;

    private void clearData() {
        Logger.d("EntryProxy", " clearData");
        d = null;
        this.b = false;
        AndroidResources.clearData();
        BaseInfo.clearData();
        this.c = null;
    }

    public static EntryProxy getInstnace() {
        return d;
    }

    public static EntryProxy init(Activity activity, ICore.ICoreStatusListener iCoreStatusListener) {
        Context applicationContext = activity.getApplicationContext();
        DCLoudApplicationImpl.self().setContext(applicationContext);
        AndroidResources.initAndroidResources(applicationContext);
        EntryProxy entryProxy = d;
        if (entryProxy != null) {
            entryProxy.c.a().setmCoreListener(iCoreStatusListener);
            if (d.c.b() != applicationContext) {
                d.destroy(activity);
            }
        }
        if (d == null) {
            d = new EntryProxy();
            CookieSyncManager.createInstance(applicationContext);
            d.c = new io.dcloud.f.b.d(applicationContext, iCoreStatusListener);
        }
        d.f6315a.add(activity);
        return d;
    }

    public void destroy(Activity activity) {
        onStop(activity);
    }

    public boolean didCreate() {
        return this.b;
    }

    public ICore getCoreHandler() {
        io.dcloud.f.b.d dVar = this.c;
        if (dVar != null) {
            return dVar.a();
        }
        return null;
    }

    public Activity getEntryActivity() {
        if (this.f6315a.size() > 0) {
            return this.f6315a.get(0);
        }
        return null;
    }

    public boolean onActivityExecute(Activity activity, ISysEventListener.SysEventType sysEventType, Object obj) {
        io.dcloud.f.b.d dVar = this.c;
        if (dVar != null) {
            return dVar.a(activity, sysEventType, obj);
        }
        return false;
    }

    public void onConfigurationChanged(Activity activity, int i) {
        io.dcloud.f.b.d dVar = this.c;
        if (dVar != null) {
            dVar.a(activity, i);
        }
    }

    @Deprecated
    public boolean onCreate(Bundle bundle, FrameLayout frameLayout, SDK.IntegratedMode integratedMode, IOnCreateSplashView iOnCreateSplashView) {
        return onCreate(bundle, integratedMode, iOnCreateSplashView);
    }

    public void onNewIntent(Activity activity, Intent intent) {
        io.dcloud.f.b.d dVar = this.c;
        if (dVar != null) {
            dVar.a(activity, intent);
        }
    }

    public void onPause(Activity activity) {
        io.dcloud.f.b.d dVar = this.c;
        if (dVar != null) {
            dVar.a(activity);
        }
        CookieSyncManager.getInstance().stopSync();
    }

    public void onResume(Activity activity) {
        io.dcloud.f.b.d dVar = this.c;
        if (dVar != null) {
            dVar.b(activity);
        }
        CookieSyncManager.getInstance().startSync();
    }

    public void onStop(Activity activity) {
        io.dcloud.f.b.d dVar;
        try {
            DCKeyboardManager.getInstance().onStop();
            RuningAcitvityUtil.isRuningActivity = false;
            BaseInfo.isFirstRun = false;
            this.f6315a.remove(activity);
            if (this.f6315a.size() == 0 && ((dVar = this.c) == null || dVar.c(activity))) {
                clearData();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public boolean onCreate(Bundle bundle, SDK.IntegratedMode integratedMode, IOnCreateSplashView iOnCreateSplashView) {
        return onCreate(this.f6315a.get(r0.size() - 1), bundle, integratedMode, iOnCreateSplashView);
    }

    public boolean onCreate(Activity activity, Bundle bundle, SDK.IntegratedMode integratedMode, IOnCreateSplashView iOnCreateSplashView) {
        RuningAcitvityUtil.isRuningActivity = true;
        DCKeyboardManager.getInstance().onCreate(activity);
        AndroidResources.initAndroidResources(activity.getBaseContext());
        this.c.a(activity, bundle, integratedMode, iOnCreateSplashView);
        return true;
    }

    @Deprecated
    public boolean onCreate(Bundle bundle) {
        return onCreate(bundle, (FrameLayout) null, (SDK.IntegratedMode) null, (IOnCreateSplashView) null);
    }

    public static EntryProxy init(Activity activity) {
        return init(activity, null);
    }
}
