package supwisdom;

/* JADX INFO: compiled from: SpscArrayQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uy1<E> extends zy1<E> {
    public uy1(int i) {
        super(i);
    }

    public final long a() {
        return cz1.f7274a.getLongVolatile(this, wy1.h);
    }

    public final long b() {
        return cz1.f7274a.getLongVolatile(this, az1.g);
    }

    public final void c(long j) {
        cz1.f7274a.putOrderedLong(this, wy1.h, j);
    }

    public final void d(long j) {
        cz1.f7274a.putOrderedLong(this, az1.g, j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return b() == a();
    }

    @Override // java.util.Queue
    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException("null elements not allowed");
        }
        E[] eArr = this.b;
        long j = this.producerIndex;
        long jA = a(j);
        if (a(eArr, jA) != null) {
            return false;
        }
        a(eArr, jA, e2);
        d(j + 1);
        return true;
    }

    @Override // java.util.Queue
    public E peek() {
        return b(a(this.consumerIndex));
    }

    @Override // java.util.Queue, supwisdom.sy1
    public E poll() {
        long j = this.consumerIndex;
        long jA = a(j);
        E[] eArr = this.b;
        E eA = a(eArr, jA);
        if (eA == null) {
            return null;
        }
        a(eArr, jA, null);
        c(j + 1);
        return eA;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        long jA = a();
        while (true) {
            long jB = b();
            long jA2 = a();
            if (jA == jA2) {
                return (int) (jB - jA2);
            }
            jA = jA2;
        }
    }
}
