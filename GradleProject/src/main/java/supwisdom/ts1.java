package supwisdom;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.TlsVersion;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ts1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TlsVersion f9314a;
    public final js1 b;
    public final List<Certificate> c;
    public final List<Certificate> d;

    public ts1(TlsVersion tlsVersion, js1 js1Var, List<Certificate> list, List<Certificate> list2) {
        this.f9314a = tlsVersion;
        this.b = js1Var;
        this.c = list;
        this.d = list2;
    }

    public static ts1 a(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if ("SSL_NULL_WITH_NULL_NULL".equals(cipherSuite)) {
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        }
        js1 js1VarA = js1.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        TlsVersion tlsVersionForJavaName = TlsVersion.forJavaName(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        List listA = peerCertificates != null ? kt1.a(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new ts1(tlsVersionForJavaName, js1VarA, listA, localCertificates != null ? kt1.a(localCertificates) : Collections.emptyList());
    }

    public List<Certificate> b() {
        return this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ts1)) {
            return false;
        }
        ts1 ts1Var = (ts1) obj;
        return this.f9314a.equals(ts1Var.f9314a) && this.b.equals(ts1Var.b) && this.c.equals(ts1Var.c) && this.d.equals(ts1Var.d);
    }

    public int hashCode() {
        return ((((((527 + this.f9314a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public js1 a() {
        return this.b;
    }
}
