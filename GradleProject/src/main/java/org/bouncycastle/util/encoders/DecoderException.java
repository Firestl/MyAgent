package org.bouncycastle.util.encoders;

/* JADX INFO: loaded from: classes3.dex */
public class DecoderException extends IllegalStateException {
    public Throwable cause;

    public DecoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
