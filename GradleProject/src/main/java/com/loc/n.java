package com.loc;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.WindowManager;
import com.ta.utdid2.device.UTUtdid;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: DeviceInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n {
    public static String A = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3825a = "";
    public static String b = "";
    public static volatile boolean c = true;
    public static boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f3826e = "";
    public static boolean f = false;
    public static a g = null;
    public static int h = -1;
    public static String i = "";
    public static String j = "";
    public static String k = null;
    public static boolean l = false;
    public static volatile boolean m = false;
    public static String n = "";
    public static boolean o = false;
    public static String p = "";
    public static String q = "";
    public static String r = "";
    public static String s = "";
    public static String t = "";
    public static String u = "";
    public static boolean v = false;
    public static String w = "";
    public static long x;
    public static int y;
    public static String z;

    /* JADX INFO: compiled from: DeviceInfo.java */
    public interface a {
        av a();

        String b();

        String c();

        String d();
    }

    /* JADX INFO: compiled from: DeviceInfo.java */
    public static class b implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(com.umeng.analytics.pro.bc.f5194a);
                iBinder.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                String unused = n.n = parcelObtain2.readString();
            } finally {
                try {
                } finally {
                }
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public static String A(Context context) {
        try {
            return H(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int B(Context context) {
        BufferedReader bufferedReader;
        int i2 = y;
        if (i2 != 0) {
            return i2;
        }
        int iIntValue = 0;
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return 0;
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            iIntValue = (int) (memoryInfo.totalMem / 1024);
        } else {
            BufferedReader bufferedReader2 = null;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(new File("/proc/meminfo")));
                } catch (Throwable unused) {
                }
                try {
                    iIntValue = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue();
                    bufferedReader.close();
                } catch (Throwable unused2) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    int i3 = iIntValue / 1024;
                    y = i3;
                    return i3;
                }
            } catch (IOException unused3) {
            }
        }
        int i32 = iIntValue / 1024;
        y = i32;
        return i32;
    }

    public static String C(Context context) {
        try {
            return I(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String E(Context context) {
        FileInputStream fileInputStream = null;
        try {
            if (u.a(context, "android.permission.READ_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    xmlPullParserNewPullParser.setInput(fileInputStream2, "utf-8");
                    boolean z2 = false;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); 1 != eventType; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 2) {
                            if (eventType == 3) {
                                z2 = false;
                            } else if (eventType == 4 && z2) {
                                String text = xmlPullParserNewPullParser.getText();
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable unused) {
                                }
                                return text;
                            }
                        } else if (xmlPullParserNewPullParser.getAttributeCount() > 0) {
                            int attributeCount = xmlPullParserNewPullParser.getAttributeCount();
                            for (int i2 = 0; i2 < attributeCount; i2++) {
                                String attributeValue = xmlPullParserNewPullParser.getAttributeValue(i2);
                                if ("UTDID2".equals(attributeValue) || "UTDID".equals(attributeValue)) {
                                    z2 = true;
                                }
                            }
                        }
                    }
                    fileInputStream = fileInputStream2;
                } catch (Throwable unused2) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream == null) {
                        return "";
                    }
                }
            }
            if (fileInputStream == null) {
                return "";
            }
        } catch (Throwable unused3) {
        }
        try {
            fileInputStream.close();
            return "";
        } catch (Throwable unused4) {
            return "";
        }
    }

    public static String F(Context context) {
        int i2 = 0;
        if (u.c("IeGlhb21p").equalsIgnoreCase(Build.MANUFACTURER)) {
            try {
                Class<?> cls = Class.forName(u.c("WY29tLmFuZHJvaWQuaWQuaW1wbC5JZFByb3ZpZGVySW1wbA"));
                Object objInvoke = cls.getMethod(u.c("MZ2V0T0FJRA"), Context.class).invoke(cls.newInstance(), context);
                if (objInvoke != null) {
                    String str = (String) objInvoke;
                    n = str;
                    return str;
                }
            } catch (Throwable unused) {
                o = true;
            }
            return n;
        }
        if (u.c("IaHVhd2Vp").equalsIgnoreCase(Build.MANUFACTURER)) {
            try {
                Intent intent = new Intent();
                intent.setAction(u.c("WY29tLnVvZGlzLm9wZW5kZXZpY2UuT1BFTklEU19TRVJWSUNF"));
                intent.setPackage(u.c("UY29tLmh1YXdlaS5od2lk"));
                b bVar = new b();
                if (context.bindService(intent, bVar, 1)) {
                    while (i2 < 100 && TextUtils.isEmpty(n)) {
                        i2++;
                        Thread.sleep(15L);
                    }
                    context.unbindService(bVar);
                }
                return n;
            } catch (Throwable th) {
                y.a(th, "oa", "hw");
            }
        } else {
            "OPPO".equalsIgnoreCase(Build.MANUFACTURER);
        }
        o = true;
        return n;
    }

    public static String G(Context context) {
        if (!TextUtils.isEmpty(w)) {
            return w;
        }
        try {
            String strB = x.b(context, "open_common", "a1", "");
            if (TextUtils.isEmpty(strB)) {
                w = "amap" + UUID.randomUUID().toString().replace("_", "").toLowerCase();
                SharedPreferences.Editor editorB = x.b(context, "open_common");
                x.a(editorB, "a1", u.b(w));
                x.a(editorB);
            } else {
                w = u.c(strB);
            }
            return w;
        } catch (Throwable unused) {
            return w;
        }
    }

    public static String H(Context context) throws IllegalAccessException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        String str = A;
        if ((str == null || "".equals(str)) && b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            TelephonyManager telephonyManagerM = M(context);
            if (telephonyManagerM == null) {
                return "";
            }
            Method methodA = u.a(telephonyManagerM.getClass(), "UZ2V0U3Vic2NyaWJlcklk", (Class<?>[]) new Class[0]);
            if (methodA != null) {
                A = (String) methodA.invoke(telephonyManagerM, new Object[0]);
            }
            if (A == null) {
                A = "";
            }
            return A;
        }
        return A;
    }

    public static String I(Context context) {
        if (!b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return null;
        }
        TelephonyManager telephonyManagerM = M(context);
        if (telephonyManagerM == null) {
            return "";
        }
        String simOperatorName = telephonyManagerM.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? telephonyManagerM.getNetworkOperatorName() : simOperatorName;
    }

    public static int J(Context context) {
        ConnectivityManager connectivityManagerK;
        NetworkInfo activeNetworkInfo;
        if (context == null || !b(context, u.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) || (connectivityManagerK = K(context)) == null || (activeNetworkInfo = connectivityManagerK.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    public static ConnectivityManager K(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    public static int L(Context context) {
        TelephonyManager telephonyManagerM;
        if (b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU=")) && (telephonyManagerM = M(context)) != null) {
            return telephonyManagerM.getNetworkType();
        }
        return -1;
    }

    public static TelephonyManager M(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public static String N(Context context) {
        if (!c) {
            return "";
        }
        String strO = null;
        try {
            strO = O(context);
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(strO)) {
            c = false;
            return "";
        }
        try {
            byte[] bytes = u.c("MAAAAAAAAAAAAAAAAAAAAAA").getBytes("UTF-8");
            return new String(o.a(u.c("HYW1hcGFkaXVhbWFwYWRpdWFtYXBhZGl1YW1hcGFkaXU").getBytes("UTF-8"), o.b(strO), bytes), "UTF-8");
        } catch (Throwable unused2) {
            c = false;
            return "";
        }
    }

    public static String O(Context context) {
        String strP;
        try {
            strP = P(context);
        } catch (Throwable unused) {
            strP = "";
        }
        return !TextUtils.isEmpty(strP) ? strP : context == null ? "" : context.getSharedPreferences(u.c("SU2hhcmVkUHJlZmVyZW5jZUFkaXU"), 0).getString(r.a(u.c("RYW1hcF9kZXZpY2VfYWRpdQ")), "");
    }

    public static String P(Context context) {
        RandomAccessFile randomAccessFile;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream;
        String[] strArrSplit;
        String strA = r.a(u.c("LYW1hcF9kZXZpY2VfYWRpdQ"));
        String strQ = Q(context);
        if (TextUtils.isEmpty(strQ)) {
            return "";
        }
        File file = new File(strQ + File.separator + u.c("KYmFja3Vwcw"), u.c("MLmFkaXU"));
        if (file.exists() && file.canRead()) {
            if (file.length() == 0) {
                file.delete();
                return "";
            }
            ByteArrayOutputStream byteArrayOutputStream2 = null;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    bArr = new byte[1024];
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                randomAccessFile = null;
            }
            while (true) {
                try {
                    int i2 = randomAccessFile.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                } catch (Throwable unused3) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    a(byteArrayOutputStream2);
                }
                a(randomAccessFile);
            }
            String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            if (!TextUtils.isEmpty(str) && str.contains(u.c("SIw")) && (strArrSplit = str.split(u.c("SIw"))) != null && strArrSplit.length == 2 && TextUtils.equals(strA, strArrSplit[0])) {
                String str2 = strArrSplit[1];
                a(byteArrayOutputStream);
                a(randomAccessFile);
                return str2;
            }
            a(byteArrayOutputStream);
            a(randomAccessFile);
        }
        return "";
    }

    public static String Q(Context context) {
        if (Build.VERSION.SDK_INT < 9) {
            return null;
        }
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            Class<?> cls = Class.forName(u.c("SYW5kcm9pZC5vcy5zdG9yYWdlLlN0b3JhZ2VWb2x1bWU"));
            Method method = storageManager.getClass().getMethod(u.c("MZ2V0Vm9sdW1lTGlzdA"), new Class[0]);
            Method method2 = cls.getMethod(u.c("FZ2V0UGF0aA"), new Class[0]);
            Method method3 = cls.getMethod(u.c("DaXNSZW1vdmFibGU"), new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = Array.get(objInvoke, i2);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (!((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static String a() {
        return k;
    }

    public static String a(final Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (context == null) {
            return "";
        }
        String strN = N(context);
        b = strN;
        if (!TextUtils.isEmpty(strN)) {
            return b;
        }
        if (g == null || m) {
            return "";
        }
        m = true;
        ab.d().submit(new Runnable() { // from class: com.loc.n.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    a aVar = n.g;
                    n.h(context);
                    n.A(context);
                    String strD = aVar.d();
                    if (TextUtils.isEmpty(strD)) {
                        return;
                    }
                    aq.a();
                    a aVar2 = n.g;
                    strD.getBytes();
                    byte[] bArrA = aq.a(aVar2.a());
                    a aVar3 = n.g;
                    new String(bArrA);
                    String strC = aVar3.c();
                    if (TextUtils.isEmpty(strC)) {
                        return;
                    }
                    n.b = strC;
                } catch (Throwable unused) {
                }
            }
        });
        return "";
    }

    public static String a(Context context, String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29 || i2 < 21) {
            return "";
        }
        try {
            if (!TextUtils.isEmpty(i)) {
                return i;
            }
            TelephonyManager telephonyManagerM = M(context);
            if (h == -1) {
                Method methodA = u.a(TelephonyManager.class, "UZ2V0UGhvbmVDb3VudA=", (Class<?>[]) new Class[0]);
                if (methodA != null) {
                    try {
                        h = ((Integer) methodA.invoke(telephonyManagerM, new Object[0])).intValue();
                    } catch (Throwable unused) {
                        h = 0;
                    }
                } else {
                    h = 0;
                }
            }
            Method methodA2 = u.a(TelephonyManager.class, "MZ2V0SW1laQ=", (Class<?>[]) new Class[]{Integer.TYPE});
            if (methodA2 == null) {
                h = 0;
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < h; i3++) {
                try {
                    sb.append((String) methodA2.invoke(telephonyManagerM, Integer.valueOf(i3)));
                    sb.append(str);
                } catch (Throwable unused2) {
                }
            }
            String string = sb.toString();
            if (string.length() == 0) {
                h = 0;
                return "";
            }
            String strSubstring = string.substring(0, string.length() - 1);
            i = strSubstring;
            return strSubstring;
        } catch (Throwable unused3) {
            return "";
        }
    }

    public static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size - 1; i2++) {
            for (int i3 = 1; i3 < size - i2; i3++) {
                int i4 = i3 - 1;
                if (list.get(i4).level > list.get(i3).level) {
                    ScanResult scanResult = list.get(i4);
                    list.set(i4, list.get(i3));
                    list.set(i3, scanResult);
                }
            }
        }
        return list;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(String str) {
        k = str;
    }

    public static String b() {
        try {
            return !TextUtils.isEmpty(f3826e) ? f3826e : g == null ? "" : g.b();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return I(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static boolean b(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String c(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            String strA = A(context);
            if (strA != null && strA.length() >= 5) {
                return strA.substring(3, 5);
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String[] c() {
        return new String[]{"", ""};
    }

    public static int d(Context context) {
        try {
            return L(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static long d() {
        long blockCount;
        long blockCount2;
        long j2 = x;
        if (j2 != 0) {
            return j2;
        }
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockCount = (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1048576;
                blockCount2 = (statFs2.getBlockCountLong() * statFs2.getBlockSizeLong()) / 1048576;
            } else {
                blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1048576;
                blockCount2 = (((long) statFs2.getBlockCount()) * ((long) statFs2.getBlockSize())) / 1048576;
            }
            x = blockCount + blockCount2;
        } catch (Throwable unused) {
        }
        return x;
    }

    public static int e(Context context) {
        try {
            return J(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String e() {
        if (!TextUtils.isEmpty(z)) {
            return z;
        }
        String property = System.getProperty("os.arch");
        z = property;
        return property;
    }

    public static String f(Context context) {
        try {
            return H(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static /* synthetic */ boolean f() {
        o = true;
        return true;
    }

    public static String g() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = Build.VERSION.SDK_INT >= 9 ? networkInterface.getHardwareAddress() : null;
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        String upperCase = Integer.toHexString(b2 & 255).toUpperCase();
                        if (upperCase.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(upperCase);
                        sb.append(Constants.COLON_SEPARATOR);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String g(final Context context) {
        if (o) {
            return "";
        }
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return F(context);
        }
        ab.d().submit(new Runnable() { // from class: com.loc.n.2
            @Override // java.lang.Runnable
            public final void run() {
                n.F(context);
            }
        });
        return n;
    }

    public static String h(Context context) {
        if (f3825a != null && !"".equals(f3825a)) {
            return f3825a;
        }
        if (b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFVFRJTkdT"))) {
            f3825a = Settings.System.getString(context.getContentResolver(), UTUtdid.UM_SETTINGS_STORAGE_NEW);
        }
        if (f3825a != null && !"".equals(f3825a)) {
            return f3825a;
        }
        try {
            f3825a = E(context);
        } catch (Throwable unused) {
        }
        String str = f3825a;
        return str == null ? "" : str;
    }

    public static String i(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (!TextUtils.isEmpty(q)) {
            return q;
        }
        if (!b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return (String) u.a(Build.class, "MZ2V0U2VyaWFs", (Class<?>[]) new Class[0]).invoke(Build.class, new Object[0]);
        }
        if (Build.VERSION.SDK_INT >= 9) {
            q = Build.SERIAL;
        }
        String str = q;
        return str == null ? "" : str;
    }

    public static String j(Context context) {
        if (!TextUtils.isEmpty(p)) {
            return p;
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), u.c(new String(v.a(13))));
            p = string;
            return string == null ? "" : string;
        } catch (Throwable unused) {
            return p;
        }
    }

    public static String k(Context context) {
        WifiManager wifiManager;
        if (context == null) {
            return "";
        }
        try {
            return (b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF")) && (wifiManager = (WifiManager) context.getSystemService("wifi")) != null && wifiManager.isWifiEnabled()) ? wifiManager.getConnectionInfo().getBSSID() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String l(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            if (b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                if (wifiManager.isWifiEnabled()) {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() != 0) {
                        List<ScanResult> listA = a(scanResults);
                        boolean z2 = true;
                        for (int i2 = 0; i2 < listA.size() && i2 < 7; i2++) {
                            ScanResult scanResult = listA.get(i2);
                            if (z2) {
                                z2 = false;
                            } else {
                                sb.append(";");
                            }
                            sb.append(scanResult.BSSID);
                        }
                    }
                    return sb.toString();
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static String m(Context context) {
        if ((r == null || "".equals(r)) && b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            r = wifiManager.getConnectionInfo().getMacAddress();
            if (u.c("YMDI6MDA6MDA6MDA6MDA6MDA").equals(r) || u.c("YMDA6MDA6MDA6MDA6MDA6MDA").equals(r)) {
                r = g();
            }
            return r;
        }
        return r;
    }

    public static String n(Context context) {
        try {
            TelephonyManager telephonyManagerM = M(context);
            if (telephonyManagerM == null) {
                return "";
            }
            String networkOperator = telephonyManagerM.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                return networkOperator.substring(0, 3);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String o(Context context) {
        try {
            TelephonyManager telephonyManagerM = M(context);
            if (telephonyManagerM == null) {
                return "";
            }
            String networkOperator = telephonyManagerM.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                return networkOperator.substring(3);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int p(Context context) {
        try {
            return L(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int q(Context context) {
        try {
            return J(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static NetworkInfo r(Context context) {
        ConnectivityManager connectivityManagerK;
        if (b(context, u.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) && (connectivityManagerK = K(context)) != null) {
            return connectivityManagerK.getActiveNetworkInfo();
        }
        return null;
    }

    public static String s(Context context) {
        try {
            NetworkInfo networkInfoR = r(context);
            if (networkInfoR == null) {
                return null;
            }
            return networkInfoR.getExtraInfo();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String t(Context context) {
        StringBuilder sb;
        if (s != null && !"".equals(s)) {
            return s;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (i3 > i2) {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append(Operators.MUL);
            sb.append(i3);
        } else {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append(Operators.MUL);
            sb.append(i2);
        }
        s = sb.toString();
        return s;
    }

    public static String u(Context context) {
        try {
            if (!b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return A;
            }
            TelephonyManager telephonyManagerM = M(context);
            return telephonyManagerM == null ? "" : telephonyManagerM.getNetworkOperatorName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String v(Context context) {
        ConnectivityManager connectivityManagerK;
        NetworkInfo activeNetworkInfo;
        try {
            return (!b(context, u.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) || (connectivityManagerK = K(context)) == null || (activeNetworkInfo = connectivityManagerK.getActiveNetworkInfo()) == null) ? "" : activeNetworkInfo.getTypeName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String w(Context context) {
        try {
            String strX = x(context);
            try {
                if (TextUtils.isEmpty(strX)) {
                    strX = a(context);
                }
                if (TextUtils.isEmpty(strX)) {
                    strX = h(context);
                }
                if (TextUtils.isEmpty(strX)) {
                    strX = g(context);
                }
                if (TextUtils.isEmpty(strX)) {
                    strX = j(context);
                }
                return TextUtils.isEmpty(strX) ? G(context) : strX;
            } catch (Throwable unused) {
                return strX;
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String x(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if ((t == null || "".equals(t)) && b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            TelephonyManager telephonyManagerM = M(context);
            if (telephonyManagerM == null) {
                return "";
            }
            Method methodA = u.a(telephonyManagerM.getClass(), "QZ2V0RGV2aWNlSWQ", (Class<?>[]) new Class[0]);
            if (Build.VERSION.SDK_INT >= 26) {
                methodA = u.a(telephonyManagerM.getClass(), "QZ2V0SW1laQ==", (Class<?>[]) new Class[0]);
            }
            if (methodA != null) {
                t = (String) methodA.invoke(telephonyManagerM, new Object[0]);
            }
            if (t == null) {
                t = "";
            }
            return t;
        }
        return t;
    }

    public static String y(Context context) {
        return x(context) + "#" + a(context) + "#" + w(context);
    }

    public static String z(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if ((u == null || "".equals(u)) && b(context, u.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            TelephonyManager telephonyManagerM = M(context);
            if (telephonyManagerM == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT >= 26) {
                Method methodA = u.a(telephonyManagerM.getClass(), "QZ2V0TWVpZA==", (Class<?>[]) new Class[0]);
                if (methodA != null) {
                    u = (String) methodA.invoke(telephonyManagerM, new Object[0]);
                }
                if (u == null) {
                    u = "";
                }
            }
            return u;
        }
        return u;
    }
}
