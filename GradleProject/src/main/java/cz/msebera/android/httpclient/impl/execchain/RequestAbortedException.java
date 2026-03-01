package cz.msebera.android.httpclient.impl.execchain;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes2.dex */
public class RequestAbortedException extends InterruptedIOException {
    public static final long serialVersionUID = 4973849966012490112L;

    public RequestAbortedException(String str) {
        super(str);
    }

    public RequestAbortedException(String str, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
    }
}
