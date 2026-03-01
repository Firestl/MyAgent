package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: classes3.dex */
public class WinternitzOTSVerify {
    public Digest messDigestOTS;
    public int w;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.w = i;
        this.messDigestOTS = digest;
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int i3;
        byte[] bArr3 = bArr2;
        int digestSize = this.messDigestOTS.getDigestSize();
        byte[] bArr4 = new byte[digestSize];
        int i4 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        int digestSize2 = this.messDigestOTS.getDigestSize();
        byte[] bArr5 = new byte[digestSize2];
        this.messDigestOTS.doFinal(bArr5, 0);
        int i5 = digestSize << 3;
        int i6 = this.w;
        int i7 = ((i6 - 1) + i5) / i6;
        int log = getLog((i7 << i6) + 1);
        int i8 = this.w;
        int i9 = ((((log + i8) - 1) / i8) + i7) * digestSize;
        if (i9 != bArr3.length) {
            return null;
        }
        byte[] bArr6 = new byte[i9];
        int i10 = 8;
        if (8 % i8 == 0) {
            int i11 = 8 / i8;
            int i12 = (1 << i8) - 1;
            byte[] bArr7 = new byte[digestSize];
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i13 < digestSize2) {
                while (i4 < i11) {
                    int i16 = bArr5[i13] & i12;
                    i14 += i16;
                    int i17 = digestSize2;
                    int i18 = i15 * digestSize;
                    int i19 = i11;
                    System.arraycopy(bArr3, i18, bArr7, 0, digestSize);
                    int i20 = i16;
                    while (i20 < i12) {
                        this.messDigestOTS.update(bArr7, 0, bArr7.length);
                        bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr7, 0);
                        i20++;
                        i14 = i14;
                        i9 = i9;
                    }
                    System.arraycopy(bArr7, 0, bArr6, i18, digestSize);
                    bArr5[i13] = (byte) (bArr5[i13] >>> this.w);
                    i15++;
                    i4++;
                    digestSize2 = i17;
                    bArr3 = bArr2;
                    i11 = i19;
                }
                i13++;
                bArr3 = bArr2;
                i4 = 0;
            }
            i = i9;
            int i21 = (i7 << this.w) - i14;
            int i22 = 0;
            while (i22 < log) {
                int i23 = i15 * digestSize;
                System.arraycopy(bArr2, i23, bArr7, 0, digestSize);
                for (int i24 = i21 & i12; i24 < i12; i24++) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                }
                System.arraycopy(bArr7, 0, bArr6, i23, digestSize);
                int i25 = this.w;
                i21 >>>= i25;
                i15++;
                i22 += i25;
            }
        } else {
            i = i9;
            if (i8 < 8) {
                int i26 = digestSize / i8;
                int i27 = (1 << i8) - 1;
                byte[] bArr8 = new byte[digestSize];
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                int i31 = 0;
                while (i28 < i26) {
                    int i32 = 0;
                    long j = 0;
                    while (i32 < this.w) {
                        j ^= (long) ((bArr5[i29] & 255) << (i32 << 3));
                        i29++;
                        i32++;
                        bArr8 = bArr8;
                    }
                    int i33 = 0;
                    while (i33 < i10) {
                        int i34 = i28;
                        int i35 = (int) (j & ((long) i27));
                        i30 += i35;
                        int i36 = i31 * digestSize;
                        System.arraycopy(bArr3, i36, bArr8, 0, digestSize);
                        while (true) {
                            int i37 = i26;
                            if (i35 < i27) {
                                this.messDigestOTS.update(bArr8, 0, bArr8.length);
                                bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                                this.messDigestOTS.doFinal(bArr8, 0);
                                i35++;
                                i26 = i37;
                                i29 = i29;
                            }
                        }
                        System.arraycopy(bArr8, 0, bArr6, i36, digestSize);
                        j >>>= this.w;
                        i31++;
                        i33++;
                        i28 = i34;
                        i10 = 8;
                    }
                    i28++;
                    i10 = 8;
                }
                byte[] bArr9 = bArr8;
                int i38 = digestSize % this.w;
                long j2 = 0;
                for (int i39 = 0; i39 < i38; i39++) {
                    j2 ^= (long) ((bArr5[i29] & 255) << (i39 << 3));
                    i29++;
                }
                int i40 = i38 << 3;
                byte[] bArr10 = bArr9;
                int i41 = 0;
                while (i41 < i40) {
                    int i42 = (int) (j2 & ((long) i27));
                    i30 += i42;
                    int i43 = i31 * digestSize;
                    System.arraycopy(bArr3, i43, bArr10, 0, digestSize);
                    while (i42 < i27) {
                        this.messDigestOTS.update(bArr10, 0, bArr10.length);
                        bArr10 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr10, 0);
                        i42++;
                    }
                    System.arraycopy(bArr10, 0, bArr6, i43, digestSize);
                    int i44 = this.w;
                    j2 >>>= i44;
                    i31++;
                    i41 += i44;
                }
                int i45 = (i7 << this.w) - i30;
                int i46 = 0;
                while (i46 < log) {
                    int i47 = i31 * digestSize;
                    System.arraycopy(bArr3, i47, bArr10, 0, digestSize);
                    for (int i48 = i45 & i27; i48 < i27; i48++) {
                        this.messDigestOTS.update(bArr10, 0, bArr10.length);
                        bArr10 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr10, 0);
                    }
                    System.arraycopy(bArr10, 0, bArr6, i47, digestSize);
                    int i49 = this.w;
                    i45 >>>= i49;
                    i31++;
                    i46 += i49;
                }
            } else if (i8 < 57) {
                int i50 = i5 - i8;
                int i51 = (1 << i8) - 1;
                byte[] bArr11 = new byte[digestSize];
                int i52 = 0;
                int i53 = 0;
                int i54 = 0;
                while (i53 <= i50) {
                    int i55 = i53 >>> 3;
                    int i56 = i53 % 8;
                    int i57 = i53 + this.w;
                    int i58 = (i57 + 7) >>> 3;
                    int i59 = 0;
                    long j3 = 0;
                    while (true) {
                        i2 = i50;
                        if (i55 >= i58) {
                            break;
                        }
                        j3 ^= (long) ((bArr5[i55] & 255) << (i59 << 3));
                        i59++;
                        i55++;
                        i50 = i2;
                        log = log;
                        i7 = i7;
                    }
                    int i60 = log;
                    int i61 = i7;
                    long j4 = i51;
                    long j5 = (j3 >>> i56) & j4;
                    int i62 = i57;
                    i54 = (int) (((long) i54) + j5);
                    int i63 = i52 * digestSize;
                    System.arraycopy(bArr3, i63, bArr11, 0, digestSize);
                    while (true) {
                        i3 = i62;
                        if (j5 < j4) {
                            this.messDigestOTS.update(bArr11, 0, bArr11.length);
                            bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(bArr11, 0);
                            j5++;
                            i62 = i3;
                            i54 = i54;
                        }
                    }
                    System.arraycopy(bArr11, 0, bArr6, i63, digestSize);
                    i52++;
                    i53 = i3;
                    i50 = i2;
                    log = i60;
                    i7 = i61;
                }
                int i64 = log;
                int i65 = i7;
                int i66 = i53 >>> 3;
                if (i66 < digestSize) {
                    int i67 = i53 % 8;
                    int i68 = 0;
                    long j6 = 0;
                    while (i66 < digestSize) {
                        j6 ^= (long) ((bArr5[i66] & 255) << (i68 << 3));
                        i68++;
                        i66++;
                    }
                    long j7 = i51;
                    long j8 = (j6 >>> i67) & j7;
                    i54 = (int) (((long) i54) + j8);
                    int i69 = i52 * digestSize;
                    System.arraycopy(bArr3, i69, bArr11, 0, digestSize);
                    while (j8 < j7) {
                        this.messDigestOTS.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr11, 0);
                        j8++;
                    }
                    System.arraycopy(bArr11, 0, bArr6, i69, digestSize);
                    i52++;
                }
                int i70 = (i65 << this.w) - i54;
                int i71 = 0;
                while (i71 < i64) {
                    int i72 = i52 * digestSize;
                    System.arraycopy(bArr3, i72, bArr11, 0, digestSize);
                    for (long j9 = i70 & i51; j9 < i51; j9++) {
                        this.messDigestOTS.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr11, 0);
                    }
                    System.arraycopy(bArr11, 0, bArr6, i72, digestSize);
                    int i73 = this.w;
                    i70 >>>= i73;
                    i52++;
                    i71 += i73;
                }
            }
        }
        byte[] bArr12 = new byte[digestSize];
        this.messDigestOTS.update(bArr6, 0, i);
        byte[] bArr13 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr13, 0);
        return bArr13;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = this.w;
        int i2 = ((digestSize << 3) + (i - 1)) / i;
        int log = getLog((i2 << i) + 1);
        return digestSize * (i2 + (((log + r3) - 1) / this.w));
    }
}
