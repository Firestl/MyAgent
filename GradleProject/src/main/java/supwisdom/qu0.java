package supwisdom;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class qu0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ hu0 f8954a;
    public final /* synthetic */ Callable b;

    public qu0(hu0 hu0Var, Callable callable) {
        this.f8954a = hu0Var;
        this.b = callable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f8954a.a(this.b.call());
        } catch (Exception e2) {
            this.f8954a.a(e2);
        }
    }
}
