package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.util.Collection;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class t21 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9239a;
    public final t11 b;
    public final int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9240e;

    public t21(String str, t11 t11Var, int i) {
        if (t11Var == null) {
            throw new NullPointerException("file == null");
        }
        c(i);
        this.f9239a = str;
        this.b = t11Var;
        this.c = i;
        this.d = -1;
        this.f9240e = false;
    }

    public abstract int a(f21 f21Var);

    public final void a(h61 h61Var) {
        h61Var.d(this.c);
    }

    public final t11 b() {
        return this.b;
    }

    public final int c() {
        int i = this.d;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("fileOffset not set");
    }

    public abstract void c(h61 h61Var);

    public abstract Collection<? extends f21> d();

    public final void e() {
        h();
        f();
        this.f9240e = true;
    }

    public abstract void f();

    public final void g() {
        if (!this.f9240e) {
            throw new RuntimeException("not prepared");
        }
    }

    public final void h() {
        if (this.f9240e) {
            throw new RuntimeException("already prepared");
        }
    }

    public abstract int i();

    public final int a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("relative < 0");
        }
        int i2 = this.d;
        if (i2 >= 0) {
            return i2 + i;
        }
        throw new RuntimeException("fileOffset not yet set");
    }

    public final int b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("fileOffset < 0");
        }
        if (this.d >= 0) {
            throw new RuntimeException("fileOffset already set");
        }
        int i2 = this.c - 1;
        int i3 = (i + i2) & (~i2);
        this.d = i3;
        return i3;
    }

    public static void c(int i) {
        if (i <= 0 || (i & (i - 1)) != 0) {
            throw new IllegalArgumentException("invalid alignment");
        }
    }

    public final int a() {
        return this.c;
    }

    public final void b(h61 h61Var) {
        g();
        a(h61Var);
        int iA = h61Var.a();
        int i = this.d;
        if (i < 0) {
            this.d = iA;
        } else if (i != iA) {
            throw new RuntimeException("alignment mismatch: for " + this + ", at " + iA + ", but expected " + this.d);
        }
        if (h61Var.e()) {
            if (this.f9239a != null) {
                h61Var.a(0, "\n" + this.f9239a + Constants.COLON_SEPARATOR);
            } else if (iA != 0) {
                h61Var.a(0, "\n");
            }
        }
        c(h61Var);
    }
}
