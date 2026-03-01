package supwisdom;

import com.sangfor.dex.DexException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import supwisdom.jz0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class vz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cz0 f9568a;
    public final int b;
    public ArrayList<kz0> c;
    public int g;
    public final int h;
    public int f = -1;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9569e = false;

    public vz0(cz0 cz0Var, int i, int i2, int i3) {
        this.f9568a = cz0Var;
        this.b = i2;
        this.c = new ArrayList<>(i);
        this.h = i3;
    }

    public void a(kz0 kz0Var) {
        this.c.add(kz0Var);
        c(kz0Var);
    }

    public final void b(int i) {
        c(i);
        this.f += i;
    }

    public lz0 c() {
        if (this.f >= 0) {
            throw new UnsupportedOperationException("already processed");
        }
        mz0[] mz0VarArrH = h();
        e(mz0VarArrH);
        if (this.f9568a.f7273a) {
            a(mz0VarArrH);
        }
        c(mz0VarArrH);
        b();
        return lz0.a(this.c, this.f + this.b + this.g);
    }

    public final boolean d() {
        int size = this.c.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            kz0 kz0Var = this.c.get(i);
            if (kz0Var instanceof b01) {
                mz0 mz0VarH = kz0Var.h();
                b01 b01Var = (b01) kz0Var;
                if (mz0VarH.b().a(b01Var)) {
                    continue;
                } else {
                    if (mz0VarH.a() == 40) {
                        mz0 mz0VarA = a(kz0Var, mz0VarH);
                        if (mz0VarA == null) {
                            throw new UnsupportedOperationException("method too long");
                        }
                        this.c.set(i, kz0Var.a(mz0VarA));
                    } else {
                        try {
                            int i2 = i + 1;
                            hz0 hz0Var = (hz0) this.c.get(i2);
                            this.c.set(i, new b01(nz0.Q, b01Var.i(), m41.c, b01Var.n()));
                            this.c.add(i, b01Var.a(hz0Var));
                            size++;
                            i = i2;
                        } catch (ClassCastException unused) {
                            throw new IllegalStateException("unpaired TargetInsn");
                        } catch (IndexOutOfBoundsException unused2) {
                            throw new IllegalStateException("unpaired TargetInsn (dangling)");
                        }
                    }
                    z = true;
                }
            }
            i++;
        }
        return z;
    }

    public HashSet<u41> e() {
        HashSet<u41> hashSet = new HashSet<>(20);
        Iterator<kz0> it = this.c.iterator();
        while (it.hasNext()) {
            a(hashSet, it.next());
        }
        return hashSet;
    }

    public boolean f() {
        return this.f9569e;
    }

    public boolean g() {
        return this.d;
    }

    public final mz0[] h() {
        int size = this.c.size();
        mz0[] mz0VarArr = new mz0[size];
        for (int i = 0; i < size; i++) {
            mz0VarArr[i] = this.c.get(i).h();
        }
        return mz0VarArr;
    }

    public static void a(HashSet<u41> hashSet, kz0 kz0Var) {
        if (kz0Var instanceof iz0) {
            hashSet.add(((iz0) kz0Var).n());
            return;
        }
        int i = 0;
        if (kz0Var instanceof tz0) {
            tz0 tz0Var = (tz0) kz0Var;
            while (i < tz0Var.n()) {
                hashSet.add(tz0Var.c(i));
                i++;
            }
            return;
        }
        if (kz0Var instanceof rz0) {
            n41 n41VarN = ((rz0) kz0Var).n();
            int size = n41VarN.size();
            while (i < size) {
                a(hashSet, n41VarN.a(i));
                i++;
            }
            return;
        }
        if (kz0Var instanceof sz0) {
            a(hashSet, ((sz0) kz0Var).n());
        }
    }

    public final void b() {
        do {
            a();
        } while (d());
    }

    public final boolean e(mz0[] mz0VarArr) {
        int i = this.f;
        if (i < 0) {
            i = 0;
        }
        boolean z = false;
        while (true) {
            int iB = b(mz0VarArr);
            if (i >= iB) {
                this.f = i;
                return z;
            }
            int i2 = iB - i;
            int size = this.c.size();
            for (int i3 = 0; i3 < size; i3++) {
                kz0 kz0Var = this.c.get(i3);
                if (!(kz0Var instanceof hz0)) {
                    this.c.set(i3, kz0Var.b(i2));
                }
            }
            z = true;
            i = iB;
        }
    }

    public final int b(mz0[] mz0VarArr) {
        int size = this.c.size();
        int i = this.f;
        for (int i2 = 0; i2 < size; i2++) {
            kz0 kz0Var = this.c.get(i2);
            mz0 mz0Var = mz0VarArr[i2];
            mz0 mz0VarA = a(kz0Var, mz0Var);
            if (mz0VarA == null) {
                int iD = kz0Var.d(b(kz0Var).b().a(kz0Var));
                if (iD > i) {
                    i = iD;
                }
            } else {
                if (mz0Var == mz0VarA) {
                }
            }
            mz0VarArr[i2] = mz0VarA;
        }
        return i;
    }

    public final void c(mz0[] mz0VarArr) {
        if (this.f == 0) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                kz0 kz0Var = this.c.get(i);
                mz0 mz0VarH = kz0Var.h();
                mz0 mz0Var = mz0VarArr[i];
                if (mz0VarH != mz0Var) {
                    this.c.set(i, kz0Var.a(mz0Var));
                }
            }
            return;
        }
        this.c = d(mz0VarArr);
    }

    public final mz0 b(kz0 kz0Var) {
        mz0 mz0VarA = a(kz0Var.f(), kz0Var.h());
        if (mz0VarA != null) {
            return mz0VarA;
        }
        throw new DexException("No expanded opcode for " + kz0Var);
    }

    public final void a(int i) {
        d(i);
        this.g += i;
    }

    public final void c(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            kz0 kz0Var = this.c.get(i2);
            if (!(kz0Var instanceof hz0)) {
                this.c.set(i2, kz0Var.b(i));
            }
        }
    }

    public static boolean d(kz0 kz0Var) {
        if (kz0Var instanceof rz0) {
            n41 n41VarN = ((rz0) kz0Var).n();
            int size = n41VarN.size();
            for (int i = 0; i < size; i++) {
                if (a(n41VarN.a(i))) {
                    return true;
                }
            }
        } else if ((kz0Var instanceof sz0) && a(((sz0) kz0Var).n())) {
            return true;
        }
        return false;
    }

    public final void a(mz0[] mz0VarArr) {
        do {
            int i = ((this.b + this.f) + this.g) - this.h;
            Iterator<kz0> it = this.c.iterator();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (it.hasNext()) {
                m41 m41VarJ = it.next().j();
                for (int i6 = 0; i6 < m41VarJ.size(); i6++) {
                    l41 l41VarD = m41VarJ.d(i6);
                    if (l41VarD.g()) {
                        boolean z = l41VarD.f() >= i;
                        if (l41VarD.h()) {
                            if (z) {
                                i3++;
                            } else {
                                i5++;
                            }
                        } else if (z) {
                            i2++;
                        } else {
                            i4++;
                        }
                    }
                }
            }
            if (i2 > i3 && i4 > i5) {
                b(1);
            } else if (i2 > i3) {
                a(1);
            } else {
                if (i4 <= i5) {
                    return;
                }
                b(1);
                if (this.h != 0 && i3 > i2) {
                    a(1);
                }
            }
        } while (e(mz0VarArr));
    }

    public final void c(kz0 kz0Var) {
        if (!this.d && kz0Var.i().a() >= 0) {
            this.d = true;
        }
        if (this.f9569e || !d(kz0Var)) {
            return;
        }
        this.f9569e = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList<supwisdom.kz0> d(supwisdom.mz0[] r12) {
        /*
            r11 = this;
            java.util.ArrayList<supwisdom.kz0> r0 = r11.c
            int r0 = r0.size()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0 * 2
            r1.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
        L13:
            if (r3 >= r0) goto L89
            java.util.ArrayList<supwisdom.kz0> r4 = r11.c
            java.lang.Object r4 = r4.get(r3)
            supwisdom.kz0 r4 = (supwisdom.kz0) r4
            supwisdom.mz0 r5 = r4.h()
            r6 = r12[r3]
            r7 = 0
            if (r6 == 0) goto L28
            r9 = r7
            goto L41
        L28:
            supwisdom.mz0 r6 = r11.b(r4)
            supwisdom.qz0 r7 = r6.b()
            java.util.BitSet r7 = r7.a(r4)
            supwisdom.kz0 r8 = r4.a(r7)
            supwisdom.kz0 r9 = r4.b(r7)
            supwisdom.kz0 r4 = r4.c(r7)
            r7 = r8
        L41:
            boolean r8 = r4 instanceof supwisdom.hz0
            if (r8 == 0) goto L52
            r8 = r4
            supwisdom.hz0 r8 = (supwisdom.hz0) r8
            boolean r10 = r8.n()
            if (r10 == 0) goto L52
            r2.add(r8)
            goto L86
        L52:
            if (r7 == 0) goto L57
            r1.add(r7)
        L57:
            boolean r7 = r4 instanceof supwisdom.d01
            if (r7 != 0) goto L78
            int r7 = r2.size()
            if (r7 <= 0) goto L78
            java.util.Iterator r7 = r2.iterator()
        L65:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L75
            java.lang.Object r8 = r7.next()
            supwisdom.hz0 r8 = (supwisdom.hz0) r8
            r1.add(r8)
            goto L65
        L75:
            r2.clear()
        L78:
            if (r6 == r5) goto L7e
            supwisdom.kz0 r4 = r4.a(r6)
        L7e:
            r1.add(r4)
            if (r9 == 0) goto L86
            r1.add(r9)
        L86:
            int r3 = r3 + 1
            goto L13
        L89:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.vz0.d(supwisdom.mz0[]):java.util.ArrayList");
    }

    public final void a() {
        int size = this.c.size();
        int iB = 0;
        for (int i = 0; i < size; i++) {
            kz0 kz0Var = this.c.get(i);
            kz0Var.a(iB);
            iB += kz0Var.b();
        }
    }

    public void a(jz0.a aVar) {
        for (kz0 kz0Var : this.c) {
            if (kz0Var instanceof iz0) {
                a((iz0) kz0Var, aVar);
            } else if (kz0Var instanceof tz0) {
                a((tz0) kz0Var, aVar);
            }
        }
    }

    public final mz0 a(kz0 kz0Var, mz0 mz0Var) {
        while (mz0Var != null && (!mz0Var.b().c(kz0Var) || (this.f9568a.c && mz0Var.e() == 26))) {
            mz0Var = nz0.a(mz0Var, this.f9568a);
        }
        return mz0Var;
    }

    public void a(int i, hz0 hz0Var) {
        int size = (this.c.size() - i) - 1;
        try {
            this.c.set(size, ((b01) this.c.get(size)).a(hz0Var));
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException("non-reversible instruction");
        } catch (IndexOutOfBoundsException unused2) {
            throw new IllegalArgumentException("too few instructions");
        }
    }

    public final void d(int i) {
        int size = this.c.size();
        int i2 = this.b + this.f + this.g;
        int i3 = i2 - this.h;
        e61 e61Var = new e61(i2);
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 >= i3) {
                e61Var.a(i4, i4 + i, 1);
            } else {
                e61Var.a(i4, i4, 1);
            }
        }
        for (int i5 = 0; i5 < size; i5++) {
            kz0 kz0Var = this.c.get(i5);
            if (!(kz0Var instanceof hz0)) {
                this.c.set(i5, kz0Var.a(e61Var));
            }
        }
    }

    public static void a(iz0 iz0Var, jz0.a aVar) {
        int iA;
        u41 u41VarN = iz0Var.n();
        int iA2 = aVar.a(u41VarN);
        if (iA2 >= 0) {
            iz0Var.d(iA2);
        }
        if (!(u41VarN instanceof p51) || (iA = aVar.a(((p51) u41VarN).d())) < 0) {
            return;
        }
        iz0Var.c(iA);
    }

    public static boolean a(l41 l41Var) {
        if (l41Var == null) {
            return false;
        }
        l41Var.d().a();
        throw null;
    }

    public static void a(HashSet<u41> hashSet, l41 l41Var) {
        if (l41Var == null) {
            return;
        }
        l41Var.d().a();
        throw null;
    }

    public static void a(tz0 tz0Var, jz0.a aVar) {
        for (int i = 0; i < tz0Var.n(); i++) {
            u41 u41VarC = tz0Var.c(i);
            tz0Var.a(i, aVar.a(u41VarC));
            if (u41VarC instanceof p51) {
                tz0Var.f(aVar.a(((p51) u41VarC).d()));
            }
        }
    }
}
