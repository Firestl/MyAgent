package supwisdom;

import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

/* JADX INFO: compiled from: BasicLineFormatter.java */
/* JADX INFO: loaded from: classes2.dex */
public class ro1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ro1 f9079a;

    static {
        new ro1();
        f9079a = new ro1();
    }

    public CharArrayBuffer a(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer == null) {
            return new CharArrayBuffer(64);
        }
        charArrayBuffer.clear();
        return charArrayBuffer;
    }

    public CharArrayBuffer b(CharArrayBuffer charArrayBuffer, do1 do1Var) {
        yo1.a(do1Var, "Request line");
        CharArrayBuffer charArrayBufferA = a(charArrayBuffer);
        a(charArrayBufferA, do1Var);
        return charArrayBufferA;
    }

    public CharArrayBuffer a(CharArrayBuffer charArrayBuffer, ProtocolVersion protocolVersion) {
        yo1.a(protocolVersion, "Protocol version");
        int iA = a(protocolVersion);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(iA);
        } else {
            charArrayBuffer.ensureCapacity(iA);
        }
        charArrayBuffer.append(protocolVersion.getProtocol());
        charArrayBuffer.append('/');
        charArrayBuffer.append(Integer.toString(protocolVersion.getMajor()));
        charArrayBuffer.append('.');
        charArrayBuffer.append(Integer.toString(protocolVersion.getMinor()));
        return charArrayBuffer;
    }

    public CharArrayBuffer b(CharArrayBuffer charArrayBuffer, eo1 eo1Var) {
        yo1.a(eo1Var, "Status line");
        CharArrayBuffer charArrayBufferA = a(charArrayBuffer);
        a(charArrayBufferA, eo1Var);
        return charArrayBufferA;
    }

    public CharArrayBuffer b(CharArrayBuffer charArrayBuffer, xn1 xn1Var) {
        yo1.a(xn1Var, "Header");
        if (xn1Var instanceof wn1) {
            return ((wn1) xn1Var).getBuffer();
        }
        CharArrayBuffer charArrayBufferA = a(charArrayBuffer);
        a(charArrayBufferA, xn1Var);
        return charArrayBufferA;
    }

    public int a(ProtocolVersion protocolVersion) {
        return protocolVersion.getProtocol().length() + 4;
    }

    public void a(CharArrayBuffer charArrayBuffer, do1 do1Var) {
        String method = do1Var.getMethod();
        String uri = do1Var.getUri();
        charArrayBuffer.ensureCapacity(method.length() + 1 + uri.length() + 1 + a(do1Var.getProtocolVersion()));
        charArrayBuffer.append(method);
        charArrayBuffer.append(' ');
        charArrayBuffer.append(uri);
        charArrayBuffer.append(' ');
        a(charArrayBuffer, do1Var.getProtocolVersion());
    }

    public void a(CharArrayBuffer charArrayBuffer, eo1 eo1Var) {
        int iA = a(eo1Var.getProtocolVersion()) + 1 + 3 + 1;
        String reasonPhrase = eo1Var.getReasonPhrase();
        if (reasonPhrase != null) {
            iA += reasonPhrase.length();
        }
        charArrayBuffer.ensureCapacity(iA);
        a(charArrayBuffer, eo1Var.getProtocolVersion());
        charArrayBuffer.append(' ');
        charArrayBuffer.append(Integer.toString(eo1Var.getStatusCode()));
        charArrayBuffer.append(' ');
        if (reasonPhrase != null) {
            charArrayBuffer.append(reasonPhrase);
        }
    }

    public void a(CharArrayBuffer charArrayBuffer, xn1 xn1Var) {
        String name = xn1Var.getName();
        String value = xn1Var.getValue();
        int length = name.length() + 2;
        if (value != null) {
            length += value.length();
        }
        charArrayBuffer.ensureCapacity(length);
        charArrayBuffer.append(name);
        charArrayBuffer.append(": ");
        if (value != null) {
            charArrayBuffer.append(value);
        }
    }
}
