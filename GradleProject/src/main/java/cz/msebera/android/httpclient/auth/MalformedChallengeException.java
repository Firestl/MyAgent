package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.ProtocolException;

/* JADX INFO: loaded from: classes2.dex */
public class MalformedChallengeException extends ProtocolException {
    public static final long serialVersionUID = 814586927989932284L;

    public MalformedChallengeException() {
    }

    public MalformedChallengeException(String str) {
        super(str);
    }

    public MalformedChallengeException(String str, Throwable th) {
        super(str, th);
    }
}
