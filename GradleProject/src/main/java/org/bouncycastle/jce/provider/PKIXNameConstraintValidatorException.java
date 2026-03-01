package org.bouncycastle.jce.provider;

/* JADX INFO: loaded from: classes3.dex */
public class PKIXNameConstraintValidatorException extends Exception {
    public Throwable cause;

    public PKIXNameConstraintValidatorException(String str) {
        super(str);
    }

    public PKIXNameConstraintValidatorException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
