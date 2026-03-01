package dc.squareup.okhttp3.internal.cache;

import dc.squareup.okhttp3.Request;
import dc.squareup.okhttp3.Response;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalCache {
    Response get(Request request) throws IOException;

    CacheRequest put(Response response) throws IOException;

    void remove(Request request) throws IOException;

    void trackConditionalCacheHit();

    void trackResponse(CacheStrategy cacheStrategy);

    void update(Response response, Response response2);
}
