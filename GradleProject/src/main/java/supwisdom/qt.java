package supwisdom;

import java.nio.ByteBuffer;
import java.util.Iterator;

/* JADX INFO: compiled from: VisualSampleEntry.java */
/* JADX INFO: loaded from: classes.dex */
public class qt extends pt implements qs {
    public int g;
    public int h;
    public double i;
    public double j;
    public int k;
    public String l;
    public int m;
    public long[] n;

    public qt(String str) {
        super(str);
        this.i = 72.0d;
        this.j = 72.0d;
        this.k = 1;
        this.m = 24;
        this.n = new long[3];
    }

    public void a(String str) {
        this.l = str;
    }

    public void b(double d) {
        this.j = d;
    }

    public void c(int i) {
        this.k = i;
    }

    public void d(int i) {
        this.h = i;
    }

    public void e(int i) {
        this.g = i;
    }

    public String f() {
        return this.l;
    }

    public int g() {
        return this.m;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.h;
    }

    public double j() {
        return this.i;
    }

    public double k() {
        return this.j;
    }

    public int l() {
        return this.g;
    }

    public void a(double d) {
        this.i = d;
    }

    public void b(int i) {
        this.m = i;
    }

    @Override // supwisdom.ws0
    public long a() {
        Iterator<os> it = this.f.iterator();
        long size = 78;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        d(byteBuffer);
        ks.a(byteBuffer, 0);
        ks.a(byteBuffer, 0);
        ks.a(byteBuffer, this.n[0]);
        ks.a(byteBuffer, this.n[1]);
        ks.a(byteBuffer, this.n[2]);
        ks.a(byteBuffer, l());
        ks.a(byteBuffer, i());
        ks.b(byteBuffer, j());
        ks.b(byteBuffer, k());
        ks.a(byteBuffer, 0L);
        ks.a(byteBuffer, h());
        ks.c(byteBuffer, ms.b(f()));
        byteBuffer.put(ms.a(f()));
        int iB = ms.b(f());
        while (iB < 31) {
            iB++;
            byteBuffer.put((byte) 0);
        }
        ks.a(byteBuffer, g());
        ks.a(byteBuffer, 65535);
        c(byteBuffer);
    }
}
