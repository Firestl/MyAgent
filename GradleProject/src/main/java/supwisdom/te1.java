package supwisdom;

import java.io.IOException;
import okio.BufferedSink;

/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class te1 {

    /* JADX INFO: compiled from: RequestBody.java */
    public static class a extends te1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ qe1 f9280a;
        public final /* synthetic */ int b;
        public final /* synthetic */ byte[] c;
        public final /* synthetic */ int d;

        public a(qe1 qe1Var, int i, byte[] bArr, int i2) {
            this.f9280a = qe1Var;
            this.b = i;
            this.c = bArr;
            this.d = i2;
        }

        @Override // supwisdom.te1
        public long a() {
            return this.b;
        }

        @Override // supwisdom.te1
        public qe1 b() {
            return this.f9280a;
        }

        @Override // supwisdom.te1
        public void a(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(this.c, this.d, this.b);
        }
    }

    public static te1 a(qe1 qe1Var, byte[] bArr) {
        return a(qe1Var, bArr, 0, bArr.length);
    }

    public abstract long a() throws IOException;

    public abstract void a(BufferedSink bufferedSink) throws IOException;

    public abstract qe1 b();

    public static te1 a(qe1 qe1Var, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        gf1.a(bArr.length, i, i2);
        return new a(qe1Var, i2, bArr, i);
    }
}
