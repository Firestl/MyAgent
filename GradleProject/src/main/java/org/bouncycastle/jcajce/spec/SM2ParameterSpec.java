package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {
    public byte[] id;

    public SM2ParameterSpec(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("id string cannot be null");
        }
        this.id = Arrays.clone(bArr);
    }

    public byte[] getID() {
        return Arrays.clone(this.id);
    }
}
