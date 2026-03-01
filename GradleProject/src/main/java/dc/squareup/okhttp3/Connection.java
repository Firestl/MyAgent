package dc.squareup.okhttp3;

import java.net.Socket;

/* JADX INFO: loaded from: classes2.dex */
public interface Connection {
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
