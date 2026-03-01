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

/* JADX INFO: compiled from: ImprintValue.java */
/* JADX INFO: loaded from: classes2.dex */
public class e implements bv<e, EnumC0133e>, Serializable, Cloneable {
    public static final Map<EnumC0133e, ch> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f5474e = 7501688097813630241L;
    public static final cz f = new cz("ImprintValue");
    public static final cp g = new cp("value", (byte) 11, 1);
    public static final cp h = new cp("ts", (byte) 10, 2);
    public static final cp i = new cp("guid", (byte) 11, 3);
    public static final Map<Class<? extends dc>, dd> j;
    public static final int k = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5475a;
    public long b;
    public String c;
    public byte l;
    public EnumC0133e[] m;

    /* JADX INFO: compiled from: ImprintValue.java */
    public static class a extends de<e> {
        public a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, e eVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                byte b = cpVarL.b;
                if (b == 0) {
                    cuVar.k();
                    eVar.k();
                    return;
                }
                short s = cpVarL.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            cx.a(cuVar, b);
                        } else if (b == 11) {
                            eVar.c = cuVar.z();
                            eVar.c(true);
                        } else {
                            cx.a(cuVar, b);
                        }
                    } else if (b == 10) {
                        eVar.b = cuVar.x();
                        eVar.b(true);
                    } else {
                        cx.a(cuVar, b);
                    }
                } else if (b == 11) {
                    eVar.f5475a = cuVar.z();
                    eVar.a(true);
                } else {
                    cx.a(cuVar, b);
                }
                cuVar.m();
            }
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, e eVar) throws cb {
            eVar.k();
            cuVar.a(e.f);
            if (eVar.f5475a != null && eVar.d()) {
                cuVar.a(e.g);
                cuVar.a(eVar.f5475a);
                cuVar.c();
            }
            if (eVar.g()) {
                cuVar.a(e.h);
                cuVar.a(eVar.b);
                cuVar.c();
            }
            if (eVar.c != null && eVar.j()) {
                cuVar.a(e.i);
                cuVar.a(eVar.c);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    /* JADX INFO: compiled from: ImprintValue.java */
    public static class b implements dd {
        public b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: ImprintValue.java */
    public static class c extends df<e> {
        public c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, e eVar) throws cb {
            da daVar = (da) cuVar;
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            if (eVar.g()) {
                bitSet.set(1);
            }
            if (eVar.j()) {
                bitSet.set(2);
            }
            daVar.a(bitSet, 3);
            if (eVar.d()) {
                daVar.a(eVar.f5475a);
            }
            if (eVar.g()) {
                daVar.a(eVar.b);
            }
            if (eVar.j()) {
                daVar.a(eVar.c);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, e eVar) throws cb {
            da daVar = (da) cuVar;
            BitSet bitSetB = daVar.b(3);
            if (bitSetB.get(0)) {
                eVar.f5475a = daVar.z();
                eVar.a(true);
            }
            if (bitSetB.get(1)) {
                eVar.b = daVar.x();
                eVar.b(true);
            }
            if (bitSetB.get(2)) {
                eVar.c = daVar.z();
                eVar.c(true);
            }
        }
    }

    /* JADX INFO: compiled from: ImprintValue.java */
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
        map.put(de.class, new b());
        j.put(df.class, new d());
        EnumMap enumMap = new EnumMap(EnumC0133e.class);
        enumMap.put(EnumC0133e.VALUE, new ch("value", (byte) 2, new ci((byte) 11)));
        enumMap.put(EnumC0133e.TS, new ch("ts", (byte) 2, new ci((byte) 10)));
        enumMap.put(EnumC0133e.GUID, new ch("guid", (byte) 2, new ci((byte) 11)));
        Map<EnumC0133e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(e.class, mapUnmodifiableMap);
    }

    public e() {
        this.l = (byte) 0;
        this.m = new EnumC0133e[]{EnumC0133e.VALUE, EnumC0133e.TS, EnumC0133e.GUID};
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e deepCopy() {
        return new e(this);
    }

    public String b() {
        return this.f5475a;
    }

    public void c() {
        this.f5475a = null;
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.f5475a = null;
        b(false);
        this.b = 0L;
        this.c = null;
    }

    public boolean d() {
        return this.f5475a != null;
    }

    public long e() {
        return this.b;
    }

    public void f() {
        this.l = bs.b(this.l, 0);
    }

    public boolean g() {
        return bs.a(this.l, 0);
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

    public void k() throws cb {
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        j.get(cuVar.D()).b().b(cuVar, this);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (d()) {
            sb.append("value:");
            String str = this.f5475a;
            if (str == null) {
                sb.append(com.igexin.push.core.b.m);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        String str2 = this.c;
        if (str2 == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str2);
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        j.get(cuVar.D()).b().a(cuVar, this);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImprintValue.java */
    public enum EnumC0133e implements cc {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");

        public static final Map<String, EnumC0133e> d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final short f5477e;
        public final String f;

        static {
            for (EnumC0133e enumC0133e : EnumSet.allOf(EnumC0133e.class)) {
                d.put(enumC0133e.b(), enumC0133e);
            }
        }

        EnumC0133e(short s, String str) {
            this.f5477e = s;
            this.f = str;
        }

        public static EnumC0133e a(int i) {
            if (i == 1) {
                return VALUE;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return GUID;
        }

        public static EnumC0133e b(int i) {
            EnumC0133e enumC0133eA = a(i);
            if (enumC0133eA != null) {
                return enumC0133eA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        @Override // com.umeng.analytics.pro.cc
        public String b() {
            return this.f;
        }

        public static EnumC0133e a(String str) {
            return d.get(str);
        }

        @Override // com.umeng.analytics.pro.cc
        public short a() {
            return this.f5477e;
        }
    }

    public e a(String str) {
        this.f5475a = str;
        return this;
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

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f5475a = null;
    }

    public e b(String str) {
        this.c = str;
        return this;
    }

    public e(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public e a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public EnumC0133e fieldForId(int i2) {
        return EnumC0133e.a(i2);
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new co(new dg(objectOutputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public e(e eVar) {
        this.l = (byte) 0;
        this.m = new EnumC0133e[]{EnumC0133e.VALUE, EnumC0133e.TS, EnumC0133e.GUID};
        this.l = eVar.l;
        if (eVar.d()) {
            this.f5475a = eVar.f5475a;
        }
        this.b = eVar.b;
        if (eVar.j()) {
            this.c = eVar.c;
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
