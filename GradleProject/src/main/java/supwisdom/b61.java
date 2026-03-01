package supwisdom;

import com.taobao.weex.el.parse.Operators;
import com.tencent.qphone.base.util.QLog;
import dc.squareup.okhttp3.HttpUrl;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class b61 implements c61, Comparable<b61> {
    public static final b61 A;
    public static final b61 B;
    public static final b61 C;
    public static final b61 D;
    public static final b61 E;
    public static final b61 F;
    public static final b61 G;
    public static final b61 H;
    public static final b61 I;
    public static final b61 J;
    public static final b61 K;
    public static final b61 L;
    public static final b61 M;
    public static final b61 N;
    public static final b61 O;
    public static final b61 P;
    public static final b61 Q;
    public static final b61 R;
    public static final b61 S;
    public static final ConcurrentMap<String, b61> f = new ConcurrentHashMap(10000, 0.75f);
    public static final b61 g;
    public static final b61 h;
    public static final b61 i;
    public static final b61 j;
    public static final b61 k;
    public static final b61 l;
    public static final b61 m;
    public static final b61 n;
    public static final b61 o;
    public static final b61 p;
    public static final b61 q;
    public static final b61 r;
    public static final b61 s;
    public static final b61 t;
    public static final b61 u;
    public static final b61 v;
    public static final b61 w;
    public static final b61 x;
    public static final b61 y;
    public static final b61 z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7029a;
    public final int b;
    public String c;
    public b61 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b61 f7030e;

    static {
        b61 b61Var = new b61("Z", 1);
        g = b61Var;
        b61 b61Var2 = new b61("B", 2);
        h = b61Var2;
        b61 b61Var3 = new b61("C", 3);
        i = b61Var3;
        b61 b61Var4 = new b61(QLog.TAG_REPORTLEVEL_DEVELOPER, 4);
        j = b61Var4;
        b61 b61Var5 = new b61("F", 5);
        k = b61Var5;
        b61 b61Var6 = new b61("I", 6);
        l = b61Var6;
        b61 b61Var7 = new b61("J", 7);
        m = b61Var7;
        b61 b61Var8 = new b61("S", 8);
        n = b61Var8;
        o = new b61("V", 0);
        p = new b61("<null>", 9);
        q = new b61("<addr>", 10);
        r = new b61("Ljava/lang/annotation/Annotation;", 9);
        s = new b61("Ljava/lang/Class;", 9);
        t = new b61("Ljava/lang/Cloneable;", 9);
        u = new b61("Ljava/lang/invoke/MethodHandle;", 9);
        v = new b61("Ljava/lang/invoke/MethodType;", 9);
        w = new b61("Ljava/lang/invoke/VarHandle;", 9);
        b61 b61Var9 = new b61("Ljava/lang/Object;", 9);
        x = b61Var9;
        y = new b61("Ljava/io/Serializable;", 9);
        z = new b61("Ljava/lang/String;", 9);
        A = new b61("Ljava/lang/Throwable;", 9);
        B = new b61("Ljava/lang/Boolean;", 9);
        C = new b61("Ljava/lang/Byte;", 9);
        D = new b61("Ljava/lang/Character;", 9);
        E = new b61("Ljava/lang/Double;", 9);
        F = new b61("Ljava/lang/Float;", 9);
        G = new b61("Ljava/lang/Integer;", 9);
        H = new b61("Ljava/lang/Long;", 9);
        I = new b61("Ljava/lang/Short;", 9);
        J = new b61("Ljava/lang/Void;", 9);
        K = new b61(Operators.ARRAY_START_STR + b61Var.f7029a, 9);
        L = new b61(Operators.ARRAY_START_STR + b61Var2.f7029a, 9);
        M = new b61(Operators.ARRAY_START_STR + b61Var3.f7029a, 9);
        N = new b61(Operators.ARRAY_START_STR + b61Var4.f7029a, 9);
        O = new b61(Operators.ARRAY_START_STR + b61Var5.f7029a, 9);
        P = new b61(Operators.ARRAY_START_STR + b61Var6.f7029a, 9);
        Q = new b61(Operators.ARRAY_START_STR + b61Var7.f7029a, 9);
        R = new b61(Operators.ARRAY_START_STR + b61Var9.f7029a, 9);
        S = new b61(Operators.ARRAY_START_STR + b61Var8.f7029a, 9);
        l();
    }

    public b61(String str, int i2, int i3) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        }
        if (i2 < 0 || i2 >= 11) {
            throw new IllegalArgumentException("bad basicType");
        }
        if (i3 < -1) {
            throw new IllegalArgumentException("newAt < -1");
        }
        this.f7029a = str;
        this.b = i2;
        this.d = null;
        this.f7030e = null;
    }

    public static void l() {
        b(g);
        b(h);
        b(i);
        b(j);
        b(k);
        b(l);
        b(m);
        b(n);
        b(r);
        b(s);
        b(t);
        b(u);
        b(w);
        b(x);
        b(y);
        b(z);
        b(A);
        b(B);
        b(C);
        b(D);
        b(E);
        b(F);
        b(G);
        b(H);
        b(I);
        b(J);
        b(K);
        b(L);
        b(M);
        b(N);
        b(O);
        b(P);
        b(Q);
        b(R);
        b(S);
    }

    @Override // supwisdom.c61
    public int a() {
        return this.b;
    }

    @Override // supwisdom.c61
    public int b() {
        int i2 = this.b;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 6 || i2 == 8) {
            return 6;
        }
        return i2;
    }

    public b61 c() {
        if (this.d == null) {
            this.d = b(new b61(Operators.ARRAY_START + this.f7029a, 9));
        }
        return this.d;
    }

    public int d() {
        int i2 = this.b;
        return (i2 == 4 || i2 == 7) ? 2 : 1;
    }

    public String e() {
        if (this.c == null) {
            if (!k()) {
                throw new IllegalArgumentException("not an object type: " + this.f7029a);
            }
            if (this.f7029a.charAt(0) == '[') {
                this.c = this.f7029a;
            } else {
                String str = this.f7029a;
                this.c = str.substring(1, str.length() - 1);
            }
        }
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b61) {
            return this.f7029a.equals(((b61) obj).f7029a);
        }
        return false;
    }

    public b61 f() {
        if (this.f7030e == null) {
            if (this.f7029a.charAt(0) != '[') {
                throw new IllegalArgumentException("not an array type: " + this.f7029a);
            }
            this.f7030e = a(this.f7029a.substring(1));
        }
        return this.f7030e;
    }

    public String g() {
        return this.f7029a;
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return this;
    }

    public boolean h() {
        return this.f7029a.charAt(0) == '[';
    }

    public int hashCode() {
        return this.f7029a.hashCode();
    }

    public boolean i() {
        int i2 = this.b;
        return i2 == 4 || i2 == 7;
    }

    public boolean j() {
        int i2 = this.b;
        return i2 == 1 || i2 == 2 || i2 == 3 || i2 == 6 || i2 == 8;
    }

    public boolean k() {
        return this.b == 9;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        switch (this.b) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "byte";
            case 3:
                return "char";
            case 4:
                return "double";
            case 5:
                return "float";
            case 6:
                return "int";
            case 7:
                return "long";
            case 8:
                return "short";
            case 9:
                if (!h()) {
                    return e().replace("/", Operators.DOT_STR);
                }
                return f().toHuman() + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            default:
                return this.f7029a;
        }
    }

    public String toString() {
        return this.f7029a;
    }

    public static b61 a(String str) {
        b61 b61Var = f.get(str);
        if (b61Var != null) {
            return b61Var;
        }
        try {
            char cCharAt = str.charAt(0);
            if (cCharAt == '[') {
                return a(str.substring(1)).c();
            }
            int length = str.length();
            if (cCharAt == 'L') {
                int i2 = length - 1;
                if (str.charAt(i2) == ';') {
                    for (int i3 = 1; i3 < i2; i3++) {
                        char cCharAt2 = str.charAt(i3);
                        if (cCharAt2 != '(' && cCharAt2 != ')' && cCharAt2 != '.') {
                            if (cCharAt2 == '/') {
                                if (i3 == 1 || i3 == i2 || str.charAt(i3 - 1) == '/') {
                                    throw new IllegalArgumentException("bad descriptor: " + str);
                                }
                            } else if (cCharAt2 == ';' || cCharAt2 == '[') {
                            }
                        }
                        throw new IllegalArgumentException("bad descriptor: " + str);
                    }
                    return b(new b61(str, 9));
                }
            }
            throw new IllegalArgumentException("bad descriptor: " + str);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("descriptor is empty");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public static b61 b(String str) {
        try {
            return str.equals("V") ? o : a(str);
        } catch (NullPointerException unused) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public static b61 b(b61 b61Var) {
        b61 b61VarPutIfAbsent = f.putIfAbsent(b61Var.g(), b61Var);
        return b61VarPutIfAbsent != null ? b61VarPutIfAbsent : b61Var;
    }

    public b61(String str, int i2) {
        this(str, i2, -1);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(b61 b61Var) {
        return this.f7029a.compareTo(b61Var.f7029a);
    }
}
