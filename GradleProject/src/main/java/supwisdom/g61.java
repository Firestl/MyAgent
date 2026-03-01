package supwisdom;

import com.google.zxing.client.android.encode.MECARDContactEncoder;
import com.sangfor.dx.Comparison;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class g61<T> {
    public static final Map<c<?>, Class<?>> k = Collections.synchronizedMap(new HashMap());
    public static final Map<Class<?>, Class<?>> l;
    public static final Map<az0<?>, zy0<?, ?>> m;
    public static final Map<Class<?>, zy0<?, ?>> n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<T> f7690a;
    public InvocationHandler c;
    public File d;
    public Method[] h;
    public boolean i;
    public boolean j;
    public ClassLoader b = g61.class.getClassLoader();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Class<?>[] f7691e = new Class[0];
    public Object[] f = new Object[0];
    public Set<Class<?>> g = new HashSet();

    /* JADX INFO: compiled from: Proguard */
    public class a implements Comparator<Method> {
        public a(g61 g61Var) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Method method, Method method2) {
            return method.toString().compareTo(method2.toString());
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f7692a;
        public final Class<?>[] b;
        public final Class<?> c;
        public final Method d;

        public b(Method method) {
            this.d = method;
            this.f7692a = method.getName();
            this.b = method.getParameterTypes();
            this.c = method.getReturnType();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f7692a.equals(bVar.f7692a) && this.c.equals(bVar.c) && Arrays.equals(this.b, bVar.b);
        }

        public int hashCode() {
            int iHashCode = this.f7692a.hashCode() + 527 + 17;
            int iHashCode2 = iHashCode + (iHashCode * 31) + this.c.hashCode();
            return iHashCode2 + (iHashCode2 * 31) + Arrays.hashCode(this.b);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class c<U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Class<U> f7693a;
        public final ClassLoader b;
        public final boolean c;

        public /* synthetic */ c(Class cls, ClassLoader classLoader, boolean z, a aVar) {
            this(cls, classLoader, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || c.class != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            return this.f7693a == cVar.f7693a && this.b == cVar.b && this.c == cVar.c;
        }

        public int hashCode() {
            return this.f7693a.hashCode() + this.b.hashCode() + (this.c ? 1 : 0);
        }

        public c(Class<U> cls, ClassLoader classLoader, boolean z) {
            this.f7693a = cls;
            this.b = classLoader;
            this.c = z;
        }
    }

    static {
        HashMap map = new HashMap();
        l = map;
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Long.TYPE, Long.class);
        map.put(Short.TYPE, Short.class);
        map.put(Float.TYPE, Float.class);
        map.put(Double.TYPE, Double.class);
        map.put(Character.TYPE, Character.class);
        m = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            az0<?> az0VarA = az0.a((Class) entry.getKey());
            az0 az0VarA2 = az0.a((Class) entry.getValue());
            m.put(az0VarA, az0VarA2.a(az0VarA2, "valueOf", az0VarA));
        }
        HashMap map2 = new HashMap();
        map2.put(Boolean.TYPE, az0.a(Boolean.class).a(az0.d, "booleanValue", new az0[0]));
        map2.put(Integer.TYPE, az0.a(Integer.class).a(az0.i, "intValue", new az0[0]));
        map2.put(Byte.TYPE, az0.a(Byte.class).a(az0.f6997e, "byteValue", new az0[0]));
        map2.put(Long.TYPE, az0.a(Long.class).a(az0.j, "longValue", new az0[0]));
        map2.put(Short.TYPE, az0.a(Short.class).a(az0.k, "shortValue", new az0[0]));
        map2.put(Float.TYPE, az0.a(Float.class).a(az0.h, "floatValue", new az0[0]));
        map2.put(Double.TYPE, az0.a(Double.class).a(az0.g, "doubleValue", new az0[0]));
        map2.put(Character.TYPE, az0.a(Character.class).a(az0.f, "charValue", new az0[0]));
        n = map2;
    }

    public g61(Class<T> cls) {
        this.f7690a = cls;
    }

    public static <T> Set<T> a(T... tArr) {
        return new CopyOnWriteArraySet(Arrays.asList(tArr));
    }

    public Class<? extends T> b() {
        ClassLoader classLoader = this.i ? this.f7690a.getClassLoader() : this.b;
        Map<c<?>, Class<?>> map = k;
        a aVar = null;
        Class<? extends T> cls = (Class) map.get(new c(this.f7690a, classLoader, this.i, aVar));
        if (cls != null && this.g.equals(a((Object[]) cls.getInterfaces()))) {
            return cls;
        }
        vy0 vy0Var = new vy0();
        String strC = c(this.f7690a);
        az0<?> az0VarA = az0.a("L" + strC + ";");
        az0<?> az0VarA2 = az0.a(this.f7690a);
        a(vy0Var, az0VarA, az0VarA2, this.f7690a);
        Method[] methodArrD = this.h;
        if (methodArrD == null) {
            methodArrD = d();
        }
        Arrays.sort(methodArrD, new a(this));
        a(vy0Var, az0VarA, methodArrD, az0VarA2);
        vy0Var.a(az0VarA, strC + ".generated", 1, az0VarA2, c());
        if (this.i) {
            vy0Var.a(classLoader);
        }
        if (this.j) {
            vy0Var.c();
        }
        try {
            Class<? extends T> clsA = a(this.i ? vy0Var.a((ClassLoader) null, this.d) : vy0Var.a(this.b, this.d), strC);
            a(clsA, methodArrD);
            map.put(new c<>(this.f7690a, classLoader, this.i, aVar), clsA);
            return clsA;
        } catch (ClassNotFoundException e2) {
            throw new AssertionError(e2);
        } catch (IllegalAccessError e3) {
            throw new UnsupportedOperationException("cannot proxy inaccessible class " + this.f7690a, e3);
        }
    }

    public final az0<?>[] c() {
        az0<?>[] az0VarArr = new az0[this.g.size()];
        Iterator<Class<?>> it = this.g.iterator();
        int i = 0;
        while (it.hasNext()) {
            az0VarArr[i] = az0.a(it.next());
            i++;
        }
        return az0VarArr;
    }

    public final Method[] d() {
        int i;
        Set<b> hashSet = new HashSet<>();
        Set<b> hashSet2 = new HashSet<>();
        for (Class<T> superclass = this.f7690a; superclass != null; superclass = superclass.getSuperclass()) {
            a(hashSet, hashSet2, (Class<?>) superclass);
        }
        Class<T> superclass2 = this.f7690a;
        while (true) {
            i = 0;
            if (superclass2 == null) {
                break;
            }
            Class<?>[] interfaces = superclass2.getInterfaces();
            int length = interfaces.length;
            while (i < length) {
                a(hashSet, hashSet2, interfaces[i]);
                i++;
            }
            superclass2 = superclass2.getSuperclass();
        }
        Iterator<Class<?>> it = this.g.iterator();
        while (it.hasNext()) {
            a(hashSet, hashSet2, it.next());
        }
        Method[] methodArr = new Method[hashSet.size()];
        Iterator<b> it2 = hashSet.iterator();
        while (it2.hasNext()) {
            methodArr[i] = it2.next().d;
            i++;
        }
        return methodArr;
    }

    public static yy0<?> a(ty0 ty0Var, yy0<?> yy0Var, yy0<Object> yy0Var2) {
        zy0<?, ?> zy0Var = m.get(yy0Var.a());
        if (zy0Var == null) {
            return yy0Var;
        }
        ty0Var.a(zy0Var, yy0Var2, yy0Var);
        return yy0Var2;
    }

    public static <T> String c(Class<T> cls) {
        return cls.getName().replace(Operators.DOT_STR, "/") + "_Proxy";
    }

    public T a() {
        a(this.c != null, "handler == null");
        a(this.f7691e.length == this.f.length, "constructorArgValues.length != constructorArgTypes.length");
        try {
            try {
                T tNewInstance = b().getConstructor(this.f7691e).newInstance(this.f);
                a(tNewInstance, this.c);
                return tNewInstance;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new AssertionError(e3);
            } catch (InvocationTargetException e4) {
                a(e4);
                throw null;
            }
        } catch (NoSuchMethodException unused) {
            throw new IllegalArgumentException("No constructor for " + this.f7690a.getName() + " with parameter types " + Arrays.toString(this.f7691e));
        }
    }

    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static zy0<?, ?> d(Class<?> cls) {
        return n.get(cls);
    }

    public static az0<?>[] a(Class<?>[] clsArr) {
        az0<?>[] az0VarArr = new az0[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            az0VarArr[i] = az0.a(clsArr[i]);
        }
        return az0VarArr;
    }

    public g61<T> a(File file) {
        File file2 = new File(file, "v" + Integer.toString(1));
        this.d = file2;
        file2.mkdir();
        return this;
    }

    public static <T> g61<T> a(Class<T> cls) {
        return new g61<>(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [supwisdom.ty0] */
    /* JADX WARN: Type inference failed for: r5v4, types: [supwisdom.ty0] */
    public static <T, G extends T> void a(vy0 vy0Var, az0<G> az0Var, Method[] methodArr, az0<T> az0Var2) {
        az0 az0Var3;
        Class<?>[] clsArr;
        yy0 yy0VarB;
        yy0 yy0VarB2;
        yy0 yy0Var;
        yy0[] yy0VarArr;
        az0 az0Var4;
        zy0 zy0Var;
        vy0 vy0Var2 = vy0Var;
        az0<G> az0Var5 = az0Var;
        Method[] methodArr2 = methodArr;
        az0 az0VarA = az0.a(InvocationHandler.class);
        az0 az0VarA2 = az0.a(Method[].class);
        Object objA = az0Var5.a(az0VarA, "$__handler");
        Object objA2 = az0Var5.a(az0VarA2, "$__methodArray");
        az0<?> az0VarA3 = az0.a(Method.class);
        az0<?> az0VarA4 = az0.a(Object[].class);
        az0<?> az0Var6 = az0.m;
        zy0 zy0VarA = az0VarA.a(az0Var6, "invoke", az0Var6, az0VarA3, az0VarA4);
        int i = 0;
        az0 az0Var7 = az0VarA;
        az0 az0Var8 = az0VarA2;
        while (i < methodArr2.length) {
            Method method = methodArr2[i];
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            int length = parameterTypes.length;
            az0<?>[] az0VarArr = new az0[length];
            for (int i2 = 0; i2 < length; i2++) {
                az0VarArr[i2] = az0.a(parameterTypes[i2]);
            }
            Class<?> returnType = method.getReturnType();
            zy0 zy0Var2 = zy0VarA;
            az0 az0VarA5 = az0.a(returnType);
            Object obj = objA;
            zy0<?, ?> zy0VarA2 = az0Var5.a(az0VarA5, name, az0VarArr);
            az0 az0VarA6 = az0.a(AbstractMethodError.class);
            Object obj2 = objA2;
            ?? A = vy0Var2.a(zy0VarA2, 1);
            yy0 yy0VarA = A.a(az0Var5);
            yy0 yy0VarB3 = A.b(az0Var7);
            az0<Object> az0Var9 = az0.m;
            yy0 yy0VarB4 = A.b(az0Var9);
            az0<Integer> az0Var10 = az0.i;
            yy0 yy0VarB5 = A.b(az0Var10);
            yy0 yy0VarB6 = A.b(az0VarA4);
            az0<?> az0Var11 = az0VarA4;
            yy0 yy0VarB7 = A.b(az0Var10);
            yy0 yy0VarB8 = A.b(az0Var9);
            yy0 yy0VarB9 = A.b(az0VarA5);
            yy0 yy0VarB10 = A.b(az0Var8);
            az0 az0Var12 = az0Var8;
            yy0 yy0VarB11 = A.b(az0VarA3);
            yy0 yy0VarB12 = A.b(az0Var10);
            az0<?> az0Var13 = az0VarA3;
            Class<?> cls = l.get(returnType);
            yy0 yy0VarB13 = cls != null ? A.b(az0.a(cls)) : null;
            yy0 yy0VarB14 = A.b(az0Var7);
            az0 az0Var14 = az0Var7;
            if ((method.getModifiers() & 1024) == 0) {
                yy0[] yy0VarArr2 = new yy0[parameterTypes.length];
                yy0 yy0VarB15 = A.b(az0VarA5);
                zy0 zy0VarA3 = az0Var2.a(az0VarA5, name, az0VarArr);
                az0Var3 = az0VarA5;
                yy0Var = yy0VarB15;
                yy0VarB = null;
                az0Var4 = az0VarA6;
                zy0Var = zy0VarA3;
                yy0VarArr = yy0VarArr2;
                clsArr = parameterTypes;
                yy0VarB2 = null;
            } else {
                az0Var3 = az0VarA5;
                clsArr = parameterTypes;
                yy0VarB = A.b(az0.n);
                yy0VarB2 = A.b(az0VarA6);
                yy0Var = null;
                yy0VarArr = null;
                az0Var4 = az0VarA6;
                zy0Var = null;
            }
            A.a(yy0VarB12, Integer.valueOf(i));
            A.a(obj2, yy0VarB10);
            A.a(yy0VarB11, yy0VarB10, yy0VarB12);
            A.a(yy0VarB7, Integer.valueOf(length));
            A.b(yy0VarB6, yy0VarB7);
            A.a(obj, yy0VarB3, yy0VarA);
            A.a(yy0VarB14, null);
            xy0 xy0Var = new xy0();
            A.a(Comparison.EQ, xy0Var, yy0VarB14, yy0VarB3);
            int i3 = length;
            int i4 = 0;
            while (i4 < i3) {
                A.a(yy0VarB5, Integer.valueOf(i4));
                A.b(yy0VarB6, yy0VarB5, a((ty0) A, (yy0<?>) A.a(i4, az0VarArr[i4]), (yy0<Object>) yy0VarB8));
                i4++;
                i3 = i3;
                i = i;
                yy0VarB2 = yy0VarB2;
            }
            int i5 = i;
            yy0 yy0Var2 = yy0VarB2;
            A.b(zy0Var2, yy0VarB4, yy0VarB3, yy0VarA, yy0VarB11, yy0VarB6);
            a((ty0) A, returnType, yy0VarB4, yy0VarB9, yy0VarB13);
            A.c(xy0Var);
            if ((method.getModifiers() & 1024) == 0) {
                for (int i6 = 0; i6 < yy0VarArr.length; i6++) {
                    yy0VarArr[i6] = A.a(i6, az0VarArr[i6]);
                }
                if (Void.TYPE.equals(returnType)) {
                    A.c(zy0Var, null, yy0VarA, yy0VarArr);
                    A.d();
                } else {
                    a(zy0Var, (ty0) A, yy0VarA, yy0VarArr, yy0Var);
                    A.a(yy0Var);
                }
            } else {
                a((ty0) A, method, (yy0<String>) yy0VarB, (yy0<AbstractMethodError>) yy0Var2);
            }
            az0Var5 = az0Var;
            az0 az0Var15 = az0Var3;
            ?? A2 = vy0Var.a(az0Var5.a(az0Var15, a(method), az0VarArr), 1);
            if ((method.getModifiers() & 1024) == 0) {
                yy0 yy0VarA2 = A2.a(az0Var5);
                int length2 = clsArr.length;
                yy0[] yy0VarArr3 = new yy0[length2];
                for (int i7 = 0; i7 < length2; i7++) {
                    yy0VarArr3[i7] = A2.a(i7, az0VarArr[i7]);
                }
                if (Void.TYPE.equals(returnType)) {
                    A2.c(zy0Var, null, yy0VarA2, yy0VarArr3);
                    A2.d();
                } else {
                    yy0 yy0VarB16 = A2.b(az0Var15);
                    a(zy0Var, (ty0) A2, yy0VarA2, yy0VarArr3, yy0VarB16);
                    A2.a(yy0VarB16);
                }
            } else {
                a((ty0) A2, method, (yy0<String>) A2.b(az0.n), (yy0<AbstractMethodError>) A2.b(az0Var4));
            }
            methodArr2 = methodArr;
            i = i5 + 1;
            zy0VarA = zy0Var2;
            vy0Var2 = vy0Var;
            objA = obj;
            objA2 = obj2;
            az0VarA4 = az0Var11;
            az0Var8 = az0Var12;
            az0VarA3 = az0Var13;
            az0Var7 = az0Var14;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Constructor<T>[] b(Class<T> cls) {
        return (Constructor<T>[]) cls.getDeclaredConstructors();
    }

    public static void a(ty0 ty0Var, Class cls, yy0 yy0Var, yy0 yy0Var2, yy0 yy0Var3) {
        if (n.containsKey(cls)) {
            ty0Var.a((yy0<?>) yy0Var3, (yy0<?>) yy0Var);
            ty0Var.d(d(cls), yy0Var2, yy0Var3, new yy0[0]);
            ty0Var.a((yy0<?>) yy0Var2);
        } else if (Void.TYPE.equals(cls)) {
            ty0Var.d();
        } else {
            ty0Var.a((yy0<?>) yy0Var2, (yy0<?>) yy0Var);
            ty0Var.a((yy0<?>) yy0Var2);
        }
    }

    public static <T, G extends T> void a(vy0 vy0Var, az0<G> az0Var, az0<T> az0Var2, Class<T> cls) {
        az0<V> az0VarA = az0.a(InvocationHandler.class);
        az0<V> az0VarA2 = az0.a(Method[].class);
        vy0Var.a(az0Var.a(az0VarA, "$__handler"), 2, (Object) null);
        vy0Var.a(az0Var.a(az0VarA2, "$__methodArray"), 10, (Object) null);
        for (Constructor constructor : b(cls)) {
            if (constructor.getModifiers() != 16) {
                az0<?>[] az0VarArrA = a(constructor.getParameterTypes());
                ty0 ty0VarA = vy0Var.a(az0Var.a(az0VarArrA), 1);
                yy0<T> yy0VarA = ty0VarA.a(az0Var);
                int length = az0VarArrA.length;
                yy0<?>[] yy0VarArr = new yy0[length];
                for (int i = 0; i < length; i++) {
                    yy0VarArr[i] = ty0VarA.a(i, az0VarArrA[i]);
                }
                ty0VarA.a(az0Var2.a(az0VarArrA), (yy0) null, yy0VarA, yy0VarArr);
                ty0VarA.d();
            }
        }
    }

    public final void a(Set<b> set, Set<b> set2, Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if ((method.getModifiers() & 16) != 0) {
                b bVar = new b(method);
                set2.add(bVar);
                set.remove(bVar);
            } else if ((method.getModifiers() & 8) == 0 && ((Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers()) || (this.i && !Modifier.isPrivate(method.getModifiers()))) && (!method.getName().equals("finalize") || method.getParameterTypes().length != 0))) {
                b bVar2 = new b(method);
                if (!set2.contains(bVar2)) {
                    set.add(bVar2);
                }
            }
        }
        if (cls.isInterface()) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                a(set, set2, cls2);
            }
        }
    }

    public g61<T> a(InvocationHandler invocationHandler) {
        this.c = invocationHandler;
        return this;
    }

    public static void a(zy0 zy0Var, ty0 ty0Var, yy0 yy0Var, yy0[] yy0VarArr, yy0 yy0Var2) {
        ty0Var.c(zy0Var, yy0Var2, yy0Var, yy0VarArr);
    }

    public static RuntimeException a(InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (!(cause instanceof Error)) {
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new UndeclaredThrowableException(cause);
        }
        throw ((Error) cause);
    }

    public final Class<? extends T> a(ClassLoader classLoader, String str) {
        return (Class<? extends T>) classLoader.loadClass(str);
    }

    public static void a(Object obj, InvocationHandler invocationHandler) {
        try {
            Field declaredField = obj.getClass().getDeclaredField("$__handler");
            declaredField.setAccessible(true);
            declaredField.set(obj, invocationHandler);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        } catch (NoSuchFieldException e3) {
            throw new IllegalArgumentException("Not a valid proxy instance", e3);
        }
    }

    public static void a(Class<?> cls, Method[] methodArr) {
        try {
            Field declaredField = cls.getDeclaredField("$__methodArray");
            declaredField.setAccessible(true);
            declaredField.set(null, methodArr);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        } catch (NoSuchFieldException e3) {
            throw new AssertionError(e3);
        }
    }

    public static String a(Method method) {
        return "super$" + method.getName() + Operators.DOLLAR_STR + method.getReturnType().getName().replace('.', '_').replace(Operators.ARRAY_START, '_').replace(MECARDContactEncoder.TERMINATOR, '_');
    }

    public static void a(ty0 ty0Var, Method method, yy0<String> yy0Var, yy0<AbstractMethodError> yy0Var2) {
        zy0<T, Void> zy0VarA = az0.a(AbstractMethodError.class).a(az0.n);
        ty0Var.a(yy0Var, "'" + method + "' cannot be called");
        ty0Var.a(yy0Var2, zy0VarA, yy0Var);
        ty0Var.b(yy0Var2);
    }
}
