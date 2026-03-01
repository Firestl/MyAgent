package supwisdom;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.util.atomic.LinkedQueueNode;

/* JADX INFO: compiled from: BaseLinkedAtomicQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class iy1<E> extends AbstractQueue<E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference<LinkedQueueNode<E>> f8000a = new AtomicReference<>();
    public final AtomicReference<LinkedQueueNode<E>> b = new AtomicReference<>();

    public final LinkedQueueNode<E> a() {
        return this.b.get();
    }

    public final LinkedQueueNode<E> b() {
        return this.f8000a.get();
    }

    public final LinkedQueueNode<E> g() {
        return this.b.get();
    }

    public final LinkedQueueNode<E> h() {
        return this.f8000a.get();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return g() == h();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        LinkedQueueNode<E> linkedQueueNodeLvNext;
        LinkedQueueNode<E> linkedQueueNodeG = g();
        LinkedQueueNode<E> linkedQueueNodeH = h();
        int i = 0;
        while (linkedQueueNodeG != linkedQueueNodeH && i < Integer.MAX_VALUE) {
            do {
                linkedQueueNodeLvNext = linkedQueueNodeG.lvNext();
            } while (linkedQueueNodeLvNext == null);
            i++;
            linkedQueueNodeG = linkedQueueNodeLvNext;
        }
        return i;
    }

    public final void a(LinkedQueueNode<E> linkedQueueNode) {
        this.b.lazySet(linkedQueueNode);
    }

    public final void b(LinkedQueueNode<E> linkedQueueNode) {
        this.f8000a.lazySet(linkedQueueNode);
    }
}
