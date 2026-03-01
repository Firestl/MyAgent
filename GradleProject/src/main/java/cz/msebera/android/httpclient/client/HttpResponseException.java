package cz.msebera.android.httpclient.client;

/* JADX INFO: loaded from: classes2.dex */
public class HttpResponseException extends ClientProtocolException {
    public static final long serialVersionUID = -7186627969477257933L;
    public final int statusCode;

    public HttpResponseException(int i, String str) {
        super(str);
        this.statusCode = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
