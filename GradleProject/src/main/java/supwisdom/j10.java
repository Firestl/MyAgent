package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: WindowAckSize.java */
/* JADX INFO: loaded from: classes.dex */
public class j10 extends f10 {
    public int b;

    public j10(RtmpHeader rtmpHeader) {
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

    public String toString() {
        return "RTMP Window Acknowledgment Size";
    }

    public j10(int i, u00 u00Var) {
        super(new RtmpHeader(u00Var.a(RtmpHeader.MessageType.WINDOW_ACKNOWLEDGEMENT_SIZE) ? RtmpHeader.ChunkType.TYPE_2_RELATIVE_TIMESTAMP_ONLY : RtmpHeader.ChunkType.TYPE_0_FULL, 2, RtmpHeader.MessageType.WINDOW_ACKNOWLEDGEMENT_SIZE));
        this.b = i;
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
        j00.c(outputStream, this.b);
    }
}
