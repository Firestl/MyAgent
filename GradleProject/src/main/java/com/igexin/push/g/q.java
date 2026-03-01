package com.igexin.push.g;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.getui.gtc.base.util.CommonUtil;
import com.igexin.push.core.b.y;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3605a = "Task145PhoneDataUtils";

    public static int a() {
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            if ("wlan0".equalsIgnoreCase(networkInterfaceNextElement.getName())) {
                for (InterfaceAddress interfaceAddress : networkInterfaceNextElement.getInterfaceAddresses()) {
                    InetAddress address = interfaceAddress.getAddress();
                    short networkPrefixLength = interfaceAddress.getNetworkPrefixLength();
                    if (!address.isLoopbackAddress() && (address instanceof Inet4Address)) {
                        com.igexin.c.a.c.a.b(f3605a, "IPv4 maskLength: ".concat(String.valueOf((int) networkPrefixLength)));
                        if (networkPrefixLength > 0) {
                            return networkPrefixLength;
                        }
                        return 24;
                    }
                    return 24;
                }
            }
        }
        return 24;
    }

    public static String a(int i) {
        return (i & 255) + Operators.DOT_STR + ((i >> 8) & 255) + Operators.DOT_STR + ((i >> 16) & 255) + Operators.DOT_STR + ((i >> 24) & 255);
    }

    public static String a(Context context) {
        String str;
        String str2 = "";
        try {
            str = c(context).b;
        } catch (Throwable th) {
            th = th;
        }
        try {
            com.igexin.c.a.c.a.b(f3605a, "new get self iv4 by dhcp, ip = ".concat(String.valueOf(str)));
            if (!TextUtils.isEmpty(str) && !"0.0.0.0".equalsIgnoreCase(str)) {
                return str;
            }
            for (String str3 : b().split("#")) {
                if (str3.contains("wlan0/ipv4")) {
                    String strReplace = str3.replace("wlan0/ipv4=", "");
                    if (strReplace.contains("/")) {
                        str = strReplace.split("/")[0];
                    }
                    str2 = str;
                    com.igexin.c.a.c.a.b(f3605a, "new get self iv4 by sl, ip = ".concat(String.valueOf(str2)));
                }
            }
            return str;
        } catch (Throwable th2) {
            th = th2;
            str2 = str;
            com.igexin.c.a.c.a.a(th);
        }
        return str2;
    }

    public static com.igexin.push.core.b.d b(Context context) {
        try {
            if (CommonUtil.hasPermission(context, Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29 ? "android.permission.ACCESS_FINE_LOCATION" : "android.permission.ACCESS_COARSE_LOCATION", false) && d(context)) {
                return e(context);
            }
        } catch (Throwable unused) {
        }
        return new com.igexin.push.core.b.d();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|(5:75|3|83|4|5)|(9:81|6|7|(3:9|(2:11|(2:13|91)(1:93))(1:92)|14)(1:90)|74|54|(1:56)|57|58)|15|(5:17|(1:21)|22|(2:24|(2:26|96)(1:95))(2:28|94)|29)|88|30|(2:35|74)|54|(0)|57|58|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x010b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x010c, code lost:
    
        com.igexin.c.a.c.a.a(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b() {
        /*
            Method dump skipped, instruction units count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.g.q.b():java.lang.String");
    }

    public static y c(Context context) {
        com.igexin.c.a.c.a.b(f3605a, "SLMA getDhcpWifiInfo.");
        y yVar = new y();
        try {
            CommonUtil.hasPermission(context, "android.permission.ACCESS_WIFI_STATE", false);
            DhcpInfo dhcpInfo = ((WifiManager) context.getSystemService("wifi")).getDhcpInfo();
            yVar.f3375a = a(dhcpInfo.gateway);
            yVar.b = a(dhcpInfo.ipAddress);
            int i = dhcpInfo.netmask;
            yVar.c = a();
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return yVar;
    }

    public static boolean d(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimState() == 5;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return false;
        }
    }

    public static com.igexin.push.core.b.d e(Context context) {
        String str;
        String str2 = "android.telephony.CellInfoNr";
        if (Build.VERSION.SDK_INT < 17) {
            return new com.igexin.push.core.b.d();
        }
        long cid = 0;
        List<CellInfo> allCellInfo = ((TelephonyManager) context.getSystemService("phone")).getAllCellInfo();
        if (allCellInfo == null || allCellInfo.isEmpty()) {
            return new com.igexin.push.core.b.d();
        }
        int mcc = 0;
        int mnc = 0;
        int lac = 0;
        int i = 0;
        for (CellInfo cellInfo : allCellInfo) {
            if (cellInfo.isRegistered()) {
                if (cellInfo instanceof CellInfoGsm) {
                    CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                    mcc = cellIdentity.getMcc();
                    mnc = cellIdentity.getMnc();
                    lac = cellIdentity.getLac();
                    cid = cellIdentity.getCid();
                    i = 1;
                } else if (cellInfo instanceof CellInfoCdma) {
                    CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
                    mnc = cellIdentity2.getSystemId();
                    lac = cellIdentity2.getNetworkId();
                    cid = cellIdentity2.getBasestationId();
                    i = 2;
                } else if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity3 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    mcc = cellIdentity3.getMcc();
                    mnc = cellIdentity3.getMnc();
                    lac = cellIdentity3.getLac();
                    cid = cellIdentity3.getCid();
                    i = 4;
                } else if (cellInfo instanceof CellInfoLte) {
                    CellIdentityLte cellIdentity4 = ((CellInfoLte) cellInfo).getCellIdentity();
                    mcc = cellIdentity4.getMcc();
                    mnc = cellIdentity4.getMnc();
                    lac = cellIdentity4.getTac();
                    cid = cellIdentity4.getCi();
                    i = 3;
                } else {
                    if (str2.equals(cellInfo.getClass().getName())) {
                        try {
                            Method method = Class.forName(str2).getMethod("getCellIdentity", new Class[0]);
                            Class<?> cls = Class.forName("android.telephony.CellIdentityNr");
                            Method method2 = cls.getMethod("getMccString", new Class[0]);
                            Method method3 = cls.getMethod("getMncString", new Class[0]);
                            Method method4 = cls.getMethod("getTac", new Class[0]);
                            str = str2;
                            try {
                                Method method5 = cls.getMethod("getNci", new Class[0]);
                                Object objInvoke = method.invoke(cellInfo, new Object[0]);
                                String str3 = (String) method2.invoke(objInvoke, new Object[0]);
                                String str4 = (String) method3.invoke(objInvoke, new Object[0]);
                                mcc = Integer.parseInt(str3);
                                mnc = Integer.parseInt(str4);
                                lac = ((Integer) method4.invoke(objInvoke, new Object[0])).intValue();
                                cid = ((Long) method5.invoke(objInvoke, new Object[0])).longValue();
                                i = 6;
                            } catch (Throwable th) {
                                th = th;
                                com.igexin.c.a.c.a.a(th);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            str = str2;
                        }
                    } else {
                        str = str2;
                    }
                    str2 = str;
                }
            }
        }
        com.igexin.push.core.b.d dVar = new com.igexin.push.core.b.d();
        dVar.f3343a = mcc;
        dVar.b = mnc;
        dVar.c = lac;
        dVar.d = cid;
        dVar.f3344e = i;
        return dVar;
    }

    public static boolean f(Context context) {
        return CommonUtil.hasPermission(context, Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29 ? "android.permission.ACCESS_FINE_LOCATION" : "android.permission.ACCESS_COARSE_LOCATION", false);
    }
}
