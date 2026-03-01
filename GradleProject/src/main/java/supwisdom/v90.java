package supwisdom;

import java.io.IOException;
import supwisdom.o90;

/* JADX INFO: compiled from: InitializationChunk.java */
/* JADX INFO: loaded from: classes.dex */
public final class v90 extends n90 {
    public final o90 i;
    public volatile int j;
    public volatile boolean k;

    public v90(s70 s70Var, u70 u70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, o90 o90Var) {
        super(s70Var, u70Var, 2, jVar, i, obj, -9223372036854775807L, -9223372036854775807L);
        this.i = o90Var;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public void a() {
        this.k = true;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public boolean b() {
        return this.k;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public void c() throws InterruptedException, IOException {
        u70 u70VarA = x80.a(this.f8485a, this.j);
        try {
            r20 r20Var = new r20(this.h, u70VarA.c, this.h.a(u70VarA));
            if (this.j == 0) {
                this.i.a((o90.b) null);
            }
            try {
                y30 y30Var = this.i.f8646a;
                int iA = 0;
                while (iA == 0 && !this.k) {
                    iA = y30Var.a(r20Var, (d50) null);
                }
                e80.b(iA != 1);
            } finally {
                this.j = (int) (r20Var.c() - this.f8485a.c);
            }
        } finally {
            x80.a(this.h);
        }
    }

    @Override // supwisdom.n90
    public long d() {
        return this.j;
    }
}
