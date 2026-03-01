package com.getui.gtc.base.http;

import com.getui.gtc.base.http.Headers;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class MultipartBody extends RequestBody {
    public final String boundary;
    public long contentLength = -1;
    public final MediaType contentType;
    public final MediaType originalType;
    public final List<Part> parts;
    public static final MediaType MIXED = MediaType.get("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.get("multipart/alternative");
    public static final MediaType DIGEST = MediaType.get("multipart/digest");
    public static final MediaType PARALLEL = MediaType.get("multipart/parallel");
    public static final MediaType FORM = MediaType.get("multipart/form-data");
    public static final byte[] COLONSPACE = {58, 32};
    public static final byte[] CRLF = {13, 10};
    public static final byte[] DASHDASH = {45, 45};

    public static final class Builder {
        public final String boundary;
        public final List<Part> parts;
        public MediaType type;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String str) {
            this.type = MultipartBody.FORM;
            this.parts = new ArrayList();
            this.boundary = str;
        }

        public final Builder addFormDataPart(String str, String str2) {
            return addPart(Part.createFormData(str, str2));
        }

        public final Builder addFormDataPart(String str, String str2, RequestBody requestBody) {
            return addPart(Part.createFormData(str, str2, requestBody));
        }

        public final Builder addPart(Headers headers, RequestBody requestBody) {
            return addPart(Part.create(headers, requestBody));
        }

        public final Builder addPart(Part part) {
            if (part == null) {
                throw new NullPointerException("part == null");
            }
            this.parts.add(part);
            return this;
        }

        public final Builder addPart(RequestBody requestBody) {
            return addPart(Part.create(requestBody));
        }

        public final MultipartBody build() {
            if (this.parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MultipartBody(this.boundary, this.type, this.parts);
        }

        public final Builder setType(MediaType mediaType) {
            if (mediaType == null) {
                throw new NullPointerException("type == null");
            }
            if (!mediaType.type().equals("multipart")) {
                throw new IllegalArgumentException("multipart != ".concat(String.valueOf(mediaType)));
            }
            this.type = mediaType;
            return this;
        }
    }

    public static final class Part {
        public final RequestBody body;
        public final Headers headers;

        public Part(Headers headers, RequestBody requestBody) {
            this.headers = headers;
            this.body = requestBody;
        }

        public static Part create(Headers headers, RequestBody requestBody) {
            if (requestBody == null) {
                throw new NullPointerException("body == null");
            }
            if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (headers == null || headers.get(HttpHeaders.HEAD_KEY_CONTENT_LENGTH) == null) {
                return new Part(headers, requestBody);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static Part create(RequestBody requestBody) {
            return create(null, requestBody);
        }

        public static Part createFormData(String str, String str2) {
            return createFormData(str, null, RequestBody.create((MediaType) null, str2));
        }

        public static Part createFormData(String str, String str2, RequestBody requestBody) {
            return createFormData(str, str2, requestBody, null);
        }

        public static Part createFormData(String str, String str2, RequestBody requestBody, String str3) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            MultipartBody.appendQuotedString(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                MultipartBody.appendQuotedString(sb, str2);
            }
            Headers.Builder builderAddUnsafeNonAscii = new Headers.Builder().addUnsafeNonAscii(HttpHeaders.HEAD_KEY_CONTENT_DISPOSITION, sb.toString());
            if (str3 != null) {
                builderAddUnsafeNonAscii.add("Content-Transfer-Encoding", str3);
            }
            return create(builderAddUnsafeNonAscii.build(), requestBody);
        }

        public final RequestBody body() {
            return this.body;
        }

        public final Headers headers() {
            return this.headers;
        }
    }

    public MultipartBody(String str, MediaType mediaType, List<Part> list) {
        this.boundary = str;
        this.originalType = mediaType;
        this.contentType = MediaType.get(mediaType + "; boundary=" + str);
        this.parts = Collections.unmodifiableList(new ArrayList(list));
    }

    public static void appendQuotedString(StringBuilder sb, String str) {
        String str2;
        sb.append(Operators.QUOTE);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n') {
                str2 = "%0A";
            } else if (cCharAt == '\r') {
                str2 = "%0D";
            } else if (cCharAt != '\"') {
                sb.append(cCharAt);
            } else {
                str2 = "%22";
            }
            sb.append(str2);
        }
        sb.append(Operators.QUOTE);
    }

    private long writeOrCountBytes(OutputStream outputStream, boolean z) throws IOException {
        BufferedSink bufferedSink;
        BufferedSink bufferedSink2;
        if (z) {
            bufferedSink = new BufferedSink(new ByteArrayOutputStream());
            bufferedSink2 = bufferedSink;
        } else {
            bufferedSink = new BufferedSink(outputStream);
            bufferedSink2 = bufferedSink;
            bufferedSink = null;
        }
        int size = this.parts.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Part part = this.parts.get(i);
            Headers headers = part.headers;
            RequestBody requestBody = part.body;
            bufferedSink2.write(DASHDASH);
            bufferedSink2.write(this.boundary);
            bufferedSink2.write(CRLF);
            if (headers != null) {
                int size2 = headers.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    bufferedSink2.writeUtf8(headers.name(i2)).write(COLONSPACE).writeUtf8(headers.value(i2)).write(CRLF);
                }
            }
            MediaType mediaTypeContentType = requestBody.contentType();
            if (mediaTypeContentType != null) {
                bufferedSink2.writeUtf8("Content-Type: ").writeUtf8(mediaTypeContentType.toString()).write(CRLF);
            }
            long jContentLength = requestBody.contentLength();
            if (jContentLength != -1) {
                bufferedSink2.writeUtf8("Content-Length: ").writeLong(jContentLength).write(CRLF);
            } else if (z) {
                bufferedSink.close();
                return -1L;
            }
            bufferedSink2.write(CRLF);
            if (z) {
                j += jContentLength;
            } else {
                requestBody.writeTo(outputStream);
            }
            bufferedSink2.write(CRLF);
        }
        bufferedSink2.write(DASHDASH);
        bufferedSink2.write(this.boundary);
        bufferedSink2.write(DASHDASH);
        bufferedSink2.write(CRLF);
        if (!z) {
            return j;
        }
        long size3 = j + bufferedSink.size();
        bufferedSink.close();
        return size3;
    }

    public final String boundary() {
        return this.boundary;
    }

    @Override // com.getui.gtc.base.http.RequestBody
    public final long contentLength() {
        long j = this.contentLength;
        if (j != -1) {
            return j;
        }
        try {
            long jWriteOrCountBytes = writeOrCountBytes(null, true);
            this.contentLength = jWriteOrCountBytes;
            return jWriteOrCountBytes;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    @Override // com.getui.gtc.base.http.RequestBody
    public final MediaType contentType() {
        return this.contentType;
    }

    public final Part part(int i) {
        return this.parts.get(i);
    }

    public final List<Part> parts() {
        return this.parts;
    }

    public final int size() {
        return this.parts.size();
    }

    public final MediaType type() {
        return this.originalType;
    }

    @Override // com.getui.gtc.base.http.RequestBody
    public final void writeTo(OutputStream outputStream) throws IOException {
        writeOrCountBytes(outputStream, false);
    }
}
