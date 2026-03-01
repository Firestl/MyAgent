package org.bouncycastle.crypto.modes.kgcm;

/* JADX INFO: loaded from: classes3.dex */
public class BasicKGCMMultiplier_128 implements KGCMMultiplier {
    public final long[] H = new long[2];

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        KGCMUtil_128.copy(jArr, this.H);
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        KGCMUtil_128.multiply(jArr, this.H, jArr);
    }
}
