package supwisdom;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.2.0 */
/* JADX INFO: loaded from: classes.dex */
public final class sk0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Executor f9184a = new a();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.2.0 */
    public static final class a implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f9185a = new bk0(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.f9185a.post(runnable);
        }
    }

    static {
        new xk0();
    }
}
