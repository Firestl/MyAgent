package supwisdom;

import java.util.Iterator;

/* JADX INFO: compiled from: ConcurrentCircularArrayQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class qy1<E> extends ry1<E> {
    public static final int c = Integer.getInteger("sparse.shift", 0).intValue();
    public static final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f8973e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f8974a;
    public final E[] b;

    static {
        int iArrayIndexScale = cz1.f7274a.arrayIndexScale(Object[].class);
        if (4 == iArrayIndexScale) {
            f8973e = c + 2;
        } else {
            if (8 != iArrayIndexScale) {
                throw new IllegalStateException("Unknown pointer size");
            }
            f8973e = c + 3;
        }
        d = cz1.f7274a.arrayBaseOffset(Object[].class) + (32 << (f8973e - c));
    }

    public qy1(int i) {
        int iA = ty1.a(i);
        this.f8974a = iA - 1;
        this.b = (E[]) new Object[(iA << c) + 64];
    }

    public final long a(long j) {
        return a(j, this.f8974a);
    }

    public final E b(long j) {
        return a(this.b, j);
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final long a(long j, long j2) {
        return d + ((j & j2) << f8973e);
    }

    public final void a(E[] eArr, long j, E e2) {
        cz1.f7274a.putOrderedObject(eArr, j, e2);
    }

    public final E a(E[] eArr, long j) {
        return (E) cz1.f7274a.getObjectVolatile(eArr, j);
    }
}
