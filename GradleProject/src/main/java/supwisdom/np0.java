package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import supwisdom.oq0;

/* JADX INFO: compiled from: BinaryReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class np0 implements jr0 {

    /* JADX INFO: compiled from: BinaryReader.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8547a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f8547a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8547a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8547a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8547a[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8547a[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8547a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8547a[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8547a[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8547a[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8547a[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8547a[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f8547a[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f8547a[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f8547a[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f8547a[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f8547a[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f8547a[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: compiled from: BinaryReader.java */
    public static final class b extends np0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f8548a;
        public final byte[] b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8549e;
        public int f;

        public b(ByteBuffer byteBuffer, boolean z) {
            super(null);
            this.f8548a = z;
            this.b = byteBuffer.array();
            this.c = byteBuffer.arrayOffset() + byteBuffer.position();
            this.d = byteBuffer.arrayOffset() + byteBuffer.limit();
        }

        public final void A() throws IOException {
            int i = this.f;
            this.f = WireFormat.a(WireFormat.a(this.f8549e), 4);
            while (k() != Integer.MAX_VALUE && n()) {
            }
            if (this.f8549e != this.f) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            this.f = i;
        }

        public final void B() throws IOException {
            int i = this.d;
            int i2 = this.c;
            if (i - i2 >= 10) {
                byte[] bArr = this.b;
                int i3 = 0;
                while (i3 < 10) {
                    int i4 = i2 + 1;
                    if (bArr[i2] >= 0) {
                        this.c = i4;
                        return;
                    } else {
                        i3++;
                        i2 = i4;
                    }
                }
            }
            C();
        }

        public final void C() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (s() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // supwisdom.jr0
        public long a() throws IOException {
            c(0);
            return y();
        }

        @Override // supwisdom.jr0
        public long b() throws IOException {
            c(1);
            return v();
        }

        @Override // supwisdom.jr0
        public int c() throws IOException {
            c(5);
            return t();
        }

        @Override // supwisdom.jr0
        public boolean d() throws IOException {
            c(0);
            return x() != 0;
        }

        @Override // supwisdom.jr0
        public long e() throws IOException {
            c(1);
            return v();
        }

        @Override // supwisdom.jr0
        public int f() throws IOException {
            c(0);
            return x();
        }

        @Override // supwisdom.jr0
        public int g() throws IOException {
            c(0);
            return x();
        }

        @Override // supwisdom.jr0
        public int getTag() {
            return this.f8549e;
        }

        @Override // supwisdom.jr0
        public int h() throws IOException {
            c(0);
            return rp0.e(x());
        }

        @Override // supwisdom.jr0
        public long i() throws IOException {
            c(0);
            return rp0.a(y());
        }

        @Override // supwisdom.jr0
        public String j() throws IOException {
            return a(false);
        }

        @Override // supwisdom.jr0
        public int k() throws IOException {
            if (r()) {
                return Integer.MAX_VALUE;
            }
            int iX = x();
            this.f8549e = iX;
            if (iX == this.f) {
                return Integer.MAX_VALUE;
            }
            return WireFormat.a(iX);
        }

        @Override // supwisdom.jr0
        public ByteString l() throws IOException {
            c(2);
            int iX = x();
            if (iX == 0) {
                return ByteString.EMPTY;
            }
            a(iX);
            ByteString byteStringWrap = this.f8548a ? ByteString.wrap(this.b, this.c, iX) : ByteString.copyFrom(this.b, this.c, iX);
            this.c += iX;
            return byteStringWrap;
        }

        @Override // supwisdom.jr0
        public int m() throws IOException {
            c(0);
            return x();
        }

        @Override // supwisdom.jr0
        public boolean n() throws IOException {
            int i;
            if (r() || (i = this.f8549e) == this.f) {
                return false;
            }
            int iB = WireFormat.b(i);
            if (iB == 0) {
                B();
                return true;
            }
            if (iB == 1) {
                d(8);
                return true;
            }
            if (iB == 2) {
                d(x());
                return true;
            }
            if (iB == 3) {
                A();
                return true;
            }
            if (iB != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            d(4);
            return true;
        }

        @Override // supwisdom.jr0
        public int o() throws IOException {
            c(5);
            return t();
        }

        @Override // supwisdom.jr0
        public long p() throws IOException {
            c(0);
            return y();
        }

        @Override // supwisdom.jr0
        public String q() throws IOException {
            return a(true);
        }

        public final boolean r() {
            return this.c == this.d;
        }

        @Override // supwisdom.jr0
        public double readDouble() throws IOException {
            c(1);
            return Double.longBitsToDouble(v());
        }

        @Override // supwisdom.jr0
        public float readFloat() throws IOException {
            c(5);
            return Float.intBitsToFloat(t());
        }

        public final byte s() throws IOException {
            int i = this.c;
            if (i == this.d) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.b;
            this.c = i + 1;
            return bArr[i];
        }

        public final int t() throws IOException {
            a(4);
            return u();
        }

        public final int u() {
            int i = this.c;
            byte[] bArr = this.b;
            this.c = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        public final long v() throws IOException {
            a(8);
            return w();
        }

        public final long w() {
            int i = this.c;
            byte[] bArr = this.b;
            this.c = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        public final int x() throws IOException {
            int i;
            int i2 = this.c;
            int i3 = this.d;
            if (i3 == i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.b;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.c = i4;
                return b;
            }
            if (i3 - i4 < 9) {
                return (int) z();
            }
            int i5 = i4 + 1;
            int i6 = b ^ (bArr[i4] << 7);
            if (i6 < 0) {
                i = i6 ^ (-128);
            } else {
                int i7 = i5 + 1;
                int i8 = i6 ^ (bArr[i5] << com.umeng.analytics.pro.db.l);
                if (i8 >= 0) {
                    i = i8 ^ 16256;
                } else {
                    i5 = i7 + 1;
                    int i9 = i8 ^ (bArr[i7] << 21);
                    if (i9 < 0) {
                        i = i9 ^ (-2080896);
                    } else {
                        i7 = i5 + 1;
                        byte b2 = bArr[i5];
                        i = (i9 ^ (b2 << 28)) ^ 266354560;
                        if (b2 < 0) {
                            i5 = i7 + 1;
                            if (bArr[i7] < 0) {
                                i7 = i5 + 1;
                                if (bArr[i5] < 0) {
                                    i5 = i7 + 1;
                                    if (bArr[i7] < 0) {
                                        i7 = i5 + 1;
                                        if (bArr[i5] < 0) {
                                            i5 = i7 + 1;
                                            if (bArr[i7] < 0) {
                                                throw InvalidProtocolBufferException.malformedVarint();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                i5 = i7;
            }
            this.c = i5;
            return i;
        }

        public long y() throws IOException {
            long j;
            long j2;
            long j3;
            int i;
            int i2 = this.c;
            int i3 = this.d;
            if (i3 == i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.b;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.c = i4;
                return b;
            }
            if (i3 - i4 < 9) {
                return z();
            }
            int i5 = i4 + 1;
            int i6 = b ^ (bArr[i4] << 7);
            if (i6 >= 0) {
                int i7 = i5 + 1;
                int i8 = i6 ^ (bArr[i5] << com.umeng.analytics.pro.db.l);
                if (i8 >= 0) {
                    i5 = i7;
                    j = i8 ^ 16256;
                } else {
                    i5 = i7 + 1;
                    int i9 = i8 ^ (bArr[i7] << 21);
                    if (i9 < 0) {
                        i = i9 ^ (-2080896);
                    } else {
                        long j4 = i9;
                        int i10 = i5 + 1;
                        long j5 = j4 ^ (((long) bArr[i5]) << 28);
                        if (j5 >= 0) {
                            j3 = 266354560;
                        } else {
                            i5 = i10 + 1;
                            long j6 = j5 ^ (((long) bArr[i10]) << 35);
                            if (j6 < 0) {
                                j2 = -34093383808L;
                            } else {
                                i10 = i5 + 1;
                                j5 = j6 ^ (((long) bArr[i5]) << 42);
                                if (j5 >= 0) {
                                    j3 = 4363953127296L;
                                } else {
                                    i5 = i10 + 1;
                                    j6 = j5 ^ (((long) bArr[i10]) << 49);
                                    if (j6 < 0) {
                                        j2 = -558586000294016L;
                                    } else {
                                        int i11 = i5 + 1;
                                        long j7 = (j6 ^ (((long) bArr[i5]) << 56)) ^ 71499008037633920L;
                                        if (j7 < 0) {
                                            i5 = i11 + 1;
                                            if (bArr[i11] < 0) {
                                                throw InvalidProtocolBufferException.malformedVarint();
                                            }
                                        } else {
                                            i5 = i11;
                                        }
                                        j = j7;
                                    }
                                }
                            }
                            j = j6 ^ j2;
                        }
                        j = j5 ^ j3;
                        i5 = i10;
                    }
                }
                this.c = i5;
                return j;
            }
            i = i6 ^ (-128);
            j = i;
            this.c = i5;
            return j;
        }

        public final long z() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte bS = s();
                j |= ((long) (bS & 127)) << i;
                if ((bS & com.igexin.c.a.d.g.n) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // supwisdom.jr0
        public void j(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (!(list instanceof fq0)) {
                int iB = WireFormat.b(this.f8549e);
                if (iB == 2) {
                    int iX = x();
                    e(iX);
                    int i3 = this.c + iX;
                    while (this.c < i3) {
                        list.add(Integer.valueOf(u()));
                    }
                    return;
                }
                if (iB != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    list.add(Integer.valueOf(c()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            fq0 fq0Var = (fq0) list;
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 2) {
                int iX2 = x();
                e(iX2);
                int i4 = this.c + iX2;
                while (this.c < i4) {
                    fq0Var.c(u());
                }
                return;
            }
            if (iB2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                fq0Var.c(c());
                if (r()) {
                    return;
                } else {
                    i2 = this.c;
                }
            } while (x() == this.f8549e);
            this.c = i2;
        }

        @Override // supwisdom.jr0
        public void q(List<Long> list) throws IOException {
            int i;
            int i2;
            if (!(list instanceof mq0)) {
                int iB = WireFormat.b(this.f8549e);
                if (iB == 1) {
                    do {
                        list.add(Long.valueOf(b()));
                        if (r()) {
                            return;
                        } else {
                            i = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i;
                    return;
                }
                if (iB != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int iX = x();
                f(iX);
                int i3 = this.c + iX;
                while (this.c < i3) {
                    list.add(Long.valueOf(w()));
                }
                return;
            }
            mq0 mq0Var = (mq0) list;
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 1) {
                do {
                    mq0Var.a(b());
                    if (r()) {
                        return;
                    } else {
                        i2 = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i2;
                return;
            }
            if (iB2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iX2 = x();
            f(iX2);
            int i4 = this.c + iX2;
            while (this.c < i4) {
                mq0Var.a(w());
            }
        }

        public String a(boolean z) throws IOException {
            c(2);
            int iX = x();
            if (iX == 0) {
                return "";
            }
            a(iX);
            if (z) {
                byte[] bArr = this.b;
                int i = this.c;
                if (!wr0.d(bArr, i, i + iX)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            String str = new String(this.b, this.c, iX, gq0.f7764a);
            this.c += iX;
            return str;
        }

        @Override // supwisdom.jr0
        public <T> T b(Class<T> cls, xp0 xp0Var) throws IOException {
            c(2);
            return (T) d(fr0.a().a((Class) cls), xp0Var);
        }

        public final <T> T c(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
            int i = this.f;
            this.f = WireFormat.a(WireFormat.a(this.f8549e), 4);
            try {
                T tNewInstance = lr0Var.newInstance();
                lr0Var.a(tNewInstance, this, xp0Var);
                lr0Var.a(tNewInstance);
                if (this.f8549e == this.f) {
                    return tNewInstance;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.f = i;
            }
        }

        public final <T> T d(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
            int iX = x();
            a(iX);
            int i = this.d;
            int i2 = this.c + iX;
            this.d = i2;
            try {
                T tNewInstance = lr0Var.newInstance();
                lr0Var.a(tNewInstance, this, xp0Var);
                lr0Var.a(tNewInstance);
                if (this.c == i2) {
                    return tNewInstance;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.d = i;
            }
        }

        @Override // supwisdom.jr0
        public void e(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof mq0) {
                mq0 mq0Var = (mq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 0) {
                    do {
                        mq0Var.a(a());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                if (iB == 2) {
                    int iX = this.c + x();
                    while (this.c < iX) {
                        mq0Var.a(y());
                    }
                    b(iX);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 0) {
                do {
                    list.add(Long.valueOf(a()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            if (iB2 == 2) {
                int iX2 = this.c + x();
                while (this.c < iX2) {
                    list.add(Long.valueOf(y()));
                }
                b(iX2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void f(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof mq0) {
                mq0 mq0Var = (mq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 0) {
                    do {
                        mq0Var.a(p());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                if (iB == 2) {
                    int iX = this.c + x();
                    while (this.c < iX) {
                        mq0Var.a(y());
                    }
                    b(iX);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 0) {
                do {
                    list.add(Long.valueOf(p()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            if (iB2 == 2) {
                int iX2 = this.c + x();
                while (this.c < iX2) {
                    list.add(Long.valueOf(y()));
                }
                b(iX2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void g(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof mq0) {
                mq0 mq0Var = (mq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 1) {
                    do {
                        mq0Var.a(e());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                if (iB == 2) {
                    int iX = x();
                    f(iX);
                    int i3 = this.c + iX;
                    while (this.c < i3) {
                        mq0Var.a(w());
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 1) {
                do {
                    list.add(Long.valueOf(e()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            if (iB2 == 2) {
                int iX2 = x();
                f(iX2);
                int i4 = this.c + iX2;
                while (this.c < i4) {
                    list.add(Long.valueOf(w()));
                }
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void h(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof fq0) {
                fq0 fq0Var = (fq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 0) {
                    do {
                        fq0Var.c(m());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                if (iB == 2) {
                    int iX = this.c + x();
                    while (this.c < iX) {
                        fq0Var.c(x());
                    }
                    b(iX);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 0) {
                do {
                    list.add(Integer.valueOf(m()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            if (iB2 == 2) {
                int iX2 = this.c + x();
                while (this.c < iX2) {
                    list.add(Integer.valueOf(x()));
                }
                b(iX2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void i(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof fq0) {
                fq0 fq0Var = (fq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB != 0) {
                    if (iB == 2) {
                        int iX = this.c + x();
                        while (this.c < iX) {
                            fq0Var.c(x());
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    fq0Var.c(g());
                    if (r()) {
                        return;
                    } else {
                        i2 = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i2;
                return;
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 != 0) {
                if (iB2 == 2) {
                    int iX2 = this.c + x();
                    while (this.c < iX2) {
                        list.add(Integer.valueOf(x()));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(g()));
                if (r()) {
                    return;
                } else {
                    i = this.c;
                }
            } while (x() == this.f8549e);
            this.c = i;
        }

        @Override // supwisdom.jr0
        public void m(List<String> list) throws IOException {
            a(list, true);
        }

        @Override // supwisdom.jr0
        public void o(List<ByteString> list) throws IOException {
            int i;
            if (WireFormat.b(this.f8549e) == 2) {
                do {
                    list.add(l());
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void p(List<Double> list) throws IOException {
            int i;
            int i2;
            if (list instanceof up0) {
                up0 up0Var = (up0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 1) {
                    do {
                        up0Var.a(readDouble());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                if (iB == 2) {
                    int iX = x();
                    f(iX);
                    int i3 = this.c + iX;
                    while (this.c < i3) {
                        up0Var.a(Double.longBitsToDouble(w()));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 1) {
                do {
                    list.add(Double.valueOf(readDouble()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            if (iB2 == 2) {
                int iX2 = x();
                f(iX2);
                int i4 = this.c + iX2;
                while (this.c < i4) {
                    list.add(Double.valueOf(Double.longBitsToDouble(w())));
                }
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public <T> T b(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
            c(2);
            return (T) d(lr0Var, xp0Var);
        }

        @Override // supwisdom.jr0
        public void k(List<Boolean> list) throws IOException {
            int i;
            int i2;
            if (list instanceof op0) {
                op0 op0Var = (op0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB != 0) {
                    if (iB == 2) {
                        int iX = this.c + x();
                        while (this.c < iX) {
                            op0Var.a(x() != 0);
                        }
                        b(iX);
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    op0Var.a(d());
                    if (r()) {
                        return;
                    } else {
                        i2 = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i2;
                return;
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 != 0) {
                if (iB2 == 2) {
                    int iX2 = this.c + x();
                    while (this.c < iX2) {
                        list.add(Boolean.valueOf(x() != 0));
                    }
                    b(iX2);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Boolean.valueOf(d()));
                if (r()) {
                    return;
                } else {
                    i = this.c;
                }
            } while (x() == this.f8549e);
            this.c = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // supwisdom.jr0
        public <T> void b(List<T> list, lr0<T> lr0Var, xp0 xp0Var) throws IOException {
            int i;
            if (WireFormat.b(this.f8549e) == 3) {
                int i2 = this.f8549e;
                do {
                    list.add(c(lr0Var, xp0Var));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == i2);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void l(List<String> list) throws IOException {
            a(list, false);
        }

        @Override // supwisdom.jr0
        public void n(List<Float> list) throws IOException {
            int i;
            int i2;
            if (list instanceof dq0) {
                dq0 dq0Var = (dq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 2) {
                    int iX = x();
                    e(iX);
                    int i3 = this.c + iX;
                    while (this.c < i3) {
                        dq0Var.a(Float.intBitsToFloat(u()));
                    }
                    return;
                }
                if (iB == 5) {
                    do {
                        dq0Var.a(readFloat());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 2) {
                int iX2 = x();
                e(iX2);
                int i4 = this.c + iX2;
                while (this.c < i4) {
                    list.add(Float.valueOf(Float.intBitsToFloat(u())));
                }
                return;
            }
            if (iB2 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public <T> T a(Class<T> cls, xp0 xp0Var) throws IOException {
            c(3);
            return (T) c(fr0.a().a((Class) cls), xp0Var);
        }

        @Override // supwisdom.jr0
        public <T> T a(lr0<T> lr0Var, xp0 xp0Var) throws IOException {
            c(3);
            return (T) c(lr0Var, xp0Var);
        }

        @Override // supwisdom.jr0
        public void c(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof mq0) {
                mq0 mq0Var = (mq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB != 0) {
                    if (iB == 2) {
                        int iX = this.c + x();
                        while (this.c < iX) {
                            mq0Var.a(rp0.a(y()));
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    mq0Var.a(i());
                    if (r()) {
                        return;
                    } else {
                        i2 = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i2;
                return;
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 != 0) {
                if (iB2 == 2) {
                    int iX2 = this.c + x();
                    while (this.c < iX2) {
                        list.add(Long.valueOf(rp0.a(y())));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Long.valueOf(i()));
                if (r()) {
                    return;
                } else {
                    i = this.c;
                }
            } while (x() == this.f8549e);
            this.c = i;
        }

        public void a(List<String> list, boolean z) throws IOException {
            int i;
            int i2;
            if (WireFormat.b(this.f8549e) == 2) {
                if ((list instanceof kq0) && !z) {
                    kq0 kq0Var = (kq0) list;
                    do {
                        kq0Var.a(l());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                do {
                    list.add(a(z));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void b(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof fq0) {
                fq0 fq0Var = (fq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB == 2) {
                    int iX = x();
                    e(iX);
                    int i3 = this.c + iX;
                    while (this.c < i3) {
                        fq0Var.c(u());
                    }
                    return;
                }
                if (iB == 5) {
                    do {
                        fq0Var.c(o());
                        if (r()) {
                            return;
                        } else {
                            i2 = this.c;
                        }
                    } while (x() == this.f8549e);
                    this.c = i2;
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 == 2) {
                int iX2 = x();
                e(iX2);
                int i4 = this.c + iX2;
                while (this.c < i4) {
                    list.add(Integer.valueOf(u()));
                }
                return;
            }
            if (iB2 == 5) {
                do {
                    list.add(Integer.valueOf(o()));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // supwisdom.jr0
        public void d(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof fq0) {
                fq0 fq0Var = (fq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB != 0) {
                    if (iB == 2) {
                        int iX = this.c + x();
                        while (this.c < iX) {
                            fq0Var.c(x());
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    fq0Var.c(f());
                    if (r()) {
                        return;
                    } else {
                        i2 = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i2;
                return;
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 != 0) {
                if (iB2 == 2) {
                    int iX2 = this.c + x();
                    while (this.c < iX2) {
                        list.add(Integer.valueOf(x()));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(f()));
                if (r()) {
                    return;
                } else {
                    i = this.c;
                }
            } while (x() == this.f8549e);
            this.c = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // supwisdom.jr0
        public <T> void a(List<T> list, lr0<T> lr0Var, xp0 xp0Var) throws IOException {
            int i;
            if (WireFormat.b(this.f8549e) == 2) {
                int i2 = this.f8549e;
                do {
                    list.add(d(lr0Var, xp0Var));
                    if (r()) {
                        return;
                    } else {
                        i = this.c;
                    }
                } while (x() == i2);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        public final void e(int i) throws IOException {
            a(i);
            if ((i & 3) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        public final void f(int i) throws IOException {
            a(i);
            if ((i & 7) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        @Override // supwisdom.jr0
        public void a(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof fq0) {
                fq0 fq0Var = (fq0) list;
                int iB = WireFormat.b(this.f8549e);
                if (iB != 0) {
                    if (iB == 2) {
                        int iX = this.c + x();
                        while (this.c < iX) {
                            fq0Var.c(rp0.e(x()));
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    fq0Var.c(h());
                    if (r()) {
                        return;
                    } else {
                        i2 = this.c;
                    }
                } while (x() == this.f8549e);
                this.c = i2;
                return;
            }
            int iB2 = WireFormat.b(this.f8549e);
            if (iB2 != 0) {
                if (iB2 == 2) {
                    int iX2 = this.c + x();
                    while (this.c < iX2) {
                        list.add(Integer.valueOf(rp0.e(x())));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(h()));
                if (r()) {
                    return;
                } else {
                    i = this.c;
                }
            } while (x() == this.f8549e);
            this.c = i;
        }

        public final void c(int i) throws IOException {
            if (WireFormat.b(this.f8549e) != i) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public final void d(int i) throws IOException {
            a(i);
            this.c += i;
        }

        public final void b(int i) throws IOException {
            if (this.c != i) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // supwisdom.jr0
        public <K, V> void a(Map<K, V> map, oq0.a<K, V> aVar, xp0 xp0Var) throws IOException {
            c(2);
            int iX = x();
            a(iX);
            int i = this.d;
            this.d = this.c + iX;
            try {
                Object objA = aVar.b;
                Object objA2 = aVar.d;
                while (true) {
                    int iK = k();
                    if (iK == Integer.MAX_VALUE) {
                        map.put(objA, objA2);
                        return;
                    }
                    if (iK == 1) {
                        objA = a(aVar.f8699a, (Class<?>) null, (xp0) null);
                    } else if (iK != 2) {
                        try {
                            if (!n()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            }
                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                            if (!n()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            }
                        }
                    } else {
                        objA2 = a(aVar.c, aVar.d.getClass(), xp0Var);
                    }
                }
            } finally {
                this.d = i;
            }
        }

        public final Object a(WireFormat.FieldType fieldType, Class<?> cls, xp0 xp0Var) throws IOException {
            switch (a.f8547a[fieldType.ordinal()]) {
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
            if (i < 0 || i > this.d - this.c) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
    }

    public /* synthetic */ np0(a aVar) {
        this();
    }

    public static np0 a(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return new b(byteBuffer, z);
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    public np0() {
    }
}
