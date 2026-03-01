package java9.util.concurrent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RecursiveAction extends ForkJoinTask<Void> {
    public static final long serialVersionUID = 5232453952276485070L;

    public abstract void compute();

    @Override // java9.util.concurrent.ForkJoinTask
    public final boolean exec() {
        compute();
        return true;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public final Void getRawResult() {
        return null;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public final void setRawResult(Void r1) {
    }
}
