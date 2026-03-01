package io.dcloud.feature.audio.recorder;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.dcloud.common.adapter.util.PlatformUtil;
import supwisdom.qv;

/* JADX INFO: loaded from: classes2.dex */
public class RecorderUtil {
    public static Context context;
    public static boolean isDebug;
    public static Handler mainHandler;

    public static Context getContext() {
        return context;
    }

    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }

    public static void init(Context context2, boolean z) {
        context = context2;
        isDebug = z;
        mainHandler = getMainHandler();
    }

    public static boolean isContainMp3() {
        return PlatformUtil.checkClass("io.dcloud.feature.audio.mp3.mp3Impl");
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void postTaskSafely(Runnable runnable) {
        getMainHandler().post(runnable);
    }

    public static void showDebugToast(final String str) {
        if (isDebug) {
            getMainHandler().post(new Runnable() { // from class: io.dcloud.feature.audio.recorder.RecorderUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    qv.makeText(RecorderUtil.context, (CharSequence) str, 0).show();
                }
            });
        }
    }
}
