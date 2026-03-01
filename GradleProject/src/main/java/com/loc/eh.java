package com.loc;

import android.annotation.SuppressLint;
import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;

/* JADX INFO: compiled from: Req.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public final class eh {
    public static String J;
    public static String L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3778a = "1";
    public short b = 0;
    public String c = null;
    public String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3779e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public int z = 0;
    public String A = null;
    public String B = null;
    public ArrayList<dv> C = new ArrayList<>();
    public String D = null;
    public String E = null;
    public ArrayList<ScanResult> F = new ArrayList<>();
    public String G = null;
    public String H = null;
    public byte[] I = null;
    public byte[] O = null;
    public int P = 0;
    public String K = null;
    public String M = null;
    public String N = null;

    public static int a(String str, byte[] bArr, int i) {
        try {
        } catch (Throwable th) {
            ej.a(th, "Req", "copyContentWithByteLen");
            bArr[i] = 0;
        }
        if (TextUtils.isEmpty(str)) {
            bArr[i] = 0;
            return i + 1;
        }
        byte[] bytes = str.getBytes("GBK");
        int length = bytes.length;
        if (length > 127) {
            length = 127;
        }
        bArr[i] = (byte) length;
        int i2 = i + 1;
        System.arraycopy(bytes, 0, bArr, i2, length);
        return i2 + length;
    }

    private String a(String str, int i) {
        String[] strArrSplit = this.B.split("\\*")[i].split(",");
        if ("lac".equals(str)) {
            return strArrSplit[0];
        }
        if ("cellid".equals(str)) {
            return strArrSplit[1];
        }
        if ("signal".equals(str)) {
            return strArrSplit[2];
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:4:0x000c, B:10:0x001c, B:12:0x001f, B:14:0x0028, B:15:0x0030, B:6:0x000f, B:8:0x0014), top: B:20:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r7.split(r0)
            r1 = 6
            byte[] r2 = new byte[r1]
            r3 = 0
            if (r0 == 0) goto Lf
            int r4 = r0.length     // Catch: java.lang.Throwable -> L3e
            if (r4 == r1) goto L1b
        Lf:
            java.lang.String[] r0 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L3e
            r4 = 0
        L12:
            if (r4 >= r1) goto L1b
            java.lang.String r5 = "0"
            r0[r4] = r5     // Catch: java.lang.Throwable -> L3e
            int r4 = r4 + 1
            goto L12
        L1b:
            r1 = 0
        L1c:
            int r4 = r0.length     // Catch: java.lang.Throwable -> L3e
            if (r1 >= r4) goto L58
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L3e
            r5 = 2
            if (r4 <= r5) goto L30
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r4 = r4.substring(r3, r5)     // Catch: java.lang.Throwable -> L3e
            r0[r1] = r4     // Catch: java.lang.Throwable -> L3e
        L30:
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            r5 = 16
            int r4 = java.lang.Integer.parseInt(r4, r5)     // Catch: java.lang.Throwable -> L3e
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> L3e
            r2[r1] = r4     // Catch: java.lang.Throwable -> L3e
            int r1 = r1 + 1
            goto L1c
        L3e:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getMacBa "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            java.lang.String r1 = "Req"
            com.loc.ej.a(r0, r1, r7)
            java.lang.String r7 = "00:00:00:00:00:00"
            byte[] r2 = r6.a(r7)
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (TextUtils.isEmpty(this.A)) {
            return "0";
        }
        if (!this.A.contains(str + Operators.G)) {
            return "0";
        }
        return this.A.substring(this.A.indexOf(str + Operators.G) + str.length() + 1, this.A.indexOf("</" + str));
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Context r25, boolean r26, boolean r27, com.loc.dw r28, com.loc.dx r29, android.net.ConnectivityManager r30, java.lang.String r31) {
        /*
            Method dump skipped, instruction units count: 867
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.a(android.content.Context, boolean, boolean, com.loc.dw, com.loc.dx, android.net.ConnectivityManager, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x044a A[PHI: r0
  0x044a: PHI (r0v127 int) = (r0v126 int), (r0v135 int) binds: [B:102:0x0359, B:127:0x0427] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x05b1 A[Catch: all -> 0x05c2, TryCatch #2 {all -> 0x05c2, blocks: (B:206:0x05a3, B:210:0x05b1, B:211:0x05b5), top: B:248:0x05a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05b5 A[Catch: all -> 0x05c2, TRY_LEAVE, TryCatch #2 {all -> 0x05c2, blocks: (B:206:0x05a3, B:210:0x05b1, B:211:0x05b5), top: B:248:0x05a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x05d4 A[Catch: all -> 0x05f3, TryCatch #3 {all -> 0x05f3, blocks: (B:216:0x05cc, B:218:0x05d4, B:219:0x05de), top: B:249:0x05cc }] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x061a  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x029d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x032b A[PHI: r0
  0x032b: PHI (r0v70 int) = (r0v69 int), (r0v69 int), (r0v145 int) binds: [B:87:0x030d, B:89:0x0314, B:244:0x032b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a() {
        /*
            Method dump skipped, instruction units count: 1643
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.a():byte[]");
    }
}
