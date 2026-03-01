package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class m4<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f8353e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8354a;
    public long[] b;
    public Object[] c;
    public int d;

    public m4() {
        this(10);
    }

    public E a(long j) {
        return b(j, null);
    }

    public E b(long j, E e2) {
        int iA = l4.a(this.b, this.d, j);
        if (iA >= 0) {
            Object[] objArr = this.c;
            if (objArr[iA] != f8353e) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public void c(long j) {
        int iA = l4.a(this.b, this.d, j);
        if (iA >= 0) {
            Object[] objArr = this.c;
            Object obj = objArr[iA];
            Object obj2 = f8353e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f8354a = true;
            }
        }
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
            sb.append(a(i));
            sb.append('=');
            E eC = c(i);
            if (eC != this) {
                sb.append(eC);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public m4(int i) {
        this.f8354a = false;
        if (i == 0) {
            this.b = l4.b;
            this.c = l4.c;
        } else {
            int iC = l4.c(i);
            this.b = new long[iC];
            this.c = new Object[iC];
        }
    }

    public long a(int i) {
        if (this.f8354a) {
            b();
        }
        return this.b[i];
    }

    public m4<E> clone() {
        try {
            m4<E> m4Var = (m4) super.clone();
            m4Var.b = (long[]) this.b.clone();
            m4Var.c = (Object[]) this.c.clone();
            return m4Var;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public void b(int i) {
        Object[] objArr = this.c;
        Object obj = objArr[i];
        Object obj2 = f8353e;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f8354a = true;
        }
    }

    public void a() {
        int i = this.d;
        Object[] objArr = this.c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.d = 0;
        this.f8354a = false;
    }

    public void c(long j, E e2) {
        int iA = l4.a(this.b, this.d, j);
        if (iA >= 0) {
            this.c[iA] = e2;
            return;
        }
        int i = ~iA;
        if (i < this.d) {
            Object[] objArr = this.c;
            if (objArr[i] == f8353e) {
                this.b[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f8354a && this.d >= this.b.length) {
            b();
            i = ~l4.a(this.b, this.d, j);
        }
        int i2 = this.d;
        if (i2 >= this.b.length) {
            int iC = l4.c(i2 + 1);
            long[] jArr = new long[iC];
            Object[] objArr2 = new Object[iC];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = jArr;
            this.c = objArr2;
        }
        int i3 = this.d;
        if (i3 - i != 0) {
            long[] jArr3 = this.b;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, i, objArr4, i4, this.d - i);
        }
        this.b[i] = j;
        this.c[i] = e2;
        this.d++;
    }

    public final void b() {
        int i = this.d;
        long[] jArr = this.b;
        Object[] objArr = this.c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f8353e) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f8354a = false;
        this.d = i2;
    }

    public void a(long j, E e2) {
        int i = this.d;
        if (i != 0 && j <= this.b[i - 1]) {
            c(j, e2);
            return;
        }
        if (this.f8354a && this.d >= this.b.length) {
            b();
        }
        int i2 = this.d;
        if (i2 >= this.b.length) {
            int iC = l4.c(i2 + 1);
            long[] jArr = new long[iC];
            Object[] objArr = new Object[iC];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = jArr;
            this.c = objArr;
        }
        this.b[i2] = j;
        this.c[i2] = e2;
        this.d = i2 + 1;
    }

    public int b(long j) {
        if (this.f8354a) {
            b();
        }
        return l4.a(this.b, this.d, j);
    }

    public int c() {
        if (this.f8354a) {
            b();
        }
        return this.d;
    }

    public E c(int i) {
        if (this.f8354a) {
            b();
        }
        return (E) this.c[i];
    }
}
