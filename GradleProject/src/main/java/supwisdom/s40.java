package supwisdom;

import supwisdom.u40;

/* JADX INFO: compiled from: SpliceInfoSectionReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class s40 implements p40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public u80 f9121a;
    public f50 b;
    public boolean c;

    @Override // supwisdom.p40
    public void a(u80 u80Var, z40 z40Var, u40.d dVar) {
        this.f9121a = u80Var;
        dVar.a();
        f50 f50VarA = z40Var.a(dVar.b(), 4);
        this.b = f50VarA;
        f50VarA.a(com.google.android.exoplayer2.j.a(dVar.c(), "application/x-scte35", null, -1, null));
    }

    @Override // supwisdom.p40
    public void a(o80 o80Var) {
        if (!this.c) {
            if (this.f9121a.c() == -9223372036854775807L) {
                return;
            }
            this.b.a(com.google.android.exoplayer2.j.a((String) null, "application/x-scte35", this.f9121a.c()));
            this.c = true;
        }
        int iB = o80Var.b();
        this.b.a(o80Var, iB);
        this.b.a(this.f9121a.b(), 1, iB, 0, null);
    }
}
