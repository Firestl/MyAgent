package supwisdom;

import java.io.IOException;
import okio.Timeout;

/* JADX INFO: compiled from: Call.java */
/* JADX INFO: loaded from: classes3.dex */
public interface gs1 extends Cloneable {

    /* JADX INFO: compiled from: Call.java */
    public interface a {
        gs1 a(bt1 bt1Var);
    }

    void a(hs1 hs1Var);

    void cancel();

    dt1 execute() throws IOException;

    boolean isCanceled();

    bt1 request();

    Timeout timeout();
}
