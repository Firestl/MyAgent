package supwisdom;

import android.os.IBinder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class o91<T> extends p91 implements q91 {
    public T d;

    /* JADX INFO: compiled from: Proguard */
    public class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IBinder f8650a;

        public a(IBinder iBinder) {
            this.f8650a = iBinder;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (!"queryLocalInterface".equals(method.getName())) {
                return method.invoke(this.f8650a, objArr);
            }
            t91.a("MethodInvocationProxy", "hook queryLocalInterface");
            return o91.this.c();
        }
    }

    public o91(T t) {
        super(t);
        this.d = t;
    }

    public void a(IBinder iBinder) {
    }

    public void c(String str) {
        IBinder iBinderA = fa1.f7581a.a(str);
        IBinder iBinder = (IBinder) Proxy.newProxyInstance(iBinderA.getClass().getClassLoader(), new Class[]{IBinder.class}, new a(iBinderA));
        if (iBinder == null) {
            t91.b("MethodInvocationProxy", "replaceService " + str + "   failed");
            return;
        }
        t91.c("MethodInvocationProxy", "replaceService " + str + "   success");
        a(iBinder);
        fa1.b.a().put(str, iBinder);
    }

    public o91(T t, Class cls) {
        super(t, cls);
        this.d = t;
    }
}
