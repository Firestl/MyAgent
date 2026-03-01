package org.bouncycastle.util;

/* JADX INFO: loaded from: classes3.dex */
public class StreamParsingException extends Exception {
    public Throwable _e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this._e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this._e;
    }
}
