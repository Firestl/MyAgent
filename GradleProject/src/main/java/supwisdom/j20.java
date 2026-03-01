package supwisdom;

import com.google.android.exoplayer2.d.a.d;

/* JADX INFO: compiled from: VideoTagPayloadReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class j20 extends com.google.android.exoplayer2.d.a.d {
    public final o80 b;
    public final o80 c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8011e;
    public int f;

    public j20(f50 f50Var) {
        super(f50Var);
        this.b = new o80(m80.f8362a);
        this.c = new o80(4);
    }

    @Override // com.google.android.exoplayer2.d.a.d
    public boolean a(o80 o80Var) throws d.a {
        int iG = o80Var.g();
        int i = (iG >> 4) & 15;
        int i2 = iG & 15;
        if (i2 == 7) {
            this.f = i;
            return i != 5;
        }
        throw new d.a("Video format not supported: " + i2);
    }

    @Override // com.google.android.exoplayer2.d.a.d
    public void a(o80 o80Var, long j) throws com.google.android.exoplayer2.n {
        int iG = o80Var.g();
        long jK = j + (((long) o80Var.k()) * 1000);
        if (iG == 0 && !this.f8011e) {
            o80 o80Var2 = new o80(new byte[o80Var.b()]);
            o80Var.a(o80Var2.f8644a, 0, o80Var.b());
            z80 z80VarA = z80.a(o80Var2);
            this.d = z80VarA.b;
            this.f2270a.a(com.google.android.exoplayer2.j.a((String) null, "video/avc", (String) null, -1, -1, z80VarA.c, z80VarA.d, -1.0f, z80VarA.f9975a, -1, z80VarA.f9976e, (com.google.android.exoplayer2.c.a) null));
            this.f8011e = true;
            return;
        }
        if (iG == 1 && this.f8011e) {
            byte[] bArr = this.c.f8644a;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i = 4 - this.d;
            int i2 = 0;
            while (o80Var.b() > 0) {
                o80Var.a(this.c.f8644a, i, this.d);
                this.c.c(0);
                int iT = this.c.t();
                this.b.c(0);
                this.f2270a.a(this.b, 4);
                this.f2270a.a(o80Var, iT);
                i2 = i2 + 4 + iT;
            }
            this.f2270a.a(jK, this.f == 1 ? 1 : 0, i2, 0, null);
        }
    }
}
