package org.bouncycastle.math.ec;

/* JADX INFO: loaded from: classes3.dex */
public class ScaleYNegateXPointMap implements ECPointMap {
    public final ECFieldElement scale;

    public ScaleYNegateXPointMap(ECFieldElement eCFieldElement) {
        this.scale = eCFieldElement;
    }

    @Override // org.bouncycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleYNegateX(this.scale);
    }
}
