package retrofit2;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import supwisdom.bt1;
import supwisdom.ct1;
import supwisdom.ss1;
import supwisdom.us1;
import supwisdom.vs1;
import supwisdom.xs1;
import supwisdom.ys1;

/* JADX INFO: loaded from: classes3.dex */
public final class RequestBuilder {
    public static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    public final vs1 baseUrl;

    @Nullable
    public ct1 body;

    @Nullable
    public xs1 contentType;

    @Nullable
    public ss1.a formBuilder;
    public final boolean hasBody;
    public final us1.a headersBuilder;
    public final String method;

    @Nullable
    public ys1.a multipartBuilder;

    @Nullable
    public String relativeUrl;
    public final bt1.a requestBuilder = new bt1.a();

    @Nullable
    public vs1.a urlBuilder;
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};
    public static final Pattern PATH_TRAVERSAL = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    public static class ContentTypeOverridingRequestBody extends ct1 {
        public final xs1 contentType;
        public final ct1 delegate;

        public ContentTypeOverridingRequestBody(ct1 ct1Var, xs1 xs1Var) {
            this.delegate = ct1Var;
            this.contentType = xs1Var;
        }

        @Override // supwisdom.ct1
        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        @Override // supwisdom.ct1
        public xs1 contentType() {
            return this.contentType;
        }

        @Override // supwisdom.ct1
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.delegate.writeTo(bufferedSink);
        }
    }

    public RequestBuilder(String str, vs1 vs1Var, @Nullable String str2, @Nullable us1 us1Var, @Nullable xs1 xs1Var, boolean z, boolean z2, boolean z3) {
        this.method = str;
        this.baseUrl = vs1Var;
        this.relativeUrl = str2;
        this.contentType = xs1Var;
        this.hasBody = z;
        if (us1Var != null) {
            this.headersBuilder = us1Var.b();
        } else {
            this.headersBuilder = new us1.a();
        }
        if (z2) {
            this.formBuilder = new ss1.a();
        } else if (z3) {
            ys1.a aVar = new ys1.a();
            this.multipartBuilder = aVar;
            aVar.a(ys1.f);
        }
    }

    public static String canonicalizeForPath(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, iCharCount);
                canonicalizeForPath(buffer, str, iCharCount, length, z);
                return buffer.readUtf8();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    public void addFormField(String str, String str2, boolean z) {
        if (z) {
            this.formBuilder.b(str, str2);
        } else {
            this.formBuilder.a(str, str2);
        }
    }

    public void addHeader(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.headersBuilder.a(str, str2);
            return;
        }
        try {
            this.contentType = xs1.a(str2);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("Malformed content type: " + str2, e2);
        }
    }

    public void addHeaders(us1 us1Var) {
        this.headersBuilder.a(us1Var);
    }

    public void addPart(us1 us1Var, ct1 ct1Var) {
        this.multipartBuilder.a(us1Var, ct1Var);
    }

    public void addPathParam(String str, String str2, boolean z) {
        if (this.relativeUrl == null) {
            throw new AssertionError();
        }
        String strCanonicalizeForPath = canonicalizeForPath(str2, z);
        String strReplace = this.relativeUrl.replace(Operators.BLOCK_START_STR + str + Operators.BLOCK_END_STR, strCanonicalizeForPath);
        if (!PATH_TRAVERSAL.matcher(strReplace).matches()) {
            this.relativeUrl = strReplace;
            return;
        }
        throw new IllegalArgumentException("@Path parameters shouldn't perform path traversal ('.' or '..'): " + str2);
    }

    public void addQueryParam(String str, @Nullable String str2, boolean z) {
        String str3 = this.relativeUrl;
        if (str3 != null) {
            vs1.a aVarA = this.baseUrl.a(str3);
            this.urlBuilder = aVarA;
            if (aVarA == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
            this.relativeUrl = null;
        }
        if (z) {
            this.urlBuilder.a(str, str2);
        } else {
            this.urlBuilder.b(str, str2);
        }
    }

    public <T> void addTag(Class<T> cls, @Nullable T t) {
        this.requestBuilder.a(cls, t);
    }

    public bt1.a get() {
        vs1 vs1VarB;
        vs1.a aVar = this.urlBuilder;
        if (aVar != null) {
            vs1VarB = aVar.a();
        } else {
            vs1VarB = this.baseUrl.b(this.relativeUrl);
            if (vs1VarB == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
        }
        ct1 contentTypeOverridingRequestBody = this.body;
        if (contentTypeOverridingRequestBody == null) {
            ss1.a aVar2 = this.formBuilder;
            if (aVar2 != null) {
                contentTypeOverridingRequestBody = aVar2.a();
            } else {
                ys1.a aVar3 = this.multipartBuilder;
                if (aVar3 != null) {
                    contentTypeOverridingRequestBody = aVar3.a();
                } else if (this.hasBody) {
                    contentTypeOverridingRequestBody = ct1.create((xs1) null, new byte[0]);
                }
            }
        }
        xs1 xs1Var = this.contentType;
        if (xs1Var != null) {
            if (contentTypeOverridingRequestBody != null) {
                contentTypeOverridingRequestBody = new ContentTypeOverridingRequestBody(contentTypeOverridingRequestBody, xs1Var);
            } else {
                this.headersBuilder.a("Content-Type", xs1Var.toString());
            }
        }
        bt1.a aVar4 = this.requestBuilder;
        aVar4.a(vs1VarB);
        aVar4.a(this.headersBuilder.a());
        aVar4.a(this.method, contentTypeOverridingRequestBody);
        return aVar4;
    }

    public void setBody(ct1 ct1Var) {
        this.body = ct1Var;
    }

    public void setRelativeUrl(Object obj) {
        this.relativeUrl = obj.toString();
    }

    public void addPart(ys1.b bVar) {
        this.multipartBuilder.a(bVar);
    }

    public static void canonicalizeForPath(Buffer buffer, String str, int i, int i2, boolean z) {
        Buffer buffer2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt >= 32 && iCodePointAt < 127 && PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) == -1 && (z || (iCodePointAt != 47 && iCodePointAt != 37))) {
                    buffer.writeUtf8CodePoint(iCodePointAt);
                } else {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(iCodePointAt);
                    while (!buffer2.exhausted()) {
                        int i3 = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        buffer.writeByte((int) HEX_DIGITS[(i3 >> 4) & 15]);
                        buffer.writeByte((int) HEX_DIGITS[i3 & 15]);
                    }
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }
}
