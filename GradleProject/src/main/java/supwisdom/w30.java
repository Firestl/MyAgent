package supwisdom;

import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: DummyTrackOutput.java */
/* JADX INFO: loaded from: classes.dex */
public final class w30 implements f50 {
    @Override // supwisdom.f50
    public int a(v40 v40Var, int i, boolean z) throws InterruptedException, IOException {
        int iA = v40Var.a(i);
        if (iA != -1) {
            return iA;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // supwisdom.f50
    public void a(long j, int i, int i2, int i3, byte[] bArr) {
    }

    @Override // supwisdom.f50
    public void a(com.google.android.exoplayer2.j jVar) {
    }

    @Override // supwisdom.f50
    public void a(o80 o80Var, int i) {
        o80Var.d(i);
    }
}
