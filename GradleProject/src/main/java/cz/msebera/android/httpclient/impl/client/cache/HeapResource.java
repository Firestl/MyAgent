package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class HeapResource implements Resource {
    public static final long serialVersionUID = -2078599905620463394L;
    public final byte[] b;

    public HeapResource(byte[] bArr) {
        this.b = bArr;
    }

    @Override // cz.msebera.android.httpclient.client.cache.Resource
    public void dispose() {
    }

    public byte[] getByteArray() {
        return this.b;
    }

    @Override // cz.msebera.android.httpclient.client.cache.Resource
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.b);
    }

    @Override // cz.msebera.android.httpclient.client.cache.Resource
    public long length() {
        return this.b.length;
    }
}
