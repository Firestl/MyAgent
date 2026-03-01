package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public class ku0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ pt0 f8207a;
    public final /* synthetic */ lu0 b;

    public ku0(lu0 lu0Var, pt0 pt0Var) {
        this.b = lu0Var;
        this.f8207a = pt0Var;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.b.c) {
            fu0<TResult> fu0Var = this.b.b;
            if (fu0Var != 0) {
                fu0Var.a((pt0<TResult>) this.f8207a);
            }
        }
    }
}
