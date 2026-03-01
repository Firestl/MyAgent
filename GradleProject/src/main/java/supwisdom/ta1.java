package supwisdom;

import java.lang.reflect.Field;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ta1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Field f9270a;

    public T a() {
        try {
            return (T) this.f9270a.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    public Class<?> b() {
        return this.f9270a.getType();
    }

    public void a(T t) {
        try {
            this.f9270a.set(null, t);
        } catch (Exception unused) {
        }
    }
}
