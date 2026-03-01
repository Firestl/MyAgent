package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ChunkStreamInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class u00 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f9346e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RtmpHeader f9347a;
    public RtmpHeader b;
    public long c = System.nanoTime() / 1000000;
    public ByteArrayOutputStream d = new ByteArrayOutputStream(131072);

    public static void g() {
        f9346e = System.nanoTime() / 1000000;
    }

    public void a(RtmpHeader rtmpHeader) {
        this.f9347a = rtmpHeader;
    }

    public RtmpHeader b() {
        return this.b;
    }

    public ByteArrayInputStream c() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.d.toByteArray());
        this.d.reset();
        return byteArrayInputStream;
    }

    public long d() {
        return (System.nanoTime() / 1000000) - f9346e;
    }

    public long e() {
        long jNanoTime = System.nanoTime() / 1000000;
        long j = jNanoTime - this.c;
        this.c = jNanoTime;
        return j;
    }

    public RtmpHeader f() {
        return this.f9347a;
    }

    public boolean a(RtmpHeader.MessageType messageType) {
        RtmpHeader rtmpHeader = this.b;
        return rtmpHeader != null && rtmpHeader.c() == messageType;
    }

    public void b(RtmpHeader rtmpHeader) {
        this.b = rtmpHeader;
    }

    public boolean a(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[Math.min(this.f9347a.d() - this.d.size(), i)];
        j00.a(inputStream, bArr);
        this.d.write(bArr);
        return this.d.size() == this.f9347a.d();
    }

    public void a() {
        this.d.reset();
    }
}
