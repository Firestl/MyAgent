package supwisdom;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: compiled from: ProtobufArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class gr0<E> extends kp0<E> implements RandomAccess {
    public static final gr0<Object> d;
    public E[] b;
    public int c;

    static {
        gr0<Object> gr0Var = new gr0<>(new Object[0], 0);
        d = gr0Var;
        gr0Var.c();
    }

    public gr0(E[] eArr, int i) {
        this.b = eArr;
        this.c = i;
    }

    public static <E> gr0<E> b() {
        return (gr0<E>) d;
    }

    public static <E> E[] e(int i) {
        return (E[]) new Object[i];
    }

    @Override // supwisdom.kp0, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e2) {
        a();
        int i = this.c;
        E[] eArr = this.b;
        if (i == eArr.length) {
            this.b = (E[]) Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        eArr2[i2] = e2;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
    }

    public final String d(int i) {
        return "Index:" + i + ", Size:" + this.c;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        c(i);
        return this.b[i];
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        a();
        c(i);
        E[] eArr = this.b;
        E e2 = eArr[i];
        if (i < this.c - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (r2 - i) - 1);
        }
        this.c--;
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e2) {
        a();
        c(i);
        E[] eArr = this.b;
        E e3 = eArr[i];
        eArr[i] = e2;
        ((AbstractList) this).modCount++;
        return e3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.c;
    }

    @Override // supwisdom.gq0.i
    public gr0<E> a(int i) {
        if (i >= this.c) {
            return new gr0<>(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e2) {
        int i2;
        a();
        if (i >= 0 && i <= (i2 = this.c)) {
            E[] eArr = this.b;
            if (i2 < eArr.length) {
                System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
            } else {
                E[] eArr2 = (E[]) e(((i2 * 3) / 2) + 1);
                System.arraycopy(this.b, 0, eArr2, 0, i);
                System.arraycopy(this.b, i, eArr2, i + 1, this.c - i);
                this.b = eArr2;
            }
            this.b[i] = e2;
            this.c++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(d(i));
    }
}
