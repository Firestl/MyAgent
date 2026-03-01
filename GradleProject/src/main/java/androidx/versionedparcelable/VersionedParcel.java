package androidx.versionedparcelable;

import android.os.Parcelable;
import com.huawei.hms.framework.common.ExceptionCode;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import supwisdom.j4;
import supwisdom.yh;

/* JADX INFO: loaded from: classes.dex */
public abstract class VersionedParcel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j4<String, Method> f1447a;
    public final j4<String, Method> b;
    public final j4<String, Class> c;

    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }

    public VersionedParcel(j4<String, Method> j4Var, j4<String, Method> j4Var2, j4<String, Class> j4Var3) {
        this.f1447a = j4Var;
        this.b = j4Var2;
        this.c = j4Var3;
    }

    public abstract void a();

    public abstract void a(Parcelable parcelable);

    public abstract void a(CharSequence charSequence);

    public abstract void a(boolean z);

    public void a(boolean z, boolean z2) {
    }

    public abstract void a(byte[] bArr);

    public abstract boolean a(int i);

    public boolean a(boolean z, int i) {
        return !a(i) ? z : d();
    }

    public abstract VersionedParcel b();

    public abstract void b(int i);

    public abstract void b(String str);

    public void b(boolean z, int i) {
        b(i);
        a(z);
    }

    public abstract void c(int i);

    public boolean c() {
        return false;
    }

    public abstract boolean d();

    public abstract byte[] e();

    public abstract CharSequence f();

    public abstract int g();

    public abstract <T extends Parcelable> T h();

    public abstract String i();

    public <T extends yh> T j() {
        String strI = i();
        if (strI == null) {
            return null;
        }
        return (T) a(strI, b());
    }

    public int a(int i, int i2) {
        return !a(i2) ? i : g();
    }

    public void b(byte[] bArr, int i) {
        b(i);
        a(bArr);
    }

    public String a(String str, int i) {
        return !a(i) ? str : i();
    }

    public void b(CharSequence charSequence, int i) {
        b(i);
        a(charSequence);
    }

    public byte[] a(byte[] bArr, int i) {
        return !a(i) ? bArr : e();
    }

    public void b(int i, int i2) {
        b(i2);
        c(i);
    }

    public <T extends Parcelable> T a(T t, int i) {
        return !a(i) ? t : (T) h();
    }

    public void b(String str, int i) {
        b(i);
        b(str);
    }

    public CharSequence a(CharSequence charSequence, int i) {
        return !a(i) ? charSequence : f();
    }

    public void b(Parcelable parcelable, int i) {
        b(i);
        a(parcelable);
    }

    public void a(yh yhVar) {
        if (yhVar == null) {
            b((String) null);
            return;
        }
        b(yhVar);
        VersionedParcel versionedParcelB = b();
        a(yhVar, versionedParcelB);
        versionedParcelB.a();
    }

    public void b(yh yhVar, int i) {
        b(i);
        a(yhVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(yh yhVar) {
        try {
            b(a((Class<? extends yh>) yhVar.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(yhVar.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    public <T extends yh> T a(T t, int i) {
        return !a(i) ? t : (T) j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method b(Class cls) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class clsA = a((Class<? extends yh>) cls);
        System.currentTimeMillis();
        Method declaredMethod = clsA.getDeclaredMethod(ExceptionCode.WRITE, cls, VersionedParcel.class);
        this.b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public <T extends yh> T a(String str, VersionedParcel versionedParcel) {
        try {
            return (T) a(str).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    public <T extends yh> void a(T t, VersionedParcel versionedParcel) {
        try {
            b(t.getClass()).invoke(null, t, versionedParcel);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    public final Method a(String str) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.f1447a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod(ExceptionCode.READ, VersionedParcel.class);
        this.f1447a.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Class a(Class<? extends yh> cls) throws ClassNotFoundException {
        Class cls2 = this.c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.c.put(cls.getName(), cls3);
        return cls3;
    }
}
