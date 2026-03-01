package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: Command.java */
/* JADX INFO: loaded from: classes.dex */
public class b10 extends h10 {
    public String c;
    public int d;

    public b10(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    @Override // supwisdom.f10
    public void a(InputStream inputStream) throws IOException {
        this.c = s00.a(inputStream, false);
        this.d = (int) q00.b(inputStream);
        a(inputStream, s00.a(this.c, false) + 9);
    }

    @Override // supwisdom.f10
    public byte[] a() {
        return null;
    }

    @Override // supwisdom.f10
    public int c() {
        return 0;
    }

    public String e() {
        return this.c;
    }

    public int f() {
        return this.d;
    }

    public String toString() {
        return "RTMP Command (command: " + this.c + ", transaction ID: " + this.d + ")";
    }

    public b10(String str, int i, u00 u00Var) {
        super(new RtmpHeader(u00Var.a(RtmpHeader.MessageType.COMMAND_AMF0) ? RtmpHeader.ChunkType.TYPE_1_RELATIVE_LARGE : RtmpHeader.ChunkType.TYPE_0_FULL, 3, RtmpHeader.MessageType.COMMAND_AMF0));
        this.c = str;
        this.d = i;
    }

    public b10(String str, int i) {
        super(new RtmpHeader(RtmpHeader.ChunkType.TYPE_0_FULL, 3, RtmpHeader.MessageType.COMMAND_AMF0));
        this.c = str;
        this.d = i;
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
        s00.a(outputStream, this.c, false);
        q00.a(outputStream, this.d);
        b(outputStream);
    }
}
