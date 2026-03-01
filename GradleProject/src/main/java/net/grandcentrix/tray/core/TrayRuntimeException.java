package net.grandcentrix.tray.core;

/* JADX INFO: loaded from: classes3.dex */
public class TrayRuntimeException extends RuntimeException {
    public TrayRuntimeException() {
    }

    public TrayRuntimeException(String str) {
        super(str);
    }

    public TrayRuntimeException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }

    public TrayRuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public TrayRuntimeException(Throwable th) {
        super(th);
    }
}
