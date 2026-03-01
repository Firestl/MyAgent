package supwisdom;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: DataChunk.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u90 extends n90 {
    public byte[] i;
    public int j;
    public volatile boolean k;

    public u90(s70 s70Var, u70 u70Var, int i, com.google.android.exoplayer2.j jVar, int i2, Object obj, byte[] bArr) {
        super(s70Var, u70Var, i, jVar, i2, obj, -9223372036854775807L, -9223372036854775807L);
        this.i = bArr;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final void a() {
        this.k = true;
    }

    public abstract void a(byte[] bArr, int i) throws IOException;

    @Override // com.google.android.exoplayer2.i.r.c
    public final boolean b() {
        return this.k;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final void c() throws InterruptedException, IOException {
        try {
            this.h.a(this.f8485a);
            int iA = 0;
            this.j = 0;
            while (iA != -1 && !this.k) {
                f();
                iA = this.h.a(this.i, this.j, 16384);
                if (iA != -1) {
                    this.j += iA;
                }
            }
            if (!this.k) {
                a(this.i, this.j);
            }
        } finally {
            x80.a(this.h);
        }
    }

    @Override // supwisdom.n90
    public long d() {
        return this.j;
    }

    public byte[] e() {
        return this.i;
    }

    public final void f() {
        byte[] bArr = this.i;
        if (bArr == null) {
            this.i = new byte[16384];
        } else if (bArr.length < this.j + 16384) {
            this.i = Arrays.copyOf(bArr, bArr.length + 16384);
        }
    }
}
