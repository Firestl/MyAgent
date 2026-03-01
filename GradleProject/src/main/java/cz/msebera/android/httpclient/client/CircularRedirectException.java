package cz.msebera.android.httpclient.client;

/* JADX INFO: loaded from: classes2.dex */
public class CircularRedirectException extends RedirectException {
    public static final long serialVersionUID = 6830063487001091803L;

    public CircularRedirectException() {
    }

    public CircularRedirectException(String str) {
        super(str);
    }

    public CircularRedirectException(String str, Throwable th) {
        super(str, th);
    }
}
