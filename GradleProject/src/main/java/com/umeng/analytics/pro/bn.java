package com.umeng.analytics.pro;

import com.tencent.open.SocialOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: UMEnvelope.java */
/* JADX INFO: loaded from: classes2.dex */
public class bn implements bv<bn, e>, Serializable, Cloneable {
    public static final int A = 2;
    public static final int B = 3;
    public static final Map<e, ch> k;
    public static final long l = 420342210744516016L;
    public static final cz m = new cz("UMEnvelope");
    public static final cp n = new cp("version", (byte) 11, 1);
    public static final cp o = new cp("address", (byte) 11, 2);
    public static final cp p = new cp(SocialOperation.GAME_SIGNATURE, (byte) 11, 3);
    public static final cp q = new cp("serial_num", (byte) 8, 4);
    public static final cp r = new cp("ts_secs", (byte) 8, 5);
    public static final cp s = new cp("length", (byte) 8, 6);
    public static final cp t = new cp("entity", (byte) 11, 7);
    public static final cp u = new cp("guid", (byte) 11, 8);
    public static final cp v = new cp("checksum", (byte) 11, 9);
    public static final cp w = new cp("codex", (byte) 8, 10);
    public static final Map<Class<? extends dc>, dd> x;
    public static final int y = 0;
    public static final int z = 1;
    public byte C;
    public e[] D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5205a;
    public String b;
    public String c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5206e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    /* JADX INFO: compiled from: UMEnvelope.java */
    public static class a extends de<bn> {
        public a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, bn bnVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                byte b = cpVarL.b;
                if (b == 0) {
                    cuVar.k();
                    if (!bnVar.m()) {
                        throw new cv("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bnVar.p()) {
                        throw new cv("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (bnVar.s()) {
                        bnVar.G();
                        return;
                    }
                    throw new cv("Required field 'length' was not found in serialized data! Struct: " + toString());
                }
                switch (cpVarL.c) {
                    case 1:
                        if (b != 11) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.f5205a = cuVar.z();
                            bnVar.a(true);
                        }
                        break;
                    case 2:
                        if (b != 11) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.b = cuVar.z();
                            bnVar.b(true);
                        }
                        break;
                    case 3:
                        if (b != 11) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.c = cuVar.z();
                            bnVar.c(true);
                        }
                        break;
                    case 4:
                        if (b != 8) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.d = cuVar.w();
                            bnVar.d(true);
                        }
                        break;
                    case 5:
                        if (b != 8) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.f5206e = cuVar.w();
                            bnVar.e(true);
                        }
                        break;
                    case 6:
                        if (b != 8) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.f = cuVar.w();
                            bnVar.f(true);
                        }
                        break;
                    case 7:
                        if (b != 11) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.g = cuVar.A();
                            bnVar.g(true);
                        }
                        break;
                    case 8:
                        if (b != 11) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.h = cuVar.z();
                            bnVar.h(true);
                        }
                        break;
                    case 9:
                        if (b != 11) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.i = cuVar.z();
                            bnVar.i(true);
                        }
                        break;
                    case 10:
                        if (b != 8) {
                            cx.a(cuVar, b);
                        } else {
                            bnVar.j = cuVar.w();
                            bnVar.j(true);
                        }
                        break;
                    default:
                        cx.a(cuVar, b);
                        break;
                }
                cuVar.m();
            }
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, bn bnVar) throws cb {
            bnVar.G();
            cuVar.a(bn.m);
            if (bnVar.f5205a != null) {
                cuVar.a(bn.n);
                cuVar.a(bnVar.f5205a);
                cuVar.c();
            }
            if (bnVar.b != null) {
                cuVar.a(bn.o);
                cuVar.a(bnVar.b);
                cuVar.c();
            }
            if (bnVar.c != null) {
                cuVar.a(bn.p);
                cuVar.a(bnVar.c);
                cuVar.c();
            }
            cuVar.a(bn.q);
            cuVar.a(bnVar.d);
            cuVar.c();
            cuVar.a(bn.r);
            cuVar.a(bnVar.f5206e);
            cuVar.c();
            cuVar.a(bn.s);
            cuVar.a(bnVar.f);
            cuVar.c();
            if (bnVar.g != null) {
                cuVar.a(bn.t);
                cuVar.a(bnVar.g);
                cuVar.c();
            }
            if (bnVar.h != null) {
                cuVar.a(bn.u);
                cuVar.a(bnVar.h);
                cuVar.c();
            }
            if (bnVar.i != null) {
                cuVar.a(bn.v);
                cuVar.a(bnVar.i);
                cuVar.c();
            }
            if (bnVar.F()) {
                cuVar.a(bn.w);
                cuVar.a(bnVar.j);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
    public static class b implements dd {
        public b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
    public static class c extends df<bn> {
        public c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, bn bnVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(bnVar.f5205a);
            daVar.a(bnVar.b);
            daVar.a(bnVar.c);
            daVar.a(bnVar.d);
            daVar.a(bnVar.f5206e);
            daVar.a(bnVar.f);
            daVar.a(bnVar.g);
            daVar.a(bnVar.h);
            daVar.a(bnVar.i);
            BitSet bitSet = new BitSet();
            if (bnVar.F()) {
                bitSet.set(0);
            }
            daVar.a(bitSet, 1);
            if (bnVar.F()) {
                daVar.a(bnVar.j);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, bn bnVar) throws cb {
            da daVar = (da) cuVar;
            bnVar.f5205a = daVar.z();
            bnVar.a(true);
            bnVar.b = daVar.z();
            bnVar.b(true);
            bnVar.c = daVar.z();
            bnVar.c(true);
            bnVar.d = daVar.w();
            bnVar.d(true);
            bnVar.f5206e = daVar.w();
            bnVar.e(true);
            bnVar.f = daVar.w();
            bnVar.f(true);
            bnVar.g = daVar.A();
            bnVar.g(true);
            bnVar.h = daVar.z();
            bnVar.h(true);
            bnVar.i = daVar.z();
            bnVar.i(true);
            if (daVar.b(1).get(0)) {
                bnVar.j = daVar.w();
                bnVar.j(true);
            }
        }
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
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
        x = map;
        map.put(de.class, new b());
        x.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new ch("version", (byte) 1, new ci((byte) 11)));
        enumMap.put(e.ADDRESS, new ch("address", (byte) 1, new ci((byte) 11)));
        enumMap.put(e.SIGNATURE, new ch(SocialOperation.GAME_SIGNATURE, (byte) 1, new ci((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new ch("serial_num", (byte) 1, new ci((byte) 8)));
        enumMap.put(e.TS_SECS, new ch("ts_secs", (byte) 1, new ci((byte) 8)));
        enumMap.put(e.LENGTH, new ch("length", (byte) 1, new ci((byte) 8)));
        enumMap.put(e.ENTITY, new ch("entity", (byte) 1, new ci((byte) 11, true)));
        enumMap.put(e.GUID, new ch("guid", (byte) 1, new ci((byte) 11)));
        enumMap.put(e.CHECKSUM, new ch("checksum", (byte) 1, new ci((byte) 11)));
        enumMap.put(e.CODEX, new ch("codex", (byte) 2, new ci((byte) 8)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        k = mapUnmodifiableMap;
        ch.a(bn.class, mapUnmodifiableMap);
    }

    public bn() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public String A() {
        return this.i;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public int D() {
        return this.j;
    }

    public void E() {
        this.C = bs.b(this.C, 3);
    }

    public boolean F() {
        return bs.a(this.C, 3);
    }

    public void G() throws cb {
        if (this.f5205a == null) {
            throw new cv("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.b == null) {
            throw new cv("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.c == null) {
            throw new cv("Required field 'signature' was not present! Struct: " + toString());
        }
        if (this.g == null) {
            throw new cv("Required field 'entity' was not present! Struct: " + toString());
        }
        if (this.h == null) {
            throw new cv("Required field 'guid' was not present! Struct: " + toString());
        }
        if (this.i != null) {
            return;
        }
        throw new cv("Required field 'checksum' was not present! Struct: " + toString());
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public bn deepCopy() {
        return new bn(this);
    }

    public String b() {
        return this.f5205a;
    }

    public void c() {
        this.f5205a = null;
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.f5205a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.f5206e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public boolean d() {
        return this.f5205a != null;
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

    public int k() {
        return this.d;
    }

    public void l() {
        this.C = bs.b(this.C, 0);
    }

    public boolean m() {
        return bs.a(this.C, 0);
    }

    public int n() {
        return this.f5206e;
    }

    public void o() {
        this.C = bs.b(this.C, 1);
    }

    public boolean p() {
        return bs.a(this.C, 1);
    }

    public int q() {
        return this.f;
    }

    public void r() {
        this.C = bs.b(this.C, 2);
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        x.get(cuVar.D()).b().b(cuVar, this);
    }

    public boolean s() {
        return bs.a(this.C, 2);
    }

    public byte[] t() {
        a(bw.c(this.g));
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            return null;
        }
        return byteBuffer.array();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        String str = this.f5205a;
        if (str == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("address:");
        String str2 = this.b;
        if (str2 == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("signature:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f5206e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            bw.a(byteBuffer, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        String str4 = this.h;
        if (str4 == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str4);
        }
        sb.append(", ");
        sb.append("checksum:");
        String str5 = this.i;
        if (str5 == null) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(str5);
        }
        if (F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        x.get(cuVar.D()).b().a(cuVar, this);
    }

    public String x() {
        return this.h;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
    public enum e implements cc {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, SocialOperation.GAME_SIGNATURE),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");

        public static final Map<String, e> k = new HashMap();
        public final short l;
        public final String m;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                k.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
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
            return this.m;
        }

        public static e a(String str) {
            return k.get(str);
        }

        @Override // com.umeng.analytics.pro.cc
        public short a() {
            return this.l;
        }
    }

    public bn a(String str) {
        this.f5205a = str;
        return this;
    }

    public bn b(String str) {
        this.b = str;
        return this;
    }

    public bn c(String str) {
        this.c = str;
        return this;
    }

    public void d(boolean z2) {
        this.C = bs.a(this.C, 0, z2);
    }

    public void e(boolean z2) {
        this.C = bs.a(this.C, 1, z2);
    }

    public void f(boolean z2) {
        this.C = bs.a(this.C, 2, z2);
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public void j(boolean z2) {
        this.C = bs.a(this.C, 3, z2);
    }

    public void a(boolean z2) {
        if (z2) {
            return;
        }
        this.f5205a = null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.b = null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.c = null;
    }

    public bn d(String str) {
        this.h = str;
        return this;
    }

    public bn e(String str) {
        this.i = str;
        return this;
    }

    public bn(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f5205a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.f5206e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public bn a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public bn b(int i) {
        this.f5206e = i;
        e(true);
        return this;
    }

    public bn c(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public bn d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    @Override // com.umeng.analytics.pro.bv
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.a(i);
    }

    public bn a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public bn a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
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
            this.C = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public bn(bn bnVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = bnVar.C;
        if (bnVar.d()) {
            this.f5205a = bnVar.f5205a;
        }
        if (bnVar.g()) {
            this.b = bnVar.b;
        }
        if (bnVar.j()) {
            this.c = bnVar.c;
        }
        this.d = bnVar.d;
        this.f5206e = bnVar.f5206e;
        this.f = bnVar.f;
        if (bnVar.w()) {
            this.g = bw.d(bnVar.g);
        }
        if (bnVar.z()) {
            this.h = bnVar.h;
        }
        if (bnVar.C()) {
            this.i = bnVar.i;
        }
        this.j = bnVar.j;
    }
}
