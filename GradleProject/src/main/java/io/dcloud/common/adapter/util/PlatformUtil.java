package io.dcloud.common.adapter.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import com.baidu.speech.utils.analysis.Analysis;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.bq;
import com.umeng.ccg.a;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.LoadAppUtils;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlatformUtil extends SP {
    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    public static boolean APS_COVER = false;
    public static final byte ASSETS_RESOUCE = 0;
    public static final String EXTRA_SHORTCUT_DUPLICATE = "duplicate";
    public static final byte FILE_RESOUCE = 2;
    public static int MAX_SPAN_IN_ONE_SCREEN = 16;
    public static final String[] PROJECTION = {bq.d, "title", "iconResource"};
    public static final byte SRC_RESOUCE = 1;
    public static final String URI_HTC_LAUNCER = "content://com.htc.launcher.settings/favorites?notify=true";
    public static final String URI_SAMSUNG_LAUNCER = "content://com.sec.android.app.twlauncher.settings/favorites?notify=true";
    public static int _SCREEN_CONTENT_HEIGHT;
    public static int _SCREEN_HEIGHT;
    public static int _SCREEN_STATUSBAR_HEIGHT;
    public static int _SCREEN_WIDTH;
    public static int[] _blackpixels;
    public static int[] _pixels;

    public static class APKInfo {
        public String mAppName;
        public Drawable mIcon;
    }

    public static int[] GET_BLACK_LINE(int i) {
        int[] iArr = _blackpixels;
        if (iArr == null || iArr.length != i) {
            _blackpixels = new int[i];
            int i2 = 0;
            while (true) {
                int[] iArr2 = _blackpixels;
                if (i2 >= iArr2.length) {
                    break;
                }
                iArr2[i2] = -16777216;
                i2++;
            }
        }
        return _blackpixels;
    }

    public static int[] GET_WIHTE_LINE(int i) {
        int[] iArr = _pixels;
        if (iArr == null || iArr.length != i) {
            _pixels = new int[i];
            int i2 = 0;
            while (true) {
                int[] iArr2 = _pixels;
                if (i2 >= iArr2.length) {
                    break;
                }
                iArr2[i2] = -1;
                i2++;
            }
        }
        return _pixels;
    }

    public static int MESURE_SCREEN_CONTENT_HEIGHT(Activity activity) {
        if (_SCREEN_CONTENT_HEIGHT == 0) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            _SCREEN_STATUSBAR_HEIGHT = rect.top;
            int iHeight = rect.height();
            _SCREEN_CONTENT_HEIGHT = iHeight;
            if (_SCREEN_STATUSBAR_HEIGHT < 0 || iHeight > SCREEN_HEIGHT(activity)) {
                _SCREEN_STATUSBAR_HEIGHT = 0;
                _SCREEN_CONTENT_HEIGHT = SCREEN_HEIGHT(activity);
            }
        }
        return _SCREEN_CONTENT_HEIGHT;
    }

    public static int MESURE_SCREEN_STATUSBAR_HEIGHT(Activity activity) {
        if (_SCREEN_STATUSBAR_HEIGHT == 0) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            _SCREEN_STATUSBAR_HEIGHT = rect.top;
            int iHeight = rect.height();
            _SCREEN_CONTENT_HEIGHT = iHeight;
            if (_SCREEN_STATUSBAR_HEIGHT < 0 || iHeight > SCREEN_HEIGHT(activity)) {
                _SCREEN_STATUSBAR_HEIGHT = 0;
                _SCREEN_CONTENT_HEIGHT = SCREEN_HEIGHT(activity);
            }
        }
        return _SCREEN_STATUSBAR_HEIGHT;
    }

    public static void RESET_H_W() {
        _SCREEN_WIDTH = 0;
        _SCREEN_HEIGHT = 0;
        _SCREEN_STATUSBAR_HEIGHT = 0;
        _SCREEN_CONTENT_HEIGHT = 0;
        _pixels = null;
    }

    public static int SCREEN_HEIGHT(Context context) {
        if (_SCREEN_HEIGHT == 0) {
            _SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
        }
        return _SCREEN_HEIGHT;
    }

    public static int SCREEN_WIDTH(Context context) {
        if (_SCREEN_WIDTH == 0) {
            _SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
        }
        return _SCREEN_WIDTH;
    }

    public static Bitmap captureView(View view) {
        return captureView(view, true, true, null, "ARGB");
    }

    public static boolean checkClass(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean checkGTAndYoumeng() {
        return checkClass("io.dcloud.feature.apsGt.GTPushService") || checkClass("io.dcloud.feature.statistics.UmengStatisticsMgr");
    }

    public static boolean checkLauncherScreenSpace(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse(URI_SAMSUNG_LAUNCER), new String[]{Analysis.KEY_SCREEN, "spanX", "spanY"}, null, null, null);
        if (cursorQuery == null) {
            return true;
        }
        int iQueryMaxLauncherScreenCount = queryMaxLauncherScreenCount(context);
        Logger.e("PlatformUtil", "Samsung Launcher: max screen num = " + iQueryMaxLauncherScreenCount);
        int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("spanX");
        int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("spanY");
        int i = iQueryMaxLauncherScreenCount * MAX_SPAN_IN_ONE_SCREEN;
        while (cursorQuery.moveToNext()) {
            try {
                try {
                    i -= cursorQuery.getInt(columnIndexOrThrow) * cursorQuery.getInt(columnIndexOrThrow2);
                } catch (Exception e2) {
                    Logger.e("PlatformUtil", "Check Launcher space" + e2);
                    cursorQuery.close();
                    i = 0;
                }
            } finally {
                cursorQuery.close();
            }
        }
        return i > 0;
    }

    public static void createShortut(Context context, String str, String str2, int i, boolean z) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setClassName(context, str2);
        Intent intent2 = new Intent(ACTION_INSTALL_SHORTCUT);
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context, i));
        intent2.putExtra(EXTRA_SHORTCUT_DUPLICATE, z);
        context.sendBroadcast(intent2);
    }

    public static void destroyDrawingCache(View view) {
        view.destroyDrawingCache();
    }

    public static void disableWebViewMultiProcess(Context context) {
        Object objInvokeFieldValue;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                if (Settings.Global.getInt(context.getContentResolver(), "webview_multiprocess", 0) == 1) {
                    Object objInvokeFieldValue2 = invokeFieldValue(Settings.Global.class.getName(), "sNameValueCache", Settings.Global.class.newInstance());
                    if (objInvokeFieldValue2 == null || (objInvokeFieldValue = invokeFieldValue(objInvokeFieldValue2.getClass().getName(), "mValues", objInvokeFieldValue2)) == null || !(objInvokeFieldValue instanceof HashMap)) {
                        return;
                    }
                    ((HashMap) objInvokeFieldValue).put("webview_multiprocess", "0");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static APKInfo getApkFileInfo(Context context, String str) {
        String str2;
        PackageInfo packageArchiveInfo;
        ApplicationInfo applicationInfo;
        APKInfo aPKInfo = new APKInfo();
        Drawable drawable = null;
        try {
            packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        } catch (Throwable th) {
            th = th;
            str2 = null;
        }
        if (packageArchiveInfo != null && (applicationInfo = packageArchiveInfo.applicationInfo) != null) {
            Class<?> cls = Class.forName("android.content.res.AssetManager");
            AssetManager assetManager = (AssetManager) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            cls.getDeclaredMethod("addAssetPath", String.class).invoke(assetManager, str);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Resources resources = new Resources(assetManager, displayMetrics, context.getResources().getConfiguration());
            int i = applicationInfo.icon;
            if (i == 0) {
                str2 = null;
            } else {
                str2 = (String) resources.getText(applicationInfo.labelRes);
                try {
                    drawable = resources.getDrawable(i);
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                }
            }
            aPKInfo.mIcon = drawable;
            aPKInfo.mAppName = str2;
            return aPKInfo;
        }
        return aPKInfo;
    }

    public static ActivityInfo getBestActivityInfo(List<ResolveInfo> list, LinkedList<String> linkedList) {
        LinkedList linkedList2 = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo resolveInfo = list.get(i);
            if (linkedList.contains(resolveInfo.activityInfo.packageName)) {
                linkedList2.add(resolveInfo.activityInfo);
            }
        }
        int i2 = Integer.MAX_VALUE;
        ActivityInfo activityInfo = null;
        for (int i3 = 0; i3 < linkedList2.size(); i3++) {
            ActivityInfo activityInfo2 = (ActivityInfo) linkedList2.get(i3);
            int iIndexOf = linkedList.indexOf(activityInfo2.packageName);
            if (iIndexOf < i2) {
                activityInfo = activityInfo2;
                i2 = iIndexOf;
            }
        }
        return activityInfo;
    }

    public static byte[] getFileContent(String str, int i) {
        InputStream inputStream = getInputStream(str, i);
        byte[] bytes = null;
        try {
            if (inputStream != null) {
                try {
                    bytes = IOUtil.getBytes(inputStream);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return bytes;
        } finally {
            IOUtil.close(inputStream);
        }
    }

    public static String getFileContent4S(String str) {
        return new String(getFileContent(str, 0)).replace("p", "");
    }

    public static String getFilePathFromContentUri(Uri uri, ContentResolver contentResolver) {
        String[] strArr = {"_data"};
        Cursor cursorQuery = contentResolver.query(uri, strArr, null, null, null);
        cursorQuery.moveToFirst();
        String string = cursorQuery.getString(cursorQuery.getColumnIndex(strArr[0]));
        cursorQuery.close();
        return string;
    }

    public static InputStream getInputStream(String str, int i) {
        try {
            String str2 = DeviceInfo.sDeviceRootDir;
            if (str2 != null && str.startsWith(str2)) {
                i = 2;
            }
        } catch (Exception e2) {
            Logger.e("platform", e2.toString());
        }
        if (i == 0) {
            return getResInputStream(str);
        }
        if (i == 1) {
            return PlatformUtil.class.getClassLoader().getResourceAsStream(str);
        }
        if (i == 2) {
            File file = new File(str);
            if (file.exists()) {
                return FileUtil.getFileInputStream(DCLoudApplicationImpl.self().getContext(), file);
            }
        }
        return null;
    }

    public static InputStream getResInputStream(String str) {
        try {
            String strUseAndroidPath = useAndroidPath(str);
            if (!TextUtils.isEmpty(strUseAndroidPath)) {
                if (strUseAndroidPath.startsWith("assets://")) {
                    strUseAndroidPath = strUseAndroidPath.replace("assets://", "");
                } else if (strUseAndroidPath.startsWith("android_asset/")) {
                    strUseAndroidPath = strUseAndroidPath.replace("android_asset/", "");
                } else if (strUseAndroidPath.startsWith("file:///android_asset/")) {
                    strUseAndroidPath = strUseAndroidPath.replace("file:///android_asset/", "");
                }
            }
            return AndroidResources.sAssetMgr.open(strUseAndroidPath, 2);
        } catch (FileNotFoundException unused) {
            Logger.e("PlatformUtil.getResInputStream FileNotFoundException pFilePath=" + str);
            return null;
        } catch (IOException unused2) {
            Logger.e("PlatformUtil.getResInputStream IOException pFilePath=" + str);
            return null;
        } catch (RuntimeException unused3) {
            Logger.e("PlatformUtil.getResInputStream RuntimeException pFilePath=" + str);
            return null;
        } catch (Exception unused4) {
            Logger.e("PlatformUtil.getResInputStream Exception pFilePath=" + str);
            return null;
        }
    }

    public static String getSechemeList(Context context, String str) {
        try {
            JSONArray jSONArray = new JSONArray();
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (listQueryIntentActivities != null) {
                int size = listQueryIntentActivities.size();
                for (int i = 0; i < size; i++) {
                    ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("pn", resolveInfo.activityInfo.packageName);
                    jSONObject.put(com.tencent.stat.DeviceInfo.TAG_VERSION, context.getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, 0).versionName);
                    jSONArray.put(jSONObject);
                }
            }
            return URLEncoder.encode(jSONArray.toString(), "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getStartupSechemeList(Context context, String str) {
        try {
            JSONArray jSONArray = new JSONArray();
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (listQueryIntentActivities != null) {
                int size = listQueryIntentActivities.size();
                for (int i = 0; i < size; i++) {
                    ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("pn", resolveInfo.activityInfo.packageName);
                    jSONObject.put(com.tencent.stat.DeviceInfo.TAG_VERSION, context.getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, 0).versionName);
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean hasAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new File("/sdcard/Android/data/" + str).exists();
        }
    }

    public static void init(Context context) {
        DeviceInfo.sApplicationContext = context;
    }

    public static boolean initComponentName(Context context, Intent intent, String str) {
        try {
            Intent intent2 = new Intent();
            intent2.setData(Uri.parse("streamapp://"));
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent2, 0);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
                return false;
            }
            LinkedList linkedList = new LinkedList();
            linkedList.add("com.gionee.aora.market");
            linkedList.add("com.ivvi.browser");
            linkedList.add("com.letv.app.appstore");
            linkedList.add("com.qihoo.appstore");
            linkedList.add("com.aspire.mm");
            linkedList.add("io.dcloud.streamapps");
            ActivityInfo bestActivityInfo = getBestActivityInfo(listQueryIntentActivities, linkedList);
            if (bestActivityInfo == null) {
                return false;
            }
            intent.setClassName(bestActivityInfo.packageName, bestActivityInfo.name);
            if ("com.qihoo.appstore".equals(bestActivityInfo.packageName)) {
                intent.putExtra(IntentConst.SHORT_CUT_APPID, str);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static Object invokeFieldValue(String str, String str2, Object obj) {
        Class<?> superclass;
        if (obj != null) {
            try {
                try {
                    superclass = obj.getClass();
                } catch (ClassNotFoundException e2) {
                    Logger.i("platform", e2.toString());
                }
            } catch (Throwable th) {
                Logger.i("platform", th.toString());
            }
        } else {
            superclass = null;
        }
        if (superclass == null) {
            superclass = Class.forName(str);
        }
        if (superclass != null) {
            Field declaredField = null;
            while (superclass != null) {
                try {
                    declaredField = superclass.getField(str2);
                } catch (Exception unused) {
                }
                if (declaredField != null) {
                    break;
                }
                if (declaredField == null) {
                    try {
                        declaredField = superclass.getDeclaredField(str2);
                        if (declaredField != null) {
                            break;
                        }
                    } catch (Exception unused2) {
                        continue;
                    }
                }
                superclass = superclass.getSuperclass();
            }
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            }
        }
        return null;
    }

    public static Object invokeMethod(String str, String str2) {
        return invokeMethod(str, str2, null, new Class[0], new Object[0]);
    }

    public static boolean invokeSetFieldValue(Object obj, String str, Object obj2) {
        if (obj == null) {
            return false;
        }
        try {
            Class<?> superclass = obj.getClass();
            if (superclass != null) {
                Field declaredField = null;
                while (superclass != null) {
                    try {
                        declaredField = superclass.getField(str);
                    } catch (Exception unused) {
                    }
                    if (declaredField != null) {
                        break;
                    }
                    if (declaredField == null) {
                        try {
                            declaredField = superclass.getDeclaredField(str);
                            if (declaredField != null) {
                                break;
                            }
                        } catch (Exception unused2) {
                            continue;
                        }
                    }
                    superclass = superclass.getSuperclass();
                }
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(obj, obj2);
                    return true;
                }
            }
        } catch (Throwable th) {
            Logger.i("platform", th.toString());
        }
        return false;
    }

    public static boolean isAppInstalled(Context context, String str) {
        return LoadAppUtils.isAppLoad(context, str);
    }

    public static boolean isEmulator() {
        String str = Build.MODEL;
        return str.equals(a.r) || str.equals("google_sdk");
    }

    public static boolean isLineWhiteBitmap(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int[] iArr = new int[width];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), 1);
        boolean zEquals = Arrays.equals(iArr, GET_WIHTE_LINE(width));
        return z ? zEquals | Arrays.equals(iArr, GET_BLACK_LINE(width)) : zEquals;
    }

    public static boolean isPureColor(int[] iArr) {
        int i = iArr[0];
        boolean z = true;
        for (int i2 : iArr) {
            if (i != i2) {
                z = false;
            }
        }
        return z;
    }

    public static boolean isResFileExists(String str) {
        try {
            InputStream inputStreamOpen = AndroidResources.sAssetMgr.open(str);
            if (inputStreamOpen == null) {
                return false;
            }
            inputStreamOpen.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isWhiteBitmap(Bitmap bitmap) {
        return isWhiteBitmap(bitmap, false, false);
    }

    public static void launchApplication(Context context, String str, String str2, HashMap map, boolean z) throws Exception {
        Intent intent = PdrUtil.isEmpty(str2) ? new Intent("android.intent.action.MAIN") : new Intent(str2);
        if (!PdrUtil.isEmpty(str) && !setPackageName(context, intent, str)) {
            throw new RuntimeException();
        }
        if (z) {
            intent.setFlags(268435456);
        }
        if (map != null && !map.isEmpty()) {
            for (String str3 : map.keySet()) {
                Object obj = map.get(str3);
                if (obj instanceof Integer) {
                    intent.putExtra(str3, ((Integer) obj).intValue());
                } else if (obj instanceof String) {
                    intent.putExtra(str3, (String) obj);
                } else if (obj instanceof Boolean) {
                    intent.putExtra(str3, ((Boolean) obj).booleanValue());
                }
            }
        }
        context.startActivity(intent);
    }

    public static String[] listFsAppsFiles(String str) {
        try {
            return new File(str).list();
        } catch (Exception e2) {
            Logger.w("PlatformUtil.listResFiles pPath=" + str, e2);
            return null;
        }
    }

    public static String[] listResFiles(String str) {
        try {
            return AndroidResources.sAssetMgr.list(useAndroidPath(str));
        } catch (IOException e2) {
            Logger.w("PlatformUtil.listResFiles pPath=" + str, e2);
            return null;
        }
    }

    public static Object newInstance(String str, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Exception e2) {
            Logger.i("platform", e2.toString());
            return null;
        } catch (Throwable th) {
            Logger.i("platform", th.toString());
            return null;
        }
    }

    public static void openFileBySystem(Context context, String str, String str2, String str3, ICallBack iCallBack) {
        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        }
        boolean zHasAppInstalled = TextUtils.isEmpty(str2) ^ true ? hasAppInstalled(context, str2) : false;
        try {
            String mimeType = PdrUtil.getMimeType(str);
            if (str.startsWith("file://")) {
                str = str.substring(7);
            }
            if (str.startsWith("content://")) {
                str = getFilePathFromContentUri(Uri.parse(str), context.getContentResolver());
                mimeType = PdrUtil.getMimeType(str);
            }
            if (PdrUtil.isEmpty(str3)) {
                str3 = mimeType;
            }
            Intent dataAndTypeIntent = LoadAppUtils.getDataAndTypeIntent(context, str, str3);
            if (zHasAppInstalled) {
                dataAndTypeIntent.setPackage(str2);
            }
            if (new File(str).exists()) {
                context.startActivity(dataAndTypeIntent);
                iCallBack.onCallBack(1, "{}");
            } else if (iCallBack != null) {
                iCallBack.onCallBack(-1, StringUtil.format(DOMException.JSON_ERROR_INFO, 0, DOMException.MSG_NOT_FOUND_FILE));
            }
        } catch (ActivityNotFoundException e2) {
            Logger.w(e2);
            if (iCallBack != null) {
                iCallBack.onCallBack(-1, StringUtil.format(DOMException.JSON_ERROR_INFO, 1, DOMException.MSG_NOT_FOUND_3TH));
            }
        }
    }

    public static void openURL(Context context, String str, String str2) throws Exception {
        Intent uri = Intent.parseUri(str, 0);
        if (!PdrUtil.isEmpty(str2)) {
            uri.setPackage(str2);
        }
        uri.setSelector(null);
        if (BaseInfo.isDefense) {
            uri.setComponent(null);
            uri.addCategory("android.intent.category.BROWSABLE");
        }
        uri.setFlags(268435456);
        context.startActivity(uri);
    }

    public static PackageInfo parseApkInfo(Context context, String str) throws Exception {
        return context.getPackageManager().getPackageArchiveInfo(str, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004f A[PHI: r1
  0x004f: PHI (r1v3 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v4 android.database.Cursor) binds: [B:20:0x004d, B:13:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean queryDefaultShortcut(java.lang.String r9) {
        /*
            r0 = 0
            r1 = 0
            android.content.Context r2 = io.dcloud.common.adapter.util.DeviceInfo.sApplicationContext     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            java.lang.String r2 = "content://com.android.launcher2.settings/favorites?notify=false"
            android.net.Uri r4 = android.net.Uri.parse(r2)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            java.lang.String[] r5 = io.dcloud.common.adapter.util.PlatformUtil.PROJECTION     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            java.lang.String r6 = "title=?"
            r2 = 1
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r7[r0] = r9     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r1 == 0) goto L2d
            boolean r9 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r9 == 0) goto L2d
            r1.close()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r1 == 0) goto L2c
            r1.close()
        L2c:
            return r2
        L2d:
            if (r1 == 0) goto L52
            goto L4f
        L30:
            r9 = move-exception
            goto L53
        L32:
            r9 = move-exception
            java.lang.String r2 = "PlatformUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L30
            r3.<init>()     // Catch: java.lang.Throwable -> L30
            java.lang.String r4 = "queryHTCShortCut error:"
            r3.append(r4)     // Catch: java.lang.Throwable -> L30
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L30
            r3.append(r9)     // Catch: java.lang.Throwable -> L30
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> L30
            io.dcloud.common.adapter.util.Logger.e(r2, r9)     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L52
        L4f:
            r1.close()
        L52:
            return r0
        L53:
            if (r1 == 0) goto L58
            r1.close()
        L58:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.util.PlatformUtil.queryDefaultShortcut(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004f A[PHI: r1
  0x004f: PHI (r1v3 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v4 android.database.Cursor) binds: [B:20:0x004d, B:13:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean queryHTCShortCut(java.lang.String r9) {
        /*
            r0 = 0
            r1 = 0
            android.content.Context r2 = io.dcloud.common.adapter.util.DeviceInfo.sApplicationContext     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            java.lang.String r2 = "content://com.htc.launcher.settings/favorites?notify=true"
            android.net.Uri r4 = android.net.Uri.parse(r2)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            java.lang.String[] r5 = io.dcloud.common.adapter.util.PlatformUtil.PROJECTION     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            java.lang.String r6 = "title=?"
            r2 = 1
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r7[r0] = r9     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r1 == 0) goto L2d
            boolean r9 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r9 == 0) goto L2d
            r1.close()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r1 == 0) goto L2c
            r1.close()
        L2c:
            return r2
        L2d:
            if (r1 == 0) goto L52
            goto L4f
        L30:
            r9 = move-exception
            goto L53
        L32:
            r9 = move-exception
            java.lang.String r2 = "PlatformUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L30
            r3.<init>()     // Catch: java.lang.Throwable -> L30
            java.lang.String r4 = "queryHTCShortCut error:"
            r3.append(r4)     // Catch: java.lang.Throwable -> L30
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L30
            r3.append(r9)     // Catch: java.lang.Throwable -> L30
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> L30
            io.dcloud.common.adapter.util.Logger.e(r2, r9)     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L52
        L4f:
            r1.close()
        L52:
            return r0
        L53:
            if (r1 == 0) goto L58
            r1.close()
        L58:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.util.PlatformUtil.queryHTCShortCut(java.lang.String):boolean");
    }

    public static int queryMaxLauncherScreenCount(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse(URI_SAMSUNG_LAUNCER), new String[]{"MAX(screen)"}, null, null, null);
        if (cursorQuery != null) {
            try {
                cursorQuery.moveToNext();
                return cursorQuery.getInt(0) + 1;
            } catch (Exception e2) {
                Logger.e("PlatformUtil", "Samsung Launcher" + e2);
                return -1;
            } finally {
                cursorQuery.close();
            }
        }
        return -1;
    }

    public static boolean setPackageName(Context context, Intent intent, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return false;
        }
        intent.setClassName(str, launchIntentForPackage.getComponent().getClassName());
        return true;
    }

    public static String useAndroidPath(String str) {
        return StringUtil.trimString(StringUtil.trimString(str, '/'), '\\');
    }

    public void delShortcut(String str, String str2, String str3) {
        Intent intent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", str);
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.DEFAULT");
        intent2.setComponent(new ComponentName(str2, str3));
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        DeviceInfo.sApplicationContext.sendBroadcast(intent);
    }

    public static Bitmap captureView(View view, boolean z, boolean z2, Rect rect, String str) {
        if (z2 && AndroidResources.sIMEAlive) {
            return null;
        }
        int iWidth = rect != null ? rect.width() : view.getMeasuredWidth();
        int iHeight = rect != null ? rect.height() : view.getMeasuredHeight();
        if (iWidth == 0) {
            return null;
        }
        Bitmap.Config config = Bitmap.Config.RGB_565;
        if (str.equals("ARGB")) {
            config = Bitmap.Config.ARGB_4444;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iWidth, iHeight, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (rect != null) {
            canvas.translate(-rect.left, -rect.top);
        }
        view.draw(canvas);
        if (!z || !isWhiteBitmap(bitmapCreateBitmap)) {
            return bitmapCreateBitmap;
        }
        bitmapCreateBitmap.recycle();
        return null;
    }

    public static Object invokeMethod(String str, String str2, Object obj) {
        return invokeMethod(str, str2, obj, new Class[0], new Object[0]);
    }

    public static boolean isWhiteBitmap(Bitmap bitmap, boolean z, boolean z2) {
        int height = bitmap.getHeight();
        int[] iArr = new int[height];
        bitmap.getPixels(iArr, 0, 1, bitmap.getWidth() / 4, 0, 1, height);
        boolean zEquals = Arrays.equals(iArr, GET_WIHTE_LINE(height));
        if (z2) {
            zEquals = isPureColor(iArr);
        }
        bitmap.getPixels(iArr, 0, 1, bitmap.getWidth() / 2, 0, 1, height);
        boolean zEquals2 = Arrays.equals(iArr, GET_WIHTE_LINE(height)) & zEquals;
        if (z2) {
            zEquals2 = isPureColor(iArr);
        }
        bitmap.getPixels(iArr, 0, 1, (bitmap.getWidth() * 3) / 4, 0, 1, height);
        boolean zEquals3 = Arrays.equals(iArr, GET_WIHTE_LINE(height)) & zEquals2;
        if (z2) {
            zEquals3 = isPureColor(iArr);
        }
        return z ? zEquals3 | Arrays.equals(iArr, GET_BLACK_LINE(height)) : zEquals3;
    }

    public static Object invokeMethod(String str, String str2, Object obj, Class[] clsArr, Object[] objArr) {
        String message;
        Object objInvoke;
        String str3 = null;
        try {
            Method method = Class.forName(str).getMethod(str2, clsArr);
            if (method != null) {
                method.setAccessible(true);
                objInvoke = method.invoke(obj, objArr);
            } else {
                objInvoke = null;
            }
        } catch (ClassNotFoundException unused) {
            message = "ClassNotFoundException";
            str3 = message;
            objInvoke = null;
        } catch (NoSuchMethodException unused2) {
            message = "NoSuchMethodException";
            str3 = message;
            objInvoke = null;
        } catch (Exception e2) {
            message = e2.getMessage();
            str3 = message;
            objInvoke = null;
        }
        if (str3 != null && !"getJsContent".equals(str2)) {
            Logger.i("platform", str3 + Operators.SPACE_STR + str + Operators.SPACE_STR + str2);
        }
        return objInvoke;
    }

    public static InputStream getInputStream(String str) {
        if (str == null) {
            return null;
        }
        return getInputStream(str, PdrUtil.isDeviceRootDir(str) ? 2 : 0);
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        Method declaredMethod;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> cls = obj.getClass();
            if (Build.VERSION.SDK_INT > 10) {
                declaredMethod = cls.getMethod(str, clsArr);
            } else {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            }
            declaredMethod.setAccessible(true);
            if (objArr.length == 0) {
                objArr = null;
            }
            return declaredMethod.invoke(obj, objArr);
        } catch (Throwable th) {
            Logger.i("platform", th.toString());
            return null;
        }
    }

    public static void createShortut(Context context, String str, String str2, Bitmap bitmap, boolean z) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setClassName(context, str2);
        Intent intent2 = new Intent(ACTION_INSTALL_SHORTCUT);
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        intent2.putExtra(EXTRA_SHORTCUT_DUPLICATE, z);
        context.sendBroadcast(intent2);
    }

    public static Bitmap captureView(View view, String str) {
        return captureView(view, null, str);
    }

    public static Bitmap captureView(View view, Rect rect, String str) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            boolean z = rect != null;
            if (z) {
                int i = rect.left;
                int i2 = rect.top;
                view.layout(i, i2, rect.right - i, rect.bottom - i2);
            } else {
                view.layout(0, 0, width, height);
            }
            view.setDrawingCacheEnabled(true);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
            bitmapCreateBitmap.setDensity(view.getContext().getResources().getDisplayMetrics().densityDpi);
            if (!PdrUtil.isEmpty(str)) {
                PdrUtil.saveBitmapToFile(bitmapCreateBitmap, str);
            }
            view.setDrawingCacheEnabled(false);
            if (z) {
                view.layout(0, 0, width, height);
            }
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
