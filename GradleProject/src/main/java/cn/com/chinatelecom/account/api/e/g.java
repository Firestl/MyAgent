package cn.com.chinatelecom.account.api.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import com.baidu.aip.face.stat.NetUtil;
import com.igexin.assist.sdk.AssistPushConsts;
import com.tencent.connect.common.Constants;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1519a = null;
    public static String b = null;
    public static String c = null;
    public static String d = "0";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f1520e = {"46000", "46002", "46004", "46007", "46008"};
    public static final String[] f = {"46003", "46005", "46011"};
    public static final String[] g = {"46001", "46006", "46009"};

    public static int a(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return 2;
                    case 13:
                    case 18:
                    case 19:
                        return 3;
                    case 20:
                        return 4;
                    default:
                        return i;
                }
            }
        }
        return i2;
    }

    public static NetworkInfo a(Context context) {
        if (context == null) {
            return null;
        }
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static String a() {
        return f1519a;
    }

    public static String a(Context context, boolean z) {
        String strH = h(context);
        if (strH != null) {
            for (String str : f) {
                if (strH.equals(str)) {
                    return z ? "1" : AssistPushConsts.MSG_KEY_CONTENT;
                }
            }
            for (String str2 : f1520e) {
                if (strH.equals(str2)) {
                    return z ? "2" : "CM";
                }
            }
            for (String str3 : g) {
                if (strH.equals(str3)) {
                    return z ? "3" : "CU";
                }
            }
        }
        return z ? "0" : "UN";
    }

    public static String b() {
        return b != null ? "https://open.e.189.cn/openapi/special/getTimeStamp.do".replace(cn.com.chinatelecom.account.api.a.d.a(b.g), b) : "https://open.e.189.cn/openapi/special/getTimeStamp.do";
    }

    public static boolean b(Context context) {
        NetworkInfo networkInfoA = a(context);
        return networkInfoA != null && networkInfoA.isAvailable();
    }

    public static String c() {
        String str = c;
        return str != null ? "https://api-e189.21cn.com/gw/client/accountMsg.do".replace("e189.21cn.com", str) : "https://api-e189.21cn.com/gw/client/accountMsg.do";
    }

    public static boolean c(Context context) {
        NetworkInfo networkInfoA = a(context);
        return networkInfoA != null && networkInfoA.getType() == 0;
    }

    public static boolean d(Context context) {
        if (context == null) {
            return true;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Throwable th) {
            CtAuth.warn(NetUtil.TAG, "isMobileEnable error ", th);
            return true;
        }
    }

    public static String e(Context context) {
        int iJ = j(context);
        return iJ != -101 ? (iJ == -1 || iJ == 0) ? com.igexin.push.core.b.m : iJ != 1 ? iJ != 2 ? iJ != 3 ? iJ != 4 ? Integer.toString(iJ) : "5G" : "4G" : "3G" : "2G" : "WIFI";
    }

    public static String f(Context context) {
        String strE = e(context);
        return (strE != null && strE.equals("WIFI") && d(context)) ? "BOTH" : strE;
    }

    public static String g(Context context) {
        String strF = f(context);
        if (!TextUtils.isEmpty(strF) && !strF.equals(com.igexin.push.core.b.m)) {
            if (strF.equals("2G")) {
                return Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            }
            if (strF.equals("3G")) {
                return Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
            }
            if (strF.equals("4G")) {
                return Constants.VIA_REPORT_TYPE_SET_AVATAR;
            }
            if (strF.equals("5G")) {
                return Constants.VIA_REPORT_TYPE_START_WAP;
            }
            if (strF.equals("WIFI")) {
                return "13";
            }
            if (strF.equals("BOTH")) {
                return Constants.VIA_REPORT_TYPE_MAKE_FRIEND;
            }
        }
        return Constants.VIA_REPORT_TYPE_WPA_STATE;
    }

    public static String h(Context context) {
        try {
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            return !TextUtils.isEmpty(simOperator) ? simOperator : "00000";
        } catch (Throwable th) {
            th.printStackTrace();
            return "00000";
        }
    }

    public static String i(Context context) {
        return a(context, true);
    }

    public static int j(Context context) {
        int subtype = 0;
        try {
            NetworkInfo networkInfoA = a(context);
            if (networkInfoA != null && networkInfoA.isAvailable() && networkInfoA.isConnected()) {
                int type = networkInfoA.getType();
                if (type == 1) {
                    subtype = -101;
                } else if (type == 0) {
                    try {
                        subtype = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (subtype == 0) {
                        subtype = networkInfoA.getSubtype();
                    }
                }
            } else {
                subtype = -1;
            }
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return a(subtype);
    }
}
