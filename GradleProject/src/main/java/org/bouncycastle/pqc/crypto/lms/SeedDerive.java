package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: classes3.dex */
public class SeedDerive {
    public final byte[] I;
    public final Digest digest;
    public int j;
    public final byte[] masterSeed;
    public int q;

    public SeedDerive(byte[] bArr, byte[] bArr2, Digest digest) {
        this.I = bArr;
        this.masterSeed = bArr2;
        this.digest = digest;
    }

    public void deriveSeed(byte[] bArr, boolean z) {
        deriveSeed(bArr, z, 0);
    }

    public void deriveSeed(byte[] bArr, boolean z, int i) {
        deriveSeed(bArr, i);
        if (z) {
            this.j++;
        }
    }

    public byte[] deriveSeed(byte[] bArr, int i) {
        if (bArr.length < this.digest.getDigestSize()) {
            throw new IllegalArgumentException("target length is less than digest size.");
        }
        Digest digest = this.digest;
        byte[] bArr2 = this.I;
        digest.update(bArr2, 0, bArr2.length);
        this.digest.update((byte) (this.q >>> 24));
        this.digest.update((byte) (this.q >>> 16));
        this.digest.update((byte) (this.q >>> 8));
        this.digest.update((byte) this.q);
        this.digest.update((byte) (this.j >>> 8));
        this.digest.update((byte) this.j);
        this.digest.update((byte) -1);
        Digest digest2 = this.digest;
        byte[] bArr3 = this.masterSeed;
        digest2.update(bArr3, 0, bArr3.length);
        this.digest.doFinal(bArr, i);
        return bArr;
    }

    public byte[] getI() {
        return this.I;
    }

    public int getJ() {
        return this.j;
    }

    public byte[] getMasterSeed() {
        return this.masterSeed;
    }

    public int getQ() {
        return this.q;
    }

    public void setJ(int i) {
        this.j = i;
    }

    public void setQ(int i) {
        this.q = i;
    }
}
