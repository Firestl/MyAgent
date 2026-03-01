package java9.util.concurrent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RecursiveTask<V> extends ForkJoinTask<V> {
    public static final long serialVersionUID = 5232453952276485270L;
    public V result;

    public abstract V compute();

    @Override // java9.util.concurrent.ForkJoinTask
    public final boolean exec() {
        this.result = compute();
        return true;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public final V getRawResult() {
        return this.result;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public final void setRawResult(V v) {
        this.result = v;
    }
}
