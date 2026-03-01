package supwisdom;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public class hn0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static hn0 f7853e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f7854a = new Object();
    public final Handler b = new Handler(Looper.getMainLooper(), new a());
    public c c;
    public c d;

    /* JADX INFO: compiled from: SnackbarManager.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            hn0.this.a((c) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public interface b {
        void a(int i);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f7856a;
        public int b;
        public boolean c;

        public boolean a(b bVar) {
            return bVar != null && this.f7856a.get() == bVar;
        }
    }

    public static hn0 a() {
        if (f7853e == null) {
            f7853e = new hn0();
        }
        return f7853e;
    }

    public void b(b bVar) {
        synchronized (this.f7854a) {
            if (a(bVar) && !this.c.c) {
                this.c.c = true;
                this.b.removeCallbacksAndMessages(this.c);
            }
        }
    }

    public void c(b bVar) {
        synchronized (this.f7854a) {
            if (a(bVar) && this.c.c) {
                this.c.c = false;
                b(this.c);
            }
        }
    }

    public final boolean a(c cVar, int i) {
        b bVar = cVar.f7856a.get();
        if (bVar == null) {
            return false;
        }
        this.b.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    public final void b(c cVar) {
        int i = cVar.b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.b.removeCallbacksAndMessages(cVar);
        Handler handler = this.b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    public final boolean a(b bVar) {
        c cVar = this.c;
        return cVar != null && cVar.a(bVar);
    }

    public void a(c cVar) {
        synchronized (this.f7854a) {
            if (this.c == cVar || this.d == cVar) {
                a(cVar, 2);
            }
        }
    }
}
