package supwisdom;

import java.io.IOException;
import okio.Sink;
import supwisdom.dt1;

/* JADX INFO: compiled from: HttpCodec.java */
/* JADX INFO: loaded from: classes3.dex */
public interface yt1 {
    Sink a(bt1 bt1Var, long j);

    et1 a(dt1 dt1Var) throws IOException;

    void a(bt1 bt1Var) throws IOException;

    void cancel();

    void finishRequest() throws IOException;

    void flushRequest() throws IOException;

    dt1.a readResponseHeaders(boolean z) throws IOException;
}
