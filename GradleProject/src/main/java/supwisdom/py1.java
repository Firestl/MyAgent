package supwisdom;

import rx.internal.util.atomic.LinkedQueueNode;

/* JADX INFO: compiled from: BaseLinkedQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class py1<E> extends ny1<E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f8851a = cz1.a(py1.class, "producerNode");
    public LinkedQueueNode<E> producerNode;

    public final void a(LinkedQueueNode<E> linkedQueueNode) {
        this.producerNode = linkedQueueNode;
    }

    public final LinkedQueueNode<E> a() {
        return (LinkedQueueNode) cz1.f7274a.getObjectVolatile(this, f8851a);
    }
}
