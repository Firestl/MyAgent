package org.bouncycastle.math.field;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public interface FiniteField {
    BigInteger getCharacteristic();

    int getDimension();
}
