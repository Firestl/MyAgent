package supwisdom;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class ja1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static HashMap<Class<?>, Constructor<?>> f8036a;

    static {
        HashMap<Class<?>, Constructor<?>> map = new HashMap<>();
        f8036a = map;
        try {
            map.put(qa1.class, qa1.class.getConstructor(Class.class, Field.class));
            f8036a.put(pa1.class, pa1.class.getConstructor(Class.class, Field.class));
            f8036a.put(na1.class, na1.class.getConstructor(Class.class, Field.class));
            f8036a.put(oa1.class, oa1.class.getConstructor(Class.class, Field.class));
            f8036a.put(ma1.class, ma1.class.getConstructor(Class.class, Field.class));
            f8036a.put(la1.class, la1.class.getConstructor(Class.class, Field.class));
            f8036a.put(ia1.class, ia1.class.getConstructor(Class.class, Field.class));
            f8036a.put(ta1.class, ta1.class.getConstructor(Class.class, Field.class));
            f8036a.put(ra1.class, ra1.class.getConstructor(Class.class, Field.class));
            f8036a.put(sa1.class, sa1.class.getConstructor(Class.class, Field.class));
            f8036a.put(ka1.class, ka1.class.getConstructor(Class.class, Field.class));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Class<?> a(Class<?> cls, String str) {
        try {
            Class<?> cls2 = Class.forName(str);
            a(cls, cls2);
            return cls2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Class a(Class cls, Class<?> cls2) {
        Constructor<?> constructor;
        for (Field field : cls.getDeclaredFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && (constructor = f8036a.get(field.getType())) != null) {
                    field.set(null, constructor.newInstance(cls2, field));
                }
            } catch (Exception unused) {
                t91.b("RefClass", "load faild", ", mappingClass: " + cls.getSimpleName() + ", field: " + field.getName());
            }
        }
        return cls2;
    }
}
