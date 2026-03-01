package supwisdom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class pa1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Method f8783a;

    public T a(Object obj, Object... objArr) {
        try {
            return (T) this.f8783a.invoke(obj, objArr);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() != null) {
                e2.getCause().printStackTrace();
                return null;
            }
            e2.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
