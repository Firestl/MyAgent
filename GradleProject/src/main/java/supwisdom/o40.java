package supwisdom;

import android.util.SparseArray;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import java.io.IOException;
import supwisdom.e50;
import supwisdom.u40;

/* JADX INFO: compiled from: PsExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class o40 implements y30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u80 f8621a;
    public final SparseArray<b> b;
    public final o80 c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8622e;
    public boolean f;
    public z40 g;

    /* JADX INFO: compiled from: PsExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new o40()};
        }
    }

    static {
        new a();
    }

    public o40() {
        this(new u80(0L));
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        byte[] bArr = new byte[14];
        v40Var.c(bArr, 0, 14);
        if (442 != (((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        v40Var.c(bArr[13] & 7);
        v40Var.c(bArr, 0, 3);
        return 1 == ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255));
    }

    @Override // supwisdom.y30
    public void c() {
    }

    /* JADX INFO: compiled from: PsExtractor.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g40 f8623a;
        public final u80 b;
        public final n80 c = new n80(new byte[64]);
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8624e;
        public boolean f;
        public int g;
        public long h;

        public b(g40 g40Var, u80 u80Var) {
            this.f8623a = g40Var;
            this.b = u80Var;
        }

        public void a() {
            this.f = false;
            this.f8623a.a();
        }

        public final void b() {
            this.c.b(8);
            this.d = this.c.d();
            this.f8624e = this.c.d();
            this.c.b(6);
            this.g = this.c.c(8);
        }

        public final void c() {
            this.h = 0L;
            if (this.d) {
                this.c.b(4);
                long jC = ((long) this.c.c(3)) << 30;
                this.c.b(1);
                long jC2 = jC | ((long) (this.c.c(15) << 15));
                this.c.b(1);
                long jC3 = jC2 | ((long) this.c.c(15));
                this.c.b(1);
                if (!this.f && this.f8624e) {
                    this.c.b(4);
                    long jC4 = ((long) this.c.c(3)) << 30;
                    this.c.b(1);
                    long jC5 = jC4 | ((long) (this.c.c(15) << 15));
                    this.c.b(1);
                    long jC6 = jC5 | ((long) this.c.c(15));
                    this.c.b(1);
                    this.b.b(jC6);
                    this.f = true;
                }
                this.h = this.b.b(jC3);
            }
        }

        public void a(o80 o80Var) {
            o80Var.a(this.c.f8483a, 0, 3);
            this.c.a(0);
            b();
            o80Var.a(this.c.f8483a, 0, this.g);
            this.c.a(0);
            c();
            this.f8623a.a(this.h, true);
            this.f8623a.a(o80Var);
            this.f8623a.b();
        }
    }

    public o40(u80 u80Var) {
        this.f8621a = u80Var;
        this.c = new o80(4096);
        this.b = new SparseArray<>();
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.g = z40Var;
        z40Var.a(new e50.a(-9223372036854775807L));
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.f8621a.d();
        for (int i = 0; i < this.b.size(); i++) {
            this.b.valueAt(i).a();
        }
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        if (!v40Var.b(this.c.f8644a, 0, 4, true)) {
            return -1;
        }
        this.c.c(0);
        int iN = this.c.n();
        if (iN == 441) {
            return -1;
        }
        if (iN == 442) {
            v40Var.c(this.c.f8644a, 0, 10);
            this.c.c(9);
            v40Var.b((this.c.g() & 7) + 14);
            return 0;
        }
        if (iN == 443) {
            v40Var.c(this.c.f8644a, 0, 2);
            this.c.c(0);
            v40Var.b(this.c.h() + 6);
            return 0;
        }
        if (((iN & DefaultImageHeaderParser.VP8_HEADER_MASK) >> 8) != 1) {
            v40Var.b(1);
            return 0;
        }
        int i = iN & 255;
        b bVar = this.b.get(i);
        if (!this.d) {
            if (bVar == null) {
                g40 h40Var = null;
                if (!this.f8622e && i == 189) {
                    h40Var = new a40();
                    this.f8622e = true;
                } else if (!this.f8622e && (i & 224) == 192) {
                    h40Var = new l40();
                    this.f8622e = true;
                } else if (!this.f && (i & 240) == 224) {
                    h40Var = new h40();
                    this.f = true;
                }
                if (h40Var != null) {
                    h40Var.a(this.g, new u40.d(i, 256));
                    bVar = new b(h40Var, this.f8621a);
                    this.b.put(i, bVar);
                }
            }
            if ((this.f8622e && this.f) || v40Var.c() > 1048576) {
                this.d = true;
                this.g.a();
            }
        }
        v40Var.c(this.c.f8644a, 0, 2);
        this.c.c(0);
        int iH = this.c.h() + 6;
        if (bVar == null) {
            v40Var.b(iH);
        } else {
            this.c.a(iH);
            v40Var.b(this.c.f8644a, 0, iH);
            this.c.c(6);
            bVar.a(this.c);
            o80 o80Var = this.c;
            o80Var.b(o80Var.e());
        }
        return 0;
    }
}
