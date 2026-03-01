package io.dcloud.common.constant;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.idtracking.g;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.CreateShortResultReceiver;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TelephonyUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class DataInterface {
    public static String getAppListUrl(String str) {
        return StringConst.STREAMAPP_KEY_BASESERVICEURL() + AbsoluteConst.STREAMAPP_KEY_APPLISTURL + getTestParam(str);
    }

    public static String getBaseUrl() {
        return StringConst.STREAMAPP_KEY_BASESERVICEURL();
    }

    public static String getDatailUrl(String str) {
        return StringConst.STREAMAPP_KEY_BASESERVICEURL() + AbsoluteConst.STREAMAPP_KEY_DETAIL + str + getTestParam(str);
    }

    public static String getDatailsUrl(String str, ArrayList<String> arrayList, String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                stringBuffer.append(URLEncoder.encode(arrayList.get(i), "utf-8"));
                if (arrayList.size() > 1 && i <= arrayList.size() - 1) {
                    stringBuffer.append(',');
                }
            } catch (Exception unused) {
                return null;
            }
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            stringBuffer2.append(URLEncoder.encode(strArr[i2], "utf-8"));
            if (strArr.length > 1 && i2 <= strArr.length - 1) {
                stringBuffer2.append(',');
            }
        }
        return StringConst.STREAMAPP_KEY_BASESERVICEURL() + AbsoluteConst.STREAMAPP_KEY_DETAILS + BaseInfo.sChannel + "&sign=" + str + "&appids=" + stringBuffer.toString() + "&columns=" + stringBuffer2.toString();
    }

    public static String getJsonUrl(String str, String str2, String str3, Context context, String str4) {
        return str + "resource/stream/v2?" + getUrlBaseData(context, str2, str3, str4);
    }

    public static String getRomVersion() {
        String str;
        if (!Build.MANUFACTURER.equals(MobilePhoneModel.XIAOMI)) {
            return Build.VERSION.INCREMENTAL;
        }
        String buildValue = DeviceInfo.getBuildValue("ro.miui.ui.version.name");
        StringBuilder sb = new StringBuilder();
        sb.append(Build.VERSION.INCREMENTAL);
        if (TextUtils.isEmpty(buildValue)) {
            str = "";
        } else {
            str = "-" + buildValue;
        }
        sb.append(str);
        return sb.toString();
    }

    public static String getSplashUrl(String str, String str2, String str3) {
        return StringConst.STREAMAPP_KEY_BASESERVICEURL() + AbsoluteConst.STREAMAPP_KEY_SPLASHBASEURL + str + "&width=" + str2 + "&height=" + str3 + getTestParam(str);
    }

    public static HashMap getStartupUrlBaseData(Context context, String str, String str2, String str3) {
        return getStartupUrlBaseData(context, str, str2, str3, true);
    }

    public static String getStatisticsUrl(String str, String str2, int i, String str3, String str4) {
        return StringUtil.format(AbsoluteConst.STREAMAPP_KEY_STATISTICURL, str, str2, Integer.valueOf(i), str3, str4, Integer.valueOf(Build.VERSION.SDK_INT), PdrUtil.encodeURL(Build.MANUFACTURER)) + "&p=a" + getTestParam(str) + "&mc=" + BaseInfo.sChannel;
    }

    public static String getStreamappFrom(Intent intent) {
        if (intent == null || !intent.hasExtra(IntentConst.RUNING_STREAPP_LAUNCHER)) {
            return null;
        }
        String stringExtra = intent.getStringExtra(IntentConst.RUNING_STREAPP_LAUNCHER);
        if (stringExtra.indexOf("third:") == 0) {
            return stringExtra.substring(6, stringExtra.length());
        }
        return null;
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0085: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:24:0x0085 */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getSystemProperty() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Exception while closing InputStream"
            java.lang.String r1 = "ro.miui.ui.version.name"
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r4.<init>()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r5 = "getprop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r4.append(r1)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.Process r3 = r3.exec(r4)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.io.InputStream r3 = r3.getInputStream()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r3 = 1024(0x400, float:1.435E-42)
            r4.<init>(r5, r3)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r3 = r4.readLine()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L84
            r4.close()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L84
            r4.close()     // Catch: java.io.IOException -> L39
            goto L4c
        L39:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            io.dcloud.common.adapter.util.Logger.i(r0)
        L4c:
            return r3
        L4d:
            r3 = move-exception
            goto L53
        L4f:
            r1 = move-exception
            goto L86
        L51:
            r3 = move-exception
            r4 = r2
        L53:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84
            r5.<init>()     // Catch: java.lang.Throwable -> L84
            java.lang.String r6 = "Unable to read sysprop "
            r5.append(r6)     // Catch: java.lang.Throwable -> L84
            r5.append(r1)     // Catch: java.lang.Throwable -> L84
            r5.append(r3)     // Catch: java.lang.Throwable -> L84
            java.lang.String r1 = r5.toString()     // Catch: java.lang.Throwable -> L84
            io.dcloud.common.adapter.util.Logger.i(r1)     // Catch: java.lang.Throwable -> L84
            if (r4 == 0) goto L83
            r4.close()     // Catch: java.io.IOException -> L70
            goto L83
        L70:
            r1 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            io.dcloud.common.adapter.util.Logger.i(r0)
        L83:
            return r2
        L84:
            r1 = move-exception
            r2 = r4
        L86:
            if (r2 == 0) goto L9f
            r2.close()     // Catch: java.io.IOException -> L8c
            goto L9f
        L8c:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r2)
            java.lang.String r0 = r3.toString()
            io.dcloud.common.adapter.util.Logger.i(r0)
        L9f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.constant.DataInterface.getSystemProperty():java.lang.String");
    }

    public static String getTestParam(String str) {
        return "&__am=" + ((TextUtils.isEmpty(str) || !BaseInfo.isTest(str)) ? "r" : "t");
    }

    public static String getUrlBaseData(Context context, String str, String str2, String str3) {
        String strEncode;
        String imei = TelephonyUtil.getIMEI(context);
        int networkType = NetworkTypeUtil.getNetworkType(context);
        try {
            strEncode = URLEncoder.encode(Build.MODEL, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            strEncode = null;
        }
        int i = Build.VERSION.SDK_INT;
        String str4 = DeviceInfo.sPackageName;
        if (str4 == null) {
            str4 = AndroidResources.packageName;
        }
        return StringUtil.format(StringConst.T_URL_BASE_DATA, str, imei, Integer.valueOf(networkType), strEncode, Integer.valueOf(i), "1.9.9.81096", Integer.valueOf(StringConst.getIntSF(str2)), Long.valueOf(System.currentTimeMillis()), str3, PdrUtil.encodeURL(Build.MANUFACTURER), str4) + getTestParam(str) + "&mc=" + BaseInfo.sChannel;
    }

    public static HashMap getStartupUrlBaseData(Context context, String str, String str2, String str3, boolean z) {
        String bundleData = SP.getBundleData(context, "pdr", SP.STARTUP_DEVICE_ID);
        if (PdrUtil.isEmpty(bundleData)) {
            bundleData = z ? TelephonyUtil.getSBBS(context, true, true) : TelephonyUtil.getSBBS(context, true, true, false);
        }
        int networkType = NetworkTypeUtil.getNetworkType(context);
        String str4 = Build.MODEL;
        int i = Build.VERSION.SDK_INT;
        String str5 = DeviceInfo.sPackageName;
        if (str5 == null) {
            str5 = AndroidResources.packageName;
        }
        HashMap map = new HashMap();
        map.put("appid", str);
        map.put(g.f5442a, bundleData);
        map.put("net", Integer.valueOf(networkType));
        map.put("md", str4);
        map.put("os", Integer.valueOf(i));
        map.put("vb", "1.9.9.81096");
        map.put(CreateShortResultReceiver.KEY_SF, Integer.valueOf(StringConst.getIntSF(str2)));
        map.put("p", "a");
        map.put("d1", Long.valueOf(System.currentTimeMillis()));
        map.put(CreateShortResultReceiver.KEY_SFD, str3);
        map.put("vd", Build.MANUFACTURER);
        map.put("pn", str5);
        return map;
    }
}
