package io.dcloud.application;

import android.app.Application;
import android.content.Context;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class DCloudApplication extends Application {
    public final String TAG = DCloudApplication.class.getSimpleName();

    public void addActivityStatusListener(DCLoudApplicationImpl.ActivityStatusListener activityStatusListener) {
        DCLoudApplicationImpl.self().addActivityStatusListener(activityStatusListener);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        DCLoudApplicationImpl.self().attachBaseContext(context);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        DCLoudApplicationImpl.self().onCreate(this);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        Logger.e(this.TAG, "onLowMemory" + Runtime.getRuntime().maxMemory());
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Logger.e(this.TAG, "onTrimMemory");
    }

    public void removeActivityStatusListener(DCLoudApplicationImpl.ActivityStatusListener activityStatusListener) {
        DCLoudApplicationImpl.self().removeActivityStatusListener(activityStatusListener);
    }

    public void stopB2FOnce() {
        DCLoudApplicationImpl.self().stopActivityStatusListener();
    }
}
