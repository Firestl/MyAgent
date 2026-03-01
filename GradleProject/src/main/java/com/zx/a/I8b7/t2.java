package com.zx.a.I8b7;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.bm;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.zx.a.I8b7.s;
import com.zx.a.I8b7.t1;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class t2 {
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static String D = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f6286a = null;
    public static String b = "";
    public static String c = "";
    public static String d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f6287e = null;
    public static String f = "";
    public static String g = "";
    public static String h = null;
    public static String i = null;
    public static String j = "{}";
    public static String k = "{}";
    public static String l = "";
    public static int m = 0;
    public static String n = "ANDROID-V3";
    public static boolean o;
    public static SecretKey u;
    public static IvParameterSpec v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;
    public static HashMap<String, String> p = new HashMap<>();
    public static int q = 1;
    public static int r = 1;
    public static int s = -1;
    public static long t = 0;
    public static JSONObject E = new JSONObject();
    public static volatile boolean F = false;
    public static final Set<String> G = Collections.newSetFromMap(new ConcurrentHashMap());
    public static final Set<String> H = Collections.newSetFromMap(new ConcurrentHashMap());
    public static Bundle I = null;

    public static void a(Context context) throws Exception {
        Context applicationContext = context.getApplicationContext();
        f6286a = applicationContext;
        g = applicationContext.getPackageName();
        h1.c(f6286a);
        f = h1.a(f6286a);
        StringBuilder sbA = m2.a("initAppId: ");
        sbA.append(f);
        y1.a(sbA.toString());
        b(f6286a);
        if (TextUtils.isEmpty(h)) {
            b();
        } else {
            String strA = j.a(d3.b() + Build.MODEL, "SHA256");
            String[] strArrSplit = h.split("-");
            if (strArrSplit.length < 2) {
                StringBuilder sbA2 = m2.a("ZXID 检测到老版本LID:");
                sbA2.append(h);
                l.a(sbA2.toString());
                h += "-" + strA;
                t1 t1Var = t1.a.f6285a;
                b3 b3Var = t1Var.f6284a;
                String str = h;
                b3Var.getClass();
                if (!TextUtils.equals(str, h)) {
                    h = str;
                    t1Var.f6284a.a(0, str, true);
                }
                StringBuilder sbA3 = m2.a("ZXID 兼容老版本LID后重新生成LID:");
                sbA3.append(h);
                l.a(sbA3.toString());
            } else if (TextUtils.equals(strA, strArrSplit[1])) {
                l.a("ZXID LID校验通过!");
            } else {
                b3 b3Var2 = t1.a.f6285a.f6284a;
                if (b3Var2.b == null) {
                    b3Var2.b = b3Var2.d();
                }
                try {
                    SQLiteDatabase sQLiteDatabase = b3Var2.b;
                    StringBuilder sb = new StringBuilder();
                    sb.append("key in(");
                    sb.append("0,1,3,4,6,11,12,15,21,22,23,24,25,26,19,13,14");
                    sb.append(")");
                    sQLiteDatabase.delete("zx_table", sb.toString(), null);
                    h = "";
                    i = "";
                    j = "";
                    k = "";
                    m = 0;
                    n = "ANDROID-V3";
                    o = false;
                    w = "";
                    x = "";
                    y = "";
                    C = "";
                    q = 1;
                    z = "";
                    y1.a("ZXID清理数据成功");
                } catch (Exception e2) {
                    StringBuilder sbA4 = m2.a("清理本地数据error:");
                    sbA4.append(e2.getMessage());
                    y1.b(sbA4.toString());
                }
                b();
                l.a("ZXID LID校验不通过");
            }
        }
        w.i();
    }

    public static void b(Context context) {
        f6286a = context.getApplicationContext();
        b3 b3Var = t1.a.f6285a.f6284a;
        a(b3Var);
        b3Var.getClass();
        Cursor cursorQuery = null;
        try {
            if (b3Var.b == null) {
                b3Var.b = b3Var.b();
            }
            try {
                cursorQuery = b3Var.b().query("zx_table", new String[]{"key", "value"}, null, null, null, null, null);
            } catch (Exception e2) {
                y1.b("query ex = " + e2.toString());
            }
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("key"));
                    try {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                        if ((!TextUtils.isEmpty(string) && i2 == 11) || i2 == 12 || i2 == 0 || i2 == 1 || i2 == 16 || i2 == 30 || i2 == 28 || i2 == 15 || i2 == 21 || i2 == 22 || i2 == 23 || i2 == 18 || i2 == 13 || i2 == 19) {
                            string = new String(j.a("AES/CBC/PKCS5Padding", u, v, Base64.decode(string, 0)), StandardCharsets.UTF_8);
                        }
                        if (i2 == 0) {
                            h = string;
                            y1.a("read lid = " + h);
                        } else if (i2 == 1) {
                            i = string;
                            y1.a("read zid = " + i);
                        } else if (i2 == 3) {
                            m = Integer.parseInt(string);
                            y1.a("read syncId = " + m);
                        } else if (i2 == 4) {
                            n = string;
                            y1.a("read configVersion = " + n);
                        } else if (i2 == 6) {
                            o = Boolean.parseBoolean(string);
                            y1.a("read isInitialized = " + o);
                        } else if (i2 == 7) {
                            s = Integer.parseInt(string);
                            y1.a("read permission = " + s);
                        } else if (i2 == 8) {
                            t = Long.parseLong(string);
                            y1.a("read lastRequestTime = " + t);
                        } else if (i2 == 28) {
                            l = string;
                            y1.a("read lastReportExtList = " + l);
                        } else if (i2 != 30) {
                            switch (i2) {
                                case 11:
                                    w = string;
                                    y1.a("read fieldConfigJSON = " + w);
                                    break;
                                case 12:
                                    x = string;
                                    y1.a("read reportConfigJSON = " + x);
                                    break;
                                case 13:
                                    C = string;
                                    y1.a("read localLv1JSON = " + C);
                                    break;
                                case 14:
                                    q = Integer.parseInt(string);
                                    break;
                                case 15:
                                    y = string;
                                    y1.a("read cryptoConfigJSON = " + y);
                                    break;
                                case 16:
                                    j = string;
                                    break;
                                default:
                                    switch (i2) {
                                        case 18:
                                            E = new JSONObject(string);
                                            break;
                                        case 19:
                                            B = string;
                                            y1.a("read invokeConfigJSON = " + B);
                                            c();
                                            break;
                                        case 20:
                                            r = Integer.parseInt(string);
                                            y1.a("read allowPermissionDialog = " + r);
                                            break;
                                        case 21:
                                            z = string;
                                            y1.a("read appConfigJSON = " + z);
                                            break;
                                        case 22:
                                            A = string;
                                            y1.a("read commonConfigJSON = " + A);
                                            break;
                                        case 23:
                                            y1.a("read events = " + string);
                                            s.b.f6278a.a(string);
                                            break;
                                    }
                                    break;
                            }
                        } else {
                            k = string;
                        }
                    } catch (Throwable th) {
                        y1.b("ZXTable解密失败,Key:" + i2 + ",error:" + th.getMessage());
                    }
                }
            }
        } catch (Throwable th2) {
            try {
                y1.a(th2);
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
        if (cursorQuery != null) {
        }
        F = true;
    }

    public static void c() {
        try {
            if (B != null) {
                JSONObject jSONObject = new JSONObject(B);
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(UMModuleRegister.INNER);
                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("external");
                if (jSONArrayOptJSONArray != null) {
                    Set<String> set = G;
                    set.clear();
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        set.add(jSONArrayOptJSONArray.getString(i2));
                    }
                }
                if (jSONArrayOptJSONArray2 != null) {
                    Set<String> set2 = H;
                    set2.clear();
                    for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                        set2.add(jSONArrayOptJSONArray2.getString(i3));
                    }
                }
            }
        } catch (JSONException e2) {
            y1.a(e2);
        }
    }

    public static void b() {
        String str = UUID.randomUUID().toString().replaceAll("-", "") + "-" + j.a(d3.b() + Build.MODEL, "SHA256");
        t1 t1Var = t1.a.f6285a;
        t1Var.f6284a.getClass();
        if (!TextUtils.equals(str, h)) {
            h = str;
            t1Var.f6284a.a(0, str, true);
        }
        l.a("ZXID 生成LID:" + str);
    }

    public static void a(b3 b3Var) {
        if (v == null) {
            IvParameterSpec ivParameterSpecG = b3Var.g();
            v = ivParameterSpecG;
            if (ivParameterSpecG == null) {
                byte[] bArrGenerateSeed = new SecureRandom().generateSeed(16);
                String str = new String(Base64.encode(bArrGenerateSeed, 0), StandardCharsets.UTF_8);
                b3Var.a(10, str + "", false);
                y1.a("ZXID saveIvParameter ivStr:" + str);
                v = new IvParameterSpec(bArrGenerateSeed);
            }
        }
        if (u == null) {
            SecretKey secretKeyI = b3Var.i();
            u = secretKeyI;
            if (secretKeyI == null) {
                try {
                    SecureRandom secureRandom = j.f6232a;
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                    keyGenerator.init(128);
                    SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
                    u = secretKeyGenerateKey;
                    b3Var.a(secretKeyGenerateKey.getEncoded());
                } catch (NoSuchAlgorithmException e2) {
                    y1.a(e2);
                    e2.printStackTrace();
                }
            }
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String[] strArrSplit = str.split("-");
        return strArrSplit.length == 2 ? strArrSplit[0] : str;
    }

    public static String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(bm.al, i);
            jSONObject.put("ext", j);
        } catch (JSONException e2) {
            y1.a(e2);
        }
        return jSONObject.toString();
    }
}
