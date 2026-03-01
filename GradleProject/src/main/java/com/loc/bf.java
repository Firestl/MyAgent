package com.loc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: EncryptRsaDataStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bf extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ak f3678a;

    public bf() {
        this.f3678a = new an();
    }

    public bf(bk bkVar) {
        super(bkVar);
        this.f3678a = new an();
    }

    @Override // com.loc.bk
    public final byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        return this.f3678a.b(bArr);
    }
}
