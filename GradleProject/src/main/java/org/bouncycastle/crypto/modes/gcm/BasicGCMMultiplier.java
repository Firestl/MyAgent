package org.bouncycastle.crypto.modes.gcm;

/* JADX INFO: loaded from: classes3.dex */
public class BasicGCMMultiplier implements GCMMultiplier {
    public long[] H;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.H = GCMUtil.asLongs(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArrAsLongs = GCMUtil.asLongs(bArr);
        GCMUtil.multiply(jArrAsLongs, this.H);
        GCMUtil.asBytes(jArrAsLongs, bArr);
    }
}
