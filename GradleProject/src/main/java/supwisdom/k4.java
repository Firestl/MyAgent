package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: classes.dex */
public final class k4<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f8120e = new int[0];
    public static final Object[] f = new Object[0];
    public static Object[] g;
    public static int h;
    public static Object[] i;
    public static int j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f8121a;
    public Object[] b;
    public int c;
    public o4<E, E> d;

    /* JADX INFO: compiled from: ArraySet.java */
    public class a extends o4<E, E> {
        public a() {
        }

        @Override // supwisdom.o4
        public Object a(int i, int i2) {
            return k4.this.b[i];
        }

        @Override // supwisdom.o4
        public int b(Object obj) {
            return k4.this.indexOf(obj);
        }

        @Override // supwisdom.o4
        public int c() {
            return k4.this.c;
        }

        @Override // supwisdom.o4
        public int a(Object obj) {
            return k4.this.indexOf(obj);
        }

        @Override // supwisdom.o4
        public Map<E, E> b() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // supwisdom.o4
        public void a(E e2, E e3) {
            k4.this.add(e2);
        }

        @Override // supwisdom.o4
        public E a(int i, E e2) {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // supwisdom.o4
        public void a(int i) {
            k4.this.e(i);
        }

        @Override // supwisdom.o4
        public void a() {
            k4.this.clear();
        }
    }

    public k4() {
        this(0);
    }

    public final int a(Object obj, int i2) {
        int i3 = this.c;
        if (i3 == 0) {
            return -1;
        }
        int iA = l4.a(this.f8121a, i3, i2);
        if (iA < 0 || obj.equals(this.b[iA])) {
            return iA;
        }
        int i4 = iA + 1;
        while (i4 < i3 && this.f8121a[i4] == i2) {
            if (obj.equals(this.b[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iA - 1; i5 >= 0 && this.f8121a[i5] == i2; i5--) {
            if (obj.equals(this.b[i5])) {
                return i5;
            }
        }
        return ~i4;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i2;
        int iA;
        if (e2 == null) {
            iA = b();
            i2 = 0;
        } else {
            int iHashCode = e2.hashCode();
            i2 = iHashCode;
            iA = a(e2, iHashCode);
        }
        if (iA >= 0) {
            return false;
        }
        int i3 = ~iA;
        int i4 = this.c;
        if (i4 >= this.f8121a.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f8121a;
            Object[] objArr = this.b;
            c(i5);
            int[] iArr2 = this.f8121a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.b, 0, objArr.length);
            }
            a(iArr, objArr, this.c);
        }
        int i6 = this.c;
        if (i3 < i6) {
            int[] iArr3 = this.f8121a;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.b;
            System.arraycopy(objArr2, i3, objArr2, i7, this.c - i3);
        }
        this.f8121a[i3] = i2;
        this.b[i3] = e2;
        this.c++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        d(this.c + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public final int b() {
        int i2 = this.c;
        if (i2 == 0) {
            return -1;
        }
        int iA = l4.a(this.f8121a, i2, 0);
        if (iA < 0 || this.b[iA] == null) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.f8121a[i3] == 0) {
            if (this.b[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.f8121a[i4] == 0; i4--) {
            if (this.b[i4] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public final void c(int i2) {
        if (i2 == 8) {
            synchronized (k4.class) {
                if (i != null) {
                    Object[] objArr = i;
                    this.b = objArr;
                    i = (Object[]) objArr[0];
                    this.f8121a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    j--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (k4.class) {
                if (g != null) {
                    Object[] objArr2 = g;
                    this.b = objArr2;
                    g = (Object[]) objArr2[0];
                    this.f8121a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    h--;
                    return;
                }
            }
        }
        this.f8121a = new int[i2];
        this.b = new Object[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.c;
        if (i2 != 0) {
            a(this.f8121a, this.b, i2);
            this.f8121a = f8120e;
            this.b = f;
            this.c = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public void d(int i2) {
        int[] iArr = this.f8121a;
        if (iArr.length < i2) {
            Object[] objArr = this.b;
            c(i2);
            int i3 = this.c;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.f8121a, 0, i3);
                System.arraycopy(objArr, 0, this.b, 0, this.c);
            }
            a(iArr, objArr, this.c);
        }
    }

    public E e(int i2) {
        Object[] objArr = this.b;
        E e2 = (E) objArr[i2];
        int i3 = this.c;
        if (i3 <= 1) {
            a(this.f8121a, objArr, i3);
            this.f8121a = f8120e;
            this.b = f;
            this.c = 0;
        } else {
            int[] iArr = this.f8121a;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                int i4 = this.c - 1;
                this.c = i4;
                if (i2 < i4) {
                    int[] iArr2 = this.f8121a;
                    int i5 = i2 + 1;
                    System.arraycopy(iArr2, i5, iArr2, i2, i4 - i2);
                    Object[] objArr2 = this.b;
                    System.arraycopy(objArr2, i5, objArr2, i2, this.c - i2);
                }
                this.b[this.c] = null;
            } else {
                int i6 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr3 = this.f8121a;
                Object[] objArr3 = this.b;
                c(i6);
                this.c--;
                if (i2 > 0) {
                    System.arraycopy(iArr3, 0, this.f8121a, 0, i2);
                    System.arraycopy(objArr3, 0, this.b, 0, i2);
                }
                int i7 = this.c;
                if (i2 < i7) {
                    int i8 = i2 + 1;
                    System.arraycopy(iArr3, i8, this.f8121a, i2, i7 - i2);
                    System.arraycopy(objArr3, i8, this.b, i2, this.c - i2);
                }
            }
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.c; i2++) {
                try {
                    if (!set.contains(f(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public E f(int i2) {
        return (E) this.b[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f8121a;
        int i2 = this.c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public int indexOf(Object obj) {
        return obj == null ? b() : a(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.c <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return a().e().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        e(iIndexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int i2 = this.c - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.b[i2])) {
                e(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.c;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i2 = this.c;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.b, 0, objArr, 0, i2);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.c * 14);
        sb.append(Operators.BLOCK_START);
        for (int i2 = 0; i2 < this.c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            E eF = f(i2);
            if (eF != this) {
                sb.append(eF);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public k4(int i2) {
        if (i2 == 0) {
            this.f8121a = f8120e;
            this.b = f;
        } else {
            c(i2);
        }
        this.c = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.c) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.c));
        }
        System.arraycopy(this.b, 0, tArr, 0, this.c);
        int length = tArr.length;
        int i2 = this.c;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    public static void a(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (k4.class) {
                if (j < 10) {
                    objArr[0] = i;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    i = objArr;
                    j++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (k4.class) {
                if (h < 10) {
                    objArr[0] = g;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    g = objArr;
                    h++;
                }
            }
        }
    }

    public final o4<E, E> a() {
        if (this.d == null) {
            this.d = new a();
        }
        return this.d;
    }
}
