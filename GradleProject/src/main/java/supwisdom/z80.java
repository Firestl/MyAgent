package supwisdom;

import java.util.ArrayList;
import java.util.List;
import supwisdom.m80;

/* JADX INFO: compiled from: AvcConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class z80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<byte[]> f9975a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f9976e;

    public z80(List<byte[]> list, int i, int i2, int i3, float f) {
        this.f9975a = list;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.f9976e = f;
    }

    public static z80 a(o80 o80Var) throws com.google.android.exoplayer2.n {
        int i;
        int i2;
        float f;
        try {
            o80Var.d(4);
            int iG = (o80Var.g() & 3) + 1;
            if (iG == 3) {
                throw new IllegalStateException();
            }
            ArrayList arrayList = new ArrayList();
            int iG2 = o80Var.g() & 31;
            for (int i3 = 0; i3 < iG2; i3++) {
                arrayList.add(b(o80Var));
            }
            int iG3 = o80Var.g();
            for (int i4 = 0; i4 < iG3; i4++) {
                arrayList.add(b(o80Var));
            }
            if (iG2 > 0) {
                m80.b bVarA = m80.a((byte[]) arrayList.get(0), iG, ((byte[]) arrayList.get(0)).length);
                int i5 = bVarA.b;
                int i6 = bVarA.c;
                f = bVarA.d;
                i = i5;
                i2 = i6;
            } else {
                i = -1;
                i2 = -1;
                f = 1.0f;
            }
            return new z80(arrayList, iG, i, i2, f);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new com.google.android.exoplayer2.n("Error parsing AVC config", e2);
        }
    }

    public static byte[] b(o80 o80Var) {
        int iH = o80Var.h();
        int iD = o80Var.d();
        o80Var.d(iH);
        return f80.a(o80Var.f8644a, iD, iH);
    }
}
