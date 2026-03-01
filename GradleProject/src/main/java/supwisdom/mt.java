package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: VideoMediaHeaderBox.java */
/* JADX INFO: loaded from: classes.dex */
public class mt extends ns {
    public int g;
    public int[] h;

    public mt() {
        super("vmhd");
        this.g = 0;
        this.h = new int[]{0, 0, 0};
        a(1);
    }

    @Override // supwisdom.ws0
    public long a() {
        return 12L;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g);
        for (int i : this.h) {
            ks.a(byteBuffer, i);
        }
    }

    public int g() {
        return this.g;
    }

    public int[] h() {
        return this.h;
    }

    public String toString() {
        return "VideoMediaHeaderBox[graphicsmode=" + g() + ";opcolor0=" + h()[0] + ";opcolor1=" + h()[1] + ";opcolor2=" + h()[2] + Operators.ARRAY_END_STR;
    }
}
