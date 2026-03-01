package com.loc;

import android.content.Context;
import com.efs.sdk.base.Constants;
import com.loopj.android.http.RequestParams;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: compiled from: LocNetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ee {
    public static ee b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public aq f3775a;
    public Context c;
    public int d = ej.g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3776e = false;
    public int f = 0;

    public ee(Context context) {
        this.f3775a = null;
        this.c = null;
        try {
            p.a().a(context);
        } catch (Throwable unused) {
        }
        this.c = context;
        this.f3775a = aq.a();
    }

    public static ee a(Context context) {
        if (b == null) {
            b = new ee(context);
        }
        return b;
    }

    public final int a() {
        return this.d;
    }

    public final aw a(ef efVar) throws Throwable {
        return aq.a(efVar, this.f3776e || ep.k(this.c));
    }

    public final aw a(ef efVar, int i) throws Throwable {
        return aq.a(efVar, this.f3776e || ep.k(this.c), i);
    }

    public final ef a(Context context, byte[] bArr, String str, String str2, boolean z) {
        String str3;
        try {
            HashMap map = new HashMap(16);
            ef efVar = new ef(context, ej.c());
            try {
                map.put("Content-Type", RequestParams.APPLICATION_OCTET_STREAM);
                map.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, Constants.CP_GZIP);
                map.put("gzipped", "1");
                map.put(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
                map.put("User-Agent", "AMAP_Location_SDK_Android 5.2.0");
                map.put("KEY", k.f(context));
                map.put("enginever", "5.1");
                String strA = m.a();
                String strA2 = m.a(context, strA, "key=" + k.f(context));
                map.put("ts", strA);
                map.put("scode", strA2);
                map.put("encr", "1");
                efVar.f = map;
                String str4 = z ? "loc" : "locf";
                efVar.n = true;
                efVar.l = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "5.2.0", str4, 3);
                efVar.k = z;
                efVar.g = str;
                efVar.h = str2;
                efVar.i = ep.a(bArr);
                efVar.a(s.a(context));
                HashMap map2 = new HashMap(16);
                map2.put("output", "bin");
                map2.put(com.umeng.analytics.pro.bm.bw, "3103");
                int i = this.f;
                if (i == 0) {
                    map2.remove("custom");
                } else {
                    if (i != 1) {
                        str3 = i == 2 ? "language:en" : "language:cn";
                        map2.remove("custom");
                    }
                    map2.put("custom", str3);
                }
                efVar.m = map2;
                efVar.a(this.d);
                efVar.b(this.d);
                if ((!this.f3776e && !ep.k(context)) || !str.startsWith("http:")) {
                    return efVar;
                }
                efVar.g = efVar.c().replace("https:", "https:");
                return efVar;
            } catch (Throwable unused) {
                return efVar;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00f8 A[Catch: all -> 0x0116, TryCatch #2 {all -> 0x0116, blocks: (B:31:0x00ec, B:33:0x00f8, B:35:0x010d, B:34:0x0107), top: B:54:0x00ec, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0107 A[Catch: all -> 0x0116, TryCatch #2 {all -> 0x0116, blocks: (B:31:0x00ec, B:33:0x00f8, B:35:0x010d, B:34:0x0107), top: B:54:0x00ec, outer: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(android.content.Context r10, double r11, double r13) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a(android.content.Context, double, double):java.lang.String");
    }

    public final void a(long j, boolean z, int i) {
        try {
            this.f3776e = z;
            try {
                p.a().a(z);
            } catch (Throwable unused) {
            }
            this.d = Long.valueOf(j).intValue();
            this.f = i;
        } catch (Throwable th) {
            ej.a(th, "LocNetManager", "setOption");
        }
    }
}
