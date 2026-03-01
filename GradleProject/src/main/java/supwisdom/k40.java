package supwisdom;

import android.util.Log;
import supwisdom.u40;

/* JADX INFO: compiled from: Id3Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class k40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f8122a = new o80(10);
    public f50 b;
    public boolean c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8123e;
    public int f;

    @Override // supwisdom.g40
    public void a() {
        this.c = false;
    }

    @Override // supwisdom.g40
    public void b() {
        int i;
        if (this.c && (i = this.f8123e) != 0 && this.f == i) {
            this.b.a(this.d, 1, i, 0, null);
            this.c = false;
        }
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        f50 f50VarA = z40Var.a(dVar.b(), 4);
        this.b = f50VarA;
        f50VarA.a(com.google.android.exoplayer2.j.a(dVar.c(), "application/id3", null, -1, null));
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        if (z) {
            this.c = true;
            this.d = j;
            this.f8123e = 0;
            this.f = 0;
        }
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        if (this.c) {
            int iB = o80Var.b();
            int i = this.f;
            if (i < 10) {
                int iMin = Math.min(iB, 10 - i);
                System.arraycopy(o80Var.f8644a, o80Var.d(), this.f8122a.f8644a, this.f, iMin);
                if (this.f + iMin == 10) {
                    this.f8122a.c(0);
                    if (73 == this.f8122a.g() && 68 == this.f8122a.g() && 51 == this.f8122a.g()) {
                        this.f8122a.d(3);
                        this.f8123e = this.f8122a.s() + 10;
                    } else {
                        Log.w("Id3Reader", "Discarding invalid ID3 tag");
                        this.c = false;
                        return;
                    }
                }
            }
            int iMin2 = Math.min(iB, this.f8123e - this.f);
            this.b.a(o80Var, iMin2);
            this.f += iMin2;
        }
    }
}
