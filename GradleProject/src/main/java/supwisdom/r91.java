package supwisdom;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class r91 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9024a = true;

    public Object a(Object obj, Method method, Object[] objArr, Object obj2) {
        return obj2;
    }

    public boolean a() {
        return this.f9024a;
    }

    public boolean a(Object obj, Method method, Object... objArr) {
        return true;
    }

    public Object b(Object obj, Method method, Object... objArr) {
        return method.invoke(obj, objArr);
    }
}
