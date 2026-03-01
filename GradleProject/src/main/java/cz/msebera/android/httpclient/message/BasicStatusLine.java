package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;
import supwisdom.eo1;
import supwisdom.ro1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicStatusLine implements eo1, Cloneable, Serializable {
    public static final long serialVersionUID = -2443303766890459269L;
    public final ProtocolVersion protoVersion;
    public final String reasonPhrase;
    public final int statusCode;

    public BasicStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        yo1.a(protocolVersion, "Version");
        this.protoVersion = protocolVersion;
        yo1.a(i, "Status code");
        this.statusCode = i;
        this.reasonPhrase = str;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // supwisdom.eo1
    public ProtocolVersion getProtocolVersion() {
        return this.protoVersion;
    }

    @Override // supwisdom.eo1
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override // supwisdom.eo1
    public int getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        return ro1.f9079a.b((CharArrayBuffer) null, this).toString();
    }
}
