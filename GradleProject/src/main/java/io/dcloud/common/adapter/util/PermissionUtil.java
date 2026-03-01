package io.dcloud.common.adapter.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.CheckBox;
import com.igexin.assist.util.AssistUtils;
import io.dcloud.WebAppActivity;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.util.AppPermissionUtil;
import io.dcloud.common.util.DialogUtil;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.StringUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import supwisdom.j7;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionUtil implements IReflectAble {
    public static final String PMS_CAMERA = "CAMERA";
    public static final String PMS_CONTACTS = "CONTACTS";
    public static final String PMS_GALLERY = "GALLERY";
    public static final String PMS_LOCATION = "LOCATION";
    public static final String PMS_NATIVEJS = "NATIVE.JS";
    public static final String PMS_PHONE = "PHONE";
    public static final String PMS_PUSH = "PUSH";
    public static final String PMS_RECORD = "RECORD";
    public static final String PMS_SHORTCUT = "SHORTCUT";
    public static final String PMS_SMS = "SMS";
    public static final String PMS_STORAGE = "STORAGE";
    public static List<String> alwaysDeniedPer;
    public static int sDefQequestCode;
    public static HashMap<String, PermissionData> sPermissionData;
    public static int sRequestCodeCounter;
    public static HashMap<String, HashMap<String, Integer>> useRejectedCache = new HashMap<>();
    public static HashMap<Integer, Object[]> sActivityResultCallBacks = new HashMap<>();
    public static LinkedList<ShowDialogData> sUseStreamAppPermissionDialogs = new LinkedList<>();
    public static int sUseStreamAppPermissionDialogCount = 0;
    public static HashMap<Integer, HashMap<Request, String[]>> sRequestCallBacks = new HashMap<>();

    public static class PermissionData {
        public static final int CB_NOSHOW = -1;
        public static final int CB_SELECTED = 1;
        public static final int CB_SHOW = 0;
        public static final int GT_DENIED = -1;
        public static final int GT_GRANTED = 1;
        public static final int GT_ONCE = 0;
        public int checkbox;
        public int grantType;
        public int messageId;
        public String name;

        public PermissionData(String str, int i, int i2, int i3) {
            this.name = str;
            this.messageId = i;
            this.checkbox = i2;
            this.grantType = i3;
        }
    }

    public static abstract class Request {
        public static final int PERMISSION_ASK = 1;
        public static final int PERMISSION_DENIED = -1;
        public static final int PERMISSION_GRANTED = 0;
        public int mRequestCode = PermissionUtil.sDefQequestCode;

        public String getAppName() {
            return null;
        }

        public int getRequestCode() {
            return this.mRequestCode;
        }

        public abstract void onDenied(String str);

        public abstract void onGranted(String str);

        public void setRequestCode(int i) {
            this.mRequestCode = i;
        }
    }

    public static abstract class StreamPermissionRequest extends Request {
        public IApp mApp;
        public Object mTag = null;
        public String mAppid = null;
        public String mAppName = null;
        public String[] mPermission = null;
        public String[] mOriginalPermisson = null;

        public StreamPermissionRequest(IApp iApp) {
            setApp(iApp);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public String getAppName() {
            return this.mAppName;
        }

        public String[] getStreamRequestPermission() {
            return this.mOriginalPermisson;
        }

        public String[] getSystemRequestPermission() {
            return this.mPermission;
        }

        public void setApp(IApp iApp) {
            this.mApp = iApp;
            this.mAppid = iApp.obtainAppId();
            this.mAppName = iApp.obtainAppName();
        }

        public StreamPermissionRequest setRequestPermission(String... strArr) {
            this.mOriginalPermisson = strArr;
            this.mPermission = new String[strArr.length];
            int i = 0;
            while (true) {
                String[] strArr2 = this.mPermission;
                if (i >= strArr2.length) {
                    return this;
                }
                strArr2[i] = PermissionUtil.convert2SystemPermission(strArr[i]);
                i++;
            }
        }
    }

    static {
        HashMap<String, PermissionData> map = new HashMap<>();
        sPermissionData = map;
        map.put(PMS_LOCATION, new PermissionData(PMS_LOCATION, R.string.dcloud_permissions_whether_allow, -1, 1));
        sPermissionData.put(PMS_RECORD, new PermissionData(PMS_RECORD, R.string.dcloud_permissions_record_whether_allow, -1, 1));
        sPermissionData.put(PMS_CAMERA, new PermissionData(PMS_CAMERA, R.string.dcloud_permissions_camera_whether_allow, -1, 1));
        sPermissionData.put(PMS_GALLERY, new PermissionData(PMS_GALLERY, R.string.dcloud_permissions_album_whether_allow, -1, 1));
        sPermissionData.put("PUSH", new PermissionData("PUSH", R.string.dcloud_permissions_informs_whether_allow, -1, 1));
        sPermissionData.put("SHORTCUT", new PermissionData("SHORTCUT", R.string.dcloud_permissions_short_cut_close_tips, 1, 0));
        sPermissionData.put(PMS_SMS, new PermissionData(PMS_SMS, R.string.dcloud_permissions_sms_whether_allow, -1, 1));
        sPermissionData.put(PMS_PHONE, new PermissionData(PMS_PHONE, R.string.dcloud_permissions_phone_call_whether_allow, -1, 1));
        sPermissionData.put(PMS_NATIVEJS, new PermissionData(PMS_NATIVEJS, R.string.dcloud_permissions_njs_whether_allow, 1, 0));
        sDefQequestCode = 60505;
        sRequestCodeCounter = 60505;
    }

    public static boolean caseVersion(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23 && activity != null && activity.getApplicationInfo().targetSdkVersion >= 23) {
            String str = Build.BRAND;
            if (!str.equalsIgnoreCase(MobilePhoneModel.GIONEE) && !str.equalsIgnoreCase(MobilePhoneModel.QIHU360)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLocationPermission(Activity activity) {
        if (isEMUIRom(activity)) {
            return checkPermission_EMUI(activity);
        }
        if (isMiuiRom(activity)) {
            int iIsMiui = isMiui(activity, "android.permission.ACCESS_COARSE_LOCATION", (String) null);
            return iIsMiui == -100 || iIsMiui == 0;
        }
        if (isFlymeRom(activity)) {
            return checkPermission_Flyme(activity);
        }
        return true;
    }

    public static boolean checkLocationService(Activity activity) {
        try {
            LocationManager locationManager = (LocationManager) activity.getSystemService("location");
            return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static String checkPermission(IWebview iWebview, String[] strArr) {
        return strArr[0].equals("SHORTCUT") ? PermissionControler.checkPermission(iWebview, strArr) : caseVersion(iWebview.getActivity()) ? PermissionControler.checkPermission(iWebview, strArr) : checkSelfPermission(iWebview.getActivity(), convert2SystemPermission(strArr[0]), iWebview.obtainApp().obtainAppName()) == -1 ? "denied" : "notdeny";
    }

    public static boolean checkPermission_EMUI(Activity activity) {
        try {
            return ((Integer) PlatformUtil.invokeMethod("com.huawei.android.app.AppOpsManagerEx", "getMode", null, new Class[]{Integer.TYPE, String.class}, new Object[]{8, activity.getPackageName()})).intValue() == 1;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static boolean checkPermission_Flyme(Activity activity) {
        try {
            return ((Boolean) PlatformUtil.invokeMethod("meizu.security.FlymePermissionManager", "isFlymePermissionGranted", null, new Class[]{Integer.TYPE}, new Object[]{75})).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static int checkSelfPermission(Activity activity, String str, String str2) {
        return "android.permission.INSTALL_SHORTCUT".equals(str) ? 1 != AppPermissionUtil.checkPermission(activity, str2) ? 0 : -1 : (!caseVersion(activity) || str == null) ? trycatchGetPermission(activity, str, str2) : ((Integer) PlatformUtil.invokeMethod(activity.getClass().getName(), "checkSelfPermission", activity, new Class[]{str.getClass()}, new Object[]{str})).intValue();
    }

    public static int checkStreamAppPermission(Context context, String str, String str2) {
        return context.getSharedPreferences("stream_permission", 0).getInt(str + "_" + str2, 1);
    }

    public static void clearPermission(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("stream_permission", 0);
        Iterator<String> it = sPermissionData.keySet().iterator();
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        while (it.hasNext()) {
            editorEdit.remove(str + "_" + it.next());
        }
        editorEdit.commit();
    }

    public static void clearUseRejectedCache() {
        useRejectedCache.clear();
    }

    public static synchronized boolean continueShowStreamAppPermissionDialog(ShowDialogData showDialogData) {
        if (showDialogData.force) {
            return true;
        }
        if (sUseStreamAppPermissionDialogCount != 0) {
            sUseStreamAppPermissionDialogs.add(showDialogData);
        }
        sUseStreamAppPermissionDialogCount++;
        return sUseStreamAppPermissionDialogs.isEmpty();
    }

    public static String convert2StreamPermission(String str) {
        if ("android.permission.CAMERA".equals(str)) {
            return PMS_CAMERA;
        }
        if ("android.permission.RECORD_AUDIO".equals(str)) {
            return PMS_RECORD;
        }
        if ("android.permission.ACCESS_COARSE_LOCATION".equals(str)) {
            return PMS_LOCATION;
        }
        if ("android.permission.WRITE_CONTACTS".equals(str)) {
            return PMS_CONTACTS;
        }
        if ("android.permission.SEND_SMS".equals(str)) {
            return PMS_SMS;
        }
        if ("android.permission.CALL_PHONE".equals(str)) {
            return PMS_PHONE;
        }
        if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(str)) {
            return PMS_STORAGE;
        }
        if ("android.permission.INSTALL_SHORTCUT".equals(str)) {
            return "SHORTCUT";
        }
        if (!PMS_GALLERY.equals(str) && !PMS_NATIVEJS.equals(str)) {
            "PUSH".equals(str);
        }
        return str;
    }

    public static String convert2SystemPermission(String str) {
        if (PMS_CAMERA.equalsIgnoreCase(str)) {
            return "android.permission.CAMERA";
        }
        if (PMS_RECORD.equalsIgnoreCase(str)) {
            return "android.permission.RECORD_AUDIO";
        }
        if (PMS_LOCATION.equalsIgnoreCase(str)) {
            return "android.permission.ACCESS_COARSE_LOCATION";
        }
        if (PMS_CONTACTS.equalsIgnoreCase(str)) {
            return "android.permission.WRITE_CONTACTS";
        }
        if (PMS_STORAGE.equalsIgnoreCase(str)) {
            return "android.permission.WRITE_EXTERNAL_STORAGE";
        }
        if (PMS_SMS.equalsIgnoreCase(str)) {
            return "android.permission.SEND_SMS";
        }
        if (PMS_PHONE.equalsIgnoreCase(str)) {
            return "android.permission.CALL_PHONE";
        }
        if ("SHORTCUT".equalsIgnoreCase(str)) {
            return "android.permission.INSTALL_SHORTCUT";
        }
        if (!PMS_GALLERY.equalsIgnoreCase(str) && !PMS_NATIVEJS.equalsIgnoreCase(str)) {
            "PUSH".equalsIgnoreCase(str);
        }
        return str;
    }

    public static String convert5PlusValue(int i) {
        return PermissionControler.convert5PlusValue(i);
    }

    public static String convertNativePermission(String str) {
        return PermissionControler.convertNativePermission(str);
    }

    public static int getDeivceSuitablePixel(Activity activity, int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) (i * displayMetrics.density);
    }

    public static int getRequestCode() {
        if (sRequestCodeCounter >= 65535) {
            restRequstCode();
        }
        int i = sRequestCodeCounter;
        sRequestCodeCounter = i + 1;
        return i;
    }

    public static void goPermissionCenter(Activity activity, String str, String str2, Request request) {
        if (SafeCenter.goSafeCenter(activity, str2, request)) {
            return;
        }
        request.onDenied(str2);
    }

    public static void goSafeCenter(Activity activity) {
        SafeCenter.goSafeCenter(activity);
    }

    public static boolean isAndroid(Activity activity, String str, Request request) {
        Intent intent = new Intent();
        intent.setClassName("com.android.Setting", "com.android.SubSetting");
        intent.putExtra("package", activity.getPackageName());
        int requestCode = getRequestCode();
        activity.startActivityForResult(intent, requestCode);
        saveCallabckData(activity, str, request, requestCode);
        return true;
    }

    public static boolean isEMUIRom(Activity activity) {
        return Build.MANUFACTURER.toLowerCase().contains(AssistUtils.BRAND_HW);
    }

    public static boolean isFlyme(Activity activity, String str, Request request) {
        if (!Build.BRAND.contains("Meizu")) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", activity.getPackageName());
        int requestCode = getRequestCode();
        activity.startActivityForResult(intent, requestCode);
        saveCallabckData(activity, str, request, requestCode);
        return true;
    }

    public static boolean isFlymeRom(Activity activity) {
        return Build.BRAND.toLowerCase().contains(AssistUtils.BRAND_MZ);
    }

    public static boolean isMainStreamPermission(String str) {
        return PMS_LOCATION.equalsIgnoreCase(str) || "SHORTCUT".equalsIgnoreCase(str) || PMS_RECORD.equalsIgnoreCase(str);
    }

    public static int isMiui(Activity activity, String str, String str2) {
        Object systemService;
        try {
            if (isMiuiRom(activity) && (systemService = activity.getSystemService("appops")) != null) {
                int i = systemService.getClass().getField("OP_GPS").getInt(null);
                Class<?> cls = systemService.getClass();
                Class<?> cls2 = Integer.TYPE;
                int iIntValue = ((Integer) cls.getMethod("checkOp", cls2, cls2, String.class).invoke(systemService, Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), activity.getPackageName())).intValue();
                if (iIntValue == systemService.getClass().getField("MODE_IGNORED").getInt(null)) {
                    return -1;
                }
                if (iIntValue == systemService.getClass().getField("MODE_ALLOWED").getInt(null)) {
                    return 0;
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return -100;
    }

    public static boolean isMiuiRom(Activity activity) {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        String property = System.getProperty("http.agent");
        return !TextUtils.isEmpty(property) && property.toLowerCase().contains("miui");
    }

    public static void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        int i3 = i % 10000;
        Object[] objArr = sActivityResultCallBacks.get(Integer.valueOf(i3));
        if (objArr != null) {
            if (System.currentTimeMillis() - activity.getIntent().getLongExtra(IntentConst.PER_GO_CENTER_TIME, System.currentTimeMillis()) > 1000) {
                sActivityResultCallBacks.remove(Integer.valueOf(i3));
                String str = (String) objArr[0];
                String strConvert2SystemPermission = convert2SystemPermission(str);
                Request request = (Request) objArr[1];
                activity.getIntent().removeExtra(IntentConst.PER_GO_CENTER_TIME);
                if (checkSelfPermission(activity, strConvert2SystemPermission, request.getAppName()) == 0) {
                    request.onGranted(str);
                } else {
                    request.onDenied(str);
                }
            }
        }
    }

    public static void onRequestSysPermissionResume(Activity activity) {
        int intExtra = activity.getIntent().getIntExtra(IntentConst.PER_GO_CENTER_REQUESTCODE, 0);
        if (intExtra != 0) {
            onActivityResult(activity, intExtra, 0, null);
        }
    }

    public static void onSystemPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        String[] strArr2;
        HashMap<Request, String[]> mapRemove = sRequestCallBacks.remove(Integer.valueOf(i));
        if (mapRemove == null || mapRemove.size() <= 0) {
            return;
        }
        Request[] requestArr = (Request[]) mapRemove.keySet().toArray(new Request[0]);
        Request request = requestArr.length > 0 ? requestArr[0] : null;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int i3 = iArr[i2];
            String strConvert2StreamPermission = convert2StreamPermission(strArr[i2]);
            if (i3 == -1) {
                try {
                    if (!j7.a(activity, strArr[i2])) {
                        if (alwaysDeniedPer == null) {
                            String bundleData = SP.getBundleData(activity, "ALWAYS_DENIED_PERMISSION", "permissions");
                            if (TextUtils.isEmpty(bundleData)) {
                                alwaysDeniedPer = new ArrayList();
                            } else {
                                alwaysDeniedPer = new ArrayList(Arrays.asList(bundleData.split(",")));
                            }
                        }
                        if (!alwaysDeniedPer.contains(strArr[i2])) {
                            alwaysDeniedPer.add(strArr[i2]);
                            StringBuilder sb = new StringBuilder();
                            Iterator<String> it = alwaysDeniedPer.iterator();
                            while (it.hasNext()) {
                                sb.append(it.next());
                                sb.append(",");
                            }
                            SP.setBundleData(activity, "ALWAYS_DENIED_PERMISSION", "permissions", sb.substring(0, sb.length() - 1));
                        }
                    }
                } catch (RuntimeException unused) {
                }
                if (request != null) {
                    request.onDenied(strConvert2StreamPermission);
                }
            } else if (i3 == 0 && request != null) {
                request.onGranted(strConvert2StreamPermission);
            }
        }
        if (strArr.length != 0 || iArr.length != 0 || request == null || (strArr2 = mapRemove.get(request)) == null) {
            return;
        }
        for (String str : strArr2) {
            request.onDenied(convert2StreamPermission(str));
        }
    }

    public static void putStreamAppPermission(Context context, String str, String str2, int i) {
        context.getSharedPreferences("stream_permission", 0).edit().putInt(str + "_" + str2, i).commit();
    }

    public static void removeStreamAppPermission(Context context, String str, String str2) {
        context.getSharedPreferences("stream_permission", 0).edit().remove(str + "_" + str2).commit();
    }

    public static void removeTempPermission(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("stream_permission", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        for (String str2 : sPermissionData.keySet()) {
            String str3 = str + "_" + str2;
            if (sharedPreferences.contains(str3) && (sharedPreferences.getInt(str3, 1) != 0 || "SHORTCUT".equals(str2) || PMS_NATIVEJS.equals(str2))) {
                editorEdit.remove(str3);
            }
        }
        editorEdit.commit();
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        PermissionControler.requestPermissions(activity, strArr, i);
    }

    public static void requestSystemPermissions(Activity activity, String[] strArr, int i, Request request) {
        if (caseVersion(activity) && strArr != null) {
            HashMap<Request, String[]> map = new HashMap<>();
            map.put(request, strArr);
            sRequestCallBacks.put(Integer.valueOf(i), map);
            requestPermissions(activity, strArr, i);
            return;
        }
        if (strArr != null) {
            try {
                for (String str : strArr) {
                    request.onGranted(convert2StreamPermission(str));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void restRequstCode() {
        sRequestCodeCounter = sDefQequestCode;
    }

    public static void saveCallabckData(Activity activity, String str, Request request, int i) {
        sActivityResultCallBacks.put(Integer.valueOf(i), new Object[]{str, request});
        activity.getIntent().putExtra(IntentConst.PER_GO_CENTER_REQUESTCODE, i);
        activity.getIntent().putExtra(IntentConst.PER_GO_CENTER_TIME, System.currentTimeMillis());
    }

    public static synchronized void showStreamAppPermissionDialog() {
        int i = sUseStreamAppPermissionDialogCount;
        if (i > 0) {
            sUseStreamAppPermissionDialogCount = i - 1;
        }
        if (!sUseStreamAppPermissionDialogs.isEmpty()) {
            ShowDialogData showDialogDataPop = sUseStreamAppPermissionDialogs.pop();
            showDialogDataPop.force = true;
            if (1 != useStreamPermission(showDialogDataPop)) {
                showStreamAppPermissionDialog();
            }
        }
    }

    public static int trycatchGetPermission(Activity activity, String str, String str2) {
        int i = -1;
        try {
            try {
                if ("android.permission.CAMERA".equals(str)) {
                    Camera cameraOpen = null;
                    int i2 = 0;
                    while (true) {
                        try {
                            if (i2 >= Camera.getNumberOfCameras()) {
                                break;
                            }
                            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                            Camera.getCameraInfo(i2, cameraInfo);
                            if (cameraInfo.facing == 1) {
                                cameraOpen = Camera.open(i2);
                                break;
                            }
                            i2++;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (cameraOpen == null) {
                        cameraOpen = Camera.open();
                    }
                    if (cameraOpen != null) {
                        Camera.Parameters parameters = cameraOpen.getParameters();
                        if (parameters != null) {
                            parameters.getSupportedVideoSizes();
                        }
                        cameraOpen.release();
                        return 0;
                    }
                } else {
                    try {
                        if ("android.permission.RECORD_AUDIO".equals(str)) {
                            try {
                                MediaRecorder mediaRecorder = new MediaRecorder();
                                mediaRecorder.reset();
                                mediaRecorder.setAudioSource(0);
                                mediaRecorder.setOutputFile(activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/temp.3gp");
                                mediaRecorder.setAudioSamplingRate(96000);
                                mediaRecorder.setOutputFormat(1);
                                mediaRecorder.setAudioEncoder(3);
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                                mediaRecorder.stop();
                                mediaRecorder.release();
                                if (new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/temp.3gp").length() > 0) {
                                    try {
                                        new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/temp.3gp").delete();
                                        return 0;
                                    } catch (IOException e3) {
                                        e = e3;
                                        i = 0;
                                        if ((e.getMessage() == null || (!e.getMessage().contains("Permission deny") && !e.getMessage().contains("Permission denied"))) && !Build.BRAND.equalsIgnoreCase(MobilePhoneModel.GIONEE)) {
                                            return 0;
                                        }
                                        return i;
                                    } catch (Exception e4) {
                                        e = e4;
                                        i = 0;
                                        String message = e.getMessage();
                                        if (message == null) {
                                            return 0;
                                        }
                                        if (!message.contains("start failed") && !message.contains("setAudioSource failed")) {
                                            return 0;
                                        }
                                        return i;
                                    }
                                }
                            } catch (IOException e5) {
                                e = e5;
                            } catch (Exception e6) {
                                e = e6;
                            }
                        } else {
                            if ("android.permission.ACCESS_COARSE_LOCATION".equals(str)) {
                                LocationManager locationManager = (LocationManager) activity.getSystemService("location");
                                boolean zIsProviderEnabled = locationManager.isProviderEnabled("gps");
                                boolean zIsProviderEnabled2 = locationManager.isProviderEnabled("network");
                                boolean zEquals = TextUtils.equals("ZTE B880", Build.MODEL);
                                if (!zIsProviderEnabled && !zIsProviderEnabled2) {
                                    if (zEquals) {
                                        return 0;
                                    }
                                }
                                int iIsMiui = isMiui(activity, str, str2);
                                if (iIsMiui != -100) {
                                    return iIsMiui;
                                }
                                return 0;
                            }
                            if (!"android.permission.WRITE_CONTACTS".equals(str) && !"android.permission.SEND_SMS".equals(str) && !"android.permission.CALL_PHONE".equals(str)) {
                                if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(str)) {
                                    try {
                                        File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/temp.arm");
                                        if (!file.getParentFile().exists()) {
                                            file.getParentFile().mkdirs();
                                        }
                                        if (file.exists()) {
                                            return 0;
                                        }
                                        file.createNewFile();
                                        return 0;
                                    } catch (Exception e7) {
                                        e7.printStackTrace();
                                    }
                                } else if ("android.permission.INSTALL_SHORTCUT".equals(str)) {
                                    if (1 != AppPermissionUtil.checkPermission(activity, str2)) {
                                        return 0;
                                    }
                                } else if (!PMS_GALLERY.equals(str) && !PMS_NATIVEJS.equals(str)) {
                                    "PUSH".equals(str);
                                }
                            }
                        }
                    } catch (SecurityException unused) {
                    }
                }
            } catch (Throwable unused2) {
                return 0;
            }
        } catch (IllegalArgumentException | SecurityException unused3) {
        }
        return -1;
    }

    public static void unregisterWebAppReStartEvent(IApp iApp, ISysEventListener iSysEventListener) {
        iApp.unregisterSysEventListener(iSysEventListener, ISysEventListener.SysEventType.onWebAppReStart);
    }

    public static synchronized void usePermission(Activity activity, final String str, String str2, int i, final Request request) {
        HashMap<String, Integer> map;
        String strConvert2SystemPermission = convert2SystemPermission(str2);
        if (checkSelfPermission(activity, strConvert2SystemPermission, request.getAppName()) == 0) {
            request.onGranted(str2);
            return;
        }
        if (!useRejectedCache.containsKey(str) || i <= 0 || (map = useRejectedCache.get(str)) == null || map.isEmpty() || map.get(str2).intValue() < i) {
            useSystemPermission(activity, strConvert2SystemPermission, new Request() { // from class: io.dcloud.common.adapter.util.PermissionUtil.1
                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onDenied(String str3) {
                    try {
                        if (PermissionUtil.useRejectedCache.containsKey(str)) {
                            HashMap map2 = (HashMap) PermissionUtil.useRejectedCache.get(str);
                            if (map2 == null) {
                                map2 = new HashMap();
                            }
                            if (map2.containsKey(str3)) {
                                map2.put(str3, Integer.valueOf(((Integer) map2.get(str3)).intValue() + 1));
                            } else {
                                map2.put(str3, 1);
                            }
                            PermissionUtil.useRejectedCache.put(str3, map2);
                        } else {
                            HashMap map3 = new HashMap();
                            map3.put(str3, 1);
                            PermissionUtil.useRejectedCache.put(str, map3);
                        }
                    } catch (Exception unused) {
                    }
                    request.onDenied(str3);
                }

                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onGranted(String str3) {
                    try {
                        if (PermissionUtil.useRejectedCache.containsKey(str)) {
                            ((HashMap) PermissionUtil.useRejectedCache.get(str)).remove(str3);
                        }
                    } catch (Exception unused) {
                    }
                    request.onGranted(str3);
                }
            });
        } else {
            request.onDenied(str2);
        }
    }

    public static int useStreamPermission(ShowDialogData showDialogData) {
        Activity activity = showDialogData.activity;
        String str = showDialogData.appid;
        String str2 = showDialogData.streamPerName;
        Request request = showDialogData.request;
        int iCheckStreamAppPermission = checkStreamAppPermission(activity, str, str2);
        if (iCheckStreamAppPermission == -1) {
            request.onDenied(str2);
        } else if (iCheckStreamAppPermission == 0) {
            request.onGranted(str2);
        } else if (iCheckStreamAppPermission == 1) {
            showStreamAppPermissionDialog(showDialogData);
        }
        return iCheckStreamAppPermission;
    }

    public static boolean useSystemPermission(Activity activity, String str, Request request) {
        boolean z = activity.getApplicationInfo().targetSdkVersion >= 23 && str != null && str.contains("android.permission") && Build.VERSION.SDK_INT >= 23;
        String strConvert2StreamPermission = convert2StreamPermission(str);
        if (z) {
            request.setRequestCode(getRequestCode());
            int iCheckSelfPermission = checkSelfPermission(activity, str, request.getAppName());
            if (iCheckSelfPermission == -1) {
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    requestSystemPermissions(activity, new String[]{str, "android.permission.ACCESS_FINE_LOCATION"}, request.getRequestCode(), request);
                    return false;
                }
                requestSystemPermissions(activity, new String[]{str}, request.getRequestCode(), request);
                return false;
            }
            if (iCheckSelfPermission != 0) {
                return false;
            }
            request.onGranted(strConvert2StreamPermission);
        } else {
            request.onGranted(strConvert2StreamPermission);
        }
        return true;
    }

    public static void useSystemPermissions(Activity activity, String[] strArr, Request request) {
        boolean z = true;
        boolean z2 = activity.getApplicationInfo().targetSdkVersion >= 23 && strArr != null && strArr.length > 0 && Build.VERSION.SDK_INT >= 23;
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        if (!z2) {
            for (int i = 0; i < arrayList.size(); i++) {
                request.onGranted(convert2StreamPermission((String) arrayList.get(i)));
            }
            return;
        }
        request.setRequestCode(getRequestCode());
        ArrayList arrayList2 = new ArrayList();
        if (alwaysDeniedPer == null) {
            String bundleData = SP.getBundleData(activity, "ALWAYS_DENIED_PERMISSION", "permissions");
            if (TextUtils.isEmpty(bundleData)) {
                alwaysDeniedPer = new ArrayList();
            } else {
                alwaysDeniedPer = new ArrayList(Arrays.asList(bundleData.split(",")));
            }
        } else {
            z = false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String strConvert2SystemPermission = convert2SystemPermission((String) arrayList.get(i2));
            if (checkSelfPermission(activity, strConvert2SystemPermission, request.getAppName()) == 0) {
                arrayList2.add(strConvert2SystemPermission);
                request.onGranted(convert2StreamPermission(strConvert2SystemPermission));
            } else if (alwaysDeniedPer.contains(strConvert2SystemPermission) && !z && !j7.a(activity, strConvert2SystemPermission)) {
                arrayList2.add(strConvert2SystemPermission);
                request.onDenied(convert2StreamPermission(strConvert2SystemPermission));
            }
        }
        if (arrayList2.size() > 0) {
            arrayList.removeAll(arrayList2);
        }
        if (arrayList.size() > 0) {
            requestSystemPermissions(activity, (String[]) arrayList.toArray(new String[0]), request.getRequestCode(), request);
        }
    }

    public static class ShowDialogData {
        public Activity activity;
        public String appName;
        public String appid;
        public boolean force;
        public IApp mApp;
        public Request request;
        public String streamPerName;
        public int tryTimes;

        public ShowDialogData(Activity activity, IApp iApp, String str, String str2, String str3, Request request) {
            this(activity, iApp, str, str2, str3);
            setRequestHandler(request);
        }

        public void setRequestHandler(Request request) {
            this.request = request;
        }

        public ShowDialogData(Activity activity, IApp iApp, String str, String str2, String str3) {
            this.tryTimes = 0;
            this.activity = activity;
            this.mApp = iApp;
            this.streamPerName = str;
            this.appid = str2;
            this.appName = str3;
        }
    }

    public static int checkSelfPermission(Activity activity, String str) {
        if (activity == null || str == null) {
            return 0;
        }
        return ((Integer) PlatformUtil.invokeMethod(activity.getClass().getName(), "checkSelfPermission", activity, new Class[]{str.getClass()}, new Object[]{str})).intValue();
    }

    public static void showStreamAppPermissionDialog(final ShowDialogData showDialogData) {
        String string;
        final Activity activity = showDialogData.activity;
        final String str = showDialogData.appid;
        String str2 = showDialogData.appName;
        final String str3 = showDialogData.streamPerName;
        final Request request = showDialogData.request;
        Logger.e("Permission", "showStreamAppPermissionDialog streamPerName=" + str3 + ";count=" + sUseStreamAppPermissionDialogCount);
        PermissionData permissionData = sPermissionData.get(str3);
        if (permissionData == null) {
            request.onGranted(str3);
            return;
        }
        if (continueShowStreamAppPermissionDialog(showDialogData)) {
            int i = showDialogData.tryTimes + 1;
            showDialogData.tryTimes = i;
            boolean z = i == 1;
            final AlertDialog alertDialogCreate = DialogUtil.initDialogTheme(activity, true).create();
            if (activity != null && (activity instanceof WebAppActivity)) {
                ((WebAppActivity) activity).recordDialog(alertDialogCreate);
            }
            alertDialogCreate.setCanceledOnTouchOutside(false);
            if (z) {
                if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
                    alertDialogCreate.setMessage(activity.getString(R.string.dcloud_permissions_short_cut_tips));
                } else if (TextUtils.isEmpty(str2)) {
                    alertDialogCreate.setMessage(StringUtil.format(activity.getString(permissionData.messageId), "App"));
                } else {
                    alertDialogCreate.setMessage(StringUtil.format(activity.getString(permissionData.messageId), str2));
                }
            } else {
                if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
                    alertDialogCreate.setMessage(activity.getString(R.string.dcloud_permissions_short_cut_tips2));
                }
                if (PMS_LOCATION.equalsIgnoreCase(str3)) {
                    alertDialogCreate.setMessage(activity.getString(R.string.dcloud_permissions_geo_retry_tips));
                } else {
                    alertDialogCreate.setMessage(StringUtil.format(activity.getString(R.string.dcloud_permissions_retry_tips), activity.getPackageManager().getApplicationLabel(activity.getApplicationInfo())));
                }
            }
            CheckBox checkBox = null;
            if (permissionData.checkbox != -1 && z) {
                checkBox = new CheckBox(activity);
                checkBox.setText(R.string.dcloud_permissions_checkbox_close_tips);
                checkBox.setTextColor(-65536);
                checkBox.setChecked(permissionData.checkbox == 1);
                int deivceSuitablePixel = getDeivceSuitablePixel(activity, 20);
                alertDialogCreate.setView(checkBox, deivceSuitablePixel, deivceSuitablePixel, 0, 0);
            }
            final CheckBox checkBox2 = checkBox;
            final ISysEventListener iSysEventListener = new ISysEventListener() { // from class: io.dcloud.common.adapter.util.PermissionUtil.3
                @Override // io.dcloud.common.DHInterface.ISysEventListener
                public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                    Logger.e("Permission", "unregisterSysEventListener registerSysEventListener pEventType=" + sysEventType);
                    if (sysEventType != ISysEventListener.SysEventType.onWebAppReStart) {
                        return false;
                    }
                    alertDialogCreate.dismiss();
                    PermissionUtil.unregisterWebAppReStartEvent(showDialogData.mApp, this);
                    try {
                        request.onDenied(str3);
                        PermissionUtil.showStreamAppPermissionDialog();
                        return false;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return false;
                    }
                }
            };
            if (showDialogData.mApp != null) {
                Logger.e("Permission", "showStreamAppPermissionDialog registerSysEventListener");
                showDialogData.mApp.registerSysEventListener(iSysEventListener, ISysEventListener.SysEventType.onWebAppReStart);
            }
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.util.PermissionUtil.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    alertDialogCreate.dismiss();
                    IApp iApp = showDialogData.mApp;
                    if (iApp != null) {
                        PermissionUtil.unregisterWebAppReStartEvent(iApp, iSysEventListener);
                    }
                    if (i2 == -1) {
                        PermissionUtil.putStreamAppPermission(activity, str, str3, !(checkBox2 == null ? 1 : r4.isChecked()));
                        request.onGranted(str3);
                    } else if (i2 == -2) {
                        CheckBox checkBox3 = checkBox2;
                        if (checkBox3 != null && checkBox3.isChecked()) {
                            PermissionUtil.putStreamAppPermission(activity, str, str3, -1);
                        }
                        request.onDenied(str3);
                    }
                    PermissionUtil.showStreamAppPermissionDialog();
                }
            };
            String string2 = activity.getString(z ? R.string.dcloud_common_no_allow : R.string.dcloud_common_cancel);
            if (z) {
                string = activity.getString(R.string.dcloud_common_allow);
            } else if (!Build.BRAND.equalsIgnoreCase(MobilePhoneModel.QiKU) && !PMS_LOCATION.equalsIgnoreCase(str3)) {
                string = activity.getString(R.string.dcloud_permissions_reauthorization);
            } else {
                string = activity.getString(R.string.dcloud_permissions_reopened);
            }
            alertDialogCreate.setButton(-2, string2, onClickListener);
            alertDialogCreate.setButton(-1, string, onClickListener);
            alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.adapter.util.PermissionUtil.5
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 1 || i2 != 4) {
                        return false;
                    }
                    alertDialogCreate.dismiss();
                    IApp iApp = showDialogData.mApp;
                    if (iApp != null) {
                        PermissionUtil.unregisterWebAppReStartEvent(iApp, iSysEventListener);
                    }
                    request.onDenied(str3);
                    PermissionUtil.showStreamAppPermissionDialog();
                    return true;
                }
            });
            try {
                alertDialogCreate.show();
                alertDialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: io.dcloud.common.adapter.util.PermissionUtil.6
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        Activity activity2 = activity;
                        if (activity2 == null || !(activity2 instanceof WebAppActivity)) {
                            return;
                        }
                        ((WebAppActivity) activity2).removeFromRecord(alertDialogCreate);
                    }
                });
            } catch (Exception e2) {
                Logger.e("ian", "try dialog");
                e2.printStackTrace();
            }
        }
    }

    public static void usePermission(Activity activity, String str, final Request request) {
        useSystemPermission(activity, convert2SystemPermission(str), new Request() { // from class: io.dcloud.common.adapter.util.PermissionUtil.2
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str2) {
                request.onDenied(str2);
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str2) {
                request.onGranted(str2);
            }
        });
    }

    public static boolean isMiui(Activity activity, String str, Request request) {
        String property = System.getProperty("http.agent");
        if (!TextUtils.isEmpty(property) ? property.toLowerCase().contains("miui") : Build.BRAND.contains("Xiaomi")) {
            return false;
        }
        int requestCode = getRequestCode();
        Intent intent = new Intent();
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", activity.getPackageName());
        try {
            activity.startActivityForResult(intent, requestCode);
            saveCallabckData(activity, str, request, requestCode);
            return true;
        } catch (ActivityNotFoundException unused) {
            intent.setComponent(null);
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            try {
                activity.startActivityForResult(intent, requestCode);
                saveCallabckData(activity, str, request, requestCode);
                return true;
            } catch (ActivityNotFoundException unused2) {
                intent.setComponent(null);
                intent.setPackage("com.android.Setting");
                activity.startActivityForResult(intent, requestCode);
                saveCallabckData(activity, str, request, requestCode);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return true;
        }
    }

    public static class SafeCenter {
        public static ArrayList<Item> datas = new ArrayList<>();

        public static class Item {
            public String action;
            public String clsName;
            public String extParamName;
            public String pname;

            public Item(String str, String str2, String str3, String str4) {
                this.pname = str;
                this.clsName = str2;
                this.extParamName = str3;
                this.action = str4;
            }
        }

        public static void goSafeCenter(Activity activity) {
            init(activity);
            PackageManager packageManager = activity.getPackageManager();
            for (int i = 0; i < datas.size(); i++) {
                Intent intent = new Intent();
                Item item = datas.get(i);
                try {
                    if (packageManager.getPackageInfo(item.pname, 0) != null) {
                        if (!TextUtils.isEmpty(item.clsName)) {
                            intent.setClassName(item.pname, item.clsName);
                        } else if (!TextUtils.isEmpty(item.pname)) {
                            intent.setPackage(item.pname);
                        }
                        if (!TextUtils.isEmpty(item.action)) {
                            intent.setAction(item.action);
                        }
                        if (!TextUtils.isEmpty(item.extParamName)) {
                            intent.putExtra(item.extParamName, activity.getPackageName());
                        }
                        try {
                            intent.setFlags(268435456);
                            activity.startActivity(intent);
                            Logger.i("Permission", "successful " + Build.MODEL + "intent=" + intent);
                        } catch (ActivityNotFoundException e2) {
                            Logger.e("Permission", "ActivityNotFoundException =" + e2);
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            Logger.e("Permission", "Exception =" + e3);
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }

        public static void init(Context context) {
            if (datas.isEmpty()) {
                try {
                    JSONArray jSONArray = new JSONArray(new String(IOUtil.toString(new FileInputStream(new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/temp.j")))));
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        String strOptString = jSONObjectOptJSONObject.optString("p");
                        if (!TextUtils.isEmpty(strOptString)) {
                            datas.add(new Item(strOptString, jSONObjectOptJSONObject.optString("c"), jSONObjectOptJSONObject.optString("e"), jSONObjectOptJSONObject.optString("a")));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (datas.isEmpty()) {
                    datas.add(new Item("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity", "extra_pkgname", null));
                    datas.add(new Item("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity", "extra_pkgname", null));
                    datas.add(new Item("com.meizu.safe", "com.meizu.safe.security.AppSecActivity", "packageName", null));
                    datas.add(new Item("com.aliyun.mobile.permission", "com.aliyun.mobile.permission.ExternalAppDetailActivity", "packageName", null));
                    datas.add(new Item("com.iqoo.secure", "com.iqoo.secure.MainActivity", "packageName", null));
                    datas.add(new Item("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity", "package", null));
                    datas.add(new Item("com.mediatek.security", "com.mediatek.security.ui.PermissionControlPageActivity", "package", null));
                    datas.add(new Item("com.yulong.android.launcher3", "com.yulong.android.launcher3.LauncherSettingsActivity", "package", null));
                    datas.add(new Item("com.android.settings", "com.android.settings.Settings$ManageApplicationsActivity", "package", null));
                    datas.add(new Item(null, null, "package", "android.settings.MANAGE_APPLICATIONS_SETTINGS"));
                }
            }
        }

        public static boolean goSafeCenter(Activity activity, String str, Request request) {
            int requestCode = PermissionUtil.getRequestCode();
            if (PermissionUtil.PMS_LOCATION.equalsIgnoreCase(str)) {
                LocationManager locationManager = (LocationManager) activity.getSystemService("location");
                boolean zIsProviderEnabled = locationManager.isProviderEnabled("gps");
                boolean zIsProviderEnabled2 = locationManager.isProviderEnabled("network");
                if (!zIsProviderEnabled && !zIsProviderEnabled2) {
                    Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    try {
                        activity.startActivity(intent);
                        PermissionUtil.saveCallabckData(activity, str, request, requestCode);
                        Logger.i("Permission", "successful " + Build.MODEL + "intent=" + intent);
                        return true;
                    } catch (ActivityNotFoundException e2) {
                        Logger.e("Permission", "ActivityNotFoundException =" + e2);
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        Logger.e("Permission", "Exception =" + e3);
                    }
                }
            }
            init(activity);
            PackageManager packageManager = activity.getPackageManager();
            int i = 0;
            int i2 = 0;
            while (i2 < datas.size()) {
                Intent intent2 = new Intent();
                Item item = datas.get(i2);
                try {
                    if (packageManager.getPackageInfo(item.pname, i) == null) {
                        continue;
                    } else {
                        if (!TextUtils.isEmpty(item.clsName)) {
                            intent2.setClassName(item.pname, item.clsName);
                        } else if (!TextUtils.isEmpty(item.pname)) {
                            intent2.setPackage(item.pname);
                        }
                        if (!TextUtils.isEmpty(item.action)) {
                            intent2.setAction(item.action);
                        }
                        if (!TextUtils.isEmpty(item.extParamName)) {
                            intent2.putExtra(item.extParamName, activity.getPackageName());
                        }
                        try {
                            activity.startActivityForResult(intent2, requestCode);
                            PermissionUtil.saveCallabckData(activity, str, request, requestCode);
                            Logger.i("Permission", "successful " + Build.MODEL + "intent=" + intent2);
                            return true;
                        } catch (ActivityNotFoundException e4) {
                            Logger.e("Permission", "ActivityNotFoundException =" + e4);
                            e4.printStackTrace();
                        } catch (Exception e5) {
                            Logger.e("Permission", "Exception =" + e5);
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                i2++;
                i = 0;
            }
            return true;
        }
    }
}
