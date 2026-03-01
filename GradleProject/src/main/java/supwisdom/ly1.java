package supwisdom;

import java.util.Iterator;
import rx.internal.util.atomic.LinkedQueueNode;

/* JADX INFO: compiled from: BaseLinkedQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ly1<E> extends my1<E> {
    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return b() == a();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        LinkedQueueNode<E> linkedQueueNodeLvNext;
        LinkedQueueNode<E> linkedQueueNodeB = b();
        LinkedQueueNode<E> linkedQueueNodeA = a();
        int i = 0;
        while (linkedQueueNodeB != linkedQueueNodeA && i < Integer.MAX_VALUE) {
            do {
                linkedQueueNodeLvNext = linkedQueueNodeB.lvNext();
            } while (linkedQueueNodeLvNext == null);
            i++;
            linkedQueueNodeB = linkedQueueNodeLvNext;
        }
        return i;
    }
}
