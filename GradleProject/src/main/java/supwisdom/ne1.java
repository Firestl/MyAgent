package supwisdom;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ne1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8507a;
    public final List<Certificate> b;
    public final List<Certificate> c;

    public ne1(String str, List<Certificate> list, List<Certificate> list2) {
        this.f8507a = str;
        this.b = list;
        this.c = list2;
    }

    public static ne1 a(SSLSession sSLSession) {
        Certificate[] peerCertificates;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        List listA = peerCertificates != null ? gf1.a(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new ne1(cipherSuite, listA, localCertificates != null ? gf1.a(localCertificates) : Collections.emptyList());
    }

    public List<Certificate> b() {
        return this.c;
    }

    public List<Certificate> c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ne1)) {
            return false;
        }
        ne1 ne1Var = (ne1) obj;
        return this.f8507a.equals(ne1Var.f8507a) && this.b.equals(ne1Var.b) && this.c.equals(ne1Var.c);
    }

    public int hashCode() {
        return ((((527 + this.f8507a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public static ne1 a(String str, List<Certificate> list, List<Certificate> list2) {
        if (str != null) {
            return new ne1(str, gf1.a(list), gf1.a(list2));
        }
        throw new IllegalArgumentException("cipherSuite == null");
    }

    public String a() {
        return this.f8507a;
    }
}
