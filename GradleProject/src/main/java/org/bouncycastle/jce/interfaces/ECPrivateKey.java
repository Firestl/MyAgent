package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes3.dex */
public interface ECPrivateKey extends ECKey, PrivateKey {
    BigInteger getD();
}
