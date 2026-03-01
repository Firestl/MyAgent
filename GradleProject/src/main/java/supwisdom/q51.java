package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class q51 extends x51 {
    public static final String[] c = {"static-put", "static-get", "instance-put", "instance-get", "invoke-static", "invoke-instance", "invoke-constructor", "invoke-direct", "invoke-interface"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8880a;
    public final u41 b;

    public static String a(int i) {
        return c[i];
    }

    public static boolean b(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3;
    }

    public static boolean c(int i) {
        switch (i) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        q51 q51Var = (q51) u41Var;
        return d() == q51Var.d() ? e().compareTo(q51Var.e()) : Integer.compare(d(), q51Var.d());
    }

    @Override // supwisdom.u41
    public String c() {
        return "method handle";
    }

    public int d() {
        return this.f8880a;
    }

    public u41 e() {
        return this.b;
    }

    public boolean f() {
        return b(this.f8880a);
    }

    public boolean g() {
        return c(this.f8880a);
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.u;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return a(this.f8880a) + "," + this.b.toString();
    }

    public String toString() {
        return "method-handle{" + toHuman() + Operators.BLOCK_END_STR;
    }
}
