package com.getui.gtc.a.a;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.base.util.ScheduleQueue;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2103a = -1;
    public static String b = null;
    public static String c = "";

    public static class a implements InvocationHandler {
        public a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            final o oVar;
            if (!"onResult".equalsIgnoreCase(method.getName()) || objArr == null) {
                return null;
            }
            try {
                if (objArr.length <= 0) {
                    return null;
                }
                String str = (String) objArr[0];
                JSONObject jSONObject = new JSONObject(str);
                com.getui.gtc.i.c.a.d("ct prelg result: ".concat(String.valueOf(str)));
                jSONObject.optInt("result", -1);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                if (jSONObjectOptJSONObject != null) {
                    oVar = l.b(jSONObjectOptJSONObject.optString("number"));
                } else {
                    com.getui.gtc.i.c.a.d("ct prelg error.");
                    oVar = new o(-9);
                }
                ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.a.l.a.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        com.getui.gtc.a.f.a(3, oVar);
                    }
                });
                return null;
            } catch (Throwable th) {
                com.getui.gtc.i.c.a.b(th);
                return null;
            }
        }
    }

    public static class b implements InvocationHandler {
        public b() {
        }

        public /* synthetic */ b(byte b) {
            this();
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            final o oVar;
            if (!"onResult".equalsIgnoreCase(method.getName()) || objArr == null) {
                return null;
            }
            try {
                if (objArr.length <= 0) {
                    return null;
                }
                String str = (String) objArr[0];
                JSONObject jSONObject = new JSONObject(str);
                com.getui.gtc.i.c.a.d("cu prelg result: ".concat(String.valueOf(str)));
                int iOptInt = jSONObject.optInt("resultCode", -1);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("resultData");
                if (iOptInt != 0 || jSONObjectOptJSONObject == null) {
                    oVar = new o(-9, l.c, "");
                } else {
                    String strOptString = jSONObjectOptJSONObject.optString("mobile", "");
                    oVar = new o(TextUtils.isEmpty(strOptString) ? -9 : 0, l.c, strOptString);
                }
                ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.a.l.b.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        com.getui.gtc.a.f.a(2, oVar);
                    }
                });
                return null;
            } catch (Throwable th) {
                com.getui.gtc.i.c.a.b(th);
                return null;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int a(Context context) {
        try {
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            com.getui.gtc.i.c.a.d("PM operator ".concat(String.valueOf(simOperator)));
            byte b2 = -1;
            int iHashCode = simOperator.hashCode();
            if (iHashCode != 49679479) {
                if (iHashCode != 49679502) {
                    switch (iHashCode) {
                        case 49679470:
                            if (simOperator.equals("46000")) {
                                b2 = 0;
                            }
                            break;
                        case 49679471:
                            if (simOperator.equals("46001")) {
                                b2 = 4;
                            }
                            break;
                        case 49679472:
                            if (simOperator.equals("46002")) {
                                b2 = 1;
                            }
                            break;
                        case 49679473:
                            if (simOperator.equals("46003")) {
                                b2 = 7;
                            }
                            break;
                        case 49679474:
                            if (simOperator.equals("46004")) {
                                b2 = 2;
                            }
                            break;
                        case 49679475:
                            if (simOperator.equals("46005")) {
                                b2 = 8;
                            }
                            break;
                        case 49679476:
                            if (simOperator.equals("46006")) {
                                b2 = 5;
                            }
                            break;
                        case 49679477:
                            if (simOperator.equals("46007")) {
                                b2 = 3;
                            }
                            break;
                    }
                } else if (simOperator.equals("46011")) {
                    b2 = 9;
                }
            } else if (simOperator.equals("46009")) {
                b2 = 6;
            }
            switch (b2) {
            }
        } catch (Exception e2) {
            com.getui.gtc.i.c.a.b(e2);
            return 4;
        }
        return 4;
    }

    public static boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) GtcProvider.context().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && !"none".equalsIgnoreCase(str)) {
            for (String str2 : str.split(",")) {
                try {
                    Class.forName(str2 + ".UniAccountHelper");
                    b = str2;
                    return true;
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.b(th);
                }
            }
        }
        return false;
    }

    public static int b() {
        try {
            try {
                Class.forName(new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LnNkay4=")) + "CtAuth");
                f2103a = 1;
                return 1;
            } catch (Throwable unused) {
                f2103a = -1;
                return -1;
            }
        } catch (Throwable unused2) {
            Class.forName(new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LmFwaS4=")) + "CtAuth");
            f2103a = 2;
            return 2;
        }
    }

    public static o b(String str) {
        String str2;
        String str3;
        Class<?> cls;
        if (f2103a == -1) {
            return new o(-3);
        }
        try {
            cls = Class.forName(new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LmFwaS4=")) + "ClientUtils");
            str2 = (String) cls.getMethod("getSdkVersion", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            th = th;
            str2 = "";
        }
        try {
            str3 = (String) cls.getMethod("getApiVersion", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            th = th2;
            com.getui.gtc.i.c.a.b(th);
            str3 = "";
        }
        String str4 = str2 + "," + str3;
        String str5 = new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LnNkay5hLmE="));
        int i = -7;
        if (TextUtils.isEmpty(str)) {
            try {
                Class<?> cls2 = Class.forName(str5);
                i = -6;
                str = (String) cls2.getMethod("c", new Class[0]).invoke(cls2.getMethod("a", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            } catch (Throwable th3) {
                com.getui.gtc.i.c.a.b(th3);
            }
        }
        return new o(i, str4, "以本机号码登录".equalsIgnoreCase(str) ? "" : str);
    }

    public static boolean b(Context context) {
        try {
            if (i()) {
                return false;
            }
            String language = Locale.getDefault().getLanguage();
            if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                String country = Locale.getDefault().getCountry();
                if (TextUtils.isEmpty(country) || !country.equals("CN") || CommonUtil.isAppDebugEnable()) {
                    return false;
                }
                return !c(context);
            }
            return false;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return false;
        }
    }

    public static boolean c() {
        String str;
        int iB;
        try {
            str = new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LmFwaS4="));
            iB = b();
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
        if (iB == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("a");
            return !TextUtils.isEmpty((String) Class.forName(sb.toString()).getField("a").get(null));
        }
        if (iB != 2) {
            return false;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("CtAuth");
        return !TextUtils.isEmpty((String) Class.forName(sb2.toString()).getField("mAppId").get(null));
    }

    public static boolean c(Context context) {
        try {
            if (!CommonUtil.isAppForeground()) {
                Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                return intentRegisterReceiver == null || intentRegisterReceiver.getExtras() == null || intentRegisterReceiver.getExtras().getInt("plugged") == 2;
            }
            boolean z = Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
            Intent intentRegisterReceiver2 = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intentRegisterReceiver2 != null && intentRegisterReceiver2.getExtras() != null) {
                return z && intentRegisterReceiver2.getExtras().getInt("plugged") == 2;
            }
            return true;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return true;
        }
    }

    public static boolean d() {
        try {
            if (b == null) {
                return false;
            }
            Class<?> cls = Class.forName(b + ".UniAccountHelper");
            Object objInvoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mContext");
            declaredField.setAccessible(true);
            return ((Context) declaredField.get(objInvoke)) != null;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return false;
        }
    }

    public static o e() {
        String str;
        Throwable th;
        int i;
        try {
            Class<?> cls = Class.forName(b + ".UniAccountHelper");
            i = 0;
            c = (String) cls.getMethod("getSdkVersion", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            str = (String) Class.forName(b + ".f.h").getMethod("c", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            str = "";
            th = th2;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                i = -4;
            }
        } catch (Throwable th3) {
            th = th3;
            i = -5;
            com.getui.gtc.i.c.a.b(th);
        }
        return new o(i, c, str);
    }

    public static void f() {
        Method method;
        try {
            String str = b + ".UniAccountHelper";
            String str2 = b + ".ResultListener";
            com.getui.gtc.i.c.a.d("strUniAccountHelper: " + str + ", strResultListener: " + str2);
            Class<?> cls = Class.forName(str);
            byte b2 = 0;
            Method method2 = cls.getMethod("getInstance", new Class[0]);
            Class<?> cls2 = Class.forName(str2);
            try {
                method = cls.getMethod("preGetToken", Integer.TYPE, cls2);
            } catch (Throwable unused) {
                method = cls.getMethod("login", Integer.TYPE, cls2);
            }
            method.invoke(method2.invoke(null, new Object[0]), 5000, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new b(b2)));
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            com.getui.gtc.a.f.a(2, new o(-9));
        }
    }

    public static void g() {
        try {
            String str = new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LnNkay4="));
            String str2 = new String(c.a("Y24uY29tLmNoaW5hdGVsZWNvbS5hY2NvdW50LmFwaS4="));
            String str3 = str2 + "CtSetting";
            com.getui.gtc.i.c.a.d("prefixOfficial: " + str + ", prefixCustom: " + str2);
            if (f2103a != 1) {
                str = str2;
            }
            Class<?> cls = Class.forName(str + "CtAuth");
            Class<?> cls2 = Class.forName(str3);
            Class<?> cls3 = Class.forName(str + "ResultListener");
            cls.getMethod("requestPreLogin", cls2, cls3).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), null, Proxy.newProxyInstance(cls3.getClassLoader(), new Class[]{cls3}, new a((byte) 0)));
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            com.getui.gtc.a.f.a(3, new o(-9));
        }
    }

    public static boolean i() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0 && ("tun0".equals(networkInterface.getName()) || "ppp0".equals(networkInterface.getName()))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return false;
        }
    }
}
