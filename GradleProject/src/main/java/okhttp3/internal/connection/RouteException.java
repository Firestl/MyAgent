package okhttp3.internal.connection;

import java.io.IOException;
import supwisdom.kt1;

/* JADX INFO: loaded from: classes3.dex */
public final class RouteException extends RuntimeException {
    public IOException firstException;
    public IOException lastException;

    public RouteException(IOException iOException) {
        super(iOException);
        this.firstException = iOException;
        this.lastException = iOException;
    }

    public void addConnectException(IOException iOException) {
        kt1.a((Throwable) this.firstException, (Throwable) iOException);
        this.lastException = iOException;
    }

    public IOException getFirstConnectException() {
        return this.firstException;
    }

    public IOException getLastConnectException() {
        return this.lastException;
    }
}
