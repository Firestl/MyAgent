package com.umeng.commonsdk.statistics.proto;

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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: IdSnapshot.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements bv<b, e>, Serializable, Cloneable {
    public static final Map<e, ch> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f5462e = -6496538196005191531L;
    public static final cz f = new cz("IdSnapshot");
    public static final cp g = new cp("identity", (byte) 11, 1);
    public static final cp h = new cp("ts", (byte) 10, 2);
    public static final cp i = new cp("version", (byte) 8, 3);
    public static final Map<Class<? extends dc>, dd> j;
    public static final int k = 0;
    public static final int l = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5463a;
    public long b;
    public int c;
    public byte m;

    /* JADX INFO: compiled from: IdSnapshot.java */
    public static class a extends de<b> {
        public a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, b bVar) throws cb {
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
                        } else if (b == 8) {
                            bVar.c = cuVar.w();
                            bVar.c(true);
                        } else {
                            cx.a(cuVar, b);
                        }
                    } else if (b == 10) {
                        bVar.b = cuVar.x();
                        bVar.b(true);
                    } else {
                        cx.a(cuVar, b);
                    }
                } else if (b == 11) {
                    bVar.f5463a = cuVar.z();
                    bVar.a(true);
                } else {
                    cx.a(cuVar, b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (!bVar.g()) {
                throw new cv("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            if (bVar.j()) {
                bVar.k();
                return;
            }
            throw new cv("Required field 'version' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, b bVar) throws cb {
            bVar.k();
            cuVar.a(b.f);
            if (bVar.f5463a != null) {
                cuVar.a(b.g);
                cuVar.a(bVar.f5463a);
                cuVar.c();
            }
            cuVar.a(b.h);
            cuVar.a(bVar.b);
            cuVar.c();
            cuVar.a(b.i);
            cuVar.a(bVar.c);
            cuVar.c();
            cuVar.d();
            cuVar.b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IdSnapshot.java */
    public static class C0130b implements dd {
        public C0130b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: IdSnapshot.java */
    public static class c extends df<b> {
        public c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, b bVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(bVar.f5463a);
            daVar.a(bVar.b);
            daVar.a(bVar.c);
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, b bVar) throws cb {
            da daVar = (da) cuVar;
            bVar.f5463a = daVar.z();
            bVar.a(true);
            bVar.b = daVar.x();
            bVar.b(true);
            bVar.c = daVar.w();
            bVar.c(true);
        }
    }

    /* JADX INFO: compiled from: IdSnapshot.java */
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
        j = map;
        map.put(de.class, new C0130b());
        j.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.IDENTITY, new ch("identity", (byte) 1, new ci((byte) 11)));
        enumMap.put(e.TS, new ch("ts", (byte) 1, new ci((byte) 10)));
        enumMap.put(e.VERSION, new ch("version", (byte) 1, new ci((byte) 8)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(b.class, mapUnmodifiableMap);
    }

    public b() {
        this.m = (byte) 0;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public b deepCopy() {
        return new b(this);
    }

    public String b() {
        return this.f5463a;
    }

    public void c() {
        this.f5463a = null;
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.f5463a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0;
    }

    public boolean d() {
        return this.f5463a != null;
    }

    public long e() {
        return this.b;
    }

    public void f() {
        this.m = bs.b(this.m, 0);
    }

    public boolean g() {
        return bs.a(this.m, 0);
    }

    public int h() {
        return this.c;
    }

    public void i() {
        this.m = bs.b(this.m, 1);
    }

    public boolean j() {
        return bs.a(this.m, 1);
    }

    public void k() throws cb {
        if (this.f5463a != null) {
            return;
        }
        throw new cv("Required field 'identity' was not present! Struct: " + toString());
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        j.get(cuVar.D()).b().b(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        String str = this.f5463a;
        if (str == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        j.get(cuVar.D()).b().a(cuVar, this);
    }

    /* JADX INFO: compiled from: IdSnapshot.java */
    public enum e implements cc {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");

        public static final Map<String, e> d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final short f5465e;
        public final String f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                d.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f5465e = s;
            this.f = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return IDENTITY;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return VERSION;
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
            return this.f5465e;
        }
    }

    public b a(String str) {
        this.f5463a = str;
        return this;
    }

    public void b(boolean z) {
        this.m = bs.a(this.m, 0, z);
    }

    public void c(boolean z) {
        this.m = bs.a(this.m, 1, z);
    }

    public b(String str, long j2, int i2) {
        this();
        this.f5463a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f5463a = null;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public b a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public b a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new co(new dg(objectOutputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public b(b bVar) {
        this.m = (byte) 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.f5463a = bVar.f5463a;
        }
        this.b = bVar.b;
        this.c = bVar.c;
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.m = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
