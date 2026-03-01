package supwisdom;

import com.squareup.okhttp.CipherSuite;
import com.squareup.okhttp.TlsVersion;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ke1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ke1 f8167e;
    public static final ke1 f;
    public static final ke1 g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8168a;
    public final String[] b;
    public final String[] c;
    public final boolean d;

    /* JADX INFO: compiled from: ConnectionSpec.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8169a;
        public String[] b;
        public String[] c;
        public boolean d;

        public b(boolean z) {
            this.f8169a = z;
        }

        public b a(CipherSuite... cipherSuiteArr) {
            if (!this.f8169a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[cipherSuiteArr.length];
            for (int i = 0; i < cipherSuiteArr.length; i++) {
                strArr[i] = cipherSuiteArr[i].javaName;
            }
            this.b = strArr;
            return this;
        }

        public b b(String... strArr) {
            if (!this.f8169a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr == null) {
                this.c = null;
            } else {
                this.c = (String[]) strArr.clone();
            }
            return this;
        }

        public b(ke1 ke1Var) {
            this.f8169a = ke1Var.f8168a;
            this.b = ke1Var.b;
            this.c = ke1Var.c;
            this.d = ke1Var.d;
        }

        public b a(String... strArr) {
            if (!this.f8169a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (strArr == null) {
                this.b = null;
            } else {
                this.b = (String[]) strArr.clone();
            }
            return this;
        }

        public b a(TlsVersion... tlsVersionArr) {
            if (this.f8169a) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                this.c = strArr;
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public b a(boolean z) {
            if (this.f8169a) {
                this.d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public ke1 a() {
            return new ke1(this);
        }
    }

    static {
        b bVar = new b(true);
        bVar.a(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA);
        bVar.a(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        bVar.a(true);
        ke1 ke1VarA = bVar.a();
        f8167e = ke1VarA;
        b bVar2 = new b(ke1VarA);
        bVar2.a(TlsVersion.TLS_1_0);
        bVar2.a(true);
        f = bVar2.a();
        g = new b(false).a();
    }

    public boolean c() {
        return this.d;
    }

    public List<TlsVersion> d() {
        TlsVersion[] tlsVersionArr = new TlsVersion[this.c.length];
        int i = 0;
        while (true) {
            String[] strArr = this.c;
            if (i >= strArr.length) {
                return gf1.a(tlsVersionArr);
            }
            tlsVersionArr[i] = TlsVersion.forJavaName(strArr[i]);
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ke1)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ke1 ke1Var = (ke1) obj;
        boolean z = this.f8168a;
        if (z != ke1Var.f8168a) {
            return false;
        }
        return !z || (Arrays.equals(this.b, ke1Var.b) && Arrays.equals(this.c, ke1Var.c) && this.d == ke1Var.d);
    }

    public int hashCode() {
        if (this.f8168a) {
            return ((((527 + Arrays.hashCode(this.b)) * 31) + Arrays.hashCode(this.c)) * 31) + (!this.d ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f8168a) {
            return "ConnectionSpec()";
        }
        List<CipherSuite> listA = a();
        return "ConnectionSpec(cipherSuites=" + (listA == null ? "[use default]" : listA.toString()) + ", tlsVersions=" + d() + ", supportsTlsExtensions=" + this.d + ")";
    }

    public ke1(b bVar) {
        this.f8168a = bVar.f8169a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
    }

    public List<CipherSuite> a() {
        String[] strArr = this.b;
        if (strArr == null) {
            return null;
        }
        CipherSuite[] cipherSuiteArr = new CipherSuite[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.b;
            if (i >= strArr2.length) {
                return gf1.a(cipherSuiteArr);
            }
            cipherSuiteArr[i] = CipherSuite.forJavaName(strArr2[i]);
            i++;
        }
    }

    public boolean b() {
        return this.f8168a;
    }

    public void a(SSLSocket sSLSocket, we1 we1Var) {
        ke1 ke1VarA = a(sSLSocket);
        sSLSocket.setEnabledProtocols(ke1VarA.c);
        String[] enabledCipherSuites = ke1VarA.b;
        if (we1Var.f9617e && Arrays.asList(sSLSocket.getSupportedCipherSuites()).contains("TLS_FALLBACK_SCSV")) {
            if (enabledCipherSuites == null) {
                enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            }
            int length = enabledCipherSuites.length + 1;
            String[] strArr = new String[length];
            System.arraycopy(enabledCipherSuites, 0, strArr, 0, enabledCipherSuites.length);
            strArr[length - 1] = "TLS_FALLBACK_SCSV";
            enabledCipherSuites = strArr;
        }
        if (enabledCipherSuites != null) {
            sSLSocket.setEnabledCipherSuites(enabledCipherSuites);
        }
        ef1 ef1VarC = ef1.c();
        if (ke1VarA.d) {
            ae1 ae1Var = we1Var.f9616a;
            ef1VarC.a(sSLSocket, ae1Var.b, ae1Var.i);
        }
    }

    public final ke1 a(SSLSocket sSLSocket) {
        String[] strArr;
        if (this.b != null) {
            strArr = (String[]) gf1.a(String.class, this.b, sSLSocket.getEnabledCipherSuites());
        } else {
            strArr = null;
        }
        String[] strArr2 = (String[]) gf1.a(String.class, this.c, sSLSocket.getEnabledProtocols());
        b bVar = new b(this);
        bVar.a(strArr);
        bVar.b(strArr2);
        return bVar.a();
    }
}
