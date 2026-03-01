package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.ProtocolException;

/* JADX INFO: loaded from: classes2.dex */
public class AuthenticationException extends ProtocolException {
    public static final long serialVersionUID = -6794031905674764776L;

    public AuthenticationException() {
    }

    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(String str, Throwable th) {
        super(str, th);
    }
}
