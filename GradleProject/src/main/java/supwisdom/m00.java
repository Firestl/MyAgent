package supwisdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: AmfData.java */
/* JADX INFO: loaded from: classes.dex */
public interface m00 {
    void a(InputStream inputStream) throws IOException;

    void a(OutputStream outputStream) throws IOException;

    int getSize();
}
