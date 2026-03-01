package cz.msebera.android.httpclient;

/* JADX INFO: loaded from: classes2.dex */
public class HttpException extends Exception {
    public static final long serialVersionUID = -5437299376222011036L;

    public HttpException() {
    }

    public HttpException(String str) {
        super(str);
    }

    public HttpException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
