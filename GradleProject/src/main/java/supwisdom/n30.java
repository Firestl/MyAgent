package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: OggExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public class n30 implements y30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public s30 f8471a;

    /* JADX INFO: compiled from: OggExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new n30()};
        }
    }

    static {
        new a();
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        try {
            p30 p30Var = new p30();
            if (p30Var.a(v40Var, true) && (p30Var.b & 2) == 2) {
                int iMin = Math.min(p30Var.f, 8);
                o80 o80Var = new o80(iMin);
                v40Var.c(o80Var.f8644a, 0, iMin);
                a(o80Var);
                if (m30.c(o80Var)) {
                    this.f8471a = new m30();
                } else {
                    a(o80Var);
                    if (u30.c(o80Var)) {
                        this.f8471a = new u30();
                    } else {
                        a(o80Var);
                        if (r30.b(o80Var)) {
                            this.f8471a = new r30();
                        }
                    }
                }
                return true;
            }
        } catch (com.google.android.exoplayer2.n unused) {
        }
        return false;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        f50 f50VarA = z40Var.a(0, 1);
        z40Var.a();
        this.f8471a.a(z40Var, f50VarA);
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.f8471a.a(j, j2);
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        return this.f8471a.a(v40Var, d50Var);
    }

    public static o80 a(o80 o80Var) {
        o80Var.c(0);
        return o80Var;
    }
}
