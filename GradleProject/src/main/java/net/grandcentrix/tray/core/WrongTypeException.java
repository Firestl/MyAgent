package net.grandcentrix.tray.core;

/* JADX INFO: loaded from: classes3.dex */
public class WrongTypeException extends TrayRuntimeException {
    public WrongTypeException() {
    }

    public WrongTypeException(String str) {
        super(str);
    }

    public WrongTypeException(String str, Object... objArr) {
        super(str, objArr);
    }

    public WrongTypeException(String str, Throwable th) {
        super(str, th);
    }

    public WrongTypeException(Throwable th) {
        super(th);
    }
}
