package supwisdom;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class lu0<TResult> implements eu0<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Executor f8316a;
    public fu0<TResult> b;
    public final Object c = new Object();

    public lu0(Executor executor, fu0<TResult> fu0Var) {
        this.b = fu0Var;
        this.f8316a = executor;
    }

    @Override // supwisdom.eu0
    public final void a(pt0<TResult> pt0Var) {
        this.f8316a.execute(new ku0(this, pt0Var));
    }
}
