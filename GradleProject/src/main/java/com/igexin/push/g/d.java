package com.igexin.push.g;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Pair;
import com.igexin.assist.util.AssistUtils;
import com.vivo.push.PushClientConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f3582a = 0;
    public static HashMap<String, Object> b = new HashMap<>();
    public static final String c = "ro.miui.ui.version.name";
    public static final String d = "ro.miui.ui.version.code";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3583e = "GT";
    public static volatile Boolean f;
    public static String g;
    public static PackageInfo h;

    /* JADX WARN: Code restructure failed: missing block: B:104:0x01e6, code lost:
    
        r8.putExtras(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01e9, code lost:
    
        if (r9 == false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01ef, code lost:
    
        if (r3.getPackage() != null) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01f5, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 15) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01f7, code lost:
    
        r3.setSelector(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01fb, code lost:
    
        r3 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01fc, code lost:
    
        if (r5 == null) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0204, code lost:
    
        if (r5.startsWith("intent:") == false) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0206, code lost:
    
        r5 = r5.substring(7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x020b, code lost:
    
        if (r10 == null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x020d, code lost:
    
        r0 = new java.lang.StringBuilder();
        r0.append(r10);
        r0.append(com.taobao.weex.el.parse.Operators.CONDITION_IF_MIDDLE);
        r0.append(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x021d, code lost:
    
        r5 = r0.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0227, code lost:
    
        if (r5.startsWith("android-app:") == false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0231, code lost:
    
        if (r5.charAt(12) != '/') goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0239, code lost:
    
        if (r5.charAt(13) != '/') goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x023b, code lost:
    
        r6 = r5.indexOf(47, 14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0243, code lost:
    
        if (r6 >= 0) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0245, code lost:
    
        r3.setPackage(r5.substring(14));
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x024c, code lost:
    
        if (r11 != false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x024e, code lost:
    
        r3.setAction("android.intent.action.MAIN");
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0252, code lost:
    
        r3.setPackage(r5.substring(14, r6));
        r0 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x025f, code lost:
    
        if (r0 >= r5.length()) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0261, code lost:
    
        r8 = r5.indexOf(47, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0265, code lost:
    
        if (r8 < 0) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0267, code lost:
    
        r10 = r5.substring(r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x026f, code lost:
    
        if (r8 >= r5.length()) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0271, code lost:
    
        r0 = r8 + 1;
        r6 = r5.indexOf(47, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0277, code lost:
    
        if (r6 < 0) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0279, code lost:
    
        r0 = r5.substring(r0, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x027e, code lost:
    
        r6 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0280, code lost:
    
        r10 = r5.substring(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0284, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0285, code lost:
    
        if (r10 != null) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0287, code lost:
    
        if (r11 != false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x028a, code lost:
    
        if (r0 != null) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x028c, code lost:
    
        r0 = new java.lang.StringBuilder();
        r0.append(r10);
        r0.append(com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0298, code lost:
    
        r5 = r10 + "://" + r0 + r5.substring(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02b4, code lost:
    
        r5 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x02b9, code lost:
    
        if (r5.length() <= 0) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x02bb, code lost:
    
        r3.setData(android.net.Uri.parse(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02c3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x02cd, code lost:
    
        throw new java.net.URISyntaxException(r17, r0.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02ce, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent a(java.lang.String r17) throws java.net.URISyntaxException {
        /*
            Method dump skipped, instruction units count: 728
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.g.d.a(java.lang.String):android.content.Intent");
    }

    public static Pair<ServiceInfo, Class> a(Context context, Class cls) {
        try {
            if (h == null) {
                h = context.getPackageManager().getPackageInfo(context.getPackageName(), Build.VERSION.SDK_INT >= 24 ? 516 : 4);
            }
            ServiceInfo[] serviceInfoArr = h.services;
            if (serviceInfoArr != null && serviceInfoArr.length > 0) {
                int length = serviceInfoArr.length;
                for (int i = 0; i < length; i++) {
                    ServiceInfo serviceInfo = serviceInfoArr[i];
                    try {
                        Class<?> cls2 = Class.forName(serviceInfo.name);
                        if (cls2 != cls && cls.isAssignableFrom(cls2)) {
                            com.igexin.c.a.c.a.b("GT", cls.getSimpleName() + " child is " + cls2.getSimpleName());
                            return Pair.create(serviceInfo, cls2);
                        }
                    } catch (Throwable th) {
                        com.igexin.c.a.c.a.a(th);
                    }
                }
            }
        } catch (Throwable th2) {
            com.igexin.c.a.c.a.a(th2);
            com.igexin.c.a.c.a.c.a().a(" findGtImplClassInManifest error = " + th2.toString());
        }
        return Pair.create(null, null);
    }

    public static String a(Context context) {
        try {
            Intent launchIntentForPackage = context.getApplicationContext().getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            return (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) ? "" : launchIntentForPackage.getComponent().getClassName();
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return "";
        }
    }

    public static String a(ApplicationInfo applicationInfo) {
        try {
            String string = applicationInfo.metaData.getString(com.igexin.push.core.b.f3337a);
            if (TextUtils.isEmpty(string)) {
                string = applicationInfo.packageName;
            }
            Class<?> cls = Class.forName(string + ".BuildConfig");
            return (String) cls.getField("GETUI_APPID").get(cls);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a("get cf error|" + e2.toString(), new Object[0]);
            return "";
        }
    }

    public static HashMap<String, Object> a() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - f3582a < 2000) {
                return b;
            }
            b.put("isPause", Boolean.FALSE);
            b.put("isTranslucent", Boolean.FALSE);
            f3582a = jCurrentTimeMillis;
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Activity activity = null;
            Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = Build.VERSION.SDK_INT < 19 ? (HashMap) declaredField.get(objInvoke) : (ArrayMap) declaredField.get(objInvoke);
            if (map.size() <= 0) {
                return b;
            }
            Boolean boolValueOf = null;
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("activity");
                declaredField2.setAccessible(true);
                Activity activity2 = (Activity) declaredField2.get(obj);
                Field declaredField3 = cls2.getDeclaredField("paused");
                declaredField3.setAccessible(true);
                boolean z = declaredField3.getBoolean(obj);
                boolValueOf = Boolean.valueOf(boolValueOf == null ? z : boolValueOf.booleanValue() && z);
                if (!z) {
                    activity = activity2;
                }
            }
            boolean z2 = activity != null ? activity.getTheme().obtainStyledAttributes(new int[]{R.attr.windowIsTranslucent}).getBoolean(0, false) : false;
            b.put("isPause", Boolean.valueOf(Boolean.TRUE.equals(boolValueOf)));
            b.put("isTranslucent", Boolean.valueOf(z2));
            return b;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return b;
        }
    }

    public static synchronized boolean a(int i, boolean z) {
        try {
            if (com.igexin.push.core.e.l == null) {
                return false;
            }
            String str = com.igexin.push.core.e.G;
            if (AssistUtils.BRAND_HW.equalsIgnoreCase(str) || AssistUtils.BRAND_HON.equalsIgnoreCase(str)) {
                int iIntValue = ((Integer) o.b(com.igexin.push.core.e.l, o.h, 0)).intValue();
                if (!z) {
                    i += iIntValue;
                }
                o.a(com.igexin.push.core.e.l, o.h, Integer.valueOf(i));
                Bundle bundle = new Bundle();
                bundle.putString("package", com.igexin.push.core.e.g);
                bundle.putString("class", a(com.igexin.push.core.e.l));
                bundle.putInt("badgenumber", i);
                Uri uri = Uri.parse("content://com.huawei.android.launcher.settings/badge/");
                Uri uri2 = Uri.parse("content://com.hihonor.android.launcher.settings/badge/");
                if (TextUtils.isEmpty(com.igexin.push.core.e.l.getContentResolver().getType(uri))) {
                    uri = uri2;
                }
                com.igexin.push.core.e.l.getContentResolver().call(uri, "change_badge", (String) null, bundle);
                return true;
            }
        } finally {
        }
        return false;
    }

    public static boolean a(String... strArr) {
        for (int i = 0; i < 5; i++) {
            if (TextUtils.isEmpty(strArr[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean b() {
        try {
            if (f != null) {
                return f.booleanValue();
            }
            Boolean boolValueOf = Boolean.valueOf((!"Xiaomi".equalsIgnoreCase(com.igexin.push.core.e.G) && TextUtils.isEmpty(c("ro.miui.ui.version.name")) && TextUtils.isEmpty(c("ro.miui.ui.version.code"))) ? false : true);
            f = boolValueOf;
            return boolValueOf.booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean b(int i, boolean z) {
        try {
            if (com.igexin.push.core.e.l == null || !AssistUtils.BRAND_VIVO.equalsIgnoreCase(com.igexin.push.core.e.G)) {
                return false;
            }
            Intent intent = new Intent();
            intent.setAction("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", com.igexin.push.core.e.l.getPackageName());
            Intent launchIntentForPackage = com.igexin.push.core.e.l.getPackageManager().getLaunchIntentForPackage(com.igexin.push.core.e.l.getPackageName());
            if (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) {
                return false;
            }
            int iIntValue = ((Integer) o.b(com.igexin.push.core.e.l, o.i, 0)).intValue();
            if (!z) {
                i += iIntValue;
            }
            o.a(com.igexin.push.core.e.l, o.i, Integer.valueOf(i));
            intent.putExtra(PushClientConstants.TAG_CLASS_NAME, launchIntentForPackage.getComponent().getClassName());
            intent.putExtra("notificationNum", i);
            intent.addFlags(16777216);
            com.igexin.push.core.e.l.sendBroadcast(intent);
            return true;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            if (TextUtils.isEmpty(g)) {
                g = c();
                com.igexin.c.a.c.a.b("GT", " gtcVersion = " + g);
            }
            String[] strArrSplit = g.split("\\.");
            String[] strArrSplit2 = str.split("\\.");
            if (strArrSplit.length == 4 && strArrSplit2.length == 4) {
                for (int i = 0; i < 3; i++) {
                    int i2 = Integer.parseInt(strArrSplit2[i]);
                    int i3 = Integer.parseInt(strArrSplit[i]);
                    if (i3 != i2) {
                        return i3 < i2;
                    }
                }
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return false;
    }

    public static String c() {
        try {
            Field declaredField = Class.forName("com.getui.gtc.BuildConfig").getDeclaredField("VERSION_NAME");
            declaredField.setAccessible(true);
            return ((String) declaredField.get(null)).substring(4);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return "";
        }
    }

    public static String c(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ".concat(String.valueOf(str))).getInputStream()), 1024);
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            String line = bufferedReader.readLine();
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            return line;
        } catch (Exception unused2) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    com.igexin.c.a.c.a.a(e3);
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    com.igexin.c.a.c.a.a(e4);
                }
            }
            throw th;
        }
    }

    public static boolean c(int i, boolean z) {
        try {
            if (com.igexin.push.core.e.l == null || !AssistUtils.BRAND_OPPO.equalsIgnoreCase(com.igexin.push.core.e.G)) {
                return false;
            }
            int iIntValue = ((Integer) o.b(com.igexin.push.core.e.l, o.j, 0)).intValue();
            if (!z) {
                i += iIntValue;
            }
            o.a(com.igexin.push.core.e.l, o.j, Integer.valueOf(i));
            Intent intent = new Intent("com.oppo.unsettledevent");
            intent.putExtra("packageName", com.igexin.push.core.e.l.getPackageName());
            intent.putExtra("number", i);
            intent.putExtra("upgradeNumber", i);
            List<ResolveInfo> listQueryBroadcastReceivers = com.igexin.push.core.e.l.getPackageManager().queryBroadcastReceivers(intent, 0);
            if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
                com.igexin.push.core.e.l.sendBroadcast(intent);
                return true;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("app_badge_count", i);
            com.igexin.push.core.e.l.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
            return true;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return false;
        }
    }

    public static Intent d(String str) throws URISyntaxException {
        boolean z;
        int iLastIndexOf = str.lastIndexOf(35);
        if (iLastIndexOf < 0) {
            return new Intent("android.intent.action.VIEW", Uri.parse(str));
        }
        String str2 = null;
        int i = iLastIndexOf + 1;
        boolean z2 = true;
        if (str.regionMatches(i, "action(", 0, 7)) {
            int i2 = i + 7;
            int iIndexOf = str.indexOf(41, i2);
            String strSubstring = str.substring(i2, iIndexOf);
            z = true;
            i = iIndexOf + 1;
            str2 = strSubstring;
        } else {
            z = false;
        }
        Intent intent = new Intent(str2);
        if (str.regionMatches(i, "categories(", 0, 11)) {
            int i3 = i + 11;
            int iIndexOf2 = str.indexOf(41, i3);
            while (i3 < iIndexOf2) {
                int iIndexOf3 = str.indexOf(33, i3);
                if (iIndexOf3 < 0 || iIndexOf3 > iIndexOf2) {
                    iIndexOf3 = iIndexOf2;
                }
                if (i3 < iIndexOf3) {
                    intent.addCategory(str.substring(i3, iIndexOf3));
                }
                i3 = iIndexOf3 + 1;
            }
            i = iIndexOf2 + 1;
            z = true;
        }
        if (str.regionMatches(i, "type(", 0, 5)) {
            int i4 = i + 5;
            int iIndexOf4 = str.indexOf(41, i4);
            intent.setType(str.substring(i4, iIndexOf4));
            i = iIndexOf4 + 1;
            z = true;
        }
        if (str.regionMatches(i, "launchFlags(", 0, 12)) {
            int i5 = i + 12;
            int iIndexOf5 = str.indexOf(41, i5);
            intent.setFlags(Integer.decode(str.substring(i5, iIndexOf5)).intValue());
            int i6 = Build.VERSION.SDK_INT >= 19 ? 67 : 3;
            if (Build.VERSION.SDK_INT >= 21) {
                i6 |= 128;
            }
            intent.setFlags((~i6) & intent.getFlags());
            i = iIndexOf5 + 1;
            z = true;
        }
        if (str.regionMatches(i, "component(", 0, 10)) {
            int i7 = i + 10;
            int iIndexOf6 = str.indexOf(41, i7);
            int iIndexOf7 = str.indexOf(33, i7);
            if (iIndexOf7 >= 0 && iIndexOf7 < iIndexOf6) {
                intent.setComponent(new ComponentName(str.substring(i7, iIndexOf7), str.substring(iIndexOf7 + 1, iIndexOf6)));
            }
            i = iIndexOf6 + 1;
            z = true;
        }
        if (str.regionMatches(i, "extras(", 0, 7)) {
            int i8 = i + 7;
            int iIndexOf8 = str.indexOf(41, i8);
            int i9 = -1;
            if (iIndexOf8 == -1) {
                throw new URISyntaxException(str, "EXTRA missing trailing ')'", i8);
            }
            if (intent.getExtras() == null) {
                intent.putExtras(new Bundle());
            }
            Bundle extras = intent.getExtras();
            while (i8 < iIndexOf8) {
                int iIndexOf9 = str.indexOf(61, i8);
                int i10 = i8 + 1;
                if (iIndexOf9 <= i10 || i8 >= iIndexOf8) {
                    throw new URISyntaxException(str, "EXTRA missing '='", i8);
                }
                char cCharAt = str.charAt(i8);
                String strSubstring2 = str.substring(i10, iIndexOf9);
                int i11 = iIndexOf9 + 1;
                int iIndexOf10 = str.indexOf(33, i11);
                if (iIndexOf10 == i9 || iIndexOf10 >= iIndexOf8) {
                    iIndexOf10 = iIndexOf8;
                }
                if (i11 >= iIndexOf10) {
                    throw new URISyntaxException(str, "EXTRA missing '!'", i11);
                }
                String strSubstring3 = str.substring(i11, iIndexOf10);
                if (cCharAt == 'B') {
                    extras.putBoolean(strSubstring2, Boolean.parseBoolean(strSubstring3));
                } else if (cCharAt == 'S') {
                    extras.putString(strSubstring2, Uri.decode(strSubstring3));
                } else if (cCharAt == 'f') {
                    extras.putFloat(strSubstring2, Float.parseFloat(strSubstring3));
                } else if (cCharAt == 'i') {
                    extras.putInt(strSubstring2, Integer.parseInt(strSubstring3));
                } else if (cCharAt == 'l') {
                    extras.putLong(strSubstring2, Long.parseLong(strSubstring3));
                } else {
                    if (cCharAt != 's') {
                        switch (cCharAt) {
                            case 'b':
                                extras.putByte(strSubstring2, Byte.parseByte(strSubstring3));
                                break;
                            case 'c':
                                extras.putChar(strSubstring2, Uri.decode(strSubstring3).charAt(0));
                                break;
                            case 'd':
                                try {
                                    extras.putDouble(strSubstring2, Double.parseDouble(strSubstring3));
                                } catch (NumberFormatException unused) {
                                    throw new URISyntaxException(str, "EXTRA value can't be parsed", iIndexOf10);
                                }
                                break;
                            default:
                                throw new URISyntaxException(str, "EXTRA has unknown type", iIndexOf10);
                        }
                        throw new URISyntaxException(str, "EXTRA value can't be parsed", iIndexOf10);
                    }
                    extras.putShort(strSubstring2, Short.parseShort(strSubstring3));
                }
                char cCharAt2 = str.charAt(iIndexOf10);
                if (cCharAt2 == ')') {
                    intent.putExtras(extras);
                } else {
                    if (cCharAt2 != '!') {
                        throw new URISyntaxException(str, "EXTRA missing '!'", iIndexOf10);
                    }
                    i8 = iIndexOf10 + 1;
                    i9 = -1;
                }
            }
            intent.putExtras(extras);
        } else {
            z2 = z;
        }
        intent.setData(z2 ? Uri.parse(str.substring(0, iLastIndexOf)) : Uri.parse(str));
        if (intent.getAction() != null) {
            return intent;
        }
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }
}
