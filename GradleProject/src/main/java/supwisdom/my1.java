package supwisdom;

import rx.internal.util.atomic.LinkedQueueNode;

/* JADX INFO: compiled from: BaseLinkedQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class my1<E> extends oy1<E> {
    public static final long b = cz1.a(my1.class, "consumerNode");
    public LinkedQueueNode<E> consumerNode;

    public final void b(LinkedQueueNode<E> linkedQueueNode) {
        this.consumerNode = linkedQueueNode;
    }

    public final LinkedQueueNode<E> b() {
        return (LinkedQueueNode) cz1.f7274a.getObjectVolatile(this, b);
    }
}
