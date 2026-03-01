package supwisdom;

import com.huawei.secure.android.common.ssl.SSLUtil;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import org.conscrypt.Conscrypt;

/* JADX INFO: compiled from: ConscryptPlatform.java */
/* JADX INFO: loaded from: classes3.dex */
public class uu1 extends yu1 {
    public static uu1 f() {
        try {
            Class.forName("org.conscrypt.Conscrypt");
            if (Conscrypt.isAvailable()) {
                return new uu1();
            }
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    @Override // supwisdom.yu1
    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (!Conscrypt.isConscrypt(sSLSocket)) {
            super.a(sSLSocket, str, list);
            return;
        }
        if (str != null) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            Conscrypt.setHostname(sSLSocket, str);
        }
        Conscrypt.setApplicationProtocols(sSLSocket, (String[]) yu1.a(list).toArray(new String[0]));
    }

    @Override // supwisdom.yu1
    @Nullable
    public String b(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket) ? Conscrypt.getApplicationProtocol(sSLSocket) : super.b(sSLSocket);
    }

    @Override // supwisdom.yu1
    @Nullable
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        if (!Conscrypt.isConscrypt(sSLSocketFactory)) {
            return super.c(sSLSocketFactory);
        }
        try {
            Object objA = yu1.a(sSLSocketFactory, (Class<Object>) Object.class, "sslParameters");
            if (objA != null) {
                return (X509TrustManager) yu1.a(objA, X509TrustManager.class, "x509TrustManager");
            }
            return null;
        } catch (Exception e2) {
            throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on Conscrypt", e2);
        }
    }

    public final Provider e() {
        return Conscrypt.newProviderBuilder().provideTrustManager().build();
    }

    @Override // supwisdom.yu1
    public void b(SSLSocketFactory sSLSocketFactory) {
        if (Conscrypt.isConscrypt(sSLSocketFactory)) {
            Conscrypt.setUseEngineSocket(sSLSocketFactory, true);
        }
    }

    @Override // supwisdom.yu1
    public SSLContext a() {
        try {
            return SSLContext.getInstance(SSLUtil.b, e());
        } catch (NoSuchAlgorithmException e2) {
            try {
                return SSLContext.getInstance(SSLUtil.d, e());
            } catch (NoSuchAlgorithmException unused) {
                throw new IllegalStateException("No TLS provider", e2);
            }
        }
    }
}
