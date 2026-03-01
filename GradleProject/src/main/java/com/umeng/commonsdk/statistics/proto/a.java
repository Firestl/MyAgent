package com.umeng.commonsdk.statistics.proto;

import com.lzy.okgo.cookie.SerializableCookie;
import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.ch;
import com.umeng.analytics.pro.ci;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.cp;
import com.umeng.analytics.pro.cu;
import com.umeng.analytics.pro.cv;
import com.umeng.analytics.pro.cx;
import com.umeng.analytics.pro.cz;
import com.umeng.analytics.pro.da;
import com.umeng.analytics.pro.dc;
import com.umeng.analytics.pro.dd;
import com.umeng.analytics.pro.de;
import com.umeng.analytics.pro.df;
import com.umeng.analytics.pro.dg;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: IdJournal.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements bv<a, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Map<e, ch> f5458e;
    public static final long f = 9132678615281394583L;
    public static final cz g = new cz("IdJournal");
    public static final cp h = new cp(SerializableCookie.DOMAIN, (byte) 11, 1);
    public static final cp i = new cp("old_id", (byte) 11, 2);
    public static final cp j = new cp("new_id", (byte) 11, 3);
    public static final cp k = new cp("ts", (byte) 10, 4);
    public static final Map<Class<? extends dc>, dd> l;
    public static final int m = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5459a;
    public String b;
    public String c;
    public long d;
    public byte n;
    public e[] o;

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IdJournal.java */
    public static class C0129a extends de<a> {
        public C0129a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, a aVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                byte b = cpVarL.b;
                if (b == 0) {
                    break;
                }
                short s = cpVarL.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            if (s != 4) {
                                cx.a(cuVar, b);
                            } else if (b == 10) {
                                aVar.d = cuVar.x();
                                aVar.d(true);
                            } else {
                                cx.a(cuVar, b);
                            }
                        } else if (b == 11) {
                            aVar.c = cuVar.z();
                            aVar.c(true);
                        } else {
                            cx.a(cuVar, b);
                        }
                    } else if (b == 11) {
                        aVar.b = cuVar.z();
                        aVar.b(true);
                    } else {
                        cx.a(cuVar, b);
                    }
                } else if (b == 11) {
                    aVar.f5459a = cuVar.z();
                    aVar.a(true);
                } else {
                    cx.a(cuVar, b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (aVar.m()) {
                aVar.n();
                return;
            }
            throw new cv("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, a aVar) throws cb {
            aVar.n();
            cuVar.a(a.g);
            if (aVar.f5459a != null) {
                cuVar.a(a.h);
                cuVar.a(aVar.f5459a);
                cuVar.c();
            }
            if (aVar.b != null && aVar.g()) {
                cuVar.a(a.i);
                cuVar.a(aVar.b);
                cuVar.c();
            }
            if (aVar.c != null) {
                cuVar.a(a.j);
                cuVar.a(aVar.c);
                cuVar.c();
            }
            cuVar.a(a.k);
            cuVar.a(aVar.d);
            cuVar.c();
            cuVar.d();
            cuVar.b();
        }
    }

    /* JADX INFO: compiled from: IdJournal.java */
    public static class b implements dd {
        public b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0129a b() {
            return new C0129a();
        }
    }

    /* JADX INFO: compiled from: IdJournal.java */
    public static class c extends df<a> {
        public c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, a aVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(aVar.f5459a);
            daVar.a(aVar.c);
            daVar.a(aVar.d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            daVar.a(bitSet, 1);
            if (aVar.g()) {
                daVar.a(aVar.b);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, a aVar) throws cb {
            da daVar = (da) cuVar;
            aVar.f5459a = daVar.z();
            aVar.a(true);
            aVar.c = daVar.z();
            aVar.c(true);
            aVar.d = daVar.x();
            aVar.d(true);
            if (daVar.b(1).get(0)) {
                aVar.b = daVar.z();
                aVar.b(true);
            }
        }
    }

    /* JADX INFO: compiled from: IdJournal.java */
    public static class d implements dd {
        public d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    static {
        HashMap map = new HashMap();
        l = map;
        map.put(de.class, new b());
        l.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new ch(SerializableCookie.DOMAIN, (byte) 1, new ci((byte) 11)));
        enumMap.put(e.OLD_ID, new ch("old_id", (byte) 2, new ci((byte) 11)));
        enumMap.put(e.NEW_ID, new ch("new_id", (byte) 1, new ci((byte) 11)));
        enumMap.put(e.TS, new ch("ts", (byte) 1, new ci((byte) 10)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        f5458e = mapUnmodifiableMap;
        ch.a(a.class, mapUnmodifiableMap);
    }

    public a() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a deepCopy() {
        return new a(this);
    }

    public String b() {
        return this.f5459a;
    }

    public void c() {
        this.f5459a = null;
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.f5459a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0L;
    }

    public boolean d() {
        return this.f5459a != null;
    }

    public String e() {
        return this.b;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public String h() {
        return this.c;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public long k() {
        return this.d;
    }

    public void l() {
        this.n = bs.b(this.n, 0);
    }

    public boolean m() {
        return bs.a(this.n, 0);
    }

    public void n() throws cb {
        if (this.f5459a == null) {
            throw new cv("Required field 'domain' was not present! Struct: " + toString());
        }
        if (this.c != null) {
            return;
        }
        throw new cv("Required field 'new_id' was not present! Struct: " + toString());
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        l.get(cuVar.D()).b().b(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        String str = this.f5459a;
        if (str == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str);
        }
        if (g()) {
            sb.append(", ");
            sb.append("old_id:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(com.igexin.push.core.b.m);
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        l.get(cuVar.D()).b().a(cuVar, this);
    }

    /* JADX INFO: compiled from: IdJournal.java */
    public enum e implements cc {
        DOMAIN(1, SerializableCookie.DOMAIN),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final Map<String, e> f5461e = new HashMap();
        public final short f;
        public final String g;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f5461e.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return DOMAIN;
            }
            if (i == 2) {
                return OLD_ID;
            }
            if (i == 3) {
                return NEW_ID;
            }
            if (i != 4) {
                return null;
            }
            return TS;
        }

        public static e b(int i) {
            e eVarA = a(i);
            if (eVarA != null) {
                return eVarA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        @Override // com.umeng.analytics.pro.cc
        public String b() {
            return this.g;
        }

        public static e a(String str) {
            return f5461e.get(str);
        }

        @Override // com.umeng.analytics.pro.cc
        public short a() {
            return this.f;
        }
    }

    public a a(String str) {
        this.f5459a = str;
        return this;
    }

    public a b(String str) {
        this.b = str;
        return this;
    }

    public a c(String str) {
        this.c = str;
        return this;
    }

    public void d(boolean z) {
        this.n = bs.a(this.n, 0, z);
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f5459a = null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    public a(String str, String str2, long j2) {
        this();
        this.f5459a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public a a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new co(new dg(objectOutputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public a(a aVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = aVar.n;
        if (aVar.d()) {
            this.f5459a = aVar.f5459a;
        }
        if (aVar.g()) {
            this.b = aVar.b;
        }
        if (aVar.j()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.n = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
