package io.dcloud.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.huawei.secure.android.common.util.ZipUtil;
import com.igexin.assist.util.AssistUtils;
import com.igexin.push.core.b;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.XmlUtil;
import io.dcloud.g.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.qv;

/* JADX INFO: loaded from: classes2.dex */
public class PdrUtil {
    public static final String FILE_PATH_ENTRY_BACK = "..";
    public static final String FILE_PATH_ENTRY_SEPARATOR2 = "%";
    public static final String NAVIGATION = "navigationBarBackground";
    public static volatile boolean mHasCheckAllScreen;
    public static volatile boolean mIsAllScreenDevice;

    public static void alert(Activity activity, String str, Bitmap bitmap) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(activity).create();
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        TextView textView = new TextView(activity);
        textView.setText(str);
        linearLayout.addView(textView);
        ImageView imageView = new ImageView(activity);
        imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
        linearLayout.addView(imageView, new ViewGroup.LayoutParams(-2, -2));
        alertDialogCreate.setView(linearLayout);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setButton(activity.getString(R.string.dcloud_common_ok), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.PdrUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.show();
    }

    public static String appendByDeviceRootDir(String str) {
        if (str == null || str.startsWith(DeviceInfo.sDeviceRootDir)) {
            return str;
        }
        if (str.startsWith("file://")) {
            str = str.substring(7);
        }
        if (str.indexOf("sdcard/") > -1) {
            str = str.substring(str.indexOf("sdcard/") + 7);
        }
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        return DeviceInfo.sDeviceRootDir + "/" + str;
    }

    public static boolean checkAlphaTransparent(int i) {
        return (i == -1 || (i >>> 24) == 255) ? false : true;
    }

    public static boolean checkIntl() {
        return PlatformUtil.checkClass("io.dcloud.common.DHInterface.IntlCallback");
    }

    public static boolean checkStatusbarColor(int i) {
        String str = Build.BRAND;
        if (str.equals(MobilePhoneModel.GOOGLE)) {
            str = Build.MANUFACTURER;
        }
        if ((Build.VERSION.SDK_INT >= 23 || str.equalsIgnoreCase(MobilePhoneModel.XIAOMI) || str.equalsIgnoreCase(MobilePhoneModel.MEIZU)) && !str.equalsIgnoreCase(MobilePhoneModel.DUOWEI)) {
            return true;
        }
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        if (i2 >= 30 || i3 >= 30 || i4 >= 30) {
            return i2 <= 235 || i3 <= 235 || i4 <= 235;
        }
        return false;
    }

    public static void closeAndroidPDialog() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            PlatformUtil.invokeSetFieldValue(PlatformUtil.invokeMethod(Base64.decode2String("YW5kcm9pZC5hcHAuQWN0aXZpdHlUaHJlYWQ="), "currentActivityThread"), "mHiddenApiWarningShown", Boolean.TRUE);
        } catch (Exception e2) {
            Logger.e("PdrUtil", "closeAndroidPDialog--" + e2.getMessage());
        }
    }

    public static String convertAppPath(IWebview iWebview, String str) {
        IApp iAppObtainApp = iWebview.obtainApp();
        if (isNetPath(str) || str.startsWith("file:") || str.contains("/storage") || iAppObtainApp.obtainRunningAppMode() != 1) {
            return iAppObtainApp.convert2WebviewFullPath(iWebview.obtainFullUrl(), str);
        }
        String strConvert2AbsFullPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), str);
        if (strConvert2AbsFullPath.startsWith("/")) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.substring(1, strConvert2AbsFullPath.length());
        }
        if (strConvert2AbsFullPath.contains("android_asset/")) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.replace("android_asset/", "");
        }
        return ImageDownloader.Scheme.ASSETS.wrap(strConvert2AbsFullPath);
    }

    public static int convertToScreenInt(String str, int i, int i2, float f) {
        if (str == null) {
            return i2;
        }
        try {
            if (str.endsWith("px")) {
                String strSubstring = str.substring(0, str.length() - 2);
                return (strSubstring == null || !strSubstring.contains(Operators.DOT_STR)) ? (int) (Integer.parseInt(strSubstring) * f) : (int) (Float.parseFloat(strSubstring) * f);
            }
            if (!str.endsWith("%")) {
                return (int) (Double.parseDouble(str) * ((double) f));
            }
            String strSubstring2 = str.substring(0, str.length() - 1);
            try {
                return strSubstring2.contains(Operators.DOT_STR) ? (int) ((i * Float.parseFloat(strSubstring2)) / 100.0f) : (i * Integer.parseInt(strSubstring2)) / 100;
            } catch (NumberFormatException unused) {
                return i2;
            }
        } catch (Exception unused2) {
            return i2;
        }
    }

    public static String dealString(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("GBK");
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ 8);
        }
        return new String(bytes, "GBK");
    }

    public static float dpiFromPx(int i, DisplayMetrics displayMetrics) {
        return i / (displayMetrics.densityDpi / 160.0f);
    }

    public static String encodeURL(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            Logger.e("URLEncode error str=" + str);
            return URLEncoder.encode(str);
        }
    }

    public static JSONObject getConfigData(Context context, String str, String str2, boolean z) {
        try {
            InputStream resInputStream = z ? PlatformUtil.getResInputStream(str2) : DHFile.getInputStream(DHFile.createFileHandler(str2));
            if (resInputStream == null) {
                return null;
            }
            byte[] bytes = IOUtil.getBytes(resInputStream);
            String strA = a.a(context, bytes);
            if (TextUtils.isEmpty(strA)) {
                strA = new String(bytes);
            }
            return new JSONObject(strA);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int getConfigOrientation(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || !jSONObject.has("screenOrientation")) {
            return 2;
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("screenOrientation");
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String string = jSONArrayOptJSONArray.getString(i);
            string.hashCode();
            byte b = -1;
            switch (string.hashCode()) {
                case -1228021296:
                    if (string.equals("portrait-primary")) {
                        b = 0;
                    }
                    break;
                case -147105566:
                    if (string.equals("landscape-secondary")) {
                        b = 1;
                    }
                    break;
                case 1862465776:
                    if (string.equals("landscape-primary")) {
                        b = 2;
                    }
                    break;
                case 2012187074:
                    if (string.equals("portrait-secondary")) {
                        b = 3;
                    }
                    break;
            }
            if (b == 0) {
                z3 = true;
            } else if (b == 1) {
                z2 = true;
            } else if (b == 2) {
                z = true;
            } else if (b == 3) {
                z4 = true;
            }
        }
        if ((z || z2) && (z3 || z4)) {
            return 2;
        }
        if (z && z2) {
            return 6;
        }
        if (z3 && z4) {
            return 7;
        }
        if (z) {
            return 0;
        }
        if (z2) {
            return 8;
        }
        if (z3) {
            return 1;
        }
        return z4 ? 9 : 2;
    }

    public static String getDefaultPrivateDocPath(String str, String str2) {
        if (isEmpty(str)) {
            str = AbsoluteConst.MINI_SERVER_APP_DOC + System.currentTimeMillis();
        } else if (str.endsWith("/")) {
            str = str + System.currentTimeMillis();
        }
        if (str.endsWith(Operators.DOT_STR + str2)) {
            return str;
        }
        return str + Operators.DOT_STR + str2;
    }

    public static String getDownloadFilename(String str, String str2, String str3) {
        String[] strArrStringSplit;
        String[] strArrStringSplit2;
        String strValueOf = null;
        try {
            str3 = URLDecoder.decode(str3, "utf-8");
            Uri uri = Uri.parse(str3);
            if (uri != null) {
                String path = uri.getPath();
                if (!isEmpty(path)) {
                    str3 = path;
                }
            }
            if (!isEmpty(str) && (strArrStringSplit = stringSplit(str, ";")) != null) {
                int i = 0;
                while (true) {
                    if (i >= strArrStringSplit.length) {
                        break;
                    }
                    if (strArrStringSplit[i] != null && strArrStringSplit[i].contains(AbsoluteConst.JSON_KEY_FILENAME) && (strArrStringSplit2 = stringSplit(strArrStringSplit[i].trim(), ContainerUtils.KEY_VALUE_DELIMITER)) != null) {
                        String strReplace = strArrStringSplit2[0].replace(JSUtil.QUOTE, "");
                        String strReplace2 = strArrStringSplit2[1].replace(JSUtil.QUOTE, "");
                        if (!isEmpty(strArrStringSplit2[1]) && isEquals(AbsoluteConst.JSON_KEY_FILENAME, strReplace) && !isEmpty(strReplace2)) {
                            strValueOf = strReplace2;
                            break;
                        }
                    }
                    i++;
                }
            }
        } catch (Exception unused) {
            Logger.d("PdrUtil.getDownloadFilename " + str + " not found filename");
        }
        if (isEmpty(strValueOf)) {
            int iLastIndexOf = str3.lastIndexOf(47);
            if (iLastIndexOf <= 0 || iLastIndexOf >= str3.length() - 1) {
                strValueOf = String.valueOf(System.currentTimeMillis());
            } else {
                strValueOf = str3.substring(iLastIndexOf + 1);
                int iIndexOf = strValueOf.indexOf(Operators.CONDITION_IF_STRING);
                if (iIndexOf > 0) {
                    strValueOf = iIndexOf < strValueOf.length() - 1 ? strValueOf.substring(0, iIndexOf) : String.valueOf(System.currentTimeMillis());
                }
            }
        }
        if (strValueOf.indexOf(Operators.DOT_STR) < 0) {
            String extensionFromMimeType = getExtensionFromMimeType(str2);
            if (!isEmpty(extensionFromMimeType)) {
                strValueOf = strValueOf + Operators.DOT_STR + extensionFromMimeType;
            }
        }
        try {
            String strReplaceAll = URLDecoder.decode(strValueOf, "UTF-8").replaceAll(File.separator, "");
            if (strReplaceAll.contains(Operators.CONDITION_IF_STRING)) {
                strReplaceAll = strReplaceAll.replaceAll("\\?", "0");
            }
            if (strReplaceAll.length() <= 80) {
                return strReplaceAll;
            }
            return strReplaceAll.substring(0, 80) + System.currentTimeMillis();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return strValueOf;
        }
    }

    public static String getExtensionFromMimeType(String str) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
    }

    public static String getFileNameByUrl(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            if (lastPathSegment != null && lastPathSegment.contains(str2)) {
                return lastPathSegment;
            }
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            return String.valueOf(System.currentTimeMillis()) + str2;
        }
        Matcher matcher = Pattern.compile("[\\w\\.]+[\\.](avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        if (!TextUtils.isEmpty(str3)) {
            return str3;
        }
        return String.valueOf(System.currentTimeMillis()) + ".dat";
    }

    public static Object getKeyByIndex(HashMap map, int i) {
        if (i < 0) {
            return null;
        }
        int i2 = 0;
        for (Object obj : map.keySet()) {
            if (i == i2) {
                return obj;
            }
            i2++;
        }
        return null;
    }

    public static Object getKeyByValue(HashMap map, Object obj, boolean z) {
        if (z && !map.containsValue(obj)) {
            return null;
        }
        for (Object obj2 : map.keySet()) {
            Object obj3 = map.get(obj2);
            if (obj3 != null && obj3.equals(obj)) {
                return obj2;
            }
        }
        return null;
    }

    public static String getMimeType(String str) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (isEmpty(fileExtensionFromUrl) && str.lastIndexOf(Operators.DOT_STR) >= 0) {
            fileExtensionFromUrl = str.substring(str.lastIndexOf(Operators.DOT_STR) + 1);
        }
        String mimeTypeFromExtension = singleton.getMimeTypeFromExtension(fileExtensionFromUrl);
        if (!TextUtils.isEmpty(mimeTypeFromExtension)) {
            return mimeTypeFromExtension;
        }
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            return "*/*";
        }
        return "application/" + fileExtensionFromUrl;
    }

    public static String getNavBarOverride() {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        try {
            Method declaredMethod = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources;
        int identifier;
        if (!hasNavBar(context) || (identifier = (resources = context.getResources()).getIdentifier("navigation_bar_height", "dimen", "android")) <= 0) {
            return 0;
        }
        return resources.getDimensionPixelSize(identifier);
    }

    public static String getNonString(String str, String str2) {
        return isEmpty(str) ? str2 : str;
    }

    public static Object getObject(Object[] objArr, int i) {
        try {
            return objArr[i];
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0216 A[LOOP:1: B:17:0x0074->B:101:0x0216, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0168 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015f A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject getSitemapParameters(org.json.JSONObject r30, java.lang.String r31, java.lang.String r32) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.PdrUtil.getSitemapParameters(org.json.JSONObject, java.lang.String, java.lang.String):org.json.JSONObject");
    }

    public static String getUrlPathName(String str) {
        return str != null ? URLUtil.stripAnchor(stripQuery(str)) : str;
    }

    @TargetApi(14)
    public static boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier == 0) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        boolean z = resources.getBoolean(identifier);
        String navBarOverride = getNavBarOverride();
        if ("1".equals(navBarOverride)) {
            return false;
        }
        if ("0".equals(navBarOverride)) {
            return true;
        }
        return z;
    }

    public static String int2DecimalStr(int i, int i2) {
        return String.valueOf(BigDecimal.valueOf(i).divide(BigDecimal.valueOf(i2)));
    }

    public static boolean isAllScreenDevice(Activity activity) {
        float f;
        float f2;
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            return false;
        }
        if (i >= 28 && activity.getWindow().getDecorView().getRootWindowInsets() != null && activity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout() != null) {
            mIsAllScreenDevice = true;
            return mIsAllScreenDevice;
        }
        WindowManager windowManager = (WindowManager) activity.getSystemService("window");
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            int i2 = point.x;
            int i3 = point.y;
            if (i2 < i3) {
                f2 = i2;
                f = i3;
            } else {
                float f3 = i3;
                f = i2;
                f2 = f3;
            }
            if (f / f2 >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }

    public static boolean isContains(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2)) {
            return false;
        }
        return str.contains(str2);
    }

    public static boolean isDeviceRootDir(String str) {
        return str.startsWith(DeviceInfo.sDeviceRootDir) || str.startsWith(DeviceInfo.sCacheRootDir) || str.startsWith("/sdcard/") || str.startsWith(DeviceInfo.sDeviceRootDir.substring(1)) || str.startsWith("sdcard/");
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.equals("") || (obj.toString().length() == 4 && obj.toString().toLowerCase().equals(b.m));
    }

    public static boolean isEquals(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static boolean isFilePath(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("file:/");
    }

    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 1024;
    }

    public static boolean isNavigationBarExist(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        if (viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                viewGroup.getChildAt(i).getContext().getPackageName();
                if (viewGroup.getChildAt(i).getId() != -1 && NAVIGATION.equals(activity.getResources().getResourceEntryName(viewGroup.getChildAt(i).getId()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNavigationBarShow(Activity activity) {
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(activity).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getRealSize(point2);
        return point2.y != point.y;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isNavigationBarShowing(android.content.Context r3) {
        /*
            boolean r0 = hasNavBar(r3)
            r1 = 0
            if (r0 == 0) goto L44
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 17
            if (r0 < r2) goto L44
            java.lang.String r0 = android.os.Build.BRAND
            java.lang.String r2 = "HUAWEI"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L18
            goto L37
        L18:
            java.lang.String r2 = "XIAOMI"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L23
            java.lang.String r0 = "force_fsg_nav_bar"
            goto L39
        L23:
            java.lang.String r2 = "VIVO"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L2c
            goto L34
        L2c:
            java.lang.String r2 = "OPPO"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L37
        L34:
            java.lang.String r0 = "navigation_gesture_on"
            goto L39
        L37:
            java.lang.String r0 = "navigationbar_is_min"
        L39:
            android.content.ContentResolver r3 = r3.getContentResolver()
            int r3 = android.provider.Settings.Global.getInt(r3, r0, r1)
            if (r3 != 0) goto L44
            r1 = 1
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.PdrUtil.isNavigationBarShowing(android.content.Context):boolean");
    }

    public static boolean isNetPath(String str) {
        return str != null && ((str.startsWith(DeviceInfo.HTTP_PROTOCOL) && !str.startsWith("http://localhost")) || ((str.startsWith(DeviceInfo.HTTPS_PROTOCOL) && !str.startsWith("https://localhost")) || ((str.startsWith("rtmp://") && !str.startsWith("rtmp://localhost")) || (str.startsWith("rtsp://") && !str.startsWith("rtsp://localhost")))));
    }

    public static boolean isSafeEntryName(String str) {
        return (str.contains(FILE_PATH_ENTRY_BACK) || str.contains("%")) ? false : true;
    }

    public static boolean isSameDay(long j, long j2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(j);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.setTimeInMillis(j2);
        return gregorianCalendar.get(1) == gregorianCalendar2.get(1) && gregorianCalendar.get(2) == gregorianCalendar2.get(2) && gregorianCalendar.get(5) == gregorianCalendar2.get(5);
    }

    public static boolean isSupportOaid() {
        String str = Build.BRAND;
        int i = Build.VERSION.SDK_INT;
        if ((str.equalsIgnoreCase(AssistUtils.BRAND_HON) || str.equalsIgnoreCase(AssistUtils.BRAND_HW)) && i >= 23) {
            return true;
        }
        if (!str.equalsIgnoreCase(AssistUtils.BRAND_VIVO) || i < 28) {
            return (str.equalsIgnoreCase(AssistUtils.BRAND_XIAOMI) && i >= 28) || i >= 29;
        }
        return true;
    }

    public static boolean isUniMPHostForUniApp() {
        return PlatformUtil.checkClass("io.dcloud.feature.sdk.Interface.DCUniAppHost");
    }

    public static void loadProperties2HashMap(HashMap<String, String> map, String str) {
        InputStream resInputStream = PlatformUtil.getResInputStream(str);
        Properties properties = new Properties();
        try {
            try {
                properties.load(resInputStream);
                Enumeration<?> enumerationPropertyNames = properties.propertyNames();
                if (enumerationPropertyNames != null) {
                    while (enumerationPropertyNames.hasMoreElements()) {
                        String str2 = (String) enumerationPropertyNames.nextElement();
                        map.put(str2.toLowerCase(), (String) properties.get(str2));
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } finally {
            IOUtil.close(resInputStream);
        }
    }

    public static String makeQueryStringAllRegExp(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace("\\", "\\\\").replace(Operators.MUL, "\\*").replace(Operators.PLUS, "\\+").replace("|", "\\|").replace(Operators.BLOCK_START_STR, "\\{").replace(Operators.BLOCK_END_STR, "\\}").replace("(", "\\(").replace(")", "\\)").replace("^", "\\^").replace(Operators.DOLLAR_STR, "\\$").replace(Operators.ARRAY_START_STR, "\\[").replace(Operators.ARRAY_END_STR, "\\]").replace(Operators.CONDITION_IF_STRING, "\\?").replace(",", "\\,").replace(Operators.DOT_STR, "\\.").replace("&", "\\&").replace("'", "\\'");
    }

    public static boolean match(String str, String str2) {
        return Pattern.compile(str).matcher(str2).find();
    }

    public static boolean matchArray(JSONArray jSONArray, String str) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                if (match(jSONArray.optString(i), str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean navigationGestureEnabled(Context context) {
        return (DeviceInfo.sBrand.toLowerCase().equals(AssistUtils.BRAND_XIAOMI) ? Build.VERSION.SDK_INT >= 26 ? Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) : 0 : Settings.Secure.getInt(context.getContentResolver(), "navigation_gesture_on", 0)) != 0;
    }

    public static boolean parseBoolean(String str, boolean z, boolean z2) {
        return isEmpty(str) ? z : str.equalsIgnoreCase("true") ? (!z2) & true : str.equalsIgnoreCase(AbsoluteConst.FALSE) ? z2 | false : z;
    }

    public static float parseFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return f;
        }
    }

    public static int parseInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static long parseLong(String str, long j) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j;
        }
    }

    public static int pxFromDp(float f, DisplayMetrics displayMetrics) {
        return Math.round(TypedValue.applyDimension(1, f, displayMetrics));
    }

    public static int pxFromSp(float f, DisplayMetrics displayMetrics) {
        return Math.round(TypedValue.applyDimension(2, f, displayMetrics));
    }

    public static Bitmap renderCroppedGreyscaleBitmap(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = new int[i5 * i6];
        int i7 = (i4 * i) + i3;
        for (int i8 = 0; i8 < i6; i8++) {
            int i9 = i8 * i5;
            for (int i10 = 0; i10 < i5; i10++) {
                iArr[i9 + i10] = ((bArr[i7 + i10] & 255) * 65793) | (-16777216);
            }
            i7 += i;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i5, i6, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, i5, 0, 0, i5, i6);
        return bitmapCreateBitmap;
    }

    public static boolean saveBitmapToFile(Bitmap bitmap, String str) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String standardizedURL(String str, String str2) {
        String strStripQuery = stripQuery(stripAnchor(str));
        if (str2.startsWith("./")) {
            str2 = str2.substring(2);
            int iLastIndexOf = strStripQuery.lastIndexOf(47);
            if (iLastIndexOf >= 0) {
                return strStripQuery.substring(0, iLastIndexOf + 1) + str2;
            }
        }
        int iIndexOf = str2.indexOf(ZipUtil.f3018e);
        int iLastIndexOf2 = strStripQuery.lastIndexOf(47);
        if (!(iLastIndexOf2 > -1)) {
            return str2;
        }
        String strSubstring = strStripQuery.substring(0, iLastIndexOf2);
        while (iIndexOf > -1) {
            str2 = str2.substring(3);
            strSubstring = strSubstring.substring(0, strSubstring.lastIndexOf(47));
            iIndexOf = str2.indexOf(ZipUtil.f3018e);
        }
        return strSubstring + '/' + str2;
    }

    public static String[] stringSplit(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        int i = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2, false);
        String[] strArr = new String[stringTokenizer.countTokens()];
        while (stringTokenizer.hasMoreElements()) {
            strArr[i] = stringTokenizer.nextToken().trim();
            i++;
        }
        return strArr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:41:0x009d
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static int stringToColor(java.lang.String r8) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.PdrUtil.stringToColor(java.lang.String):int");
    }

    public static String stripAnchor(String str) {
        return URLUtil.stripAnchor(str);
    }

    public static String stripQuery(String str) {
        int iIndexOf = str.indexOf(63);
        return iIndexOf != -1 ? str.substring(0, iIndexOf) : str;
    }

    public static String toHexFromColor(int i) {
        StringBuilder sb = new StringBuilder();
        String hexString = Integer.toHexString(Color.red(i));
        String hexString2 = Integer.toHexString(Color.green(i));
        String hexString3 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        String upperCase = hexString.toUpperCase();
        String upperCase2 = hexString2.toUpperCase();
        String upperCase3 = hexString3.toUpperCase();
        sb.append("#");
        sb.append(upperCase);
        sb.append(upperCase2);
        sb.append(upperCase3);
        return sb.toString();
    }

    public static void toast(Context context, String str, Bitmap bitmap) {
        qv qvVarMakeText = qv.makeText(context, (CharSequence) str, 1);
        if (bitmap != null) {
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            View view = qvVarMakeText.getView();
            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(bitmap);
            ((ViewGroup) view).addView(imageView, 0);
            qvVarMakeText.setText(str + " w=" + width + ";h=" + height);
        }
        qvVarMakeText.setDuration(1);
        qvVarMakeText.show();
    }

    public static float parseFloat(String str, float f, float f2) {
        return parseFloat(str, f, f2, 1.0f);
    }

    public static int parseInt(String str, int i, int i2) {
        if (str == null) {
            return i2;
        }
        try {
            String lowerCase = str.toLowerCase();
            if (lowerCase.endsWith("px")) {
                return Integer.parseInt(lowerCase.substring(0, lowerCase.length() - 2));
            }
            if (lowerCase.endsWith("%")) {
                String strSubstring = lowerCase.substring(0, lowerCase.length() - 1);
                try {
                    return strSubstring.contains(Operators.DOT_STR) ? (int) ((i * Float.parseFloat(strSubstring)) / 100.0f) : (i * Integer.parseInt(strSubstring)) / 100;
                } catch (NumberFormatException unused) {
                    return i2;
                }
            }
            if (lowerCase.startsWith("#")) {
                lowerCase = "0x" + lowerCase.substring(1);
            }
            return lowerCase.startsWith("0x") ? Integer.valueOf(lowerCase.substring(2), 16).intValue() : Integer.parseInt(lowerCase);
        } catch (Exception unused2) {
            return i2;
        }
    }

    public static boolean parseBoolean(String str, boolean z) {
        return isEmpty(str) ? z : str.equalsIgnoreCase("true");
    }

    public static float parseFloat(String str, float f, float f2, float f3) {
        if (str == null) {
            return f2;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith("px")) {
            lowerCase = lowerCase.substring(0, lowerCase.length() - 2);
        }
        try {
            return Float.parseFloat(lowerCase) * f3;
        } catch (NumberFormatException unused) {
            if (lowerCase.endsWith("%")) {
                try {
                    return (f * Float.parseFloat(lowerCase.substring(0, lowerCase.length() - 1))) / 100.0f;
                } catch (Exception unused2) {
                    return f2;
                }
            }
            return f2;
        }
    }

    public static Object getKeyByValue(HashMap map, Object obj) {
        return getKeyByValue(map, obj, false);
    }

    public static void loadProperties2HashMap(HashMap<String, String> map, HashMap<String, String> map2, HashMap<String, HashMap<String, String>> map3, String str) {
        XmlUtil.DHNode dHNodeXML_Parser;
        InputStream resInputStream = PlatformUtil.getResInputStream(str);
        if (resInputStream == null || (dHNodeXML_Parser = XmlUtil.XML_Parser(resInputStream)) == null) {
            return;
        }
        ArrayList<XmlUtil.DHNode> elements = XmlUtil.getElements(XmlUtil.getElement(dHNodeXML_Parser, IApp.ConfigProperty.CONFIG_FEATURES), IApp.ConfigProperty.CONFIG_FEATURE);
        if (elements != null && !elements.isEmpty()) {
            for (XmlUtil.DHNode dHNode : elements) {
                String lowerCase = XmlUtil.getAttributeValue(dHNode, "name").toLowerCase();
                String attributeValue = XmlUtil.getAttributeValue(dHNode, "value");
                if ("ui".equals(lowerCase)) {
                    map2.put("webview", attributeValue);
                }
                map2.put(lowerCase, attributeValue);
                ArrayList<XmlUtil.DHNode> elements2 = XmlUtil.getElements(dHNode, "module");
                if (elements2 != null && !elements2.isEmpty()) {
                    HashMap<String, String> linkedHashMap = map3.get(lowerCase);
                    if (linkedHashMap == null) {
                        linkedHashMap = new LinkedHashMap<>(2);
                        map3.put(lowerCase, linkedHashMap);
                    }
                    for (XmlUtil.DHNode dHNode2 : elements2) {
                        linkedHashMap.put(XmlUtil.getAttributeValue(dHNode2, "name").toLowerCase(), XmlUtil.getAttributeValue(dHNode2, "value"));
                    }
                }
            }
        }
        ArrayList<XmlUtil.DHNode> elements3 = XmlUtil.getElements(XmlUtil.getElement(dHNodeXML_Parser, IApp.ConfigProperty.CONFIG_SERVICES), "service");
        if (elements3 == null || elements3.isEmpty()) {
            return;
        }
        for (XmlUtil.DHNode dHNode3 : elements3) {
            map.put(XmlUtil.getAttributeValue(dHNode3, "name").toLowerCase(), XmlUtil.getAttributeValue(dHNode3, "value"));
        }
    }
}
