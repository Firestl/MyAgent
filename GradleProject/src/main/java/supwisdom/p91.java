package supwisdom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class p91<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f8779a;
    public T b;
    public final Map<String, r91> c;

    /* JADX INFO: compiled from: Proguard */
    public final class b implements InvocationHandler {
        public b() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            Object objInvoke;
            r91 r91VarA = p91.this.a(method.getName());
            try {
                if (r91VarA != null && r91VarA.a()) {
                    r91VarA.a(p91.this.f8779a, method, objArr);
                    objInvoke = r91VarA.b(p91.this.f8779a, method, objArr);
                    r91VarA.a(p91.this.f8779a, method, objArr, objInvoke);
                } else {
                    objInvoke = method.invoke(p91.this.f8779a, objArr);
                }
                return objInvoke;
            } finally {
            }
        }
    }

    public p91(T t, Class cls, Class<?>... clsArr) {
        this.c = new HashMap(3);
        this.f8779a = t;
        if (t == null) {
            t91.b("MethodInvocationStub", "baseInterface is null");
            return;
        }
        clsArr = clsArr == null ? ob1.a(t.getClass()) : clsArr;
        if (cls == null && clsArr != null) {
            try {
                if (clsArr.length != 0) {
                    this.b = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), clsArr, new b());
                    t91.c("MethodInvocationStub", "dynamic proxy ..." + t);
                    return;
                }
            } catch (Exception e2) {
                t91.a("MethodInvocationStub", "proxy failed..." + t, e2);
                return;
            }
        }
        cls = cls == null ? t.getClass() : cls;
        if (n91.b() == null) {
            t91.b("MethodInvocationStub", "DexMaker proxy failed,context is null");
        }
        g61 g61VarA = g61.a(cls);
        g61VarA.a(n91.b().getDir("dx", 0));
        g61VarA.a(new b());
        this.b = (T) g61VarA.a();
        t91.c("MethodInvocationStub", "DexMaker proxy..." + t);
    }

    public <H extends r91> H b(String str) {
        return (H) this.c.get(str);
    }

    public T c() {
        return this.b;
    }

    public final <H extends r91> H a(String str) {
        H h = (H) b(str);
        return h != null ? h : (H) b("hook_all");
    }

    public p91(T t) {
        this(t, null);
    }

    public p91(T t, Class cls) {
        this(t, cls, null);
    }
}
