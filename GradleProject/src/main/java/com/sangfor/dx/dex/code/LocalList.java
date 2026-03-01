package com.sangfor.dx.dex.code;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import supwisdom.b61;
import supwisdom.kz0;
import supwisdom.l41;
import supwisdom.l61;
import supwisdom.lz0;
import supwisdom.n41;
import supwisdom.rz0;
import supwisdom.sz0;
import supwisdom.v51;
import supwisdom.w51;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class LocalList extends l61 {
    public static final LocalList c = new LocalList(0);

    /* JADX INFO: compiled from: Proguard */
    public enum Disposition {
        START,
        END_SIMPLY,
        END_REPLACED,
        END_MOVED,
        END_CLOBBERED_BY_PREV,
        END_CLOBBERED_BY_NEXT
    }

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3879a;
        public final Disposition b;
        public final l41 c;
        public final w51 d;

        public a(int i, Disposition disposition, l41 l41Var) {
            if (i < 0) {
                throw new IllegalArgumentException("address < 0");
            }
            if (disposition == null) {
                throw new NullPointerException("disposition == null");
            }
            try {
                if (l41Var.d() == null) {
                    throw new NullPointerException("spec.getLocalItem() == null");
                }
                this.f3879a = i;
                this.b = disposition;
                this.c = l41Var;
                this.d = w51.a(l41Var.getType());
            } catch (NullPointerException unused) {
                throw new NullPointerException("spec == null");
            }
        }

        public int a() {
            return this.f3879a;
        }

        public Disposition b() {
            return this.b;
        }

        public v51 c() {
            this.c.d().a();
            throw null;
        }

        public int d() {
            return this.c.f();
        }

        public l41 e() {
            return this.c;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && compareTo((a) obj) == 0;
        }

        public v51 f() {
            this.c.d().b();
            throw null;
        }

        public boolean g() {
            return this.b == Disposition.START;
        }

        public String toString() {
            return Integer.toHexString(this.f3879a) + Operators.SPACE_STR + this.b + Operators.SPACE_STR + this.c;
        }

        public boolean a(l41 l41Var) {
            return this.c.b(l41Var);
        }

        public boolean b(a aVar) {
            return a(aVar.c);
        }

        public a a(Disposition disposition) {
            return disposition == this.b ? this : new a(this.f3879a, disposition, this.c);
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            int i = this.f3879a;
            int i2 = aVar.f3879a;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            boolean zG = g();
            if (zG != aVar.g()) {
                return zG ? 1 : -1;
            }
            return this.c.compareTo(aVar.c);
        }
    }

    public LocalList(int i) {
        super(i);
    }

    public static LocalList a(lz0 lz0Var) {
        int size = lz0Var.size();
        b bVar = new b(size);
        for (int i = 0; i < size; i++) {
            kz0 kz0VarD = lz0Var.d(i);
            if (kz0VarD instanceof rz0) {
                bVar.a(kz0VarD.e(), ((rz0) kz0VarD).n());
            } else if (kz0VarD instanceof sz0) {
                bVar.c(kz0VarD.e(), ((sz0) kz0VarD).n());
            }
        }
        return bVar.a();
    }

    public a d(int i) {
        return (a) a(i);
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<a> f3880a;
        public int b = 0;
        public n41 c = null;
        public int[] d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f3881e = 0;

        public b(int i) {
            this.f3880a = new ArrayList<>(i);
        }

        public final void a(int i, int i2) {
            int[] iArr = this.d;
            boolean z = iArr == null;
            int i3 = this.f3881e;
            if (i != i3 || z) {
                if (i < i3) {
                    throw new RuntimeException("shouldn't happen");
                }
                if (z || i2 >= iArr.length) {
                    int i4 = i2 + 1;
                    n41 n41Var = new n41(i4);
                    int[] iArr2 = new int[i4];
                    Arrays.fill(iArr2, -1);
                    if (!z) {
                        n41Var.a(this.c);
                        int[] iArr3 = this.d;
                        System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
                    }
                    this.c = n41Var;
                    this.d = iArr2;
                }
            }
        }

        public final void b(int i, Disposition disposition, l41 l41Var) {
            if (disposition == Disposition.START) {
                throw new RuntimeException("shouldn't happen");
            }
            int i2 = this.d[l41Var.f()];
            if (i2 >= 0) {
                a aVar = this.f3880a.get(i2);
                if (aVar.a() == i && aVar.e().equals(l41Var)) {
                    this.f3880a.set(i2, aVar.a(disposition));
                    this.c.c(l41Var);
                    return;
                }
            }
            a(i, l41Var, disposition);
        }

        public void c(int i, l41 l41Var) {
            l41 l41VarA;
            l41 l41VarA2;
            int iF = l41Var.f();
            l41 l41VarA3 = a(l41Var);
            a(i, iF);
            l41 l41VarA4 = this.c.a(iF);
            if (l41VarA3.b(l41VarA4)) {
                return;
            }
            l41 l41VarA5 = this.c.a(l41VarA3);
            if (l41VarA5 != null) {
                b(i, Disposition.END_MOVED, l41VarA5);
            }
            int i2 = this.d[iF];
            if (l41VarA4 != null) {
                a(i, Disposition.END_REPLACED, l41VarA4);
            } else if (i2 >= 0) {
                a aVar = this.f3880a.get(i2);
                if (aVar.a() == i) {
                    if (aVar.a(l41VarA3)) {
                        this.f3880a.set(i2, null);
                        this.b++;
                        this.c.b(l41VarA3);
                        this.d[iF] = -1;
                        return;
                    }
                    this.f3880a.set(i2, aVar.a(Disposition.END_REPLACED));
                }
            }
            if (iF > 0 && (l41VarA2 = this.c.a(iF - 1)) != null && l41VarA2.g()) {
                b(i, Disposition.END_CLOBBERED_BY_NEXT, l41VarA2);
            }
            if (l41VarA3.g() && (l41VarA = this.c.a(iF + 1)) != null) {
                b(i, Disposition.END_CLOBBERED_BY_PREV, l41VarA);
            }
            a(i, Disposition.START, l41VarA3);
        }

        public void b(int i, l41 l41Var) {
            a(i, l41Var, Disposition.END_SIMPLY);
        }

        public final void a(int i, Disposition disposition, l41 l41Var) {
            int iF = l41Var.f();
            this.f3880a.add(new a(i, disposition, l41Var));
            if (disposition == Disposition.START) {
                this.c.b(l41Var);
                this.d[iF] = -1;
            } else {
                this.c.c(l41Var);
                this.d[iF] = this.f3880a.size() - 1;
            }
        }

        public final boolean a(int i, l41 l41Var) {
            boolean z;
            int size = this.f3880a.size() - 1;
            while (true) {
                z = false;
                if (size < 0) {
                    break;
                }
                a aVar = this.f3880a.get(size);
                if (aVar != null) {
                    if (aVar.a() != i) {
                        return false;
                    }
                    if (aVar.a(l41Var)) {
                        break;
                    }
                }
                size--;
            }
            this.c.c(l41Var);
            a aVar2 = null;
            this.f3880a.set(size, null);
            this.b++;
            int iF = l41Var.f();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                aVar2 = this.f3880a.get(size);
                if (aVar2 != null && aVar2.e().f() == iF) {
                    z = true;
                    break;
                }
            }
            if (z) {
                this.d[iF] = size;
                if (aVar2.a() == i) {
                    this.f3880a.set(size, aVar2.a(Disposition.END_SIMPLY));
                }
            }
            return true;
        }

        public static l41 a(l41 l41Var) {
            return (l41Var == null || l41Var.getType() != b61.p) ? l41Var : l41Var.a(b61.x);
        }

        public LocalList a() {
            a(Integer.MAX_VALUE, 0);
            int size = this.f3880a.size();
            int i = size - this.b;
            if (i == 0) {
                return LocalList.c;
            }
            a[] aVarArr = new a[i];
            if (size == i) {
                this.f3880a.toArray(aVarArr);
            } else {
                int i2 = 0;
                for (a aVar : this.f3880a) {
                    if (aVar != null) {
                        aVarArr[i2] = aVar;
                        i2++;
                    }
                }
            }
            Arrays.sort(aVarArr);
            LocalList localList = new LocalList(i);
            for (int i3 = 0; i3 < i; i3++) {
                localList.a(i3, aVarArr[i3]);
            }
            localList.e();
            return localList;
        }

        public void a(int i, n41 n41Var) {
            int iH = n41Var.h();
            a(i, iH - 1);
            for (int i2 = 0; i2 < iH; i2++) {
                l41 l41VarA = this.c.a(i2);
                l41 l41VarA2 = a(n41Var.a(i2));
                if (l41VarA == null) {
                    if (l41VarA2 != null) {
                        c(i, l41VarA2);
                    }
                } else if (l41VarA2 == null) {
                    b(i, l41VarA);
                } else if (!l41VarA2.b(l41VarA)) {
                    b(i, l41VarA);
                    c(i, l41VarA2);
                }
            }
        }

        public void a(int i, l41 l41Var, Disposition disposition) {
            int iF = l41Var.f();
            l41 l41VarA = a(l41Var);
            a(i, iF);
            if (this.d[iF] < 0 && !a(i, l41VarA)) {
                a(i, disposition, l41VarA);
            }
        }
    }

    public void a(int i, a aVar) {
        a(i, (Object) aVar);
    }
}
