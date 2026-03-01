package com.zx.a.I8b7;

import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes2.dex */
public class x implements X509TrustManager {
    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length != 2) {
            throw new CertificateException("ca chain is illegal");
        }
        X509Certificate x509CertificateF = w.f();
        if (x509CertificateF == null) {
            throw new CertificateException("getCurEnvCA is null");
        }
        if (!new BigInteger(1, x509CertificateF.getPublicKey().getEncoded()).toString(16).equals(new BigInteger(1, x509CertificateArr[1].getPublicKey().getEncoded()).toString(16))) {
            throw new CertificateException("Trust anchor for certification illegal code: 10003");
        }
        try {
            x509CertificateArr[0].verify(x509CertificateF.getPublicKey());
            try {
                x509CertificateArr[0].checkValidity();
            } catch (Exception unused) {
                throw new CertificateException("Trust anchor for certification illegal code: 10005");
            }
        } catch (Exception unused2) {
            throw new CertificateException("Trust anchor for certification illegal code: 10004");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
