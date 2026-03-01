package supwisdom;

import android.os.Handler;

/* JADX INFO: compiled from: AudioRendererEventListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface m10 {

    /* JADX INFO: compiled from: AudioRendererEventListener.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f8338a;
        public final m10 b;

        /* JADX INFO: renamed from: supwisdom.m10$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AudioRendererEventListener.java */
        public class RunnableC0218a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ x10 f8339a;

            public RunnableC0218a(x10 x10Var) {
                this.f8339a = x10Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onAudioEnabled(this.f8339a);
            }
        }

        /* JADX INFO: compiled from: AudioRendererEventListener.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f8340a;
            public final /* synthetic */ long b;
            public final /* synthetic */ long c;

            public b(String str, long j, long j2) {
                this.f8340a = str;
                this.b = j;
                this.c = j2;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onAudioDecoderInitialized(this.f8340a, this.b, this.c);
            }
        }

        /* JADX INFO: compiled from: AudioRendererEventListener.java */
        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ com.google.android.exoplayer2.j f8341a;

            public c(com.google.android.exoplayer2.j jVar) {
                this.f8341a = jVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onAudioInputFormatChanged(this.f8341a);
            }
        }

        /* JADX INFO: compiled from: AudioRendererEventListener.java */
        public class d implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8342a;
            public final /* synthetic */ long b;
            public final /* synthetic */ long c;

            public d(int i, long j, long j2) {
                this.f8342a = i;
                this.b = j;
                this.c = j2;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onAudioTrackUnderrun(this.f8342a, this.b, this.c);
            }
        }

        /* JADX INFO: compiled from: AudioRendererEventListener.java */
        public class e implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ x10 f8343a;

            public e(x10 x10Var) {
                this.f8343a = x10Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f8343a.a();
                a.this.b.onAudioDisabled(this.f8343a);
            }
        }

        /* JADX INFO: compiled from: AudioRendererEventListener.java */
        public class f implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8344a;

            public f(int i) {
                this.f8344a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onAudioSessionId(this.f8344a);
            }
        }

        public a(Handler handler, m10 m10Var) {
            Handler handler2;
            if (m10Var != null) {
                e80.a(handler);
                handler2 = handler;
            } else {
                handler2 = null;
            }
            this.f8338a = handler2;
            this.b = m10Var;
        }

        public void b(x10 x10Var) {
            if (this.b != null) {
                this.f8338a.post(new e(x10Var));
            }
        }

        public void a(x10 x10Var) {
            if (this.b != null) {
                this.f8338a.post(new RunnableC0218a(x10Var));
            }
        }

        public void a(String str, long j, long j2) {
            if (this.b != null) {
                this.f8338a.post(new b(str, j, j2));
            }
        }

        public void a(com.google.android.exoplayer2.j jVar) {
            if (this.b != null) {
                this.f8338a.post(new c(jVar));
            }
        }

        public void a(int i, long j, long j2) {
            if (this.b != null) {
                this.f8338a.post(new d(i, j, j2));
            }
        }

        public void a(int i) {
            if (this.b != null) {
                this.f8338a.post(new f(i));
            }
        }
    }

    void onAudioDecoderInitialized(String str, long j, long j2);

    void onAudioDisabled(x10 x10Var);

    void onAudioEnabled(x10 x10Var);

    void onAudioInputFormatChanged(com.google.android.exoplayer2.j jVar);

    void onAudioSessionId(int i);

    void onAudioTrackUnderrun(int i, long j, long j2);
}
