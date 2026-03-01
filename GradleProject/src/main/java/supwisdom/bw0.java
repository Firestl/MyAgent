package supwisdom;

import com.lzy.okgo.model.Progress;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* JADX INFO: compiled from: ProgressRequestBody.java */
/* JADX INFO: loaded from: classes2.dex */
public class bw0<T> extends ct1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ct1 f7111a;
    public rv0<T> b;
    public c c;

    /* JADX INFO: compiled from: ProgressRequestBody.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Progress f7112a;

        public a(Progress progress) {
            this.f7112a = progress;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (bw0.this.b != null) {
                bw0.this.b.a(this.f7112a);
            }
        }
    }

    /* JADX INFO: compiled from: ProgressRequestBody.java */
    public final class b extends ForwardingSink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Progress f7113a;

        /* JADX INFO: compiled from: ProgressRequestBody.java */
        public class a implements Progress.a {
            public a() {
            }

            @Override // com.lzy.okgo.model.Progress.a
            public void a(Progress progress) {
                if (bw0.this.c != null) {
                    bw0.this.c.a(progress);
                } else {
                    bw0.this.a(progress);
                }
            }
        }

        public b(Sink sink) {
            super(sink);
            Progress progress = new Progress();
            this.f7113a = progress;
            progress.totalSize = bw0.this.contentLength();
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            Progress.changeProgress(this.f7113a, j, new a());
        }
    }

    /* JADX INFO: compiled from: ProgressRequestBody.java */
    public interface c {
        void a(Progress progress);
    }

    public bw0(ct1 ct1Var, rv0<T> rv0Var) {
        this.f7111a = ct1Var;
        this.b = rv0Var;
    }

    @Override // supwisdom.ct1
    public long contentLength() {
        try {
            return this.f7111a.contentLength();
        } catch (IOException e2) {
            fw0.a(e2);
            return -1L;
        }
    }

    @Override // supwisdom.ct1
    public xs1 contentType() {
        return this.f7111a.contentType();
    }

    @Override // supwisdom.ct1
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink bufferedSinkBuffer = Okio.buffer(new b(bufferedSink));
        this.f7111a.writeTo(bufferedSinkBuffer);
        bufferedSinkBuffer.flush();
    }

    public final void a(Progress progress) {
        dw0.a(new a(progress));
    }

    public void a(c cVar) {
        this.c = cVar;
    }
}
