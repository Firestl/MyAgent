package supwisdom;

import com.google.android.exoplayer2.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: compiled from: ChannelMappingAudioProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public final class n10 implements com.google.android.exoplayer2.a.c {
    public int b;
    public int c;
    public int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8466e;
    public int[] f;
    public ByteBuffer g;
    public ByteBuffer h;
    public boolean i;

    public n10() {
        ByteBuffer byteBuffer = com.google.android.exoplayer2.a.c.f2259a;
        this.g = byteBuffer;
        this.h = byteBuffer;
        this.b = -1;
        this.c = -1;
    }

    public void a(int[] iArr) {
        this.d = iArr;
    }

    @Override // com.google.android.exoplayer2.a.c
    public int b() {
        int[] iArr = this.f;
        return iArr == null ? this.b : iArr.length;
    }

    @Override // com.google.android.exoplayer2.a.c
    public int c() {
        return 2;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void d() {
        this.i = true;
    }

    @Override // com.google.android.exoplayer2.a.c
    public ByteBuffer e() {
        ByteBuffer byteBuffer = this.h;
        this.h = com.google.android.exoplayer2.a.c.f2259a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.a.c
    public boolean f() {
        return this.i && this.h == com.google.android.exoplayer2.a.c.f2259a;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void g() {
        this.h = com.google.android.exoplayer2.a.c.f2259a;
        this.i = false;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void h() {
        g();
        this.g = com.google.android.exoplayer2.a.c.f2259a;
        this.b = -1;
        this.c = -1;
        this.f = null;
        this.f8466e = false;
    }

    @Override // com.google.android.exoplayer2.a.c
    public boolean a(int i, int i2, int i3) throws c.a {
        boolean z = !Arrays.equals(this.d, this.f);
        int[] iArr = this.d;
        this.f = iArr;
        if (iArr == null) {
            this.f8466e = false;
            return z;
        }
        if (i3 != 2) {
            throw new c.a(i, i2, i3);
        }
        if (!z && this.c == i && this.b == i2) {
            return false;
        }
        this.c = i;
        this.b = i2;
        this.f8466e = i2 != this.f.length;
        int i4 = 0;
        while (true) {
            int[] iArr2 = this.f;
            if (i4 >= iArr2.length) {
                return true;
            }
            int i5 = iArr2[i4];
            if (i5 >= i2) {
                throw new c.a(i, i2, i3);
            }
            this.f8466e = (i5 != i4) | this.f8466e;
            i4++;
        }
    }

    @Override // com.google.android.exoplayer2.a.c
    public boolean a() {
        return this.f8466e;
    }

    @Override // com.google.android.exoplayer2.a.c
    public void a(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int length = ((iLimit - iPosition) / (this.b * 2)) * this.f.length * 2;
        if (this.g.capacity() < length) {
            this.g = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.g.clear();
        }
        while (iPosition < iLimit) {
            for (int i : this.f) {
                this.g.putShort(byteBuffer.getShort((i * 2) + iPosition));
            }
            iPosition += this.b * 2;
        }
        byteBuffer.position(iLimit);
        this.g.flip();
        this.h = this.g;
    }
}
