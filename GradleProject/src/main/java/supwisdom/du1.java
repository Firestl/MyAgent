package supwisdom;

import javax.annotation.Nullable;
import okio.BufferedSource;

/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes3.dex */
public final class du1 extends et1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public final String f7384a;
    public final long b;
    public final BufferedSource c;

    public du1(@Nullable String str, long j, BufferedSource bufferedSource) {
        this.f7384a = str;
        this.b = j;
        this.c = bufferedSource;
    }

    @Override // supwisdom.et1
    public long contentLength() {
        return this.b;
    }

    @Override // supwisdom.et1
    public xs1 contentType() {
        String str = this.f7384a;
        if (str != null) {
            return xs1.b(str);
        }
        return null;
    }

    @Override // supwisdom.et1
    public BufferedSource source() {
        return this.c;
    }
}
