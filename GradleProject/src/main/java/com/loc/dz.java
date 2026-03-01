package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.Md5Utils;
import java.util.ArrayList;
import java.util.Hashtable;
import org.json.JSONObject;

/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Hashtable<String, ArrayList<a>> f3762a = new Hashtable<>();
    public long i = 0;
    public boolean j = false;
    public String k = "2.0.201501131131".replace(Operators.DOT_STR, "");
    public String l = null;
    public boolean b = true;
    public long c = 0;
    public String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public dv f3763e = null;
    public String m = null;
    public long n = 0;
    public boolean f = true;
    public boolean g = true;
    public String h = String.valueOf(AMapLocationClientOption.GeoLanguage.DEFAULT);

    /* JADX INFO: compiled from: Cache.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ds f3764a = null;
        public String b = null;

        public final ds a() {
            return this.f3764a;
        }

        public final void a(ds dsVar) {
            this.f3764a = dsVar;
        }

        public final void a(String str) {
            this.b = TextUtils.isEmpty(str) ? null : str.replace("##", "#");
        }

        public final String b() {
            return this.b;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01a6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f4 A[Catch: all -> 0x0242, TryCatch #0 {all -> 0x0242, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001b, B:10:0x0023, B:12:0x002b, B:14:0x0039, B:92:0x01f7, B:94:0x0201, B:96:0x021b, B:98:0x0221, B:99:0x0225, B:101:0x022a, B:103:0x022e, B:105:0x0236, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01eb, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01de, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012a A[Catch: all -> 0x0242, LOOP:1: B:55:0x0122->B:58:0x012a, LOOP_END, TRY_ENTER, TryCatch #0 {all -> 0x0242, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001b, B:10:0x0023, B:12:0x002b, B:14:0x0039, B:92:0x01f7, B:94:0x0201, B:96:0x021b, B:98:0x0221, B:99:0x0225, B:101:0x022a, B:103:0x022e, B:105:0x0236, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01eb, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01de, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0143 A[Catch: all -> 0x0242, LOOP:2: B:60:0x013d->B:62:0x0143, LOOP_END, TryCatch #0 {all -> 0x0242, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001b, B:10:0x0023, B:12:0x002b, B:14:0x0039, B:92:0x01f7, B:94:0x0201, B:96:0x021b, B:98:0x0221, B:99:0x0225, B:101:0x022a, B:103:0x022e, B:105:0x0236, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01eb, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01de, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a8 A[Catch: all -> 0x0242, TryCatch #0 {all -> 0x0242, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001b, B:10:0x0023, B:12:0x002b, B:14:0x0039, B:92:0x01f7, B:94:0x0201, B:96:0x021b, B:98:0x0221, B:99:0x0225, B:101:0x022a, B:103:0x022e, B:105:0x0236, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01eb, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01de, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.loc.ds a(java.lang.String r27, java.lang.StringBuilder r28) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a(java.lang.String, java.lang.StringBuilder):com.loc.ds");
    }

    private String a(String str, StringBuilder sb, Context context) {
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.l == null) {
                this.l = dy.a(Md5Utils.ALGORITHM, k.c(context));
            }
            if (str.contains("&")) {
                str = str.substring(0, str.indexOf("&"));
            }
            String strSubstring = str.substring(str.lastIndexOf("#") + 1);
            if (strSubstring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!TextUtils.isEmpty(sb) && sb.indexOf(",access") != -1) {
                jSONObject.put("cgi", str.substring(0, str.length() - (strSubstring.length() + 9)));
                String[] strArrSplit = sb.toString().split(",access");
                jSONObject.put("mmac", strArrSplit[0].contains("#") ? strArrSplit[0].substring(strArrSplit[0].lastIndexOf("#") + 1) : strArrSplit[0]);
            }
            return o.b(dy.a(jSONObject.toString().getBytes("UTF-8"), this.l));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0280 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0285 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0244 A[EDGE_INSN: B:131:0x0244->B:82:0x0244 BREAK  A[LOOP:0: B:33:0x00b0->B:84:0x0254], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010b A[Catch: all -> 0x00a6, TRY_LEAVE, TryCatch #2 {all -> 0x00a6, blocks: (B:26:0x0099, B:36:0x00c8, B:38:0x00e2, B:39:0x00e6, B:43:0x00fb, B:45:0x010b, B:64:0x019a, B:66:0x01a0, B:68:0x01cc, B:69:0x01d9, B:70:0x01de, B:40:0x00ea, B:42:0x00f0, B:49:0x013b, B:50:0x014e, B:59:0x0182, B:54:0x0158), top: B:122:0x0099 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0182 A[Catch: all -> 0x00a6, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00a6, blocks: (B:26:0x0099, B:36:0x00c8, B:38:0x00e2, B:39:0x00e6, B:43:0x00fb, B:45:0x010b, B:64:0x019a, B:66:0x01a0, B:68:0x01cc, B:69:0x01d9, B:70:0x01de, B:40:0x00ea, B:42:0x00f0, B:49:0x013b, B:50:0x014e, B:59:0x0182, B:54:0x0158), top: B:122:0x0099 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0254 A[LOOP:0: B:33:0x00b0->B:84:0x0254, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.content.Context r20, java.lang.String r21) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 673
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a(android.content.Context, java.lang.String):void");
    }

    private void a(String str, AMapLocation aMapLocation, StringBuilder sb, Context context) throws Exception {
        if (context == null) {
            return;
        }
        if (this.l == null) {
            this.l = dy.a(Md5Utils.ALGORITHM, k.c(context));
        }
        String strA = a(str, sb, context);
        StringBuilder sb2 = new StringBuilder();
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
        try {
            sQLiteDatabaseOpenOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            sb2.append("CREATE TABLE IF NOT EXISTS hist");
            sb2.append(this.k);
            sb2.append(" (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);");
            sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb2.toString());
            sb2.delete(0, sb2.length());
            sb2.append("REPLACE INTO ");
            sb2.append("hist");
            sb2.append(this.k);
            sb2.append(" VALUES (?, ?, ?, ?)");
            Object[] objArr = new Object[4];
            objArr[0] = strA;
            byte[] bArrA = dy.a(sb.toString().getBytes("UTF-8"), this.l);
            objArr[1] = bArrA;
            objArr[2] = dy.a(aMapLocation.toStr().getBytes("UTF-8"), this.l);
            objArr[3] = Long.valueOf(aMapLocation.getTime());
            for (int i = 1; i < 3; i++) {
                objArr[i] = o.b((byte[]) objArr[i]);
            }
            sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb2.toString(), objArr);
            sb2.delete(0, sb2.length());
        } catch (Throwable th) {
            try {
                ej.a(th, "DB", "updateHist");
                sb2.delete(0, sb2.length());
                if (sQLiteDatabaseOpenOrCreateDatabase == null || !sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabaseOpenOrCreateDatabase.close();
            } finally {
                sb2.delete(0, sb2.length());
                if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                    sQLiteDatabaseOpenOrCreateDatabase.close();
                }
            }
        }
    }

    public static void a(String str, Hashtable<String, String> hashtable) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        hashtable.clear();
        for (String str2 : str.split("#")) {
            if (!TextUtils.isEmpty(str2) && !str2.contains("|")) {
                hashtable.put(str2, "");
            }
        }
    }

    public static double[] a(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            d2 += dArr[i3] * dArr[i3];
            d3 += dArr2[i3] * dArr2[i3];
            d += dArr[i3] * dArr2[i3];
            if (dArr2[i3] == 1.0d) {
                i2++;
                if (dArr[i3] == 1.0d) {
                    i++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d2) * Math.sqrt(d3));
        double d4 = i;
        dArr3[1] = (d4 * 1.0d) / ((double) i2);
        dArr3[2] = d4;
        for (int i4 = 0; i4 < 2; i4++) {
            if (dArr3[i4] > 1.0d) {
                dArr3[i4] = 1.0d;
            }
        }
        return dArr3;
    }

    private boolean b() {
        long jB = ep.b();
        long j = this.i;
        long j2 = jB - j;
        if (j == 0) {
            return false;
        }
        return this.f3762a.size() > 360 || j2 > 36000000;
    }

    private void c() {
        this.i = 0L;
        if (!this.f3762a.isEmpty()) {
            this.f3762a.clear();
        }
        this.j = false;
    }

    public final ds a(Context context, String str, StringBuilder sb, boolean z) {
        if (TextUtils.isEmpty(str) || !ei.e()) {
            return null;
        }
        String str2 = str + "&" + this.f + "&" + this.g + "&" + this.h;
        if (str2.contains("gps") || !ei.e() || sb == null) {
            return null;
        }
        if (b()) {
            c();
            return null;
        }
        if (z && !this.j) {
            try {
                String strA = a(str2, sb, context);
                c();
                a(context, strA);
            } catch (Throwable unused) {
            }
        }
        if (this.f3762a.isEmpty()) {
            return null;
        }
        return a(str2, sb);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.dw r17, boolean r18, com.loc.ds r19, com.loc.dx r20, java.lang.StringBuilder r21, java.lang.String r22, android.content.Context r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a(com.loc.dw, boolean, com.loc.ds, com.loc.dx, java.lang.StringBuilder, java.lang.String, android.content.Context, boolean):com.loc.ds");
    }

    public final void a() {
        this.c = 0L;
        this.d = null;
    }

    public final void a(Context context) {
        if (this.j) {
            return;
        }
        try {
            c();
            a(context, (String) null);
        } catch (Throwable th) {
            ej.a(th, "Cache", "loadDB");
        }
        this.j = true;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.g = aMapLocationClientOption.isNeedAddress();
        this.f = aMapLocationClientOption.isOffset();
        this.b = aMapLocationClientOption.isLocationCacheEnable();
        this.h = String.valueOf(aMapLocationClientOption.getGeoLanguage());
    }

    public final void a(dv dvVar) {
        this.f3763e = dvVar;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, StringBuilder sb, ds dsVar, Context context, boolean z) {
        try {
            if (ep.a(dsVar)) {
                String str2 = str + "&" + dsVar.isOffset() + "&" + dsVar.i() + "&" + dsVar.j();
                if (!((TextUtils.isEmpty(str2) || !ep.a(dsVar) || str2.startsWith("#")) ? false : str2.contains("network")) || dsVar.e().equals("mem") || dsVar.e().equals("file") || dsVar.e().equals("wifioff") || "-3".equals(dsVar.d())) {
                    return;
                }
                if (b()) {
                    c();
                }
                JSONObject jSONObjectF = dsVar.f();
                if (ep.a(jSONObjectF, "offpct")) {
                    jSONObjectF.remove("offpct");
                    dsVar.a(jSONObjectF);
                }
                if (str2.contains("wifi")) {
                    if (TextUtils.isEmpty(sb)) {
                        return;
                    }
                    if (dsVar.getAccuracy() >= 300.0f) {
                        int i = 0;
                        for (String str3 : sb.toString().split("#")) {
                            if (str3.contains(",")) {
                                i++;
                            }
                        }
                        if (i >= 8) {
                            return;
                        }
                    } else if (dsVar.getAccuracy() <= 3.0f) {
                        return;
                    }
                    if (str2.contains("cgiwifi") && !TextUtils.isEmpty(dsVar.g())) {
                        String strReplace = str2.replace("cgiwifi", "cgi");
                        ds dsVarH = dsVar.h();
                        if (ep.a(dsVarH)) {
                            a(strReplace, new StringBuilder(), dsVarH, context, true);
                        }
                    }
                } else if (str2.contains("cgi") && ((sb != null && sb.indexOf(",") != -1) || "4".equals(dsVar.d()))) {
                    return;
                }
                ds dsVarA = a(str2, sb);
                if (ep.a(dsVarA) && dsVarA.toStr().equals(dsVar.toStr(3))) {
                    return;
                }
                this.i = ep.b();
                a aVar = new a();
                aVar.a(dsVar);
                aVar.a(TextUtils.isEmpty(sb) ? null : sb.toString());
                if (this.f3762a.containsKey(str2)) {
                    this.f3762a.get(str2).add(aVar);
                } else {
                    ArrayList<a> arrayList = new ArrayList<>();
                    arrayList.add(aVar);
                    this.f3762a.put(str2, arrayList);
                }
                if (z) {
                    try {
                        a(str2, dsVar, sb, context);
                    } catch (Throwable th) {
                        ej.a(th, "Cache", "add");
                    }
                }
            }
        } catch (Throwable th2) {
            ej.a(th2, "Cache", "add");
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void b(Context context) {
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase;
        try {
            c();
            if (context != null) {
                try {
                    sQLiteDatabaseOpenOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabaseOpenOrCreateDatabase = null;
                }
                try {
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ej.a(th, "DB", "clearHist p2");
                        if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                            sQLiteDatabaseOpenOrCreateDatabase.close();
                        }
                    } catch (Throwable th3) {
                        if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                            sQLiteDatabaseOpenOrCreateDatabase.close();
                        }
                        throw th3;
                    }
                }
                if (ep.a(sQLiteDatabaseOpenOrCreateDatabase, "hist")) {
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.delete("hist" + this.k, "time<?", new String[]{String.valueOf(ep.a() - 86400000)});
                    } catch (Throwable th4) {
                        ej.a(th4, "DB", "clearHist");
                        String message = th4.getMessage();
                        if (!TextUtils.isEmpty(message)) {
                            message.contains("no such table");
                        }
                    }
                    if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                        sQLiteDatabaseOpenOrCreateDatabase.close();
                    }
                } else if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                    sQLiteDatabaseOpenOrCreateDatabase.close();
                }
            }
            this.j = false;
            this.d = null;
            this.n = 0L;
        } catch (Throwable th5) {
            ej.a(th5, "Cache", "destroy part");
        }
    }
}
