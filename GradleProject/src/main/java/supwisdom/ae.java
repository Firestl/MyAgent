package supwisdom;

import androidx.lifecycle.CompositeGeneratedAdaptersObserver;
import androidx.lifecycle.FullLifecycleObserverAdapter;
import androidx.lifecycle.ReflectiveGenericLifecycleObserver;
import androidx.lifecycle.SingleGeneratedAdapterObserver;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: Lifecycling.java */
/* JADX INFO: loaded from: classes.dex */
public class ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<Class, Integer> f6889a = new HashMap();
    public static Map<Class, List<Constructor<? extends td>>> b = new HashMap();

    public static vd a(Object obj) {
        boolean z = obj instanceof vd;
        boolean z2 = obj instanceof sd;
        if (z && z2) {
            return new FullLifecycleObserverAdapter((sd) obj, (vd) obj);
        }
        if (z2) {
            return new FullLifecycleObserverAdapter((sd) obj, null);
        }
        if (z) {
            return (vd) obj;
        }
        Class<?> cls = obj.getClass();
        if (b(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends td>> list = b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a(list.get(0), obj));
        }
        td[] tdVarArr = new td[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tdVarArr[i] = a(list.get(i), obj);
        }
        return new CompositeGeneratedAdaptersObserver(tdVarArr);
    }

    public static int b(Class<?> cls) {
        Integer num = f6889a.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int iD = d(cls);
        f6889a.put(cls, Integer.valueOf(iD));
        return iD;
    }

    public static boolean c(Class<?> cls) {
        return cls != null && wd.class.isAssignableFrom(cls);
    }

    public static int d(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends td> constructorA = a(cls);
        if (constructorA != null) {
            b.put(cls, Collections.singletonList(constructorA));
            return 2;
        }
        if (rd.c.c(cls)) {
            return 1;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        ArrayList arrayList = null;
        if (c(superclass)) {
            if (b(superclass) == 1) {
                return 1;
            }
            arrayList = new ArrayList(b.get(superclass));
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (c(cls2)) {
                if (b(cls2) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.addAll(b.get(cls2));
            }
        }
        if (arrayList == null) {
            return 1;
        }
        b.put(cls, arrayList);
        return 2;
    }

    public static td a(Constructor<? extends td> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    public static Constructor<? extends td> a(Class<?> cls) {
        try {
            Package r0 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r0 != null ? r0.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            String strA = a(canonicalName);
            if (!name.isEmpty()) {
                strA = name + Operators.DOT_STR + strA;
            }
            Constructor declaredConstructor = Class.forName(strA).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String a(String str) {
        return str.replace(Operators.DOT_STR, "_") + "_LifecycleAdapter";
    }
}
