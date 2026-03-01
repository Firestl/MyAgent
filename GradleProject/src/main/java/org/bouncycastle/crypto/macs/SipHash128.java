package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int i, int i2) {
        super(i, i2);
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        long j = this.m;
        int i2 = this.wordPos;
        long j2 = j >>> ((7 - i2) << 3);
        this.m = j2;
        long j3 = j2 >>> 8;
        this.m = j3;
        this.m = j3 | ((((long) ((this.wordCount << 3) + i2)) & 255) << 56);
        processMessageWord();
        this.v2 ^= 238;
        applySipRounds(this.d);
        long j4 = this.v0;
        long j5 = this.v1;
        long j6 = ((j4 ^ j5) ^ this.v2) ^ this.v3;
        this.v1 = j5 ^ 221;
        applySipRounds(this.d);
        long j7 = ((this.v0 ^ this.v1) ^ this.v2) ^ this.v3;
        reset();
        Pack.longToLittleEndian(j6, bArr, i);
        Pack.longToLittleEndian(j7, bArr, i + 8);
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash
    public long doFinal() throws IllegalStateException, DataLengthException {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash128-" + this.c + "-" + this.d;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public void reset() {
        super.reset();
        this.v1 ^= 238;
    }
}
