package io.dcloud.common.adapter.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.pro.as;
import io.dcloud.common.util.BuildProperties;
import io.dcloud.common.util.LauncherUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class MobilePhoneModel {
    public static String COOLPAD = "Coolpad";
    public static final ArrayList<String> CREATE_NO_TOAST;
    public static final ArrayList<String> CREATE_TOAST;
    public static String DUOWEI = "DOOV";
    public static String GIONEE = "GIONEE";
    public static String GOOGLE = "google";
    public static String HONOR = "honor";
    public static String HTC = "htc";
    public static String HUAWEI = "Huawei";
    public static String LEMOBILE = "LeMobile";
    public static String LENOVO = "Lenovo";
    public static String LETV = "Letv";
    public static String MEIZU = "Meizu";
    public static String MOTO = "motorola";
    public static final ArrayList<String> NO_CREATE;
    public static String ONEPLUS = "OnePlus";
    public static String OPPO = "OPPO";
    public static String QIHU360 = "360";
    public static String QiKU = "QiKU";
    public static String SAMSUNG = "samsung";
    public static String SMARTISAN = "SMARTISAN";
    public static String SONY = "Sony";
    public static String VIVO = "vivo";
    public static String XIAOMI = "Xiaomi";
    public static String YULONG = "YuLong";
    public static String ZTE = "ZTE";

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        NO_CREATE = arrayList;
        arrayList.add("com.android.launcher3");
        arrayList.add("com.zte.mifavor.launcher");
        ArrayList<String> arrayList2 = new ArrayList<>();
        CREATE_TOAST = arrayList2;
        arrayList2.add("com.sec.android.app.twlauncher");
        arrayList2.add("com.oppo.launcher");
        arrayList2.add("com.tencent.qlauncher");
        arrayList2.add("com.sec.android.app.launcher");
        arrayList2.add("com.huawei.android.launcher");
        ArrayList<String> arrayList3 = new ArrayList<>();
        CREATE_NO_TOAST = arrayList3;
        arrayList3.add("com.android.launcher3");
        arrayList3.add("com.android.launcher");
        arrayList3.add("com.lenovo.launcher ");
        arrayList3.add("com.cyanogenmod.trebuchet");
        arrayList3.add("com.miui.home");
        arrayList3.add("com.htc.launcher");
    }

    public static boolean checkDeviceHtml5Geo() {
        String str = Build.BRAND;
        if (str.equals(GOOGLE)) {
            str = Build.MANUFACTURER;
        }
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.RELEASE;
        String deviceRomVersionName = getDeviceRomVersionName();
        if (QIHU360.equalsIgnoreCase(str)) {
            return ("1505-A01".equalsIgnoreCase(str2) && "6.0".equalsIgnoreCase(str3)) ? false : true;
        }
        if (VIVO.equalsIgnoreCase(str)) {
            return ("vivo Xplay5A".equalsIgnoreCase(str2) && "5.1.1".equalsIgnoreCase(str3) && "Funtouch OS_2.5.1".equalsIgnoreCase(deviceRomVersionName)) ? false : true;
        }
        if (GOOGLE.equalsIgnoreCase(str)) {
            return ("Nexus 5".equalsIgnoreCase(str2) && "7.0".equalsIgnoreCase(str3)) ? false : true;
        }
        if ("Android".equalsIgnoreCase(str)) {
            return ("Nexus 5".equalsIgnoreCase(str2) && "7.0".equalsIgnoreCase(str3)) ? false : true;
        }
        if (GIONEE.equalsIgnoreCase(str)) {
            if ("GiONEE-W800/W800".equalsIgnoreCase(str2) && "4.2.1".equalsIgnoreCase(str3) && "W800_T10".equalsIgnoreCase(deviceRomVersionName)) {
                return false;
            }
            if ("GN137".equalsIgnoreCase(str2) && "4.2.2".equalsIgnoreCase(str3) && "Amigo1.5.1".equalsIgnoreCase(deviceRomVersionName)) {
                return false;
            }
            if ("GN9000".equalsIgnoreCase(str2) && "4.4.2".equalsIgnoreCase(str3) && "amigo2.7.4".equalsIgnoreCase(deviceRomVersionName)) {
                return false;
            }
            if ("F100".equalsIgnoreCase(str2) && "5.1".equalsIgnoreCase(str3)) {
                return ("amigo3.1.2".equalsIgnoreCase(deviceRomVersionName) || "amigo3.1.7".equalsIgnoreCase(deviceRomVersionName)) ? false : true;
            }
            return true;
        }
        if (LETV.equalsIgnoreCase(str) || LEMOBILE.equalsIgnoreCase(str)) {
            return ("Letv X500".equalsIgnoreCase(str2) && "6.0".equalsIgnoreCase(str3)) ? false : true;
        }
        if (!LENOVO.equalsIgnoreCase(str)) {
            if (MEIZU.equalsIgnoreCase(str)) {
                if ("PRO 5".equalsIgnoreCase(str2) && "5.1".equalsIgnoreCase(str3) && "Flyme OS 4.5.4.1A".equalsIgnoreCase(deviceRomVersionName)) {
                    return false;
                }
                if ("m2".equalsIgnoreCase(str2) && "5.1".equalsIgnoreCase(str3) && "Flyme OS 4.5.4.2U".equalsIgnoreCase(deviceRomVersionName)) {
                    return false;
                }
                if ("m1".equalsIgnoreCase(str2) && "4.4.4".equalsIgnoreCase(str3)) {
                    return false;
                }
                if ("M351".equalsIgnoreCase(str2) && "4.2.1".equalsIgnoreCase(str3)) {
                    return false;
                }
                return ("M355".equalsIgnoreCase(str2) && "4.2.1".equalsIgnoreCase(str3)) ? false : true;
            }
            if (MOTO.equalsIgnoreCase(str)) {
                if ("Nexus 6".equalsIgnoreCase(str2) && "6.0".equalsIgnoreCase(str3)) {
                    return false;
                }
                return ("XT1058".equalsIgnoreCase(str2) && "4.2.2".equalsIgnoreCase(str3)) ? false : true;
            }
            if (SAMSUNG.equalsIgnoreCase(str)) {
                return ("GT-I9228".equalsIgnoreCase(str2) && "4.1.2".equalsIgnoreCase(str3)) ? false : true;
            }
            if (SONY.equalsIgnoreCase(str)) {
                return ("E6883".equalsIgnoreCase(str2) && "5.1.1".equalsIgnoreCase(str3)) ? false : true;
            }
            if (!ONEPLUS.equalsIgnoreCase(str)) {
                return (ZTE.equalsIgnoreCase(str) && "ZTE U809".equalsIgnoreCase(str2) && "4.2.2".equalsIgnoreCase(str3)) ? false : true;
            }
            if ("ONE E1001".equalsIgnoreCase(str2) && "5.1.1".equalsIgnoreCase(str3)) {
                return false;
            }
            return ("ONE A2001".equalsIgnoreCase(str2) && "5.1.1".equalsIgnoreCase(str3)) ? false : true;
        }
        if ("Lenovo S820".equalsIgnoreCase(str2) && "4.4.2".equalsIgnoreCase(str3) && "VIBEUI_V2.0_1512_7.149.1_ST_S820".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo A770e".equalsIgnoreCase(str2) && "4.1.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo A820t".equalsIgnoreCase(str2) && "4.1.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo K900".equalsIgnoreCase(str2) && "4.2.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo S810t".equalsIgnoreCase(str2) && "4.3".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo A916".equalsIgnoreCase(str2) && "4.4.2".equalsIgnoreCase(str3) && "A916_S207_150205".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo S856".equalsIgnoreCase(str2) && "4.4.2".equalsIgnoreCase(str3) && "VIBEUI_V1.5_1428_2_ST_S856_CU".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo P70-t".equalsIgnoreCase(str2) && "4.4.4".equalsIgnoreCase(str3) && "P70-t_S132_160401".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo K30-T".equalsIgnoreCase(str2) && "4.4.4".equalsIgnoreCase(str3) && "K30-T_S040_150126".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo K80M".equalsIgnoreCase(str2) && "4.4.4".equalsIgnoreCase(str3) && "VIBEUI_V2.0_1512_1.129.1_ST_K80M".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo K910".equalsIgnoreCase(str2) && "4.2.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo A3580".equalsIgnoreCase(str2) && "5.0.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo Z90-3".equalsIgnoreCase(str2) && "5.0.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo Z90-7".equalsIgnoreCase(str2) && "5.0.2".equalsIgnoreCase(str3)) {
            return false;
        }
        if ("Lenovo X3c70".equalsIgnoreCase(str2) && "5.1.1".equalsIgnoreCase(str3) && "VIBEUI_V2.9_1546_5.334.1_ST_X3c70".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo X3c50".equalsIgnoreCase(str2) && "5.1.1".equalsIgnoreCase(str3) && "VIBEUI_V2.9_1546_5.233.1_ST_X3c50".equalsIgnoreCase(deviceRomVersionName)) {
            return false;
        }
        if ("Lenovo PB2-690N".equalsIgnoreCase(str2) && "6.0".equalsIgnoreCase(str3)) {
            return false;
        }
        return ("Lenovo K50-t5".equalsIgnoreCase(str2) && "6.0".equalsIgnoreCase(str3)) ? false : true;
    }

    public static boolean checkPhoneBanAcceleration(String str) {
        if (str == null || Build.VERSION.SDK_INT == 21) {
        }
        return true;
    }

    public static String getDeviceRomVersionName() {
        try {
            BuildProperties buildProperties = BuildProperties.getInstance();
            if (buildProperties == null) {
                return "";
            }
            if (buildProperties.containsKey(as.h)) {
                return buildProperties.getProperty("ro.vivo.os.build.display.id");
            }
            if (buildProperties.containsKey("ro.gn.extvernumber")) {
                return buildProperties.getProperty("ro.build.display.id");
            }
            if (buildProperties.containsKey("ro.miui.ui.version.name")) {
                return buildProperties.getProperty("ro.miui.ui.version.name");
            }
            if (buildProperties.containsKey("ro.lenovo.platform")) {
                return buildProperties.getProperty("ro.build.version.incremental");
            }
            if (!buildProperties.containsKey("ro.sony.fota.encrypteddata") && !buildProperties.containsKey("sony.effect.custom.caplus_hs") && !buildProperties.containsKey("sony.effect.custom.caplus_sp")) {
                buildProperties.containsKey("sony.effect.custom.sp_bundle");
            }
            String property = buildProperties.getProperty("ro.build.display.id");
            return (TextUtils.isEmpty(property) || !property.toLowerCase().startsWith("flyme")) ? "" : property;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean hasToast(String str) {
        System.err.println(Build.MODEL + ";launcherName=" + str);
        return true;
    }

    public static boolean isAppointPhone(String str) {
        String str2 = Build.BRAND;
        if (str2.equalsIgnoreCase(GOOGLE)) {
            str2 = Build.MANUFACTURER;
        }
        return str2.equalsIgnoreCase(str);
    }

    public static boolean isDLGeoPhone() {
        String str = Build.BRAND;
        if (str.equals(GOOGLE)) {
            str = Build.MANUFACTURER;
        }
        return (str.equalsIgnoreCase(HUAWEI) || str.equalsIgnoreCase(OPPO) || str.equalsIgnoreCase(GIONEE) || str.equalsIgnoreCase(XIAOMI) || str.equalsIgnoreCase(QiKU) || str.equalsIgnoreCase(VIVO) || str.equalsIgnoreCase(MEIZU) || str.equalsIgnoreCase(ZTE) || str.equalsIgnoreCase(COOLPAD) || str.equalsIgnoreCase(ONEPLUS)) ? false : true;
    }

    public static boolean isSmartisanLauncherPhone(Context context) {
        String launcherPackageName = LauncherUtil.getLauncherPackageName(context);
        String str = Build.BRAND;
        if (str.equalsIgnoreCase(GOOGLE)) {
            str = Build.MANUFACTURER;
        }
        return SMARTISAN.equalsIgnoreCase(str) && launcherPackageName.contains("com.smartisanos.launcher");
    }

    public static boolean isSpecialPhone(Context context) {
        String str = Build.BRAND;
        if (str.equals(GOOGLE)) {
            str = Build.MANUFACTURER;
        }
        return QiKU.equalsIgnoreCase(str) || VIVO.equalsIgnoreCase(str) || isSmartisanLauncherPhone(context);
    }

    public static boolean isYunOSRom() {
        try {
            BuildProperties buildProperties = BuildProperties.getInstance();
            if (buildProperties != null) {
                return buildProperties.containsKey("ro.yunos.version");
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean needToast(String str) {
        return CREATE_NO_TOAST.contains(str);
    }
}
