package rx;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes3.dex */
public final class Notification<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Kind f6815a;
    public final Throwable b;
    public final T c;

    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    static {
        new Notification(Kind.OnCompleted, null, null);
    }

    public Notification(Kind kind, T t, Throwable th) {
        this.c = t;
        this.b = th;
        this.f6815a = kind;
    }

    public Kind a() {
        return this.f6815a;
    }

    public Throwable b() {
        return this.b;
    }

    public T c() {
        return this.c;
    }

    public boolean d() {
        return f() && this.b != null;
    }

    public boolean e() {
        return g() && this.c != null;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != Notification.class) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.a() != a()) {
            return false;
        }
        T t = this.c;
        T t2 = notification.c;
        if (t != t2 && (t == null || !t.equals(t2))) {
            return false;
        }
        Throwable th = this.b;
        Throwable th2 = notification.b;
        return th == th2 || (th != null && th.equals(th2));
    }

    public boolean f() {
        return a() == Kind.OnError;
    }

    public boolean g() {
        return a() == Kind.OnNext;
    }

    public int hashCode() {
        int iHashCode = a().hashCode();
        if (e()) {
            iHashCode = (iHashCode * 31) + c().hashCode();
        }
        return d() ? (iHashCode * 31) + b().hashCode() : iHashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(Operators.ARRAY_START);
        sb.append(super.toString());
        sb.append(' ');
        sb.append(a());
        if (e()) {
            sb.append(' ');
            sb.append(c());
        }
        if (d()) {
            sb.append(' ');
            sb.append(b().getMessage());
        }
        sb.append(Operators.ARRAY_END);
        return sb.toString();
    }
}
