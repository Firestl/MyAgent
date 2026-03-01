package supwisdom;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: SpscAtomicArrayQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jy1<E> extends hy1<E> {
    public static final Integer g = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    public final AtomicLong c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicLong f8099e;
    public final int f;

    public jy1(int i) {
        super(i);
        this.c = new AtomicLong();
        this.f8099e = new AtomicLong();
        this.f = Math.min(i / 4, g.intValue());
    }

    public final long a() {
        return this.f8099e.get();
    }

    public final void b(long j) {
        this.f8099e.lazySet(j);
    }

    public final void c(long j) {
        this.c.lazySet(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return b() == a();
    }

    @Override // java.util.Queue
    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray<E> atomicReferenceArray = this.f7888a;
        int i = this.b;
        long j = this.c.get();
        int iA = a(j, i);
        if (j >= this.d) {
            long j2 = ((long) this.f) + j;
            if (a(atomicReferenceArray, a(j2, i)) == null) {
                this.d = j2;
            } else if (a(atomicReferenceArray, iA) != null) {
                return false;
            }
        }
        a(atomicReferenceArray, iA, e2);
        c(j + 1);
        return true;
    }

    @Override // java.util.Queue
    public E peek() {
        return c(a(this.f8099e.get()));
    }

    @Override // java.util.Queue
    public E poll() {
        long j = this.f8099e.get();
        int iA = a(j);
        AtomicReferenceArray<E> atomicReferenceArray = this.f7888a;
        E eA = a(atomicReferenceArray, iA);
        if (eA == null) {
            return null;
        }
        a(atomicReferenceArray, iA, null);
        b(j + 1);
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

    public final long b() {
        return this.c.get();
    }
}
