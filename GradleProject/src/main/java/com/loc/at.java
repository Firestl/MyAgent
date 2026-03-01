package com.loc;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.loc.aq;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: HttpUrlUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class at {
    public static SoftReference<SSLContext> k;
    public static SoftReference<au> m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3655a;
    public int b;
    public boolean c;
    public SSLContext d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Proxy f3656e;
    public volatile boolean f;
    public long g;
    public long h;
    public String i;
    public b j;
    public aq.a l;
    public String n;
    public boolean o;
    public String p;

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HttpURLConnection f3657a;
        public int b;

        public a(HttpURLConnection httpURLConnection, int i) {
            this.f3657a = httpURLConnection;
            this.b = i;
        }
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Vector<c> f3658a;
        public volatile c b;

        public b() {
            this.f3658a = new Vector<>();
            this.b = new c((byte) 0);
        }

        public /* synthetic */ b(byte b) {
            this();
        }

        public final c a() {
            return this.b;
        }

        public final c a(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.b;
            }
            byte b = 0;
            for (int i = 0; i < this.f3658a.size(); i++) {
                c cVar = this.f3658a.get(i);
                if (cVar != null && cVar.a().equals(str)) {
                    return cVar;
                }
            }
            c cVar2 = new c(b);
            cVar2.b(str);
            this.f3658a.add(cVar2);
            return cVar2;
        }

        public final void b(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.b.a(str);
        }
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    public static class c implements HostnameVerifier {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3659a;
        public String b;

        public c() {
        }

        public /* synthetic */ c(byte b) {
            this();
        }

        public final String a() {
            return this.b;
        }

        public final void a(String str) {
            this.f3659a = str;
        }

        public final void b(String str) {
            this.b = str;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            return !TextUtils.isEmpty(this.f3659a) ? this.f3659a.equals(str) : !TextUtils.isEmpty(this.b) ? defaultHostnameVerifier.verify(this.b, sSLSession) : defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    public at(int i, int i2, Proxy proxy, boolean z) {
        byte b2 = 0;
        this.f = false;
        this.g = -1L;
        this.h = 0L;
        this.o = false;
        this.p = "";
        this.f3655a = i;
        this.b = i2;
        this.f3656e = proxy;
        this.c = p.a().b(z);
        l.d();
        if (p.b()) {
            this.c = false;
        }
        this.l = null;
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            y.a(th, "ht", "ic");
        }
        if (this.c) {
            try {
                if (k == null || k.get() == null) {
                    k = new SoftReference<>(SSLContext.getInstance(SSLUtil.d));
                }
            } catch (Throwable unused) {
            }
            SSLContext sSLContext = k != null ? k.get() : null;
            if (sSLContext == null) {
                try {
                    sSLContext = SSLContext.getInstance(SSLUtil.d);
                } catch (Throwable th2) {
                    y.a(th2, "ht", "ne");
                }
            }
            sSLContext.init(null, null, null);
            this.d = sSLContext;
        }
        this.j = new b(b2);
    }

    public at(av avVar, boolean z) {
        this(avVar, z, (byte) 0);
    }

    public at(av avVar, boolean z, byte b2) {
        this(avVar.c, avVar.d, avVar.f3661e, z);
        if (avVar == null) {
            return;
        }
        try {
            String strP = avVar.p();
            this.p = strP;
            if (TextUtils.isEmpty(strP)) {
                if (avVar instanceof ar) {
                    this.p = a(((ar) avVar).k());
                } else {
                    this.p = b(avVar.b());
                }
            }
        } catch (Throwable th) {
            y.a(th, "ht", "pnfr");
        }
    }

    public static int a(av avVar) {
        try {
            if (l.b()) {
                return 4;
            }
            if (avVar != null && !avVar.i()) {
                return 1;
            }
            if (2 == (!l.a() ? (char) 1 : (char) 2)) {
                return 2;
            }
        } catch (Throwable th) {
            ab.b(th, "htu", "gt");
        }
        return 1;
    }

    private au a() {
        try {
            if (m == null || m.get() == null) {
                m = new SoftReference<>(new au(l.c, this.d));
            }
            au auVar = k != null ? m.get() : null;
            return auVar == null ? new au(l.c, this.d) : auVar;
        } catch (Throwable th) {
            ab.b(th, "ht", "gsf");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01ba A[Catch: all -> 0x01d4, TryCatch #14 {all -> 0x01d4, blocks: (B:108:0x01ad, B:110:0x01ba, B:112:0x01c4, B:114:0x01d0, B:115:0x01d3), top: B:154:0x01ad }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:? A[ADDED_TO_REGION, Catch: all -> 0x01d4, REMOVE, SYNTHETIC, TRY_LEAVE, TryCatch #14 {all -> 0x01d4, blocks: (B:108:0x01ad, B:110:0x01ba, B:112:0x01c4, B:114:0x01d0, B:115:0x01d3), top: B:154:0x01ad }] */
    /* JADX WARN: Removed duplicated region for block: B:175:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.loc.aw a(com.loc.at.a r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 515
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(com.loc.at$a):com.loc.aw");
    }

    public static String a(String str) {
        String str2;
        String strTrim = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit = str.split("&");
                if (strArrSplit.length > 1) {
                    int length = strArrSplit.length;
                    int i = 0;
                    String str3 = "";
                    while (true) {
                        if (i >= length) {
                            str2 = "";
                            break;
                        }
                        str2 = strArrSplit[i];
                        if (str2.contains("sdkversion")) {
                            str3 = str2;
                        }
                        if (str2.contains("product")) {
                            break;
                        }
                        i++;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        String[] strArrSplit2 = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                        if (strArrSplit2.length > 1) {
                            strTrim = strArrSplit2[1].trim();
                            if (!TextUtils.isEmpty(str3) && TextUtils.isEmpty(v.a(strTrim))) {
                                String[] strArrSplit3 = str3.split(ContainerUtils.KEY_VALUE_DELIMITER);
                                if (strArrSplit3.length > 1) {
                                    v.a(strTrim, strArrSplit3[1].trim());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            y.a(th, "ht", "pnfp");
        }
        return strTrim;
    }

    public static String a(String str, String str2, int i) {
        if (i == 2 || i == 4) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
            } catch (Throwable unused) {
            }
        }
        return str;
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(key));
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(value));
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(com.loc.at.a r9, boolean r10, long r11, long r13) {
        /*
            r0 = 0
            java.net.HttpURLConnection r1 = r9.f3657a     // Catch: java.lang.Throwable -> L23
            java.net.URL r1 = r1.getURL()     // Catch: java.lang.Throwable -> L23
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L23
            int r2 = r9.b     // Catch: java.lang.Throwable -> L24
            r3 = 3
            r4 = 1
            if (r2 != r3) goto L13
            r2 = 1
            goto L14
        L13:
            r2 = 0
        L14:
            int r3 = r9.b     // Catch: java.lang.Throwable -> L25
            r5 = 2
            if (r3 == r5) goto L1e
            int r9 = r9.b     // Catch: java.lang.Throwable -> L25
            r3 = 4
            if (r9 != r3) goto L1f
        L1e:
            r0 = 1
        L1f:
            r4 = r0
            r3 = r1
            r5 = r2
            goto L28
        L23:
            r1 = 0
        L24:
            r2 = 0
        L25:
            r3 = r1
            r5 = r2
            r4 = 0
        L28:
            boolean r9 = android.text.TextUtils.isEmpty(r3)
            if (r9 == 0) goto L2f
            return
        L2f:
            r0 = 0
            long r13 = r13 - r11
            long r7 = java.lang.Math.max(r0, r13)
            r6 = r10
            com.loc.l.a(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(com.loc.at$a, boolean, long, long):void");
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.i);
        } catch (Throwable th) {
            y.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.f3655a);
        httpURLConnection.setReadTimeout(this.b);
    }

    public static boolean a(int i) {
        return i == 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7, boolean r8) {
        /*
            r6 = this;
            java.lang.String r0 = "#"
            java.lang.String r1 = "lct"
            r2 = 1
            r3 = 0
            java.lang.String r4 = "sc"
            java.lang.Object r4 = r7.get(r4)     // Catch: java.lang.Throwable -> L72
            java.util.List r4 = (java.util.List) r4     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L3c
            int r5 = r4.size()     // Catch: java.lang.Throwable -> L72
            if (r5 <= 0) goto L3c
            java.lang.Object r4 = r4.get(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L72
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L72
            if (r5 != 0) goto L3c
            boolean r5 = r4.contains(r0)     // Catch: java.lang.Throwable -> L72
            if (r5 != 0) goto L2a
        L28:
            r0 = 1
            goto L3d
        L2a:
            java.lang.String[] r0 = r4.split(r0)     // Catch: java.lang.Throwable -> L72
            int r4 = r0.length     // Catch: java.lang.Throwable -> L72
            if (r4 <= r2) goto L3c
            java.lang.String r4 = "1"
            r0 = r0[r2]     // Catch: java.lang.Throwable -> L72
            boolean r0 = r4.equals(r0)     // Catch: java.lang.Throwable -> L72
            if (r0 == 0) goto L3c
            goto L28
        L3c:
            r0 = 0
        L3d:
            if (r0 != 0) goto L40
            return r3
        L40:
            if (r8 == 0) goto L73
            boolean r8 = r7.containsKey(r1)     // Catch: java.lang.Throwable -> L72
            if (r8 == 0) goto L72
            java.lang.Object r7 = r7.get(r1)     // Catch: java.lang.Throwable -> L72
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L72
            if (r7 == 0) goto L72
            int r8 = r7.size()     // Catch: java.lang.Throwable -> L72
            if (r8 <= 0) goto L72
            java.lang.Object r7 = r7.get(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L72
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L72
            if (r8 != 0) goto L72
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch: java.lang.Throwable -> L72
            long r7 = r7.longValue()     // Catch: java.lang.Throwable -> L72
            java.lang.String r0 = r6.p     // Catch: java.lang.Throwable -> L72
            boolean r7 = com.loc.l.a(r0, r7)     // Catch: java.lang.Throwable -> L72
            r2 = r7
            goto L73
        L72:
            r2 = 0
        L73:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(java.util.Map, boolean):boolean");
    }

    public static String b(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            if (map.containsKey("platinfo")) {
                return a(map.get("platinfo"));
            }
            return null;
        } catch (Throwable th) {
            y.a(th, "ht", "pnfh");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0040 A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TRY_ENTER, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008b A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0093 A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bf A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00cc A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d6 A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e9 A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0111 A[Catch: all -> 0x0170, j -> 0x017b, IOException -> 0x0186, InterruptedIOException -> 0x0193, SocketTimeoutException -> 0x019a, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, MalformedURLException -> 0x01c2, ConnectException -> 0x01cf, TryCatch #8 {j -> 0x017b, ConnectException -> 0x01cf, MalformedURLException -> 0x01c2, SocketTimeoutException -> 0x019a, InterruptedIOException -> 0x0193, SocketException -> 0x01a7, UnknownHostException -> 0x01b4, IOException -> 0x0186, all -> 0x0170, blocks: (B:3:0x0009, B:6:0x0012, B:17:0x0040, B:18:0x0045, B:20:0x004f, B:22:0x0055, B:23:0x005b, B:25:0x0063, B:27:0x006b, B:29:0x0073, B:30:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x00ad, B:39:0x00b2, B:41:0x00b6, B:42:0x00bb, B:44:0x00bf, B:45:0x00c3, B:47:0x00cc, B:50:0x00d6, B:52:0x00da, B:53:0x00e1, B:54:0x00e5, B:56:0x00e9, B:58:0x00ef, B:60:0x00f5, B:64:0x010d, B:66:0x0113, B:68:0x0117, B:70:0x011d, B:71:0x0124, B:61:0x00fc, B:62:0x0102, B:63:0x0106, B:65:0x0111, B:33:0x008b), top: B:153:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.aw a(java.lang.String r8, java.lang.String r9, boolean r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, byte[] r13, int r14) throws com.loc.j {
        /*
            Method dump skipped, instruction units count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(java.lang.String, java.lang.String, boolean, java.lang.String, java.util.Map, byte[], int):com.loc.aw");
    }
}
