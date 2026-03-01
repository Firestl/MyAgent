package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SyncSampleBox.java */
/* JADX INFO: loaded from: classes.dex */
public class it extends ys0 {
    public long[] g;

    public it() {
        super("stss");
    }

    @Override // supwisdom.ws0
    public long a() {
        return (this.g.length * 4) + 8;
    }

    public String toString() {
        return "SyncSampleBox[entryCount=" + this.g.length + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g.length);
        for (long j : this.g) {
            ks.a(byteBuffer, j);
        }
    }

    public void a(long[] jArr) {
        this.g = jArr;
    }
}
