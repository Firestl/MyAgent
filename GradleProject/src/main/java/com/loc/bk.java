package com.loc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: UpdateDataStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class bk {
    public bk c;
    public byte[] d = null;

    public bk() {
    }

    public bk(bk bkVar) {
        this.c = bkVar;
    }

    public final byte[] a() throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        bk bkVar = this;
        while (true) {
            byte[] bArrA = bkVar.a(bkVar.d);
            bkVar = bkVar.c;
            if (bkVar == null) {
                return bArrA;
            }
            bkVar.d = bArrA;
        }
    }

    public abstract byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException;

    public void b(byte[] bArr) {
    }
}
