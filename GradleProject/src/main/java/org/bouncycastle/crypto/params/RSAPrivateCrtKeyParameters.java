package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {
    public BigInteger dP;
    public BigInteger dQ;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BigInteger f6802e;
    public BigInteger p;
    public BigInteger q;
    public BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f6802e = bigInteger2;
        this.p = bigInteger4;
        this.q = bigInteger5;
        this.dP = bigInteger6;
        this.dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.dP;
    }

    public BigInteger getDQ() {
        return this.dQ;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getPublicExponent() {
        return this.f6802e;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
