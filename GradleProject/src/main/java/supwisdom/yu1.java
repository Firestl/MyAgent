package supwisdom;

import com.huawei.secure.android.common.ssl.SSLUtil;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okio.Buffer;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes3.dex */
public class yu1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final yu1 f9919a = b();
    public static final Logger b = Logger.getLogger(zs1.class.getName());

    public static yu1 b() {
        uu1 uu1VarF;
        yu1 yu1VarE = tu1.e();
        if (yu1VarE != null) {
            return yu1VarE;
        }
        if (d() && (uu1VarF = uu1.f()) != null) {
            return uu1VarF;
        }
        vu1 vu1VarE = vu1.e();
        if (vu1VarE != null) {
            return vu1VarE;
        }
        yu1 yu1VarE2 = wu1.e();
        return yu1VarE2 != null ? yu1VarE2 : new yu1();
    }

    public static yu1 c() {
        return f9919a;
    }

    public static boolean d() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void a(SSLSocket sSLSocket) {
    }

    public void a(SSLSocket sSLSocket, @Nullable String str, List<Protocol> list) {
    }

    @Nullable
    public String b(SSLSocket sSLSocket) {
        return null;
    }

    public void b(SSLSocketFactory sSLSocketFactory) {
    }

    public boolean b(String str) {
        return true;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void a(int i, String str, @Nullable Throwable th) {
        b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    @Nullable
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        try {
            Object objA = a(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), com.umeng.analytics.pro.f.X);
            if (objA == null) {
                return null;
            }
            return (X509TrustManager) a(objA, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public Object a(String str) {
        if (b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        a(5, str, (Throwable) obj);
    }

    public static byte[] b(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }

    public static List<String> a(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public cv1 a(X509TrustManager x509TrustManager) {
        return new av1(b(x509TrustManager));
    }

    public cv1 a(SSLSocketFactory sSLSocketFactory) {
        X509TrustManager x509TrustManagerC = c(sSLSocketFactory);
        if (x509TrustManagerC != null) {
            return a(x509TrustManagerC);
        }
        throw new IllegalStateException("Unable to extract the trust manager on " + c() + ", sslSocketFactory is " + sSLSocketFactory.getClass());
    }

    public ev1 b(X509TrustManager x509TrustManager) {
        return new bv1(x509TrustManager.getAcceptedIssuers());
    }

    @Nullable
    public static <T> T a(Object obj, Class<T> cls, String str) {
        Object objA;
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null && cls.isInstance(obj2)) {
                    return cls.cast(obj2);
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (NoSuchFieldException unused2) {
            }
        }
        if (str.equals("delegate") || (objA = a(obj, (Class<Object>) Object.class, "delegate")) == null) {
            return null;
        }
        return (T) a(objA, cls, str);
    }

    public SSLContext a() {
        if ("1.7".equals(System.getProperty("java.specification.version"))) {
            try {
                return SSLContext.getInstance(SSLUtil.c);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        try {
            return SSLContext.getInstance(SSLUtil.d);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }
}
