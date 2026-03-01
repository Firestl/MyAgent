package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: CodedInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rp0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9082a;
    public int b;
    public int c;
    public sp0 d;

    /* JADX INFO: compiled from: CodedInputStream.java */
    public static final class b extends rp0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final byte[] f9083e;
        public final boolean f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public boolean l;
        public int m;

        public final void A() {
            int i = this.g + this.h;
            this.g = i;
            int i2 = i - this.j;
            int i3 = this.m;
            if (i2 <= i3) {
                this.h = 0;
                return;
            }
            int i4 = i2 - i3;
            this.h = i4;
            this.g = i - i4;
        }

        public void B() throws IOException {
            int iR;
            do {
                iR = r();
                if (iR == 0) {
                    return;
                }
            } while (d(iR));
        }

        public final void C() throws IOException {
            if (this.g - this.i >= 10) {
                D();
            } else {
                E();
            }
        }

        public final void D() throws IOException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.f9083e;
                int i2 = this.i;
                this.i = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void E() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (u() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // supwisdom.rp0
        public void a(int i) throws InvalidProtocolBufferException {
            if (this.k != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // supwisdom.rp0
        public void b(int i) {
            this.m = i;
            A();
        }

        @Override // supwisdom.rp0
        public boolean c() throws IOException {
            return y() != 0;
        }

        @Override // supwisdom.rp0
        public boolean d(int i) throws IOException {
            int iB = WireFormat.b(i);
            if (iB == 0) {
                C();
                return true;
            }
            if (iB == 1) {
                g(8);
                return true;
            }
            if (iB == 2) {
                g(x());
                return true;
            }
            if (iB == 3) {
                B();
                a(WireFormat.a(WireFormat.a(i), 4));
                return true;
            }
            if (iB == 4) {
                return false;
            }
            if (iB != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            g(4);
            return true;
        }

        @Override // supwisdom.rp0
        public double e() throws IOException {
            return Double.longBitsToDouble(w());
        }

        @Override // supwisdom.rp0
        public int f() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public int g() throws IOException {
            return v();
        }

        @Override // supwisdom.rp0
        public long h() throws IOException {
            return w();
        }

        @Override // supwisdom.rp0
        public float i() throws IOException {
            return Float.intBitsToFloat(v());
        }

        @Override // supwisdom.rp0
        public int j() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public long k() throws IOException {
            return y();
        }

        @Override // supwisdom.rp0
        public int l() throws IOException {
            return v();
        }

        @Override // supwisdom.rp0
        public long m() throws IOException {
            return w();
        }

        @Override // supwisdom.rp0
        public int n() throws IOException {
            return rp0.e(x());
        }

        @Override // supwisdom.rp0
        public long o() throws IOException {
            return rp0.a(y());
        }

        @Override // supwisdom.rp0
        public String p() throws IOException {
            int iX = x();
            if (iX > 0 && iX <= this.g - this.i) {
                String str = new String(this.f9083e, this.i, iX, gq0.f7764a);
                this.i += iX;
                return str;
            }
            if (iX == 0) {
                return "";
            }
            if (iX < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // supwisdom.rp0
        public String q() throws IOException {
            int iX = x();
            if (iX > 0) {
                int i = this.g;
                int i2 = this.i;
                if (iX <= i - i2) {
                    String strB = wr0.b(this.f9083e, i2, iX);
                    this.i += iX;
                    return strB;
                }
            }
            if (iX == 0) {
                return "";
            }
            if (iX <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // supwisdom.rp0
        public int r() throws IOException {
            if (b()) {
                this.k = 0;
                return 0;
            }
            int iX = x();
            this.k = iX;
            if (WireFormat.a(iX) != 0) {
                return this.k;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // supwisdom.rp0
        public int s() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public long t() throws IOException {
            return y();
        }

        public byte u() throws IOException {
            int i = this.i;
            if (i == this.g) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.f9083e;
            this.i = i + 1;
            return bArr[i];
        }

        public int v() throws IOException {
            int i = this.i;
            if (this.g - i < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.f9083e;
            this.i = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        public long w() throws IOException {
            int i = this.i;
            if (this.g - i < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.f9083e;
            this.i = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r2[r3] < 0) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int x() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.i
                int r1 = r5.g
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.f9083e
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.i = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.z()
                int r1 = (int) r0
                return r1
            L70:
                r5.i = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.rp0.b.x():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        
            if (r2[r0] < 0) goto L40;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long y() throws java.io.IOException {
            /*
                r11 = this;
                int r0 = r11.i
                int r1 = r11.g
                if (r1 != r0) goto L8
                goto Lb6
            L8:
                byte[] r2 = r11.f9083e
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L14
                r11.i = r3
                long r0 = (long) r0
                return r0
            L14:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L1b
                goto Lb6
            L1b:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L29
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            L26:
                long r2 = (long) r0
                goto Lbd
            L29:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3a
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                long r0 = (long) r0
                r9 = r0
                r1 = r3
                r2 = r9
                goto Lbd
            L3a:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L48
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L26
            L48:
                long r3 = (long) r0
                int r0 = r1 + 1
                r1 = r2[r1]
                long r5 = (long) r1
                r1 = 28
                long r5 = r5 << r1
                long r3 = r3 ^ r5
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L5f
                r1 = 266354560(0xfe03f80, double:1.315966377E-315)
            L5b:
                long r2 = r3 ^ r1
                r1 = r0
                goto Lbd
            L5f:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 35
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L74
                r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
            L71:
                long r2 = r3 ^ r5
                goto Lbd
            L74:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 42
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L87
                r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
                goto L5b
            L87:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 49
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L9a
                r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
                goto L71
            L9a:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 56
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 >= 0) goto Lbb
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r0 >= 0) goto Lbc
            Lb6:
                long r0 = r11.z()
                return r0
            Lbb:
                r1 = r0
            Lbc:
                r2 = r3
            Lbd:
                r11.i = r1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.rp0.b.y():long");
        }

        public long z() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte bU = u();
                j |= ((long) (bU & 127)) << i;
                if ((bU & com.igexin.c.a.d.g.n) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public b(byte[] bArr, int i, int i2, boolean z) {
            super();
            this.m = Integer.MAX_VALUE;
            this.f9083e = bArr;
            this.g = i2 + i;
            this.i = i;
            this.j = i;
            this.f = z;
        }

        @Override // supwisdom.rp0
        public int c(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int iA = i + a();
            int i2 = this.m;
            if (iA > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.m = iA;
            A();
            return i2;
        }

        public byte[] f(int i) throws IOException {
            if (i > 0) {
                int i2 = this.g;
                int i3 = this.i;
                if (i <= i2 - i3) {
                    int i4 = i + i3;
                    this.i = i4;
                    return Arrays.copyOfRange(this.f9083e, i3, i4);
                }
            }
            if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i == 0) {
                return gq0.b;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public void g(int i) throws IOException {
            if (i >= 0) {
                int i2 = this.g;
                int i3 = this.i;
                if (i <= i2 - i3) {
                    this.i = i3 + i;
                    return;
                }
            }
            if (i >= 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // supwisdom.rp0
        public int a() {
            return this.i - this.j;
        }

        @Override // supwisdom.rp0
        public boolean b() throws IOException {
            return this.i == this.g;
        }

        @Override // supwisdom.rp0
        public ByteString d() throws IOException {
            ByteString byteStringCopyFrom;
            int iX = x();
            if (iX > 0) {
                int i = this.g;
                int i2 = this.i;
                if (iX <= i - i2) {
                    if (this.f && this.l) {
                        byteStringCopyFrom = ByteString.wrap(this.f9083e, i2, iX);
                    } else {
                        byteStringCopyFrom = ByteString.copyFrom(this.f9083e, this.i, iX);
                    }
                    this.i += iX;
                    return byteStringCopyFrom;
                }
            }
            if (iX == 0) {
                return ByteString.EMPTY;
            }
            return ByteString.wrap(f(iX));
        }
    }

    /* JADX INFO: compiled from: CodedInputStream.java */
    public static final class c extends rp0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final InputStream f9084e;
        public final byte[] f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public a m;

        /* JADX INFO: compiled from: CodedInputStream.java */
        public interface a {
            void a();
        }

        public final void A() {
            int i = this.g + this.h;
            this.g = i;
            int i2 = this.k + i;
            int i3 = this.l;
            if (i2 <= i3) {
                this.h = 0;
                return;
            }
            int i4 = i2 - i3;
            this.h = i4;
            this.g = i - i4;
        }

        public void B() throws IOException {
            int iR;
            do {
                iR = r();
                if (iR == 0) {
                    return;
                }
            } while (d(iR));
        }

        public final void C() throws IOException {
            if (this.g - this.i >= 10) {
                D();
            } else {
                E();
            }
        }

        public final void D() throws IOException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.f;
                int i2 = this.i;
                this.i = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void E() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (u() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // supwisdom.rp0
        public void a(int i) throws InvalidProtocolBufferException {
            if (this.j != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // supwisdom.rp0
        public void b(int i) {
            this.l = i;
            A();
        }

        @Override // supwisdom.rp0
        public boolean c() throws IOException {
            return y() != 0;
        }

        @Override // supwisdom.rp0
        public boolean d(int i) throws IOException {
            int iB = WireFormat.b(i);
            if (iB == 0) {
                C();
                return true;
            }
            if (iB == 1) {
                j(8);
                return true;
            }
            if (iB == 2) {
                j(x());
                return true;
            }
            if (iB == 3) {
                B();
                a(WireFormat.a(WireFormat.a(i), 4));
                return true;
            }
            if (iB == 4) {
                return false;
            }
            if (iB != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            j(4);
            return true;
        }

        @Override // supwisdom.rp0
        public double e() throws IOException {
            return Double.longBitsToDouble(w());
        }

        @Override // supwisdom.rp0
        public int f() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public int g() throws IOException {
            return v();
        }

        @Override // supwisdom.rp0
        public long h() throws IOException {
            return w();
        }

        @Override // supwisdom.rp0
        public float i() throws IOException {
            return Float.intBitsToFloat(v());
        }

        @Override // supwisdom.rp0
        public int j() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public long k() throws IOException {
            return y();
        }

        @Override // supwisdom.rp0
        public int l() throws IOException {
            return v();
        }

        @Override // supwisdom.rp0
        public long m() throws IOException {
            return w();
        }

        @Override // supwisdom.rp0
        public int n() throws IOException {
            return rp0.e(x());
        }

        @Override // supwisdom.rp0
        public long o() throws IOException {
            return rp0.a(y());
        }

        @Override // supwisdom.rp0
        public String p() throws IOException {
            int iX = x();
            if (iX > 0 && iX <= this.g - this.i) {
                String str = new String(this.f, this.i, iX, gq0.f7764a);
                this.i += iX;
                return str;
            }
            if (iX == 0) {
                return "";
            }
            if (iX > this.g) {
                return new String(a(iX, false), gq0.f7764a);
            }
            i(iX);
            String str2 = new String(this.f, this.i, iX, gq0.f7764a);
            this.i += iX;
            return str2;
        }

        @Override // supwisdom.rp0
        public String q() throws IOException {
            byte[] bArrA;
            int iX = x();
            int i = this.i;
            if (iX <= this.g - i && iX > 0) {
                bArrA = this.f;
                this.i = i + iX;
            } else {
                if (iX == 0) {
                    return "";
                }
                if (iX <= this.g) {
                    i(iX);
                    bArrA = this.f;
                    this.i = iX + 0;
                } else {
                    bArrA = a(iX, false);
                }
                i = 0;
            }
            return wr0.b(bArrA, i, iX);
        }

        @Override // supwisdom.rp0
        public int r() throws IOException {
            if (b()) {
                this.j = 0;
                return 0;
            }
            int iX = x();
            this.j = iX;
            if (WireFormat.a(iX) != 0) {
                return this.j;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // supwisdom.rp0
        public int s() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public long t() throws IOException {
            return y();
        }

        public byte u() throws IOException {
            if (this.i == this.g) {
                i(1);
            }
            byte[] bArr = this.f;
            int i = this.i;
            this.i = i + 1;
            return bArr[i];
        }

        public int v() throws IOException {
            int i = this.i;
            if (this.g - i < 4) {
                i(4);
                i = this.i;
            }
            byte[] bArr = this.f;
            this.i = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        public long w() throws IOException {
            int i = this.i;
            if (this.g - i < 8) {
                i(8);
                i = this.i;
            }
            byte[] bArr = this.f;
            this.i = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r2[r3] < 0) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int x() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.i
                int r1 = r5.g
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.f
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.i = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.z()
                int r1 = (int) r0
                return r1
            L70:
                r5.i = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.rp0.c.x():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        
            if (r2[r0] < 0) goto L40;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long y() throws java.io.IOException {
            /*
                r11 = this;
                int r0 = r11.i
                int r1 = r11.g
                if (r1 != r0) goto L8
                goto Lb6
            L8:
                byte[] r2 = r11.f
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L14
                r11.i = r3
                long r0 = (long) r0
                return r0
            L14:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L1b
                goto Lb6
            L1b:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L29
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            L26:
                long r2 = (long) r0
                goto Lbd
            L29:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3a
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                long r0 = (long) r0
                r9 = r0
                r1 = r3
                r2 = r9
                goto Lbd
            L3a:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L48
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L26
            L48:
                long r3 = (long) r0
                int r0 = r1 + 1
                r1 = r2[r1]
                long r5 = (long) r1
                r1 = 28
                long r5 = r5 << r1
                long r3 = r3 ^ r5
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L5f
                r1 = 266354560(0xfe03f80, double:1.315966377E-315)
            L5b:
                long r2 = r3 ^ r1
                r1 = r0
                goto Lbd
            L5f:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 35
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L74
                r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
            L71:
                long r2 = r3 ^ r5
                goto Lbd
            L74:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 42
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L87
                r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
                goto L5b
            L87:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 49
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L9a
                r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
                goto L71
            L9a:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 56
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 >= 0) goto Lbb
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r0 >= 0) goto Lbc
            Lb6:
                long r0 = r11.z()
                return r0
            Lbb:
                r1 = r0
            Lbc:
                r2 = r3
            Lbd:
                r11.i = r1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.rp0.c.y():long");
        }

        public long z() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte bU = u();
                j |= ((long) (bU & 127)) << i;
                if ((bU & com.igexin.c.a.d.g.n) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public c(InputStream inputStream, int i) {
            super();
            this.l = Integer.MAX_VALUE;
            this.m = null;
            gq0.a(inputStream, "input");
            this.f9084e = inputStream;
            this.f = new byte[i];
            this.g = 0;
            this.i = 0;
            this.k = 0;
        }

        @Override // supwisdom.rp0
        public int c(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = i + this.k + this.i;
            int i3 = this.l;
            if (i2 > i3) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.l = i2;
            A();
            return i3;
        }

        public final ByteString f(int i) throws IOException {
            byte[] bArrG = g(i);
            if (bArrG != null) {
                return ByteString.copyFrom(bArrG);
            }
            int i2 = this.i;
            int i3 = this.g;
            int length = i3 - i2;
            this.k += i3;
            this.i = 0;
            this.g = 0;
            List<byte[]> listH = h(i - length);
            byte[] bArr = new byte[i];
            System.arraycopy(this.f, i2, bArr, 0, length);
            for (byte[] bArr2 : listH) {
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return ByteString.wrap(bArr);
        }

        public final byte[] g(int i) throws IOException {
            if (i == 0) {
                return gq0.b;
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.k;
            int i3 = this.i;
            int i4 = i2 + i3 + i;
            if (i4 - this.c > 0) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            int i5 = this.l;
            if (i4 > i5) {
                j((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i6 = this.g - i3;
            int i7 = i - i6;
            if (i7 >= 4096 && i7 > this.f9084e.available()) {
                return null;
            }
            byte[] bArr = new byte[i];
            System.arraycopy(this.f, this.i, bArr, 0, i6);
            this.k += this.g;
            this.i = 0;
            this.g = 0;
            while (i6 < i) {
                int i8 = this.f9084e.read(bArr, i6, i - i6);
                if (i8 == -1) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                this.k += i8;
                i6 += i8;
            }
            return bArr;
        }

        public final List<byte[]> h(int i) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (i > 0) {
                int iMin = Math.min(i, 4096);
                byte[] bArr = new byte[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    int i3 = this.f9084e.read(bArr, i2, iMin - i2);
                    if (i3 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.k += i3;
                    i2 += i3;
                }
                i -= iMin;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        public final void i(int i) throws IOException {
            if (l(i)) {
                return;
            }
            if (i <= (this.c - this.k) - this.i) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.sizeLimitExceeded();
        }

        public void j(int i) throws IOException {
            int i2 = this.g;
            int i3 = this.i;
            if (i > i2 - i3 || i < 0) {
                k(i);
            } else {
                this.i = i3 + i;
            }
        }

        public final void k(int i) throws IOException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.k;
            int i3 = this.i;
            int i4 = i2 + i3 + i;
            int i5 = this.l;
            if (i4 > i5) {
                j((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i6 = 0;
            if (this.m == null) {
                this.k = i2 + i3;
                int i7 = this.g - i3;
                this.g = 0;
                this.i = 0;
                i6 = i7;
                while (i6 < i) {
                    try {
                        long j = i - i6;
                        long jSkip = this.f9084e.skip(j);
                        if (jSkip < 0 || jSkip > j) {
                            throw new IllegalStateException(this.f9084e.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                        }
                        if (jSkip == 0) {
                            break;
                        } else {
                            i6 += (int) jSkip;
                        }
                    } finally {
                        this.k += i6;
                        A();
                    }
                }
            }
            if (i6 >= i) {
                return;
            }
            int i8 = this.g;
            int i9 = i8 - this.i;
            this.i = i8;
            i(1);
            while (true) {
                int i10 = i - i9;
                int i11 = this.g;
                if (i10 <= i11) {
                    this.i = i10;
                    return;
                } else {
                    i9 += i11;
                    this.i = i11;
                    i(1);
                }
            }
        }

        public final boolean l(int i) throws IOException {
            int i2 = this.i;
            if (i2 + i <= this.g) {
                throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
            }
            int i3 = this.c;
            int i4 = this.k;
            if (i > (i3 - i4) - i2 || i4 + i2 + i > this.l) {
                return false;
            }
            a aVar = this.m;
            if (aVar != null) {
                aVar.a();
            }
            int i5 = this.i;
            if (i5 > 0) {
                int i6 = this.g;
                if (i6 > i5) {
                    byte[] bArr = this.f;
                    System.arraycopy(bArr, i5, bArr, 0, i6 - i5);
                }
                this.k += i5;
                this.g -= i5;
                this.i = 0;
            }
            InputStream inputStream = this.f9084e;
            byte[] bArr2 = this.f;
            int i7 = this.g;
            int i8 = inputStream.read(bArr2, i7, Math.min(bArr2.length - i7, (this.c - this.k) - i7));
            if (i8 == 0 || i8 < -1 || i8 > this.f.length) {
                throw new IllegalStateException(this.f9084e.getClass() + "#read(byte[]) returned invalid result: " + i8 + "\nThe InputStream implementation is buggy.");
            }
            if (i8 <= 0) {
                return false;
            }
            this.g += i8;
            A();
            if (this.g >= i) {
                return true;
            }
            return l(i);
        }

        @Override // supwisdom.rp0
        public int a() {
            return this.k + this.i;
        }

        @Override // supwisdom.rp0
        public boolean b() throws IOException {
            return this.i == this.g && !l(1);
        }

        public final byte[] a(int i, boolean z) throws IOException {
            byte[] bArrG = g(i);
            if (bArrG != null) {
                return z ? (byte[]) bArrG.clone() : bArrG;
            }
            int i2 = this.i;
            int i3 = this.g;
            int length = i3 - i2;
            this.k += i3;
            this.i = 0;
            this.g = 0;
            List<byte[]> listH = h(i - length);
            byte[] bArr = new byte[i];
            System.arraycopy(this.f, i2, bArr, 0, length);
            for (byte[] bArr2 : listH) {
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return bArr;
        }

        @Override // supwisdom.rp0
        public ByteString d() throws IOException {
            int iX = x();
            int i = this.g;
            int i2 = this.i;
            if (iX > i - i2 || iX <= 0) {
                if (iX == 0) {
                    return ByteString.EMPTY;
                }
                return f(iX);
            }
            ByteString byteStringCopyFrom = ByteString.copyFrom(this.f, i2, iX);
            this.i += iX;
            return byteStringCopyFrom;
        }
    }

    /* JADX INFO: compiled from: CodedInputStream.java */
    public static final class d extends rp0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ByteBuffer f9085e;
        public final boolean f;
        public final long g;
        public long h;
        public long i;
        public long j;
        public int k;
        public int l;
        public boolean m;
        public int n;

        public static boolean G() {
            return vr0.e();
        }

        public final void A() {
            long j = this.h + ((long) this.k);
            this.h = j;
            int i = (int) (j - this.j);
            int i2 = this.n;
            if (i <= i2) {
                this.k = 0;
                return;
            }
            int i3 = i - i2;
            this.k = i3;
            this.h = j - ((long) i3);
        }

        public final int B() {
            return (int) (this.h - this.i);
        }

        public void C() throws IOException {
            int iR;
            do {
                iR = r();
                if (iR == 0) {
                    return;
                }
            } while (d(iR));
        }

        public final void D() throws IOException {
            if (B() >= 10) {
                E();
            } else {
                F();
            }
        }

        public final void E() throws IOException {
            for (int i = 0; i < 10; i++) {
                long j = this.i;
                this.i = 1 + j;
                if (vr0.a(j) >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void F() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (u() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // supwisdom.rp0
        public void a(int i) throws InvalidProtocolBufferException {
            if (this.l != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // supwisdom.rp0
        public void b(int i) {
            this.n = i;
            A();
        }

        @Override // supwisdom.rp0
        public boolean c() throws IOException {
            return y() != 0;
        }

        @Override // supwisdom.rp0
        public boolean d(int i) throws IOException {
            int iB = WireFormat.b(i);
            if (iB == 0) {
                D();
                return true;
            }
            if (iB == 1) {
                f(8);
                return true;
            }
            if (iB == 2) {
                f(x());
                return true;
            }
            if (iB == 3) {
                C();
                a(WireFormat.a(WireFormat.a(i), 4));
                return true;
            }
            if (iB == 4) {
                return false;
            }
            if (iB != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            f(4);
            return true;
        }

        @Override // supwisdom.rp0
        public double e() throws IOException {
            return Double.longBitsToDouble(w());
        }

        @Override // supwisdom.rp0
        public int f() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public int g() throws IOException {
            return v();
        }

        @Override // supwisdom.rp0
        public long h() throws IOException {
            return w();
        }

        @Override // supwisdom.rp0
        public float i() throws IOException {
            return Float.intBitsToFloat(v());
        }

        @Override // supwisdom.rp0
        public int j() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public long k() throws IOException {
            return y();
        }

        @Override // supwisdom.rp0
        public int l() throws IOException {
            return v();
        }

        @Override // supwisdom.rp0
        public long m() throws IOException {
            return w();
        }

        @Override // supwisdom.rp0
        public int n() throws IOException {
            return rp0.e(x());
        }

        @Override // supwisdom.rp0
        public long o() throws IOException {
            return rp0.a(y());
        }

        @Override // supwisdom.rp0
        public String p() throws IOException {
            int iX = x();
            if (iX <= 0 || iX > B()) {
                if (iX == 0) {
                    return "";
                }
                if (iX < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = new byte[iX];
            long j = iX;
            vr0.a(this.i, bArr, 0L, j);
            String str = new String(bArr, gq0.f7764a);
            this.i += j;
            return str;
        }

        @Override // supwisdom.rp0
        public String q() throws IOException {
            int iX = x();
            if (iX > 0 && iX <= B()) {
                String strB = wr0.b(this.f9085e, b(this.i), iX);
                this.i += (long) iX;
                return strB;
            }
            if (iX == 0) {
                return "";
            }
            if (iX <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // supwisdom.rp0
        public int r() throws IOException {
            if (b()) {
                this.l = 0;
                return 0;
            }
            int iX = x();
            this.l = iX;
            if (WireFormat.a(iX) != 0) {
                return this.l;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // supwisdom.rp0
        public int s() throws IOException {
            return x();
        }

        @Override // supwisdom.rp0
        public long t() throws IOException {
            return y();
        }

        public byte u() throws IOException {
            long j = this.i;
            if (j == this.h) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.i = 1 + j;
            return vr0.a(j);
        }

        public int v() throws IOException {
            long j = this.i;
            if (this.h - j < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.i = 4 + j;
            return ((vr0.a(j + 3) & 255) << 24) | (vr0.a(j) & 255) | ((vr0.a(1 + j) & 255) << 8) | ((vr0.a(2 + j) & 255) << 16);
        }

        public long w() throws IOException {
            long j = this.i;
            if (this.h - j < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.i = 8 + j;
            return ((((long) vr0.a(j + 7)) & 255) << 56) | (((long) vr0.a(j)) & 255) | ((((long) vr0.a(1 + j)) & 255) << 8) | ((((long) vr0.a(2 + j)) & 255) << 16) | ((((long) vr0.a(3 + j)) & 255) << 24) | ((((long) vr0.a(4 + j)) & 255) << 32) | ((((long) vr0.a(5 + j)) & 255) << 40) | ((((long) vr0.a(6 + j)) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        
            if (supwisdom.vr0.a(r4) < 0) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int x() throws java.io.IOException {
            /*
                r10 = this;
                long r0 = r10.i
                long r2 = r10.h
                int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r4 != 0) goto La
                goto L85
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = supwisdom.vr0.a(r0)
                if (r0 < 0) goto L17
                r10.i = r4
                return r0
            L17:
                long r6 = r10.h
                long r6 = r6 - r4
                r8 = 9
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L21
                goto L85
            L21:
                long r6 = r4 + r2
                byte r1 = supwisdom.vr0.a(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L2f
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L8b
            L2f:
                long r4 = r6 + r2
                byte r1 = supwisdom.vr0.a(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3e
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L3c:
                r6 = r4
                goto L8b
            L3e:
                long r6 = r4 + r2
                byte r1 = supwisdom.vr0.a(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L4e
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L8b
            L4e:
                long r4 = r6 + r2
                byte r1 = supwisdom.vr0.a(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = supwisdom.vr0.a(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = supwisdom.vr0.a(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = supwisdom.vr0.a(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = supwisdom.vr0.a(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = supwisdom.vr0.a(r4)
                if (r1 >= 0) goto L8b
            L85:
                long r0 = r10.z()
                int r1 = (int) r0
                return r1
            L8b:
                r10.i = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.rp0.d.x():int");
        }

        public long y() throws IOException {
            long jA;
            long j;
            long j2;
            int i;
            long j3 = this.i;
            if (this.h != j3) {
                long j4 = j3 + 1;
                byte bA = vr0.a(j3);
                if (bA >= 0) {
                    this.i = j4;
                    return bA;
                }
                if (this.h - j4 >= 9) {
                    long j5 = j4 + 1;
                    int iA = bA ^ (vr0.a(j4) << 7);
                    if (iA >= 0) {
                        long j6 = j5 + 1;
                        int iA2 = iA ^ (vr0.a(j5) << com.umeng.analytics.pro.db.l);
                        if (iA2 >= 0) {
                            jA = iA2 ^ 16256;
                        } else {
                            j5 = j6 + 1;
                            int iA3 = iA2 ^ (vr0.a(j6) << 21);
                            if (iA3 < 0) {
                                i = iA3 ^ (-2080896);
                            } else {
                                j6 = j5 + 1;
                                long jA2 = ((long) iA3) ^ (((long) vr0.a(j5)) << 28);
                                if (jA2 < 0) {
                                    long j7 = j6 + 1;
                                    long jA3 = jA2 ^ (((long) vr0.a(j6)) << 35);
                                    if (jA3 < 0) {
                                        j = -34093383808L;
                                    } else {
                                        j6 = j7 + 1;
                                        jA2 = jA3 ^ (((long) vr0.a(j7)) << 42);
                                        if (jA2 >= 0) {
                                            j2 = 4363953127296L;
                                        } else {
                                            j7 = j6 + 1;
                                            jA3 = jA2 ^ (((long) vr0.a(j6)) << 49);
                                            if (jA3 >= 0) {
                                                j6 = j7 + 1;
                                                jA = (jA3 ^ (((long) vr0.a(j7)) << 56)) ^ 71499008037633920L;
                                                if (jA < 0) {
                                                    long j8 = 1 + j6;
                                                    if (vr0.a(j6) >= 0) {
                                                        j5 = j8;
                                                    }
                                                }
                                                this.i = j5;
                                                return jA;
                                            }
                                            j = -558586000294016L;
                                        }
                                    }
                                    jA = jA3 ^ j;
                                    j5 = j7;
                                    this.i = j5;
                                    return jA;
                                }
                                j2 = 266354560;
                                jA = jA2 ^ j2;
                            }
                        }
                        j5 = j6;
                        this.i = j5;
                        return jA;
                    }
                    i = iA ^ (-128);
                    jA = i;
                    this.i = j5;
                    return jA;
                }
            }
            return z();
        }

        public long z() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte bU = u();
                j |= ((long) (bU & 127)) << i;
                if ((bU & com.igexin.c.a.d.g.n) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public d(ByteBuffer byteBuffer, boolean z) {
            super();
            this.n = Integer.MAX_VALUE;
            this.f9085e = byteBuffer;
            long jA = vr0.a(byteBuffer);
            this.g = jA;
            this.h = jA + ((long) byteBuffer.limit());
            long jPosition = this.g + ((long) byteBuffer.position());
            this.i = jPosition;
            this.j = jPosition;
            this.f = z;
        }

        @Override // supwisdom.rp0
        public int c(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int iA = i + a();
            int i2 = this.n;
            if (iA > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.n = iA;
            A();
            return i2;
        }

        public void f(int i) throws IOException {
            if (i >= 0 && i <= B()) {
                this.i += (long) i;
            } else {
                if (i >= 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        @Override // supwisdom.rp0
        public int a() {
            return (int) (this.i - this.j);
        }

        @Override // supwisdom.rp0
        public boolean b() throws IOException {
            return this.i == this.h;
        }

        public final ByteBuffer a(long j, long j2) throws IOException {
            int iPosition = this.f9085e.position();
            int iLimit = this.f9085e.limit();
            try {
                try {
                    this.f9085e.position(b(j));
                    this.f9085e.limit(b(j2));
                    return this.f9085e.slice();
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.f9085e.position(iPosition);
                this.f9085e.limit(iLimit);
            }
        }

        public final int b(long j) {
            return (int) (j - this.g);
        }

        @Override // supwisdom.rp0
        public ByteString d() throws IOException {
            int iX = x();
            if (iX <= 0 || iX > B()) {
                if (iX == 0) {
                    return ByteString.EMPTY;
                }
                if (iX < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (this.f && this.m) {
                long j = this.i;
                long j2 = iX;
                ByteBuffer byteBufferA = a(j, j + j2);
                this.i += j2;
                return ByteString.wrap(byteBufferA);
            }
            byte[] bArr = new byte[iX];
            long j3 = iX;
            vr0.a(this.i, bArr, 0L, j3);
            this.i += j3;
            return ByteString.wrap(bArr);
        }
    }

    public static long a(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static rp0 a(InputStream inputStream) {
        return a(inputStream, 4096);
    }

    public static int e(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract int a();

    public abstract void a(int i) throws InvalidProtocolBufferException;

    public abstract void b(int i);

    public abstract boolean b() throws IOException;

    public abstract int c(int i) throws InvalidProtocolBufferException;

    public abstract boolean c() throws IOException;

    public abstract ByteString d() throws IOException;

    public abstract boolean d(int i) throws IOException;

    public abstract double e() throws IOException;

    public abstract int f() throws IOException;

    public abstract int g() throws IOException;

    public abstract long h() throws IOException;

    public abstract float i() throws IOException;

    public abstract int j() throws IOException;

    public abstract long k() throws IOException;

    public abstract int l() throws IOException;

    public abstract long m() throws IOException;

    public abstract int n() throws IOException;

    public abstract long o() throws IOException;

    public abstract String p() throws IOException;

    public abstract String q() throws IOException;

    public abstract int r() throws IOException;

    public abstract int s() throws IOException;

    public abstract long t() throws IOException;

    public rp0() {
        this.b = 100;
        this.c = Integer.MAX_VALUE;
    }

    public static rp0 a(InputStream inputStream, int i) {
        if (i > 0) {
            return inputStream == null ? a(gq0.b) : new c(inputStream, i);
        }
        throw new IllegalArgumentException("bufferSize must be > 0");
    }

    public static rp0 a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static rp0 a(byte[] bArr, int i, int i2) {
        return a(bArr, i, i2, false);
    }

    public static rp0 a(byte[] bArr, int i, int i2, boolean z) {
        b bVar = new b(bArr, i, i2, z);
        try {
            bVar.c(i2);
            return bVar;
        } catch (InvalidProtocolBufferException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static rp0 a(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return a(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && d.G()) {
            return new d(byteBuffer, z);
        }
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.duplicate().get(bArr);
        return a(bArr, 0, iRemaining, true);
    }
}
