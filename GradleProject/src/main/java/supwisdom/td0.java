package supwisdom;

import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class td0 implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f9279a;

    public td0(Handler handler) {
        this.f9279a = handler;
    }

    public static Executor a(Handler handler) {
        return new td0(handler);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f9279a.post(runnable);
    }
}
