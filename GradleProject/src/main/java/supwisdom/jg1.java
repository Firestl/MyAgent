package supwisdom;

import com.squareup.okhttp.internal.spdy.ErrorCode;
import java.io.IOException;
import java.util.List;
import okio.BufferedSource;

/* JADX INFO: compiled from: PushObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public interface jg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final jg1 f8059a = new a();

    /* JADX INFO: compiled from: PushObserver.java */
    public static class a implements jg1 {
        @Override // supwisdom.jg1
        public void a(int i, ErrorCode errorCode) {
        }

        @Override // supwisdom.jg1
        public boolean a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
            bufferedSource.skip(i2);
            return true;
        }

        @Override // supwisdom.jg1
        public boolean onHeaders(int i, List<cg1> list, boolean z) {
            return true;
        }

        @Override // supwisdom.jg1
        public boolean onRequest(int i, List<cg1> list) {
            return true;
        }
    }

    void a(int i, ErrorCode errorCode);

    boolean a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<cg1> list, boolean z);

    boolean onRequest(int i, List<cg1> list);
}
