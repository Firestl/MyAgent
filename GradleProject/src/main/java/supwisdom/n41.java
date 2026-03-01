package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class n41 extends r61 {
    public final l41[] b;
    public int c;

    static {
        new n41(0);
    }

    public n41(int i) {
        super(i != 0);
        this.b = new l41[i];
        this.c = 0;
    }

    public l41 a(l41 l41Var) {
        int length = this.b.length;
        for (int i = 0; i < length; i++) {
            l41 l41Var2 = this.b[i];
            if (l41Var2 != null && l41Var.c(l41Var2)) {
                return l41Var2;
            }
        }
        return null;
    }

    public void b(l41 l41Var) {
        int i;
        l41 l41Var2;
        f();
        if (l41Var == null) {
            throw new NullPointerException("spec == null");
        }
        this.c = -1;
        try {
            int iF = l41Var.f();
            l41[] l41VarArr = this.b;
            l41VarArr[iF] = l41Var;
            if (iF > 0 && (l41Var2 = l41VarArr[iF - 1]) != null && l41Var2.c() == 2) {
                this.b[i] = null;
            }
            if (l41Var.c() == 2) {
                this.b[iF + 1] = null;
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("spec.getReg() out of range");
        }
    }

    public void c(l41 l41Var) {
        try {
            this.b[l41Var.f()] = null;
            this.c = -1;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus reg");
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof n41)) {
            return false;
        }
        n41 n41Var = (n41) obj;
        l41[] l41VarArr = n41Var.b;
        int length = this.b.length;
        if (length != l41VarArr.length || size() != n41Var.size()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            l41 l41Var = this.b[i];
            Object obj2 = l41VarArr[i];
            if (l41Var != obj2 && (l41Var == null || !l41Var.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    public int h() {
        return this.b.length;
    }

    public int hashCode() {
        int length = this.b.length;
        int iHashCode = 0;
        for (int i = 0; i < length; i++) {
            l41 l41Var = this.b[i];
            iHashCode = (iHashCode * 31) + (l41Var == null ? 0 : l41Var.hashCode());
        }
        return iHashCode;
    }

    public int size() {
        int i = this.c;
        if (i >= 0) {
            return i;
        }
        int length = this.b.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.b[i3] != null) {
                i2++;
            }
        }
        this.c = i2;
        return i2;
    }

    public String toString() {
        int length = this.b.length;
        StringBuilder sb = new StringBuilder(length * 25);
        sb.append(Operators.BLOCK_START);
        boolean z = false;
        for (int i = 0; i < length; i++) {
            l41 l41Var = this.b[i];
            if (l41Var != null) {
                if (z) {
                    sb.append(", ");
                } else {
                    z = true;
                }
                sb.append(l41Var);
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public l41 a(int i) {
        try {
            return this.b[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus reg");
        }
    }

    public void a(n41 n41Var) {
        int iH = n41Var.h();
        for (int i = 0; i < iH; i++) {
            l41 l41VarA = n41Var.a(i);
            if (l41VarA != null) {
                b(l41VarA);
            }
        }
    }

    public n41 b(int i) {
        int length = this.b.length;
        n41 n41Var = new n41(length + i);
        for (int i2 = 0; i2 < length; i2++) {
            l41 l41Var = this.b[i2];
            if (l41Var != null) {
                n41Var.b(l41Var.a(i));
            }
        }
        n41Var.c = this.c;
        if (c()) {
            n41Var.e();
        }
        return n41Var;
    }
}
