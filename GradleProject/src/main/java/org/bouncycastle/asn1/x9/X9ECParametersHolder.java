package org.bouncycastle.asn1.x9;

/* JADX INFO: loaded from: classes3.dex */
public abstract class X9ECParametersHolder {
    public X9ECParameters params;

    public abstract X9ECParameters createParameters();

    public synchronized X9ECParameters getParameters() {
        if (this.params == null) {
            this.params = createParameters();
        }
        return this.params;
    }
}
