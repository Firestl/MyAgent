package com.getui.gtc.dim.b;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.getui.gtc.dim.AppDataProvider;
import com.getui.gtc.dim.Caller;
import com.getui.gtc.dim.DimSource;
import com.getui.gtc.dim.b.d;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f2163a = Arrays.asList("dim-2-1-21-5", "dim-2-1-21-3", "dim-2-1-21-1");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AppDataProvider f2164e;
    public String f;
    public volatile int k;
    public volatile String r;
    public int c = 0;
    public int d = 1;
    public int m = 3;
    public final int[] n = {-1, 33};
    public volatile boolean s = true;
    public final Map<String, Integer> b = new HashMap();
    public final Map<String, Integer> g = new HashMap();
    public final List<String> i = new ArrayList();
    public final List<String> j = new ArrayList();
    public final Map<String, String> h = new HashMap();
    public final Map<String, Boolean> o = new HashMap(4);
    public final List<String> p = new ArrayList();
    public final List<String> q = new ArrayList();
    public final Map<String, Boolean> l = new HashMap();

    /* JADX INFO: renamed from: com.getui.gtc.dim.b.f$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2165a;

        static {
            int[] iArr = new int[Caller.values().length];
            f2165a = iArr;
            try {
                iArr[Caller.PUSH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2165a[Caller.IDO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2165a[Caller.GY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2165a[Caller.WUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2165a[Caller.ONEID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static boolean a(boolean z, String str) {
        if (z && Operators.MUL.equals(str)) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        for (String str2 : str.split("#")) {
            if (str2.contains("-")) {
                String[] strArrSplit = str2.split("-");
                if (strArrSplit.length == 2 && i >= Integer.parseInt(strArrSplit[0]) && i <= Integer.parseInt(strArrSplit[1])) {
                    return true;
                }
            } else if (String.valueOf(i).equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String b(Caller caller) {
        if (caller == null) {
            return "";
        }
        int i = AnonymousClass1.f2165a[caller.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "oneid" : "wus" : "gy" : "ido" : "gt";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.getui.gtc.dim.AllowSysCall h(java.lang.String r1) {
        /*
            Method dump skipped, instruction units count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.f.h(java.lang.String):com.getui.gtc.dim.AllowSysCall");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean i(String str) {
        if (!TextUtils.isEmpty(str)) {
            byte b = -1;
            switch (str.hashCode()) {
                case 320919007:
                    if (str.equals("dim-2-1-21-1")) {
                        b = 2;
                    }
                    break;
                case 320919008:
                    if (str.equals("dim-2-1-21-2")) {
                        b = 3;
                    }
                    break;
                case 320919009:
                    if (str.equals("dim-2-1-21-3")) {
                        b = 1;
                    }
                    break;
                case 320919011:
                    if (str.equals("dim-2-1-21-5")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0 || b == 1 || b == 2 || b == 3) {
                return true;
            }
        }
        return false;
    }

    public static boolean j(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private Boolean k(String str) {
        try {
            if (this.l.containsKey(str)) {
                return this.l.get(str);
            }
            d unused = d.a.f2161a;
            h hVarA = d.a(str);
            Boolean bool = hVarA != null ? (Boolean) hVarA.f2169a : null;
            com.getui.gtc.dim.e.b.a("dim sys callable from db : " + str + " : " + bool);
            this.l.put(str, bool);
            return bool;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("callable", th);
            return null;
        }
    }

    public int a() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
        com.getui.gtc.dim.e.b.a("dim sys busi enable set: ".concat(String.valueOf(i)));
    }

    public void a(AppDataProvider appDataProvider) {
        this.f2164e = appDataProvider;
        com.getui.gtc.dim.e.b.a("dim sys app data provider set: ".concat(String.valueOf(appDataProvider)));
    }

    public void a(Caller caller) {
        if (caller != null) {
            synchronized (this) {
                this.k |= caller.index;
            }
        }
        com.getui.gtc.dim.e.b.a("dim sys gtc init caller set: ".concat(String.valueOf(caller)));
    }

    public void a(String str, int i) {
        if (str.equalsIgnoreCase(Build.BRAND)) {
            this.n[0] = i;
        } else if (str.equals("dim-2-2-0-1")) {
            this.n[1] = i;
        }
        com.getui.gtc.dim.e.b.a("dim sys pm policy set: " + str + " : " + i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
    
        if (r6.j.contains(r8 + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + b(r7)) == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(com.getui.gtc.dim.Caller r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = " : "
            r1 = 0
            if (r7 == 0) goto L93
            com.getui.gtc.dim.Caller r2 = com.getui.gtc.dim.Caller.UNKNOWN
            if (r7 == r2) goto L93
            int r2 = r6.k
            boolean r2 = r7.containAt(r2)
            if (r2 == 0) goto L93
            java.util.List<java.lang.String> r2 = r6.j     // Catch: java.lang.Throwable -> L8f
            boolean r2 = r2.isEmpty()     // Catch: java.lang.Throwable -> L8f
            java.lang.String r3 = ":"
            if (r2 != 0) goto L39
            java.util.List<java.lang.String> r2 = r6.j     // Catch: java.lang.Throwable -> L8f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            r4.<init>()     // Catch: java.lang.Throwable -> L8f
            r4.append(r8)     // Catch: java.lang.Throwable -> L8f
            r4.append(r3)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r5 = b(r7)     // Catch: java.lang.Throwable -> L8f
            r4.append(r5)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L8f
            boolean r2 = r2.contains(r4)     // Catch: java.lang.Throwable -> L8f
            if (r2 != 0) goto L93
        L39:
            int r2 = r6.m     // Catch: java.lang.Throwable -> L8f
            r4 = 1
            r2 = r2 & r4
            if (r2 != 0) goto L45
            java.lang.String r7 = "dim sys ig ca"
            com.getui.gtc.dim.e.b.a(r7)     // Catch: java.lang.Throwable -> L8f
            return r4
        L45:
            com.getui.gtc.dim.b.a r2 = com.getui.gtc.dim.b.a.a(r8)     // Catch: java.lang.Throwable -> L8f
            if (r2 == 0) goto L8e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            r5.<init>()     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = r2.f2153a     // Catch: java.lang.Throwable -> L8f
            r5.append(r2)     // Catch: java.lang.Throwable -> L8f
            r5.append(r3)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = r7.name()     // Catch: java.lang.Throwable -> L8f
            r5.append(r2)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = r5.toString()     // Catch: java.lang.Throwable -> L8f
            java.lang.Boolean r2 = r6.k(r2)     // Catch: java.lang.Throwable -> L8f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            java.lang.String r5 = "dim sys get callable "
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L8f
            r3.append(r7)     // Catch: java.lang.Throwable -> L8f
            r3.append(r0)     // Catch: java.lang.Throwable -> L8f
            r3.append(r8)     // Catch: java.lang.Throwable -> L8f
            r3.append(r0)     // Catch: java.lang.Throwable -> L8f
            r3.append(r2)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L8f
            com.getui.gtc.dim.e.b.a(r7)     // Catch: java.lang.Throwable -> L8f
            if (r2 == 0) goto L8e
            boolean r7 = r2.booleanValue()     // Catch: java.lang.Throwable -> L8f
            if (r7 == 0) goto L8d
            goto L8e
        L8d:
            return r1
        L8e:
            return r4
        L8f:
            r7 = move-exception
            com.getui.gtc.dim.e.b.b(r7)
        L93:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.f.a(com.getui.gtc.dim.Caller, java.lang.String):boolean");
    }

    public final boolean a(String str) {
        try {
            return this.i.contains(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean a(String str, Caller caller, boolean z) {
        this.l.put(str + Constants.COLON_SEPARATOR + caller.name(), Boolean.valueOf(z));
        d unused = d.a.f2161a;
        d.a(str + Constants.COLON_SEPARATOR + caller.name(), Boolean.valueOf(z));
        com.getui.gtc.dim.e.b.a("dim sys callable set: " + str + " : " + caller + " : " + z);
        return true;
    }

    public boolean a(String str, String str2) {
        if (Caller.valueOf(str2) == Caller.IDO) {
            return this.s;
        }
        com.getui.gtc.dim.e.b.a("dim sys gbdExecutable get always true");
        return true;
    }

    public final int b(String str) {
        Integer num = 0;
        if (!TextUtils.isEmpty(str) && (num = this.g.get(str)) == null) {
            num = this.g.get("dim-2-2-0-1");
        }
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public Boolean b(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return k(str + Constants.COLON_SEPARATOR + Caller.valueOf(str2).name());
        }
        for (Caller caller : Caller.values()) {
            if (caller.containAt(this.k)) {
                Boolean boolK = k(str + Constants.COLON_SEPARATOR + caller.name());
                if (boolK == null || boolK.booleanValue()) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public void b(int i) {
        this.c = i;
        com.getui.gtc.dim.e.b.a("dim sys trace enable set: ".concat(String.valueOf(i)));
    }

    public void b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.b.put(str, Integer.valueOf(i));
        com.getui.gtc.dim.e.b.a("dim sys globalAllow set: " + str + " : " + i);
    }

    public final boolean b() {
        int[] iArr = this.n;
        int i = iArr[0];
        return i >= 0 ? Build.VERSION.SDK_INT >= i : Build.VERSION.SDK_INT >= iArr[1];
    }

    public boolean b(String str, Caller caller, boolean z) {
        if (caller != Caller.IDO || j("com.igexin.sdk.PushManager") || j("com.g.gysdk.GYManager") || !j("com.getui.gs.sdk.GsManager")) {
            com.getui.gtc.dim.e.b.a("dim sys gbdExecutable set ignored");
            return false;
        }
        this.s = z;
        com.getui.gtc.dim.e.b.a("dim sys gbdExecutable set: ".concat(String.valueOf(z)));
        return true;
    }

    public int c() {
        return this.k;
    }

    public void c(int i) {
        this.d = i;
        com.getui.gtc.dim.e.b.a("dim sys trace hw oaid enable set: ".concat(String.valueOf(i)));
    }

    public void c(String str) {
        if (str != null) {
            try {
                if (str.contains(new String(Base64.decode("Y29tLmdldHVpLmd0Yy5leHRlbnNpb24uZGlzdHJpYnV0aW9uLmdkaS5zdHViLlB1c2hFeHRlbnNpb24=", 2)))) {
                    this.f = str;
                }
            } catch (Throwable th) {
                com.getui.gtc.dim.e.b.b(th);
                return;
            }
        }
        com.getui.gtc.dim.e.b.a("dim sys gtc dyc config set: ".concat(String.valueOf(str)));
    }

    public void c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.g.put(str, Integer.valueOf(i));
        com.getui.gtc.dim.e.b.a("dim sys globalAllow policy set: " + str + " : " + i);
    }

    public void c(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            for (String str3 : str2.split("#")) {
                this.j.add(str + Constants.COLON_SEPARATOR + str3);
            }
            com.getui.gtc.dim.e.b.a("dim sys global disallow set: " + str + " : " + str2);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("dim sys global disallow set: " + str + " : " + str2, th);
        }
    }

    public void d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.h.put(str, str2);
        com.getui.gtc.dim.e.b.a("dim sys global trace order set: " + str + " : " + str2);
    }

    public final boolean d(String str) {
        for (Caller caller : Caller.values()) {
            if (a(caller, str)) {
                return true;
            }
        }
        return false;
    }

    public final DimSource e(String str) {
        ArrayList<Caller> arrayList;
        int i;
        String str2;
        try {
            arrayList = new ArrayList();
            for (Caller caller : Caller.values()) {
                if (a(caller, str)) {
                    arrayList.add(caller);
                }
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("allowSource key:".concat(String.valueOf(str)), th);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (this.h.isEmpty()) {
            str2 = null;
        } else {
            str2 = this.h.get(str);
            if (str2 == null) {
                str2 = this.h.get("dim-2-2-0-1");
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            String[] strArrSplit = str2.split("#");
            if (strArrSplit.length >= 4) {
                for (String str3 : strArrSplit) {
                    for (Caller caller2 : arrayList) {
                        if (b(caller2).equals(str3)) {
                            return DimSource.of(caller2);
                        }
                    }
                }
            } else {
                com.getui.gtc.dim.e.b.b("dim sys trace order: " + str2 + " not match for " + str);
            }
        }
        if (arrayList.contains(Caller.IDO)) {
            return DimSource.of(Caller.IDO);
        }
        if (arrayList.contains(Caller.PUSH)) {
            return DimSource.of(Caller.PUSH);
        }
        if (arrayList.contains(Caller.GY)) {
            return DimSource.of(Caller.GY);
        }
        if (arrayList.contains(Caller.WUS)) {
            return DimSource.of(Caller.WUS);
        }
        if (arrayList.contains(Caller.ONEID)) {
            return DimSource.of(Caller.ONEID);
        }
        return null;
    }

    public void e(String str, String str2) {
        boolean zA = a(false, str2);
        this.o.put(str, Boolean.valueOf(zA));
        com.getui.gtc.dim.e.b.a("dim sys black version set: " + str + " : " + str2 + " : " + zA);
    }

    public void f(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.i.add(str);
        com.getui.gtc.dim.e.b.a("dim sys app provider globalAllow set: " + str + " : true");
    }

    public void f(String str, String str2) {
        String string;
        String str3;
        String[] strArrSplit;
        StringBuilder sb;
        boolean z = false;
        try {
            str3 = Build.BRAND;
            strArrSplit = str2.split("&");
        } catch (Throwable th) {
            th = th;
        }
        if (str3.equalsIgnoreCase(strArrSplit[0]) && a(true, strArrSplit[1])) {
            String str4 = strArrSplit[2];
            if (!str4.equals(Operators.MUL)) {
                if (this.r == null) {
                    this.r = com.getui.gtc.dim.c.a.d();
                }
                if (TextUtils.isEmpty(this.r)) {
                    sb = new StringBuilder("dim sys black rom set: ");
                } else {
                    String[] strArrSplit2 = str4.split("#");
                    int length = strArrSplit2.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        if (this.r.startsWith(strArrSplit2[i])) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                    if (!z) {
                        sb = new StringBuilder("dim sys black rom set: ");
                    }
                }
            }
            try {
                this.q.add(str);
                sb = new StringBuilder("dim sys black rom set: ");
                sb.append(str);
                sb.append(" : ");
                sb.append(str2);
                sb.append(" : true");
                string = sb.toString();
            } catch (Throwable th2) {
                th = th2;
                z = true;
                try {
                    com.getui.gtc.dim.e.b.b(th);
                    string = "dim sys black rom set: " + str + " : " + str2 + " : " + z;
                } catch (Throwable th3) {
                    com.getui.gtc.dim.e.b.a("dim sys black rom set: " + str + " : " + str2 + " : " + z);
                    throw th3;
                }
            }
            com.getui.gtc.dim.e.b.a(string);
        }
        sb = new StringBuilder("dim sys black rom set: ");
        sb.append(str);
        sb.append(" : ");
        sb.append(str2);
        sb.append(" : false");
        string = sb.toString();
        com.getui.gtc.dim.e.b.a(string);
    }

    public void g(String str, String str2) {
        String[] strArrSplit = str2.split("#");
        int length = strArrSplit.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (strArrSplit[i].equals(Build.MODEL)) {
                this.p.add(str);
                z = true;
                break;
            }
            i++;
        }
        com.getui.gtc.dim.e.b.a("dim sys black model set: " + str + " : " + str2 + " : " + z);
    }

    public final boolean g(String str) {
        try {
            Boolean bool = this.o.get(str);
            if (bool == null) {
                bool = this.o.get("dim-2-2-0-1");
            }
            if (bool == null) {
                if ((Build.VERSION.SDK_INT >= 34 || Build.VERSION.SDK_INT < 7) && i(str)) {
                    com.getui.gtc.dim.e.b.a("dim sys black version use ld for: " + str + " : true");
                    return true;
                }
            } else if (bool.booleanValue()) {
                return true;
            }
            return this.p.contains(str) || this.q.contains(str);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
            return true;
        }
    }
}
