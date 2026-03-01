package supwisdom;

import com.google.zxing.client.android.encode.MECARDContactEncoder;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class az0<T> {
    public static final az0<Boolean> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final az0<Byte> f6997e;
    public static final az0<Character> f;
    public static final az0<Double> g;
    public static final az0<Float> h;
    public static final az0<Integer> i;
    public static final az0<Long> j;
    public static final az0<Short> k;
    public static final az0<Void> l;
    public static final az0<Object> m;
    public static final az0<String> n;
    public static final Map<Class<?>, az0<?>> o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6998a;
    public final b61 b;
    public final w51 c;

    static {
        az0<Boolean> az0Var = new az0<>(b61.g);
        d = az0Var;
        az0<Byte> az0Var2 = new az0<>(b61.h);
        f6997e = az0Var2;
        az0<Character> az0Var3 = new az0<>(b61.i);
        f = az0Var3;
        az0<Double> az0Var4 = new az0<>(b61.j);
        g = az0Var4;
        az0<Float> az0Var5 = new az0<>(b61.k);
        h = az0Var5;
        az0<Integer> az0Var6 = new az0<>(b61.l);
        i = az0Var6;
        az0<Long> az0Var7 = new az0<>(b61.m);
        j = az0Var7;
        az0<Short> az0Var8 = new az0<>(b61.n);
        k = az0Var8;
        az0<Void> az0Var9 = new az0<>(b61.o);
        l = az0Var9;
        m = new az0<>(b61.x);
        n = new az0<>(b61.z);
        HashMap map = new HashMap();
        o = map;
        map.put(Boolean.TYPE, az0Var);
        map.put(Byte.TYPE, az0Var2);
        map.put(Character.TYPE, az0Var3);
        map.put(Double.TYPE, az0Var4);
        map.put(Float.TYPE, az0Var5);
        map.put(Integer.TYPE, az0Var6);
        map.put(Long.TYPE, az0Var7);
        map.put(Short.TYPE, az0Var8);
        map.put(Void.TYPE, az0Var9);
    }

    public az0(b61 b61Var) {
        this(b61Var.g(), b61Var);
    }

    public static <T> az0<T> a(String str) {
        return new az0<>(str, b61.b(str));
    }

    public boolean equals(Object obj) {
        return (obj instanceof az0) && ((az0) obj).f6998a.equals(this.f6998a);
    }

    public int hashCode() {
        return this.f6998a.hashCode();
    }

    public String toString() {
        return this.f6998a;
    }

    public az0(String str, b61 b61Var) {
        if (str == null || b61Var == null) {
            throw null;
        }
        this.f6998a = str;
        this.b = b61Var;
        this.c = w51.a(b61Var);
    }

    public zy0<T, Void> a(az0<?>... az0VarArr) {
        return new zy0<>(this, l, "<init>", new bz0(az0VarArr));
    }

    public <V> wy0<T, V> a(az0<V> az0Var, String str) {
        return new wy0<>(this, az0Var, str);
    }

    public <R> zy0<T, R> a(az0<R> az0Var, String str, az0<?>... az0VarArr) {
        return new zy0<>(this, az0Var, str, new bz0(az0VarArr));
    }

    public static <T> az0<T> a(Class<T> cls) {
        if (cls.isPrimitive()) {
            return (az0) o.get(cls);
        }
        String strReplace = cls.getName().replace('.', '/');
        if (!cls.isArray()) {
            strReplace = Matrix.MATRIX_TYPE_RANDOM_LT + strReplace + MECARDContactEncoder.TERMINATOR;
        }
        return a(strReplace);
    }
}
