package supwisdom;

import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.HeadersMode;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: FrameReader.java */
/* JADX INFO: loaded from: classes2.dex */
public interface ag1 extends Closeable {

    /* JADX INFO: compiled from: FrameReader.java */
    public interface a {
        void a(int i, ErrorCode errorCode);

        void a(int i, ErrorCode errorCode, ByteString byteString);

        void a(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException;

        void a(boolean z, kg1 kg1Var);

        void a(boolean z, boolean z2, int i, int i2, List<cg1> list, HeadersMode headersMode);

        void ackSettings();

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<cg1> list) throws IOException;

        void windowUpdate(int i, long j);
    }

    boolean a(a aVar) throws IOException;

    void m() throws IOException;
}
