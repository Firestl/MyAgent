package supwisdom;

import supwisdom.u40;

/* JADX INFO: compiled from: MpegAudioReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class l40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f8243a;
    public final c50 b;
    public final String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f50 f8244e;
    public int f;
    public int g;
    public boolean h;
    public boolean i;
    public long j;
    public int k;
    public long l;

    public l40() {
        this(null);
    }

    @Override // supwisdom.g40
    public void a() {
        this.f = 0;
        this.g = 0;
        this.i = false;
    }

    @Override // supwisdom.g40
    public void b() {
    }

    public final void b(o80 o80Var) {
        byte[] bArr = o80Var.f8644a;
        int iC = o80Var.c();
        for (int iD = o80Var.d(); iD < iC; iD++) {
            boolean z = (bArr[iD] & 255) == 255;
            boolean z2 = this.i && (bArr[iD] & com.umeng.analytics.pro.co.k) == 224;
            this.i = z;
            if (z2) {
                o80Var.c(iD + 1);
                this.i = false;
                this.f8243a.f8644a[1] = bArr[iD];
                this.g = 2;
                this.f = 1;
                return;
            }
        }
        o80Var.c(iC);
    }

    public final void c(o80 o80Var) {
        int iMin = Math.min(o80Var.b(), 4 - this.g);
        o80Var.a(this.f8243a.f8644a, this.g, iMin);
        int i = this.g + iMin;
        this.g = i;
        if (i < 4) {
            return;
        }
        this.f8243a.c(0);
        if (!c50.a(this.f8243a.n(), this.b)) {
            this.g = 0;
            this.f = 1;
            return;
        }
        c50 c50Var = this.b;
        this.k = c50Var.c;
        if (!this.h) {
            long j = ((long) c50Var.g) * 1000000;
            int i2 = c50Var.d;
            this.j = j / ((long) i2);
            this.f8244e.a(com.google.android.exoplayer2.j.a(this.d, c50Var.b, null, -1, 4096, c50Var.f7147e, i2, null, null, 0, this.c));
            this.h = true;
        }
        this.f8243a.c(0);
        this.f8244e.a(this.f8243a, 4);
        this.f = 2;
    }

    public final void d(o80 o80Var) {
        int iMin = Math.min(o80Var.b(), this.k - this.g);
        this.f8244e.a(o80Var, iMin);
        int i = this.g + iMin;
        this.g = i;
        int i2 = this.k;
        if (i < i2) {
            return;
        }
        this.f8244e.a(this.l, 1, i2, 0, null);
        this.l += this.j;
        this.g = 0;
        this.f = 0;
    }

    public l40(String str) {
        this.f = 0;
        o80 o80Var = new o80(4);
        this.f8243a = o80Var;
        o80Var.f8644a[0] = -1;
        this.b = new c50();
        this.c = str;
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.d = dVar.c();
        this.f8244e = z40Var.a(dVar.b(), 1);
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        this.l = j;
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        while (o80Var.b() > 0) {
            int i = this.f;
            if (i == 0) {
                b(o80Var);
            } else if (i == 1) {
                c(o80Var);
            } else if (i == 2) {
                d(o80Var);
            }
        }
    }
}
