package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: ContentData.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c10 extends f10 {
    public byte[] b;
    public int c;

    public c10(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
    }

    public void a(byte[] bArr, int i) {
        this.b = bArr;
        this.c = i;
    }

    @Override // supwisdom.f10
    public int c() {
        return this.c;
    }

    @Override // supwisdom.f10
    public void a(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[this.f7557a.d()];
        this.b = bArr;
        j00.a(inputStream, bArr);
    }

    @Override // supwisdom.f10
    public byte[] a() {
        return this.b;
    }
}
