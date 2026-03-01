package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;
import supwisdom.do1;
import supwisdom.ro1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicRequestLine implements do1, Cloneable, Serializable {
    public static final long serialVersionUID = 2810581718468737193L;
    public final String method;
    public final ProtocolVersion protoversion;
    public final String uri;

    public BasicRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        yo1.a(str, "Method");
        this.method = str;
        yo1.a(str2, "URI");
        this.uri = str2;
        yo1.a(protocolVersion, "Version");
        this.protoversion = protocolVersion;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // supwisdom.do1
    public String getMethod() {
        return this.method;
    }

    @Override // supwisdom.do1
    public ProtocolVersion getProtocolVersion() {
        return this.protoversion;
    }

    @Override // supwisdom.do1
    public String getUri() {
        return this.uri;
    }

    public String toString() {
        return ro1.f9079a.b((CharArrayBuffer) null, this).toString();
    }
}
