package supwisdom;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: StaticChunkOffsetBox.java */
/* JADX INFO: loaded from: classes.dex */
public class ht extends ps {
    public long[] g;

    public ht() {
        super("stco");
        this.g = new long[0];
    }

    @Override // supwisdom.ws0
    public long a() {
        return (this.g.length * 4) + 8;
    }

    @Override // supwisdom.ps
    public long[] g() {
        return this.g;
    }

    public void a(long[] jArr) {
        this.g = jArr;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g.length);
        for (long j : this.g) {
            ks.a(byteBuffer, j);
        }
    }
}
