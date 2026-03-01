package com.getui.gtc.dim.c;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.getui.gtc.base.annotation.MutableMethod;
import com.getui.gtc.base.util.CommonUtil;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.igexin.assist.util.AssistUtils;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.as;
import io.dcloud.common.util.JSUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final Map<String, String> c = new HashMap<String, String>() { // from class: com.getui.gtc.dim.c.a.1
        {
            put(AssistUtils.BRAND_HW, "ro.build.version.emui");
            put(AssistUtils.BRAND_HON, "ro.build.version.magic#ro.build.version.emui");
            put(AssistUtils.BRAND_XIAOMI, "ro.build.version.incremental");
            put("redmi", "ro.build.version.incremental");
            put("blackshark", "ro.build.version.incremental");
            put("samsang", "ro.build.version.incremental");
            put(AssistUtils.BRAND_VIVO, as.i);
            put(AssistUtils.BRAND_OPPO, "ro.build.version.opporom#ro.build.version.oplusrom");
            put(AssistUtils.BRAND_MZ, "ro.build.display.id");
            put("lenovo", "ro.build.version.incremental");
            put("smartisan", "ro.modversion");
            put("htc", "ro.build.sense.version");
            put("oneplus", as.k);
            put("yunos", "ro.cta.yunos.version");
            put("360", "ro.build.uiversion");
            put("nubia", "ro.build.rom.internal.id");
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, String> f2170a = new HashMap();
    public static final Map<String, String> d = new HashMap<String, String>() { // from class: com.getui.gtc.dim.c.a.2
        {
            put(AssistUtils.BRAND_HW, "com.android.permission.GET_INSTALLED_APP");
            put(AssistUtils.BRAND_HON, "com.android.permission.GET_INSTALLED_APPS");
        }
    };
    public static final Map<String, String> b = new HashMap();

    /* JADX INFO: renamed from: com.getui.gtc.dim.c.a$a, reason: collision with other inner class name */
    public class ServiceConnectionC0038a implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2171a = false;
        public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (Throwable th) {
                com.getui.gtc.dim.e.b.a(th);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public class b implements IInterface {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final IBinder f2172a;

        public b(IBinder iBinder) {
            this.f2172a = iBinder;
        }

        public final String a() throws RemoteException {
            String string;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.f2172a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    string = parcelObtain2.readString();
                } catch (Exception e2) {
                    com.getui.gtc.dim.e.b.a(e2);
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    string = null;
                }
                return string;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f2172a;
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static Location a(Context context, String str) {
        try {
            com.getui.gtc.dim.e.c.a(context, "network".equals(str) ? "android.permission.ACCESS_COARSE_LOCATION" : "android.permission.ACCESS_FINE_LOCATION", true);
            return ((LocationManager) context.getSystemService("location")).getLastKnownLocation(str);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return null;
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String a() {
        Process processA;
        BufferedReader bufferedReader = null;
        try {
            StringBuilder sb = new StringBuilder();
            processA = com.getui.gtc.dim.e.c.a(new String(Base64.decode("aXAgYWRkcg==", 0)));
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(processA.getInputStream()));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        if (Pattern.matches("^\\d+: ((wlan\\d+)|(eth\\d+)): .*", line)) {
                            String strSubstring = line.substring(line.indexOf(": ") + 2);
                            sb.append(sb.length() == 0 ? "" : ",");
                            sb.append(strSubstring.substring(0, strSubstring.indexOf(": ")));
                            sb.append("#");
                            String line2 = bufferedReader2.readLine();
                            if (line2 != null) {
                                sb.append(line2.substring(line2.indexOf("link/ether ") + 11, line2.indexOf(" brd")));
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        try {
                            com.getui.gtc.dim.e.b.a(th);
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable unused) {
                                }
                            }
                            if (processA != null) {
                                try {
                                    processA.destroy();
                                } catch (Throwable unused2) {
                                }
                            }
                            return "";
                        } finally {
                        }
                    }
                }
                String string = sb.toString();
                try {
                    bufferedReader2.close();
                } catch (Throwable unused3) {
                }
                if (processA != null) {
                    try {
                        processA.destroy();
                    } catch (Throwable unused4) {
                    }
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            processA = null;
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String a(int i, Context context) {
        String deviceId;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imei above 29");
            }
            if (AssistUtils.BRAND_VIVO.equalsIgnoreCase(b()) && Build.VERSION.SDK_INT < 26) {
                throw new RuntimeException("do not get imei from vivo below 29");
            }
            if (Build.VERSION.SDK_INT < 23) {
                return "";
            }
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (telephonyManager == null || i < 0 || (deviceId = telephonyManager.getDeviceId(i)) == null) ? "" : deviceId;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imei above 29");
            }
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return !TextUtils.isEmpty(deviceId) ? deviceId : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String a(Context context, boolean z) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new RuntimeException("can not get oaid at main thread");
            }
            d.a();
            if (d.f2174a != null && context != null) {
                d.b = context.getApplicationContext();
                if (d.b()) {
                    d.c = d.f2174a.c(d.b);
                }
            }
            String strC = d.c ? d.c() : null;
            if (!"HONOR".equals(d.d)) {
                return strC;
            }
            String strB = b(context, z);
            if (strB == null) {
                strB = "";
            }
            return strB + '#' + strC;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String b() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String b(int i, Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imsi above 29");
            }
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            Object objA = com.getui.gtc.dim.e.c.a(i, "getSubscriberId", context);
            return objA != null ? (String) objA : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String b(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imsi above 29");
            }
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            return !TextUtils.isEmpty(subscriberId) ? subscriberId : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String b(Context context, String str) {
        try {
            com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_WIFI_STATE", true);
            if (!com.getui.gtc.dim.e.c.c(context)) {
                return "2##";
            }
            int i = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getDhcpInfo().gateway;
            String strB = com.getui.gtc.dim.e.c.b((i & 255) + Operators.DOT_STR + ((i >> 8) & 255) + Operators.DOT_STR + ((i >> 16) & 255) + Operators.DOT_STR + ((i >> 24) & 255));
            return "1#" + str.replace(JSUtil.QUOTE, "") + "#" + strB;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a A[Catch: all -> 0x0035, TryCatch #0 {all -> 0x0035, blocks: (B:4:0x0004, B:6:0x000a, B:8:0x0010, B:10:0x001a, B:12:0x0025, B:14:0x002d, B:15:0x0034), top: B:19:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d A[Catch: all -> 0x0035, TryCatch #0 {all -> 0x0035, blocks: (B:4:0x0004, B:6:0x000a, B:8:0x0010, B:10:0x001a, B:12:0x0025, B:14:0x002d, B:15:0x0034), top: B:19:0x0004 }] */
    @com.getui.gtc.base.annotation.MutableMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r2, boolean r3) {
        /*
            java.lang.String r0 = ""
            if (r3 != 0) goto L10
            boolean r3 = com.getui.gtc.dim.c.d.i.c()     // Catch: java.lang.Throwable -> L35
            if (r3 == 0) goto L10
            java.lang.String r2 = "support honor oaid"
            com.getui.gtc.dim.e.b.a(r2)     // Catch: java.lang.Throwable -> L35
            return r0
        L10:
            android.os.Looper r3 = android.os.Looper.myLooper()     // Catch: java.lang.Throwable -> L35
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L35
            if (r3 == r1) goto L2d
            com.getui.gtc.dim.c.d$h r3 = new com.getui.gtc.dim.c.d$h     // Catch: java.lang.Throwable -> L35
            r3.<init>()     // Catch: java.lang.Throwable -> L35
            boolean r1 = r3.a(r2)     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L39
            r3.c(r2)     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = r3.b(r2)     // Catch: java.lang.Throwable -> L35
            return r2
        L2d:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = "can not get oaid at main thread"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L35
            throw r2     // Catch: java.lang.Throwable -> L35
        L35:
            r2 = move-exception
            com.getui.gtc.dim.e.b.a(r2)
        L39:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.a.b(android.content.Context, boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @com.getui.gtc.base.annotation.MutableMethod
    @android.annotation.SuppressLint({"MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int c(int r11, android.content.Context r12) {
        /*
            java.lang.String r0 = "_id"
            r1 = -1
            r2 = 0
            java.lang.String r3 = "android.permission.READ_PHONE_STATE"
            r4 = 1
            com.getui.gtc.dim.e.c.a(r12, r3, r4)     // Catch: java.lang.Throwable -> L62
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L2a
            r5 = 22
            if (r3 < r5) goto L1d
            android.telephony.SubscriptionManager r3 = android.telephony.SubscriptionManager.from(r12)     // Catch: java.lang.Throwable -> L2a
            android.telephony.SubscriptionInfo r3 = r3.getActiveSubscriptionInfoForSimSlotIndex(r11)     // Catch: java.lang.Throwable -> L2a
            int r3 = r3.getSubscriptionId()     // Catch: java.lang.Throwable -> L2a
            goto L1e
        L1d:
            r3 = -1
        L1e:
            if (r3 == r1) goto L21
            goto L5c
        L21:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L29
            java.lang.String r5 = "invalid subId"
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L29
            throw r1     // Catch: java.lang.Throwable -> L29
        L29:
            r1 = r3
        L2a:
            android.content.ContentResolver r5 = r12.getContentResolver()     // Catch: java.lang.Throwable -> L62
            java.lang.String r12 = "content://telephony/siminfo"
            android.net.Uri r6 = android.net.Uri.parse(r12)     // Catch: java.lang.Throwable -> L62
            java.lang.String r12 = "sim_id"
            java.lang.String[] r7 = new java.lang.String[]{r0, r12}     // Catch: java.lang.Throwable -> L62
            java.lang.String r8 = "sim_id = ?"
            java.lang.String[] r9 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L62
            r12 = 0
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> L62
            r9[r12] = r11     // Catch: java.lang.Throwable -> L62
            r10 = 0
            android.database.Cursor r2 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L62
            if (r2 == 0) goto L5b
            boolean r11 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L62
            if (r11 == 0) goto L5b
            int r11 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L62
            int r3 = r2.getInt(r11)     // Catch: java.lang.Throwable -> L62
            goto L5c
        L5b:
            r3 = r1
        L5c:
            if (r2 == 0) goto L6c
            r2.close()
            goto L6c
        L62:
            r11 = move-exception
            com.getui.gtc.dim.e.b.a(r11)     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L6b
            r2.close()
        L6b:
            r3 = r1
        L6c:
            return r3
        L6d:
            r11 = move-exception
            if (r2 == 0) goto L73
            r2.close()
        L73:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.a.c(int, android.content.Context):int");
    }

    @MutableMethod
    public static String c() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String c(Context context) {
        Throwable th;
        String simSerialNumber;
        try {
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (TextUtils.isEmpty(simSerialNumber)) {
                simSerialNumber = "";
            }
        } catch (Throwable th2) {
            th = th2;
            simSerialNumber = "";
        }
        try {
            if (!TextUtils.isEmpty(simSerialNumber)) {
                if (simSerialNumber.length() < 20) {
                    return "";
                }
            }
        } catch (Throwable th3) {
            th = th3;
            com.getui.gtc.dim.e.b.a(th);
        }
        return simSerialNumber;
    }

    @MutableMethod
    public static String d() {
        try {
            String strB = b();
            if (!TextUtils.isEmpty(strB)) {
                String lowerCase = strB.toLowerCase();
                if (f2170a.containsKey(lowerCase)) {
                    return com.getui.gtc.dim.e.c.a(f2170a.get(lowerCase), "");
                }
                if (c.containsKey(lowerCase)) {
                    return com.getui.gtc.dim.e.c.a(c.get(lowerCase), "");
                }
            }
            String strE = e();
            if (!TextUtils.isEmpty(strE)) {
                String lowerCase2 = strE.toLowerCase();
                if (f2170a.containsKey(lowerCase2)) {
                    return com.getui.gtc.dim.e.c.a(f2170a.get(lowerCase2), "");
                }
                if (c.containsKey(lowerCase2)) {
                    return com.getui.gtc.dim.e.c.a(c.get(lowerCase2), "");
                }
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
        }
        return "";
    }

    @MutableMethod
    public static String d(int i, Context context) {
        String str;
        String str2 = "";
        try {
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            Object objA = com.getui.gtc.dim.e.c.a(i, "getSimSerialNumber", context);
            str = objA != null ? (String) objA : "";
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                if (str.length() < 20) {
                    return "";
                }
            }
            return str;
        } catch (Throwable th2) {
            str2 = str;
            th = th2;
            com.getui.gtc.dim.e.b.a(th);
            return str2;
        }
    }

    @MutableMethod
    public static String d(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), com.umeng.commonsdk.statistics.idtracking.b.f5435a);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String e() {
        try {
            return Build.MANUFACTURER;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String e(Context context) {
        try {
            if (CommonUtil.isMainThread()) {
                throw new RuntimeException("cannot get advertisingId from main thread");
            }
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            ServiceConnectionC0038a serviceConnectionC0038a = new ServiceConnectionC0038a();
            if (!context.bindService(intent, serviceConnectionC0038a, 1)) {
                throw new IOException("Google Play connection failed");
            }
            try {
                if (serviceConnectionC0038a.f2171a) {
                    throw new IllegalStateException();
                }
                serviceConnectionC0038a.f2171a = true;
                return new b(serviceConnectionC0038a.b.poll(3000L, TimeUnit.MILLISECONDS)).a();
            } finally {
                context.unbindService(serviceConnectionC0038a);
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String f() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String f(Context context) {
        Object objInvoke;
        try {
            com.getui.gtc.dim.e.c.a(context, "android.permission.READ_PHONE_STATE", true);
            if (Build.VERSION.SDK_INT < 26) {
                Class<?> cls = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP);
                objInvoke = cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
            } else {
                if (Build.VERSION.SDK_INT >= 29) {
                    throw new RuntimeException("can not get serialnumber above 29");
                }
                Class<?> cls2 = Class.forName("android.os.Build");
                objInvoke = cls2.getMethod("getSerial", new Class[0]).invoke(cls2, new Object[0]);
            }
            return (String) objInvoke;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return null;
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String g(Context context) {
        byte[] hardwareAddress;
        String string = "";
        try {
            if (Build.VERSION.SDK_INT < 23) {
                com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_WIFI_STATE", true);
                return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            }
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ("wlan0".equalsIgnoreCase(networkInterfaceNextElement.getName()) && (hardwareAddress = networkInterfaceNextElement.getHardwareAddress()) != null && hardwareAddress.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    string = sb.toString();
                }
            }
            return string;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @com.getui.gtc.base.annotation.MutableMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<android.content.pm.PackageInfo> g() {
        /*
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L59
            r2 = 33
            if (r1 >= r2) goto L51
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L59
            r1.<init>()     // Catch: java.lang.Throwable -> L59
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> L59
            java.lang.String r3 = "cG0gbGlzdCBwYWNrYWdlcw=="
            r4 = 0
            byte[] r3 = android.util.Base64.decode(r3, r4)     // Catch: java.lang.Throwable -> L59
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L59
            java.lang.Process r2 = com.getui.gtc.dim.e.c.a(r2)     // Catch: java.lang.Throwable -> L59
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4e
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4e
            java.io.InputStream r6 = r2.getInputStream()     // Catch: java.lang.Throwable -> L4e
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L4e
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L4e
        L2a:
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Throwable -> L4c
            if (r0 == 0) goto L41
            java.lang.String r5 = ":"
            java.lang.String[] r0 = r0.split(r5)     // Catch: java.lang.Throwable -> L2a
            r5 = 1
            r0 = r0[r5]     // Catch: java.lang.Throwable -> L2a
            android.content.pm.PackageInfo r0 = com.getui.gtc.dim.e.d.a(r0, r4)     // Catch: java.lang.Throwable -> L2a
            r1.add(r0)     // Catch: java.lang.Throwable -> L2a
            goto L2a
        L41:
            r3.close()     // Catch: java.lang.Throwable -> L45
            goto L46
        L45:
        L46:
            if (r2 == 0) goto L4b
            r2.destroy()     // Catch: java.lang.Throwable -> L4b
        L4b:
            return r1
        L4c:
            r0 = move-exception
            goto L5d
        L4e:
            r1 = move-exception
            r3 = r0
            goto L5c
        L51:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L59
            java.lang.String r2 = "can not get al by pm above 33"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L59
            throw r1     // Catch: java.lang.Throwable -> L59
        L59:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L5c:
            r0 = r1
        L5d:
            com.getui.gtc.dim.e.b.a(r0)     // Catch: java.lang.Throwable -> L71
            if (r3 == 0) goto L67
            r3.close()     // Catch: java.lang.Throwable -> L66
            goto L67
        L66:
        L67:
            if (r2 == 0) goto L6c
            r2.destroy()     // Catch: java.lang.Throwable -> L6c
        L6c:
            java.util.List r0 = java.util.Collections.emptyList()
            return r0
        L71:
            r0 = move-exception
            if (r3 == 0) goto L79
            r3.close()     // Catch: java.lang.Throwable -> L78
            goto L79
        L78:
        L79:
            if (r2 == 0) goto L7e
            r2.destroy()     // Catch: java.lang.Throwable -> L7e
        L7e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.a.g():java.util.List");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String h(Context context) {
        try {
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return "";
            }
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
                case 0:
                case 1:
                case 2:
                case 3:
                    return "中国移动";
                case 4:
                case 5:
                case 6:
                    return "中国联通";
                case 7:
                case 8:
                case 9:
                    return "中国电信";
                default:
                    return simOperator;
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static List<PackageInfo> h() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new RuntimeException("can not get al by us at main thread");
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 10000; i <= 19999; i++) {
                PackageInfo packageInfoA = com.getui.gtc.dim.e.d.a(i);
                if (packageInfoA != null) {
                    arrayList.add(packageInfoA);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return Collections.emptyList();
        }
    }

    @Deprecated
    public static String i() {
        return "";
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String i(Context context) {
        try {
            com.getui.gtc.dim.e.c.a(context, DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, true);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                throw new IllegalStateException("getSystemService: CONNECTIVITY_SERVICE failed");
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                throw new IllegalStateException("getActiveNetworkInfo failed");
            }
            if (!activeNetworkInfo.isAvailable()) {
                throw new IllegalStateException("no available activeNetwork");
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return "WIFI";
            }
            int subtype = activeNetworkInfo.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                subtype = telephonyManager.getNetworkType();
            }
            if (subtype == 20) {
                return "5G";
            }
            switch (subtype) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    return "NULL";
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "NULL";
        }
    }

    @MutableMethod
    public static String j(Context context) {
        try {
            if (!com.getui.gtc.dim.e.c.a(context)) {
                throw new IllegalStateException("network not connected");
            }
            boolean zB = com.getui.gtc.dim.e.c.b(context);
            boolean zC = com.getui.gtc.dim.e.c.c(context);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ((zB && networkInterfaceNextElement.getName().toLowerCase().contains("rmnet")) || (zC && networkInterfaceNextElement.getName().toLowerCase().contains("wlan0"))) {
                    List<InterfaceAddress> interfaceAddresses = networkInterfaceNextElement.getInterfaceAddresses();
                    ArrayList arrayList3 = new ArrayList();
                    Iterator<InterfaceAddress> it = interfaceAddresses.iterator();
                    while (it.hasNext()) {
                        InetAddress address = it.next().getAddress();
                        if (!address.isLoopbackAddress()) {
                            arrayList3.add(address.getHostAddress());
                        }
                    }
                    if (zB) {
                        arrayList.addAll(arrayList3);
                    }
                    if (zC) {
                        arrayList2.addAll(arrayList3);
                    }
                }
            }
            if (zB) {
                StringBuilder sb = new StringBuilder();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    sb.append((String) it2.next());
                    sb.append(",");
                }
                if (sb.toString().endsWith(",")) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            if (!zC) {
                return "";
            }
            StringBuilder sb2 = new StringBuilder();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                sb2.append((String) it3.next());
                sb2.append(",");
            }
            if (sb2.toString().endsWith(",")) {
                sb2.deleteCharAt(sb2.length() - 1);
            }
            return sb2.toString();
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    public static String k(Context context) {
        try {
            if (!com.getui.gtc.dim.e.c.a(context)) {
                throw new IllegalStateException("network not connected");
            }
            boolean zB = com.getui.gtc.dim.e.c.b(context);
            boolean zC = com.getui.gtc.dim.e.c.c(context);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                String lowerCase = networkInterfaceNextElement.getName().toLowerCase();
                if ((zB && (lowerCase.contains("rmnet") || lowerCase.contains("ccmni"))) || (zC && lowerCase.contains("wlan0"))) {
                    List<InterfaceAddress> interfaceAddresses = networkInterfaceNextElement.getInterfaceAddresses();
                    ArrayList arrayList3 = new ArrayList();
                    boolean z = false;
                    Iterator<InterfaceAddress> it = interfaceAddresses.iterator();
                    while (it.hasNext()) {
                        InetAddress address = it.next().getAddress();
                        if (!address.isLoopbackAddress()) {
                            if (address instanceof Inet6Address) {
                                arrayList3.add(address.getHostAddress());
                            } else if (address instanceof Inet4Address) {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        if (zB) {
                            arrayList.addAll(arrayList3);
                        }
                        if (zC) {
                            arrayList2.addAll(arrayList3);
                        }
                    }
                }
            }
            if (zB) {
                StringBuilder sb = new StringBuilder();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    sb.append((String) it2.next());
                    sb.append(",");
                }
                if (sb.toString().endsWith(",")) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            if (!zC) {
                return "";
            }
            StringBuilder sb2 = new StringBuilder();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                sb2.append((String) it3.next());
                sb2.append(",");
            }
            if (sb2.toString().endsWith(",")) {
                sb2.deleteCharAt(sb2.length() - 1);
            }
            return sb2.toString();
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return "";
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static WifiInfo l(Context context) {
        try {
            com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_WIFI_STATE", true);
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                try {
                    Field declaredField = WifiInfo.class.getDeclaredField("mIpAddress");
                    declaredField.setAccessible(true);
                    declaredField.set(connectionInfo, null);
                } catch (Throwable th) {
                    com.getui.gtc.dim.e.b.a(th);
                }
            }
            return connectionInfo;
        } catch (Throwable th2) {
            com.getui.gtc.dim.e.b.a(th2);
            return null;
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static List<ScanResult> m(Context context) {
        try {
            if (CommonUtil.isMainThread()) {
                throw new IllegalStateException("cannot get wifi list from the main thread");
            }
            com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_FINE_LOCATION", true);
            List<ScanResult> scanResults = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getScanResults();
            if (scanResults == null || scanResults.size() <= 0) {
                return null;
            }
            return scanResults;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [int] */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v7 */
    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static String n(Context context) {
        int systemId;
        int i;
        int baseStationId;
        ?? r8;
        boolean z;
        ?? r82;
        CellLocation cellLocation;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_FINE_LOCATION", true);
            } else if (!com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_COARSE_LOCATION") && !com.getui.gtc.dim.e.c.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
                throw new IllegalStateException("permission coarse/fine location not granted");
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            List list = null;
            if (telephonyManager.getSimState() == 5) {
                String networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator == null || networkOperator.length() < 3) {
                    systemId = 0;
                    i = 0;
                } else {
                    i = Integer.parseInt(networkOperator.substring(0, 3));
                    systemId = Integer.parseInt(networkOperator.substring(3));
                }
                try {
                    cellLocation = telephonyManager.getCellLocation();
                    z = cellLocation instanceof GsmCellLocation;
                } catch (Throwable th) {
                    th = th;
                    z = false;
                }
                try {
                    if (z) {
                        int lac = ((GsmCellLocation) cellLocation).getLac();
                        baseStationId = ((GsmCellLocation) cellLocation).getCid();
                        r82 = lac;
                    } else if (cellLocation instanceof CdmaCellLocation) {
                        int networkId = ((CdmaCellLocation) cellLocation).getNetworkId();
                        if (systemId == 0) {
                            systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                        }
                        baseStationId = ((CdmaCellLocation) cellLocation).getBaseStationId();
                        r82 = networkId;
                    } else {
                        baseStationId = 0;
                        r82 = 0;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    com.getui.gtc.dim.e.b.a(th);
                    baseStationId = 0;
                    r82 = z;
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    r8 = r82;
                } else {
                    list = (List) telephonyManager.getClass().getMethod("getNeighboringCellInfo", new Class[0]).invoke(telephonyManager, new Object[0]);
                    r8 = r82;
                }
            } else {
                systemId = 0;
                i = 0;
                baseStationId = 0;
                r8 = 0;
            }
            ?? sb = new StringBuilder();
            sb.append(i);
            sb.append("|");
            sb.append(systemId);
            sb.append("|");
            sb.append(r8);
            sb.append("|");
            sb.append(baseStationId);
            sb.append("|");
            for (int i2 = 0; list != null && i2 < list.size(); i2++) {
                sb.append(((NeighboringCellInfo) list.get(i2)).getCid());
                if (i2 < list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        } catch (Throwable th3) {
            com.getui.gtc.dim.e.b.a(th3);
            return "0|0|0|0|";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x01a0 A[Catch: all -> 0x01e7, TryCatch #4 {all -> 0x01e7, blocks: (B:3:0x0008, B:5:0x000e, B:12:0x0025, B:15:0x002f, B:17:0x003f, B:20:0x0047, B:21:0x0054, B:23:0x005a, B:25:0x0066, B:27:0x006a, B:62:0x019a, B:64:0x01a0, B:65:0x01a5, B:29:0x0092, B:31:0x0096, B:32:0x00b7, B:34:0x00bd, B:36:0x00c1, B:37:0x00df, B:39:0x00e3, B:40:0x0101, B:60:0x018e, B:67:0x01cb, B:69:0x01d1, B:72:0x01d7, B:73:0x01de, B:74:0x01df, B:75:0x01e6), top: B:87:0x0008 }] */
    @com.getui.gtc.base.annotation.MutableMethod
    @android.annotation.SuppressLint({"MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String o(android.content.Context r22) {
        /*
            Method dump skipped, instruction units count: 492
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.a.o(android.content.Context):java.lang.String");
    }

    @MutableMethod
    public static List<PackageInfo> p(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (listQueryIntentActivities.size() > 0) {
                Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
                while (it.hasNext()) {
                    try {
                        arrayList.add(com.getui.gtc.dim.e.d.a(it.next().activityInfo.packageName, 0));
                    } catch (Throwable unused) {
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return Collections.emptyList();
        }
    }

    @MutableMethod
    public static List<PackageInfo> q(Context context) {
        String str;
        try {
            String lowerCase = b().toLowerCase();
            if (b.containsKey(lowerCase)) {
                str = b.get(lowerCase);
            } else {
                if (!d.containsKey(lowerCase)) {
                    throw new RuntimeException("not support brand: ".concat(String.valueOf(lowerCase)));
                }
                str = d.get(lowerCase);
            }
            com.getui.gtc.dim.e.c.a(context, str, false);
            PackageManager packageManager = context.getPackageManager();
            return (List) packageManager.getClass().getDeclaredMethod(new String(Base64.decode("Z2V0SW5zdGFsbGVkUGFja2FnZXM=", 0)), Integer.TYPE).invoke(packageManager, 5);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return Collections.emptyList();
        }
    }

    @MutableMethod
    public static List<PackageInfo> r(Context context) {
        String[] list;
        File parentFile;
        try {
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            throw new RuntimeException("can not get localDirs above 29");
        }
        File externalCacheDir = context.getExternalCacheDir();
        File parentFile2 = null;
        if (externalCacheDir != null && (parentFile = externalCacheDir.getParentFile()) != null) {
            parentFile2 = parentFile.getParentFile();
        }
        if (parentFile2 != null && parentFile2.isDirectory() && (list = parentFile2.list(new FilenameFilter() { // from class: com.getui.gtc.dim.c.a.3
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                try {
                    if (file.isDirectory()) {
                        if (str.contains(Operators.DOT_STR)) {
                            return true;
                        }
                    }
                } catch (Throwable unused) {
                }
                return false;
            }
        })) != null) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                try {
                    arrayList.add(com.getui.gtc.dim.e.d.a(str, 0));
                } catch (Throwable unused) {
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }
}
