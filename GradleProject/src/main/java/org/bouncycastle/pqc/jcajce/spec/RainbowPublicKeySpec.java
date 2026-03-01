package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class RainbowPublicKeySpec implements KeySpec {
    public short[][] coeffquadratic;
    public short[] coeffscalar;
    public short[][] coeffsingular;
    public int docLength;

    public RainbowPublicKeySpec(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.docLength = i;
        this.coeffquadratic = sArr;
        this.coeffsingular = sArr2;
        this.coeffscalar = sArr3;
    }

    public short[][] getCoeffQuadratic() {
        return this.coeffquadratic;
    }

    public short[] getCoeffScalar() {
        return this.coeffscalar;
    }

    public short[][] getCoeffSingular() {
        return this.coeffsingular;
    }

    public int getDocLength() {
        return this.docLength;
    }
}
