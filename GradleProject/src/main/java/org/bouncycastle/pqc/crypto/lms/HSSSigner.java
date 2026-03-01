package org.bouncycastle.pqc.crypto.lms;

import java.io.IOException;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.crypto.MessageSigner;

/* JADX INFO: loaded from: classes3.dex */
public class HSSSigner implements MessageSigner {
    public HSSPrivateKeyParameters privKey;
    public HSSPublicKeyParameters pubKey;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        try {
            return HSS.generateSignature(this.privKey, bArr).getEncoded();
        } catch (IOException e2) {
            throw new IllegalStateException("unable to encode signature: " + e2.getMessage());
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            this.privKey = (HSSPrivateKeyParameters) cipherParameters;
        } else {
            this.pubKey = (HSSPublicKeyParameters) cipherParameters;
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        try {
            return HSS.verifySignature(this.pubKey, HSSSignature.getInstance(bArr2, this.pubKey.getL()), bArr);
        } catch (IOException e2) {
            throw new IllegalStateException("unable to decode signature: " + e2.getMessage());
        }
    }
}
