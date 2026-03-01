package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.ProtocolException;

/* JADX INFO: loaded from: classes2.dex */
public class MalformedCookieException extends ProtocolException {
    public static final long serialVersionUID = -6695462944287282185L;

    public MalformedCookieException() {
    }

    public MalformedCookieException(String str) {
        super(str);
    }

    public MalformedCookieException(String str, Throwable th) {
        super(str, th);
    }
}
