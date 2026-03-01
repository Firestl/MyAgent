package supwisdom;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: AtomicReferenceArrayQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class hy1<E> extends AbstractQueue<E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReferenceArray<E> f7888a;
    public final int b;

    public hy1(int i) {
        int iA = ty1.a(i);
        this.b = iA - 1;
        this.f7888a = new AtomicReferenceArray<>(iA);
    }

    public final int a(long j) {
        return this.b & ((int) j);
    }

    public final int a(long j, int i) {
        return ((int) j) & i;
    }

    public final E c(int i) {
        return a(this.f7888a, i);
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

    public final E a(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    public final void a(AtomicReferenceArray<E> atomicReferenceArray, int i, E e2) {
        atomicReferenceArray.lazySet(i, e2);
    }
}
