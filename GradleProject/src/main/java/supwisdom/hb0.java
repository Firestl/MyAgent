package supwisdom;

import android.net.Uri;
import android.os.Handler;
import java.io.IOException;
import java.util.List;
import supwisdom.k90;
import supwisdom.s70;
import supwisdom.ua0;
import supwisdom.wa0;
import supwisdom.za0;

/* JADX INFO: compiled from: HlsMediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class hb0 implements ua0, za0.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f7822a;
    public final db0 b;
    public final int c;
    public final k90.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public za0 f7823e;
    public ua0.a f;

    public hb0(Uri uri, s70.a aVar, Handler handler, k90 k90Var) {
        this(uri, aVar, 3, handler, k90Var);
    }

    @Override // supwisdom.ua0
    public void a(j50 j50Var, boolean z, ua0.a aVar) {
        e80.b(this.f7823e == null);
        za0 za0Var = new za0(this.f7822a, this.b, this.d, this.c, this);
        this.f7823e = za0Var;
        this.f = aVar;
        za0Var.a();
    }

    @Override // supwisdom.ua0
    public void b() {
        za0 za0Var = this.f7823e;
        if (za0Var != null) {
            za0Var.c();
            this.f7823e = null;
        }
        this.f = null;
    }

    public hb0(Uri uri, s70.a aVar, int i, Handler handler, k90 k90Var) {
        this(uri, new bb0(aVar), i, handler, k90Var);
    }

    public hb0(Uri uri, db0 db0Var, int i, Handler handler, k90 k90Var) {
        this.f7822a = uri;
        this.b = db0Var;
        this.c = i;
        this.d = new k90.a(handler, k90Var);
    }

    @Override // supwisdom.ua0
    public void a() throws IOException {
        this.f7823e.d();
    }

    @Override // supwisdom.ua0
    public ta0 a(int i, q70 q70Var, long j) {
        e80.a(i == 0);
        return new gb0(this.f7823e, this.b, this.c, this.d, q70Var, j);
    }

    @Override // supwisdom.ua0
    public void a(ta0 ta0Var) {
        ((gb0) ta0Var).a();
    }

    @Override // supwisdom.za0.c
    public void a(wa0 wa0Var) {
        ob0 ob0Var;
        long j = wa0Var.c;
        if (this.f7823e.e()) {
            long j2 = wa0Var.j ? wa0Var.d + wa0Var.o : -9223372036854775807L;
            List<wa0.a> list = wa0Var.m;
            if (j == -9223372036854775807L) {
                j = list.isEmpty() ? 0L : list.get(Math.max(0, list.size() - 3)).d;
            }
            ob0Var = new ob0(j2, wa0Var.o, wa0Var.d, j, true, !wa0Var.j);
        } else {
            long j3 = j == -9223372036854775807L ? 0L : j;
            long j4 = wa0Var.d;
            long j5 = wa0Var.o;
            ob0Var = new ob0(j4 + j5, j5, j4, j3, true, false);
        }
        this.f.a(ob0Var, new eb0(this.f7823e.b(), wa0Var));
    }
}
