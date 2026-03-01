package org.bouncycastle.crypto.params;

/* JADX INFO: loaded from: classes3.dex */
public class DSAKeyParameters extends AsymmetricKeyParameter {
    public DSAParameters params;

    public DSAKeyParameters(boolean z, DSAParameters dSAParameters) {
        super(z);
        this.params = dSAParameters;
    }

    public DSAParameters getParameters() {
        return this.params;
    }
}
