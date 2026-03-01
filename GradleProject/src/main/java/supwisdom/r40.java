package supwisdom;

import java.util.List;
import supwisdom.u40;

/* JADX INFO: compiled from: SeiReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class r40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<com.google.android.exoplayer2.j> f9008a;
    public final f50[] b;

    public r40(List<com.google.android.exoplayer2.j> list) {
        this.f9008a = list;
        this.b = new f50[list.size()];
    }

    public void a(z40 z40Var, u40.d dVar) {
        for (int i = 0; i < this.b.length; i++) {
            dVar.a();
            f50 f50VarA = z40Var.a(dVar.b(), 3);
            com.google.android.exoplayer2.j jVar = this.f9008a.get(i);
            String str = jVar.f;
            e80.a("application/cea-608".equals(str) || "application/cea-708".equals(str), "Invalid closed caption mime type provided: " + str);
            f50VarA.a(com.google.android.exoplayer2.j.a(dVar.c(), str, (String) null, -1, jVar.x, jVar.y, jVar.z, (com.google.android.exoplayer2.c.a) null));
            this.b[i] = f50VarA;
        }
    }

    public void a(long j, o80 o80Var) {
        x50.a(j, o80Var, this.b);
    }
}
