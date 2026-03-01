package supwisdom;

import com.huawei.secure.android.common.ssl.SSLUtil;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: HttpsUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class zv0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static X509TrustManager f10036a = new a();
    public static HostnameVerifier b = new b();

    /* JADX INFO: compiled from: HttpsUtils.java */
    public static class a implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* JADX INFO: compiled from: HttpsUtils.java */
    public static class b implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    /* JADX INFO: compiled from: HttpsUtils.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SSLSocketFactory f10037a;
        public X509TrustManager b;
    }

    public static c a() {
        return a(null, null, null, new InputStream[0]);
    }

    public static c a(X509TrustManager x509TrustManager, InputStream inputStream, String str, InputStream... inputStreamArr) {
        c cVar = new c();
        try {
            KeyManager[] keyManagerArrA = a(inputStream, str);
            TrustManager[] trustManagerArrA = a(inputStreamArr);
            if (x509TrustManager == null) {
                x509TrustManager = trustManagerArrA != null ? a(trustManagerArrA) : f10036a;
            }
            SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
            sSLContext.init(keyManagerArrA, new TrustManager[]{x509TrustManager}, null);
            cVar.f10037a = sSLContext.getSocketFactory();
            cVar.b = x509TrustManager;
            return cVar;
        } catch (KeyManagementException e2) {
            throw new AssertionError(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AssertionError(e3);
        }
    }

    public static KeyManager[] a(InputStream inputStream, String str) {
        if (inputStream != null && str != null) {
            try {
                KeyStore keyStore = KeyStore.getInstance("BKS");
                keyStore.load(inputStream, str.toCharArray());
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, str.toCharArray());
                return keyManagerFactory.getKeyManagers();
            } catch (Exception e2) {
                fw0.a(e2);
            }
        }
        return null;
    }

    public static TrustManager[] a(InputStream... inputStreamArr) {
        if (inputStreamArr != null && inputStreamArr.length > 0) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null);
                int length = inputStreamArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    InputStream inputStream = inputStreamArr[i];
                    int i3 = i2 + 1;
                    keyStore.setCertificateEntry(Integer.toString(i2), certificateFactory.generateCertificate(inputStream));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            fw0.a(e2);
                        }
                    }
                    i++;
                    i2 = i3;
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                return trustManagerFactory.getTrustManagers();
            } catch (Exception e3) {
                fw0.a(e3);
            }
        }
        return null;
    }

    public static X509TrustManager a(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }
}
