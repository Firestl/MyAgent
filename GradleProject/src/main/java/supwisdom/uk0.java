package supwisdom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.2.0 */
/* JADX INFO: loaded from: classes.dex */
public final class uk0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ qk0 f9418a;
    public final /* synthetic */ tk0 b;

    public uk0(tk0 tk0Var, qk0 qk0Var) {
        this.b = tk0Var;
        this.f9418a = qk0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                this.b.c.a(this.f9418a);
            }
        }
    }
}
