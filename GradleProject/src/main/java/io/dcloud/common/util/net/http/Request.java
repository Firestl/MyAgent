package io.dcloud.common.util.net.http;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class Request {
    public InputStream input;
    public String mData;
    public String uri;

    public Request(InputStream inputStream) {
        this.input = inputStream;
    }

    private String parseUri(String str) {
        int i;
        int iIndexOf;
        int iIndexOf2 = str.indexOf(32);
        if (iIndexOf2 == -1 || (iIndexOf = str.indexOf(32, (i = iIndexOf2 + 1))) <= iIndexOf2) {
            return null;
        }
        return str.substring(i, iIndexOf);
    }

    public String getData() {
        return this.mData;
    }

    public String getUri() {
        return this.uri;
    }

    public void parse() {
        int i;
        StringBuffer stringBuffer = new StringBuffer(2048);
        byte[] bArr = new byte[2048];
        try {
            i = this.input.read(bArr);
        } catch (IOException e2) {
            e2.printStackTrace();
            i = -1;
        }
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append((char) bArr[i2]);
        }
        String string = stringBuffer.toString();
        this.mData = string;
        String uri = parseUri(string);
        this.uri = uri;
        this.uri = (uri == null || !uri.startsWith("/")) ? this.uri : this.uri.substring(1);
    }
}
