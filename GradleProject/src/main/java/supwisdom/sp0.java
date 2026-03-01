package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: CodedInputStreamReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class sp0 implements jr0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final rp0 f9199a;
    public int b;
    public int c;
    public int d = 0;

    /* JADX INFO: compiled from: CodedInputStreamReader.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9200a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9200a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9200a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9200a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9200a[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9200a[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9200a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9200a[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9200a[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9200a[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9200a[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9200a[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9200a[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9200a[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9200a[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9200a[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9200a[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9200a[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public sp0(rp0 rp0Var) {
        gq0.a(rp0Var, "input");
        rp0 rp0Var2 = rp0Var;
        this.f9199a = rp0Var2;
        rp0Var2.d = this;
    }

    public static sp0 a(rp0 rp0Var) {
        sp0 sp0Var = rp0Var.d;
        return sp0Var != null ? sp0Var : new sp0(rp0Var);
    }

    public final void b(int i) throws IOException {
        if (WireFormat.b(this.b) != i) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // supwisdom.jr0
    public int c() throws IOException {
        b(5);
        return this.f9199a.g();
    }

    @Override // supwisdom.jr0
    public boolean d() throws IOException {
        b(0);
        return this.f9199a.c();
    }

    @Override // supwisdom.jr0
    public long e() throws IOException {
        b(1);
        return this.f9199a.m();
    }

    @Override // supwisdom.jr0
    public int f() throws IOException {
        b(0);
        return this.f9199a.s();
    }

    @Override // supwisdom.jr0
    public int g() throws IOException {
        b(0);
        return this.f9199a.f();
    }

    @Override // supwisdom.jr0
    public int getTag() {
        return this.b;
    }

    @Override // supwisdom.jr0
    public int h() throws IOException {
        b(0);
        return this.f9199a.n();
    }

    @Override // supwisdom.jr0
    public long i() throws IOException {
        b(0);
        return this.f9199a.o();
    }

    @Override // supwisdom.jr0
    public String j() throws IOException {
        b(2);
        return this.f9199a.p();
    }

    @Override // supwisdom.jr0
    public int k() throws IOException {
        int i = this.d;
        if (i != 0) {
            this.b = i;
            this.d = 0;
        } else {
            this.b = this.f9199a.r();
        }
        int i2 = this.b;
        if (i2 == 0 || i2 == this.c) {
            return Integer.MAX_VALUE;
        }
        return WireFormat.a(i2);
    }

    @Override // supwisdom.jr0
    public ByteString l() throws IOException {
        b(2);
        return this.f9199a.d();
    }

    @Override // supwisdom.jr0
    public int m() throws IOException {
        b(0);
        return this.f9199a.j();
    }

    @Override // supwisdom.jr0
    public boolean n() throws IOException {
        int i;
        if (this.f9199a.b() || (i = this.b) == this.c) {
            return false;
        }
        return this.f9199a.d(i);
    }

    @Override // supwisdom.jr0
    public int o() throws IOException {
        b(5);
        return this.f9199a.l();
    }

    @Override // supwisdom.jr0
    public long p() throws IOException {
        b(0);
        return this.f9199a.k();
    }

    @Override // supwisdom.jr0
    public String q() throws IOException {
        b(2);
        return this.f9199a.q();
    }

    @Override // supwisdom.jr0
    public double readDouble() throws IOException {
        b(1);
        return this.f9199a.e();
    }

    @Override // supwisdom.jr0
    public float readFloat() throws IOException {
        b(5);
        return this.f9199a.i();
    }

    @Override // supwisdom.jr0
    public long a() throws IOException {
        b(0);
        return this.f9199a.t();
    }

    @Override // supwisdom.jr0
    public long b() throws IOException {
        b(1);
        return this.f9199a.h();
    }

    public final <T> T c(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
        int i = this.c;
        this.c = WireFormat.a(WireFormat.a(this.b), 4);
        try {
            T tNewInstance = lr0Var.newInstance();
            lr0Var.a(tNewInstance, this, xp0Var);
            lr0Var.a(tNewInstance);
            if (this.b == this.c) {
                return tNewInstance;
            }
            throw InvalidProtocolBufferException.parseFailure();
        } finally {
            this.c = i;
        }
    }

    public final <T> T d(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
        int iS = this.f9199a.s();
        rp0 rp0Var = this.f9199a;
        if (rp0Var.f9082a < rp0Var.b) {
            int iC = rp0Var.c(iS);
            T tNewInstance = lr0Var.newInstance();
            this.f9199a.f9082a++;
            lr0Var.a(tNewInstance, this, xp0Var);
            lr0Var.a(tNewInstance);
            this.f9199a.a(0);
            r5.f9082a--;
            this.f9199a.b(iC);
            return tNewInstance;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    @Override // supwisdom.jr0
    public void e(List<Long> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    mq0Var.a(this.f9199a.t());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    mq0Var.a(this.f9199a.t());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Long.valueOf(this.f9199a.t()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Long.valueOf(this.f9199a.t()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void f(List<Long> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    mq0Var.a(this.f9199a.k());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    mq0Var.a(this.f9199a.k());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Long.valueOf(this.f9199a.k()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Long.valueOf(this.f9199a.k()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void g(List<Long> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 1) {
                do {
                    mq0Var.a(this.f9199a.m());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iS = this.f9199a.s();
                d(iS);
                int iA = this.f9199a.a() + iS;
                do {
                    mq0Var.a(this.f9199a.m());
                } while (this.f9199a.a() < iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 1) {
            do {
                list.add(Long.valueOf(this.f9199a.m()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iS2 = this.f9199a.s();
            d(iS2);
            int iA2 = this.f9199a.a() + iS2;
            do {
                list.add(Long.valueOf(this.f9199a.m()));
            } while (this.f9199a.a() < iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void h(List<Integer> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    fq0Var.c(this.f9199a.j());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    fq0Var.c(this.f9199a.j());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Integer.valueOf(this.f9199a.j()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Integer.valueOf(this.f9199a.j()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void i(List<Integer> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    fq0Var.c(this.f9199a.f());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    fq0Var.c(this.f9199a.f());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Integer.valueOf(this.f9199a.f()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Integer.valueOf(this.f9199a.f()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void j(List<Integer> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 2) {
                int iS = this.f9199a.s();
                c(iS);
                int iA = this.f9199a.a() + iS;
                do {
                    fq0Var.c(this.f9199a.g());
                } while (this.f9199a.a() < iA);
                return;
            }
            if (iB == 5) {
                do {
                    fq0Var.c(this.f9199a.g());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 2) {
            int iS2 = this.f9199a.s();
            c(iS2);
            int iA2 = this.f9199a.a() + iS2;
            do {
                list.add(Integer.valueOf(this.f9199a.g()));
            } while (this.f9199a.a() < iA2);
            return;
        }
        if (iB2 == 5) {
            do {
                list.add(Integer.valueOf(this.f9199a.g()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void l(List<String> list) throws IOException {
        a(list, false);
    }

    @Override // supwisdom.jr0
    public void m(List<String> list) throws IOException {
        a(list, true);
    }

    @Override // supwisdom.jr0
    public void n(List<Float> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof dq0) {
            dq0 dq0Var = (dq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 2) {
                int iS = this.f9199a.s();
                c(iS);
                int iA = this.f9199a.a() + iS;
                do {
                    dq0Var.a(this.f9199a.i());
                } while (this.f9199a.a() < iA);
                return;
            }
            if (iB == 5) {
                do {
                    dq0Var.a(this.f9199a.i());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 2) {
            int iS2 = this.f9199a.s();
            c(iS2);
            int iA2 = this.f9199a.a() + iS2;
            do {
                list.add(Float.valueOf(this.f9199a.i()));
            } while (this.f9199a.a() < iA2);
            return;
        }
        if (iB2 == 5) {
            do {
                list.add(Float.valueOf(this.f9199a.i()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void o(List<ByteString> list) throws IOException {
        int iR;
        if (WireFormat.b(this.b) == 2) {
            do {
                list.add(l());
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void p(List<Double> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof up0) {
            up0 up0Var = (up0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 1) {
                do {
                    up0Var.a(this.f9199a.e());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iS = this.f9199a.s();
                d(iS);
                int iA = this.f9199a.a() + iS;
                do {
                    up0Var.a(this.f9199a.e());
                } while (this.f9199a.a() < iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 1) {
            do {
                list.add(Double.valueOf(this.f9199a.e()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iS2 = this.f9199a.s();
            d(iS2);
            int iA2 = this.f9199a.a() + iS2;
            do {
                list.add(Double.valueOf(this.f9199a.e()));
            } while (this.f9199a.a() < iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void q(List<Long> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 1) {
                do {
                    mq0Var.a(this.f9199a.h());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iS = this.f9199a.s();
                d(iS);
                int iA = this.f9199a.a() + iS;
                do {
                    mq0Var.a(this.f9199a.h());
                } while (this.f9199a.a() < iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 1) {
            do {
                list.add(Long.valueOf(this.f9199a.h()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iS2 = this.f9199a.s();
            d(iS2);
            int iA2 = this.f9199a.a() + iS2;
            do {
                list.add(Long.valueOf(this.f9199a.h()));
            } while (this.f9199a.a() < iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public <T> T a(Class<T> cls, xp0 xp0Var) throws IOException {
        b(3);
        return (T) c(fr0.a().a((Class) cls), xp0Var);
    }

    @Override // supwisdom.jr0
    public <T> T b(Class<T> cls, xp0 xp0Var) throws IOException {
        b(2);
        return (T) d(fr0.a().a((Class) cls), xp0Var);
    }

    @Override // supwisdom.jr0
    public <T> T a(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
        b(3);
        return (T) c(lr0Var, xp0Var);
    }

    @Override // supwisdom.jr0
    public <T> T b(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
        b(2);
        return (T) d(lr0Var, xp0Var);
    }

    @Override // supwisdom.jr0
    public void k(List<Boolean> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof op0) {
            op0 op0Var = (op0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    op0Var.a(this.f9199a.c());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    op0Var.a(this.f9199a.c());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Boolean.valueOf(this.f9199a.c()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Boolean.valueOf(this.f9199a.c()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public void a(List<String> list, boolean z) throws IOException {
        int iR;
        int iR2;
        if (WireFormat.b(this.b) == 2) {
            if ((list instanceof kq0) && !z) {
                kq0 kq0Var = (kq0) list;
                do {
                    kq0Var.a(l());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            do {
                list.add(z ? q() : j());
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.jr0
    public <T> void b(List<T> list, lr0<T> lr0Var, xp0 xp0Var) throws IOException {
        int iR;
        if (WireFormat.b(this.b) == 3) {
            int i = this.b;
            do {
                list.add(c(lr0Var, xp0Var));
                if (this.f9199a.b() || this.d != 0) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == i);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void c(List<Long> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    mq0Var.a(this.f9199a.o());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    mq0Var.a(this.f9199a.o());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Long.valueOf(this.f9199a.o()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Long.valueOf(this.f9199a.o()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void d(List<Integer> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    fq0Var.c(this.f9199a.s());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    fq0Var.c(this.f9199a.s());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Integer.valueOf(this.f9199a.s()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Integer.valueOf(this.f9199a.s()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void b(List<Integer> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 2) {
                int iS = this.f9199a.s();
                c(iS);
                int iA = this.f9199a.a() + iS;
                do {
                    fq0Var.c(this.f9199a.l());
                } while (this.f9199a.a() < iA);
                return;
            }
            if (iB == 5) {
                do {
                    fq0Var.c(this.f9199a.l());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 2) {
            int iS2 = this.f9199a.s();
            c(iS2);
            int iA2 = this.f9199a.a() + iS2;
            do {
                list.add(Integer.valueOf(this.f9199a.l()));
            } while (this.f9199a.a() < iA2);
            return;
        }
        if (iB2 == 5) {
            do {
                list.add(Integer.valueOf(this.f9199a.l()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.jr0
    public <T> void a(List<T> list, lr0<T> lr0Var, xp0 xp0Var) throws IOException {
        int iR;
        if (WireFormat.b(this.b) == 2) {
            int i = this.b;
            do {
                list.add(d(lr0Var, xp0Var));
                if (this.f9199a.b() || this.d != 0) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == i);
            this.d = iR;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // supwisdom.jr0
    public void a(List<Integer> list) throws IOException {
        int iR;
        int iR2;
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            int iB = WireFormat.b(this.b);
            if (iB == 0) {
                do {
                    fq0Var.c(this.f9199a.n());
                    if (this.f9199a.b()) {
                        return;
                    } else {
                        iR2 = this.f9199a.r();
                    }
                } while (iR2 == this.b);
                this.d = iR2;
                return;
            }
            if (iB == 2) {
                int iA = this.f9199a.a() + this.f9199a.s();
                do {
                    fq0Var.c(this.f9199a.n());
                } while (this.f9199a.a() < iA);
                a(iA);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int iB2 = WireFormat.b(this.b);
        if (iB2 == 0) {
            do {
                list.add(Integer.valueOf(this.f9199a.n()));
                if (this.f9199a.b()) {
                    return;
                } else {
                    iR = this.f9199a.r();
                }
            } while (iR == this.b);
            this.d = iR;
            return;
        }
        if (iB2 == 2) {
            int iA2 = this.f9199a.a() + this.f9199a.s();
            do {
                list.add(Integer.valueOf(this.f9199a.n()));
            } while (this.f9199a.a() < iA2);
            a(iA2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public final void c(int i) throws IOException {
        if ((i & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    public final void d(int i) throws IOException {
        if ((i & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:
    
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0064, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.jr0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <K, V> void a(java.util.Map<K, V> r8, supwisdom.oq0.a<K, V> r9, supwisdom.xp0 r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.b(r0)
            supwisdom.rp0 r1 = r7.f9199a
            int r1 = r1.s()
            supwisdom.rp0 r2 = r7.f9199a
            int r1 = r2.c(r1)
            K r2 = r9.b
            V r3 = r9.d
        L14:
            int r4 = r7.k()     // Catch: java.lang.Throwable -> L65
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L5c
            supwisdom.rp0 r5 = r7.f9199a     // Catch: java.lang.Throwable -> L65
            boolean r5 = r5.b()     // Catch: java.lang.Throwable -> L65
            if (r5 == 0) goto L26
            goto L5c
        L26:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L47
            if (r4 == r0) goto L3a
            boolean r4 = r7.n()     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            if (r4 == 0) goto L34
            goto L14
        L34:
            com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException r4 = new com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            r4.<init>(r6)     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            throw r4     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
        L3a:
            com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r4 = r9.c     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            V r5 = r9.d     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            java.lang.Class r5 = r5.getClass()     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            java.lang.Object r3 = r7.a(r4, r5, r10)     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            goto L14
        L47:
            com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r4 = r9.f8699a     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            r5 = 0
            java.lang.Object r2 = r7.a(r4, r5, r5)     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            goto L14
        L4f:
            boolean r4 = r7.n()     // Catch: java.lang.Throwable -> L65
            if (r4 == 0) goto L56
            goto L14
        L56:
            com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException r8 = new com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L65
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L65
            throw r8     // Catch: java.lang.Throwable -> L65
        L5c:
            r8.put(r2, r3)     // Catch: java.lang.Throwable -> L65
            supwisdom.rp0 r8 = r7.f9199a
            r8.b(r1)
            return
        L65:
            r8 = move-exception
            supwisdom.rp0 r9 = r7.f9199a
            r9.b(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.sp0.a(java.util.Map, supwisdom.oq0$a, supwisdom.xp0):void");
    }

    public final Object a(WireFormat.FieldType fieldType, Class<?> cls, xp0 xp0Var) throws IOException {
        switch (a.f9200a[fieldType.ordinal()]) {
            case 1:
                return Boolean.valueOf(d());
            case 2:
                return l();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(g());
            case 5:
                return Integer.valueOf(c());
            case 6:
                return Long.valueOf(b());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(m());
            case 9:
                return Long.valueOf(p());
            case 10:
                return b(cls, xp0Var);
            case 11:
                return Integer.valueOf(o());
            case 12:
                return Long.valueOf(e());
            case 13:
                return Integer.valueOf(h());
            case 14:
                return Long.valueOf(i());
            case 15:
                return q();
            case 16:
                return Integer.valueOf(f());
            case 17:
                return Long.valueOf(a());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    public final void a(int i) throws IOException {
        if (this.f9199a.a() != i) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
