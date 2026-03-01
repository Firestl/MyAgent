package supwisdom;

import android.graphics.Point;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import supwisdom.k70;

/* JADX INFO: compiled from: DefaultTrackSelector.java */
/* JADX INFO: loaded from: classes.dex */
public class h70 extends j70 {
    public static final int[] g = new int[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k70.a f7808e;
    public final AtomicReference<b> f = new AtomicReference<>(new b());

    /* JADX INFO: compiled from: DefaultTrackSelector.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7809a;
        public final int b;
        public final String c;

        public a(int i, int i2, String str) {
            this.f7809a = i;
            this.b = i2;
            this.c = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.f7809a == aVar.f7809a && this.b == aVar.b && TextUtils.equals(this.c, aVar.c);
        }

        public int hashCode() {
            int i = ((this.f7809a * 31) + this.b) * 31;
            String str = this.c;
            return i + (str != null ? str.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: DefaultTrackSelector.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f7810a;
        public final String b;
        public final boolean c;
        public final boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f7811e;
        public final int f;
        public final int g;
        public final boolean h;
        public final boolean i;
        public final int j;
        public final int k;
        public final boolean l;

        public b() {
            this(null, null, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.c == bVar.c && this.d == bVar.d && this.f7811e == bVar.f7811e && this.f == bVar.f && this.h == bVar.h && this.i == bVar.i && this.l == bVar.l && this.j == bVar.j && this.k == bVar.k && this.g == bVar.g && TextUtils.equals(this.f7810a, bVar.f7810a) && TextUtils.equals(this.b, bVar.b);
        }

        public int hashCode() {
            return (((((((((((((((((((((this.f7810a.hashCode() * 31) + this.b.hashCode()) * 31) + (this.c ? 1 : 0)) * 31) + (this.d ? 1 : 0)) * 31) + this.f7811e) * 31) + this.f) * 31) + this.g) * 31) + (this.h ? 1 : 0)) * 31) + (this.i ? 1 : 0)) * 31) + (this.l ? 1 : 0)) * 31) + this.j) * 31) + this.k;
        }

        public b(String str, String str2, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, int i4, int i5, boolean z5) {
            this.f7810a = str;
            this.b = str2;
            this.c = z;
            this.d = z2;
            this.f7811e = i;
            this.f = i2;
            this.g = i3;
            this.h = z3;
            this.i = z4;
            this.j = i4;
            this.k = i5;
            this.l = z5;
        }
    }

    public h70(k70.a aVar) {
        this.f7808e = aVar;
    }

    public static int a(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    public static boolean a(int i, boolean z) {
        int i2 = i & 3;
        return i2 == 3 || (z && i2 == 2);
    }

    public static void b(pb0 pb0Var, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            int iIntValue = list.get(size).intValue();
            if (!a(pb0Var.a(iIntValue), str, iArr[iIntValue], i, i2, i3, i4)) {
                list.remove(size);
            }
        }
    }

    @Override // supwisdom.j70
    public k70[] a(i90[] i90VarArr, qb0[] qb0VarArr, int[][][] iArr) throws com.google.android.exoplayer2.e {
        b bVar;
        int i;
        b bVar2;
        k70[] k70VarArr;
        int i2;
        h70 h70Var = this;
        i90[] i90VarArr2 = i90VarArr;
        int length = i90VarArr2.length;
        k70[] k70VarArr2 = new k70[length];
        b bVar3 = h70Var.f.get();
        int i3 = 0;
        boolean z = false;
        while (i3 < length) {
            if (2 == i90VarArr2[i3].a()) {
                i = i3;
                k70VarArr = k70VarArr2;
                bVar2 = bVar3;
                i2 = length;
                k70VarArr[i] = a(i90VarArr2[i3], qb0VarArr[i3], iArr[i3], bVar3.f7811e, bVar3.f, bVar3.g, bVar3.d, bVar3.c, bVar3.j, bVar3.k, bVar3.l, h70Var.f7808e, bVar3.h, bVar3.i);
                z |= qb0VarArr[i].f8907a > 0;
            } else {
                i = i3;
                bVar2 = bVar3;
                k70VarArr = k70VarArr2;
                i2 = length;
            }
            i3 = i + 1;
            h70Var = this;
            i90VarArr2 = i90VarArr;
            k70VarArr2 = k70VarArr;
            bVar3 = bVar2;
            length = i2;
        }
        b bVar4 = bVar3;
        k70[] k70VarArr3 = k70VarArr2;
        int i4 = length;
        int i5 = 0;
        while (i5 < i4) {
            int iA = i90VarArr[i5].a();
            if (iA == 1) {
                bVar = bVar4;
                k70VarArr3[i5] = a(qb0VarArr[i5], iArr[i5], bVar.f7810a, bVar.i, bVar.c, z ? null : this.f7808e);
            } else if (iA == 2) {
                bVar = bVar4;
            } else if (iA != 3) {
                bVar = bVar4;
                k70VarArr3[i5] = a(i90VarArr[i5].a(), qb0VarArr[i5], iArr[i5], bVar.i);
            } else {
                bVar = bVar4;
                k70VarArr3[i5] = a(qb0VarArr[i5], iArr[i5], bVar.b, bVar.f7810a, bVar.i);
            }
            i5++;
            bVar4 = bVar;
        }
        return k70VarArr3;
    }

    public k70 a(i90 i90Var, qb0 qb0Var, int[][] iArr, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, boolean z3, k70.a aVar, boolean z4, boolean z5) throws com.google.android.exoplayer2.e {
        k70 k70VarA = aVar != null ? a(i90Var, qb0Var, iArr, i, i2, i3, z, z2, i4, i5, z3, aVar) : null;
        return k70VarA == null ? a(qb0Var, iArr, i, i2, i3, i4, i5, z3, z4, z5) : k70VarA;
    }

    public static k70 a(i90 i90Var, qb0 qb0Var, int[][] iArr, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, boolean z3, k70.a aVar) throws com.google.android.exoplayer2.e {
        int i6 = z ? 12 : 8;
        boolean z4 = z2 && (i90Var.m() & i6) != 0;
        for (int i7 = 0; i7 < qb0Var.f8907a; i7++) {
            pb0 pb0VarA = qb0Var.a(i7);
            int[] iArrA = a(pb0VarA, iArr[i7], z4, i6, i, i2, i3, i4, i5, z3);
            if (iArrA.length > 0) {
                return aVar.a(pb0VarA, iArrA);
            }
        }
        return null;
    }

    public static int[] a(pb0 pb0Var, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        String str;
        int iA;
        if (pb0Var.f8788a < 2) {
            return g;
        }
        List<Integer> listA = a(pb0Var, i5, i6, z2);
        if (listA.size() < 2) {
            return g;
        }
        if (z) {
            str = null;
        } else {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i7 = 0;
            for (int i8 = 0; i8 < listA.size(); i8++) {
                String str3 = pb0Var.a(listA.get(i8).intValue()).f;
                if (hashSet.add(str3) && (iA = a(pb0Var, iArr, i, str3, i2, i3, i4, listA)) > i7) {
                    i7 = iA;
                    str2 = str3;
                }
            }
            str = str2;
        }
        b(pb0Var, iArr, i, str, i2, i3, i4, listA);
        return listA.size() < 2 ? g : x80.a(listA);
    }

    public static int a(pb0 pb0Var, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        int i5 = 0;
        for (int i6 = 0; i6 < list.size(); i6++) {
            int iIntValue = list.get(i6).intValue();
            if (a(pb0Var.a(iIntValue), str, iArr[iIntValue], i, i2, i3, i4)) {
                i5++;
            }
        }
        return i5;
    }

    public static boolean a(com.google.android.exoplayer2.j jVar, String str, int i, int i2, int i3, int i4, int i5) {
        if (!a(i, false) || (i & i2) == 0) {
            return false;
        }
        if (str != null && !x80.a(jVar.f, str)) {
            return false;
        }
        int i6 = jVar.j;
        if (i6 != -1 && i6 > i3) {
            return false;
        }
        int i7 = jVar.k;
        if (i7 != -1 && i7 > i4) {
            return false;
        }
        int i8 = jVar.b;
        return i8 == -1 || i8 <= i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00bd A[PHI: r17 r18
  0x00bd: PHI (r17v2 supwisdom.pb0) = (r17v1 supwisdom.pb0), (r17v4 supwisdom.pb0) binds: [B:59:0x00b8, B:57:0x00a9] A[DONT_GENERATE, DONT_INLINE]
  0x00bd: PHI (r18v1 supwisdom.pb0) = (r18v0 supwisdom.pb0), (r18v3 supwisdom.pb0) binds: [B:59:0x00b8, B:57:0x00a9] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.k70 a(supwisdom.qb0 r19, int[][] r20, int r21, int r22, int r23, int r24, int r25, boolean r26, boolean r27, boolean r28) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.h70.a(supwisdom.qb0, int[][], int, int, int, int, int, boolean, boolean, boolean):supwisdom.k70");
    }

    public k70 a(qb0 qb0Var, int[][] iArr, String str, boolean z, boolean z2, k70.a aVar) {
        int i = -1;
        int i2 = -1;
        int i3 = 0;
        for (int i4 = 0; i4 < qb0Var.f8907a; i4++) {
            pb0 pb0VarA = qb0Var.a(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < pb0VarA.f8788a; i5++) {
                if (a(iArr2[i5], z)) {
                    int iA = a(iArr2[i5], str, pb0VarA.a(i5));
                    if (iA > i3) {
                        i = i4;
                        i2 = i5;
                        i3 = iA;
                    }
                }
            }
        }
        if (i == -1) {
            return null;
        }
        pb0 pb0VarA2 = qb0Var.a(i);
        if (aVar != null) {
            int[] iArrA = a(pb0VarA2, iArr[i], z2);
            if (iArrA.length > 0) {
                return aVar.a(pb0VarA2, iArrA);
            }
        }
        return new i70(pb0VarA2, i2);
    }

    public static int a(int i, String str, com.google.android.exoplayer2.j jVar) {
        int i2 = 1;
        boolean z = (jVar.x & 1) != 0;
        if (a(jVar, str)) {
            i2 = z ? 4 : 3;
        } else if (z) {
            i2 = 2;
        }
        return a(i, false) ? i2 + 1000 : i2;
    }

    public static int[] a(pb0 pb0Var, int[] iArr, boolean z) {
        int iA;
        HashSet hashSet = new HashSet();
        a aVar = null;
        int i = 0;
        for (int i2 = 0; i2 < pb0Var.f8788a; i2++) {
            com.google.android.exoplayer2.j jVarA = pb0Var.a(i2);
            a aVar2 = new a(jVarA.r, jVarA.s, z ? null : jVarA.f);
            if (hashSet.add(aVar2) && (iA = a(pb0Var, iArr, aVar2)) > i) {
                i = iA;
                aVar = aVar2;
            }
        }
        if (i > 1) {
            int[] iArr2 = new int[i];
            int i3 = 0;
            for (int i4 = 0; i4 < pb0Var.f8788a; i4++) {
                if (a(pb0Var.a(i4), iArr[i4], aVar)) {
                    iArr2[i3] = i4;
                    i3++;
                }
            }
            return iArr2;
        }
        return g;
    }

    public static int a(pb0 pb0Var, int[] iArr, a aVar) {
        int i = 0;
        for (int i2 = 0; i2 < pb0Var.f8788a; i2++) {
            if (a(pb0Var.a(i2), iArr[i2], aVar)) {
                i++;
            }
        }
        return i;
    }

    public static boolean a(com.google.android.exoplayer2.j jVar, int i, a aVar) {
        if (!a(i, false) || jVar.r != aVar.f7809a || jVar.s != aVar.b) {
            return false;
        }
        String str = aVar.c;
        return str == null || TextUtils.equals(str, jVar.f);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0072 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.k70 a(supwisdom.qb0 r19, int[][] r20, java.lang.String r21, java.lang.String r22, boolean r23) {
        /*
            r18 = this;
            r0 = r19
            r1 = 0
            r2 = 0
            r4 = r1
            r3 = 0
            r5 = 0
            r6 = 0
        L8:
            int r7 = r0.f8907a
            if (r3 >= r7) goto L7e
            supwisdom.pb0 r7 = r0.a(r3)
            r8 = r20[r3]
            r9 = 0
        L13:
            int r10 = r7.f8788a
            if (r9 >= r10) goto L75
            r10 = r8[r9]
            r11 = r23
            boolean r10 = a(r10, r11)
            if (r10 == 0) goto L6e
            com.google.android.exoplayer2.j r10 = r7.a(r9)
            int r12 = r10.x
            r13 = 1
            r12 = r12 & r13
            if (r12 == 0) goto L2d
            r12 = 1
            goto L2e
        L2d:
            r12 = 0
        L2e:
            int r14 = r10.x
            r15 = 2
            r14 = r14 & r15
            if (r14 == 0) goto L39
            r14 = r21
            r16 = 1
            goto L3d
        L39:
            r14 = r21
            r16 = 0
        L3d:
            boolean r17 = a(r10, r14)
            if (r17 == 0) goto L4d
            if (r12 == 0) goto L47
            r13 = 6
            goto L50
        L47:
            if (r16 != 0) goto L4b
            r13 = 5
            goto L50
        L4b:
            r13 = 4
            goto L50
        L4d:
            if (r12 == 0) goto L53
            r13 = 3
        L50:
            r12 = r22
            goto L5e
        L53:
            r12 = r22
            if (r16 == 0) goto L72
            boolean r10 = a(r10, r12)
            if (r10 == 0) goto L5e
            r13 = 2
        L5e:
            r10 = r8[r9]
            boolean r10 = a(r10, r2)
            if (r10 == 0) goto L68
            int r13 = r13 + 1000
        L68:
            if (r13 <= r6) goto L72
            r4 = r7
            r5 = r9
            r6 = r13
            goto L72
        L6e:
            r14 = r21
            r12 = r22
        L72:
            int r9 = r9 + 1
            goto L13
        L75:
            r14 = r21
            r12 = r22
            r11 = r23
            int r3 = r3 + 1
            goto L8
        L7e:
            if (r4 != 0) goto L81
            goto L86
        L81:
            supwisdom.i70 r1 = new supwisdom.i70
            r1.<init>(r4, r5)
        L86:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.h70.a(supwisdom.qb0, int[][], java.lang.String, java.lang.String, boolean):supwisdom.k70");
    }

    public k70 a(int i, qb0 qb0Var, int[][] iArr, boolean z) {
        pb0 pb0Var = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < qb0Var.f8907a; i4++) {
            pb0 pb0VarA = qb0Var.a(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < pb0VarA.f8788a; i5++) {
                if (a(iArr2[i5], z)) {
                    int i6 = (pb0VarA.a(i5).x & 1) != 0 ? 2 : 1;
                    if (a(iArr2[i5], false)) {
                        i6 += 1000;
                    }
                    if (i6 > i3) {
                        pb0Var = pb0VarA;
                        i2 = i5;
                        i3 = i6;
                    }
                }
            }
        }
        if (pb0Var == null) {
            return null;
        }
        return new i70(pb0Var, i2);
    }

    public static boolean a(com.google.android.exoplayer2.j jVar, String str) {
        return str != null && TextUtils.equals(str, x80.b(jVar.y));
    }

    public static List<Integer> a(pb0 pb0Var, int i, int i2, boolean z) {
        int i3;
        ArrayList arrayList = new ArrayList(pb0Var.f8788a);
        for (int i4 = 0; i4 < pb0Var.f8788a; i4++) {
            arrayList.add(Integer.valueOf(i4));
        }
        if (i != Integer.MAX_VALUE && i2 != Integer.MAX_VALUE) {
            int i5 = Integer.MAX_VALUE;
            for (int i6 = 0; i6 < pb0Var.f8788a; i6++) {
                com.google.android.exoplayer2.j jVarA = pb0Var.a(i6);
                int i7 = jVarA.j;
                if (i7 > 0 && (i3 = jVarA.k) > 0) {
                    Point pointA = a(z, i, i2, i7, i3);
                    int i8 = jVarA.j;
                    int i9 = jVarA.k;
                    int i10 = i8 * i9;
                    if (i8 >= ((int) (pointA.x * 0.98f)) && i9 >= ((int) (pointA.y * 0.98f)) && i10 < i5) {
                        i5 = i10;
                    }
                }
            }
            if (i5 != Integer.MAX_VALUE) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    int iA = pb0Var.a(((Integer) arrayList.get(size)).intValue()).a();
                    if (iA == -1 || iA > i5) {
                        arrayList.remove(size);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Point a(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L10
            r3 = 1
            r0 = 0
            if (r6 <= r7) goto L8
            r1 = 1
            goto L9
        L8:
            r1 = 0
        L9:
            if (r4 <= r5) goto Lc
            goto Ld
        Lc:
            r3 = 0
        Ld:
            if (r1 == r3) goto L10
            goto L13
        L10:
            r2 = r5
            r5 = r4
            r4 = r2
        L13:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L23
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = supwisdom.x80.a(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L23:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = supwisdom.x80.a(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.h70.a(boolean, int, int, int, int):android.graphics.Point");
    }
}
