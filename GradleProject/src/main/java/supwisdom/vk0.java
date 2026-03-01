package supwisdom;

import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.2.0 */
/* JADX INFO: loaded from: classes.dex */
public final class vk0<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9516a = new Object();

    @GuardedBy("mLock")
    public Queue<wk0<TResult>> b;

    @GuardedBy("mLock")
    public boolean c;

    public final void a(wk0<TResult> wk0Var) {
        synchronized (this.f9516a) {
            if (this.b == null) {
                this.b = new ArrayDeque();
            }
            this.b.add(wk0Var);
        }
    }

    public final void a(qk0<TResult> qk0Var) {
        wk0<TResult> wk0VarPoll;
        synchronized (this.f9516a) {
            if (this.b != null && !this.c) {
                this.c = true;
                while (true) {
                    synchronized (this.f9516a) {
                        wk0VarPoll = this.b.poll();
                        if (wk0VarPoll == null) {
                            this.c = false;
                            return;
                        }
                    }
                    wk0VarPoll.a(qk0Var);
                }
            }
        }
    }
}
