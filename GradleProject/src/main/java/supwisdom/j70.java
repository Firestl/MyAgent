package supwisdom;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Map;
import supwisdom.k70;

/* JADX INFO: compiled from: MappingTrackSelector.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j70 extends m70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray<Map<qb0, b>> f8028a = new SparseArray<>();
    public final SparseBooleanArray b = new SparseBooleanArray();
    public int c = 0;
    public a d;

    /* JADX INFO: compiled from: MappingTrackSelector.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8029a;
        public final qb0[] b;
        public final int[] c;
        public final int[][][] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final qb0 f8030e;

        public a(int[] iArr, qb0[] qb0VarArr, int[] iArr2, int[][][] iArr3, qb0 qb0Var) {
            this.b = qb0VarArr;
            this.d = iArr3;
            this.c = iArr2;
            this.f8030e = qb0Var;
            this.f8029a = qb0VarArr.length;
        }

        public qb0 a(int i) {
            return this.b[i];
        }

        public int a(int i, int i2, int i3) {
            return this.d[i][i2][i3] & 3;
        }

        public int a(int i, int i2, boolean z) {
            int i3 = this.b[i].a(i2).f8788a;
            int[] iArr = new int[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int iA = a(i, i2, i5);
                if (iA == 3 || (z && iA == 2)) {
                    iArr[i4] = i5;
                    i4++;
                }
            }
            return a(i, i2, Arrays.copyOf(iArr, i4));
        }

        public int a(int i, int i2, int[] iArr) {
            int i3 = 0;
            String str = null;
            boolean z = false;
            int i4 = 0;
            int iMin = 8;
            while (i3 < iArr.length) {
                String str2 = this.b[i].a(i2).a(iArr[i3]).f;
                int i5 = i4 + 1;
                if (i4 == 0) {
                    str = str2;
                } else {
                    z |= !x80.a(str, str2);
                }
                iMin = Math.min(iMin, this.d[i][i2][i3] & 12);
                i3++;
                i4 = i5;
            }
            return z ? Math.min(iMin, this.c[i]) : iMin;
        }

        public qb0 a() {
            return this.f8030e;
        }
    }

    /* JADX INFO: compiled from: MappingTrackSelector.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k70.a f8031a;
        public final int b;
        public final int[] c;

        public k70 a(qb0 qb0Var) {
            return this.f8031a.a(qb0Var.a(this.b), this.c);
        }
    }

    public final a a() {
        return this.d;
    }

    public abstract k70[] a(i90[] i90VarArr, qb0[] qb0VarArr, int[][][] iArr) throws com.google.android.exoplayer2.e;

    @Override // supwisdom.m70
    public final n70 a(i90[] i90VarArr, qb0 qb0Var) throws com.google.android.exoplayer2.e {
        int[] iArr = new int[i90VarArr.length + 1];
        int length = i90VarArr.length + 1;
        pb0[][] pb0VarArr = new pb0[length][];
        int[][][] iArr2 = new int[i90VarArr.length + 1][][];
        for (int i = 0; i < length; i++) {
            int i2 = qb0Var.f8907a;
            pb0VarArr[i] = new pb0[i2];
            iArr2[i] = new int[i2][];
        }
        int[] iArrA = a(i90VarArr);
        for (int i3 = 0; i3 < qb0Var.f8907a; i3++) {
            pb0 pb0VarA = qb0Var.a(i3);
            int iA = a(i90VarArr, pb0VarA);
            int[] iArrA2 = iA == i90VarArr.length ? new int[pb0VarA.f8788a] : a(i90VarArr[iA], pb0VarA);
            int i4 = iArr[iA];
            pb0VarArr[iA][i4] = pb0VarA;
            iArr2[iA][i4] = iArrA2;
            iArr[iA] = iArr[iA] + 1;
        }
        qb0[] qb0VarArr = new qb0[i90VarArr.length];
        int[] iArr3 = new int[i90VarArr.length];
        for (int i5 = 0; i5 < i90VarArr.length; i5++) {
            int i6 = iArr[i5];
            qb0VarArr[i5] = new qb0((pb0[]) Arrays.copyOf(pb0VarArr[i5], i6));
            iArr2[i5] = (int[][]) Arrays.copyOf(iArr2[i5], i6);
            iArr3[i5] = i90VarArr[i5].a();
        }
        qb0 qb0Var2 = new qb0((pb0[]) Arrays.copyOf(pb0VarArr[i90VarArr.length], iArr[i90VarArr.length]));
        k70[] k70VarArrA = a(i90VarArr, qb0VarArr, iArr2);
        int i7 = 0;
        while (true) {
            if (i7 >= i90VarArr.length) {
                break;
            }
            if (this.b.get(i7)) {
                k70VarArrA[i7] = null;
            } else {
                qb0 qb0Var3 = qb0VarArr[i7];
                Map<qb0, b> map = this.f8028a.get(i7);
                b bVar = map != null ? map.get(qb0Var3) : null;
                if (bVar != null) {
                    k70VarArrA[i7] = bVar.a(qb0Var3);
                }
            }
            i7++;
        }
        a aVar = new a(iArr3, qb0VarArr, iArrA, iArr2, qb0Var2);
        j90[] j90VarArr = new j90[i90VarArr.length];
        for (int i8 = 0; i8 < i90VarArr.length; i8++) {
            j90VarArr[i8] = k70VarArrA[i8] != null ? j90.b : null;
        }
        a(i90VarArr, qb0VarArr, iArr2, j90VarArr, k70VarArrA, this.c);
        return new n70(qb0Var, new l70(k70VarArrA), aVar, j90VarArr);
    }

    @Override // supwisdom.m70
    public final void a(Object obj) {
        this.d = (a) obj;
    }

    public static int a(i90[] i90VarArr, pb0 pb0Var) throws com.google.android.exoplayer2.e {
        int length = i90VarArr.length;
        int i = 0;
        for (int i2 = 0; i2 < i90VarArr.length; i2++) {
            i90 i90Var = i90VarArr[i2];
            for (int i3 = 0; i3 < pb0Var.f8788a; i3++) {
                int iA = i90Var.a(pb0Var.a(i3)) & 3;
                if (iA > i) {
                    if (iA == 3) {
                        return i2;
                    }
                    length = i2;
                    i = iA;
                }
            }
        }
        return length;
    }

    public static int[] a(i90 i90Var, pb0 pb0Var) throws com.google.android.exoplayer2.e {
        int[] iArr = new int[pb0Var.f8788a];
        for (int i = 0; i < pb0Var.f8788a; i++) {
            iArr[i] = i90Var.a(pb0Var.a(i));
        }
        return iArr;
    }

    public static int[] a(i90[] i90VarArr) throws com.google.android.exoplayer2.e {
        int length = i90VarArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = i90VarArr[i].m();
        }
        return iArr;
    }

    public static void a(i90[] i90VarArr, qb0[] qb0VarArr, int[][][] iArr, j90[] j90VarArr, k70[] k70VarArr, int i) {
        boolean z;
        if (i == 0) {
            return;
        }
        boolean z2 = false;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < i90VarArr.length; i4++) {
            int iA = i90VarArr[i4].a();
            k70 k70Var = k70VarArr[i4];
            if ((iA == 1 || iA == 2) && k70Var != null && a(iArr[i4], qb0VarArr[i4], k70Var)) {
                if (iA == 1) {
                    if (i3 != -1) {
                        z = false;
                        break;
                    }
                    i3 = i4;
                } else {
                    if (i2 != -1) {
                        z = false;
                        break;
                    }
                    i2 = i4;
                }
            }
        }
        z = true;
        if (i3 != -1 && i2 != -1) {
            z2 = true;
        }
        if (z && z2) {
            j90 j90Var = new j90(i);
            j90VarArr[i3] = j90Var;
            j90VarArr[i2] = j90Var;
        }
    }

    public static boolean a(int[][] iArr, qb0 qb0Var, k70 k70Var) {
        if (k70Var == null) {
            return false;
        }
        int iA = qb0Var.a(k70Var.d());
        for (int i = 0; i < k70Var.e(); i++) {
            if ((iArr[iA][k70Var.b(i)] & 16) != 16) {
                return false;
            }
        }
        return true;
    }
}
