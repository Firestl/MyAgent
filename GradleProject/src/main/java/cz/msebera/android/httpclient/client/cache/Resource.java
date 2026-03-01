package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public interface Resource extends Serializable {
    void dispose();

    InputStream getInputStream() throws IOException;

    long length();
}
