package com.dcloud.android.downloader;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import java.util.List;
import supwisdom.rt;
import supwisdom.st;
import supwisdom.ut;

/* JADX INFO: loaded from: classes.dex */
public class DownloadService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static st f1777a;

    public static st a(Context context) {
        return a(context, null);
    }

    public static boolean b(Context context) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null || runningServices.size() == 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(DownloadService.class.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        st stVar = f1777a;
        if (stVar != null) {
            stVar.onDestroy();
            f1777a = null;
        }
        super.onDestroy();
    }

    public static st a(Context context, ut utVar) {
        if (!b(context)) {
            context.startService(new Intent(context, (Class<?>) DownloadService.class));
        }
        if (f1777a == null) {
            f1777a = rt.a(context, utVar);
        }
        return f1777a;
    }
}
