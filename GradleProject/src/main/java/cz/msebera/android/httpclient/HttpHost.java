package cz.msebera.android.httpclient;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;
import supwisdom.ap1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpHost implements Cloneable, Serializable {
    public static final String DEFAULT_SCHEME_NAME = "http";
    public static final long serialVersionUID = -7529410654042457626L;
    public final InetAddress address;
    public final String hostname;
    public final String lcHostname;
    public final int port;
    public final String schemeName;

    public HttpHost(String str, int i, String str2) {
        yo1.a(str, "Host name");
        this.hostname = str;
        this.lcHostname = str.toLowerCase(Locale.ENGLISH);
        if (str2 != null) {
            this.schemeName = str2.toLowerCase(Locale.ENGLISH);
        } else {
            this.schemeName = "http";
        }
        this.port = i;
        this.address = null;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpHost)) {
            return false;
        }
        HttpHost httpHost = (HttpHost) obj;
        return this.lcHostname.equals(httpHost.lcHostname) && this.port == httpHost.port && this.schemeName.equals(httpHost.schemeName);
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public String getHostName() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public int hashCode() {
        return ap1.a(ap1.a(ap1.a(17, this.lcHostname), this.port), this.schemeName);
    }

    public String toHostString() {
        if (this.port == -1) {
            return this.hostname;
        }
        StringBuilder sb = new StringBuilder(this.hostname.length() + 6);
        sb.append(this.hostname);
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(Integer.toString(this.port));
        return sb.toString();
    }

    public String toString() {
        return toURI();
    }

    public String toURI() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.schemeName);
        sb.append("://");
        sb.append(this.hostname);
        if (this.port != -1) {
            sb.append(Operators.CONDITION_IF_MIDDLE);
            sb.append(Integer.toString(this.port));
        }
        return sb.toString();
    }

    public HttpHost(String str, int i) {
        this(str, i, (String) null);
    }

    public HttpHost(String str) {
        this(str, -1, (String) null);
    }

    public HttpHost(InetAddress inetAddress, int i, String str) {
        yo1.a(inetAddress, "Inet address");
        this.address = inetAddress;
        String hostAddress = inetAddress.getHostAddress();
        this.hostname = hostAddress;
        this.lcHostname = hostAddress.toLowerCase(Locale.ENGLISH);
        if (str != null) {
            this.schemeName = str.toLowerCase(Locale.ENGLISH);
        } else {
            this.schemeName = "http";
        }
        this.port = i;
    }

    public HttpHost(InetAddress inetAddress, int i) {
        this(inetAddress, i, (String) null);
    }

    public HttpHost(InetAddress inetAddress) {
        this(inetAddress, -1, (String) null);
    }

    public HttpHost(HttpHost httpHost) {
        yo1.a(httpHost, "HTTP host");
        this.hostname = httpHost.hostname;
        this.lcHostname = httpHost.lcHostname;
        this.schemeName = httpHost.schemeName;
        this.port = httpHost.port;
        this.address = httpHost.address;
    }
}
