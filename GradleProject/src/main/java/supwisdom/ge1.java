package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ge1 {
    public static final ge1 b = new b().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, List<ByteString>> f7717a;

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<String, List<ByteString>> f7718a = new LinkedHashMap();

        public ge1 a() {
            return new ge1(this);
        }
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<ByteString> list2 = this.f7717a.get(str);
        if (list2 == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list2.contains(a((X509Certificate) list.get(i)))) {
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size2 = list.size();
        for (int i2 = 0; i2 < size2; i2++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i2);
            sb.append("\n    ");
            sb.append(a((Certificate) x509Certificate));
            sb.append(": ");
            sb.append(x509Certificate.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(Constants.COLON_SEPARATOR);
        int size3 = list2.size();
        for (int i3 = 0; i3 < size3; i3++) {
            ByteString byteString = list2.get(i3);
            sb.append("\n    sha1/");
            sb.append(byteString.base64());
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public ge1(b bVar) {
        this.f7717a = gf1.a(bVar.f7718a);
    }

    public static String a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha1/" + a((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public static ByteString a(X509Certificate x509Certificate) {
        return gf1.a(ByteString.of(x509Certificate.getPublicKey().getEncoded()));
    }
}
