package supwisdom;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import supwisdom.s30;

/* JADX INFO: compiled from: FlacReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class m30 extends s30 {
    public i80 n;
    public a o;

    public static boolean c(o80 o80Var) {
        return o80Var.b() >= 5 && o80Var.g() == 127 && o80Var.l() == 1179402563;
    }

    public final int b(o80 o80Var) {
        int i;
        int i2;
        int i3 = (o80Var.f8644a[2] & 255) >> 4;
        switch (i3) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                i = 576;
                i2 = i3 - 2;
                break;
            case 6:
            case 7:
                o80Var.d(4);
                o80Var.z();
                int iG = i3 == 6 ? o80Var.g() : o80Var.h();
                o80Var.c(0);
                return iG + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i = 256;
                i2 = i3 - 8;
                break;
            default:
                return -1;
        }
        return i << i2;
    }

    @Override // supwisdom.s30
    public void a(boolean z) {
        super.a(z);
        if (z) {
            this.n = null;
            this.o = null;
        }
    }

    /* JADX INFO: compiled from: FlacReader.java */
    public class a implements q30, e50 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long[] f8350a;
        public long[] b;
        public long c = -1;
        public long d = -1;

        public a() {
        }

        public void a(o80 o80Var) {
            o80Var.d(1);
            int iK = o80Var.k() / 18;
            this.f8350a = new long[iK];
            this.b = new long[iK];
            for (int i = 0; i < iK; i++) {
                this.f8350a[i] = o80Var.p();
                this.b[i] = o80Var.p();
                o80Var.d(2);
            }
        }

        @Override // supwisdom.e50
        public boolean a() {
            return true;
        }

        @Override // supwisdom.e50
        public long b(long j) {
            return this.c + this.b[x80.a(this.f8350a, m30.this.b(j), true, true)];
        }

        @Override // supwisdom.q30
        public e50 c() {
            return this;
        }

        public void c(long j) {
            this.c = j;
        }

        @Override // supwisdom.e50
        public long b() {
            return m30.this.n.b();
        }

        @Override // supwisdom.q30
        public long a(v40 v40Var) throws InterruptedException, IOException {
            long j = this.d;
            if (j < 0) {
                return -1L;
            }
            long j2 = -(j + 2);
            this.d = -1L;
            return j2;
        }

        @Override // supwisdom.q30
        public long a(long j) {
            long jB = m30.this.b(j);
            this.d = this.f8350a[x80.a(this.f8350a, jB, true, true)];
            return jB;
        }
    }

    public static boolean a(byte[] bArr) {
        return bArr[0] == -1;
    }

    @Override // supwisdom.s30
    public long a(o80 o80Var) {
        if (a(o80Var.f8644a)) {
            return b(o80Var);
        }
        return -1L;
    }

    @Override // supwisdom.s30
    public boolean a(o80 o80Var, long j, s30.b bVar) throws InterruptedException, IOException {
        byte[] bArr = o80Var.f8644a;
        if (this.n == null) {
            this.n = new i80(bArr, 17);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 9, o80Var.c());
            bArrCopyOfRange[4] = com.igexin.c.a.d.g.n;
            List listSingletonList = Collections.singletonList(bArrCopyOfRange);
            int iA = this.n.a();
            i80 i80Var = this.n;
            bVar.f9118a = com.google.android.exoplayer2.j.a(null, "audio/x-flac", null, -1, iA, i80Var.b, i80Var.f7928a, listSingletonList, null, 0, null);
            return true;
        }
        if ((bArr[0] & 127) == 3) {
            a aVar = new a();
            this.o = aVar;
            aVar.a(o80Var);
            return true;
        }
        if (!a(bArr)) {
            return true;
        }
        a aVar2 = this.o;
        if (aVar2 != null) {
            aVar2.c(j);
            bVar.b = this.o;
        }
        return false;
    }
}
