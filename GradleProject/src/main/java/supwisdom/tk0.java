package supwisdom;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.2.0 */
/* JADX INFO: loaded from: classes.dex */
public final class tk0<TResult> implements wk0<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f9299a;
    public final Object b = new Object();

    @GuardedBy("mLock")
    @Nullable
    public pk0<TResult> c;

    public tk0(Executor executor, pk0<TResult> pk0Var) {
        this.f9299a = executor;
        this.c = pk0Var;
    }

    @Override // supwisdom.wk0
    public final void a(qk0<TResult> qk0Var) {
        synchronized (this.b) {
            if (this.c == null) {
                return;
            }
            this.f9299a.execute(new uk0(this, qk0Var));
        }
    }
}
