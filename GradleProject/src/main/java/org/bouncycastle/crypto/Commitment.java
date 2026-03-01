package org.bouncycastle.crypto;

/* JADX INFO: loaded from: classes3.dex */
public class Commitment {
    public final byte[] commitment;
    public final byte[] secret;

    public Commitment(byte[] bArr, byte[] bArr2) {
        this.secret = bArr;
        this.commitment = bArr2;
    }

    public byte[] getCommitment() {
        return this.commitment;
    }

    public byte[] getSecret() {
        return this.secret;
    }
}
