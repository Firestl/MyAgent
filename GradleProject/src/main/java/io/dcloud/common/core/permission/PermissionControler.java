package io.dcloud.common.core.permission;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.umeng.analytics.pro.f;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.util.AppPermissionUtil;
import io.dcloud.common.util.ShortCutUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.src.dcloud.adapter.DCloudBaseActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import supwisdom.j7;

/* JADX INFO: loaded from: classes2.dex */
public final class PermissionControler {
    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_GRANTED = 0;
    public static HashMap<String, ArrayList<String>> mAppPermissions = new HashMap<>(2);
    public static ArrayList<String> mRootAppList = new ArrayList<>();
    public static LinkedList<a> mRtPnInfos = new LinkedList<>();

    public static String checkPermission(IWebview iWebview, String[] strArr) {
        String str = strArr[0];
        iWebview.obtainApp().obtainAppId();
        String strObtainAppName = iWebview.obtainApp().obtainAppName();
        Context context = iWebview.getContext();
        if (!str.equals("SHORTCUT")) {
            return String.valueOf(convert5PlusValue(iWebview.obtainApp().checkSelfPermission(convertNativePermission(str), iWebview.obtainApp().obtainAppName())));
        }
        String str2 = Build.BRAND;
        if (str2.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
            return !AppPermissionUtil.isFlymeShortcutallowAllow(context, ShortCutUtil.getHeadShortCutIntent(strObtainAppName)) ? "denied" : "notdeny";
        }
        String str3 = Build.MANUFACTURER;
        if (str3.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
            return MobilePhoneModel.isSmartisanLauncherPhone(context) ? "denied" : "notdeny";
        }
        if (AppPermissionUtil.getShotCutOpId() == -1) {
            return "unknown";
        }
        AppPermissionUtil.getShotCutOpId();
        if (!str2.equalsIgnoreCase(MobilePhoneModel.XIAOMI)) {
            return str3.equalsIgnoreCase(MobilePhoneModel.HUAWEI) ? !AppPermissionUtil.isEmuiShortcutallowAllow() ? "denied" : "notdeny" : "unknown";
        }
        int iCheckOp = AppPermissionUtil.checkOp(context);
        return iCheckOp != -1 ? iCheckOp != 0 ? iCheckOp != 1 ? (iCheckOp == 3 || iCheckOp == 4) ? IApp.AUTHORITY_UNDETERMINED : "unknown" : "denied" : IApp.AUTHORITY_AUTHORIZED : "unsupported";
    }

    public static boolean checkPermission(String str, String str2) {
        return true;
    }

    public static boolean checkSafePermission(String str, String str2) {
        return "console".equals(str2) || f.ax.equals(str2.toLowerCase()) || "uninview".equals(str2.toLowerCase()) || "webview-x5".equals(str2.toLowerCase()) || "uiwebview".equals(str2.toLowerCase()) || "faceid".equals(str2.toLowerCase());
    }

    public static void clearCRequestPermissionsCache() {
        mRtPnInfos.clear();
    }

    public static String convert5PlusValue(int i) {
        return i != -1 ? i != 0 ? "unknown" : IApp.AUTHORITY_AUTHORIZED : IApp.AUTHORITY_UNDETERMINED;
    }

    public static String convertNativePermission(String str) {
        return PermissionUtil.convert2SystemPermission(str);
    }

    public static void destroy() {
        mAppPermissions.clear();
        mAppPermissions = null;
    }

    public static ArrayList<String> getPermissionList(String str) {
        return mAppPermissions.get(str);
    }

    public static String getPermissionsErrorDesp(String str) {
        str.hashCode();
        if (str.equals("invocation")) {
            return DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_permissions_njs_tips1);
        }
        return null;
    }

    public static void registerPermission(String str, ArrayList<String> arrayList) {
        mAppPermissions.remove(str);
        mAppPermissions.put(str, arrayList);
    }

    public static void registerRootPermission(String str) {
        mRootAppList.add(str);
    }

    public static void removeRequestPermissionForCode(int i) {
        for (a aVar : mRtPnInfos) {
            if (aVar.b == i) {
                mRtPnInfos.remove(aVar);
                return;
            }
        }
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        if (activity == null || strArr == null || strArr.length <= 0) {
            return;
        }
        if (!(activity instanceof DCloudBaseActivity)) {
            runRequestPermissions(activity, strArr, i);
            return;
        }
        mRtPnInfos.offer(new a(strArr, i));
        if (mRtPnInfos.size() == 1) {
            runRequestPermissions(activity, strArr, i);
        }
    }

    public static void runNextRequestPermission(Activity activity, int i) {
        a first;
        removeRequestPermissionForCode(i);
        if (mRtPnInfos.size() <= 0 || (first = mRtPnInfos.getFirst()) == null) {
            return;
        }
        runRequestPermissions(activity, first.f6359a, first.b);
    }

    public static void runRequestPermissions(Activity activity, String[] strArr, int i) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        if (SDK.isUniMPSDK()) {
            PlatformUtil.invokeMethod(activity.getClass().getName(), "requestUniMPPermissions", activity, new Class[]{strArr.getClass(), Integer.TYPE}, new Object[]{strArr, Integer.valueOf(i)});
        } else {
            j7.a(activity, strArr, i);
        }
    }

    public static void unregisterRootPermission(String str) {
        mRootAppList.remove(str);
    }
}
