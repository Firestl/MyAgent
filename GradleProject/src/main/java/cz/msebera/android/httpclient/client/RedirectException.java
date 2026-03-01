package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.ProtocolException;

/* JADX INFO: loaded from: classes2.dex */
public class RedirectException extends ProtocolException {
    public static final long serialVersionUID = 4418824536372559326L;

    public RedirectException() {
    }

    public RedirectException(String str) {
        super(str);
    }

    public RedirectException(String str, Throwable th) {
        super(str, th);
    }
}
