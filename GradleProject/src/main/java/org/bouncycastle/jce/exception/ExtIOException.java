package org.bouncycastle.jce.exception;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ExtIOException extends IOException implements ExtException {
    public Throwable cause;

    public ExtIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable, org.bouncycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this.cause;
    }
}
