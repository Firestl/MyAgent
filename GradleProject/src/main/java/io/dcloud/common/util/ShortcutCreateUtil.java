package io.dcloud.common.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.igexin.assist.util.AssistUtils;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ShortcutCreateUtil {
    public static final boolean DEFALT_VALUE = true;
    public static final String TAG = "ShortcutCreateUtil";
    public static List<String> hasToastTipPackages = new ArrayList();
    public static List<String> noToastTipPackages = new ArrayList();
    public static List<String> disablePackages = new ArrayList();
    public static List<String> selfDisablePackages = new ArrayList();
    public static List<String> thirdDisablePackages = new ArrayList();
    public static List<String> deletePackages = new ArrayList();
    public static List<String> systemLauncher = new ArrayList();
    public static List<String> duplicatePackages = new ArrayList();

    static {
        noToastTipPackages.add("com.miui.home");
        noToastTipPackages.add("com.miui.mihome2");
        noToastTipPackages.add("com.htc.launcher");
        noToastTipPackages.add("com.huawei.launcher2");
        noToastTipPackages.add("com.zte.mifavor.launcher");
        noToastTipPackages.add("com.cyanogenmod.trebuchet");
        noToastTipPackages.add("com.lenovo.launcher");
        noToastTipPackages.add("com.you.launcher");
        noToastTipPackages.add("com.android.launcher3");
        noToastTipPackages.add("com.meizu.flyme.easylauncher");
        noToastTipPackages.add("com.meizu.flyme.launcher");
        noToastTipPackages.add("com.ztemt.launcher");
        noToastTipPackages.add("cn.nubia.launcher");
        noToastTipPackages.add("com.zte.lqsoft.launcher");
        noToastTipPackages.add("com.yulong.android.launcher3");
        noToastTipPackages.add("com.google.android.googlequicksearchbox");
        hasToastTipPackages.add("com.android.launcher");
        hasToastTipPackages.add("com.android.launcher2");
        hasToastTipPackages.add("com.oppo.launcher");
        hasToastTipPackages.add("com.dianxinos.dxhome");
        hasToastTipPackages.add("com.xsg.launcher");
        hasToastTipPackages.add("com.sec.android.app.launcher");
        hasToastTipPackages.add("com.sec.android.app.twlauncher");
        hasToastTipPackages.add("com.qihoo360.launcher");
        hasToastTipPackages.add("com.huawei.android.launcher");
        hasToastTipPackages.add("com.sonyericsson.home");
        thirdDisablePackages.add("com.nd.android.pandahome2");
        thirdDisablePackages.add("com.gau.go.launcherex");
        thirdDisablePackages.add("com.Dean.launcher");
        thirdDisablePackages.add("com.moxiu.launcher");
        thirdDisablePackages.add("com.tencent.launcher");
        thirdDisablePackages.add("com.apusapps.launcher");
        thirdDisablePackages.add("com.baoruan.launcher2");
        thirdDisablePackages.add("com.lx.launcher");
        thirdDisablePackages.add("com.ltp.launcherpad");
        thirdDisablePackages.add("com.zui.launcher");
        thirdDisablePackages.add("com.lewa.launcher5");
        thirdDisablePackages.add("com.mycheering.launcher");
        thirdDisablePackages.add("com.jeejen.family");
        selfDisablePackages.add("com.smartisanos.launcher");
        selfDisablePackages.add("com.zte.mifavor.launcher");
        selfDisablePackages.add("com.bbk.launcher2");
        selfDisablePackages.add("com.oneplus.hydrogen.launcher");
        selfDisablePackages.add("com.sonyericsson.setupwizard");
        selfDisablePackages.add("com.gionee.navil");
        selfDisablePackages.add("com.sec.android.app.easylauncher");
        selfDisablePackages.add("com.nbbsw.launcherdoov");
        selfDisablePackages.add("com.huaqin.launcherEx");
        selfDisablePackages.add("com.ibingo.launcher");
        deletePackages.add("com.sec.android.app.launcher");
        deletePackages.add("com.sec.android.app.twlauncher");
        deletePackages.add("com.huawei.android.launcher");
        deletePackages.add("com.htc.launcher");
        deletePackages.add("com.android.launcher");
        deletePackages.add("com.android.launcher2");
        systemLauncher.add("com.huawei.android.launcher");
        systemLauncher.add("com.huawei.launcher2");
        systemLauncher.add("com.miui.home");
        systemLauncher.add("com.miui.mihome2");
        systemLauncher.add("com.android.launcher2");
        systemLauncher.add("com.sec.android.app.launcher");
        systemLauncher.add("com.sec.android.app.twlauncher");
        systemLauncher.add("com.cyanogenmod.trebuchet");
        systemLauncher.add("com.lenovo.launcher");
        systemLauncher.add("com.zte.mifavor.launcher");
        systemLauncher.add("com.android.launcher");
        systemLauncher.add("com.oppo.launcher");
        systemLauncher.add("com.htc.launcher");
        systemLauncher.add("com.sonyericsson.home");
        systemLauncher.add("com.android.launcher3");
        systemLauncher.add("com.yulong.android.launcher3");
        systemLauncher.add("com.oneplus.hydrogen.launcher");
        systemLauncher.add("com.smartisanos.launcher");
        duplicatePackages.add("com.huawei.launcher2");
        duplicatePackages.add("com.miui.home");
        duplicatePackages.add("com.miui.mihome2");
        duplicatePackages.add("com.lenovo.launcher");
        duplicatePackages.add("com.huawei.android.launcher");
        duplicatePackages.add("com.sec.android.app.twlauncher");
        duplicatePackages.add("com.yulong.android.launcher3");
    }

    public static boolean canCreateShortcut(Context context) {
        String launcherPackageName = LauncherUtil.getLauncherPackageName(context);
        if (TextUtils.isEmpty(launcherPackageName)) {
            return true;
        }
        if ("com.android.launcher3".equalsIgnoreCase(launcherPackageName) && AssistUtils.BRAND_VIVO.equalsIgnoreCase(getBrand())) {
            return false;
        }
        if (launcherPackageName.equals("com.android.launcher3") && Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
            return true;
        }
        if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.COOLPAD) && launcherPackageName.equals("com.yulong.android.launcher3") && Build.VERSION.SDK_INT >= 19) {
            return false;
        }
        return getHasTipPackageList().contains(launcherPackageName) || getNoTipPackageList().contains(launcherPackageName) || !getDisablePackageList().contains(launcherPackageName);
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static List<String> getDisablePackageList() {
        if (disablePackages.size() == 0) {
            disablePackages.addAll(selfDisablePackages);
            disablePackages.addAll(thirdDisablePackages);
        }
        return disablePackages;
    }

    public static List<String> getDuplicatePackages() {
        return duplicatePackages;
    }

    public static List<String> getHasTipPackageList() {
        return hasToastTipPackages;
    }

    public static List<String> getNoTipPackageList() {
        return noToastTipPackages;
    }

    public static List<String> getSystemLauncherList() {
        return systemLauncher;
    }

    public static boolean isDeleteLaucher(Context context) {
        return deletePackages.contains(LauncherUtil.getLauncherPackageName(context));
    }

    public static boolean isDisableShort(Context context) {
        String launcherPackageName = LauncherUtil.getLauncherPackageName(context);
        if (launcherPackageName.equals("com.bbk.launcher2") && Build.VERSION.SDK_INT >= 21) {
            return false;
        }
        if ("com.oppo.launcher".equals(launcherPackageName) && Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        if (launcherPackageName.equals("com.android.launcher3") && Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
            return false;
        }
        String str = Build.BRAND;
        if (str.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
            return false;
        }
        if (str.equalsIgnoreCase(MobilePhoneModel.COOLPAD) && launcherPackageName.equals("com.yulong.android.launcher3") && Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        if (MobilePhoneModel.isSpecialPhone(context)) {
            return false;
        }
        return getDisablePackageList().contains(launcherPackageName);
    }

    public static boolean isDuplicateLauncher(Context context) {
        if (LauncherUtil.getLauncherPackageName(context).equals("com.android.launcher3") && Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
            return true;
        }
        return duplicatePackages.contains(LauncherUtil.getLauncherPackageName(context));
    }

    public static boolean isSystemLauncher(Context context) {
        return getSystemLauncherList().contains(LauncherUtil.getLauncherPackageName(context));
    }

    public static boolean needToast(Context context) {
        return getNoTipPackageList().contains(LauncherUtil.getLauncherPackageName(context));
    }
}
