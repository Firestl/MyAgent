package org.bouncycastle.crypto.modes.kgcm;

/* JADX INFO: loaded from: classes3.dex */
public class BasicKGCMMultiplier_512 implements KGCMMultiplier {
    public final long[] H = new long[8];

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        KGCMUtil_512.copy(jArr, this.H);
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        KGCMUtil_512.multiply(jArr, this.H, jArr);
    }
}
