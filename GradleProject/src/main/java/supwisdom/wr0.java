package supwisdom;

import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.taobao.weex.el.parse.Operators;
import com.tencent.liteav.TXLiteAVCode;
import java.nio.ByteBuffer;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;

/* JADX INFO: compiled from: Utf8.java */
/* JADX INFO: loaded from: classes.dex */
public final class wr0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f9652a;

    /* JADX INFO: compiled from: Utf8.java */
    public static class a {
        public static char a(int i) {
            return (char) ((i >>> 10) + 55232);
        }

        public static char b(int i) {
            return (char) ((i & TXLiteAVCode.EVT_CAMERA_REMOVED) + 56320);
        }

        public static boolean d(byte b) {
            return b > -65;
        }

        public static boolean e(byte b) {
            return b >= 0;
        }

        public static boolean f(byte b) {
            return b < -16;
        }

        public static boolean g(byte b) {
            return b < -32;
        }

        public static int h(byte b) {
            return b & 63;
        }

        public static void b(byte b, char[] cArr, int i) {
            cArr[i] = (char) b;
        }

        public static void b(byte b, byte b2, char[] cArr, int i) throws InvalidProtocolBufferException {
            if (b >= -62 && !d(b2)) {
                cArr[i] = (char) (((b & com.umeng.analytics.pro.co.j) << 6) | h(b2));
                return;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        public static void b(byte b, byte b2, byte b3, char[] cArr, int i) throws InvalidProtocolBufferException {
            if (!d(b2) && ((b != -32 || b2 >= -96) && ((b != -19 || b2 < -96) && !d(b3)))) {
                cArr[i] = (char) (((b & 15) << 12) | (h(b2) << 6) | h(b3));
                return;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        public static void b(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws InvalidProtocolBufferException {
            if (!d(b2) && (((b << 28) + (b2 + KeyFactorySpi.Ed25519_type)) >> 30) == 0 && !d(b3) && !d(b4)) {
                int iH = ((b & 7) << 18) | (h(b2) << 12) | (h(b3) << 6) | h(b4);
                cArr[i] = a(iH);
                cArr[i + 1] = b(iH);
                return;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static abstract class b {
        public static int e(ByteBuffer byteBuffer, int i, int i2) {
            int iC = i + wr0.c(byteBuffer, i, i2);
            while (iC < i2) {
                int i3 = iC + 1;
                byte b = byteBuffer.get(iC);
                if (b < 0) {
                    if (b < -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b < -62 || byteBuffer.get(i3) > -65) {
                            return -1;
                        }
                        i3++;
                    } else {
                        if (b >= -16) {
                            if (i3 >= i2 - 2) {
                                return wr0.b(byteBuffer, b, i3, i2 - i3);
                            }
                            int i4 = i3 + 1;
                            byte b2 = byteBuffer.get(i3);
                            if (b2 <= -65 && (((b << 28) + (b2 + KeyFactorySpi.Ed25519_type)) >> 30) == 0) {
                                int i5 = i4 + 1;
                                if (byteBuffer.get(i4) <= -65) {
                                    i3 = i5 + 1;
                                    if (byteBuffer.get(i5) > -65) {
                                    }
                                }
                            }
                            return -1;
                        }
                        if (i3 >= i2 - 1) {
                            return wr0.b(byteBuffer, b, i3, i2 - i3);
                        }
                        int i6 = i3 + 1;
                        byte b3 = byteBuffer.get(i3);
                        if (b3 > -65 || ((b == -32 && b3 < -96) || ((b == -19 && b3 >= -96) || byteBuffer.get(i6) > -65))) {
                            return -1;
                        }
                        iC = i6 + 1;
                    }
                }
                iC = i3;
            }
            return 0;
        }

        public final int a(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (!byteBuffer.hasArray()) {
                return byteBuffer.isDirect() ? c(i, byteBuffer, i2, i3) : b(i, byteBuffer, i2, i3);
            }
            int iArrayOffset = byteBuffer.arrayOffset();
            return a(i, byteBuffer.array(), i2 + iArrayOffset, iArrayOffset + i3);
        }

        public abstract int a(int i, byte[] bArr, int i2, int i3);

        public abstract int a(CharSequence charSequence, byte[] bArr, int i, int i2);

        public abstract String a(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        public final boolean b(byte[] bArr, int i, int i2) {
            return a(0, bArr, i, i2) == 0;
        }

        public abstract int c(int i, ByteBuffer byteBuffer, int i2, int i3);

        public abstract String c(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException;

        public final boolean d(ByteBuffer byteBuffer, int i, int i2) {
            return a(0, byteBuffer, i, i2) == 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        
            if (r8.get(r9) > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
        
            if (r8.get(r9) > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008b, code lost:
        
            if (r8.get(r9) > (-65)) goto L53;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int b(int r7, java.nio.ByteBuffer r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L8e
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L1e
                r7 = -62
                if (r0 < r7) goto L1d
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L1a
                goto L1d
            L1a:
                r9 = r7
                goto L8e
            L1d:
                return r2
            L1e:
                r4 = -16
                if (r0 >= r4) goto L4f
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L38
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r7 < r10) goto L35
                int r7 = supwisdom.wr0.a(r0, r9)
                return r7
            L35:
                r5 = r9
                r9 = r7
                r7 = r5
            L38:
                if (r7 > r3) goto L4e
                r4 = -96
                if (r0 != r1) goto L40
                if (r7 < r4) goto L4e
            L40:
                r1 = -19
                if (r0 != r1) goto L46
                if (r7 >= r4) goto L4e
            L46:
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L1a
            L4e:
                return r2
            L4f:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L65
                int r7 = r9 + 1
                byte r1 = r8.get(r9)
                if (r7 < r10) goto L63
                int r7 = supwisdom.wr0.a(r0, r1)
                return r7
            L63:
                r9 = r7
                goto L68
            L65:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
            L68:
                if (r4 != 0) goto L78
                int r7 = r9 + 1
                byte r4 = r8.get(r9)
                if (r7 < r10) goto L77
                int r7 = supwisdom.wr0.a(r0, r1, r4)
                return r7
            L77:
                r9 = r7
            L78:
                if (r1 > r3) goto L8d
                int r7 = r0 << 28
                int r1 = r1 + 112
                int r7 = r7 + r1
                int r7 = r7 >> 30
                if (r7 != 0) goto L8d
                if (r4 > r3) goto L8d
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L1a
            L8d:
                return r2
            L8e:
                int r7 = e(r8, r9, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.wr0.b.b(int, java.nio.ByteBuffer, int, int):int");
        }

        public final String a(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
            if (byteBuffer.hasArray()) {
                return a(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2);
            }
            if (byteBuffer.isDirect()) {
                return c(byteBuffer, i, i2);
            }
            return b(byteBuffer, i, i2);
        }

        public final String b(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) >= 0) {
                int i3 = i + i2;
                char[] cArr = new char[i2];
                int i4 = 0;
                while (i < i3) {
                    byte b = byteBuffer.get(i);
                    if (!a.e(b)) {
                        break;
                    }
                    i++;
                    a.b(b, cArr, i4);
                    i4++;
                }
                int i5 = i4;
                while (i < i3) {
                    int i6 = i + 1;
                    byte b2 = byteBuffer.get(i);
                    if (!a.e(b2)) {
                        if (a.g(b2)) {
                            if (i6 < i3) {
                                a.b(b2, byteBuffer.get(i6), cArr, i5);
                                i = i6 + 1;
                                i5++;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else if (a.f(b2)) {
                            if (i6 < i3 - 1) {
                                int i7 = i6 + 1;
                                a.b(b2, byteBuffer.get(i6), byteBuffer.get(i7), cArr, i5);
                                i = i7 + 1;
                                i5++;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else if (i6 < i3 - 2) {
                            int i8 = i6 + 1;
                            byte b3 = byteBuffer.get(i6);
                            int i9 = i8 + 1;
                            a.b(b2, b3, byteBuffer.get(i8), byteBuffer.get(i9), cArr, i5);
                            i = i9 + 1;
                            i5 = i5 + 1 + 1;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else {
                        int i10 = i5 + 1;
                        a.b(b2, cArr, i5);
                        while (i6 < i3) {
                            byte b4 = byteBuffer.get(i6);
                            if (!a.e(b4)) {
                                break;
                            }
                            i6++;
                            a.b(b4, cArr, i10);
                            i10++;
                        }
                        i = i6;
                        i5 = i10;
                    }
                }
                return new String(cArr, 0, i5);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static final class c extends b {
        public static int d(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b < 0) {
                    if (b < -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b >= -62) {
                            i = i3 + 1;
                            if (bArr[i3] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b >= -16) {
                        if (i3 >= i2 - 2) {
                            return wr0.c(bArr, i3, i2);
                        }
                        int i4 = i3 + 1;
                        byte b2 = bArr[i3];
                        if (b2 <= -65 && (((b << 28) + (b2 + KeyFactorySpi.Ed25519_type)) >> 30) == 0) {
                            int i5 = i4 + 1;
                            if (bArr[i4] <= -65) {
                                i3 = i5 + 1;
                                if (bArr[i5] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    if (i3 >= i2 - 1) {
                        return wr0.c(bArr, i3, i2);
                    }
                    int i6 = i3 + 1;
                    byte b3 = bArr[i3];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        i = i6 + 1;
                        if (bArr[i6] > -65) {
                        }
                    }
                    return -1;
                }
                i = i3;
            }
            return 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        
            if (r8[r9] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
        
            if (r8[r9] > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x007f, code lost:
        
            if (r8[r9] > (-65)) goto L53;
         */
        @Override // supwisdom.wr0.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int a(int r7, byte[] r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L82
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L1c
                r7 = -62
                if (r0 < r7) goto L1b
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L18
                goto L1b
            L18:
                r9 = r7
                goto L82
            L1b:
                return r2
            L1c:
                r4 = -16
                if (r0 >= r4) goto L49
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L34
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r7 < r10) goto L31
                int r7 = supwisdom.wr0.a(r0, r9)
                return r7
            L31:
                r5 = r9
                r9 = r7
                r7 = r5
            L34:
                if (r7 > r3) goto L48
                r4 = -96
                if (r0 != r1) goto L3c
                if (r7 < r4) goto L48
            L3c:
                r1 = -19
                if (r0 != r1) goto L42
                if (r7 >= r4) goto L48
            L42:
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L18
            L48:
                return r2
            L49:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L5d
                int r7 = r9 + 1
                r1 = r8[r9]
                if (r7 < r10) goto L5b
                int r7 = supwisdom.wr0.a(r0, r1)
                return r7
            L5b:
                r9 = r7
                goto L60
            L5d:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
            L60:
                if (r4 != 0) goto L6e
                int r7 = r9 + 1
                r4 = r8[r9]
                if (r7 < r10) goto L6d
                int r7 = supwisdom.wr0.a(r0, r1, r4)
                return r7
            L6d:
                r9 = r7
            L6e:
                if (r1 > r3) goto L81
                int r7 = r0 << 28
                int r1 = r1 + 112
                int r7 = r7 + r1
                int r7 = r7 >> 30
                if (r7 != 0) goto L81
                if (r4 > r3) goto L81
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L18
            L81:
                return r2
            L82:
                int r7 = c(r8, r9, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.wr0.c.a(int, byte[], int, int):int");
        }

        @Override // supwisdom.wr0.b
        public int c(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return b(i, byteBuffer, i2, i3);
        }

        @Override // supwisdom.wr0.b
        public String c(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
            return b(byteBuffer, i, i2);
        }

        public static int c(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return d(bArr, i, i2);
        }

        @Override // supwisdom.wr0.b
        public String a(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
                int i3 = i + i2;
                char[] cArr = new char[i2];
                int i4 = 0;
                while (i < i3) {
                    byte b = bArr[i];
                    if (!a.e(b)) {
                        break;
                    }
                    i++;
                    a.b(b, cArr, i4);
                    i4++;
                }
                int i5 = i4;
                while (i < i3) {
                    int i6 = i + 1;
                    byte b2 = bArr[i];
                    if (!a.e(b2)) {
                        if (a.g(b2)) {
                            if (i6 < i3) {
                                a.b(b2, bArr[i6], cArr, i5);
                                i = i6 + 1;
                                i5++;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else if (a.f(b2)) {
                            if (i6 < i3 - 1) {
                                int i7 = i6 + 1;
                                a.b(b2, bArr[i6], bArr[i7], cArr, i5);
                                i = i7 + 1;
                                i5++;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else if (i6 < i3 - 2) {
                            int i8 = i6 + 1;
                            byte b3 = bArr[i6];
                            int i9 = i8 + 1;
                            a.b(b2, b3, bArr[i8], bArr[i9], cArr, i5);
                            i = i9 + 1;
                            i5 = i5 + 1 + 1;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else {
                        int i10 = i5 + 1;
                        a.b(b2, cArr, i5);
                        while (i6 < i3) {
                            byte b4 = bArr[i6];
                            if (!a.e(b4)) {
                                break;
                            }
                            i6++;
                            a.b(b4, cArr, i10);
                            i10++;
                        }
                        i = i6;
                        i5 = i10;
                    }
                }
                return new String(cArr, 0, i5);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // supwisdom.wr0.b
        public int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            int i5;
            char cCharAt;
            int length = charSequence.length();
            int i6 = i2 + i;
            int i7 = 0;
            while (i7 < length && (i5 = i7 + i) < i6 && (cCharAt = charSequence.charAt(i7)) < 128) {
                bArr[i5] = (byte) cCharAt;
                i7++;
            }
            if (i7 == length) {
                return i + length;
            }
            int i8 = i + i7;
            while (i7 < length) {
                char cCharAt2 = charSequence.charAt(i7);
                if (cCharAt2 >= 128 || i8 >= i6) {
                    if (cCharAt2 < 2048 && i8 <= i6 - 2) {
                        int i9 = i8 + 1;
                        bArr[i8] = (byte) ((cCharAt2 >>> 6) | 960);
                        i8 = i9 + 1;
                        bArr[i9] = (byte) ((cCharAt2 & Operators.CONDITION_IF) | 128);
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i8 > i6 - 3) {
                            if (i8 <= i6 - 4) {
                                int i10 = i7 + 1;
                                if (i10 != charSequence.length()) {
                                    char cCharAt3 = charSequence.charAt(i10);
                                    if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                        int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                        int i11 = i8 + 1;
                                        bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                                        int i12 = i11 + 1;
                                        bArr[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                                        int i13 = i12 + 1;
                                        bArr[i12] = (byte) (((codePoint >>> 6) & 63) | 128);
                                        i8 = i13 + 1;
                                        bArr[i13] = (byte) ((codePoint & 63) | 128);
                                        i7 = i10;
                                    } else {
                                        i7 = i10;
                                    }
                                }
                                throw new d(i7 - 1, length);
                            }
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i4 = i7 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i4)))) {
                                throw new d(i7, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i8);
                        }
                        int i14 = i8 + 1;
                        bArr[i8] = (byte) ((cCharAt2 >>> '\f') | 480);
                        int i15 = i14 + 1;
                        bArr[i14] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                        i3 = i15 + 1;
                        bArr[i15] = (byte) ((cCharAt2 & Operators.CONDITION_IF) | 128);
                    }
                    i7++;
                } else {
                    i3 = i8 + 1;
                    bArr[i8] = (byte) cCharAt2;
                }
                i8 = i3;
                i7++;
            }
            return i8;
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static class d extends IllegalArgumentException {
        public d(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static final class e extends b {
        public static boolean a() {
            return vr0.d() && vr0.e();
        }

        public static int b(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            while (i2 < i) {
                long j2 = 1 + j;
                if (vr0.a(bArr, j) < 0) {
                    return i2;
                }
                i2++;
                j = j2;
            }
            return i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
        
            if (supwisdom.vr0.a(r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00a8, code lost:
        
            if (supwisdom.vr0.a(r2) > (-65)) goto L59;
         */
        @Override // supwisdom.wr0.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int c(int r11, java.nio.ByteBuffer r12, int r13, int r14) {
            /*
                Method dump skipped, instruction units count: 217
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.wr0.e.c(int, java.nio.ByteBuffer, int, int):int");
        }

        public static int b(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = 8 - (((int) j) & 7);
            int i3 = i2;
            while (i3 > 0) {
                long j2 = 1 + j;
                if (vr0.a(j) < 0) {
                    return i2 - i3;
                }
                i3--;
                j = j2;
            }
            int i4 = i - i2;
            while (i4 >= 8 && (vr0.b(j) & (-9187201950435737472L)) == 0) {
                j += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0059, code lost:
        
            if (supwisdom.vr0.a(r13, r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009e, code lost:
        
            if (supwisdom.vr0.a(r13, r2) > (-65)) goto L59;
         */
        @Override // supwisdom.wr0.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int a(int r12, byte[] r13, int r14, int r15) {
            /*
                Method dump skipped, instruction units count: 204
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.wr0.e.a(int, byte[], int, int):int");
        }

        @Override // supwisdom.wr0.b
        public String a(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
                int i3 = i + i2;
                char[] cArr = new char[i2];
                int i4 = 0;
                while (i < i3) {
                    byte bA = vr0.a(bArr, i);
                    if (!a.e(bA)) {
                        break;
                    }
                    i++;
                    a.b(bA, cArr, i4);
                    i4++;
                }
                int i5 = i4;
                while (i < i3) {
                    int i6 = i + 1;
                    byte bA2 = vr0.a(bArr, i);
                    if (!a.e(bA2)) {
                        if (a.g(bA2)) {
                            if (i6 < i3) {
                                a.b(bA2, vr0.a(bArr, i6), cArr, i5);
                                i = i6 + 1;
                                i5++;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else if (a.f(bA2)) {
                            if (i6 < i3 - 1) {
                                int i7 = i6 + 1;
                                a.b(bA2, vr0.a(bArr, i6), vr0.a(bArr, i7), cArr, i5);
                                i = i7 + 1;
                                i5++;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else if (i6 < i3 - 2) {
                            int i8 = i6 + 1;
                            byte bA3 = vr0.a(bArr, i6);
                            int i9 = i8 + 1;
                            a.b(bA2, bA3, vr0.a(bArr, i8), vr0.a(bArr, i9), cArr, i5);
                            i = i9 + 1;
                            i5 = i5 + 1 + 1;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else {
                        int i10 = i5 + 1;
                        a.b(bA2, cArr, i5);
                        while (i6 < i3) {
                            byte bA4 = vr0.a(bArr, i6);
                            if (!a.e(bA4)) {
                                break;
                            }
                            i6++;
                            a.b(bA4, cArr, i10);
                            i10++;
                        }
                        i = i6;
                        i5 = i10;
                    }
                }
                return new String(cArr, 0, i5);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // supwisdom.wr0.b
        public String c(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) >= 0) {
                long jA = vr0.a(byteBuffer) + ((long) i);
                long j = ((long) i2) + jA;
                char[] cArr = new char[i2];
                int i3 = 0;
                while (jA < j) {
                    byte bA = vr0.a(jA);
                    if (!a.e(bA)) {
                        break;
                    }
                    jA++;
                    a.b(bA, cArr, i3);
                    i3++;
                }
                while (true) {
                    int i4 = i3;
                    while (jA < j) {
                        long j2 = jA + 1;
                        byte bA2 = vr0.a(jA);
                        if (!a.e(bA2)) {
                            if (a.g(bA2)) {
                                if (j2 < j) {
                                    jA = j2 + 1;
                                    a.b(bA2, vr0.a(j2), cArr, i4);
                                    i4++;
                                } else {
                                    throw InvalidProtocolBufferException.invalidUtf8();
                                }
                            } else if (a.f(bA2)) {
                                if (j2 < j - 1) {
                                    long j3 = j2 + 1;
                                    a.b(bA2, vr0.a(j2), vr0.a(j3), cArr, i4);
                                    i4++;
                                    jA = j3 + 1;
                                } else {
                                    throw InvalidProtocolBufferException.invalidUtf8();
                                }
                            } else if (j2 < j - 2) {
                                long j4 = j2 + 1;
                                byte bA3 = vr0.a(j2);
                                long j5 = j4 + 1;
                                byte bA4 = vr0.a(j4);
                                jA = j5 + 1;
                                a.b(bA2, bA3, bA4, vr0.a(j5), cArr, i4);
                                i3 = i4 + 1 + 1;
                            } else {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        } else {
                            int i5 = i4 + 1;
                            a.b(bA2, cArr, i4);
                            while (j2 < j) {
                                byte bA5 = vr0.a(j2);
                                if (!a.e(bA5)) {
                                    break;
                                }
                                j2++;
                                a.b(bA5, cArr, i5);
                                i5++;
                            }
                            i4 = i5;
                            jA = j2;
                        }
                    }
                    return new String(cArr, 0, i4);
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // supwisdom.wr0.b
        public int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
            char c;
            long j;
            long j2;
            long j3;
            int i3;
            char cCharAt;
            long j4 = i;
            long j5 = ((long) i2) + j4;
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i + i2));
            }
            int i4 = 0;
            while (true) {
                c = 128;
                j = 1;
                if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                    break;
                }
                vr0.a(bArr, j4, (byte) cCharAt);
                i4++;
                j4 = 1 + j4;
            }
            if (i4 == length) {
                return (int) j4;
            }
            while (i4 < length) {
                char cCharAt2 = charSequence.charAt(i4);
                if (cCharAt2 >= c || j4 >= j5) {
                    if (cCharAt2 < 2048 && j4 <= j5 - 2) {
                        long j6 = j4 + j;
                        vr0.a(bArr, j4, (byte) ((cCharAt2 >>> 6) | 960));
                        vr0.a(bArr, j6, (byte) ((cCharAt2 & Operators.CONDITION_IF) | 128));
                        j2 = j6 + j;
                        j3 = j;
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j4 > j5 - 3) {
                            if (j4 <= j5 - 4) {
                                int i5 = i4 + 1;
                                if (i5 != length) {
                                    char cCharAt3 = charSequence.charAt(i5);
                                    if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                        int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                        long j7 = j4 + 1;
                                        vr0.a(bArr, j4, (byte) ((codePoint >>> 18) | 240));
                                        long j8 = j7 + 1;
                                        vr0.a(bArr, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                                        long j9 = j8 + 1;
                                        vr0.a(bArr, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                                        j3 = 1;
                                        j2 = j9 + 1;
                                        vr0.a(bArr, j9, (byte) ((codePoint & 63) | 128));
                                        i4 = i5;
                                    } else {
                                        i4 = i5;
                                    }
                                }
                                throw new d(i4 - 1, length);
                            }
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                                throw new d(i4, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + j4);
                        }
                        long j10 = j4 + j;
                        vr0.a(bArr, j4, (byte) ((cCharAt2 >>> '\f') | 480));
                        long j11 = j10 + j;
                        vr0.a(bArr, j10, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                        vr0.a(bArr, j11, (byte) ((cCharAt2 & Operators.CONDITION_IF) | 128));
                        j2 = j11 + 1;
                        j3 = 1;
                    }
                    i4++;
                    c = 128;
                    long j12 = j3;
                    j4 = j2;
                    j = j12;
                } else {
                    long j13 = j4 + j;
                    vr0.a(bArr, j4, (byte) cCharAt2);
                    j3 = j;
                    j2 = j13;
                }
                i4++;
                c = 128;
                long j122 = j3;
                j4 = j2;
                j = j122;
            }
            return (int) j4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0063, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int a(byte[] r8, long r9, int r11) {
            /*
                int r0 = b(r8, r9, r11)
                int r11 = r11 - r0
                long r0 = (long) r0
                long r9 = r9 + r0
            L7:
                r0 = 0
                r1 = 0
            L9:
                r2 = 1
                if (r11 <= 0) goto L1a
                long r4 = r9 + r2
                byte r1 = supwisdom.vr0.a(r8, r9)
                if (r1 < 0) goto L19
                int r11 = r11 + (-1)
                r9 = r4
                goto L9
            L19:
                r9 = r4
            L1a:
                if (r11 != 0) goto L1d
                return r0
            L1d:
                int r11 = r11 + (-1)
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L3a
                if (r11 != 0) goto L29
                return r1
            L29:
                int r11 = r11 + (-1)
                r0 = -62
                if (r1 < r0) goto L39
                long r2 = r2 + r9
                byte r9 = supwisdom.vr0.a(r8, r9)
                if (r9 <= r4) goto L37
                goto L39
            L37:
                r9 = r2
                goto L7
            L39:
                return r5
            L3a:
                r6 = -16
                if (r1 >= r6) goto L64
                r6 = 2
                if (r11 >= r6) goto L46
                int r8 = a(r8, r1, r9, r11)
                return r8
            L46:
                int r11 = r11 + (-2)
                long r6 = r9 + r2
                byte r9 = supwisdom.vr0.a(r8, r9)
                if (r9 > r4) goto L63
                r10 = -96
                if (r1 != r0) goto L56
                if (r9 < r10) goto L63
            L56:
                r0 = -19
                if (r1 != r0) goto L5c
                if (r9 >= r10) goto L63
            L5c:
                long r2 = r2 + r6
                byte r9 = supwisdom.vr0.a(r8, r6)
                if (r9 <= r4) goto L37
            L63:
                return r5
            L64:
                r0 = 3
                if (r11 >= r0) goto L6c
                int r8 = a(r8, r1, r9, r11)
                return r8
            L6c:
                int r11 = r11 + (-3)
                long r6 = r9 + r2
                byte r9 = supwisdom.vr0.a(r8, r9)
                if (r9 > r4) goto L8e
                int r10 = r1 << 28
                int r9 = r9 + 112
                int r10 = r10 + r9
                int r9 = r10 >> 30
                if (r9 != 0) goto L8e
                long r9 = r6 + r2
                byte r0 = supwisdom.vr0.a(r8, r6)
                if (r0 > r4) goto L8e
                long r2 = r2 + r9
                byte r9 = supwisdom.vr0.a(r8, r9)
                if (r9 <= r4) goto L37
            L8e:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.wr0.e.a(byte[], long, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0063, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int a(long r8, int r10) {
            /*
                int r0 = b(r8, r10)
                long r1 = (long) r0
                long r8 = r8 + r1
                int r10 = r10 - r0
            L7:
                r0 = 0
                r1 = 0
            L9:
                r2 = 1
                if (r10 <= 0) goto L1a
                long r4 = r8 + r2
                byte r1 = supwisdom.vr0.a(r8)
                if (r1 < 0) goto L19
                int r10 = r10 + (-1)
                r8 = r4
                goto L9
            L19:
                r8 = r4
            L1a:
                if (r10 != 0) goto L1d
                return r0
            L1d:
                int r10 = r10 + (-1)
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L3a
                if (r10 != 0) goto L29
                return r1
            L29:
                int r10 = r10 + (-1)
                r0 = -62
                if (r1 < r0) goto L39
                long r2 = r2 + r8
                byte r8 = supwisdom.vr0.a(r8)
                if (r8 <= r4) goto L37
                goto L39
            L37:
                r8 = r2
                goto L7
            L39:
                return r5
            L3a:
                r6 = -16
                if (r1 >= r6) goto L64
                r6 = 2
                if (r10 >= r6) goto L46
                int r8 = a(r8, r1, r10)
                return r8
            L46:
                int r10 = r10 + (-2)
                long r6 = r8 + r2
                byte r8 = supwisdom.vr0.a(r8)
                if (r8 > r4) goto L63
                r9 = -96
                if (r1 != r0) goto L56
                if (r8 < r9) goto L63
            L56:
                r0 = -19
                if (r1 != r0) goto L5c
                if (r8 >= r9) goto L63
            L5c:
                long r2 = r2 + r6
                byte r8 = supwisdom.vr0.a(r6)
                if (r8 <= r4) goto L37
            L63:
                return r5
            L64:
                r0 = 3
                if (r10 >= r0) goto L6c
                int r8 = a(r8, r1, r10)
                return r8
            L6c:
                int r10 = r10 + (-3)
                long r6 = r8 + r2
                byte r8 = supwisdom.vr0.a(r8)
                if (r8 > r4) goto L8e
                int r9 = r1 << 28
                int r8 = r8 + 112
                int r9 = r9 + r8
                int r8 = r9 >> 30
                if (r8 != 0) goto L8e
                long r8 = r6 + r2
                byte r0 = supwisdom.vr0.a(r6)
                if (r0 > r4) goto L8e
                long r2 = r2 + r8
                byte r8 = supwisdom.vr0.a(r8)
                if (r8 <= r4) goto L37
            L8e:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.wr0.e.a(long, int):int");
        }

        public static int a(byte[] bArr, int i, long j, int i2) {
            if (i2 == 0) {
                return wr0.b(i);
            }
            if (i2 == 1) {
                return wr0.b(i, vr0.a(bArr, j));
            }
            if (i2 == 2) {
                return wr0.b(i, vr0.a(bArr, j), vr0.a(bArr, j + 1));
            }
            throw new AssertionError();
        }

        public static int a(long j, int i, int i2) {
            if (i2 == 0) {
                return wr0.b(i);
            }
            if (i2 == 1) {
                return wr0.b(i, vr0.a(j));
            }
            if (i2 == 2) {
                return wr0.b(i, vr0.a(j), vr0.a(j + 1));
            }
            throw new AssertionError();
        }
    }

    static {
        f9652a = (!e.a() || lp0.b()) ? new c() : new e();
    }

    public static int b(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static int b(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int b(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static int b(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (i3 == 0) {
            return b(i);
        }
        if (i3 == 1) {
            return b(i, byteBuffer.get(i2));
        }
        if (i3 == 2) {
            return b(i, byteBuffer.get(i2), byteBuffer.get(i2 + 1));
        }
        throw new AssertionError();
    }

    public static int c(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return b(b2);
        }
        if (i3 == 1) {
            return b(b2, bArr[i]);
        }
        if (i3 == 2) {
            return b(b2, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }

    public static boolean d(byte[] bArr, int i, int i2) {
        return f9652a.b(bArr, i, i2);
    }

    public static String b(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
        return f9652a.a(byteBuffer, i, i2);
    }

    public static String b(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
        return f9652a.a(bArr, i, i2);
    }

    public static int c(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & (-9187201950435737472L)) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    public static boolean a(byte[] bArr) {
        return f9652a.b(bArr, 0, bArr.length);
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        return f9652a.a(i, bArr, i2, i3);
    }

    public static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int iA = length;
        while (true) {
            if (i < length) {
                char cCharAt = charSequence.charAt(i);
                if (cCharAt >= 2048) {
                    iA += a(charSequence, i);
                    break;
                }
                iA += (127 - cCharAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (iA >= length) {
            return iA;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) iA) + 4294967296L));
    }

    public static int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt < 2048) {
                i2 += (127 - cCharAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        throw new d(i, length);
                    }
                    i++;
                }
            }
            i++;
        }
        return i2;
    }

    public static int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return f9652a.a(charSequence, bArr, i, i2);
    }

    public static boolean a(ByteBuffer byteBuffer) {
        return f9652a.d(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static int a(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return f9652a.a(i, byteBuffer, i2, i3);
    }
}
