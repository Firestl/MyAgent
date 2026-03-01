package supwisdom;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class z51 implements Comparable<z51> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ConcurrentMap<String, z51> f9968e = new ConcurrentHashMap(10000, 0.75f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9969a;
    public final b61 b;
    public final a61 c;
    public a61 d;

    public z51(String str, b61 b61Var, a61 a61Var) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        }
        if (b61Var == null) {
            throw new NullPointerException("returnType == null");
        }
        if (a61Var == null) {
            throw new NullPointerException("parameterTypes == null");
        }
        this.f9969a = str;
        this.b = b61Var;
        this.c = a61Var;
        this.d = null;
    }

    public static z51 a(String str) {
        int i;
        z51 z51Var = f9968e.get(str);
        if (z51Var != null) {
            return z51Var;
        }
        b61[] b61VarArrC = c(str);
        int i2 = 1;
        int i3 = 0;
        while (true) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == ')') {
                b61 b61VarB = b61.b(str.substring(i2 + 1));
                a61 a61Var = new a61(i3);
                for (int i4 = 0; i4 < i3; i4++) {
                    a61Var.a(i4, b61VarArrC[i4]);
                }
                return new z51(str, b61VarB, a61Var);
            }
            int i5 = i2;
            while (cCharAt == '[') {
                i5++;
                cCharAt = str.charAt(i5);
            }
            if (cCharAt == 'L') {
                int iIndexOf = str.indexOf(59, i5);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("bad descriptor");
                }
                i = iIndexOf + 1;
            } else {
                i = i5 + 1;
            }
            b61VarArrC[i3] = b61.a(str.substring(i2, i));
            i3++;
            i2 = i;
        }
    }

    public a61 b() {
        if (this.d == null) {
            int size = this.c.size();
            a61 a61Var = new a61(size);
            boolean z = false;
            for (int i = 0; i < size; i++) {
                b61 b61VarD = this.c.d(i);
                if (b61VarD.j()) {
                    b61VarD = b61.l;
                    z = true;
                }
                a61Var.a(i, b61VarD);
            }
            if (!z) {
                a61Var = this.c;
            }
            this.d = a61Var;
        }
        return this.d;
    }

    public a61 c() {
        return this.c;
    }

    public b61 d() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z51) {
            return this.f9969a.equals(((z51) obj).f9969a);
        }
        return false;
    }

    public int hashCode() {
        return this.f9969a.hashCode();
    }

    public String toString() {
        return this.f9969a;
    }

    public static b61[] c(String str) {
        int length = str.length();
        int i = 0;
        if (str.charAt(0) != '(') {
            throw new IllegalArgumentException("bad descriptor");
        }
        int i2 = 1;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char cCharAt = str.charAt(i2);
            if (cCharAt == ')') {
                i = i2;
                break;
            }
            if (cCharAt >= 'A' && cCharAt <= 'Z') {
                i3++;
            }
            i2++;
        }
        if (i == 0 || i == length - 1) {
            throw new IllegalArgumentException("bad descriptor");
        }
        if (str.indexOf(41, i + 1) == -1) {
            return new b61[i3];
        }
        throw new IllegalArgumentException("bad descriptor");
    }

    public static z51 b(String str) {
        if (str != null) {
            z51 z51Var = f9968e.get(str);
            return z51Var != null ? z51Var : b(a(str));
        }
        throw new NullPointerException("descriptor == null");
    }

    public String a() {
        return this.f9969a;
    }

    public z51 a(b61 b61Var) {
        String str = "(" + b61Var.g() + this.f9969a.substring(1);
        a61 a61VarA = this.c.a(b61Var);
        a61VarA.e();
        return b(new z51(str, this.b, a61VarA));
    }

    public static z51 b(z51 z51Var) {
        z51 z51VarPutIfAbsent = f9968e.putIfAbsent(z51Var.a(), z51Var);
        return z51VarPutIfAbsent != null ? z51VarPutIfAbsent : z51Var;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(z51 z51Var) {
        if (this == z51Var) {
            return 0;
        }
        int iCompareTo = this.b.compareTo(z51Var.b);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int size = this.c.size();
        int size2 = z51Var.c.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo2 = this.c.d(i).compareTo(z51Var.c.d(i));
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (size < size2) {
            return -1;
        }
        return size > size2 ? 1 : 0;
    }
}
