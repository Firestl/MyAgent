package supwisdom;

import android.os.Build;
import android.util.Log;
import dc.squareup.okhttp3.internal.platform.AndroidPlatform;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes3.dex */
public class tu1 extends yu1 {
    public final Class<?> c;
    public final xu1<Socket> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final xu1<Socket> f9318e;
    public final xu1<Socket> f;
    public final xu1<Socket> g;
    public final c h = c.a();

    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class a extends cv1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f9319a;
        public final Method b;

        public a(Object obj, Method method) {
            this.f9319a = obj;
            this.b = method;
        }

        @Override // supwisdom.cv1
        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                return (List) this.b.invoke(this.f9319a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e3.getMessage());
                sSLPeerUnverifiedException.initCause(e3);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof a;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class b implements ev1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X509TrustManager f9320a;
        public final Method b;

        public b(X509TrustManager x509TrustManager, Method method) {
            this.b = method;
            this.f9320a = x509TrustManager;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f9320a.equals(bVar.f9320a) && this.b.equals(bVar.b);
        }

        @Override // supwisdom.ev1
        public X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.b.invoke(this.f9320a, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException e2) {
                throw kt1.a("unable to get issues and signature", (Exception) e2);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public int hashCode() {
            return this.f9320a.hashCode() + (this.b.hashCode() * 31);
        }
    }

    public tu1(Class<?> cls, xu1<Socket> xu1Var, xu1<Socket> xu1Var2, xu1<Socket> xu1Var3, xu1<Socket> xu1Var4) {
        this.c = cls;
        this.d = xu1Var;
        this.f9318e = xu1Var2;
        this.f = xu1Var3;
        this.g = xu1Var4;
    }

    public static yu1 e() {
        Class<?> cls;
        xu1 xu1Var;
        xu1 xu1Var2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            xu1 xu1Var3 = new xu1(null, "setUseSessionTickets", Boolean.TYPE);
            xu1 xu1Var4 = new xu1(null, "setHostname", String.class);
            if (f()) {
                xu1 xu1Var5 = new xu1(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                xu1Var2 = new xu1(null, "setAlpnProtocols", byte[].class);
                xu1Var = xu1Var5;
            } else {
                xu1Var = null;
                xu1Var2 = null;
            }
            return new tu1(cls2, xu1Var3, xu1Var4, xu1Var, xu1Var2);
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    public static boolean f() {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // supwisdom.yu1
    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e2) {
            if (!kt1.a(e2)) {
                throw e2;
            }
            throw new IOException(e2);
        } catch (ClassCastException e3) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e3;
            }
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e3);
            throw iOException;
        } catch (SecurityException e4) {
            IOException iOException2 = new IOException("Exception in connect");
            iOException2.initCause(e4);
            throw iOException2;
        }
    }

    @Override // supwisdom.yu1
    @Nullable
    public String b(SSLSocket sSLSocket) {
        byte[] bArr;
        xu1<Socket> xu1Var = this.f;
        if (xu1Var == null || !xu1Var.a(sSLSocket) || (bArr = (byte[]) this.f.d(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, kt1.i);
    }

    @Override // supwisdom.yu1
    @Nullable
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        Object objA = yu1.a(sSLSocketFactory, this.c, "sslParameters");
        if (objA == null) {
            try {
                objA = yu1.a(sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
            } catch (ClassNotFoundException unused) {
                return super.c(sSLSocketFactory);
            }
        }
        X509TrustManager x509TrustManager = (X509TrustManager) yu1.a(objA, X509TrustManager.class, "x509TrustManager");
        return x509TrustManager != null ? x509TrustManager : (X509TrustManager) yu1.a(objA, X509TrustManager.class, "trustManager");
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Method f9321a;
        public final Method b;
        public final Method c;

        public c(Method method, Method method2, Method method3) {
            this.f9321a = method;
            this.b = method2;
            this.c = method3;
        }

        public Object a(String str) {
            Method method = this.f9321a;
            if (method != null) {
                try {
                    Object objInvoke = method.invoke(null, new Object[0]);
                    this.b.invoke(objInvoke, str);
                    return objInvoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        public boolean a(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public static c a() throws NoSuchMethodException {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod("get", new Class[0]);
                method2 = cls.getMethod("open", String.class);
                method = cls.getMethod("warnIfOpen", new Class[0]);
                method3 = method4;
            } catch (Exception unused) {
                method = null;
                method2 = null;
            }
            return new c(method3, method2, method);
        }
    }

    @Override // supwisdom.yu1
    public boolean b(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return b(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.b(str);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw kt1.a("unable to determine cleartext support", e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            throw kt1.a("unable to determine cleartext support", e);
        } catch (InvocationTargetException e4) {
            e = e4;
            throw kt1.a("unable to determine cleartext support", e);
        }
    }

    public final boolean b(String str, Class<?> cls, Object obj) throws IllegalAccessException, InvocationTargetException {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(obj, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            return a(str, cls, obj);
        }
    }

    @Override // supwisdom.yu1
    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.d.c(sSLSocket, true);
            this.f9318e.c(sSLSocket, str);
        }
        xu1<Socket> xu1Var = this.g;
        if (xu1Var == null || !xu1Var.a(sSLSocket)) {
            return;
        }
        this.g.d(sSLSocket, yu1.b(list));
    }

    @Override // supwisdom.yu1
    public ev1 b(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.b(x509TrustManager);
        }
    }

    @Override // supwisdom.yu1
    public void a(int i, String str, @Nullable Throwable th) {
        int iMin;
        int i2 = i != 5 ? 3 : 5;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int iIndexOf = str.indexOf(10, i3);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            while (true) {
                iMin = Math.min(iIndexOf, i3 + AndroidPlatform.MAX_LOG_LENGTH);
                Log.println(i2, "OkHttp", str.substring(i3, iMin));
                if (iMin >= iIndexOf) {
                    break;
                } else {
                    i3 = iMin;
                }
            }
            i3 = iMin + 1;
        }
    }

    @Override // supwisdom.yu1
    public Object a(String str) {
        return this.h.a(str);
    }

    @Override // supwisdom.yu1
    public void a(String str, Object obj) {
        if (this.h.a(obj)) {
            return;
        }
        a(5, str, (Throwable) null);
    }

    public final boolean a(String str, Class<?> cls, Object obj) throws IllegalAccessException, InvocationTargetException {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.b(str);
        }
    }

    @Override // supwisdom.yu1
    public cv1 a(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.a(x509TrustManager);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000e  */
    @Override // supwisdom.yu1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public javax.net.ssl.SSLContext a() {
        /*
            r3 = this;
            r0 = 1
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.NoClassDefFoundError -> L10
            r2 = 16
            if (r1 < r2) goto Le
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.NoClassDefFoundError -> L10
            r2 = 22
            if (r1 >= r2) goto Le
            goto L11
        Le:
            r0 = 0
            goto L11
        L10:
        L11:
            if (r0 == 0) goto L1a
            java.lang.String r0 = "TLSv1.2"
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getInstance(r0)     // Catch: java.security.NoSuchAlgorithmException -> L1a
            return r0
        L1a:
            java.lang.String r0 = "TLS"
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getInstance(r0)     // Catch: java.security.NoSuchAlgorithmException -> L21
            return r0
        L21:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "No TLS provider"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.tu1.a():javax.net.ssl.SSLContext");
    }
}
