package supwisdom;

import java.util.ArrayList;
import java.util.HashSet;
import supwisdom.gz0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class a01 implements ez0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p41 f6838a;
    public final int[] b;
    public final dz0 c;

    public a01(p41 p41Var, int[] iArr, dz0 dz0Var) {
        if (p41Var == null) {
            throw new NullPointerException("method == null");
        }
        if (iArr == null) {
            throw new NullPointerException("order == null");
        }
        if (dz0Var == null) {
            throw new NullPointerException("addresses == null");
        }
        this.f6838a = p41Var;
        this.b = iArr;
        this.c = dz0Var;
    }

    public static fz0 a(a41 a41Var, dz0 dz0Var) {
        o61 o61VarG = a41Var.g();
        int size = o61VarG.size();
        int iE = a41Var.e();
        d61 d61VarD = a41Var.d().d();
        int size2 = d61VarD.size();
        if (size2 == 0) {
            return fz0.c;
        }
        if ((iE == -1 && size != size2) || (iE != -1 && (size != size2 + 1 || iE != o61VarG.d(size2)))) {
            throw new RuntimeException("shouldn't happen: weird successors list");
        }
        int i = 0;
        while (true) {
            if (i >= size2) {
                break;
            }
            if (d61VarD.getType(i).equals(b61.x)) {
                size2 = i + 1;
                break;
            }
            i++;
        }
        fz0 fz0Var = new fz0(size2);
        for (int i2 = 0; i2 < size2; i2++) {
            fz0Var.a(i2, new w51(d61VarD.getType(i2)), dz0Var.a(o61VarG.d(i2)).e());
        }
        fz0Var.e();
        return fz0Var;
    }

    @Override // supwisdom.ez0
    public HashSet<b61> b() {
        HashSet<b61> hashSet = new HashSet<>(20);
        b41 b41VarB = this.f6838a.b();
        int size = b41VarB.size();
        for (int i = 0; i < size; i++) {
            d61 d61VarD = b41VarB.f(i).d().d();
            int size2 = d61VarD.size();
            for (int i2 = 0; i2 < size2; i2++) {
                hashSet.add(d61VarD.getType(i2));
            }
        }
        return hashSet;
    }

    @Override // supwisdom.ez0
    public gz0 build() {
        return a(this.f6838a, this.b, this.c);
    }

    @Override // supwisdom.ez0
    public boolean a() {
        b41 b41VarB = this.f6838a.b();
        int size = b41VarB.size();
        for (int i = 0; i < size; i++) {
            if (b41VarB.f(i).d().d().size() != 0) {
                return true;
            }
        }
        return false;
    }

    public static gz0.a a(a41 a41Var, a41 a41Var2, fz0 fz0Var, dz0 dz0Var) {
        return new gz0.a(dz0Var.b(a41Var).e(), dz0Var.a(a41Var2).e(), fz0Var);
    }

    public static boolean a(a41 a41Var, a41 a41Var2, dz0 dz0Var) {
        if (a41Var == null) {
            throw new NullPointerException("start == null");
        }
        if (a41Var2 != null) {
            return dz0Var.a(a41Var2).e() - dz0Var.b(a41Var).e() <= 65535;
        }
        throw new NullPointerException("end == null");
    }

    public static gz0 a(p41 p41Var, int[] iArr, dz0 dz0Var) {
        int length = iArr.length;
        b41 b41VarB = p41Var.b();
        ArrayList arrayList = new ArrayList(length);
        fz0 fz0Var = fz0.c;
        a41 a41Var = null;
        a41 a41Var2 = null;
        for (int i : iArr) {
            a41 a41VarG = b41VarB.g(i);
            if (a41VarG.b()) {
                fz0 fz0VarA = a(a41VarG, dz0Var);
                if (fz0Var.size() == 0) {
                    a41Var = a41VarG;
                    a41Var2 = a41Var;
                    fz0Var = fz0VarA;
                } else if (fz0Var.equals(fz0VarA) && a(a41Var, a41VarG, dz0Var)) {
                    a41Var2 = a41VarG;
                } else {
                    if (fz0Var.size() != 0) {
                        arrayList.add(a(a41Var, a41Var2, fz0Var, dz0Var));
                    }
                    a41Var = a41VarG;
                    a41Var2 = a41Var;
                    fz0Var = fz0VarA;
                }
            }
        }
        if (fz0Var.size() != 0) {
            arrayList.add(a(a41Var, a41Var2, fz0Var, dz0Var));
        }
        int size = arrayList.size();
        if (size == 0) {
            return gz0.c;
        }
        gz0 gz0Var = new gz0(size);
        for (int i2 = 0; i2 < size; i2++) {
            gz0Var.a(i2, (gz0.a) arrayList.get(i2));
        }
        gz0Var.e();
        return gz0Var;
    }
}
