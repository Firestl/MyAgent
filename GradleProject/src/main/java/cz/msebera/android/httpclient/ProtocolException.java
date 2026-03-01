package cz.msebera.android.httpclient;

/* JADX INFO: loaded from: classes2.dex */
public class ProtocolException extends HttpException {
    public static final long serialVersionUID = -2143571074341228994L;

    public ProtocolException() {
    }

    public ProtocolException(String str) {
        super(str);
    }

    public ProtocolException(String str, Throwable th) {
        super(str, th);
    }
}
