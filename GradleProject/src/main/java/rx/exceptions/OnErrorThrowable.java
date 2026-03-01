package rx.exceptions;

import com.igexin.push.core.b;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import supwisdom.lz1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class OnErrorThrowable extends RuntimeException {
    public static final long serialVersionUID = -569558213262703934L;
    public final boolean hasValue;
    public final Object value;

    public static class OnNextValue extends RuntimeException {
        public static final long serialVersionUID = -3454462756050397899L;
        public final Object value;

        public static final class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final Set<Class<?>> f6818a = a();

            public static Set<Class<?>> a() {
                HashSet hashSet = new HashSet();
                hashSet.add(Boolean.class);
                hashSet.add(Character.class);
                hashSet.add(Byte.class);
                hashSet.add(Short.class);
                hashSet.add(Integer.class);
                hashSet.add(Long.class);
                hashSet.add(Float.class);
                hashSet.add(Double.class);
                return hashSet;
            }
        }

        public OnNextValue(Object obj) {
            super("OnError while emitting onNext value: " + renderValue(obj));
            if (!(obj instanceof Serializable)) {
                try {
                    obj = String.valueOf(obj);
                } catch (Throwable th) {
                    obj = th.getMessage();
                }
            }
            this.value = obj;
        }

        public static String renderValue(Object obj) {
            if (obj == null) {
                return b.m;
            }
            if (a.f6818a.contains(obj.getClass())) {
                return obj.toString();
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Enum) {
                return ((Enum) obj).name();
            }
            String strA = lz1.f().b().a(obj);
            if (strA != null) {
                return strA;
            }
            return obj.getClass().getName() + ".class";
        }

        public Object getValue() {
            return this.value;
        }
    }

    public OnErrorThrowable(Throwable th) {
        super(th);
        this.hasValue = false;
        this.value = null;
    }

    public static Throwable addValueAsLastCause(Throwable th, Object obj) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable thA = zw1.a(th);
        if ((thA instanceof OnNextValue) && ((OnNextValue) thA).getValue() == obj) {
            return th;
        }
        zw1.a(th, new OnNextValue(obj));
        return th;
    }

    public static OnErrorThrowable from(Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable thA = zw1.a(th);
        return thA instanceof OnNextValue ? new OnErrorThrowable(th, ((OnNextValue) thA).getValue()) : new OnErrorThrowable(th);
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isValueNull() {
        return this.hasValue;
    }

    public OnErrorThrowable(Throwable th, Object obj) {
        super(th);
        this.hasValue = true;
        if (!(obj instanceof Serializable)) {
            try {
                obj = String.valueOf(obj);
            } catch (Throwable th2) {
                obj = th2.getMessage();
            }
        }
        this.value = obj;
    }
}
