package supwisdom;

import android.util.Log;
import supwisdom.o90;

/* JADX INFO: compiled from: BaseMediaChunkOutput.java */
/* JADX INFO: loaded from: classes.dex */
public final class m90 implements o90.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f8366a;
    public final x20[] b;

    public m90(int[] iArr, x20[] x20VarArr) {
        this.f8366a = iArr;
        this.b = x20VarArr;
    }

    @Override // supwisdom.o90.b
    public f50 a(int i, int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.f8366a;
            if (i3 >= iArr.length) {
                Log.e("BaseMediaChunkOutput", "Unmatched track of type: " + i2);
                return new w30();
            }
            if (i2 == iArr[i3]) {
                return this.b[i3];
            }
            i3++;
        }
    }

    public int[] a() {
        int[] iArr = new int[this.b.length];
        int i = 0;
        while (true) {
            x20[] x20VarArr = this.b;
            if (i >= x20VarArr.length) {
                return iArr;
            }
            if (x20VarArr[i] != null) {
                iArr[i] = x20VarArr[i].b();
            }
            i++;
        }
    }

    public void a(long j) {
        for (x20 x20Var : this.b) {
            if (x20Var != null) {
                x20Var.a(j);
            }
        }
    }
}
