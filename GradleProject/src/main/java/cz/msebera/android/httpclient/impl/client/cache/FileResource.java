package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileResource implements Resource {
    public static final long serialVersionUID = 4132244415919043397L;
    public volatile boolean disposed = false;
    public final File file;

    public FileResource(File file) {
        this.file = file;
    }

    @Override // cz.msebera.android.httpclient.client.cache.Resource
    public synchronized void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.file.delete();
    }

    public synchronized File getFile() {
        return this.file;
    }

    @Override // cz.msebera.android.httpclient.client.cache.Resource
    public synchronized InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // cz.msebera.android.httpclient.client.cache.Resource
    public synchronized long length() {
        return this.file.length();
    }
}
