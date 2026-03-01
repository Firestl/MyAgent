package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.ch;
import com.umeng.analytics.pro.ci;
import com.umeng.analytics.pro.ck;
import com.umeng.analytics.pro.cm;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.cp;
import com.umeng.analytics.pro.cr;
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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Imprint.java */
/* JADX INFO: loaded from: classes2.dex */
public class d implements bv<d, e>, Serializable, Cloneable {
    public static final Map<e, ch> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f5470e = 2846460275012375038L;
    public static final cz f = new cz("Imprint");
    public static final cp g = new cp("property", (byte) 13, 1);
    public static final cp h = new cp("version", (byte) 8, 2);
    public static final cp i = new cp("checksum", (byte) 11, 3);
    public static final Map<Class<? extends dc>, dd> j;
    public static final int k = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, com.umeng.commonsdk.statistics.proto.e> f5471a;
    public int b;
    public String c;
    public byte l;

    /* JADX INFO: compiled from: Imprint.java */
    public static class a extends de<d> {
        public a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, d dVar) throws cb {
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
                            cx.a(cuVar, b);
                        } else if (b == 11) {
                            dVar.c = cuVar.z();
                            dVar.c(true);
                        } else {
                            cx.a(cuVar, b);
                        }
                    } else if (b == 8) {
                        dVar.b = cuVar.w();
                        dVar.b(true);
                    } else {
                        cx.a(cuVar, b);
                    }
                } else if (b == 13) {
                    cr crVarN = cuVar.n();
                    dVar.f5471a = new HashMap(crVarN.c * 2);
                    for (int i = 0; i < crVarN.c; i++) {
                        String strZ = cuVar.z();
                        com.umeng.commonsdk.statistics.proto.e eVar = new com.umeng.commonsdk.statistics.proto.e();
                        eVar.read(cuVar);
                        dVar.f5471a.put(strZ, eVar);
                    }
                    cuVar.o();
                    dVar.a(true);
                } else {
                    cx.a(cuVar, b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (dVar.h()) {
                dVar.l();
                return;
            }
            throw new cv("Required field 'version' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, d dVar) throws cb {
            dVar.l();
            cuVar.a(d.f);
            if (dVar.f5471a != null) {
                cuVar.a(d.g);
                cuVar.a(new cr((byte) 11, (byte) 12, dVar.f5471a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.f5471a.entrySet()) {
                    cuVar.a(entry.getKey());
                    entry.getValue().write(cuVar);
                }
                cuVar.e();
                cuVar.c();
            }
            cuVar.a(d.h);
            cuVar.a(dVar.b);
            cuVar.c();
            if (dVar.c != null) {
                cuVar.a(d.i);
                cuVar.a(dVar.c);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    /* JADX INFO: compiled from: Imprint.java */
    public static class b implements dd {
        public b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: Imprint.java */
    public static class c extends df<d> {
        public c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, d dVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(dVar.f5471a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.f5471a.entrySet()) {
                daVar.a(entry.getKey());
                entry.getValue().write(daVar);
            }
            daVar.a(dVar.b);
            daVar.a(dVar.c);
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, d dVar) throws cb {
            da daVar = (da) cuVar;
            cr crVar = new cr((byte) 11, (byte) 12, daVar.w());
            dVar.f5471a = new HashMap(crVar.c * 2);
            for (int i = 0; i < crVar.c; i++) {
                String strZ = daVar.z();
                com.umeng.commonsdk.statistics.proto.e eVar = new com.umeng.commonsdk.statistics.proto.e();
                eVar.read(daVar);
                dVar.f5471a.put(strZ, eVar);
            }
            dVar.a(true);
            dVar.b = daVar.w();
            dVar.b(true);
            dVar.c = daVar.z();
            dVar.c(true);
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Imprint.java */
    public static class C0132d implements dd {
        public C0132d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    static {
        HashMap map = new HashMap();
        j = map;
        map.put(de.class, new b());
        j.put(df.class, new C0132d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new ch("property", (byte) 1, new ck((byte) 13, new ci((byte) 11), new cm((byte) 12, com.umeng.commonsdk.statistics.proto.e.class))));
        enumMap.put(e.VERSION, new ch("version", (byte) 1, new ci((byte) 8)));
        enumMap.put(e.CHECKSUM, new ch("checksum", (byte) 1, new ci((byte) 11)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(d.class, mapUnmodifiableMap);
    }

    public d() {
        this.l = (byte) 0;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public d deepCopy() {
        return new d(this);
    }

    public int b() {
        Map<String, com.umeng.commonsdk.statistics.proto.e> map = this.f5471a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.e> c() {
        return this.f5471a;
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.f5471a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public void d() {
        this.f5471a = null;
    }

    public boolean e() {
        return this.f5471a != null;
    }

    public int f() {
        return this.b;
    }

    public void g() {
        this.l = bs.b(this.l, 0);
    }

    public boolean h() {
        return bs.a(this.l, 0);
    }

    public String i() {
        return this.c;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void l() throws cb {
        if (this.f5471a == null) {
            throw new cv("Required field 'property' was not present! Struct: " + toString());
        }
        if (this.c != null) {
            return;
        }
        throw new cv("Required field 'checksum' was not present! Struct: " + toString());
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        j.get(cuVar.D()).b().b(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        Map<String, com.umeng.commonsdk.statistics.proto.e> map = this.f5471a;
        if (map == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(map);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("checksum:");
        String str = this.c;
        if (str == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        j.get(cuVar.D()).b().a(cuVar, this);
    }

    /* JADX INFO: compiled from: Imprint.java */
    public enum e implements cc {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");

        public static final Map<String, e> d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final short f5473e;
        public final String f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                d.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f5473e = s;
            this.f = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return PROPERTY;
            }
            if (i == 2) {
                return VERSION;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
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
            return this.f;
        }

        public static e a(String str) {
            return d.get(str);
        }

        @Override // com.umeng.analytics.pro.cc
        public short a() {
            return this.f5473e;
        }
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.e eVar) {
        if (this.f5471a == null) {
            this.f5471a = new HashMap();
        }
        this.f5471a.put(str, eVar);
    }

    public void b(boolean z) {
        this.l = bs.a(this.l, 0, z);
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    public d(Map<String, com.umeng.commonsdk.statistics.proto.e> map, int i2, String str) {
        this();
        this.f5471a = map;
        this.b = i2;
        b(true);
        this.c = str;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public d a(Map<String, com.umeng.commonsdk.statistics.proto.e> map) {
        this.f5471a = map;
        return this;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f5471a = null;
    }

    public d a(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public d(d dVar) {
        this.l = (byte) 0;
        this.l = dVar.l;
        if (dVar.e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.f5471a.entrySet()) {
                map.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.e(entry.getValue()));
            }
            this.f5471a = map;
        }
        this.b = dVar.b;
        if (dVar.k()) {
            this.c = dVar.c;
        }
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new co(new dg(objectOutputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.l = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
