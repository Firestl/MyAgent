package supwisdom;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: DecoderInputBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public class y10 extends u10 {
    public final v10 b = new v10();
    public ByteBuffer c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9836e;

    public y10(int i) {
        this.f9836e = i;
    }

    public static y10 i() {
        return new y10(0);
    }

    @Override // supwisdom.u10
    public void a() {
        super.a();
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    public void f(int i) throws IllegalStateException {
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer == null) {
            this.c = g(i);
            return;
        }
        int iCapacity = byteBuffer.capacity();
        int iPosition = this.c.position();
        int i2 = i + iPosition;
        if (iCapacity >= i2) {
            return;
        }
        ByteBuffer byteBufferG = g(i2);
        if (iPosition > 0) {
            this.c.position(0);
            this.c.limit(iPosition);
            byteBufferG.put(this.c);
        }
        this.c = byteBufferG;
    }

    public final boolean g() {
        return e(1073741824);
    }

    public final void h() {
        this.c.flip();
    }

    public final ByteBuffer g(int i) {
        int i2 = this.f9836e;
        if (i2 == 1) {
            return ByteBuffer.allocate(i);
        }
        if (i2 == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        ByteBuffer byteBuffer = this.c;
        throw new IllegalStateException("Buffer too small (" + (byteBuffer == null ? 0 : byteBuffer.capacity()) + " < " + i + ")");
    }

    public final boolean f() {
        return this.c == null && this.f9836e == 0;
    }
}
