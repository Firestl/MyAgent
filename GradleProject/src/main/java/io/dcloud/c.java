package io.dcloud;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.ImageLoaderUtil;
import io.dcloud.feature.internal.reflect.BroadcastReceiver;
import io.src.dcloud.adapter.DCloudBaseActivity;
import java.util.HashMap;
import java.util.Iterator;
import supwisdom.ne;
import supwisdom.qv;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c extends DCloudBaseActivity implements IActivityHandler, IReflectAble {
    public static String d = "www/";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6354a = 0;
    public HashMap<String, d> b = new HashMap<>();
    public HashMap<String, d> c = new HashMap<>();

    private void b() {
        Iterator<d> it = this.b.values().iterator();
        while (it.hasNext()) {
            ne.a(this).a(it.next());
        }
        this.b.clear();
        Iterator<d> it2 = this.c.values().iterator();
        while (it2.hasNext()) {
            unregisterReceiver(it2.next());
        }
        this.c.clear();
    }

    public void callBack(String str, Bundle bundle) {
    }

    public int getActivityState() {
        return this.f6354a;
    }

    public Context getContext() {
        return this.that;
    }

    public String getUrlByFilePath(String str, String str2) {
        return DataInterface.getBaseUrl() + str2.substring(str2.indexOf(d) + d.length());
    }

    public boolean isMultiProcessMode() {
        return false;
    }

    public void onAsyncStartAppEnd(String str, Object obj) {
    }

    public Object onAsyncStartAppStart(String str) {
        return null;
    }

    @Override // io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.f6354a = 1;
        super.onCreate(bundle);
        AndroidResources.initAndroidResources(this.that);
        BaseInfo.parseControl(getContext(), null, null);
        if (BaseInfo.SyncDebug && !getPackageName().equals(getResources().getString(PdrR.DCLOUD_PACKAGE_NAME_BASE))) {
            qv.makeText((Context) this, PdrR.DCLOUD_SYNC_DEBUD_MESSAGE, 0).show();
        }
        DeviceInfo.initPath(this.that);
        ImageLoaderUtil.initImageLoader(this.that);
        ImageLoaderUtil.initImageLoaderL(this.that);
    }

    @Override // io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f6354a = 0;
        super.onDestroy();
        try {
            b();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.f6354a = 2;
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        this.f6354a = 1;
        super.onResume();
    }

    public void registerLocalReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        d dVar = new d(broadcastReceiver, intentFilter);
        try {
            ne.a(this).a(dVar, intentFilter);
            this.b.put(broadcastReceiver.toString(), dVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        Intent intentRegisterReceiver;
        d dVar = new d(broadcastReceiver, intentFilter);
        try {
            intentRegisterReceiver = registerReceiver(dVar, intentFilter, str, handler);
            try {
                this.c.put(broadcastReceiver.toString(), dVar);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (Exception e3) {
            e = e3;
            intentRegisterReceiver = null;
        }
        return intentRegisterReceiver;
    }

    public void sendLocalBroadcast(Intent intent) {
        ne.a(this).a(intent);
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        d dVarRemove = this.b.remove(broadcastReceiver.toString());
        if (dVarRemove != null) {
            ne.a(this).a(dVarRemove);
        }
        d dVarRemove2 = this.c.remove(broadcastReceiver.toString());
        if (dVarRemove2 != null) {
            unregisterReceiver(dVarRemove2);
        }
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        d dVar = new d(broadcastReceiver, intentFilter);
        try {
            registerReceiver(dVar, intentFilter);
            this.c.put(broadcastReceiver.toString(), dVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
