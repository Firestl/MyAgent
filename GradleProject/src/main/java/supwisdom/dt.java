package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SampleSizeBox.java */
/* JADX INFO: loaded from: classes.dex */
public class dt extends ys0 {
    public long g;
    public long[] h;
    public int i;

    public dt() {
        super("stsz");
        this.h = new long[0];
    }

    public void a(long[] jArr) {
        this.h = jArr;
    }

    public long g() {
        return this.g > 0 ? this.i : this.h.length;
    }

    public long h() {
        return this.g;
    }

    public String toString() {
        return "SampleSizeBox[sampleSize=" + h() + ";sampleCount=" + g() + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public long a() {
        return (this.g == 0 ? this.h.length * 4 : 0) + 12;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g);
        if (this.g == 0) {
            ks.a(byteBuffer, this.h.length);
            for (long j : this.h) {
                ks.a(byteBuffer, j);
            }
            return;
        }
        ks.a(byteBuffer, this.i);
    }
}
