package supwisdom;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.http2.ErrorCode;
import okio.BufferedSource;

/* JADX INFO: compiled from: PushObserver.java */
/* JADX INFO: loaded from: classes3.dex */
public interface ru1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ru1 f9093a = new a();

    /* JADX INFO: compiled from: PushObserver.java */
    public class a implements ru1 {
        @Override // supwisdom.ru1
        public void a(int i, ErrorCode errorCode) {
        }

        @Override // supwisdom.ru1
        public boolean a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
            bufferedSource.skip(i2);
            return true;
        }

        @Override // supwisdom.ru1
        public boolean onHeaders(int i, List<iu1> list, boolean z) {
            return true;
        }

        @Override // supwisdom.ru1
        public boolean onRequest(int i, List<iu1> list) {
            return true;
        }
    }

    void a(int i, ErrorCode errorCode);

    boolean a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<iu1> list, boolean z);

    boolean onRequest(int i, List<iu1> list);
}
