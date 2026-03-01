package supwisdom;

import com.google.android.exoplayer2.f.a;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SpliceInfoDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class n50 implements m50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f8477a = new o80();
    public final n80 b = new n80();
    public u80 c;

    @Override // supwisdom.m50
    public com.google.android.exoplayer2.f.a a(p50 p50Var) throws com.google.android.exoplayer2.f.c {
        u80 u80Var = this.c;
        if (u80Var == null || p50Var.f != u80Var.c()) {
            u80 u80Var2 = new u80(p50Var.d);
            this.c = u80Var2;
            u80Var2.c(p50Var.d - p50Var.f);
        }
        ByteBuffer byteBuffer = p50Var.c;
        byte[] bArrArray = byteBuffer.array();
        int iLimit = byteBuffer.limit();
        this.f8477a.a(bArrArray, iLimit);
        this.b.a(bArrArray, iLimit);
        this.b.b(39);
        long jC = (((long) this.b.c(1)) << 32) | ((long) this.b.c(32));
        this.b.b(20);
        int iC = this.b.c(12);
        int iC2 = this.b.c(8);
        a.InterfaceC0053a eVar = null;
        this.f8477a.d(14);
        if (iC2 == 0) {
            eVar = new com.google.android.exoplayer2.f.c.e();
        } else if (iC2 == 255) {
            eVar = com.google.android.exoplayer2.f.c.a.a(this.f8477a, iC, jC);
        } else if (iC2 == 4) {
            eVar = com.google.android.exoplayer2.f.c.f.a(this.f8477a);
        } else if (iC2 == 5) {
            eVar = com.google.android.exoplayer2.f.c.d.a(this.f8477a, jC, this.c);
        } else if (iC2 == 6) {
            eVar = com.google.android.exoplayer2.f.c.g.a(this.f8477a, jC, this.c);
        }
        return eVar == null ? new com.google.android.exoplayer2.f.a(new a.InterfaceC0053a[0]) : new com.google.android.exoplayer2.f.a(eVar);
    }
}
