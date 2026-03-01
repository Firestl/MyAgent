package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class h51 extends l51 {
    public static final h51[] b = new h51[511];
    public static final h51 c;

    static {
        a(-1);
        c = a(0);
        a(1);
        a(2);
        a(3);
        a(4);
        a(5);
    }

    public h51(int i) {
        super(i);
    }

    public static h51 a(int i) {
        h51[] h51VarArr = b;
        int length = (Integer.MAX_VALUE & i) % h51VarArr.length;
        h51 h51Var = h51VarArr[length];
        if (h51Var != null && h51Var.g() == i) {
            return h51Var;
        }
        h51 h51Var2 = new h51(i);
        h51VarArr[length] = h51Var2;
        return h51Var2;
    }

    @Override // supwisdom.u41
    public String c() {
        return "int";
    }

    public int g() {
        return e();
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.l;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Integer.toString(e());
    }

    public String toString() {
        int iE = e();
        return "int{0x" + m61.g(iE) + " / " + iE + Operators.BLOCK_END;
    }
}
