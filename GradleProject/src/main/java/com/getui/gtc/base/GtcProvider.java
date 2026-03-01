package com.getui.gtc.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Publisher;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.base.util.EmptyProvider;
import com.getui.gtc.base.util.OnForegroundChangedListener;
import com.getui.gtc.base.util.ScheduleQueue;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class GtcProvider extends EmptyProvider implements Publisher {
    public static final String METHOD_ON_FOREGROUND_CHANGED = "onForegroundChanged";

    @SuppressLint({"StaticFieldLeak"})
    public static Context context = null;
    public static int gtcPid = 0;
    public static boolean isCheckedLifecycle = false;
    public static volatile boolean isForeground = false;
    public static boolean lazyInitCrypt = false;
    public static String sdcardPath;

    public static Context context() {
        return context;
    }

    @Deprecated
    public static Set<String> getForeActivities() {
        return Collections.emptySet();
    }

    @Deprecated
    public static String getSdcardPath() {
        if (sdcardPath == null) {
            synchronized (GtcProvider.class) {
                if (sdcardPath == null) {
                    sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                }
            }
        }
        return sdcardPath;
    }

    public static int gtcPid() {
        return gtcPid;
    }

    public static boolean isForeground() {
        return isForeground;
    }

    public static void lazyInitCrypt() {
        lazyInitCrypt = true;
    }

    @Deprecated
    public static int mainPid() {
        return gtcPid;
    }

    public static void setContext(Context context2) {
        if (context == null && context2 != null) {
            context = context2.getApplicationContext();
        }
        tryListenerForeground(context);
    }

    public static void subscribe(final Context context2, final boolean z) {
        ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.base.GtcProvider.2
            @Override // java.lang.Runnable
            public final void run() {
                context2.getContentResolver().call(Uri.parse("content://" + context2.getPackageName() + ".getui.gtc.provider"), GtcProvider.METHOD_ON_FOREGROUND_CHANGED, Boolean.toString(z), (Bundle) null);
            }
        });
    }

    public static void tryListenerForeground(Context context2) {
        try {
            if (isCheckedLifecycle) {
                return;
            }
            synchronized (GtcProvider.class) {
                if (isCheckedLifecycle) {
                    return;
                }
                final Context applicationContext = context2 != null ? context2.getApplicationContext() : CommonUtil.findAppContext();
                if (applicationContext == null) {
                    return;
                }
                if (CommonUtil.isMainProcess(applicationContext)) {
                    ((Application) applicationContext).registerActivityLifecycleCallbacks(new OnForegroundChangedListener() { // from class: com.getui.gtc.base.GtcProvider.1
                        @Override // com.getui.gtc.base.util.OnForegroundChangedListener
                        public final void onForegroundChanged(boolean z) {
                            boolean unused = GtcProvider.isForeground = z;
                            if (Process.myPid() != GtcProvider.gtcPid) {
                                GtcProvider.subscribe(applicationContext, z);
                            }
                        }
                    });
                    if (Process.myPid() != gtcPid && CommonUtil.getTopActivity() != null) {
                        isForeground = true;
                        subscribe(applicationContext, true);
                    }
                }
                isCheckedLifecycle = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (METHOD_ON_FOREGROUND_CHANGED.equals(str)) {
            isForeground = Boolean.parseBoolean(str2);
            return null;
        }
        if (bundle != null) {
            bundle.setClassLoader(GtcProvider.class.getClassLoader());
        }
        return publish(bundle);
    }

    @Override // com.getui.gtc.base.util.EmptyProvider, android.content.ContentProvider
    public boolean onCreate() {
        try {
            gtcPid = Process.myPid();
            setContext(getContext());
            if (lazyInitCrypt) {
                return true;
            }
            ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.base.GtcProvider.3
                @Override // java.lang.Runnable
                public final void run() {
                    SecureCryptTools.getInstance();
                }
            });
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.getui.gtc.base.publish.Publisher
    public Bundle publish(Bundle bundle) {
        return Broker.getInstance().publish(bundle);
    }
}
