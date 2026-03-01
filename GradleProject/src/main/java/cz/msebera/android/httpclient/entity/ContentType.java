package cz.msebera.android.httpclient.entity;

import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import io.dcloud.common.util.net.NetWork;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import supwisdom.ao1;
import supwisdom.bp1;
import supwisdom.co1;
import supwisdom.po1;
import supwisdom.qo1;
import supwisdom.uo1;
import supwisdom.vn1;
import supwisdom.xn1;
import supwisdom.yn1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentType implements Serializable {
    public static final long serialVersionUID = -7768694718232371896L;
    public final Charset charset;
    public final String mimeType;
    public final co1[] params;
    public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", vn1.c);
    public static final ContentType APPLICATION_FORM_URLENCODED = create(NetWork.CONTENT_TYPE_UPLOAD, vn1.c);
    public static final ContentType APPLICATION_JSON = create(RequestParams.APPLICATION_JSON, vn1.f9531a);
    public static final ContentType APPLICATION_OCTET_STREAM = create(RequestParams.APPLICATION_OCTET_STREAM, (Charset) null);
    public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", vn1.c);
    public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", vn1.c);
    public static final ContentType APPLICATION_XML = create("application/xml", vn1.c);
    public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", vn1.c);
    public static final ContentType TEXT_HTML = create("text/html", vn1.c);
    public static final ContentType TEXT_PLAIN = create("text/plain", vn1.c);
    public static final ContentType TEXT_XML = create("text/xml", vn1.c);
    public static final ContentType WILDCARD = create("*/*", (Charset) null);
    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;

    public ContentType(String str, Charset charset) {
        this.mimeType = str;
        this.charset = charset;
        this.params = null;
    }

    public static ContentType create(String str, Charset charset) {
        yo1.a(str, "MIME type");
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        yo1.a(valid(lowerCase), "MIME type may not contain reserved characters");
        return new ContentType(lowerCase, charset);
    }

    public static ContentType get(ao1 ao1Var) throws ParseException, UnsupportedCharsetException {
        xn1 xn1VarA;
        if (ao1Var != null && (xn1VarA = ao1Var.a()) != null) {
            yn1[] elements = xn1VarA.getElements();
            if (elements.length > 0) {
                return create(elements[0]);
            }
        }
        return null;
    }

    public static ContentType getOrDefault(ao1 ao1Var) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = get(ao1Var);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public static ContentType parse(String str) throws ParseException, UnsupportedCharsetException {
        yo1.a(str, "Content type");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        yn1[] yn1VarArrA = qo1.f8943a.a(charArrayBuffer, new uo1(0, str.length()));
        if (yn1VarArrA.length > 0) {
            return create(yn1VarArrA[0]);
        }
        throw new ParseException("Invalid content type: " + str);
    }

    public static boolean valid(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == ',' || cCharAt == ';') {
                return false;
            }
        }
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getParameter(String str) {
        yo1.b(str, "Parameter name");
        co1[] co1VarArr = this.params;
        if (co1VarArr == null) {
            return null;
        }
        for (co1 co1Var : co1VarArr) {
            if (co1Var.getName().equalsIgnoreCase(str)) {
                return co1Var.getValue();
            }
        }
        return null;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        charArrayBuffer.append(this.mimeType);
        if (this.params != null) {
            charArrayBuffer.append("; ");
            po1.f8814a.a(charArrayBuffer, this.params, false);
        } else if (this.charset != null) {
            charArrayBuffer.append("; charset=");
            charArrayBuffer.append(this.charset.name());
        }
        return charArrayBuffer.toString();
    }

    public ContentType withCharset(Charset charset) {
        return create(getMimeType(), charset);
    }

    public ContentType withCharset(String str) {
        return create(getMimeType(), str);
    }

    public static ContentType create(String str) {
        return new ContentType(str, (Charset) null);
    }

    public ContentType(String str, co1[] co1VarArr) throws UnsupportedCharsetException {
        this.mimeType = str;
        this.params = co1VarArr;
        String parameter = getParameter("charset");
        this.charset = !bp1.a(parameter) ? Charset.forName(parameter) : null;
    }

    public static ContentType create(String str, String str2) throws UnsupportedCharsetException {
        return create(str, !bp1.a(str2) ? Charset.forName(str2) : null);
    }

    public static ContentType create(yn1 yn1Var) {
        String name = yn1Var.getName();
        co1[] parameters = yn1Var.getParameters();
        if (parameters == null || parameters.length <= 0) {
            parameters = null;
        }
        return new ContentType(name, parameters);
    }
}
