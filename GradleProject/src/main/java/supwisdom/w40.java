package supwisdom;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: WavExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class w40 implements y30, e50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z40 f9583a;
    public f50 b;
    public x40 c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9584e;

    /* JADX INFO: compiled from: WavExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new w40()};
        }
    }

    static {
        new a();
    }

    @Override // supwisdom.e50
    public boolean a() {
        return true;
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        return y40.a(v40Var) != null;
    }

    @Override // supwisdom.e50
    public long b() {
        return this.c.a();
    }

    @Override // supwisdom.y30
    public void c() {
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.f9583a = z40Var;
        this.b = z40Var.a(0, 1);
        this.c = null;
        z40Var.a();
    }

    @Override // supwisdom.e50
    public long b(long j) {
        return this.c.a(j);
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.f9584e = 0;
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        if (this.c == null) {
            x40 x40VarA = y40.a(v40Var);
            this.c = x40VarA;
            if (x40VarA != null) {
                this.b.a(com.google.android.exoplayer2.j.a((String) null, "audio/raw", (String) null, x40VarA.c(), 32768, this.c.e(), this.c.d(), this.c.g(), (List<byte[]>) null, (com.google.android.exoplayer2.c.a) null, 0, (String) null));
                this.d = this.c.b();
            } else {
                throw new com.google.android.exoplayer2.n("Unsupported or unrecognized wav header.");
            }
        }
        if (!this.c.f()) {
            y40.a(v40Var, this.c);
            this.f9583a.a(this);
        }
        int iA = this.b.a(v40Var, 32768 - this.f9584e, true);
        if (iA != -1) {
            this.f9584e += iA;
        }
        int i = this.f9584e / this.d;
        if (i > 0) {
            long jB = this.c.b(v40Var.c() - ((long) this.f9584e));
            int i2 = i * this.d;
            int i3 = this.f9584e - i2;
            this.f9584e = i3;
            this.b.a(jB, 1, i2, i3, null);
        }
        return iA == -1 ? -1 : 0;
    }
}
