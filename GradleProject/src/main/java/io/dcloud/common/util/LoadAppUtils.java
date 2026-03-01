package io.dcloud.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.igexin.assist.util.AssistUtils;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class LoadAppUtils {
    public static final int APK_DOWNGRADE = -1;
    public static final int APK_INSTALLED = 0;
    public static final int APK_UNINSTALLED = -2;
    public static final int APK_UPGRADE = 1;
    public static final int APP_TYPE_ALL = 0;
    public static final int APP_TYPE_NON_SYSTEM = 1;
    public static final int APP_TYPE_SYSTEM = 2;
    public static final String TAG = "LoadAppUtils";

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

        public static boolean goSafeCenter(Context context) {
            try {
                init(context);
                PackageManager packageManager = context.getPackageManager();
                for (int i = 0; i < datas.size(); i++) {
                    Intent intent = new Intent();
                    Item item = datas.get(i);
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
                            intent.putExtra(item.extParamName, context.getPackageName());
                        }
                        intent.setFlags(268435456);
                        context.startActivity(intent);
                        Logger.e("Permission", "successful " + Build.MODEL + "intent=" + intent);
                        return true;
                    }
                }
                return true;
            } catch (Exception e2) {
                Logger.e("Permission", "Exception =" + e2);
                return false;
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
    }

    public static String getApkFileLable(Context context, String str) {
        try {
            Class<?> cls = Class.forName(ReflectUtils.CLASSNAME_PAGEAGEPARSE);
            Class<?>[] clsArr = {String.class};
            int i = Build.VERSION.SDK_INT;
            Constructor<?> constructor = i >= 21 ? cls.getConstructor(new Class[0]) : cls.getConstructor(clsArr);
            Object objNewInstance = i >= 21 ? constructor.newInstance(new Object[0]) : constructor.newInstance(str);
            Log.d("DownloadUtils", "pkgParser:" + objNewInstance.toString());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Object objInvoke = cls.getDeclaredMethod("parsePackage", i >= 21 ? new Class[]{File.class, Integer.TYPE} : new Class[]{File.class, String.class, DisplayMetrics.class, Integer.TYPE}).invoke(objNewInstance, i >= 21 ? new Object[]{new File(str), 0} : new Object[]{new File(str), str, displayMetrics, 0});
            ApplicationInfo applicationInfo = (ApplicationInfo) objInvoke.getClass().getDeclaredField("applicationInfo").get(objInvoke);
            Log.d("DownloadUtils", "pkg:" + applicationInfo.packageName + " uid=" + applicationInfo.uid);
            Class<?> cls2 = Class.forName("android.content.res.AssetManager");
            Object objNewInstance2 = cls2.getConstructor(null).newInstance(null);
            cls2.getDeclaredMethod("addAssetPath", String.class).invoke(objNewInstance2, str);
            Resources resources = context.getResources();
            Resources resources2 = (Resources) Resources.class.getConstructor(objNewInstance2.getClass(), resources.getDisplayMetrics().getClass(), resources.getConfiguration().getClass()).newInstance(objNewInstance2, resources.getDisplayMetrics(), resources.getConfiguration());
            int i2 = applicationInfo.labelRes;
            CharSequence text = i2 != 0 ? resources2.getText(i2) : null;
            Log.d("DownloadUtils", "label=" + ((Object) text));
            return text.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "-1";
        }
    }

    public static String[] getApkFileSignatureAndPackageName(Context context, String str) {
        Object obj;
        Signature[] apkSignature;
        try {
            String[] packageSignatures = getPackageSignatures(context, str);
            if (packageSignatures != null) {
                return packageSignatures;
            }
            try {
                obj = parsePackage(str, 64);
            } catch (OutOfMemoryError unused) {
                obj = null;
            }
            if (obj == null || (apkSignature = getApkSignature(obj, str)) == null || apkSignature.length <= 0) {
                return null;
            }
            return new String[]{HashUtils.getHash(Arrays.toString(apkSignature[0].toByteArray())).toLowerCase(), (String) ReflectUtils.getObjectFieldNoDeclared(ReflectUtils.getField(obj, "applicationInfo"), "packageName")};
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String[] getApkFileSignatureAndPackageNameEx(Context context, String str) {
        Signature[] signatureArr;
        try {
            Object obj = parsePackage(str, 64);
            if (obj != null && (signatureArr = (Signature[]) ReflectUtils.getField(obj, "mSignatures")) != null && signatureArr.length != 0) {
                return new String[]{HashUtils.getHash(Arrays.toString(signatureArr[0].toByteArray())).toLowerCase(), (String) ReflectUtils.getField(obj, "packageName")};
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Signature[] getApkSignature(Object obj, String str) {
        Signature[] signatureArr = new Signature[0];
        try {
            signatureArr = (Signature[]) ReflectUtils.getField(obj, "mSignatures");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
        if (signatureArr != null && signatureArr.length > 0) {
            return signatureArr;
        }
        return null;
    }

    public static String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAppSignatureMd5(Context context, String str) {
        try {
            return Md5Utils.md5LowerCase(Arrays.toString(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray()));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String getAppSignatureSHA1(Context context) {
        try {
            String signatureString = null;
            for (Signature signature : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                signatureString = getSignatureString(signature, "SHA1");
            }
            return signatureString;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String getAppVersionName(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static Intent getDataAndTypeIntent(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".dc.fileprovider", new File(str));
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, str2);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(str)), str2);
        }
        return intent;
    }

    public static int getLoadState(PackageInfo packageInfo, int i) {
        if (packageInfo == null) {
            return -2;
        }
        int i2 = packageInfo.versionCode;
        if (i2 == i) {
            return 0;
        }
        return i2 < i ? 1 : -1;
    }

    public static PackageInfo getLoadedApp(Context context, PackageManager packageManager, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getLoadedAppNameByPackageInfo(Context context, PackageManager packageManager, PackageInfo packageInfo) {
        if (packageManager == null) {
            packageManager = context.getPackageManager();
        }
        return packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
    }

    public static String[] getPackageSignatures(Context context, String str) {
        Signature[] signatureArr;
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 64);
            if (packageArchiveInfo == null || (signatureArr = packageArchiveInfo.signatures) == null || signatureArr.length <= 0) {
                return null;
            }
            return new String[]{HashUtils.getHash(Arrays.toString(signatureArr[0].toByteArray())).toLowerCase(), packageArchiveInfo.applicationInfo.packageName};
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String getSignatureString(Signature signature, String str) {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest != null) {
                byte[] bArrDigest = messageDigest.digest(byteArray);
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                }
                return sb.toString();
            }
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return "error!";
    }

    public static boolean isAppLoad(Context context, String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(context.getPackageName())) {
            return true;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 256) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return new File("/sdcard/Android/data/" + str).exists();
        }
    }

    public static boolean isEMUIRom() {
        return Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI);
    }

    public static boolean isSystemApp(ApplicationInfo applicationInfo) {
        return ((applicationInfo.flags & 1) <= 0 || applicationInfo.publicSourceDir.startsWith("data/dataapp") || applicationInfo.publicSourceDir.startsWith("/data/dataapp")) ? false : true;
    }

    public static boolean isValidAppPackageName(String str) {
        return Pattern.compile("^[a-zA-Z_]\\w*(\\.[a-zA-Z_]\\w*)*$").matcher(str).matches();
    }

    public static boolean isVivoRom() {
        return Build.FINGERPRINT.toLowerCase().contains(AssistUtils.BRAND_VIVO) || Build.MODEL.toLowerCase().contains(AssistUtils.BRAND_VIVO);
    }

    public static void loadRunApp(Context context, String str) {
        context.startActivity(getDataAndTypeIntent(context, str, "application/vnd.android.package-archive"));
    }

    public static boolean openApp(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return false;
        }
        context.startActivity(launchIntentForPackage);
        return true;
    }

    public static Object parsePackage(String str, int i) {
        try {
            int i2 = Build.VERSION.SDK_INT;
            Object objNewInstance = i2 >= 21 ? ReflectUtils.getObjectConstructor(ReflectUtils.CLASSNAME_PAGEAGEPARSE, new Class[0]).newInstance(new Object[0]) : ReflectUtils.getObjectConstructor(ReflectUtils.CLASSNAME_PAGEAGEPARSE, String.class).newInstance(str);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            File file = new File(str);
            Object objInvoke = i2 >= 21 ? objNewInstance.getClass().getMethod("parsePackage", File.class, Integer.TYPE).invoke(objNewInstance, file, Integer.valueOf(i)) : objNewInstance.getClass().getMethod("parsePackage", File.class, String.class, DisplayMetrics.class, Integer.TYPE).invoke(objNewInstance, file, str, displayMetrics, Integer.valueOf(i));
            if (objInvoke == null) {
                Log.d(TAG, "---parsePackage is null------;;sourceFile=" + file.getAbsolutePath());
                return null;
            }
            if (i2 >= 21) {
                Method declaredMethod = objNewInstance.getClass().getDeclaredMethod("collectCertificates", ReflectUtils.classForName(ReflectUtils.CLASSNAME_PAGEAGEPARSE_PACKAGE), File.class, Integer.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(objNewInstance, objInvoke, file, 1);
            } else {
                objNewInstance.getClass().getDeclaredMethod("collectCertificates", ReflectUtils.classForName(ReflectUtils.CLASSNAME_PAGEAGEPARSE_PACKAGE), Integer.TYPE).invoke(objNewInstance, objInvoke, 1);
            }
            return objInvoke;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean startAppDetailSettings(Context context, String str) {
        return SafeCenter.goSafeCenter(context);
    }

    public static boolean startSecuritySettingPage(Context context) {
        return isVivoRom() ? startShortcutSettingsVivo(context) : isEMUIRom() ? startShortcutSettingsEMUI(context, context.getPackageName()) : startAppDetailSettings(context, context.getPackageName());
    }

    public static boolean startShortcutSettingsEMUI(Context context, String str) {
        Intent intent = new Intent();
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", str, null));
        context.startActivity(intent);
        return true;
    }

    public static boolean startShortcutSettingsVivo(Context context) {
        try {
            Intent intent = new Intent("com.bbk.launcher.installshortcutpermission.open");
            intent.setPackage("com.bbk.launcher2");
            if (!(context instanceof Activity)) {
                intent.setFlags(268435456);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void uninstall(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.DELETE", Uri.fromParts("package", str, null));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
