package supwisdom;

import com.squareup.okhttp.Protocol;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes2.dex */
public class ef1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ef1 f7488a = b();

    /* JADX INFO: compiled from: Platform.java */
    public static class c implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f7491a;
        public boolean b;
        public String c;

        public c(List<String> list) {
            this.f7491a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = gf1.b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f7491a;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.c = (String) objArr[0];
                return null;
            }
            List list = (List) objArr[0];
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (this.f7491a.contains(list.get(i))) {
                    String str = (String) list.get(i);
                    this.c = str;
                    return str;
                }
            }
            String str2 = this.f7491a.get(0);
            this.c = str2;
            return str2;
        }
    }

    public static ef1 b() {
        Method method;
        Method method2;
        df1 df1Var;
        try {
            try {
                Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
            } catch (ClassNotFoundException unused) {
                Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            }
            df1 df1Var2 = null;
            df1 df1Var3 = new df1(null, "setUseSessionTickets", Boolean.TYPE);
            df1 df1Var4 = new df1(null, "setHostname", String.class);
            try {
                Class<?> cls = Class.forName("android.net.TrafficStats");
                method2 = cls.getMethod("tagSocket", Socket.class);
                try {
                    method = cls.getMethod("untagSocket", Socket.class);
                    try {
                        Class.forName("android.net.Network");
                        df1Var = new df1(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                        try {
                            df1Var2 = new df1(null, "setAlpnProtocols", byte[].class);
                        } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException unused3) {
                        df1Var = null;
                    }
                } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                    method = null;
                    df1Var = null;
                }
            } catch (ClassNotFoundException | NoSuchMethodException unused5) {
                method = null;
                method2 = null;
                df1Var = null;
            }
            return new a(df1Var3, df1Var4, method2, method, df1Var, df1Var2);
        } catch (ClassNotFoundException unused6) {
            try {
                Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN");
                Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
                return new b(cls2.getMethod("put", SSLSocket.class, cls3), cls2.getMethod("get", SSLSocket.class), cls2.getMethod(AbsoluteConst.XML_REMOVE, SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
            } catch (ClassNotFoundException | NoSuchMethodException unused7) {
                return new ef1();
            }
        }
    }

    public static ef1 c() {
        return f7488a;
    }

    public String a() {
        return "OkHttp";
    }

    public void a(String str) {
        System.out.println(str);
    }

    public void a(Socket socket) throws SocketException {
    }

    public void a(SSLSocket sSLSocket) {
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public String b(SSLSocket sSLSocket) {
        return null;
    }

    public void b(Socket socket) throws SocketException {
    }

    public URI a(URL url) throws URISyntaxException {
        return url.toURI();
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    /* JADX INFO: compiled from: Platform.java */
    public static class a extends ef1 {
        public final df1<Socket> b;
        public final df1<Socket> c;
        public final Method d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Method f7489e;
        public final df1<Socket> f;
        public final df1<Socket> g;

        public a(df1<Socket> df1Var, df1<Socket> df1Var2, Method method, Method method2, df1<Socket> df1Var3, df1<Socket> df1Var4) {
            this.b = df1Var;
            this.c = df1Var2;
            this.d = method;
            this.f7489e = method2;
            this.f = df1Var3;
            this.g = df1Var4;
        }

        @Override // supwisdom.ef1
        public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (SecurityException e2) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e2);
                throw iOException;
            }
        }

        @Override // supwisdom.ef1
        public String b(SSLSocket sSLSocket) {
            byte[] bArr;
            df1<Socket> df1Var = this.f;
            if (df1Var == null || !df1Var.a(sSLSocket) || (bArr = (byte[]) this.f.d(sSLSocket, new Object[0])) == null) {
                return null;
            }
            return new String(bArr, gf1.c);
        }

        @Override // supwisdom.ef1
        public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.b.c(sSLSocket, true);
                this.c.c(sSLSocket, str);
            }
            df1<Socket> df1Var = this.g;
            if (df1Var == null || !df1Var.a(sSLSocket)) {
                return;
            }
            this.g.d(sSLSocket, ef1.a(list));
        }

        @Override // supwisdom.ef1
        public void b(Socket socket) throws SocketException {
            Method method = this.f7489e;
            if (method == null) {
                return;
            }
            try {
                method.invoke(null, socket);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }

        @Override // supwisdom.ef1
        public void a(Socket socket) throws SocketException {
            Method method = this.d;
            if (method == null) {
                return;
            }
            try {
                method.invoke(null, socket);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
    }

    public static byte[] a(List<Protocol> list) {
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

    /* JADX INFO: compiled from: Platform.java */
    public static class b extends ef1 {
        public final Method b;
        public final Method c;
        public final Method d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Class<?> f7490e;
        public final Class<?> f;

        public b(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
            this.b = method;
            this.c = method2;
            this.d = method3;
            this.f7490e = cls;
            this.f = cls2;
        }

        @Override // supwisdom.ef1
        public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
            }
            try {
                this.b.invoke(null, sSLSocket, Proxy.newProxyInstance(ef1.class.getClassLoader(), new Class[]{this.f7490e, this.f}, new c(arrayList)));
            } catch (IllegalAccessException | InvocationTargetException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // supwisdom.ef1
        public String b(SSLSocket sSLSocket) {
            try {
                c cVar = (c) Proxy.getInvocationHandler(this.c.invoke(null, sSLSocket));
                if (!cVar.b && cVar.c == null) {
                    ze1.f9996a.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                }
                if (cVar.b) {
                    return null;
                }
                return cVar.c;
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }

        @Override // supwisdom.ef1
        public void a(SSLSocket sSLSocket) {
            try {
                this.d.invoke(null, sSLSocket);
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }
    }
}
