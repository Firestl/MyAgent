package com.getui.gtc.base.http;

import io.dcloud.common.util.net.NetWork;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
public class FormBody extends RequestBody {
    public static final String CHARSET_NAME = "UTF-8";
    public static final MediaType CONTENT_TYPE = MediaType.get(NetWork.CONTENT_TYPE_UPLOAD);
    public final ByteArrayOutputStream content = new ByteArrayOutputStream();

    public void addField(String str, String str2) {
        addField(str, true, str2, true);
    }

    public void addField(String str, boolean z, String str2, boolean z2) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        if (str2 == null) {
            throw new NullPointerException("value");
        }
        if (this.content.size() > 0) {
            this.content.write(38);
        }
        if (z) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        if (z2) {
            str2 = URLEncoder.encode(str2, "UTF-8");
        }
        this.content.write(str.getBytes("UTF-8"));
        this.content.write(61);
        this.content.write(str2.getBytes("UTF-8"));
    }

    @Override // com.getui.gtc.base.http.RequestBody
    public long contentLength() {
        return this.content.size();
    }

    @Override // com.getui.gtc.base.http.RequestBody
    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    @Override // com.getui.gtc.base.http.RequestBody
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.content.toByteArray());
    }
}
