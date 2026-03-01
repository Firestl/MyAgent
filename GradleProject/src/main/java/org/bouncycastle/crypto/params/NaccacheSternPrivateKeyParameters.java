package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
public class NaccacheSternPrivateKeyParameters extends NaccacheSternKeyParameters {
    public BigInteger phi_n;
    public Vector smallPrimes;

    public NaccacheSternPrivateKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, int i, Vector vector, BigInteger bigInteger3) {
        super(true, bigInteger, bigInteger2, i);
        this.smallPrimes = vector;
        this.phi_n = bigInteger3;
    }

    public BigInteger getPhi_n() {
        return this.phi_n;
    }

    public Vector getSmallPrimes() {
        return this.smallPrimes;
    }
}
