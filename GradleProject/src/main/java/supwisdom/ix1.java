package supwisdom;

import java.io.Serializable;

/* JADX INFO: compiled from: NotificationLite.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ix1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f7997a = new a();
    public static final Object b = new b();

    /* JADX INFO: compiled from: NotificationLite.java */
    public static class a implements Serializable {
        public String toString() {
            return "Notification=>Completed";
        }
    }

    /* JADX INFO: compiled from: NotificationLite.java */
    public static class b implements Serializable {
        public String toString() {
            return "Notification=>NULL";
        }
    }

    /* JADX INFO: compiled from: NotificationLite.java */
    public static final class c implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f7998a;

        public c(Throwable th) {
            this.f7998a = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.f7998a;
        }
    }

    public static Object a() {
        return f7997a;
    }

    public static boolean b(Object obj) {
        return obj == f7997a;
    }

    public static boolean c(Object obj) {
        return obj instanceof c;
    }

    public static <T> Object d(T t) {
        return t == null ? b : t;
    }

    public static Object a(Throwable th) {
        return new c(th);
    }

    public static <T> boolean a(sw1<? super T> sw1Var, Object obj) {
        if (obj == f7997a) {
            sw1Var.onCompleted();
            return true;
        }
        if (obj == b) {
            sw1Var.onNext(null);
            return false;
        }
        if (obj != null) {
            if (obj.getClass() == c.class) {
                sw1Var.onError(((c) obj).f7998a);
                return true;
            }
            sw1Var.onNext(obj);
            return false;
        }
        throw new IllegalArgumentException("The lite notification can not be null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T a(Object obj) {
        if (obj == b) {
            return null;
        }
        return obj;
    }
}
