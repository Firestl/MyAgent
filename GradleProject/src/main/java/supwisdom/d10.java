package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: Data.java */
/* JADX INFO: loaded from: classes.dex */
public class d10 extends h10 {
    public String c;

    public d10(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    @Override // supwisdom.f10
    public void a(InputStream inputStream) throws IOException {
        String strA = s00.a(inputStream, false);
        this.c = strA;
        a(inputStream, s00.a(strA, false));
    }

    @Override // supwisdom.f10
    public byte[] a() {
        return null;
    }

    @Override // supwisdom.f10
    public int c() {
        return 0;
    }

    public d10(String str) {
        super(new RtmpHeader(RtmpHeader.ChunkType.TYPE_0_FULL, 3, RtmpHeader.MessageType.DATA_AMF0));
        this.c = str;
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
        s00.a(outputStream, this.c, false);
        b(outputStream);
    }
}
