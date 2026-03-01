package com.igexin.push.c;

import android.text.TextUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3247a = com.igexin.push.c.b.f3254a + a.class.getName();
    public static final int q = 10;
    public int b;
    public int g;
    public volatile long h;
    public volatile long i;
    public boolean j;
    public int l;
    public int m;
    public d n;
    public final List<d> c = new ArrayList();
    public final List<b> o = new ArrayList();
    public final Object d = new Object();
    public final Object p = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile EnumC0075a f3248e = EnumC0075a.NORMAL;
    public int r = 0;
    public AtomicBoolean f = new AtomicBoolean(false);
    public final Comparator<d> k = new Comparator<d>() { // from class: com.igexin.push.c.a.1
        public static int a(d dVar, d dVar2) {
            return (int) (dVar.c() - dVar2.c());
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(d dVar, d dVar2) {
            return (int) (dVar.c() - dVar2.c());
        }
    };

    /* JADX INFO: renamed from: com.igexin.push.c.a$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3250a;

        static {
            int[] iArr = new int[EnumC0075a.values().length];
            f3250a = iArr;
            try {
                iArr[EnumC0075a.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3250a[EnumC0075a.BACKUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3250a[EnumC0075a.TRY_NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.c.a$a, reason: collision with other inner class name */
    public enum EnumC0075a {
        NORMAL(0),
        BACKUP(1),
        TRY_NORMAL(2);

        public int d;

        EnumC0075a(int i) {
            this.d = -1;
            this.d = i;
        }

        private int a() {
            return this.d;
        }

        public static EnumC0075a a(int i) {
            for (EnumC0075a enumC0075a : values()) {
                if (enumC0075a.d == i) {
                    return enumC0075a;
                }
            }
            return null;
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3253a;
        public long b;

        public final b a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return this;
            }
            try {
                this.f3253a = jSONObject.getString("address");
                this.b = jSONObject.getLong("outdateTime");
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            return this;
        }

        public final JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("address", this.f3253a);
                jSONObject.put("outdateTime", this.b);
                return jSONObject;
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
                return null;
            }
        }

        public final String toString() {
            return "ServerAddress{address='" + this.f3253a + Operators.SINGLE_QUOTE + ", outdateTime=" + this.b + Operators.BLOCK_END;
        }
    }

    private String a(boolean z) {
        try {
            synchronized (this.p) {
                String str = this.j ? com.igexin.push.core.e.at : com.igexin.push.core.e.au;
                if (this.o.isEmpty() && TextUtils.isEmpty(str)) {
                    com.igexin.c.a.c.a.a(f3247a + "cm list size = 0", new Object[0]);
                    this.m = 0;
                    this.l = 0;
                    return null;
                }
                if (this.o.isEmpty() && !TextUtils.isEmpty(str)) {
                    a(str);
                }
                com.igexin.c.a.c.a.a(f3247a + "cm try = " + this.m + " times", new Object[0]);
                if (this.m >= this.o.size() * 1) {
                    com.igexin.c.a.c.a.a(f3247a + "cm invalid", new Object[0]);
                    this.m = 0;
                    this.l = 0;
                    this.o.clear();
                    return null;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                Iterator<b> it = this.o.iterator();
                while (it.hasNext()) {
                    b next = it.next();
                    if (next.b < jCurrentTimeMillis) {
                        com.igexin.c.a.c.a.a(f3247a + "|add[" + next.f3253a + "] outDate", new Object[0]);
                        it.remove();
                    }
                }
                h();
                if (this.o.isEmpty()) {
                    return null;
                }
                if (z) {
                    this.m++;
                }
                int i = this.l >= this.o.size() ? 0 : this.l;
                this.l = i;
                String str2 = this.o.get(i).f3253a;
                this.l++;
                return str2;
            }
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(f3247a, e2.toString());
            com.igexin.c.a.c.a.a(f3247a + "|" + e2.toString(), new Object[0]);
            return null;
        }
    }

    private void a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                this.o.add(new b().a(jSONArray.getJSONObject(i)));
            }
            com.igexin.c.a.c.a.a(f3247a + "|get cm from cache, isWf = " + this.j + ", lastCmList = " + str, new Object[0]);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    private String b(boolean z) {
        String strA;
        synchronized (this.d) {
            int i = this.b >= this.c.size() ? 0 : this.b;
            this.b = i;
            d dVar = this.c.get(i);
            this.n = dVar;
            strA = dVar.a(z);
        }
        return strA;
    }

    private void c(boolean z) {
        this.j = z;
    }

    private List<b> g() {
        return this.o;
    }

    private void h() {
        JSONArray jSONArray = new JSONArray();
        Iterator<b> it = this.o.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        com.igexin.push.core.e.f.a().c(jSONArray.length() == 0 ? com.igexin.push.core.b.m : jSONArray.toString(), !this.j);
    }

    private void i() {
        synchronized (this.d) {
            this.b = 0;
            Collections.sort(this.c, this.k);
        }
    }

    private void j() {
        com.igexin.c.a.c.a.a(f3247a + "|detect success, current type = " + this.f3248e, new Object[0]);
        if (this.f3248e == EnumC0075a.BACKUP) {
            a(EnumC0075a.TRY_NORMAL);
            com.igexin.push.core.d unused = d.a.f3384a;
            com.igexin.push.e.a.a(true);
        }
    }

    private void k() {
        com.igexin.c.a.c.a.a(f3247a + "|before disconnect, type = " + this.f3248e, new Object[0]);
        int i = AnonymousClass2.f3250a[this.f3248e.ordinal()];
        if (i != 1) {
            if (i == 2 && System.currentTimeMillis() - this.h > com.igexin.push.config.d.r) {
                a(EnumC0075a.TRY_NORMAL);
                return;
            }
            return;
        }
        if (System.currentTimeMillis() - this.i <= 86400000 || this.g <= com.igexin.push.config.d.t) {
            return;
        }
        a(EnumC0075a.BACKUP);
    }

    public final synchronized void a(EnumC0075a enumC0075a) {
        com.igexin.c.a.c.a.a(f3247a + "|set domain type = " + enumC0075a, new Object[0]);
        if (com.igexin.push.config.d.g) {
            if (this.f3248e != enumC0075a) {
                a((List<b>) null);
            }
            int i = AnonymousClass2.f3250a[enumC0075a.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    this.f.set(true);
                    if (this.f3248e != enumC0075a) {
                        this.h = System.currentTimeMillis();
                    }
                    SDKUrlConfig.setConnectAddress(SDKUrlConfig.XFR_ADDRESS_BAK[0]);
                    SDKUrlConfig.getConnectAddress();
                    com.igexin.c.a.c.a.a(f3247a + "|set domain type backup cm = " + SDKUrlConfig.getConnectAddress(), new Object[0]);
                } else if (i == 3) {
                    if (this.f3248e != enumC0075a) {
                        this.r = 0;
                    }
                }
                this.f3248e = enumC0075a;
                c.a().f().n();
            }
            this.b = 0;
            SDKUrlConfig.setConnectAddress(b(true));
            if (enumC0075a == EnumC0075a.NORMAL) {
                this.f.set(false);
            }
            SDKUrlConfig.getConnectAddress();
            com.igexin.c.a.c.a.a(f3247a + "|set domain type normal cm = " + SDKUrlConfig.getConnectAddress(), new Object[0]);
            this.f3248e = enumC0075a;
            c.a().f().n();
        }
    }

    public final void a(List<b> list) {
        synchronized (this.p) {
            this.l = 0;
            this.m = 0;
            this.o.clear();
            if (list != null) {
                this.o.addAll(list);
                com.igexin.c.a.c.a.a(f3247a + "|set cm list: " + list.toString(), new Object[0]);
            }
            h();
        }
    }

    public final boolean a() {
        boolean z;
        try {
            com.igexin.push.core.d unused = d.a.f3384a;
            z = true;
            boolean z2 = !com.igexin.push.e.a.e();
            String strA = a(z2);
            com.igexin.c.a.c.a.a(f3247a + "|get from cm = " + strA, new Object[0]);
            if (strA == null) {
                if (com.igexin.push.config.d.g && this.f3248e == EnumC0075a.BACKUP) {
                    int i = this.b >= SDKUrlConfig.XFR_ADDRESS_BAK.length ? 0 : this.b;
                    this.b = i;
                    strA = SDKUrlConfig.XFR_ADDRESS_BAK[i];
                    this.b = i + 1;
                } else {
                    if (this.n != null && !this.n.d()) {
                        this.b++;
                    }
                    strA = b(z2);
                }
                z = false;
            }
            try {
                if (!SDKUrlConfig.getConnectAddress().equals(strA)) {
                    SDKUrlConfig.getConnectAddress();
                    com.igexin.c.a.c.a.a(f3247a + "|address changed : form [" + SDKUrlConfig.getConnectAddress() + "] to [" + strA + Operators.ARRAY_END_STR, new Object[0]);
                }
                SDKUrlConfig.setConnectAddress(strA);
            } catch (Exception e2) {
                e = e2;
                com.igexin.c.a.c.a.a(e);
                com.igexin.c.a.c.a.a(f3247a, e.toString());
                com.igexin.c.a.c.a.a(f3247a + "|switch address|" + e.toString(), new Object[0]);
            }
        } catch (Exception e3) {
            e = e3;
            z = false;
        }
        return z;
    }

    public final synchronized void b() {
        this.m = 0;
        if (this.n != null) {
            this.n.e();
        }
    }

    public final void b(List<d> list) {
        synchronized (this.d) {
            this.c.clear();
            this.c.addAll(list);
            Collections.sort(this.c, this.k);
        }
    }

    public final synchronized void c() {
        this.g++;
        com.igexin.c.a.c.a.a(f3247a + "|loginFailedCnt = " + this.g, new Object[0]);
    }

    public final void d() {
        if (AnonymousClass2.f3250a[this.f3248e.ordinal()] == 2 && System.currentTimeMillis() - this.h > com.igexin.push.config.d.r) {
            a(EnumC0075a.TRY_NORMAL);
        }
    }

    public final void e() {
        if (this.f3248e != EnumC0075a.BACKUP) {
            this.g = 0;
        }
        int i = AnonymousClass2.f3250a[this.f3248e.ordinal()];
        if (i == 1) {
            this.i = System.currentTimeMillis();
            c.a().f().n();
            this.f.set(false);
        } else {
            if (i != 3) {
                return;
            }
            a(EnumC0075a.NORMAL);
            this.f.set(false);
        }
    }

    public final void f() {
        EnumC0075a enumC0075a;
        com.igexin.c.a.c.a.a(f3247a + "|before disconnect, type = " + this.f3248e, new Object[0]);
        int i = AnonymousClass2.f3250a[this.f3248e.ordinal()];
        if (i != 1) {
            if (i == 2 && System.currentTimeMillis() - this.h > com.igexin.push.config.d.r) {
                enumC0075a = EnumC0075a.TRY_NORMAL;
                a(enumC0075a);
            }
        } else if (System.currentTimeMillis() - this.i > 86400000 && this.g > com.igexin.push.config.d.t) {
            enumC0075a = EnumC0075a.BACKUP;
            a(enumC0075a);
        }
        if (com.igexin.push.core.e.u && this.f3248e != EnumC0075a.BACKUP) {
            this.i = System.currentTimeMillis();
            c.a().f().n();
        }
        if (AnonymousClass2.f3250a[this.f3248e.ordinal()] != 3) {
            return;
        }
        int i2 = this.r + 1;
        this.r = i2;
        if (i2 >= 10) {
            this.g = 0;
            this.h = System.currentTimeMillis();
            a(EnumC0075a.BACKUP);
        }
    }
}
