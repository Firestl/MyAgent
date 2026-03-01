package com.getui.gtc.i.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.e.c;
import com.umeng.analytics.AnalyticsConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f2245a = new AtomicBoolean(false);

    public static File a() {
        return new File(CommonUtil.getExternalFilesDir(GtcProvider.context()), GtcProvider.context().getPackageName() + "-online.properties");
    }

    public static JSONObject a(String str) {
        JSONObject jSONObjectA;
        int i;
        boolean z;
        try {
            a.a("update file log config:".concat(String.valueOf(str)));
            jSONObjectA = c.a.f2213a.f2212a.a();
            i = 0;
            z = jSONObjectA != null && jSONObjectA.length() > 0;
        } catch (Throwable th) {
            a.b(th);
        }
        if (TextUtils.isEmpty(str)) {
            if (z) {
                a().delete();
                c.a.f2213a.f2212a.a((JSONObject) null);
                a.a("file log clear old config and properties");
            }
            return null;
        }
        if (z && jSONObjectA.optString("dycConfig").equals(str)) {
            a.a("file log same config");
            return jSONObjectA;
        }
        c.a.f2213a.f2212a.a((JSONObject) null);
        a().delete();
        a.a("file log clear old config and properties");
        String[] strArrSplit = str.split("\\|");
        if (strArrSplit.length < 8) {
            throw new IllegalStateException("file log dyc error");
        }
        JSONObject jSONObject = new JSONObject();
        String[] strArrSplit2 = strArrSplit[0].split(",");
        String str2 = com.getui.gtc.c.b.d;
        int length = strArrSplit2.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str3 = strArrSplit2[i2];
            if (!TextUtils.isEmpty(str3) && str3.equals(str2)) {
                jSONObject.put("gtcid", str2);
                break;
            }
            i2++;
        }
        String[] strArrSplit3 = strArrSplit[1].split(",");
        String strB = b(GtcProvider.context());
        int length2 = strArrSplit3.length;
        while (true) {
            if (i >= length2) {
                break;
            }
            String str4 = strArrSplit3[i];
            if (!TextUtils.isEmpty(str4) && str4.equals(strB)) {
                jSONObject.put("cid", strB);
                break;
            }
            i++;
        }
        if (!jSONObject.has("gtcid") && !jSONObject.has("cid")) {
            a.a("file upload not match cur user");
            return null;
        }
        long time = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(strArrSplit[2]).getTime();
        long j = Long.parseLong(strArrSplit[3]);
        if (j <= 0) {
            a.a("file upload interval=" + j + " not valid");
        }
        jSONObject.put("allowMobile", "1".equals(strArrSplit[6]));
        jSONObject.put(AnalyticsConfig.RTD_START_TIME, time);
        jSONObject.put("interval", j);
        jSONObject.put("suffixes", strArrSplit[4]);
        jSONObject.put("enableKeys", strArrSplit[5]);
        jSONObject.put("url", strArrSplit[7]);
        if (a(jSONObject)) {
            jSONObject.put("lastModified", a().lastModified());
        }
        jSONObject.put("dycConfig", str);
        c.a.f2213a.f2212a.a(jSONObject);
        a.a("save file log dyc to db: " + jSONObject.toString());
        return jSONObject;
    }

    public static void a(Map<String, String> map) {
        try {
            if (f2245a.getAndSet(true)) {
                return;
            }
            final JSONObject jSONObjectA = a(map != null ? map.get("sdk.gtc.fileLog.upload") : null);
            if (jSONObjectA != null && jSONObjectA.length() != 0) {
                final long jOptLong = jSONObjectA.optLong(AnalyticsConfig.RTD_START_TIME);
                String strOptString = jSONObjectA.optString("gtcid");
                String strOptString2 = jSONObjectA.optString("cid");
                final long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis >= jOptLong && jCurrentTimeMillis <= 604800000 + jOptLong) {
                    if ((!TextUtils.isEmpty(strOptString) && strOptString.equals(com.getui.gtc.c.b.d)) || (!TextUtils.isEmpty(strOptString2) && strOptString2.equals(b(GtcProvider.context())))) {
                        if (CommonUtil.isAppDebugEnable()) {
                            a.b("file log upload is debug, disallow");
                            return;
                        }
                        boolean zOptBoolean = jSONObjectA.optBoolean("allowMobile");
                        if (!a(zOptBoolean)) {
                            a.b("file log upload network is not allowed, allowMobile:".concat(String.valueOf(zOptBoolean)));
                            return;
                        }
                        File fileA = a();
                        long jOptLong2 = jSONObjectA.optLong("lastModified");
                        if (jOptLong2 == 0 || jOptLong2 != fileA.lastModified()) {
                            if (!a(jSONObjectA)) {
                                return;
                            }
                            jSONObjectA.put("lastModified", fileA.lastModified());
                            c.a.f2213a.f2212a.a(jSONObjectA);
                        }
                        if (jCurrentTimeMillis - jSONObjectA.optLong("reportTime") < jSONObjectA.optLong("interval") * 1000) {
                            a.c("file log report time not expired");
                            return;
                        }
                        Thread thread = new Thread(new Runnable() { // from class: com.getui.gtc.i.c.b.1
                            /* JADX WARN: Removed duplicated region for block: B:122:0x02fd A[Catch: all -> 0x0313, TryCatch #12 {all -> 0x0313, blocks: (B:119:0x02f4, B:120:0x02f7, B:122:0x02fd, B:123:0x0310, B:76:0x025d, B:77:0x0260, B:79:0x0266, B:100:0x02b0, B:102:0x02b6), top: B:164:0x02f4 }] */
                            /* JADX WARN: Removed duplicated region for block: B:164:0x02f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                            /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
                            @Override // java.lang.Runnable
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct code enable 'Show inconsistent code' option in preferences
                            */
                            public final void run() {
                                /*
                                    Method dump skipped, instruction units count: 872
                                    To view this dump change 'Code comments level' option to 'DEBUG'
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.i.c.b.AnonymousClass1.run():void");
                            }
                        });
                        thread.setName("GTC_fileLogUploadThread");
                        thread.start();
                        return;
                    }
                    a().delete();
                    a.a("file log upload gtcid or cid changed");
                    return;
                }
                a.a("current time is not in file log upload time range");
                a().delete();
                return;
            }
            a.a("file log upload no dyc config in db");
        } catch (Throwable th) {
            a.b(th);
        }
    }

    public static boolean a(JSONObject jSONObject) {
        FileOutputStream fileOutputStream = null;
        try {
            long jOptLong = jSONObject.optLong(AnalyticsConfig.RTD_START_TIME);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis >= jOptLong && jCurrentTimeMillis <= jOptLong + 604800000) {
                String[] strArrSplit = jSONObject.optString("enableKeys").split(",");
                StringBuilder sb = new StringBuilder();
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        sb.append(str);
                        sb.append('=');
                        sb.append("true\n");
                    }
                }
                if (sb.length() == 0) {
                    return false;
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(a(), false);
                try {
                    fileOutputStream2.write(sb.toString().getBytes());
                    fileOutputStream2.flush();
                    a.a("file log write enableKeys success.");
                    try {
                        fileOutputStream2.close();
                        return true;
                    } catch (Throwable th) {
                        a.b(th);
                        return true;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    try {
                        a.b(th);
                        return false;
                    } finally {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th3) {
                                a.b(th3);
                            }
                        }
                    }
                }
            }
            a.a("current time is not in file log upload time range");
            return false;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static boolean a(boolean z) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) GtcProvider.context().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            if (!z) {
                if (activeNetworkInfo.getType() != 1) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static File b(File file) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        GZIPOutputStream gZIPOutputStream;
        if (!file.exists()) {
            return null;
        }
        File file2 = new File(file + ".zip");
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    gZIPOutputStream = new GZIPOutputStream(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = null;
                }
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i != -1) {
                            gZIPOutputStream.write(bArr, 0, i);
                        } else {
                            try {
                                break;
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        }
                    }
                    gZIPOutputStream.close();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return file2;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        th.printStackTrace();
                        if (gZIPOutputStream != null) {
                            try {
                            } catch (Throwable th4) {
                                return null;
                            }
                        }
                        return null;
                    } finally {
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Throwable th42) {
                                th42.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                gZIPOutputStream = null;
            }
        } catch (Throwable th6) {
            th = th6;
            fileOutputStream = null;
            fileInputStream = null;
            gZIPOutputStream = null;
        }
    }

    public static String b(Context context) {
        String str = "";
        try {
            Class<?> cls = Class.forName("com.igexin.sdk.PushManager");
            Object objInvoke = cls.getDeclaredMethod("getClientid", Context.class).invoke(cls.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]), context);
            if (objInvoke != null) {
                str = (String) objInvoke;
            }
        } catch (Throwable th) {
            a.a("reflect cid", th);
        }
        a.a("reflect cid:".concat(String.valueOf(str)));
        return str;
    }
}
