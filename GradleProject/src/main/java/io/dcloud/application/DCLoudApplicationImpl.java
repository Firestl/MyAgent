package io.dcloud.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.webkit.WebView;
import io.dcloud.a;
import io.dcloud.common.DHInterface.INativeAppInfo;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.UEH;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.common.util.TelephonyUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.f.a.b;
import io.dcloud.feature.internal.sdk.SDK;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import supwisdom.ne;

/* JADX INFO: loaded from: classes2.dex */
public class DCLoudApplicationImpl {
    public static DCLoudApplicationImpl mInstance;
    public ActivityCallbacks activityCallbacks;
    public Context mApplication;
    public String Tag = "DCLoudApplicationImpl";
    public boolean isUniMP = false;
    public boolean isInit = false;
    public ConcurrentHashMap<String, WeakReference<Activity>> topActiveMap = new ConcurrentHashMap<>();

    public class ActivityCallbacks implements Application.ActivityLifecycleCallbacks {
        public int activityStartCount;
        public volatile boolean isBack;
        public boolean isStop;
        public List<ActivityStatusListener> listeners;

        public ActivityCallbacks() {
            this.activityStartCount = 0;
            this.isBack = false;
            this.isStop = false;
        }

        public void addListener(ActivityStatusListener activityStatusListener) {
            if (this.listeners == null) {
                this.listeners = new ArrayList();
            }
            this.listeners.add(activityStatusListener);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            try {
                DCLoudApplicationImpl.this.topActiveMap.remove(activity.getComponentName().getClassName());
            } catch (Exception unused) {
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            try {
                DCLoudApplicationImpl.this.topActiveMap.put(activity.getComponentName().getClassName(), new WeakReference<>(activity));
            } catch (Exception unused) {
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.isStop) {
                this.isStop = false;
            } else if (this.activityStartCount == 0 && this.isBack) {
                this.isBack = false;
                List<ActivityStatusListener> list = this.listeners;
                if (list != null && !list.isEmpty()) {
                    for (ActivityStatusListener activityStatusListener : this.listeners) {
                        if (activityStatusListener != null) {
                            activityStatusListener.onFront();
                        }
                    }
                }
            }
            this.activityStartCount++;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            List<ActivityStatusListener> list;
            int i = this.activityStartCount - 1;
            this.activityStartCount = i;
            if (i != 0 || this.isStop || (list = this.listeners) == null || list.isEmpty()) {
                return;
            }
            try {
                List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) activity.getSystemService("activity")).getRunningTasks(1);
                if (runningTasks.get(0).numActivities > 1) {
                    ComponentName componentName = runningTasks.get(0).baseActivity;
                    ComponentName componentName2 = runningTasks.get(0).topActivity;
                    if (componentName2 != null && !componentName2.getPackageName().equals(activity.getApplication().getPackageName()) && componentName != null) {
                        if (componentName.getPackageName().equals(activity.getApplication().getPackageName())) {
                            return;
                        }
                    }
                }
            } catch (Exception unused) {
            }
            this.isBack = true;
            for (ActivityStatusListener activityStatusListener : this.listeners) {
                if (activityStatusListener != null) {
                    activityStatusListener.onBack();
                }
            }
        }

        public void removeListener(ActivityStatusListener activityStatusListener) {
            List<ActivityStatusListener> list = this.listeners;
            if (list != null) {
                list.remove(activityStatusListener);
            }
        }

        public void stopListener() {
            this.isStop = true;
        }
    }

    public interface ActivityStatusListener {
        void onBack();

        void onFront();
    }

    public class DynamicLanguageReceiver extends BroadcastReceiver {
        public DynamicLanguageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null) {
                return;
            }
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            launchIntentForPackage.setFlags(268468224);
            context.startActivity(launchIntentForPackage);
            Process.killProcess(Process.myPid());
        }
    }

    private INativeAppInfo getNativeInfo(Application application) {
        return new b(application);
    }

    private void initLanguageConfig(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LanguageUtil.LanguageBroadCastIntent);
        ne.a(self().getContext()).a(new DynamicLanguageReceiver(), intentFilter);
        if (Build.VERSION.SDK_INT < 26) {
            LanguageUtil.initAppLanguageForAppBeforeO(context);
        }
    }

    private void initX5(Application application) {
        if (AppRuntime.hasPrivacyForNotShown(application)) {
            return;
        }
        AppRuntime.initX5(application);
    }

    public static DCLoudApplicationImpl self() {
        if (mInstance == null) {
            mInstance = new DCLoudApplicationImpl();
        }
        return mInstance;
    }

    public void addActivityStatusListener(ActivityStatusListener activityStatusListener) {
        ActivityCallbacks activityCallbacks = this.activityCallbacks;
        if (activityCallbacks != null) {
            activityCallbacks.addListener(activityStatusListener);
        }
    }

    public Context attachBaseContext(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            context = LanguageUtil.updateContextLanguageAfterO(context, true);
        }
        if (i < 21) {
            supportMultiDex(context);
        }
        if (!SDK.isUniMPSDK()) {
            a.a(context);
        }
        return context;
    }

    public Context getContext() {
        return this.mApplication;
    }

    public void init(Application application, boolean z) {
        this.isUniMP = z;
        if (z && Build.VERSION.SDK_INT >= 26) {
            LanguageUtil.updateContextLanguageAfterO(application, true);
        }
        SDK.isUniMPSdk = this.isUniMP;
        onCreate(application);
        if (z) {
            DeviceInfo.initGsmCdmaCell();
            TelephonyUtil.updateIMEI(application);
        }
    }

    public boolean isInit() {
        return this.isInit;
    }

    public boolean isMainProcess(Context context) {
        if (AppRuntime.hasPrivacyForNotShown(context)) {
            return true;
        }
        boolean zEquals = context.getPackageName().equals(RuningAcitvityUtil.getAppName(context));
        if (!zEquals && !this.isUniMP) {
            if (RuningAcitvityUtil.getAppName(context).startsWith(context.getPackageName() + ":unimp")) {
                this.isUniMP = true;
                SDK.isUniMPSdk = true;
            }
        }
        return zEquals;
    }

    public void onCreate(Application application) {
        if (this.isInit) {
            return;
        }
        AndroidResources.initAndroidResources(application.getBaseContext());
        BaseInfo.parseControl();
        DeviceInfo.initPath(application);
        boolean zIsMainProcess = isMainProcess(application);
        webviewSetPath(application);
        this.isInit = true;
        PdrUtil.closeAndroidPDialog();
        INativeAppInfo nativeInfo = getNativeInfo(application);
        io.dcloud.g.a.a(nativeInfo);
        BaseInfo.isFirstRun = true;
        Logger.e(this.Tag, "SDK.isUniMPSDK()==" + SDK.isUniMPSDK() + "  isMainProcess-=" + zIsMainProcess + "  isMiniAppProcess=" + this.isUniMP);
        if ((!SDK.isUniMPSDK() && zIsMainProcess) || (SDK.isUniMPSDK() && this.isUniMP)) {
            initX5(application);
            AppRuntime.initWeex(nativeInfo);
        } else if (!zIsMainProcess) {
            AppRuntime.onSubProcess(application);
        }
        if (!SDK.isUniMPSDK()) {
            a.a(application);
        }
        this.mApplication = application;
        Logger.e(this.Tag, "mApplication--=" + this.mApplication);
        setContext(application);
        UEH.catchUncaughtException(application);
        if (PdrUtil.isSupportOaid()) {
            try {
                Method declaredMethod = Class.forName("com.bun.miitmdid.core.JLibrary").getDeclaredMethod("InitEntry", Context.class);
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(null, application);
                }
            } catch (Exception unused) {
            }
        }
        ActivityCallbacks activityCallbacks = new ActivityCallbacks();
        this.activityCallbacks = activityCallbacks;
        application.registerActivityLifecycleCallbacks(activityCallbacks);
        initLanguageConfig(getContext());
        io.dcloud.common.ui.b.a().a(self().getContext());
    }

    public void removeActivityStatusListener(ActivityStatusListener activityStatusListener) {
        ActivityCallbacks activityCallbacks = this.activityCallbacks;
        if (activityCallbacks != null) {
            activityCallbacks.removeListener(activityStatusListener);
        }
    }

    public void setContext(Context context) {
        if (this.mApplication == null) {
            this.mApplication = context;
        }
    }

    public void stopActivityStatusListener() {
        ActivityCallbacks activityCallbacks = this.activityCallbacks;
        if (activityCallbacks != null) {
            activityCallbacks.stopListener();
        }
    }

    public void supportMultiDex(Context context) {
        try {
            Class.forName("supwisdom.xe").getMethod("install", Context.class).invoke(null, context);
        } catch (Exception unused) {
        }
    }

    public void webviewSetPath(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 28 || !SDK.isUniMPSDK() || isMainProcess(context)) {
                return;
            }
            WebView.setDataDirectorySuffix(RuningAcitvityUtil.getAppName(context));
        } catch (Exception unused) {
        }
    }
}
