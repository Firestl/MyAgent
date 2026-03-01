package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: RtmpPacket.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f10 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RtmpHeader f7557a;

    public f10(RtmpHeader rtmpHeader) {
        this.f7557a = rtmpHeader;
    }

    public abstract void a(InputStream inputStream) throws IOException;

    public abstract void a(OutputStream outputStream) throws IOException;

    public void a(OutputStream outputStream, int i, u00 u00Var) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(byteArrayOutputStream);
        boolean z = this instanceof c10;
        byte[] bArrA = z ? a() : byteArrayOutputStream.toByteArray();
        int iC = z ? c() : bArrA.length;
        this.f7557a.d(iC);
        this.f7557a.a(outputStream, RtmpHeader.ChunkType.TYPE_0_FULL, u00Var);
        int i2 = 0;
        while (iC > i) {
            outputStream.write(bArrA, i2, i);
            iC -= i;
            i2 += i;
            this.f7557a.a(outputStream, RtmpHeader.ChunkType.TYPE_3_RELATIVE_SINGLE_BYTE, u00Var);
        }
        outputStream.write(bArrA, i2, iC);
    }

    public abstract byte[] a();

    public RtmpHeader b() {
        return this.f7557a;
    }

    public abstract int c();
}
