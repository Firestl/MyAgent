package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: SampleToChunkBox.java */
/* JADX INFO: loaded from: classes.dex */
public class ft extends ys0 {
    public List<a> g;

    /* JADX INFO: compiled from: SampleToChunkBox.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f7645a;
        public long b;
        public long c;

        public a(long j, long j2, long j3) {
            this.f7645a = j;
            this.b = j2;
            this.c = j3;
        }

        public long a() {
            return this.f7645a;
        }

        public long b() {
            return this.c;
        }

        public long c() {
            return this.b;
        }

        public String toString() {
            return "Entry{firstChunk=" + this.f7645a + ", samplesPerChunk=" + this.b + ", sampleDescriptionIndex=" + this.c + Operators.BLOCK_END;
        }
    }

    public ft() {
        super("stsc");
        this.g = Collections.emptyList();
    }

    public void a(List<a> list) {
        this.g = list;
    }

    public List<a> g() {
        return this.g;
    }

    public String toString() {
        return "SampleToChunkBox[entryCount=" + this.g.size() + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public long a() {
        return (this.g.size() * 12) + 8;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g.size());
        for (a aVar : this.g) {
            ks.a(byteBuffer, aVar.a());
            ks.a(byteBuffer, aVar.c());
            ks.a(byteBuffer, aVar.b());
        }
    }
}
