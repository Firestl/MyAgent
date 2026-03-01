package supwisdom;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.TlsVersion;

/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ms1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final js1[] f8426e = {js1.q, js1.r, js1.s, js1.t, js1.u, js1.k, js1.m, js1.l, js1.n, js1.p, js1.o};
    public static final js1[] f = {js1.q, js1.r, js1.s, js1.t, js1.u, js1.k, js1.m, js1.l, js1.n, js1.p, js1.o, js1.i, js1.j, js1.g, js1.h, js1.f8083e, js1.f, js1.d};
    public static final ms1 g;
    public static final ms1 h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8427a;
    public final boolean b;

    @Nullable
    public final String[] c;

    @Nullable
    public final String[] d;

    static {
        a aVar = new a(true);
        aVar.a(f8426e);
        aVar.a(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2);
        aVar.a(true);
        aVar.a();
        a aVar2 = new a(true);
        aVar2.a(f);
        aVar2.a(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        aVar2.a(true);
        g = aVar2.a();
        a aVar3 = new a(true);
        aVar3.a(f);
        aVar3.a(TlsVersion.TLS_1_0);
        aVar3.a(true);
        aVar3.a();
        h = new a(false).a();
    }

    public ms1(a aVar) {
        this.f8427a = aVar.f8428a;
        this.c = aVar.b;
        this.d = aVar.c;
        this.b = aVar.d;
    }

    @Nullable
    public List<js1> a() {
        String[] strArr = this.c;
        if (strArr != null) {
            return js1.a(strArr);
        }
        return null;
    }

    public boolean b() {
        return this.f8427a;
    }

    public boolean c() {
        return this.b;
    }

    @Nullable
    public List<TlsVersion> d() {
        String[] strArr = this.d;
        if (strArr != null) {
            return TlsVersion.forJavaNames(strArr);
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ms1)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ms1 ms1Var = (ms1) obj;
        boolean z = this.f8427a;
        if (z != ms1Var.f8427a) {
            return false;
        }
        return !z || (Arrays.equals(this.c, ms1Var.c) && Arrays.equals(this.d, ms1Var.d) && this.b == ms1Var.b);
    }

    public int hashCode() {
        if (this.f8427a) {
            return ((((527 + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d)) * 31) + (!this.b ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f8427a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.c != null ? a().toString() : "[all enabled]") + ", tlsVersions=" + (this.d != null ? d().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.b + ")";
    }

    /* JADX INFO: compiled from: ConnectionSpec.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8428a;

        @Nullable
        public String[] b;

        @Nullable
        public String[] c;
        public boolean d;

        public a(boolean z) {
            this.f8428a = z;
        }

        public a a(js1... js1VarArr) {
            if (!this.f8428a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[js1VarArr.length];
            for (int i = 0; i < js1VarArr.length; i++) {
                strArr[i] = js1VarArr[i].f8084a;
            }
            a(strArr);
            return this;
        }

        public a b(String... strArr) {
            if (!this.f8428a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.c = (String[]) strArr.clone();
            return this;
        }

        public a(ms1 ms1Var) {
            this.f8428a = ms1Var.f8427a;
            this.b = ms1Var.c;
            this.c = ms1Var.d;
            this.d = ms1Var.b;
        }

        public a a(String... strArr) {
            if (this.f8428a) {
                if (strArr.length != 0) {
                    this.b = (String[]) strArr.clone();
                    return this;
                }
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(TlsVersion... tlsVersionArr) {
            if (this.f8428a) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                b(strArr);
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a a(boolean z) {
            if (this.f8428a) {
                this.d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public ms1 a() {
            return new ms1(this);
        }
    }

    public void a(SSLSocket sSLSocket, boolean z) {
        ms1 ms1VarB = b(sSLSocket, z);
        String[] strArr = ms1VarB.d;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = ms1VarB.c;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    public final ms1 b(SSLSocket sSLSocket, boolean z) {
        String[] strArrA = this.c != null ? kt1.a(js1.b, sSLSocket.getEnabledCipherSuites(), this.c) : sSLSocket.getEnabledCipherSuites();
        String[] strArrA2 = this.d != null ? kt1.a(kt1.o, sSLSocket.getEnabledProtocols(), this.d) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iA = kt1.a(js1.b, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iA != -1) {
            strArrA = kt1.a(strArrA, supportedCipherSuites[iA]);
        }
        a aVar = new a(this);
        aVar.a(strArrA);
        aVar.b(strArrA2);
        return aVar.a();
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.f8427a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !kt1.b(kt1.o, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.c;
        return strArr2 == null || kt1.b(js1.b, strArr2, sSLSocket.getEnabledCipherSuites());
    }
}
