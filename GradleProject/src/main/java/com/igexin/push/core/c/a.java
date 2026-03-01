package com.igexin.push.core.c;

import android.app.AppOpsManager;
import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.text.TextUtils;
import com.igexin.assist.util.AssistUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.config.d;
import com.igexin.push.core.b;
import com.igexin.push.core.b.w;
import com.igexin.push.core.d;
import com.igexin.push.core.e;
import com.igexin.push.core.e.f;
import com.igexin.push.g.c;
import com.igexin.push.g.k;
import com.igexin.push.g.n;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements com.igexin.push.core.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3378a = "BIDataManager";
    public static a b;

    public static int a(ApplicationInfo applicationInfo, AppOpsManager appOpsManager, PackageManager packageManager) {
        try {
            if (applicationInfo.packageName.equals(e.g)) {
                return c.b(e.l) ? 1 : 0;
            }
            if (Build.VERSION.SDK_INT < 31 || !d.ai) {
                return -1;
            }
            if (Build.VERSION.SDK_INT >= 33 && applicationInfo.targetSdkVersion >= 33) {
                try {
                    return packageManager.checkPermission("android.permission.POST_NOTIFICATIONS", applicationInfo.packageName) == 0 ? 1 : 0;
                } catch (Throwable unused) {
                    String[] strArr = k.a(applicationInfo.packageName, 4096).requestedPermissions;
                    if (strArr == null || !new HashSet(Arrays.asList(strArr)).contains("android.permission.POST_NOTIFICATIONS")) {
                        return 0;
                    }
                }
            }
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod(c.c, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(c.d).get(Integer.class)).intValue()), Integer.valueOf(applicationInfo.uid), applicationInfo.packageName)).intValue() != 0 ? 0 : 1;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return -1;
        }
    }

    public static long a(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", (Integer) 10);
        contentValues.put("data", str);
        contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
        return d.a.f3384a.i.a(b.af, contentValues);
    }

    public static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    public static void a(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || b.m.equals(str) || !com.igexin.push.config.d.R.booleanValue()) {
                return;
            }
            if ((System.currentTimeMillis() - e.aw) - (com.igexin.push.config.d.S * 1000) < 0) {
                com.igexin.c.a.c.a.b(f3378a, "type253 in Interval = " + com.igexin.push.config.d.S);
                return;
            }
            Boolean bool = com.igexin.push.config.d.R;
            long j = com.igexin.push.config.d.S;
            long j2 = com.igexin.push.config.d.S;
            com.igexin.c.a.c.a.a(f3378a, "start up id type253Enable = " + com.igexin.push.config.d.R + " ，type253Interval = " + com.igexin.push.config.d.S);
            String strM = n.m();
            String strH = n.h();
            String str3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()));
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append("|");
            sb.append(e.C);
            sb.append("|");
            sb.append(e.f3403a);
            sb.append("|");
            sb.append(str);
            sb.append("|");
            if (strH == null || b.m.equals(strH)) {
                strH = "";
            }
            sb.append(strH);
            sb.append("|");
            if (strM == null || b.m.equals(strM)) {
                strM = "";
            }
            sb.append(strM);
            sb.append("|");
            sb.append(str2);
            String string = sb.toString();
            SDKUrlConfig.getBiUploadServiceUrl();
            com.igexin.c.a.c.a.a("BIDataManager| upload253 = ".concat(String.valueOf(string)), new Object[0]);
            byte[] bytes = string.getBytes();
            f.a().d(System.currentTimeMillis());
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.f.a.a(new com.igexin.push.core.h.e(SDKUrlConfig.getBiUploadServiceUrl(), bytes, com.igexin.push.config.c.E)), false, true);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public static void a(ArrayList<String> arrayList) {
        d.a.f3384a.i.a(b.af, new String[]{"id"}, (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public static void b() {
        String str;
        if (!c.e()) {
            str = " upload type144 network false";
        } else if (com.igexin.push.config.d.V) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if ((jCurrentTimeMillis - e.ay) - (com.igexin.push.config.d.W * 1000) >= 0) {
                try {
                    String[] strArrB = com.igexin.assist.sdk.a.a().b();
                    StringBuilder sb = new StringBuilder();
                    sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    sb.append("|");
                    sb.append(e.A);
                    sb.append("|");
                    sb.append(e.f3403a);
                    sb.append("|");
                    sb.append(e.C);
                    sb.append("|");
                    sb.append(com.igexin.push.config.d.T ? n.q() : "");
                    sb.append("|");
                    sb.append(n.d());
                    sb.append("|");
                    sb.append(AssistUtils.getDeviceBrand().toLowerCase());
                    sb.append("|");
                    sb.append(strArrB[0]);
                    sb.append("|");
                    sb.append(strArrB[1]);
                    sb.append("|");
                    sb.append(n.n());
                    sb.append("|");
                    sb.append(n.o());
                    f.a().e(jCurrentTimeMillis);
                    com.igexin.c.a.c.a.b("UploadBITask", "upload type144 data = " + sb.toString());
                    com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.f.a.a(new com.igexin.push.core.h.e(SDKUrlConfig.getBiUploadServiceUrl(), sb.toString().getBytes(), 144)), false, true);
                    return;
                } catch (Throwable th) {
                    com.igexin.c.a.c.a.a(th);
                    return;
                }
            }
            str = "type144 in Interval = " + com.igexin.push.config.d.W;
        } else {
            str = " isUpload type144 Enable false";
        }
        com.igexin.c.a.c.a.b(f3378a, str);
    }

    public static void b(ArrayList<String> arrayList) {
        d.a.f3384a.i.a(b.af, new String[]{"rowid"}, (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0050 A[DONT_GENERATE, PHI: r1
  0x0050: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v3 android.database.Cursor) binds: [B:13:0x004e, B:9:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.igexin.push.core.b.c> c() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.igexin.push.core.d r2 = com.igexin.push.core.d.a.a()     // Catch: java.lang.Throwable -> L4a
            com.igexin.push.b.b r3 = r2.i     // Catch: java.lang.Throwable -> L4a
            java.lang.String r4 = "bidata"
            java.lang.String r2 = "type"
            java.lang.String[] r5 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L4a
            java.lang.String r2 = "10"
            java.lang.String[] r6 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L4a
            r7 = 0
            java.lang.String r8 = ""
            android.database.Cursor r1 = r3.a(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L4a
            if (r1 == 0) goto L47
        L23:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L4a
            if (r2 == 0) goto L47
            r2 = 0
            int r4 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L4a
            r2 = 1
            java.lang.String r5 = r1.getString(r2)     // Catch: java.lang.Throwable -> L4a
            r2 = 2
            int r6 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L4a
            r2 = 3
            long r7 = r1.getLong(r2)     // Catch: java.lang.Throwable -> L4a
            com.igexin.push.core.b.c r2 = new com.igexin.push.core.b.c     // Catch: java.lang.Throwable -> L4a
            r3 = r2
            r3.<init>(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L4a
            r0.add(r2)     // Catch: java.lang.Throwable -> L4a
            goto L23
        L47:
            if (r1 == 0) goto L53
            goto L50
        L4a:
            r2 = move-exception
            com.igexin.c.a.c.a.a(r2)     // Catch: java.lang.Throwable -> L54
            if (r1 == 0) goto L53
        L50:
            r1.close()
        L53:
            return r0
        L54:
            r0 = move-exception
            if (r1 == 0) goto L5a
            r1.close()
        L5a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.c.a.c():java.util.List");
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        a((List<w>) arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", "reportapplist");
            jSONObject.put("session_last", e.z);
            JSONArray jSONArray = new JSONArray();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("appid", arrayList.get(i).c);
                jSONObject2.put("name", arrayList.get(i).f3372a);
                jSONObject2.put("version", arrayList.get(i).b);
                jSONObject2.put(com.heytap.mcssdk.d.p, arrayList.get(i).d);
                jSONObject2.put("notificationEnabled", arrayList.get(i).f3373e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("applist", jSONArray);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.f.a.a(new com.igexin.push.core.h.a(SDKUrlConfig.getBiUploadServiceUrl(), jSONObject.toString().getBytes())), false, true);
        com.igexin.c.a.c.a.a("reportAL", new Object[0]);
        if (com.igexin.push.config.d.ak) {
            com.igexin.c.a.c.a.a("reportAL = " + jSONObject.toString(), new Object[0]);
        }
    }

    public static void e() {
        Cursor cursor = null;
        try {
            Cursor cursorA = d.a.f3384a.i.a(b.af, null, null, new String[]{"COUNT(*)"}, null);
            if (cursorA == null) {
                if (cursorA != null) {
                    cursorA.close();
                    return;
                }
                return;
            }
            cursorA.moveToNext();
            long j = cursorA.getLong(0);
            cursorA.close();
            long j2 = j - 200;
            if (j2 > 0) {
                d.a.f3384a.i.a(b.af, "id IN(SELECT id FROM bidata ORDER BY time ASC LIMIT " + j2 + ")");
                com.igexin.c.a.c.a.b(f3378a, "delete bidata " + j2 + " old expired data");
            }
            if (cursorA != null) {
                cursorA.close();
            }
        } catch (Throwable th) {
            try {
                com.igexin.c.a.c.a.a(th);
            } finally {
                if (0 != 0) {
                    cursor.close();
                }
            }
        }
    }

    @Override // com.igexin.push.core.e.a
    public final void a(SQLiteDatabase sQLiteDatabase) {
    }

    public final void a(List<w> list) {
        Comparator<w> comparator = new Comparator<w>() { // from class: com.igexin.push.core.c.a.1
            public static int a(w wVar, w wVar2) {
                if (wVar.c.equals(wVar2.c)) {
                    return 0;
                }
                return wVar.c.compareTo(wVar2.c);
            }

            @Override // java.util.Comparator
            public final /* synthetic */ int compare(w wVar, w wVar2) {
                w wVar3 = wVar;
                w wVar4 = wVar2;
                if (wVar3.c.equals(wVar4.c)) {
                    return 0;
                }
                return wVar3.c.compareTo(wVar4.c);
            }
        };
        AppOpsManager appOpsManager = (AppOpsManager) e.l.getSystemService("appops");
        PackageManager packageManager = e.l.getPackageManager();
        List<PackageInfo> listA = n.a();
        for (int i = 0; i < listA.size(); i++) {
            try {
                PackageInfo packageInfo = listA.get(i);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if ((applicationInfo.flags & 1) <= 0) {
                    w wVar = new w();
                    wVar.f3372a = applicationInfo.loadLabel(packageManager).toString();
                    wVar.c = applicationInfo.packageName;
                    wVar.b = String.valueOf(packageInfo.versionCode);
                    wVar.d = packageInfo.versionName;
                    wVar.f3373e = a(applicationInfo, appOpsManager, packageManager);
                    list.add(wVar);
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
        Collections.sort(list, comparator);
    }

    @Override // com.igexin.push.core.e.a
    public final void b(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.igexin.push.core.e.a
    public final void c(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            Cursor cursorA = d.a.f3384a.i.a(b.af, null, null, new String[]{"COUNT(*)"}, null);
            if (cursorA == null) {
                if (cursorA != null) {
                    cursorA.close();
                    return;
                }
                return;
            }
            cursorA.moveToNext();
            long j = cursorA.getLong(0);
            cursorA.close();
            long j2 = j - 200;
            if (j2 > 0) {
                d.a.f3384a.i.a(b.af, "id IN(SELECT id FROM bidata ORDER BY time ASC LIMIT " + j2 + ")");
                com.igexin.c.a.c.a.b(f3378a, "delete bidata " + j2 + " old expired data");
            }
            if (cursorA != null) {
                cursorA.close();
            }
        } catch (Throwable th) {
            try {
                com.igexin.c.a.c.a.a(th);
            } finally {
                if (0 != 0) {
                    cursor.close();
                }
            }
        }
    }
}
