package supwisdom;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
public class rd {
    public static rd c = new rd();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Class, a> f9049a = new HashMap();
    public final Map<Class, Boolean> b = new HashMap();

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9051a;
        public final Method b;

        public b(int i, Method method) {
            this.f9051a = i;
            this.b = method;
            method.setAccessible(true);
        }

        public void a(xd xdVar, Lifecycle.Event event, Object obj) {
            try {
                int i = this.f9051a;
                if (i == 0) {
                    this.b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.b.invoke(obj, xdVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.b.invoke(obj, xdVar, event);
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to call observer method", e3.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f9051a == bVar.f9051a && this.b.getName().equals(bVar.b.getName());
        }

        public int hashCode() {
            return (this.f9051a * 31) + this.b.getName().hashCode();
        }
    }

    public final Method[] a(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    public a b(Class cls) {
        a aVar = this.f9049a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    public boolean c(Class cls) {
        Boolean bool = this.b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrA = a(cls);
        for (Method method : methodArrA) {
            if (((ee) method.getAnnotation(ee.class)) != null) {
                a(cls, methodArrA);
                return true;
            }
        }
        this.b.put(cls, false);
        return false;
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<Lifecycle.Event, List<b>> f9050a = new HashMap();
        public final Map<b, Lifecycle.Event> b;

        public a(Map<b, Lifecycle.Event> map) {
            this.b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f9050a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f9050a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        public void a(xd xdVar, Lifecycle.Event event, Object obj) {
            a(this.f9050a.get(event), xdVar, event, obj);
            a(this.f9050a.get(Lifecycle.Event.ON_ANY), xdVar, event, obj);
        }

        public static void a(List<b> list, xd xdVar, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(xdVar, event, obj);
                }
            }
        }
    }

    public final void a(Map<b, Lifecycle.Event> map, b bVar, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = map.get(bVar);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(bVar, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }

    public final a a(Class cls, Method[] methodArr) {
        int i;
        a aVarB;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarB = b(superclass)) != null) {
            map.putAll(aVarB.b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : b(cls2).b.entrySet()) {
                a(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = a(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            ee eeVar = (ee) method.getAnnotation(ee.class);
            if (eeVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(xd.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                Lifecycle.Event eventValue = eeVar.value();
                if (parameterTypes.length > 1) {
                    if (parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        if (eventValue != Lifecycle.Event.ON_ANY) {
                            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                        }
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                }
                if (parameterTypes.length <= 2) {
                    a(map, new b(i, method), eventValue, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        a aVar = new a(map);
        this.f9049a.put(cls, aVar);
        this.b.put(cls, Boolean.valueOf(z));
        return aVar;
    }
}
