package supwisdom;

import com.squareup.okhttp.internal.spdy.ErrorCode;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import okio.Buffer;

/* JADX INFO: compiled from: FrameWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public interface bg1 extends Closeable {
    void a(int i, ErrorCode errorCode) throws IOException;

    void a(int i, ErrorCode errorCode, byte[] bArr) throws IOException;

    void a(kg1 kg1Var) throws IOException;

    void a(boolean z, int i, Buffer buffer, int i2) throws IOException;

    void a(boolean z, boolean z2, int i, int i2, List<cg1> list) throws IOException;

    void b(kg1 kg1Var) throws IOException;

    void connectionPreface() throws IOException;

    void flush() throws IOException;

    int maxDataLength();

    void ping(boolean z, int i, int i2) throws IOException;

    void pushPromise(int i, int i2, List<cg1> list) throws IOException;

    void windowUpdate(int i, long j) throws IOException;
}
