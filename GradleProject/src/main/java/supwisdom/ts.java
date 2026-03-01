package supwisdom;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: DataReferenceBox.java */
/* JADX INFO: loaded from: classes.dex */
public class ts extends zs0 {
    public ts() {
        super("dref");
    }

    @Override // supwisdom.zs0, supwisdom.ws0
    public long a() {
        return super.a() + 4;
    }

    @Override // supwisdom.zs0, supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, g().size());
        d(byteBuffer);
    }
}
