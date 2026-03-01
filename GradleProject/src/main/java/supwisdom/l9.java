package supwisdom;

import android.os.Build;
import android.os.CancellationSignal;

/* JADX INFO: compiled from: CancellationSignal.java */
/* JADX INFO: loaded from: classes.dex */
public final class l9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8264a;
    public a b;
    public Object c;

    /* JADX INFO: compiled from: CancellationSignal.java */
    public interface a {
        void onCancel();
    }

    public void a() {
        synchronized (this) {
            if (this.f8264a) {
                return;
            }
            this.f8264a = true;
            a aVar = this.b;
            Object obj = this.c;
            if (aVar != null) {
                try {
                    aVar.onCancel();
                } catch (Throwable th) {
                    synchronized (this) {
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public Object b() {
        Object obj;
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.c == null) {
                CancellationSignal cancellationSignal = new CancellationSignal();
                this.c = cancellationSignal;
                if (this.f8264a) {
                    cancellationSignal.cancel();
                }
            }
            obj = this.c;
        }
        return obj;
    }

    public boolean c() {
        boolean z;
        synchronized (this) {
            z = this.f8264a;
        }
        return z;
    }
}
