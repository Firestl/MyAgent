package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class vy0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f9561e;
    public static boolean f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<az0<?>, c> f9562a = new LinkedHashMap();
    public ClassLoader b;
    public t11 c;
    public boolean d;

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final wy0<?, ?> f9563a;
        public final int b;
        public final Object c;

        public a(wy0<?, ?> wy0Var, int i, Object obj) {
            if ((i & 8) == 0 && obj != null) {
                throw new IllegalArgumentException("instance fields may not have a value");
            }
            this.f9563a = wy0Var;
            this.b = i;
            this.c = obj;
        }

        public v11 b() {
            return new v11(this.f9563a.c, this.b);
        }

        public boolean a() {
            return (this.b & 8) != 0;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final zy0<?, ?> f9564a;
        public final int b;
        public final ty0 c = new ty0(this);

        public b(zy0<?, ?> zy0Var, int i) {
            this.f9564a = zy0Var;
            this.b = i;
        }

        public boolean b() {
            return (this.b & 8) != 0;
        }

        public boolean a() {
            return (this.b & 65546) != 0;
        }

        public x11 a(cz0 cz0Var) {
            return new x11(this.f9564a.f10048e, this.b, yz0.a(new p41(this.c.e(), 0), 1, null, this.c.c(), cz0Var), a61.c);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final az0<?> f9565a;
        public boolean b;
        public int c;
        public az0<?> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f9566e;
        public bz0 f;
        public o11 g;
        public final Map<wy0, a> h = new LinkedHashMap();
        public final Map<zy0, b> i = new LinkedHashMap();

        public c(az0<?> az0Var) {
            this.f9565a = az0Var;
        }

        public o11 a() {
            if (this.b) {
                cz0 cz0Var = new cz0();
                cz0Var.b = 13;
                w51 w51Var = this.f9565a.c;
                if (this.g == null) {
                    this.g = new o11(w51Var, this.c, this.d.c, this.f.b, new v51(this.f9566e));
                    for (b bVar : this.i.values()) {
                        x11 x11VarA = bVar.a(cz0Var);
                        if (bVar.a()) {
                            this.g.a(x11VarA);
                        } else {
                            this.g.b(x11VarA);
                        }
                    }
                    for (a aVar : this.h.values()) {
                        v11 v11VarB = aVar.b();
                        if (aVar.a()) {
                            this.g.a(v11VarB, uy0.a(aVar.c));
                        } else {
                            this.g.a(v11VarB);
                        }
                    }
                }
                return this.g;
            }
            throw new IllegalStateException("Undeclared type " + this.f9565a + " declares members: " + this.h.keySet() + Operators.SPACE_STR + this.i.keySet());
        }
    }

    public void a(az0<?> az0Var, String str, int i, az0<?> az0Var2, az0<?>... az0VarArr) {
        c cVarA = a(az0Var);
        if ((i & (-5138)) != 0) {
            throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i));
        }
        if (cVarA.b) {
            throw new IllegalStateException("already declared: " + az0Var);
        }
        cVarA.b = true;
        cVarA.c = i;
        cVarA.d = az0Var2;
        cVarA.f9566e = str;
        cVarA.f = new bz0(az0VarArr);
    }

    public final String b() {
        Set<az0<?>> setKeySet = this.f9562a.keySet();
        Iterator<az0<?>> it = setKeySet.iterator();
        int size = setKeySet.size();
        int[] iArr = new int[size];
        int i = 0;
        while (it.hasNext()) {
            c cVarA = a(it.next());
            Set setKeySet2 = cVarA.i.keySet();
            if (cVarA.d != null) {
                iArr[i] = (cVarA.d.hashCode() * 31) + setKeySet2.hashCode();
                i++;
            }
        }
        Arrays.sort(iArr);
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        return "Generated_" + i2 + ".jar";
    }

    public void c() {
        this.d = true;
    }

    public byte[] a() {
        if (this.c == null) {
            cz0 cz0Var = new cz0();
            cz0Var.b = 13;
            this.c = new t11(cz0Var);
        }
        Iterator<c> it = this.f9562a.values().iterator();
        while (it.hasNext()) {
            this.c.a(it.next().a());
        }
        try {
            return this.c.a((Writer) null, false);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public ClassLoader a(ClassLoader classLoader, File file) {
        if (file == null) {
            String property = System.getProperty("dexmaker.dexcache");
            if (property != null) {
                file = new File(property);
            } else {
                file = new sy0().a();
                if (file == null) {
                    throw new IllegalArgumentException("dexcache == null (and no default could be found; consider setting the 'dexmaker.dexcache' system property)");
                }
            }
        }
        File file2 = new File(file, b());
        if (file2.exists()) {
            if (!file2.canWrite()) {
                return a(file2, file, classLoader);
            }
            file2.delete();
        }
        byte[] bArrA = a();
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file2));
        file2.setReadOnly();
        try {
            JarEntry jarEntry = new JarEntry("classes.dex");
            jarEntry.setSize(bArrA.length);
            jarOutputStream.putNextEntry(jarEntry);
            try {
                jarOutputStream.write(bArrA);
                jarOutputStream.close();
                return a(file2, file, classLoader);
            } finally {
                jarOutputStream.closeEntry();
            }
        } catch (Throwable th) {
            jarOutputStream.close();
            throw th;
        }
    }

    public final ClassLoader a(File file, File file2, ClassLoader classLoader) {
        try {
            try {
                ClassLoader classLoader2 = this.b;
                boolean z = classLoader2 != null;
                ClassLoader classLoader3 = classLoader != null ? classLoader : classLoader2 != null ? classLoader2 : null;
                Class<?> cls = Class.forName("dalvik.system.BaseDexClassLoader");
                if (z && !cls.isAssignableFrom(classLoader3.getClass())) {
                    if (!classLoader3.getClass().getName().equals("java.lang.BootClassLoader") && !f) {
                        System.err.println("Cannot share classloader as shared classloader '" + classLoader3 + "' is not a subclass of '" + cls + "'");
                        f = true;
                    }
                    z = false;
                }
                if (this.d) {
                    try {
                        if (z) {
                            classLoader3.getClass().getMethod("addDexPath", String.class, Boolean.TYPE).invoke(classLoader3, file.getPath(), Boolean.TRUE);
                            return classLoader3;
                        }
                        return (ClassLoader) cls.getConstructor(String.class, File.class, String.class, ClassLoader.class, Boolean.TYPE).newInstance(file.getPath(), file2.getAbsoluteFile(), null, classLoader3, Boolean.TRUE);
                    } catch (InvocationTargetException e2) {
                        if (e2.getCause() instanceof SecurityException) {
                            if (!f9561e) {
                                System.err.println("Cannot allow to call blacklisted super methods. This might break spying on system classes." + e2.getCause());
                                f9561e = true;
                            }
                        } else {
                            throw e2;
                        }
                    }
                }
                if (!z) {
                    return (ClassLoader) Class.forName("dalvik.system.DexClassLoader").getConstructor(String.class, String.class, String.class, ClassLoader.class).newInstance(file.getPath(), file2.getAbsolutePath(), null, classLoader3);
                }
                classLoader3.getClass().getMethod("addDexPath", String.class).invoke(classLoader3, file.getPath());
                return classLoader3;
            } catch (ClassNotFoundException e3) {
                throw new UnsupportedOperationException("load() requires a Dalvik VM", e3);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InstantiationException unused2) {
                throw new AssertionError();
            } catch (NoSuchMethodException unused3) {
                throw new AssertionError();
            }
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4.getCause());
        }
    }

    public c a(az0<?> az0Var) {
        c cVar = this.f9562a.get(az0Var);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(az0Var);
        this.f9562a.put(az0Var, cVar2);
        return cVar2;
    }

    public void a(ClassLoader classLoader) {
        this.b = classLoader;
    }

    public ty0 a(zy0<?, ?> zy0Var, int i) {
        c cVarA = a(zy0Var.f10047a);
        if (cVarA.i.containsKey(zy0Var)) {
            throw new IllegalStateException("already declared: " + zy0Var);
        }
        if ((i & (-4224)) == 0) {
            if ((i & 32) != 0) {
                i = (i & (-33)) | 131072;
            }
            if (zy0Var.a() || zy0Var.b()) {
                i |= 65536;
            }
            b bVar = new b(zy0Var, i);
            cVarA.i.put(zy0Var, bVar);
            return bVar.c;
        }
        throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i));
    }

    public void a(wy0<?, ?> wy0Var, int i, Object obj) {
        c cVarA = a(wy0Var.f9683a);
        if (cVarA.h.containsKey(wy0Var)) {
            throw new IllegalStateException("already declared: " + wy0Var);
        }
        if ((i & (-4320)) != 0) {
            throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i));
        }
        if ((i & 8) == 0 && obj != null) {
            throw new IllegalArgumentException("staticValue is non-null, but field is not static");
        }
        cVarA.h.put(wy0Var, new a(wy0Var, i, obj));
    }
}
