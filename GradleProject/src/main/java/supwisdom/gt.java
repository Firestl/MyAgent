package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SoundMediaHeaderBox.java */
/* JADX INFO: loaded from: classes.dex */
public class gt extends ns {
    public float g;

    public gt() {
        super("smhd");
    }

    @Override // supwisdom.ws0
    public long a() {
        return 8L;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.c(byteBuffer, this.g);
        ks.a(byteBuffer, 0);
    }

    public float g() {
        return this.g;
    }

    public String toString() {
        return "SoundMediaHeaderBox[balance=" + g() + Operators.ARRAY_END_STR;
    }
}
