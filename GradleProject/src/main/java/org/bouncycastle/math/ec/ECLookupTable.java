package org.bouncycastle.math.ec;

/* JADX INFO: loaded from: classes3.dex */
public interface ECLookupTable {
    int getSize();

    ECPoint lookup(int i);

    ECPoint lookupVar(int i);
}
