package supwisdom;

import cz.msebera.android.httpclient.entity.ContentType;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: compiled from: StringEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public class no1 extends mo1 implements Cloneable {
    public final byte[] d;

    public no1(String str, ContentType contentType) throws UnsupportedCharsetException {
        yo1.a(str, "Source string");
        Charset charset = contentType != null ? contentType.getCharset() : null;
        charset = charset == null ? xo1.f9794a : charset;
        try {
            this.d = str.getBytes(charset.name());
            if (contentType != null) {
                a(contentType.toString());
            }
        } catch (UnsupportedEncodingException unused) {
            throw new UnsupportedCharsetException(charset.name());
        }
    }

    @Override // supwisdom.ao1
    public long b() {
        return this.d.length;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
