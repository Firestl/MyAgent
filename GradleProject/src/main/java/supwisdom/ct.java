package supwisdom;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SampleDescriptionBox.java */
/* JADX INFO: loaded from: classes.dex */
public class ct extends zs0 {
    public ct() {
        super("stsd");
    }

    @Override // supwisdom.zs0, supwisdom.ws0
    public long a() {
        return super.a() + 4;
    }

    @Override // supwisdom.zs0, supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.g.size());
        d(byteBuffer);
    }
}
