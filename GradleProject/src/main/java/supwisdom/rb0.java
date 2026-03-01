package supwisdom;

import android.net.Uri;
import java.io.IOException;
import supwisdom.s70;
import supwisdom.sb0;
import supwisdom.tb0;

/* JADX INFO: compiled from: DefaultSsChunkSource.java */
/* JADX INFO: loaded from: classes.dex */
public class rb0 implements tb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b80 f9041a;
    public final int b;
    public final k70 c;
    public final o90[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s70 f9042e;
    public sb0 f;
    public int g;
    public IOException h;

    /* JADX INFO: compiled from: DefaultSsChunkSource.java */
    public static final class a implements tb0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final s70.a f9043a;

        public a(s70.a aVar) {
            this.f9043a = aVar;
        }

        @Override // supwisdom.tb0.a
        public tb0 a(b80 b80Var, sb0 sb0Var, int i, k70 k70Var, i30[] i30VarArr) {
            return new rb0(b80Var, sb0Var, i, k70Var, this.f9043a.a(), i30VarArr);
        }
    }

    public rb0(b80 b80Var, sb0 sb0Var, int i, k70 k70Var, s70 s70Var, i30[] i30VarArr) {
        this.f9041a = b80Var;
        this.f = sb0Var;
        this.b = i;
        this.c = k70Var;
        this.f9042e = s70Var;
        sb0.b bVar = sb0Var.c[i];
        this.d = new o90[k70Var.e()];
        int i2 = 0;
        while (i2 < this.d.length) {
            int iB = k70Var.b(i2);
            com.google.android.exoplayer2.j jVar = bVar.c[iB];
            int i3 = i2;
            this.d[i3] = new o90(new c30(3, null, new h30(iB, bVar.f9147a, bVar.b, -9223372036854775807L, sb0Var.d, jVar, 0, i30VarArr, bVar.f9147a == 2 ? 4 : 0, null, null)), jVar);
            i2 = i3 + 1;
        }
    }

    @Override // supwisdom.r90
    public void a(n90 n90Var) {
    }

    @Override // supwisdom.tb0
    public void a(sb0 sb0Var) {
        sb0.b[] bVarArr = this.f.c;
        int i = this.b;
        sb0.b bVar = bVarArr[i];
        int i2 = bVar.d;
        sb0.b bVar2 = sb0Var.c[i];
        if (i2 == 0 || bVar2.d == 0) {
            this.g += i2;
        } else {
            int i3 = i2 - 1;
            long jA = bVar.a(i3) + bVar.b(i3);
            long jA2 = bVar2.a(0);
            if (jA <= jA2) {
                this.g += i2;
            } else {
                this.g += bVar.a(jA2);
            }
        }
        this.f = sb0Var;
    }

    @Override // supwisdom.r90
    public void a() throws IOException {
        IOException iOException = this.h;
        if (iOException == null) {
            this.f9041a.d();
            return;
        }
        throw iOException;
    }

    @Override // supwisdom.r90
    public final void a(w90 w90Var, long j, p90 p90Var) {
        int iE;
        if (this.h != null) {
            return;
        }
        this.c.a(w90Var != null ? w90Var.g - j : 0L);
        sb0.b bVar = this.f.c[this.b];
        if (bVar.d == 0) {
            p90Var.b = !r5.f9144a;
            return;
        }
        if (w90Var == null) {
            iE = bVar.a(j);
        } else {
            iE = w90Var.e() - this.g;
            if (iE < 0) {
                this.h = new com.google.android.exoplayer2.source.b();
                return;
            }
        }
        if (iE >= bVar.d) {
            p90Var.b = !this.f.f9144a;
            return;
        }
        long jA = bVar.a(iE);
        long jB = jA + bVar.b(iE);
        int i = iE + this.g;
        int iA = this.c.a();
        p90Var.f8778a = a(this.c.f(), this.f9042e, bVar.a(this.c.b(iA), iE), null, i, jA, jB, this.c.b(), this.c.c(), this.d[iA]);
    }

    @Override // supwisdom.r90
    public boolean a(n90 n90Var, boolean z, Exception exc) {
        if (z) {
            k70 k70Var = this.c;
            if (s90.a(k70Var, k70Var.a(n90Var.c), exc)) {
                return true;
            }
        }
        return false;
    }

    public static w90 a(com.google.android.exoplayer2.j jVar, s70 s70Var, Uri uri, String str, int i, long j, long j2, int i2, Object obj, o90 o90Var) {
        return new t90(s70Var, new u70(uri, 0L, -1L, str), jVar, i2, obj, j, j2, i, 1, j, o90Var);
    }
}
