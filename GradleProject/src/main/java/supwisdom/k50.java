package supwisdom;

import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: EventMessageDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class k50 implements m50 {
    @Override // supwisdom.m50
    public com.google.android.exoplayer2.f.a a(p50 p50Var) {
        ByteBuffer byteBuffer = p50Var.c;
        byte[] bArrArray = byteBuffer.array();
        int iLimit = byteBuffer.limit();
        o80 o80Var = new o80(bArrArray, iLimit);
        String strX = o80Var.x();
        String strX2 = o80Var.x();
        long jL = o80Var.l();
        o80Var.d(4);
        return new com.google.android.exoplayer2.f.a(new com.google.android.exoplayer2.f.a.a(strX, strX2, (o80Var.l() * 1000) / jL, o80Var.l(), Arrays.copyOfRange(bArrArray, o80Var.d(), iLimit)));
    }
}
