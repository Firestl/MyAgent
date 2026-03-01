package dc.squareup.okhttp3.internal.cache;

import dc.squareup.okio.Sink;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
