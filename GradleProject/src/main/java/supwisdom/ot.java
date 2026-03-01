package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* JADX INFO: compiled from: AudioSampleEntry.java */
/* JADX INFO: loaded from: classes.dex */
public class ot extends pt implements qs {
    public int g;
    public int h;
    public long i;
    public int j;
    public int k;
    public int l;
    public long m;
    public long n;
    public long o;
    public long p;
    public int q;
    public long r;
    public byte[] s;

    public ot(String str) {
        super(str);
    }

    public void a(long j) {
        this.i = j;
    }

    public void b(int i) {
        this.g = i;
    }

    public void c(int i) {
        this.h = i;
    }

    public int f() {
        return this.g;
    }

    public long g() {
        return this.i;
    }

    public String toString() {
        return "AudioSampleEntry{bytesPerSample=" + this.p + ", bytesPerFrame=" + this.o + ", bytesPerPacket=" + this.n + ", samplesPerPacket=" + this.m + ", packetSize=" + this.l + ", compressionId=" + this.k + ", soundVersion=" + this.j + ", sampleRate=" + this.i + ", sampleSize=" + this.h + ", channelCount=" + this.g + ", boxes=" + e() + Operators.BLOCK_END;
    }

    @Override // supwisdom.ws0
    public long a() {
        long size = (this.j > 0 ? 16L : 0L) + 28 + (this.j == 2 ? 20L : 0L);
        Iterator<os> it = this.f.iterator();
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        d(byteBuffer);
        ks.a(byteBuffer, this.j);
        ks.a(byteBuffer, this.q);
        ks.a(byteBuffer, this.r);
        ks.a(byteBuffer, this.g);
        ks.a(byteBuffer, this.h);
        ks.a(byteBuffer, this.k);
        ks.a(byteBuffer, this.l);
        if (this.f9654a.equals("mlpa")) {
            ks.a(byteBuffer, g());
        } else {
            ks.a(byteBuffer, g() << 16);
        }
        if (this.j > 0) {
            ks.a(byteBuffer, this.m);
            ks.a(byteBuffer, this.n);
            ks.a(byteBuffer, this.o);
            ks.a(byteBuffer, this.p);
        }
        if (this.j == 2) {
            byteBuffer.put(this.s);
        }
        c(byteBuffer);
    }
}
