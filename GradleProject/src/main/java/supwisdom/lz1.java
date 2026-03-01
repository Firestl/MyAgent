package supwisdom;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: RxJavaPlugins.java */
/* JADX INFO: loaded from: classes3.dex */
public class lz1 {
    public static final lz1 f = new lz1();
    public static final hz1 g = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference<hz1> f8333a = new AtomicReference<>();
    public final AtomicReference<jz1> b = new AtomicReference<>();
    public final AtomicReference<nz1> c = new AtomicReference<>();
    public final AtomicReference<gz1> d = new AtomicReference<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicReference<mz1> f8334e = new AtomicReference<>();

    /* JADX INFO: compiled from: RxJavaPlugins.java */
    public static class a extends hz1 {
    }

    /* JADX INFO: compiled from: RxJavaPlugins.java */
    public class b extends gz1 {
        public b(lz1 lz1Var) {
        }
    }

    @Deprecated
    public static lz1 f() {
        return f;
    }

    public gz1 a() {
        if (this.d.get() == null) {
            Object objA = a(gz1.class, System.getProperties());
            if (objA == null) {
                this.d.compareAndSet(null, new b(this));
            } else {
                this.d.compareAndSet(null, (gz1) objA);
            }
        }
        return this.d.get();
    }

    public hz1 b() {
        if (this.f8333a.get() == null) {
            Object objA = a(hz1.class, System.getProperties());
            if (objA == null) {
                this.f8333a.compareAndSet(null, g);
            } else {
                this.f8333a.compareAndSet(null, (hz1) objA);
            }
        }
        return this.f8333a.get();
    }

    public jz1 c() {
        if (this.b.get() == null) {
            Object objA = a(jz1.class, System.getProperties());
            if (objA == null) {
                this.b.compareAndSet(null, kz1.a());
            } else {
                this.b.compareAndSet(null, (jz1) objA);
            }
        }
        return this.b.get();
    }

    public mz1 d() {
        if (this.f8334e.get() == null) {
            Object objA = a(mz1.class, System.getProperties());
            if (objA == null) {
                this.f8334e.compareAndSet(null, mz1.g());
            } else {
                this.f8334e.compareAndSet(null, (mz1) objA);
            }
        }
        return this.f8334e.get();
    }

    public nz1 e() {
        if (this.c.get() == null) {
            Object objA = a(nz1.class, System.getProperties());
            if (objA == null) {
                this.c.compareAndSet(null, oz1.a());
            } else {
                this.c.compareAndSet(null, (nz1) objA);
            }
        }
        return this.c.get();
    }

    public static Object a(Class<?> cls, Properties properties) {
        Properties properties2 = (Properties) properties.clone();
        String simpleName = cls.getSimpleName();
        String property = properties2.getProperty("rxjava.plugin." + simpleName + ".implementation");
        if (property == null) {
            Iterator it = properties2.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String string = entry.getKey().toString();
                if (string.startsWith("rxjava.plugin.") && string.endsWith(".class") && simpleName.equals(entry.getValue().toString())) {
                    String str = "rxjava.plugin." + string.substring(0, string.length() - 6).substring(14) + ".impl";
                    String property2 = properties2.getProperty(str);
                    if (property2 == null) {
                        throw new IllegalStateException("Implementing class declaration for " + simpleName + " missing: " + str);
                    }
                    property = property2;
                }
            }
        }
        if (property == null) {
            return null;
        }
        try {
            return Class.forName(property).asSubclass(cls).newInstance();
        } catch (ClassCastException e2) {
            throw new IllegalStateException(simpleName + " implementation is not an instance of " + simpleName + ": " + property, e2);
        } catch (ClassNotFoundException e3) {
            throw new IllegalStateException(simpleName + " implementation class not found: " + property, e3);
        } catch (IllegalAccessException e4) {
            throw new IllegalStateException(simpleName + " implementation not able to be accessed: " + property, e4);
        } catch (InstantiationException e5) {
            throw new IllegalStateException(simpleName + " implementation not able to be instantiated: " + property, e5);
        }
    }
}
