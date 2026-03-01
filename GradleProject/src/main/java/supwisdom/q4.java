package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class q4<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f8872e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8873a;
    public int[] b;
    public Object[] c;
    public int d;

    public q4() {
        this(10);
    }

    public E a(int i) {
        return b(i, null);
    }

    public E b(int i, E e2) {
        int iA = l4.a(this.b, this.d, i);
        if (iA >= 0) {
            Object[] objArr = this.c;
            if (objArr[iA] != f8872e) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public void c(int i, E e2) {
        int iA = l4.a(this.b, this.d, i);
        if (iA >= 0) {
            this.c[iA] = e2;
            return;
        }
        int i2 = ~iA;
        if (i2 < this.d) {
            Object[] objArr = this.c;
            if (objArr[i2] == f8872e) {
                this.b[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f8873a && this.d >= this.b.length) {
            b();
            i2 = ~l4.a(this.b, this.d, i);
        }
        int i3 = this.d;
        if (i3 >= this.b.length) {
            int iB = l4.b(i3 + 1);
            int[] iArr = new int[iB];
            Object[] objArr2 = new Object[iB];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = iArr;
            this.c = objArr2;
        }
        int i4 = this.d;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.b;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, i2, objArr4, i5, this.d - i2);
        }
        this.b[i2] = i;
        this.c[i2] = e2;
        this.d++;
    }

    public void d(int i) {
        int iA = l4.a(this.b, this.d, i);
        if (iA >= 0) {
            Object[] objArr = this.c;
            Object obj = objArr[iA];
            Object obj2 = f8872e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f8873a = true;
            }
        }
    }

    public E e(int i) {
        if (this.f8873a) {
            b();
        }
        return (E) this.c[i];
    }

    public String toString() {
        if (c() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.d * 28);
        sb.append(Operators.BLOCK_START);
        for (int i = 0; i < this.d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(c(i));
            sb.append('=');
            E e2 = e(i);
            if (e2 != this) {
                sb.append(e2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public q4(int i) {
        this.f8873a = false;
        if (i == 0) {
            this.b = l4.f8242a;
            this.c = l4.c;
        } else {
            int iB = l4.b(i);
            this.b = new int[iB];
            this.c = new Object[iB];
        }
    }

    public int a(E e2) {
        if (this.f8873a) {
            b();
        }
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] == e2) {
                return i;
            }
        }
        return -1;
    }

    public q4<E> clone() {
        try {
            q4<E> q4Var = (q4) super.clone();
            q4Var.b = (int[]) this.b.clone();
            q4Var.c = (Object[]) this.c.clone();
            return q4Var;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void b() {
        int i = this.d;
        int[] iArr = this.b;
        Object[] objArr = this.c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f8872e) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f8873a = false;
        this.d = i2;
    }

    public void a() {
        int i = this.d;
        Object[] objArr = this.c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.d = 0;
        this.f8873a = false;
    }

    public void a(int i, E e2) {
        int i2 = this.d;
        if (i2 != 0 && i <= this.b[i2 - 1]) {
            c(i, e2);
            return;
        }
        if (this.f8873a && this.d >= this.b.length) {
            b();
        }
        int i3 = this.d;
        if (i3 >= this.b.length) {
            int iB = l4.b(i3 + 1);
            int[] iArr = new int[iB];
            Object[] objArr = new Object[iB];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = iArr;
            this.c = objArr;
        }
        this.b[i3] = i;
        this.c[i3] = e2;
        this.d = i3 + 1;
    }

    public int b(int i) {
        if (this.f8873a) {
            b();
        }
        return l4.a(this.b, this.d, i);
    }

    public int c() {
        if (this.f8873a) {
            b();
        }
        return this.d;
    }

    public int c(int i) {
        if (this.f8873a) {
            b();
        }
        return this.b[i];
    }
}
