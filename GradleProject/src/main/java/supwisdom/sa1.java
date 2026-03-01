package supwisdom;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class sa1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Method f9143a;

    public T a(Object... objArr) {
        try {
            return (T) this.f9143a.invoke(null, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
