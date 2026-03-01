package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.lzy.okgo.cookie.SerializableCookie;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IApp;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: ReportUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class en {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SparseArray<Long> f3786a = new SparseArray<>();
    public int b = -1;
    public long c = 0;
    public String[] d = {"ol", "cl", "gl", "ha", "bs", "ds"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3787e = -1;
    public long f = -1;
    public static List<bb> i = new ArrayList();
    public static JSONArray j = null;
    public static AMapLocation g = null;
    public static boolean h = false;

    /* JADX INFO: renamed from: com.loc.en$1, reason: invalid class name */
    /* JADX INFO: compiled from: ReportUtil.java */
    public static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3788a;

        static {
            int[] iArr = new int[AMapLocationClientOption.AMapLocationMode.values().length];
            f3788a = iArr;
            try {
                iArr[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3788a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3788a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void a(long j2, long j3) {
        try {
            if (h) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("gpsTime:");
            stringBuffer.append(ep.a(j2, "yyyy-MM-dd HH:mm:ss.SSS"));
            stringBuffer.append(",");
            stringBuffer.append("sysTime:");
            stringBuffer.append(ep.a(j3, "yyyy-MM-dd HH:mm:ss.SSS"));
            stringBuffer.append(",");
            long jU = ei.u();
            String strA = 0 != jU ? ep.a(jU, "yyyy-MM-dd HH:mm:ss.SSS") : "0";
            stringBuffer.append("serverTime:");
            stringBuffer.append(strA);
            a("checkgpstime", stringBuffer.toString());
            if (0 != jU && Math.abs(j2 - jU) < 31536000000L) {
                stringBuffer.append(", correctError");
                a("checkgpstimeerror", stringBuffer.toString());
            }
            stringBuffer.delete(0, stringBuffer.length());
            h = true;
        } catch (Throwable unused) {
        }
    }

    public static synchronized void a(Context context) {
        if (context != null) {
            try {
                if (ei.a()) {
                    if (i != null && i.size() > 0) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(i);
                        bc.b(arrayList, context);
                        i.clear();
                    }
                    f(context);
                }
            } catch (Throwable th) {
                ej.a(th, "ReportUtil", Constants.Event.SLOT_LIFECYCLE.DESTORY);
            }
        }
    }

    public static void a(Context context, int i2, int i3, long j2, long j3) {
        if (i2 == -1 || i3 == -1 || context == null) {
            return;
        }
        try {
            if (ei.a()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("param_int_first", i2);
                jSONObject.put("param_int_second", i3);
                jSONObject.put("param_long_first", j2);
                jSONObject.put("param_long_second", j3);
                a(context, "O012", jSONObject);
            }
        } catch (Throwable th) {
            try {
                ej.a(th, "ReportUtil", "applyStatisticsEx");
            } catch (Throwable th2) {
                ej.a(th2, "ReportUtil", "reportServiceAliveTime");
            }
        }
    }

    public static void a(Context context, long j2, boolean z) {
        if (context != null) {
            try {
                if (ei.a()) {
                    int iIntValue = Long.valueOf(j2).intValue();
                    String str = z ? "domestic" : "abroad";
                    if (context != null) {
                        try {
                            if (ei.a()) {
                                JSONObject jSONObject = new JSONObject();
                                if (!TextUtils.isEmpty(str)) {
                                    jSONObject.put("param_string_first", str);
                                }
                                if (!TextUtils.isEmpty(null)) {
                                    jSONObject.put("param_string_second", (Object) null);
                                }
                                if (iIntValue != Integer.MAX_VALUE) {
                                    jSONObject.put("param_int_first", iIntValue);
                                }
                                a(context, "O015", jSONObject);
                            }
                        } catch (Throwable th) {
                            ej.a(th, "ReportUtil", "applyStatisticsEx");
                        }
                    }
                }
            } catch (Throwable th2) {
                ej.a(th2, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x002e A[Catch: all -> 0x00e7, TRY_LEAVE, TryCatch #1 {all -> 0x00e7, blocks: (B:4:0x0003, B:8:0x000b, B:24:0x002e, B:33:0x003e, B:35:0x0042, B:36:0x0049, B:38:0x0082, B:41:0x008f, B:42:0x00d0, B:44:0x00e2, B:39:0x0088), top: B:56:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(android.content.Context r8, com.amap.api.location.AMapLocation r9) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.en.a(android.content.Context, com.amap.api.location.AMapLocation):void");
    }

    public static void a(Context context, AMapLocation aMapLocation, dm dmVar) {
        boolean z;
        if (aMapLocation == null) {
            return;
        }
        try {
            if ("gps".equalsIgnoreCase(aMapLocation.getProvider())) {
                return;
            }
            int i2 = 1;
            if (aMapLocation.getLocationType() == 1) {
                return;
            }
            if (ep.a(aMapLocation)) {
                z = ej.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) ? false : true;
            } else if ("http://abroad.apilocate.amap.com/mobile/binary".equals(ej.f3783a)) {
            }
            String str = z ? "abroad" : "domestic";
            int errorCode = aMapLocation.getErrorCode();
            String str2 = IApp.ConfigProperty.CONFIG_CACHE;
            if (errorCode != 0) {
                int errorCode2 = aMapLocation.getErrorCode();
                if (errorCode2 == 4 || errorCode2 == 5 || errorCode2 == 6 || errorCode2 == 11) {
                    str2 = "net";
                }
                i2 = 0;
            } else {
                int locationType = aMapLocation.getLocationType();
                if (locationType == 5 || locationType == 6) {
                    str2 = "net";
                }
            }
            int errorCode3 = aMapLocation.getErrorCode();
            if (context != null) {
                try {
                    if (ei.a()) {
                        JSONObject jSONObject = new JSONObject();
                        if (!TextUtils.isEmpty(str2)) {
                            jSONObject.put("param_string_first", str2);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.put("param_string_second", str);
                        }
                        if (i2 != Integer.MAX_VALUE) {
                            jSONObject.put("param_int_first", i2);
                        }
                        if (errorCode3 != Integer.MAX_VALUE) {
                            jSONObject.put("param_int_second", errorCode3);
                        }
                        if (dmVar != null) {
                            if (!TextUtils.isEmpty(dmVar.d())) {
                                jSONObject.put("dns", dmVar.d());
                            }
                            if (!TextUtils.isEmpty(dmVar.e())) {
                                jSONObject.put(SerializableCookie.DOMAIN, dmVar.e());
                            }
                            if (!TextUtils.isEmpty(dmVar.f())) {
                                jSONObject.put("type", dmVar.f());
                            }
                            if (!TextUtils.isEmpty(dmVar.g())) {
                                jSONObject.put("reason", dmVar.g());
                            }
                            if (!TextUtils.isEmpty(dmVar.c())) {
                                jSONObject.put("ip", dmVar.c());
                            }
                            if (!TextUtils.isEmpty(dmVar.b())) {
                                jSONObject.put("stack", dmVar.b());
                            }
                            if (dmVar.h() > 0) {
                                jSONObject.put("ctime", String.valueOf(dmVar.h()));
                            }
                            if (dmVar.a() > 0) {
                                jSONObject.put("ntime", String.valueOf(dmVar.a()));
                            }
                        }
                        a(context, "O016", jSONObject);
                    }
                } catch (Throwable th) {
                    ej.a(th, "ReportUtil", "applyStatisticsEx");
                }
            }
        } catch (Throwable th2) {
            ej.a(th2, "ReportUtil", "reportBatting");
        }
    }

    public static synchronized void a(Context context, String str, JSONObject jSONObject) {
        if (context != null) {
            try {
                if (ei.a()) {
                    bb bbVar = new bb(context, "loc", "5.2.0", str);
                    if (jSONObject != null) {
                        bbVar.a(jSONObject.toString());
                    }
                    i.add(bbVar);
                    if (i.size() >= 30) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(i);
                        bc.b(arrayList, context);
                        i.clear();
                    }
                }
            } catch (Throwable th) {
                ej.a(th, "ReportUtil", "applyStatistics");
            }
        }
    }

    public static void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        try {
            if (g == null) {
                if (!ep.a(aMapLocation)) {
                    g = aMapLocation2;
                    return;
                }
                g = aMapLocation.m1clone();
            }
            if (ep.a(g) && ep.a(aMapLocation2)) {
                AMapLocation aMapLocationM1clone = aMapLocation2.m1clone();
                if (g.getLocationType() != 1 && g.getLocationType() != 9 && !"gps".equalsIgnoreCase(g.getProvider()) && g.getLocationType() != 7 && aMapLocationM1clone.getLocationType() != 1 && aMapLocationM1clone.getLocationType() != 9 && !"gps".equalsIgnoreCase(aMapLocationM1clone.getProvider()) && aMapLocationM1clone.getLocationType() != 7) {
                    long jAbs = Math.abs(aMapLocationM1clone.getTime() - g.getTime()) / 1000;
                    if (jAbs <= 0) {
                        jAbs = 1;
                    }
                    if (jAbs <= 1800) {
                        float fA = ep.a(g, aMapLocationM1clone);
                        float f = fA / jAbs;
                        if (fA > 30000.0f && f > 1000.0f) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(g.getLatitude());
                            sb.append(",");
                            sb.append(g.getLongitude());
                            sb.append(",");
                            sb.append(g.getAccuracy());
                            sb.append(",");
                            sb.append(g.getLocationType());
                            sb.append(",");
                            if (aMapLocation.getTime() != 0) {
                                sb.append(ep.a(g.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                sb.append(g.getTime());
                            }
                            sb.append("#");
                            sb.append(aMapLocationM1clone.getLatitude());
                            sb.append(",");
                            sb.append(aMapLocationM1clone.getLongitude());
                            sb.append(",");
                            sb.append(aMapLocationM1clone.getAccuracy());
                            sb.append(",");
                            sb.append(aMapLocationM1clone.getLocationType());
                            sb.append(",");
                            if (aMapLocationM1clone.getTime() != 0) {
                                sb.append(ep.a(aMapLocationM1clone.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                sb.append(aMapLocationM1clone.getTime());
                            }
                            a("bigshiftstatistics", sb.toString());
                            sb.delete(0, sb.length());
                        }
                    }
                }
                g = aMapLocationM1clone;
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, int i2) {
        String str2;
        String strValueOf = String.valueOf(i2);
        if (i2 == 2011) {
            str2 = "ContextIsNull";
        } else if (i2 == 2031) {
            str2 = "CreateApsReqException";
        } else if (i2 == 2041) {
            str2 = "ResponseResultIsNull";
        } else if (i2 == 2081) {
            str2 = "LocalLocException";
        } else if (i2 == 2091) {
            str2 = "InitException";
        } else if (i2 == 2111) {
            str2 = "ErrorCgiInfo";
        } else if (i2 == 2121) {
            str2 = "NotLocPermission";
        } else if (i2 == 2141) {
            str2 = "NoEnoughStatellites";
        } else if (i2 == 2021) {
            str2 = "OnlyMainWifi";
        } else if (i2 == 2022) {
            str2 = "OnlyOneWifiButNotMain";
        } else if (i2 == 2061) {
            str2 = "ServerRetypeError";
        } else if (i2 == 2062) {
            str2 = "ServerLocFail";
        } else if (i2 == 2151) {
            str2 = "MaybeMockNetLoc";
        } else if (i2 != 2152) {
            switch (i2) {
                case 2051:
                    str2 = "NeedLoginNetWork\t";
                    break;
                case 2052:
                    str2 = "MaybeIntercepted";
                    break;
                case 2053:
                    str2 = "DecryptResponseException";
                    break;
                case 2054:
                    str2 = "ParserDataException";
                    break;
                default:
                    switch (i2) {
                        case 2101:
                            str2 = "BindAPSServiceException";
                            break;
                        case 2102:
                            str2 = "AuthClientScodeFail";
                            break;
                        case 2103:
                            str2 = "NotConfigAPSService";
                            break;
                        default:
                            switch (i2) {
                                case 2131:
                                    str2 = "NoCgiOAndWifiInfo";
                                    break;
                                case 2132:
                                    str2 = "AirPlaneModeAndWifiOff";
                                    break;
                                case 2133:
                                    str2 = "NoCgiAndWifiOff";
                                    break;
                                default:
                                    str2 = "";
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            str2 = "MaybeMockGPSLoc";
        }
        a(str, strValueOf, str2);
    }

    public static void a(String str, String str2) {
        try {
            ab.b(ej.c(), str2, str);
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "reportLog");
        }
    }

    public static void a(String str, String str2, String str3) {
        try {
            ab.a(ej.c(), "/mobile/binary", str3, str, str2);
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, Throwable th) {
        try {
            if (th instanceof j) {
                ab.a(ej.c(), str, (j) th);
            }
        } catch (Throwable unused) {
        }
    }

    public static void f(Context context) {
        try {
            if (j == null || j.length() <= 0) {
                return;
            }
            ba.a(new az(context, ej.c(), j.toString()), context);
            j = null;
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "writeOfflineLocLog");
        }
    }

    public final void a(Context context, int i2) {
        try {
            if (this.b == i2) {
                return;
            }
            if (this.b != -1 && this.b != i2) {
                this.f3786a.append(this.b, Long.valueOf((ep.b() - this.c) + this.f3786a.get(this.b, 0L).longValue()));
            }
            this.c = ep.b() - eo.a(context, "pref1", this.d[i2], 0L);
            this.b = i2;
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "setLocationType");
        }
    }

    public final void a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        try {
            int i2 = AnonymousClass1.f3788a[aMapLocationClientOption.getLocationMode().ordinal()];
            int i3 = 3;
            if (i2 == 1) {
                i3 = 4;
            } else if (i2 == 2) {
                i3 = 5;
            } else if (i2 != 3) {
                i3 = -1;
            }
            if (this.f3787e == i3) {
                return;
            }
            if (this.f3787e != -1 && this.f3787e != i3) {
                this.f3786a.append(this.f3787e, Long.valueOf((ep.b() - this.f) + this.f3786a.get(this.f3787e, 0L).longValue()));
            }
            this.f = ep.b() - eo.a(context, "pref1", this.d[i3], 0L);
            this.f3787e = i3;
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "setLocationMode");
        }
    }

    public final void b(Context context) {
        try {
            long jB = ep.b() - this.c;
            if (this.b != -1) {
                this.f3786a.append(this.b, Long.valueOf(jB + this.f3786a.get(this.b, 0L).longValue()));
            }
            long jB2 = ep.b() - this.f;
            if (this.f3787e != -1) {
                this.f3786a.append(this.f3787e, Long.valueOf(jB2 + this.f3786a.get(this.f3787e, 0L).longValue()));
            }
            SharedPreferences.Editor editorA = eo.a(context, "pref1");
            for (int i2 = 0; i2 < this.d.length; i2++) {
                long jLongValue = this.f3786a.get(i2, 0L).longValue();
                if (jLongValue > 0 && jLongValue > eo.a(context, "pref1", this.d[i2], 0L)) {
                    eo.a(editorA, this.d[i2], jLongValue);
                }
            }
            eo.a(editorA);
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    public final int c(Context context) {
        try {
            long jA = eo.a(context, "pref1", this.d[2], 0L);
            long jA2 = eo.a(context, "pref1", this.d[0], 0L);
            long jA3 = eo.a(context, "pref1", this.d[1], 0L);
            if (jA == 0 && jA2 == 0 && jA3 == 0) {
                return -1;
            }
            long j2 = jA2 - jA;
            long j3 = jA3 - jA;
            return jA > j2 ? jA > j3 ? 2 : 1 : j2 > j3 ? 0 : 1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public final int d(Context context) {
        try {
            long jA = eo.a(context, "pref1", this.d[3], 0L);
            long jA2 = eo.a(context, "pref1", this.d[4], 0L);
            long jA3 = eo.a(context, "pref1", this.d[5], 0L);
            if (jA == 0 && jA2 == 0 && jA3 == 0) {
                return -1;
            }
            return jA > jA2 ? jA > jA3 ? 3 : 5 : jA2 > jA3 ? 4 : 5;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public final void e(Context context) {
        try {
            SharedPreferences.Editor editorA = eo.a(context, "pref1");
            for (int i2 = 0; i2 < this.d.length; i2++) {
                eo.a(editorA, this.d[i2], 0L);
            }
            eo.a(editorA);
        } catch (Throwable unused) {
        }
    }
}
