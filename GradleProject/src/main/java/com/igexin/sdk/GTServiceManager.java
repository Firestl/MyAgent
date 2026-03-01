package com.igexin.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.igexin.push.core.ServiceManager;

/* JADX INFO: loaded from: classes2.dex */
public class GTServiceManager {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final GTServiceManager f3609a = new GTServiceManager();
    }

    public GTServiceManager() {
    }

    public static GTServiceManager getInstance() {
        return a.f3609a;
    }

    public void onActivityCreate(Activity activity) {
        ServiceManager.getInstance().a(activity);
    }

    public void onServiceCreate(Context context, Intent intent) {
        ServiceManager.getInstance().a(context, intent);
    }
}
