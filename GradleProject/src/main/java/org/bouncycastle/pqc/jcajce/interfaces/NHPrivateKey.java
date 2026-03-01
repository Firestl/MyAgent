package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;

/* JADX INFO: loaded from: classes3.dex */
public interface NHPrivateKey extends NHKey, PrivateKey {
    short[] getSecretData();
}
