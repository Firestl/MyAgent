package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: Abort.java */
/* JADX INFO: loaded from: classes.dex */
public class y00 extends f10 {
    public int b;

    public y00(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    @Override // supwisdom.f10
    public void a(InputStream inputStream) throws IOException {
        this.b = j00.d(inputStream);
    }

    @Override // supwisdom.f10
    public byte[] a() {
        return null;
    }

    @Override // supwisdom.f10
    public int c() {
        return 0;
    }

    public int d() {
        return this.b;
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
        j00.c(outputStream, this.b);
    }
}
