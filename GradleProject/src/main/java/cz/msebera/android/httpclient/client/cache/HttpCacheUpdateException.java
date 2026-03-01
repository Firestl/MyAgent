package cz.msebera.android.httpclient.client.cache;

/* JADX INFO: loaded from: classes2.dex */
public class HttpCacheUpdateException extends Exception {
    public static final long serialVersionUID = 823573584868632876L;

    public HttpCacheUpdateException(String str) {
        super(str);
    }

    public HttpCacheUpdateException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
