package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.ProtocolException;

/* JADX INFO: loaded from: classes2.dex */
public class NonRepeatableRequestException extends ProtocolException {
    public static final long serialVersionUID = 82685265288806048L;

    public NonRepeatableRequestException() {
    }

    public NonRepeatableRequestException(String str) {
        super(str);
    }

    public NonRepeatableRequestException(String str, Throwable th) {
        super(str, th);
    }
}
