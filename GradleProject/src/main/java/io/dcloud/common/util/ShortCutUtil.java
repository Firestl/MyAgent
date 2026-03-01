package io.dcloud.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.umeng.analytics.pro.bm;
import io.dcloud.PdrR;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.constant.StringConst;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.qv;

/* JADX INFO: loaded from: classes2.dex */
public class ShortCutUtil {
    public static final String NOPERMISSIONS = "nopermissions";
    public static final String SHORTCUT_SRC_STREAM_APPS = "short_cut_src_stream_apps";
    public static final String SHORT_CUT_EXISTING = "short_cut_existing";
    public static final String SHORT_CUT_NONE = "short_cut_none";
    public static final String TAG = "ShortCutUtil";
    public static final String UNKNOWN = "unknown";
    public static String activityNameSDK = null;
    public static HashMap<String, String> extraProSDK = null;
    public static boolean mAutoCreateShortcut = true;
    public static TypeRunnable mRunnable;
    public boolean isChekShortCut;

    public interface TypeRunnable extends Runnable {
        String getType();

        void setType(String str);
    }

    public static void addShortCutSrc(Context context, Intent intent, String str) {
        intent.putExtra(IntentConst.SHORT_CUT_SRC, str.hashCode() + "_" + context.getPackageName());
    }

    public static void checkShortcutPermission(IApp iApp, String str, SharedPreferences sharedPreferences, String str2) {
        if (!PdrUtil.isEquals(iApp.forceShortCut(), "tipOnce")) {
            AppPermissionUtil.showShortCutOpsDialog(iApp, iApp.getActivity(), str, sharedPreferences);
            return;
        }
        if (sharedPreferences.getBoolean(str + str2, true)) {
            sharedPreferences.edit().putBoolean(str + str2, false).commit();
            AppPermissionUtil.showShortCutOpsDialog(iApp, iApp.getActivity(), str, sharedPreferences);
        }
    }

    public static void commitShortcut(IApp iApp, int i, int i2) {
        commitShortcut(iApp, i, false, false, false, i2);
    }

    public static void createShortcut(IApp iApp, String str, Bitmap bitmap, boolean z) {
        Bitmap bitmapDecodeResource;
        String strSubstring = str;
        Logger.e("StreamSDK", "come in createShortcut");
        Logger.e("IAN", "createShortcut: BaseInfo.mAutoCreateShortcut" + mAutoCreateShortcut);
        Logger.e("IAN", "createShortcut: ShortCutUtil.activityNameSDK" + activityNameSDK);
        if (iApp == null || TextUtils.isEmpty(str) || iApp.startFromShortCut() || iApp.forceShortCut().equals("none")) {
            if (iApp != null) {
                Logger.e("IAN", "createShortcut: filePath==" + strSubstring + "app.startFromShortCut()==" + iApp.startFromShortCut() + "app.forceShortCut().equals(none)==" + iApp.forceShortCut().equals("none"));
                return;
            }
            return;
        }
        if (PdrUtil.isEquals(iApp.forceShortCut(), "none")) {
            return;
        }
        Logger.e("StreamSDK", "come out return 1");
        Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
        boolean z2 = intentObtainWebAppIntent != null && intentObtainWebAppIntent.getIntExtra(IntentConst.START_FROM, -1) == 5;
        Logger.e("StreamSDK", "isMyRuning" + z2);
        if (z2) {
            return;
        }
        Activity activity = iApp.getActivity();
        String strObtainAppName = iApp.obtainAppName();
        String strObtainAppId = iApp.obtainAppId();
        if (bitmap == null) {
            if (!TextUtils.isEmpty(str) && strSubstring.startsWith("file://")) {
                strSubstring = strSubstring.substring(7);
            }
            bitmapDecodeResource = BitmapFactory.decodeFile(strSubstring);
        } else {
            bitmapDecodeResource = bitmap;
        }
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = BitmapFactory.decodeResource(activity.getResources(), PdrR.DRAWABLE_ICON);
        }
        Bitmap bitmap2 = bitmapDecodeResource;
        Intent intentObtainWebAppIntent2 = iApp.obtainWebAppIntent();
        String stringExtra = intentObtainWebAppIntent2 != null ? intentObtainWebAppIntent2.getStringExtra(IntentConst.WEBAPP_SHORT_CUT_CLASS_NAME) : "";
        if (hasShortcut(activity, strObtainAppName)) {
            Logger.e("StreamSDK", "ShortCutUtil.hasShortcut(context, name)");
            return;
        }
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(activity, "pdr");
        boolean z3 = orCreateBundle.getBoolean(strObtainAppId + SP.STAREMAPP_FIRST_SHORT_CUT, true);
        if (ShortcutCreateUtil.isDisableShort(iApp.getActivity())) {
            handleDisableShort(iApp.getActivity(), strObtainAppId, z3, orCreateBundle);
            orCreateBundle.edit().putBoolean(strObtainAppId + SP.STAREMAPP_FIRST_SHORT_CUT, false).commit();
            return;
        }
        if (MobilePhoneModel.isSpecialPhone(activity) && showSettingsDialog(iApp, strSubstring, bitmap)) {
            return;
        }
        String string = orCreateBundle.getString(AbsoluteConst.TEST_RUN + strObtainAppId, null);
        if (!TextUtils.isEmpty(string)) {
            string.equals("__am=t");
        }
        boolean z4 = orCreateBundle.getBoolean(strObtainAppId + SP.K_CREATED_SHORTCUT, false);
        if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU) && !AppPermissionUtil.isFlymeShortcutallowAllow(activity, getHeadShortCutIntent(strObtainAppName))) {
            checkShortcutPermission(iApp, strObtainAppId, orCreateBundle, SP.STAREMAPP_SHORTCUT_GUIDE_IS_FIRST_FLYME);
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI) && !AppPermissionUtil.isEmuiShortcutallowAllow()) {
            checkShortcutPermission(iApp, strObtainAppId, orCreateBundle, SP.STAREMAPP_SHORTCUT_GUIDE_IS_FIRST_EMUI);
            return;
        }
        if (!ShortcutCreateUtil.isDuplicateLauncher(activity) && iApp.forceShortCut().equals("auto") && z4) {
            return;
        }
        if (!z4) {
            SP.getOrCreateBundle(activity, "streamapp_create_shortcut").getBoolean("is_create_shortcut" + strObtainAppId, false);
        }
        if (orCreateBundle.getBoolean(SP.K_SHORT_CUT_ONE_TIPS, true)) {
            orCreateBundle.edit().putBoolean(SP.K_SHORT_CUT_ONE_TIPS, false).commit();
        }
        orCreateBundle.edit().putBoolean(strObtainAppId + SP.STAREMAPP_FIRST_SHORT_CUT, false).commit();
        if (createShortcutToDeskTop(activity, strObtainAppId, strObtainAppName, bitmap2, stringExtra, null, false)) {
            Logger.e("StreamSDK", "come into createShortcutToDeskTop and return ture already");
            if (z) {
                if (PdrUtil.isEquals(iApp.forceShortCut(), "tipOnce")) {
                    if (orCreateBundle.getBoolean(strObtainAppId + SP.STAREMAPP_SHORTCUT_TIP_IS_FIRST, true)) {
                        orCreateBundle.edit().putBoolean(strObtainAppId + SP.STAREMAPP_SHORTCUT_TIP_IS_FIRST, false).commit();
                        if (showToast(iApp, activity, strObtainAppId, orCreateBundle)) {
                            return;
                        }
                    }
                } else if (showToast(iApp, activity, strObtainAppId, orCreateBundle)) {
                    return;
                }
            } else if (isHasShortCut(iApp, 1000L, "auto")) {
                return;
            } else {
                showCreateShortCutToast(iApp);
            }
        }
        commitShortcut(iApp, 11, 0);
        orCreateBundle.edit().putString(strObtainAppId + SP.K_CREATE_SHORTCUT_NAME, strObtainAppName).commit();
        orCreateBundle.edit().putBoolean(strObtainAppId + SP.K_CREATED_SHORTCUT, true).commit();
    }

    public static int createShortcutGuide(IApp iApp, String str, Bitmap bitmap, boolean z, boolean z2, boolean z3) {
        Bitmap bitmapDecodeResource;
        String strSubstring = str;
        if (iApp == null || TextUtils.isEmpty(str) || iApp.startFromShortCut()) {
            return -1;
        }
        Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
        if (intentObtainWebAppIntent != null && intentObtainWebAppIntent.getIntExtra(IntentConst.START_FROM, -1) == 5) {
            return -1;
        }
        Activity activity = iApp.getActivity();
        String strObtainAppName = iApp.obtainAppName();
        String strObtainAppId = iApp.obtainAppId();
        if (bitmap == null) {
            if (!TextUtils.isEmpty(str) && strSubstring.startsWith("file://")) {
                strSubstring = strSubstring.substring(7);
            }
            bitmapDecodeResource = BitmapFactory.decodeFile(strSubstring);
        } else {
            bitmapDecodeResource = bitmap;
        }
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = BitmapFactory.decodeResource(activity.getResources(), PdrR.DRAWABLE_ICON);
        }
        Bitmap bitmap2 = bitmapDecodeResource;
        Intent intentObtainWebAppIntent2 = iApp.obtainWebAppIntent();
        String stringExtra = intentObtainWebAppIntent2 != null ? intentObtainWebAppIntent2.getStringExtra(IntentConst.WEBAPP_SHORT_CUT_CLASS_NAME) : "";
        if (hasShortcut(activity, strObtainAppName)) {
            return -1;
        }
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(activity, "pdr");
        boolean z4 = orCreateBundle.getBoolean(strObtainAppId + SP.STAREMAPP_FIRST_SHORT_CUT, true);
        if (ShortcutCreateUtil.isDisableShort(iApp.getActivity())) {
            handleDisableShort(iApp.getActivity(), strObtainAppId, z4, orCreateBundle);
            orCreateBundle.edit().putBoolean(strObtainAppId + SP.STAREMAPP_FIRST_SHORT_CUT, false).commit();
            return -1;
        }
        String string = orCreateBundle.getString(AbsoluteConst.TEST_RUN + strObtainAppId, null);
        if (!TextUtils.isEmpty(string)) {
            string.equals("__am=t");
        }
        boolean z5 = orCreateBundle.getBoolean(strObtainAppId + SP.K_CREATED_SHORTCUT, false);
        int iCheckNoShortcutPermionGuide = AppPermissionUtil.checkNoShortcutPermionGuide(activity, strObtainAppName, z3, iApp, strObtainAppId, orCreateBundle, z2);
        if (iCheckNoShortcutPermionGuide == 1) {
            return iCheckNoShortcutPermionGuide;
        }
        if (!ShortcutCreateUtil.isDuplicateLauncher(activity) && iApp.forceShortCut().equals("auto") && z5) {
            return iCheckNoShortcutPermionGuide;
        }
        if (!z5) {
            SP.getOrCreateBundle(activity, "streamapp_create_shortcut").getBoolean("is_create_shortcut" + strObtainAppId, false);
        }
        if (orCreateBundle.getBoolean(SP.K_SHORT_CUT_ONE_TIPS, true)) {
            orCreateBundle.edit().putBoolean(SP.K_SHORT_CUT_ONE_TIPS, false).commit();
        }
        orCreateBundle.edit().putBoolean(strObtainAppId + SP.STAREMAPP_FIRST_SHORT_CUT, false).commit();
        if (createShortcutToDeskTop(activity, strObtainAppId, strObtainAppName, bitmap2, stringExtra, null, false)) {
            if (!z) {
                showCreateShortCutToast(iApp);
            } else if (PdrUtil.isEquals(iApp.forceShortCut(), "tipOnce")) {
                if (orCreateBundle.getBoolean(strObtainAppId + SP.STAREMAPP_SHORTCUT_TIP_IS_FIRST, true)) {
                    orCreateBundle.edit().putBoolean(strObtainAppId + SP.STAREMAPP_SHORTCUT_TIP_IS_FIRST, false).commit();
                    if (showToast(iApp, activity, strObtainAppId, orCreateBundle)) {
                        return iCheckNoShortcutPermionGuide;
                    }
                }
            } else if (showToast(iApp, activity, strObtainAppId, orCreateBundle)) {
                return iCheckNoShortcutPermionGuide;
            }
        }
        commitShortcut(iApp, 11, 0);
        orCreateBundle.edit().putString(strObtainAppId + SP.K_CREATE_SHORTCUT_NAME, strObtainAppName).commit();
        orCreateBundle.edit().putBoolean(strObtainAppId + SP.K_CREATED_SHORTCUT, true).commit();
        return iCheckNoShortcutPermionGuide;
    }

    public static boolean createShortcutToDeskTop(Context context, String str, String str2, Bitmap bitmap, String str3, JSONObject jSONObject, boolean z) {
        return createShortcutToDeskTop(context, str, str2, bitmap, str3, jSONObject, z, false);
    }

    public static Intent getHeadShortCutIntent(String str) {
        Intent intent = new Intent(PlatformUtil.ACTION_INSTALL_SHORTCUT);
        intent.putExtra("android.intent.extra.shortcut.NAME", str);
        intent.putExtra(PlatformUtil.EXTRA_SHORTCUT_DUPLICATE, false);
        return intent;
    }

    public static String getShortCutUri(Context context) {
        ActivityInfo activityInfo;
        if (context == null) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null || activityInfo.packageName.equals("android")) {
            return "";
        }
        String str = resolveInfoResolveActivity.activityInfo.packageName;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return "content://" + str + ".settings/favorites?notify=true";
    }

    public static String getSreamAppShortName(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            ContentResolver contentResolver = context.getContentResolver();
            Uri uriFromLauncher = getUriFromLauncher(context);
            if (uriFromLauncher != null) {
                Cursor cursorQuery = contentResolver.query(uriFromLauncher, new String[]{"title", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK}, null, null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK));
                        if (!TextUtils.isEmpty(cursorQuery.getString(cursorQuery.getColumnIndex("title")))) {
                            TextUtils.isEmpty(string);
                        }
                    }
                }
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
            }
            return URLEncoder.encode(jSONArray.toString(), "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Uri getUriFromLauncher(Context context) {
        StringBuilder sb = new StringBuilder();
        String launcherPackageName = LauncherUtil.getLauncherPackageName(context);
        Logger.e("tag", "getUriFromLauncher: packageName" + launcherPackageName);
        if ("com.nd.android.pandahome2".equals(launcherPackageName)) {
            return Uri.parse("content://com.nd.android.launcher2.settings/com.nd.hilauncherdev/favorites?notify=true");
        }
        String authorityFromPermission = LauncherUtil.getAuthorityFromPermission(context, launcherPackageName + ".permission.READ_SETTINGS");
        Logger.e("TAG", "getUriFromLauncher: LauncherUtil.getAuthorityFromPermissionwithpackagename(" + authorityFromPermission);
        if (TextUtils.isEmpty(authorityFromPermission)) {
            authorityFromPermission = LauncherUtil.getAuthorityFromPermissionDefault(context);
            Logger.e("TAG", "getUriFromLauncher: LauncherUtil.getAuthorityFromPermissionDefault(" + authorityFromPermission);
        }
        if (TextUtils.isEmpty(authorityFromPermission)) {
            if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
                return Uri.parse("content://com.yulong.android.launcher3.compound/compoundworkspace?notify=false");
            }
            return null;
        }
        sb.append("content://");
        sb.append(authorityFromPermission);
        if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.OPPO)) {
            sb.append("/singledesktopitems?notify=true");
        } else {
            sb.append("/favorites?notify=true");
        }
        return Uri.parse(sb.toString());
    }

    public static void handleDisableShort(Activity activity, String str, boolean z, SharedPreferences sharedPreferences) {
    }

    public static boolean hasShortcut(Context context, String str) {
        return SHORT_CUT_EXISTING.equals(requestShortCut(context, str));
    }

    public static boolean isHasShortCut(final IApp iApp, long j, String str) {
        if (!Build.BRAND.equalsIgnoreCase(MobilePhoneModel.XIAOMI)) {
            return false;
        }
        TypeRunnable typeRunnable = mRunnable;
        if (typeRunnable != null) {
            if (typeRunnable.getType().equals("back") && str.equals(mRunnable.getType())) {
                return true;
            }
            removeRunHandler();
        }
        BaseInfo.isPostChcekShortCut = true;
        TypeRunnable typeRunnable2 = new TypeRunnable() { // from class: io.dcloud.common.util.ShortCutUtil.4
            public String type;

            @Override // io.dcloud.common.util.ShortCutUtil.TypeRunnable
            public String getType() {
                return this.type;
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseInfo.isPostChcekShortCut = false;
                if (!iApp.getActivity().isFinishing()) {
                    SharedPreferences orCreateBundle = SP.getOrCreateBundle(iApp.getActivity(), "pdr");
                    if (ShortCutUtil.hasShortcut(iApp.getActivity(), iApp.obtainAppName())) {
                        orCreateBundle.edit().putString(iApp.obtainAppId() + SP.K_CREATE_SHORTCUT_NAME, iApp.obtainAppName()).commit();
                        orCreateBundle.edit().putBoolean(iApp.obtainAppId() + SP.K_CREATED_SHORTCUT, true).commit();
                        ShortCutUtil.commitShortcut(iApp, 11, 0);
                        ShortCutUtil.showCreateShortCutToast(iApp);
                    } else if (AppPermissionUtil.getCheckShortcutOps(iApp.getActivity()) == 0) {
                        ShortCutUtil.createShortcutToDeskTop(iApp, true);
                        ShortCutUtil.commitShortcut(iApp, 11, 0);
                        ShortCutUtil.showCreateShortCutToast(iApp);
                    } else {
                        ShortCutUtil.commitShortcut(iApp, 11, 0);
                        IApp iApp2 = iApp;
                        AppPermissionUtil.showShortCutOpsDialog(iApp2, iApp2.getActivity(), iApp.obtainAppId(), orCreateBundle);
                    }
                }
                ShortCutUtil.mRunnable = null;
            }

            @Override // io.dcloud.common.util.ShortCutUtil.TypeRunnable
            public void setType(String str2) {
                this.type = str2;
            }
        };
        mRunnable = typeRunnable2;
        typeRunnable2.setType(str);
        MessageHandler.postDelayed(mRunnable, j);
        return true;
    }

    public static boolean isOpsCreateShortcut(Context context, String str) {
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, "pdr");
        boolean z = orCreateBundle.getBoolean(str + SP.IS_CREATE_SHORTCUT, false);
        if (z) {
            orCreateBundle.edit().remove(str + SP.IS_CREATE_SHORTCUT).commit();
        }
        return z;
    }

    public static boolean isRunShortCut(Context context, String str) {
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, "pdr");
        if (!orCreateBundle.getString(SP.RECORD_RUN_SHORT_CUT, "").equals(str)) {
            return false;
        }
        orCreateBundle.edit().remove(SP.RECORD_RUN_SHORT_CUT).commit();
        return true;
    }

    public static void onResumeCreateShortcut(IApp iApp) {
        boolean z;
        boolean zIsFlymeShortcutallowAllow;
        int checkShortcutOps = AppPermissionUtil.getCheckShortcutOps(iApp.getActivity());
        String str = Build.BRAND;
        if (str.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
            zIsFlymeShortcutallowAllow = AppPermissionUtil.isFlymeShortcutallowAllow(iApp.getActivity(), getHeadShortCutIntent(iApp.obtainAppName()));
            if (!zIsFlymeShortcutallowAllow) {
                commitShortcut(iApp, 12, true, false, true, 0);
            }
            z = false;
        } else {
            if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
                boolean zIsEmuiShortcutallowAllow = AppPermissionUtil.isEmuiShortcutallowAllow();
                if (!zIsEmuiShortcutallowAllow) {
                    commitShortcut(iApp, 12, true, false, true, 0);
                }
                z = zIsEmuiShortcutallowAllow;
            } else {
                if (checkShortcutOps == 1) {
                    commitShortcut(iApp, 12, true, false, true, 0);
                    AppPermissionUtil.checkShortcutOps(iApp, iApp.getActivity(), iApp.obtainAppId(), iApp.obtainAppName());
                    Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
                    createShortcutToDeskTop(iApp.getActivity(), iApp.obtainAppId(), iApp.obtainAppName(), null, intentObtainWebAppIntent != null ? intentObtainWebAppIntent.getStringExtra(IntentConst.WEBAPP_SHORT_CUT_CLASS_NAME) : "", null, false);
                    return;
                }
                z = false;
            }
            zIsFlymeShortcutallowAllow = false;
        }
        if (str.equalsIgnoreCase(MobilePhoneModel.GOOGLE)) {
            str = Build.MANUFACTURER;
        }
        if (MobilePhoneModel.SMARTISAN.equals(str) || ((checkShortcutOps != 1 && MobilePhoneModel.XIAOMI.equals(str)) || ((MobilePhoneModel.MEIZU.equals(str) && zIsFlymeShortcutallowAllow) || (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI) && z)))) {
            commitShortcut(iApp, 12, true, true, true, 0);
        }
        createShortcut(iApp, null, null, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void onResumeCreateShortcutGuide(io.dcloud.common.DHInterface.IApp r16) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.ShortCutUtil.onResumeCreateShortcutGuide(io.dcloud.common.DHInterface.IApp):void");
    }

    public static void removeRunHandler() {
        if (mRunnable != null) {
            BaseInfo.isPostChcekShortCut = false;
            MessageHandler.removeCallbacks(mRunnable);
        }
    }

    public static boolean removeShortcutFromDeskTop(Context context, String str, String str2, String str3, JSONObject jSONObject) {
        Intent intent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", str2);
        intent.putExtra(PlatformUtil.EXTRA_SHORTCUT_DUPLICATE, false);
        Intent intent2 = new Intent();
        if (TextUtils.isEmpty(str3)) {
            intent2 = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        } else {
            if (PdrUtil.isEmpty(activityNameSDK)) {
                intent2.setClassName(context.getPackageName(), str3);
            } else {
                intent2.putExtra(IntentConst.WEBAPP_ACTIVITY_SHORTCUTACTIVITY, activityNameSDK);
                intent2.setClassName(context.getPackageName(), activityNameSDK);
            }
            intent2.setAction("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
        }
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                try {
                    String next = itKeys.next();
                    intent2.putExtra(next, jSONObject.getString(next));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        addShortCutSrc(context, intent2, str2);
        intent2.putExtra(IntentConst.SHORT_CUT_APPID, str);
        intent2.putExtra(IntentConst.FROM_SHORT_CUT_STRAT, true);
        intent2.setFlags(268435456);
        intent2.setData(Uri.parse("http://m3w.cn/s/" + str));
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        context.sendBroadcast(intent);
        return true;
    }

    public static String requestShortCut(Context context, String str) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriFromLauncher = getUriFromLauncher(context);
        if (uriFromLauncher == null) {
            String shortCutUri = getShortCutUri(context);
            if (!TextUtils.isEmpty(shortCutUri)) {
                uriFromLauncher = Uri.parse(shortCutUri);
                Logger.es("shortcututil", context.getString(R.string.dcloud_short_cut_err1) + uriFromLauncher);
            }
        }
        Uri uri = uriFromLauncher;
        Logger.e("shortcututil", "requestShortCut: uri===" + uri);
        String str2 = "unknown";
        if (uri != null) {
            try {
                Cursor cursorQuery = contentResolver.query(uri, new String[]{"title", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK}, "title=? ", new String[]{str}, null);
                if (cursorQuery == null || cursorQuery.getCount() <= 0) {
                    str2 = SHORT_CUT_NONE;
                } else {
                    Logger.e("shortcututil", "c != null && c.getCount() > 0");
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK));
                        if (TextUtils.isEmpty(string)) {
                            str2 = SHORT_CUT_NONE;
                        } else {
                            Logger.e("shortcututil", "intent=====" + string);
                            if (string.contains(IntentConst.SHORT_CUT_APPID)) {
                                if (!BaseInfo.isBase(context)) {
                                    if (string.contains("io.dcloud.appstream.StreamAppMainActivity")) {
                                        if (string.contains(str.hashCode() + "_" + context.getPackageName())) {
                                        }
                                    }
                                }
                                str2 = SHORT_CUT_EXISTING;
                            }
                        }
                    }
                }
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                if (e2.getMessage() != null && e2.getMessage().contains("READ_SETTINGS")) {
                    str2 = NOPERMISSIONS;
                }
                Logger.es("shortcututil", e2.getMessage() + "URI==" + uri);
                e2.printStackTrace();
            }
        }
        return str2;
    }

    public static String requestShortCutForCommit(Context context, String str) {
        return requestShortCut(context, str);
    }

    public static String requestShortCutPermissionVivo(Context context, String str) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriFromLauncher = getUriFromLauncher(context);
        if (uriFromLauncher == null) {
            String shortCutUri = getShortCutUri(context);
            if (!TextUtils.isEmpty(shortCutUri)) {
                uriFromLauncher = Uri.parse(shortCutUri);
            }
        }
        Uri uri = uriFromLauncher;
        Logger.e("shortcututil", "requestShortCut: uri===" + uri);
        if (uri == null) {
            return "-1";
        }
        try {
            Cursor cursorQuery = contentResolver.query(uri, new String[]{"title", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "shortcutPermission"}, "title=? ", new String[]{str}, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                Logger.e("shortcututil", "c != null && c.getCount() > 0");
                if (cursorQuery.moveToNext()) {
                    return cursorQuery.getString(cursorQuery.getColumnIndex("shortcutPermission"));
                }
            }
            if (cursorQuery == null || cursorQuery.isClosed()) {
                return "-1";
            }
            cursorQuery.close();
            return "-1";
        } catch (Exception e2) {
            Logger.es("shortcututil", e2.getMessage() + "URI==" + uri);
            e2.printStackTrace();
            return "-1";
        }
    }

    public static void showCreateShortCutToast(IApp iApp) {
        String str = StringUtil.format(iApp.getActivity().getString(R.string.dcloud_short_cut_created), iApp.obtainAppName());
        if (!iApp.forceShortCut().equals(AbsoluteConst.INSTALL_OPTIONS_FORCE) || ShortcutCreateUtil.isDuplicateLauncher(iApp.getActivity())) {
            if (ShortcutCreateUtil.needToast(iApp.getActivity())) {
                qv.makeText(iApp.getActivity().getApplicationContext(), (CharSequence) str, 1).show();
                return;
            }
            return;
        }
        String str2 = "“" + iApp.obtainAppName() + iApp.getActivity().getString(R.string.dcloud_short_cut_created_tip);
        if (ShortcutCreateUtil.needToast(iApp.getActivity())) {
            qv.makeText(iApp.getActivity().getApplicationContext(), (CharSequence) str2, 1).show();
        }
    }

    public static boolean showSettingsDialog(final IApp iApp, final String str, final Bitmap bitmap) {
        String str2;
        String string;
        String string2;
        final SharedPreferences orCreateBundle = SP.getOrCreateBundle(iApp.getActivity(), "pdr");
        if (!orCreateBundle.getBoolean(SP.K_SHORT_CUT_ONE_TIPS, true) && !Build.BRAND.equals(MobilePhoneModel.SMARTISAN)) {
            return false;
        }
        if (orCreateBundle.getBoolean(SP.K_SHORT_CUT_ONE_TIPS, true)) {
            orCreateBundle.edit().putBoolean(SP.K_SHORT_CUT_ONE_TIPS, false).commit();
        }
        String string3 = iApp.getActivity().getString(R.string.dcloud_short_cut_goto_pms);
        String string4 = iApp.getActivity().getString(R.string.dcloud_short_cut_it_set);
        String str3 = Build.BRAND;
        if (str3.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
            string2 = iApp.getActivity().getString(R.string.dcloud_short_cut_qiku1);
            string = iApp.getActivity().getString(R.string.dcloud_short_cut_goto_run);
        } else {
            if (str3.equalsIgnoreCase(MobilePhoneModel.VIVO)) {
                if (!LoadAppUtils.isAppLoad(iApp.getActivity(), "com.iqoo.secure") || Build.VERSION.SDK_INT < 21) {
                    return false;
                }
                str2 = iApp.getActivity().getString(R.string.dcloud_short_cut_vivo1) + "App" + iApp.getActivity().getString(R.string.dcloud_short_cut_vivo2);
            } else if (str3.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
                string = string3;
                string2 = iApp.getActivity().getString(R.string.dcloud_short_cut_chuizi1);
                string4 = iApp.getActivity().getString(R.string.dcloud_short_cut_not_install);
            } else {
                str2 = "";
            }
            String str4 = str2;
            string = string3;
            string2 = str4;
        }
        AlertDialog.Builder builderInitDialogTheme = DialogUtil.initDialogTheme(iApp.getActivity(), true);
        builderInitDialogTheme.setMessage(string2).setTitle(iApp.getActivity().getString(R.string.dcloud_short_cut_tips)).setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.ShortCutUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                orCreateBundle.edit().putString(SP.RECORD_RUN_SHORT_CUT, iApp.obtainAppId()).commit();
                String str5 = Build.BRAND;
                if (str5.equalsIgnoreCase(MobilePhoneModel.QiKU) || str5.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
                    if (str5.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
                        intent = new Intent();
                        intent.setClassName("com.yulong.android.launcher3", "com.yulong.android.launcher3.LauncherSettingsActivity");
                    } else {
                        intent = new Intent("android.settings.SETTINGS");
                    }
                    iApp.getActivity().startActivity(intent);
                    return;
                }
                if (str5.equalsIgnoreCase(MobilePhoneModel.VIVO)) {
                    PackageManager packageManager = iApp.getActivity().getPackageManager();
                    new Intent();
                    Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.iqoo.secure");
                    launchIntentForPackage.setFlags(337641472);
                    iApp.getActivity().startActivity(launchIntentForPackage);
                }
            }
        }).setNegativeButton(string4, new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.ShortCutUtil.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
                    ShortCutUtil.commitShortcut(iApp, 12, true, false, false, 0);
                } else {
                    ShortCutUtil.createShortcut(iApp, str, bitmap, false);
                }
            }
        });
        AlertDialog alertDialogCreate = builderInitDialogTheme.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        return true;
    }

    public static boolean showToast(IApp iApp, Activity activity, String str, SharedPreferences sharedPreferences) {
        if ("12214060304".equals(BaseInfo.sChannel) && "com.aliyun.homeshell".equals(LauncherUtil.getLauncherPackageName(activity))) {
            if (sharedPreferences.getBoolean(str + SP.STAREMAPP_ALIYUN_SHORT_CUT_IS_FIRST_CREATED, true)) {
                showCreateShortCutToast(iApp);
                sharedPreferences.edit().putBoolean(str + SP.STAREMAPP_ALIYUN_SHORT_CUT_IS_FIRST_CREATED, false).commit();
            }
        } else {
            showCreateShortCutToast(iApp);
        }
        return false;
    }

    public static void updateShortcutFromDeskTop(Activity activity, String str, String str2, Bitmap bitmap, String str3) {
        removeShortcutFromDeskTop(activity, str, str2, str3, null);
        createShortcutToDeskTop(activity, str, str2, bitmap, str3, null, false);
    }

    public static void commitShortcut(IApp iApp, int i, boolean z, boolean z2, boolean z3, int i2) {
        commitShortcut(iApp, i, z, z2, z3, i2, null);
    }

    public static boolean createShortcutToDeskTop(IApp iApp, boolean z) {
        Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
        return createShortcutToDeskTop(iApp.getActivity(), iApp.obtainAppId(), iApp.obtainAppName(), null, intentObtainWebAppIntent != null ? intentObtainWebAppIntent.getStringExtra(IntentConst.WEBAPP_SHORT_CUT_CLASS_NAME) : "", null, false, z);
    }

    public static void commitShortcut(final IApp iApp, final int i, final boolean z, final boolean z2, final boolean z3, final int i2, final String str) {
        new Thread() { // from class: io.dcloud.common.util.ShortCutUtil.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                String str2;
                String strObtainAppName = str;
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (TextUtils.isEmpty(strObtainAppName)) {
                    strObtainAppName = iApp.obtainAppName();
                }
                int iCheckPermission = AppPermissionUtil.checkPermission(iApp.getActivity(), strObtainAppName);
                Intent intent = iApp.getActivity().getIntent();
                iApp.obtainAppVersionName();
                String str3 = StringConst.STREAMAPP_KEY_BASESERVICEURL() + "collect/startup?s=" + i + "&" + DataInterface.getUrlBaseData(iApp.getActivity(), iApp.obtainAppId(), BaseInfo.getLaunchType(iApp.getActivity().getIntent()), DataInterface.getStreamappFrom(intent)) + "&romv=" + DataInterface.getRomVersion() + "&scf=" + i2 + "&scp=" + iCheckPermission + "&v=" + PdrUtil.encodeURL(iApp.obtainAppVersionName());
                if (z) {
                    str2 = str3 + "&scr=" + (z2 ? 1 : 0) + "&scs=" + (z3 ? 1 : 0);
                } else {
                    String strRequestShortCutForCommit = ShortCutUtil.requestShortCutForCommit(iApp.getActivity(), strObtainAppName);
                    str2 = str3 + "&sc=" + (ShortCutUtil.SHORT_CUT_EXISTING.equals(strRequestShortCutForCommit) ? "s" : ShortCutUtil.SHORT_CUT_NONE.equals(strRequestShortCutForCommit) ? "n" : bm.aL);
                }
                try {
                    NetTool.httpGet(str2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }.start();
    }

    public static boolean createShortcutToDeskTop(Context context, String str, String str2, Bitmap bitmap, String str3, JSONObject jSONObject, boolean z, boolean z2) {
        String str4;
        Intent headShortCutIntent = getHeadShortCutIntent(str2);
        Intent intent = new Intent();
        if (TextUtils.isEmpty(str3)) {
            intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        } else {
            intent.setClassName(context.getPackageName(), str3);
            intent.setAction("android.intent.action.MAIN");
        }
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                try {
                    String next = itKeys.next();
                    intent.putExtra(next, jSONObject.getString(next));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        addShortCutSrc(context, intent, str2);
        intent.putExtra(IntentConst.SHORT_CUT_APPID, str);
        intent.putExtra(IntentConst.FROM_SHORT_CUT_STRAT, true);
        intent.setFlags(268435456);
        StringBuilder sb = new StringBuilder();
        sb.append("http://m3w.cn/s/");
        sb.append(str);
        if (z2) {
            str4 = "&time=" + System.currentTimeMillis();
        } else {
            str4 = "";
        }
        sb.append(str4);
        intent.setData(Uri.parse(sb.toString()));
        headShortCutIntent.putExtra("android.intent.extra.shortcut.INTENT", intent);
        headShortCutIntent.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        context.sendBroadcast(headShortCutIntent);
        return true;
    }
}
