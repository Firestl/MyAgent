package supwisdom;

import android.net.Uri;
import java.io.IOException;

/* JADX INFO: compiled from: DataSource.java */
/* JADX INFO: loaded from: classes.dex */
public interface s70 {

    /* JADX INFO: compiled from: DataSource.java */
    public interface a {
        s70 a();
    }

    int a(byte[] bArr, int i, int i2) throws IOException;

    long a(u70 u70Var) throws IOException;

    void a() throws IOException;

    Uri b();
}
