package cz.msebera.android.httpclient.conn;

import com.taobao.weex.el.parse.Operators;
import cz.msebera.android.httpclient.HttpHost;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectTimeoutException extends InterruptedIOException {
    public static final long serialVersionUID = -4816682903149535989L;
    public final HttpHost host;

    public ConnectTimeoutException() {
        this.host = null;
    }

    public HttpHost getHost() {
        return this.host;
    }

    public ConnectTimeoutException(String str) {
        super(str);
        this.host = null;
    }

    public ConnectTimeoutException(IOException iOException, HttpHost httpHost, InetAddress... inetAddressArr) {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("Connect to ");
        sb.append(httpHost != null ? httpHost.toHostString() : "remote host");
        if (inetAddressArr == null || inetAddressArr.length <= 0) {
            str = "";
        } else {
            str = Operators.SPACE_STR + Arrays.asList(inetAddressArr);
        }
        sb.append(str);
        if (iOException == null || iOException.getMessage() == null) {
            str2 = " timed out";
        } else {
            str2 = " failed: " + iOException.getMessage();
        }
        sb.append(str2);
        super(sb.toString());
        this.host = httpHost;
        initCause(iOException);
    }
}
