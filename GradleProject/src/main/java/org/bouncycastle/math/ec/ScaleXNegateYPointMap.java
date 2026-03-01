package org.bouncycastle.math.ec;

/* JADX INFO: loaded from: classes3.dex */
public class ScaleXNegateYPointMap implements ECPointMap {
    public final ECFieldElement scale;

    public ScaleXNegateYPointMap(ECFieldElement eCFieldElement) {
        this.scale = eCFieldElement;
    }

    @Override // org.bouncycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleXNegateY(this.scale);
    }
}
