package supwisdom;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

/* JADX INFO: compiled from: AbstractDescriptorBox.java */
/* JADX INFO: loaded from: classes.dex */
public class bt0 extends ys0 {
    public ByteBuffer g;

    static {
        Logger.getLogger(bt0.class.getName());
    }

    public bt0(String str) {
        super(str);
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        this.g.rewind();
        byteBuffer.put(this.g);
    }

    public void a(et0 et0Var) {
    }

    public void d(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
    }

    @Override // supwisdom.ws0
    public long a() {
        return this.g.limit() + 4;
    }
}
