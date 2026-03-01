package supwisdom;

import android.net.Uri;
import android.os.Handler;
import java.io.IOException;
import supwisdom.s70;
import supwisdom.ua0;
import supwisdom.yb0;

/* JADX INFO: compiled from: ExtractorMediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class sa0 implements ua0, ua0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f9141a;
    public final s70.a b;
    public final a50 c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f9142e;
    public final a f;
    public final yb0.b g;
    public final String h;
    public ua0.a i;
    public yb0 j;
    public boolean k;

    /* JADX INFO: compiled from: ExtractorMediaSource.java */
    public interface a {
        void onLoadError(IOException iOException);
    }

    public sa0(Uri uri, s70.a aVar, a50 a50Var, Handler handler, a aVar2) {
        this(uri, aVar, a50Var, -1, handler, aVar2, null);
    }

    @Override // supwisdom.ua0
    public void a() throws IOException {
    }

    @Override // supwisdom.ua0
    public void a(j50 j50Var, boolean z, ua0.a aVar) {
        this.i = aVar;
        ob0 ob0Var = new ob0(-9223372036854775807L, false);
        this.j = ob0Var;
        aVar.a(ob0Var, null);
    }

    @Override // supwisdom.ua0
    public void b() {
        this.i = null;
    }

    public sa0(Uri uri, s70.a aVar, a50 a50Var, int i, Handler handler, a aVar2, String str) {
        this.f9141a = uri;
        this.b = aVar;
        this.c = a50Var;
        this.d = i;
        this.f9142e = handler;
        this.f = aVar2;
        this.h = str;
        this.g = new yb0.b();
    }

    @Override // supwisdom.ua0
    public ta0 a(int i, q70 q70Var, long j) {
        e80.a(i == 0);
        return new ra0(this.f9141a, this.b.a(), this.c.a(), this.d, this.f9142e, this.f, this, q70Var, this.h);
    }

    @Override // supwisdom.ua0
    public void a(ta0 ta0Var) {
        ((ra0) ta0Var).b();
    }

    @Override // supwisdom.ua0.a
    public void a(yb0 yb0Var, Object obj) {
        boolean z = yb0Var.a(0, this.g).b() != -9223372036854775807L;
        if (!this.k || z) {
            this.j = yb0Var;
            this.k = z;
            this.i.a(yb0Var, null);
        }
    }
}
