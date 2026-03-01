package supwisdom;

import java.util.Collections;
import java.util.List;
import supwisdom.u40;

/* JADX INFO: compiled from: DvbSubtitleReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class f40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<u40.a> f7564a;
    public final f50[] b;
    public boolean c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7565e;
    public long f;

    public f40(List<u40.a> list) {
        this.f7564a = list;
        this.b = new f50[list.size()];
    }

    @Override // supwisdom.g40
    public void a() {
        this.c = false;
    }

    @Override // supwisdom.g40
    public void b() {
        if (this.c) {
            for (f50 f50Var : this.b) {
                f50Var.a(this.f, 1, this.f7565e, 0, null);
            }
            this.c = false;
        }
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        for (int i = 0; i < this.b.length; i++) {
            u40.a aVar = this.f7564a.get(i);
            dVar.a();
            f50 f50VarA = z40Var.a(dVar.b(), 3);
            f50VarA.a(com.google.android.exoplayer2.j.a(dVar.c(), "application/dvbsubs", (String) null, -1, (List<byte[]>) Collections.singletonList(aVar.b), aVar.f9365a, (com.google.android.exoplayer2.c.a) null));
            this.b[i] = f50VarA;
        }
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        if (z) {
            this.c = true;
            this.f = j;
            this.f7565e = 0;
            this.d = 2;
        }
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        if (this.c) {
            if (this.d != 2 || a(o80Var, 32)) {
                if (this.d != 1 || a(o80Var, 0)) {
                    int iD = o80Var.d();
                    int iB = o80Var.b();
                    for (f50 f50Var : this.b) {
                        o80Var.c(iD);
                        f50Var.a(o80Var, iB);
                    }
                    this.f7565e += iB;
                }
            }
        }
    }

    public final boolean a(o80 o80Var, int i) {
        if (o80Var.b() == 0) {
            return false;
        }
        if (o80Var.g() != i) {
            this.c = false;
        }
        this.d--;
        return this.c;
    }
}
