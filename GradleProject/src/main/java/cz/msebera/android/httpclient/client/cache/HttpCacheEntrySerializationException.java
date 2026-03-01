package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class HttpCacheEntrySerializationException extends IOException {
    public static final long serialVersionUID = 9219188365878433519L;

    public HttpCacheEntrySerializationException(String str) {
    }

    public HttpCacheEntrySerializationException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
