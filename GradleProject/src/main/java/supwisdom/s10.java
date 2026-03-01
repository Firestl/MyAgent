package supwisdom;

import com.google.android.exoplayer2.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* JADX INFO: compiled from: SonicAudioProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public final class s10 implements com.google.android.exoplayer2.a.c {
    public r10 d;
    public ByteBuffer g;
    public ShortBuffer h;
    public ByteBuffer i;
    public long j;
    public long k;
    public boolean l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9108e = 1.0f;
    public float f = 1.0f;
    public int b = -1;
    public int c = -1;

    public s10() {
        ByteBuffer byteBuffer = com.google.android.exoplayer2.a.c.f2259a;
        this.g = byteBuffer;
        this.h = byteBuffer.asShortBuffer();
        this.i = com.google.android.exoplayer2.a.c.f2259a;
    }

    public float a(float f) {
        float fA = x80.a(f, 0.1f, 8.0f);
        this.f9108e = fA;
        return fA;
    }

    public float b(float f) {
        this.f = x80.a(f, 0.1f, 8.0f);
        return f;
    }

    @Override // com.google.android.exoplayer2.a.c
    public int c() {
        return 2;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void d() {
        this.d.a();
        this.l = true;
    }

    @Override // com.google.android.exoplayer2.a.c
    public ByteBuffer e() {
        ByteBuffer byteBuffer = this.i;
        this.i = com.google.android.exoplayer2.a.c.f2259a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.a.c
    public boolean f() {
        r10 r10Var;
        return this.l && ((r10Var = this.d) == null || r10Var.b() == 0);
    }

    @Override // com.google.android.exoplayer2.a.c
    public void g() {
        r10 r10Var = new r10(this.c, this.b);
        this.d = r10Var;
        r10Var.a(this.f9108e);
        this.d.b(this.f);
        this.i = com.google.android.exoplayer2.a.c.f2259a;
        this.j = 0L;
        this.k = 0L;
        this.l = false;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void h() {
        this.d = null;
        ByteBuffer byteBuffer = com.google.android.exoplayer2.a.c.f2259a;
        this.g = byteBuffer;
        this.h = byteBuffer.asShortBuffer();
        this.i = com.google.android.exoplayer2.a.c.f2259a;
        this.b = -1;
        this.c = -1;
        this.j = 0L;
        this.k = 0L;
        this.l = false;
    }

    public long i() {
        return this.j;
    }

    public long j() {
        return this.k;
    }

    @Override // com.google.android.exoplayer2.a.c
    public boolean a(int i, int i2, int i3) throws c.a {
        if (i3 != 2) {
            throw new c.a(i, i2, i3);
        }
        if (this.c == i && this.b == i2) {
            return false;
        }
        this.c = i;
        this.b = i2;
        return true;
    }

    @Override // com.google.android.exoplayer2.a.c
    public int b() {
        return this.b;
    }

    @Override // com.google.android.exoplayer2.a.c
    public boolean a() {
        return Math.abs(this.f9108e - 1.0f) >= 0.01f || Math.abs(this.f - 1.0f) >= 0.01f;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer shortBufferAsShortBuffer = byteBuffer.asShortBuffer();
            int iRemaining = byteBuffer.remaining();
            this.j += (long) iRemaining;
            this.d.a(shortBufferAsShortBuffer);
            byteBuffer.position(byteBuffer.position() + iRemaining);
        }
        int iB = this.d.b() * this.b * 2;
        if (iB > 0) {
            if (this.g.capacity() < iB) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(iB).order(ByteOrder.nativeOrder());
                this.g = byteBufferOrder;
                this.h = byteBufferOrder.asShortBuffer();
            } else {
                this.g.clear();
                this.h.clear();
            }
            this.d.b(this.h);
            this.k += (long) iB;
            this.g.limit(iB);
            this.i = this.g;
        }
    }
}
