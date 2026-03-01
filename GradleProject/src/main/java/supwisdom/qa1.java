package supwisdom;

import java.lang.reflect.Field;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class qa1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Field f8906a;

    public T a(Object obj) {
        try {
            return (T) this.f8906a.get(obj);
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(Object obj, T t) {
        try {
            this.f8906a.set(obj, t);
        } catch (Exception unused) {
        }
    }
}
