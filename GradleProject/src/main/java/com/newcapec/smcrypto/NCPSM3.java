package com.newcapec.smcrypto;

import java.security.Security;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes2.dex */
public class NCPSM3 {
    public Digest gmd;

    public NCPSM3() {
        Security.addProvider(new BouncyCastleProvider());
        this.gmd = new SM3Digest();
    }

    public int SM3Final(byte[] bArr, int i, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length < i || bArr2.length < 32) {
            return 8001;
        }
        this.gmd.update(bArr, 0, i);
        this.gmd.doFinal(bArr2, 0);
        return 8000;
    }

    public int SM3Update(byte[] bArr, int i) {
        if (bArr == null || bArr.length < i) {
            return 8001;
        }
        this.gmd.update(bArr, 0, i);
        return 8000;
    }
}
