package supwisdom;

import com.sangfor.dex.util.ExceptionWithContext;
import com.sangfor.dx.dex.file.ItemType;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class p21 extends f21 implements Comparable<p21> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8761a;
    public int b;
    public t21 c;
    public int d;

    public p21(int i, int i2) {
        t21.c(i);
        if (i2 < -1) {
            throw new IllegalArgumentException("writeSize < -1");
        }
        this.f8761a = i;
        this.b = i2;
        this.c = null;
        this.d = -1;
    }

    public static int c(p21 p21Var) {
        if (p21Var == null) {
            return 0;
        }
        return p21Var.d();
    }

    public final int a(t21 t21Var, int i) {
        if (t21Var == null) {
            throw new NullPointerException("addedTo == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        if (this.c != null) {
            throw new RuntimeException("already written");
        }
        int i2 = this.f8761a - 1;
        int i3 = (i + i2) & (~i2);
        this.c = t21Var;
        this.d = i3;
        b(t21Var, i3);
        return i3;
    }

    public int b(p21 p21Var) {
        throw new UnsupportedOperationException("unsupported");
    }

    public abstract void b(t11 t11Var, h61 h61Var);

    public void b(t21 t21Var, int i) {
    }

    public final int d() {
        int i = this.d;
        if (i >= 0) {
            return this.c.a(i);
        }
        throw new RuntimeException("offset not yet known");
    }

    public final int e() {
        return this.f8761a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        p21 p21Var = (p21) obj;
        return a() == p21Var.a() && b(p21Var) == 0;
    }

    public final String f() {
        return Operators.ARRAY_START + Integer.toHexString(d()) + Operators.ARRAY_END;
    }

    public abstract String g();

    @Override // supwisdom.f21
    public final int c() {
        int i = this.b;
        if (i >= 0) {
            return i;
        }
        throw new UnsupportedOperationException("writeSize is unknown");
    }

    public final void a(int i) {
        if (i >= 0) {
            if (this.b < 0) {
                this.b = i;
                return;
            }
            throw new UnsupportedOperationException("writeSize already set");
        }
        throw new IllegalArgumentException("writeSize < 0");
    }

    @Override // supwisdom.f21
    public final void a(t11 t11Var, h61 h61Var) {
        h61Var.d(this.f8761a);
        try {
            if (this.b >= 0) {
                h61Var.b(d());
                b(t11Var, h61Var);
                return;
            }
            throw new UnsupportedOperationException("writeSize is unknown");
        } catch (RuntimeException e2) {
            throw ExceptionWithContext.withContext(e2, "...while writing " + this);
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(p21 p21Var) {
        if (this == p21Var) {
            return 0;
        }
        ItemType itemTypeA = a();
        ItemType itemTypeA2 = p21Var.a();
        if (itemTypeA != itemTypeA2) {
            return itemTypeA.compareTo(itemTypeA2);
        }
        return b(p21Var);
    }
}
