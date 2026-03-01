package io.dcloud.common.adapter.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.bumptech.glide.load.engine.executor.RuntimeCompat;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.taobao.weex.el.parse.Operators;
import com.tencent.connect.common.Constants;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TelephonyUtil;
import io.dcloud.common.util.language.LanguageUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfo {
    public static final String CDMA_DATA_NETWORK = "cdma";
    public static String CONNECTION_CELL2G = null;
    public static String CONNECTION_CELL3G = null;
    public static String CONNECTION_CELL4G = null;
    public static String CONNECTION_CELL5G = null;
    public static String CONNECTION_ETHERNET = null;
    public static String CONNECTION_UNKNOW = null;
    public static final String DEFAULT_DATA_NETWORK = "default_data_network";
    public static float DEFAULT_FONT_SIZE = 0.0f;
    public static String DEVICESTATUS_JS = null;
    public static final String FILE_PROTOCOL = "file://";
    public static int HARDWAREACCELERATED_VIEW = 0;
    public static int HARDWAREACCELERATED_WINDOW = 0;
    public static final String HTTPS_PROTOCOL = "https://";
    public static final String HTTP_PROTOCOL = "http://";
    public static String NETTYPE_NONE = null;
    public static String NETTYPE_WIFI = null;
    public static final String NONE_DATA_NETWORK = "none";
    public static final int OSTYPE_ANDROID = 0;
    public static final int OSTYPE_LEOS10 = 4;
    public static final int OSTYPE_OMS10 = 3;
    public static final int OSTYPE_OMS15 = 2;
    public static final int OSTYPE_OMS20 = 1;
    public static final String SAVED_DATA_NETWORK = "saved_data_network";
    public static final String TAG = "DeviceInfo";
    public static float dpiX;
    public static float dpiY;
    public static volatile boolean isIMEShow;
    public static boolean isPrivateDirectory;
    public static boolean isVolumeButtonEnabled;
    public static String mAppAuthorities;
    public static String oaids;
    public static int osType;
    public static Context sApplicationContext;
    public static String sBaseFsCachePath;
    public static String sBaseFsRootFullPath;
    public static String sBaseFsRootPath;
    public static String sBaseResRootFullPath;
    public static String sBaseResRootPathName;
    public static String sCacheRootDir;
    public static ConnectivityManager sConnectMgr;
    public static float sDensity;
    public static String sDeviceRootDir;
    public static String sIMEI;
    public static String sIMSI;
    public static int sInputMethodHeight;
    public static String sMAC;
    public static String sPackageName;
    public static Paint sPaint;
    public static String sPrivateDir;
    public static String sPrivateExternalDir;
    public static String sPublicDCIMDir;
    public static String sPublicDocumentsDir;
    public static String sPublicDownloadDir;
    public static String sPublicMoviesDir;
    public static String sPublicMusicDir;
    public static String sPublicPicturesDir;
    public static String sPublicRingtonesDir;
    public static char sSeparatorChar;
    public static int sStatusBarHeight;
    public static int sDeviceSdkVer = Build.VERSION.SDK_INT;
    public static String sModel = Build.MODEL;
    public static String sBrand = Build.BRAND;
    public static long sTotalMem = -1;
    public static int sCoreNums = -1;
    public static String sVendor = Build.MANUFACTURER;
    public static String sVersion_release = Build.VERSION.RELEASE;
    public static String sLanguage = LanguageUtil.getDeviceDefLocalLanguage();
    public static boolean sNetWorkInited = false;
    public static GsmCellLocation sCellLocation = null;
    public static final String GSM_DATA_NETWORK = "gsm";
    public static String sDeftDataNetwork = GSM_DATA_NETWORK;
    public static String sSimOperator = null;

    static {
        char c = File.separatorChar;
        sSeparatorChar = c;
        sBaseResRootPathName = String.valueOf(c);
        sPackageName = null;
        DEVICESTATUS_JS = null;
        Paint paint = new Paint();
        sPaint = paint;
        DEFAULT_FONT_SIZE = paint.getTextSize();
        osType = 0;
        sApplicationContext = null;
        sConnectMgr = null;
        isPrivateDirectory = false;
        isVolumeButtonEnabled = true;
        sPrivateExternalDir = null;
        sPrivateDir = null;
        sPublicDocumentsDir = null;
        sPublicDCIMDir = null;
        sPublicDownloadDir = null;
        sPublicMoviesDir = null;
        sPublicMusicDir = null;
        sPublicPicturesDir = null;
        sPublicRingtonesDir = null;
        CONNECTION_UNKNOW = "0";
        NETTYPE_NONE = "1";
        CONNECTION_ETHERNET = "2";
        NETTYPE_WIFI = "3";
        CONNECTION_CELL2G = "4";
        CONNECTION_CELL3G = "5";
        CONNECTION_CELL4G = Constants.VIA_SHARE_TYPE_INFO;
        CONNECTION_CELL5G = "7";
        sStatusBarHeight = 0;
        sInputMethodHeight = 0;
        HARDWAREACCELERATED_WINDOW = 0;
        HARDWAREACCELERATED_VIEW = 1;
        isIMEShow = false;
    }

    public static boolean checkCoverLoadApp() {
        boolean z;
        String bundleData = SP.getBundleData(BaseInfo.PDR, "last_app_modify_date");
        long jLastModified = new File(sApplicationContext.getPackageCodePath()).lastModified();
        Logger.d(TAG, "old_app_modify_date=" + bundleData);
        if (PdrUtil.isEquals(bundleData, String.valueOf(jLastModified))) {
            z = false;
        } else {
            SP.setBundleData(sApplicationContext, BaseInfo.PDR, "last_app_modify_date", String.valueOf(jLastModified));
            bundleData = Logger.generateTimeStamp(Logger.TIMESTAMP_YYYY_MM_DD_HH_MM_SS_SSS, new Date(jLastModified));
            Logger.d(TAG, "new_app_modify_date=" + jLastModified);
            z = true;
        }
        Logger.d(TAG, "App Modify Date=" + bundleData + ";_ret=" + z);
        return z;
    }

    public static void closeHardwareAccelerated(Activity activity, int i, Object obj) {
        Window window = null;
        if (i != HARDWAREACCELERATED_WINDOW) {
            if (i != HARDWAREACCELERATED_VIEW || sDeviceSdkVer < 11) {
                return;
            }
            ((View) obj).setLayerType(1, null);
            return;
        }
        Window window2 = (Window) obj;
        if (window2 != null) {
            window = window2;
        } else if (activity != null) {
            window = activity.getWindow();
        }
        if (window != null) {
            window.clearFlags(16777216);
        }
    }

    public static long getAvailMemory() {
        Context context = sApplicationContext;
        if (context == null) {
            return 0L;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static String getBuildValue(String str) {
        try {
            Class<?> cls = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP);
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getCurrentAPN() {
        return NetworkTypeUtil.getCurrentAPN(DCLoudApplicationImpl.self().getContext().getApplicationContext());
    }

    public static int getDeivceSuitablePixel(Activity activity, int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) (i * displayMetrics.density);
    }

    public static String getDevicestatus_js(IApp iApp) {
        DEVICESTATUS_JS = "(function(p){p.device.imei='%s';p.device.uuid='%s';p.device.imsi=['%s'];p.device.model='%s';p.device.vendor='%s';p.os.language='%s';p.os.version='%s';p.os.name='%s';p.os.vendor='%s';})(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus));";
        String strUpdateIMEI = TelephonyUtil.updateIMEI(iApp.getActivity());
        String str = StringUtil.format(DEVICESTATUS_JS, strUpdateIMEI, strUpdateIMEI, sIMSI, sModel, sVendor, sLanguage, sVersion_release, "Android", "Google");
        DEVICESTATUS_JS = str;
        return str;
    }

    public static String getMac(Context context) {
        try {
            sMAC = TelephonyUtil.getWifiData(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sMAC;
    }

    public static String getNetWorkType() {
        String str = NETTYPE_NONE;
        ConnectivityManager connectivityManager = sConnectMgr;
        if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) {
            return str;
        }
        String str2 = CONNECTION_UNKNOW;
        if (sConnectMgr.getActiveNetworkInfo().getType() == 1) {
            return NETTYPE_WIFI;
        }
        if (sConnectMgr.getActiveNetworkInfo().getType() != 0) {
            return str2;
        }
        int subtype = sConnectMgr.getActiveNetworkInfo().getSubtype();
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
                return CONNECTION_CELL2G;
            case 3:
            case 8:
                return CONNECTION_CELL3G;
            case 5:
            case 6:
            case 12:
            case 14:
                return CONNECTION_CELL3G;
            case 9:
            case 10:
            case 11:
            case 13:
            case 15:
                return CONNECTION_CELL4G;
            case 16:
            case 19:
            default:
                return "" + subtype;
            case 17:
            case 18:
                return CONNECTION_CELL3G;
            case 20:
                return CONNECTION_CELL5G;
        }
    }

    public static int getNumCores() {
        int i = sCoreNums;
        if (i != -1) {
            return i;
        }
        try {
            File[] fileArrListFiles = new File(RuntimeCompat.CPU_LOCATION).listFiles(new FileFilter() { // from class: io.dcloud.common.adapter.util.DeviceInfo.1CpuFilter
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            });
            sCoreNums = fileArrListFiles.length;
            return fileArrListFiles.length;
        } catch (Exception unused) {
            return 1;
        }
    }

    public static String getPlusCache() {
        return "window.plus.cache = navigator.plus.cache = (function(mkey){return {clear : function(clearCB){var callbackid = mkey.helper.callbackid( function(args){if ( clearCB ) {clearCB()};}, null);mkey.exec('Cache', 'clear', [callbackId]);},calculate : function(calculateCB){var callbackid = mkey.helper.callbackid( function(args){if ( calculateCB ) {calculateCB(args)};}, null);mkey.exec('Cache', 'calculate', [callbackid]);},setMaxSize : function (size) {mkey.exec('Cache', 'setMaxSize', [size]);}};})(window.__Mkey__);";
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        r3 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getStatusHeight(android.content.Context r3) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L3e
            r1 = 29
            java.lang.String r2 = "status_bar_height"
            if (r0 < r1) goto L1b
            android.content.res.Resources r3 = r3.getResources()     // Catch: java.lang.Exception -> L3e
            java.lang.String r0 = "dimen"
            java.lang.String r1 = "android"
            int r0 = r3.getIdentifier(r2, r0, r1)     // Catch: java.lang.Exception -> L3e
            if (r0 <= 0) goto L59
            int r3 = r3.getDimensionPixelSize(r0)     // Catch: java.lang.Exception -> L3e
            goto L5a
        L1b:
            java.lang.String r0 = "com.android.internal.R$dimen"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L3e
            java.lang.Object r1 = r0.newInstance()     // Catch: java.lang.Exception -> L3e
            java.lang.reflect.Field r0 = r0.getField(r2)     // Catch: java.lang.Exception -> L3e
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Exception -> L3e
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L3e
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L3e
            android.content.res.Resources r3 = r3.getResources()     // Catch: java.lang.Exception -> L3e
            int r3 = r3.getDimensionPixelSize(r0)     // Catch: java.lang.Exception -> L3e
            goto L5a
        L3e:
            r3 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getStatusHeight --"
            r0.append(r1)
            java.lang.String r3 = r3.getMessage()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "DeviceInfo"
            io.dcloud.common.adapter.util.Logger.e(r0, r3)
        L59:
            r3 = -1
        L5a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.util.DeviceInfo.getStatusHeight(android.content.Context):int");
    }

    public static long getTotalMemory() {
        long j = sTotalMem;
        if (j != -1) {
            return j;
        }
        long jIntValue = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            jIntValue = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue();
            bufferedReader.close();
            sTotalMem = jIntValue;
            return jIntValue;
        } catch (Exception unused) {
            return jIntValue;
        }
    }

    public static String getUpdateIMSI() {
        String imsi = TelephonyUtil.getIMSI(sApplicationContext);
        sIMSI = imsi;
        if (imsi == null) {
            sIMSI = "";
        }
        return sIMSI;
    }

    public static void hideIME(View view) {
        IBinder windowToken;
        Context context = sApplicationContext;
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (view == null || (windowToken = view.getWindowToken()) == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    public static void init(Context context) {
        sDeviceSdkVer = Build.VERSION.SDK_INT;
        String str = Build.MODEL;
        sModel = str;
        if ("OMAP_SS".equals(str)) {
            osType = 1;
        } else if ("OMS1_5".equals(sModel)) {
            osType = 2;
        } else if ("generic".equals(sModel)) {
            osType = 3;
        }
        mAppAuthorities = context.getPackageName() + ".dc.fileprovider";
        sBrand = Build.BRAND;
        sVendor = Build.MANUFACTURER;
        sLanguage = LanguageUtil.getDeviceDefLocalLanguage();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        sDensity = displayMetrics.density;
        dpiX = displayMetrics.xdpi;
        dpiY = displayMetrics.ydpi;
        sConnectMgr = (ConnectivityManager) context.getSystemService("connectivity");
        sStatusBarHeight = getStatusHeight(context);
    }

    public static void initAppDir(Context context) {
        if (TextUtils.isEmpty(sPrivateExternalDir)) {
            try {
                File externalCacheDir = context.getExternalCacheDir();
                if (externalCacheDir == null) {
                    sPrivateExternalDir = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName();
                } else {
                    sPrivateExternalDir = externalCacheDir.getParent();
                }
                sPrivateDir = context.getFilesDir().getParent();
            } catch (Exception unused) {
            }
            sPublicDocumentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath();
            sPublicDCIMDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();
            sPublicDownloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            sPublicMoviesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
            sPublicMusicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath();
            sPublicPicturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
            sPublicRingtonesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getPath();
        }
    }

    public static void initBaseFsRootPath() {
        initPath(sApplicationContext);
    }

    public static void initGsmCdmaCell() {
        if (sNetWorkInited) {
            return;
        }
        String string = Settings.System.getString(sApplicationContext.getContentResolver(), DEFAULT_DATA_NETWORK);
        sDeftDataNetwork = string;
        if (string == null) {
            sDeftDataNetwork = GSM_DATA_NETWORK;
        }
        Logger.i("DefaultDataNetwork：", sDeftDataNetwork);
        TelephonyManager telephonyManager = (TelephonyManager) sApplicationContext.getSystemService("phone");
        int phoneType = telephonyManager.getPhoneType();
        sIMEI = TelephonyUtil.getIMEI(sApplicationContext, false);
        sIMSI = TelephonyUtil.getIMSI(sApplicationContext);
        sSimOperator = telephonyManager.getSimOperator();
        if ("none".equals(sDeftDataNetwork)) {
            sDeftDataNetwork = GSM_DATA_NETWORK;
            if (phoneType == 1) {
                sCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            }
        }
        if (sIMEI == null) {
            sIMEI = "";
        }
        if (sIMSI == null) {
            sIMSI = "";
        }
        sNetWorkInited = true;
    }

    public static void initPath(Context context) {
        AndroidResources.initAndroidResources(context);
        sApplicationContext = context.getApplicationContext();
        String packageName = context.getPackageName();
        sPackageName = packageName;
        if (sDeviceRootDir != null) {
            return;
        }
        boolean zEquals = packageName.equals("io.dcloud.HBuilder");
        boolean zIsSDcardExists = isSDcardExists();
        boolean z = BaseInfo.ISDEBUG || DHFile.hasFile();
        if (zEquals) {
            z = true;
        }
        initAppDir(context);
        String str = sBaseFsRootPath;
        if (zIsSDcardExists) {
            if (str == null) {
                try {
                    context.getExternalCacheDir();
                } catch (Exception unused) {
                }
                sDeviceRootDir = Environment.getExternalStorageDirectory().getPath();
                str = sDeviceRootDir + "/Android/data/" + sPackageName + sSeparatorChar + "";
            }
            sBaseFsRootPath = str;
            if (z) {
                sCacheRootDir = sDeviceRootDir;
                sBaseFsCachePath = str;
            } else {
                isPrivateDirectory = true;
                sCacheRootDir = context.getFilesDir().getParent() + sSeparatorChar;
                sBaseFsCachePath = context.getFilesDir().getAbsolutePath() + sSeparatorChar;
            }
        } else {
            BaseInfo.ISDEBUG = false;
            isPrivateDirectory = true;
            String str2 = context.getFilesDir().getParent() + sSeparatorChar;
            sDeviceRootDir = str2;
            sCacheRootDir = str2;
            String str3 = sDeviceRootDir + "";
            sBaseFsRootPath = str3;
            sBaseFsCachePath = str3;
        }
        updatePath();
    }

    public static String intToIp(int i) {
        return (i & 255) + Operators.DOT_STR + ((i >> 8) & 255) + Operators.DOT_STR + ((i >> 16) & 255) + Operators.DOT_STR + ((i >> 24) & 255);
    }

    public static boolean isOMS() {
        int i = osType;
        return i == 1 || i == 2 || i == 3;
    }

    public static boolean isSDcardExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isStreamMode() {
        try {
            Class.forName("io.dcloud.appstream.StreamAppMainActivity");
            return true;
        } catch (ClassNotFoundException e2) {
            Logger.i(TAG, "e.getMessage" + e2.getMessage());
            return false;
        }
    }

    public static void openHardwareAccelerated(Activity activity, int i, Object obj) {
        Window window = null;
        if (i != HARDWAREACCELERATED_WINDOW) {
            if (i != HARDWAREACCELERATED_VIEW || sDeviceSdkVer < 11) {
                return;
            }
            ((View) obj).setLayerType(2, null);
            return;
        }
        Window window2 = (Window) obj;
        if (window2 != null) {
            window = window2;
        } else if (activity != null) {
            window = activity.getWindow();
        }
        if (window != null) {
            window.setFlags(16777216, 16777216);
        }
    }

    public static void showIME(final View view) {
        if (sApplicationContext != null) {
            if (view == null || !(view instanceof EditText)) {
                new Timer().schedule(new TimerTask() { // from class: io.dcloud.common.adapter.util.DeviceInfo.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        Context context;
                        if (DeviceInfo.isIMEShow || (context = DeviceInfo.sApplicationContext) == null) {
                            return;
                        }
                        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
                    }
                }, 250L);
            } else {
                new Timer().schedule(new TimerTask() { // from class: io.dcloud.common.adapter.util.DeviceInfo.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (view != null) {
                            ((InputMethodManager) DeviceInfo.sApplicationContext.getSystemService("input_method")).showSoftInput(view, 0);
                        }
                    }
                }, 100L);
            }
        }
    }

    public static boolean startsWithSdcard(String str) {
        return str.startsWith(sDeviceRootDir) || str.startsWith("/sdcard/") || str.startsWith("mnt/sdcard/") || str.startsWith(sCacheRootDir);
    }

    public static void updateIMEI() {
        sIMEI = TelephonyUtil.updateIMEI(sApplicationContext);
    }

    public static void updatePath() {
        sBaseFsRootFullPath = "file://" + sBaseFsRootPath;
        sBaseResRootFullPath = "file:///android_asset/";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sPackageName=" + sPackageName);
        stringBuffer.append(";\n");
        stringBuffer.append("sDeviceRootDir=" + sDeviceRootDir);
        stringBuffer.append(";\n");
        stringBuffer.append("sBaseFsRootPath=" + sBaseFsRootPath);
        stringBuffer.append(";\n");
        stringBuffer.append("sBaseFsRootFullPath=" + sBaseFsRootFullPath);
        stringBuffer.append(";\n");
        stringBuffer.append("sBaseResRootFullPath=" + sBaseResRootFullPath);
        stringBuffer.append(";\n");
        Logger.d(TAG, stringBuffer.toString());
        BaseInfo.updateBaseInfo();
    }

    public static void updateStatusBarHeight(Activity activity) {
        if (sStatusBarHeight == 0) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int i = rect.top;
            sStatusBarHeight = i;
            if (i <= 0) {
                sStatusBarHeight = getStatusHeight(activity);
            }
        }
    }
}
