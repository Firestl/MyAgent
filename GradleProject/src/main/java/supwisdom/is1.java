package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import dc.squareup.okhttp3.CertificatePinner;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes3.dex */
public final class is1 {
    public static final is1 c = new a().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<b> f7979a;

    @Nullable
    public final cv1 b;

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<b> f7980a = new ArrayList();

        public is1 a() {
            return new is1(new LinkedHashSet(this.f7980a), null);
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f7981a;
        public final String b;
        public final String c;
        public final ByteString d;

        public boolean a(String str) {
            if (!this.f7981a.startsWith(CertificatePinner.Pin.WILDCARD)) {
                return str.equals(this.b);
            }
            int iIndexOf = str.indexOf(46);
            if ((str.length() - iIndexOf) - 1 == this.b.length()) {
                String str2 = this.b;
                if (str.regionMatches(false, iIndexOf + 1, str2, 0, str2.length())) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f7981a.equals(bVar.f7981a) && this.c.equals(bVar.c) && this.d.equals(bVar.d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f7981a.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
        }

        public String toString() {
            return this.c + this.d.base64();
        }
    }

    public is1(Set<b> set, @Nullable cv1 cv1Var) {
        this.f7979a = set;
        this.b = cv1Var;
    }

    public static ByteString b(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<b> listA = a(str);
        if (listA.isEmpty()) {
            return;
        }
        cv1 cv1Var = this.b;
        if (cv1Var != null) {
            list = cv1Var.a(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = listA.size();
            ByteString byteStringB = null;
            ByteString byteStringA = null;
            for (int i2 = 0; i2 < size2; i2++) {
                b bVar = listA.get(i2);
                if (bVar.c.equals("sha256/")) {
                    if (byteStringB == null) {
                        byteStringB = b(x509Certificate);
                    }
                    if (bVar.d.equals(byteStringB)) {
                        return;
                    }
                } else {
                    if (!bVar.c.equals("sha1/")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + bVar.c);
                    }
                    if (byteStringA == null) {
                        byteStringA = a(x509Certificate);
                    }
                    if (bVar.d.equals(byteStringA)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sb.append("\n    ");
            sb.append(a((Certificate) x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(Constants.COLON_SEPARATOR);
        int size4 = listA.size();
        for (int i4 = 0; i4 < size4; i4++) {
            b bVar2 = listA.get(i4);
            sb.append("\n    ");
            sb.append(bVar2);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof is1) {
            is1 is1Var = (is1) obj;
            if (kt1.a(this.b, is1Var.b) && this.f7979a.equals(is1Var.f7979a)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        cv1 cv1Var = this.b;
        return ((cv1Var != null ? cv1Var.hashCode() : 0) * 31) + this.f7979a.hashCode();
    }

    public List<b> a(String str) {
        List<b> listEmptyList = Collections.emptyList();
        for (b bVar : this.f7979a) {
            if (bVar.a(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(bVar);
            }
        }
        return listEmptyList;
    }

    public is1 a(@Nullable cv1 cv1Var) {
        return kt1.a(this.b, cv1Var) ? this : new is1(this.f7979a, cv1Var);
    }

    public static String a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + b((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public static ByteString a(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }
}
