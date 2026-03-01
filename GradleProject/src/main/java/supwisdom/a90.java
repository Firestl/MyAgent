package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: HevcConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class a90 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<byte[]> f6875a;
    public final int b;

    public a90(List<byte[]> list, int i) {
        this.f6875a = list;
        this.b = i;
    }

    public static a90 a(o80 o80Var) throws com.google.android.exoplayer2.n {
        try {
            o80Var.d(21);
            int iG = o80Var.g() & 3;
            int iG2 = o80Var.g();
            int iD = o80Var.d();
            int i = 0;
            for (int i2 = 0; i2 < iG2; i2++) {
                o80Var.d(1);
                int iH = o80Var.h();
                for (int i3 = 0; i3 < iH; i3++) {
                    int iH2 = o80Var.h();
                    i += iH2 + 4;
                    o80Var.d(iH2);
                }
            }
            o80Var.c(iD);
            byte[] bArr = new byte[i];
            int i4 = 0;
            for (int i5 = 0; i5 < iG2; i5++) {
                o80Var.d(1);
                int iH3 = o80Var.h();
                for (int i6 = 0; i6 < iH3; i6++) {
                    int iH4 = o80Var.h();
                    System.arraycopy(m80.f8362a, 0, bArr, i4, m80.f8362a.length);
                    int length = i4 + m80.f8362a.length;
                    System.arraycopy(o80Var.f8644a, o80Var.d(), bArr, length, iH4);
                    i4 = length + iH4;
                    o80Var.d(iH4);
                }
            }
            return new a90(i == 0 ? null : Collections.singletonList(bArr), iG + 1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new com.google.android.exoplayer2.n("Error parsing HEVC config", e2);
        }
    }
}
