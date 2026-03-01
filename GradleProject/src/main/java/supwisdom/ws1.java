package supwisdom;

import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Interceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public interface ws1 {

    /* JADX INFO: compiled from: Interceptor.java */
    public interface a {
        dt1 a(bt1 bt1Var) throws IOException;

        int connectTimeoutMillis();

        @Nullable
        ks1 connection();

        int readTimeoutMillis();

        bt1 request();

        int writeTimeoutMillis();
    }

    dt1 intercept(a aVar) throws IOException;
}
