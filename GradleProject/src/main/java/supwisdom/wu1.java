package supwisdom;

import io.dcloud.common.constant.AbsoluteConst;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;

/* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
/* JADX INFO: loaded from: classes3.dex */
public class wu1 extends yu1 {
    public final Method c;
    public final Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f9667e;
    public final Class<?> f;
    public final Class<?> g;

    /* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
    public static class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f9668a;
        public boolean b;
        public String c;

        public a(List<String> list) {
            this.f9668a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = kt1.b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f9668a;
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
                if (this.f9668a.contains(list.get(i))) {
                    String str = (String) list.get(i);
                    this.c = str;
                    return str;
                }
            }
            String str2 = this.f9668a.get(0);
            this.c = str2;
            return str2;
        }
    }

    public wu1(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.c = method;
        this.d = method2;
        this.f9667e = method3;
        this.f = cls;
        this.g = cls2;
    }

    public static yu1 e() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new wu1(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod(AbsoluteConst.XML_REMOVE, SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // supwisdom.yu1
    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            this.c.invoke(null, sSLSocket, Proxy.newProxyInstance(yu1.class.getClassLoader(), new Class[]{this.f, this.g}, new a(yu1.a(list))));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw kt1.a("unable to set alpn", (Exception) e2);
        }
    }

    @Override // supwisdom.yu1
    @Nullable
    public String b(SSLSocket sSLSocket) {
        try {
            a aVar = (a) Proxy.getInvocationHandler(this.d.invoke(null, sSLSocket));
            if (!aVar.b && aVar.c == null) {
                yu1.c().a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
                return null;
            }
            if (aVar.b) {
                return null;
            }
            return aVar.c;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw kt1.a("unable to get selected protocol", (Exception) e2);
        }
    }

    @Override // supwisdom.yu1
    public void a(SSLSocket sSLSocket) {
        try {
            this.f9667e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw kt1.a("unable to remove alpn", (Exception) e2);
        }
    }
}
