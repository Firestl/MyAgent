package supwisdom;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import okio.BufferedSource;

/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ve1 implements Closeable {
    public final InputStream a() throws IOException {
        return c().inputStream();
    }

    public abstract long b() throws IOException;

    public abstract BufferedSource c() throws IOException;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        c().close();
    }
}
