package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class o41 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8625a;
    public final b61 b;
    public final d61 c;
    public final d61 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8626e;
    public final boolean f;
    public final String g;

    public o41(int i, b61 b61Var, d61 d61Var, d61 d61Var2, int i2, boolean z, String str) {
        if (b61Var == null) {
            throw new NullPointerException("result == null");
        }
        if (d61Var == null) {
            throw new NullPointerException("sources == null");
        }
        if (d61Var2 == null) {
            throw new NullPointerException("exceptions == null");
        }
        if (i2 < 1 || i2 > 6) {
            throw new IllegalArgumentException("invalid branchingness: " + i2);
        }
        if (d61Var2.size() != 0 && i2 != 6) {
            throw new IllegalArgumentException("exceptions / branchingness mismatch");
        }
        this.f8625a = i;
        this.b = b61Var;
        this.c = d61Var;
        this.d = d61Var2;
        this.f8626e = i2;
        this.f = z;
        this.g = str;
    }

    public final boolean a() {
        return this.d.size() != 0;
    }

    public int b() {
        return this.f8626e;
    }

    public String c() {
        String str = this.g;
        return str != null ? str : toString();
    }

    public int d() {
        return this.f8625a;
    }

    public boolean e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o41)) {
            return false;
        }
        o41 o41Var = (o41) obj;
        return this.f8625a == o41Var.f8625a && this.f8626e == o41Var.f8626e && this.b == o41Var.b && this.c.equals(o41Var.c) && this.d.equals(o41Var.d);
    }

    public boolean f() {
        int i = this.f8625a;
        if (i == 14 || i == 16) {
            return true;
        }
        switch (i) {
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return false;
        }
    }

    public int hashCode() {
        return (((((((this.f8625a * 31) + this.f8626e) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("Rop{");
        sb.append(k41.a(this.f8625a));
        if (this.b != b61.o) {
            sb.append(Operators.SPACE_STR);
            sb.append(this.b);
        } else {
            sb.append(" .");
        }
        sb.append(" <-");
        int size = this.c.size();
        if (size == 0) {
            sb.append(" .");
        } else {
            for (int i = 0; i < size; i++) {
                sb.append(' ');
                sb.append(this.c.getType(i));
            }
        }
        if (this.f) {
            sb.append(" call");
        }
        int size2 = this.d.size();
        if (size2 != 0) {
            sb.append(" throws");
            for (int i2 = 0; i2 < size2; i2++) {
                sb.append(' ');
                if (this.d.getType(i2) == b61.A) {
                    sb.append("<any>");
                } else {
                    sb.append(this.d.getType(i2));
                }
            }
        } else {
            int i3 = this.f8626e;
            if (i3 == 1) {
                sb.append(" flows");
            } else if (i3 == 2) {
                sb.append(" returns");
            } else if (i3 == 3) {
                sb.append(" gotos");
            } else if (i3 == 4) {
                sb.append(" ifs");
            } else if (i3 != 5) {
                sb.append(Operators.SPACE_STR + m61.c(this.f8626e));
            } else {
                sb.append(" switches");
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public o41(int i, b61 b61Var, d61 d61Var, int i2, String str) {
        this(i, b61Var, d61Var, a61.c, i2, false, str);
    }

    public o41(int i, b61 b61Var, d61 d61Var, String str) {
        this(i, b61Var, d61Var, a61.c, 1, false, str);
    }

    public o41(int i, b61 b61Var, d61 d61Var, d61 d61Var2, String str) {
        this(i, b61Var, d61Var, d61Var2, 6, false, str);
    }

    public o41(int i, d61 d61Var, d61 d61Var2) {
        this(i, b61.o, d61Var, d61Var2, 6, true, null);
    }
}
