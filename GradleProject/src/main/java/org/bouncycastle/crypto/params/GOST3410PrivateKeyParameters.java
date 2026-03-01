package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {
    public BigInteger x;

    public GOST3410PrivateKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(true, gOST3410Parameters);
        this.x = bigInteger;
    }

    public BigInteger getX() {
        return this.x;
    }
}
