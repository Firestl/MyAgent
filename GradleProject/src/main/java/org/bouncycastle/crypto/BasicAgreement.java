package org.bouncycastle.crypto;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public interface BasicAgreement {
    BigInteger calculateAgreement(CipherParameters cipherParameters);

    int getFieldSize();

    void init(CipherParameters cipherParameters);
}
