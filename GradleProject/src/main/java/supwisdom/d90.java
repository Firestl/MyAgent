package supwisdom;

import android.os.Handler;
import android.view.Surface;

/* JADX INFO: compiled from: VideoRendererEventListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface d90 {

    /* JADX INFO: compiled from: VideoRendererEventListener.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f7305a;
        public final d90 b;

        /* JADX INFO: renamed from: supwisdom.d90$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class RunnableC0212a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ x10 f7306a;

            public RunnableC0212a(x10 x10Var) {
                this.f7306a = x10Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onVideoEnabled(this.f7306a);
            }
        }

        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f7307a;
            public final /* synthetic */ long b;
            public final /* synthetic */ long c;

            public b(String str, long j, long j2) {
                this.f7307a = str;
                this.b = j;
                this.c = j2;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onVideoDecoderInitialized(this.f7307a, this.b, this.c);
            }
        }

        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ com.google.android.exoplayer2.j f7308a;

            public c(com.google.android.exoplayer2.j jVar) {
                this.f7308a = jVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onVideoInputFormatChanged(this.f7308a);
            }
        }

        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class d implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f7309a;
            public final /* synthetic */ long b;

            public d(int i, long j) {
                this.f7309a = i;
                this.b = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onDroppedFrames(this.f7309a, this.b);
            }
        }

        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class e implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f7310a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ float d;

            public e(int i, int i2, int i3, float f) {
                this.f7310a = i;
                this.b = i2;
                this.c = i3;
                this.d = f;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onVideoSizeChanged(this.f7310a, this.b, this.c, this.d);
            }
        }

        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class f implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Surface f7312a;

            public f(Surface surface) {
                this.f7312a = surface;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onRenderedFirstFrame(this.f7312a);
            }
        }

        /* JADX INFO: compiled from: VideoRendererEventListener.java */
        public class g implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ x10 f7313a;

            public g(x10 x10Var) {
                this.f7313a = x10Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f7313a.a();
                a.this.b.onVideoDisabled(this.f7313a);
            }
        }

        public a(Handler handler, d90 d90Var) {
            Handler handler2;
            if (d90Var != null) {
                e80.a(handler);
                handler2 = handler;
            } else {
                handler2 = null;
            }
            this.f7305a = handler2;
            this.b = d90Var;
        }

        public void b(x10 x10Var) {
            if (this.b != null) {
                this.f7305a.post(new g(x10Var));
            }
        }

        public void a(x10 x10Var) {
            if (this.b != null) {
                this.f7305a.post(new RunnableC0212a(x10Var));
            }
        }

        public void a(String str, long j, long j2) {
            if (this.b != null) {
                this.f7305a.post(new b(str, j, j2));
            }
        }

        public void a(com.google.android.exoplayer2.j jVar) {
            if (this.b != null) {
                this.f7305a.post(new c(jVar));
            }
        }

        public void a(int i, long j) {
            if (this.b != null) {
                this.f7305a.post(new d(i, j));
            }
        }

        public void a(int i, int i2, int i3, float f2) {
            if (this.b != null) {
                this.f7305a.post(new e(i, i2, i3, f2));
            }
        }

        public void a(Surface surface) {
            if (this.b != null) {
                this.f7305a.post(new f(surface));
            }
        }
    }

    void onDroppedFrames(int i, long j);

    void onRenderedFirstFrame(Surface surface);

    void onVideoDecoderInitialized(String str, long j, long j2);

    void onVideoDisabled(x10 x10Var);

    void onVideoEnabled(x10 x10Var);

    void onVideoInputFormatChanged(com.google.android.exoplayer2.j jVar);

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
