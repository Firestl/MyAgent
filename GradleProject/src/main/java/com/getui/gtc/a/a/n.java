package com.getui.gtc.a.a;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2107a = "dj1om0z0za9kwzxrphkqxsu9oc21tez1";
    public static String b = "PHLa/XQjrIl5cU/kj+C+Ig==";
    public static String c = a.a();

    public static String a() {
        try {
            if (TextUtils.isEmpty(c)) {
                c = a.a();
            }
            return c.a(m.a(a.a(c.getBytes("UTF-8")), m.a(c.a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8UA4F9zfelx7qoRjTXEViE8WT60FBHJVl3T3/B+Nmljxiqa7H6GtOnmLFfpTVT+QdgBhxsU097DEBQhX8Z/9rVMp825T10jLefXly84/6p6B9Q0rNYX37zoWD5QT+5JWVgERX9P2o7fCXtlplLjv3dDXbzLdlWwdl53vtnAIidQIDAQAB".getBytes("UTF-8")))), 0);
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return "";
        }
    }

    public static String a(String str) {
        try {
            if (TextUtils.isEmpty(c)) {
                c = a.a();
            }
            return a.a(c.getBytes("UTF-8"), str.getBytes("UTF-8"), h.a(f2107a.getBytes("UTF-8")));
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return null;
        }
    }
}
