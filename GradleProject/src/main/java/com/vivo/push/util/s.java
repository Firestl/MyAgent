package com.vivo.push.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: PushPackageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Boolean f5641a;
    public static String b;

    public static com.vivo.push.model.b a(Context context) {
        com.vivo.push.model.b bVarF;
        com.vivo.push.model.b bVarF2;
        Context applicationContext = ContextDelegate.getContext(context).getApplicationContext();
        com.vivo.push.model.b bVarD = d(applicationContext);
        if (bVarD != null) {
            o.d("PushPackageUtils", "get system push info :".concat(String.valueOf(bVarD)));
            return bVarD;
        }
        List<String> listE = e(applicationContext);
        com.vivo.push.model.b bVarF3 = f(applicationContext, applicationContext.getPackageName());
        if (listE.size() <= 0) {
            if (bVarF3 != null && bVarF3.d()) {
                bVarD = bVarF3;
            }
            o.a("PushPackageUtils", "findAllPushPackages error: find no package!");
        } else {
            com.vivo.push.model.b bVar = null;
            String strA = y.b(applicationContext).a("com.vivo.push.cur_pkg", null);
            if (TextUtils.isEmpty(strA) || !a(applicationContext, strA, "com.vivo.pushservice.action.METHOD") || (bVarF = f(applicationContext, strA)) == null || !bVarF.d()) {
                bVarF = null;
            }
            if (bVarF3 == null || !bVarF3.d()) {
                bVarF3 = null;
            }
            if (bVarF == null) {
                bVarF = null;
            }
            if (bVarF3 == null || (bVarF != null && (!bVarF3.c() ? !(bVarF.c() || bVarF3.b() > bVarF.b()) : !(bVarF.c() && bVarF3.b() > bVarF.b())))) {
                bVarF3 = bVarF;
            }
            HashMap map = new HashMap();
            if (bVarF3 == null) {
                bVarF3 = null;
            } else if (bVarF3.c()) {
                bVar = bVarF3;
                bVarF3 = null;
            }
            int size = listE.size();
            for (int i = 0; i < size; i++) {
                String str = listE.get(i);
                if (!TextUtils.isEmpty(str) && (bVarF2 = f(applicationContext, str)) != null) {
                    map.put(str, bVarF2);
                    if (bVarF2.d()) {
                        if (bVarF2.c()) {
                            if (bVar == null || bVarF2.b() > bVar.b()) {
                                bVar = bVarF2;
                            }
                        } else if (bVarF3 == null || bVarF2.b() > bVarF3.b()) {
                            bVarF3 = bVarF2;
                        }
                    }
                }
            }
            if (bVarF3 != null) {
                bVarD = bVarF3;
            } else {
                o.d("PushPackageUtils", "findSuitablePushPackage, all push app in balck list.");
                bVarD = bVar;
            }
        }
        if (bVarD == null) {
            o.b(applicationContext, "查找最优包为空!");
            o.d("PushPackageUtils", "finSuitablePushPackage is null");
        } else if (bVarD.c()) {
            o.a(applicationContext, "查找最优包为:" + bVarD.a() + "(" + bVarD.b() + ", Black)");
            o.d("PushPackageUtils", "finSuitablePushPackage" + bVarD.a() + "(" + bVarD.b() + ", Black)");
        } else {
            o.a(applicationContext, "查找最优包为:" + bVarD.a() + "(" + bVarD.b() + ")");
            o.d("PushPackageUtils", "finSuitablePushPackage" + bVarD.a() + "(" + bVarD.b() + ")");
        }
        return bVarD;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3 A[Catch: Exception -> 0x009d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x009d, blocks: (B:44:0x0099, B:58:0x00b3), top: B:70:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r10) {
        /*
            java.lang.String r0 = "name"
            java.lang.String r1 = "close"
            java.lang.String r2 = "PushPackageUtils"
            java.lang.String r3 = com.vivo.push.util.s.b
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L11
            java.lang.String r10 = com.vivo.push.util.s.b
            return r10
        L11:
            r3 = 0
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Laa
            android.net.Uri r5 = com.vivo.push.p.f5622a     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Laa
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r10 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Laa
            if (r10 != 0) goto L36
            java.lang.String r0 = "cursor is null"
            com.vivo.push.util.o.a(r2, r0)     // Catch: java.lang.Exception -> L32 java.lang.Throwable -> La2
            if (r10 == 0) goto L31
            r10.close()     // Catch: java.lang.Exception -> L2d
            goto L31
        L2d:
            r10 = move-exception
            com.vivo.push.util.o.a(r2, r1, r10)
        L31:
            return r3
        L32:
            r0 = move-exception
            r5 = r3
            goto La6
        L36:
            r4 = 0
            r5 = r3
        L38:
            boolean r6 = r10.moveToNext()     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            if (r6 == 0) goto L77
            java.lang.String r6 = "pushPkgName"
            int r7 = r10.getColumnIndex(r0)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            java.lang.String r7 = r10.getString(r7)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            java.lang.String r7 = "value"
            if (r6 == 0) goto L5a
            int r6 = r10.getColumnIndex(r7)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            java.lang.String r5 = r10.getString(r6)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            goto L38
        L5a:
            java.lang.String r6 = "pushEnable"
            int r8 = r10.getColumnIndex(r0)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            java.lang.String r8 = r10.getString(r8)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            if (r6 == 0) goto L38
            int r4 = r10.getColumnIndex(r7)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            java.lang.String r4 = r10.getString(r4)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            boolean r4 = java.lang.Boolean.parseBoolean(r4)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            goto L38
        L77:
            com.vivo.push.util.s.b = r5     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La5
            if (r0 == 0) goto L8a
            if (r10 == 0) goto L89
            r10.close()     // Catch: java.lang.Exception -> L85
            goto L89
        L85:
            r10 = move-exception
            com.vivo.push.util.o.a(r2, r1, r10)
        L89:
            return r3
        L8a:
            if (r4 != 0) goto L97
            if (r10 == 0) goto L96
            r10.close()     // Catch: java.lang.Exception -> L92
            goto L96
        L92:
            r10 = move-exception
            com.vivo.push.util.o.a(r2, r1, r10)
        L96:
            return r3
        L97:
            if (r10 == 0) goto Lb6
            r10.close()     // Catch: java.lang.Exception -> L9d
            goto Lb6
        L9d:
            r10 = move-exception
            com.vivo.push.util.o.a(r2, r1, r10)
            goto Lb6
        La2:
            r0 = move-exception
            r3 = r10
            goto Lb7
        La5:
            r0 = move-exception
        La6:
            r3 = r10
            goto Lac
        La8:
            r0 = move-exception
            goto Lb7
        Laa:
            r0 = move-exception
            r5 = r3
        Lac:
            java.lang.String r10 = "getSystemPush"
            com.vivo.push.util.o.a(r2, r10, r0)     // Catch: java.lang.Throwable -> La8
            if (r3 == 0) goto Lb6
            r3.close()     // Catch: java.lang.Exception -> L9d
        Lb6:
            return r5
        Lb7:
            if (r3 == 0) goto Lc1
            r3.close()     // Catch: java.lang.Exception -> Lbd
            goto Lc1
        Lbd:
            r10 = move-exception
            com.vivo.push.util.o.a(r2, r1, r10)
        Lc1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.s.b(android.content.Context):java.lang.String");
    }

    public static boolean c(Context context, String str) {
        return a(context, str, "com.vivo.pushclient.action.RECEIVE");
    }

    public static com.vivo.push.model.b d(Context context) {
        String strB = b(context);
        ApplicationInfo applicationInfo = null;
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        com.vivo.push.model.b bVar = new com.vivo.push.model.b(strB);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(strB, 128);
            if (packageInfo != null) {
                bVar.a(packageInfo.versionCode);
                bVar.a(packageInfo.versionName);
                applicationInfo = packageInfo.applicationInfo;
            }
            if (applicationInfo != null) {
                bVar.a(z.a(context, strB));
            }
            bVar.a(a(context, bVar.b()));
            bVar.b(a(context, strB));
            return bVar;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            o.d("PushPackageUtils", "PackageManager NameNotFoundException is null");
            return null;
        }
    }

    public static boolean e(Context context, String str) {
        return a(context, str, "com.vivo.pushservice.action.METHOD");
    }

    public static com.vivo.push.model.b f(Context context, String str) {
        ApplicationInfo applicationInfo = null;
        if (!TextUtils.isEmpty(str)) {
            if (a(context, str, "com.vivo.pushservice.action.METHOD") || a(context, str, "com.vivo.pushservice.action.RECEIVE")) {
                com.vivo.push.model.b bVar = new com.vivo.push.model.b(str);
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
                    if (packageInfo != null) {
                        bVar.a(packageInfo.versionCode);
                        bVar.a(packageInfo.versionName);
                        applicationInfo = packageInfo.applicationInfo;
                    }
                    if (applicationInfo != null) {
                        bVar.a(z.a(context, str));
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    o.a("PushPackageUtils", "getPushPackageInfo exception: ", e2);
                }
                bVar.b(a(context, str));
                bVar.a(a(context, bVar.b()));
                return bVar;
            }
        }
        return null;
    }

    public static String g(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
                byte[] bArrDigest = MessageDigest.getInstance("SHA256").digest(signatureArr[0].toByteArray());
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b2 : bArrDigest) {
                    String upperCase = Integer.toHexString(b2 & 255).toUpperCase(Locale.US);
                    if (upperCase.length() == 1) {
                        stringBuffer.append("0");
                    }
                    stringBuffer.append(upperCase);
                }
                return stringBuffer.toString();
            } catch (Exception e2) {
                o.a("PushPackageUtils", e2);
            }
        }
        return null;
    }

    public static boolean c(Context context) {
        ProviderInfo providerInfoResolveContentProvider;
        Boolean bool = f5641a;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = null;
        if (context != null && !TextUtils.isEmpty("com.vivo.push.sdk.service.SystemPushConfig") && (providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.vivo.push.sdk.service.SystemPushConfig", 128)) != null) {
            str = providerInfoResolveContentProvider.packageName;
        }
        Boolean boolValueOf = Boolean.valueOf("BCC35D4D3606F154F0402AB7634E8490C0B244C2675C3C6238986987024F0C02".equals(g(context, str)));
        f5641a = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static List<String> e(Context context) {
        List<ResolveInfo> listQueryIntentServices;
        g.a("findAllCoreClientPush");
        ArrayList arrayList = new ArrayList();
        try {
            listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent("com.vivo.pushservice.action.PUSH_SERVICE"), 576);
        } catch (Exception unused) {
            listQueryIntentServices = null;
        }
        if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
            int size = listQueryIntentServices.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = listQueryIntentServices.get(i);
                if (resolveInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        if (arrayList.size() <= 0) {
            o.d("PushPackageUtils", "get all push packages is null");
        }
        return arrayList;
    }

    public static boolean d(Context context, String str) {
        return a(context, str, "com.vivo.pushservice.action.RECEIVE");
    }

    public static int b(Context context, String str) {
        int i = a(context, str, "com.vivo.pushservice.action.RECEIVE") ? 0 : -1;
        if (a(context, str, "com.vivo.pushclient.action.RECEIVE")) {
            return 1;
        }
        return i;
    }

    public static boolean a(Context context, String str) {
        ServiceInfo serviceInfo;
        if (!TextUtils.isEmpty(str) && context != null) {
            Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
            intent.setPackage(str);
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 576);
            if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
                int size = listQueryIntentServices.size();
                boolean z = false;
                for (int i = 0; i < size; i++) {
                    ResolveInfo resolveInfo = listQueryIntentServices.get(i);
                    if (resolveInfo != null && (serviceInfo = resolveInfo.serviceInfo) != null) {
                        String str2 = serviceInfo.name;
                        boolean z2 = serviceInfo.exported;
                        if ("com.vivo.push.sdk.service.PushService".equals(str2) && z2) {
                            boolean z3 = resolveInfo.serviceInfo.enabled;
                            int componentEnabledSetting = packageManager.getComponentEnabledSetting(new ComponentName(str, "com.vivo.push.sdk.service.PushService"));
                            z = componentEnabledSetting == 1 || (componentEnabledSetting == 0 && z3);
                        }
                    }
                }
                return z;
            }
            o.a("PushPackageUtils", "isEnablePush error: can not find push service.");
        }
        return false;
    }

    public static boolean a(Context context, long j) {
        com.vivo.push.cache.d dVarA = com.vivo.push.cache.b.a().a(context);
        if (dVarA != null) {
            return dVarA.isInBlackList(j);
        }
        return false;
    }

    public static boolean a(Context context, String str, String str2) {
        List<ResolveInfo> listQueryBroadcastReceivers;
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        } catch (Exception unused) {
            listQueryBroadcastReceivers = null;
        }
        return listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0;
    }
}
