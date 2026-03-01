package org.bouncycastle.crypto;

/* JADX INFO: loaded from: classes3.dex */
public class EphemeralKeyPair {
    public AsymmetricCipherKeyPair keyPair;
    public KeyEncoder publicKeyEncoder;

    public EphemeralKeyPair(AsymmetricCipherKeyPair asymmetricCipherKeyPair, KeyEncoder keyEncoder) {
        this.keyPair = asymmetricCipherKeyPair;
        this.publicKeyEncoder = keyEncoder;
    }

    public byte[] getEncodedPublicKey() {
        return this.publicKeyEncoder.getEncoded(this.keyPair.getPublic());
    }

    public AsymmetricCipherKeyPair getKeyPair() {
        return this.keyPair;
    }
}
