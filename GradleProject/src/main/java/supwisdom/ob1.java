package supwisdom;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ob1 {
    public static Class<?>[] a(Class cls) {
        HashSet hashSet = new HashSet();
        a(cls, hashSet);
        return (Class[]) hashSet.toArray(new Class[hashSet.size()]);
    }

    public static void a(Class cls, HashSet<Class<?>> hashSet) {
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces.length != 0) {
            hashSet.addAll(Arrays.asList(interfaces));
        }
        if (cls.getSuperclass() != Object.class) {
            a(cls.getSuperclass(), hashSet);
        }
    }
}
