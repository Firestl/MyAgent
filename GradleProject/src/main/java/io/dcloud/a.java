package io.dcloud;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Class f6345a;

    public static void a(Application application) {
        IWaiter iWaiterA = a();
        if (iWaiterA != null) {
            iWaiterA.doForFeature("onAppCreate", application);
        }
    }

    public static boolean b() {
        if (f6345a != null) {
            return true;
        }
        try {
            f6345a = Class.forName("io.dcloud.feature.gg.AdFeatureImplMgr");
        } catch (Exception unused) {
        }
        return f6345a != null;
    }

    public static void a(Context context) {
        IWaiter iWaiterA = a();
        DeviceInfo.sApplicationContext = context;
        if (iWaiterA != null) {
            iWaiterA.doForFeature("onAppAttachBaseContext", context);
        }
    }

    public static Object a(Context context, String str, String str2, Object obj) {
        IWaiter iWaiterA;
        if (SDK.isUniMPSDK()) {
            return null;
        }
        if ((!PdrUtil.checkIntl() || LanguageUtil.getDeviceDefLocalLanguage().equalsIgnoreCase(io.dcloud.f.b.b.c().decryptStr("~l)gj", (byte) 4))) && (iWaiterA = a()) != null) {
            return iWaiterA.doForFeature(str2, new Object[]{context, str, obj});
        }
        return null;
    }

    public static IWaiter a() {
        if (b()) {
            try {
                Object objInvoke = f6345a.getMethod("self", new Class[0]).invoke(null, new Object[0]);
                if (objInvoke instanceof IWaiter) {
                    return (IWaiter) objInvoke;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static View a(Activity activity, ICallBack iCallBack, String str) {
        IWaiter iWaiterA = a();
        if (iWaiterA != null) {
            return (View) iWaiterA.doForFeature("onCreateAdSplash", new Object[]{activity, iCallBack, str});
        }
        return null;
    }
}
