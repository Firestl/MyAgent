package com.loc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: EncryptProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ak f3633a;

    public ak() {
    }

    public ak(ak akVar) {
        this.f3633a = akVar;
    }

    public abstract byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException;

    public final byte[] b(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        ak akVar = this.f3633a;
        if (akVar != null) {
            bArr = akVar.b(bArr);
        }
        return a(bArr);
    }
}
