package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.bouncycastle.pqc.crypto.qtesla.QTesla1p;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class Poly1305 implements Mac {
    public static final int BLOCK_SIZE = 16;
    public final BlockCipher cipher;
    public final byte[] currentBlock;
    public int currentBlockOffset;
    public int h0;
    public int h1;
    public int h2;
    public int h3;
    public int h4;
    public int k0;
    public int k1;
    public int k2;
    public int k3;
    public int r0;
    public int r1;
    public int r2;
    public int r3;
    public int r4;
    public int s1;
    public int s2;
    public int s3;
    public int s4;
    public final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.cipher = blockCipher;
    }

    public static final long mul32x32_64(int i, int i2) {
        return (((long) i) & 4294967295L) * ((long) i2);
    }

    private void processBlock() {
        int i = this.currentBlockOffset;
        if (i < 16) {
            this.currentBlock[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.currentBlock[i2] = 0;
            }
        }
        long jLittleEndianToInt = ((long) Pack.littleEndianToInt(this.currentBlock, 0)) & 4294967295L;
        long jLittleEndianToInt2 = ((long) Pack.littleEndianToInt(this.currentBlock, 4)) & 4294967295L;
        long jLittleEndianToInt3 = ((long) Pack.littleEndianToInt(this.currentBlock, 8)) & 4294967295L;
        long jLittleEndianToInt4 = 4294967295L & ((long) Pack.littleEndianToInt(this.currentBlock, 12));
        this.h0 = (int) (((long) this.h0) + (jLittleEndianToInt & Ed448.M26L));
        this.h1 = (int) (((long) this.h1) + ((((jLittleEndianToInt2 << 32) | jLittleEndianToInt) >>> 26) & Ed448.M26L));
        this.h2 = (int) (((long) this.h2) + (((jLittleEndianToInt2 | (jLittleEndianToInt3 << 32)) >>> 20) & Ed448.M26L));
        this.h3 = (int) (((long) this.h3) + ((((jLittleEndianToInt4 << 32) | jLittleEndianToInt3) >>> 14) & Ed448.M26L));
        int i3 = (int) (((long) this.h4) + (jLittleEndianToInt4 >>> 8));
        this.h4 = i3;
        if (this.currentBlockOffset == 16) {
            this.h4 = i3 + 16777216;
        }
        long jMul32x32_64 = mul32x32_64(this.h0, this.r0) + mul32x32_64(this.h1, this.s4) + mul32x32_64(this.h2, this.s3) + mul32x32_64(this.h3, this.s2) + mul32x32_64(this.h4, this.s1);
        long jMul32x32_642 = mul32x32_64(this.h0, this.r1) + mul32x32_64(this.h1, this.r0) + mul32x32_64(this.h2, this.s4) + mul32x32_64(this.h3, this.s3) + mul32x32_64(this.h4, this.s2);
        long jMul32x32_643 = mul32x32_64(this.h0, this.r2) + mul32x32_64(this.h1, this.r1) + mul32x32_64(this.h2, this.r0) + mul32x32_64(this.h3, this.s4) + mul32x32_64(this.h4, this.s3);
        long jMul32x32_644 = mul32x32_64(this.h0, this.r3) + mul32x32_64(this.h1, this.r2) + mul32x32_64(this.h2, this.r1) + mul32x32_64(this.h3, this.r0) + mul32x32_64(this.h4, this.s4);
        long jMul32x32_645 = mul32x32_64(this.h0, this.r4) + mul32x32_64(this.h1, this.r3) + mul32x32_64(this.h2, this.r2) + mul32x32_64(this.h3, this.r1) + mul32x32_64(this.h4, this.r0);
        int i4 = ((int) jMul32x32_64) & X25519Field.M26;
        this.h0 = i4;
        long j = jMul32x32_642 + (jMul32x32_64 >>> 26);
        int i5 = ((int) j) & X25519Field.M26;
        this.h1 = i5;
        long j2 = jMul32x32_643 + (j >>> 26);
        this.h2 = ((int) j2) & X25519Field.M26;
        long j3 = jMul32x32_644 + (j2 >>> 26);
        this.h3 = ((int) j3) & X25519Field.M26;
        long j4 = jMul32x32_645 + (j3 >>> 26);
        this.h4 = ((int) j4) & X25519Field.M26;
        int i6 = i4 + (((int) (j4 >>> 26)) * 5);
        this.h0 = i6;
        this.h1 = i5 + (i6 >>> 26);
        this.h0 = i6 & X25519Field.M26;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int i = 16;
        if (this.cipher != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.r0 = 67108863 & iLittleEndianToInt;
        int i2 = ((iLittleEndianToInt >>> 26) | (iLittleEndianToInt2 << 6)) & 67108611;
        this.r1 = i2;
        int i3 = ((iLittleEndianToInt2 >>> 20) | (iLittleEndianToInt3 << 12)) & 67092735;
        this.r2 = i3;
        int i4 = ((iLittleEndianToInt3 >>> 14) | (iLittleEndianToInt4 << 18)) & 66076671;
        this.r3 = i4;
        int i5 = (iLittleEndianToInt4 >>> 8) & QTesla1p.maskb1;
        this.r4 = i5;
        this.s1 = i2 * 5;
        this.s2 = i3 * 5;
        this.s3 = i4 * 5;
        this.s4 = i5 * 5;
        BlockCipher blockCipher = this.cipher;
        if (blockCipher != null) {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.cipher.processBlock(bArr2, 0, bArr3, 0);
            bArr = bArr3;
            i = 0;
        }
        this.k0 = Pack.littleEndianToInt(bArr, i + 0);
        this.k1 = Pack.littleEndianToInt(bArr, i + 4);
        this.k2 = Pack.littleEndianToInt(bArr, i + 8);
        this.k3 = Pack.littleEndianToInt(bArr, i + 12);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        if (i + 16 > bArr.length) {
            throw new OutputLengthException("Output buffer is too short.");
        }
        if (this.currentBlockOffset > 0) {
            processBlock();
        }
        int i2 = this.h1;
        int i3 = this.h0;
        int i4 = i2 + (i3 >>> 26);
        this.h1 = i4;
        int i5 = i3 & X25519Field.M26;
        this.h0 = i5;
        int i6 = this.h2 + (i4 >>> 26);
        this.h2 = i6;
        int i7 = i4 & X25519Field.M26;
        this.h1 = i7;
        int i8 = this.h3 + (i6 >>> 26);
        this.h3 = i8;
        int i9 = i6 & X25519Field.M26;
        this.h2 = i9;
        int i10 = this.h4 + (i8 >>> 26);
        this.h4 = i10;
        int i11 = i8 & X25519Field.M26;
        this.h3 = i11;
        int i12 = i5 + ((i10 >>> 26) * 5);
        this.h0 = i12;
        int i13 = i10 & X25519Field.M26;
        this.h4 = i13;
        int i14 = i7 + (i12 >>> 26);
        this.h1 = i14;
        int i15 = i12 & X25519Field.M26;
        this.h0 = i15;
        int i16 = i15 + 5;
        int i17 = i16 >>> 26;
        int i18 = i16 & X25519Field.M26;
        int i19 = i17 + i14;
        int i20 = i19 >>> 26;
        int i21 = i19 & X25519Field.M26;
        int i22 = i20 + i9;
        int i23 = i22 >>> 26;
        int i24 = i22 & X25519Field.M26;
        int i25 = i23 + i11;
        int i26 = 67108863 & i25;
        int i27 = ((i25 >>> 26) + i13) - 67108864;
        int i28 = (i27 >>> 31) - 1;
        int i29 = ~i28;
        int i30 = (i15 & i29) | (i18 & i28);
        this.h0 = i30;
        int i31 = (i14 & i29) | (i21 & i28);
        this.h1 = i31;
        int i32 = (i9 & i29) | (i24 & i28);
        this.h2 = i32;
        int i33 = (i26 & i28) | (i11 & i29);
        this.h3 = i33;
        int i34 = (i13 & i29) | (i27 & i28);
        this.h4 = i34;
        long j = (((long) (i30 | (i31 << 26))) & 4294967295L) + (((long) this.k0) & 4294967295L);
        long j2 = (((long) ((i31 >>> 6) | (i32 << 20))) & 4294967295L) + (((long) this.k1) & 4294967295L);
        long j3 = (((long) ((i32 >>> 12) | (i33 << 14))) & 4294967295L) + (((long) this.k2) & 4294967295L);
        long j4 = (((long) ((i33 >>> 18) | (i34 << 8))) & 4294967295L) + (4294967295L & ((long) this.k3));
        Pack.intToLittleEndian((int) j, bArr, i);
        long j5 = j2 + (j >>> 32);
        Pack.intToLittleEndian((int) j5, bArr, i + 4);
        long j6 = j3 + (j5 >>> 32);
        Pack.intToLittleEndian((int) j6, bArr, i + 8);
        Pack.intToLittleEndian((int) (j4 + (j6 >>> 32)), bArr, i + 12);
        reset();
        return 16;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.cipher.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] iv;
        if (this.cipher == null) {
            iv = null;
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) cipherParameters).getKey(), iv);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.currentBlockOffset = 0;
        this.h4 = 0;
        this.h3 = 0;
        this.h2 = 0;
        this.h1 = 0;
        this.h0 = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int iMin = Math.min(i2 - i3, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i3 + i, this.currentBlock, this.currentBlockOffset, iMin);
            i3 += iMin;
            this.currentBlockOffset += iMin;
        }
    }
}
