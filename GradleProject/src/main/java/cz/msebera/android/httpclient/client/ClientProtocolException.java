package cz.msebera.android.httpclient.client;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class ClientProtocolException extends IOException {
    public static final long serialVersionUID = -5596590843227115865L;

    public ClientProtocolException() {
    }

    public ClientProtocolException(String str) {
        super(str);
    }

    public ClientProtocolException(Throwable th) {
        initCause(th);
    }

    public ClientProtocolException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
