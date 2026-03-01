package supwisdom;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Choreographer;

/* JADX INFO: compiled from: AnimationFrame.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class cj {

    /* JADX INFO: compiled from: AnimationFrame.java */
    public interface a {
        void a();
    }

    public static cj c() {
        return Build.VERSION.SDK_INT >= 16 ? new b() : new c();
    }

    public abstract void a();

    public abstract void a(a aVar);

    public abstract void b();

    /* JADX INFO: compiled from: AnimationFrame.java */
    @TargetApi(16)
    public static class b extends cj implements Choreographer.FrameCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Choreographer f7216a;
        public a b;
        public boolean c;

        @TargetApi(16)
        public b() {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            this.f7216a = Choreographer.getInstance();
        }

        @Override // supwisdom.cj
        public void a() {
            Choreographer choreographer = this.f7216a;
            if (choreographer != null) {
                choreographer.removeFrameCallback(this);
            }
            this.c = false;
        }

        @Override // supwisdom.cj
        public void b() {
            a();
            this.f7216a = null;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            a aVar = this.b;
            if (aVar != null) {
                aVar.a();
            }
            Choreographer choreographer = this.f7216a;
            if (choreographer == null || !this.c) {
                return;
            }
            choreographer.postFrameCallback(this);
        }

        @Override // supwisdom.cj
        public void a(a aVar) {
            this.b = aVar;
            this.c = true;
            Choreographer choreographer = this.f7216a;
            if (choreographer != null) {
                choreographer.postFrameCallback(this);
            }
        }
    }

    /* JADX INFO: compiled from: AnimationFrame.java */
    public static class c extends cj implements Handler.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HandlerThread f7217a;
        public Handler b;
        public a c;
        public boolean d;

        public c() {
            if (this.f7217a != null) {
                b();
            }
            HandlerThread handlerThread = new HandlerThread("expression-timing-thread");
            this.f7217a = handlerThread;
            handlerThread.start();
            this.b = new Handler(this.f7217a.getLooper(), this);
        }

        @Override // supwisdom.cj
        public void a() {
            Handler handler = this.b;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            this.d = false;
        }

        @Override // supwisdom.cj
        public void b() {
            a();
            if (Build.VERSION.SDK_INT >= 18) {
                this.f7217a.quitSafely();
            } else {
                this.f7217a.quit();
            }
            this.b = null;
            this.f7217a = null;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null || message.what != 100 || this.b == null) {
                return false;
            }
            a aVar = this.c;
            if (aVar != null) {
                aVar.a();
            }
            if (!this.d) {
                return true;
            }
            this.b.sendEmptyMessageDelayed(100, 16L);
            return true;
        }

        @Override // supwisdom.cj
        public void a(a aVar) {
            this.c = aVar;
            this.d = true;
            Handler handler = this.b;
            if (handler != null) {
                handler.sendEmptyMessage(100);
            }
        }
    }
}
