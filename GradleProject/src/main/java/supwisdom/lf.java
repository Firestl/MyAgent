package supwisdom;

import java.util.List;
import supwisdom.af;

/* JADX INFO: compiled from: OpReorderer.java */
/* JADX INFO: loaded from: classes.dex */
public class lf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f8280a;

    /* JADX INFO: compiled from: OpReorderer.java */
    public interface a {
        af.b a(int i, int i2, int i3, Object obj);

        void a(af.b bVar);
    }

    public lf(a aVar) {
        this.f8280a = aVar;
    }

    public final void a(List<af.b> list, int i, int i2) {
        af.b bVar = list.get(i);
        af.b bVar2 = list.get(i2);
        int i3 = bVar2.f6894a;
        if (i3 == 1) {
            a(list, i, bVar, i2, bVar2);
        } else if (i3 == 2) {
            b(list, i, bVar, i2, bVar2);
        } else {
            if (i3 != 4) {
                return;
            }
            c(list, i, bVar, i2, bVar2);
        }
    }

    public void b(List<af.b> list) {
        while (true) {
            int iA = a(list);
            if (iA == -1) {
                return;
            } else {
                a(list, iA, iA + 1);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.util.List<supwisdom.af.b> r9, int r10, supwisdom.af.b r11, int r12, supwisdom.af.b r13) {
        /*
            r8 = this;
            int r0 = r11.d
            int r1 = r13.b
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.b = r1
            goto L20
        Ld:
            int r5 = r13.d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.d = r5
            supwisdom.lf$a r0 = r8.f8280a
            int r1 = r11.b
            java.lang.Object r5 = r13.c
            supwisdom.af$b r0 = r0.a(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.b
            int r5 = r13.b
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.b = r5
            goto L41
        L2b:
            int r6 = r13.d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            supwisdom.lf$a r3 = r8.f8280a
            int r1 = r1 + r4
            java.lang.Object r4 = r13.c
            supwisdom.af$b r3 = r3.a(r2, r1, r5, r4)
            int r1 = r13.d
            int r1 = r1 - r5
            r13.d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            supwisdom.lf$a r11 = r8.f8280a
            r11.a(r13)
        L54:
            if (r0 == 0) goto L59
            r9.add(r10, r0)
        L59:
            if (r3 == 0) goto L5e
            r9.add(r10, r3)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.lf.c(java.util.List, int, supwisdom.af$b, int, supwisdom.af$b):void");
    }

    public void b(List<af.b> list, int i, af.b bVar, int i2, af.b bVar2) {
        boolean z;
        int i3 = bVar.b;
        int i4 = bVar.d;
        boolean z2 = false;
        if (i3 < i4) {
            if (bVar2.b == i3 && bVar2.d == i4 - i3) {
                z = false;
                z2 = true;
            } else {
                z = false;
            }
        } else if (bVar2.b == i4 + 1 && bVar2.d == i3 - i4) {
            z = true;
            z2 = true;
        } else {
            z = true;
        }
        int i5 = bVar.d;
        int i6 = bVar2.b;
        if (i5 < i6) {
            bVar2.b = i6 - 1;
        } else {
            int i7 = bVar2.d;
            if (i5 < i6 + i7) {
                bVar2.d = i7 - 1;
                bVar.f6894a = 2;
                bVar.d = 1;
                if (bVar2.d == 0) {
                    list.remove(i2);
                    this.f8280a.a(bVar2);
                    return;
                }
                return;
            }
        }
        int i8 = bVar.b;
        int i9 = bVar2.b;
        af.b bVarA = null;
        if (i8 <= i9) {
            bVar2.b = i9 + 1;
        } else {
            int i10 = bVar2.d;
            if (i8 < i9 + i10) {
                bVarA = this.f8280a.a(2, i8 + 1, (i9 + i10) - i8, null);
                bVar2.d = bVar.b - bVar2.b;
            }
        }
        if (z2) {
            list.set(i, bVar2);
            list.remove(i2);
            this.f8280a.a(bVar);
            return;
        }
        if (z) {
            if (bVarA != null) {
                int i11 = bVar.b;
                if (i11 > bVarA.b) {
                    bVar.b = i11 - bVarA.d;
                }
                int i12 = bVar.d;
                if (i12 > bVarA.b) {
                    bVar.d = i12 - bVarA.d;
                }
            }
            int i13 = bVar.b;
            if (i13 > bVar2.b) {
                bVar.b = i13 - bVar2.d;
            }
            int i14 = bVar.d;
            if (i14 > bVar2.b) {
                bVar.d = i14 - bVar2.d;
            }
        } else {
            if (bVarA != null) {
                int i15 = bVar.b;
                if (i15 >= bVarA.b) {
                    bVar.b = i15 - bVarA.d;
                }
                int i16 = bVar.d;
                if (i16 >= bVarA.b) {
                    bVar.d = i16 - bVarA.d;
                }
            }
            int i17 = bVar.b;
            if (i17 >= bVar2.b) {
                bVar.b = i17 - bVar2.d;
            }
            int i18 = bVar.d;
            if (i18 >= bVar2.b) {
                bVar.d = i18 - bVar2.d;
            }
        }
        list.set(i, bVar2);
        if (bVar.b != bVar.d) {
            list.set(i2, bVar);
        } else {
            list.remove(i2);
        }
        if (bVarA != null) {
            list.add(i, bVarA);
        }
    }

    public final void a(List<af.b> list, int i, af.b bVar, int i2, af.b bVar2) {
        int i3 = bVar.d < bVar2.b ? -1 : 0;
        if (bVar.b < bVar2.b) {
            i3++;
        }
        int i4 = bVar2.b;
        int i5 = bVar.b;
        if (i4 <= i5) {
            bVar.b = i5 + bVar2.d;
        }
        int i6 = bVar2.b;
        int i7 = bVar.d;
        if (i6 <= i7) {
            bVar.d = i7 + bVar2.d;
        }
        bVar2.b += i3;
        list.set(i, bVar2);
        list.set(i2, bVar);
    }

    public final int a(List<af.b> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f6894a != 8) {
                z = true;
            } else if (z) {
                return size;
            }
        }
        return -1;
    }
}
