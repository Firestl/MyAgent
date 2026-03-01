package org.bouncycastle.crypto.params;

/* JADX INFO: loaded from: classes3.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    public final ECDomainParameters parameters;

    public ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        if (eCDomainParameters == null) {
            throw new NullPointerException("'parameters' cannot be null");
        }
        this.parameters = eCDomainParameters;
    }

    public ECDomainParameters getParameters() {
        return this.parameters;
    }
}
