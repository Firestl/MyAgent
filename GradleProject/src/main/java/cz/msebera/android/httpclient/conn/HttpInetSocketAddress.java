package cz.msebera.android.httpclient.conn;

import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.HttpHost;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class HttpInetSocketAddress extends InetSocketAddress {
    public static final long serialVersionUID = -6650701828361907957L;
    public final HttpHost httphost;

    public HttpInetSocketAddress(HttpHost httpHost, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        yo1.a(httpHost, "HTTP host");
        this.httphost = httpHost;
    }

    public HttpHost getHttpHost() {
        return this.httphost;
    }

    @Override // java.net.InetSocketAddress
    public String toString() {
        return this.httphost.getHostName() + Constants.COLON_SEPARATOR + getPort();
    }
}
