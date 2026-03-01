package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class u51 extends l51 {
    public static final u51 b = a((short) 0);

    public u51(short s) {
        super(s);
    }

    public static u51 a(short s) {
        return new u51(s);
    }

    @Override // supwisdom.u41
    public String c() {
        return "short";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.n;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Integer.toString(e());
    }

    public String toString() {
        int iE = e();
        return "short{0x" + m61.d(iE) + " / " + iE + Operators.BLOCK_END;
    }
}
