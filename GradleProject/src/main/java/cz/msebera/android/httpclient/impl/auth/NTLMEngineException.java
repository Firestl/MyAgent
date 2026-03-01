package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.auth.AuthenticationException;

/* JADX INFO: loaded from: classes2.dex */
public class NTLMEngineException extends AuthenticationException {
    public static final long serialVersionUID = 6027981323731768824L;

    public NTLMEngineException() {
    }

    public NTLMEngineException(String str) {
        super(str);
    }

    public NTLMEngineException(String str, Throwable th) {
        super(str, th);
    }
}
