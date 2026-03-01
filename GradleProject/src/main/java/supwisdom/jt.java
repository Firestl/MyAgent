package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: TimeToSampleBox.java */
/* JADX INFO: loaded from: classes.dex */
public class jt extends ys0 {
    public List<a> g;

    /* JADX INFO: compiled from: TimeToSampleBox.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f8085a;
        public long b;

        public a(long j, long j2) {
            this.f8085a = j;
            this.b = j2;
        }

        public long a() {
            return this.f8085a;
        }

        public long b() {
            return this.b;
        }

        public String toString() {
            return "Entry{count=" + this.f8085a + ", delta=" + this.b + Operators.BLOCK_END;
        }

        public void a(long j) {
            this.f8085a = j;
        }
    }

    public jt() {
        super("stts");
        this.g = Collections.emptyList();
    }

    @Override // supwisdom.ws0
    public long a() {
        return (this.g.size() * 8) + 8;
    }

    public String toString() {
        return "TimeToSampleBox[entryCount=" + this.g.size() + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g.size());
        for (a aVar : this.g) {
            ks.a(byteBuffer, aVar.a());
            ks.a(byteBuffer, aVar.b());
        }
    }

    public void a(List<a> list) {
        this.g = list;
    }
}
