package supwisdom;

import com.squareup.okhttp.internal.spdy.ErrorCode;
import java.io.IOException;

/* JADX INFO: compiled from: IncomingStreamHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public interface gg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final gg1 f7733a = new a();

    /* JADX INFO: compiled from: IncomingStreamHandler.java */
    public static class a implements gg1 {
        @Override // supwisdom.gg1
        public void a(ng1 ng1Var) throws IOException {
            ng1Var.a(ErrorCode.REFUSED_STREAM);
        }
    }

    void a(ng1 ng1Var) throws IOException;
}
