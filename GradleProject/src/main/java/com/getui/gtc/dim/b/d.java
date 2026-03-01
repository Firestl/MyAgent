package com.getui.gtc.dim.b;

import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.db.DbManager;
import com.getui.gtc.dim.Caller;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Long> f2160a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f2161a = new d(0);
    }

    public d() {
        this.f2160a = new HashMap();
        try {
            DbManager.init(GtcProvider.context(), com.getui.gtc.dim.a.a.class, com.getui.gtc.dim.a.b.class);
            ((com.getui.gtc.dim.a.b) DbManager.getTable(com.getui.gtc.dim.a.a.class, com.getui.gtc.dim.a.b.class)).a();
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
        }
    }

    public /* synthetic */ d(byte b) {
        this();
    }

    public static h a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return ((com.getui.gtc.dim.a.b) DbManager.getTable(com.getui.gtc.dim.a.a.class, com.getui.gtc.dim.a.b.class)).a(str);
    }

    public static boolean a(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return ((com.getui.gtc.dim.a.b) DbManager.getTable(com.getui.gtc.dim.a.a.class, com.getui.gtc.dim.a.b.class)).a(str, obj);
    }

    private Long b(String str) {
        try {
            if (this.f2160a.containsKey(str)) {
                return this.f2160a.get(str);
            }
            d unused = a.f2161a;
            h hVarA = a(str);
            Long l = hVarA != null ? (Long) hVarA.f2169a : null;
            com.getui.gtc.dim.e.b.a("dim interval from db : " + str + " : " + l);
            this.f2160a.put(str, l);
            return l;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("interval", th);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0184  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long c(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.d.c(java.lang.String):long");
    }

    public final Long a(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return b(str + Constants.COLON_SEPARATOR + Caller.valueOf(str2).name());
        }
        g gVarD = g.d();
        int iC = gVarD.c();
        com.getui.gtc.dim.b.a aVarA = com.getui.gtc.dim.b.a.a(str);
        if (aVarA == null) {
            return null;
        }
        Long l = null;
        for (Caller caller : Caller.values()) {
            if (caller.containAt(iC)) {
                Boolean boolB = gVarD.b(aVarA.f2153a, caller.name());
                Long lB = b(str + Constants.COLON_SEPARATOR + caller.name());
                com.getui.gtc.dim.e.b.a("dim check interval for " + str + ", inited caller = " + caller + ", callable = " + boolB + ", interval = " + lB);
                if (boolB == null || boolB.booleanValue()) {
                    if (lB == null) {
                        return null;
                    }
                    if (l == null || lB.longValue() < l.longValue()) {
                        l = lB;
                    }
                }
            }
        }
        return l;
    }

    public final void a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f2160a.put(str, Long.valueOf(j));
        com.getui.gtc.dim.e.b.a("dim storage globalValidTime set: " + str + " : " + j);
    }

    public final boolean a(h hVar, String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jC = c(str);
        Long l = this.f2160a.get(str);
        if (l == null) {
            l = this.f2160a.get("dim-2-2-0-1");
        }
        long jLongValue = l != null ? l.longValue() : jC;
        Long lA = null;
        if ((g.d().a() & 2) != 0) {
            com.getui.gtc.dim.b.a aVarA = com.getui.gtc.dim.b.a.a(str);
            if (aVarA != null) {
                lA = a(aVarA.b, (String) null);
            }
        } else {
            com.getui.gtc.dim.e.b.a("dim ig in");
        }
        if (lA != null) {
            jLongValue = lA.longValue();
        }
        com.getui.gtc.dim.e.b.a("dim storageValidTime check for " + str + ", dycValue = " + l + ", localValue = " + jC + ", interval = " + lA + ", use " + jLongValue);
        boolean z = jCurrentTimeMillis - hVar.b > jLongValue;
        if (z) {
            com.getui.gtc.dim.e.b.b("dim storage source expired for ".concat(String.valueOf(str)));
        }
        return z;
    }
}
